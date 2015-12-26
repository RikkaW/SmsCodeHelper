package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class b
  implements Parcelable.Creator<BusRouteResult>
{
  public BusRouteResult a(Parcel paramParcel)
  {
    return new BusRouteResult(paramParcel);
  }
  
  public BusRouteResult[] a(int paramInt)
  {
    return new BusRouteResult[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */