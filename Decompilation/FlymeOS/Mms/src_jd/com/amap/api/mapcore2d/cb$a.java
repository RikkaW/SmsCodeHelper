package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.graphics.PointF;

public class cb$a
  implements Cloneable
{
  public final int a;
  public final int b;
  public final int c;
  public cv.a d = null;
  private final int f;
  private PointF g;
  private Bitmap h = null;
  private int i = 0;
  
  private cb$a(cb paramcb, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    a = paramInt1;
    b = paramInt2;
    c = paramInt3;
    f = paramInt4;
  }
  
  private cb$a(cb paramcb, a parama)
  {
    a = a;
    b = b;
    c = c;
    f = f;
    g = g;
  }
  
  public a a()
  {
    return new a(e, this);
  }
  
  public void a(Bitmap paramBitmap)
  {
    if ((paramBitmap != null) && (!paramBitmap.isRecycled())) {}
    do
    {
      for (;;)
      {
        try
        {
          d = null;
          int j = paramBitmap.getWidth();
          int k = paramBitmap.getHeight();
          h = cy.a(paramBitmap, cy.a(j), cy.a(k));
          if (cb.b(e) != null) {
            break;
          }
          return;
        }
        catch (Exception paramBitmap)
        {
          cy.a(paramBitmap, "TileOverDelegateImp", "setBitmap");
          if (i >= 3) {
            continue;
          }
          cb.a(e).a(true, this);
          i += 1;
          cw.a("TileOverlayDelegateImp", "setBitmap Exception: " + this + "retry: " + i, 111);
          continue;
        }
        if (i < 3)
        {
          cb.a(e).a(true, this);
          i += 1;
          cw.a("TileOverlayDelegateImp", "setBitmap failed: " + this + "retry: " + i, 111);
        }
      }
    } while (cb.b(e).a() == null);
    be).a().g.postInvalidate();
  }
  
  public void b()
  {
    cv.a(this);
    if ((h != null) && (!h.isRecycled())) {
      h.recycle();
    }
    h = null;
    d = null;
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
    } while ((a == a) && (b == b) && (c == c) && (f == f));
    return false;
  }
  
  public int hashCode()
  {
    return a * 7 + b * 11 + c * 13 + f;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(a);
    localStringBuilder.append("-");
    localStringBuilder.append(b);
    localStringBuilder.append("-");
    localStringBuilder.append(c);
    localStringBuilder.append("-");
    localStringBuilder.append(f);
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cb.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */