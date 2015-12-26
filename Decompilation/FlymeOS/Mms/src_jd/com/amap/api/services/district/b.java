package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class b
  implements Parcelable.Creator<DistrictResult>
{
  b(DistrictResult paramDistrictResult) {}
  
  public DistrictResult a(Parcel paramParcel)
  {
    return new DistrictResult(paramParcel);
  }
  
  public DistrictResult[] a(int paramInt)
  {
    return new DistrictResult[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.district.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */