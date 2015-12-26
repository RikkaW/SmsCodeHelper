package com.amap.api.location;

import com.amap.api.location.core.AMapLocException;
import java.util.List;

public class AMapLocalWeatherForecast
{
  private String a;
  private List<AMapLocalDayWeatherForecast> b;
  private AMapLocException c;
  
  void a(AMapLocException paramAMapLocException)
  {
    c = paramAMapLocException;
  }
  
  void a(String paramString)
  {
    a = paramString;
  }
  
  void a(List<AMapLocalDayWeatherForecast> paramList)
  {
    b = paramList;
  }
  
  public AMapLocException getAMapException()
  {
    return c;
  }
  
  public String getReportTime()
  {
    return a;
  }
  
  public List<AMapLocalDayWeatherForecast> getWeatherForecast()
  {
    return b;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.AMapLocalWeatherForecast
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */