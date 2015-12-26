package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.util.XyUtil;

final class e
  implements XyCallBack
{
  e(SdkCallBack paramSdkCallBack) {}
  
  public final void execute(Object... paramVarArgs)
  {
    if ((paramVarArgs == null) || (paramVarArgs.length < 2))
    {
      XyUtil.doXycallBackResult(a, new Object[0]);
      return;
    }
    XyUtil.doXycallBackResult(a, paramVarArgs);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */