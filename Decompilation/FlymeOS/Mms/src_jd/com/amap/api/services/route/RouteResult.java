package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class RouteResult
  implements Parcelable
{
  public static final Parcelable.Creator<RouteResult> CREATOR = new l();
  private LatLonPoint a;
  private LatLonPoint b;
  
  public RouteResult() {}
  
  public RouteResult(Parcel paramParcel)
  {
    a = ((LatLonPoint)paramParcel.readParcelable(LatLonPoint.class.getClassLoader()));
    b = ((LatLonPoint)paramParcel.readParcelable(LatLonPoint.class.getClassLoader()));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public LatLonPoint getStartPos()
  {
    return a;
  }
  
  public LatLonPoint getTargetPos()
  {
    return b;
  }
  
  public void setStartPos(LatLonPoint paramLatLonPoint)
  {
    a = paramLatLonPoint;
  }
  
  public void setTargetPos(LatLonPoint paramLatLonPoint)
  {
    b = paramLatLonPoint;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(a, paramInt);
    paramParcel.writeParcelable(b, paramInt);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.RouteResult
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */