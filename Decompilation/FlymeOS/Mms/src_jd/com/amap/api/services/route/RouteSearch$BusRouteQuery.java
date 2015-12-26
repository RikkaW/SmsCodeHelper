package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.d;

public class RouteSearch$BusRouteQuery
  implements Parcelable, Cloneable
{
  public static final Parcelable.Creator<BusRouteQuery> CREATOR = new m();
  private RouteSearch.FromAndTo a;
  private int b;
  private String c;
  private int d;
  
  public RouteSearch$BusRouteQuery() {}
  
  public RouteSearch$BusRouteQuery(Parcel paramParcel)
  {
    a = ((RouteSearch.FromAndTo)paramParcel.readParcelable(RouteSearch.FromAndTo.class.getClassLoader()));
    b = paramParcel.readInt();
    c = paramParcel.readString();
    d = paramParcel.readInt();
  }
  
  public RouteSearch$BusRouteQuery(RouteSearch.FromAndTo paramFromAndTo, int paramInt1, String paramString, int paramInt2)
  {
    a = paramFromAndTo;
    b = paramInt1;
    c = paramString;
    d = paramInt2;
  }
  
  public BusRouteQuery clone()
  {
    try
    {
      super.clone();
      return new BusRouteQuery(a, b, c, d);
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      for (;;)
      {
        d.a(localCloneNotSupportedException, "RouteSearch", "BusRouteQueryclone");
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
      paramObject = (BusRouteQuery)paramObject;
      if (c == null)
      {
        if (c != null) {
          return false;
        }
      }
      else if (!c.equals(c)) {
        return false;
      }
      if (a == null)
      {
        if (a != null) {
          return false;
        }
      }
      else if (!a.equals(a)) {
        return false;
      }
      if (b != b) {
        return false;
      }
    } while (d == d);
    return false;
  }
  
  public String getCity()
  {
    return c;
  }
  
  public RouteSearch.FromAndTo getFromAndTo()
  {
    return a;
  }
  
  public int getMode()
  {
    return b;
  }
  
  public int getNightFlag()
  {
    return d;
  }
  
  public int hashCode()
  {
    int j = 0;
    int i;
    if (c == null)
    {
      i = 0;
      if (a != null) {
        break label55;
      }
    }
    for (;;)
    {
      return (((i + 31) * 31 + j) * 31 + b) * 31 + d;
      i = c.hashCode();
      break;
      label55:
      j = a.hashCode();
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(a, paramInt);
    paramParcel.writeInt(b);
    paramParcel.writeString(c);
    paramParcel.writeInt(d);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.RouteSearch.BusRouteQuery
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */