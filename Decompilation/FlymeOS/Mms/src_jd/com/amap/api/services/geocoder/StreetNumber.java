package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public final class StreetNumber
  implements Parcelable
{
  public static final Parcelable.Creator<StreetNumber> CREATOR = new e();
  private String a;
  private String b;
  private LatLonPoint c;
  private String d;
  private float e;
  
  public StreetNumber() {}
  
  private StreetNumber(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.readString();
    c = ((LatLonPoint)paramParcel.readValue(LatLonPoint.class.getClassLoader()));
    d = paramParcel.readString();
    e = paramParcel.readFloat();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getDirection()
  {
    return d;
  }
  
  public float getDistance()
  {
    return e;
  }
  
  public LatLonPoint getLatLonPoint()
  {
    return c;
  }
  
  public String getNumber()
  {
    return b;
  }
  
  public String getStreet()
  {
    return a;
  }
  
  public void setDirection(String paramString)
  {
    d = paramString;
  }
  
  public void setDistance(float paramFloat)
  {
    e = paramFloat;
  }
  
  public void setLatLonPoint(LatLonPoint paramLatLonPoint)
  {
    c = paramLatLonPoint;
  }
  
  public void setNumber(String paramString)
  {
    b = paramString;
  }
  
  public void setStreet(String paramString)
  {
    a = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(a);
    paramParcel.writeString(b);
    paramParcel.writeValue(c);
    paramParcel.writeString(d);
    paramParcel.writeFloat(e);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.geocoder.StreetNumber
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */