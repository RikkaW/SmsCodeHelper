package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import java.lang.ref.WeakReference;

public class cv$a
  extends cq<Boolean, Void, Bitmap>
{
  private final WeakReference<cb.a> c;
  
  private cv$a(cv paramcv, cb.a parama)
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
    synchronized (cv.a(a))
    {
      for (;;)
      {
        if (cv.b(a))
        {
          boolean bool2 = c();
          if (!bool2) {
            try
            {
              cv.a(a).wait();
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
    if (cv.c(a) != null)
    {
      ??? = (Boolean[])localObject2;
      if (!c())
      {
        ??? = (Boolean[])localObject2;
        if (e() != null)
        {
          ??? = (Boolean[])localObject2;
          if (!cv.d(a)) {
            ??? = cv.c(a).b(str);
          }
        }
      }
    }
    if ((bool1) && (??? == null) && (!c()) && (e() != null) && (!cv.d(a))) {
      ??? = a.a(locala);
    }
    for (;;)
    {
      if ((??? != null) && (cv.c(a) != null)) {
        cv.c(a).a(str, ???);
      }
      cw.a("ImageWorker", "doInBackground - finished work", 111);
      return (Bitmap)???;
    }
  }
  
  protected void a(Bitmap paramBitmap)
  {
    if ((c()) || (cv.d(a))) {
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
    synchronized (cv.a(a))
    {
      cv.a(a).notifyAll();
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cv.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */