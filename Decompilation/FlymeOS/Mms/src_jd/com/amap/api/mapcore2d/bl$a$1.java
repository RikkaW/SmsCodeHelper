package com.amap.api.mapcore2d;

import java.util.Locale;

class bl$a$1
  extends ck
{
  bl$a$1(bl.a parama) {}
  
  public String a(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((y.h == null) || (y.h.equals(""))) {
      return bj.a().b() + "/appmaptile?z=" + paramInt3 + "&x=" + paramInt1 + "&y=" + paramInt2 + "&lang=" + a.d + "&size=1&scale=1&style=7";
    }
    return String.format(Locale.US, y.h, new Object[] { Integer.valueOf(paramInt3), Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bl.a.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */