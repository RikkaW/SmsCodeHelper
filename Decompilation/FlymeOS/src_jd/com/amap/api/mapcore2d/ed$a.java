package com.amap.api.mapcore2d;

import android.content.Context;

class ed$a
  implements ew
{
  private Context a;
  
  ed$a(Context paramContext)
  {
    a = paramContext;
  }
  
  public void a()
  {
    try
    {
      ek.b(a);
      return;
    }
    catch (Throwable localThrowable)
    {
      ed.a(localThrowable, "LogNetListener", "onNetCompleted");
      localThrowable.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ed.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */