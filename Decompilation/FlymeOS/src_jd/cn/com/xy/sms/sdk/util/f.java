package cn.com.xy.sms.sdk.util;

import cn.com.xy.sms.sdk.db.ParseItemManager;

final class f
  implements Runnable
{
  public final void run()
  {
    ParseItemManager.deleteRepeatData();
    ParseItemManager.updateStatue(-2, 0);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.f
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */