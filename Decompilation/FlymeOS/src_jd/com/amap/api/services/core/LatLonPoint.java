package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class LatLonPoint
  implements Parcelable
{
  public static final Parcelable.Creator<LatLonPoint> CREATOR = new k();
  private double a;
  private double b;
  
  public LatLonPoint(double paramDouble1, double paramDouble2)
  {
    a = paramDouble1;
    b = paramDouble2;
  }
  
  private LatLonPoint(Parcel paramParcel)
  {
    a = paramParcel.readDouble();
    b = paramParcel.readDouble();
  }
  
  public LatLonPoint copy()
  {
    return new LatLonPoint(a, b);
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
      paramObject = (LatLonPoint)paramObject;
      if (Double.doubleToLongBits(a) != Double.doubleToLongBits(a)) {
        return false;
      }
    } while (Double.doubleToLongBits(b) == Double.doubleToLongBits(b));
    return false;
  }
  
  public double getLatitude()
  {
    return a;
  }
  
  public double getLongitude()
  {
    return b;
  }
  
  public int hashCode()
  {
    long l = Double.doubleToLongBits(a);
    int i = (int)(l ^ l >>> 32);
    l = Double.doubleToLongBits(b);
    return (i + 31) * 31 + (int)(l ^ l >>> 32);
  }
  
  public void setLatitude(double paramDouble)
  {
    a = paramDouble;
  }
  
  public void setLongitude(double paramDouble)
  {
    b = paramDouble;
  }
  
  public String toString()
  {
    return "" + a + "," + b;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeDouble(a);
    paramParcel.writeDouble(b);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.LatLonPoint
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */