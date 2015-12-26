package com.amap.api.location.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build.VERSION;
import com.amap.api.location.AMapLocation;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

public class d
{
  public static String a = "";
  public static String b = "";
  static int c = 2048;
  static String d = null;
  private static String e = null;
  private static SharedPreferences f = null;
  private static SharedPreferences.Editor g = null;
  private static Method h;
  
  public static String a()
  {
    try
    {
      String str = String.valueOf(System.currentTimeMillis());
      int i = str.length();
      str = str.substring(0, i - 2) + "1" + str.substring(i - 1);
      return str;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public static String a(String paramString1, String paramString2)
  {
    try
    {
      if ((d == null) || (d.length() == 0)) {
        d = c.a(null).i();
      }
      paramString1 = g.a(d + ":" + paramString1.substring(0, paramString1.length() - 3) + ":" + paramString2);
      return paramString1;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public static void a(Context paramContext, AMapLocation paramAMapLocation)
  {
    try
    {
      if (f == null) {
        f = paramContext.getSharedPreferences("last_known_location", 0);
      }
      if (g == null) {
        g = f.edit();
      }
      if (e == null) {
        e = e.a("MD5", c.b());
      }
      paramContext = e.d(String.valueOf(paramAMapLocation.getLatitude()).getBytes(), e);
      g.putString("a", paramContext);
      paramContext = e.d(String.valueOf(paramAMapLocation.getLongitude()).getBytes(), e);
      g.putString("b", paramContext);
      paramContext = paramAMapLocation.getProvince();
      if ((paramContext != null) && (paramContext.length() > 0))
      {
        paramContext = e.d(paramContext.getBytes(), e);
        g.putString("c", paramContext);
      }
      paramContext = paramAMapLocation.getCity();
      if ((paramContext != null) && (paramContext.length() > 0))
      {
        paramContext = e.d(paramContext.getBytes(), e);
        g.putString("d", paramContext);
      }
      paramContext = paramAMapLocation.getDistrict();
      if ((paramContext != null) && (paramContext.length() > 0))
      {
        paramContext = e.d(paramContext.getBytes(), e);
        g.putString("e", paramContext);
      }
      paramContext = paramAMapLocation.getCityCode();
      if ((paramContext != null) && (paramContext.length() > 0))
      {
        paramContext = e.d(paramContext.getBytes(), e);
        g.putString("f", paramContext);
      }
      paramContext = paramAMapLocation.getAdCode();
      if ((paramContext != null) && (paramContext.length() > 0))
      {
        paramContext = e.d(paramContext.getBytes(), e);
        g.putString("g", paramContext);
      }
      paramContext = paramAMapLocation.getAccuracy() + "";
      if ((paramContext != null) && (paramContext.length() > 0))
      {
        paramContext = e.d(paramContext.getBytes(), e);
        g.putString("h", paramContext);
      }
      paramContext = paramAMapLocation.getTime() + "";
      if ((paramContext != null) && (paramContext.length() > 0))
      {
        paramContext = e.d(paramContext.getBytes(), e);
        g.putString("i", paramContext);
      }
      paramContext = paramAMapLocation.getAddress() + "";
      if ((paramContext != null) && (paramContext.length() > 0))
      {
        paramContext = e.d(paramContext.getBytes(), e);
        g.putString("j", paramContext);
      }
      paramContext = paramAMapLocation.getRoad() + "";
      if ((paramContext != null) && (paramContext.length() > 0))
      {
        paramContext = e.d(paramContext.getBytes(), e);
        g.putString("k", paramContext);
      }
      paramContext = paramAMapLocation.getPoiId() + "";
      if ((paramContext != null) && (paramContext.length() > 0))
      {
        paramContext = e.d(paramContext.getBytes(), e);
        g.putString("l", paramContext);
      }
      paramContext = paramAMapLocation.getPoiName() + "";
      if ((paramContext != null) && (paramContext.length() > 0))
      {
        paramContext = e.d(paramContext.getBytes(), e);
        g.putString("m", paramContext);
      }
      a(g);
      return;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private static void a(SharedPreferences.Editor paramEditor)
  {
    if (paramEditor == null) {
      return;
    }
    if (Build.VERSION.SDK_INT >= 9) {
      try
      {
        if (h == null) {
          h = SharedPreferences.Editor.class.getDeclaredMethod("apply", new Class[0]);
        }
        h.invoke(paramEditor, new Object[0]);
        return;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        paramEditor.commit();
        return;
      }
    }
    paramEditor.commit();
  }
  
  public static void a(String paramString)
  {
    try
    {
      Object localObject = new JSONObject(paramString);
      if (((JSONObject)localObject).has("status"))
      {
        if (!((JSONObject)localObject).has("info")) {
          return;
        }
        paramString = ((JSONObject)localObject).getString("status");
        localObject = ((JSONObject)localObject).getString("info");
        if ((!paramString.equals("1")) && (paramString.equals("0")))
        {
          if ((((String)localObject).equals("INVALID_USER_KEY")) || (((String)localObject).equals("INSUFFICIENT_PRIVILEGES")) || (((String)localObject).equals("USERKEY_PLAT_NOMATCH")) || (((String)localObject).equals("INVALID_USER_SCODE"))) {
            throw new AMapLocException("key鉴权失败");
          }
          if ((((String)localObject).equals("SERVICE_NOT_EXIST")) || (((String)localObject).equals("SERVICE_RESPONSE_ERROR")) || (((String)localObject).equals("OVER_QUOTA")) || (((String)localObject).equals("UNKNOWN_ERROR"))) {
            throw new AMapLocException("未知的错误");
          }
          if (((String)localObject).equals("INVALID_PARAMS")) {
            throw new AMapLocException("无效的参数 - IllegalArgumentException");
          }
        }
      }
      return;
    }
    catch (JSONException paramString) {}
  }
  
  public static boolean a(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    try
    {
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext == null) {
        return false;
      }
      paramContext = paramContext.getActiveNetworkInfo();
      if (paramContext == null) {
        return false;
      }
      paramContext = paramContext.getState();
      NetworkInfo.State localState;
      if ((paramContext != null) && (paramContext != NetworkInfo.State.DISCONNECTED)) {
        localState = NetworkInfo.State.DISCONNECTING;
      }
      return paramContext != localState;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static AMapLocation b(Context paramContext)
  {
    try
    {
      if (e == null) {
        e = e.a("MD5", paramContext.getApplicationContext().getPackageName());
      }
      paramContext = paramContext.getSharedPreferences("last_known_location", 0);
      AMapLocation localAMapLocation = new AMapLocation("");
      localAMapLocation.setProvider("lbs");
      double d1 = Double.parseDouble(e.b(paramContext.getString("a", ""), e));
      double d2 = Double.parseDouble(e.b(paramContext.getString("b", ""), e));
      localAMapLocation.setLatitude(d1);
      localAMapLocation.setLongitude(d2);
      localAMapLocation.setProvince(e.b(paramContext.getString("c", ""), e));
      localAMapLocation.setCity(e.b(paramContext.getString("d", ""), e));
      localAMapLocation.setDistrict(e.b(paramContext.getString("e", ""), e));
      localAMapLocation.setCityCode(e.b(paramContext.getString("f", ""), e));
      localAMapLocation.setAdCode(e.b(paramContext.getString("g", ""), e));
      localAMapLocation.setAccuracy(Float.parseFloat(e.b(paramContext.getString("h", ""), e)));
      localAMapLocation.setTime(Long.parseLong(e.b(paramContext.getString("i", ""), e)));
      localAMapLocation.setAddress(e.b(paramContext.getString("j", ""), e));
      localAMapLocation.setRoad(e.b(paramContext.getString("k", ""), e));
      localAMapLocation.setPoiId(e.b(paramContext.getString("l", ""), e));
      localAMapLocation.setPoiName(e.b(paramContext.getString("m", ""), e));
      return localAMapLocation;
    }
    catch (Throwable paramContext) {}
    return null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.core.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */