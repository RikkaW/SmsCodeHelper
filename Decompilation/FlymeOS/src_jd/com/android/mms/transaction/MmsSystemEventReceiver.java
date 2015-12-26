package com.android.mms.transaction;

import aan;
import abe;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.transaction.flyme.FlymeTransactionService;
import gr;
import oj;
import ok;
import zv;

public class MmsSystemEventReceiver
  extends BroadcastReceiver
{
  private static MmsSystemEventReceiver a;
  private Handler b = new Handler();
  
  public static void a(Context paramContext)
  {
    Log.v("MmsSystemEventReceiver", "wakeUpService: start transaction service ...");
    paramContext.startService(new Intent(paramContext, FlymeTransactionService.class));
    paramContext.startService(new Intent(paramContext, TransactionService.class));
  }
  
  public static void b(Context paramContext)
  {
    c(paramContext);
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.ANY_DATA_STATE");
    if (Log.isLoggable("Mms:transaction", 2)) {
      Log.v("MmsSystemEventReceiver", "registerForConnectionStateChanges");
    }
    if (a == null) {
      a = new MmsSystemEventReceiver();
    }
    paramContext.registerReceiver(a, localIntentFilter);
  }
  
  public static void c(Context paramContext)
  {
    if (Log.isLoggable("Mms:transaction", 2)) {
      Log.v("MmsSystemEventReceiver", "unRegisterForConnectionStateChanges");
    }
    if (a != null) {}
    try
    {
      paramContext.unregisterReceiver(a);
      return;
    }
    catch (IllegalArgumentException paramContext) {}
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Log.v("MmsSystemEventReceiver", "Intent received: " + paramIntent);
    String str = paramIntent.getAction();
    if (str.equals("android.intent.action.SERVICE_STATE")) {
      if (paramIntent.getExtras() == null) {
        Log.e("Mms", "android.provider.Telephony.SMS_STATE_CHANGED. extras is null.");
      }
    }
    do
    {
      do
      {
        boolean bool;
        do
        {
          do
          {
            return;
            int i = paramIntent.getExtras().getInt("voiceRegState");
            int j = paramIntent.getExtras().getInt("dataRegState");
            zv.a(paramContext, paramIntent.getIntExtra("slot", -1), i, j);
            return;
            if (str.equals("android.intent.action.CONTENT_CHANGED"))
            {
              paramContext = (Uri)paramIntent.getParcelableExtra("deleted_contents");
              if ((paramContext != null) && (paramContext.toString().equals("content://mms-sms/restore-del-allmsg")))
              {
                Log.d("MmsSystemEventReceiver", "Flyme account has logout, clear all cache");
                Process.killProcess(Process.myPid());
                return;
              }
              MmsApp.c().e().a(paramContext);
              return;
            }
            if (!str.equals("android.intent.action.ANY_DATA_STATE")) {
              break;
            }
            paramIntent = paramIntent.getStringExtra("state");
            Log.v("MmsSystemEventReceiver", "ANY_DATA_STATE event received: " + paramIntent);
          } while (!paramIntent.equals("CONNECTED"));
          a(paramContext);
          return;
          if (str.equals("android.intent.action.BOOT_COMPLETED"))
          {
            MessagingNotification.a(paramContext, -2L);
            new Thread(new oj(this, paramContext)).start();
            abe.a(MmsApp.c(), true);
            return;
          }
          if (str.equals("android.intent.action.MEIZU_REMOVE_UNREAD_MMS"))
          {
            gr.a(MmsApp.c(), null);
            return;
          }
          if (str.equals("android.intent.action.SIM_STATE_CHANGED"))
          {
            str = paramIntent.getStringExtra("ss");
            Log.d("Mms", "ACTION_SIM_STATE_CHANGED is " + str);
            if (str.equals("LOADED")) {
              abe.a(MmsApp.c(), true);
            }
            zv.a(paramContext, paramIntent.getIntExtra("slot", -1), str);
            return;
          }
          if (!str.equals("android.provider.Telephony.SMS_STATE_CHANGED")) {
            break;
          }
          bool = paramIntent.getBooleanExtra("ready", true);
          Log.d("Mms", "android.provider.Telephony.SMS_STATE_CHANGED SIM ready -> " + bool);
        } while (!bool);
        abe.a(MmsApp.c(), true);
        return;
        if (!str.equals("com.meizu.notify_show_mms_read_report_action")) {
          break;
        }
        paramIntent = paramIntent.getStringExtra("report");
      } while (paramIntent == null);
      b.post(new ok(this, paramIntent, paramContext));
      return;
      if (str.equals("com.meizu.send_sms_by_message_app"))
      {
        paramIntent.setClass(paramContext, BackgroundSmsMmsSenderService.class);
        paramContext.startService(paramIntent);
        return;
      }
    } while (!str.equals("com.meizu.action.SMS_DEFAULT_APPLICATION_CHANGED"));
    MmsApp.a(paramIntent.getStringExtra("packageName"));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.MmsSystemEventReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */