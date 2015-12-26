package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class BusinessArea
  implements Parcelable
{
  public static final Parcelable.Creator<BusinessArea> CREATOR = new a();
  private LatLonPoint a;
  private String b;
  
  public BusinessArea() {}
  
  public BusinessArea(Parcel paramParcel)
  {
    a = ((LatLonPoint)paramParcel.readParcelable(LatLonPoint.class.getClassLoader()));
    b = paramParcel.readString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public LatLonPoint getCenterPoint()
  {
    return a;
  }
  
  public String getName()
  {
    return b;
  }
  
  public void setCenterPoint(LatLonPoint paramLatLonPoint)
  {
    a = paramLatLonPoint;
  }
  
  public void setName(String paramString)
  {
    b = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(a, paramInt);
    paramParcel.writeString(b);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.geocoder.BusinessArea
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */