package com.amap.api.mapcore2d;

import java.util.Comparator;

class ac$a
  implements Comparator<Object>
{
  private ac$a(ac paramac) {}
  
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

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ac.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */