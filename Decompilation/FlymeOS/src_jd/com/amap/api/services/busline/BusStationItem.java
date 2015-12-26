package com.amap.api.services.busline;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

public class BusStationItem
  implements Parcelable
{
  public static final Parcelable.Creator<BusStationItem> CREATOR = new b();
  private String a;
  private String b;
  private LatLonPoint c;
  private String d;
  private String e;
  private List<BusLineItem> f = new ArrayList();
  
  public BusStationItem() {}
  
  private BusStationItem(Parcel paramParcel)
  {
    b = paramParcel.readString();
    a = paramParcel.readString();
    c = ((LatLonPoint)paramParcel.readValue(LatLonPoint.class.getClassLoader()));
    d = paramParcel.readString();
    e = paramParcel.readString();
    f = paramParcel.readArrayList(BusLineItem.class.getClassLoader());
  }
  
  private String a(List<BusLineItem> paramList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (paramList != null)
    {
      int i = 0;
      while (i < paramList.size())
      {
        localStringBuffer.append(((BusLineItem)paramList.get(i)).getBusLineName());
        if (i < paramList.size() - 1) {
          localStringBuffer.append("|");
        }
        i += 1;
      }
    }
    return localStringBuffer.toString();
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
        paramObject = (BusStationItem)paramObject;
        if (a != null) {
          break;
        }
      } while (a == null);
      return false;
    } while (a.equals(a));
    return false;
  }
  
  public String getAdCode()
  {
    return e;
  }
  
  public List<BusLineItem> getBusLineItems()
  {
    return f;
  }
  
  public String getBusStationId()
  {
    return a;
  }
  
  public String getBusStationName()
  {
    return b;
  }
  
  public String getCityCode()
  {
    return d;
  }
  
  public LatLonPoint getLatLonPoint()
  {
    return c;
  }
  
  public int hashCode()
  {
    if (a == null) {}
    for (int i = 0;; i = a.hashCode()) {
      return i + 31;
    }
  }
  
  public void setAdCode(String paramString)
  {
    e = paramString;
  }
  
  public void setBusLineItems(List<BusLineItem> paramList)
  {
    f = paramList;
  }
  
  public void setBusStationId(String paramString)
  {
    a = paramString;
  }
  
  public void setBusStationName(String paramString)
  {
    b = paramString;
  }
  
  public void setCityCode(String paramString)
  {
    d = paramString;
  }
  
  public void setLatLonPoint(LatLonPoint paramLatLonPoint)
  {
    c = paramLatLonPoint;
  }
  
  public String toString()
  {
    return "BusStationName: " + b + " LatLonPoint: " + c.toString() + " BusLines: " + a(f) + " CityCode: " + d + " AdCode: " + e;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(b);
    paramParcel.writeString(a);
    paramParcel.writeValue(c);
    paramParcel.writeString(d);
    paramParcel.writeString(e);
    paramParcel.writeList(f);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.busline.BusStationItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */