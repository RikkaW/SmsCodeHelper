/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.net.ConnectivityManager
 *  java.lang.Object
 */
package android.support.v4.net;

import android.net.ConnectivityManager;

class ConnectivityManagerCompatJellyBean {
    ConnectivityManagerCompatJellyBean() {
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
        return connectivityManager.isActiveNetworkMetered();
    }
}

