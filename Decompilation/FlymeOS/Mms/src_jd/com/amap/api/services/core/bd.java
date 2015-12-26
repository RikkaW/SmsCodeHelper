package com.amap.api.services.core;

import android.content.Context;
import android.os.Looper;

class bd
  extends bg
{
  private static boolean a = true;
  
  protected bd(Context paramContext)
  {
    super(paramContext);
  }
  
  protected String a()
  {
    return bf.b;
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
        if (localas.b())
        {
          localas.b(false);
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
    return 1;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.bd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */