package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

public class RouteBusLineItem
  extends BusLineItem
  implements Parcelable
{
  public static final Parcelable.Creator<RouteBusLineItem> CREATOR = new j();
  private BusStationItem a;
  private BusStationItem b;
  private List<LatLonPoint> c = new ArrayList();
  private int d;
  private List<BusStationItem> e = new ArrayList();
  private float f;
  
  public RouteBusLineItem() {}
  
  public RouteBusLineItem(Parcel paramParcel)
  {
    super(paramParcel);
    a = ((BusStationItem)paramParcel.readParcelable(BusStationItem.class.getClassLoader()));
    b = ((BusStationItem)paramParcel.readParcelable(BusStationItem.class.getClassLoader()));
    c = paramParcel.createTypedArrayList(LatLonPoint.CREATOR);
    d = paramParcel.readInt();
    e = paramParcel.createTypedArrayList(BusStationItem.CREATOR);
    f = paramParcel.readFloat();
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
        if (!super.equals(paramObject)) {
          return false;
        }
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (RouteBusLineItem)paramObject;
        if (b == null)
        {
          if (b != null) {
            return false;
          }
        }
        else if (!b.equals(b)) {
          return false;
        }
        if (a != null) {
          break;
        }
      } while (a == null);
      return false;
    } while (a.equals(a));
    return false;
  }
  
  public BusStationItem getArrivalBusStation()
  {
    return b;
  }
  
  public BusStationItem getDepartureBusStation()
  {
    return a;
  }
  
  public float getDuration()
  {
    return f;
  }
  
  public int getPassStationNum()
  {
    return d;
  }
  
  public List<BusStationItem> getPassStations()
  {
    return e;
  }
  
  public List<LatLonPoint> getPolyline()
  {
    return c;
  }
  
  public int hashCode()
  {
    int j = 0;
    int k = super.hashCode();
    int i;
    if (b == null)
    {
      i = 0;
      if (a != null) {
        break label46;
      }
    }
    for (;;)
    {
      return (i + k * 31) * 31 + j;
      i = b.hashCode();
      break;
      label46:
      j = a.hashCode();
    }
  }
  
  public void setArrivalBusStation(BusStationItem paramBusStationItem)
  {
    b = paramBusStationItem;
  }
  
  public void setDepartureBusStation(BusStationItem paramBusStationItem)
  {
    a = paramBusStationItem;
  }
  
  public void setDuration(float paramFloat)
  {
    f = paramFloat;
  }
  
  public void setPassStationNum(int paramInt)
  {
    d = paramInt;
  }
  
  public void setPassStations(List<BusStationItem> paramList)
  {
    e = paramList;
  }
  
  public void setPolyline(List<LatLonPoint> paramList)
  {
    c = paramList;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeParcelable(a, paramInt);
    paramParcel.writeParcelable(b, paramInt);
    paramParcel.writeTypedList(c);
    paramParcel.writeInt(d);
    paramParcel.writeTypedList(e);
    paramParcel.writeFloat(f);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.RouteBusLineItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */