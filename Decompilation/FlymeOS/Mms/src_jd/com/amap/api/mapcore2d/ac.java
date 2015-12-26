package com.amap.api.mapcore2d;

import android.graphics.Canvas;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

class ac
{
  private static int a = 0;
  private CopyOnWriteArrayList<am> b = new CopyOnWriteArrayList();
  private a c = new a(null);
  private Handler d = new Handler();
  private Runnable e = new ad(this);
  
  static String a(String paramString)
  {
    a += 1;
    return paramString + a;
  }
  
  private am c(String paramString)
  {
    Iterator localIterator = b.iterator();
    while (localIterator.hasNext())
    {
      am localam = (am)localIterator.next();
      if ((localam != null) && (localam.c().equals(paramString))) {
        return localam;
      }
    }
    return null;
  }
  
  private void c()
  {
    d.removeCallbacks(e);
    d.postDelayed(e, 10L);
  }
  
  public void a()
  {
    Iterator localIterator = b.iterator();
    while (localIterator.hasNext()) {
      ((am)localIterator.next()).l();
    }
    try
    {
      localIterator = b.iterator();
      while (localIterator.hasNext()) {
        ((am)localIterator.next()).l();
      }
      b.clear();
    }
    catch (Exception localException)
    {
      cy.a(localException, "GLOverlayLayer", "clear");
      Log.d("amapApi", "GLOverlayLayer clear erro" + localException.getMessage());
      return;
    }
  }
  
  public void a(Canvas paramCanvas)
  {
    c();
    int i = b.size();
    Iterator localIterator = b.iterator();
    while (localIterator.hasNext())
    {
      am localam = (am)localIterator.next();
      try
      {
        if (!localam.e()) {
          continue;
        }
        if (i <= 20) {
          break label91;
        }
        if (!localam.a()) {
          continue;
        }
        localam.a(paramCanvas);
      }
      catch (RemoteException localRemoteException)
      {
        cy.a(localRemoteException, "GLOverlayLayer", "draw");
      }
      continue;
      label91:
      localRemoteException.a(paramCanvas);
    }
  }
  
  public void a(am paramam)
  {
    b(paramam.c());
    b.add(paramam);
    c();
  }
  
  public void b()
  {
    try
    {
      Iterator localIterator = b.iterator();
      while (localIterator.hasNext()) {
        ((am)localIterator.next()).l();
      }
      a();
    }
    catch (Exception localException)
    {
      cy.a(localException, "GLOverlayLayer", "destory");
      Log.d("amapApi", "GLOverlayLayer destory erro" + localException.getMessage());
      return;
    }
  }
  
  public boolean b(String paramString)
  {
    paramString = c(paramString);
    if (paramString != null) {
      return b.remove(paramString);
    }
    return false;
  }
  
  class a
    implements Comparator<Object>
  {
    private a() {}
    
    public int compare(Object paramObject1, Object paramObject2)
    {
      paramObject1 = (am)paramObject1;
      paramObject2 = (am)paramObject2;
      if ((paramObject1 != null) && (paramObject2 != null)) {
        try
        {
          if (((am)paramObject1).d() > ((am)paramObject2).d()) {
            return 1;
          }
          float f1 = ((am)paramObject1).d();
          float f2 = ((am)paramObject2).d();
          if (f1 < f2) {
            return -1;
          }
        }
        catch (Exception paramObject1)
        {
          cy.a((Throwable)paramObject1, "GLOverlayLayer", "compare");
        }
      }
      return 0;
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ac
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */