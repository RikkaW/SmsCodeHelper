package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class WalkRouteResult
  extends RouteResult
  implements Parcelable
{
  public static final Parcelable.Creator<WalkRouteResult> CREATOR = new t();
  private List<WalkPath> a = new ArrayList();
  private RouteSearch.WalkRouteQuery b;
  
  public WalkRouteResult() {}
  
  public WalkRouteResult(Parcel paramParcel)
  {
    super(paramParcel);
    a = paramParcel.createTypedArrayList(WalkPath.CREATOR);
    b = ((RouteSearch.WalkRouteQuery)paramParcel.readParcelable(RouteSearch.WalkRouteQuery.class.getClassLoader()));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public List<WalkPath> getPaths()
  {
    return a;
  }
  
  public RouteSearch.WalkRouteQuery getWalkQuery()
  {
    return b;
  }
  
  public void setPaths(List<WalkPath> paramList)
  {
    a = paramList;
  }
  
  public void setWalkQuery(RouteSearch.WalkRouteQuery paramWalkRouteQuery)
  {
    b = paramWalkRouteQuery;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeTypedList(a);
    paramParcel.writeParcelable(b, paramInt);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.WalkRouteResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */