package com.amap.api.services.route;

import android.content.Context;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.a;
import com.amap.api.services.core.d;
import com.amap.api.services.core.f;
import com.amap.api.services.core.l;
import com.amap.api.services.core.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RouteSearch
{
  public static final int BusComfortable = 4;
  public static final int BusDefault = 0;
  public static final int BusLeaseChange = 2;
  public static final int BusLeaseWalk = 3;
  public static final int BusNoSubway = 5;
  public static final int BusSaveMoney = 1;
  public static final int DrivingAvoidCongestion = 4;
  public static final int DrivingDefault = 0;
  public static final int DrivingMultiStrategy = 5;
  public static final int DrivingNoExpressways = 3;
  public static final int DrivingNoHighAvoidCongestionSaveMoney = 9;
  public static final int DrivingNoHighWay = 6;
  public static final int DrivingNoHighWaySaveMoney = 7;
  public static final int DrivingSaveMoney = 1;
  public static final int DrivingSaveMoneyAvoidCongestion = 8;
  public static final int DrivingShortDistance = 2;
  public static final int WalkDefault = 0;
  public static final int WalkMultipath = 1;
  private OnRouteSearchListener a;
  private Context b;
  private Handler c;
  
  public RouteSearch(Context paramContext)
  {
    b = paramContext.getApplicationContext();
    c = com.amap.api.services.core.p.a();
  }
  
  public BusRouteResult calculateBusRoute(BusRouteQuery paramBusRouteQuery)
  {
    l.a(b);
    paramBusRouteQuery = paramBusRouteQuery.clone();
    BusRouteResult localBusRouteResult = (BusRouteResult)new a(b, paramBusRouteQuery).g();
    if (localBusRouteResult != null) {
      localBusRouteResult.setBusQuery(paramBusRouteQuery);
    }
    return localBusRouteResult;
  }
  
  public void calculateBusRouteAsyn(BusRouteQuery paramBusRouteQuery)
  {
    new RouteSearch.2(this, paramBusRouteQuery).start();
  }
  
  public DriveRouteResult calculateDriveRoute(DriveRouteQuery paramDriveRouteQuery)
  {
    l.a(b);
    paramDriveRouteQuery = paramDriveRouteQuery.clone();
    DriveRouteResult localDriveRouteResult = (DriveRouteResult)new f(b, paramDriveRouteQuery).g();
    if (localDriveRouteResult != null) {
      localDriveRouteResult.setDriveQuery(paramDriveRouteQuery);
    }
    return localDriveRouteResult;
  }
  
  public void calculateDriveRouteAsyn(DriveRouteQuery paramDriveRouteQuery)
  {
    new RouteSearch.3(this, paramDriveRouteQuery).start();
  }
  
  public WalkRouteResult calculateWalkRoute(WalkRouteQuery paramWalkRouteQuery)
  {
    l.a(b);
    paramWalkRouteQuery = paramWalkRouteQuery.clone();
    WalkRouteResult localWalkRouteResult = (WalkRouteResult)new u(b, paramWalkRouteQuery).g();
    if (localWalkRouteResult != null) {
      localWalkRouteResult.setWalkQuery(paramWalkRouteQuery);
    }
    return localWalkRouteResult;
  }
  
  public void calculateWalkRouteAsyn(WalkRouteQuery paramWalkRouteQuery)
  {
    new RouteSearch.1(this, paramWalkRouteQuery).start();
  }
  
  public void setRouteSearchListener(OnRouteSearchListener paramOnRouteSearchListener)
  {
    a = paramOnRouteSearchListener;
  }
  
  public static class BusRouteQuery
    implements Parcelable, Cloneable
  {
    public static final Parcelable.Creator<BusRouteQuery> CREATOR = new m();
    private RouteSearch.FromAndTo a;
    private int b;
    private String c;
    private int d;
    
    public BusRouteQuery() {}
    
    public BusRouteQuery(Parcel paramParcel)
    {
      a = ((RouteSearch.FromAndTo)paramParcel.readParcelable(RouteSearch.FromAndTo.class.getClassLoader()));
      b = paramParcel.readInt();
      c = paramParcel.readString();
      d = paramParcel.readInt();
    }
    
    public BusRouteQuery(RouteSearch.FromAndTo paramFromAndTo, int paramInt1, String paramString, int paramInt2)
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
  
  public static class DriveRouteQuery
    implements Parcelable, Cloneable
  {
    public static final Parcelable.Creator<DriveRouteQuery> CREATOR = new n();
    private RouteSearch.FromAndTo a;
    private int b;
    private List<LatLonPoint> c;
    private List<List<LatLonPoint>> d;
    private String e;
    
    public DriveRouteQuery() {}
    
    public DriveRouteQuery(Parcel paramParcel)
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
    
    public DriveRouteQuery(RouteSearch.FromAndTo paramFromAndTo, int paramInt, List<LatLonPoint> paramList, List<List<LatLonPoint>> paramList1, String paramString)
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
  
  public static class FromAndTo
    implements Parcelable, Cloneable
  {
    public static final Parcelable.Creator<FromAndTo> CREATOR = new o();
    private LatLonPoint a;
    private LatLonPoint b;
    private String c;
    private String d;
    
    public FromAndTo() {}
    
    public FromAndTo(Parcel paramParcel)
    {
      a = ((LatLonPoint)paramParcel.readParcelable(LatLonPoint.class.getClassLoader()));
      b = ((LatLonPoint)paramParcel.readParcelable(LatLonPoint.class.getClassLoader()));
      c = paramParcel.readString();
      d = paramParcel.readString();
    }
    
    public FromAndTo(LatLonPoint paramLatLonPoint1, LatLonPoint paramLatLonPoint2)
    {
      a = paramLatLonPoint1;
      b = paramLatLonPoint2;
    }
    
    public FromAndTo clone()
    {
      try
      {
        super.clone();
        FromAndTo localFromAndTo = new FromAndTo(a, b);
        localFromAndTo.setStartPoiID(c);
        localFromAndTo.setDestinationPoiID(d);
        return localFromAndTo;
      }
      catch (CloneNotSupportedException localCloneNotSupportedException)
      {
        for (;;)
        {
          d.a(localCloneNotSupportedException, "RouteSearch", "FromAndToclone");
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
          paramObject = (FromAndTo)paramObject;
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
          if (c == null)
          {
            if (c != null) {
              return false;
            }
          }
          else if (!c.equals(c)) {
            return false;
          }
          if (b != null) {
            break;
          }
        } while (b == null);
        return false;
      } while (b.equals(b));
      return false;
    }
    
    public String getDestinationPoiID()
    {
      return d;
    }
    
    public LatLonPoint getFrom()
    {
      return a;
    }
    
    public String getStartPoiID()
    {
      return c;
    }
    
    public LatLonPoint getTo()
    {
      return b;
    }
    
    public int hashCode()
    {
      int m = 0;
      int i;
      int j;
      label21:
      int k;
      if (d == null)
      {
        i = 0;
        if (a != null) {
          break label69;
        }
        j = 0;
        if (c != null) {
          break label80;
        }
        k = 0;
        label30:
        if (b != null) {
          break label91;
        }
      }
      for (;;)
      {
        return (k + (j + (i + 31) * 31) * 31) * 31 + m;
        i = d.hashCode();
        break;
        label69:
        j = a.hashCode();
        break label21;
        label80:
        k = c.hashCode();
        break label30;
        label91:
        m = b.hashCode();
      }
    }
    
    public void setDestinationPoiID(String paramString)
    {
      d = paramString;
    }
    
    public void setStartPoiID(String paramString)
    {
      c = paramString;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeParcelable(a, paramInt);
      paramParcel.writeParcelable(b, paramInt);
      paramParcel.writeString(c);
      paramParcel.writeString(d);
    }
  }
  
  public static abstract interface OnRouteSearchListener
  {
    public abstract void onBusRouteSearched(BusRouteResult paramBusRouteResult, int paramInt);
    
    public abstract void onDriveRouteSearched(DriveRouteResult paramDriveRouteResult, int paramInt);
    
    public abstract void onWalkRouteSearched(WalkRouteResult paramWalkRouteResult, int paramInt);
  }
  
  public static class WalkRouteQuery
    implements Parcelable, Cloneable
  {
    public static final Parcelable.Creator<WalkRouteQuery> CREATOR = new p();
    private RouteSearch.FromAndTo a;
    private int b;
    
    public WalkRouteQuery() {}
    
    public WalkRouteQuery(Parcel paramParcel)
    {
      a = ((RouteSearch.FromAndTo)paramParcel.readParcelable(RouteSearch.FromAndTo.class.getClassLoader()));
      b = paramParcel.readInt();
    }
    
    public WalkRouteQuery(RouteSearch.FromAndTo paramFromAndTo, int paramInt)
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
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.RouteSearch
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */