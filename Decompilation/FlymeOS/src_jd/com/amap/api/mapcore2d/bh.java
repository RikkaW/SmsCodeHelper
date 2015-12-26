package com.amap.api.mapcore2d;

import android.graphics.Point;
import android.graphics.PointF;
import java.util.ArrayList;

class bh
{
  double a = 156543.0339D;
  int b = 0;
  double c = -2.003750834E7D;
  double d = 2.003750834E7D;
  public int e = y.d;
  public int f = y.c;
  public int g = 10;
  public double[] h = null;
  public ae i = null;
  public ae j = null;
  public Point k = null;
  public a l = null;
  bl.d m = null;
  private double n = 116.39716D;
  private double o = 39.91669D;
  private double p = 0.01745329251994329D;
  
  public bh(bl.d paramd)
  {
    m = paramd;
  }
  
  private int a(int paramInt1, int paramInt2)
  {
    int i2 = 1;
    int i1 = 0;
    while (i1 < paramInt2)
    {
      i2 *= paramInt1;
      i1 += 1;
    }
    return i2;
  }
  
  public float a(ae paramae1, ae paramae2)
  {
    double d4 = z.a(paramae1.c());
    double d3 = z.a(paramae1.d());
    double d2 = z.a(paramae2.c());
    double d1 = z.a(paramae2.d());
    double d5 = d4 * p;
    double d6 = d3 * p;
    d3 = d2 * p;
    d4 = d1 * p;
    d1 = Math.sin(d5);
    d2 = Math.sin(d6);
    d5 = Math.cos(d5);
    d6 = Math.cos(d6);
    double d7 = Math.sin(d3);
    double d8 = Math.sin(d4);
    d3 = Math.cos(d3);
    d4 = Math.cos(d4);
    paramae1 = new double[3];
    paramae2 = new double[3];
    paramae1[0] = (d5 * d6);
    paramae1[1] = (d6 * d1);
    paramae1[2] = d2;
    paramae2[0] = (d4 * d3);
    paramae2[1] = (d4 * d7);
    paramae2[2] = d8;
    return (float)(Math.asin(Math.sqrt((paramae1[0] - paramae2[0]) * (paramae1[0] - paramae2[0]) + (paramae1[1] - paramae2[1]) * (paramae1[1] - paramae2[1]) + (paramae1[2] - paramae2[2]) * (paramae1[2] - paramae2[2])) / 2.0D) * 1.27420015798544E7D);
  }
  
  PointF a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, PointF paramPointF, int paramInt5, int paramInt6)
  {
    PointF localPointF = new PointF();
    x = ((paramInt1 - paramInt3) * y.i + x);
    if (b == 0) {
      y = ((paramInt2 - paramInt4) * y.i + y);
    }
    for (;;)
    {
      if ((x + y.i > 0.0F) && (x < paramInt5) && (y + y.i > 0.0F))
      {
        paramPointF = localPointF;
        if (y < paramInt6) {}
      }
      else
      {
        paramPointF = null;
      }
      return paramPointF;
      if (b == 1) {
        y -= (paramInt2 - paramInt4) * y.i;
      }
    }
  }
  
  PointF a(ae paramae1, ae paramae2, Point paramPoint, double paramDouble)
  {
    PointF localPointF = new PointF();
    x = ((float)((paramae1.e() - paramae2.e()) / paramDouble + x));
    y = ((float)(y - (paramae1.f() - paramae2.f()) / paramDouble));
    return localPointF;
  }
  
  public ae a(PointF paramPointF, ae paramae, Point paramPoint, double paramDouble, a parama)
  {
    return b(b(paramPointF, paramae, paramPoint, paramDouble, parama));
  }
  
  public ae a(ae paramae)
  {
    if (paramae == null) {
      return null;
    }
    double d1 = paramae.b() / 1000000.0D;
    double d2 = paramae.a() / 1000000.0D * 2.003750834E7D / 180.0D;
    return new ae(Math.log(Math.tan((d1 + 90.0D) * 3.141592653589793D / 360.0D)) / 0.017453292519943295D * 2.003750834E7D / 180.0D, d2, false);
  }
  
  public ArrayList<n.a> a(ae paramae, int paramInt1, int paramInt2, int paramInt3)
  {
    double d2 = h[g];
    int i5 = (int)((paramae.e() - c) / (y.i * d2));
    double d3 = y.i * i5;
    double d4 = c;
    double d1 = 0.0D;
    int i2;
    if (b == 0)
    {
      i2 = (int)((d - paramae.f()) / (y.i * d2));
      d1 = d - y.i * i2 * d2;
    }
    for (;;)
    {
      paramae = a(new ae(d1, d4 + d3 * d2, false), paramae, k, d2);
      Object localObject = new n.a(i5, i2, g, -1);
      f = paramae;
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(localObject);
      int i3 = 1;
      paramInt1 = 0;
      int i4 = i5 - i3;
      label176:
      int i6;
      int i1;
      if (i4 <= i5 + i3)
      {
        i6 = i2 + i3;
        localObject = a(i4, i6, i5, i2, paramae, paramInt2, paramInt3);
        i1 = paramInt1;
        if (localObject != null)
        {
          if (paramInt1 != 0) {
            break label594;
          }
          paramInt1 = 1;
        }
      }
      label403:
      label588:
      label594:
      for (;;)
      {
        n.a locala = new n.a(i4, i6, g, -1);
        f = ((PointF)localObject);
        localArrayList.add(locala);
        i1 = paramInt1;
        i6 = i2 - i3;
        localObject = a(i4, i6, i5, i2, paramae, paramInt2, paramInt3);
        paramInt1 = i1;
        if (localObject != null)
        {
          paramInt1 = i1;
          if (i1 == 0) {
            paramInt1 = 1;
          }
          locala = new n.a(i4, i6, g, -1);
          f = ((PointF)localObject);
          localArrayList.add(locala);
        }
        i4 += 1;
        break label176;
        if (b != 1) {
          break label597;
        }
        i2 = (int)((paramae.f() - d) / (y.i * d2));
        d1 = (i2 + 1) * y.i * d2;
        break;
        i4 = i2 + i3 - 1;
        i1 = paramInt1;
        if (i4 > i2 - i3)
        {
          i6 = i5 + i3;
          localObject = a(i6, i4, i5, i2, paramae, paramInt2, paramInt3);
          paramInt1 = i1;
          if (localObject != null) {
            if (i1 != 0) {
              break label588;
            }
          }
        }
        for (paramInt1 = 1;; paramInt1 = i1)
        {
          locala = new n.a(i6, i4, g, -1);
          f = ((PointF)localObject);
          localArrayList.add(locala);
          i6 = i5 - i3;
          localObject = a(i6, i4, i5, i2, paramae, paramInt2, paramInt3);
          i1 = paramInt1;
          if (localObject != null)
          {
            i1 = paramInt1;
            if (paramInt1 == 0) {
              i1 = 1;
            }
            locala = new n.a(i6, i4, g, -1);
            f = ((PointF)localObject);
            localArrayList.add(locala);
          }
          i4 -= 1;
          break label403;
          if (i1 == 0) {
            return localArrayList;
          }
          i3 += 1;
          break;
        }
      }
      label597:
      i2 = 0;
    }
  }
  
  public void a()
  {
    a = (d * 2.0D / y.i);
    h = new double[f + 1];
    int i1 = 0;
    while (i1 <= f)
    {
      double d1 = a / a(2, i1);
      h[i1] = d1;
      i1 += 1;
    }
    i = a(new ae(o, n, true));
    j = i.g();
    k = new Point(m.c() / 2, m.d() / 2);
    l = new a();
    l.a = -2.0037508E7F;
    l.b = 2.0037508E7F;
    l.c = 2.0037508E7F;
    l.d = -2.0037508E7F;
  }
  
  public void a(Point paramPoint)
  {
    k = paramPoint;
  }
  
  public void a(PointF paramPointF1, PointF paramPointF2, int paramInt)
  {
    double d1 = h[paramInt];
    paramPointF1 = b(paramPointF1, i, k, d1, l);
    paramPointF2 = b(paramPointF2, i, k, d1, l);
    d1 = paramPointF2.e();
    double d4 = paramPointF1.e();
    double d2 = paramPointF2.f();
    double d3 = paramPointF1.f();
    d1 = i.e() + (d1 - d4);
    d3 = i.f() + (d2 - d3);
    for (;;)
    {
      d2 = d1;
      if (d1 >= l.a) {
        break;
      }
      d1 += l.b - l.a;
    }
    for (;;)
    {
      d1 = d3;
      if (d2 <= l.b) {
        break;
      }
      d2 -= l.b - l.a;
    }
    for (;;)
    {
      d3 = d1;
      if (d1 >= l.d) {
        break;
      }
      d1 += l.c - l.d;
    }
    while (d3 > l.c) {
      d3 -= l.c - l.d;
    }
    i.b(d3);
    i.a(d2);
  }
  
  public PointF b(ae paramae1, ae paramae2, Point paramPoint, double paramDouble)
  {
    paramae1 = a(a(paramae1), paramae2, paramPoint, paramDouble);
    return m.g().b(paramae1);
  }
  
  ae b(PointF paramPointF, ae paramae, Point paramPoint, double paramDouble, a parama)
  {
    paramPointF = m.g().c(paramPointF);
    float f1 = x;
    float f2 = x;
    float f3 = y;
    float f4 = y;
    double d1 = paramae.e();
    d1 = (f1 - f2) * paramDouble + d1;
    double d2 = paramae.f();
    double d3 = f3 - f4;
    while (d1 < a) {
      d1 += b - a;
    }
    for (;;)
    {
      if (d1 > b) {
        d1 -= b - a;
      } else {
        for (;;)
        {
          if (paramDouble < d)
          {
            paramDouble += c - d;
          }
          else
          {
            for (;;)
            {
              if (paramDouble > c) {
                paramDouble -= c - d;
              } else {
                return new ae(paramDouble, d1, false);
              }
            }
            paramDouble = d2 - d3 * paramDouble;
          }
        }
      }
    }
  }
  
  public ae b(ae paramae)
  {
    float f1 = (float)(paramae.e() * 180.0D / 2.003750834E7D);
    return new ae((int)((float)(57.29577951308232D * (2.0D * Math.atan(Math.exp((float)(paramae.f() * 180.0D / 2.003750834E7D) * 3.141592653589793D / 180.0D)) - 1.5707963267948966D)) * 1000000.0D), (int)(f1 * 1000000.0D));
  }
  
  static class a
  {
    float a;
    float b;
    float c;
    float d;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */