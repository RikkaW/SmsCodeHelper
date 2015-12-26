package com.amap.api.mapcore2d;

import android.content.Context;

final class ek$1
  implements Runnable
{
  ek$1(int paramInt, Context paramContext, Throwable paramThrowable, String paramString1, String paramString2) {}
  
  public void run()
  {
    try
    {
      en localen = en.a(a);
      if (localen == null) {
        return;
      }
      localen.a(b, c, d, e);
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ek.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */