package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PointF;

class n
{
  private static Paint a = null;
  private static Bitmap b = null;
  private static int c = Color.rgb(222, 215, 214);
  
  public static int a()
  {
    return c;
  }
  
  public static Paint b()
  {
    try
    {
      if (a == null)
      {
        a = new Paint();
        a.setColor(-7829368);
        a.setAlpha(90);
        localObject1 = new DashPathEffect(new float[] { 2.0F, 2.5F }, 1.0F);
        a.setPathEffect((PathEffect)localObject1);
      }
      Object localObject1 = a;
      return (Paint)localObject1;
    }
    finally {}
  }
  
  public static Bitmap c()
  {
    try
    {
      if (b == null)
      {
        localObject1 = new n.1();
        o localo = new o(Bitmap.Config.ARGB_4444);
        localo.a(y.i, y.i);
        localo.a((p)localObject1);
        b = localo.b();
      }
      Object localObject1 = b;
      return (Bitmap)localObject1;
    }
    finally {}
  }
  
  static class a
    implements Cloneable
  {
    public int a = 0;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public PointF f;
    public int g = -1;
    
    public a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      b = paramInt1;
      c = paramInt2;
      d = paramInt3;
      e = paramInt4;
    }
    
    public a(a parama)
    {
      b = b;
      c = c;
      d = d;
      e = e;
      f = f;
      a = a;
    }
    
    public a a()
    {
      return new a(this);
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (!(paramObject instanceof a)) {
          return false;
        }
        paramObject = (a)paramObject;
      } while ((b == b) && (c == c) && (d == d) && (e == e));
      return false;
    }
    
    public int hashCode()
    {
      return b * 7 + c * 11 + d * 13 + e;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(b);
      localStringBuilder.append("-");
      localStringBuilder.append(c);
      localStringBuilder.append("-");
      localStringBuilder.append(d);
      localStringBuilder.append("-");
      localStringBuilder.append(e);
      return localStringBuilder.toString();
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.n
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */