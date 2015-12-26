package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class RouteSearchCity
  extends SearchCity
  implements Parcelable
{
  public static final Parcelable.Creator<RouteSearchCity> CREATOR = new q();
  List<District> a = new ArrayList();
  
  public RouteSearchCity() {}
  
  public RouteSearchCity(Parcel paramParcel)
  {
    super(paramParcel);
    a = paramParcel.createTypedArrayList(District.CREATOR);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public List<District> getDistricts()
  {
    return a;
  }
  
  public void setDistricts(List<District> paramList)
  {
    a = paramList;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeTypedList(a);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.RouteSearchCity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */