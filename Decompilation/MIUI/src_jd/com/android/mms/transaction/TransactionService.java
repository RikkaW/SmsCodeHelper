package com.android.mms.transaction;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.Mms.Outbox;
import android.provider.Telephony.MmsSms.PendingMessages;
import android.telephony.PhoneStateListener;
import android.widget.Toast;
import com.android.mms.LogTag;
import com.android.mms.ui.MessageUtils;
import com.android.mms.util.DownloadManager;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.RateController;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.MiuiPduParser;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.NotificationInd;
import java.util.Iterator;
import java.util.LinkedHashSet;
import miui.telephony.TelephonyManager;

public class TransactionService
  extends Service
  implements Observer
{
  private ConnectivityManager mConnMgr;
  private Transaction mCurrentTransaction;
  private int mFailureCount = 0;
  private Handler mHandler;
  private HandlerThread mHandlerThread;
  private boolean mIsWaitingReConnect = false;
  private long mLastSimId = -1L;
  private int mLastStartId = -1;
  private int mMmsConnectivityFailedRetryCount = 0;
  private int mMmsConnectivityStartedRetryCount = 0;
  private Runnable mOnSim1CallStateChangedCallback = new Runnable()
  {
    public void run()
    {
      if ((mPhoneState == 0) && (mPhoneState2 == 0))
      {
        LogTag.verbose("get slot0 new mReConnectWhenCallIdle : " + mReConnectWhenCallIdle, new Object[0]);
        if (mReConnectWhenCallIdle)
        {
          TransactionService.access$1902(TransactionService.this, false);
          TransactionService.this.connectAndProcessNextTransaction();
        }
      }
    }
  };
  private Runnable mOnSim2CallStateChangedCallback = new Runnable()
  {
    public void run()
    {
      if ((mPhoneState == 0) && (mPhoneState2 == 0))
      {
        LogTag.verbose("get slot1 new mReConnectWhenCallIdle : " + mReConnectWhenCallIdle, new Object[0]);
        if (mReConnectWhenCallIdle)
        {
          TransactionService.access$1902(TransactionService.this, false);
          TransactionService.this.connectAndProcessNextTransaction();
        }
      }
    }
  };
  private Transaction mPendingTransaction;
  private int mPhoneState = 0;
  private int mPhoneState2 = 0;
  private PhoneStateListener mPhoneStateListener;
  private PhoneStateListener mPhoneStateListener2;
  private Object mPhoneStateLock = new Object();
  private boolean mReConnectWhenCallIdle = false;
  private int mSuccessCount = 0;
  private LinkedHashSet<Transaction> mTransactionQueue = new LinkedHashSet();
  private Handler mUIHandler = new Handler();
  private PowerManager.WakeLock mWakeLock;
  
  private boolean checkMobileNetwork(int paramInt)
  {
    if (MmsSystemEventReceiver.getInstance().isMmsAllowed())
    {
      LogTag.verbose("checkMobileNetwork isMmsAllowed is true", new Object[0]);
      boolean bool = isNetworkAvailable(paramInt);
      if (bool)
      {
        MmsSystemEventReceiver.getInstance().unlistenForMmsAvailability(paramInt);
        return true;
      }
      if ((mReConnectWhenCallIdle) || ((!bool) && (isDuringCall())))
      {
        mReConnectWhenCallIdle = true;
        LogTag.verbose("checkMobile during call", new Object[0]);
        MmsSystemEventReceiver.getInstance().unlistenForMmsAvailability(paramInt);
        return true;
      }
    }
    else
    {
      LogTag.verbose("checkMobileNetwork isMmsAllowed is false", new Object[0]);
      mReConnectWhenCallIdle = false;
    }
    MmsSystemEventReceiver.getInstance().listenForMmsAvailability(paramInt);
    return false;
  }
  
  private void cleanOrStop()
  {
    if ((mCurrentTransaction == null) && (mTransactionQueue.isEmpty()))
    {
      endMmsConnectivity();
      stopSelfIfIdle(mLastStartId);
      LogTag.debug("TrasctionService stopped.", new Object[0]);
      return;
    }
    LogTag.error("no pending messages in database but in memory", new Object[0]);
  }
  
  private void connectAndProcessNextTransaction()
  {
    if (mIsWaitingReConnect)
    {
      LogTag.verbose("mIsWaitingReConnect is true", new Object[0]);
      return;
    }
    if (mCurrentTransaction != null)
    {
      LogTag.verbose("while there is already one processing, transaction=%s", new Object[] { mCurrentTransaction });
      return;
    }
    if (mTransactionQueue.isEmpty())
    {
      if (!mReConnectWhenCallIdle)
      {
        onFinish();
        return;
      }
      LogTag.verbose("not on finish when calling", new Object[0]);
      return;
    }
    if (mPendingTransaction == null) {
      mPendingTransaction = getPendingTransaction();
    }
    int i = MSimUtils.getSlotIdBySimInfoId(mPendingTransaction.mSimId);
    boolean bool = MSimUtils.isSimInserted(i);
    if ((!bool) || (!checkMobileNetwork(i)))
    {
      LogTag.verbose("transaction is not available in case of no slotId", new Object[0]);
      MmsSystemEventReceiver.getInstance().listenForMmsAvailability(i);
      markTransactionFailureBySimId(mPendingTransaction.mSimId, false, false, bool);
      mMmsConnectivityFailedRetryCount = 0;
      mMmsConnectivityStartedRetryCount = 0;
      mPendingTransaction = null;
      requestRetryConnect();
      return;
    }
    if ((mPendingTransaction.getType() == 0) && (!mPendingTransaction).mShouldDownload))
    {
      LogTag.verbose("notification trasaction should download is false", new Object[0]);
      mLastSimId = mPendingTransaction.mSimId;
      processTransaction(mPendingTransaction);
      mPendingTransaction = null;
      mMmsConnectivityFailedRetryCount = 0;
      mMmsConnectivityStartedRetryCount = 0;
      return;
    }
    LogTag.verbose("startUsingNetworkFeature slotId = " + i, new Object[0]);
    i = mConnMgr.startUsingNetworkFeature(0, "enableMMS", i);
    mLastSimId = mPendingTransaction.mSimId;
    LogTag.verbose("connectAndProcessNextTransaction: result=" + i, new Object[0]);
    switch (i)
    {
    default: 
      if (isDuringCall())
      {
        mReConnectWhenCallIdle = true;
        markTransactionFailureBySimId(mLastSimId, false, true, true);
        mMmsConnectivityFailedRetryCount = 0;
        mMmsConnectivityStartedRetryCount = 0;
        mPendingTransaction = null;
        requestRetryConnect();
        return;
      }
      break;
    case 0: 
      processTransaction(mPendingTransaction);
      mPendingTransaction = null;
      mMmsConnectivityFailedRetryCount = 0;
      mMmsConnectivityStartedRetryCount = 0;
      return;
    case 1: 
      mWakeLock.acquire();
      sendBroadcast(new Intent("com.android.mms.transaction.START"));
      requestRetryConnectForStarted();
      return;
    }
    requestRetryConnectForFailed();
  }
  
  private void endMmsConnectivity()
  {
    try
    {
      LogTag.verbose("endMmsConnectivity", new Object[0]);
      int i = MSimUtils.getSlotIdBySimInfoId(mLastSimId);
      if ((mConnMgr != null) && (i >= 0))
      {
        LogTag.verbose("mLastSimId = %d ", new Object[] { Long.valueOf(mLastSimId) });
        mConnMgr.stopUsingNetworkFeature(0, "enableMMS", i);
        sendBroadcast(new Intent("com.android.mms.transaction.STOP"));
      }
      return;
    }
    finally
    {
      mWakeLock.release();
    }
  }
  
  private void enqueueAllMessages(int paramInt)
  {
    Cursor localCursor = MiuiPduPersister.getPduPersister(this).getPendingMessages(2147483647L);
    if (localCursor != null) {}
    for (;;)
    {
      int k;
      long l;
      TransactionBundle localTransactionBundle;
      try
      {
        int i = localCursor.getCount();
        LogTag.verbose("enqueueAllMessages: Pending transaction count=%d", new Object[] { Integer.valueOf(i) });
        if (i == 0)
        {
          cleanOrStop();
          return;
        }
        i = localCursor.getColumnIndexOrThrow("msg_id");
        int j = localCursor.getColumnIndexOrThrow("msg_type");
        if (!localCursor.moveToNext()) {
          break label309;
        }
        k = getTransactionType(localCursor.getInt(j));
        switch (k)
        {
        case -1: 
        case 0: 
          l = localCursor.getLong(i);
          Uri localUri = ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, l);
          String str = localUri.toString();
          localTransactionBundle = new TransactionBundle(k, str);
          l = getSimId(str);
          k = MSimUtils.getSlotIdBySimInfoId(l);
          if ((paramInt != MSimUtils.SLOT_ID_INVALID) && (paramInt != k)) {
            continue;
          }
          if (MSimUtils.isSimInserted(k)) {
            break label286;
          }
          LogTag.error("sim id is not inserted to slot", new Object[0]);
          MmsSystemEventReceiver.getInstance().listenForMmsAvailability(k);
          onTransactionFailure(localUri, l, true, false, false);
          break;
        case 1: 
          m = localCursor.getInt(localCursor.getColumnIndexOrThrow("err_type"));
        }
      }
      finally
      {
        localCursor.close();
      }
      int m;
      if (m < 10) {
        if (m <= 0) {
          label286:
          if ((goto 80) && (checkMobileNetwork(k)))
          {
            enqueueMessage(localTransactionBundle, l, k);
            continue;
            label309:
            connectAndProcessNextTransaction();
            localCursor.close();
            return;
            LogTag.warn("enqueueAllMessages: pending messages = null", new Object[0]);
            cleanOrStop();
            return;
          }
        }
      }
    }
  }
  
  private boolean enqueueMessage(TransactionBundle paramTransactionBundle, long paramLong, int paramInt)
  {
    Object localObject1 = paramTransactionBundle.getMmscUrl();
    if (localObject1 != null) {}
    for (localObject1 = new TransactionSettings((String)localObject1, paramTransactionBundle.getProxyAddress(), paramTransactionBundle.getProxyPort());; localObject1 = new TransactionSettings(this, null, paramInt))
    {
      paramInt = paramTransactionBundle.getTransactionType();
      Object localObject2 = null;
      switch (paramInt)
      {
      }
      try
      {
        LogTag.warn("Invalid transaction type: %d", new Object[] { Integer.valueOf(paramInt) });
        return false;
      }
      catch (MmsException paramTransactionBundle)
      {
        for (;;)
        {
          String str;
          paramTransactionBundle = (TransactionBundle)localObject2;
        }
      }
    }
    str = paramTransactionBundle.getUri();
    if (str != null)
    {
      localObject1 = new NotificationTransaction(this, (TransactionSettings)localObject1, str);
      paramTransactionBundle = (TransactionBundle)localObject1;
    }
    for (;;)
    {
      try
      {
        ((Transaction)localObject1).setSimId(paramLong);
        paramTransactionBundle = (TransactionBundle)localObject1;
      }
      catch (MmsException localMmsException)
      {
        continue;
      }
      if (paramTransactionBundle != null) {
        continue;
      }
      return false;
      paramTransactionBundle = new MiuiPduParser(paramTransactionBundle.getPushData()).parse();
      if ((paramTransactionBundle != null) && (paramTransactionBundle.getMessageType() == 130))
      {
        localObject1 = new NotificationTransaction(this, (TransactionSettings)localObject1, (NotificationInd)paramTransactionBundle);
        paramTransactionBundle = (TransactionBundle)localObject1;
        ((Transaction)localObject1).setSimId(paramLong);
        paramTransactionBundle = (TransactionBundle)localObject1;
      }
      else
      {
        LogTag.error("Invalid PUSH data.", new Object[0]);
        return false;
        if (!MessageUtils.isStorageAvailable(this))
        {
          toast(-1, true);
          paramTransactionBundle = (TransactionBundle)localObject2;
        }
        else
        {
          localObject1 = new RetrieveTransaction(this, (TransactionSettings)localObject1, paramTransactionBundle.getUri());
          paramTransactionBundle = (TransactionBundle)localObject1;
          ((Transaction)localObject1).setSimId(paramLong);
          paramTransactionBundle = (TransactionBundle)localObject1;
          continue;
          localObject1 = new SendTransaction(this, (TransactionSettings)localObject1, paramTransactionBundle.getUri());
          paramTransactionBundle = (TransactionBundle)localObject1;
          ((Transaction)localObject1).setSimId(paramLong);
          paramTransactionBundle = (TransactionBundle)localObject1;
          continue;
          localObject1 = new ReadRecTransaction(this, (TransactionSettings)localObject1, paramTransactionBundle.getUri());
          paramTransactionBundle = (TransactionBundle)localObject1;
          ((Transaction)localObject1).setSimId(paramLong);
          paramTransactionBundle = (TransactionBundle)localObject1;
        }
      }
    }
    if (!mTransactionQueue.contains(paramTransactionBundle))
    {
      mTransactionQueue.add(paramTransactionBundle);
      LogTag.verbose("Enqueued transaction: %s", new Object[] { paramTransactionBundle });
    }
    paramTransactionBundle.onEnqueue();
    return true;
  }
  
  private void enqueueSingleMessage(TransactionBundle paramTransactionBundle)
  {
    String str = paramTransactionBundle.getUri();
    long l = getSimId(str);
    int i = MSimUtils.getSlotIdBySimInfoId(l);
    if (!MSimUtils.isSimInserted(i))
    {
      LogTag.error("sim id is not inserted to slot", new Object[0]);
      MmsSystemEventReceiver.getInstance().listenForMmsAvailability(i);
      onTransactionFailure(Uri.parse(str), l, true, false, false);
      return;
    }
    if (checkMobileNetwork(i)) {
      enqueueMessage(paramTransactionBundle, l, i);
    }
    for (;;)
    {
      connectAndProcessNextTransaction();
      return;
      toast(paramTransactionBundle.getTransactionType());
    }
  }
  
  private Transaction getPendingTransaction()
  {
    if (mLastSimId == -1L) {
      return (Transaction)mTransactionQueue.iterator().next();
    }
    Iterator localIterator = mTransactionQueue.iterator();
    while (localIterator.hasNext())
    {
      Transaction localTransaction = (Transaction)localIterator.next();
      if (mSimId == mLastSimId) {
        return localTransaction;
      }
    }
    endMmsConnectivity();
    return (Transaction)mTransactionQueue.iterator().next();
  }
  
  private int getResponseStatus(long paramLong)
  {
    Cursor localCursor = SqliteWrapper.query(this, getContentResolver(), Telephony.Mms.Outbox.CONTENT_URI, null, "_id=" + paramLong, null, null);
    for (;;)
    {
      try
      {
        if (localCursor.moveToFirst())
        {
          i = localCursor.getInt(localCursor.getColumnIndexOrThrow("resp_st"));
          localCursor.close();
          if (i != 0) {
            LogTag.warn("Response status is: %d", new Object[] { Integer.valueOf(i) });
          }
          return i;
        }
      }
      finally
      {
        localCursor.close();
      }
      int i = 0;
    }
  }
  
  private long getSimId(String paramString)
  {
    long l1 = -1L;
    if (MSimUtils.isMSim())
    {
      paramString = SqliteWrapper.query(this, getContentResolver(), Uri.parse(paramString), new String[] { "sim_id" }, null, null, null);
      long l2 = l1;
      if (paramString != null) {}
      try
      {
        if (paramString.moveToFirst())
        {
          int i = paramString.getInt(0);
          l1 = i;
        }
        paramString.close();
        l2 = l1;
        return l2;
      }
      finally
      {
        paramString.close();
      }
    }
    return 0L;
  }
  
  private int getTransactionType(int paramInt)
  {
    int i = 1;
    switch (paramInt)
    {
    default: 
      LogTag.warn("Unrecognized MESSAGE_TYPE: %d", new Object[] { Integer.valueOf(paramInt) });
      i = -1;
    case 130: 
      return i;
    case 135: 
      return 3;
    }
    return 2;
  }
  
  private boolean isDuringCall()
  {
    updateCallState();
    return (mPhoneState != 0) || (mPhoneState2 != 0);
  }
  
  private boolean isNetworkAvailable(int paramInt)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramInt != MSimUtils.getPreferredDataSlotId())
    {
      LogTag.verbose("isNetworkAvailable not preferred data slotId", new Object[0]);
      bool1 = true;
    }
    for (;;)
    {
      LogTag.verbose("isNetworkAvaliable is " + bool1, new Object[0]);
      return bool1;
      NetworkInfo localNetworkInfo = mConnMgr.getNetworkInfo(2);
      if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
      {
        bool1 = true;
      }
      else
      {
        localNetworkInfo = mConnMgr.getNetworkInfo(0);
        bool1 = bool2;
        if (localNetworkInfo != null)
        {
          bool1 = bool2;
          if (localNetworkInfo.isAvailable()) {
            bool1 = true;
          }
        }
      }
    }
  }
  
  private boolean isRetry()
  {
    boolean bool = true;
    if (MmsSystemEventReceiver.getInstance().isListening()) {
      LogTag.verbose("for listen mms available, not retry", new Object[0]);
    }
    Cursor localCursor;
    do
    {
      return false;
      localCursor = MiuiPduPersister.getPduPersister(this).getPendingMessages(2147483647L);
    } while (localCursor == null);
    for (;;)
    {
      try
      {
        if (localCursor.moveToNext())
        {
          if (1 == getTransactionType(localCursor.getInt(localCursor.getColumnIndexOrThrow("msg_type"))))
          {
            i = localCursor.getInt(localCursor.getColumnIndexOrThrow("err_type"));
            if ((i >= 10) || (i <= 0)) {
              continue;
            }
          }
          int i = localCursor.getInt(localCursor.getColumnIndexOrThrow("retry_index"));
          if (i >= 5) {
            continue;
          }
          return bool;
        }
      }
      finally
      {
        localCursor.close();
      }
      bool = false;
    }
  }
  
  private void markTransactionFailureAndRemove(Transaction paramTransaction, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramTransaction != null)
    {
      LogTag.verbose("markTransactionFailureAndRemove transaction : %s", new Object[] { paramTransaction });
      onTransactionFailure(paramTransaction.getState().getContentUri(), mSimId, paramBoolean1, paramBoolean2, true);
      mTransactionQueue.remove(paramTransaction);
      mFailureCount += 1;
    }
  }
  
  private void markTransactionFailureBySimId(long paramLong, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    Object localObject1 = new LinkedHashSet();
    Object localObject2 = mTransactionQueue.iterator();
    while (((Iterator)localObject2).hasNext())
    {
      Transaction localTransaction = (Transaction)((Iterator)localObject2).next();
      if (mSimId == paramLong) {
        ((LinkedHashSet)localObject1).add(localTransaction);
      }
    }
    localObject1 = ((LinkedHashSet)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Transaction)((Iterator)localObject1).next();
      onTransactionFailure(((Transaction)localObject2).getState().getContentUri(), paramLong, paramBoolean1, paramBoolean2, paramBoolean3);
      mTransactionQueue.remove(localObject2);
      mFailureCount += 1;
    }
  }
  
  private void onFinish()
  {
    LogTag.debug("Finished all transactions in queue. %d success, %d failure.", new Object[] { Integer.valueOf(mSuccessCount), Integer.valueOf(mFailureCount) });
    if (mFailureCount > 0) {
      scheduleNextRetry();
    }
    mFailureCount = 0;
    endMmsConnectivity();
    stopSelfIfIdle(mLastStartId);
    LogTag.debug("TrasctionService stopped.", new Object[0]);
  }
  
  private void onTransactionFailure(Uri paramUri, long paramLong, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    long l1 = ContentUris.parseId(paramUri);
    Object localObject1 = Telephony.MmsSms.PendingMessages.CONTENT_URI.buildUpon();
    ((Uri.Builder)localObject1).appendQueryParameter("protocol", "mms");
    ((Uri.Builder)localObject1).appendQueryParameter("message", String.valueOf(l1));
    localObject1 = SqliteWrapper.query(this, getContentResolver(), ((Uri.Builder)localObject1).build(), null, null, null, null);
    if (localObject1 != null) {}
    for (;;)
    {
      int m;
      try
      {
        if ((((Cursor)localObject1).getCount() != 1) || (!((Cursor)localObject1).moveToFirst())) {
          break label619;
        }
        i = ((Cursor)localObject1).getInt(((Cursor)localObject1).getColumnIndexOrThrow("msg_type"));
        j = ((Cursor)localObject1).getInt(((Cursor)localObject1).getColumnIndexOrThrow("retry_index"));
        if (paramBoolean2) {
          break label640;
        }
        j += 1;
        int n = 1;
        localContentValues = new ContentValues(4);
        l3 = System.currentTimeMillis();
        if (i != 130) {
          continue;
        }
        k = 1;
        m = 1;
        int i1 = getResponseStatus(l1);
        i = 0;
        switch (i1)
        {
        case 132: 
          if (i != 0)
          {
            if (!paramBoolean3) {
              break label656;
            }
            DownloadManager.getInstance().showErrorCodeToast(i);
            break label656;
          }
          if ((j >= 5) || (m == 0)) {
            continue;
          }
          i = n;
          if (k != 0)
          {
            DownloadManager.getInstance().markState(paramUri, 130, paramLong);
            i = n;
          }
          break;
        }
      }
      finally
      {
        try
        {
          int j;
          ContentValues localContentValues;
          long l3;
          int k;
          if (((Cursor)localObject2).moveToFirst()) {
            l1 = ((Cursor)localObject2).getLong(0);
          }
          ((Cursor)localObject2).close();
          long l2 = l1;
          if (l2 != -1L) {
            MessagingNotification.notifyDownloadFailed(this, l2);
          }
          DownloadManager.getInstance().markState(paramUri, 135, paramLong);
          i = 10;
        }
        finally
        {
          ((Cursor)localObject2).close();
        }
        ((Cursor)localObject1).close();
      }
      localContentValues.put("err_type", Integer.valueOf(i));
      localContentValues.put("retry_index", Integer.valueOf(j));
      localContentValues.put("last_try", Long.valueOf(l3));
      paramLong = ((Cursor)localObject1).getLong(((Cursor)localObject1).getColumnIndexOrThrow("_id"));
      SqliteWrapper.update(this, getContentResolver(), Telephony.MmsSms.PendingMessages.CONTENT_URI, localContentValues, "_id=" + paramLong, null);
      ((Cursor)localObject1).close();
      return;
      k = 0;
      continue;
      int i = 2131361860;
      continue;
      i = 2131361861;
      continue;
      i = 2131361863;
      continue;
      i = 2131361862;
      continue;
      LogTag.verbose("onTransactionFailure: retry for %s is reached limit. Abort.", new Object[] { paramUri });
      if (k != 0)
      {
        localObject2 = SqliteWrapper.query(this, getContentResolver(), paramUri, new String[] { "thread_id" }, null, null, null);
        l1 = -1L;
        l2 = l1;
        if (localObject2 == null) {}
      }
      Object localObject2 = new ContentValues(1);
      ((ContentValues)localObject2).put("read", Integer.valueOf(0));
      SqliteWrapper.update(this, getContentResolver(), paramUri, (ContentValues)localObject2, null, null);
      MessagingNotification.notifySendFailed(this, true);
      i = 10;
      continue;
      label619:
      LogTag.verbose("Cannot found correct pending status for: %d", new Object[] { Long.valueOf(l1) });
      continue;
      label640:
      continue;
      if (paramBoolean1)
      {
        i = 2131361861;
        continue;
        label656:
        m = 0;
      }
    }
  }
  
  private void processTransaction(Transaction paramTransaction)
  {
    mCurrentTransaction = paramTransaction;
    mIsWaitingReConnect = false;
    LogTag.verbose("Processing next transaction: %s", new Object[] { mCurrentTransaction });
    sendBroadcast(new Intent("com.android.mms.transaction.START"));
    mCurrentTransaction.attach(this);
    mCurrentTransaction.process();
  }
  
  private void registerPhoneCallListener()
  {
    mPhoneStateListener = new PhoneStateListener()
    {
      public void onCallStateChanged(int paramAnonymousInt, String arg2)
      {
        synchronized (mPhoneStateLock)
        {
          TransactionService.this.updateCallState();
          TransactionService.this.runInWorkerThread(mOnSim1CallStateChangedCallback);
          return;
        }
      }
    };
    TelephonyManager.getDefault().listenForSlot(0, mPhoneStateListener, 32);
    if (TelephonyManager.getDefault().isMultiSimEnabled())
    {
      mPhoneStateListener2 = new PhoneStateListener()
      {
        public void onCallStateChanged(int paramAnonymousInt, String arg2)
        {
          synchronized (mPhoneStateLock)
          {
            TransactionService.this.updateCallState();
            TransactionService.this.runInWorkerThread(mOnSim2CallStateChangedCallback);
            return;
          }
        }
      };
      TelephonyManager.getDefault().listenForSlot(1, mPhoneStateListener2, 32);
    }
    LogTag.verbose("register Phone Call Listener.", new Object[0]);
  }
  
  private void requestRetryConnect()
  {
    mIsWaitingReConnect = true;
    LogTag.verbose("requestRetryConnect start", new Object[0]);
    runInWorkerThread(new Runnable()
    {
      public void run()
      {
        TransactionService.access$002(TransactionService.this, false);
        LogTag.verbose("requestRetryConnect end", new Object[0]);
        TransactionService.this.connectAndProcessNextTransaction();
      }
    }, 5000);
  }
  
  private void requestRetryConnectForFailed()
  {
    mIsWaitingReConnect = true;
    LogTag.verbose("requestRetryConnectForFailed start", new Object[0]);
    runInWorkerThread(new Runnable()
    {
      public void run()
      {
        TransactionService.access$002(TransactionService.this, false);
        LogTag.verbose("requestRetryConnectForFailed end", new Object[0]);
        if (mMmsConnectivityFailedRetryCount < 3) {
          TransactionService.access$204(TransactionService.this);
        }
        for (;;)
        {
          TransactionService.this.connectAndProcessNextTransaction();
          return;
          TransactionService.this.markTransactionFailureBySimId(mLastSimId, false, false, true);
          TransactionService.access$202(TransactionService.this, 0);
          TransactionService.access$502(TransactionService.this, 0);
          TransactionService.access$602(TransactionService.this, null);
        }
      }
    }, 5000);
  }
  
  private void requestRetryConnectForStarted()
  {
    mIsWaitingReConnect = true;
    LogTag.verbose("requestRetryConnectForStarted start", new Object[0]);
    int i = 5000;
    if (MSimUtils.isMSim()) {
      if (!MSimUtils.isLeadcore()) {
        break label49;
      }
    }
    label49:
    for (i = 3000;; i = 12000)
    {
      runInWorkerThread(new Runnable()
      {
        public void run()
        {
          TransactionService.access$002(TransactionService.this, false);
          LogTag.verbose("requestRetryConnectForStarted end", new Object[0]);
          int i = 18;
          if (MSimUtils.isMSim()) {
            i = 20;
          }
          if (mMmsConnectivityStartedRetryCount < i) {
            TransactionService.access$504(TransactionService.this);
          }
          for (;;)
          {
            TransactionService.this.connectAndProcessNextTransaction();
            return;
            TransactionService.this.markTransactionFailureAndRemove(mPendingTransaction, false, false);
            TransactionService.access$202(TransactionService.this, 0);
            TransactionService.access$502(TransactionService.this, 0);
            TransactionService.access$602(TransactionService.this, null);
            TransactionService.this.endMmsConnectivity();
          }
        }
      }, i);
      return;
    }
  }
  
  private void resetDownloadState()
  {
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("st", Integer.valueOf(128));
    SqliteWrapper.update(this, getContentResolver(), Telephony.Mms.Inbox.CONTENT_URI, localContentValues, "m_type=130", null);
  }
  
  /* Error */
  private void runInWorkerThread(Runnable paramRunnable)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 796	com/android/mms/transaction/TransactionService:mHandler	Landroid/os/Handler;
    //   6: ifnull +15 -> 21
    //   9: aload_0
    //   10: getfield 796	com/android/mms/transaction/TransactionService:mHandler	Landroid/os/Handler;
    //   13: aload_1
    //   14: invokevirtual 800	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   17: pop
    //   18: aload_0
    //   19: monitorexit
    //   20: return
    //   21: ldc_w 802
    //   24: iconst_0
    //   25: anewarray 100	java/lang/Object
    //   28: invokestatic 252	com/android/mms/LogTag:error	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   31: goto -13 -> 18
    //   34: astore_1
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_1
    //   38: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	39	0	this	TransactionService
    //   0	39	1	paramRunnable	Runnable
    // Exception table:
    //   from	to	target	type
    //   2	18	34	finally
    //   21	31	34	finally
  }
  
  /* Error */
  private void runInWorkerThread(Runnable paramRunnable, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 796	com/android/mms/transaction/TransactionService:mHandler	Landroid/os/Handler;
    //   6: ifnull +17 -> 23
    //   9: aload_0
    //   10: getfield 796	com/android/mms/transaction/TransactionService:mHandler	Landroid/os/Handler;
    //   13: aload_1
    //   14: iload_2
    //   15: i2l
    //   16: invokevirtual 806	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
    //   19: pop
    //   20: aload_0
    //   21: monitorexit
    //   22: return
    //   23: ldc_w 808
    //   26: iconst_0
    //   27: anewarray 100	java/lang/Object
    //   30: invokestatic 252	com/android/mms/LogTag:error	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   33: goto -13 -> 20
    //   36: astore_1
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_1
    //   40: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	41	0	this	TransactionService
    //   0	41	1	paramRunnable	Runnable
    //   0	41	2	paramInt	int
    // Exception table:
    //   from	to	target	type
    //   2	20	36	finally
    //   23	33	36	finally
  }
  
  private void scheduleNextRetry()
  {
    if (isRetry())
    {
      LogTag.verbose("scheduleNextRetry isRetry", new Object[0]);
      long l = System.currentTimeMillis();
      PendingIntent localPendingIntent = PendingIntent.getService(this, 0, new Intent("android.intent.action.ACTION_ONALARM", null, this, TransactionService.class), 1073741824);
      ((AlarmManager)getSystemService("alarm")).set(1, l + 45000L, localPendingIntent);
    }
  }
  
  private void stopSelfIfIdle(int paramInt)
  {
    if (mReConnectWhenCallIdle)
    {
      LogTag.verbose("need wait call end, no stop.", new Object[0]);
      return;
    }
    LogTag.verbose("stop TransactionService.", new Object[0]);
    stopSelf(paramInt);
  }
  
  private void toast(int paramInt)
  {
    toast(paramInt, false);
  }
  
  private void toast(final int paramInt, final boolean paramBoolean)
  {
    mUIHandler.post(new Runnable()
    {
      public void run()
      {
        if (paramBoolean)
        {
          Toast.makeText(TransactionService.this, 2131362380, 1).show();
          return;
        }
        switch (paramInt)
        {
        default: 
          return;
        case 1: 
          Toast.makeText(TransactionService.this, 2131362010, 1).show();
          return;
        }
        Toast.makeText(TransactionService.this, 2131362000, 1).show();
      }
    });
  }
  
  private void unregisterPhoneCallListener()
  {
    TelephonyManager.getDefault().listenForSlot(0, mPhoneStateListener, 0);
    mPhoneStateListener = null;
    if (mPhoneStateListener2 != null)
    {
      TelephonyManager.getDefault().listenForSlot(1, mPhoneStateListener2, 0);
      mPhoneStateListener2 = null;
    }
    LogTag.verbose("unregister Phone Call Listener.", new Object[0]);
  }
  
  private void updateCallState()
  {
    synchronized (mPhoneStateLock)
    {
      mPhoneState = TelephonyManager.getDefault().getCallStateForSlot(0);
      if (TelephonyManager.getDefault().isMultiSimEnabled()) {
        mPhoneState2 = TelephonyManager.getDefault().getCallStateForSlot(1);
      }
      LogTag.verbose("whether is during call and mPhoneState = " + mPhoneState + " and mPhoneState2 = " + mPhoneState2, new Object[0]);
      return;
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    LogTag.debug("Creating TransactionService...", new Object[0]);
    mWakeLock = ((PowerManager)getSystemService("power")).newWakeLock(1, "MMS Connectivity");
    mWakeLock.setReferenceCounted(false);
    mConnMgr = ((ConnectivityManager)getSystemService("connectivity"));
    mHandlerThread = new HandlerThread("TransactionService");
    mHandlerThread.start();
    mHandler = new Handler(mHandlerThread.getLooper());
    mSuccessCount = 0;
    mFailureCount = 0;
    registerPhoneCallListener();
    resetDownloadState();
  }
  
  /* Error */
  public void onDestroy()
  {
    // Byte code:
    //   0: ldc_w 905
    //   3: iconst_0
    //   4: anewarray 100	java/lang/Object
    //   7: invokestatic 247	com/android/mms/LogTag:debug	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   10: aload_0
    //   11: monitorenter
    //   12: aload_0
    //   13: getfield 886	com/android/mms/transaction/TransactionService:mHandlerThread	Landroid/os/HandlerThread;
    //   16: invokevirtual 908	android/os/HandlerThread:quit	()Z
    //   19: pop
    //   20: aload_0
    //   21: aconst_null
    //   22: putfield 796	com/android/mms/transaction/TransactionService:mHandler	Landroid/os/Handler;
    //   25: ldc_w 910
    //   28: iconst_0
    //   29: anewarray 100	java/lang/Object
    //   32: invokestatic 219	com/android/mms/LogTag:verbose	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_0
    //   38: invokespecial 912	com/android/mms/transaction/TransactionService:unregisterPhoneCallListener	()V
    //   41: aload_0
    //   42: getfield 136	com/android/mms/transaction/TransactionService:mCurrentTransaction	Lcom/android/mms/transaction/Transaction;
    //   45: ifnull +21 -> 66
    //   48: ldc_w 914
    //   51: iconst_0
    //   52: anewarray 100	java/lang/Object
    //   55: invokestatic 252	com/android/mms/LogTag:error	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   58: aload_0
    //   59: getfield 136	com/android/mms/transaction/TransactionService:mCurrentTransaction	Lcom/android/mms/transaction/Transaction;
    //   62: invokevirtual 917	com/android/mms/transaction/Transaction:abort	()Z
    //   65: pop
    //   66: aload_0
    //   67: aconst_null
    //   68: putfield 136	com/android/mms/transaction/TransactionService:mCurrentTransaction	Lcom/android/mms/transaction/Transaction;
    //   71: aload_0
    //   72: getfield 92	com/android/mms/transaction/TransactionService:mTransactionQueue	Ljava/util/LinkedHashSet;
    //   75: invokevirtual 239	java/util/LinkedHashSet:isEmpty	()Z
    //   78: ifne +20 -> 98
    //   81: ldc_w 919
    //   84: iconst_0
    //   85: anewarray 100	java/lang/Object
    //   88: invokestatic 252	com/android/mms/LogTag:error	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   91: aload_0
    //   92: getfield 92	com/android/mms/transaction/TransactionService:mTransactionQueue	Ljava/util/LinkedHashSet;
    //   95: invokevirtual 922	java/util/LinkedHashSet:clear	()V
    //   98: return
    //   99: astore_1
    //   100: aload_0
    //   101: monitorexit
    //   102: aload_1
    //   103: athrow
    //   104: astore_1
    //   105: goto -39 -> 66
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	this	TransactionService
    //   99	4	1	localObject	Object
    //   104	1	1	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   12	37	99	finally
    //   100	102	99	finally
    //   58	66	104	java/lang/Exception
  }
  
  public int onStartCommand(final Intent paramIntent, final int paramInt1, final int paramInt2)
  {
    LogTag.debug("onStartService: startId=%d", new Object[] { Integer.valueOf(paramInt2) });
    if ((paramIntent == null) || ("android.intent.action.ACTION_ONALARM".equals(paramIntent.getAction())) || ("android.intent.action.ACTION_WAKEUP".equals(paramIntent.getAction())) || (paramIntent.getExtras() == null))
    {
      LogTag.verbose("Posting enqueueAllMessages", new Object[0]);
      if ((paramIntent != null) && ("android.intent.action.ACTION_WAKEUP".equals(paramIntent.getAction())))
      {
        paramInt1 = MSimUtils.getSlotIdFromIntent(paramIntent);
        LogTag.verbose("onStartCommand ACTION_WAKEUP slotId " + paramInt1, new Object[0]);
        runInWorkerThread(new Runnable()
        {
          public void run()
          {
            LogTag.verbose("Posting enqueueAllMessages", new Object[0]);
            TransactionService.access$902(TransactionService.this, paramInt2);
            TransactionService.this.enqueueAllMessages(paramInt1);
          }
        });
      }
    }
    for (;;)
    {
      return 2;
      paramInt1 = MSimUtils.SLOT_ID_INVALID;
      break;
      paramIntent = new TransactionBundle(paramIntent.getExtras());
      LogTag.verbose("Posting enqueueSingleMessage", new Object[0]);
      runInWorkerThread(new Runnable()
      {
        public void run()
        {
          TransactionService.access$902(TransactionService.this, paramInt2);
          TransactionService.this.enqueueSingleMessage(paramIntent);
        }
      });
    }
  }
  
  public void update(final Observable paramObservable)
  {
    paramObservable = (Transaction)paramObservable;
    LogTag.verbose("Posting transaction update", new Object[0]);
    runInWorkerThread(new Runnable()
    {
      public void run()
      {
        LogTag.verbose("Update transaction %s", new Object[] { paramObservable });
        if (paramObservable != mCurrentTransaction) {
          LogTag.error("Expecting %s to update but it is actually %s", new Object[] { mCurrentTransaction, paramObservable });
        }
        Intent localIntent = new Intent("android.intent.action.TRANSACTION_COMPLETED_ACTION");
        TransactionState localTransactionState = paramObservable.getState();
        int i = localTransactionState.getState();
        localIntent.putExtra("state", i);
        localIntent.putExtra("uri", localTransactionState.getContentUri());
        switch (i)
        {
        default: 
          LogTag.verbose("Transaction state unknown: %s. result=%d", new Object[] { paramObservable, Integer.valueOf(i) });
        }
        for (;;)
        {
          paramObservable.detach(TransactionService.this);
          TransactionService.access$1202(TransactionService.this, null);
          sendBroadcast(localIntent);
          TransactionService.this.connectAndProcessNextTransaction();
          return;
          LogTag.verbose("Transaction complete: %s", new Object[] { paramObservable });
          switch (paramObservable.getType())
          {
          }
          for (;;)
          {
            mTransactionQueue.remove(paramObservable);
            TransactionService.access$1404(TransactionService.this);
            break;
            MessagingNotification.blockingUpdateNewMessageIndicator(TransactionService.this, true, false);
            MessagingNotification.updateDownloadFailedNotification(TransactionService.this);
            continue;
            RateController.getInstance().update();
          }
          LogTag.verbose("Transaction failed: %s", new Object[] { paramObservable });
          TransactionService.this.onTransactionFailure(localTransactionState.getContentUri(), paramObservablemSimId, false, false, true);
          mTransactionQueue.remove(paramObservable);
          TransactionService.access$1604(TransactionService.this);
        }
      }
    });
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.TransactionService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */