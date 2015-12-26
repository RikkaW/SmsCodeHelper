package com.amap.api.location;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Looper;
import android.os.SystemClock;
import java.util.Iterator;

public class d
{
  public LocationManager a = null;
  LocationListener b = new e(this);
  LocationListener c = new f(this);
  private a.a d;
  private a e;
  private Context f;
  private final GpsStatus.Listener g = new g(this);
  
  d(Context paramContext, LocationManager paramLocationManager, a.a parama, a parama1)
  {
    f = paramContext;
    a = paramLocationManager;
    e = parama1;
    d = parama;
  }
  
  private void a(int paramInt, GpsStatus paramGpsStatus)
  {
    if (paramInt == 4)
    {
      int i = paramGpsStatus.getMaxSatellites();
      paramGpsStatus = paramGpsStatus.getSatellites().iterator();
      paramInt = 0;
      if ((paramGpsStatus.hasNext()) && (paramInt <= i))
      {
        if (!((GpsSatellite)paramGpsStatus.next()).usedInFix()) {
          break label93;
        }
        paramInt += 1;
      }
    }
    label93:
    for (;;)
    {
      break;
      if (paramInt >= 3) {
        e.i = SystemClock.elapsedRealtime();
      }
      do
      {
        return;
      } while ((paramInt == 1) || (paramInt != 2));
      e.b(false);
      return;
    }
  }
  
  void a() {}
  
  void a(long paramLong, float paramFloat)
  {
    try
    {
      Looper localLooper = f.getMainLooper();
      if (Looper.myLooper() == null) {
        Looper.prepare();
      }
      a.addGpsStatusListener(g);
      a.requestLocationUpdates("gps", paramLong, paramFloat, c, localLooper);
      a.requestLocationUpdates("gps", 5000L, 0.0F, b, localLooper);
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  void b()
  {
    if (c != null) {
      a.removeUpdates(c);
    }
    if (g != null) {
      a.removeGpsStatusListener(g);
    }
    if (b != null) {
      a.removeUpdates(b);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.location.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */