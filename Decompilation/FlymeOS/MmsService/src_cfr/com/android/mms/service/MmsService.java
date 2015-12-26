/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.PendingIntent
 *  android.app.PendingIntent$CanceledException
 *  android.app.Service
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteException
 *  android.net.Uri
 *  android.os.Binder
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.HandlerThread
 *  android.os.IBinder
 *  android.os.Looper
 *  android.os.Message
 *  android.os.ParcelFileDescriptor
 *  android.os.ParcelFileDescriptor$AutoCloseInputStream
 *  android.os.ParcelFileDescriptor$AutoCloseOutputStream
 *  android.os.RemoteException
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Draft
 *  android.provider.Telephony$Mms$Inbox
 *  android.provider.Telephony$Mms$Sent
 *  android.provider.Telephony$Sms
 *  android.provider.Telephony$Sms$Draft
 *  android.provider.Telephony$Sms$Inbox
 *  android.provider.Telephony$Sms$Sent
 *  android.provider.Telephony$Threads
 *  android.telephony.SubscriptionManager
 *  android.telephony.TelephonyManager
 *  android.text.TextUtils
 *  android.util.Log
 *  android.util.SparseArray
 *  com.android.internal.telephony.IMms
 *  com.android.internal.telephony.IMms$Stub
 *  com.android.internal.telephony.SmsApplication
 *  com.google.android.mms.MmsException
 *  com.meizu.android.mms.pdu.MzDeliveryInd
 *  com.meizu.android.mms.pdu.MzGenericPdu
 *  com.meizu.android.mms.pdu.MzNotificationInd
 *  com.meizu.android.mms.pdu.MzPduComposer
 *  com.meizu.android.mms.pdu.MzPduParser
 *  com.meizu.android.mms.pdu.MzPduPersister
 *  com.meizu.android.mms.pdu.MzReadOrigInd
 *  com.meizu.android.mms.pdu.MzRetrieveConf
 *  com.meizu.android.mms.pdu.MzSendReq
 *  com.meizu.android.mms.util.MzSqliteWrapper
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayDeque
 *  java.util.Arrays
 *  java.util.HashMap
 *  java.util.concurrent.Executors
 *  java.util.concurrent.TimeUnit
 */
package com.android.mms.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.provider.Telephony;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.telephony.IMms;
import com.android.internal.telephony.SmsApplication;
import com.android.mms.service.DownloadRequest;
import com.android.mms.service.MmsConfig;
import com.android.mms.service.MmsConfigManager;
import com.android.mms.service.MmsNetworkManager;
import com.android.mms.service.MmsRequest;
import com.android.mms.service.SendRequest;
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
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MmsService
extends Service
implements MmsRequest.RequestManager {
    private int mCurrentSubId;
    private final ExecutorService mExecutor = Executors.newCachedThreadPool();
    private final SparseArray<MmsNetworkManager> mNetworkManagerCache = new SparseArray();
    private final Queue<MmsRequest> mPendingSimRequestQueue = new ArrayDeque();
    private final SparseArray<Queue<MmsRequest>> mPendingSimRequestQueues;
    private int mRunningRequestCount;
    private final RequestQueue[] mRunningRequestQueues;
    private final SparseArray<RequestQueue> mRunningRequestQueuesMap;
    private IMms.Stub mStub;

    public MmsService() {
        this.mStub = new IMms.Stub(){

            public Uri addMultimediaMessageDraft(String string, Uri uri) throws RemoteException {
                Log.d((String)"MmsService", (String)"addMultimediaMessageDraft");
                MmsService.this.enforceSystemUid();
                return MmsService.this.addMmsDraft(uri, string);
            }

            public Uri addTextMessageDraft(String string, String string2, String string3) throws RemoteException {
                Log.d((String)"MmsService", (String)"addTextMessageDraft");
                MmsService.this.enforceSystemUid();
                return MmsService.this.addSmsDraft(string2, string3, string);
            }

            public boolean archiveStoredConversation(String string, long l, boolean bl) throws RemoteException {
                Log.d((String)"MmsService", (String)("archiveStoredConversation " + l + " " + bl));
                if (l == -1) {
                    Log.e((String)"MmsService", (String)"archiveStoredConversation: invalid thread id");
                    return false;
                }
                return MmsService.this.archiveConversation(l, bl);
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public boolean deleteStoredConversation(String string, long l) throws RemoteException {
                Log.d((String)"MmsService", (String)("deleteStoredConversation " + l));
                MmsService.this.enforceSystemUid();
                if (l == -1) {
                    Log.e((String)"MmsService", (String)"deleteStoredConversation: invalid thread id");
                    return false;
                }
                string = ContentUris.withAppendedId((Uri)Telephony.Threads.CONTENT_URI, (long)l);
                l = Binder.clearCallingIdentity();
                try {
                    if (MmsService.this.getContentResolver().delete((Uri)string, null, null) != 1) {
                        Log.e((String)"MmsService", (String)"deleteStoredConversation: failed to delete");
                        return false;
                    }
                    do {
                        return true;
                        break;
                    } while (true);
                }
                catch (SQLiteException var1_2) {
                    Log.e((String)"MmsService", (String)"deleteStoredConversation: failed to delete", (Throwable)var1_2);
                    return true;
                }
                finally {
                    Binder.restoreCallingIdentity((long)l);
                    return true;
                }
            }

            /*
             * Enabled force condition propagation
             * Lifted jumps to return sites
             */
            public boolean deleteStoredMessage(String string, Uri uri) throws RemoteException {
                Log.d((String)"MmsService", (String)("deleteStoredMessage " + (Object)uri));
                MmsService.this.enforceSystemUid();
                if (!MmsService.isSmsMmsContentUri(uri)) {
                    Log.e((String)"MmsService", (String)("deleteStoredMessage: invalid message URI: " + uri.toString()));
                    return false;
                }
                long l = Binder.clearCallingIdentity();
                try {
                    if (MmsService.this.getContentResolver().delete(uri, null, null) != 1) {
                        Log.e((String)"MmsService", (String)"deleteStoredMessage: failed to delete");
                        return false;
                    }
                    do {
                        return true;
                        break;
                    } while (true);
                }
                catch (SQLiteException var1_2) {
                    Log.e((String)"MmsService", (String)"deleteStoredMessage: failed to delete", (Throwable)var1_2);
                    return true;
                }
                finally {
                    Binder.restoreCallingIdentity((long)l);
                    return true;
                }
            }

            public void downloadMessage(int n, String object, String string, Uri uri, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Log.d((String)"MmsService", (String)("downloadMessage: " + string + ", subId = " + n + ", contentUri = " + (Object)uri));
                MmsService.this.enforceSystemUid();
                n = MmsService.this.checkSubId(n);
                object = new DownloadRequest(MmsService.this, n, string, uri, pendingIntent, (String)object, bundle);
                string = MmsService.this.getCarrierMessagingServicePackageIfExists();
                if (string != null) {
                    Log.d((String)"MmsService", (String)"downloading message by carrier app");
                    object.tryDownloadingByCarrierApp((Context)MmsService.this, string);
                    return;
                }
                MmsService.this.addSimRequest((MmsRequest)object);
            }

            public boolean getAutoPersisting() throws RemoteException {
                Log.d((String)"MmsService", (String)"getAutoPersisting");
                return MmsService.this.getAutoPersistingPref();
            }

            public Bundle getCarrierConfigValues(int n) {
                Log.d((String)"MmsService", (String)"getCarrierConfigValues");
                n = MmsService.this.checkSubId(n);
                MmsConfig mmsConfig = MmsConfigManager.getInstance().getMmsConfigBySubId(n);
                if (mmsConfig == null) {
                    return new Bundle();
                }
                return mmsConfig.getCarrierConfigValues();
            }

            public Uri importMultimediaMessage(String string, Uri uri, String string2, long l, boolean bl, boolean bl2) {
                Log.d((String)"MmsService", (String)"importMultimediaMessage");
                MmsService.this.enforceSystemUid();
                return MmsService.this.importMms(uri, string2, l, bl, bl2, string);
            }

            public Uri importTextMessage(String string, String string2, int n, String string3, long l, boolean bl, boolean bl2) {
                Log.d((String)"MmsService", (String)"importTextMessage");
                MmsService.this.enforceSystemUid();
                return MmsService.this.importSms(string2, n, string3, l, bl, bl2, string);
            }

            public void sendMessage(int n, String string, Uri object, String string2, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Log.d((String)"MmsService", (String)"sendMessage");
                MmsService.this.enforceSystemUid();
                n = MmsService.this.checkSubId(n);
                object = new SendRequest((MmsRequest.RequestManager)MmsService.this, n, (Uri)object, null, string2, pendingIntent, string, bundle);
                if (SmsApplication.shouldWriteMessageForPackage((String)string, (Context)MmsService.this)) {
                    object.storeInOutbox((Context)MmsService.this);
                }
                if ((string = MmsService.this.getCarrierMessagingServicePackageIfExists()) != null) {
                    Log.d((String)"MmsService", (String)"sending message by carrier app");
                    object.trySendingByCarrierApp((Context)MmsService.this, string);
                    return;
                }
                MmsService.this.addSimRequest((MmsRequest)object);
            }

            public void sendStoredMessage(int n, String object, Uri object2, Bundle bundle, PendingIntent pendingIntent) throws RemoteException {
                Log.d((String)"MmsService", (String)("sendStoredMessage: messageUri = " + object2 + ", subId = " + n));
                MmsService.this.enforceSystemUid();
                n = MmsService.this.checkSubId(n);
                if (!MmsService.this.isFailedOrDraft((Uri)object2)) {
                    Log.e((String)"MmsService", (String)"sendStoredMessage: not FAILED or DRAFT message");
                    MmsService.this.returnUnspecifiedFailure(pendingIntent);
                    return;
                }
                byte[] arrby = MmsService.this.loadPdu((Uri)object2);
                if (arrby == null || arrby.length < 1) {
                    Log.e((String)"MmsService", (String)"sendStoredMessage: failed to load PDU data");
                    MmsService.this.returnUnspecifiedFailure(pendingIntent);
                    return;
                }
                object = new SendRequest((MmsRequest.RequestManager)MmsService.this, n, arrby, (Uri)object2, null, pendingIntent, (String)object, bundle);
                object.storeInOutbox((Context)MmsService.this);
                object2 = MmsService.this.getCarrierMessagingServicePackageIfExists();
                if (object2 != null) {
                    Log.d((String)"MmsService", (String)"sending message by carrier app");
                    object.trySendingByCarrierApp((Context)MmsService.this, (String)object2);
                    return;
                }
                MmsService.this.addSimRequest((MmsRequest)object);
            }

            public void setAutoPersisting(String string, boolean bl) throws RemoteException {
                Log.d((String)"MmsService", (String)("setAutoPersisting " + bl));
                MmsService.this.enforceSystemUid();
                string = MmsService.this.getSharedPreferences("mmspref", 0).edit();
                string.putBoolean("autopersisting", bl);
                string.apply();
            }

            public boolean updateStoredMessageStatus(String string, Uri uri, ContentValues contentValues) throws RemoteException {
                Log.d((String)"MmsService", (String)("updateStoredMessageStatus " + (Object)uri));
                MmsService.this.enforceSystemUid();
                return MmsService.this.updateMessageStatus(uri, contentValues);
            }
        };
        this.mRunningRequestQueues = new RequestQueue[2];
        this.mPendingSimRequestQueues = new SparseArray();
        this.mRunningRequestQueuesMap = new SparseArray();
    }

    static /* synthetic */ int access$110(MmsService mmsService) {
        int n = mmsService.mRunningRequestCount;
        mmsService.mRunningRequestCount = n - 1;
        return n;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private Uri addMmsDraft(Uri object, String string) {
        if ((object = (Object)this.readPduFromContentUri((Uri)object, 8388608)) == null || object.length < 1) {
            Log.e((String)"MmsService", (String)"addMmsDraft: empty PDU");
            return null;
        }
        long l = Binder.clearCallingIdentity();
        try {
            object = MmsService.parsePduForAnyCarrier((byte[])object);
            if (object == null) {
                Log.e((String)"MmsService", (String)"addMmsDraft: can't parse input PDU");
                return null;
            }
            if (!(object instanceof MzSendReq)) {
                Log.e((String)"MmsService", (String)("addMmsDraft; invalid MMS type: " + object.getClass().getCanonicalName()));
                return null;
            }
            object = MzPduPersister.getPduPersister((Context)this).persist((MzGenericPdu)object, Telephony.Mms.Draft.CONTENT_URI, true, true, null);
            if (object == null) {
                Log.e((String)"MmsService", (String)"addMmsDraft: failed to persist message");
                return null;
            }
            ContentValues contentValues = new ContentValues(3);
            contentValues.put("read", Integer.valueOf((int)1));
            contentValues.put("seen", Integer.valueOf((int)1));
            if (!TextUtils.isEmpty((CharSequence)string)) {
                contentValues.put("creator", string);
            }
            if (MzSqliteWrapper.update((Context)this, (ContentResolver)this.getContentResolver(), (Uri)object, (ContentValues)contentValues, (String)null, (String[])null) == 1) return object;
            Log.e((String)"MmsService", (String)"addMmsDraft: failed to update message");
            return object;
        }
        catch (RuntimeException var1_2) {
            Log.e((String)"MmsService", (String)"addMmsDraft: failed to parse input PDU", (Throwable)var1_2);
            do {
                return null;
                break;
            } while (true);
        }
        catch (MmsException var1_3) {
            Log.e((String)"MmsService", (String)"addMmsDraft: failed to persist message", (Throwable)var1_3);
            return null;
        }
        finally {
            Binder.restoreCallingIdentity((long)l);
            return null;
        }
    }

    private Uri addSmsDraft(String string, String string2, String string3) {
        ContentValues contentValues = new ContentValues(5);
        contentValues.put("address", string);
        contentValues.put("body", string2);
        contentValues.put("read", Integer.valueOf((int)1));
        contentValues.put("seen", Integer.valueOf((int)1));
        if (!TextUtils.isEmpty((CharSequence)string3)) {
            contentValues.put("creator", string3);
        }
        long l = Binder.clearCallingIdentity();
        try {
            string = this.getContentResolver().insert(Telephony.Sms.Draft.CONTENT_URI, contentValues);
            return string;
        }
        catch (SQLiteException var1_2) {
            Log.e((String)"MmsService", (String)"addSmsDraft: failed to store draft message", (Throwable)var1_2);
            return null;
        }
        finally {
            Binder.restoreCallingIdentity((long)l);
        }
    }

    private void addToRunningRequestQueueSynchronized(MmsRequest mmsRequest) {
        Log.d((String)"MmsService", (String)("Add request to running queue for subId " + mmsRequest.getSubId()));
        this.mCurrentSubId = mmsRequest.getSubId();
        ++this.mRunningRequestCount;
        RequestQueue requestQueue = this.getRequestQueue(this.mCurrentSubId);
        if (requestQueue == null) {
            return;
        }
        Message message = Message.obtain();
        message.obj = mmsRequest;
        requestQueue.sendMessage(message);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean archiveConversation(long l, boolean bl) {
        ContentValues contentValues = new ContentValues(1);
        int n = bl ? 1 : 0;
        contentValues.put("archived", Integer.valueOf((int)n));
        long l2 = Binder.clearCallingIdentity();
        try {
            if (this.getContentResolver().update(Telephony.Threads.CONTENT_URI, contentValues, "_id=?", new String[]{Long.toString((long)l)}) != 1) {
                Log.e((String)"MmsService", (String)"archiveConversation: failed to update database");
                return false;
            }
            return true;
        }
        catch (SQLiteException var7_4) {
            Log.e((String)"MmsService", (String)"archiveConversation: failed to update database", (Throwable)var7_4);
            return false;
        }
        finally {
            Binder.restoreCallingIdentity((long)l2);
        }
    }

    private int checkSubId(int n) {
        if (!SubscriptionManager.isValidSubscriptionId((int)n)) {
            throw new RuntimeException("Invalid subId " + n);
        }
        int n2 = n;
        if (n == Integer.MAX_VALUE) {
            n2 = SubscriptionManager.getDefaultSmsSubId();
        }
        return n2;
    }

    private void enforceSystemUid() {
        if (Binder.getCallingUid() != 1000) {
            throw new SecurityException("Only system can call this service");
        }
    }

    private String getCarrierMessagingServicePackageIfExists() {
        Object object = new Intent("android.service.carrier.CarrierMessagingService");
        object = ((TelephonyManager)this.getSystemService("phone")).getCarrierPackageNamesForIntent((Intent)object);
        if (object == null || object.size() != 1) {
            return null;
        }
        return (String)object.get(0);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private MmsNetworkManager getNetworkManager(int n) {
        SparseArray<MmsNetworkManager> sparseArray = this.mNetworkManagerCache;
        synchronized (sparseArray) {
            MmsNetworkManager mmsNetworkManager;
            MmsNetworkManager mmsNetworkManager2 = mmsNetworkManager = (MmsNetworkManager)this.mNetworkManagerCache.get(n);
            if (mmsNetworkManager == null) {
                mmsNetworkManager2 = new MmsNetworkManager((Context)this, n);
                this.mNetworkManagerCache.put(n, (Object)mmsNetworkManager2);
            }
            return mmsNetworkManager2;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private Queue<MmsRequest> getPendingSimRequestQueue(int n) {
        if (!SubscriptionManager.isValidSubscriptionId((int)n)) {
            return null;
        }
        SparseArray<Queue<MmsRequest>> sparseArray = this.mPendingSimRequestQueues;
        synchronized (sparseArray) {
            Queue queue;
            Queue queue2 = queue = (Queue)this.mPendingSimRequestQueues.get(n);
            if (queue == null) {
                queue2 = new ArrayDeque();
                this.mPendingSimRequestQueues.put(n, (Object)queue2);
            }
            return queue2;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private RequestQueue getRequestQueue(int n) {
        if (!SubscriptionManager.isValidSubscriptionId((int)n)) {
            return null;
        }
        SparseArray<RequestQueue> sparseArray = this.mRunningRequestQueuesMap;
        synchronized (sparseArray) {
            RequestQueue requestQueue;
            RequestQueue requestQueue2 = requestQueue = (RequestQueue)((Object)this.mRunningRequestQueuesMap.get(n));
            if (requestQueue == null) {
                requestQueue2 = new HandlerThread("MmsService RequestQueueEx " + n);
                requestQueue2.start();
                requestQueue2 = new RequestQueue(requestQueue2.getLooper());
                this.mRunningRequestQueuesMap.put(n, (Object)requestQueue2);
            }
            return requestQueue2;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private Uri importMms(Uri object, String string, long l, boolean bl, boolean bl2, String string2) {
        MzGenericPdu mzGenericPdu;
        if ((object = (Object)this.readPduFromContentUri((Uri)object, 8388608)) == null || object.length < 1) {
            Log.e((String)"MmsService", (String)"importMessage: empty PDU");
            return null;
        }
        long l2 = Binder.clearCallingIdentity();
        try {
            mzGenericPdu = MmsService.parsePduForAnyCarrier((byte[])object);
            if (mzGenericPdu == null) {
                Log.e((String)"MmsService", (String)"importMessage: can't parse input PDU");
                return null;
            }
            object = null;
            if (mzGenericPdu instanceof MzSendReq) {
                object = Telephony.Mms.Sent.CONTENT_URI;
            } else if (mzGenericPdu instanceof MzRetrieveConf || mzGenericPdu instanceof MzNotificationInd || mzGenericPdu instanceof MzDeliveryInd || mzGenericPdu instanceof MzReadOrigInd) {
                object = Telephony.Mms.Inbox.CONTENT_URI;
            }
            if (object == null) {
                Log.e((String)"MmsService", (String)("importMessage; invalid MMS type: " + mzGenericPdu.getClass().getCanonicalName()));
                return null;
            }
            object = MzPduPersister.getPduPersister((Context)this).persist(mzGenericPdu, (Uri)object, true, true, null);
            if (object == null) {
                Log.e((String)"MmsService", (String)"importMessage: failed to persist message");
                return null;
            }
            mzGenericPdu = new ContentValues(5);
            if (!TextUtils.isEmpty((CharSequence)string)) {
                mzGenericPdu.put("m_id", string);
            }
            if (l != -1) {
                mzGenericPdu.put("date", Long.valueOf((long)l));
            }
        }
        catch (RuntimeException var1_2) {
            Log.e((String)"MmsService", (String)"importMessage: failed to parse input PDU", (Throwable)var1_2);
            return null;
        }
        catch (MmsException var1_3) {
            Log.e((String)"MmsService", (String)"importMessage: failed to persist message", (Throwable)var1_3);
            return null;
        }
        int n = bl ? 1 : 0;
        mzGenericPdu.put("read", Integer.valueOf((int)n));
        n = bl2 ? 1 : 0;
        mzGenericPdu.put("seen", Integer.valueOf((int)n));
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            mzGenericPdu.put("creator", string2);
        }
        if (MzSqliteWrapper.update((Context)this, (ContentResolver)this.getContentResolver(), (Uri)object, (ContentValues)mzGenericPdu, (String)null, (String[])null) != 1) {
            Log.e((String)"MmsService", (String)"importMessage: failed to update message");
        }
        Binder.restoreCallingIdentity((long)l2);
        return object;
        finally {
            Binder.restoreCallingIdentity((long)l2);
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private Uri importSms(String string, int n, String string2, long l, boolean bl, boolean bl2, String string3) {
        Uri uri = null;
        switch (n) {
            case 0: {
                uri = Telephony.Sms.Inbox.CONTENT_URI;
                break;
            }
            case 1: {
                uri = Telephony.Sms.Sent.CONTENT_URI;
            }
        }
        if (uri == null) {
            Log.e((String)"MmsService", (String)("importTextMessage: invalid message type for importing: " + n));
            return null;
        }
        ContentValues contentValues = new ContentValues(6);
        contentValues.put("address", string);
        contentValues.put("date", Long.valueOf((long)l));
        n = bl ? 1 : 0;
        contentValues.put("seen", Integer.valueOf((int)n));
        n = bl2 ? 1 : 0;
        contentValues.put("read", Integer.valueOf((int)n));
        contentValues.put("body", string2);
        if (!TextUtils.isEmpty((CharSequence)string3)) {
            contentValues.put("creator", string3);
        }
        l = Binder.clearCallingIdentity();
        try {
            string = this.getContentResolver().insert(uri, contentValues);
            return string;
        }
        catch (SQLiteException var1_2) {
            Log.e((String)"MmsService", (String)"importTextMessage: failed to persist imported text message", (Throwable)var1_2);
            return null;
        }
        finally {
            Binder.restoreCallingIdentity((long)l);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean isFailedOrDraft(Uri uri) {
        long l;
        block7 : {
            boolean bl;
            l = Binder.clearCallingIdentity();
            Uri uri2 = null;
            Uri uri3 = null;
            try {
                uri = this.getContentResolver().query(uri, new String[]{"msg_box"}, null, null, null);
                if (uri == null) break block7;
                uri3 = uri;
                uri2 = uri;
                if (!uri.moveToFirst()) break block7;
                uri3 = uri;
                uri2 = uri;
                int n = uri.getInt(0);
                bl = n == 3 || n == 5 || n == 4;
            }
            catch (SQLiteException var1_2) {
                block8 : {
                    uri2 = uri3;
                    try {
                        Log.e((String)"MmsService", (String)"isFailedOrDraft: query message type failed", (Throwable)var1_2);
                        if (uri3 == null) break block8;
                    }
                    catch (Throwable var1_3) {
                        if (uri2 != null) {
                            uri2.close();
                        }
                        Binder.restoreCallingIdentity((long)l);
                        throw var1_3;
                    }
                    uri3.close();
                }
                Binder.restoreCallingIdentity((long)l);
                return false;
            }
            if (uri != null) {
                uri.close();
            }
            Binder.restoreCallingIdentity((long)l);
            return bl;
        }
        if (uri != null) {
            uri.close();
        }
        Binder.restoreCallingIdentity((long)l);
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static boolean isSmsMmsContentUri(Uri uri) {
        String string = uri.toString();
        if (!string.startsWith("content://sms/") && !string.startsWith("content://mms/") || ContentUris.parseId((Uri)uri) == -1) {
            return false;
        }
        return true;
    }

    private byte[] loadPdu(Uri uri) {
        long l = Binder.clearCallingIdentity();
        try {
            Object object = MzPduPersister.getPduPersister((Context)this).load(uri);
            if (object == null) {
                Log.e((String)"MmsService", (String)("loadPdu: failed to load PDU from " + uri.toString()));
                return null;
            }
            object = new MzPduComposer((Context)this, (MzGenericPdu)object).make();
            return object;
        }
        catch (MmsException var4_6) {
            Log.e((String)"MmsService", (String)("loadPdu: failed to load PDU from " + uri.toString()), (Throwable)var4_6);
            return null;
        }
        catch (RuntimeException var1_2) {
            Log.e((String)"MmsService", (String)"loadPdu: failed to serialize PDU", (Throwable)var1_2);
            return null;
        }
        finally {
            Binder.restoreCallingIdentity((long)l);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void movePendingSimRequestsToRunningSynchronized() {
        Log.d((String)"MmsService", (String)"Schedule requests pending on SIM");
        Queue queue = this.getPendingSimRequestQueue(this.mCurrentSubId);
        if (queue != null && queue.size() > 0) {
            while (queue.size() > 0) {
                MmsRequest mmsRequest = queue.peek();
                if (mmsRequest != null) {
                    queue.remove();
                    this.addToRunningRequestQueueSynchronized(mmsRequest);
                    return;
                }
                Log.e((String)"MmsService", (String)"Schedule pending: found empty request");
                queue.remove();
            }
            return;
        }
        int n = 0;
        while (n < this.mPendingSimRequestQueues.size()) {
            queue = (Queue)this.mPendingSimRequestQueues.valueAt(n);
            if (queue != null && queue.size() > 0) {
                while (queue.size() > 0) {
                    MmsRequest mmsRequest = (MmsRequest)queue.peek();
                    if (mmsRequest != null) {
                        queue.remove();
                        this.addToRunningRequestQueueSynchronized(mmsRequest);
                        return;
                    }
                    Log.e((String)"MmsService", (String)"Schedule pending: found empty request");
                    queue.remove();
                }
            }
            ++n;
        }
    }

    private static MzGenericPdu parsePduForAnyCarrier(byte[] object) {
        try {
            object = (Object)new MzPduParser((byte[])object).parse();
            return object;
        }
        catch (RuntimeException var0_1) {
            Log.d((String)"MmsService", (String)"parsePduForAnyCarrier: Failed to parse PDU with content disposition", (Throwable)var0_1);
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void returnUnspecifiedFailure(PendingIntent pendingIntent) {
        if (pendingIntent == null) return;
        try {
            pendingIntent.send(1);
            return;
        }
        catch (PendingIntent.CanceledException var1_2) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean updateMessageStatus(Uri uri, ContentValues contentValues) {
        if (!MmsService.isSmsMmsContentUri(uri)) {
            Log.e((String)"MmsService", (String)("updateMessageStatus: invalid messageUri: " + uri.toString()));
            return false;
        }
        if (contentValues == null) {
            Log.w((String)"MmsService", (String)"updateMessageStatus: empty values to update");
            return false;
        }
        ContentValues contentValues2 = new ContentValues();
        if (contentValues.containsKey("read")) {
            if ((contentValues = contentValues.getAsInteger("read")) != null) {
                contentValues2.put("read", (Integer)contentValues);
            }
        } else if (contentValues.containsKey("seen") && (contentValues = contentValues.getAsInteger("seen")) != null) {
            contentValues2.put("seen", (Integer)contentValues);
        }
        if (contentValues2.size() < 1) {
            Log.w((String)"MmsService", (String)"updateMessageStatus: no value to update");
            return false;
        }
        long l = Binder.clearCallingIdentity();
        try {
            if (this.getContentResolver().update(uri, contentValues2, null, null) != 1) {
                Log.e((String)"MmsService", (String)"updateMessageStatus: failed to update database");
                return false;
            }
            return true;
        }
        catch (SQLiteException var1_2) {
            Log.e((String)"MmsService", (String)"updateMessageStatus: failed to update database", (Throwable)var1_2);
            return false;
        }
        finally {
            Binder.restoreCallingIdentity((long)l);
        }
    }

    private void updatePendingMmsRequestCountForSubId(int n) {
        Queue<MmsRequest> queue = this.getPendingSimRequestQueue(n);
        if (queue == null) {
            return;
        }
        this.getNetworkManager(n).updatePendingMmsRequestCount(queue.size());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    @Override
    public void addSimRequest(MmsRequest mmsRequest) {
        if (mmsRequest == null) {
            Log.e((String)"MmsService", (String)"Add running or pending: empty request");
            return;
        }
        // MONITORENTER : this
        Queue<MmsRequest> queue = this.getPendingSimRequestQueue(mmsRequest.getSubId());
        if (queue == null) {
            // MONITOREXIT : this
            return;
        }
        Log.d((String)"MmsService", (String)("Current running=" + this.mRunningRequestCount + ", " + "current subId=" + this.mCurrentSubId + ", " + "pending request count" + queue.size()));
        if (queue.size() > 0 || this.mRunningRequestCount > 0) {
            queue.add(mmsRequest);
            this.updatePendingMmsRequestCountForSubId(mmsRequest.getSubId());
            return;
        } else {
            if (queue.size() <= 0 && this.mRunningRequestCount <= 0 && mmsRequest.getSubId() == this.mCurrentSubId) {
                this.addToRunningRequestQueueSynchronized(mmsRequest);
                return;
            }
            queue.add(mmsRequest);
            this.movePendingSimRequestsToRunningSynchronized();
            for (int i = 0; i < this.mPendingSimRequestQueues.size(); ++i) {
                this.updatePendingMmsRequestCountForSubId(this.mPendingSimRequestQueues.keyAt(i));
            }
        }
        // MONITOREXIT : this
    }

    @Override
    public boolean getAutoPersistingPref() {
        return this.getSharedPreferences("mmspref", 0).getBoolean("autopersisting", false);
    }

    public IBinder onBind(Intent intent) {
        return this.mStub;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void onCreate() {
        super.onCreate();
        Log.d((String)"MmsService", (String)"onCreate");
        MmsConfigManager.getInstance().init((Context)this);
        synchronized (this) {
            this.mCurrentSubId = -1;
            this.mRunningRequestCount = 0;
            return;
        }
    }

    @Override
    public byte[] readPduFromContentUri(Uri future, int n) {
        Log.d((String)"MmsService", (String)("readPduFromContentUri(), uri = " + future + ", maxSize = " + n));
        future = new Callable<byte[]>((Uri)future, n){
            final /* synthetic */ Uri val$contentUri;
            final /* synthetic */ int val$maxSize;

            /*
             * Unable to fully structure code
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Lifted jumps to return sites
             */
            @Override
            public byte[] call() {
                block20 : {
                    block19 : {
                        var2_1 /* !! */  = null;
                        var4_7 = null;
                        var3_10 = new ParcelFileDescriptor.AutoCloseInputStream(MmsService.this.getContentResolver().openFileDescriptor(this.val$contentUri, "r"));
                        var2_1 /* !! */  = (IOException)new byte[this.val$maxSize + 1];
                        var1_13 = var3_10.read((byte[])var2_1 /* !! */ , 0, this.val$maxSize + 1);
                        if (var1_13 != 0) break block19;
                        Log.e((String)"MmsService", (String)"MmsService.readPduFromContentUri: empty PDU");
                        if (var3_10 == null) return null;
                        var3_10.close();
                        return null;
                        {
                            catch (IOException var2_4) {
                                return null;
                            }
                        }
                    }
                    if (var1_13 > this.val$maxSize) break block20;
                    var2_1 /* !! */  = (IOException)Arrays.copyOf((byte[])var2_1 /* !! */ , (int)var1_13);
                    if (var3_10 == null) return var2_1 /* !! */ ;
                    var3_10.close();
                    return var2_1 /* !! */ ;
                    {
                        catch (IOException var3_12) {
                            return var2_1 /* !! */ ;
                        }
                    }
                }
                Log.e((String)"MmsService", (String)"MmsService.readPduFromContentUri: PDU too large");
                if (var3_10 == null) return null;
                var3_10.close();
                return null;
                {
                    catch (IOException var2_5) {
                        return null;
                    }
                }
                catch (IOException var2_2) {
                    var3_10 = var4_7;
                    var4_7 = var2_2;
                    ** GOTO lbl44
                    catch (Throwable var4_8) {
                        var2_1 /* !! */  = var3_10;
                        var3_10 = var4_8;
                        ** GOTO lbl-1000
                    }
                    catch (IOException var4_9) {}
lbl44: // 2 sources:
                    var2_1 /* !! */  = var3_10;
                    try {
                        Log.e((String)"MmsService", (String)"MmsService.readPduFromContentUri: IO exception reading PDU", (Throwable)var4_7);
                        if (var3_10 == null) return null;
                    }
                    catch (Throwable var3_11) lbl-1000: // 2 sources:
                    {
                        if (var2_1 /* !! */  == null) throw var3_10;
                        try {
                            var2_1 /* !! */ .close();
                        }
                        catch (IOException var2_6) {
                            throw var3_10;
                        }
                        throw var3_10;
                    }
                    try {
                        var3_10.close();
                        return null;
                    }
                    catch (IOException var2_3) {
                        return null;
                    }
                }
            }
        };
        future = this.mExecutor.submit(future);
        try {
            byte[] arrby = (byte[])future.get(30000, TimeUnit.MILLISECONDS);
            return arrby;
        }
        catch (Exception var3_4) {
            future.cancel(true);
            return null;
        }
    }

    @Override
    public boolean writePduToContentUri(Uri future, byte[] object) {
        future = new Callable<Boolean>((Uri)future, (byte[])object){
            final /* synthetic */ Uri val$contentUri;
            final /* synthetic */ byte[] val$pdu;

            /*
             * Unable to fully structure code
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Lifted jumps to return sites
             */
            @Override
            public Boolean call() {
                var1_1 = null;
                var3_6 = null;
                var2_8 = new ParcelFileDescriptor.AutoCloseOutputStream(MmsService.this.getContentResolver().openFileDescriptor(this.val$contentUri, "w"));
                var2_8.write(this.val$pdu);
                var1_1 = Boolean.TRUE;
                if (var2_8 == null) return var1_1;
                var2_8.close();
                return var1_1;
                {
                    catch (IOException var2_10) {
                        return var1_1;
                    }
                }
                catch (IOException var1_2) {
                    var2_8 = var3_6;
                    ** GOTO lbl22
                    catch (Throwable var3_7) {
                        var1_1 = var2_8;
                        var2_8 = var3_7;
                        ** GOTO lbl-1000
                    }
                    catch (IOException var1_5) {}
lbl22: // 2 sources:
                    var1_1 = var2_8;
                    try {
                        var1_1 = var3_6 = Boolean.FALSE;
                        if (var2_8 == null) return var1_1;
                    }
                    catch (Throwable var2_9) lbl-1000: // 2 sources:
                    {
                        if (var1_1 == null) throw var2_8;
                        try {
                            var1_1.close();
                        }
                        catch (IOException var1_4) {
                            throw var2_8;
                        }
                        throw var2_8;
                    }
                    try {
                        var2_8.close();
                        return var3_6;
                    }
                    catch (IOException var1_3) {
                        return var3_6;
                    }
                }
            }
        };
        future = this.mExecutor.submit(future);
        try {
            object = (Object)((Boolean)future.get(30000, TimeUnit.MILLISECONDS));
            Boolean bl = Boolean.TRUE;
            if (object == bl) {
                return true;
            }
            return false;
        }
        catch (Exception var2_3) {
            future.cancel(true);
            return false;
        }
    }

    private class RequestQueue
    extends Handler {
        public RequestQueue(Looper looper) {
            super(looper);
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Converted monitor instructions to comments
         * Lifted jumps to return sites
         */
        public void handleMessage(Message object) {
            object = (MmsRequest)object.obj;
            if (object == null) {
                Log.e((String)"MmsService", (String)"RequestQueue: handling empty request");
                return;
            }
            try {
                object.execute((Context)MmsService.this, MmsService.this.getNetworkManager(object.getSubId()));
                return;
            }
            finally {
                object = MmsService.this;
                // MONITORENTER : object
                MmsService.access$110(MmsService.this);
                if (MmsService.this.mRunningRequestCount <= 0) {
                    MmsService.this.movePendingSimRequestsToRunningSynchronized();
                    for (int i = 0; i < MmsService.this.mPendingSimRequestQueues.size(); ++i) {
                        int n = MmsService.this.mPendingSimRequestQueues.keyAt(i);
                        MmsService.this.updatePendingMmsRequestCountForSubId(n);
                    }
                }
                // MONITOREXIT : object
            }
        }
    }

}

