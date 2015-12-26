package com.amap.api.mapcore2d;

class bz
{
  private Thread[] a;
  
  public bz(int paramInt, Runnable paramRunnable1, Runnable paramRunnable2)
  {
    a = new Thread[paramInt];
    int i = 0;
    if (i < paramInt)
    {
      if ((i == 0) && (paramInt > 1)) {
        a[i] = new Thread(paramRunnable1);
      }
      for (;;)
      {
        i += 1;
        break;
        a[i] = new Thread(paramRunnable2);
      }
    }
  }
  
  public void a()
  {
    Thread[] arrayOfThread = a;
    int j = arrayOfThread.length;
    int i = 0;
    while (i < j)
    {
      Thread localThread = arrayOfThread[i];
      localThread.setDaemon(true);
      localThread.start();
      i += 1;
    }
  }
  
  public void b()
  {
    if (a == null) {
      return;
    }
    int j = a.length;
    int i = 0;
    while (i < j)
    {
      a[i].interrupt();
      a[i] = null;
      i += 1;
    }
    a = null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */