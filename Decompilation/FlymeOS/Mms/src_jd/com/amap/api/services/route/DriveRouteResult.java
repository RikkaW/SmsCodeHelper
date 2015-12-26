package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class DriveRouteResult
  extends RouteResult
  implements Parcelable
{
  public static final Parcelable.Creator<DriveRouteResult> CREATOR = new g();
  private float a;
  private List<DrivePath> b = new ArrayList();
  private RouteSearch.DriveRouteQuery c;
  
  public DriveRouteResult() {}
  
  public DriveRouteResult(Parcel paramParcel)
  {
    super(paramParcel);
    a = paramParcel.readFloat();
    b = paramParcel.createTypedArrayList(DrivePath.CREATOR);
    c = ((RouteSearch.DriveRouteQuery)paramParcel.readParcelable(RouteSearch.DriveRouteQuery.class.getClassLoader()));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public RouteSearch.DriveRouteQuery getDriveQuery()
  {
    return c;
  }
  
  public List<DrivePath> getPaths()
  {
    return b;
  }
  
  public float getTaxiCost()
  {
    return a;
  }
  
  public void setDriveQuery(RouteSearch.DriveRouteQuery paramDriveRouteQuery)
  {
    c = paramDriveRouteQuery;
  }
  
  public void setPaths(List<DrivePath> paramList)
  {
    b = paramList;
  }
  
  public void setTaxiCost(float paramFloat)
  {
    a = paramFloat;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeFloat(a);
    paramParcel.writeTypedList(b);
    paramParcel.writeParcelable(c, paramInt);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.DriveRouteResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */