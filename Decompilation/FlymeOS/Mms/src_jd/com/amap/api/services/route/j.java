package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class j
  implements Parcelable.Creator<RouteBusLineItem>
{
  public RouteBusLineItem a(Parcel paramParcel)
  {
    return new RouteBusLineItem(paramParcel);
  }
  
  public RouteBusLineItem[] a(int paramInt)
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.j
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */