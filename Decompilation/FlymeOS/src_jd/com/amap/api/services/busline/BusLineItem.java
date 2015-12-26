package com.amap.api.services.busline;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.d;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusLineItem
  implements Parcelable
{
  public static final Parcelable.Creator<BusLineItem> CREATOR = new a();
  private float a;
  private String b;
  private String c;
  private String d;
  private List<LatLonPoint> e = new ArrayList();
  private List<LatLonPoint> f = new ArrayList();
  private String g;
  private String h;
  private String i;
  private Date j;
  private Date k;
  private String l;
  private float m;
  private float n;
  private List<BusStationItem> o = new ArrayList();
  
  public BusLineItem() {}
  
  public BusLineItem(Parcel paramParcel)
  {
    a = paramParcel.readFloat();
    b = paramParcel.readString();
    c = paramParcel.readString();
    d = paramParcel.readString();
    e = paramParcel.readArrayList(LatLonPoint.class.getClassLoader());
    f = paramParcel.readArrayList(LatLonPoint.class.getClassLoader());
    g = paramParcel.readString();
    h = paramParcel.readString();
    i = paramParcel.readString();
    j = d.e(paramParcel.readString());
    k = d.e(paramParcel.readString());
    l = paramParcel.readString();
    m = paramParcel.readFloat();
    n = paramParcel.readFloat();
    o = paramParcel.readArrayList(BusStationItem.class.getClassLoader());
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
        paramObject = (BusLineItem)paramObject;
        if (g != null) {
          break;
        }
      } while (g == null);
      return false;
    } while (g.equals(g));
    return false;
  }
  
  public float getBasicPrice()
  {
    return m;
  }
  
  public List<LatLonPoint> getBounds()
  {
    return f;
  }
  
  public String getBusCompany()
  {
    return l;
  }
  
  public String getBusLineId()
  {
    return g;
  }
  
  public String getBusLineName()
  {
    return b;
  }
  
  public String getBusLineType()
  {
    return c;
  }
  
  public List<BusStationItem> getBusStations()
  {
    return o;
  }
  
  public String getCityCode()
  {
    return d;
  }
  
  public List<LatLonPoint> getDirectionsCoordinates()
  {
    return e;
  }
  
  public float getDistance()
  {
    return a;
  }
  
  public Date getFirstBusTime()
  {
    if (j == null) {
      return null;
    }
    return (Date)j.clone();
  }
  
  public Date getLastBusTime()
  {
    if (k == null) {
      return null;
    }
    return (Date)k.clone();
  }
  
  public String getOriginatingStation()
  {
    return h;
  }
  
  public String getTerminalStation()
  {
    return i;
  }
  
  public float getTotalPrice()
  {
    return n;
  }
  
  public int hashCode()
  {
    if (g == null) {}
    for (int i1 = 0;; i1 = g.hashCode()) {
      return i1 + 31;
    }
  }
  
  public void setBasicPrice(float paramFloat)
  {
    m = paramFloat;
  }
  
  public void setBounds(List<LatLonPoint> paramList)
  {
    f = paramList;
  }
  
  public void setBusCompany(String paramString)
  {
    l = paramString;
  }
  
  public void setBusLineId(String paramString)
  {
    g = paramString;
  }
  
  public void setBusLineName(String paramString)
  {
    b = paramString;
  }
  
  public void setBusLineType(String paramString)
  {
    c = paramString;
  }
  
  public void setBusStations(List<BusStationItem> paramList)
  {
    o = paramList;
  }
  
  public void setCityCode(String paramString)
  {
    d = paramString;
  }
  
  public void setDirectionsCoordinates(List<LatLonPoint> paramList)
  {
    e = paramList;
  }
  
  public void setDistance(float paramFloat)
  {
    a = paramFloat;
  }
  
  public void setFirstBusTime(Date paramDate)
  {
    if (paramDate == null)
    {
      j = null;
      return;
    }
    j = ((Date)paramDate.clone());
  }
  
  public void setLastBusTime(Date paramDate)
  {
    if (paramDate == null)
    {
      k = null;
      return;
    }
    k = ((Date)paramDate.clone());
  }
  
  public void setOriginatingStation(String paramString)
  {
    h = paramString;
  }
  
  public void setTerminalStation(String paramString)
  {
    i = paramString;
  }
  
  public void setTotalPrice(float paramFloat)
  {
    n = paramFloat;
  }
  
  public String toString()
  {
    return b + " " + d.a(j) + "-" + d.a(k);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeFloat(a);
    paramParcel.writeString(b);
    paramParcel.writeString(c);
    paramParcel.writeString(d);
    paramParcel.writeList(e);
    paramParcel.writeList(f);
    paramParcel.writeString(g);
    paramParcel.writeString(h);
    paramParcel.writeString(i);
    paramParcel.writeString(d.a(j));
    paramParcel.writeString(d.a(k));
    paramParcel.writeString(l);
    paramParcel.writeFloat(m);
    paramParcel.writeFloat(n);
    paramParcel.writeList(o);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.busline.BusLineItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */