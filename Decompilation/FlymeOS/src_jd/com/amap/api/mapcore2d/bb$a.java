package com.amap.api.mapcore2d;

import android.os.Handler;
import android.os.Message;

class bb$a
  implements ch
{
  private cg b = null;
  private Message c = null;
  private Runnable d = null;
  
  private bb$a(bb parambb) {}
  
  private cg a(ae paramae, int paramInt)
  {
    int i = 500;
    if (paramInt < 500) {}
    for (;;)
    {
      return new cg(i, 10, aa).h.i, paramae, paramInt, this);
      i = paramInt;
    }
  }
  
  private void c()
  {
    b = null;
    c = null;
    d = null;
  }
  
  public void a()
  {
    if (b != null) {
      b.d();
    }
  }
  
  public void a(ae paramae)
  {
    if (paramae == null) {
      return;
    }
    if ((paramae.d() == Long.MIN_VALUE) || (paramae.c() == Long.MIN_VALUE))
    {
      paramae = aa).h.b(paramae);
      a.a(paramae);
      return;
    }
    a.a(paramae);
  }
  
  public void a(ae paramae, Message paramMessage, Runnable paramRunnable, int paramInt)
  {
    aa).c.a = true;
    aa).h.j = paramae.g();
    a();
    b = a(paramae, paramInt);
    c = paramMessage;
    d = paramRunnable;
    b.c();
  }
  
  public void b()
  {
    if (c != null) {
      c.getTarget().sendMessage(c);
    }
    if (d != null) {
      d.run();
    }
    c();
    if (aa).c != null) {
      aa).c.a = false;
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bb.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */