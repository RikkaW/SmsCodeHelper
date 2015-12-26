package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.sdk.queue.i;
import cn.com.xy.sms.sdk.util.DateUtils;
import cn.com.xy.sms.sdk.util.e;
import java.util.Map;

final class g
  implements Runnable
{
  g(Context paramContext, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, Map paramMap) {}
  
  public final void run()
  {
    try
    {
      Context localContext = a.getApplicationContext();
      Object localObject = localContext;
      if (localContext == null) {
        localObject = a;
      }
      long l = System.currentTimeMillis();
      SysParamEntityManager.initParams((Context)localObject, b, c, d, e, f);
      new StringBuilder("initParams time=").append(System.currentTimeMillis() - l);
      cn.com.xy.sms.sdk.queue.g.a();
      e.b();
      localObject = DexUtil.getBubbleViewVersion(null);
      ParseManager.addViewVersion(DateUtils.getCurrentTimeString("yyyyMMdd"), (String)localObject);
      cn.com.xy.sms.sdk.queue.g.a(new i(2, new String[0]));
      cn.com.xy.sms.sdk.queue.g.a(new i(7, new String[0]));
      localObject = b;
      l.a();
      return;
    }
    catch (Throwable localThrowable)
    {
      LogManager.e("XIAOYUAN", "ParseManager.initSdk " + localThrowable.getMessage(), localThrowable);
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.g
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */