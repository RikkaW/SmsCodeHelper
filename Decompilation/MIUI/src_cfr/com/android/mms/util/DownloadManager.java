/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$OnSharedPreferenceChangeListener
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.SystemProperties
 *  android.preference.PreferenceManager
 *  android.telephony.ServiceState
 *  android.text.TextUtils
 *  android.util.Log
 *  android.widget.Toast
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.EncodedStringValue
 *  com.google.android.mms.pdu.GenericPdu
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  com.google.android.mms.pdu.NotificationInd
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  miui.os.Build
 *  miui.telephony.TelephonyManagerEx
 */
package com.android.mms.util;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemProperties;
import android.preference.PreferenceManager;
import android.telephony.ServiceState;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.android.mms.data.Contact;
import com.android.mms.util.MSimUtils;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.NotificationInd;
import miui.os.Build;
import miui.telephony.TelephonyManagerEx;

public class DownloadManager {
    private static DownloadManager sInstance;
    private boolean[] mAutoDownload;
    private final Context mContext;
    private final Handler mHandler;
    private boolean mIsOutOfMemory = false;
    private final SharedPreferences mPreferences;
    private final SharedPreferences.OnSharedPreferenceChangeListener mPreferencesChangeListener;
    private final BroadcastReceiver mRoamingStateListener;
    private int[] mSimServiceState;

    private DownloadManager(Context context) {
        this.mPreferencesChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String string2) {
                int n = DownloadManager.this.mAutoDownload.length;
                int n2 = 0;
                while (n2 < n) {
                    long l = MSimUtils.getSimIdBySlotId(n2);
                    if (l >= 0) {
                        Object object = MSimUtils.createKeyWithSimId(l, "pref_key_mms_auto_retrieval");
                        String string3 = MSimUtils.createKeyWithSimId(l, "pref_key_mms_retrieval_during_roaming");
                        if (!TextUtils.isEmpty((CharSequence)object) && object.equals((Object)string2) || !TextUtils.isEmpty((CharSequence)string3) && string3.equals((Object)string2)) {
                            object = sInstance;
                            synchronized (object) {
                                DownloadManager.access$000((DownloadManager)DownloadManager.this)[n2] = DownloadManager.this.getAutoDownloadState(sharedPreferences, l);
                            }
                        }
                    }
                    ++n2;
                }
            }
        };
        this.mRoamingStateListener = new BroadcastReceiver(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public void onReceive(Context object, Intent intent) {
                if (!"android.intent.action.SERVICE_STATE".equals((Object)intent.getAction())) {
                    return;
                }
                ServiceState serviceState = ServiceState.newFromBundle((Bundle)intent.getExtras());
                boolean bl = serviceState.getRoaming();
                object = sInstance;
                synchronized (object) {
                    int n = MSimUtils.getSlotIdFromIntent(intent);
                    DownloadManager.access$300((DownloadManager)DownloadManager.this)[n] = serviceState.getState();
                    if (MSimUtils.isMSimSlotIdValid(n)) {
                        long l = MSimUtils.getSimIdBySlotId(n);
                        if (l < 0) {
                            Log.e((String)"DownloadManager", (String)("Download manager : cannot get sim id for slot " + n));
                            return;
                        }
                        DownloadManager.access$000((DownloadManager)DownloadManager.this)[n] = DownloadManager.this.getAutoDownloadState(DownloadManager.this.mPreferences, bl, l);
                    }
                    return;
                }
            }
        };
        this.mContext = context;
        this.mHandler = new Handler();
        int n = MSimUtils.getMultiSimCount();
        this.mAutoDownload = new boolean[n];
        this.mSimServiceState = new int[n];
        this.mPreferences = PreferenceManager.getDefaultSharedPreferences((Context)context);
        this.mPreferences.registerOnSharedPreferenceChangeListener(this.mPreferencesChangeListener);
        context.registerReceiver(this.mRoamingStateListener, new IntentFilter("android.intent.action.SERVICE_STATE"));
        for (int i = 0; i < n; ++i) {
            this.mSimServiceState[i] = 0;
            long l = MSimUtils.getSimIdBySlotId(i);
            if (l < 0) continue;
            this.mAutoDownload[i] = this.getAutoDownloadState(this.mPreferences, l);
        }
    }

    static /* synthetic */ int[] access$300(DownloadManager downloadManager) {
        return downloadManager.mSimServiceState;
    }

    private boolean getAutoDownloadState(SharedPreferences sharedPreferences, long l) {
        return this.getAutoDownloadState(sharedPreferences, this.isRoaming(l), l);
    }

    private boolean getAutoDownloadState(SharedPreferences sharedPreferences, boolean bl, long l) {
        if (sharedPreferences.getBoolean(MSimUtils.createKeyWithSimId(l, "pref_key_mms_auto_retrieval"), true)) {
            boolean bl2 = sharedPreferences.getBoolean(MSimUtils.createKeyWithSimId(l, "pref_key_mms_retrieval_during_roaming"), false);
            if (!bl || bl2) {
                return true;
            }
        }
        return false;
    }

    public static DownloadManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException("Uninitialized.");
        }
        return sInstance;
    }

    /*
     * Enabled aggressive block sorting
     */
    private String getMessage(Uri object) throws MmsException {
        Object object2 = (NotificationInd)MiuiPduPersister.getPduPersister((Context)this.mContext).load((Uri)object);
        object = object2.getSubject();
        object = object != null ? object.getString() : this.mContext.getString(2131362002);
        object2 = (object2 = object2.getFrom()) != null ? Contact.get(object2.getString()).load(true, true).getName() : this.mContext.getString(2131362003);
        return this.mContext.getString(2131362004, new Object[]{object, object2});
    }

    public static void init(Context context) {
        if (sInstance != null) {
            Log.w((String)"DownloadManager", (String)"Already initialized.");
        }
        sInstance = new DownloadManager(context.getApplicationContext());
    }

    private boolean isRoaming(long l) {
        if (MSimUtils.isMSim()) {
            int n = MSimUtils.getSlotIdBySimInfoId(l);
            return TelephonyManagerEx.getDefault().isNetworkRoamingForSlot(n);
        }
        return "true".equals((Object)SystemProperties.get((String)"gsm.operator.isroaming", (String)null));
    }

    public int getServiceStateForSlotId(int n) {
        if (MSimUtils.isMSimSlotIdValid(n) && n >= 0 && n < this.mSimServiceState.length) {
            return this.mSimServiceState[n];
        }
        return -1;
    }

    public boolean isAuto(long l) {
        if (this.getAutoDownloadState(PreferenceManager.getDefaultSharedPreferences((Context)this.mContext), l) && !this.mIsOutOfMemory) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void markState(Uri uri, int n, long l) {
        Object object;
        int n2;
        block14 : {
            try {
                object = MiuiPduPersister.getPduPersister((Context)this.mContext).load(uri);
                if (object instanceof NotificationInd) {
                    if (((NotificationInd)object).getExpiry() < System.currentTimeMillis() / 1000 && n == 129) {
                        this.mHandler.post(new Runnable(){

                            @Override
                            public void run() {
                                Toast.makeText((Context)DownloadManager.this.mContext, (int)2131361862, (int)1).show();
                            }
                        });
                        SqliteWrapper.delete((Context)this.mContext, (ContentResolver)this.mContext.getContentResolver(), (Uri)uri, (String)null, (String[])null);
                        return;
                    }
                    break block14;
                }
                if (object != null) {
                    Log.e((String)"DownloadManager", (String)("pdu is not Notification ind for uri " + object.toString()));
                    return;
                }
                Log.e((String)"DownloadManager", (String)"pdu is not Notification ind");
                return;
            }
            catch (MmsException var1_2) {
                Log.e((String)"DownloadManager", (String)var1_2.getMessage(), (Throwable)var1_2);
                return;
            }
        }
        if (n == 135) {
            try {
                object = this.getMessage(uri);
                this.mHandler.post(new Runnable((String)object){
                    final /* synthetic */ String val$message;

                    @Override
                    public void run() {
                        Toast.makeText((Context)DownloadManager.this.mContext, (CharSequence)this.val$message, (int)1).show();
                    }
                });
                n2 = n;
            }
            catch (MmsException var7_6) {
                Log.e((String)"DownloadManager", (String)var7_6.getMessage(), (Throwable)var7_6);
                n2 = n;
            }
        } else {
            n2 = n;
            if (l >= 0) {
                int n3 = MSimUtils.getSlotIdBySimInfoId(l);
                n2 = n;
                if (MSimUtils.isMSimSlotIdValid(n3)) {
                    n2 = n;
                    if (!this.mAutoDownload[n3]) {
                        n2 = n | 4;
                    }
                }
            }
        }
        object = new ContentValues();
        object.put("st", Integer.valueOf((int)n2));
        SqliteWrapper.update((Context)this.mContext, (ContentResolver)this.mContext.getContentResolver(), (Uri)uri, (ContentValues)object, (String)null, (String[])null);
    }

    public void setOutOfMemory(boolean bl) {
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
            this.mIsOutOfMemory = bl;
        }
    }

    public void showErrorCodeToast(final int n) {
        this.mHandler.post(new Runnable(){

            @Override
            public void run() {
                try {
                    Toast.makeText((Context)DownloadManager.this.mContext, (int)n, (int)1).show();
                    return;
                }
                catch (Exception var1_1) {
                    Log.e((String)"DownloadManager", (String)"Caught an exception in showErrorCodeToast");
                    return;
                }
            }
        });
    }

}

