package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.busline.BusLineQuery;
import com.amap.api.services.busline.BusLineQuery.SearchType;
import com.amap.api.services.busline.BusStationQuery;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class b<T>
  extends r<T, ArrayList<?>>
{
  private int h = 0;
  private List<String> i = new ArrayList();
  private List<SuggestionCity> j = new ArrayList();
  
  public b(Context paramContext, T paramT)
  {
    super(paramContext, paramT);
  }
  
  protected ArrayList<?> a(String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString);
      JSONObject localJSONObject = paramString.optJSONObject("suggestion");
      if (localJSONObject != null)
      {
        j = j.a(localJSONObject);
        i = j.b(localJSONObject);
      }
      h = paramString.optInt("count");
      if ((a instanceof BusLineQuery)) {
        return j.i(paramString);
      }
      paramString = j.e(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      d.a(paramString, "BusSearchServerHandler", "paseJSON");
    }
    return null;
  }
  
  protected String a_()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("output=json");
    Object localObject;
    if ((a instanceof BusLineQuery))
    {
      localObject = (BusLineQuery)a;
      localStringBuilder.append("&extensions=all");
      if (((BusLineQuery)localObject).getCategory() == BusLineQuery.SearchType.BY_LINE_ID) {
        localStringBuilder.append("&id=").append(c(((BusLineQuery)a).getQueryString()));
      }
    }
    for (;;)
    {
      localStringBuilder.append("&key=" + w.f(d));
      return localStringBuilder.toString();
      String str = ((BusLineQuery)localObject).getCity();
      if (!j.h(str))
      {
        str = c(str);
        localStringBuilder.append("&city=").append(str);
      }
      localStringBuilder.append("&keywords=" + c(((BusLineQuery)localObject).getQueryString()));
      localStringBuilder.append("&offset=" + ((BusLineQuery)localObject).getPageSize());
      localStringBuilder.append("&page=" + (((BusLineQuery)localObject).getPageNumber() + 1));
      continue;
      localObject = (BusStationQuery)a;
      str = ((BusStationQuery)localObject).getCity();
      if (!j.h(str))
      {
        str = c(str);
        localStringBuilder.append("&city=").append(str);
      }
      localStringBuilder.append("&keywords=" + c(((BusStationQuery)localObject).getQueryString()));
      localStringBuilder.append("&offset=" + ((BusStationQuery)localObject).getPageSize());
      localStringBuilder.append("&page=" + (((BusStationQuery)localObject).getPageNumber() + 1));
    }
  }
  
  public String b()
  {
    String str;
    if ((a instanceof BusLineQuery)) {
      if (((BusLineQuery)a).getCategory() == BusLineQuery.SearchType.BY_LINE_ID) {
        str = "lineid";
      }
    }
    for (;;)
    {
      return c.a() + "/bus/" + str + "?";
      if (((BusLineQuery)a).getCategory() == BusLineQuery.SearchType.BY_LINE_NAME)
      {
        str = "linename";
        continue;
        str = "stopname";
      }
      else
      {
        str = "";
      }
    }
  }
  
  public List<String> b_()
  {
    return i;
  }
  
  public T c()
  {
    return (T)a;
  }
  
  public int d()
  {
    return h;
  }
  
  public List<SuggestionCity> f()
  {
    return j;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */