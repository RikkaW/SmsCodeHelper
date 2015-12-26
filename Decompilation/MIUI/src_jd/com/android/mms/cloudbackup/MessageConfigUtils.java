package com.android.mms.cloudbackup;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.provider.Settings.System;
import android.util.Pair;
import com.android.mms.ServiceCategoryUpdateAsyncTask;
import com.android.mms.ServiceCategoryUpdateAsyncTask.IServiceCategoryUpdateListener;
import com.android.mms.ui.MessageUtils;
import miui.os.Build;

public class MessageConfigUtils
{
  private static final Pair<String, Boolean>[] MMSConfigs = { Pair.create("pref_key_mms_read_reports", Boolean.valueOf(false)), Pair.create("pref_key_mms_auto_retrieval", Boolean.valueOf(true)), Pair.create("pref_key_mms_retrieval_during_roaming", Boolean.valueOf(false)) };
  public static ServiceCategoryUpdateAsyncTask.IServiceCategoryUpdateListener fakeServiceCategoryUpdateListener = new ServiceCategoryUpdateAsyncTask.IServiceCategoryUpdateListener()
  {
    public void onUpdateSuccess() {}
  };
  
  public static boolean getAllowSiSlPush(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_allow_si_sl_push", true);
  }
  
  public static boolean getCollapseSpMessage(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_collapse_sp_messages", true);
  }
  
  public static String getDateType(SharedPreferences paramSharedPreferences)
  {
    return MessageUtils.getDateType(paramSharedPreferences);
  }
  
  public static boolean getFestivalNetWorking(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_festival_networking", true);
  }
  
  public static boolean getHideFestival(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_hide_festival", true);
  }
  
  public static String getNotificationRingToneRepeat(SharedPreferences paramSharedPreferences)
  {
    return MessageUtils.getNotificationRingToneRepeat(paramSharedPreferences);
  }
  
  public static boolean getPrefNotificationBodyEnabled(Context paramContext)
  {
    return MessageUtils.getPrefNotificationBodyEnabled(paramContext);
  }
  
  public static boolean getShowBlockedMessage(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_show_blocked_messages", true);
  }
  
  public static boolean getShowRecentContacts(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_show_recent_contacts", true);
  }
  
  public static boolean getShowTemplate(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_show_template", true);
  }
  
  public static boolean getTimeStyleAutoGroup(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_key_time_style_auto_group", true);
  }
  
  public static void setAllowSiSlPush(Context paramContext, boolean paramBoolean)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putBoolean("pref_key_allow_si_sl_push", paramBoolean).commit();
  }
  
  public static void setCollapseSpMessage(Context paramContext, boolean paramBoolean)
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    if (Build.IS_INTERNATIONAL_BUILD) {
      paramBoolean = false;
    }
    localSharedPreferences.edit().putBoolean("pref_key_collapse_sp_messages", paramBoolean).commit();
    if (!Build.IS_INTERNATIONAL_BUILD) {
      if (localSharedPreferences.contains("pref_service_category_upadate_time")) {
        break label62;
      }
    }
    label62:
    for (paramBoolean = true;; paramBoolean = false)
    {
      ServiceCategoryUpdateAsyncTask.startUpdateAsyncTask(paramContext, paramBoolean, fakeServiceCategoryUpdateListener);
      return;
    }
  }
  
  public static void setDateType(SharedPreferences paramSharedPreferences, String paramString)
  {
    paramSharedPreferences.edit().putString("pref_key_date_type", paramString).commit();
  }
  
  public static void setFestivalNetWorking(Context paramContext, boolean paramBoolean)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    if (Build.IS_INTERNATIONAL_BUILD) {
      return;
    }
    paramContext.edit().putBoolean("pref_key_festival_networking", paramBoolean).commit();
  }
  
  public static void setHideFestival(Context paramContext, boolean paramBoolean)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    if ((MessageUtils.isZhCnLanguage()) || (Build.IS_INTERNATIONAL_BUILD)) {
      return;
    }
    paramContext.edit().putBoolean("pref_key_hide_festival", paramBoolean).commit();
  }
  
  public static void setNotificationRingToneRepeat(SharedPreferences paramSharedPreferences, String paramString)
  {
    paramSharedPreferences.edit().putString("pref_key_ringtone_repeat", paramString).commit();
  }
  
  public static void setPrefNotificationBodyEnabled(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getContentResolver();
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      Settings.System.putInt(paramContext, "pref_key_enable_notification_body", i);
      return;
    }
  }
  
  public static void setShowBlockedMessage(Context paramContext, boolean paramBoolean)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putBoolean("pref_key_show_blocked_messages", paramBoolean).commit();
  }
  
  public static void setShowRecentContacts(Context paramContext, boolean paramBoolean)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putBoolean("pref_key_show_recent_contacts", paramBoolean).commit();
  }
  
  public static void setShowTemplate(Context paramContext, boolean paramBoolean)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putBoolean("pref_key_show_template", paramBoolean).commit();
  }
  
  public static void setTimeStyleAutoGroup(Context paramContext, boolean paramBoolean)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putBoolean("pref_key_time_style_auto_group", paramBoolean).commit();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.cloudbackup.MessageConfigUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */