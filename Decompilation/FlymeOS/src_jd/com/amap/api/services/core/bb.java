package com.amap.api.services.core;

import android.content.Context;
import android.os.Looper;

class bb
  extends bg
{
  private static boolean a = true;
  
  protected bb(Context paramContext)
  {
    super(paramContext);
  }
  
  protected String a()
  {
    return bf.c;
  }
  
  protected boolean a(Context paramContext)
  {
    if (a)
    {
      a = false;
      synchronized (Looper.getMainLooper())
      {
        paramContext = new aq(paramContext);
        as localas = paramContext.a();
        if (localas == null) {
          return true;
        }
        if (localas.a())
        {
          localas.a(false);
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
    return 0;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.bb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */