package com.meizu.cloud.pushsdk.util;

import android.os.Build.VERSION;

public class MinSdkChecker
{
  public static boolean isSupportBigTextStyleAndAction()
  {
    return Build.VERSION.SDK_INT >= 16;
  }
  
  public static boolean isSupportDeviceDefaultLight()
  {
    return Build.VERSION.SDK_INT >= 14;
  }
  
  public static boolean isSupportKeyguardState()
  {
    return Build.VERSION.SDK_INT >= 16;
  }
  
  public static boolean isSupportNotificationBuild()
  {
    return Build.VERSION.SDK_INT >= 16;
  }
  
  public static boolean isSupportSendNotification()
  {
    return Build.VERSION.SDK_INT >= 21;
  }
}

/* Location:
 * Qualified Name:     com.meizu.cloud.pushsdk.util.MinSdkChecker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */