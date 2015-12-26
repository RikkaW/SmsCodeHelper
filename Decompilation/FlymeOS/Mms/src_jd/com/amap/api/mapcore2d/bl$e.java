package com.amap.api.mapcore2d;

import android.graphics.Point;
import android.graphics.PointF;
import android.os.RemoteException;
import java.util.HashMap;

public class bl$e
  implements br
{
  private int b = 0;
  private HashMap<Float, Float> c = new HashMap();
  
  public bl$e(bl parambl) {}
  
  private int a(boolean paramBoolean)
  {
    int i = a.b.c();
    ae localae1 = a(0, a.b.d());
    ae localae2 = a(i, 0);
    if (paramBoolean) {
      return Math.abs(localae1.a() - localae2.a());
    }
    return Math.abs(localae1.b() - localae2.b());
  }
  
  public float a(float paramFloat)
  {
    int i = a.b.e();
    if ((c.size() > 30) || (i != b))
    {
      b = i;
      c.clear();
    }
    if (!c.containsKey(Float.valueOf(paramFloat)))
    {
      ae localae1 = a(0, 0);
      ae localae2 = a(0, 100);
      float f = a.h.a(localae1, localae2);
      if (f <= 0.0F) {
        return 0.0F;
      }
      f = paramFloat / f;
      c.put(Float.valueOf(paramFloat), Float.valueOf(100.0F * f));
    }
    return ((Float)c.get(Float.valueOf(paramFloat))).floatValue();
  }
  
  public int a()
  {
    return a(false);
  }
  
  public Point a(ae paramae, Point paramPoint)
  {
    int i = a.b.e();
    paramae = a.h.b(paramae, a.h.i, a.h.k, a.h.h[i]);
    bm localbm = bl.d.a(a.b).G();
    Point localPoint = aa.b).a().h.k;
    float f1;
    float f2;
    int m;
    int k;
    int j;
    if (m)
    {
      int n = 1;
      try
      {
        boolean bool = a.g.g.f();
        n = bool;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          localRemoteException.printStackTrace();
          continue;
          j = (int)x;
          i = (int)y;
        }
      }
      if ((l) && (n != 0))
      {
        f1 = bm.j * ((int)x - f.x) + f.x + (g.x - f.x);
        f2 = bm.j * ((int)y - f.y) + f.y + (g.y - f.y);
        i = (int)f1;
        m = (int)f2;
        k = i;
        if (f1 >= i + 0.5D) {
          k = i + 1;
        }
        i = m;
        j = k;
        if (f2 >= m + 0.5D)
        {
          i = m + 1;
          j = k;
        }
      }
    }
    for (;;)
    {
      paramae = new Point(j, i);
      if (paramPoint != null)
      {
        x = x;
        y = y;
      }
      return paramae;
      f1 = co.c;
      f2 = (int)x - x;
      f1 = x + f1 * f2;
      f2 = co.c * ((int)y - y) + y;
      i = (int)f1;
      m = (int)f2;
      k = i;
      if (f1 >= i + 0.5D) {
        k = i + 1;
      }
      i = m;
      j = k;
      if (f2 >= m + 0.5D)
      {
        i = m + 1;
        j = k;
      }
    }
  }
  
  public ae a(int paramInt1, int paramInt2)
  {
    int i = a.b.e();
    PointF localPointF = new PointF(paramInt1, paramInt2);
    return a.h.a(localPointF, a.h.i, a.h.k, a.h.h[i], a.h.l);
  }
  
  public int b()
  {
    return a(true);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bl.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */