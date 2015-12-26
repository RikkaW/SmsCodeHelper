package com.android.mms.transaction;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.android.mms.transaction.flyme.FlymeTransactionService;
import com.android.mms.transaction.sns.SnsTransactionService;

public class BackgroundService
  extends Service
{
  private static a c;
  private static b d;
  private Looper a;
  private c b;
  
  private void a()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.ANY_DATA_STATE");
    if (c == null) {
      c = new a(null);
    }
    getApplicationContext().registerReceiver(c, localIntentFilter);
  }
  
  private void a(Context paramContext)
  {
    Log.d("FlymeBackgroundService", "wakeUpService : " + FlymeTransactionService.class.getName());
    Intent localIntent = new Intent(paramContext, FlymeTransactionService.class);
    localIntent.setAction("android.intent.action.REHANDLE_ALL_UNHANDLE_MSG");
    localIntent.putExtra("From", BackgroundService.class.getName());
    paramContext.startService(localIntent);
    Log.d("FlymeBackgroundService", "wakeUpService : " + SnsTransactionService.class.getName());
    localIntent = new Intent(paramContext, SnsTransactionService.class);
    localIntent.setAction("android.intent.action.REHANDLE_ALL_UNHANDLE_MSG");
    localIntent.putExtra("From", BackgroundService.class.getName());
    paramContext.startService(localIntent);
    b.sendEmptyMessage(3);
  }
  
  private void b()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
    localIntentFilter.addDataScheme("file");
    if (d == null) {
      d = new b(null);
    }
    getApplicationContext().registerReceiver(d, localIntentFilter);
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    Log.v("FlymeBackgroundService", "Creating FlymeBackgroundService");
    HandlerThread localHandlerThread = new HandlerThread("FlymeBackgroundService");
    localHandlerThread.start();
    a = localHandlerThread.getLooper();
    b = new c(a);
  }
  
  public void onDestroy()
  {
    Log.v("FlymeBackgroundService", "Destroying FlymeBackgroundService");
    b.sendEmptyMessage(3);
    unregisterReceiver(c);
    unregisterReceiver(d);
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    Log.d("FlymeBackgroundService", "onStartCommand intent : " + paramIntent);
    Object localObject;
    if (paramIntent != null)
    {
      paramInt1 = 0;
      localObject = paramIntent.getAction();
      if (!((String)localObject).equals("android.intent.action.LISTEN_NETWORK_STATUS")) {
        break label83;
      }
      paramInt1 = 1;
    }
    for (;;)
    {
      localObject = b.obtainMessage(paramInt1);
      arg1 = paramInt2;
      obj = paramIntent;
      b.sendMessage((Message)localObject);
      return 2;
      label83:
      if (((String)localObject).equals("android.intent.action.LISTEN_SDCARD_STATUS")) {
        paramInt1 = 2;
      } else {
        Log.d("FlymeBackgroundService", "FlymeBackgroundService handle unknow action!");
      }
    }
  }
  
  class a
    extends BroadcastReceiver
  {
    private a() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      Log.d("FlymeBackgroundService", "NetworkStatusMonitorReceiver -> intent : " + paramIntent);
      if (paramIntent.getAction().equals("android.intent.action.ANY_DATA_STATE"))
      {
        paramIntent = paramIntent.getStringExtra("state");
        Log.d("FlymeBackgroundService", "NetworkInfo : " + paramIntent);
        if (paramIntent.equals("CONNECTED")) {
          BackgroundService.a(BackgroundService.this, paramContext);
        }
      }
    }
  }
  
  class b
    extends BroadcastReceiver
  {
    private b() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      Log.d("FlymeBackgroundService", "SDCardMountStatusMonitorReceiver -> intent : " + paramIntent);
      if (paramIntent.getAction().equals("android.intent.action.MEDIA_MOUNTED")) {
        BackgroundService.a(BackgroundService.this, paramContext);
      }
    }
  }
  
  final class c
    extends Handler
  {
    public c(Looper paramLooper)
    {
      super();
    }
    
    private String a(Message paramMessage)
    {
      if (what == 2) {
        return "EVENT_LISTEN_SDCARD_STATUS";
      }
      if (what == 1) {
        return "EVENT_LISTEN_NEWWORK_STATUS";
      }
      if (what == 3) {
        return "EVENT_QUIT";
      }
      return "unknown message.what";
    }
    
    public void handleMessage(Message paramMessage)
    {
      Log.v("FlymeBackgroundService", "Handling incoming message :" + paramMessage + " = " + a(paramMessage));
      switch (what)
      {
      default: 
        Log.d("FlymeBackgroundService", "what= " + what);
        return;
      case 1: 
        BackgroundService.a(BackgroundService.this);
        return;
      case 2: 
        BackgroundService.b(BackgroundService.this);
        return;
      }
      getLooper().quit();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.BackgroundService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */