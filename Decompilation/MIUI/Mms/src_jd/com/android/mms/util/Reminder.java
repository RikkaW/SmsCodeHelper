package com.android.mms.util;

import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.android.mms.ui.MessageUtils;

public class Reminder
  extends BroadcastReceiver
{
  private static String ACTION_REMINDER = "com.android.mms.action.Reminder";
  private static long INTERVAL_FIVE_MINUTES;
  private static String TAG = "Reminder";
  private static Notification reminderNotification = null;
  private static int sReminderRepeatCount = 0;
  private static final Object sReminderRepeatCountLock = new Object();
  
  static
  {
    INTERVAL_FIVE_MINUTES = 300000L;
  }
  
  public static void cancelReminder(Context paramContext)
  {
    synchronized (sReminderRepeatCountLock)
    {
      if (reminderNotification != null)
      {
        reminderNotification = null;
        sReminderRepeatCount = 0;
        ((AlarmManager)paramContext.getSystemService("alarm")).cancel(getSender(paramContext));
      }
      return;
    }
  }
  
  private static int getPrefRepeatCount(SharedPreferences paramSharedPreferences)
  {
    paramSharedPreferences = paramSharedPreferences.getString("pref_key_ringtone_repeat", "1");
    try
    {
      int i = Integer.parseInt(paramSharedPreferences);
      return i;
    }
    catch (NumberFormatException paramSharedPreferences) {}
    return 1;
  }
  
  private static PendingIntent getSender(Context paramContext)
  {
    Intent localIntent = new Intent(paramContext, Reminder.class);
    localIntent.setAction(ACTION_REMINDER);
    return PendingIntent.getBroadcast(paramContext, 0, localIntent, 134217728);
  }
  
  public static void newNotification(Context paramContext, Notification paramNotification)
  {
    cancelReminder(paramContext);
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    if (!MessageUtils.getPrefNotificationEnabled(paramContext)) {
      return;
    }
    KeyguardManager localKeyguardManager = (KeyguardManager)paramContext.getSystemService("keyguard");
    int i = getPrefRepeatCount(localSharedPreferences);
    if ((i == 1) || (!localKeyguardManager.inKeyguardRestrictedInputMode()))
    {
      updateNotification(paramContext, paramNotification);
      return;
    }
    updateNotification(paramContext, paramNotification);
    setupReminder(paramContext, paramNotification, i);
  }
  
  private static void setupReminder(Context paramContext, Notification paramNotification, int paramInt)
  {
    synchronized (sReminderRepeatCountLock)
    {
      reminderNotification = paramNotification;
      sReminderRepeatCount = paramInt - 1;
      ((AlarmManager)paramContext.getSystemService("alarm")).setRepeating(0, System.currentTimeMillis() + INTERVAL_FIVE_MINUTES, INTERVAL_FIVE_MINUTES, getSender(paramContext));
      return;
    }
  }
  
  private static void updateNotification(Context paramContext, Notification paramNotification)
  {
    VibratorManager.vibrate(paramContext);
    ((NotificationManager)paramContext.getSystemService("notification")).notify(123, paramNotification);
  }
  
  public void onReceive(Context paramContext, Intent arg2)
  {
    ??? = ???.getAction();
    if ("android.intent.action.USER_PRESENT".equals(???)) {
      cancelReminder(paramContext);
    }
    while (!ACTION_REMINDER.equals(???)) {
      return;
    }
    if (!MessageUtils.getPrefNotificationEnabled(paramContext))
    {
      Log.v(TAG, "user cancel for disable notification");
      cancelReminder(paramContext);
      return;
    }
    synchronized (sReminderRepeatCountLock)
    {
      if (sReminderRepeatCount > 0)
      {
        updateNotification(paramContext, reminderNotification);
        sReminderRepeatCount -= 1;
      }
      if (sReminderRepeatCount <= 0) {
        cancelReminder(paramContext);
      }
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.Reminder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */