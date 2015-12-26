/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.preference.PreferenceManager
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.mx.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtils {
    public static long getSettingLong(Context context, String string2, long l) {
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getLong(string2, l);
    }

    public static void setSettingLong(Context context, String string2, long l) {
        PreferenceManager.getDefaultSharedPreferences((Context)context).edit().putLong(string2, l).commit();
    }
}

