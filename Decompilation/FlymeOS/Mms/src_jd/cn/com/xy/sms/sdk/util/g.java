package cn.com.xy.sms.sdk.util;

import cn.com.xy.sms.sdk.db.ParseItemManager;

final class g
  implements Runnable
{
  public final void run()
  {
    ParseItemManager.updateStatue(0, -2);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.g
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */