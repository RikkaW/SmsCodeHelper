package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class SearchCity
  implements Parcelable
{
  public static final Parcelable.Creator<SearchCity> CREATOR = new r();
  private String a;
  private String b;
  private String c;
  
  public SearchCity() {}
  
  public SearchCity(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.readString();
    c = paramParcel.readString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getSearchCityAdCode()
  {
    return c;
  }
  
  public String getSearchCityName()
  {
    return a;
  }
  
  public String getSearchCitycode()
  {
    return b;
  }
  
  public void setSearchCityName(String paramString)
  {
    a = paramString;
  }
  
  public void setSearchCitycode(String paramString)
  {
    b = paramString;
  }
  
  public void setSearchCityhAdCode(String paramString)
  {
    c = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(a);
    paramParcel.writeString(b);
    paramParcel.writeString(c);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.SearchCity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */