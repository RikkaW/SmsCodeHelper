package com.meizu.cloud.pushsdk;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public class NotificationService
  extends Service
{
  private static final int STOP_SERVICE = 10001;
  private static final String TAG = "NotificationService";
  Handler handler;
  
  private void stopService()
  {
    handler.removeMessages(10001);
    handler.sendEmptyMessageDelayed(10001, 60000L);
  }
  
  public String getReceiver(String paramString1, String paramString2)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (!TextUtils.isEmpty(paramString1))
    {
      localObject1 = localObject2;
      if (!TextUtils.isEmpty(paramString2))
      {
        paramString2 = new Intent(paramString2);
        paramString2.setPackage(paramString1);
        paramString1 = getPackageManager().queryBroadcastReceivers(paramString2, 0);
        localObject1 = localObject2;
        if (paramString1 != null)
        {
          localObject1 = localObject2;
          if (paramString1.size() > 0) {
            localObject1 = get0activityInfo.name;
          }
        }
      }
    }
    return (String)localObject1;
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    Log.d("NotificationService", "onBind initSuc: ");
    return null;
  }
  
  public void onCreate()
  {
    Log.i("NotificationService", "NotificationService create");
    super.onCreate();
    handler = new NotificationService.1(this, getMainLooper());
  }
  
  public void onDestroy()
  {
    Log.i("NotificationService", "NotificationService destroy");
    super.onDestroy();
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    String str = paramIntent.getStringExtra("command_type");
    Log.d("NotificationService", "-- command_type -- " + str);
    if ((!TextUtils.isEmpty(str)) && (str.equals("reflect_receiver")))
    {
      reflectReceiver(paramIntent);
      stopService();
    }
    return 2;
  }
  
  public boolean onUnbind(Intent paramIntent)
  {
    return super.onUnbind(paramIntent);
  }
  
  public void reflectReceiver(Intent paramIntent)
  {
    String str = getReceiver(getPackageName(), paramIntent.getAction());
    if (TextUtils.isEmpty(str))
    {
      Log.i("NotificationService", " reflectReceiver error: receiver for: " + paramIntent.getAction() + " not found, package: " + getPackageName());
      paramIntent.setPackage(getPackageName());
      sendBroadcast(paramIntent);
      return;
    }
    try
    {
      Object localObject2 = Class.forName(str);
      Object localObject1 = ((Class)localObject2).getConstructor((Class[])null).newInstance((Object[])null);
      localObject2 = ((Class)localObject2).getMethod("onReceive", new Class[] { Context.class, Intent.class });
      paramIntent.setClassName(getPackageName(), str);
      ((Method)localObject2).invoke(localObject1, new Object[] { getApplicationContext(), paramIntent });
      return;
    }
    catch (Exception paramIntent)
    {
      Log.i("NotificationService", "reflect e: " + paramIntent);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.cloud.pushsdk.NotificationService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */