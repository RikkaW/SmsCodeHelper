package com.amap.api.mapcore2d;

import java.util.ArrayList;
import java.util.Iterator;

public class bl$d
{
  public boolean a = true;
  private b c;
  private ArrayList<cl> d;
  
  private bl$d(bl parambl, b paramb)
  {
    c = paramb;
    d = new ArrayList();
  }
  
  public int a()
  {
    try
    {
      int i = b.h.f;
      return i;
    }
    catch (Throwable localThrowable)
    {
      cy.a(localThrowable, "Mediator", "getMaxZoomLevel");
    }
    return 0;
  }
  
  public void a(int paramInt)
  {
    if (paramInt != b.h.g)
    {
      b.h.g = paramInt;
      b.g.b[1] = paramInt;
      b.g.e.a(paramInt);
    }
    a(false, false);
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    if ((paramInt1 != y.m) || (paramInt2 != y.n))
    {
      y.m = paramInt1;
      y.n = paramInt2;
      a(true, false);
    }
  }
  
  public void a(ae paramae)
  {
    if (paramae == null) {
      return;
    }
    if (y.p == true)
    {
      paramae = b.h.a(paramae);
      b.h.i = paramae;
    }
    a(false, false);
  }
  
  public void a(cl paramcl)
  {
    d.add(paramcl);
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    Iterator localIterator = d.iterator();
    while (localIterator.hasNext()) {
      ((cl)localIterator.next()).a(paramBoolean1, paramBoolean2);
    }
    if ((b.g != null) && (b.g.h != null))
    {
      b.g.h.a(true);
      b.g.postInvalidate();
    }
  }
  
  public int b()
  {
    try
    {
      int i = b.h.e;
      return i;
    }
    catch (Throwable localThrowable)
    {
      cy.a(localThrowable, "Mediator", "getMinZoomLevel");
    }
    return 0;
  }
  
  public void b(int paramInt)
  {
    if (paramInt <= 0) {
      return;
    }
    try
    {
      bh localbh = b.h;
      y.c = paramInt;
      f = paramInt;
      return;
    }
    catch (Throwable localThrowable)
    {
      cy.a(localThrowable, "Mediator", "setMaxZoomLevel");
    }
  }
  
  public void b(ae paramae)
  {
    ae localae = b.b.f();
    if ((paramae != null) && (!paramae.equals(localae)))
    {
      if (y.p == true)
      {
        paramae = b.h.a(paramae);
        b.h.i = paramae;
      }
      a(false, true);
    }
  }
  
  public int c()
  {
    return y.m;
  }
  
  public void c(int paramInt)
  {
    if (paramInt <= 0) {
      return;
    }
    try
    {
      bh localbh = b.h;
      y.d = paramInt;
      e = paramInt;
      return;
    }
    catch (Throwable localThrowable)
    {
      cy.a(localThrowable, "Mediator", "setMinZoomLevel");
    }
  }
  
  public int d()
  {
    return y.n;
  }
  
  public int e()
  {
    try
    {
      int i = b.h.g;
      return i;
    }
    catch (Throwable localThrowable)
    {
      cy.a(localThrowable, "Mediator", "getZoomLevel");
    }
    return 0;
  }
  
  public ae f()
  {
    ae localae2 = b.h.b(b.h.i);
    ae localae1 = localae2;
    if (b.c != null)
    {
      localae1 = localae2;
      if (b.c.a) {
        localae1 = b.h.j;
      }
    }
    return localae1;
  }
  
  public b g()
  {
    return c;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bl.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */