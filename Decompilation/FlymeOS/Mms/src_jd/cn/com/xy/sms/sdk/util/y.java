package cn.com.xy.sms.sdk.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;
import java.net.URLEncoder;

public final class y
{
  private static String a = "com.eg.android.AlipayGphone";
  private static String b = "alipays://platformapi/startapp?";
  private static String c = "&";
  private static String d = "appId=10000003";
  private static String e = "appId=09999999";
  private static String f = "XIAOYUAN";
  
  private static void a(StringBuffer paramStringBuffer, String paramString1, String paramString2)
  {
    if ((paramString1.length() > 0) && (paramString2 != null) && (paramString2.length() > 0))
    {
      paramStringBuffer.append("&");
      paramStringBuffer.append(paramString1);
      paramStringBuffer.append("=");
      paramStringBuffer.append(paramString2);
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if (!b(paramContext, "com.eg.android.AlipayGphone")) {
      return false;
    }
    try
    {
      StringBuffer localStringBuffer = new StringBuffer("alipays://platformapi/startapp?appId=10000003");
      a(localStringBuffer, "sourceId", "XIAOYUAN");
      a(localStringBuffer, "actionType", "recharge");
      a(localStringBuffer, "mobileNo", paramString);
      c(paramContext, localStringBuffer.toString());
      return true;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
      try
      {
        Toast.makeText(paramContext, "处理失败.", 1).show();
        return false;
      }
      catch (Throwable paramContext)
      {
        paramString.printStackTrace();
      }
    }
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean)
  {
    if (!b(paramContext, "com.eg.android.AlipayGphone")) {
      return false;
    }
    try
    {
      StringBuffer localStringBuffer = new StringBuffer("alipays://platformapi/startapp?appId=09999999");
      a(localStringBuffer, "sourceId", "XIAOYUAN");
      a(localStringBuffer, "outTradeNo", "9999");
      a(localStringBuffer, "cardNumberType", "TAIL");
      a(localStringBuffer, "cardNumber", paramString1);
      paramString1 = paramString2;
      if (!StringUtils.isNull(paramString2)) {
        paramString1 = paramString2.replaceAll(",", "");
      }
      a(localStringBuffer, "amount", paramString1);
      a(localStringBuffer, "bankMark", paramString4);
      if ((paramString3 != null) && (paramString3.length() > 0)) {
        a(localStringBuffer, "userName", URLEncoder.encode(paramString3, "UTF-8"));
      }
      c(paramContext, localStringBuffer.toString());
      return true;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
      try
      {
        Toast.makeText(paramContext, "处理失败.", 1).show();
        return false;
      }
      catch (Throwable paramContext)
      {
        paramString1.printStackTrace();
      }
    }
    return false;
  }
  
  private static boolean b(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getPackageGids(paramString);
      return true;
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  private static void c(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setData(Uri.parse(paramString));
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.addFlags(536870912);
    localIntent.setFlags(335544320);
    paramContext.startActivity(localIntent);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.y
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */