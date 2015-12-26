package com.amap.api.location.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class GeoPoint
  implements Parcelable
{
  public static final Parcelable.Creator<GeoPoint> CREATOR = new f();
  private long a = Long.MIN_VALUE;
  private long b = Long.MIN_VALUE;
  private double c = Double.MIN_VALUE;
  private double d = Double.MIN_VALUE;
  
  public GeoPoint()
  {
    a = 0L;
    b = 0L;
  }
  
  public GeoPoint(int paramInt1, int paramInt2)
  {
    a = paramInt1;
    b = paramInt2;
  }
  
  public GeoPoint(long paramLong1, long paramLong2)
  {
    a = paramLong1;
    b = paramLong2;
  }
  
  private GeoPoint(Parcel paramParcel)
  {
    a = paramParcel.readLong();
    b = paramParcel.readLong();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    do
    {
      do
      {
        return false;
      } while (paramObject.getClass() != getClass());
      paramObject = (GeoPoint)paramObject;
    } while ((c != c) || (d != d) || (a != a) || (b != b));
    return true;
  }
  
  public int getLatitudeE6()
  {
    return (int)a;
  }
  
  public int getLongitudeE6()
  {
    return (int)b;
  }
  
  public int hashCode()
  {
    return (int)(d * 7.0D + c * 11.0D);
  }
  
  public String toString()
  {
    return "" + a + "," + b;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeLong(a);
    paramParcel.writeLong(b);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.core.GeoPoint
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */