package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.dex.DexUtil;

final class j
  implements Runnable
{
  j(String paramString) {}
  
  public final void run()
  {
    try
    {
      String str = DexUtil.getBubbleViewVersion(null);
      ParseManager.addViewVersion(a, str);
      return;
    }
    catch (Throwable localThrowable) {}
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.j
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */