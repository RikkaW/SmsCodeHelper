package com.amap.api.services.geocoder;

import com.amap.api.services.core.LatLonPoint;

public class RegeocodeQuery
{
  private LatLonPoint a;
  private float b;
  private String c = "autonavi";
  
  public RegeocodeQuery(LatLonPoint paramLatLonPoint, float paramFloat, String paramString)
  {
    a = paramLatLonPoint;
    b = paramFloat;
    setLatLonType(paramString);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      paramObject = (RegeocodeQuery)paramObject;
      if (c == null)
      {
        if (c != null) {
          return false;
        }
      }
      else if (!c.equals(c)) {
        return false;
      }
      if (a == null)
      {
        if (a != null) {
          return false;
        }
      }
      else if (!a.equals(a)) {
        return false;
      }
    } while (Float.floatToIntBits(b) == Float.floatToIntBits(b));
    return false;
  }
  
  public String getLatLonType()
  {
    return c;
  }
  
  public LatLonPoint getPoint()
  {
    return a;
  }
  
  public float getRadius()
  {
    return b;
  }
  
  public int hashCode()
  {
    int j = 0;
    int i;
    if (c == null)
    {
      i = 0;
      if (a != null) {
        break label50;
      }
    }
    for (;;)
    {
      return ((i + 31) * 31 + j) * 31 + Float.floatToIntBits(b);
      i = c.hashCode();
      break;
      label50:
      j = a.hashCode();
    }
  }
  
  public void setLatLonType(String paramString)
  {
    if ((paramString != null) && ((paramString.equals("autonavi")) || (paramString.equals("gps")))) {
      c = paramString;
    }
  }
  
  public void setPoint(LatLonPoint paramLatLonPoint)
  {
    a = paramLatLonPoint;
  }
  
  public void setRadius(float paramFloat)
  {
    b = paramFloat;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.geocoder.RegeocodeQuery
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */