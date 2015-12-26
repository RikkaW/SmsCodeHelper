package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RouteSearch.DriveRouteQuery;
import com.amap.api.services.route.RouteSearch.FromAndTo;

public class f
  extends r<RouteSearch.DriveRouteQuery, DriveRouteResult>
{
  public f(Context paramContext, RouteSearch.DriveRouteQuery paramDriveRouteQuery)
  {
    super(paramContext, paramDriveRouteQuery);
  }
  
  protected DriveRouteResult a(String paramString)
  {
    return j.c(paramString);
  }
  
  protected String a_()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("key=").append(w.f(d));
    localStringBuffer.append("&origin=").append(d.a(((RouteSearch.DriveRouteQuery)a).getFromAndTo().getFrom()));
    if (!j.h(((RouteSearch.DriveRouteQuery)a).getFromAndTo().getStartPoiID())) {
      localStringBuffer.append("&originid=").append(((RouteSearch.DriveRouteQuery)a).getFromAndTo().getStartPoiID());
    }
    localStringBuffer.append("&destination=").append(d.a(((RouteSearch.DriveRouteQuery)a).getFromAndTo().getTo()));
    if (!j.h(((RouteSearch.DriveRouteQuery)a).getFromAndTo().getDestinationPoiID())) {
      localStringBuffer.append("&destinationid=").append(((RouteSearch.DriveRouteQuery)a).getFromAndTo().getDestinationPoiID());
    }
    localStringBuffer.append("&strategy=").append("" + ((RouteSearch.DriveRouteQuery)a).getMode());
    localStringBuffer.append("&extensions=all");
    if (((RouteSearch.DriveRouteQuery)a).hasPassPoint()) {
      localStringBuffer.append("&waypoints=").append(((RouteSearch.DriveRouteQuery)a).getPassedPointStr());
    }
    if (((RouteSearch.DriveRouteQuery)a).hasAvoidpolygons()) {
      localStringBuffer.append("&avoidpolygons=").append(((RouteSearch.DriveRouteQuery)a).getAvoidpolygonsStr());
    }
    if (((RouteSearch.DriveRouteQuery)a).hasAvoidRoad()) {
      localStringBuffer.append("&avoidroad=").append(c(((RouteSearch.DriveRouteQuery)a).getAvoidRoad()));
    }
    localStringBuffer.append("&output=json");
    return localStringBuffer.toString();
  }
  
  public String b()
  {
    return c.a() + "/direction/driving?";
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.f
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */