package cn.com.xy.sms.sdk.net;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.net.util.i;
import java.util.HashMap;

final class f
  implements XyCallBack
{
  public final void execute(Object... paramVarArgs)
  {
    if (paramVarArgs != null)
    {
      String str = paramVarArgs[0].toString();
      if ((str.equals("0")) && (paramVarArgs.length == 2))
      {
        paramVarArgs = paramVarArgs[1].toString();
        new StringBuilder("resutCode=").append(str).append("response =").append(paramVarArgs);
        paramVarArgs = i.d(paramVarArgs);
        if (paramVarArgs != null)
        {
          SysParamEntityManager.setParam("HTTPTOKEN", paramVarArgs);
          SysParamEntityManager.cacheMap.put("HTTPTOKEN", paramVarArgs);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.f
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */