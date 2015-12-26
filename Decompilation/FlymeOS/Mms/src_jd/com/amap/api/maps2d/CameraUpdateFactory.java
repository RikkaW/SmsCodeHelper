package com.amap.api.maps2d;

import android.graphics.Point;
import com.amap.api.mapcore2d.u;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;

public final class CameraUpdateFactory
{
  public static CameraUpdate changeLatLng(LatLng paramLatLng)
  {
    return new CameraUpdate(u.a(paramLatLng));
  }
  
  public static CameraUpdate newCameraPosition(CameraPosition paramCameraPosition)
  {
    return new CameraUpdate(u.a(paramCameraPosition));
  }
  
  public static CameraUpdate newLatLng(LatLng paramLatLng)
  {
    return new CameraUpdate(u.b(paramLatLng));
  }
  
  public static CameraUpdate newLatLngBounds(LatLngBounds paramLatLngBounds, int paramInt)
  {
    return new CameraUpdate(u.a(paramLatLngBounds, paramInt));
  }
  
  public static CameraUpdate newLatLngBounds(LatLngBounds paramLatLngBounds, int paramInt1, int paramInt2, int paramInt3)
  {
    return new CameraUpdate(u.a(paramLatLngBounds, paramInt1, paramInt2, paramInt3));
  }
  
  public static CameraUpdate newLatLngZoom(LatLng paramLatLng, float paramFloat)
  {
    return new CameraUpdate(u.a(paramLatLng, paramFloat));
  }
  
  public static CameraUpdate scrollBy(float paramFloat1, float paramFloat2)
  {
    return new CameraUpdate(u.a(paramFloat1, paramFloat2));
  }
  
  public static CameraUpdate zoomBy(float paramFloat)
  {
    return new CameraUpdate(u.b(paramFloat));
  }
  
  public static CameraUpdate zoomBy(float paramFloat, Point paramPoint)
  {
    return new CameraUpdate(u.a(paramFloat, paramPoint));
  }
  
  public static CameraUpdate zoomIn()
  {
    return new CameraUpdate(u.b());
  }
  
  public static CameraUpdate zoomOut()
  {
    return new CameraUpdate(u.c());
  }
  
  public static CameraUpdate zoomTo(float paramFloat)
  {
    return new CameraUpdate(u.a(paramFloat));
  }
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.CameraUpdateFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */