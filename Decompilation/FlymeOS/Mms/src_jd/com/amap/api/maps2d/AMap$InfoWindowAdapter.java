package com.amap.api.maps2d;

import android.view.View;
import com.amap.api.maps2d.model.Marker;

public abstract interface AMap$InfoWindowAdapter
{
  public abstract View getInfoContents(Marker paramMarker);
  
  public abstract View getInfoWindow(Marker paramMarker);
}

/* Location:
 * Qualified Name:     com.amap.api.maps2d.AMap.InfoWindowAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */