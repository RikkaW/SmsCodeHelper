package cn.com.xy.sms.sdk.iccid;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.IccidInfoManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.util.i;
import cn.com.xy.sms.sdk.util.StringUtils;

final class b
  implements XyCallBack
{
  b(String paramString1, String paramString2) {}
  
  public final void execute(Object... paramVarArgs)
  {
    cn.com.xy.sms.sdk.db.entity.a locala;
    if (paramVarArgs != null)
    {
      String str = paramVarArgs[0].toString();
      if ((str.equals("0")) && (paramVarArgs.length == 2))
      {
        paramVarArgs = paramVarArgs[1].toString();
        new StringBuilder("iccid=").append(a).append(" response=").append(paramVarArgs);
        locala = i.c(paramVarArgs);
        if (a != 0) {
          break label166;
        }
        new StringBuilder("resutCode=").append(str).append("response =").append(paramVarArgs);
        b = StringUtils.getSubString(b);
        g = System.currentTimeMillis();
        if (StringUtils.isNull(b)) {
          break label127;
        }
        cn.com.xy.sms.sdk.db.entity.a.a.a(locala);
      }
    }
    label127:
    label166:
    while (a != i.b)
    {
      do
      {
        return;
      } while (StringUtils.isNull(a));
      IccidInfoManager.insertIccid(a, true, d, c, e, f, Constant.getContext());
      return;
    }
    NetUtil.QueryTokenRequest(a);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.iccid.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */