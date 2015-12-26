package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class q
  implements Parcelable.Creator<PoiItem>
{
  public PoiItem a(Parcel paramParcel)
  {
    return new PoiItem(paramParcel);
  }
  
  public PoiItem[] a(int paramInt)
  {
    return new PoiItem[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.q
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */