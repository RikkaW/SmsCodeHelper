package com.amap.api.mapcore2d;

import android.graphics.Matrix;
import android.graphics.Point;
import android.view.animation.Animation.AnimationListener;

class co
  extends h
{
  static float c = 1.0F;
  public int d = -1;
  public boolean e = false;
  private Animation.AnimationListener f;
  private b g;
  private float h;
  private float i;
  private float j;
  private float k;
  private float l;
  private boolean m;
  private boolean n = false;
  
  public co(b paramb, Animation.AnimationListener paramAnimationListener)
  {
    super(160, 40);
    g = paramb;
    f = paramAnimationListener;
  }
  
  protected void a()
  {
    if (m) {}
    for (c += l;; c -= l)
    {
      Matrix localMatrix = new Matrix();
      localMatrix.setScale(c, c, h, i);
      g.c(c);
      g.b(localMatrix);
      return;
    }
  }
  
  public void a(float paramFloat1, int paramInt, boolean paramBoolean, float paramFloat2, float paramFloat3)
  {
    m = paramBoolean;
    h = paramFloat2;
    i = paramFloat3;
    j = paramFloat1;
    c = j;
    if (m)
    {
      l = (j * b / a);
      k = (j * 2.0F);
      return;
    }
    l = (j * 0.5F * b / a);
    k = (j * 0.5F);
  }
  
  public void a(int paramInt, boolean paramBoolean, float paramFloat1, float paramFloat2)
  {
    g.b[0] = g.b[1];
    g.b[1] = paramInt;
    if (g.b[0] == g.b[1]) {
      return;
    }
    g.a().a(g.B());
    if (!e())
    {
      a = 160;
      a(g.H(), paramInt, paramBoolean, paramFloat1, paramFloat2);
      g.a().d.a(true);
      g.a().d.b = true;
      f.onAnimationStart(null);
      super.c();
      return;
    }
    n = true;
    d();
    a(k, paramInt, paramBoolean, paramFloat1, paramFloat2);
    g.a().d.a(true);
    g.a().d.b = true;
    f.onAnimationStart(null);
    super.c();
    n = false;
  }
  
  protected void b()
  {
    if (n) {}
    for (;;)
    {
      return;
      try
      {
        if ((g != null) && (g.a() != null))
        {
          g.a().d.b = false;
          Point localPoint;
          ae localae;
          if (e == true)
          {
            localPoint = new Point((int)h, (int)i);
            localae = g.s().a((int)h, (int)i);
            g.a().h.i = g.a().h.a(localae);
            g.a().h.a(localPoint);
            g.a().b.a(false, false);
          }
          g.D().c(d);
          f.onAnimationEnd(null);
          if (e == true)
          {
            localPoint = new Point(g.a().b.c() / 2, g.a().b.d() / 2);
            localae = g.s().a(g.a().b.c() / 2, g.a().b.d() / 2);
            g.a().h.i = g.a().h.a(localae);
            g.a().h.a(localPoint);
            g.a().b.a(false, false);
          }
          g.e(0);
          c = 1.0F;
          bm.j = 1.0F;
          g.a().a(true);
          return;
        }
      }
      catch (Exception localException)
      {
        cy.a(localException, "ZoomCtlAnim", "onStop");
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.co
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */