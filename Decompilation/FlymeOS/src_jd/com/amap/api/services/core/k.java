package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class k
  implements Parcelable.Creator<LatLonPoint>
{
  public LatLonPoint a(Parcel paramParcel)
  {
    return new LatLonPoint(paramParcel, null);
  }
  
  public LatLonPoint[] a(int paramInt)
  {
    return new LatLonPoint[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.k
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */