package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class b
  implements Parcelable.Creator<GeocodeAddress>
{
  public GeocodeAddress a(Parcel paramParcel)
  {
    return new GeocodeAddress(paramParcel, null);
  }
  
  public GeocodeAddress[] a(int paramInt)
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.geocoder.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */