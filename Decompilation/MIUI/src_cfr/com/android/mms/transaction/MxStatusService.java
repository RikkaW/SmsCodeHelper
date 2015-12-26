/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Service
 *  android.content.Context
 *  android.content.Intent
 *  android.os.IBinder
 *  android.os.RemoteException
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashSet
 *  miui.mms.IMxStatusService
 *  miui.mms.IMxStatusService$Stub
 */
package com.android.mms.transaction;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.transaction.MxTaskService;
import com.xiaomi.mms.utils.logger.MyLog;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import miui.mms.IMxStatusService;

public class MxStatusService
extends Service {
    private static HashSet<String> sPendingQueries = new HashSet();
    private volatile int mBinderCount = 0;
    private MxStatuQueryListener mMxStatuQueryListener;
    private MxStatusServiceImpl mService;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void broadcastStatusToReceiver(String string) {
        boolean bl = false;
        HashSet<String> hashSet = MxIdCache.get(string, false);
        if (hashSet == null) {
            MyLog.d("MxStatusService", "broadcastStatusToReceiver(String address) -> requeryStatus(this, address)");
            MxTaskService.queryStatus((Context)this, string);
            return;
        }
        Intent intent = new Intent("com.android.mms.QUERY_MX_STATUS_RESULT");
        if (hashSet.allowSms() || hashSet.allowMms()) {
            bl = true;
        }
        intent.putExtra("online", bl);
        intent.putExtra("address", string);
        this.sendBroadcast(intent, "com.xiaomi.permission.QUERY_MX_STAUTS");
        hashSet = sPendingQueries;
        synchronized (hashSet) {
            sPendingQueries.remove((Object)string);
        }
        MyLog.d("MxStatusService", "broadcast mx status update");
    }

    private int decreaseReference() {
        synchronized (this) {
            int n;
            this.mBinderCount = n = this.mBinderCount - 1;
            return n;
        }
    }

    private int increaseReference() {
        synchronized (this) {
            int n;
            this.mBinderCount = n = this.mBinderCount + 1;
            return n;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void queryPendingPresence(Context context) {
        MyLog.d("MxStatusService", "queryPendingPresence(Context context)");
        ArrayList arrayList = new ArrayList();
        Object object = sPendingQueries;
        synchronized (object) {
            Iterator iterator = sPendingQueries.iterator();
            while (iterator.hasNext()) {
                arrayList.add((Object)((String)iterator.next()));
            }
        }
        object = arrayList.iterator();
        while (object.hasNext()) {
            MxTaskService.queryStatus(context, (String)object.next());
        }
        return;
    }

    public IBinder onBind(Intent intent) {
        if (this.mService == null) {
            this.mService = new MxStatusServiceImpl((Context)this);
        }
        this.increaseReference();
        MyLog.d("MxStatusService", "MxStatusService is on bind");
        return this.mService;
    }

    public void onCreate() {
        super.onCreate();
        this.mMxStatuQueryListener = new MxStatuQueryListener();
        MxIdCache.addStatusListener(this.mMxStatuQueryListener);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public void onDestroy() {
        MyLog.d("MxStatusService", "MxStatusService is on destroy");
        super.onDestroy();
        HashSet<String> hashSet = sPendingQueries;
        // MONITORENTER : hashSet
        sPendingQueries.clear();
        // MONITOREXIT : hashSet
        if (this.mMxStatuQueryListener != null) {
            MxIdCache.removeStatusListener(this.mMxStatuQueryListener);
        }
        this.mMxStatuQueryListener = null;
    }

    public boolean onUnbind(Intent intent) {
        this.decreaseReference();
        MyLog.d("MxStatusService", "MxStatusService is on Unbind");
        super.onUnbind(intent);
        this.mService = null;
        return true;
    }

    private class MxStatuQueryListener
    implements MxIdCache.MxCacheStatusListener {
        private MxStatuQueryListener() {
        }

        @Override
        public void onMxIdAdded(String string, String string2) {
            if (MxStatusService.this.mBinderCount > 0) {
                MyLog.d("MxStatusService", "onMxIdAdded");
                MxStatusService.this.broadcastStatusToReceiver(string2);
            }
        }

        @Override
        public void onMxIdOffline(String string, String string2) {
            if (MxStatusService.this.mBinderCount > 0) {
                MyLog.d("MxStatusService", "onMxIdOffline");
                MxStatusService.this.broadcastStatusToReceiver(string2);
            }
        }

        @Override
        public void onMxIdOnline(String string, String string2) {
            if (MxStatusService.this.mBinderCount > 0) {
                MyLog.d("MxStatusService", "onMxIdOnline");
                MxStatusService.this.broadcastStatusToReceiver(string2);
            }
        }
    }

    private static class MxStatusServiceImpl
    extends IMxStatusService.Stub {
        private Context mContext;

        public MxStatusServiceImpl(Context context) {
            this.mContext = context;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public boolean isMxOnline(String string) throws RemoteException {
            boolean bl = false;
            MxIdCache.MxIdCacheItem mxIdCacheItem = MxIdCache.get(string, false);
            MyLog.d("MxStatusService", "query mx status by remote service");
            if (mxIdCacheItem == null) {
                MyLog.d("MxStatusService", "cache missed, query the status");
                mxIdCacheItem = sPendingQueries;
                synchronized (mxIdCacheItem) {
                    sPendingQueries.add((Object)string);
                }
                MxTaskService.queryStatus(this.mContext, string);
                return false;
            }
            if (mxIdCacheItem.allowMms()) return true;
            if (!mxIdCacheItem.allowSms()) return bl;
            return true;
        }
    }

}

