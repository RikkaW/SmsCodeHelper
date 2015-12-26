package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;

class af
  implements ai
{
  private final double a = 0.01745329251994329D;
  private final double b = 6371000.79D;
  private b c;
  private BitmapDescriptor d;
  private LatLng e;
  private float f;
  private float g;
  private LatLngBounds h;
  private float i;
  private float j;
  private boolean k = true;
  private float l = 0.0F;
  private float m = 0.5F;
  private float n = 0.5F;
  private String o;
  private Bitmap p;
  
  af(b paramb)
  {
    c = paramb;
    try
    {
      o = c();
      return;
    }
    catch (RemoteException paramb)
    {
      cy.a(paramb, "GroundOverlayDelegateImp", "GroundOverlayDelegateImp");
    }
  }
  
  private ae b(LatLng paramLatLng)
  {
    if (paramLatLng == null) {
      return null;
    }
    return new ae((int)(latitude * 1000000.0D), (int)(longitude * 1000000.0D));
  }
  
  private void o()
  {
    double d1 = f / (6371000.79D * Math.cos(e.latitude * 0.01745329251994329D) * 0.01745329251994329D);
    double d2 = g / 111194.94043265979D;
    LatLng localLatLng = new LatLng(e.latitude - (1.0F - n) * d2, e.longitude - m * d1);
    double d3 = e.latitude;
    double d4 = n;
    double d5 = e.longitude;
    h = new LatLngBounds(localLatLng, new LatLng(d2 * d4 + d3, d1 * (1.0F - m) + d5));
  }
  
  private void p()
  {
    LatLng localLatLng1 = h.southwest;
    LatLng localLatLng2 = h.northeast;
    e = new LatLng(latitude + (1.0F - n) * (latitude - latitude), longitude + m * (longitude - longitude));
    f = ((float)(6371000.79D * Math.cos(e.latitude * 0.01745329251994329D) * (longitude - longitude) * 0.01745329251994329D));
    g = ((float)((latitude - latitude) * 6371000.79D * 0.01745329251994329D));
  }
  
  public void a(float paramFloat)
  {
    j = paramFloat;
    c.invalidate();
  }
  
  public void a(float paramFloat1, float paramFloat2)
  {
    boolean bool2 = true;
    if (paramFloat1 >= 0.0F)
    {
      bool1 = true;
      cp.b(bool1, "Width must be non-negative");
      if (paramFloat2 < 0.0F) {
        break label66;
      }
    }
    label66:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      cp.b(bool1, "Height must be non-negative");
      if ((f == paramFloat1) || (g == paramFloat2)) {
        break label71;
      }
      f = paramFloat1;
      g = paramFloat2;
      return;
      bool1 = false;
      break;
    }
    label71:
    f = paramFloat1;
    g = paramFloat2;
  }
  
  public void a(Canvas paramCanvas)
  {
    if ((!k) || ((e == null) && (h == null)) || (d == null)) {}
    do
    {
      do
      {
        return;
        g();
      } while ((f == 0.0F) && (g == 0.0F));
      p = d.getBitmap();
    } while ((p == null) || (p.isRecycled()));
    Object localObject1 = h.southwest;
    Object localObject2 = h.northeast;
    localObject1 = b((LatLng)localObject1);
    ae localae = b((LatLng)localObject2);
    localObject2 = new Point();
    Point localPoint = new Point();
    c.s().a((ae)localObject1, (Point)localObject2);
    c.s().a(localae, localPoint);
    localObject1 = new Paint();
    localObject2 = new RectF(x, y, x, y);
    ((Paint)localObject1).setAlpha((int)(255.0F - l * 255.0F));
    ((Paint)localObject1).setFilterBitmap(true);
    paramCanvas.drawBitmap(p, null, (RectF)localObject2, (Paint)localObject1);
  }
  
  public void a(BitmapDescriptor paramBitmapDescriptor)
  {
    d = paramBitmapDescriptor;
  }
  
  public void a(LatLng paramLatLng)
  {
    if ((e != null) && (!e.equals(paramLatLng)))
    {
      e = paramLatLng;
      o();
      return;
    }
    e = paramLatLng;
  }
  
  public void a(LatLngBounds paramLatLngBounds)
  {
    if ((h != null) && (!h.equals(paramLatLngBounds)))
    {
      h = paramLatLngBounds;
      p();
      return;
    }
    h = paramLatLngBounds;
  }
  
  public void a(boolean paramBoolean)
  {
    k = paramBoolean;
    c.postInvalidate();
  }
  
  public boolean a()
  {
    if (h == null) {}
    LatLngBounds localLatLngBounds;
    do
    {
      return false;
      localLatLngBounds = c.x();
      if (localLatLngBounds == null) {
        return true;
      }
    } while ((!localLatLngBounds.contains(h)) && (!h.intersects(localLatLngBounds)));
    return true;
  }
  
  public boolean a(am paramam)
  {
    return (equals(paramam)) || (paramam.c().equals(c()));
  }
  
  public void b()
  {
    c.a(c());
  }
  
  public void b(float paramFloat)
  {
    if (paramFloat >= 0.0F) {}
    for (boolean bool = true;; bool = false)
    {
      cp.b(bool, "Width must be non-negative");
      if (f == paramFloat) {
        break;
      }
      f = paramFloat;
      g = paramFloat;
      return;
    }
    f = paramFloat;
    g = paramFloat;
  }
  
  public void b(float paramFloat1, float paramFloat2)
  {
    m = paramFloat1;
    n = paramFloat2;
  }
  
  public String c()
  {
    if (o == null) {
      o = ac.a("GroundOverlay");
    }
    return o;
  }
  
  public void c(float paramFloat)
  {
    paramFloat = (-paramFloat % 360.0F + 360.0F) % 360.0F;
    if (Double.doubleToLongBits(i) != Double.doubleToLongBits(paramFloat))
    {
      i = paramFloat;
      return;
    }
    i = paramFloat;
  }
  
  public float d()
  {
    return j;
  }
  
  public void d(float paramFloat)
  {
    if ((paramFloat >= 0.0F) && (paramFloat <= 1.0F)) {}
    for (boolean bool = true;; bool = false)
    {
      cp.b(bool, "Transparency must be in the range [0..1]");
      l = paramFloat;
      return;
    }
  }
  
  public boolean e()
  {
    return k;
  }
  
  public int f()
  {
    return super.hashCode();
  }
  
  public void g()
  {
    if (e == null) {
      p();
    }
    while (h != null) {
      return;
    }
    o();
  }
  
  public LatLng h()
  {
    return e;
  }
  
  public float i()
  {
    return f;
  }
  
  public float j()
  {
    return g;
  }
  
  public LatLngBounds k()
  {
    return h;
  }
  
  public void l()
  {
    try
    {
      b();
      if (d != null)
      {
        Bitmap localBitmap = d.getBitmap();
        if (localBitmap != null)
        {
          localBitmap.recycle();
          d = null;
        }
      }
      e = null;
      h = null;
      return;
    }
    catch (Exception localException)
    {
      cy.a(localException, "GroundOverlayDelegateImp", "destroy");
      Log.d("destroy erro", "GroundOverlayDelegateImp destroy");
    }
  }
  
  public float m()
  {
    return i;
  }
  
  public float n()
  {
    return l;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.af
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */