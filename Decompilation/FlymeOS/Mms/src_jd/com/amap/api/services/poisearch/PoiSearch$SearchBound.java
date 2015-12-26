package com.amap.api.services.poisearch;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.d;
import java.util.List;

public class PoiSearch$SearchBound
  implements Cloneable
{
  public static final String BOUND_SHAPE = "Bound";
  public static final String ELLIPSE_SHAPE = "Ellipse";
  public static final String POLYGON_SHAPE = "Polygon";
  public static final String RECTANGLE_SHAPE = "Rectangle";
  private LatLonPoint a;
  private LatLonPoint b;
  private int c;
  private LatLonPoint d;
  private String e;
  private boolean f = true;
  private List<LatLonPoint> g;
  
  public PoiSearch$SearchBound(LatLonPoint paramLatLonPoint, int paramInt)
  {
    e = "Bound";
    c = paramInt;
    d = paramLatLonPoint;
    a(paramLatLonPoint, d.a(paramInt), d.a(paramInt));
  }
  
  public PoiSearch$SearchBound(LatLonPoint paramLatLonPoint, int paramInt, boolean paramBoolean)
  {
    e = "Bound";
    c = paramInt;
    d = paramLatLonPoint;
    a(paramLatLonPoint, d.a(paramInt), d.a(paramInt));
    f = paramBoolean;
  }
  
  public PoiSearch$SearchBound(LatLonPoint paramLatLonPoint1, LatLonPoint paramLatLonPoint2)
  {
    e = "Rectangle";
    a(paramLatLonPoint1, paramLatLonPoint2);
  }
  
  private PoiSearch$SearchBound(LatLonPoint paramLatLonPoint1, LatLonPoint paramLatLonPoint2, int paramInt, LatLonPoint paramLatLonPoint3, String paramString, List<LatLonPoint> paramList, boolean paramBoolean)
  {
    a = paramLatLonPoint1;
    b = paramLatLonPoint2;
    c = paramInt;
    d = paramLatLonPoint3;
    e = paramString;
    g = paramList;
    f = paramBoolean;
  }
  
  public PoiSearch$SearchBound(List<LatLonPoint> paramList)
  {
    e = "Polygon";
    g = paramList;
  }
  
  private void a(LatLonPoint paramLatLonPoint, double paramDouble1, double paramDouble2)
  {
    paramDouble1 /= 2.0D;
    paramDouble2 /= 2.0D;
    double d1 = paramLatLonPoint.getLatitude();
    double d2 = paramLatLonPoint.getLongitude();
    a(new LatLonPoint(d1 - paramDouble1, d2 - paramDouble2), new LatLonPoint(paramDouble1 + d1, paramDouble2 + d2));
  }
  
  private void a(LatLonPoint paramLatLonPoint1, LatLonPoint paramLatLonPoint2)
  {
    a = paramLatLonPoint1;
    b = paramLatLonPoint2;
    if ((a.getLatitude() >= b.getLatitude()) || (a.getLongitude() >= b.getLongitude())) {
      throw new IllegalArgumentException("invalid rect ");
    }
    d = new LatLonPoint((a.getLatitude() + b.getLatitude()) / 2.0D, (a.getLongitude() + b.getLongitude()) / 2.0D);
  }
  
  public SearchBound clone()
  {
    try
    {
      super.clone();
      return new SearchBound(a, b, c, d, e, g, f);
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      for (;;)
      {
        d.a(localCloneNotSupportedException, "PoiSearch", "SearchBoundClone");
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      do
      {
        return true;
        if (paramObject == null) {
          return false;
        }
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (SearchBound)paramObject;
        if (d == null)
        {
          if (d != null) {
            return false;
          }
        }
        else if (!d.equals(d)) {
          return false;
        }
        if (f != f) {
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
        if (b == null)
        {
          if (b != null) {
            return false;
          }
        }
        else if (!b.equals(b)) {
          return false;
        }
        if (g == null)
        {
          if (g != null) {
            return false;
          }
        }
        else if (!g.equals(g)) {
          return false;
        }
        if (c != c) {
          return false;
        }
        if (e != null) {
          break;
        }
      } while (e == null);
      return false;
    } while (e.equals(e));
    return false;
  }
  
  public LatLonPoint getCenter()
  {
    return d;
  }
  
  public double getLatSpanInMeter()
  {
    if (!"Rectangle".equals(getShape())) {
      return 0.0D;
    }
    return b.getLatitude() - a.getLatitude();
  }
  
  public double getLonSpanInMeter()
  {
    if (!"Rectangle".equals(getShape())) {
      return 0.0D;
    }
    return b.getLongitude() - a.getLongitude();
  }
  
  public LatLonPoint getLowerLeft()
  {
    return a;
  }
  
  public List<LatLonPoint> getPolyGonList()
  {
    return g;
  }
  
  public int getRange()
  {
    return c;
  }
  
  public String getShape()
  {
    return e;
  }
  
  public LatLonPoint getUpperRight()
  {
    return b;
  }
  
  public int hashCode()
  {
    int i1 = 0;
    int i;
    int j;
    label23:
    int k;
    label32:
    int m;
    label42:
    int n;
    label52:
    int i2;
    if (d == null)
    {
      i = 0;
      if (!f) {
        break label115;
      }
      j = 1231;
      if (a != null) {
        break label122;
      }
      k = 0;
      if (b != null) {
        break label133;
      }
      m = 0;
      if (g != null) {
        break label145;
      }
      n = 0;
      i2 = c;
      if (e != null) {
        break label159;
      }
    }
    for (;;)
    {
      return ((n + (m + (k + (j + (i + 31) * 31) * 31) * 31) * 31) * 31 + i2) * 31 + i1;
      i = d.hashCode();
      break;
      label115:
      j = 1237;
      break label23;
      label122:
      k = a.hashCode();
      break label32;
      label133:
      m = b.hashCode();
      break label42;
      label145:
      n = g.hashCode();
      break label52;
      label159:
      i1 = e.hashCode();
    }
  }
  
  public boolean isDistanceSort()
  {
    return f;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.poisearch.PoiSearch.SearchBound
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */