package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class c
  implements Parcelable.Creator<BusStep>
{
  public BusStep a(Parcel paramParcel)
  {
    return new BusStep(paramParcel);
  }
  
  public BusStep[] a(int paramInt)
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */