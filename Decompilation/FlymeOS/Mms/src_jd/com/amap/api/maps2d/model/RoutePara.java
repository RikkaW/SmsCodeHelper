package com.amap.api.maps2d.model;

public class RoutePara
{
  private int a = 0;
  private int b = 0;
  private LatLng c;
  private LatLng d;
  private String e;
  private String f;
  
  public int getDrivingRouteStyle()
  {
    return a;
  }
  
  public String getEndName()
  {
    return f;
  }
  
  public LatLng getEndPoint()
  {
    return d;
  }
  
  public String getStartName()
  {
    return e;
  }
  
  public LatLng getStartPoint()
  {
    return c;
  }
  
  public int getTransitRouteStyle()
  {
    return b;
  }
  
  public void setDrivingRouteStyle(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < 9)) {
      a = paramInt;
    }
  }
  
  public void setEndName(String paramString)
  {
    f = paramString;
  }
  
  public void setEndPoint(LatLng paramLatLng)
  {
    d = paramLatLng;
  }
  
  public void setStartName(String paramString)
  {
    e = paramString;
  }
  
  public void setStartPoint(LatLng paramLatLng)
  {
    c = paramLatLng;
  }
  
  public void setTransitRouteStyle(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < 6)) {
      b = paramInt;
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.RoutePara
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */