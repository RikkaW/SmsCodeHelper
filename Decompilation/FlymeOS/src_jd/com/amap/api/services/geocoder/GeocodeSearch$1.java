package com.amap.api.services.geocoder;

import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.d;
import com.amap.api.services.core.p;
import com.amap.api.services.core.p.f;

class GeocodeSearch$1
  implements Runnable
{
  GeocodeSearch$1(GeocodeSearch paramGeocodeSearch, RegeocodeQuery paramRegeocodeQuery) {}
  
  public void run()
  {
    Message localMessage = p.a().obtainMessage();
    try
    {
      arg1 = 2;
      what = 21;
      RegeocodeAddress localRegeocodeAddress = b.getFromLocation(a);
      arg2 = 0;
      p.f localf = new p.f();
      b = GeocodeSearch.a(b);
      a = new RegeocodeResult(a, localRegeocodeAddress);
      obj = localf;
      return;
    }
    catch (AMapException localAMapException)
    {
      d.a(localAMapException, "GeocodeSearch", "getFromLocationAsyn");
      arg2 = localAMapException.getErrorCode();
      return;
    }
    finally
    {
      GeocodeSearch.b(b).sendMessage(localMessage);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.geocoder.GeocodeSearch.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */