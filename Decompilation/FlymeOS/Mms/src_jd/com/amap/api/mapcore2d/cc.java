package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

class cc
  extends View
{
  CopyOnWriteArrayList<Integer> a = new CopyOnWriteArrayList();
  private ag b;
  private CopyOnWriteArrayList<at> c = new CopyOnWriteArrayList();
  private a d = new a(null);
  
  public cc(Context paramContext, ag paramag)
  {
    super(paramContext);
    b = paramag;
  }
  
  ag a()
  {
    return b;
  }
  
  protected void a(Canvas paramCanvas)
  {
    Iterator localIterator = c.iterator();
    while (localIterator.hasNext())
    {
      at localat = (at)localIterator.next();
      if (localat.e()) {
        localat.a(paramCanvas);
      }
    }
  }
  
  public void a(at paramat)
  {
    b(paramat);
    c.add(paramat);
    c();
  }
  
  public void a(boolean paramBoolean)
  {
    Iterator localIterator = c.iterator();
    while (localIterator.hasNext())
    {
      at localat = (at)localIterator.next();
      if ((localat != null) && (localat.e())) {
        localat.b(paramBoolean);
      }
    }
  }
  
  protected boolean b()
  {
    return c.size() > 0;
  }
  
  public boolean b(at paramat)
  {
    return c.remove(paramat);
  }
  
  void c()
  {
    Object[] arrayOfObject = c.toArray();
    Arrays.sort(arrayOfObject, d);
    c.clear();
    int j = arrayOfObject.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = arrayOfObject[i];
      c.add((at)localObject);
      i += 1;
    }
  }
  
  class a
    implements Comparator<Object>
  {
    private a() {}
    
    public int compare(Object paramObject1, Object paramObject2)
    {
      paramObject1 = (at)paramObject1;
      paramObject2 = (at)paramObject2;
      if ((paramObject1 != null) && (paramObject2 != null)) {
        try
        {
          if (((at)paramObject1).d() > ((at)paramObject2).d()) {
            return 1;
          }
          float f1 = ((at)paramObject1).d();
          float f2 = ((at)paramObject2).d();
          if (f1 < f2) {
            return -1;
          }
        }
        catch (Exception paramObject1)
        {
          cy.a((Throwable)paramObject1, "TileOverlayView", "compare");
        }
      }
      return 0;
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */