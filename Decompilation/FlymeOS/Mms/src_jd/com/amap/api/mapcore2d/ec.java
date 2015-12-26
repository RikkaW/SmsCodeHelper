package com.amap.api.mapcore2d;

public abstract class ec
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
      ed.a(localThrowable, "ThreadTask", "run");
      localThrowable.printStackTrace();
    }
  }
  
  static abstract interface a
  {
    public abstract void a(ec paramec);
    
    public abstract void b(ec paramec);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ec
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */