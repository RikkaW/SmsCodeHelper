package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class RouteBusWalkItem
  extends WalkPath
  implements Parcelable
{
  public static final Parcelable.Creator<RouteBusWalkItem> CREATOR = new k();
  private LatLonPoint a;
  private LatLonPoint b;
  
  public RouteBusWalkItem() {}
  
  public RouteBusWalkItem(Parcel paramParcel)
  {
    super(paramParcel);
    a = ((LatLonPoint)paramParcel.readParcelable(LatLonPoint.class.getClassLoader()));
    b = ((LatLonPoint)paramParcel.readParcelable(LatLonPoint.class.getClassLoader()));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public LatLonPoint getDestination()
  {
    return b;
  }
  
  public LatLonPoint getOrigin()
  {
    return a;
  }
  
  public void setDestination(LatLonPoint paramLatLonPoint)
  {
    b = paramLatLonPoint;
  }
  
  public void setOrigin(LatLonPoint paramLatLonPoint)
  {
    a = paramLatLonPoint;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeParcelable(a, paramInt);
    paramParcel.writeParcelable(b, paramInt);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.RouteBusWalkItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */