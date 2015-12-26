package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class WalkPath
  extends Path
  implements Parcelable
{
  public static final Parcelable.Creator<WalkPath> CREATOR = new s();
  private List<WalkStep> a = new ArrayList();
  
  public WalkPath() {}
  
  public WalkPath(Parcel paramParcel)
  {
    super(paramParcel);
    a = paramParcel.createTypedArrayList(WalkStep.CREATOR);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public List<WalkStep> getSteps()
  {
    return a;
  }
  
  public void setSteps(List<WalkStep> paramList)
  {
    a = paramList;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeTypedList(a);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.WalkPath
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */