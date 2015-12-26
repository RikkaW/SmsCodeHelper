package cn.com.xy.sms.sdk.util;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.k;
import cn.com.xy.sms.sdk.net.util.h;

final class p
  implements XyCallBack
{
  p(String paramString) {}
  
  public final void execute(Object... paramVarArgs)
  {
    if ((paramVarArgs != null) && (paramVarArgs[0].toString().equals("0")) && (paramVarArgs.length == 2) && (h.d(paramVarArgs[1].toString())))
    {
      SysParamEntityManager.setParam("LastSceneCountActionUpdate", a);
      paramVarArgs = a;
    }
    try
    {
      DBManager.delete("tb_popup_action_scene", "date < ?", new String[] { paramVarArgs });
      k.a(a);
      return;
    }
    catch (Throwable paramVarArgs)
    {
      for (;;)
      {
        paramVarArgs.printStackTrace();
      }
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.p
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */