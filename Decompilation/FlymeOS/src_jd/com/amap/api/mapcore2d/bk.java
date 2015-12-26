package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

class bk
  implements ak
{
  private static int a = 0;
  private int b = 0;
  private float c = 0.0F;
  private CopyOnWriteArrayList<BitmapDescriptor> d = null;
  private int e = 20;
  private String f;
  private LatLng g;
  private LatLng h;
  private String i;
  private String j;
  private float k = 0.5F;
  private float l = 1.0F;
  private boolean m = false;
  private boolean n = true;
  private be o;
  private Object p;
  private boolean q = false;
  private a r;
  private boolean s = false;
  private int t;
  private int u;
  private float v;
  private int w;
  
  public bk(MarkerOptions paramMarkerOptions, be parambe)
  {
    o = parambe;
    q = paramMarkerOptions.isGps();
    v = paramMarkerOptions.getZIndex();
    if ((paramMarkerOptions.getPosition() == null) || (q)) {}
    try
    {
      parambe = fd.a(getPositionlongitude, getPositionlatitude);
      h = new LatLng(parambe[1], parambe[0]);
      g = paramMarkerOptions.getPosition();
      k = paramMarkerOptions.getAnchorU();
      l = paramMarkerOptions.getAnchorV();
      n = paramMarkerOptions.isVisible();
      j = paramMarkerOptions.getSnippet();
      i = paramMarkerOptions.getTitle();
      m = paramMarkerOptions.isDraggable();
      e = paramMarkerOptions.getPeriod();
      f = d();
      b(paramMarkerOptions.getIcons());
      if ((d != null) && (d.size() == 0)) {
        b(paramMarkerOptions.getIcon());
      }
      return;
    }
    catch (Exception parambe)
    {
      for (;;)
      {
        cy.a(parambe, "MarkerDelegateImp", "MarkerDelegateImp");
        h = paramMarkerOptions.getPosition();
      }
    }
  }
  
  private an b(float paramFloat1, float paramFloat2)
  {
    float f1 = (float)(3.141592653589793D * c / 180.0D);
    an localan = new an();
    a = ((int)(paramFloat1 * Math.cos(f1) + paramFloat2 * Math.sin(f1)));
    b = ((int)(paramFloat2 * Math.cos(f1) - paramFloat1 * Math.sin(f1)));
    return localan;
  }
  
  private void b(BitmapDescriptor paramBitmapDescriptor)
  {
    if (paramBitmapDescriptor != null)
    {
      w();
      d.add(paramBitmapDescriptor.clone());
    }
  }
  
  private static String c(String paramString)
  {
    a += 1;
    return paramString + a;
  }
  
  public BitmapDescriptor A()
  {
    if ((d == null) || (d.size() == 0))
    {
      w();
      d.add(BitmapDescriptorFactory.defaultMarker());
    }
    while (d.get(0) != null) {
      return (BitmapDescriptor)d.get(0);
    }
    d.clear();
    return A();
  }
  
  public float B()
  {
    return k;
  }
  
  public float C()
  {
    return l;
  }
  
  public void a(float paramFloat)
  {
    c = ((-paramFloat % 360.0F + 360.0F) % 360.0F);
    if (k())
    {
      o.e(this);
      o.d(this);
    }
  }
  
  public void a(float paramFloat1, float paramFloat2)
  {
    if ((k == paramFloat1) && (l == paramFloat2)) {}
    do
    {
      return;
      k = paramFloat1;
      l = paramFloat2;
    } while (!k());
    o.e(this);
    o.d(this);
  }
  
  public void a(int paramInt)
  {
    if (paramInt <= 1)
    {
      e = 1;
      return;
    }
    e = paramInt;
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    t = paramInt1;
    u = paramInt2;
    s = true;
    if (k()) {
      i();
    }
  }
  
  public void a(Canvas paramCanvas, ag paramag)
  {
    if ((!n) || (t() == null) || (A() == null)) {}
    label162:
    label188:
    for (;;)
    {
      return;
      an localan;
      if (q())
      {
        localan = new an(t, u);
        paramag = p();
        if (paramag == null) {
          continue;
        }
        if (paramag.size() <= 1) {
          break label162;
        }
        paramag = ((BitmapDescriptor)paramag.get(b)).getBitmap();
      }
      for (;;)
      {
        if ((paramag == null) || (paramag.isRecycled())) {
          break label188;
        }
        paramCanvas.save();
        paramCanvas.rotate(c, a, b);
        paramCanvas.drawBitmap(paramag, a - B() * paramag.getWidth(), b - C() * paramag.getHeight(), null);
        paramCanvas.restore();
        return;
        localan = z();
        break;
        if (paramag.size() == 1) {
          paramag = ((BitmapDescriptor)paramag.get(0)).getBitmap();
        } else {
          paramag = null;
        }
      }
    }
  }
  
  public void a(BitmapDescriptor paramBitmapDescriptor)
  {
    if ((paramBitmapDescriptor == null) || (d == null)) {}
    do
    {
      return;
      d.clear();
      d.add(paramBitmapDescriptor);
    } while (!k());
    o.e(this);
    o.d(this);
  }
  
  public void a(LatLng paramLatLng)
  {
    if (q)
    {
      h = paramLatLng;
      return;
    }
    g = paramLatLng;
  }
  
  public void a(Object paramObject)
  {
    p = paramObject;
  }
  
  public void a(String paramString)
  {
    i = paramString;
  }
  
  public void a(ArrayList<BitmapDescriptor> paramArrayList)
  {
    if (paramArrayList == null) {}
    do
    {
      return;
      b(paramArrayList);
      if (r == null)
      {
        r = new a(null);
        r.start();
      }
    } while (!k());
    o.e(this);
    o.d(this);
  }
  
  public void a(boolean paramBoolean)
  {
    m = paramBoolean;
  }
  
  public boolean a()
  {
    return o.b(this);
  }
  
  public boolean a(ak paramak)
  {
    return (equals(paramak)) || (paramak.d().equals(d()));
  }
  
  public Rect b()
  {
    an localan1 = z();
    if (localan1 == null) {
      return new Rect(0, 0, 0, 0);
    }
    int i1 = n();
    int i2 = y();
    Rect localRect = new Rect();
    if (c == 0.0F)
    {
      top = ((int)(b - i2 * l));
      left = ((int)(a - k * i1));
      f1 = b;
      bottom = ((int)(i2 * (1.0F - l) + f1));
      f1 = a;
      f2 = k;
      right = ((int)(f1 + i1 * (1.0F - f2)));
      return localRect;
    }
    an localan2 = b(-k * i1, (l - 1.0F) * i2);
    an localan3 = b(-k * i1, l * i2);
    an localan4 = b((1.0F - k) * i1, l * i2);
    float f1 = k;
    float f2 = i1;
    float f3 = l;
    an localan5 = b(f2 * (1.0F - f1), i2 * (f3 - 1.0F));
    top = (b - Math.max(b, Math.max(b, Math.max(b, b))));
    left = (a + Math.min(a, Math.min(a, Math.min(a, a))));
    bottom = (b - Math.min(b, Math.min(b, Math.min(b, b))));
    right = (a + Math.max(a, Math.max(a, Math.max(a, a))));
    return localRect;
  }
  
  public void b(float paramFloat)
  {
    v = paramFloat;
    o.d();
  }
  
  public void b(int paramInt)
  {
    w = paramInt;
  }
  
  public void b(LatLng paramLatLng)
  {
    if (q) {}
    try
    {
      double[] arrayOfDouble = fd.a(longitude, latitude);
      h = new LatLng(arrayOfDouble[1], arrayOfDouble[0]);
      s = false;
      g = paramLatLng;
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        cy.a(localException, "MarkerDelegateImp", "setPosition");
        h = paramLatLng;
      }
    }
  }
  
  public void b(String paramString)
  {
    j = paramString;
  }
  
  public void b(ArrayList<BitmapDescriptor> paramArrayList)
  {
    w();
    if (paramArrayList != null)
    {
      Iterator localIterator = paramArrayList.iterator();
      while (localIterator.hasNext())
      {
        BitmapDescriptor localBitmapDescriptor = (BitmapDescriptor)localIterator.next();
        if (localBitmapDescriptor != null) {
          d.add(localBitmapDescriptor.clone());
        }
      }
      if ((paramArrayList.size() > 1) && (r == null))
      {
        r = new a(null);
        r.start();
      }
    }
  }
  
  public void b(boolean paramBoolean)
  {
    n = paramBoolean;
    if ((!paramBoolean) && (k())) {
      o.e(this);
    }
  }
  
  public LatLng c()
  {
    if (s)
    {
      aa localaa = new aa();
      o.a.a(t, u, localaa);
      return new LatLng(b, a);
    }
    if (q) {
      return h;
    }
    return g;
  }
  
  public String d()
  {
    if (f == null) {
      f = c("Marker");
    }
    return f;
  }
  
  public ab e()
  {
    ab localab = new ab();
    if ((d != null) && (d.size() != 0))
    {
      a = (n() * k);
      b = (y() * l);
    }
    return localab;
  }
  
  public String f()
  {
    return i;
  }
  
  public String g()
  {
    return j;
  }
  
  public boolean h()
  {
    return m;
  }
  
  public void i()
  {
    if (!s()) {
      return;
    }
    o.d(this);
  }
  
  public void j()
  {
    if (k()) {
      o.e(this);
    }
  }
  
  public boolean k()
  {
    return o.f(this);
  }
  
  public void l()
  {
    try
    {
      Iterator localIterator = d.iterator();
      while (localIterator.hasNext())
      {
        Bitmap localBitmap = ((BitmapDescriptor)localIterator.next()).getBitmap();
        if (localBitmap != null)
        {
          localBitmap.recycle();
          continue;
          r = null;
        }
      }
    }
    catch (Exception localException)
    {
      cy.a(localException, "MarkerDelegateImp", "destroy");
      Log.d("destroy erro", "MarkerDelegateImp destroy");
    }
    for (;;)
    {
      return;
      d = null;
      g = null;
      p = null;
    }
  }
  
  public int m()
  {
    return super.hashCode();
  }
  
  public int n()
  {
    return A().getWidth();
  }
  
  public int o()
  {
    return e;
  }
  
  public ArrayList<BitmapDescriptor> p()
  {
    if ((d != null) && (d.size() > 0))
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = d.iterator();
      while (localIterator.hasNext()) {
        localArrayList.add((BitmapDescriptor)localIterator.next());
      }
      return localArrayList;
    }
    return null;
  }
  
  public boolean q()
  {
    return s;
  }
  
  public float r()
  {
    return v;
  }
  
  public boolean s()
  {
    return n;
  }
  
  public LatLng t()
  {
    if (s)
    {
      aa localaa = new aa();
      o.a.a(t, u, localaa);
      return new LatLng(b, a);
    }
    return g;
  }
  
  public Object u()
  {
    return p;
  }
  
  public int v()
  {
    return w;
  }
  
  void w()
  {
    if (d == null)
    {
      d = new CopyOnWriteArrayList();
      return;
    }
    d.clear();
  }
  
  public an x()
  {
    if (t() == null) {
      return null;
    }
    an localan = new an();
    if (q) {}
    for (ae localae = new ae((int)(clatitude * 1000000.0D), (int)(clongitude * 1000000.0D));; localae = new ae((int)(tlatitude * 1000000.0D), (int)(tlongitude * 1000000.0D)))
    {
      Point localPoint = new Point();
      o.a().s().a(localae, localPoint);
      a = x;
      b = y;
      return localan;
    }
  }
  
  public int y()
  {
    return A().getHeight();
  }
  
  public an z()
  {
    an localan2 = x();
    an localan1 = localan2;
    if (localan2 == null) {
      localan1 = null;
    }
    return localan1;
  }
  
  class a
    extends Thread
  {
    private a() {}
    
    public void run()
    {
      setName("MarkerThread");
      for (;;)
      {
        if ((!Thread.currentThread().isInterrupted()) && (bk.a(bk.this) != null) && (bk.a(bk.this).size() > 1))
        {
          if (bk.b(bk.this) == bk.a(bk.this).size() - 1)
          {
            bk.a(bk.this, 0);
            bk.d(bk.this).a().postInvalidate();
          }
          try
          {
            Thread.sleep(bk.e(bk.this) * 250);
            if (bk.a(bk.this) == null)
            {
              Thread.currentThread().interrupt();
              continue;
              bk.c(bk.this);
            }
          }
          catch (InterruptedException localInterruptedException)
          {
            for (;;)
            {
              cy.a(localInterruptedException, "MarkerDelegateImp", "run");
            }
          }
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */