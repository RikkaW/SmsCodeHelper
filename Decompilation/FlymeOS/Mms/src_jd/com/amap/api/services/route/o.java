package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class o
  implements Parcelable.Creator<RouteSearch.FromAndTo>
{
  public RouteSearch.FromAndTo a(Parcel paramParcel)
  {
    return new RouteSearch.FromAndTo(paramParcel);
  }
  
  public RouteSearch.FromAndTo[] a(int paramInt)
  {
    return new RouteSearch.FromAndTo[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.o
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */