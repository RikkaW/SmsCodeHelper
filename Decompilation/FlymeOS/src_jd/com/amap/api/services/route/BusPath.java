package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class BusPath
  extends Path
  implements Parcelable
{
  public static final Parcelable.Creator<BusPath> CREATOR = new a();
  private float a;
  private boolean b;
  private float c;
  private float d;
  private List<BusStep> e = new ArrayList();
  
  public BusPath() {}
  
  public BusPath(Parcel paramParcel)
  {
    super(paramParcel);
    a = paramParcel.readFloat();
    boolean[] arrayOfBoolean = new boolean[1];
    paramParcel.readBooleanArray(arrayOfBoolean);
    b = arrayOfBoolean[0];
    c = paramParcel.readFloat();
    d = paramParcel.readFloat();
    e = paramParcel.createTypedArrayList(BusStep.CREATOR);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public float getBusDistance()
  {
    return d;
  }
  
  public float getCost()
  {
    return a;
  }
  
  public float getDistance()
  {
    return c + d;
  }
  
  public List<BusStep> getSteps()
  {
    return e;
  }
  
  public float getWalkDistance()
  {
    return c;
  }
  
  public boolean isNightBus()
  {
    return b;
  }
  
  public void setBusDistance(float paramFloat)
  {
    d = paramFloat;
  }
  
  public void setCost(float paramFloat)
  {
    a = paramFloat;
  }
  
  public void setNightBus(boolean paramBoolean)
  {
    b = paramBoolean;
  }
  
  public void setSteps(List<BusStep> paramList)
  {
    e = paramList;
  }
  
  public void setWalkDistance(float paramFloat)
  {
    c = paramFloat;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeFloat(a);
    paramParcel.writeBooleanArray(new boolean[] { b });
    paramParcel.writeFloat(c);
    paramParcel.writeFloat(d);
    paramParcel.writeTypedList(e);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.BusPath
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */