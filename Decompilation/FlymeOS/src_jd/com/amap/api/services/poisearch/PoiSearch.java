package com.amap.api.services.poisearch;

import android.content.Context;
import android.os.Handler;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.d;
import com.amap.api.services.core.l;
import com.amap.api.services.core.p;
import com.amap.api.services.core.s;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PoiSearch
{
  public static final String CHINESE = "zh-CN";
  public static final String ENGLISH = "en";
  private static HashMap<Integer, PoiResult> i;
  private SearchBound a;
  private Query b;
  private Context c;
  private OnPoiSearchListener d;
  private String e = "zh-CN";
  private Query f;
  private SearchBound g;
  private int h;
  private Handler j = null;
  
  public PoiSearch(Context paramContext, Query paramQuery)
  {
    c = paramContext.getApplicationContext();
    setQuery(paramQuery);
    j = p.a();
  }
  
  private void a(PoiResult paramPoiResult)
  {
    i = new HashMap();
    if ((b == null) || (paramPoiResult == null)) {}
    while ((h <= 0) || (h <= b.getPageNum())) {
      return;
    }
    i.put(Integer.valueOf(b.getPageNum()), paramPoiResult);
  }
  
  private boolean a()
  {
    return (!d.a(Query.a(b))) || (!d.a(Query.b(b)));
  }
  
  private boolean a(int paramInt)
  {
    return (paramInt <= h) && (paramInt >= 0);
  }
  
  private boolean b()
  {
    SearchBound localSearchBound = getBound();
    if (localSearchBound == null) {}
    while (!localSearchBound.getShape().equals("Bound")) {
      return false;
    }
    return true;
  }
  
  private static boolean b(String paramString1, String paramString2)
  {
    if ((paramString1 == null) && (paramString2 == null)) {
      return true;
    }
    if ((paramString1 != null) && (paramString2 != null)) {
      return paramString1.equals(paramString2);
    }
    return false;
  }
  
  public SearchBound getBound()
  {
    return a;
  }
  
  public String getLanguage()
  {
    return e;
  }
  
  protected PoiResult getPageLocal(int paramInt)
  {
    if (!a(paramInt)) {
      throw new IllegalArgumentException("page out of range");
    }
    return (PoiResult)i.get(Integer.valueOf(paramInt));
  }
  
  public Query getQuery()
  {
    return b;
  }
  
  public PoiResult searchPOI()
  {
    l.a(c);
    if ((!b()) && (!a())) {
      throw new AMapException("无效的参数 - IllegalArgumentException");
    }
    b.setQueryLanguage(e);
    if (((!b.queryEquals(f)) && (a == null)) || ((!b.queryEquals(f)) && (!a.equals(g))))
    {
      h = 0;
      f = b.clone();
      if (a != null) {
        g = a.clone();
      }
      if (i != null) {
        i.clear();
      }
    }
    Object localObject = null;
    if (a != null) {
      localObject = a.clone();
    }
    if (h == 0)
    {
      localObject = new j(c, new s(b.clone(), (SearchBound)localObject));
      ((j)localObject).a(Query.c(b));
      ((j)localObject).b(Query.d(b));
      localObject = PoiResult.a((j)localObject, (ArrayList)((j)localObject).g());
      a((PoiResult)localObject);
      return (PoiResult)localObject;
    }
    PoiResult localPoiResult = getPageLocal(b.getPageNum());
    if (localPoiResult == null)
    {
      localObject = new j(c, new s(b.clone(), (SearchBound)localObject));
      ((j)localObject).a(Query.c(b));
      ((j)localObject).b(Query.d(b));
      localObject = PoiResult.a((j)localObject, (ArrayList)((j)localObject).g());
      i.put(Integer.valueOf(Query.c(b)), localObject);
      return (PoiResult)localObject;
    }
    return localPoiResult;
  }
  
  public void searchPOIAsyn()
  {
    new PoiSearch.1(this).start();
  }
  
  public PoiItemDetail searchPOIDetail(String paramString)
  {
    l.a(c);
    return (PoiItemDetail)new i(c, paramString, e).g();
  }
  
  public void searchPOIDetailAsyn(String paramString)
  {
    new PoiSearch.2(this, paramString).start();
  }
  
  public void setBound(SearchBound paramSearchBound)
  {
    a = paramSearchBound;
  }
  
  public void setLanguage(String paramString)
  {
    if ("en".equals(paramString))
    {
      e = "en";
      return;
    }
    e = "zh-CN";
  }
  
  public void setOnPoiSearchListener(OnPoiSearchListener paramOnPoiSearchListener)
  {
    d = paramOnPoiSearchListener;
  }
  
  public void setQuery(Query paramQuery)
  {
    b = paramQuery;
  }
  
  public static abstract interface OnPoiSearchListener
  {
    public abstract void onPoiItemDetailSearched(PoiItemDetail paramPoiItemDetail, int paramInt);
    
    public abstract void onPoiSearched(PoiResult paramPoiResult, int paramInt);
  }
  
  public static class Query
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
    
    public Query(String paramString1, String paramString2)
    {
      this(paramString1, paramString2, null);
    }
    
    public Query(String paramString1, String paramString2, String paramString3)
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
  
  public static class SearchBound
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
    
    public SearchBound(LatLonPoint paramLatLonPoint, int paramInt)
    {
      e = "Bound";
      c = paramInt;
      d = paramLatLonPoint;
      a(paramLatLonPoint, d.a(paramInt), d.a(paramInt));
    }
    
    public SearchBound(LatLonPoint paramLatLonPoint, int paramInt, boolean paramBoolean)
    {
      e = "Bound";
      c = paramInt;
      d = paramLatLonPoint;
      a(paramLatLonPoint, d.a(paramInt), d.a(paramInt));
      f = paramBoolean;
    }
    
    public SearchBound(LatLonPoint paramLatLonPoint1, LatLonPoint paramLatLonPoint2)
    {
      e = "Rectangle";
      a(paramLatLonPoint1, paramLatLonPoint2);
    }
    
    private SearchBound(LatLonPoint paramLatLonPoint1, LatLonPoint paramLatLonPoint2, int paramInt, LatLonPoint paramLatLonPoint3, String paramString, List<LatLonPoint> paramList, boolean paramBoolean)
    {
      a = paramLatLonPoint1;
      b = paramLatLonPoint2;
      c = paramInt;
      d = paramLatLonPoint3;
      e = paramString;
      g = paramList;
      f = paramBoolean;
    }
    
    public SearchBound(List<LatLonPoint> paramList)
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
}

/* Location:
 * Qualified Name:     com.amap.api.services.poisearch.PoiSearch
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */