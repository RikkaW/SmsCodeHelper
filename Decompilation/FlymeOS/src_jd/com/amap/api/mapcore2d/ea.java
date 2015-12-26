package com.amap.api.mapcore2d;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class ea
{
  private static ea a = null;
  private ExecutorService b;
  private ConcurrentHashMap<ec, Future<?>> c = new ConcurrentHashMap();
  private ec.a d = new eb(this);
  
  private ea(int paramInt)
  {
    try
    {
      b = Executors.newFixedThreadPool(paramInt);
      return;
    }
    catch (Throwable localThrowable)
    {
      ed.a(localThrowable, "TPool", "ThreadPool");
      localThrowable.printStackTrace();
    }
  }
  
  public static ea a(int paramInt)
  {
    try
    {
      if (a == null) {
        a = new ea(paramInt);
      }
      ea localea = a;
      return localea;
    }
    finally {}
  }
  
  private void a(ec paramec, boolean paramBoolean)
  {
    try
    {
      paramec = (Future)c.remove(paramec);
      if ((paramBoolean) && (paramec != null)) {
        paramec.cancel(true);
      }
    }
    catch (Throwable paramec)
    {
      for (;;)
      {
        ed.a(paramec, "TPool", "removeQueue");
        paramec.printStackTrace();
      }
    }
    finally {}
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ea
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */