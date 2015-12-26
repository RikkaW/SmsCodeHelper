package cn.com.xy.sms.sdk.db.entity.a;

import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.util.SdkCallBack;
import java.util.HashMap;

final class f
  implements SdkCallBack
{
  f(SdkCallBack paramSdkCallBack, HashMap paramHashMap) {}
  
  public final void execute(Object... paramVarArgs)
  {
    if ((a != null) && (paramVarArgs != null) && (paramVarArgs.length == 2)) {
      if (paramVarArgs[1] != null) {
        break label68;
      }
    }
    label68:
    for (String str = null;; str = paramVarArgs[1].toString())
    {
      if ((!StringUtils.isNull(str)) && (!str.equals(b.get("pubId")))) {
        a.execute(new Object[] { paramVarArgs[0] });
      }
      return;
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.a.f
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */