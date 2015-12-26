package com.android.mms.transaction;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.MmsSms;
import android.util.Log;
import com.android.mms.LogTag;
import com.android.mms.MmsConfig;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.DownloadManager;
import com.android.mms.util.MSimUtils;
import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.MiuiPduParser;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.NotificationInd;
import com.google.android.mms.pdu.NotifyRespInd;
import com.google.android.mms.pdu.PduComposer;
import com.google.android.mms.pdu.RetrieveConf;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.IOException;
import miui.os.Build;
import miui.telephony.TelephonyManager;

public class NotificationTransaction
  extends Transaction
  implements Runnable
{
  private String mContentLocation;
  private NotificationInd mNotificationInd;
  public boolean mShouldDownload;
  private Uri mUri;
  
  public NotificationTransaction(Context paramContext, TransactionSettings paramTransactionSettings, NotificationInd paramNotificationInd)
  {
    super(paramContext, paramTransactionSettings);
    try
    {
      mUri = MiuiPduPersister.getPduPersister(paramContext).persist(paramNotificationInd, Telephony.Mms.Inbox.CONTENT_URI, null, -1L);
      mNotificationInd = paramNotificationInd;
      mId = new String(paramNotificationInd.getTransactionId());
      return;
    }
    catch (MmsException paramContext)
    {
      MyLog.e(LogTag.logFormat("Failed to save NotificationInd in constructor.\nStack:%s", new Object[] { Log.getStackTraceString(paramContext) }));
      throw new IllegalArgumentException();
    }
  }
  
  public NotificationTransaction(Context paramContext, TransactionSettings paramTransactionSettings, String paramString)
  {
    super(paramContext, paramTransactionSettings);
    mUri = Uri.parse(paramString);
    mTransactionState.setContentUri(mUri);
    try
    {
      mNotificationInd = ((NotificationInd)MiuiPduPersister.getPduPersister(paramContext).load(mUri));
      mId = new String(mNotificationInd.getTransactionId());
      mContentLocation = new String(mNotificationInd.getContentLocation());
      return;
    }
    catch (MmsException paramContext)
    {
      MyLog.e(LogTag.logFormat("Failed to load NotificationInd from: %s\nStack:%s", new Object[] { paramString, Log.getStackTraceString(paramContext) }));
      throw new IllegalArgumentException();
    }
  }
  
  private void sendNotifyRespInd(int paramInt)
    throws MmsException, IOException
  {
    NotifyRespInd localNotifyRespInd = new NotifyRespInd(18, mNotificationInd.getTransactionId(), paramInt);
    if (Build.IS_CM_CUSTOMIZATION_TEST)
    {
      if (!MessageUtils.getSendDeliverReportAllowed(mContext, mSimId)) {
        break label79;
      }
      paramInt = 128;
    }
    try
    {
      for (;;)
      {
        localNotifyRespInd.setReportAllowed(paramInt);
        if (!MmsConfig.getNotifyWapMMSC()) {
          break;
        }
        sendPdu(new PduComposer(mContext, localNotifyRespInd).make(), mContentLocation, null);
        return;
        label79:
        paramInt = 129;
      }
    }
    catch (InvalidHeaderValueException localInvalidHeaderValueException)
    {
      for (;;)
      {
        Log.e("NotificationTransaction", "acknowledgeInd.setReportAllowed Failed !!");
      }
      sendPdu(new PduComposer(mContext, localNotifyRespInd).make(), null);
    }
  }
  
  public int getType()
  {
    return 0;
  }
  
  public void onEnqueue()
  {
    DownloadManager localDownloadManager = DownloadManager.getInstance();
    mShouldDownload = localDownloadManager.isAuto(mSimId);
    if (MessageUtils.hasBlockedFlag(mUri)) {
      mShouldDownload = false;
    }
    Uri localUri = mUri;
    if (mShouldDownload) {}
    for (int i = 129;; i = 128)
    {
      localDownloadManager.markState(localUri, i, mSimId);
      mContext.getContentResolver().notifyChange(Telephony.MmsSms.CONTENT_CONVERSATIONS_URI, null);
      return;
    }
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
      try
      {
        LogTag.verbose("Notification transaction launched: %s", new Object[] { this });
        i = 131;
        if (mShouldDownload)
        {
          int j = MSimUtils.getSlotIdBySimInfoId(mSimId);
          if (TelephonyManager.getDefault().getDataStateForSlot(j) == 3) {
            continue;
          }
          bool = true;
          mShouldDownload = bool;
        }
        if (mShouldDownload) {
          continue;
        }
        sendNotifyRespInd(131);
        mTransactionState.setContentUri(mUri);
        if (!mShouldDownload) {
          mTransactionState.setState(1);
        }
        if (mTransactionState.getState() != 1)
        {
          mTransactionState.setState(2);
          MyLog.e(LogTag.logFormat("Transaction failed.", new Object[0]));
        }
      }
      catch (Throwable localThrowable)
      {
        boolean bool;
        Object localObject1;
        byte[] arrayOfByte;
        LogTag.verbose("Exception: %s", new Object[] { Log.getStackTraceString(localThrowable) });
        mTransactionState.setContentUri(mUri);
        if (mShouldDownload) {
          continue;
        }
        mTransactionState.setState(1);
        if (mTransactionState.getState() == 1) {
          continue;
        }
        mTransactionState.setState(2);
        MyLog.e(LogTag.logFormat("Transaction failed.", new Object[0]));
        continue;
        if (localThrowable.getMessageType() == 132) {
          continue;
        }
        MyLog.e(LogTag.logFormat("Invalid M-RETRIEVE.CONF PDU for type is not right.", new Object[0]));
        mTransactionState.setState(2);
        int i = 132;
        continue;
        localUri = MiuiPduPersister.getPduPersister(mContext).persist(localThrowable, Telephony.Mms.Inbox.CONTENT_URI, mUri, -1L);
        localContentValues = new ContentValues();
        localContentValues.put("sim_id", Long.valueOf(mSimId));
        localContentValues.put("m_size", Long.valueOf(mNotificationInd.getMessageSize()));
        SqliteWrapper.update(mContext, mContext.getContentResolver(), localUri, localContentValues, null, null);
        mUri = localUri;
        i = 129;
        continue;
        mTransactionState.setState(1);
        continue;
      }
      finally
      {
        mTransactionState.setContentUri(mUri);
        if (mShouldDownload) {
          continue;
        }
        mTransactionState.setState(1);
        if (mTransactionState.getState() == 1) {
          continue;
        }
        mTransactionState.setState(2);
        MyLog.e(LogTag.logFormat("Transaction failed.", new Object[0]));
        notifyObservers();
      }
      notifyObservers();
      return;
      bool = false;
    }
    LogTag.verbose("Content-Location: %s", new Object[] { mContentLocation });
    localObject1 = null;
    try
    {
      arrayOfByte = getPdu(mContentLocation, new ProgressReceiver()
      {
        public void onProgress(long paramAnonymousLong1, long paramAnonymousLong2)
        {
          if (paramAnonymousLong1 < 0L) {
            return;
          }
          long l = paramAnonymousLong2;
          if (paramAnonymousLong2 <= 0L) {
            l = mNotificationInd.getMessageSize();
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
      });
      localObject1 = arrayOfByte;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        mTransactionState.setState(2);
      }
    }
    if (localObject1 != null)
    {
      localObject1 = (RetrieveConf)new MiuiPduParser((byte[])localObject1).parse();
      if (localObject1 == null)
      {
        MyLog.e(LogTag.logFormat("Invalid M-RETRIEVE.CONF PDU for pdu is null", new Object[0]));
        mTransactionState.setState(2);
        i = 131;
      }
    }
    else
    {
      LogTag.verbose("status=%x", new Object[] { Integer.valueOf(i) });
      switch (i)
      {
      }
    }
    for (;;)
    {
      sendNotifyRespInd(i);
      mTransactionState.setContentUri(mUri);
      if (!mShouldDownload) {
        mTransactionState.setState(1);
      }
      if (mTransactionState.getState() == 1) {
        break;
      }
      mTransactionState.setState(2);
      MyLog.e(LogTag.logFormat("Transaction failed.", new Object[0]));
      break;
      Uri localUri;
      ContentValues localContentValues;
      if (mTransactionState.getState() == 0) {
        mTransactionState.setState(1);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.NotificationTransaction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */