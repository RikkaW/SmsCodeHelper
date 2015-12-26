import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.core.AMapLocException;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.LocationSource.OnLocationChangedListener;
import com.amap.api.maps2d.model.LatLng;

public class la
  implements AMapLocationListener, LocationSource
{
  private LocationManagerProxy a;
  private la.a b;
  private String c;
  private String d;
  private LatLng e;
  private LatLng f;
  private boolean g = true;
  private ld h = null;
  private LocationSource.OnLocationChangedListener i;
  
  public la(Context paramContext) {}
  
  public static boolean a(LatLng paramLatLng1, LatLng paramLatLng2)
  {
    Log.i("amap/LocateUtils", "GetLocationView isSameLatLng latLng1 = " + paramLatLng1 + ", latLng2 = " + paramLatLng2);
    if ((paramLatLng1 == null) || (paramLatLng2 == null)) {}
    while ((latitude != latitude) || (longitude != longitude)) {
      return false;
    }
    return true;
  }
  
  private void f()
  {
    Log.i("amap/LocateUtils", "createCurrentSuggestLocation mCurrentSuggestLocation = " + h);
    if ((h == null) && (!TextUtils.isEmpty(d)))
    {
      h = new ld(d, c, 0.0D, e);
      h.b(true);
      h.a(true);
      h.d(d);
      h.c(false);
      h.a(null);
      b.a(h);
    }
    while ((a(e, f)) || (TextUtils.isEmpty(d))) {
      return;
    }
    h.a(e);
    h.b(d);
    h.c(c);
    h.d(d);
    h.a(true);
    h.c(false);
    h.a(null);
    b.a(h);
  }
  
  public void a()
  {
    Log.i("amap/LocateUtils", "pause");
    deactivate();
  }
  
  public void a(String paramString)
  {
    c = paramString;
  }
  
  public void a(la.a parama)
  {
    b = parama;
  }
  
  public void a(boolean paramBoolean)
  {
    if ((paramBoolean) && (a == null))
    {
      a = LocationManagerProxy.getInstance(b.a());
      a.requestLocationData("lbs", 2000L, 10.0F, this);
    }
  }
  
  public void activate(LocationSource.OnLocationChangedListener paramOnLocationChangedListener)
  {
    Log.i("amap/LocateUtils", "activate mLocationManager = " + a);
    i = paramOnLocationChangedListener;
    a(b.b());
  }
  
  public kr b()
  {
    return h;
  }
  
  public void b(boolean paramBoolean)
  {
    Log.i("amap/LocateUtils", "updateActivateLocate isEnable = " + paramBoolean);
    a(paramBoolean);
  }
  
  public LatLng c()
  {
    return e;
  }
  
  public void d()
  {
    if ((e != null) && (!a(b.d(), e)))
    {
      b.a(e, false);
      Log.i("amap/LocateUtils", "GetLocationView requestLocation mCurrentSuggestLocation = " + h);
      if (h != null) {
        break label76;
      }
    }
    label76:
    do
    {
      return;
      h.b(true);
      b.a(true);
    } while (!b.b());
    b.a(e, d, d);
    b.a(15, e);
  }
  
  public void deactivate()
  {
    Log.i("amap/LocateUtils", " deactivate");
    i = null;
    if (a != null)
    {
      a.removeUpdates(this);
      a.destory();
    }
    a = null;
  }
  
  public String e()
  {
    return c;
  }
  
  public void onLocationChanged(Location paramLocation) {}
  
  public void onLocationChanged(AMapLocation paramAMapLocation)
  {
    Log.i("amap/LocateUtils", " onLocationChanged mLocateListener.isHasDestory() = " + b.c());
    if (b.c()) {}
    do
    {
      return;
      Log.i("amap/LocateUtils", " onLocationChanged amapLocation = " + paramAMapLocation + ", isFirstLoc = " + g);
    } while ((i == null) || (paramAMapLocation == null) || (paramAMapLocation.getAMapException().getErrorCode() != 0));
    b.b(true);
    i.onLocationChanged(paramAMapLocation);
    if (g) {
      b.a(e, true);
    }
    f = e;
    e = new LatLng(paramAMapLocation.getLatitude(), paramAMapLocation.getLongitude());
    Log.i("amap/LocateUtils", " onLocationChanged mLocateListener.isNetWorkEnable() = " + b.b());
    d = paramAMapLocation.getAddress();
    c = paramAMapLocation.getCity();
    b.a(c);
    f();
    if (g)
    {
      b.a(e, d, d);
      b.a(16, e);
    }
    g = false;
  }
  
  public void onProviderDisabled(String paramString) {}
  
  public void onProviderEnabled(String paramString) {}
  
  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
  
  public static abstract interface a
  {
    public abstract Activity a();
    
    public abstract void a(int paramInt, LatLng paramLatLng);
    
    public abstract void a(LatLng paramLatLng, String paramString1, String paramString2);
    
    public abstract void a(LatLng paramLatLng, boolean paramBoolean);
    
    public abstract void a(String paramString);
    
    public abstract void a(ld paramld);
    
    public abstract void a(boolean paramBoolean);
    
    public abstract void b(boolean paramBoolean);
    
    public abstract boolean b();
    
    public abstract boolean c();
    
    public abstract LatLng d();
  }
}

/* Location:
 * Qualified Name:     la
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */