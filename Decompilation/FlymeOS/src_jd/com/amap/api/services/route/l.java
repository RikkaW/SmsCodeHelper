package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class l
  implements Parcelable.Creator<RouteResult>
{
  public RouteResult a(Parcel paramParcel)
  {
    return new RouteResult(paramParcel);
  }
  
  public RouteResult[] a(int paramInt)
  {
    return new RouteResult[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.l
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */