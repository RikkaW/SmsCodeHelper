package com.meizu.cloud.pushsdk.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

public class SystemUtils
{
  public static boolean compareVersion(String paramString1, String paramString2)
  {
    boolean bool = false;
    paramString1 = paramString1.split("\\.");
    paramString2 = paramString2.split("\\.");
    int m = Math.min(paramString1.length, paramString2.length);
    int i = 0;
    int j = 0;
    while (j < m)
    {
      int k = paramString1[j].length() - paramString2[j].length();
      i = k;
      if (k != 0) {
        break;
      }
      k = paramString1[j].compareTo(paramString2[j]);
      i = k;
      if (k != 0) {
        break;
      }
      j += 1;
      i = k;
    }
    if (i != 0) {}
    for (;;)
    {
      if (i >= 0) {
        bool = true;
      }
      return bool;
      i = paramString1.length - paramString2.length;
    }
  }
  
  /* Error */
  public static String getAppVersionName(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 43	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: aload_1
    //   5: iconst_0
    //   6: invokevirtual 49	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   9: getfield 55	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   12: astore_0
    //   13: aload_0
    //   14: ifnull +12 -> 26
    //   17: aload_0
    //   18: astore_1
    //   19: aload_0
    //   20: invokevirtual 29	java/lang/String:length	()I
    //   23: ifgt +6 -> 29
    //   26: ldc 57
    //   28: astore_1
    //   29: aload_1
    //   30: areturn
    //   31: astore_1
    //   32: ldc 57
    //   34: astore_0
    //   35: ldc 59
    //   37: ldc 61
    //   39: aload_1
    //   40: invokestatic 67	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   43: pop
    //   44: aload_0
    //   45: areturn
    //   46: astore_1
    //   47: goto -12 -> 35
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	50	0	paramContext	Context
    //   0	50	1	paramString	String
    // Exception table:
    //   from	to	target	type
    //   0	13	31	java/lang/Exception
    //   19	26	46	java/lang/Exception
  }
  
  public static String getCurProcessName(Context paramContext)
  {
    int i = Process.myPid();
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      if (!paramContext.hasNext()) {
        return null;
      }
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
    } while (pid != i);
    return processName;
  }
  
  public static String getMzPushServicePackageName(Context paramContext)
  {
    String str = paramContext.getPackageName();
    try
    {
      paramContext = getServicesByPackageName(paramContext, "com.meizu.cloud");
      if ((!TextUtils.isEmpty(paramContext)) && (paramContext.contains("mzservice_v1"))) {
        return "com.meizu.cloud";
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.i("SystemUtils", "startservice package name " + str);
    }
    return str;
  }
  
  private static String getServicesByContext(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = getPackageInfogetPackageName4services;
      if (paramContext == null) {
        return null;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
      int i = 0;
      while (i < paramContext.length)
      {
        Log.i("SystemUtils", "serviceinfo name " + name + " processName " + processName);
        if ("com.meizu.cloud.pushsdk.pushservice.MzPushService".equals(name)) {
          return processName;
        }
        i += 1;
      }
    }
    return null;
  }
  
  private static String getServicesByPackageName(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = getPackageInfo4services;
      if (paramContext == null) {
        return null;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext = null;
      }
      int i = 0;
      while (i < paramContext.length)
      {
        Log.i("SystemUtils", "serviceinfo name " + name + " processName " + processName);
        if ("com.meizu.cloud.pushsdk.pushservice.MzPushService".equals(name)) {
          return processName;
        }
        i += 1;
      }
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.meizu.cloud.pushsdk.util.SystemUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */