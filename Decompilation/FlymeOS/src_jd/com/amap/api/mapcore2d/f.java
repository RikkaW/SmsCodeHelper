package com.amap.api.mapcore2d;

import android.os.Handler;
import android.os.Message;
import com.amap.api.maps2d.MapsInitializer;

class f
  extends Thread
{
  f(b paramb) {}
  
  public void run()
  {
    Message localMessage = new Message();
    while (!MapsInitializer.getNetworkEnable()) {
      try
      {
        Thread.sleep(5000L);
      }
      catch (Exception localException2)
      {
        cy.a(localException2, "AMapDelegateImpGLSurfaceView", "run");
        Thread.currentThread().interrupt();
      }
    }
    try
    {
      obj = new az(b.e(a)).a();
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable = localThrowable;
        cy.a(localThrowable, "AMapDelegateImpGLSurfaceView", "run");
        what = 3;
        a.j.sendMessage(localMessage);
      }
    }
    finally
    {
      what = 3;
      a.j.sendMessage(localMessage);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.f
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */