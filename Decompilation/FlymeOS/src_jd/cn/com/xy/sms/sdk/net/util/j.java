package cn.com.xy.sms.sdk.net.util;

import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.base.BaseManager;

final class j
  implements Runnable
{
  j(String paramString) {}
  
  public final void run()
  {
    String str = a;
    try
    {
      DBManager.insert("tb_update_task", BaseManager.getContentValues(null, new String[] { "content", str, "t_group", "2", "t_version", "0" }));
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.util.j
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */