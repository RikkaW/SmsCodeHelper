package com.amap.api.maps2d.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public final class CircleOptions
  implements Parcelable
{
  public static final c CREATOR = new c();
  String a;
  private LatLng b = null;
  private double c = 0.0D;
  private float d = 10.0F;
  private int e = -16777216;
  private int f = 0;
  private float g = 0.0F;
  private boolean h = true;
  
  public CircleOptions center(LatLng paramLatLng)
  {
    b = paramLatLng;
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public CircleOptions fillColor(int paramInt)
  {
    f = paramInt;
    return this;
  }
  
  public LatLng getCenter()
  {
    return b;
  }
  
  public int getFillColor()
  {
    return f;
  }
  
  public double getRadius()
  {
    return c;
  }
  
  public int getStrokeColor()
  {
    return e;
  }
  
  public float getStrokeWidth()
  {
    return d;
  }
  
  public float getZIndex()
  {
    return g;
  }
  
  public boolean isVisible()
  {
    return h;
  }
  
  public CircleOptions radius(double paramDouble)
  {
    c = paramDouble;
    return this;
  }
  
  public CircleOptions strokeColor(int paramInt)
  {
    e = paramInt;
    return this;
  }
  
  public CircleOptions strokeWidth(float paramFloat)
  {
    d = paramFloat;
    return this;
  }
  
  public CircleOptions visible(boolean paramBoolean)
  {
    h = paramBoolean;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    Bundle localBundle = new Bundle();
    if (b != null)
    {
      localBundle.putDouble("lat", b.latitude);
      localBundle.putDouble("lng", b.longitude);
    }
    paramParcel.writeBundle(localBundle);
    paramParcel.writeDouble(c);
    paramParcel.writeFloat(d);
    paramParcel.writeInt(e);
    paramParcel.writeInt(f);
    paramParcel.writeFloat(g);
    if (h) {}
    for (paramInt = 1;; paramInt = 0)
    {
      paramParcel.writeByte((byte)paramInt);
      paramParcel.writeString(a);
      return;
    }
  }
  
  public CircleOptions zIndex(float paramFloat)
  {
    g = paramFloat;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.CircleOptions
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */