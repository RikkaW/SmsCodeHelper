/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Intent
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Parcelable
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.net;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.net.ConnectivityManagerCompatGingerbread;
import android.support.v4.net.ConnectivityManagerCompatHoneycombMR2;
import android.support.v4.net.ConnectivityManagerCompatJellyBean;

public class ConnectivityManagerCompat {
    private static final ConnectivityManagerCompatImpl IMPL = Build.VERSION.SDK_INT >= 16 ? new JellyBeanConnectivityManagerCompatImpl() : (Build.VERSION.SDK_INT >= 13 ? new HoneycombMR2ConnectivityManagerCompatImpl() : (Build.VERSION.SDK_INT >= 8 ? new GingerbreadConnectivityManagerCompatImpl() : new BaseConnectivityManagerCompatImpl()));

    public static NetworkInfo getNetworkInfoFromBroadcast(ConnectivityManager connectivityManager, Intent intent) {
        if ((intent = (NetworkInfo)intent.getParcelableExtra("networkInfo")) != null) {
            return connectivityManager.getNetworkInfo(intent.getType());
        }
        return null;
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
        return IMPL.isActiveNetworkMetered(connectivityManager);
    }

    static class BaseConnectivityManagerCompatImpl
    implements ConnectivityManagerCompatImpl {
        BaseConnectivityManagerCompatImpl() {
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
            if ((connectivityManager = connectivityManager.getActiveNetworkInfo()) == null) {
                do {
                    return true;
                    break;
                } while (true);
            }
            switch (connectivityManager.getType()) {
                case 0: {
                    return true;
                }
                default: {
                    return true;
                }
                case 1: 
            }
            return false;
        }
    }

    static interface ConnectivityManagerCompatImpl {
        public boolean isActiveNetworkMetered(ConnectivityManager var1);
    }

    static class GingerbreadConnectivityManagerCompatImpl
    implements ConnectivityManagerCompatImpl {
        GingerbreadConnectivityManagerCompatImpl() {
        }

        @Override
        public boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
            return ConnectivityManagerCompatGingerbread.isActiveNetworkMetered(connectivityManager);
        }
    }

    static class HoneycombMR2ConnectivityManagerCompatImpl
    implements ConnectivityManagerCompatImpl {
        HoneycombMR2ConnectivityManagerCompatImpl() {
        }

        @Override
        public boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
            return ConnectivityManagerCompatHoneycombMR2.isActiveNetworkMetered(connectivityManager);
        }
    }

    static class JellyBeanConnectivityManagerCompatImpl
    implements ConnectivityManagerCompatImpl {
        JellyBeanConnectivityManagerCompatImpl() {
        }

        @Override
        public boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
            return ConnectivityManagerCompatJellyBean.isActiveNetworkMetered(connectivityManager);
        }
    }

}

