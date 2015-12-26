package com.amap.api.mapcore2d;

import android.graphics.Point;
import android.graphics.PointF;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.VisibleRegion;

public abstract interface aq
{
  public abstract Point a(LatLng paramLatLng);
  
  public abstract LatLng a(Point paramPoint);
  
  public abstract VisibleRegion a();
  
  public abstract PointF b(LatLng paramLatLng);
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.aq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */