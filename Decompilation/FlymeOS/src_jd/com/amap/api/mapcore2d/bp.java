package com.amap.api.mapcore2d;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.os.RemoteException;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.LatLngBounds.Builder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class bp
  implements ao
{
  private b a;
  private float b = 0.0F;
  private boolean c = true;
  private String d;
  private float e;
  private int f;
  private int g;
  private List<an> h = new ArrayList();
  private LatLngBounds i = null;
  
  public bp(b paramb)
  {
    a = paramb;
    try
    {
      d = c();
      return;
    }
    catch (RemoteException paramb)
    {
      cy.a(paramb, "PolygonDelegateImp", "PolygonDelegateImp");
    }
  }
  
  public void a(float paramFloat)
  {
    b = paramFloat;
    a.invalidate();
  }
  
  public void a(int paramInt)
  {
    f = paramInt;
  }
  
  public void a(Canvas paramCanvas)
  {
    if ((h == null) || (h.size() == 0)) {
      return;
    }
    Path localPath = new Path();
    Object localObject = new ae(h.get(0)).b, h.get(0)).a);
    Point localPoint = new Point();
    localObject = a.s().a((ae)localObject, localPoint);
    localPath.moveTo(x, y);
    int j = 1;
    while (j < h.size())
    {
      localObject = new ae(h.get(j)).b, h.get(j)).a);
      localPoint = new Point();
      localObject = a.s().a((ae)localObject, localPoint);
      localPath.lineTo(x, y);
      j += 1;
    }
    localObject = new Paint();
    ((Paint)localObject).setColor(h());
    ((Paint)localObject).setAntiAlias(true);
    localPath.close();
    ((Paint)localObject).setStyle(Paint.Style.FILL);
    paramCanvas.drawPath(localPath, (Paint)localObject);
    ((Paint)localObject).setStyle(Paint.Style.STROKE);
    ((Paint)localObject).setColor(j());
    ((Paint)localObject).setStrokeWidth(g());
    paramCanvas.drawPath(localPath, (Paint)localObject);
  }
  
  public void a(List<LatLng> paramList)
  {
    b(paramList);
  }
  
  public void a(boolean paramBoolean)
  {
    c = paramBoolean;
  }
  
  public boolean a()
  {
    if (i == null) {}
    LatLngBounds localLatLngBounds;
    do
    {
      return false;
      localLatLngBounds = a.x();
      if (localLatLngBounds == null) {
        return true;
      }
    } while ((!i.contains(localLatLngBounds)) && (!i.intersects(localLatLngBounds)));
    return true;
  }
  
  public boolean a(am paramam)
  {
    return (equals(paramam)) || (paramam.c().equals(c()));
  }
  
  public boolean a(LatLng paramLatLng)
  {
    return cy.a(paramLatLng, i());
  }
  
  public void b()
  {
    a.a(c());
  }
  
  public void b(float paramFloat)
  {
    e = paramFloat;
  }
  
  public void b(int paramInt)
  {
    g = paramInt;
  }
  
  void b(List<LatLng> paramList)
  {
    LatLngBounds.Builder localBuilder = LatLngBounds.builder();
    h.clear();
    if (paramList != null)
    {
      Iterator localIterator = paramList.iterator();
      paramList = null;
      Object localObject;
      while (localIterator.hasNext())
      {
        localObject = (LatLng)localIterator.next();
        if (!((LatLng)localObject).equals(paramList))
        {
          paramList = new an();
          a.a(latitude, longitude, paramList);
          h.add(paramList);
          localBuilder.include((LatLng)localObject);
          paramList = (List<LatLng>)localObject;
        }
      }
      int j = h.size();
      if (j > 1)
      {
        paramList = (an)h.get(0);
        localObject = (an)h.get(j - 1);
        if ((a == a) && (b == b)) {
          h.remove(j - 1);
        }
      }
    }
    i = localBuilder.build();
  }
  
  public String c()
  {
    if (d == null) {
      d = ac.a("Polygon");
    }
    return d;
  }
  
  public float d()
  {
    return b;
  }
  
  public boolean e()
  {
    return c;
  }
  
  public int f()
  {
    return super.hashCode();
  }
  
  public float g()
  {
    return e;
  }
  
  public int h()
  {
    return f;
  }
  
  public List<LatLng> i()
  {
    return k();
  }
  
  public int j()
  {
    return g;
  }
  
  List<LatLng> k()
  {
    if (h != null)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = h.iterator();
      while (localIterator.hasNext())
      {
        an localan = (an)localIterator.next();
        if (localan != null)
        {
          aa localaa = new aa();
          a.b(a, b, localaa);
          localArrayList.add(new LatLng(b, a));
        }
      }
      return localArrayList;
    }
    return null;
  }
  
  public void l() {}
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */