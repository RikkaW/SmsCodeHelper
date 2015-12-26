package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class a
  implements Parcelable.Creator<DistrictItem>
{
  public DistrictItem a(Parcel paramParcel)
  {
    return new DistrictItem(paramParcel);
  }
  
  public DistrictItem[] a(int paramInt)
  {
    return new DistrictItem[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.district.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */