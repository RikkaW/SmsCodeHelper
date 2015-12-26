package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import org.json.JSONObject;

final class t
  implements SdkCallBack
{
  t(int paramInt, SdkCallBack paramSdkCallBack) {}
  
  public final void execute(Object... paramVarArgs)
  {
    if (paramVarArgs != null)
    {
      try
      {
        paramVarArgs = (String)paramVarArgs[0];
        if (!StringUtils.isNull(paramVarArgs))
        {
          paramVarArgs = new JSONObject(paramVarArgs);
          if (a == 1)
          {
            XyUtil.doXycallBack(b, paramVarArgs.optString("logo"));
            return;
          }
          XyUtil.doXycallBack(b, paramVarArgs.optString("logoc"));
          return;
        }
      }
      catch (Throwable paramVarArgs)
      {
        XyUtil.doXycallBack(b, "");
        return;
      }
      XyUtil.doXycallBack(b, "");
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.t
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */