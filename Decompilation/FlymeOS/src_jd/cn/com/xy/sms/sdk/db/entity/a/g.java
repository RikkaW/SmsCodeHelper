package cn.com.xy.sms.sdk.db.entity.a;

import bs;
import bx;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.util.SdkCallBack;

final class g
  implements Runnable
{
  g(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, SdkCallBack paramSdkCallBack, boolean paramBoolean) {}
  
  public final void run()
  {
    if (NetUtil.isEnhance()) {
      bs.a(false, a, b, c, d, e, f, 1, false, g);
    }
    for (;;)
    {
      if (NetUtil.checkAccessNetWork(1)) {
        bx.a(c, d);
      }
      return;
      if (f != null) {
        f.execute(new Object[0]);
      }
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.a.g
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */