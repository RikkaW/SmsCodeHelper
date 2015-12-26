package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class f
  implements Parcelable.Creator<LatLng>
{
  public LatLng a(Parcel paramParcel)
  {
    double d = paramParcel.readDouble();
    return new LatLng(paramParcel.readDouble(), d);
  }
  
  public LatLng[] a(int paramInt)
  {
    return new LatLng[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.f
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */