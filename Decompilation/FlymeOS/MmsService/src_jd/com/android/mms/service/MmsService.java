package com.android.mms.service;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Telephony.Mms.Draft;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.Mms.Sent;
import android.provider.Telephony.Sms.Draft;
import android.provider.Telephony.Sms.Inbox;
import android.provider.Telephony.Sms.Sent;
import android.provider.Telephony.Threads;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.telephony.IMms.Stub;
import com.android.internal.telephony.SmsApplication;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzDeliveryInd;
import com.meizu.android.mms.pdu.MzGenericPdu;
import com.meizu.android.mms.pdu.MzNotificationInd;
import com.meizu.android.mms.pdu.MzPduComposer;
import com.meizu.android.mms.pdu.MzPduParser;
import com.meizu.android.mms.pdu.MzPduPersister;
import com.meizu.android.mms.pdu.MzReadOrigInd;
import com.meizu.android.mms.pdu.MzRetrieveConf;
import com.meizu.android.mms.pdu.MzSendReq;
import com.meizu.android.mms.util.MzSqliteWrapper;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MmsService
  extends Service
  implements MmsRequest.RequestManager
{
  private int mCurrentSubId;
  private final ExecutorService mExecutor = Executors.newCachedThreadPool();
  private final SparseArray<MmsNetworkManager> mNetworkManagerCache = new SparseArray();
  private final Queue<MmsRequest> mPendingSimRequestQueue = new ArrayDeque();
  private final SparseArray<Queue<MmsRequest>> mPendingSimRequestQueues = new SparseArray();
  private int mRunningRequestCount;
  private final RequestQueue[] mRunningRequestQueues = new RequestQueue[2];
  private final SparseArray<RequestQueue> mRunningRequestQueuesMap = new SparseArray();
  private IMms.Stub mStub = new IMms.Stub()
  {
    public Uri addMultimediaMessageDraft(String paramAnonymousString, Uri paramAnonymousUri)
      throws RemoteException
    {
      Log.d("MmsService", "addMultimediaMessageDraft");
      MmsService.this.enforceSystemUid();
      return MmsService.this.addMmsDraft(paramAnonymousUri, paramAnonymousString);
    }
    
    public Uri addTextMessageDraft(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3)
      throws RemoteException
    {
      Log.d("MmsService", "addTextMessageDraft");
      MmsService.this.enforceSystemUid();
      return MmsService.this.addSmsDraft(paramAnonymousString2, paramAnonymousString3, paramAnonymousString1);
    }
    
    public boolean archiveStoredConversation(String paramAnonymousString, long paramAnonymousLong, boolean paramAnonymousBoolean)
      throws RemoteException
    {
      Log.d("MmsService", "archiveStoredConversation " + paramAnonymousLong + " " + paramAnonymousBoolean);
      if (paramAnonymousLong == -1L)
      {
        Log.e("MmsService", "archiveStoredConversation: invalid thread id");
        return false;
      }
      return MmsService.this.archiveConversation(paramAnonymousLong, paramAnonymousBoolean);
    }
    
    public boolean deleteStoredConversation(String paramAnonymousString, long paramAnonymousLong)
      throws RemoteException
    {
      Log.d("MmsService", "deleteStoredConversation " + paramAnonymousLong);
      MmsService.this.enforceSystemUid();
      if (paramAnonymousLong == -1L)
      {
        Log.e("MmsService", "deleteStoredConversation: invalid thread id");
        return false;
      }
      paramAnonymousString = ContentUris.withAppendedId(Telephony.Threads.CONTENT_URI, paramAnonymousLong);
      paramAnonymousLong = Binder.clearCallingIdentity();
      try
      {
        if (getContentResolver().delete(paramAnonymousString, null, null) != 1)
        {
          Log.e("MmsService", "deleteStoredConversation: failed to delete");
          return false;
        }
      }
      catch (SQLiteException paramAnonymousString)
      {
        for (;;)
        {
          Log.e("MmsService", "deleteStoredConversation: failed to delete", paramAnonymousString);
          Binder.restoreCallingIdentity(paramAnonymousLong);
        }
      }
      finally
      {
        Binder.restoreCallingIdentity(paramAnonymousLong);
      }
      return true;
    }
    
    public boolean deleteStoredMessage(String paramAnonymousString, Uri paramAnonymousUri)
      throws RemoteException
    {
      Log.d("MmsService", "deleteStoredMessage " + paramAnonymousUri);
      MmsService.this.enforceSystemUid();
      if (!MmsService.isSmsMmsContentUri(paramAnonymousUri))
      {
        Log.e("MmsService", "deleteStoredMessage: invalid message URI: " + paramAnonymousUri.toString());
        return false;
      }
      l = Binder.clearCallingIdentity();
      try
      {
        if (getContentResolver().delete(paramAnonymousUri, null, null) != 1)
        {
          Log.e("MmsService", "deleteStoredMessage: failed to delete");
          return false;
        }
      }
      catch (SQLiteException paramAnonymousString)
      {
        for (;;)
        {
          Log.e("MmsService", "deleteStoredMessage: failed to delete", paramAnonymousString);
          Binder.restoreCallingIdentity(l);
        }
      }
      finally
      {
        Binder.restoreCallingIdentity(l);
      }
      return true;
    }
    
    public void downloadMessage(int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2, Uri paramAnonymousUri, Bundle paramAnonymousBundle, PendingIntent paramAnonymousPendingIntent)
      throws RemoteException
    {
      Log.d("MmsService", "downloadMessage: " + paramAnonymousString2 + ", subId = " + paramAnonymousInt + ", contentUri = " + paramAnonymousUri);
      MmsService.this.enforceSystemUid();
      paramAnonymousInt = MmsService.this.checkSubId(paramAnonymousInt);
      paramAnonymousString1 = new DownloadRequest(MmsService.this, paramAnonymousInt, paramAnonymousString2, paramAnonymousUri, paramAnonymousPendingIntent, paramAnonymousString1, paramAnonymousBundle);
      paramAnonymousString2 = MmsService.this.getCarrierMessagingServicePackageIfExists();
      if (paramAnonymousString2 != null)
      {
        Log.d("MmsService", "downloading message by carrier app");
        paramAnonymousString1.tryDownloadingByCarrierApp(MmsService.this, paramAnonymousString2);
        return;
      }
      addSimRequest(paramAnonymousString1);
    }
    
    public boolean getAutoPersisting()
      throws RemoteException
    {
      Log.d("MmsService", "getAutoPersisting");
      return getAutoPersistingPref();
    }
    
    public Bundle getCarrierConfigValues(int paramAnonymousInt)
    {
      Log.d("MmsService", "getCarrierConfigValues");
      paramAnonymousInt = MmsService.this.checkSubId(paramAnonymousInt);
      MmsConfig localMmsConfig = MmsConfigManager.getInstance().getMmsConfigBySubId(paramAnonymousInt);
      if (localMmsConfig == null) {
        return new Bundle();
      }
      return localMmsConfig.getCarrierConfigValues();
    }
    
    public Uri importMultimediaMessage(String paramAnonymousString1, Uri paramAnonymousUri, String paramAnonymousString2, long paramAnonymousLong, boolean paramAnonymousBoolean1, boolean paramAnonymousBoolean2)
    {
      Log.d("MmsService", "importMultimediaMessage");
      MmsService.this.enforceSystemUid();
      return MmsService.this.importMms(paramAnonymousUri, paramAnonymousString2, paramAnonymousLong, paramAnonymousBoolean1, paramAnonymousBoolean2, paramAnonymousString1);
    }
    
    public Uri importTextMessage(String paramAnonymousString1, String paramAnonymousString2, int paramAnonymousInt, String paramAnonymousString3, long paramAnonymousLong, boolean paramAnonymousBoolean1, boolean paramAnonymousBoolean2)
    {
      Log.d("MmsService", "importTextMessage");
      MmsService.this.enforceSystemUid();
      return MmsService.this.importSms(paramAnonymousString2, paramAnonymousInt, paramAnonymousString3, paramAnonymousLong, paramAnonymousBoolean1, paramAnonymousBoolean2, paramAnonymousString1);
    }
    
    public void sendMessage(int paramAnonymousInt, String paramAnonymousString1, Uri paramAnonymousUri, String paramAnonymousString2, Bundle paramAnonymousBundle, PendingIntent paramAnonymousPendingIntent)
      throws RemoteException
    {
      Log.d("MmsService", "sendMessage");
      MmsService.this.enforceSystemUid();
      paramAnonymousInt = MmsService.this.checkSubId(paramAnonymousInt);
      paramAnonymousUri = new SendRequest(MmsService.this, paramAnonymousInt, paramAnonymousUri, null, paramAnonymousString2, paramAnonymousPendingIntent, paramAnonymousString1, paramAnonymousBundle);
      if (SmsApplication.shouldWriteMessageForPackage(paramAnonymousString1, MmsService.this)) {
        paramAnonymousUri.storeInOutbox(MmsService.this);
      }
      paramAnonymousString1 = MmsService.this.getCarrierMessagingServicePackageIfExists();
      if (paramAnonymousString1 != null)
      {
        Log.d("MmsService", "sending message by carrier app");
        paramAnonymousUri.trySendingByCarrierApp(MmsService.this, paramAnonymousString1);
        return;
      }
      addSimRequest(paramAnonymousUri);
    }
    
    public void sendStoredMessage(int paramAnonymousInt, String paramAnonymousString, Uri paramAnonymousUri, Bundle paramAnonymousBundle, PendingIntent paramAnonymousPendingIntent)
      throws RemoteException
    {
      Log.d("MmsService", "sendStoredMessage: messageUri = " + paramAnonymousUri + ", subId = " + paramAnonymousInt);
      MmsService.this.enforceSystemUid();
      paramAnonymousInt = MmsService.this.checkSubId(paramAnonymousInt);
      if (!MmsService.this.isFailedOrDraft(paramAnonymousUri))
      {
        Log.e("MmsService", "sendStoredMessage: not FAILED or DRAFT message");
        MmsService.this.returnUnspecifiedFailure(paramAnonymousPendingIntent);
        return;
      }
      byte[] arrayOfByte = MmsService.this.loadPdu(paramAnonymousUri);
      if ((arrayOfByte == null) || (arrayOfByte.length < 1))
      {
        Log.e("MmsService", "sendStoredMessage: failed to load PDU data");
        MmsService.this.returnUnspecifiedFailure(paramAnonymousPendingIntent);
        return;
      }
      paramAnonymousString = new SendRequest(MmsService.this, paramAnonymousInt, arrayOfByte, paramAnonymousUri, null, paramAnonymousPendingIntent, paramAnonymousString, paramAnonymousBundle);
      paramAnonymousString.storeInOutbox(MmsService.this);
      paramAnonymousUri = MmsService.this.getCarrierMessagingServicePackageIfExists();
      if (paramAnonymousUri != null)
      {
        Log.d("MmsService", "sending message by carrier app");
        paramAnonymousString.trySendingByCarrierApp(MmsService.this, paramAnonymousUri);
        return;
      }
      addSimRequest(paramAnonymousString);
    }
    
    public void setAutoPersisting(String paramAnonymousString, boolean paramAnonymousBoolean)
      throws RemoteException
    {
      Log.d("MmsService", "setAutoPersisting " + paramAnonymousBoolean);
      MmsService.this.enforceSystemUid();
      paramAnonymousString = getSharedPreferences("mmspref", 0).edit();
      paramAnonymousString.putBoolean("autopersisting", paramAnonymousBoolean);
      paramAnonymousString.apply();
    }
    
    public boolean updateStoredMessageStatus(String paramAnonymousString, Uri paramAnonymousUri, ContentValues paramAnonymousContentValues)
      throws RemoteException
    {
      Log.d("MmsService", "updateStoredMessageStatus " + paramAnonymousUri);
      MmsService.this.enforceSystemUid();
      return MmsService.this.updateMessageStatus(paramAnonymousUri, paramAnonymousContentValues);
    }
  };
  
  private Uri addMmsDraft(Uri paramUri, String paramString)
  {
    paramUri = readPduFromContentUri(paramUri, 8388608);
    if ((paramUri == null) || (paramUri.length < 1))
    {
      Log.e("MmsService", "addMmsDraft: empty PDU");
      return null;
    }
    long l = Binder.clearCallingIdentity();
    try
    {
      paramUri = parsePduForAnyCarrier(paramUri);
      if (paramUri == null)
      {
        Log.e("MmsService", "addMmsDraft: can't parse input PDU");
        return null;
      }
      if (!(paramUri instanceof MzSendReq))
      {
        Log.e("MmsService", "addMmsDraft; invalid MMS type: " + paramUri.getClass().getCanonicalName());
        return null;
      }
      paramUri = MzPduPersister.getPduPersister(this).persist(paramUri, Telephony.Mms.Draft.CONTENT_URI, true, true, null);
      if (paramUri == null)
      {
        Log.e("MmsService", "addMmsDraft: failed to persist message");
        return null;
      }
      ContentValues localContentValues = new ContentValues(3);
      localContentValues.put("read", Integer.valueOf(1));
      localContentValues.put("seen", Integer.valueOf(1));
      if (!TextUtils.isEmpty(paramString)) {
        localContentValues.put("creator", paramString);
      }
      if (MzSqliteWrapper.update(this, getContentResolver(), paramUri, localContentValues, null, null) != 1) {
        Log.e("MmsService", "addMmsDraft: failed to update message");
      }
      return paramUri;
    }
    catch (RuntimeException paramUri)
    {
      Log.e("MmsService", "addMmsDraft: failed to parse input PDU", paramUri);
      return null;
    }
    catch (MmsException paramUri)
    {
      for (;;)
      {
        Log.e("MmsService", "addMmsDraft: failed to persist message", paramUri);
        Binder.restoreCallingIdentity(l);
      }
    }
    finally
    {
      Binder.restoreCallingIdentity(l);
    }
  }
  
  private Uri addSmsDraft(String paramString1, String paramString2, String paramString3)
  {
    ContentValues localContentValues = new ContentValues(5);
    localContentValues.put("address", paramString1);
    localContentValues.put("body", paramString2);
    localContentValues.put("read", Integer.valueOf(1));
    localContentValues.put("seen", Integer.valueOf(1));
    if (!TextUtils.isEmpty(paramString3)) {
      localContentValues.put("creator", paramString3);
    }
    long l = Binder.clearCallingIdentity();
    try
    {
      paramString1 = getContentResolver().insert(Telephony.Sms.Draft.CONTENT_URI, localContentValues);
      return paramString1;
    }
    catch (SQLiteException paramString1)
    {
      Log.e("MmsService", "addSmsDraft: failed to store draft message", paramString1);
      return null;
    }
    finally
    {
      Binder.restoreCallingIdentity(l);
    }
  }
  
  private void addToRunningRequestQueueSynchronized(MmsRequest paramMmsRequest)
  {
    Log.d("MmsService", "Add request to running queue for subId " + paramMmsRequest.getSubId());
    mCurrentSubId = paramMmsRequest.getSubId();
    mRunningRequestCount += 1;
    RequestQueue localRequestQueue = getRequestQueue(mCurrentSubId);
    if (localRequestQueue == null) {
      return;
    }
    Message localMessage = Message.obtain();
    obj = paramMmsRequest;
    localRequestQueue.sendMessage(localMessage);
  }
  
  private boolean archiveConversation(long paramLong, boolean paramBoolean)
  {
    ContentValues localContentValues = new ContentValues(1);
    int i;
    if (paramBoolean) {
      i = 1;
    }
    for (;;)
    {
      localContentValues.put("archived", Integer.valueOf(i));
      long l = Binder.clearCallingIdentity();
      try
      {
        if (getContentResolver().update(Telephony.Threads.CONTENT_URI, localContentValues, "_id=?", new String[] { Long.toString(paramLong) }) != 1)
        {
          Log.e("MmsService", "archiveConversation: failed to update database");
          return false;
          i = 0;
          continue;
        }
        return true;
      }
      catch (SQLiteException localSQLiteException)
      {
        Log.e("MmsService", "archiveConversation: failed to update database", localSQLiteException);
        return false;
      }
      finally
      {
        Binder.restoreCallingIdentity(l);
      }
    }
  }
  
  private int checkSubId(int paramInt)
  {
    if (!SubscriptionManager.isValidSubscriptionId(paramInt)) {
      throw new RuntimeException("Invalid subId " + paramInt);
    }
    int i = paramInt;
    if (paramInt == Integer.MAX_VALUE) {
      i = SubscriptionManager.getDefaultSmsSubId();
    }
    return i;
  }
  
  private void enforceSystemUid()
  {
    if (Binder.getCallingUid() != 1000) {
      throw new SecurityException("Only system can call this service");
    }
  }
  
  private String getCarrierMessagingServicePackageIfExists()
  {
    Object localObject = new Intent("android.service.carrier.CarrierMessagingService");
    localObject = ((TelephonyManager)getSystemService("phone")).getCarrierPackageNamesForIntent((Intent)localObject);
    if ((localObject == null) || (((List)localObject).size() != 1)) {
      return null;
    }
    return (String)((List)localObject).get(0);
  }
  
  private MmsNetworkManager getNetworkManager(int paramInt)
  {
    synchronized (mNetworkManagerCache)
    {
      MmsNetworkManager localMmsNetworkManager2 = (MmsNetworkManager)mNetworkManagerCache.get(paramInt);
      MmsNetworkManager localMmsNetworkManager1 = localMmsNetworkManager2;
      if (localMmsNetworkManager2 == null)
      {
        localMmsNetworkManager1 = new MmsNetworkManager(this, paramInt);
        mNetworkManagerCache.put(paramInt, localMmsNetworkManager1);
      }
      return localMmsNetworkManager1;
    }
  }
  
  private Queue<MmsRequest> getPendingSimRequestQueue(int paramInt)
  {
    if (!SubscriptionManager.isValidSubscriptionId(paramInt)) {
      return null;
    }
    synchronized (mPendingSimRequestQueues)
    {
      Queue localQueue = (Queue)mPendingSimRequestQueues.get(paramInt);
      Object localObject1 = localQueue;
      if (localQueue == null)
      {
        localObject1 = new ArrayDeque();
        mPendingSimRequestQueues.put(paramInt, localObject1);
      }
      return (Queue<MmsRequest>)localObject1;
    }
  }
  
  private RequestQueue getRequestQueue(int paramInt)
  {
    if (!SubscriptionManager.isValidSubscriptionId(paramInt)) {
      return null;
    }
    synchronized (mRunningRequestQueuesMap)
    {
      RequestQueue localRequestQueue = (RequestQueue)mRunningRequestQueuesMap.get(paramInt);
      Object localObject1 = localRequestQueue;
      if (localRequestQueue == null)
      {
        localObject1 = new HandlerThread("MmsService RequestQueueEx " + paramInt);
        ((HandlerThread)localObject1).start();
        localObject1 = new RequestQueue(((HandlerThread)localObject1).getLooper());
        mRunningRequestQueuesMap.put(paramInt, localObject1);
      }
      return (RequestQueue)localObject1;
    }
  }
  
  private Uri importMms(Uri paramUri, String paramString1, long paramLong, boolean paramBoolean1, boolean paramBoolean2, String paramString2)
  {
    paramUri = readPduFromContentUri(paramUri, 8388608);
    if ((paramUri == null) || (paramUri.length < 1))
    {
      Log.e("MmsService", "importMessage: empty PDU");
      return null;
    }
    long l = Binder.clearCallingIdentity();
    for (;;)
    {
      int i;
      try
      {
        localObject = parsePduForAnyCarrier(paramUri);
        if (localObject == null)
        {
          Log.e("MmsService", "importMessage: can't parse input PDU");
          return null;
        }
        paramUri = null;
        if ((localObject instanceof MzSendReq))
        {
          paramUri = Telephony.Mms.Sent.CONTENT_URI;
          if (paramUri == null)
          {
            Log.e("MmsService", "importMessage; invalid MMS type: " + localObject.getClass().getCanonicalName());
            return null;
          }
        }
        else
        {
          if ((!(localObject instanceof MzRetrieveConf)) && (!(localObject instanceof MzNotificationInd)) && (!(localObject instanceof MzDeliveryInd)) && (!(localObject instanceof MzReadOrigInd))) {
            continue;
          }
          paramUri = Telephony.Mms.Inbox.CONTENT_URI;
          continue;
        }
        paramUri = MzPduPersister.getPduPersister(this).persist((MzGenericPdu)localObject, paramUri, true, true, null);
        if (paramUri == null)
        {
          Log.e("MmsService", "importMessage: failed to persist message");
          return null;
        }
        localObject = new ContentValues(5);
        if (!TextUtils.isEmpty(paramString1)) {
          ((ContentValues)localObject).put("m_id", paramString1);
        }
        if (paramLong == -1L) {
          break label382;
        }
        ((ContentValues)localObject).put("date", Long.valueOf(paramLong));
      }
      catch (RuntimeException paramUri)
      {
        Object localObject;
        Log.e("MmsService", "importMessage: failed to parse input PDU", paramUri);
        return null;
      }
      catch (MmsException paramUri)
      {
        Log.e("MmsService", "importMessage: failed to persist message", paramUri);
        Binder.restoreCallingIdentity(l);
        continue;
      }
      finally
      {
        Binder.restoreCallingIdentity(l);
      }
      ((ContentValues)localObject).put("read", Integer.valueOf(i));
      if (paramBoolean2)
      {
        i = 1;
        ((ContentValues)localObject).put("seen", Integer.valueOf(i));
        if (!TextUtils.isEmpty(paramString2)) {
          ((ContentValues)localObject).put("creator", paramString2);
        }
        if (MzSqliteWrapper.update(this, getContentResolver(), paramUri, (ContentValues)localObject, null, null) != 1) {
          Log.e("MmsService", "importMessage: failed to update message");
        }
        Binder.restoreCallingIdentity(l);
        return paramUri;
        i = 0;
      }
      else
      {
        i = 0;
        continue;
        label382:
        if (paramBoolean1) {
          i = 1;
        }
      }
    }
  }
  
  private Uri importSms(String paramString1, int paramInt, String paramString2, long paramLong, boolean paramBoolean1, boolean paramBoolean2, String paramString3)
  {
    Uri localUri = null;
    switch (paramInt)
    {
    }
    while (localUri == null)
    {
      Log.e("MmsService", "importTextMessage: invalid message type for importing: " + paramInt);
      return null;
      localUri = Telephony.Sms.Inbox.CONTENT_URI;
      continue;
      localUri = Telephony.Sms.Sent.CONTENT_URI;
    }
    ContentValues localContentValues = new ContentValues(6);
    localContentValues.put("address", paramString1);
    localContentValues.put("date", Long.valueOf(paramLong));
    if (paramBoolean1)
    {
      paramInt = 1;
      localContentValues.put("seen", Integer.valueOf(paramInt));
      if (!paramBoolean2) {
        break label203;
      }
    }
    label203:
    for (paramInt = 1;; paramInt = 0)
    {
      localContentValues.put("read", Integer.valueOf(paramInt));
      localContentValues.put("body", paramString2);
      if (!TextUtils.isEmpty(paramString3)) {
        localContentValues.put("creator", paramString3);
      }
      paramLong = Binder.clearCallingIdentity();
      try
      {
        paramString1 = getContentResolver().insert(localUri, localContentValues);
        return paramString1;
      }
      catch (SQLiteException paramString1)
      {
        Log.e("MmsService", "importTextMessage: failed to persist imported text message", paramString1);
        return null;
      }
      finally
      {
        Binder.restoreCallingIdentity(paramLong);
      }
      paramInt = 0;
      break;
    }
  }
  
  private boolean isFailedOrDraft(Uri paramUri)
  {
    l = Binder.clearCallingIdentity();
    localObject = null;
    localUri = null;
    try
    {
      paramUri = getContentResolver().query(paramUri, new String[] { "msg_box" }, null, null, null);
      if (paramUri != null)
      {
        localUri = paramUri;
        localObject = paramUri;
        if (paramUri.moveToFirst())
        {
          localUri = paramUri;
          localObject = paramUri;
          int i = paramUri.getInt(0);
          if ((i == 3) || (i == 5) || (i == 4)) {}
          for (boolean bool = true;; bool = false)
          {
            if (paramUri != null) {
              paramUri.close();
            }
            Binder.restoreCallingIdentity(l);
            return bool;
          }
        }
      }
      if (paramUri != null) {
        paramUri.close();
      }
      Binder.restoreCallingIdentity(l);
    }
    catch (SQLiteException paramUri)
    {
      for (;;)
      {
        localObject = localUri;
        Log.e("MmsService", "isFailedOrDraft: query message type failed", paramUri);
        if (localUri != null) {
          localUri.close();
        }
        Binder.restoreCallingIdentity(l);
      }
    }
    finally
    {
      if (localObject == null) {
        break label169;
      }
      ((Cursor)localObject).close();
      Binder.restoreCallingIdentity(l);
    }
    return false;
  }
  
  private static boolean isSmsMmsContentUri(Uri paramUri)
  {
    String str = paramUri.toString();
    if ((!str.startsWith("content://sms/")) && (!str.startsWith("content://mms/"))) {}
    while (ContentUris.parseId(paramUri) == -1L) {
      return false;
    }
    return true;
  }
  
  private byte[] loadPdu(Uri paramUri)
  {
    long l = Binder.clearCallingIdentity();
    try
    {
      Object localObject = MzPduPersister.getPduPersister(this).load(paramUri);
      if (localObject == null)
      {
        Log.e("MmsService", "loadPdu: failed to load PDU from " + paramUri.toString());
        return null;
      }
      localObject = new MzPduComposer(this, (MzGenericPdu)localObject).make();
      return (byte[])localObject;
    }
    catch (MmsException localMmsException)
    {
      Log.e("MmsService", "loadPdu: failed to load PDU from " + paramUri.toString(), localMmsException);
      return null;
    }
    catch (RuntimeException paramUri)
    {
      Log.e("MmsService", "loadPdu: failed to serialize PDU", paramUri);
      return null;
    }
    finally
    {
      Binder.restoreCallingIdentity(l);
    }
  }
  
  private void movePendingSimRequestsToRunningSynchronized()
  {
    Log.d("MmsService", "Schedule requests pending on SIM");
    Queue localQueue = getPendingSimRequestQueue(mCurrentSubId);
    MmsRequest localMmsRequest;
    if ((localQueue != null) && (localQueue.size() > 0)) {
      if (localQueue.size() > 0)
      {
        localMmsRequest = (MmsRequest)localQueue.peek();
        if (localMmsRequest == null) {
          break label67;
        }
        localQueue.remove();
        addToRunningRequestQueueSynchronized(localMmsRequest);
      }
    }
    for (;;)
    {
      return;
      label67:
      Log.e("MmsService", "Schedule pending: found empty request");
      localQueue.remove();
      break;
      int i = 0;
      while (i < mPendingSimRequestQueues.size())
      {
        localQueue = (Queue)mPendingSimRequestQueues.valueAt(i);
        if ((localQueue != null) && (localQueue.size() > 0)) {
          while (localQueue.size() > 0)
          {
            localMmsRequest = (MmsRequest)localQueue.peek();
            if (localMmsRequest != null)
            {
              localQueue.remove();
              addToRunningRequestQueueSynchronized(localMmsRequest);
              return;
            }
            Log.e("MmsService", "Schedule pending: found empty request");
            localQueue.remove();
          }
        }
        i += 1;
      }
    }
  }
  
  private static MzGenericPdu parsePduForAnyCarrier(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = new MzPduParser(paramArrayOfByte).parse();
      return paramArrayOfByte;
    }
    catch (RuntimeException paramArrayOfByte)
    {
      Log.d("MmsService", "parsePduForAnyCarrier: Failed to parse PDU with content disposition", paramArrayOfByte);
    }
    return null;
  }
  
  private void returnUnspecifiedFailure(PendingIntent paramPendingIntent)
  {
    if (paramPendingIntent != null) {}
    try
    {
      paramPendingIntent.send(1);
      return;
    }
    catch (PendingIntent.CanceledException paramPendingIntent) {}
  }
  
  private boolean updateMessageStatus(Uri paramUri, ContentValues paramContentValues)
  {
    if (!isSmsMmsContentUri(paramUri))
    {
      Log.e("MmsService", "updateMessageStatus: invalid messageUri: " + paramUri.toString());
      return false;
    }
    if (paramContentValues == null)
    {
      Log.w("MmsService", "updateMessageStatus: empty values to update");
      return false;
    }
    ContentValues localContentValues = new ContentValues();
    if (paramContentValues.containsKey("read"))
    {
      paramContentValues = paramContentValues.getAsInteger("read");
      if (paramContentValues != null) {
        localContentValues.put("read", paramContentValues);
      }
    }
    while (localContentValues.size() < 1)
    {
      Log.w("MmsService", "updateMessageStatus: no value to update");
      return false;
      if (paramContentValues.containsKey("seen"))
      {
        paramContentValues = paramContentValues.getAsInteger("seen");
        if (paramContentValues != null) {
          localContentValues.put("seen", paramContentValues);
        }
      }
    }
    long l = Binder.clearCallingIdentity();
    try
    {
      if (getContentResolver().update(paramUri, localContentValues, null, null) != 1)
      {
        Log.e("MmsService", "updateMessageStatus: failed to update database");
        return false;
      }
      return true;
    }
    catch (SQLiteException paramUri)
    {
      Log.e("MmsService", "updateMessageStatus: failed to update database", paramUri);
      return false;
    }
    finally
    {
      Binder.restoreCallingIdentity(l);
    }
  }
  
  private void updatePendingMmsRequestCountForSubId(int paramInt)
  {
    Queue localQueue = getPendingSimRequestQueue(paramInt);
    if (localQueue == null) {
      return;
    }
    getNetworkManager(paramInt).updatePendingMmsRequestCount(localQueue.size());
  }
  
  public void addSimRequest(MmsRequest paramMmsRequest)
  {
    if (paramMmsRequest == null)
    {
      Log.e("MmsService", "Add running or pending: empty request");
      return;
    }
    Queue localQueue;
    try
    {
      localQueue = getPendingSimRequestQueue(paramMmsRequest.getSubId());
      if (localQueue == null) {
        return;
      }
    }
    finally {}
    Log.d("MmsService", "Current running=" + mRunningRequestCount + ", " + "current subId=" + mCurrentSubId + ", " + "pending request count" + localQueue.size());
    if ((localQueue.size() > 0) || (mRunningRequestCount > 0))
    {
      localQueue.add(paramMmsRequest);
      updatePendingMmsRequestCountForSubId(paramMmsRequest.getSubId());
    }
    for (;;)
    {
      return;
      if ((localQueue.size() <= 0) && (mRunningRequestCount <= 0) && (paramMmsRequest.getSubId() == mCurrentSubId))
      {
        addToRunningRequestQueueSynchronized(paramMmsRequest);
      }
      else
      {
        localQueue.add(paramMmsRequest);
        movePendingSimRequestsToRunningSynchronized();
        int i = 0;
        while (i < mPendingSimRequestQueues.size())
        {
          updatePendingMmsRequestCountForSubId(mPendingSimRequestQueues.keyAt(i));
          i += 1;
        }
      }
    }
  }
  
  public boolean getAutoPersistingPref()
  {
    return getSharedPreferences("mmspref", 0).getBoolean("autopersisting", false);
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return mStub;
  }
  
  public void onCreate()
  {
    super.onCreate();
    Log.d("MmsService", "onCreate");
    MmsConfigManager.getInstance().init(this);
    try
    {
      mCurrentSubId = -1;
      mRunningRequestCount = 0;
      return;
    }
    finally {}
  }
  
  public byte[] readPduFromContentUri(final Uri paramUri, final int paramInt)
  {
    Log.d("MmsService", "readPduFromContentUri(), uri = " + paramUri + ", maxSize = " + paramInt);
    paramUri = new Callable()
    {
      /* Error */
      public byte[] call()
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore_2
        //   2: aconst_null
        //   3: astore 4
        //   5: new 42	android/os/ParcelFileDescriptor$AutoCloseInputStream
        //   8: dup
        //   9: aload_0
        //   10: getfield 22	com/android/mms/service/MmsService$2:this$0	Lcom/android/mms/service/MmsService;
        //   13: invokevirtual 46	com/android/mms/service/MmsService:getContentResolver	()Landroid/content/ContentResolver;
        //   16: aload_0
        //   17: getfield 24	com/android/mms/service/MmsService$2:val$contentUri	Landroid/net/Uri;
        //   20: ldc 48
        //   22: invokevirtual 54	android/content/ContentResolver:openFileDescriptor	(Landroid/net/Uri;Ljava/lang/String;)Landroid/os/ParcelFileDescriptor;
        //   25: invokespecial 57	android/os/ParcelFileDescriptor$AutoCloseInputStream:<init>	(Landroid/os/ParcelFileDescriptor;)V
        //   28: astore_3
        //   29: aload_0
        //   30: getfield 26	com/android/mms/service/MmsService$2:val$maxSize	I
        //   33: iconst_1
        //   34: iadd
        //   35: newarray <illegal type>
        //   37: astore_2
        //   38: aload_3
        //   39: aload_2
        //   40: iconst_0
        //   41: aload_0
        //   42: getfield 26	com/android/mms/service/MmsService$2:val$maxSize	I
        //   45: iconst_1
        //   46: iadd
        //   47: invokevirtual 61	android/os/ParcelFileDescriptor$AutoCloseInputStream:read	([BII)I
        //   50: istore_1
        //   51: iload_1
        //   52: ifne +21 -> 73
        //   55: ldc 63
        //   57: ldc 65
        //   59: invokestatic 71	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   62: pop
        //   63: aload_3
        //   64: ifnull +7 -> 71
        //   67: aload_3
        //   68: invokevirtual 74	android/os/ParcelFileDescriptor$AutoCloseInputStream:close	()V
        //   71: aconst_null
        //   72: areturn
        //   73: iload_1
        //   74: aload_0
        //   75: getfield 26	com/android/mms/service/MmsService$2:val$maxSize	I
        //   78: if_icmpgt +19 -> 97
        //   81: aload_2
        //   82: iload_1
        //   83: invokestatic 80	java/util/Arrays:copyOf	([BI)[B
        //   86: astore_2
        //   87: aload_3
        //   88: ifnull +7 -> 95
        //   91: aload_3
        //   92: invokevirtual 74	android/os/ParcelFileDescriptor$AutoCloseInputStream:close	()V
        //   95: aload_2
        //   96: areturn
        //   97: ldc 63
        //   99: ldc 82
        //   101: invokestatic 71	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   104: pop
        //   105: aload_3
        //   106: ifnull +7 -> 113
        //   109: aload_3
        //   110: invokevirtual 74	android/os/ParcelFileDescriptor$AutoCloseInputStream:close	()V
        //   113: aconst_null
        //   114: areturn
        //   115: astore_2
        //   116: aload 4
        //   118: astore_3
        //   119: aload_2
        //   120: astore 4
        //   122: aload_3
        //   123: astore_2
        //   124: ldc 63
        //   126: ldc 84
        //   128: aload 4
        //   130: invokestatic 87	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   133: pop
        //   134: aload_3
        //   135: ifnull -64 -> 71
        //   138: aload_3
        //   139: invokevirtual 74	android/os/ParcelFileDescriptor$AutoCloseInputStream:close	()V
        //   142: aconst_null
        //   143: areturn
        //   144: astore_2
        //   145: aconst_null
        //   146: areturn
        //   147: astore_3
        //   148: aload_2
        //   149: ifnull +7 -> 156
        //   152: aload_2
        //   153: invokevirtual 74	android/os/ParcelFileDescriptor$AutoCloseInputStream:close	()V
        //   156: aload_3
        //   157: athrow
        //   158: astore_2
        //   159: goto -88 -> 71
        //   162: astore_3
        //   163: goto -68 -> 95
        //   166: astore_2
        //   167: goto -54 -> 113
        //   170: astore_2
        //   171: goto -15 -> 156
        //   174: astore 4
        //   176: aload_3
        //   177: astore_2
        //   178: aload 4
        //   180: astore_3
        //   181: goto -33 -> 148
        //   184: astore 4
        //   186: goto -64 -> 122
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	189	0	this	2
        //   50	33	1	i	int
        //   1	95	2	arrayOfByte	byte[]
        //   115	5	2	localIOException1	java.io.IOException
        //   123	1	2	localObject1	Object
        //   144	9	2	localIOException2	java.io.IOException
        //   158	1	2	localIOException3	java.io.IOException
        //   166	1	2	localIOException4	java.io.IOException
        //   170	1	2	localIOException5	java.io.IOException
        //   177	1	2	localObject2	Object
        //   28	111	3	localObject3	Object
        //   147	10	3	localObject4	Object
        //   162	15	3	localIOException6	java.io.IOException
        //   180	1	3	localObject5	Object
        //   3	126	4	localIOException7	java.io.IOException
        //   174	5	4	localObject6	Object
        //   184	1	4	localIOException8	java.io.IOException
        // Exception table:
        //   from	to	target	type
        //   5	29	115	java/io/IOException
        //   138	142	144	java/io/IOException
        //   5	29	147	finally
        //   124	134	147	finally
        //   67	71	158	java/io/IOException
        //   91	95	162	java/io/IOException
        //   109	113	166	java/io/IOException
        //   152	156	170	java/io/IOException
        //   29	51	174	finally
        //   55	63	174	finally
        //   73	87	174	finally
        //   97	105	174	finally
        //   29	51	184	java/io/IOException
        //   55	63	184	java/io/IOException
        //   73	87	184	java/io/IOException
        //   97	105	184	java/io/IOException
      }
    };
    paramUri = mExecutor.submit(paramUri);
    try
    {
      byte[] arrayOfByte = (byte[])paramUri.get(30000L, TimeUnit.MILLISECONDS);
      return arrayOfByte;
    }
    catch (Exception localException)
    {
      paramUri.cancel(true);
    }
    return null;
  }
  
  public boolean writePduToContentUri(final Uri paramUri, final byte[] paramArrayOfByte)
  {
    paramUri = new Callable()
    {
      /* Error */
      public Boolean call()
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore_1
        //   2: aconst_null
        //   3: astore_3
        //   4: new 36	android/os/ParcelFileDescriptor$AutoCloseOutputStream
        //   7: dup
        //   8: aload_0
        //   9: getfield 22	com/android/mms/service/MmsService$3:this$0	Lcom/android/mms/service/MmsService;
        //   12: invokevirtual 40	com/android/mms/service/MmsService:getContentResolver	()Landroid/content/ContentResolver;
        //   15: aload_0
        //   16: getfield 24	com/android/mms/service/MmsService$3:val$contentUri	Landroid/net/Uri;
        //   19: ldc 42
        //   21: invokevirtual 48	android/content/ContentResolver:openFileDescriptor	(Landroid/net/Uri;Ljava/lang/String;)Landroid/os/ParcelFileDescriptor;
        //   24: invokespecial 51	android/os/ParcelFileDescriptor$AutoCloseOutputStream:<init>	(Landroid/os/ParcelFileDescriptor;)V
        //   27: astore_2
        //   28: aload_2
        //   29: aload_0
        //   30: getfield 26	com/android/mms/service/MmsService$3:val$pdu	[B
        //   33: invokevirtual 55	android/os/ParcelFileDescriptor$AutoCloseOutputStream:write	([B)V
        //   36: getstatic 61	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
        //   39: astore_1
        //   40: aload_2
        //   41: ifnull +7 -> 48
        //   44: aload_2
        //   45: invokevirtual 64	android/os/ParcelFileDescriptor$AutoCloseOutputStream:close	()V
        //   48: aload_1
        //   49: areturn
        //   50: astore_1
        //   51: aload_3
        //   52: astore_2
        //   53: aload_2
        //   54: astore_1
        //   55: getstatic 67	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
        //   58: astore_3
        //   59: aload_3
        //   60: astore_1
        //   61: aload_2
        //   62: ifnull -14 -> 48
        //   65: aload_2
        //   66: invokevirtual 64	android/os/ParcelFileDescriptor$AutoCloseOutputStream:close	()V
        //   69: aload_3
        //   70: areturn
        //   71: astore_1
        //   72: aload_3
        //   73: areturn
        //   74: astore_2
        //   75: aload_1
        //   76: ifnull +7 -> 83
        //   79: aload_1
        //   80: invokevirtual 64	android/os/ParcelFileDescriptor$AutoCloseOutputStream:close	()V
        //   83: aload_2
        //   84: athrow
        //   85: astore_2
        //   86: goto -38 -> 48
        //   89: astore_1
        //   90: goto -7 -> 83
        //   93: astore_3
        //   94: aload_2
        //   95: astore_1
        //   96: aload_3
        //   97: astore_2
        //   98: goto -23 -> 75
        //   101: astore_1
        //   102: goto -49 -> 53
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	105	0	this	3
        //   1	48	1	localBoolean1	Boolean
        //   50	1	1	localIOException1	java.io.IOException
        //   54	7	1	localObject1	Object
        //   71	9	1	localIOException2	java.io.IOException
        //   89	1	1	localIOException3	java.io.IOException
        //   95	1	1	localObject2	Object
        //   101	1	1	localIOException4	java.io.IOException
        //   27	39	2	localObject3	Object
        //   74	10	2	localObject4	Object
        //   85	10	2	localIOException5	java.io.IOException
        //   97	1	2	localObject5	Object
        //   3	70	3	localBoolean2	Boolean
        //   93	4	3	localObject6	Object
        // Exception table:
        //   from	to	target	type
        //   4	28	50	java/io/IOException
        //   65	69	71	java/io/IOException
        //   4	28	74	finally
        //   55	59	74	finally
        //   44	48	85	java/io/IOException
        //   79	83	89	java/io/IOException
        //   28	40	93	finally
        //   28	40	101	java/io/IOException
      }
    };
    paramUri = mExecutor.submit(paramUri);
    try
    {
      paramArrayOfByte = (Boolean)paramUri.get(30000L, TimeUnit.MILLISECONDS);
      Boolean localBoolean = Boolean.TRUE;
      return paramArrayOfByte == localBoolean;
    }
    catch (Exception paramArrayOfByte)
    {
      paramUri.cancel(true);
    }
    return false;
  }
  
  private class RequestQueue
    extends Handler
  {
    public RequestQueue(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message arg1)
    {
      ??? = (MmsRequest)obj;
      if (??? != null) {
        try
        {
          ???.execute(MmsService.this, MmsService.this.getNetworkManager(???.getSubId()));
          int i;
          int j;
          synchronized (MmsService.this)
          {
            MmsService.access$110(MmsService.this);
            if (mRunningRequestCount <= 0)
            {
              MmsService.this.movePendingSimRequestsToRunningSynchronized();
              i = 0;
              while (i < mPendingSimRequestQueues.size())
              {
                j = mPendingSimRequestQueues.keyAt(i);
                MmsService.this.updatePendingMmsRequestCountForSubId(j);
                i += 1;
              }
            }
            return;
          }
          Log.e("MmsService", "RequestQueue: handling empty request");
        }
        finally {}
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.MmsService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */