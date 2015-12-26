package cn.com.xy.sms.sdk.db;

import cn.com.xy.sms.sdk.constant.Constant;

final class f
  extends Thread
{
  public final void run()
  {
    TrainManager.importTrainData(Constant.getContext());
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.f
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */