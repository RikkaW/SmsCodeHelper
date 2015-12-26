package com.amap.api.mapcore2d;

import android.content.Context;
import java.util.ArrayList;
import java.util.Vector;

abstract class j<T, V>
  extends bi
{
  protected bx<T> a;
  private volatile boolean d = true;
  private Vector<Thread> e = null;
  private Runnable f = new k(this);
  private Runnable g = new l(this);
  private bz h;
  
  public j(bl parambl, Context paramContext)
  {
    super(parambl, paramContext);
    if (e == null) {
      e = new Vector();
    }
    h = new bz(e(), g, f);
    h.a();
  }
  
  protected abstract ArrayList<T> a(ArrayList<T> paramArrayList);
  
  public void a()
  {
    a.a();
    d();
    a.c();
    a = null;
    b = null;
    c = null;
  }
  
  protected abstract ArrayList<T> b(ArrayList<T> paramArrayList);
  
  public void b()
  {
    super.b();
    d();
  }
  
  public void c()
  {
    d = true;
    if (e == null) {
      e = new Vector();
    }
    if (h == null)
    {
      h = new bz(e(), g, f);
      h.a();
    }
  }
  
  public void d()
  {
    d = false;
    if (e != null)
    {
      int j = e.size();
      int i = 0;
      while (i < j)
      {
        Thread localThread = (Thread)e.get(0);
        if (localThread != null)
        {
          localThread.interrupt();
          e.remove(0);
        }
        i += 1;
      }
      e = null;
    }
    if (h != null)
    {
      h.b();
      h = null;
    }
  }
  
  protected abstract int e();
  
  protected abstract int f();
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.j
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */