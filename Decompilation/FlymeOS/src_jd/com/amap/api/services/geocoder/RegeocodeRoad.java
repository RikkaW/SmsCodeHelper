package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public final class RegeocodeRoad
  implements Parcelable
{
  public static final Parcelable.Creator<RegeocodeRoad> CREATOR = new d();
  private String a;
  private String b;
  private float c;
  private String d;
  private LatLonPoint e;
  
  public RegeocodeRoad() {}
  
  private RegeocodeRoad(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.readString();
    c = paramParcel.readFloat();
    d = paramParcel.readString();
    e = ((LatLonPoint)paramParcel.readValue(LatLonPoint.class.getClassLoader()));
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
    return c;
  }
  
  public String getId()
  {
    return a;
  }
  
  public LatLonPoint getLatLngPoint()
  {
    return e;
  }
  
  public String getName()
  {
    return b;
  }
  
  public void setDirection(String paramString)
  {
    d = paramString;
  }
  
  public void setDistance(float paramFloat)
  {
    c = paramFloat;
  }
  
  public void setId(String paramString)
  {
    a = paramString;
  }
  
  public void setLatLngPoint(LatLonPoint paramLatLonPoint)
  {
    e = paramLatLonPoint;
  }
  
  public void setName(String paramString)
  {
    b = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(a);
    paramParcel.writeString(b);
    paramParcel.writeFloat(c);
    paramParcel.writeString(d);
    paramParcel.writeValue(e);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.geocoder.RegeocodeRoad
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */