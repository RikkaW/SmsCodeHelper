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
package com.xiaomi.channel.commonutils.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public abstract class PreferenceUtils {
    public static void checkProcess(Context context) {
    }

    public static boolean getSettingBoolean(Context context, String string2, boolean bl) {
        PreferenceUtils.checkProcess(context);
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean(string2, bl);
    }

    public static void setSettingBoolean(Context context, String string2, boolean bl) {
        PreferenceUtils.checkProcess(context);
        PreferenceManager.getDefaultSharedPreferences((Context)context).edit().putBoolean(string2, bl).commit();
    }
}

