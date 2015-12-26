package com.amap.api.services.busline;

import com.amap.api.services.core.d;

public class BusStationQuery
{
  private String a;
  private String b;
  private int c = 10;
  private int d = 0;
  
  public BusStationQuery(String paramString1, String paramString2)
  {
    a = paramString1;
    b = paramString2;
    if (!a()) {
      throw new IllegalArgumentException("Empty query");
    }
  }
  
  private boolean a()
  {
    return !d.a(a);
  }
  
  protected BusStationQuery clone()
  {
    BusStationQuery localBusStationQuery = new BusStationQuery(a, b);
    localBusStationQuery.setPageNumber(d);
    localBusStationQuery.setPageSize(c);
    return localBusStationQuery;
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
        paramObject = (BusStationQuery)paramObject;
        if (b == null)
        {
          if (b != null) {
            return false;
          }
        }
        else if (!b.equals(b)) {
          return false;
        }
        if (d != d) {
          return false;
        }
        if (c != c) {
          return false;
        }
        if (a != null) {
          break;
        }
      } while (a == null);
      return false;
    } while (a.equals(a));
    return false;
  }
  
  public String getCity()
  {
    return b;
  }
  
  public int getPageNumber()
  {
    return d;
  }
  
  public int getPageSize()
  {
    return c;
  }
  
  public String getQueryString()
  {
    return a;
  }
  
  public int hashCode()
  {
    int j = 0;
    int i;
    int k;
    int m;
    if (b == null)
    {
      i = 0;
      k = d;
      m = c;
      if (a != null) {
        break label61;
      }
    }
    for (;;)
    {
      return (((i + 31) * 31 + k) * 31 + m) * 31 + j;
      i = b.hashCode();
      break;
      label61:
      j = a.hashCode();
    }
  }
  
  public void setCity(String paramString)
  {
    b = paramString;
  }
  
  public void setPageNumber(int paramInt)
  {
    d = paramInt;
  }
  
  public void setPageSize(int paramInt)
  {
    int i = 20;
    if (paramInt > 20) {
      paramInt = i;
    }
    for (;;)
    {
      i = paramInt;
      if (paramInt <= 0) {
        i = 10;
      }
      c = i;
      return;
    }
  }
  
  public void setQueryString(String paramString)
  {
    a = paramString;
  }
  
  protected boolean weakEquals(BusStationQuery paramBusStationQuery)
  {
    if (this == paramBusStationQuery) {}
    do
    {
      do
      {
        return true;
        if (paramBusStationQuery == null) {
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
        if (c != c) {
          return false;
        }
        if (a != null) {
          break;
        }
      } while (a == null);
      return false;
    } while (a.equals(a));
    return false;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.busline.BusStationQuery
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */