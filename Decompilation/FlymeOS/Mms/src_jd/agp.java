import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.GpsStatus.NmeaListener;
import android.location.LocationManager;
import android.os.Handler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class agp
  implements GpsStatus.Listener, GpsStatus.NmeaListener
{
  private long a = 0L;
  private long b = 0L;
  private boolean c = false;
  private List d = new ArrayList();
  private String e = null;
  private String f = null;
  private String g = null;
  
  protected agp(aie paramaie) {}
  
  public final void a(String paramString)
  {
    if ((System.currentTimeMillis() - b > 400L) && (c) && (d.size() > 0)) {}
    try
    {
      aic localaic = new aic(d, e, null, g);
      if (localaic.a())
      {
        aie.e(h, aie.a(h, localaic, aie.o(h)));
        if (aie.p(h) > 0) {
          aie.b(h, String.format(Locale.CHINA, "&nmea=%.1f|%.1f&g_tp=%d", new Object[] { Double.valueOf(localaic.c()), Double.valueOf(localaic.b()), Integer.valueOf(aie.p(h)) }));
        }
      }
      for (;;)
      {
        d.clear();
        g = null;
        f = null;
        e = null;
        c = false;
        if (!paramString.startsWith("$GPGGA")) {
          break;
        }
        c = true;
        e = paramString.trim();
        b = System.currentTimeMillis();
        return;
        aie.e(h, 0);
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        aie.e(h, 0);
        continue;
        if (paramString.startsWith("$GPGSV")) {
          d.add(paramString.trim());
        } else if (paramString.startsWith("$GPGSA")) {
          g = paramString.trim();
        }
      }
    }
  }
  
  public final void onGpsStatusChanged(int paramInt)
  {
    int i = 0;
    try
    {
      if (aie.e(h) == null)
      {
        return;
        aie.d(h, 0);
        return;
        if ((aie.a) || (System.currentTimeMillis() - a >= 10000L))
        {
          if (aie.i(h) == null) {
            aie.a(h, aie.e(h).getGpsStatus(null));
          }
          int j;
          for (;;)
          {
            Iterator localIterator = aie.i(h).getSatellites().iterator();
            aie.a(h, 0);
            aie.b(h, 0);
            aie.a(h, new HashMap());
            paramInt = 0;
            j = 0;
            while (localIterator.hasNext())
            {
              GpsSatellite localGpsSatellite = (GpsSatellite)localIterator.next();
              int n = paramInt + 1;
              int k = j;
              if (localGpsSatellite.usedInFix()) {
                k = j + 1;
              }
              int m = i;
              if (localGpsSatellite.getSnr() > 0.0F) {
                m = i + 1;
              }
              i = m;
              j = k;
              paramInt = n;
              if (localGpsSatellite.getSnr() >= aie.m())
              {
                aie.j(h);
                i = m;
                j = k;
                paramInt = n;
              }
            }
            aie.e(h).getGpsStatus(aie.i(h));
          }
          if ((aie.k(h) == -1) || ((j >= 4) && (aie.k(h) < 4)) || ((j < 4) && (aie.k(h) >= 4)))
          {
            aie.c(h, j);
            if (j >= 4) {
              break label392;
            }
            if (aie.l(h) != null) {
              aie.l(h).w();
            }
          }
          for (;;)
          {
            aie.d(h, i);
            aie.b(h, aie.m(h));
            if ((aie.a) || ((j <= 3) && (paramInt <= 15)) || (aie.e(h).getLastKnownLocation("gps") == null)) {
              break;
            }
            a = System.currentTimeMillis();
            return;
            label392:
            if (aie.l(h) != null) {
              aie.l(h).v();
            }
          }
        }
        return;
      }
      switch (paramInt)
      {
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public final void onNmeaReceived(long paramLong, String paramString)
  {
    try
    {
      if (!aie.a) {
        return;
      }
      if ((paramString != null) && (!paramString.equals("")) && (paramString.length() >= 9) && (paramString.length() <= 150))
      {
        aie.n(h).sendMessage(aie.n(h).obtainMessage(1, paramString));
        return;
      }
    }
    catch (Exception paramString) {}
  }
}

/* Location:
 * Qualified Name:     agp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */