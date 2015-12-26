package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class Doorway
  implements Parcelable
{
  public static final Parcelable.Creator<Doorway> CREATOR = new e();
  private String a;
  private LatLonPoint b;
  
  public Doorway() {}
  
  public Doorway(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = ((LatLonPoint)paramParcel.readParcelable(LatLonPoint.class.getClassLoader()));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public LatLonPoint getLatLonPoint()
  {
    return b;
  }
  
  public String getName()
  {
    return a;
  }
  
  public void setLatLonPoint(LatLonPoint paramLatLonPoint)
  {
    b = paramLatLonPoint;
  }
  
  public void setName(String paramString)
  {
    a = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(a);
    paramParcel.writeParcelable(b, paramInt);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.Doorway
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */