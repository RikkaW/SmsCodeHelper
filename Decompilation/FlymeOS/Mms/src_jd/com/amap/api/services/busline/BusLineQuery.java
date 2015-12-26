package com.amap.api.services.busline;

import com.amap.api.services.core.d;

public class BusLineQuery
{
  private String a;
  private String b;
  private int c = 10;
  private int d = 0;
  private SearchType e;
  
  public BusLineQuery(String paramString1, SearchType paramSearchType, String paramString2)
  {
    a = paramString1;
    e = paramSearchType;
    b = paramString2;
    if (!a()) {
      throw new IllegalArgumentException("Empty query");
    }
  }
  
  private boolean a()
  {
    return !d.a(a);
  }
  
  protected BusLineQuery clone()
  {
    BusLineQuery localBusLineQuery = new BusLineQuery(a, e, b);
    localBusLineQuery.setPageNumber(d);
    localBusLineQuery.setPageSize(c);
    return localBusLineQuery;
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
        paramObject = (BusLineQuery)paramObject;
        if (e != e) {
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
  
  public SearchType getCategory()
  {
    return e;
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
    int k = 0;
    int i;
    int j;
    label20:
    int m;
    int n;
    if (e == null)
    {
      i = 0;
      if (b != null) {
        break label77;
      }
      j = 0;
      m = d;
      n = c;
      if (a != null) {
        break label88;
      }
    }
    for (;;)
    {
      return (((j + (i + 31) * 31) * 31 + m) * 31 + n) * 31 + k;
      i = e.hashCode();
      break;
      label77:
      j = b.hashCode();
      break label20;
      label88:
      k = a.hashCode();
    }
  }
  
  public void setCategory(SearchType paramSearchType)
  {
    e = paramSearchType;
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
    c = paramInt;
  }
  
  public void setQueryString(String paramString)
  {
    a = paramString;
  }
  
  protected boolean weakEquals(BusLineQuery paramBusLineQuery)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramBusLineQuery.getQueryString().equals(a))
    {
      bool1 = bool2;
      if (paramBusLineQuery.getCity().equals(b))
      {
        bool1 = bool2;
        if (paramBusLineQuery.getPageSize() == c)
        {
          bool1 = bool2;
          if (paramBusLineQuery.getCategory().compareTo(e) == 0) {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  public static enum SearchType
  {
    private SearchType() {}
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.busline.BusLineQuery
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */