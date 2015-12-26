package cn.com.xy.sms.sdk.db.entity.a;

import bx;
import cn.com.xy.sms.sdk.constant.Constant;
import java.util.HashMap;

final class d
  implements Runnable
{
  d(String paramString) {}
  
  public final void run()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("SUPPORT_NET_QUERY", "1");
    Constant.getContext();
    bx.a(a, 1, null, localHashMap, null);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.a.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */