package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class a
  implements Parcelable.Creator<BusinessArea>
{
  public BusinessArea a(Parcel paramParcel)
  {
    return new BusinessArea(paramParcel);
  }
  
  public BusinessArea[] a(int paramInt)
  {
    return new BusinessArea[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.geocoder.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */