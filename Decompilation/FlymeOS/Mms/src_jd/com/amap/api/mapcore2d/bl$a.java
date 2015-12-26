package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;
import java.util.Iterator;

public class bl$a
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
  
  private bl$a(bl parambl, Context paramContext)
  {
    if (paramContext == null) {
      return;
    }
    l = paramContext;
    parambl = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(parambl);
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
      i.f.a(paramCanvas);
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
    i.g.i.a(paramCanvas);
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
    if (i.d.a == null) {
      return;
    }
    Iterator localIterator = i.d.a.iterator();
    while (localIterator.hasNext())
    {
      aw localaw = (aw)localIterator.next();
      if (localaw != null) {
        localaw.a();
      }
    }
    i.d.a.clear();
    i.d.a = null;
  }
  
  public void a(Canvas paramCanvas, Matrix paramMatrix, float paramFloat1, float paramFloat2)
  {
    if (j)
    {
      paramCanvas.save();
      paramCanvas.translate(paramFloat1, paramFloat2);
      paramCanvas.concat(paramMatrix);
      a(paramCanvas);
      if (i.g.h.b()) {
        b(paramCanvas);
      }
      i.g.h.a(paramCanvas);
      paramCanvas.restore();
      if (!i.g.h.b()) {
        b(paramCanvas);
      }
      if ((!b) && (!c))
      {
        a(false);
        bl.d.a(i.b).b(new Matrix());
        bl.d.a(i.b).c(1.0F);
        bl.d.a(i.b).I();
      }
    }
    for (;;)
    {
      c(paramCanvas);
      return;
      a(paramCanvas);
      b(paramCanvas);
      i.g.h.a(paramCanvas);
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
    n = new r(paramContext, ai.b).c, paramaw);
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
        i.b.b(b);
        i.b.c(c);
      }
      c(paramString);
      i.b.a(false, false);
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
    if ((i.b == null) || (bl.d.a(i.b) == null)) {
      return;
    }
    bl.d.a(i.b).postInvalidate();
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

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bl.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */