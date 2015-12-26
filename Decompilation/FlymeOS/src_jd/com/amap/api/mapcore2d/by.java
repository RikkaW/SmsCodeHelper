package com.amap.api.mapcore2d;

import android.graphics.Canvas;
import android.graphics.Paint.FontMetrics;
import android.graphics.Point;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.TextOptions;

public class by
  implements as
{
  private b a;
  private be b;
  private String c;
  private int d;
  private int e;
  private LatLng f;
  private float g;
  private int h;
  private Typeface i;
  private boolean j;
  private float k;
  private int l;
  private int m;
  private Object n;
  private int o;
  
  public by(ag paramag, TextOptions paramTextOptions, be parambe)
  {
    b = parambe;
    c = paramTextOptions.getText();
    d = paramTextOptions.getFontSize();
    e = paramTextOptions.getFontColor();
    f = paramTextOptions.getPosition();
    g = paramTextOptions.getRotate();
    h = paramTextOptions.getBackgroundColor();
    i = paramTextOptions.getTypeface();
    j = paramTextOptions.isVisible();
    k = paramTextOptions.getZIndex();
    l = paramTextOptions.getAlignX();
    m = paramTextOptions.getAlignY();
    n = paramTextOptions.getObject();
    a = ((b)paramag);
  }
  
  public String a()
  {
    return c;
  }
  
  public void a(float paramFloat)
  {
    g = paramFloat;
    a.postInvalidate();
  }
  
  public void a(int paramInt)
  {
    d = paramInt;
    a.postInvalidate();
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    l = paramInt1;
    m = paramInt2;
    a.postInvalidate();
  }
  
  public void a(Canvas paramCanvas)
  {
    if ((TextUtils.isEmpty(c)) || (f == null)) {
      return;
    }
    TextPaint localTextPaint = new TextPaint();
    if (i == null) {
      i = Typeface.DEFAULT;
    }
    localTextPaint.setTypeface(i);
    localTextPaint.setAntiAlias(true);
    localTextPaint.setTextSize(d);
    float f1 = localTextPaint.measureText(c);
    float f2 = d;
    localTextPaint.setColor(h);
    Object localObject = new ae((int)(f.latitude * 1000000.0D), (int)(f.longitude * 1000000.0D));
    Point localPoint = new Point();
    a.s().a((ae)localObject, localPoint);
    paramCanvas.save();
    paramCanvas.rotate(-(g % 360.0F), x, y);
    localObject = localTextPaint.getFontMetrics();
    if ((l < 1) || (l > 3)) {
      l = 3;
    }
    if ((m < 4) || (m > 6)) {
      m = 6;
    }
    int i1;
    int i2;
    switch (l)
    {
    default: 
      i1 = 0;
      switch (m)
      {
      default: 
        i2 = 0;
      }
      break;
    }
    for (;;)
    {
      f2 = 2.0F + f2;
      paramCanvas.drawRect(i1 - 1, i2 - 1, i1 + (f1 + 2.0F), i2 + f2, localTextPaint);
      localTextPaint.setColor(e);
      paramCanvas.drawText(c, i1, i2 + f2 - bottom, localTextPaint);
      paramCanvas.restore();
      return;
      i1 = x;
      break;
      i1 = (int)(x - f1);
      break;
      i1 = (int)(x - f1 / 2.0F);
      break;
      i2 = y;
      continue;
      i2 = (int)(y - f2);
      continue;
      i2 = (int)(y - f2 / 2.0F);
    }
  }
  
  public void a(Typeface paramTypeface)
  {
    i = paramTypeface;
    a.postInvalidate();
  }
  
  public void a(Object paramObject)
  {
    n = paramObject;
  }
  
  public void a(String paramString)
  {
    c = paramString;
    a.postInvalidate();
  }
  
  public int b()
  {
    return d;
  }
  
  public void b(float paramFloat)
  {
    k = paramFloat;
    b.d();
  }
  
  public void b(int paramInt)
  {
    o = paramInt;
  }
  
  public void b(LatLng paramLatLng)
  {
    f = paramLatLng;
    a.postInvalidate();
  }
  
  public int c()
  {
    return e;
  }
  
  public void c(int paramInt)
  {
    e = paramInt;
    a.postInvalidate();
  }
  
  public float d()
  {
    return g;
  }
  
  public void d(int paramInt)
  {
    h = paramInt;
    a.postInvalidate();
  }
  
  public int e()
  {
    return h;
  }
  
  public Typeface f()
  {
    return i;
  }
  
  public int g()
  {
    return l;
  }
  
  public int h()
  {
    return m;
  }
  
  public void i()
  {
    if (b != null) {
      b.b(this);
    }
  }
  
  public float r()
  {
    return k;
  }
  
  public boolean s()
  {
    return j;
  }
  
  public LatLng t()
  {
    return f;
  }
  
  public Object u()
  {
    return n;
  }
  
  public int v()
  {
    return o;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.by
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */