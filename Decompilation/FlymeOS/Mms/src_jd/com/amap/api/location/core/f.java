package com.amap.api.location.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class f
  implements Parcelable.Creator<GeoPoint>
{
  public GeoPoint a(Parcel paramParcel)
  {
    return new GeoPoint(paramParcel, null);
  }
  
  public GeoPoint[] a(int paramInt)
  {
    return new GeoPoint[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.core.f
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */