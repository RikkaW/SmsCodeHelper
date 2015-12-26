package com.amap.api.services.core;

public abstract class ax
  implements Runnable
{
  a a;
  
  public abstract void a();
  
  public final void run()
  {
    try
    {
      if (a != null) {
        a.a(this);
      }
      if (Thread.interrupted()) {
        return;
      }
      a();
      if ((!Thread.interrupted()) && (a != null))
      {
        a.b(this);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      ay.a(localThrowable, "ThreadTask", "run");
      localThrowable.printStackTrace();
    }
  }
  
  static abstract interface a
  {
    public abstract void a(ax paramax);
    
    public abstract void b(ax paramax);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.ax
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */