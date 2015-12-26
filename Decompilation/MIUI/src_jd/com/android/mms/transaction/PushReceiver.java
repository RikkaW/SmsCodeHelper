package com.android.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.preference.PreferenceManager;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Mms.Inbox;
import android.text.TextUtils;
import com.android.mms.MmsConfig;
import com.android.mms.jwap_port.WBXMLDecoder;
import com.android.mms.util.DownloadManager;
import com.android.mms.util.MSimUtils;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.DeliveryInd;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.MiuiPduParser;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.NotificationInd;
import com.google.android.mms.pdu.ReadOrigInd;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.ByteArrayInputStream;
import miui.os.Build;
import miui.provider.ExtraTelephony;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class PushReceiver
  extends BroadcastReceiver
{
  private static long[] findThreadId(Context paramContext, GenericPdu paramGenericPdu, int paramInt)
  {
    if (paramInt == 134) {
      paramGenericPdu = new String(((DeliveryInd)paramGenericPdu).getMessageId());
    }
    for (;;)
    {
      Object localObject = new StringBuilder(40);
      ((StringBuilder)localObject).append("m_id");
      ((StringBuilder)localObject).append('=');
      ((StringBuilder)localObject).append(DatabaseUtils.sqlEscapeString(paramGenericPdu));
      ((StringBuilder)localObject).append(" AND ");
      ((StringBuilder)localObject).append("m_type");
      ((StringBuilder)localObject).append('=');
      ((StringBuilder)localObject).append(128);
      paramGenericPdu = new long[2];
      paramGenericPdu[0] = -1L;
      paramGenericPdu[1] = -1L;
      ContentResolver localContentResolver = paramContext.getContentResolver();
      Uri localUri = Telephony.Mms.CONTENT_URI;
      localObject = ((StringBuilder)localObject).toString();
      paramContext = SqliteWrapper.query(paramContext, localContentResolver, localUri, new String[] { "thread_id", "_id" }, (String)localObject, null, null);
      if (paramContext != null) {}
      try
      {
        if ((paramContext.getCount() == 1) && (paramContext.moveToFirst()))
        {
          paramGenericPdu[0] = paramContext.getLong(0);
          paramGenericPdu[1] = paramContext.getLong(1);
          return paramGenericPdu;
          paramGenericPdu = new String(((ReadOrigInd)paramGenericPdu).getMessageId());
          continue;
        }
        return paramGenericPdu;
      }
      finally
      {
        paramContext.close();
      }
    }
  }
  
  private static boolean isDuplicateNotification(Context paramContext, NotificationInd paramNotificationInd)
  {
    paramNotificationInd = paramNotificationInd.getContentLocation();
    if (paramNotificationInd != null)
    {
      paramNotificationInd = new String(paramNotificationInd);
      paramContext = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), Telephony.Mms.CONTENT_URI, new String[] { "_id" }, "ct_l = ?", new String[] { paramNotificationInd }, null);
      if (paramContext == null) {}
    }
    try
    {
      int i = paramContext.getCount();
      return i > 0;
    }
    finally
    {
      paramContext.close();
    }
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    processReceive(paramContext, paramIntent);
  }
  
  protected boolean processReceive(Context paramContext, Intent paramIntent)
  {
    if ("android.provider.Telephony.WAP_PUSH_DELIVER".equals(paramIntent.getAction()))
    {
      String str = paramIntent.getType();
      if ("application/vnd.wap.mms-message".equals(str))
      {
        ((PowerManager)paramContext.getSystemService("power")).newWakeLock(1, "MMS PushReceiver").acquire(5000L);
        new MmsPushTask(paramContext).execute(new Intent[] { paramIntent });
        return true;
      }
      if ((("application/vnd.wap.sic".equals(str)) || ("application/vnd.wap.slc".equals(str))) && (PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_allow_si_sl_push", true)))
      {
        new SiSlPushTask(paramContext).execute(new Intent[] { paramIntent });
        return true;
      }
    }
    return false;
  }
  
  private class MmsPushTask
    extends AsyncTask<Intent, Void, Void>
  {
    private Context mContext;
    
    public MmsPushTask(Context paramContext)
    {
      mContext = paramContext;
    }
    
    private void updateDeliveryStatus(long paramLong, GenericPdu paramGenericPdu)
    {
      if (paramLong < 0L) {
        MyLog.e("PushReceiver", "update delivery status is failed for msgId < 0");
      }
      Uri localUri;
      ContentValues localContentValues;
      do
      {
        return;
        localUri = Uri.parse(Telephony.Mms.CONTENT_URI + "/" + paramLong);
        localContentValues = new ContentValues();
        localContentValues.put("st", Integer.valueOf(((DeliveryInd)paramGenericPdu).getStatus()));
      } while (SqliteWrapper.update(mContext, mContext.getContentResolver(), localUri, localContentValues, null, null) > 0);
      MyLog.e("PushReceiver", "update delivery status is failed for msgId is " + paramLong);
    }
    
    protected Void doInBackground(Intent... paramVarArgs)
    {
      Object localObject1 = paramVarArgs[0];
      paramVarArgs = new MiuiPduParser(((Intent)localObject1).getByteArrayExtra("data")).parse();
      if (paramVarArgs == null) {
        MyLog.e("PushReceiver", "Invalid PUSH data");
      }
      Object localObject2;
      ContentResolver localContentResolver;
      long l;
      NotificationInd localNotificationInd;
      do
      {
        int i;
        do
        {
          return null;
          localObject2 = MiuiPduPersister.getPduPersister(mContext);
          localContentResolver = mContext.getContentResolver();
          i = paramVarArgs.getMessageType();
          switch (i)
          {
          default: 
          case 134: 
          case 136: 
            try
            {
              MyLog.e("PushReceiver", "Received unrecognized PDU.");
              return null;
            }
            catch (MmsException paramVarArgs)
            {
              MyLog.e("PushReceiver", "Failed to save the data from PUSH: type=" + i, paramVarArgs);
              return null;
              localObject1 = PushReceiver.findThreadId(mContext, paramVarArgs, i);
              l = localObject1[0];
              if (l == -1L)
              {
                MyLog.e("PushReceiver", "delivery or read orig ind threadId == -1");
                return null;
              }
            }
            catch (RuntimeException paramVarArgs)
            {
              MyLog.e("PushReceiver", "Unexpected RuntimeException.", paramVarArgs);
              return null;
            }
            if (((MiuiPduPersister)localObject2).persist(paramVarArgs, Telephony.Mms.Inbox.CONTENT_URI, null, l) == null) {
              MyLog.e("PushReceiver", "delivery or read orig ind gen uri is null");
            }
            break;
          }
        } while (i != 134);
        updateDeliveryStatus(localObject1[1], paramVarArgs);
        return null;
        localNotificationInd = (NotificationInd)paramVarArgs;
        if (MmsConfig.getTransIdEnabled())
        {
          byte[] arrayOfByte1 = localNotificationInd.getContentLocation();
          if (61 == arrayOfByte1[(arrayOfByte1.length - 1)])
          {
            byte[] arrayOfByte2 = localNotificationInd.getTransactionId();
            byte[] arrayOfByte3 = new byte[arrayOfByte1.length + arrayOfByte2.length];
            System.arraycopy(arrayOfByte1, 0, arrayOfByte3, 0, arrayOfByte1.length);
            System.arraycopy(arrayOfByte2, 0, arrayOfByte3, arrayOfByte1.length, arrayOfByte2.length);
            localNotificationInd.setContentLocation(arrayOfByte3);
          }
        }
      } while (PushReceiver.isDuplicateNotification(mContext, localNotificationInd));
      int j = ((Intent)localObject1).getIntExtra("blockType", 0);
      paramVarArgs = ((MiuiPduPersister)localObject2).persist(paramVarArgs, Telephony.Mms.Inbox.CONTENT_URI, null, -1L, j);
      if (j > 1) {}
      for (paramVarArgs = paramVarArgs.buildUpon().appendQueryParameter("blocked_flag", "1").build();; paramVarArgs = paramVarArgs.buildUpon().appendQueryParameter("blocked_flag", "0").build())
      {
        localObject2 = new ContentValues();
        j = MSimUtils.getSlotIdFromIntent((Intent)localObject1);
        l = MSimUtils.getSimIdBySlotId(j);
        if (l >= 0L) {
          break;
        }
        MyLog.e("PushReceiver", "Cannot get sim id for slot " + j);
        return null;
      }
      ((ContentValues)localObject2).put("sim_id", Long.valueOf(l));
      SqliteWrapper.update(mContext, localContentResolver, paramVarArgs, (ContentValues)localObject2, null, null);
      if (Build.IS_CM_CUSTOMIZATION_TEST) {
        DownloadManager.getInstance().setOutOfMemory(false);
      }
      localObject1 = new Intent(mContext, TransactionService.class);
      ((Intent)localObject1).putExtra("uri", paramVarArgs.toString());
      ((Intent)localObject1).putExtra("type", 0);
      mContext.startService((Intent)localObject1);
      return null;
    }
  }
  
  private class SiSlPushTask
    extends AsyncTask<Intent, Void, Void>
  {
    private String mAddress;
    private StringBuilder mBody;
    private Context mContext;
    
    public SiSlPushTask(Context paramContext)
    {
      mContext = paramContext;
    }
    
    private void appendNewLine(String paramString)
    {
      if (mBody.length() > 0) {
        mBody.append('\n');
      }
      for (;;)
      {
        mBody.append(paramString);
        return;
        mBody.append(mContext.getResources().getString(2131362146));
      }
    }
    
    private void storeWapPushMessage(int paramInt, long paramLong)
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("address", mAddress);
      localContentValues.put("protocol", Integer.valueOf(0));
      localContentValues.put("read", Integer.valueOf(0));
      localContentValues.put("status", Integer.valueOf(-1));
      localContentValues.put("body", mBody.toString());
      localContentValues.put("block_type", Integer.valueOf(paramInt));
      localContentValues.put("sim_id", Long.valueOf(paramLong));
      mContext.getContentResolver().insert(Uri.parse("content://sms/inbox"), localContentValues);
    }
    
    private void traverse(Node paramNode)
    {
      for (Node localNode = paramNode.getFirstChild(); localNode != null; localNode = localNode.getNextSibling()) {
        traverse(localNode);
      }
      if (paramNode.getNodeType() == 3) {
        appendNewLine(paramNode.getNodeValue());
      }
      paramNode = paramNode.getAttributes();
      if (paramNode != null)
      {
        paramNode = paramNode.getNamedItem("href");
        if (paramNode != null)
        {
          paramNode = paramNode.getNodeValue();
          if ((paramNode != null) && (paramNode.length() > 0)) {
            appendNewLine(paramNode);
          }
        }
      }
    }
    
    protected Void doInBackground(Intent... paramVarArgs)
    {
      MyLog.d("PushReceiver", "ReceiveWapPushTask doInBackground");
      paramVarArgs = paramVarArgs[0];
      byte[] arrayOfByte = paramVarArgs.getByteArrayExtra("data");
      mAddress = paramVarArgs.getStringExtra("address");
      int i = MSimUtils.getSlotIdFromIntent(paramVarArgs);
      long l = MSimUtils.getSimIdBySlotId(i);
      if (l < 0L) {
        MyLog.e("PushReceiver", "Cannot get sim id for slot " + i);
      }
      do
      {
        do
        {
          do
          {
            return null;
            if (TextUtils.isEmpty(mAddress)) {
              mAddress = mContext.getResources().getString(2131362146);
            }
            paramVarArgs = new WBXMLDecoder(mContext).decode(new ByteArrayInputStream(arrayOfByte));
          } while (paramVarArgs == null);
          mBody = new StringBuilder();
          traverse(paramVarArgs);
        } while (mBody.length() <= 0);
        paramVarArgs = mBody.toString();
        i = ExtraTelephony.getSmsBlockType(mContext, mAddress, paramVarArgs, i);
        storeWapPushMessage(i, l);
      } while (i > 1);
      MessagingNotification.blockingUpdateNewMessageIndicator(mContext, true, false);
      return null;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.PushReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */