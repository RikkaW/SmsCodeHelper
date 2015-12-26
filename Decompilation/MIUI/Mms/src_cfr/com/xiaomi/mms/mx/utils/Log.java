/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  miui.os.Build
 */
package com.xiaomi.mms.mx.utils;

import miui.os.Build;

public class Log {
    private static final boolean DEBUG = Build.IS_DEBUGGABLE;

    public static void d(String string2, String string3) {
        if (DEBUG) {
            android.util.Log.d((String)"MiXinSDK", (String)(string2 + ":" + string3));
        }
    }

    public static void e(String string2, String string3) {
        if (DEBUG) {
            android.util.Log.e((String)"MiXinSDK", (String)(string2 + ":" + string3));
        }
    }

    public static void e(String string2, String string3, Throwable throwable) {
        if (DEBUG) {
            android.util.Log.e((String)"MiXinSDK", (String)(string2 + ":" + string3), (Throwable)throwable);
        }
    }

    public static void i(String string2, String string3) {
        if (DEBUG) {
            android.util.Log.i((String)"MiXinSDK", (String)(string2 + ":" + string3));
        }
    }

    public static boolean isDebug() {
        return DEBUG;
    }

    public static void v(String string2, String string3) {
        if (DEBUG) {
            android.util.Log.v((String)"MiXinSDK", (String)(string2 + ":" + string3));
        }
    }

    public static void w(String string2, String string3) {
        if (DEBUG) {
            android.util.Log.d((String)"MiXinSDK", (String)(string2 + ":" + string3));
        }
    }
}

