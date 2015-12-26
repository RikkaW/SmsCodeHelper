package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import com.amap.api.maps2d.model.TileOverlayOptions;
import com.amap.api.maps2d.model.TileProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class cb
  implements at
{
  private static int f = 0;
  private cc a;
  private TileProvider b;
  private Float c;
  private boolean d;
  private ag e;
  private int g = y.i;
  private int h = y.i;
  private ct i;
  private CopyOnWriteArrayList<a> j = new CopyOnWriteArrayList();
  private boolean k = false;
  private b l = null;
  private String m = null;
  
  cb(TileOverlayOptions paramTileOverlayOptions, cc paramcc)
  {
    a = paramcc;
    b = paramTileOverlayOptions.getTileProvider();
    g = b.getTileWidth();
    h = b.getTileHeight();
    c = Float.valueOf(paramTileOverlayOptions.getZIndex());
    d = paramTileOverlayOptions.isVisible();
    m = c();
    e = a.a();
    paramcc = new cs.a(a.getContext(), m);
    paramcc.a(paramTileOverlayOptions.getMemoryCacheEnabled());
    paramcc.b(paramTileOverlayOptions.getDiskCacheEnabled());
    paramcc.a(paramTileOverlayOptions.getMemCacheSize());
    paramcc.b(paramTileOverlayOptions.getDiskCacheSize());
    paramTileOverlayOptions = paramTileOverlayOptions.getDiskCacheDir();
    if ((paramTileOverlayOptions != null) && (!paramTileOverlayOptions.equals(""))) {
      paramcc.a(paramTileOverlayOptions);
    }
    i = new ct(a.getContext(), g, h);
    i.a(b);
    i.a(paramcc);
    b(true);
  }
  
  private static String a(String paramString)
  {
    f += 1;
    return paramString + f;
  }
  
  private ArrayList<a> a(int paramInt1, int paramInt2, int paramInt3)
  {
    bh localbh = e.b();
    Object localObject1 = i;
    double d2 = h[g];
    int i4 = (int)((((ae)localObject1).e() - c) / (y.i * d2));
    double d3 = y.i * i4;
    double d4 = c;
    double d1 = 0.0D;
    int i1;
    if (b == 0)
    {
      i1 = (int)((d - ((ae)localObject1).f()) / (y.i * d2));
      d1 = d - y.i * i1 * d2;
    }
    for (;;)
    {
      localObject1 = localbh.a(new ae(d1, d4 + d3 * d2, false), (ae)localObject1, k, d2);
      Object localObject2 = new a(i4, i1, g, -1, null);
      a.a((a)localObject2, (PointF)localObject1);
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(localObject2);
      int i2 = 1;
      paramInt1 = 0;
      int i3 = i4 - i2;
      label212:
      int i5;
      int n;
      label262:
      a locala;
      if (i3 <= i4 + i2)
      {
        i5 = i1 + i2;
        localObject2 = localbh.a(i3, i5, i4, i1, (PointF)localObject1, paramInt2, paramInt3);
        n = paramInt1;
        if (localObject2 != null)
        {
          if (paramInt1 != 0) {
            break label642;
          }
          paramInt1 = 1;
          locala = new a(i3, i5, g, -1, null);
          a.a(locala, (PointF)localObject2);
          localArrayList.add(locala);
          n = paramInt1;
        }
        i5 = i1 - i2;
        localObject2 = localbh.a(i3, i5, i4, i1, (PointF)localObject1, paramInt2, paramInt3);
        if (localObject2 == null) {
          break label651;
        }
        if (n != 0) {
          break label645;
        }
        paramInt1 = 1;
        label340:
        locala = new a(i3, i5, g, -1, null);
        a.a(locala, (PointF)localObject2);
        localArrayList.add(locala);
      }
      for (;;)
      {
        i3 += 1;
        break label212;
        if (b != 1) {
          break label657;
        }
        i1 = (int)((((ae)localObject1).f() - d) / (y.i * d2));
        d1 = (i1 + 1) * y.i * d2;
        break;
        i3 = i1 + i2 - 1;
        label443:
        if (i3 > i1 - i2)
        {
          n = i4 + i2;
          localObject2 = localbh.a(n, i3, i4, i1, (PointF)localObject1, paramInt2, paramInt3);
          if (localObject2 == null) {
            break label639;
          }
          if (paramInt1 != 0) {
            break label636;
          }
          paramInt1 = 1;
          label490:
          locala = new a(n, i3, g, -1, null);
          a.a(locala, (PointF)localObject2);
          localArrayList.add(locala);
        }
        label636:
        label639:
        for (;;)
        {
          i5 = i4 - i2;
          localObject2 = localbh.a(i5, i3, i4, i1, (PointF)localObject1, paramInt2, paramInt3);
          n = paramInt1;
          if (localObject2 != null)
          {
            n = paramInt1;
            if (paramInt1 == 0) {
              n = 1;
            }
            locala = new a(i5, i3, g, -1, null);
            a.a(locala, (PointF)localObject2);
            localArrayList.add(locala);
          }
          i3 -= 1;
          paramInt1 = n;
          break label443;
          if (paramInt1 == 0) {
            return localArrayList;
          }
          i2 += 1;
          break;
          break label490;
        }
        label642:
        break label262;
        label645:
        paramInt1 = n;
        break label340;
        label651:
        paramInt1 = n;
      }
      label657:
      i1 = 0;
    }
  }
  
  private boolean a(List<a> paramList, int paramInt, boolean paramBoolean)
  {
    int i1 = 0;
    if (paramList == null) {
      return false;
    }
    if (j == null) {
      return false;
    }
    Object localObject = j.iterator();
    label215:
    for (;;)
    {
      a locala;
      if (((Iterator)localObject).hasNext())
      {
        locala = (a)((Iterator)localObject).next();
        Iterator localIterator = paramList.iterator();
        do
        {
          if (!localIterator.hasNext()) {
            break;
          }
        } while (!locala.equals((a)localIterator.next()));
      }
      for (int n = 1;; n = 0)
      {
        if (n != 0) {
          break label215;
        }
        locala.b();
        break;
        j.clear();
        if ((paramInt > (int)e.h()) || (paramInt < (int)e.i())) {
          return false;
        }
        n = paramList.size();
        paramInt = i1;
        if (n <= 0) {
          return false;
        }
        if (paramInt < n)
        {
          localObject = (a)paramList.get(paramInt);
          if (localObject == null) {}
          for (;;)
          {
            paramInt += 1;
            break;
            j.add(localObject);
            i.a(paramBoolean, (a)localObject);
          }
        }
        return true;
      }
    }
  }
  
  public void a()
  {
    if ((l != null) && (l.a() == cq.d.b)) {
      l.a(true);
    }
    Iterator localIterator = j.iterator();
    while (localIterator.hasNext()) {
      ((a)localIterator.next()).b();
    }
    j.clear();
    i.g();
    a.b(this);
  }
  
  public void a(float paramFloat)
  {
    c = Float.valueOf(paramFloat);
    a.c();
  }
  
  public void a(Canvas paramCanvas)
  {
    if ((j == null) || (j.size() == 0)) {}
    for (;;)
    {
      return;
      Iterator localIterator = j.iterator();
      while (localIterator.hasNext())
      {
        a locala = (a)localIterator.next();
        if (locala != null) {
          try
          {
            PointF localPointF = a.a(locala);
            if ((a.b(locala) != null) && (!a.b(locala).isRecycled()) && (localPointF != null) && (locala != null) && (localPointF != null)) {
              paramCanvas.drawBitmap(a.b(locala), x, y, null);
            }
          }
          catch (Exception localException)
          {
            cy.a(localException, "TileOverDelegateImp", "drawTiles");
            cw.a("TileOverlayDelegateImp", localException.toString(), 112);
          }
        }
      }
    }
  }
  
  public void a(boolean paramBoolean)
  {
    d = paramBoolean;
    if (paramBoolean) {
      b(true);
    }
  }
  
  public boolean a(at paramat)
  {
    return (equals(paramat)) || (paramat.c().equals(c()));
  }
  
  public void b()
  {
    i.f();
  }
  
  public void b(boolean paramBoolean)
  {
    if (k) {
      return;
    }
    if ((l != null) && (l.a() == cq.d.b)) {
      l.a(true);
    }
    l = new b(paramBoolean);
    l.c(new ag[] { e });
  }
  
  public String c()
  {
    if (m == null) {
      m = a("TileOverlay");
    }
    return m;
  }
  
  public float d()
  {
    return c.floatValue();
  }
  
  public boolean e()
  {
    return d;
  }
  
  public int f()
  {
    return super.hashCode();
  }
  
  public class a
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
    
    private a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      a = paramInt1;
      b = paramInt2;
      c = paramInt3;
      f = paramInt4;
    }
    
    private a(a parama)
    {
      a = a;
      b = b;
      c = c;
      f = f;
      g = g;
    }
    
    public a a()
    {
      return new a(cb.this, this);
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
            if (cb.b(cb.this) != null) {
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
            cb.a(cb.this).a(true, this);
            i += 1;
            cw.a("TileOverlayDelegateImp", "setBitmap Exception: " + this + "retry: " + i, 111);
            continue;
          }
          if (i < 3)
          {
            cb.a(cb.this).a(true, this);
            i += 1;
            cw.a("TileOverlayDelegateImp", "setBitmap failed: " + this + "retry: " + i, 111);
          }
        }
      } while (cb.b(cb.this).a() == null);
      bag.postInvalidate();
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
  
  class b
    extends cq<ag, Void, List<cb.a>>
  {
    private int c;
    private boolean d;
    
    public b(boolean paramBoolean)
    {
      d = paramBoolean;
    }
    
    protected List<cb.a> a(ag... paramVarArgs)
    {
      int j = 0;
      int i;
      try
      {
        int k = paramVarArgs[0].c();
        i = paramVarArgs[0].d();
        c = ((int)paramVarArgs[0].f());
        j = k;
      }
      catch (Exception paramVarArgs)
      {
        for (;;)
        {
          cy.a(paramVarArgs, "TileOverDelegateImp", "doInBackground");
          i = 0;
        }
      }
      if ((j <= 0) || (i <= 0)) {
        return null;
      }
      return cb.a(cb.this, c, j, i);
    }
    
    protected void a(List<cb.a> paramList)
    {
      if (paramList == null) {}
      while (paramList.size() <= 0) {
        return;
      }
      cb.a(cb.this, paramList, c, d);
      paramList.clear();
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */