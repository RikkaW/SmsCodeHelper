package cn.com.xy.sms.sdk.db.entity;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.DBManager;

final class t
  implements XyCallBack
{
  t(r paramr) {}
  
  public final void execute(Object... paramVarArgs)
  {
    long l = a.b;
    try
    {
      DBManager.delete("tb_update_task", "id = ?", new String[] { String.valueOf(l) });
      s.b("1");
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
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.t
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */