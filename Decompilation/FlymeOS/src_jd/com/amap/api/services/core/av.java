package com.amap.api.services.core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class av
{
  private static av a = null;
  private ExecutorService b;
  private ConcurrentHashMap<ax, Future<?>> c = new ConcurrentHashMap();
  private ax.a d = new aw(this);
  
  private av(int paramInt)
  {
    try
    {
      b = Executors.newFixedThreadPool(paramInt);
      return;
    }
    catch (Throwable localThrowable)
    {
      ay.a(localThrowable, "TPool", "ThreadPool");
      localThrowable.printStackTrace();
    }
  }
  
  public static av a(int paramInt)
  {
    try
    {
      if (a == null) {
        a = new av(paramInt);
      }
      av localav = a;
      return localav;
    }
    finally {}
  }
  
  private void a(ax paramax, boolean paramBoolean)
  {
    try
    {
      paramax = (Future)c.remove(paramax);
      if ((paramBoolean) && (paramax != null)) {
        paramax.cancel(true);
      }
    }
    catch (Throwable paramax)
    {
      for (;;)
      {
        ay.a(paramax, "TPool", "removeQueue");
        paramax.printStackTrace();
      }
    }
    finally {}
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.av
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */