package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.RouteSearch.BusRouteQuery;
import com.amap.api.services.route.RouteSearch.FromAndTo;

public class a
  extends r<RouteSearch.BusRouteQuery, BusRouteResult>
{
  public a(Context paramContext, RouteSearch.BusRouteQuery paramBusRouteQuery)
  {
    super(paramContext, paramBusRouteQuery);
  }
  
  protected BusRouteResult a(String paramString)
  {
    return j.b(paramString);
  }
  
  protected String a_()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("key=").append(w.f(d));
    localStringBuffer.append("&origin=").append(d.a(((RouteSearch.BusRouteQuery)a).getFromAndTo().getFrom()));
    localStringBuffer.append("&destination=").append(d.a(((RouteSearch.BusRouteQuery)a).getFromAndTo().getTo()));
    String str = ((RouteSearch.BusRouteQuery)a).getCity();
    if (!j.h(str))
    {
      str = c(str);
      localStringBuffer.append("&city=").append(str);
    }
    localStringBuffer.append("&strategy=").append("" + ((RouteSearch.BusRouteQuery)a).getMode());
    localStringBuffer.append("&nightflag=").append(((RouteSearch.BusRouteQuery)a).getNightFlag());
    localStringBuffer.append("&output=json");
    return localStringBuffer.toString();
  }
  
  public String b()
  {
    return c.a() + "/direction/transit/integrated?";
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */