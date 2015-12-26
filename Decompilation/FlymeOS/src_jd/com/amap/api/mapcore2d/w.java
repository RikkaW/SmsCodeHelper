package com.amap.api.mapcore2d;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.os.RemoteException;
import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.model.LatLng;

class w
  implements ah
{
  private LatLng a = null;
  private double b = 0.0D;
  private float c = 10.0F;
  private int d = -16777216;
  private int e = 0;
  private float f = 0.0F;
  private boolean g = true;
  private String h;
  private b i;
  
  public w(b paramb)
  {
    i = paramb;
    try
    {
      h = c();
      return;
    }
    catch (RemoteException paramb)
    {
      cy.a(paramb, "CircleDelegateImp", "CircleDelegateIme");
    }
  }
  
  public void a(double paramDouble)
  {
    b = paramDouble;
  }
  
  public void a(float paramFloat)
  {
    f = paramFloat;
    i.invalidate();
  }
  
  public void a(int paramInt)
  {
    d = paramInt;
  }
  
  public void a(Canvas paramCanvas)
  {
    if ((g() == null) || (b <= 0.0D) || (!e())) {
      return;
    }
    float f1 = i.a().a.a((float)h());
    Object localObject = new ae((int)(a.latitude * 1000000.0D), (int)(a.longitude * 1000000.0D));
    Point localPoint = new Point();
    i.s().a((ae)localObject, localPoint);
    localObject = new Paint();
    ((Paint)localObject).setColor(k());
    ((Paint)localObject).setAntiAlias(true);
    ((Paint)localObject).setStyle(Paint.Style.FILL);
    paramCanvas.drawCircle(x, y, f1, (Paint)localObject);
    ((Paint)localObject).setColor(j());
    ((Paint)localObject).setStyle(Paint.Style.STROKE);
    ((Paint)localObject).setStrokeWidth(i());
    paramCanvas.drawCircle(x, y, f1, (Paint)localObject);
  }
  
  public void a(LatLng paramLatLng)
  {
    a = paramLatLng;
  }
  
  public void a(boolean paramBoolean)
  {
    g = paramBoolean;
    i.postInvalidate();
  }
  
  public boolean a()
  {
    return true;
  }
  
  public boolean a(am paramam)
  {
    return (equals(paramam)) || (paramam.c().equals(c()));
  }
  
  public void b()
  {
    i.a(c());
    i.postInvalidate();
  }
  
  public void b(float paramFloat)
  {
    c = paramFloat;
  }
  
  public void b(int paramInt)
  {
    e = paramInt;
  }
  
  public boolean b(LatLng paramLatLng)
  {
    return b >= AMapUtils.calculateLineDistance(a, paramLatLng);
  }
  
  public String c()
  {
    if (h == null) {
      h = ac.a("Circle");
    }
    return h;
  }
  
  public float d()
  {
    return f;
  }
  
  public boolean e()
  {
    return g;
  }
  
  public int f()
  {
    return 0;
  }
  
  public LatLng g()
  {
    return a;
  }
  
  public double h()
  {
    return b;
  }
  
  public float i()
  {
    return c;
  }
  
  public int j()
  {
    return d;
  }
  
  public int k()
  {
    return e;
  }
  
  public void l()
  {
    a = null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.w
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */