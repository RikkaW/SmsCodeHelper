package cn.com.xy.sms.sdk.db.entity.a;

import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.db.entity.g;

final class h
  implements Runnable
{
  h(int paramInt1, String paramString1, String paramString2, int paramInt2, String paramString3, String paramString4) {}
  
  public final void run()
  {
    try
    {
      DBManager.saveOrUpdateTableData("tb_public_num_info", BaseManager.getContentValues(null, new String[] { "pubId", String.valueOf(a), "areaCode", b, "num", c, "ptype", String.valueOf(d), "lastloadtime", String.valueOf(System.currentTimeMillis()), "isrulenum", "1", "purpose", e, "extend", f }), " num = ? and ptype = ? ", new String[] { c, String.valueOf(d) });
      g.b(c, b);
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.a.h
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */