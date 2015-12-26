package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class DrivePath
  extends Path
  implements Parcelable
{
  public static final Parcelable.Creator<DrivePath> CREATOR = new f();
  private String a;
  private float b;
  private float c;
  private List<DriveStep> d = new ArrayList();
  
  public DrivePath() {}
  
  public DrivePath(Parcel paramParcel)
  {
    super(paramParcel);
    a = paramParcel.readString();
    b = paramParcel.readFloat();
    c = paramParcel.readFloat();
    d = paramParcel.createTypedArrayList(DriveStep.CREATOR);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public List<DriveStep> getSteps()
  {
    return d;
  }
  
  public String getStrategy()
  {
    return a;
  }
  
  public float getTollDistance()
  {
    return c;
  }
  
  public float getTolls()
  {
    return b;
  }
  
  public void setSteps(List<DriveStep> paramList)
  {
    d = paramList;
  }
  
  public void setStrategy(String paramString)
  {
    a = paramString;
  }
  
  public void setTollDistance(float paramFloat)
  {
    c = paramFloat;
  }
  
  public void setTolls(float paramFloat)
  {
    b = paramFloat;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(a);
    paramParcel.writeFloat(b);
    paramParcel.writeFloat(c);
    paramParcel.writeTypedList(d);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.DrivePath
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */