/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Application
 *  android.content.Context
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.update;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.android.mms.MmsApp;
import com.android.mms.ui.MessageUtils;

public class Network {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean allowUpdate(boolean bl, boolean bl2) {
        Application application = MmsApp.getApp();
        if (!bl2 && !MessageUtils.isAllowNetworkAccess((Context)application)) return false;
        boolean bl3 = true;
        if (!bl3) {
            return false;
        }
        if (!bl) return Network.isNetWorkConnected((Context)application);
        return Network.isWifiConnected((Context)application);
    }

    public static boolean isNetWorkConnected(Context context) {
        if ((context = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo()) != null && context.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public static boolean isWifiConnected(Context context) {
        context = (ConnectivityManager)context.getSystemService("connectivity");
        NetworkInfo networkInfo = context.getActiveNetworkInfo();
        if (!context.isActiveNetworkMetered() && networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}

