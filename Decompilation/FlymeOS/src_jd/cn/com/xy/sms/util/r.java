package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import java.util.Map;

final class r
  extends Thread
{
  r(String paramString, Map paramMap) {}
  
  public final void run()
  {
    setName("xiaoyuan-parseSmsToBubble1");
    try
    {
      int i = Integer.valueOf(a).intValue();
      Map localMap = DexUtil.handerValueMapByType(i, b);
      if (localMap != null) {
        DuoquUtils.getSdkDoAction().parseMsgCallBack(i, localMap);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.r
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */