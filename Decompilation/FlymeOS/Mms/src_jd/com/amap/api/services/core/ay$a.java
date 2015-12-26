package com.amap.api.services.core;

import android.content.Context;

class ay$a
  implements br
{
  private Context a;
  
  ay$a(Context paramContext)
  {
    a = paramContext;
  }
  
  public void a()
  {
    try
    {
      bf.b(a);
      return;
    }
    catch (Throwable localThrowable)
    {
      ay.a(localThrowable, "LogNetListener", "onNetCompleted");
      localThrowable.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.ay.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */