package com.amap.api.mapcore2d;

import java.util.concurrent.CopyOnWriteArrayList;

class bk$a
  extends Thread
{
  private bk$a(bk parambk) {}
  
  public void run()
  {
    setName("MarkerThread");
    for (;;)
    {
      if ((!Thread.currentThread().isInterrupted()) && (bk.a(a) != null) && (bk.a(a).size() > 1))
      {
        if (bk.b(a) == bk.a(a).size() - 1)
        {
          bk.a(a, 0);
          bk.d(a).a().postInvalidate();
        }
        try
        {
          Thread.sleep(bk.e(a) * 250);
          if (bk.a(a) == null)
          {
            Thread.currentThread().interrupt();
            continue;
            bk.c(a);
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          for (;;)
          {
            cy.a(localInterruptedException, "MarkerDelegateImp", "run");
          }
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bk.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */