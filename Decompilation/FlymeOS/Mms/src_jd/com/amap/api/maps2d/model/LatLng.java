package com.amap.api.maps2d.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class LatLng
  implements Parcelable, Cloneable
{
  public static final f CREATOR = new f();
  private static DecimalFormat a = new DecimalFormat("0.000000", new DecimalFormatSymbols(Locale.US));
  public final double latitude;
  public final double longitude;
  
  public LatLng(double paramDouble1, double paramDouble2)
  {
    latitude = paramDouble1;
    longitude = paramDouble2;
  }
  
  public LatLng clone()
  {
    return new LatLng(latitude, longitude);
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
      if (!(paramObject instanceof LatLng)) {
        return false;
      }
      paramObject = (LatLng)paramObject;
    } while ((Double.doubleToLongBits(latitude) == Double.doubleToLongBits(latitude)) && (Double.doubleToLongBits(longitude) == Double.doubleToLongBits(longitude)));
    return false;
  }
  
  public int hashCode()
  {
    long l = Double.doubleToLongBits(latitude);
    int i = (int)(l ^ l >>> 32);
    l = Double.doubleToLongBits(longitude);
    return 31 * (i + 31) + (int)(l ^ l >>> 32);
  }
  
  public String toString()
  {
    return "lat/lng: (" + latitude + "," + longitude + ")";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeDouble(longitude);
    paramParcel.writeDouble(latitude);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.LatLng
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */