package cn.com.xy.sms.sdk.util;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.entity.SceneRule;
import cn.com.xy.sms.sdk.db.entity.l;
import cn.com.xy.sms.sdk.db.entity.m;
import cn.com.xy.sms.sdk.db.entity.o;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.util.h;
import java.util.List;

final class t
  implements XyCallBack
{
  t(int paramInt) {}
  
  public final void execute(Object... paramVarArgs)
  {
    if (paramVarArgs != null) {}
    for (;;)
    {
      int k;
      int i;
      try
      {
        if ((!paramVarArgs[0].toString().equals("0")) || (paramVarArgs.length != 2)) {
          break label228;
        }
        paramVarArgs = paramVarArgs[1].toString();
        if (LogManager.debug) {}
        paramVarArgs = h.b(paramVarArgs);
        if ((paramVarArgs == null) || (paramVarArgs.isEmpty())) {
          break label228;
        }
        k = paramVarArgs.size();
        i = 0;
      }
      catch (Throwable paramVarArgs)
      {
        SceneRule localSceneRule;
        List localList;
        int m;
        int j;
        String str;
        l locall;
        paramVarArgs.printStackTrace();
        return;
      }
      localSceneRule = (SceneRule)paramVarArgs.get(i);
      o.b(localSceneRule, a);
      localList = SceneconfigUtil.getUrls(res_urls);
      if ((localList != null) && (!localList.isEmpty()))
      {
        m = localList.size();
        j = 0;
        if (j >= m)
        {
          z.a(false);
        }
        else
        {
          str = (String)localList.get(j);
          locall = new l();
          e = 0;
          b = scene_id;
          d = 0;
          c = str;
          if (!m.b(str))
          {
            m.a(locall);
            z.a(str);
          }
          j += 1;
          continue;
        }
      }
      while (i >= k)
      {
        label228:
        return;
        i += 1;
      }
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.t
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */