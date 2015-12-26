package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.route.RouteSearch.FromAndTo;
import com.amap.api.services.route.RouteSearch.WalkRouteQuery;
import com.amap.api.services.route.WalkRouteResult;

public class u
  extends r<RouteSearch.WalkRouteQuery, WalkRouteResult>
{
  public u(Context paramContext, RouteSearch.WalkRouteQuery paramWalkRouteQuery)
  {
    super(paramContext, paramWalkRouteQuery);
  }
  
  protected WalkRouteResult a(String paramString)
  {
    return j.d(paramString);
  }
  
  protected String a_()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("key=").append(w.f(d));
    localStringBuffer.append("&origin=").append(d.a(((RouteSearch.WalkRouteQuery)a).getFromAndTo().getFrom()));
    localStringBuffer.append("&destination=").append(d.a(((RouteSearch.WalkRouteQuery)a).getFromAndTo().getTo()));
    localStringBuffer.append("&multipath=0");
    localStringBuffer.append("&output=json");
    return localStringBuffer.toString();
  }
  
  public String b()
  {
    return c.a() + "/direction/walking?";
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.u
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */