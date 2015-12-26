package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.d;

public class RouteSearch$FromAndTo
  implements Parcelable, Cloneable
{
  public static final Parcelable.Creator<FromAndTo> CREATOR = new o();
  private LatLonPoint a;
  private LatLonPoint b;
  private String c;
  private String d;
  
  public RouteSearch$FromAndTo() {}
  
  public RouteSearch$FromAndTo(Parcel paramParcel)
  {
    a = ((LatLonPoint)paramParcel.readParcelable(LatLonPoint.class.getClassLoader()));
    b = ((LatLonPoint)paramParcel.readParcelable(LatLonPoint.class.getClassLoader()));
    c = paramParcel.readString();
    d = paramParcel.readString();
  }
  
  public RouteSearch$FromAndTo(LatLonPoint paramLatLonPoint1, LatLonPoint paramLatLonPoint2)
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

/* Location:
 * Qualified Name:     com.amap.api.services.route.RouteSearch.FromAndTo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */