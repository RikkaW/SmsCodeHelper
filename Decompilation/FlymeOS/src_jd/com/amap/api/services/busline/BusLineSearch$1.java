package com.amap.api.services.busline;

import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.d;
import com.amap.api.services.core.p;
import com.amap.api.services.core.p.a;

class BusLineSearch$1
  implements Runnable
{
  BusLineSearch$1(BusLineSearch paramBusLineSearch) {}
  
  public void run()
  {
    Message localMessage = p.a().obtainMessage();
    try
    {
      BusLineResult localBusLineResult = a.searchBusLine();
      arg1 = 3;
      what = 0;
      p.a locala = new p.a();
      a = localBusLineResult;
      b = BusLineSearch.a(a);
      obj = locala;
      return;
    }
    catch (AMapException localAMapException)
    {
      d.a(localAMapException, "BusLineSearch", "searchBusLineAsyn");
      what = localAMapException.getErrorCode();
      return;
    }
    finally
    {
      BusLineSearch.b(a).sendMessage(localMessage);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.busline.BusLineSearch.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */