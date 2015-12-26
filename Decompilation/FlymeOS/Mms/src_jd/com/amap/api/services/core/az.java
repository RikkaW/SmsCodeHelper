package com.amap.api.services.core;

import android.content.Context;
import android.os.Looper;

class az
  extends bg
{
  private static boolean a = true;
  
  protected az(Context paramContext)
  {
    super(paramContext);
  }
  
  protected String a()
  {
    return bf.d;
  }
  
  protected boolean a(Context paramContext)
  {
    if ((z.g(paramContext) == 1) && (a))
    {
      a = false;
      synchronized (Looper.getMainLooper())
      {
        paramContext = new aq(paramContext);
        as localas = paramContext.a();
        if (localas == null) {
          return true;
        }
        if (localas.c())
        {
          localas.c(false);
          paramContext.a(localas);
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
 * Qualified Name:     com.amap.api.services.core.az
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */