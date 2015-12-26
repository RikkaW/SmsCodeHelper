package com.amap.api.mapcore2d;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

class cq$c
  implements Executor
{
  final ArrayDeque<Runnable> a = new ArrayDeque();
  Runnable b;
  
  protected void a()
  {
    try
    {
      Runnable localRunnable = (Runnable)a.poll();
      b = localRunnable;
      if (localRunnable != null) {
        cq.d().execute(b);
      }
      return;
    }
    finally {}
  }
  
  public void execute(Runnable paramRunnable)
  {
    try
    {
      a.offer(new cq.c.1(this, paramRunnable));
      if (b == null) {
        a();
      }
      return;
    }
    finally
    {
      paramRunnable = finally;
      throw paramRunnable;
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cq.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */