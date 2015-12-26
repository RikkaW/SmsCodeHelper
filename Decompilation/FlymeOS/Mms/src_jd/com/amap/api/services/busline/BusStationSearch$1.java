package com.amap.api.services.busline;

import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.d;
import com.amap.api.services.core.p;
import com.amap.api.services.core.p.b;

class BusStationSearch$1
  implements Runnable
{
  BusStationSearch$1(BusStationSearch paramBusStationSearch) {}
  
  public void run()
  {
    Message localMessage = p.a().obtainMessage();
    try
    {
      BusStationResult localBusStationResult = a.searchBusStation();
      arg1 = 7;
      what = 0;
      p.b localb = new p.b();
      a = localBusStationResult;
      b = BusStationSearch.a(a);
      obj = localb;
      return;
    }
    catch (AMapException localAMapException)
    {
      d.a(localAMapException, "BusStationSearch", "searchBusStationAsyn");
      what = localAMapException.getErrorCode();
      return;
    }
    finally
    {
      BusStationSearch.b(a).sendMessage(localMessage);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.busline.BusStationSearch.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */