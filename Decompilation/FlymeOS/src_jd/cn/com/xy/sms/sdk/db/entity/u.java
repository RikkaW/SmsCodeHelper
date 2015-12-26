package cn.com.xy.sms.sdk.db.entity;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.DBManager;
import java.util.List;

final class u
  implements XyCallBack
{
  u(List paramList) {}
  
  public final void execute(Object... paramVarArgs)
  {
    paramVarArgs = s.a(a);
    try
    {
      DBManager.delete("tb_update_task", "id IN (" + paramVarArgs + ")", null);
      s.b("2");
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
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.u
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */