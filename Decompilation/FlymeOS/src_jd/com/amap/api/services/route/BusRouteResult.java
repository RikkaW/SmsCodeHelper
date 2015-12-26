package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class BusRouteResult
  extends RouteResult
  implements Parcelable
{
  public static final Parcelable.Creator<BusRouteResult> CREATOR = new b();
  private float a;
  private List<BusPath> b = new ArrayList();
  private RouteSearch.BusRouteQuery c;
  
  public BusRouteResult() {}
  
  public BusRouteResult(Parcel paramParcel)
  {
    super(paramParcel);
    a = paramParcel.readFloat();
    b = paramParcel.createTypedArrayList(BusPath.CREATOR);
    c = ((RouteSearch.BusRouteQuery)paramParcel.readParcelable(RouteSearch.BusRouteQuery.class.getClassLoader()));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public RouteSearch.BusRouteQuery getBusQuery()
  {
    return c;
  }
  
  public List<BusPath> getPaths()
  {
    return b;
  }
  
  public float getTaxiCost()
  {
    return a;
  }
  
  public void setBusQuery(RouteSearch.BusRouteQuery paramBusRouteQuery)
  {
    c = paramBusRouteQuery;
  }
  
  public void setPaths(List<BusPath> paramList)
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
 * Qualified Name:     com.amap.api.services.route.BusRouteResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */