package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class n
  implements Parcelable.Creator<RouteSearch.DriveRouteQuery>
{
  public RouteSearch.DriveRouteQuery a(Parcel paramParcel)
  {
    return new RouteSearch.DriveRouteQuery(paramParcel);
  }
  
  public RouteSearch.DriveRouteQuery[] a(int paramInt)
  {
    return new RouteSearch.DriveRouteQuery[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.n
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */