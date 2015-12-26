package com.amap.api.mapcore2d;

import android.graphics.PointF;

class n$a
  implements Cloneable
{
  public int a = 0;
  public final int b;
  public final int c;
  public final int d;
  public final int e;
  public PointF f;
  public int g = -1;
  
  public n$a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    b = paramInt1;
    c = paramInt2;
    d = paramInt3;
    e = paramInt4;
  }
  
  public n$a(a parama)
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

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.n.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */