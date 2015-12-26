package com.android.mms.transaction;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Mms.Inbox;
import com.android.mms.MmsConfig;
import com.android.mms.util.DownloadManager;
import com.android.mms.util.MSimUtils;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.DeliveryInd;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.MiuiPduParser;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.NotificationInd;
import com.xiaomi.mms.utils.logger.MyLog;
import miui.os.Build;

class PushReceiver$MmsPushTask
  extends AsyncTask<Intent, Void, Void>
{
  private Context mContext;
  
  public PushReceiver$MmsPushTask(PushReceiver paramPushReceiver, Context paramContext)
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
            localObject1 = PushReceiver.access$000(mContext, paramVarArgs, i);
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
    } while (PushReceiver.access$100(mContext, localNotificationInd));
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

/* Location:
 * Qualified Name:     com.android.mms.transaction.PushReceiver.MmsPushTask
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */