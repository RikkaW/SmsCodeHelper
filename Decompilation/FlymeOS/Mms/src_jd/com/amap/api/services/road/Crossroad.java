package com.amap.api.services.road;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class Crossroad
  extends Road
  implements Parcelable
{
  public static final Parcelable.Creator<Crossroad> CREATOR = new a();
  private float a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  
  public Crossroad() {}
  
  private Crossroad(Parcel paramParcel)
  {
    a = paramParcel.readFloat();
    b = paramParcel.readString();
    c = paramParcel.readString();
    d = paramParcel.readString();
    e = paramParcel.readString();
    f = paramParcel.readString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getDirection()
  {
    return b;
  }
  
  public float getDistance()
  {
    return a;
  }
  
  public String getFirstRoadId()
  {
    return c;
  }
  
  public String getFirstRoadName()
  {
    return d;
  }
  
  public String getSecondRoadId()
  {
    return e;
  }
  
  public String getSecondRoadName()
  {
    return f;
  }
  
  public void setDirection(String paramString)
  {
    b = paramString;
  }
  
  public void setDistance(float paramFloat)
  {
    a = paramFloat;
  }
  
  public void setFirstRoadId(String paramString)
  {
    c = paramString;
  }
  
  public void setFirstRoadName(String paramString)
  {
    d = paramString;
  }
  
  public void setSecondRoadId(String paramString)
  {
    e = paramString;
  }
  
  public void setSecondRoadName(String paramString)
  {
    f = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeFloat(a);
    paramParcel.writeString(b);
    paramParcel.writeString(c);
    paramParcel.writeString(d);
    paramParcel.writeString(e);
    paramParcel.writeString(f);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.road.Crossroad
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */