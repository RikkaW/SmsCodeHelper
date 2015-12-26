package com.amap.api.location;

import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.LocationManager;

class g
  implements GpsStatus.Listener
{
  g(d paramd) {}
  
  public void onGpsStatusChanged(int paramInt)
  {
    GpsStatus localGpsStatus = a.a.getGpsStatus(null);
    d.a(a, paramInt, localGpsStatus);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.g
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */