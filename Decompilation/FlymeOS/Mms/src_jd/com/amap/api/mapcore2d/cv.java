package com.amap.api.mapcore2d;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import java.lang.ref.WeakReference;

public abstract class cv
{
  protected Resources a;
  private cs b;
  private cs.a c;
  private boolean d = false;
  private boolean e = false;
  private final Object f = new Object();
  
  protected cv(Context paramContext)
  {
    a = paramContext.getResources();
  }
  
  public static void a(cb.a parama)
  {
    a locala = c(parama);
    if (locala != null)
    {
      locala.a(true);
      cw.a("ImageWorker", "cancelWork - cancelled work for " + parama, 111);
    }
  }
  
  private static a c(cb.a parama)
  {
    if (parama != null) {
      return d;
    }
    return null;
  }
  
  protected abstract Bitmap a(Object paramObject);
  
  protected cs a()
  {
    return b;
  }
  
  public void a(cs.a parama)
  {
    c = parama;
    b = cs.a(c);
    new b(null).c(new Object[] { Integer.valueOf(1) });
  }
  
  public void a(boolean paramBoolean, cb.a parama)
  {
    if (parama == null) {
      return;
    }
    if (b != null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(a);
      ((StringBuilder)localObject).append("-");
      ((StringBuilder)localObject).append(b);
      ((StringBuilder)localObject).append("-");
      ((StringBuilder)localObject).append(c);
    }
    for (Object localObject = b.a(((StringBuilder)localObject).toString());; localObject = null)
    {
      if (localObject != null)
      {
        parama.a((Bitmap)localObject);
        return;
      }
      localObject = new a(parama, null);
      d = ((a)localObject);
      ((a)localObject).a(cq.b, new Boolean[] { Boolean.valueOf(paramBoolean) });
      return;
    }
  }
  
  protected void b()
  {
    if (b != null) {
      b.a();
    }
  }
  
  protected void c()
  {
    if (b != null) {
      b.b();
    }
  }
  
  protected void d()
  {
    if (b != null) {
      b.c();
    }
  }
  
  protected void e()
  {
    if (b != null)
    {
      b.d();
      b = null;
    }
  }
  
  public void f()
  {
    new b(null).c(new Object[] { Integer.valueOf(0) });
  }
  
  public void g()
  {
    new b(null).c(new Object[] { Integer.valueOf(3) });
  }
  
  public class a
    extends cq<Boolean, Void, Bitmap>
  {
    private final WeakReference<cb.a> c;
    
    private a(cb.a parama)
    {
      c = new WeakReference(parama);
    }
    
    private cb.a e()
    {
      cb.a locala = (cb.a)c.get();
      if (this == cv.b(locala)) {
        return locala;
      }
      return null;
    }
    
    protected Bitmap a(Boolean... arg1)
    {
      Object localObject1 = null;
      cw.a("ImageWorker", "doInBackground - starting work", 111);
      boolean bool1 = ???[0].booleanValue();
      cb.a locala = (cb.a)c.get();
      if (locala == null) {
        return null;
      }
      ??? = new StringBuilder();
      ???.append(a);
      ???.append("-");
      ???.append(b);
      ???.append("-");
      ???.append(c);
      String str = ???.toString();
      synchronized (cv.a(cv.this))
      {
        for (;;)
        {
          if (cv.b(cv.this))
          {
            boolean bool2 = c();
            if (!bool2) {
              try
              {
                cv.a(cv.this).wait();
              }
              catch (InterruptedException localInterruptedException)
              {
                cy.a(localInterruptedException, "ImageWorker", "doInBackground");
              }
            }
          }
        }
      }
      ??? = (Boolean[])localObject2;
      if (cv.c(cv.this) != null)
      {
        ??? = (Boolean[])localObject2;
        if (!c())
        {
          ??? = (Boolean[])localObject2;
          if (e() != null)
          {
            ??? = (Boolean[])localObject2;
            if (!cv.d(cv.this)) {
              ??? = cv.c(cv.this).b(str);
            }
          }
        }
      }
      if ((bool1) && (??? == null) && (!c()) && (e() != null) && (!cv.d(cv.this))) {
        ??? = a(locala);
      }
      for (;;)
      {
        if ((??? != null) && (cv.c(cv.this) != null)) {
          cv.c(cv.this).a(str, ???);
        }
        cw.a("ImageWorker", "doInBackground - finished work", 111);
        return (Bitmap)???;
      }
    }
    
    protected void a(Bitmap paramBitmap)
    {
      if ((c()) || (cv.d(cv.this))) {
        paramBitmap = null;
      }
      cb.a locala = e();
      if ((paramBitmap != null) && (!paramBitmap.isRecycled()) && (locala != null))
      {
        cw.a("ImageWorker", "onPostExecute - setting bitmap: " + locala.toString(), 111);
        locala.a(paramBitmap);
      }
    }
    
    protected void b(Bitmap arg1)
    {
      super.b(???);
      synchronized (cv.a(cv.this))
      {
        cv.a(cv.this).notifyAll();
        return;
      }
    }
  }
  
  class b
    extends cq<Object, Void, Void>
  {
    private b() {}
    
    protected Void d(Object... paramVarArgs)
    {
      switch (((Integer)paramVarArgs[0]).intValue())
      {
      }
      for (;;)
      {
        return null;
        c();
        continue;
        b();
        continue;
        d();
        continue;
        e();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */