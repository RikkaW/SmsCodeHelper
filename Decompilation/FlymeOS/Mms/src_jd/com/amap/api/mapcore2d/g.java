package com.amap.api.mapcore2d;

import android.location.Location;
import android.os.RemoteException;
import com.amap.api.maps2d.LocationSource.OnLocationChangedListener;

class g
  implements LocationSource.OnLocationChangedListener
{
  Location a;
  private ag b;
  
  g(ag paramag)
  {
    b = paramag;
  }
  
  public void onLocationChanged(Location paramLocation)
  {
    a = paramLocation;
    try
    {
      if (b.n()) {
        b.a(paramLocation);
      }
      return;
    }
    catch (RemoteException paramLocation)
    {
      cy.a(paramLocation, "AMapOnLocationChangedListener", "onLocationChanged");
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.g
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */