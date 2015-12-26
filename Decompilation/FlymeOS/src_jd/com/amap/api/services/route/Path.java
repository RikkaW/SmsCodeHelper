package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Path
  implements Parcelable
{
  public static final Parcelable.Creator<Path> CREATOR = new i();
  private float a;
  private long b;
  
  public Path() {}
  
  public Path(Parcel paramParcel)
  {
    a = paramParcel.readFloat();
    b = paramParcel.readLong();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public float getDistance()
  {
    return a;
  }
  
  public long getDuration()
  {
    return b;
  }
  
  public void setDistance(float paramFloat)
  {
    a = paramFloat;
  }
  
  public void setDuration(long paramLong)
  {
    b = paramLong;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeFloat(a);
    paramParcel.writeLong(b);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.Path
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */