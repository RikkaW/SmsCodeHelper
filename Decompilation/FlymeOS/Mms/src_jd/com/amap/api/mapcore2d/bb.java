package com.amap.api.mapcore2d;

import android.graphics.Point;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import java.util.LinkedList;

final class bb
  implements View.OnKeyListener
{
  private int a = 0;
  private int b = 0;
  private bl c;
  private boolean d;
  private b e;
  private a f;
  
  bb(bl parambl)
  {
    c = parambl;
    d = false;
    e = new b(null);
    f = new a(null);
  }
  
  private int a(float paramFloat)
  {
    int i = 1;
    int k = 0;
    int j = 1;
    for (;;)
    {
      if (j > paramFloat) {
        return k;
      }
      j *= 2;
      k = i;
      i += 1;
    }
  }
  
  private boolean a(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    return a(paramInt1, paramInt2, paramBoolean1, paramBoolean2, 1);
  }
  
  private boolean a(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, int paramInt3)
  {
    c.b.g().K();
    boolean bool = false;
    if (paramBoolean1) {}
    for (paramInt3 = c.b.e() + paramInt3;; paramInt3 = c.b.e() - paramInt3)
    {
      paramInt3 = c.b.g().a(paramInt3);
      if (paramInt3 != c.b.e())
      {
        a(paramInt1, paramInt2, paramInt3, paramBoolean1, paramBoolean2);
        bool = true;
      }
      try
      {
        if (c.g.q().a()) {
          c.g.L();
        }
        return bool;
      }
      catch (RemoteException localRemoteException)
      {
        cy.a(localRemoteException, "MapController", "zoomWithAnimation");
      }
    }
    return bool;
  }
  
  private boolean b(ae paramae)
  {
    if ((paramae == null) || (c == null) || (c.b == null)) {}
    ae localae;
    do
    {
      return false;
      localae = c.b.f();
    } while ((localae == null) || ((paramae.b() == localae.b()) && (paramae.a() == localae.a())));
    return true;
  }
  
  private void c(ae paramae)
  {
    c.g.K();
    c.b.a(paramae);
  }
  
  private int f(int paramInt)
  {
    b localb = c.b.g();
    localb.K();
    paramInt = localb.a(paramInt);
    c.b.a(paramInt);
    try
    {
      if (c.g.q().a()) {
        c.g.L();
      }
      return paramInt;
    }
    catch (RemoteException localRemoteException)
    {
      cy.a(localRemoteException, "MapController", "setZoom");
    }
    return paramInt;
  }
  
  private boolean g(int paramInt)
  {
    if ((c == null) || (c.b == null)) {}
    while (paramInt == c.b.e()) {
      return false;
    }
    return true;
  }
  
  public int a()
  {
    return a;
  }
  
  public void a(int paramInt)
  {
    a = paramInt;
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    if ((paramInt1 <= 0) || (paramInt2 <= 0)) {}
    while ((c == null) || (c.b == null) || (c.a == null)) {
      return;
    }
    int k;
    int j;
    int i;
    int m;
    int n;
    try
    {
      k = c.b.b();
      j = c.b.a();
      i = c.b.e();
      m = c.a.b();
      n = c.a.a();
      if ((m != 0) || (n != 0)) {
        break label137;
      }
      a = paramInt1;
      b = paramInt2;
      return;
    }
    catch (Exception localException1)
    {
      paramInt1 = 0;
    }
    cy.a(localException1, "MapController", "zoomToSpan");
    for (;;)
    {
      c(paramInt1);
      return;
      label137:
      float f1 = paramInt1 / n;
      float f2 = paramInt2 / m;
      try
      {
        f1 = Math.max(f1, f2);
        if (f1 > 1.0F)
        {
          paramInt2 = i - a(f1);
          paramInt1 = k;
          if (paramInt2 <= k) {
            continue;
          }
          paramInt1 = paramInt2;
          continue;
        }
        if (f1 >= 0.5D) {
          break label234;
        }
        paramInt1 = a(1.0F / f1);
        paramInt2 = paramInt1 + i - 1;
        paramInt1 = paramInt2;
        if (paramInt2 < j) {
          continue;
        }
        paramInt1 = j;
      }
      catch (Exception localException2)
      {
        paramInt1 = i;
      }
      break;
      label234:
      paramInt1 = i;
    }
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2)
  {
    e.a(paramInt1, paramInt2, paramInt3, paramBoolean1, paramBoolean2);
  }
  
  public void a(ae paramae)
  {
    if (b(paramae)) {
      c(paramae);
    }
  }
  
  public void a(ae paramae, int paramInt)
  {
    if ((!b(paramae)) && (!g(paramInt))) {
      return;
    }
    c(paramae);
    f(paramInt);
    t.a().b();
  }
  
  public void a(boolean paramBoolean)
  {
    e.a();
    f.a();
  }
  
  public int b()
  {
    return b;
  }
  
  public void b(int paramInt)
  {
    b = paramInt;
  }
  
  public void b(ae paramae, int paramInt)
  {
    f.a(paramae, null, null, paramInt);
  }
  
  public boolean b(int paramInt1, int paramInt2)
  {
    return a(paramInt1, paramInt2, true, true);
  }
  
  public int c(int paramInt)
  {
    if (!g(paramInt)) {
      return paramInt;
    }
    f(paramInt);
    t.a().b();
    return paramInt;
  }
  
  public void c(int paramInt1, int paramInt2)
  {
    if (d) {
      d = false;
    }
    while ((paramInt1 == 0) && (paramInt2 == 0)) {
      return;
    }
    if (y.p == true)
    {
      PointF localPointF1 = new PointF(0.0F, 0.0F);
      PointF localPointF2 = new PointF(paramInt1, paramInt2);
      c.h.a(localPointF1, localPointF2, c.b.e());
    }
    c.b.a(false, false);
  }
  
  public boolean c()
  {
    return d(1);
  }
  
  public boolean d()
  {
    return e(1);
  }
  
  boolean d(int paramInt)
  {
    return a(c.b.c() / 2, c.b.d() / 2, true, false, paramInt);
  }
  
  public void e()
  {
    d = true;
  }
  
  boolean e(int paramInt)
  {
    return a(c.b.c() / 2, c.b.d() / 2, false, false, paramInt);
  }
  
  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() != 0) {
      return false;
    }
    boolean bool = true;
    switch (paramInt)
    {
    default: 
      bool = false;
    }
    for (;;)
    {
      return bool;
      c(-10, 0);
      continue;
      c(10, 0);
      continue;
      c(0, -10);
      continue;
      c(0, 10);
    }
  }
  
  class a
    implements ch
  {
    private cg b = null;
    private Message c = null;
    private Runnable d = null;
    
    private a() {}
    
    private cg a(ae paramae, int paramInt)
    {
      int i = 500;
      if (paramInt < 500) {}
      for (;;)
      {
        return new cg(i, 10, ah.i, paramae, paramInt, this);
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
        paramae = ah.b(paramae);
        bb.this.a(paramae);
        return;
      }
      bb.this.a(paramae);
    }
    
    public void a(ae paramae, Message paramMessage, Runnable paramRunnable, int paramInt)
    {
      ac.a = true;
      ah.j = paramae.g();
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
      if (ac != null) {
        ac.a = false;
      }
    }
  }
  
  class b
    implements Animation.AnimationListener
  {
    private LinkedList<Animation> b = new LinkedList();
    private co c = null;
    
    private b() {}
    
    private void a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    {
      if (c == null) {
        c = new co(ab.g(), this);
      }
      c.e = paramBoolean;
      c.d = paramInt1;
      c.a(paramInt1, false, paramInt2, paramInt3);
    }
    
    private void b(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    {
      if (c == null) {
        c = new co(ab.g(), this);
      }
      c.d = paramInt1;
      c.e = paramBoolean;
      if (c.e == true)
      {
        Point localPoint = new Point(paramInt2, paramInt3);
        ae localae = ab.g().s().a(paramInt2, paramInt3);
        ah.i = ah.a(localae);
        ah.a(localPoint);
      }
      c.a(paramInt1, true, paramInt2, paramInt3);
    }
    
    public void a()
    {
      b.clear();
    }
    
    public void a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2)
    {
      if (!paramBoolean1)
      {
        a(paramInt3, paramInt1, paramInt2, paramBoolean2);
        return;
      }
      b(paramInt3, paramInt1, paramInt2, paramBoolean2);
    }
    
    public void onAnimationEnd(Animation paramAnimation)
    {
      paramAnimation = ab.g();
      if (b.size() == 0)
      {
        ad.b();
        return;
      }
      paramAnimation.startAnimation((Animation)b.remove());
    }
    
    public void onAnimationRepeat(Animation paramAnimation) {}
    
    public void onAnimationStart(Animation paramAnimation) {}
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */