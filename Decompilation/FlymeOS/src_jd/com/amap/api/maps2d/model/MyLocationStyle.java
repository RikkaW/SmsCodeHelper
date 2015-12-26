package com.amap.api.maps2d.model;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

public class MyLocationStyle
  implements Parcelable
{
  private BitmapDescriptor a;
  private float b = 0.5F;
  private float c = 0.5F;
  private int d = Color.argb(100, 0, 0, 180);
  private int e = Color.argb(255, 0, 0, 220);
  private float f = 1.0F;
  
  public MyLocationStyle anchor(float paramFloat1, float paramFloat2)
  {
    b = paramFloat1;
    c = paramFloat2;
    return this;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public float getAnchorU()
  {
    return b;
  }
  
  public float getAnchorV()
  {
    return c;
  }
  
  public BitmapDescriptor getMyLocationIcon()
  {
    return a;
  }
  
  public int getRadiusFillColor()
  {
    return d;
  }
  
  public int getStrokeColor()
  {
    return e;
  }
  
  public float getStrokeWidth()
  {
    return f;
  }
  
  public MyLocationStyle myLocationIcon(BitmapDescriptor paramBitmapDescriptor)
  {
    a = paramBitmapDescriptor;
    return this;
  }
  
  public MyLocationStyle radiusFillColor(int paramInt)
  {
    d = paramInt;
    return this;
  }
  
  public MyLocationStyle strokeColor(int paramInt)
  {
    e = paramInt;
    return this;
  }
  
  public MyLocationStyle strokeWidth(float paramFloat)
  {
    f = paramFloat;
    return this;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(a, paramInt);
    paramParcel.writeFloat(b);
    paramParcel.writeFloat(c);
    paramParcel.writeInt(d);
    paramParcel.writeInt(e);
    paramParcel.writeFloat(f);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.MyLocationStyle
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */