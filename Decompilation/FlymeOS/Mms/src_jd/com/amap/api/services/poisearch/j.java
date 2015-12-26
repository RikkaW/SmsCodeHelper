package com.amap.api.services.poisearch;

import android.content.Context;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.core.c;
import com.amap.api.services.core.d;
import com.amap.api.services.core.s;
import com.amap.api.services.core.w;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

class j
  extends g<s, ArrayList<PoiItem>>
{
  private int h = 1;
  private int i = 20;
  private int j = 0;
  private List<String> k = new ArrayList();
  private List<SuggestionCity> l = new ArrayList();
  
  public j(Context paramContext, s params)
  {
    super(paramContext, params);
  }
  
  private String n()
  {
    if (a).b.isDistanceSort()) {
      return "distance";
    }
    return "weight";
  }
  
  private String o()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if ((a).a.hasGroupBuyLimit()) && (a).a.hasDiscountLimit()))
    {
      localStringBuffer.append("&filter=groupbuy:1|discount:1");
      return localStringBuffer.toString();
    }
    if (a).a.hasGroupBuyLimit())
    {
      localStringBuffer.append("&filter=");
      localStringBuffer.append("groupbuy:1");
    }
    if (a).a.hasDiscountLimit())
    {
      localStringBuffer.append("&filter=");
      localStringBuffer.append("discount:1");
    }
    return localStringBuffer.toString();
  }
  
  public void a(int paramInt)
  {
    h = (paramInt + 1);
  }
  
  protected String a_()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("output=json");
    double d1;
    double d2;
    if (a).b != null)
    {
      if (!a).b.getShape().equals("Bound")) {
        break label434;
      }
      d1 = d.a(a).b.getCenter().getLongitude());
      d2 = d.a(a).b.getCenter().getLatitude());
      localStringBuilder.append("&location=").append(d1 + "," + d2);
      localStringBuilder.append("&radius=").append(a).b.getRange());
      localStringBuilder.append("&sortrule=").append(n());
    }
    for (;;)
    {
      Object localObject = a).a.getCity();
      if (!a((String)localObject))
      {
        localObject = c((String)localObject);
        localStringBuilder.append("&city=").append((String)localObject);
      }
      if (!d.a(o())) {
        localStringBuilder.append(o());
      }
      localObject = c(a).a.getQueryString());
      localStringBuilder.append("&keywords=" + (String)localObject);
      localStringBuilder.append("&language=").append(c.b());
      localStringBuilder.append("&offset=" + i);
      localStringBuilder.append("&page=" + h);
      localObject = c(a).a.getCategory());
      localStringBuilder.append("&types=" + (String)localObject);
      localStringBuilder.append("&extensions=all");
      localStringBuilder.append("&key=" + w.f(d));
      return localStringBuilder.toString();
      label434:
      if (a).b.getShape().equals("Rectangle"))
      {
        localObject = a).b.getLowerLeft();
        LatLonPoint localLatLonPoint = a).b.getUpperRight();
        d1 = d.a(((LatLonPoint)localObject).getLatitude());
        d2 = d.a(((LatLonPoint)localObject).getLongitude());
        double d3 = d.a(localLatLonPoint.getLatitude());
        double d4 = d.a(localLatLonPoint.getLongitude());
        localStringBuilder.append("&polygon=" + d2 + "," + d1 + ";" + d4 + "," + d3);
      }
      else if (a).b.getShape().equals("Polygon"))
      {
        localObject = a).b.getPolyGonList();
        if ((localObject != null) && (((List)localObject).size() > 0)) {
          localStringBuilder.append("&polygon=" + d.a((List)localObject));
        }
      }
    }
  }
  
  public String b()
  {
    String str = c.a() + "/place";
    if (a).b == null) {
      return str + "/text?";
    }
    if (a).b.getShape().equals("Bound")) {
      return str + "/around?";
    }
    if ((a).b.getShape().equals("Rectangle")) || (a).b.getShape().equals("Polygon"))) {
      return str + "/polygon?";
    }
    return str;
  }
  
  public void b(int paramInt)
  {
    int m = 30;
    if (paramInt > 30) {
      paramInt = 30;
    }
    for (;;)
    {
      if (paramInt <= 0) {
        paramInt = m;
      }
      for (;;)
      {
        i = paramInt;
        return;
      }
    }
  }
  
  public ArrayList<PoiItem> e(String paramString)
  {
    Object localObject1 = new ArrayList();
    if (paramString == null) {}
    Object localObject3;
    for (;;)
    {
      return (ArrayList<PoiItem>)localObject1;
      Object localObject2 = localObject1;
      localObject3 = localObject1;
      try
      {
        JSONObject localJSONObject = new JSONObject(paramString);
        localObject2 = localObject1;
        localObject3 = localObject1;
        j = localJSONObject.optInt("count");
        localObject2 = localObject1;
        localObject3 = localObject1;
        paramString = com.amap.api.services.core.j.c(localJSONObject);
        localObject1 = paramString;
        localObject2 = paramString;
        localObject3 = paramString;
        if (localJSONObject.has("suggestion"))
        {
          localObject2 = paramString;
          localObject3 = paramString;
          localJSONObject = localJSONObject.optJSONObject("suggestion");
          localObject1 = paramString;
          if (localJSONObject != null)
          {
            localObject2 = paramString;
            localObject3 = paramString;
            l = com.amap.api.services.core.j.a(localJSONObject);
            localObject2 = paramString;
            localObject3 = paramString;
            k = com.amap.api.services.core.j.b(localJSONObject);
            return paramString;
          }
        }
      }
      catch (JSONException paramString)
      {
        d.a(paramString, "PoiSearchKeywordHandler", "paseJSONJSONException");
        return (ArrayList<PoiItem>)localObject2;
      }
      catch (Exception paramString)
      {
        d.a(paramString, "PoiSearchKeywordHandler", "paseJSONException");
      }
    }
    return (ArrayList<PoiItem>)localObject3;
  }
  
  public int f()
  {
    return i;
  }
  
  public int i()
  {
    return j;
  }
  
  public PoiSearch.Query j()
  {
    return a).a;
  }
  
  public PoiSearch.SearchBound k()
  {
    return a).b;
  }
  
  public List<String> l()
  {
    return k;
  }
  
  public List<SuggestionCity> m()
  {
    return l;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.poisearch.j
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */