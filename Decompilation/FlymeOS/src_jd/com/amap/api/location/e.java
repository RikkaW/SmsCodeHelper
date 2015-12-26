package com.amap.api.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.SystemClock;

class e
  implements LocationListener
{
  e(d paramd) {}
  
  public void onLocationChanged(Location paramLocation)
  {
    d.a(a).b(true);
    aa).e = SystemClock.elapsedRealtime();
  }
  
  public void onProviderDisabled(String paramString)
  {
    if ("gps".equals(paramString)) {
      d.a(a).b(false);
    }
  }
  
  public void onProviderEnabled(String paramString) {}
  
  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
  {
    if ((paramInt == 0) || (paramInt == 1)) {
      d.a(a).b(false);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */