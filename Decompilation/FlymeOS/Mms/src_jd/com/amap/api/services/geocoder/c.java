package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class c
  implements Parcelable.Creator<RegeocodeAddress>
{
  public RegeocodeAddress a(Parcel paramParcel)
  {
    return new RegeocodeAddress(paramParcel, null);
  }
  
  public RegeocodeAddress[] a(int paramInt)
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.geocoder.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */