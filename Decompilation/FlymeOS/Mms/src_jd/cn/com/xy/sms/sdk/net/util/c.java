package cn.com.xy.sms.sdk.net.util;

import android.content.Context;

final class c
  extends Thread
{
  c(Context paramContext) {}
  
  public final void run()
  {
    try
    {
      b.b(a);
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.util.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */