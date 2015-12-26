package cn.com.xy.sms.sdk.util;

import android.content.Context;
import android.content.pm.PackageManager;

public final class a
{
  public static String a(int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 1;
    for (;;)
    {
      if (i >= 5) {
        return localStringBuffer.toString();
      }
      localStringBuffer.append(DuoquUtils.getCode(i, paramInt));
      i += 1;
    }
  }
  
  public static String a(String paramString)
  {
    try
    {
      StringBuffer localStringBuffer = new StringBuffer();
      int i = 0;
      for (;;)
      {
        if (i >= 4) {
          return localStringBuffer.toString();
        }
        localStringBuffer.append(paramString.substring(i, i + 1));
        localStringBuffer.append(paramString.substring(i + 4, i + 5));
        localStringBuffer.append(paramString.substring(i + 8, i + 9));
        localStringBuffer.append(paramString.substring(i + 12, i + 13));
        i += 1;
      }
      return "";
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    try
    {
      paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
      return true;
    }
    catch (Throwable paramContext) {}
    return false;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */