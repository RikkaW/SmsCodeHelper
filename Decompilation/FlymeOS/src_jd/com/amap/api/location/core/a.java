package com.amap.api.location.core;

import ahq;
import android.content.Context;
import android.util.Log;
import java.util.Arrays;
import org.json.JSONObject;

public class a
{
  static String a = "";
  private static int b = -1;
  private static String c = "1";
  
  public static int a()
  {
    return b;
  }
  
  public static String a(String paramString)
  {
    Object localObject = paramString.split("&");
    Arrays.sort((Object[])localObject);
    StringBuffer localStringBuffer = new StringBuffer();
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      localStringBuffer.append(localObject[i]);
      localStringBuffer.append("&");
      i += 1;
    }
    localObject = localStringBuffer.toString();
    if (((String)localObject).length() > 1) {
      return (String)((String)localObject).subSequence(0, ((String)localObject).length() - 1);
    }
    return paramString;
  }
  
  public static boolean a(Context paramContext)
  {
    for (;;)
    {
      try
      {
        byte[] arrayOfByte = e("resType=json&encode=UTF-8&ec=1");
        String str = c();
        paramContext = ahq.a().a(paramContext, str, arrayOfByte, "loc");
        if (paramContext != null)
        {
          bool1 = d(paramContext);
          bool2 = bool1;
        }
      }
      catch (Throwable paramContext)
      {
        boolean bool1;
        b = 0;
        paramContext.printStackTrace();
      }
      finally
      {
        if (b != 1) {
          b = 0;
        }
      }
      try
      {
        if (b != 1)
        {
          b = 0;
          bool2 = bool1;
        }
        return bool2;
      }
      finally {}
      b = 0;
      bool1 = true;
      continue;
      boolean bool2 = true;
    }
  }
  
  public static String b(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(paramString);
    String str = d.a();
    localStringBuffer.append("&ts=" + str);
    localStringBuffer.append("&scode=" + d.a(str, paramString));
    return localStringBuffer.toString();
  }
  
  public static boolean b()
  {
    return "1".equals(c);
  }
  
  public static boolean b(Context paramContext)
  {
    bool2 = true;
    try
    {
      byte[] arrayOfByte = e("resType=json&encode=UTF-8&ec=1&opertype=callamap&output=json");
      String str = d();
      paramContext = ahq.a().a(paramContext, str, arrayOfByte, "lswu");
      bool1 = bool2;
      if (paramContext != null) {
        bool1 = c(paramContext);
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        boolean bool1 = bool2;
      }
    }
    finally
    {
      try
      {
        throw paramContext;
      }
      finally {}
    }
    return bool1;
  }
  
  private static String c()
  {
    return "http://apiinit.amap.com/v3/log/init";
  }
  
  private static boolean c(String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString);
      if (paramString.has("result"))
      {
        paramString = paramString.getJSONObject("result");
        if (paramString.has("callamap"))
        {
          paramString = paramString.getJSONObject("callamap");
          if (paramString.has("callamapflag")) {
            c = paramString.getString("callamapflag");
          }
        }
      }
      return true;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  private static String d()
  {
    return "http://restapi.amap.com/v3/config/resource?";
  }
  
  private static boolean d(String paramString)
  {
    for (;;)
    {
      try
      {
        paramString = new JSONObject(paramString);
        if (paramString.has("status"))
        {
          i = paramString.getInt("status");
          if (i != 1) {
            continue;
          }
          b = 1;
        }
        if (paramString.has("info")) {
          a = paramString.getString("info");
        }
        if (b == 0) {
          Log.i("AuthFailure", a);
        }
      }
      catch (Exception paramString)
      {
        int i;
        paramString.printStackTrace();
        b = 0;
        continue;
      }
      if (b != 1) {
        break;
      }
      return true;
      if (i == 0) {
        b = 0;
      }
    }
    return false;
  }
  
  private static byte[] e(String paramString)
  {
    try
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(paramString);
      paramString = b(a(localStringBuffer.toString())).toString().getBytes("UTF-8");
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.core.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */