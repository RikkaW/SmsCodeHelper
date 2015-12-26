package cn.com.xy.sms.util;

import android.os.Process;
import bx;
import cn.com.xy.sms.sdk.db.ParseItemManager;
import cn.com.xy.sms.sdk.db.c;
import cn.com.xy.sms.sdk.db.entity.f;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.queue.g;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.i;
import java.util.Map;

final class h
  implements Runnable
{
  h(Map paramMap, SdkCallBack paramSdkCallBack) {}
  
  public final void run()
  {
    Thread.currentThread().setName("xiaoyuan_pool_netutil");
    Process.setThreadPriority(g.b);
    try
    {
      if (!NetUtil.checkAccessNetWork(a))
      {
        XyUtil.doXycallBack(b, "-1");
        return;
      }
      if (!ParseItemManager.isInitData())
      {
        XyUtil.doXycallBack(b, "2");
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      return;
    }
    bx.a(a, null);
    Object localObject = c.c();
    if (!c.c((f)localObject))
    {
      Map localMap = a;
      if (!i.b()) {}
    }
    else
    {
      if (c.c((f)localObject)) {
        c.a((f)localObject);
      }
      localObject = a;
      if (i.b())
      {
        i.a(a, b);
        return;
      }
      if (c.c(c.c()))
      {
        XyUtil.doXycallBack(b, "0");
        return;
      }
      XyUtil.doXycallBack(b, "1");
      return;
    }
    XyUtil.doXycallBack(b, "2");
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.h
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */