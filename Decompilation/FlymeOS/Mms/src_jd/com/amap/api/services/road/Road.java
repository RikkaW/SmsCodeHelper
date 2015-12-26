package com.amap.api.services.road;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class Road
  implements Parcelable
{
  public static final Parcelable.Creator<Road> CREATOR = new b();
  private String a;
  private String b;
  private String c;
  private float d;
  private String e;
  private LatLonPoint f;
  
  public Road() {}
  
  private Road(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.readString();
    c = paramParcel.readString();
    d = paramParcel.readFloat();
    e = paramParcel.readString();
    f = ((LatLonPoint)paramParcel.readValue(LatLonPoint.class.getClassLoader()));
  }
  
  public Road(String paramString1, String paramString2)
  {
    a = paramString1;
    b = paramString2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public LatLonPoint getCenterPoint()
  {
    return f;
  }
  
  public String getCityCode()
  {
    return c;
  }
  
  public String getId()
  {
    return a;
  }
  
  public String getName()
  {
    return b;
  }
  
  public float getRoadWidth()
  {
    return d;
  }
  
  public String getType()
  {
    return e;
  }
  
  public void setCenterPoint(LatLonPoint paramLatLonPoint)
  {
    f = paramLatLonPoint;
  }
  
  public void setCityCode(String paramString)
  {
    c = paramString;
  }
  
  public void setId(String paramString)
  {
    a = paramString;
  }
  
  public void setName(String paramString)
  {
    b = paramString;
  }
  
  public void setRoadWidth(float paramFloat)
  {
    d = paramFloat;
  }
  
  public void setType(String paramString)
  {
    e = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(a);
    paramParcel.writeString(b);
    paramParcel.writeString(c);
    paramParcel.writeFloat(d);
    paramParcel.writeString(e);
    paramParcel.writeValue(f);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.road.Road
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */