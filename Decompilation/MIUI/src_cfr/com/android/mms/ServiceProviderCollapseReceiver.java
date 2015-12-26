/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Application
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.preference.PreferenceManager
 *  android.telephony.PhoneStateListener
 *  android.telephony.ServiceState
 *  android.telephony.TelephonyManager
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package com.android.mms;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.ServiceCategoryUpdateAsyncTask;

public class ServiceProviderCollapseReceiver
extends BroadcastReceiver
implements ServiceCategoryUpdateAsyncTask.IServiceCategoryUpdateListener {
    private static long UPDATE_INTERVAL = 604800000;
    private static PhoneStateListener sPhoneListener;

    /*
     * Enabled aggressive block sorting
     */
    public void onReceive(final Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals((Object)intent.getAction())) {
            intent = PreferenceManager.getDefaultSharedPreferences((Context)context);
            final boolean bl = !intent.contains("pref_service_category_upadate_time");
            long l = intent.getLong("pref_service_category_upadate_time", 0);
            boolean bl2 = false;
            long l2 = System.currentTimeMillis();
            if (bl || l2 - l >= UPDATE_INTERVAL || l > l2) {
                bl2 = true;
            }
            if (bl2) {
                if (sPhoneListener != null) return;
                sPhoneListener = new PhoneStateListener(){

                    public void onServiceStateChanged(ServiceState serviceState) {
                        Log.d((String)"ServiceNumberCollapseReceiver", (String)("onServiceStateChanged: " + (Object)serviceState));
                        ServiceCategoryUpdateAsyncTask.startUpdateAsyncTask(context, bl, ServiceProviderCollapseReceiver.this);
                    }
                };
                ((TelephonyManager)context.getSystemService("phone")).listen(sPhoneListener, 1);
            }
        }
    }

    @Override
    public void onUpdateSuccess() {
        Application application = MmsApp.getApp();
        if (sPhoneListener != null) {
            ((TelephonyManager)application.getSystemService("phone")).listen(sPhoneListener, 0);
        }
    }

}

