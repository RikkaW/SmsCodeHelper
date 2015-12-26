package com.amap.api.mapcore2d;

import android.graphics.Point;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.CameraPosition.Builder;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;

public class u
{
  a a = a.a;
  float b;
  float c;
  float d;
  float e;
  CameraPosition f;
  LatLngBounds g;
  Point h = null;
  boolean i = false;
  private float j;
  private float k;
  private int l;
  private int m;
  private int n;
  private an o;
  
  public static u a()
  {
    return new u();
  }
  
  public static u a(float paramFloat)
  {
    u localu = a();
    a = a.f;
    d = paramFloat;
    return localu;
  }
  
  public static u a(float paramFloat1, float paramFloat2)
  {
    u localu = a();
    a = a.h;
    b = paramFloat1;
    c = paramFloat2;
    return localu;
  }
  
  public static u a(float paramFloat, Point paramPoint)
  {
    u localu = a();
    a = a.g;
    e = paramFloat;
    h = paramPoint;
    return localu;
  }
  
  static u a(an paraman, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    u localu = a();
    a = a.l;
    o = paraman;
    d = paramFloat1;
    k = paramFloat2;
    j = paramFloat3;
    return localu;
  }
  
  public static u a(CameraPosition paramCameraPosition)
  {
    u localu = a();
    a = a.i;
    f = paramCameraPosition;
    return localu;
  }
  
  public static u a(LatLng paramLatLng)
  {
    u localu = a();
    a = a.c;
    f = new CameraPosition(paramLatLng, 0.0F, 0.0F, 0.0F);
    return localu;
  }
  
  public static u a(LatLng paramLatLng, float paramFloat)
  {
    return a(CameraPosition.builder().target(paramLatLng).zoom(paramFloat).build());
  }
  
  public static u a(LatLng paramLatLng, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return a(CameraPosition.builder().target(paramLatLng).zoom(paramFloat1).bearing(paramFloat2).tilt(paramFloat3).build());
  }
  
  public static u a(LatLngBounds paramLatLngBounds, int paramInt)
  {
    u localu = a();
    a = a.j;
    g = paramLatLngBounds;
    l = paramInt;
    return localu;
  }
  
  public static u a(LatLngBounds paramLatLngBounds, int paramInt1, int paramInt2, int paramInt3)
  {
    u localu = a();
    a = a.k;
    g = paramLatLngBounds;
    l = paramInt3;
    m = paramInt1;
    n = paramInt2;
    return localu;
  }
  
  public static u b()
  {
    u localu = a();
    a = a.b;
    return localu;
  }
  
  public static u b(float paramFloat)
  {
    return a(paramFloat, null);
  }
  
  public static u b(LatLng paramLatLng)
  {
    return a(CameraPosition.builder().target(paramLatLng).build());
  }
  
  public static u c()
  {
    u localu = a();
    a = a.e;
    return localu;
  }
  
  static enum a
  {
    private a() {}
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.u
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */