package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class District
  implements Parcelable
{
  public static final Parcelable.Creator<District> CREATOR = new d();
  private String a;
  private String b;
  
  public District() {}
  
  public District(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.readString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getDistrictAdcode()
  {
    return b;
  }
  
  public String getDistrictName()
  {
    return a;
  }
  
  public void setDistrictAdcode(String paramString)
  {
    b = paramString;
  }
  
  public void setDistrictName(String paramString)
  {
    a = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(a);
    paramParcel.writeString(b);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.District
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */