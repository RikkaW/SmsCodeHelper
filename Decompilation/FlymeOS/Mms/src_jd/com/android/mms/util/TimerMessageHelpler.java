package com.android.mms.util;

import abq;
import abr;
import abs;
import abt;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Telephony.Sms;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.android.mms.transaction.SmsReceiver;
import com.android.mms.view.CustomTimePicker;
import hb;
import java.util.Calendar;
import java.util.Date;
import zv;

public class TimerMessageHelpler
  extends BroadcastReceiver
{
  private static PendingIntent a = null;
  private static AlarmManager b = null;
  private static long c = -1L;
  private static long d = c;
  private static AsyncTask<Void, Void, Void> e = null;
  private static long f = 300000L;
  private static int[] g = new int[6];
  
  public static void a(Context paramContext)
  {
    b("init");
    b = (AlarmManager)paramContext.getSystemService("alarm");
    b(paramContext);
  }
  
  public static void a(Context paramContext, long paramLong)
  {
    b("sendTimerMessage time " + paramLong);
    if (a == null)
    {
      b("sendTimerMessage init mAlarmPendingIntent");
      a = PendingIntent.getBroadcast(paramContext, 0, new Intent("com.android.mms.SEND_TIMER_MSG_ACTION"), 0);
    }
    for (d = paramLong;; d = paramLong)
    {
      b("sendTimerMessage mAlarmManager.set " + paramLong);
      b.set(0, paramLong, a);
      do
      {
        return;
        b("sendTimerMessage check lastSendTime " + d);
      } while ((d != c) && (d <= paramLong));
      b.cancel(a);
    }
  }
  
  public static void a(Context paramContext, long paramLong, a parama)
  {
    try
    {
      Object localObject = paramContext.getResources().getString(2131493706);
      String str = paramContext.getResources().getString(2131493707);
      View localView = LayoutInflater.from(paramContext).inflate(2130968619, null);
      TextView localTextView = (TextView)localView.findViewById(2131886254);
      CustomTimePicker localCustomTimePicker = (CustomTimePicker)localView.findViewById(2131886256);
      long l = paramLong;
      if (paramLong == 0L) {
        l = System.currentTimeMillis() + f;
      }
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.setTime(new Date());
      localCalendar.add(1, 1);
      localCustomTimePicker.a(new Date(), localCalendar.getTime());
      localCustomTimePicker.a(l, false);
      localObject = new abr(localCustomTimePicker, paramContext, str, (String)localObject, localTextView);
      localCustomTimePicker.setOnScrollTextViewChangedListener(new abs((Runnable)localObject));
      ((Runnable)localObject).run();
      paramContext = new AlertDialog.Builder(paramContext, 2131624320);
      paramContext.setView(localView);
      paramContext.setPositiveButton(17039370, new abt(localCustomTimePicker, parama));
      paramContext.setNegativeButton(17039360, null);
      paramContext.create().show();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void a(Context paramContext, Uri paramUri, String paramString, long paramLong)
  {
    b("updateSmsAsTimer selection = " + paramString + ", dest = " + paramLong);
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("type", Integer.valueOf(7));
    localContentValues.put("date_sent", Long.valueOf(paramLong));
    paramContext.getContentResolver().update(paramUri, localContentValues, paramString, null);
  }
  
  private static void b()
  {
    b("resetLastSendTime");
    d = c;
  }
  
  public static void b(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("refreshTimer resetTimer = true, mRefreshTask == null? ");
    if (e == null) {}
    for (boolean bool = true;; bool = false)
    {
      b(bool);
      if (e == null)
      {
        e = new abq(paramContext);
        e.execute(new Void[0]);
      }
      return;
    }
  }
  
  private static void b(Context paramContext, long paramLong, int paramInt, String paramString)
  {
    b("sendTimerSms id = " + paramLong + ", protocol = " + paramInt + ", imsi = " + paramString);
    int i;
    if (paramInt == 256)
    {
      i = -10;
      b("sendTimerSms original 1 simID = " + -10);
    }
    for (;;)
    {
      b("sendTimerSms final simID = " + i);
      paramString = new ContentValues();
      paramString.put("type", Integer.valueOf(6));
      paramString.put("association_id", Long.valueOf(System.currentTimeMillis()));
      paramString.put("sim_id", Integer.valueOf(i));
      paramString.put("imsi", zv.c(i));
      paramString.put("date", Long.valueOf(System.currentTimeMillis()));
      int j = paramContext.getContentResolver().update(ContentUris.withAppendedId(Telephony.Sms.CONTENT_URI, paramLong), paramString, null, null);
      b("sendTimerSms result = " + j);
      if (j > 0)
      {
        if (hb.b(paramInt) != 1) {
          break;
        }
        paramContext.sendBroadcast(new Intent("com.android.mms.transaction.SEND_SIP_MESSAGE", null, paramContext, SmsReceiver.class));
      }
      return;
      j = zv.a(paramString);
      b("sendTimerSms original 2 simID = " + j);
      i = j;
      if (!zv.a(j)) {
        i = zv.a();
      }
    }
    paramString = new Intent("com.android.mms.transaction.SEND_MESSAGE", null, paramContext, SmsReceiver.class);
    paramString.putExtra("sim_id", i);
    paramContext.sendBroadcast(paramString);
  }
  
  private static void b(String paramString)
  {
    Log.v("TimerMessageHelpler", "TimerMessageHelpler " + paramString);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    b("onReceive uri");
    b();
    b(paramContext);
  }
  
  public static abstract interface a
  {
    public abstract void a(long paramLong);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.TimerMessageHelpler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */