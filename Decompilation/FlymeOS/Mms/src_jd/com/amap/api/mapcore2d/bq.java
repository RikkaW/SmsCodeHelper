package com.amap.api.mapcore2d;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.os.RemoteException;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.LatLngBounds.Builder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class bq
  implements ap
{
  private b a;
  private float b = 10.0F;
  private int c = -16777216;
  private float d = 0.0F;
  private boolean e = true;
  private boolean f = false;
  private boolean g = false;
  private String h;
  private List<an> i = new ArrayList();
  private List<LatLng> j = new ArrayList();
  private LatLngBounds k = null;
  
  public bq(b paramb)
  {
    a = paramb;
    try
    {
      h = c();
      return;
    }
    catch (RemoteException paramb)
    {
      cy.a(paramb, "PolylineDelegateImp", "PolylineDelegateImp");
    }
  }
  
  private List<LatLng> m()
  {
    if (i != null)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = i.iterator();
      while (localIterator.hasNext())
      {
        an localan = (an)localIterator.next();
        if (localan != null)
        {
          aa localaa = new aa();
          a.b(a, b, localaa);
          localArrayList.add(new LatLng(b, a));
        }
      }
      return localArrayList;
    }
    return null;
  }
  
  an a(an paraman1, an paraman2, an paraman3, double paramDouble, int paramInt)
  {
    an localan = new an();
    double d1 = a - a;
    double d2 = b - b;
    double d3 = d2 * d2 / (d1 * d1);
    b = ((int)(paramInt * paramDouble / Math.sqrt(d3 + 1.0D) + b));
    a = ((int)(d2 * (b - b) / d1 + a));
    return localan;
  }
  
  public void a(float paramFloat)
  {
    d = paramFloat;
    a.invalidate();
  }
  
  public void a(int paramInt)
  {
    c = paramInt;
  }
  
  public void a(Canvas paramCanvas)
  {
    if ((i == null) || (i.size() == 0) || (b <= 0.0F)) {
      return;
    }
    Path localPath = new Path();
    Object localObject = new ae(i.get(0)).b, i.get(0)).a);
    Point localPoint = new Point();
    localObject = a.s().a((ae)localObject, localPoint);
    localPath.moveTo(x, y);
    int m = 1;
    while (m < i.size())
    {
      localObject = new ae(i.get(m)).b, i.get(m)).a);
      localPoint = new Point();
      localObject = a.s().a((ae)localObject, localPoint);
      localPath.lineTo(x, y);
      m += 1;
    }
    localObject = new Paint();
    ((Paint)localObject).setColor(h());
    ((Paint)localObject).setAntiAlias(true);
    ((Paint)localObject).setStrokeWidth(g());
    ((Paint)localObject).setStyle(Paint.Style.STROKE);
    if (f)
    {
      m = (int)g();
      ((Paint)localObject).setPathEffect(new DashPathEffect(new float[] { m * 3, m, m * 3, m }, 1.0F));
    }
    paramCanvas.drawPath(localPath, (Paint)localObject);
  }
  
  void a(LatLng paramLatLng1, LatLng paramLatLng2, List<an> paramList, LatLngBounds.Builder paramBuilder)
  {
    double d1 = Math.abs(longitude - longitude) * 3.141592653589793D / 180.0D;
    Object localObject = new LatLng((latitude + latitude) / 2.0D, (longitude + longitude) / 2.0D);
    paramBuilder.include(paramLatLng1).include((LatLng)localObject).include(paramLatLng2);
    if (latitude > 0.0D) {}
    for (int m = 1;; m = -1)
    {
      paramBuilder = new an();
      a.a(latitude, longitude, paramBuilder);
      paramLatLng1 = new an();
      a.a(latitude, longitude, paramLatLng1);
      paramLatLng2 = new an();
      a.a(latitude, longitude, paramLatLng2);
      double d2 = Math.cos(0.5D * d1);
      paramLatLng2 = a(paramBuilder, paramLatLng1, paramLatLng2, Math.hypot(a - a, b - b) * 0.5D * Math.tan(0.5D * d1), m);
      localObject = new ArrayList();
      ((List)localObject).add(paramBuilder);
      ((List)localObject).add(paramLatLng2);
      ((List)localObject).add(paramLatLng1);
      a((List)localObject, paramList, d2);
      return;
    }
  }
  
  public void a(List<LatLng> paramList)
  {
    if ((g) || (f)) {
      j = paramList;
    }
    b(paramList);
  }
  
  void a(List<an> paramList1, List<an> paramList2, double paramDouble)
  {
    if (paramList1.size() != 3) {}
    for (;;)
    {
      return;
      for (int m = 0; m <= 10; m = (int)(m + 1.0F))
      {
        float f1 = m / 10.0F;
        an localan = new an();
        double d1 = f1;
        double d2 = f1;
        double d3 = get0a;
        double d4 = 2.0F * f1;
        double d5 = f1;
        double d6 = get1a;
        double d7 = get2a * (f1 * f1);
        double d8 = f1;
        double d9 = f1;
        double d10 = get0b;
        double d11 = 2.0F * f1;
        double d12 = f1;
        double d13 = get1b;
        double d14 = get2b * (f1 * f1);
        double d15 = f1;
        double d16 = f1;
        double d17 = 2.0F * f1;
        double d18 = f1;
        double d19 = f1 * f1;
        double d20 = f1;
        double d21 = f1;
        double d22 = 2.0F * f1;
        double d23 = f1;
        double d24 = f1 * f1;
        a = ((int)(((1.0D - d1) * (1.0D - d2) * d3 + d4 * (1.0D - d5) * d6 * paramDouble + d7) / ((1.0D - d15) * (1.0D - d16) + d17 * (1.0D - d18) * paramDouble + d19)));
        b = ((int)(((1.0D - d8) * (1.0D - d9) * d10 + d11 * (1.0D - d12) * d13 * paramDouble + d14) / ((1.0D - d20) * (1.0D - d21) + d22 * (1.0D - d23) * paramDouble + d24)));
        paramList2.add(localan);
      }
    }
  }
  
  public void a(boolean paramBoolean)
  {
    e = paramBoolean;
  }
  
  public boolean a()
  {
    if (k == null) {}
    LatLngBounds localLatLngBounds;
    do
    {
      return false;
      localLatLngBounds = a.x();
      if (localLatLngBounds == null) {
        return true;
      }
    } while ((!localLatLngBounds.contains(k)) && (!k.intersects(localLatLngBounds)));
    return true;
  }
  
  public boolean a(am paramam)
  {
    return (equals(paramam)) || (paramam.c().equals(c()));
  }
  
  public void b()
  {
    a.a(c());
  }
  
  public void b(float paramFloat)
  {
    b = paramFloat;
  }
  
  void b(List<LatLng> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0)) {}
    LatLngBounds.Builder localBuilder;
    do
    {
      return;
      localBuilder = LatLngBounds.builder();
      i.clear();
      if (paramList != null)
      {
        Iterator localIterator = paramList.iterator();
        paramList = null;
        while (localIterator.hasNext())
        {
          LatLng localLatLng = (LatLng)localIterator.next();
          if ((localLatLng != null) && (!localLatLng.equals(paramList)))
          {
            if (!g)
            {
              paramList = new an();
              a.a(latitude, longitude, paramList);
              i.add(paramList);
              localBuilder.include(localLatLng);
            }
            for (;;)
            {
              paramList = localLatLng;
              break;
              if (paramList != null) {
                if (Math.abs(longitude - longitude) < 0.01D)
                {
                  an localan = new an();
                  a.a(latitude, longitude, localan);
                  i.add(localan);
                  localBuilder.include(paramList);
                  paramList = new an();
                  a.a(latitude, longitude, paramList);
                  i.add(paramList);
                  localBuilder.include(localLatLng);
                }
                else
                {
                  a(paramList, localLatLng, i, localBuilder);
                }
              }
            }
          }
        }
      }
    } while (i.size() <= 0);
    k = localBuilder.build();
  }
  
  public void b(boolean paramBoolean)
  {
    f = paramBoolean;
  }
  
  public String c()
  {
    if (h == null) {
      h = ac.a("Polyline");
    }
    return h;
  }
  
  public void c(boolean paramBoolean)
  {
    if (g != paramBoolean) {
      g = paramBoolean;
    }
  }
  
  public float d()
  {
    return d;
  }
  
  public boolean e()
  {
    return e;
  }
  
  public int f()
  {
    return super.hashCode();
  }
  
  public float g()
  {
    return b;
  }
  
  public int h()
  {
    return c;
  }
  
  public List<LatLng> i()
  {
    if ((g) || (f)) {
      return j;
    }
    return m();
  }
  
  public boolean j()
  {
    return f;
  }
  
  public boolean k()
  {
    return g;
  }
  
  public void l() {}
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */