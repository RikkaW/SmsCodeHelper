package com.amap.api.mapcore2d;

import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;

public abstract interface ai
  extends am
{
  public abstract void a(float paramFloat1, float paramFloat2);
  
  public abstract void a(BitmapDescriptor paramBitmapDescriptor);
  
  public abstract void a(LatLng paramLatLng);
  
  public abstract void a(LatLngBounds paramLatLngBounds);
  
  public abstract void b(float paramFloat);
  
  public abstract void c(float paramFloat);
  
  public abstract void d(float paramFloat);
  
  public abstract LatLng h();
  
  public abstract float i();
  
  public abstract float j();
  
  public abstract LatLngBounds k();
  
  public abstract float m();
  
  public abstract float n();
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ai
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */