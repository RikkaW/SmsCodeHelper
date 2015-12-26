package com.amap.api.location;

import ahq;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.location.core.AMapLocException;
import com.amap.api.location.core.c;
import com.amap.api.location.core.d;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class b
  implements AMapLocationListener
{
  a a = null;
  AMapLocalWeatherListener b;
  a c;
  List<Integer> d = new ArrayList();
  List<AMapLocalWeatherListener> e = new ArrayList();
  private Context f;
  private int g;
  private AMapLocalWeatherListener h;
  private boolean i = false;
  
  public b(a parama, Context paramContext)
  {
    f = paramContext;
    c = parama;
    a = new a(this, paramContext.getMainLooper());
  }
  
  private AMapLocalWeatherLive a(String paramString, AMapLocation paramAMapLocation)
  {
    AMapLocalWeatherLive localAMapLocalWeatherLive = new AMapLocalWeatherLive();
    try
    {
      d.a(paramString);
    }
    catch (AMapLocException localAMapLocException)
    {
      for (;;)
      {
        try
        {
          paramString = new JSONObject(paramString).getJSONArray("lives");
          if ((paramString != null) && (paramString.length() > 0))
          {
            Object localObject = (JSONObject)paramString.get(0);
            paramString = a((JSONObject)localObject, "weather");
            String str1 = a((JSONObject)localObject, "temperature");
            String str2 = a((JSONObject)localObject, "winddirection");
            String str3 = a((JSONObject)localObject, "windpower");
            String str4 = a((JSONObject)localObject, "humidity");
            localObject = a((JSONObject)localObject, "reporttime");
            localAMapLocalWeatherLive.a(paramString);
            localAMapLocalWeatherLive.f((String)localObject);
            localAMapLocalWeatherLive.e(str4);
            localAMapLocalWeatherLive.b(str1);
            localAMapLocalWeatherLive.c(str2);
            localAMapLocalWeatherLive.d(str3);
            localAMapLocalWeatherLive.setCity(paramAMapLocation.getCity());
            localAMapLocalWeatherLive.setCityCode(paramAMapLocation.getCityCode());
            localAMapLocalWeatherLive.setProvince(paramAMapLocation.getProvince());
          }
          return localAMapLocalWeatherLive;
        }
        catch (Exception paramString)
        {
          paramString.printStackTrace();
        }
        localAMapLocException = localAMapLocException;
        localAMapLocalWeatherLive.a(localAMapLocException);
      }
    }
    return localAMapLocalWeatherLive;
  }
  
  private String a()
  {
    return "http://restapi.amap.com/v3/weather/weatherInfo?";
  }
  
  private byte[] a(AMapLocation paramAMapLocation, String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("output=json&ec=1").append("&extensions=" + paramString).append("&city=").append(paramAMapLocation.getAdCode());
    localStringBuffer.append("&key=" + c.a());
    return com.amap.api.location.core.a.b(com.amap.api.location.core.a.a(localStringBuffer.toString())).getBytes("utf-8");
  }
  
  private AMapLocalWeatherForecast b(String paramString, AMapLocation paramAMapLocation)
  {
    AMapLocalWeatherForecast localAMapLocalWeatherForecast = new AMapLocalWeatherForecast();
    try
    {
      d.a(paramString);
      paramString = new JSONObject(paramString).getJSONArray("forecasts");
      if ((paramString != null) && (paramString.length() > 0))
      {
        paramString = (JSONObject)paramString.get(0);
        localAMapLocalWeatherForecast.a(a(paramString, "reporttime"));
        paramString = paramString.getJSONArray("casts");
        if ((paramString != null) && (paramString.length() > 0))
        {
          ArrayList localArrayList = new ArrayList();
          int j = 0;
          while (j < paramString.length())
          {
            AMapLocalDayWeatherForecast localAMapLocalDayWeatherForecast = new AMapLocalDayWeatherForecast();
            Object localObject = (JSONObject)paramString.get(j);
            String str1 = a((JSONObject)localObject, "date");
            String str2 = a((JSONObject)localObject, "week");
            String str3 = a((JSONObject)localObject, "dayweather");
            String str4 = a((JSONObject)localObject, "nightweather");
            String str5 = a((JSONObject)localObject, "daytemp");
            String str6 = a((JSONObject)localObject, "nighttemp");
            String str7 = a((JSONObject)localObject, "daywind");
            String str8 = a((JSONObject)localObject, "nightwind");
            String str9 = a((JSONObject)localObject, "daypower");
            localObject = a((JSONObject)localObject, "nightpower");
            localAMapLocalDayWeatherForecast.a(str1);
            localAMapLocalDayWeatherForecast.b(str2);
            localAMapLocalDayWeatherForecast.c(str3);
            localAMapLocalDayWeatherForecast.d(str4);
            localAMapLocalDayWeatherForecast.e(str5);
            localAMapLocalDayWeatherForecast.f(str6);
            localAMapLocalDayWeatherForecast.g(str7);
            localAMapLocalDayWeatherForecast.h(str8);
            localAMapLocalDayWeatherForecast.i(str9);
            localAMapLocalDayWeatherForecast.j((String)localObject);
            localAMapLocalDayWeatherForecast.setCity(paramAMapLocation.getCity());
            localAMapLocalDayWeatherForecast.setCityCode(paramAMapLocation.getCityCode());
            localAMapLocalDayWeatherForecast.setProvince(paramAMapLocation.getProvince());
            localArrayList.add(localAMapLocalDayWeatherForecast);
            j += 1;
          }
        }
      }
    }
    catch (AMapLocException localAMapLocException)
    {
      for (;;)
      {
        localAMapLocalWeatherForecast.a(localAMapLocException);
        localAMapLocException.printStackTrace();
      }
      localAMapLocalWeatherForecast.a(localAMapLocException);
    }
    return localAMapLocalWeatherForecast;
  }
  
  protected String a(JSONObject paramJSONObject, String paramString)
  {
    if (paramJSONObject == null) {}
    while ((!paramJSONObject.has(paramString)) || (paramJSONObject.getString(paramString).equals("[]"))) {
      return "";
    }
    return paramJSONObject.optString(paramString);
  }
  
  void a(int paramInt, AMapLocalWeatherListener paramAMapLocalWeatherListener, AMapLocation paramAMapLocation)
  {
    try
    {
      g = paramInt;
      h = paramAMapLocalWeatherListener;
      if (paramAMapLocation == null)
      {
        if (d != null) {
          d.add(Integer.valueOf(g));
        }
        if (e != null) {
          e.add(h);
        }
        if (i) {
          return;
        }
        i = true;
        c.a(-1L, 10.0F, this, "lbs", true);
        return;
      }
    }
    catch (Throwable paramAMapLocalWeatherListener)
    {
      paramAMapLocalWeatherListener.printStackTrace();
      return;
    }
    if (paramInt == 1) {
      a(paramAMapLocation, "base", paramAMapLocalWeatherListener);
    }
    if (paramInt == 2) {
      a(paramAMapLocation, "all", paramAMapLocalWeatherListener);
    }
  }
  
  void a(AMapLocation paramAMapLocation, String paramString, AMapLocalWeatherListener paramAMapLocalWeatherListener)
  {
    b = paramAMapLocalWeatherListener;
    if (paramAMapLocation == null) {}
    Object localObject2;
    Object localObject1;
    do
    {
      return;
      localObject2 = a(paramAMapLocation, paramString);
      Object localObject3 = a();
      paramAMapLocalWeatherListener = new AMapLocException();
      localObject1 = null;
      try
      {
        localObject2 = ahq.a().a(f, (String)localObject3, (byte[])localObject2, "sea");
        localObject1 = localObject2;
      }
      catch (AMapLocException paramAMapLocalWeatherListener)
      {
        for (;;) {}
        continue;
      }
      if (!"base".equals(paramString)) {
        break label244;
      }
      if (localObject1 == null) {
        break;
      }
      localObject2 = a((String)localObject1, paramAMapLocation);
      ((AMapLocalWeatherLive)localObject2).a(paramAMapLocalWeatherListener);
      ((AMapLocalWeatherLive)localObject2).setCity(paramAMapLocation.getCity());
      ((AMapLocalWeatherLive)localObject2).setCityCode(paramAMapLocation.getCityCode());
      ((AMapLocalWeatherLive)localObject2).setProvince(paramAMapLocation.getProvince());
      localObject3 = Message.obtain();
      what = 1;
      obj = localObject2;
      a.sendMessage((Message)localObject3);
    } while (!"all".equals(paramString));
    if (localObject1 != null) {
      paramAMapLocation = b((String)localObject1, paramAMapLocation);
    }
    for (;;)
    {
      paramAMapLocation.a(paramAMapLocalWeatherListener);
      paramString = Message.obtain();
      what = 2;
      obj = paramAMapLocation;
      a.sendMessage(paramString);
      return;
      localObject2 = new AMapLocalWeatherLive();
      paramAMapLocalWeatherListener = new AMapLocException("http连接失败 - ConnectionException");
      break;
      paramAMapLocation = new AMapLocalWeatherForecast();
      paramAMapLocalWeatherListener = new AMapLocException("http连接失败 - ConnectionException");
    }
  }
  
  public void onLocationChanged(Location paramLocation) {}
  
  public void onLocationChanged(AMapLocation paramAMapLocation)
  {
    if (paramAMapLocation != null) {}
    try
    {
      Object localObject;
      if ((paramAMapLocation.getAMapException() != null) && (paramAMapLocation.getAMapException().getErrorCode() == 0) && (paramAMapLocation.getAdCode() != null) && (paramAMapLocation.getAdCode().length() > 0))
      {
        c.a(this);
        localObject = Message.obtain();
        what = 3;
        obj = paramAMapLocation;
        a.sendMessage((Message)localObject);
      }
      for (;;)
      {
        i = false;
        return;
        c.a(this);
        paramAMapLocation = Message.obtain();
        localObject = new AMapLocException("定位失败无法获取城市信息");
        what = 4;
        obj = localObject;
        a.sendMessage(paramAMapLocation);
      }
      return;
    }
    catch (Throwable paramAMapLocation)
    {
      paramAMapLocation.printStackTrace();
    }
  }
  
  public void onProviderDisabled(String paramString) {}
  
  public void onProviderEnabled(String paramString) {}
  
  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
  
  static class a
    extends Handler
  {
    private WeakReference<b> a;
    
    a(b paramb, Looper paramLooper)
    {
      super();
      try
      {
        a = new WeakReference(paramb);
        return;
      }
      catch (Throwable paramb)
      {
        paramb.printStackTrace();
      }
    }
    
    public void handleMessage(Message paramMessage)
    {
      b localb;
      try
      {
        super.handleMessage(paramMessage);
        localb = (b)a.get();
        switch (what)
        {
        case 1: 
          if (b == null) {
            return;
          }
          b.onWeatherLiveSearched((AMapLocalWeatherLive)obj);
          return;
        }
      }
      catch (Throwable paramMessage)
      {
        paramMessage.printStackTrace();
        return;
      }
      if (b != null)
      {
        b.onWeatherForecaseSearched((AMapLocalWeatherForecast)obj);
        return;
        try
        {
          new b.a.1(this, localb, (AMapLocation)obj).start();
          return;
        }
        catch (Throwable paramMessage)
        {
          paramMessage.printStackTrace();
          return;
        }
        for (;;)
        {
          try
          {
            paramMessage = (AMapLocException)obj;
            int i = 0;
            try
            {
              int j = d.size();
              if (i >= j) {
                break;
              }
            }
            catch (Throwable paramMessage)
            {
              Object localObject;
              paramMessage.printStackTrace();
              return;
            }
          }
          catch (Throwable paramMessage)
          {
            paramMessage.printStackTrace();
            return;
          }
          try
          {
            if (((Integer)d.get(i)).intValue() == 1)
            {
              localObject = new AMapLocalWeatherLive();
              ((AMapLocalWeatherLive)localObject).a(paramMessage);
              ((AMapLocalWeatherListener)e.get(i)).onWeatherLiveSearched((AMapLocalWeatherLive)localObject);
            }
            if (((Integer)d.get(i)).intValue() == 2)
            {
              localObject = new AMapLocalWeatherForecast();
              ((AMapLocalWeatherForecast)localObject).a(paramMessage);
              ((AMapLocalWeatherListener)e.get(i)).onWeatherForecaseSearched((AMapLocalWeatherForecast)localObject);
            }
          }
          catch (Throwable localThrowable)
          {
            continue;
          }
          i += 1;
        }
        d.clear();
        e.clear();
        return;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */