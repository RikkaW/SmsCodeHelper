package com.amap.api.mapcore2d;

import java.util.Comparator;

class cc$a
  implements Comparator<Object>
{
  private cc$a(cc paramcc) {}
  
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

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cc.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */