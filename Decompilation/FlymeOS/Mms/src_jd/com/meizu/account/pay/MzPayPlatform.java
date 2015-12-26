package com.meizu.account.pay;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class MzPayPlatform
{
  private static final int getServiceVersion(Context paramContext)
  {
    int i = 0;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.meizu.account", 0);
      if (paramContext != null) {
        i = versionCode;
      }
      return i;
    }
    catch (Exception paramContext) {}
    return 0;
  }
  
  public static final boolean inputerAvailable(Context paramContext)
  {
    return getServiceVersion(paramContext) >= 122;
  }
  
  public static final void pay(Activity paramActivity, OutTradeOrderInfo paramOutTradeOrderInfo, PayListener paramPayListener)
  {
    l.a(paramActivity, paramOutTradeOrderInfo, paramPayListener);
  }
  
  public static final void pay(Activity paramActivity, OutTradeOrderInfo paramOutTradeOrderInfo, PayListener paramPayListener, String paramString1, String paramString2)
  {
    l.a(paramActivity, paramOutTradeOrderInfo, paramPayListener, paramString1, paramString2);
  }
  
  public static final void payCustom(Activity paramActivity, String paramString1, double paramDouble, String paramString2, ICustomBusinessHandler paramICustomBusinessHandler, PayListener paramPayListener)
  {
    l.a(paramActivity, paramString1, paramDouble, paramString2, paramICustomBusinessHandler, paramPayListener);
  }
  
  public static final boolean serviceAvailable(Context paramContext)
  {
    return getServiceVersion(paramContext) >= 100;
  }
  
  public static final void setCustomTheme(int paramInt1, int paramInt2)
  {
    l.a(paramInt1, paramInt2);
  }
}

/* Location:
 * Qualified Name:     com.meizu.account.pay.MzPayPlatform
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */