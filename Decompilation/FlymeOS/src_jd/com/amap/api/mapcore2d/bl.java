package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class bl
{
  public e a;
  public d b;
  public b c;
  public a d;
  public c e;
  public ac f;
  public b g;
  public bh h = null;
  
  public bl(Context paramContext, b paramb)
  {
    g = paramb;
    b = new d(paramb, null);
    h = new bh(b);
    h.a();
    a(paramContext);
    e = new c(this, paramContext, null);
    d = new a(paramContext, null);
    a = new e();
    c = new b();
    f = new ac();
    b.a(false, false);
  }
  
  public void a()
  {
    d.a();
    a = null;
    b = null;
    c = null;
    d = null;
    e = null;
  }
  
  /* Error */
  public void a(Context paramContext)
  {
    // Byte code:
    //   0: new 94	android/util/DisplayMetrics
    //   3: dup
    //   4: invokespecial 95	android/util/DisplayMetrics:<init>	()V
    //   7: pop
    //   8: aload_1
    //   9: invokevirtual 101	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   12: invokevirtual 105	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   15: invokevirtual 111	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   18: astore 5
    //   20: aload 5
    //   22: invokevirtual 115	java/lang/Object:getClass	()Ljava/lang/Class;
    //   25: ldc 117
    //   27: invokevirtual 123	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   30: astore_1
    //   31: aload_1
    //   32: ifnull +149 -> 181
    //   35: aload 5
    //   37: getfield 127	android/util/DisplayMetrics:widthPixels	I
    //   40: aload 5
    //   42: getfield 130	android/util/DisplayMetrics:heightPixels	I
    //   45: imul
    //   46: i2l
    //   47: lstore_3
    //   48: aload_1
    //   49: aload 5
    //   51: invokevirtual 136	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
    //   54: istore_2
    //   55: iload_2
    //   56: bipush 120
    //   58: if_icmpgt +68 -> 126
    //   61: iconst_1
    //   62: putstatic 141	com/amap/api/mapcore2d/y:l	I
    //   65: return
    //   66: astore_1
    //   67: aload_1
    //   68: ldc -113
    //   70: ldc -111
    //   72: invokestatic 150	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   75: aconst_null
    //   76: astore_1
    //   77: goto -46 -> 31
    //   80: astore_1
    //   81: aload_1
    //   82: ldc -113
    //   84: ldc -111
    //   86: invokestatic 150	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   89: aconst_null
    //   90: astore_1
    //   91: goto -60 -> 31
    //   94: astore_1
    //   95: aload_1
    //   96: ldc -113
    //   98: ldc -111
    //   100: invokestatic 150	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   103: sipush 160
    //   106: istore_2
    //   107: goto -52 -> 55
    //   110: astore_1
    //   111: aload_1
    //   112: ldc -113
    //   114: ldc -111
    //   116: invokestatic 150	com/amap/api/mapcore2d/cy:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   119: sipush 160
    //   122: istore_2
    //   123: goto -68 -> 55
    //   126: iload_2
    //   127: sipush 160
    //   130: if_icmpgt +8 -> 138
    //   133: iconst_3
    //   134: putstatic 141	com/amap/api/mapcore2d/y:l	I
    //   137: return
    //   138: iload_2
    //   139: sipush 240
    //   142: if_icmpgt +8 -> 150
    //   145: iconst_2
    //   146: putstatic 141	com/amap/api/mapcore2d/y:l	I
    //   149: return
    //   150: lload_3
    //   151: ldc2_w 151
    //   154: lcmp
    //   155: ifle +8 -> 163
    //   158: iconst_2
    //   159: putstatic 141	com/amap/api/mapcore2d/y:l	I
    //   162: return
    //   163: lload_3
    //   164: ldc2_w 151
    //   167: lcmp
    //   168: ifge +8 -> 176
    //   171: iconst_1
    //   172: putstatic 141	com/amap/api/mapcore2d/y:l	I
    //   175: return
    //   176: iconst_3
    //   177: putstatic 141	com/amap/api/mapcore2d/y:l	I
    //   180: return
    //   181: aload 5
    //   183: getfield 127	android/util/DisplayMetrics:widthPixels	I
    //   186: aload 5
    //   188: getfield 130	android/util/DisplayMetrics:heightPixels	I
    //   191: imul
    //   192: i2l
    //   193: lstore_3
    //   194: lload_3
    //   195: ldc2_w 151
    //   198: lcmp
    //   199: ifle +8 -> 207
    //   202: iconst_2
    //   203: putstatic 141	com/amap/api/mapcore2d/y:l	I
    //   206: return
    //   207: lload_3
    //   208: ldc2_w 151
    //   211: lcmp
    //   212: ifge +8 -> 220
    //   215: iconst_1
    //   216: putstatic 141	com/amap/api/mapcore2d/y:l	I
    //   219: return
    //   220: iconst_3
    //   221: putstatic 141	com/amap/api/mapcore2d/y:l	I
    //   224: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	225	0	this	bl
    //   0	225	1	paramContext	Context
    //   54	89	2	i	int
    //   47	161	3	l	long
    //   18	169	5	localDisplayMetrics	DisplayMetrics
    // Exception table:
    //   from	to	target	type
    //   20	31	66	java/lang/SecurityException
    //   20	31	80	java/lang/NoSuchFieldException
    //   48	55	94	java/lang/IllegalArgumentException
    //   48	55	110	java/lang/IllegalAccessException
  }
  
  public void a(boolean paramBoolean)
  {
    d.b(paramBoolean);
  }
  
  public class a
  {
    public bw<aw> a = null;
    public boolean b = false;
    public boolean c = false;
    String d = "zh_cn";
    String e;
    String f = "SatelliteMap3";
    String g = "GridTmc3";
    String h = "SateliteTmc3";
    private boolean j = false;
    private boolean k = true;
    private Context l;
    private int m = 0;
    private int n = 0;
    private boolean o = false;
    
    private a(Context paramContext)
    {
      if (paramContext == null) {
        return;
      }
      l = paramContext;
      this$1 = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(bl.this);
      int i1 = widthPixels / y.i + c();
      int i2 = heightPixels / y.i + c();
      m = (i1 + i1 * i2 + i2);
      n = (m / 8 + 1);
      if (n == 0) {
        n = 1;
      }
      for (;;)
      {
        a(paramContext, "zh_cn");
        return;
        if (n > 5) {
          n = 5;
        }
      }
    }
    
    private void a(Context paramContext, String paramString)
    {
      if (a == null) {
        a = new bw();
      }
      if ((y.g == null) || (y.g.equals(""))) {
        if (paramString.equals("zh_cn")) {
          e = "GridMapV3";
        }
      }
      for (;;)
      {
        paramString = new aw();
        j = new bl.a.1(this);
        a = e;
        e = true;
        d = true;
        f = true;
        g = true;
        b = y.c;
        c = y.d;
        a(paramString, paramContext);
        return;
        if (paramString.equals("en"))
        {
          e = "GridMapEnV3";
          continue;
          e = y.g;
        }
      }
    }
    
    private void a(Canvas paramCanvas)
    {
      int i2 = a.size();
      int i1 = 0;
      if (i1 < i2)
      {
        aw localaw = (aw)a.get(i1);
        if (localaw == null) {}
        for (;;)
        {
          i1 += 1;
          break;
          if (f) {
            localaw.a(paramCanvas);
          }
        }
      }
    }
    
    private void b(Canvas paramCanvas)
    {
      if (k) {
        f.a(paramCanvas);
      }
    }
    
    private int c()
    {
      if (y.i == 512) {
        return 4;
      }
      return 3;
    }
    
    private void c(Canvas paramCanvas)
    {
      g.i.a(paramCanvas);
    }
    
    private void c(String paramString)
    {
      if (paramString.equals("") == true) {
        return;
      }
      int i2 = a.size();
      int i1 = 0;
      label21:
      aw localaw;
      if (i1 < i2)
      {
        localaw = (aw)a.get(i1);
        if (localaw != null) {
          break label51;
        }
      }
      for (;;)
      {
        i1 += 1;
        break label21;
        break;
        label51:
        if ((!a.equals(paramString)) && (e == true) && (f == true)) {
          f = false;
        }
      }
    }
    
    private void d()
    {
      int i2 = a.size();
      int i1 = 0;
      if (i1 < i2)
      {
        aw localaw = (aw)a.get(i1);
        if (localaw == null) {}
        for (;;)
        {
          i1 += 1;
          break;
          k = i1;
        }
      }
    }
    
    private boolean d(String paramString)
    {
      if (a == null) {
        return false;
      }
      int i2 = a.size();
      int i1 = 0;
      if (i1 < i2)
      {
        aw localaw = (aw)a.get(i1);
        if (localaw == null) {}
        while (a.equals(paramString) != true)
        {
          i1 += 1;
          break;
        }
        return true;
      }
      return false;
    }
    
    public void a()
    {
      if (d.a == null) {
        return;
      }
      Iterator localIterator = d.a.iterator();
      while (localIterator.hasNext())
      {
        aw localaw = (aw)localIterator.next();
        if (localaw != null) {
          localaw.a();
        }
      }
      d.a.clear();
      d.a = null;
    }
    
    public void a(Canvas paramCanvas, Matrix paramMatrix, float paramFloat1, float paramFloat2)
    {
      if (j)
      {
        paramCanvas.save();
        paramCanvas.translate(paramFloat1, paramFloat2);
        paramCanvas.concat(paramMatrix);
        a(paramCanvas);
        if (g.h.b()) {
          b(paramCanvas);
        }
        g.h.a(paramCanvas);
        paramCanvas.restore();
        if (!g.h.b()) {
          b(paramCanvas);
        }
        if ((!b) && (!c))
        {
          a(false);
          bl.d.a(b).b(new Matrix());
          bl.d.a(b).c(1.0F);
          bl.d.a(b).I();
        }
      }
      for (;;)
      {
        c(paramCanvas);
        return;
        a(paramCanvas);
        b(paramCanvas);
        g.h.a(paramCanvas);
      }
    }
    
    public void a(String paramString)
    {
      if ((paramString == null) || (paramString.equals(""))) {}
      while ((d.equals(paramString)) || ((!paramString.equals("zh_cn")) && (!paramString.equals("en")))) {
        return;
      }
      a();
      a(l, paramString);
      d = paramString;
    }
    
    public void a(boolean paramBoolean)
    {
      j = paramBoolean;
    }
    
    public boolean a(int paramInt, KeyEvent paramKeyEvent)
    {
      return false;
    }
    
    public boolean a(MotionEvent paramMotionEvent)
    {
      return false;
    }
    
    boolean a(aw paramaw, Context paramContext)
    {
      if (paramaw == null) {}
      while ((a.equals("") == true) || (d(a) == true)) {
        return false;
      }
      o = new bw();
      m = new q(m, n, h, i);
      n = new r(paramContext, ab).c, paramaw);
      n.a(m);
      int i1 = a.size();
      boolean bool;
      if ((!e) || (i1 == 0)) {
        bool = a.add(paramaw);
      }
      for (;;)
      {
        d();
        if (f == true) {
          a(a, true);
        }
        return bool;
        i1 -= 1;
        if (i1 >= 0)
        {
          paramContext = (aw)a.get(i1);
          if (paramContext == null) {}
          while (e != true)
          {
            i1 -= 1;
            break;
          }
          a.add(i1, paramaw);
          bool = false;
        }
        else
        {
          bool = false;
        }
      }
    }
    
    boolean a(String paramString, boolean paramBoolean)
    {
      if (paramString.equals("") == true) {
        return false;
      }
      int i2 = a.size();
      int i1 = 0;
      if (i1 < i2)
      {
        aw localaw = (aw)a.get(i1);
        if (localaw == null) {}
        do
        {
          do
          {
            i1 += 1;
            break;
          } while (a.equals(paramString) != true);
          f = paramBoolean;
          if (!e)
          {
            localaw.a();
            return true;
          }
        } while (paramBoolean != true);
        if (b > c)
        {
          b.b(b);
          b.c(c);
        }
        c(paramString);
        b.a(false, false);
        return true;
      }
      return false;
    }
    
    aw b(String paramString)
    {
      if ((paramString.equals("") == true) || (a == null) || (a.size() == 0)) {
        return null;
      }
      int i2 = a.size();
      int i1 = 0;
      if (i1 < i2)
      {
        aw localaw = (aw)a.get(i1);
        if (localaw == null) {}
        while (a.equals(paramString) != true)
        {
          i1 += 1;
          break;
        }
        return localaw;
      }
      return null;
    }
    
    public void b()
    {
      if ((b == null) || (bl.d.a(b) == null)) {
        return;
      }
      bl.d.a(b).postInvalidate();
    }
    
    public void b(boolean paramBoolean)
    {
      k = paramBoolean;
    }
    
    public boolean b(int paramInt, KeyEvent paramKeyEvent)
    {
      return false;
    }
    
    protected boolean b(MotionEvent paramMotionEvent)
    {
      return false;
    }
  }
  
  public class b
  {
    public boolean a = false;
    int b = 0;
    
    public b()
    {
      e();
    }
    
    public void a()
    {
      if (bl.a.a(d)) {
        d.b();
      }
      b += 1;
      if ((b < 20) || (b % 20 != 0)) {
        return;
      }
      int i = 0;
      label55:
      bi localbi;
      if (i < bl.c.a(e).size())
      {
        localbi = (bi)bl.c.a(e).valueAt(i);
        if (localbi != null) {
          break label101;
        }
      }
      for (;;)
      {
        i += 1;
        break label55;
        break;
        label101:
        localbi.h();
      }
    }
    
    public void b()
    {
      b.a = false;
      int i = 0;
      if (i < bl.c.a(e).size())
      {
        bi localbi = (bi)bl.c.a(e).valueAt(i);
        if (localbi == null) {}
        for (;;)
        {
          i += 1;
          break;
          localbi.a();
        }
      }
    }
    
    public void c()
    {
      int i = 0;
      if (i < bl.c.a(e).size())
      {
        bi localbi = (bi)bl.c.a(e).valueAt(i);
        if (localbi == null) {}
        for (;;)
        {
          i += 1;
          break;
          localbi.c();
        }
      }
    }
    
    public void d()
    {
      int i = 0;
      if (i < bl.c.a(e).size())
      {
        bi localbi = (bi)bl.c.a(e).valueAt(i);
        if (localbi == null) {}
        for (;;)
        {
          i += 1;
          break;
          localbi.b();
        }
      }
    }
    
    public void e()
    {
      int i = 0;
      if (i < bl.c.a(e).size())
      {
        bi localbi = (bi)bl.c.a(e).valueAt(i);
        if (localbi == null) {}
        for (;;)
        {
          i += 1;
          break;
          localbi.g();
        }
      }
    }
  }
  
  public class c
  {
    private final Context b;
    private SparseArray<bi> c = new SparseArray();
    
    private c(bl parambl, Context paramContext)
    {
      b = paramContext;
      c.put(0, new cd(parambl, paramContext));
    }
  }
  
  public class d
  {
    public boolean a = true;
    private b c;
    private ArrayList<cl> d;
    
    private d(b paramb)
    {
      c = paramb;
      d = new ArrayList();
    }
    
    public int a()
    {
      try
      {
        int i = h.f;
        return i;
      }
      catch (Throwable localThrowable)
      {
        cy.a(localThrowable, "Mediator", "getMaxZoomLevel");
      }
      return 0;
    }
    
    public void a(int paramInt)
    {
      if (paramInt != h.g)
      {
        h.g = paramInt;
        g.b[1] = paramInt;
        g.e.a(paramInt);
      }
      a(false, false);
    }
    
    public void a(int paramInt1, int paramInt2)
    {
      if ((paramInt1 != y.m) || (paramInt2 != y.n))
      {
        y.m = paramInt1;
        y.n = paramInt2;
        a(true, false);
      }
    }
    
    public void a(ae paramae)
    {
      if (paramae == null) {
        return;
      }
      if (y.p == true)
      {
        paramae = h.a(paramae);
        h.i = paramae;
      }
      a(false, false);
    }
    
    public void a(cl paramcl)
    {
      d.add(paramcl);
    }
    
    public void a(boolean paramBoolean1, boolean paramBoolean2)
    {
      Iterator localIterator = d.iterator();
      while (localIterator.hasNext()) {
        ((cl)localIterator.next()).a(paramBoolean1, paramBoolean2);
      }
      if ((g != null) && (g.h != null))
      {
        g.h.a(true);
        g.postInvalidate();
      }
    }
    
    public int b()
    {
      try
      {
        int i = h.e;
        return i;
      }
      catch (Throwable localThrowable)
      {
        cy.a(localThrowable, "Mediator", "getMinZoomLevel");
      }
      return 0;
    }
    
    public void b(int paramInt)
    {
      if (paramInt <= 0) {
        return;
      }
      try
      {
        bh localbh = h;
        y.c = paramInt;
        f = paramInt;
        return;
      }
      catch (Throwable localThrowable)
      {
        cy.a(localThrowable, "Mediator", "setMaxZoomLevel");
      }
    }
    
    public void b(ae paramae)
    {
      ae localae = b.f();
      if ((paramae != null) && (!paramae.equals(localae)))
      {
        if (y.p == true)
        {
          paramae = h.a(paramae);
          h.i = paramae;
        }
        a(false, true);
      }
    }
    
    public int c()
    {
      return y.m;
    }
    
    public void c(int paramInt)
    {
      if (paramInt <= 0) {
        return;
      }
      try
      {
        bh localbh = h;
        y.d = paramInt;
        e = paramInt;
        return;
      }
      catch (Throwable localThrowable)
      {
        cy.a(localThrowable, "Mediator", "setMinZoomLevel");
      }
    }
    
    public int d()
    {
      return y.n;
    }
    
    public int e()
    {
      try
      {
        int i = h.g;
        return i;
      }
      catch (Throwable localThrowable)
      {
        cy.a(localThrowable, "Mediator", "getZoomLevel");
      }
      return 0;
    }
    
    public ae f()
    {
      ae localae2 = h.b(h.i);
      ae localae1 = localae2;
      if (c != null)
      {
        localae1 = localae2;
        if (c.a) {
          localae1 = h.j;
        }
      }
      return localae1;
    }
    
    public b g()
    {
      return c;
    }
  }
  
  public class e
    implements br
  {
    private int b = 0;
    private HashMap<Float, Float> c = new HashMap();
    
    public e() {}
    
    private int a(boolean paramBoolean)
    {
      int i = b.c();
      ae localae1 = a(0, b.d());
      ae localae2 = a(i, 0);
      if (paramBoolean) {
        return Math.abs(localae1.a() - localae2.a());
      }
      return Math.abs(localae1.b() - localae2.b());
    }
    
    public float a(float paramFloat)
    {
      int i = b.e();
      if ((c.size() > 30) || (i != b))
      {
        b = i;
        c.clear();
      }
      if (!c.containsKey(Float.valueOf(paramFloat)))
      {
        ae localae1 = a(0, 0);
        ae localae2 = a(0, 100);
        float f = h.a(localae1, localae2);
        if (f <= 0.0F) {
          return 0.0F;
        }
        f = paramFloat / f;
        c.put(Float.valueOf(paramFloat), Float.valueOf(100.0F * f));
      }
      return ((Float)c.get(Float.valueOf(paramFloat))).floatValue();
    }
    
    public int a()
    {
      return a(false);
    }
    
    public Point a(ae paramae, Point paramPoint)
    {
      int i = b.e();
      paramae = h.b(paramae, h.i, h.k, h.h[i]);
      bm localbm = bl.d.a(b).G();
      Point localPoint = ab).a().h.k;
      float f1;
      float f2;
      int m;
      int k;
      int j;
      if (m)
      {
        int n = 1;
        try
        {
          boolean bool = g.g.f();
          n = bool;
        }
        catch (RemoteException localRemoteException)
        {
          for (;;)
          {
            localRemoteException.printStackTrace();
            continue;
            j = (int)x;
            i = (int)y;
          }
        }
        if ((l) && (n != 0))
        {
          f1 = bm.j * ((int)x - f.x) + f.x + (g.x - f.x);
          f2 = bm.j * ((int)y - f.y) + f.y + (g.y - f.y);
          i = (int)f1;
          m = (int)f2;
          k = i;
          if (f1 >= i + 0.5D) {
            k = i + 1;
          }
          i = m;
          j = k;
          if (f2 >= m + 0.5D)
          {
            i = m + 1;
            j = k;
          }
        }
      }
      for (;;)
      {
        paramae = new Point(j, i);
        if (paramPoint != null)
        {
          x = x;
          y = y;
        }
        return paramae;
        f1 = co.c;
        f2 = (int)x - x;
        f1 = x + f1 * f2;
        f2 = co.c * ((int)y - y) + y;
        i = (int)f1;
        m = (int)f2;
        k = i;
        if (f1 >= i + 0.5D) {
          k = i + 1;
        }
        i = m;
        j = k;
        if (f2 >= m + 0.5D)
        {
          i = m + 1;
          j = k;
        }
      }
    }
    
    public ae a(int paramInt1, int paramInt2)
    {
      int i = b.e();
      PointF localPointF = new PointF(paramInt1, paramInt2);
      return h.a(localPointF, h.i, h.k, h.h[i], h.l);
    }
    
    public int b()
    {
      return a(true);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */