package cn.com.xy.sms.sdk.queue;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.n;
import cn.com.xy.sms.sdk.net.util.h;

final class d
  implements XyCallBack
{
  d(int paramInt) {}
  
  public final void execute(Object... paramVarArgs)
  {
    if (paramVarArgs != null) {}
    try
    {
      if ((paramVarArgs[0].toString().equals("0")) && (paramVarArgs.length == 2))
      {
        paramVarArgs = paramVarArgs[1].toString();
        int i = a;
        SysParamEntityManager.setParam("LastCheckResourseTime_" + i, System.currentTimeMillis());
        n.a(h.f(paramVarArgs));
      }
      return;
    }
    catch (Throwable paramVarArgs)
    {
      paramVarArgs.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.queue.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */