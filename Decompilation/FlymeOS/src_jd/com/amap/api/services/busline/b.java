package com.amap.api.services.busline;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class b
  implements Parcelable.Creator<BusStationItem>
{
  public BusStationItem a(Parcel paramParcel)
  {
    return new BusStationItem(paramParcel, null);
  }
  
  public BusStationItem[] a(int paramInt)
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.busline.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */