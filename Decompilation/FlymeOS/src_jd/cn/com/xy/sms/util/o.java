package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.db.entity.a.e;

final class o
  implements SdkCallBack
{
  o(SdkCallBack paramSdkCallBack, String paramString) {}
  
  public final void execute(Object... paramVarArgs)
  {
    if (paramVarArgs != null) {}
    try
    {
      if ((paramVarArgs.length == 0) || (paramVarArgs[0] == null) || (!paramVarArgs[0].toString().contains("action_data")))
      {
        a.execute(new Object[] { Integer.valueOf(0), "menu json data error" });
        return;
      }
      a.execute(new Object[] { Integer.valueOf(1), b, e.d((String)paramVarArgs[0]) });
      return;
    }
    catch (Throwable paramVarArgs)
    {
      paramVarArgs.printStackTrace();
      a.execute(new Object[] { Integer.valueOf(0), "error:" + paramVarArgs.getMessage() });
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.o
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */