package com.amap.api.mapcore2d;

import android.os.Handler;
import android.os.Message;

class ci
  implements au
{
  private ag a;
  private boolean b = true;
  private boolean c = false;
  private boolean d = true;
  private boolean e = true;
  private boolean f = true;
  private boolean g = false;
  private int h = 0;
  private int i = 0;
  private final Handler j = new cj(this);
  
  ci(ag paramag)
  {
    a = paramag;
  }
  
  public void a(int paramInt)
  {
    h = paramInt;
    a.c(paramInt);
  }
  
  public void a(boolean paramBoolean)
  {
    g = paramBoolean;
    j.obtainMessage(1).sendToTarget();
  }
  
  public boolean a()
  {
    return g;
  }
  
  public void b(int paramInt)
  {
    i = paramInt;
    a.d(paramInt);
  }
  
  public void b(boolean paramBoolean)
  {
    e = paramBoolean;
    j.obtainMessage(0).sendToTarget();
  }
  
  public boolean b()
  {
    return e;
  }
  
  public void c(boolean paramBoolean)
  {
    f = paramBoolean;
    j.obtainMessage(2).sendToTarget();
  }
  
  public boolean c()
  {
    return f;
  }
  
  public void d(boolean paramBoolean)
  {
    c = paramBoolean;
    j.obtainMessage(3).sendToTarget();
  }
  
  public boolean d()
  {
    return c;
  }
  
  public void e(boolean paramBoolean)
  {
    b = paramBoolean;
  }
  
  public boolean e()
  {
    return b;
  }
  
  public void f(boolean paramBoolean)
  {
    d = paramBoolean;
  }
  
  public boolean f()
  {
    return d;
  }
  
  public int g()
  {
    return h;
  }
  
  public void g(boolean paramBoolean)
  {
    f(paramBoolean);
    e(paramBoolean);
  }
  
  public int h()
  {
    return i;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ci
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */