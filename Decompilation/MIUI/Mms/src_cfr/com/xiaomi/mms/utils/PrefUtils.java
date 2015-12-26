/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {
    public static String getString(Context context, String string2) {
        return context.getSharedPreferences("pref_mx", 0).getString(string2, null);
    }

    public static void remove(Context context, String string2) {
        context.getSharedPreferences("pref_mx", 0).edit().remove(string2).commit();
    }

    public static void saveString(Context context, String string2, String string3) {
        context = context.getSharedPreferences("pref_mx", 0).edit();
        context.putString(string2, string3);
        context.commit();
    }
}

