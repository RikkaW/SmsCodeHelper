package com.android.mms.transaction;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.provider.Telephony.Mms.Sent;
import android.provider.Telephony.MmsSms;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.LogTag;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.RateController;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.MiuiPduParser;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.PduComposer;
import com.google.android.mms.pdu.SendConf;
import com.google.android.mms.pdu.SendReq;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.Arrays;
import miui.telephony.TelephonyManager;

public class SendTransaction
  extends Transaction
  implements Runnable
{
  private final Uri mSendReqURI;
  private Thread mThread;
  
  public SendTransaction(Context paramContext, TransactionSettings paramTransactionSettings, String paramString)
  {
    super(paramContext, paramTransactionSettings);
    mSendReqURI = Uri.parse(paramString);
    mTransactionState.setContentUri(mSendReqURI);
    mId = paramString;
  }
  
  public int getType()
  {
    return 2;
  }
  
  public void process()
  {
    mThread = new Thread(this);
    mThread.start();
  }
  
  public void run()
  {
    sCurrentTransactionMsgId = Long.valueOf(mSendReqURI.getLastPathSegment()).longValue();
    sCurrentTransactionProgress = 0;
    for (;;)
    {
      try
      {
        localObject1 = RateController.getInstance();
        if ((!((RateController)localObject1).isLimitSurpassed()) || (((RateController)localObject1).isAllowedByUser())) {
          continue;
        }
        MyLog.e(LogTag.logFormat("Sending rate limit surpassed.", new Object[0]));
        if (mTransactionState.getState() != 1)
        {
          mTransactionState.setState(2);
          mTransactionState.setContentUri(mSendReqURI);
          MyLog.e(LogTag.logFormat("Delivery failed.", new Object[0]));
        }
      }
      catch (Throwable localThrowable)
      {
        Object localObject1;
        MiuiPduPersister localMiuiPduPersister;
        Object localObject4;
        long l;
        int i;
        Object localObject3;
        MyLog.e(LogTag.logFormat("Exception: %s", new Object[] { Log.getStackTraceString(localThrowable) }));
        if (mTransactionState.getState() == 1) {
          continue;
        }
        mTransactionState.setState(2);
        mTransactionState.setContentUri(mSendReqURI);
        MyLog.e(LogTag.logFormat("Delivery failed.", new Object[0]));
        continue;
      }
      finally
      {
        if (mTransactionState.getState() == 1) {
          continue;
        }
        mTransactionState.setState(2);
        mTransactionState.setContentUri(mSendReqURI);
        MyLog.e(LogTag.logFormat("Delivery failed.", new Object[0]));
        notifyObservers();
      }
      notifyObservers();
      return;
      localMiuiPduPersister = MiuiPduPersister.getPduPersister(mContext);
      localObject4 = (SendReq)localMiuiPduPersister.load(mSendReqURI);
      l = System.currentTimeMillis();
      ((SendReq)localObject4).setDate(l / 1000L);
      localObject1 = new ContentValues(1);
      ((ContentValues)localObject1).put("date_full", Long.valueOf(l));
      SqliteWrapper.update(mContext, mContext.getContentResolver(), mSendReqURI, (ContentValues)localObject1, null, null);
      i = MSimUtils.getSlotIdBySimInfoId(mSimId);
      localObject3 = TelephonyManager.getDefault().getLine1NumberForSlot(i);
      localObject1 = localObject3;
      if (TextUtils.isEmpty((CharSequence)localObject3))
      {
        Log.e("SendTransaction", "lineNumber is empty");
        localObject1 = "";
      }
      ((SendReq)localObject4).setFrom(new EncodedStringValue((String)localObject1));
      localObject1 = (SendConf)new MiuiPduParser(sendPdu(new PduComposer(mContext, (GenericPdu)localObject4).make(), new ProgressReceiver()
      {
        public void onProgress(long paramAnonymousLong1, long paramAnonymousLong2)
        {
          if (paramAnonymousLong1 < 0L) {}
          while (paramAnonymousLong2 <= 0L) {
            return;
          }
          long l = paramAnonymousLong1;
          if (paramAnonymousLong1 > paramAnonymousLong2) {
            l = paramAnonymousLong2;
          }
          Transaction.sCurrentTransactionProgress = (int)(100L * l / paramAnonymousLong2);
          mContext.getContentResolver().notifyChange(Telephony.MmsSms.CONTENT_CONVERSATIONS_URI, null);
        }
      })).parse();
      if (localObject1 == null) {
        MyLog.e(LogTag.logFormat("No M-Send.conf received.", new Object[0]));
      }
      localObject3 = ((SendReq)localObject4).getTransactionId();
      localObject4 = ((SendConf)localObject1).getTransactionId();
      if (!Arrays.equals((byte[])localObject3, (byte[])localObject4))
      {
        MyLog.e(LogTag.logFormat("Inconsistent Transaction-ID: req=%d, conf=%d", new Object[] { localObject3, localObject4 }));
        if (mTransactionState.getState() != 1)
        {
          mTransactionState.setState(2);
          mTransactionState.setContentUri(mSendReqURI);
          MyLog.e(LogTag.logFormat("Delivery failed.", new Object[0]));
        }
      }
      else
      {
        localObject3 = new ContentValues(2);
        i = ((SendConf)localObject1).getResponseStatus();
        ((ContentValues)localObject3).put("resp_st", Integer.valueOf(i));
        ((ContentValues)localObject3).put("sim_id", Long.valueOf(mSimId));
        if (i != 128)
        {
          SqliteWrapper.update(mContext, mContext.getContentResolver(), mSendReqURI, (ContentValues)localObject3, null, null);
          MyLog.e(LogTag.logFormat("Server returned an error code: %d", new Object[] { Integer.valueOf(i) }));
          if (mTransactionState.getState() != 1)
          {
            mTransactionState.setState(2);
            mTransactionState.setContentUri(mSendReqURI);
            MyLog.e(LogTag.logFormat("Delivery failed.", new Object[0]));
          }
        }
        else
        {
          ((ContentValues)localObject3).put("m_id", MiuiPduPersister.toIsoString(((SendConf)localObject1).getMessageId()));
          SqliteWrapper.update(mContext, mContext.getContentResolver(), mSendReqURI, (ContentValues)localObject3, null, null);
          MyLog.d(LogTag.logFormat("Moved to sent box: %s", new Object[] { mSendReqURI }));
          localObject1 = localMiuiPduPersister.move(mSendReqURI, Telephony.Mms.Sent.CONTENT_URI);
          mTransactionState.setState(1);
          mTransactionState.setContentUri((Uri)localObject1);
          if (mTransactionState.getState() != 1)
          {
            mTransactionState.setState(2);
            mTransactionState.setContentUri(mSendReqURI);
            MyLog.e(LogTag.logFormat("Delivery failed.", new Object[0]));
          }
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.SendTransaction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */