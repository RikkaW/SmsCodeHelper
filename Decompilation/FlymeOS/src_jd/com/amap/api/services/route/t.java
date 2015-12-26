package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class t
  implements Parcelable.Creator<WalkRouteResult>
{
  public WalkRouteResult a(Parcel paramParcel)
  {
    return new WalkRouteResult(paramParcel);
  }
  
  public WalkRouteResult[] a(int paramInt)
  {
    return new WalkRouteResult[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.t
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */