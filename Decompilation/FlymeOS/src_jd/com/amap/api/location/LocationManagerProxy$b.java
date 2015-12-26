package com.amap.api.location;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;

class LocationManagerProxy$b
  implements AMapLocationListener
{
  LocationManagerProxy$b(LocationManagerProxy paramLocationManagerProxy) {}
  
  public void onLocationChanged(Location paramLocation)
  {
    try
    {
      if ((LocationManagerProxy.a(a) != null) && (LocationManagerProxy.a(a).size() > 0))
      {
        Iterator localIterator = LocationManagerProxy.a(a).iterator();
        while (localIterator.hasNext())
        {
          PendingIntent localPendingIntent = (PendingIntent)localIterator.next();
          Intent localIntent = new Intent();
          Bundle localBundle = new Bundle();
          localBundle.putParcelable("location", paramLocation);
          localIntent.putExtras(localBundle);
          try
          {
            localPendingIntent.send(LocationManagerProxy.b(a), 0, localIntent);
          }
          catch (PendingIntent.CanceledException localCanceledException)
          {
            localCanceledException.printStackTrace();
          }
        }
      }
      return;
    }
    catch (Throwable paramLocation)
    {
      paramLocation.printStackTrace();
    }
  }
  
  public void onLocationChanged(AMapLocation paramAMapLocation)
  {
    try
    {
      if ((LocationManagerProxy.a(a) != null) && (LocationManagerProxy.a(a).size() > 0))
      {
        Iterator localIterator = LocationManagerProxy.a(a).iterator();
        while (localIterator.hasNext())
        {
          PendingIntent localPendingIntent = (PendingIntent)localIterator.next();
          Intent localIntent = new Intent();
          Bundle localBundle = new Bundle();
          localBundle.putParcelable("location", paramAMapLocation);
          localIntent.putExtras(localBundle);
          try
          {
            localPendingIntent.send(LocationManagerProxy.b(a), 0, localIntent);
          }
          catch (PendingIntent.CanceledException localCanceledException)
          {
            localCanceledException.printStackTrace();
          }
        }
      }
      return;
    }
    catch (Throwable paramAMapLocation)
    {
      paramAMapLocation.printStackTrace();
    }
  }
  
  public void onProviderDisabled(String paramString) {}
  
  public void onProviderEnabled(String paramString) {}
  
  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
}

/* Location:
 * Qualified Name:     com.amap.api.location.LocationManagerProxy.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */