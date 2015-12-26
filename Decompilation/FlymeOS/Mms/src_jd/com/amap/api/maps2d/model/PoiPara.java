package com.amap.api.maps2d.model;

public class PoiPara
{
  private LatLng a;
  private String b;
  
  public LatLng getCenter()
  {
    return a;
  }
  
  public String getKeywords()
  {
    return b;
  }
  
  public void setCenter(LatLng paramLatLng)
  {
    a = paramLatLng;
  }
  
  public void setKeywords(String paramString)
  {
    b = paramString;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.PoiPara
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */