package com.amap.api.location;

import android.location.Location;
import android.os.Bundle;

public class h
  implements AMapLocationListener
{
  private LocationManagerProxy a;
  private AMapLocationListener b = null;
  
  public h(LocationManagerProxy paramLocationManagerProxy)
  {
    a = paramLocationManagerProxy;
  }
  
  public void a()
  {
    if (a != null) {
      a.removeUpdates(this);
    }
    b = null;
  }
  
  public boolean a(AMapLocationListener paramAMapLocationListener, long paramLong, float paramFloat, String paramString)
  {
    boolean bool = false;
    b = paramAMapLocationListener;
    if ("lbs".equals(paramString))
    {
      a.requestLocationUpdates(paramString, paramLong, paramFloat, this);
      bool = true;
    }
    return bool;
  }
  
  public void onLocationChanged(Location paramLocation)
  {
    if (b != null) {
      b.onLocationChanged(paramLocation);
    }
  }
  
  public void onLocationChanged(AMapLocation paramAMapLocation)
  {
    if (b != null) {
      b.onLocationChanged(paramAMapLocation);
    }
  }
  
  public void onProviderDisabled(String paramString)
  {
    if (b != null) {
      b.onProviderDisabled(paramString);
    }
  }
  
  public void onProviderEnabled(String paramString)
  {
    if (b != null) {
      b.onProviderEnabled(paramString);
    }
  }
  
  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
  {
    if (b != null) {
      b.onStatusChanged(paramString, paramInt, paramBundle);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.h
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */