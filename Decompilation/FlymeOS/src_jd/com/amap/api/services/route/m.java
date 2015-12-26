package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class m
  implements Parcelable.Creator<RouteSearch.BusRouteQuery>
{
  public RouteSearch.BusRouteQuery a(Parcel paramParcel)
  {
    return new RouteSearch.BusRouteQuery(paramParcel);
  }
  
  public RouteSearch.BusRouteQuery[] a(int paramInt)
  {
    return new RouteSearch.BusRouteQuery[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.m
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */