package com.amap.api.mapcore2d;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class da
{
  private static String a = "";
  private static String b = null;
  private static String c = "";
  private static String d;
  private static String e = null;
  
  public static String a(Context paramContext)
  {
    try
    {
      if (!"".equals(a)) {
        return a;
      }
      PackageManager localPackageManager = paramContext.getPackageManager();
      a = (String)localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0));
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "AppInfo", "getApplicationName");
        paramContext.printStackTrace();
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "AppInfo", "getApplicationName");
        paramContext.printStackTrace();
      }
    }
    return a;
  }
  
  static void a(String paramString)
  {
    d = paramString;
  }
  
  public static String b(Context paramContext)
  {
    try
    {
      if ((b != null) && (!"".equals(b))) {
        return b;
      }
      b = paramContext.getApplicationContext().getPackageName();
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "AppInfo", "getPackageName");
        paramContext.printStackTrace();
      }
    }
    return b;
  }
  
  public static String c(Context paramContext)
  {
    try
    {
      if (!"".equals(c)) {
        return c;
      }
      c = getPackageManagergetPackageInfogetPackageName0versionName;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "AppInfo", "getApplicationVersion");
        paramContext.printStackTrace();
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "AppInfo", "getApplicationVersion");
        paramContext.printStackTrace();
      }
    }
    return c;
  }
  
  public static String d(Context paramContext)
  {
    int i = 0;
    try
    {
      if ((e != null) && (!"".equals(e))) {
        return e;
      }
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64);
      byte[] arrayOfByte = signatures[0].toByteArray();
      arrayOfByte = MessageDigest.getInstance("SHA1").digest(arrayOfByte);
      StringBuffer localStringBuffer = new StringBuffer();
      while (i < arrayOfByte.length)
      {
        String str = Integer.toHexString(arrayOfByte[i] & 0xFF).toUpperCase(Locale.US);
        if (str.length() == 1) {
          localStringBuffer.append("0");
        }
        localStringBuffer.append(str);
        localStringBuffer.append(":");
        i += 1;
      }
      localStringBuffer.append(packageName);
      e = localStringBuffer.toString();
      paramContext = e;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      ed.a(paramContext, "AppInfo", "getSHA1AndPackage");
      paramContext.printStackTrace();
      return e;
    }
    catch (NoSuchAlgorithmException paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "AppInfo", "getSHA1AndPackage");
        paramContext.printStackTrace();
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "AppInfo", "getSHA1AndPackage");
        paramContext.printStackTrace();
      }
    }
  }
  
  public static String e(Context paramContext)
  {
    try
    {
      paramContext = g(paramContext);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
      return d;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public static String f(Context paramContext)
  {
    try
    {
      paramContext = g(paramContext);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      ed.a(paramContext, "AppInfo", "getKey");
      paramContext.printStackTrace();
      return d;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        ed.a(paramContext, "AppInfo", "getKey");
        paramContext.printStackTrace();
      }
    }
  }
  
  private static String g(Context paramContext)
  {
    if ((d == null) || (d.equals(""))) {
      d = getPackageManagergetApplicationInfogetPackageName128metaData.getString("com.amap.api.v2.apikey");
    }
    return d;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.da
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */