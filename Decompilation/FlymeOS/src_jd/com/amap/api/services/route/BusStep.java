package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class BusStep
  implements Parcelable
{
  public static final Parcelable.Creator<BusStep> CREATOR = new c();
  private RouteBusWalkItem a;
  private List<RouteBusLineItem> b = new ArrayList();
  private Doorway c;
  private Doorway d;
  
  public BusStep() {}
  
  public BusStep(Parcel paramParcel)
  {
    a = ((RouteBusWalkItem)paramParcel.readParcelable(RouteBusWalkItem.class.getClassLoader()));
    b = paramParcel.createTypedArrayList(RouteBusLineItem.CREATOR);
    c = ((Doorway)paramParcel.readParcelable(Doorway.class.getClassLoader()));
    d = ((Doorway)paramParcel.readParcelable(Doorway.class.getClassLoader()));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public RouteBusLineItem getBusLine()
  {
    if ((b == null) || (b.size() == 0)) {
      return null;
    }
    return (RouteBusLineItem)b.get(0);
  }
  
  public Doorway getEntrance()
  {
    return c;
  }
  
  public Doorway getExit()
  {
    return d;
  }
  
  public RouteBusWalkItem getWalk()
  {
    return a;
  }
  
  public void setBusLine(RouteBusLineItem paramRouteBusLineItem)
  {
    if (b == null) {
      return;
    }
    if (b.size() == 0) {
      b.add(paramRouteBusLineItem);
    }
    b.set(0, paramRouteBusLineItem);
  }
  
  public void setBusLines(List<RouteBusLineItem> paramList)
  {
    b = paramList;
  }
  
  public void setEntrance(Doorway paramDoorway)
  {
    c = paramDoorway;
  }
  
  public void setExit(Doorway paramDoorway)
  {
    d = paramDoorway;
  }
  
  public void setWalk(RouteBusWalkItem paramRouteBusWalkItem)
  {
    a = paramRouteBusWalkItem;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(a, paramInt);
    paramParcel.writeTypedList(b);
    paramParcel.writeParcelable(c, paramInt);
    paramParcel.writeParcelable(d, paramInt);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.BusStep
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */