package cn.com.xy.sms.sdk.util;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.net.util.h;

final class u
  implements XyCallBack
{
  public final void execute(Object... paramVarArgs)
  {
    if ((paramVarArgs != null) && (paramVarArgs[0].toString().equals("0")) && (paramVarArgs.length == 2) && (h.c(paramVarArgs[1].toString())))
    {
      SysParamEntityManager.setParam("LastPostIccidSceneTime", System.currentTimeMillis());
      SysParamEntityManager.setParam("PostCount", SysParamEntityManager.getLongParam("PostCount", 0L, Constant.getContext()) + 1L);
    }
    try
    {
      DBManager.delete("tb_count_scene", null, null);
      return;
    }
    catch (Throwable paramVarArgs)
    {
      paramVarArgs.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.u
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */