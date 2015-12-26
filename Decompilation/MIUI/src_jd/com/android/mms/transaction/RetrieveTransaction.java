package com.android.mms.transaction;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.MmsSms;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.LogTag;
import com.android.mms.MmsConfig;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.DownloadManager;
import com.android.mms.util.MSimUtils;
import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.AcknowledgeInd;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduParser;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.PduComposer;
import com.google.android.mms.pdu.RetrieveConf;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.IOException;
import miui.os.Build;
import miui.telephony.TelephonyManager;

public class RetrieveTransaction
  extends Transaction
  implements Runnable
{
  static final String[] PROJECTION = { "ct_l", "locked", "m_size" };
  private final String mContentLocation;
  private boolean mLocked;
  private long mMessageSize;
  private final Uri mOldUri;
  private final Uri mUri;
  
  public RetrieveTransaction(Context paramContext, TransactionSettings paramTransactionSettings, String paramString)
    throws MmsException
  {
    super(paramContext, paramTransactionSettings);
    if (paramString.startsWith("content://"))
    {
      mOldUri = Uri.parse(paramString);
      mUri = mOldUri.buildUpon().appendQueryParameter("blocked_flag", "2").build();
      mTransactionState.setContentUri(mUri);
      paramContext = getContentLocation(paramContext, mUri);
      mContentLocation = paramContext;
      mId = paramContext;
      return;
    }
    throw new IllegalArgumentException("Initializing from X-Mms-Content-Location is abandoned!");
  }
  
  private String getContentLocation(Context paramContext, Uri paramUri)
    throws MmsException
  {
    paramContext = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), paramUri, PROJECTION, null, null, null);
    mLocked = false;
    if (paramContext != null) {
      try
      {
        if ((paramContext.getCount() == 1) && (paramContext.moveToFirst()))
        {
          if (paramContext.getInt(1) == 1) {}
          for (boolean bool = true;; bool = false)
          {
            mLocked = bool;
            mMessageSize = paramContext.getLong(2);
            paramUri = paramContext.getString(0);
            return paramUri;
          }
        }
      }
      finally
      {
        paramContext.close();
      }
    }
    throw new MmsException("Cannot get X-Mms-Content-Location from: " + paramUri);
  }
  
  private boolean isDuplicateMessage(Context paramContext, RetrieveConf paramRetrieveConf)
  {
    paramRetrieveConf = paramRetrieveConf.getMessageId();
    if (paramRetrieveConf != null)
    {
      paramRetrieveConf = new String(paramRetrieveConf);
      String str = "(m_id = ? AND m_type = ?) AND sim_id=" + mSimId;
      paramContext = SqliteWrapper.query(paramContext, paramContext.getContentResolver(), Telephony.Mms.CONTENT_URI, new String[] { "_id" }, str, new String[] { paramRetrieveConf, String.valueOf(132) }, null);
      if (paramContext != null)
      {
        try
        {
          int i = paramContext.getCount();
          if (i > 0) {
            return true;
          }
        }
        finally
        {
          paramContext.close();
        }
        paramContext.close();
      }
    }
    return false;
  }
  
  private void sendAcknowledgeInd(RetrieveConf paramRetrieveConf)
    throws MmsException, IOException
  {
    paramRetrieveConf = paramRetrieveConf.getTransactionId();
    AcknowledgeInd localAcknowledgeInd;
    int i;
    if (paramRetrieveConf != null)
    {
      localAcknowledgeInd = new AcknowledgeInd(18, paramRetrieveConf);
      i = MSimUtils.getSlotIdBySimInfoId(mSimId);
      String str = TelephonyManager.getDefault().getLine1NumberForSlot(i);
      paramRetrieveConf = str;
      if (TextUtils.isEmpty(str))
      {
        Log.e("RetrieveTransaction", "lineNumber is empty");
        paramRetrieveConf = "";
      }
      localAcknowledgeInd.setFrom(new EncodedStringValue(paramRetrieveConf));
      if (Build.IS_CM_CUSTOMIZATION_TEST)
      {
        if (!MessageUtils.getSendDeliverReportAllowed(mContext, mSimId)) {
          break label133;
        }
        i = 128;
      }
    }
    try
    {
      for (;;)
      {
        localAcknowledgeInd.setReportAllowed(i);
        if (!MmsConfig.getNotifyWapMMSC()) {
          break;
        }
        sendPdu(new PduComposer(mContext, localAcknowledgeInd).make(), mContentLocation, null);
        return;
        label133:
        i = 129;
      }
    }
    catch (InvalidHeaderValueException paramRetrieveConf)
    {
      for (;;)
      {
        Log.e("RetrieveTransaction", "acknowledgeInd.setReportAllowed Failed !!");
      }
      sendPdu(new PduComposer(mContext, localAcknowledgeInd).make(), null);
    }
  }
  
  private static void updateContentLocation(Context paramContext, Uri paramUri, String paramString, boolean paramBoolean)
  {
    ContentValues localContentValues = new ContentValues(2);
    localContentValues.put("ct_l", paramString);
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      localContentValues.put("locked", Integer.valueOf(i));
      SqliteWrapper.update(paramContext, paramContext.getContentResolver(), paramUri, localContentValues, null, null);
      return;
    }
  }
  
  public int getType()
  {
    return 1;
  }
  
  public void onEnqueue()
  {
    DownloadManager.getInstance().markState(mUri, 129, mSimId);
    mContext.getContentResolver().notifyChange(Telephony.MmsSms.CONTENT_CONVERSATIONS_URI, null);
  }
  
  public void process()
  {
    new Thread(this).start();
  }
  
  public void run()
  {
    sCurrentTransactionMsgId = Long.valueOf(mUri.getLastPathSegment()).longValue();
    sCurrentTransactionProgress = 0;
    for (;;)
    {
      RetrieveConf localRetrieveConf;
      int i;
      try
      {
        localRetrieveConf = (RetrieveConf)new MiuiPduParser(getPdu(mContentLocation, new ProgressReceiver()
        {
          public void onProgress(long paramAnonymousLong1, long paramAnonymousLong2)
          {
            if (paramAnonymousLong1 < 0L) {
              return;
            }
            long l = paramAnonymousLong2;
            if (paramAnonymousLong2 <= 0L) {
              l = mMessageSize;
            }
            paramAnonymousLong2 = l;
            if (l <= 0L) {
              paramAnonymousLong2 = MmsConfig.getMaxMessageSize();
            }
            l = paramAnonymousLong1;
            if (paramAnonymousLong1 > paramAnonymousLong2) {
              l = paramAnonymousLong2;
            }
            Transaction.sCurrentTransactionProgress = (int)(100L * l / paramAnonymousLong2);
            mContext.getContentResolver().notifyChange(Telephony.MmsSms.CONTENT_CONVERSATIONS_URI, null);
          }
        })).parse();
        if (localRetrieveConf == null) {
          throw new MmsException("Invalid M-Retrieve.conf PDU.");
        }
      }
      catch (Throwable localThrowable)
      {
        MyLog.e(LogTag.logFormat("Exception: %s", new Object[] { Log.getStackTraceString(localThrowable) }));
        return;
        if (isDuplicateMessage(mContext, localRetrieveConf))
        {
          SqliteWrapper.delete(mContext, mContext.getContentResolver(), mUri, null, null);
          mTransactionState.setState(2);
          mTransactionState.setContentUri(mUri);
          sendAcknowledgeInd(localRetrieveConf);
          if (mTransactionState.getState() == 1) {
            continue;
          }
          mTransactionState.setState(2);
          mTransactionState.setContentUri(mUri);
          MyLog.e(LogTag.logFormat("Retrieval failed.", new Object[0]));
          continue;
        }
        Object localObject1 = MiuiPduPersister.getPduPersister(mContext);
        i = MessageUtils.getMmsBlockTypeByUri(mContext, mUri);
        if (i > 1)
        {
          Object localObject3 = mOldUri.buildUpon().appendQueryParameter("blocked_flag", "1").build();
          localObject1 = ((MiuiPduPersister)localObject1).persist(localRetrieveConf, Telephony.Mms.Inbox.CONTENT_URI, (Uri)localObject3, -1L, i);
          localObject3 = new ContentValues();
          ((ContentValues)localObject3).put("sim_id", Long.valueOf(mSimId));
          ((ContentValues)localObject3).put("m_size", Long.valueOf(mMessageSize));
          SqliteWrapper.update(mContext, mContext.getContentResolver(), (Uri)localObject1, (ContentValues)localObject3, null, null);
          mTransactionState.setState(1);
          mTransactionState.setContentUri((Uri)localObject1);
          updateContentLocation(mContext, (Uri)localObject1, mContentLocation, mLocked);
          continue;
        }
      }
      finally
      {
        if (mTransactionState.getState() != 1)
        {
          mTransactionState.setState(2);
          mTransactionState.setContentUri(mUri);
          MyLog.e(LogTag.logFormat("Retrieval failed.", new Object[0]));
        }
        notifyObservers();
      }
      Uri localUri = ((MiuiPduPersister)localObject2).persist(localRetrieveConf, Telephony.Mms.Inbox.CONTENT_URI, mUri, -1L, i);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.RetrieveTransaction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */