package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.FloatMath;
import android.view.MotionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

abstract class bm
{
  static float j = 1.0F;
  private static Method p;
  private static Method q;
  private static boolean r = false;
  private static boolean s = false;
  b a;
  int b = 0;
  Matrix c = new Matrix();
  Matrix d = new Matrix();
  PointF e = new PointF();
  PointF f = new PointF();
  PointF g = new PointF();
  float h = 1.0F;
  float i = 1.0F;
  boolean k = false;
  boolean l = false;
  boolean m = false;
  public int n = 0;
  public long o = 0L;
  
  public static a a(Context paramContext, b paramb)
  {
    paramContext = new a();
    a = paramb;
    return paramContext;
  }
  
  private static void b(MotionEvent paramMotionEvent)
  {
    if (s) {}
    for (;;)
    {
      return;
      s = true;
      try
      {
        p = paramMotionEvent.getClass().getMethod("getX", new Class[] { Integer.TYPE });
        q = paramMotionEvent.getClass().getMethod("getY", new Class[] { Integer.TYPE });
        if ((p != null) && (q != null))
        {
          r = true;
          return;
        }
      }
      catch (Exception paramMotionEvent)
      {
        cy.a(paramMotionEvent, "MutiTouchGestureDetector", "checkSDKForMuti");
      }
    }
  }
  
  public static class a
    extends bm
  {
    float p;
    float q;
    float r;
    float s;
    long t = 0L;
    int u = 0;
    int v = 0;
    
    private void a(PointF paramPointF, MotionEvent paramMotionEvent)
    {
      float f2 = 0.0F;
      try
      {
        f1 = ((Float)bm.b().invoke(paramMotionEvent, new Object[] { Integer.valueOf(0) })).floatValue();
        f3 = ((Float)bm.b().invoke(paramMotionEvent, new Object[] { Integer.valueOf(1) })).floatValue();
        f1 = f3 + f1;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        for (;;)
        {
          float f3;
          float f4;
          cy.a(localIllegalArgumentException, "MutiTouchGestureDetector", "midPoint");
          f1 = 0.0F;
        }
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        for (;;)
        {
          cy.a(localIllegalAccessException, "MutiTouchGestureDetector", "midPoint");
          f1 = 0.0F;
        }
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        for (;;)
        {
          label118:
          cy.a(localInvocationTargetException, "MutiTouchGestureDetector", "midPoint");
          float f1 = 0.0F;
        }
      }
      try
      {
        f3 = ((Float)bm.c().invoke(paramMotionEvent, new Object[] { Integer.valueOf(0) })).floatValue();
        f4 = ((Float)bm.c().invoke(paramMotionEvent, new Object[] { Integer.valueOf(1) })).floatValue();
        f2 = f3 + f4;
      }
      catch (IllegalArgumentException paramMotionEvent)
      {
        cy.a(paramMotionEvent, "MutiTouchGestureDetector", "midPoint");
        break label118;
      }
      catch (IllegalAccessException paramMotionEvent)
      {
        cy.a(paramMotionEvent, "MutiTouchGestureDetector", "midPoint");
        break label118;
      }
      catch (InvocationTargetException paramMotionEvent)
      {
        cy.a(paramMotionEvent, "MutiTouchGestureDetector", "midPoint");
        break label118;
      }
      f4 = f1;
      f3 = f2;
      if (u != 0)
      {
        f4 = f1;
        f3 = f2;
        if (v != 0)
        {
          f4 = u;
          f3 = v;
        }
      }
      paramPointF.set(f4 / 2.0F, f3 / 2.0F);
    }
    
    private float b(MotionEvent paramMotionEvent)
    {
      float f2 = 0.0F;
      try
      {
        f1 = ((Float)bm.b().invoke(paramMotionEvent, new Object[] { Integer.valueOf(0) })).floatValue();
        f3 = ((Float)bm.b().invoke(paramMotionEvent, new Object[] { Integer.valueOf(1) })).floatValue();
        f1 -= f3;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        for (;;)
        {
          float f3;
          float f4;
          cy.a(localIllegalArgumentException, "MutiTouchGestureDetector", "distance");
          f1 = 0.0F;
        }
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        for (;;)
        {
          cy.a(localIllegalAccessException, "MutiTouchGestureDetector", "distance");
          f1 = 0.0F;
        }
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        for (;;)
        {
          label116:
          cy.a(localInvocationTargetException, "MutiTouchGestureDetector", "distance");
          float f1 = 0.0F;
        }
      }
      try
      {
        f3 = ((Float)bm.c().invoke(paramMotionEvent, new Object[] { Integer.valueOf(0) })).floatValue();
        f4 = ((Float)bm.c().invoke(paramMotionEvent, new Object[] { Integer.valueOf(1) })).floatValue();
        f2 = f3 - f4;
      }
      catch (IllegalArgumentException paramMotionEvent)
      {
        cy.a(paramMotionEvent, "MutiTouchGestureDetector", "distance");
        break label116;
      }
      catch (IllegalAccessException paramMotionEvent)
      {
        cy.a(paramMotionEvent, "MutiTouchGestureDetector", "distance");
        break label116;
      }
      catch (InvocationTargetException paramMotionEvent)
      {
        cy.a(paramMotionEvent, "MutiTouchGestureDetector", "distance");
        break label116;
      }
      return FloatMath.sqrt(f1 * f1 + f2 * f2);
    }
    
    public boolean a(MotionEvent paramMotionEvent, int paramInt1, int paramInt2)
    {
      boolean bool1 = true;
      u = paramInt1;
      v = paramInt2;
      bm.a(paramMotionEvent);
      if (!bm.a()) {
        return false;
      }
      switch (paramMotionEvent.getAction() & 0xFF)
      {
      }
      float f2;
      label529:
      label532:
      do
      {
        bool1 = false;
        for (;;)
        {
          return bool1;
          t = paramMotionEvent.getEventTime();
          p = paramMotionEvent.getX();
          q = paramMotionEvent.getY();
          d.set(c);
          e.set(p, q);
          b = 1;
          bool1 = false;
          continue;
          n += 1;
          if (n != 1) {
            break;
          }
          m = true;
          j = 1.0F;
          h = b(paramMotionEvent);
          if (h <= 10.0F) {
            break;
          }
          c.reset();
          d.reset();
          d.set(c);
          a(f, paramMotionEvent);
          b = 2;
          k = true;
          bool1 = a.a(e) | false;
          r = f.x;
          s = f.y;
          continue;
          o = paramMotionEvent.getEventTime();
          k = false;
          b = 0;
          bool1 = false;
          continue;
          n -= 1;
          if (n == 1)
          {
            m = true;
            b = 2;
          }
          if (n != 0) {
            break;
          }
          a(f, paramMotionEvent);
          l = false;
          m = false;
          if (!k) {
            break;
          }
          bool1 = a.a(i, f) | false;
          b = 0;
          continue;
          if (b != 1) {
            break label532;
          }
          f1 = paramMotionEvent.getX();
          f2 = paramMotionEvent.getY();
          c.set(d);
          c.postTranslate(paramMotionEvent.getX() - e.x, paramMotionEvent.getY() - e.y);
          boolean bool2 = a.a(f1 - p, f2 - q);
          p = f1;
          q = f2;
          bool2 = a.a(c) | false | bool2;
          if (paramMotionEvent.getEventTime() - t >= 30L) {
            bool1 = bool2;
          }
        }
        if (b != 2) {
          break;
        }
        f2 = b(paramMotionEvent);
        i = 0.0F;
      } while ((f2 <= 10.0F) || (Math.abs(f2 - h) <= 5.0F));
      c.set(d);
      if (f2 > h) {}
      for (float f1 = f2 / h;; f1 = h / f2)
      {
        i = f1;
        j = f2 / h;
        if (f2 < h) {
          i = (-i);
        }
        a(g, paramMotionEvent);
        bool1 = a.a(g.x - r, g.y - s);
        r = g.x;
        s = g.y;
        c.postScale(f2 / h, f2 / h, f.x, f.y);
        bool1 = bool1 | false | a.c(i) | a.b(c);
        l = true;
        break label529;
        break;
      }
    }
  }
  
  public static abstract interface b
  {
    public abstract boolean a(float paramFloat1, float paramFloat2);
    
    public abstract boolean a(float paramFloat, PointF paramPointF);
    
    public abstract boolean a(Matrix paramMatrix);
    
    public abstract boolean a(PointF paramPointF);
    
    public abstract boolean b(Matrix paramMatrix);
    
    public abstract boolean c(float paramFloat);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */