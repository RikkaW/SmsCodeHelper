package com.amap.api.mapcore2d;

import java.io.Serializable;
import java.util.Comparator;

class be$a
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

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.be.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */