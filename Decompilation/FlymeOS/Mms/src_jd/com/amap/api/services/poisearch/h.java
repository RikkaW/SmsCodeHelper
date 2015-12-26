package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class h
  implements Parcelable.Creator<PoiItemDetail>
{
  public PoiItemDetail a(Parcel paramParcel)
  {
    return new PoiItemDetail(paramParcel, null);
  }
  
  public PoiItemDetail[] a(int paramInt)
  {
    return new PoiItemDetail[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.poisearch.h
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */