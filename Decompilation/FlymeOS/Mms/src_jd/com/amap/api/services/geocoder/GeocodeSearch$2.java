package com.amap.api.services.geocoder;

import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.d;
import com.amap.api.services.core.p;
import com.amap.api.services.core.p.c;
import java.util.List;

class GeocodeSearch$2
  implements Runnable
{
  GeocodeSearch$2(GeocodeSearch paramGeocodeSearch, GeocodeQuery paramGeocodeQuery) {}
  
  public void run()
  {
    Message localMessage = p.a().obtainMessage();
    try
    {
      what = 20;
      arg1 = 2;
      List localList = b.getFromLocationName(a);
      arg2 = 0;
      p.c localc = new p.c();
      b = GeocodeSearch.a(b);
      a = new GeocodeResult(a, localList);
      obj = localc;
      return;
    }
    catch (AMapException localAMapException)
    {
      d.a(localAMapException, "GeocodeSearch", "getFromLocationNameAsyn");
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
 * Qualified Name:     com.amap.api.services.geocoder.GeocodeSearch.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */