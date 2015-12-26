package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RouteSearch$DriveRouteQuery
  implements Parcelable, Cloneable
{
  public static final Parcelable.Creator<DriveRouteQuery> CREATOR = new n();
  private RouteSearch.FromAndTo a;
  private int b;
  private List<LatLonPoint> c;
  private List<List<LatLonPoint>> d;
  private String e;
  
  public RouteSearch$DriveRouteQuery() {}
  
  public RouteSearch$DriveRouteQuery(Parcel paramParcel)
  {
    a = ((RouteSearch.FromAndTo)paramParcel.readParcelable(RouteSearch.FromAndTo.class.getClassLoader()));
    b = paramParcel.readInt();
    c = paramParcel.createTypedArrayList(LatLonPoint.CREATOR);
    int j = paramParcel.readInt();
    if (j == 0) {}
    for (d = null;; d = new ArrayList())
    {
      int i = 0;
      while (i < j)
      {
        d.add(paramParcel.createTypedArrayList(LatLonPoint.CREATOR));
        i += 1;
      }
    }
    e = paramParcel.readString();
  }
  
  public RouteSearch$DriveRouteQuery(RouteSearch.FromAndTo paramFromAndTo, int paramInt, List<LatLonPoint> paramList, List<List<LatLonPoint>> paramList1, String paramString)
  {
    a = paramFromAndTo;
    b = paramInt;
    c = paramList;
    d = paramList1;
    e = paramString;
  }
  
  public DriveRouteQuery clone()
  {
    try
    {
      super.clone();
      return new DriveRouteQuery(a, b, c, d, e);
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      for (;;)
      {
        d.a(localCloneNotSupportedException, "RouteSearch", "DriveRouteQueryclone");
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
      do
      {
        return true;
        if (paramObject == null) {
          return false;
        }
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (DriveRouteQuery)paramObject;
        if (e == null)
        {
          if (e != null) {
            return false;
          }
        }
        else if (!e.equals(e)) {
          return false;
        }
        if (d == null)
        {
          if (d != null) {
            return false;
          }
        }
        else if (!d.equals(d)) {
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
        if (c != null) {
          break;
        }
      } while (c == null);
      return false;
    } while (c.equals(c));
    return false;
  }
  
  public String getAvoidRoad()
  {
    return e;
  }
  
  public List<List<LatLonPoint>> getAvoidpolygons()
  {
    return d;
  }
  
  public String getAvoidpolygonsStr()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if ((d == null) || (d.size() == 0)) {
      return null;
    }
    int i = 0;
    while (i < d.size())
    {
      List localList = (List)d.get(i);
      int j = 0;
      while (j < localList.size())
      {
        LatLonPoint localLatLonPoint = (LatLonPoint)localList.get(j);
        localStringBuffer.append(localLatLonPoint.getLongitude());
        localStringBuffer.append(",");
        localStringBuffer.append(localLatLonPoint.getLatitude());
        if (j < localList.size() - 1) {
          localStringBuffer.append(";");
        }
        j += 1;
      }
      if (i < d.size() - 1) {
        localStringBuffer.append("|");
      }
      i += 1;
    }
    return localStringBuffer.toString();
  }
  
  public RouteSearch.FromAndTo getFromAndTo()
  {
    return a;
  }
  
  public int getMode()
  {
    return b;
  }
  
  public List<LatLonPoint> getPassedByPoints()
  {
    return c;
  }
  
  public String getPassedPointStr()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if ((c == null) || (c.size() == 0)) {
      return null;
    }
    int i = 0;
    while (i < c.size())
    {
      LatLonPoint localLatLonPoint = (LatLonPoint)c.get(i);
      localStringBuffer.append(localLatLonPoint.getLongitude());
      localStringBuffer.append(",");
      localStringBuffer.append(localLatLonPoint.getLatitude());
      if (i < c.size() - 1) {
        localStringBuffer.append(";");
      }
      i += 1;
    }
    return localStringBuffer.toString();
  }
  
  public boolean hasAvoidRoad()
  {
    return !d.a(getAvoidRoad());
  }
  
  public boolean hasAvoidpolygons()
  {
    return !d.a(getAvoidpolygonsStr());
  }
  
  public boolean hasPassPoint()
  {
    return !d.a(getPassedPointStr());
  }
  
  public int hashCode()
  {
    int m = 0;
    int i;
    int j;
    label21:
    int k;
    label30:
    int n;
    if (e == null)
    {
      i = 0;
      if (d != null) {
        break label81;
      }
      j = 0;
      if (a != null) {
        break label94;
      }
      k = 0;
      n = b;
      if (c != null) {
        break label105;
      }
    }
    for (;;)
    {
      return ((k + (j + (i + 31) * 31) * 31) * 31 + n) * 31 + m;
      i = e.hashCode();
      break;
      label81:
      j = d.hashCode();
      break label21;
      label94:
      k = a.hashCode();
      break label30;
      label105:
      m = c.hashCode();
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(a, paramInt);
    paramParcel.writeInt(b);
    paramParcel.writeTypedList(c);
    if (d == null) {
      paramParcel.writeInt(0);
    }
    for (;;)
    {
      paramParcel.writeString(e);
      return;
      paramParcel.writeInt(d.size());
      Iterator localIterator = d.iterator();
      while (localIterator.hasNext()) {
        paramParcel.writeTypedList((List)localIterator.next());
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.RouteSearch.DriveRouteQuery
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */