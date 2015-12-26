package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

class aw
  extends ax
{
  public String a = "";
  public int b = 18;
  public int c = 3;
  public boolean d = true;
  public boolean e = true;
  public boolean f = false;
  public boolean g = false;
  public boolean h = false;
  public long i = 0L;
  public ck j = null;
  public int k = -1;
  public String l = "";
  q m = null;
  r n = null;
  bw<n.a> o = null;
  private String p = "LayerPropertys";
  
  protected void a()
  {
    n.a(null);
    m.c();
    o.clear();
  }
  
  protected void a(Canvas paramCanvas)
  {
    for (;;)
    {
      try
      {
        if (o == null) {
          return;
        }
        Iterator localIterator = o.iterator();
        if (!localIterator.hasNext()) {
          break;
        }
        Object localObject = (n.a)localIterator.next();
        Bitmap localBitmap;
        if (g < 0)
        {
          if (e)
          {
            localBitmap = n.c();
            localObject = f;
            if ((localBitmap != null) && (localObject != null))
            {
              float f1 = x;
              float f2 = y.i;
              float f3 = y;
              float f4 = y.i;
              paramCanvas.drawBitmap(localBitmap, null, new RectF(x, y, f1 + f2, f3 + f4), null);
            }
          }
        }
        else {
          localBitmap = m.a(g);
        }
      }
      catch (ConcurrentModificationException paramCanvas)
      {
        cy.a(paramCanvas, p, "drawLayer");
        return;
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof aw)) {
      return false;
    }
    paramObject = (aw)paramObject;
    return a.equals(a);
  }
  
  public int hashCode()
  {
    return k;
  }
  
  public String toString()
  {
    return a;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.aw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */