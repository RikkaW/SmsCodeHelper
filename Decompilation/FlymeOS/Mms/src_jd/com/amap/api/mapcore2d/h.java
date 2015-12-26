package com.amap.api.mapcore2d;

import android.os.Handler;
import android.os.Looper;

abstract class h
{
  protected int a;
  protected int b;
  private Handler c = null;
  private int d = 0;
  private boolean e = false;
  private Runnable f = new i(this);
  
  public h(int paramInt1, int paramInt2)
  {
    a = paramInt1;
    b = paramInt2;
  }
  
  private void f()
  {
    e = false;
  }
  
  private void g()
  {
    d += b;
    if ((a != -1) && (d > a)) {
      f();
    }
  }
  
  private void h()
  {
    if (c != null) {
      c.post(f);
    }
  }
  
  protected abstract void a();
  
  public void a(boolean paramBoolean)
  {
    e = paramBoolean;
  }
  
  protected abstract void b();
  
  public void c()
  {
    if (!e())
    {
      c = new Handler(Looper.getMainLooper());
      e = true;
      d = 0;
    }
    h();
  }
  
  public void d()
  {
    v.a().b();
    f();
    f.run();
  }
  
  public boolean e()
  {
    return e;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.h
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */