package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Looper;

class ee
  extends el
{
  private static boolean a = true;
  
  protected ee(Context paramContext)
  {
    super(paramContext);
  }
  
  protected String a()
  {
    return ek.d;
  }
  
  protected boolean a(Context paramContext)
  {
    if ((dd.g(paramContext) == 1) && (a))
    {
      a = false;
      synchronized (Looper.getMainLooper())
      {
        paramContext = new dv(paramContext);
        dx localdx = paramContext.a();
        if (localdx == null) {
          return true;
        }
        if (localdx.c())
        {
          localdx.c(false);
          paramContext.a(localdx);
          return true;
        }
      }
      return false;
    }
    return false;
  }
  
  protected int b()
  {
    return 2;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ee
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */