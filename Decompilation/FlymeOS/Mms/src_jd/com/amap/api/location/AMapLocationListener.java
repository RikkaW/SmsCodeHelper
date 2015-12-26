package com.amap.api.location;

import android.location.LocationListener;

public abstract interface AMapLocationListener
  extends LocationListener
{
  public abstract void onLocationChanged(AMapLocation paramAMapLocation);
}

/* Location:
 * Qualified Name:     com.amap.api.location.AMapLocationListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */