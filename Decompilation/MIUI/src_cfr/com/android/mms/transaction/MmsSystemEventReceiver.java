/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.ComponentName
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.database.ContentObserver
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.net.Uri
 *  android.os.Handler
 *  android.os.Parcelable
 *  android.provider.Settings
 *  android.provider.Settings$Global
 *  android.provider.Settings$System
 *  android.telephony.PhoneStateListener
 *  android.telephony.ServiceState
 *  com.google.android.mms.util.PduCache
 *  com.google.android.mms.util.PduCacheEntry
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashSet
 *  miui.telephony.TelephonyManager
 */
package com.android.mms.transaction;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import com.android.mms.LogTag;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.transaction.TransactionService;
import com.android.mms.util.MSimUtils;
import com.google.android.mms.util.PduCache;
import com.google.android.mms.util.PduCacheEntry;
import com.xiaomi.mms.transaction.MxActivateService;
import java.util.HashSet;
import miui.telephony.TelephonyManager;

public class MmsSystemEventReceiver
extends BroadcastReceiver {
    private static MmsSystemEventReceiver sInstance;
    private Context mContext;
    private final Handler mHandler = new Handler();
    private volatile boolean mIsAvailable;
    private boolean[] mListening;
    private MSimPhoneStateListener mPhoneStateListener1;
    private MSimPhoneStateListener mPhoneStateListener2;
    private final HashSet<SimStateChangedListener> mSimStateChangedListeners = new HashSet();

    /*
     * Enabled aggressive block sorting
     */
    private MmsSystemEventReceiver(Context context) {
        this.mContext = context;
        int n = MSimUtils.getMultiSimCount();
        LogTag.verbose("MmsSystemEventReceiver count is " + n, new Object[0]);
        this.mListening = new boolean[n];
        for (int i = 0; i < n; ++i) {
            this.mListening[i] = false;
        }
        Object object = new IntentFilter();
        object.addAction("android.intent.action.CONTENT_CHANGED");
        object.addAction("android.intent.action.BOOT_COMPLETED");
        object.addAction("com.xiaomi.action.MICLOUD_SIM_STATE_CHANGED");
        object.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        object.addAction("android.intent.action.SIM_STATE_CHANGED");
        context.registerReceiver((BroadcastReceiver)this, (IntentFilter)object);
        object = new ContentObserver(this.mHandler){

            public void onChange(boolean bl) {
                LogTag.verbose("mobile data or enable mms is changed", new Object[0]);
                for (int i = 0; i < MSimUtils.getMultiSimCount(); ++i) {
                    MmsSystemEventReceiver.this.updateMmsAvailability(i);
                }
            }
        };
        context = context.getContentResolver();
        if (MSimUtils.isAndroid50() && !MSimUtils.isMtk()) {
            context.registerContentObserver(Settings.Global.getUriFor((String)"mobile_data0"), false, (ContentObserver)object);
            context.registerContentObserver(Settings.Global.getUriFor((String)"mobile_data1"), false, (ContentObserver)object);
        } else {
            context.registerContentObserver(Settings.Global.getUriFor((String)"mobile_data"), false, (ContentObserver)object);
        }
        context.registerContentObserver(Settings.Global.getUriFor((String)"always_enable_mms"), false, (ContentObserver)object);
    }

    public static MmsSystemEventReceiver getInstance() {
        return sInstance;
    }

    private void handleDataChanged(Context context) {
        context = (ConnectivityManager)context.getSystemService("connectivity");
        NetworkInfo networkInfo = context.getNetworkInfo(2);
        int n = MSimUtils.getMultiSimCount();
        if (networkInfo != null && networkInfo.isAvailable()) {
            LogTag.verbose("mobile mms available", new Object[0]);
            this.setAvailable(true);
            for (int i = 0; i < n; ++i) {
                this.updateMmsAvailability(i);
            }
        } else if ((context = context.getNetworkInfo(0)) != null && context.isAvailable()) {
            LogTag.verbose("mobile available", new Object[0]);
            this.setAvailable(true);
            for (int i = 0; i < n; ++i) {
                this.updateMmsAvailability(i);
            }
        } else {
            LogTag.verbose("mobile or mobile mms is not available", new Object[0]);
            this.setAvailable(false);
        }
    }

    public static void init(Context context) {
        if (sInstance != null) {
            return;
        }
        sInstance = new MmsSystemEventReceiver(context.getApplicationContext());
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean isAlwaysEnableMmsAllowed() {
        boolean bl = Settings.System.getInt((ContentResolver)this.mContext.getContentResolver(), (String)"always_enable_mms", (int)1) == 1;
        LogTag.verbose("isAlwaysEnableMmsAllowed allowed is " + bl, new Object[0]);
        return bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean isMobileDataAllowed() {
        boolean bl;
        if (MSimUtils.isAndroid50() && !MSimUtils.isMtk()) {
            boolean bl2 = Settings.Global.getInt((ContentResolver)this.mContext.getContentResolver(), (String)"mobile_data0", (int)1) == 1;
            bl = bl2;
            if (!bl2) {
                bl = Settings.Global.getInt((ContentResolver)this.mContext.getContentResolver(), (String)"mobile_data1", (int)1) == 1;
            }
        } else {
            bl = Settings.Global.getInt((ContentResolver)this.mContext.getContentResolver(), (String)"mobile_data", (int)1) == 1;
        }
        LogTag.verbose("isMobileDataAllowed allowed is " + bl, new Object[0]);
        return bl;
    }

    private void setAvailable(boolean bl) {
        this.mIsAvailable = bl;
    }

    private void updateMmsAvailability(int n) {
        if (this.isListening(n) && this.isMmsAllowed() && this.isAvailable()) {
            LogTag.verbose("updateMmsAvailability slotId " + n, new Object[0]);
            this.wakeUpService(this.mContext, n);
        }
    }

    private void wakeUpService(Context context, int n) {
        LogTag.verbose("wakeUpService: start transaction service slotId " + n, new Object[0]);
        Intent intent = new Intent(this.mContext, (Class)TransactionService.class);
        intent.setAction("android.intent.action.ACTION_WAKEUP");
        intent.putExtra(MSimUtils.SLOT_ID, n);
        context.startService(intent);
    }

    public boolean isAvailable() {
        return this.mIsAvailable;
    }

    public boolean isListening() {
        boolean bl;
        boolean bl2 = bl = false;
        if (this.isListening(0)) {
            bl2 = bl;
            if (this.isListening(1)) {
                bl2 = true;
            }
        }
        LogTag.verbose("isListening allowed is " + bl2, new Object[0]);
        return bl2;
    }

    public boolean isListening(int n) {
        boolean bl = false;
        if (MSimUtils.isMSimSlotIdValid(n)) {
            bl = this.mListening[n];
        }
        LogTag.verbose("isListening slotId " + n + " allowed is " + bl, new Object[0]);
        return bl;
    }

    public boolean isMmsAllowed() {
        if (this.isMobileDataAllowed() || this.isAlwaysEnableMmsAllowed()) {
            return true;
        }
        return false;
    }

    public void listenForMmsAvailability(int n) {
        LogTag.verbose("listenForMmsAvailability slotId " + n, new Object[0]);
        if (MSimUtils.isMSimSlotIdValid(n)) {
            this.mListening[n] = true;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onReceive(Context object, Intent object2) {
        int n;
        Object object32 = object2.getAction();
        if ("android.intent.action.CONTENT_CHANGED".equals(object32)) {
            object = (Uri)object2.getParcelableExtra("deleted_contents");
            PduCache.getInstance().purge((Uri)object);
            return;
        }
        if ("android.intent.action.BOOT_COMPLETED".equals(object32)) {
            MessagingNotification.nonBlockingUpdateNewMessageIndicator((Context)object, false, false);
            object.startService(new Intent((Context)object, (Class)TransactionService.class));
            return;
        }
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(object32)) {
            this.handleDataChanged((Context)object);
            return;
        }
        if ("android.intent.action.SIM_STATE_CHANGED".equals(object32)) {
            object = object2.getStringExtra("ss");
            for (Object object32 : this.mSimStateChangedListeners) {
                if (object32 == null) continue;
                object32.onSimStateChanged((String)object);
            }
            return;
        }
        if (!"com.xiaomi.action.MICLOUD_SIM_STATE_CHANGED".equals(object32)) return;
        {
            boolean bl = object2.getBooleanExtra("extra_sim_inserted", false);
            n = object2.getIntExtra("extra_sim_index", -1);
            if (!bl) {
                MxActivateService.closeChannel(this.mContext, n);
                return;
            }
        }
        MxActivateService.enableMx(this.mContext, n, true, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void registerForServiceStateChanged(int n) {
        if (n == 0) {
            if (this.mPhoneStateListener1 == null) {
                this.mPhoneStateListener1 = new MSimPhoneStateListener(this.mContext, n, this.mHandler);
            }
            this.mPhoneStateListener1.register();
            return;
        } else {
            if (1 != n) return;
            {
                if (this.mPhoneStateListener2 == null) {
                    this.mPhoneStateListener2 = new MSimPhoneStateListener(this.mContext, n, this.mHandler);
                }
                this.mPhoneStateListener2.register();
                return;
            }
        }
    }

    public void registerSimStateChangedListener(SimStateChangedListener simStateChangedListener) {
        if (!this.mSimStateChangedListeners.contains((Object)simStateChangedListener)) {
            this.mSimStateChangedListeners.add((Object)simStateChangedListener);
        }
    }

    public void unRegisterSimStateChangedListener(SimStateChangedListener simStateChangedListener) {
        if (this.mSimStateChangedListeners.contains((Object)simStateChangedListener)) {
            this.mSimStateChangedListeners.remove((Object)simStateChangedListener);
        }
    }

    public void unlistenForMmsAvailability(int n) {
        if (MSimUtils.isMSimSlotIdValid(n) && this.mListening[n]) {
            LogTag.verbose("unlistenForMmsAvailability slotId " + n, new Object[0]);
            this.mListening[n] = false;
        }
    }

    private class MSimPhoneStateListener
    extends PhoneStateListener {
        private int DELAY_TIME;
        private Context mContext;
        private Runnable mDelaySend;
        private Handler mHandler;
        private boolean mRegisterd;
        private int mSlotId;

        public MSimPhoneStateListener(Context context, int n, Handler handler) {
            this.DELAY_TIME = 10000;
            this.mDelaySend = new Runnable(){

                @Override
                public void run() {
                    LogTag.verbose("send queued message without toast", new Object[0]);
                    MSimUtils.sendQueuedMessageNoToast(MSimPhoneStateListener.this.mContext, MSimPhoneStateListener.this.mSlotId);
                }
            };
            this.mContext = context;
            this.mSlotId = n;
            this.mHandler = handler;
            this.mRegisterd = false;
        }

        private void unRegister() {
            if (MSimUtils.isMSimSlotIdValid(this.mSlotId)) {
                TelephonyManager.getDefault().listenForSlot(this.mSlotId, (PhoneStateListener)this, 0);
                this.mHandler.removeCallbacks(this.mDelaySend);
            }
        }

        public void onServiceStateChanged(ServiceState serviceState) {
            if (serviceState.getState() == 0) {
                LogTag.verbose("on service state is in service", new Object[0]);
                if (this.mRegisterd) {
                    this.unRegister();
                    this.mHandler.postDelayed(this.mDelaySend, (long)this.DELAY_TIME);
                }
                this.mRegisterd = false;
            }
        }

        public void register() {
            if (MSimUtils.isMSimSlotIdValid(this.mSlotId) && !this.mRegisterd) {
                TelephonyManager.getDefault().listenForSlot(this.mSlotId, (PhoneStateListener)this, 1);
                this.mRegisterd = true;
            }
        }

    }

    public static interface SimStateChangedListener {
        public void onSimStateChanged(String var1);
    }

}

