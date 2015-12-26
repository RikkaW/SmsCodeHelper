import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import java.text.SimpleDateFormat;

final class agl
  implements LocationListener
{
  agl(aie paramaie) {}
  
  private static boolean a(Location paramLocation)
  {
    return (paramLocation != null) && ("gps".equalsIgnoreCase(paramLocation.getProvider())) && (paramLocation.getLatitude() > -90.0D) && (paramLocation.getLatitude() < 90.0D) && (paramLocation.getLongitude() > -180.0D) && (paramLocation.getLongitude() < 180.0D);
  }
  
  public final void onLocationChanged(Location paramLocation)
  {
    try
    {
      long l1 = paramLocation.getTime();
      long l2 = System.currentTimeMillis();
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      localSimpleDateFormat.format(Long.valueOf(l1));
      localSimpleDateFormat.format(Long.valueOf(l2));
      if (l1 <= 0L) {
        l1 = l2;
      }
      while (paramLocation != null)
      {
        if (!a(paramLocation)) {
          return;
        }
        if (paramLocation.getSpeed() > aie.g())
        {
          agt.a(aie.h());
          agt.b(aie.h() * 10);
        }
        for (;;)
        {
          aie.b(a).a();
          a(paramLocation);
          if ((!aie.b(a).a()) || (!a(paramLocation))) {
            break label236;
          }
          paramLocation.setTime(System.currentTimeMillis());
          aie.a(a, System.currentTimeMillis());
          aie.a(a, paramLocation);
          if (aie.c(a) == true) {
            break;
          }
          aie.a(a, paramLocation, 0, l1);
          return;
          if (paramLocation.getSpeed() > aie.i())
          {
            agt.a(aie.j());
            agt.b(aie.j() * 10);
          }
          else
          {
            agt.a(aie.k());
            agt.b(aie.k() * 10);
          }
        }
        aie.a(a, "new location in indoor collect");
        return;
      }
      label236:
      return;
    }
    catch (Exception paramLocation) {}
  }
  
  public final void onProviderDisabled(String paramString) {}
  
  public final void onProviderEnabled(String paramString) {}
  
  public final void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
}

/* Location:
 * Qualified Name:     agl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */