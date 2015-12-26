/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.preference.PreferenceManager
 *  android.provider.Settings
 *  android.provider.Settings$System
 *  android.util.Pair
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 *  miui.os.Build
 */
package com.android.mms.cloudbackup;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Pair;
import com.android.mms.ServiceCategoryUpdateAsyncTask;
import com.android.mms.ui.MessageUtils;
import miui.os.Build;

public class MessageConfigUtils {
    private static final Pair<String, Boolean>[] MMSConfigs;
    public static ServiceCategoryUpdateAsyncTask.IServiceCategoryUpdateListener fakeServiceCategoryUpdateListener;

    static {
        fakeServiceCategoryUpdateListener = new ServiceCategoryUpdateAsyncTask.IServiceCategoryUpdateListener(){

            @Override
            public void onUpdateSuccess() {
            }
        };
        MMSConfigs = new Pair[]{Pair.create((Object)"pref_key_mms_read_reports", (Object)false), Pair.create((Object)"pref_key_mms_auto_retrieval", (Object)true), Pair.create((Object)"pref_key_mms_retrieval_during_roaming", (Object)false)};
    }

    public static boolean getAllowSiSlPush(Context context) {
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("pref_key_allow_si_sl_push", true);
    }

    public static boolean getCollapseSpMessage(Context context) {
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("pref_key_collapse_sp_messages", true);
    }

    public static String getDateType(SharedPreferences sharedPreferences) {
        return MessageUtils.getDateType(sharedPreferences);
    }

    public static boolean getFestivalNetWorking(Context context) {
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("pref_key_festival_networking", true);
    }

    public static boolean getHideFestival(Context context) {
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("pref_key_hide_festival", true);
    }

    public static String getNotificationRingToneRepeat(SharedPreferences sharedPreferences) {
        return MessageUtils.getNotificationRingToneRepeat(sharedPreferences);
    }

    public static boolean getPrefNotificationBodyEnabled(Context context) {
        return MessageUtils.getPrefNotificationBodyEnabled(context);
    }

    public static boolean getShowBlockedMessage(Context context) {
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("pref_key_show_blocked_messages", true);
    }

    public static boolean getShowRecentContacts(Context context) {
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("pref_key_show_recent_contacts", true);
    }

    public static boolean getShowTemplate(Context context) {
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("pref_key_show_template", true);
    }

    public static boolean getTimeStyleAutoGroup(Context context) {
        return PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("pref_key_time_style_auto_group", true);
    }

    public static void setAllowSiSlPush(Context context, boolean bl) {
        PreferenceManager.getDefaultSharedPreferences((Context)context).edit().putBoolean("pref_key_allow_si_sl_push", bl).commit();
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void setCollapseSpMessage(Context context, boolean bl) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context)context);
        if (Build.IS_INTERNATIONAL_BUILD) {
            bl = false;
        }
        sharedPreferences.edit().putBoolean("pref_key_collapse_sp_messages", bl).commit();
        if (!Build.IS_INTERNATIONAL_BUILD) {
            bl = !sharedPreferences.contains("pref_service_category_upadate_time");
            ServiceCategoryUpdateAsyncTask.startUpdateAsyncTask(context, bl, fakeServiceCategoryUpdateListener);
        }
    }

    public static void setDateType(SharedPreferences sharedPreferences, String string) {
        sharedPreferences.edit().putString("pref_key_date_type", string).commit();
    }

    public static void setFestivalNetWorking(Context context, boolean bl) {
        context = PreferenceManager.getDefaultSharedPreferences((Context)context);
        if (Build.IS_INTERNATIONAL_BUILD) {
            return;
        }
        context.edit().putBoolean("pref_key_festival_networking", bl).commit();
    }

    public static void setHideFestival(Context context, boolean bl) {
        context = PreferenceManager.getDefaultSharedPreferences((Context)context);
        if (MessageUtils.isZhCnLanguage() || Build.IS_INTERNATIONAL_BUILD) {
            return;
        }
        context.edit().putBoolean("pref_key_hide_festival", bl).commit();
    }

    public static void setNotificationRingToneRepeat(SharedPreferences sharedPreferences, String string) {
        sharedPreferences.edit().putString("pref_key_ringtone_repeat", string).commit();
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void setPrefNotificationBodyEnabled(Context context, boolean bl) {
        context = context.getContentResolver();
        int n = bl ? 1 : 0;
        Settings.System.putInt((ContentResolver)context, (String)"pref_key_enable_notification_body", (int)n);
    }

    public static void setShowBlockedMessage(Context context, boolean bl) {
        PreferenceManager.getDefaultSharedPreferences((Context)context).edit().putBoolean("pref_key_show_blocked_messages", bl).commit();
    }

    public static void setShowRecentContacts(Context context, boolean bl) {
        PreferenceManager.getDefaultSharedPreferences((Context)context).edit().putBoolean("pref_key_show_recent_contacts", bl).commit();
    }

    public static void setShowTemplate(Context context, boolean bl) {
        PreferenceManager.getDefaultSharedPreferences((Context)context).edit().putBoolean("pref_key_show_template", bl).commit();
    }

    public static void setTimeStyleAutoGroup(Context context, boolean bl) {
        PreferenceManager.getDefaultSharedPreferences((Context)context).edit().putBoolean("pref_key_time_style_auto_group", bl).commit();
    }

}

