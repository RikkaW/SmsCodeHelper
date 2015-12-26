package com.android.mms.update;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;
import com.android.mms.MmsApp;

public class UpdateManager
{
  public static void init()
  {
    Application localApplication = MmsApp.getApp();
    long l = PreferenceManager.getDefaultSharedPreferences(localApplication).getLong("update_time_preference", 0L);
    if (l == 0L)
    {
      Log.v("UpdateManager", " no need updates, ignore");
      return;
    }
    if (l < System.currentTimeMillis())
    {
      Log.v("UpdateManager", " update immediately! ");
      TemplateRequest.tryUpdateTemplate();
      onUpdated();
      return;
    }
    Log.v("UpdateManager", " schedule update at: " + l);
    updateAt(localApplication, l);
  }
  
  public static void onUpdated()
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MmsApp.getApp());
    localSharedPreferences.edit().putLong("update_time_preference", 0L).commit();
    localSharedPreferences.edit().putString("update_content_preference", "").commit();
  }
  
  public static void requestUpdateAt(long paramLong, String paramString)
  {
    Application localApplication = MmsApp.getApp();
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(localApplication);
    localSharedPreferences.edit().putLong("update_time_preference", paramLong).commit();
    localSharedPreferences.edit().putString("update_content_preference", paramString).commit();
    updateAt(localApplication, paramLong);
  }
  
  public static void updateAt(Context paramContext, long paramLong)
  {
    AlarmManager localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
    Intent localIntent = new Intent(paramContext, TimedUpdateReceiver.class);
    localIntent.setAction("com.android.mms.update.UPDATE_TEMPLATE");
    localAlarmManager.set(0, paramLong, PendingIntent.getBroadcast(paramContext, 0, localIntent, 268435456));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.update.UpdateManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */