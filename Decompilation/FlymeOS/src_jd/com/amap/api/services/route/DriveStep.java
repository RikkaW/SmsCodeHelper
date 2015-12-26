package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

public class DriveStep
  implements Parcelable
{
  public static final Parcelable.Creator<DriveStep> CREATOR = new h();
  private String a;
  private String b;
  private String c;
  private float d;
  private float e;
  private float f;
  private String g;
  private float h;
  private List<LatLonPoint> i = new ArrayList();
  private String j;
  private String k;
  private List<RouteSearchCity> l = new ArrayList();
  
  public DriveStep() {}
  
  public DriveStep(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.readString();
    c = paramParcel.readString();
    d = paramParcel.readFloat();
    e = paramParcel.readFloat();
    f = paramParcel.readFloat();
    g = paramParcel.readString();
    h = paramParcel.readFloat();
    i = paramParcel.createTypedArrayList(LatLonPoint.CREATOR);
    j = paramParcel.readString();
    k = paramParcel.readString();
    l = paramParcel.createTypedArrayList(RouteSearchCity.CREATOR);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getAction()
  {
    return j;
  }
  
  public String getAssistantAction()
  {
    return k;
  }
  
  public float getDistance()
  {
    return d;
  }
  
  public float getDuration()
  {
    return h;
  }
  
  public String getInstruction()
  {
    return a;
  }
  
  public String getOrientation()
  {
    return b;
  }
  
  public List<LatLonPoint> getPolyline()
  {
    return i;
  }
  
  public String getRoad()
  {
    return c;
  }
  
  public List<RouteSearchCity> getRouteSearchCityList()
  {
    return l;
  }
  
  public float getTollDistance()
  {
    return f;
  }
  
  public String getTollRoad()
  {
    return g;
  }
  
  public float getTolls()
  {
    return e;
  }
  
  public void setAction(String paramString)
  {
    j = paramString;
  }
  
  public void setAssistantAction(String paramString)
  {
    k = paramString;
  }
  
  public void setDistance(float paramFloat)
  {
    d = paramFloat;
  }
  
  public void setDuration(float paramFloat)
  {
    h = paramFloat;
  }
  
  public void setInstruction(String paramString)
  {
    a = paramString;
  }
  
  public void setOrientation(String paramString)
  {
    b = paramString;
  }
  
  public void setPolyline(List<LatLonPoint> paramList)
  {
    i = paramList;
  }
  
  public void setRoad(String paramString)
  {
    c = paramString;
  }
  
  public void setRouteSearchCityList(List<RouteSearchCity> paramList)
  {
    l = paramList;
  }
  
  public void setTollDistance(float paramFloat)
  {
    f = paramFloat;
  }
  
  public void setTollRoad(String paramString)
  {
    g = paramString;
  }
  
  public void setTolls(float paramFloat)
  {
    e = paramFloat;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(a);
    paramParcel.writeString(b);
    paramParcel.writeString(c);
    paramParcel.writeFloat(d);
    paramParcel.writeFloat(e);
    paramParcel.writeFloat(f);
    paramParcel.writeString(g);
    paramParcel.writeFloat(h);
    paramParcel.writeTypedList(i);
    paramParcel.writeString(j);
    paramParcel.writeString(k);
    paramParcel.writeTypedList(l);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.DriveStep
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */