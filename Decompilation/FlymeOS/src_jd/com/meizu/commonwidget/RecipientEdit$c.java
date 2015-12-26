package com.meizu.commonwidget;

import android.os.Looper;

class RecipientEdit$c
  implements Runnable
{
  private final Object a = new Object();
  private Looper b;
  private int c;
  
  public RecipientEdit$c(String arg1)
  {
    new Thread(this, ???).start();
    synchronized (a)
    {
      for (;;)
      {
        Looper localLooper = b;
        if (localLooper != null) {
          break;
        }
        try
        {
          a.wait();
        }
        catch (InterruptedException localInterruptedException) {}
      }
      return;
    }
  }
  
  public Looper a()
  {
    return b;
  }
  
  public void b()
  {
    b.quit();
  }
  
  public void run()
  {
    synchronized (a)
    {
      Looper.prepare();
      b = Looper.myLooper();
      a.notifyAll();
      Looper.loop();
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.commonwidget.RecipientEdit.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */