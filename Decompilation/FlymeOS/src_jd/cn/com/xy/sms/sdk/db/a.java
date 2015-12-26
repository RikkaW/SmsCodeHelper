package cn.com.xy.sms.sdk.db;

import cn.com.xy.sms.sdk.constant.Constant;

final class a
  extends Thread
{
  public final void run()
  {
    AirManager.importAirData(Constant.getContext());
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */