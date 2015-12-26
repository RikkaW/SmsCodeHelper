package com.amap.api.location;

import android.location.Location;
import com.amap.api.location.core.AMapLocException;

public class AMapLocation
  extends Location
{
  private String a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g;
  private String h;
  private String i;
  private String j;
  private String k;
  private String l;
  private AMapLocException m = new AMapLocException();
  
  public AMapLocation(Location paramLocation)
  {
    super(paramLocation);
  }
  
  public AMapLocation(String paramString)
  {
    super(paramString);
  }
  
  void a(String paramString)
  {
    h = paramString;
  }
  
  public AMapLocException getAMapException()
  {
    return m;
  }
  
  public String getAdCode()
  {
    return e;
  }
  
  public String getAddress()
  {
    return i;
  }
  
  public String getCity()
  {
    return b;
  }
  
  public String getCityCode()
  {
    return d;
  }
  
  public String getCountry()
  {
    return j;
  }
  
  public String getDistrict()
  {
    return c;
  }
  
  public String getFloor()
  {
    return g;
  }
  
  public String getPoiId()
  {
    return f;
  }
  
  public String getPoiName()
  {
    return l;
  }
  
  public String getProvince()
  {
    return a;
  }
  
  public String getRoad()
  {
    return k;
  }
  
  public String getStreet()
  {
    return h;
  }
  
  public void setAMapException(AMapLocException paramAMapLocException)
  {
    m = paramAMapLocException;
  }
  
  public void setAdCode(String paramString)
  {
    e = paramString;
  }
  
  public void setAddress(String paramString)
  {
    i = paramString;
  }
  
  public void setCity(String paramString)
  {
    b = paramString;
  }
  
  public void setCityCode(String paramString)
  {
    d = paramString;
  }
  
  public void setCountry(String paramString)
  {
    j = paramString;
  }
  
  public void setDistrict(String paramString)
  {
    c = paramString;
  }
  
  public void setFloor(String paramString)
  {
    g = paramString;
  }
  
  public void setPoiId(String paramString)
  {
    f = paramString;
  }
  
  public void setPoiName(String paramString)
  {
    l = paramString;
  }
  
  public void setProvince(String paramString)
  {
    a = paramString;
  }
  
  public void setRoad(String paramString)
  {
    k = paramString;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.AMapLocation
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */