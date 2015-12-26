package com.amap.api.mapcore2d;

import android.os.Handler;

class i
  implements Runnable
{
  i(h paramh) {}
  
  public void run()
  {
    h.a(a);
    if (!a.e())
    {
      h.b(a).removeCallbacks(this);
      h.a(a, null);
      a.b();
    }
    long l1;
    long l2;
    do
    {
      return;
      l1 = System.currentTimeMillis();
      a.a();
      h.c(a);
      l2 = System.currentTimeMillis();
    } while (l2 - l1 >= a.b);
    try
    {
      Thread.sleep(a.b - (l2 - l1));
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      cy.a(localInterruptedException, "AnimBase", "run");
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.i
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */