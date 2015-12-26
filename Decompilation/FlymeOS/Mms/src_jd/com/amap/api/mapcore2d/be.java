package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.amap.api.maps2d.model.LatLng;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

class be
  extends View
{
  b a;
  a b = new a();
  private ArrayList<as> c = new ArrayList(8);
  private ArrayList<ak> d = new ArrayList(8);
  private volatile int e = 0;
  private Handler f = new Handler();
  private Runnable g = new bf(this);
  private an h;
  private ak i;
  private ak j = null;
  private float k = 0.0F;
  private CopyOnWriteArrayList<Integer> l = new CopyOnWriteArrayList();
  
  public be(Context paramContext, AttributeSet paramAttributeSet, b paramb)
  {
    super(paramContext, paramAttributeSet);
    a = paramb;
  }
  
  private as a(Iterator<as> paramIterator, Rect paramRect, an paraman)
  {
    while (paramIterator.hasNext())
    {
      as localas = (as)paramIterator.next();
      LatLng localLatLng = localas.t();
      if (localLatLng != null)
      {
        a.b(latitude, longitude, paraman);
        if (a(paramRect, a, b)) {
          return localas;
        }
      }
    }
    return null;
  }
  
  private ak b(Iterator<ak> paramIterator, Rect paramRect, an paraman)
  {
    while (paramIterator.hasNext())
    {
      ak localak = (ak)paramIterator.next();
      LatLng localLatLng = localak.c();
      if (localLatLng != null)
      {
        a.b(latitude, longitude, paraman);
        if (a(paramRect, a, b)) {
          return localak;
        }
      }
    }
    return null;
  }
  
  private int h()
  {
    int m = e;
    e = (m + 1);
    return m;
  }
  
  private void i()
  {
    Iterator localIterator = d.iterator();
    for (;;)
    {
      ak localak;
      if (localIterator.hasNext())
      {
        localak = (ak)localIterator.next();
        if ((i == null) || (!i.d().equals(localak.d()))) {}
      }
      else
      {
        try
        {
          boolean bool = i.q();
          if (bool) {
            return;
          }
        }
        catch (RemoteException localRemoteException)
        {
          cy.a(localRemoteException, "MapOverlayImageView", "redrawInfoWindow");
          Rect localRect = localak.b();
          int m = left;
          h = new an(localak.n() / 2 + m, top);
          a.u();
        }
      }
    }
  }
  
  /* Error */
  public ak a(MotionEvent paramMotionEvent)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 45	com/amap/api/mapcore2d/be:d	Ljava/util/ArrayList;
    //   6: invokevirtual 181	java/util/ArrayList:size	()I
    //   9: iconst_1
    //   10: isub
    //   11: istore_2
    //   12: iload_2
    //   13: iflt +56 -> 69
    //   16: aload_0
    //   17: getfield 45	com/amap/api/mapcore2d/be:d	Ljava/util/ArrayList;
    //   20: iload_2
    //   21: invokevirtual 185	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   24: checkcast 122	com/amap/api/mapcore2d/ak
    //   27: astore 4
    //   29: aload_0
    //   30: aload 4
    //   32: invokeinterface 158 1 0
    //   37: aload_1
    //   38: invokevirtual 191	android/view/MotionEvent:getX	()F
    //   41: f2i
    //   42: aload_1
    //   43: invokevirtual 194	android/view/MotionEvent:getY	()F
    //   46: f2i
    //   47: invokevirtual 116	com/amap/api/mapcore2d/be:a	(Landroid/graphics/Rect;II)Z
    //   50: istore_3
    //   51: iload_3
    //   52: ifeq +10 -> 62
    //   55: aload 4
    //   57: astore_1
    //   58: aload_0
    //   59: monitorexit
    //   60: aload_1
    //   61: areturn
    //   62: iload_2
    //   63: iconst_1
    //   64: isub
    //   65: istore_2
    //   66: goto -54 -> 12
    //   69: aconst_null
    //   70: astore_1
    //   71: goto -13 -> 58
    //   74: astore_1
    //   75: aload_0
    //   76: monitorexit
    //   77: aload_1
    //   78: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	79	0	this	be
    //   0	79	1	paramMotionEvent	MotionEvent
    //   11	55	2	m	int
    //   50	2	3	bool	boolean
    //   27	29	4	localak	ak
    // Exception table:
    //   from	to	target	type
    //   2	12	74	finally
    //   16	51	74	finally
  }
  
  /* Error */
  public ak a(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 45	com/amap/api/mapcore2d/be:d	Ljava/util/ArrayList;
    //   6: invokevirtual 132	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   9: astore 4
    //   11: aload 4
    //   13: invokeinterface 83 1 0
    //   18: ifeq +39 -> 57
    //   21: aload 4
    //   23: invokeinterface 87 1 0
    //   28: checkcast 122	com/amap/api/mapcore2d/ak
    //   31: astore_3
    //   32: aload_3
    //   33: ifnull -22 -> 11
    //   36: aload_3
    //   37: invokeinterface 137 1 0
    //   42: aload_1
    //   43: invokevirtual 143	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   46: istore_2
    //   47: iload_2
    //   48: ifeq -37 -> 11
    //   51: aload_3
    //   52: astore_1
    //   53: aload_0
    //   54: monitorexit
    //   55: aload_1
    //   56: areturn
    //   57: aconst_null
    //   58: astore_1
    //   59: goto -6 -> 53
    //   62: astore_1
    //   63: aload_0
    //   64: monitorexit
    //   65: aload_1
    //   66: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	67	0	this	be
    //   0	67	1	paramString	String
    //   46	2	2	bool	boolean
    //   31	21	3	localak	ak
    //   9	13	4	localIterator	Iterator
    // Exception table:
    //   from	to	target	type
    //   2	11	62	finally
    //   11	32	62	finally
    //   36	47	62	finally
  }
  
  public b a()
  {
    return a;
  }
  
  public void a(Canvas paramCanvas)
  {
    for (;;)
    {
      Rect localRect;
      an localan;
      Iterator localIterator1;
      Iterator localIterator2;
      ak localak;
      as localas;
      try
      {
        i();
        localRect = new Rect(0, 0, a.c(), a.d());
        localan = new an();
        localIterator1 = d.iterator();
        localIterator2 = c.iterator();
        localak = b(localIterator1, localRect, localan);
        localas = a(localIterator2, localRect, localan);
      }
      finally {}
      if (localak == null)
      {
        localas.a(paramCanvas);
        localas = a(localIterator2, localRect, localan);
      }
      else if (localas == null)
      {
        localak.a(paramCanvas, a);
        localak = b(localIterator1, localRect, localan);
      }
      else if ((localak.r() < localas.r()) || ((localak.r() == localas.r()) && (localak.v() < localas.v())))
      {
        localak.a(paramCanvas, a);
        localak = b(localIterator1, localRect, localan);
      }
      else
      {
        localas.a(paramCanvas);
        localas = a(localIterator2, localRect, localan);
        break label238;
        return;
      }
      label238:
      if (localak == null) {
        if (localas == null) {}
      }
    }
  }
  
  public void a(ak paramak)
  {
    try
    {
      e(paramak);
      paramak.b(h());
      d.remove(paramak);
      d.add(paramak);
      d();
      return;
    }
    finally
    {
      paramak = finally;
      throw paramak;
    }
  }
  
  public void a(as paramas)
  {
    try
    {
      c.remove(paramas);
      paramas.b(h());
      c.add(paramas);
      d();
      return;
    }
    finally
    {
      paramas = finally;
      throw paramas;
    }
  }
  
  public boolean a(Rect paramRect, int paramInt1, int paramInt2)
  {
    return paramRect.contains(paramInt1, paramInt2);
  }
  
  protected int b()
  {
    return d.size();
  }
  
  public void b(as paramas)
  {
    try
    {
      c.remove(paramas);
      return;
    }
    finally
    {
      paramas = finally;
      throw paramas;
    }
  }
  
  public boolean b(MotionEvent paramMotionEvent)
  {
    for (;;)
    {
      try
      {
        int m = d.size() - 1;
        boolean bool;
        if (m >= 0)
        {
          ak localak = (ak)d.get(m);
          Rect localRect = localak.b();
          bool = a(localRect, (int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
          if (bool)
          {
            h = new an(left + localak.n() / 2, top);
            i = localak;
            return bool;
          }
          m -= 1;
        }
        else
        {
          bool = false;
        }
      }
      finally {}
    }
  }
  
  public boolean b(ak paramak)
  {
    try
    {
      e(paramak);
      boolean bool = d.remove(paramak);
      return bool;
    }
    finally
    {
      paramak = finally;
      throw paramak;
    }
  }
  
  /* Error */
  public void c()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 45	com/amap/api/mapcore2d/be:d	Ljava/util/ArrayList;
    //   6: ifnull +56 -> 62
    //   9: aload_0
    //   10: getfield 45	com/amap/api/mapcore2d/be:d	Ljava/util/ArrayList;
    //   13: invokevirtual 132	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   16: astore_1
    //   17: aload_1
    //   18: invokeinterface 83 1 0
    //   23: ifeq +32 -> 55
    //   26: aload_1
    //   27: invokeinterface 87 1 0
    //   32: checkcast 122	com/amap/api/mapcore2d/ak
    //   35: invokeinterface 251 1 0
    //   40: goto -23 -> 17
    //   43: astore_1
    //   44: aload_1
    //   45: ldc -108
    //   47: ldc -3
    //   49: invokestatic 155	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   52: aload_0
    //   53: monitorexit
    //   54: return
    //   55: aload_0
    //   56: getfield 45	com/amap/api/mapcore2d/be:d	Ljava/util/ArrayList;
    //   59: invokevirtual 255	java/util/ArrayList:clear	()V
    //   62: aload_0
    //   63: getfield 43	com/amap/api/mapcore2d/be:c	Ljava/util/ArrayList;
    //   66: ifnull -14 -> 52
    //   69: aload_0
    //   70: getfield 43	com/amap/api/mapcore2d/be:c	Ljava/util/ArrayList;
    //   73: invokevirtual 255	java/util/ArrayList:clear	()V
    //   76: goto -24 -> 52
    //   79: astore_1
    //   80: aload_0
    //   81: monitorexit
    //   82: aload_1
    //   83: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	84	0	this	be
    //   16	11	1	localIterator	Iterator
    //   43	2	1	localThrowable	Throwable
    //   79	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	17	43	java/lang/Throwable
    //   17	40	43	java/lang/Throwable
    //   55	62	43	java/lang/Throwable
    //   62	76	43	java/lang/Throwable
    //   2	17	79	finally
    //   17	40	79	finally
    //   44	52	79	finally
    //   55	62	79	finally
    //   62	76	79	finally
  }
  
  /* Error */
  public void c(ak paramak)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 66	com/amap/api/mapcore2d/be:j	Lcom/amap/api/mapcore2d/ak;
    //   6: astore_2
    //   7: aload_2
    //   8: aload_1
    //   9: if_acmpne +6 -> 15
    //   12: aload_0
    //   13: monitorexit
    //   14: return
    //   15: aload_0
    //   16: getfield 66	com/amap/api/mapcore2d/be:j	Lcom/amap/api/mapcore2d/ak;
    //   19: ifnull +32 -> 51
    //   22: aload_0
    //   23: getfield 66	com/amap/api/mapcore2d/be:j	Lcom/amap/api/mapcore2d/ak;
    //   26: invokeinterface 219 1 0
    //   31: ldc_w 256
    //   34: fcmpl
    //   35: ifne +16 -> 51
    //   38: aload_0
    //   39: getfield 66	com/amap/api/mapcore2d/be:j	Lcom/amap/api/mapcore2d/ak;
    //   42: aload_0
    //   43: getfield 68	com/amap/api/mapcore2d/be:k	F
    //   46: invokeinterface 259 2 0
    //   51: aload_0
    //   52: aload_1
    //   53: invokeinterface 219 1 0
    //   58: putfield 68	com/amap/api/mapcore2d/be:k	F
    //   61: aload_0
    //   62: aload_1
    //   63: putfield 66	com/amap/api/mapcore2d/be:j	Lcom/amap/api/mapcore2d/ak;
    //   66: aload_1
    //   67: ldc_w 256
    //   70: invokeinterface 259 2 0
    //   75: aload_0
    //   76: invokevirtual 239	com/amap/api/mapcore2d/be:d	()V
    //   79: goto -67 -> 12
    //   82: astore_1
    //   83: aload_0
    //   84: monitorexit
    //   85: aload_1
    //   86: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	87	0	this	be
    //   0	87	1	paramak	ak
    //   6	2	2	localak	ak
    // Exception table:
    //   from	to	target	type
    //   2	7	82	finally
    //   15	51	82	finally
    //   51	79	82	finally
  }
  
  void d()
  {
    f.removeCallbacks(g);
    f.postDelayed(g, 5L);
  }
  
  public void d(ak paramak)
  {
    if (h == null) {
      h = new an();
    }
    Rect localRect = paramak.b();
    h = new an(left + paramak.n() / 2, top);
    i = paramak;
    try
    {
      a.a(e());
      return;
    }
    catch (RemoteException paramak)
    {
      cy.a(paramak, "MapOverlayImageView", "showInfoWindow");
    }
  }
  
  public ak e()
  {
    return i;
  }
  
  public void e(ak paramak)
  {
    if (f(paramak)) {
      a.t();
    }
  }
  
  public void f()
  {
    try
    {
      if (f != null) {
        f.removeCallbacksAndMessages(null);
      }
      c();
      return;
    }
    catch (Exception localException)
    {
      cy.a(localException, "MapOverlayImageView", "destory");
      Log.d("amapApi", "MapOverlayImageView clear erro" + localException.getMessage());
    }
  }
  
  public boolean f(ak paramak)
  {
    return a.b(paramak);
  }
  
  /* Error */
  public java.util.List<com.amap.api.maps2d.model.Marker> g()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 38	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 316	java/util/ArrayList:<init>	()V
    //   9: astore_1
    //   10: new 160	android/graphics/Rect
    //   13: dup
    //   14: iconst_0
    //   15: iconst_0
    //   16: aload_0
    //   17: getfield 75	com/amap/api/mapcore2d/be:a	Lcom/amap/api/mapcore2d/b;
    //   20: invokevirtual 201	com/amap/api/mapcore2d/b:c	()I
    //   23: aload_0
    //   24: getfield 75	com/amap/api/mapcore2d/be:a	Lcom/amap/api/mapcore2d/b;
    //   27: invokevirtual 203	com/amap/api/mapcore2d/b:d	()I
    //   30: invokespecial 206	android/graphics/Rect:<init>	(IIII)V
    //   33: astore_2
    //   34: new 109	com/amap/api/mapcore2d/an
    //   37: dup
    //   38: invokespecial 207	com/amap/api/mapcore2d/an:<init>	()V
    //   41: astore_3
    //   42: aload_0
    //   43: getfield 45	com/amap/api/mapcore2d/be:d	Ljava/util/ArrayList;
    //   46: invokevirtual 132	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   49: astore 4
    //   51: aload 4
    //   53: invokeinterface 83 1 0
    //   58: ifeq +29 -> 87
    //   61: aload 4
    //   63: invokeinterface 87 1 0
    //   68: checkcast 122	com/amap/api/mapcore2d/ak
    //   71: astore 5
    //   73: aload 5
    //   75: invokeinterface 124 1 0
    //   80: astore 6
    //   82: aload 6
    //   84: ifnonnull +7 -> 91
    //   87: aload_0
    //   88: monitorexit
    //   89: aload_1
    //   90: areturn
    //   91: aload_0
    //   92: getfield 75	com/amap/api/mapcore2d/be:a	Lcom/amap/api/mapcore2d/b;
    //   95: aload 6
    //   97: getfield 99	com/amap/api/maps2d/model/LatLng:latitude	D
    //   100: aload 6
    //   102: getfield 102	com/amap/api/maps2d/model/LatLng:longitude	D
    //   105: aload_3
    //   106: invokevirtual 107	com/amap/api/mapcore2d/b:b	(DDLcom/amap/api/mapcore2d/an;)V
    //   109: aload_0
    //   110: aload_2
    //   111: aload_3
    //   112: getfield 111	com/amap/api/mapcore2d/an:a	I
    //   115: aload_3
    //   116: getfield 113	com/amap/api/mapcore2d/an:b	I
    //   119: invokevirtual 116	com/amap/api/mapcore2d/be:a	(Landroid/graphics/Rect;II)Z
    //   122: ifeq -71 -> 51
    //   125: aload_1
    //   126: new 318	com/amap/api/maps2d/model/Marker
    //   129: dup
    //   130: aload 5
    //   132: invokespecial 320	com/amap/api/maps2d/model/Marker:<init>	(Lcom/amap/api/mapcore2d/ak;)V
    //   135: invokeinterface 323 2 0
    //   140: pop
    //   141: goto -90 -> 51
    //   144: astore_1
    //   145: aload_0
    //   146: monitorexit
    //   147: aload_1
    //   148: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	149	0	this	be
    //   9	117	1	localArrayList	ArrayList
    //   144	4	1	localObject	Object
    //   33	78	2	localRect	Rect
    //   41	75	3	localan	an
    //   49	13	4	localIterator	Iterator
    //   71	60	5	localak	ak
    //   80	21	6	localLatLng	LatLng
    // Exception table:
    //   from	to	target	type
    //   2	51	144	finally
    //   51	82	144	finally
    //   91	141	144	finally
  }
  
  static class a
    implements Serializable, Comparator<al>
  {
    public int a(al paramal1, al paramal2)
    {
      if ((paramal1 != null) && (paramal2 != null)) {
        try
        {
          if (paramal1.r() > paramal2.r()) {
            return 1;
          }
          float f1 = paramal1.r();
          float f2 = paramal2.r();
          if (f1 < f2) {
            return -1;
          }
        }
        catch (Throwable paramal1)
        {
          cy.a(paramal1, "MapOverlayImageView", "compare");
        }
      }
      return 0;
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.be
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */