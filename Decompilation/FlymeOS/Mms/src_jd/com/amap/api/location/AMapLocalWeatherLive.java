package com.amap.api.location;

import com.amap.api.location.core.AMapLocException;

public class AMapLocalWeatherLive
{
  private String a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private AMapLocException g;
  private String h;
  private String i;
  private String j;
  
  void a(AMapLocException paramAMapLocException)
  {
    g = paramAMapLocException;
  }
  
  void a(String paramString)
  {
    a = paramString;
  }
  
  void b(String paramString)
  {
    b = paramString;
  }
  
  void c(String paramString)
  {
    c = paramString;
  }
  
  void d(String paramString)
  {
    d = paramString;
  }
  
  void e(String paramString)
  {
    e = paramString;
  }
  
  void f(String paramString)
  {
    f = paramString;
  }
  
  public AMapLocException getAMapException()
  {
    return g;
  }
  
  public String getCity()
  {
    return h;
  }
  
  public String getCityCode()
  {
    return j;
  }
  
  public String getHumidity()
  {
    return e;
  }
  
  public String getProvince()
  {
    return i;
  }
  
  public String getReportTime()
  {
    return f;
  }
  
  public String getTemperature()
  {
    return b;
  }
  
  public String getWeather()
  {
    return a;
  }
  
  public String getWindDir()
  {
    return c;
  }
  
  public String getWindPower()
  {
    return d;
  }
  
  public void setCity(String paramString)
  {
    h = paramString;
  }
  
  public void setCityCode(String paramString)
  {
    j = paramString;
  }
  
  public void setProvince(String paramString)
  {
    i = paramString;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.AMapLocalWeatherLive
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */