package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

public class WalkStep
  implements Parcelable
{
  public static final Parcelable.Creator<WalkStep> CREATOR = new u();
  private String a;
  private String b;
  private String c;
  private float d;
  private float e;
  private List<LatLonPoint> f = new ArrayList();
  private String g;
  private String h;
  
  public WalkStep() {}
  
  public WalkStep(Parcel paramParcel)
  {
    a = paramParcel.readString();
    b = paramParcel.readString();
    c = paramParcel.readString();
    d = paramParcel.readFloat();
    e = paramParcel.readFloat();
    f = paramParcel.createTypedArrayList(LatLonPoint.CREATOR);
    g = paramParcel.readString();
    h = paramParcel.readString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getAction()
  {
    return g;
  }
  
  public String getAssistantAction()
  {
    return h;
  }
  
  public float getDistance()
  {
    return d;
  }
  
  public float getDuration()
  {
    return e;
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
    return f;
  }
  
  public String getRoad()
  {
    return c;
  }
  
  public void setAction(String paramString)
  {
    g = paramString;
  }
  
  public void setAssistantAction(String paramString)
  {
    h = paramString;
  }
  
  public void setDistance(float paramFloat)
  {
    d = paramFloat;
  }
  
  public void setDuration(float paramFloat)
  {
    e = paramFloat;
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
    f = paramList;
  }
  
  public void setRoad(String paramString)
  {
    c = paramString;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(a);
    paramParcel.writeString(b);
    paramParcel.writeString(c);
    paramParcel.writeFloat(d);
    paramParcel.writeFloat(e);
    paramParcel.writeTypedList(f);
    paramParcel.writeString(g);
    paramParcel.writeString(h);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.route.WalkStep
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */