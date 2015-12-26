package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class p
  implements Parcelable.Creator<RouteSearch.WalkRouteQuery>
{
  public RouteSearch.WalkRouteQuery a(Parcel paramParcel)
  {
    return new RouteSearch.WalkRouteQuery(paramParcel);
  }
  
  public RouteSearch.WalkRouteQuery[] a(int paramInt)
  {
    return new RouteSearch.WalkRouteQuery[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.p
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */