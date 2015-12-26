package com.amap.api.maps2d.model;

import com.amap.api.mapcore2d.cp;

public final class CameraPosition$Builder
{
  private LatLng a;
  private float b;
  private float c;
  private float d;
  
  public CameraPosition$Builder() {}
  
  public CameraPosition$Builder(CameraPosition paramCameraPosition)
  {
    target(target).bearing(bearing).tilt(tilt).zoom(zoom);
  }
  
  public Builder bearing(float paramFloat)
  {
    d = paramFloat;
    return this;
  }
  
  public CameraPosition build()
  {
    cp.a(a);
    return new CameraPosition(a, b, c, d);
  }
  
  public Builder target(LatLng paramLatLng)
  {
    a = paramLatLng;
    return this;
  }
  
  public Builder tilt(float paramFloat)
  {
    c = paramFloat;
    return this;
  }
  
  public Builder zoom(float paramFloat)
  {
    b = paramFloat;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.model.CameraPosition.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */