package cn.com.xy.sms.sdk.iccid;

import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.IccidInfo;

final class a
  implements Runnable
{
  a(IccidInfo paramIccidInfo) {}
  
  public final void run()
  {
    IccidLocationUtil.a(Constant.getContext(), a, false);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.iccid.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */