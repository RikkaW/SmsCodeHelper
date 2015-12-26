package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Looper;

class ei
  extends el
{
  private static boolean a = true;
  
  protected ei(Context paramContext)
  {
    super(paramContext);
  }
  
  protected String a()
  {
    return ek.b;
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
        if (localdx.b())
        {
          localdx.b(false);
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
    return 1;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ei
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */