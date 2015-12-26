package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.d;

public class RouteSearch$WalkRouteQuery
  implements Parcelable, Cloneable
{
  public static final Parcelable.Creator<WalkRouteQuery> CREATOR = new p();
  private RouteSearch.FromAndTo a;
  private int b;
  
  public RouteSearch$WalkRouteQuery() {}
  
  public RouteSearch$WalkRouteQuery(Parcel paramParcel)
  {
    a = ((RouteSearch.FromAndTo)paramParcel.readParcelable(RouteSearch.FromAndTo.class.getClassLoader()));
    b = paramParcel.readInt();
  }
  
  public RouteSearch$WalkRouteQuery(RouteSearch.FromAndTo paramFromAndTo, int paramInt)
  {
    a = paramFromAndTo;
    b = paramInt;
  }
  
  public WalkRouteQuery clone()
  {
    try
    {
      super.clone();
      return new WalkRouteQuery(a, b);
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      for (;;)
      {
        d.a(localCloneNotSupportedException, "RouteSearch", "WalkRouteQueryclone");
      }
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      paramObject = (WalkRouteQuery)paramObject;
      if (a == null)
      {
        if (a != null) {
          return false;
        }
      }
      else if (!a.equals(a)) {
        return false;
      }
    } while (b == b);
    return false;
  }
  
  public RouteSearch.FromAndTo getFromAndTo()
  {
    return a;
  }
  
  public int getMode()
  {
    return b;
  }
  
  public int hashCode()
  {
    if (a == null) {}
    for (int i = 0;; i = a.hashCode()) {
      return (i + 31) * 31 + b;
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(a, paramInt);
    paramParcel.writeInt(b);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.RouteSearch.WalkRouteQuery
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */