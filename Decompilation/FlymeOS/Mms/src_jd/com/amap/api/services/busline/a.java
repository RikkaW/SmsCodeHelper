package com.amap.api.services.busline;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class a
  implements Parcelable.Creator<BusLineItem>
{
  public BusLineItem a(Parcel paramParcel)
  {
    return new BusLineItem(paramParcel);
  }
  
  public BusLineItem[] a(int paramInt)
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.busline.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */