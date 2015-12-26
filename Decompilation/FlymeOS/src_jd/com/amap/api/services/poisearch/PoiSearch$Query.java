package com.amap.api.services.poisearch;

import com.amap.api.services.core.d;

public class PoiSearch$Query
  implements Cloneable
{
  private String a;
  private String b;
  private String c;
  private int d = 0;
  private int e = 20;
  private boolean f;
  private boolean g;
  private String h = "zh-CN";
  
  public PoiSearch$Query(String paramString1, String paramString2)
  {
    this(paramString1, paramString2, null);
  }
  
  public PoiSearch$Query(String paramString1, String paramString2, String paramString3)
  {
    a = paramString1;
    b = paramString2;
    c = paramString3;
  }
  
  private String a()
  {
    return "";
  }
  
  public Query clone()
  {
    try
    {
      super.clone();
      Query localQuery = new Query(a, b, c);
      localQuery.setPageNum(d);
      localQuery.setPageSize(e);
      localQuery.setLimitDiscount(g);
      localQuery.setLimitGroupbuy(f);
      localQuery.setQueryLanguage(h);
      return localQuery;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      for (;;)
      {
        d.a(localCloneNotSupportedException, "PoiSearch", "queryclone");
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
        paramObject = (Query)paramObject;
        if (b == null)
        {
          if (b != null) {
            return false;
          }
        }
        else if (!b.equals(b)) {
          return false;
        }
        if (c == null)
        {
          if (c != null) {
            return false;
          }
        }
        else if (!c.equals(c)) {
          return false;
        }
        if (g != g) {
          return false;
        }
        if (f != f) {
          return false;
        }
        if (h == null)
        {
          if (h != null) {
            return false;
          }
        }
        else if (!h.equals(h)) {
          return false;
        }
        if (d != d) {
          return false;
        }
        if (e != e) {
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
  
  public String getCategory()
  {
    if ((b == null) || (b.equals("00")) || (b.equals("00|"))) {
      return a();
    }
    return b;
  }
  
  public String getCity()
  {
    return c;
  }
  
  public int getPageNum()
  {
    return d;
  }
  
  public int getPageSize()
  {
    return e;
  }
  
  protected String getQueryLanguage()
  {
    return h;
  }
  
  public String getQueryString()
  {
    return a;
  }
  
  public boolean hasDiscountLimit()
  {
    return g;
  }
  
  public boolean hasGroupBuyLimit()
  {
    return f;
  }
  
  public int hashCode()
  {
    int m = 1231;
    int i1 = 0;
    int i;
    int j;
    label26:
    int k;
    label37:
    label44:
    int n;
    label54:
    int i2;
    int i3;
    if (b == null)
    {
      i = 0;
      if (c != null) {
        break label129;
      }
      j = 0;
      if (!g) {
        break label140;
      }
      k = 1231;
      if (!f) {
        break label147;
      }
      if (h != null) {
        break label155;
      }
      n = 0;
      i2 = d;
      i3 = e;
      if (a != null) {
        break label167;
      }
    }
    for (;;)
    {
      return (((n + ((k + (j + (i + 31) * 31) * 31) * 31 + m) * 31) * 31 + i2) * 31 + i3) * 31 + i1;
      i = b.hashCode();
      break;
      label129:
      j = c.hashCode();
      break label26;
      label140:
      k = 1237;
      break label37;
      label147:
      m = 1237;
      break label44;
      label155:
      n = h.hashCode();
      break label54;
      label167:
      i1 = a.hashCode();
    }
  }
  
  public boolean queryEquals(Query paramQuery)
  {
    boolean bool2 = true;
    boolean bool1;
    if (paramQuery == null) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramQuery == this);
      if ((!PoiSearch.a(a, a)) || (!PoiSearch.a(b, b)) || (!PoiSearch.a(h, h)) || (!PoiSearch.a(c, c))) {
        break;
      }
      bool1 = bool2;
    } while (e == e);
    return false;
  }
  
  public void setLimitDiscount(boolean paramBoolean)
  {
    g = paramBoolean;
  }
  
  public void setLimitGroupbuy(boolean paramBoolean)
  {
    f = paramBoolean;
  }
  
  public void setPageNum(int paramInt)
  {
    d = paramInt;
  }
  
  public void setPageSize(int paramInt)
  {
    e = paramInt;
  }
  
  protected void setQueryLanguage(String paramString)
  {
    if ("en".equals(paramString))
    {
      h = "en";
      return;
    }
    h = "zh-CN";
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.poisearch.PoiSearch.Query
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */