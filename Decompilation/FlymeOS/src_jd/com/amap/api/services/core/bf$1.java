package com.amap.api.services.core;

import android.content.Context;

final class bf$1
  implements Runnable
{
  bf$1(int paramInt, Context paramContext, Throwable paramThrowable, String paramString1, String paramString2) {}
  
  public void run()
  {
    try
    {
      bi localbi = bi.a(a);
      if (localbi == null) {
        return;
      }
      localbi.a(b, c, d, e);
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.bf.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */