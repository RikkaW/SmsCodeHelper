package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class g
  implements Parcelable.Creator<DriveRouteResult>
{
  public DriveRouteResult a(Parcel paramParcel)
  {
    return new DriveRouteResult(paramParcel);
  }
  
  public DriveRouteResult[] a(int paramInt)
  {
    return new DriveRouteResult[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.g
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */