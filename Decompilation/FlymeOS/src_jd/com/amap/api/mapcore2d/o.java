package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;

class o
{
  private Bitmap a = null;
  private Canvas b = null;
  private Bitmap.Config c;
  
  public o(Bitmap.Config paramConfig)
  {
    c = paramConfig;
  }
  
  public void a()
  {
    if (a != null) {
      a.recycle();
    }
    a = null;
    b = null;
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    a();
    a = Bitmap.createBitmap(paramInt1, paramInt2, c);
    b = new Canvas(a);
  }
  
  public void a(Bitmap paramBitmap)
  {
    a = paramBitmap;
    b = new Canvas(a);
  }
  
  public void a(p paramp)
  {
    b.save(1);
    paramp.a(b);
    b.restore();
  }
  
  public Bitmap b()
  {
    return a;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.o
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */