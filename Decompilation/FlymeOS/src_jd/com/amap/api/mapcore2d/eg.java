package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Looper;

class eg
  extends el
{
  private static boolean a = true;
  
  protected eg(Context paramContext)
  {
    super(paramContext);
  }
  
  protected String a()
  {
    return ek.c;
  }
  
  protected boolean a(Context paramContext)
  {
    if (a)
    {
      a = false;
      synchronized (Looper.getMainLooper())
      {
        paramContext = new dv(paramContext);
        dx localdx = paramContext.a();
        if (localdx == null) {
          return true;
        }
        if (localdx.a())
        {
          localdx.a(false);
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
    return 0;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.eg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */