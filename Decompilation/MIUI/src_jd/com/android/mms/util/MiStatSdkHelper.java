package com.android.mms.util;

import android.app.Activity;
import android.content.Context;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import miui.os.Build;

public class MiStatSdkHelper
{
  private static final boolean sEnable;
  
  static
  {
    if ((!Build.IS_INTERNATIONAL_BUILD) && (!Build.IS_CTA_BUILD)) {}
    for (boolean bool = true;; bool = false)
    {
      sEnable = bool;
      return;
    }
  }
  
  public static void recordBottomMenuClick(String paramString1, String paramString2)
  {
    if (!sEnable) {
      return;
    }
    try
    {
      StringBuilder localStringBuilder = new StringBuilder("menu_click");
      localStringBuilder.append("_");
      localStringBuilder.append(paramString1);
      localStringBuilder.append("_");
      localStringBuilder.append(paramString2);
      MiStatInterface.recordCountEvent("sp_menu_category", localStringBuilder.toString());
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static void recordBottomMenuShown(String paramString)
  {
    if (!sEnable) {
      return;
    }
    try
    {
      StringBuilder localStringBuilder = new StringBuilder("menu_shown");
      localStringBuilder.append("_");
      localStringBuilder.append(paramString);
      MiStatInterface.recordCountEvent("sp_menu_category", localStringBuilder.toString());
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void recordNetworkAllowRecommend(String paramString)
  {
    if (!sEnable) {
      return;
    }
    try
    {
      MiStatInterface.recordCountEvent("network_recommend", paramString);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void recordPageEnd()
  {
    if (!sEnable) {
      return;
    }
    try
    {
      MiStatInterface.recordPageEnd();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public static void recordPageStart(Activity paramActivity, String paramString)
  {
    if (!sEnable) {
      return;
    }
    try
    {
      MiStatInterface.recordPageStart(paramActivity, paramString);
      return;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public static void recordPushmessageStatus(int paramInt)
  {
    if (!sEnable) {
      return;
    }
    try
    {
      StringBuilder localStringBuilder = new StringBuilder("pushmessage_status");
      localStringBuilder.append(paramInt);
      MiStatInterface.recordCountEvent("understand_category", localStringBuilder.toString());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public static void recordSearch(String paramString)
  {
    if (!sEnable) {
      return;
    }
    try
    {
      MiStatInterface.recordCountEvent("search", paramString);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void recordStringPropertyEvent(String paramString1, String paramString2, String paramString3)
  {
    if (!sEnable) {
      return;
    }
    try
    {
      MiStatInterface.recordStringPropertyEvent(paramString1, paramString2, paramString3);
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static void recordUnderstandButtonClick(int paramInt1, int paramInt2)
  {
    if (!sEnable) {
      return;
    }
    try
    {
      StringBuilder localStringBuilder = new StringBuilder("button_click");
      localStringBuilder.append(paramInt1);
      localStringBuilder.append("_");
      localStringBuilder.append(paramInt2);
      MiStatInterface.recordCountEvent("understand_category", localStringBuilder.toString());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public static void recordUnderstandButtonShown(int paramInt)
  {
    if (!sEnable) {
      return;
    }
    try
    {
      StringBuilder localStringBuilder = new StringBuilder("button_shown");
      localStringBuilder.append(paramInt);
      MiStatInterface.recordCountEvent("understand_category", localStringBuilder.toString());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public static void recourdUpdateEvent(String paramString)
  {
    if (!sEnable) {
      return;
    }
    try
    {
      StringBuilder localStringBuilder = new StringBuilder("update_from_");
      localStringBuilder.append(paramString);
      MiStatInterface.recordCountEvent("understand_category", localStringBuilder.toString());
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void start(Context paramContext)
  {
    if (!sEnable) {
      return;
    }
    try
    {
      MiStatInterface.initialize(paramContext, "2882303761517322150", "5921732233150", "com.android.mms");
      MiStatInterface.setUploadPolicy(1, 0L);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.MiStatSdkHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */