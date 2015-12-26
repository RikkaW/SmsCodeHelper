import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.entity.a.e;
import cn.com.xy.sms.sdk.util.JsonUtil;

final class bt
  implements XyCallBack
{
  bt(boolean paramBoolean, String paramString1, String paramString2, String paramString3, String paramString4, XyCallBack paramXyCallBack, int paramInt, String paramString5) {}
  
  public final void execute(Object... paramVarArgs)
  {
    boolean bool = a;
    String str = b;
    str = c;
    str = d;
    if ((bs.a(bool, e, f, g, paramVarArgs) != null) && (g == 0))
    {
      paramVarArgs = e.a(b, d, Integer.valueOf(h).intValue());
      if (f != null)
      {
        paramVarArgs = JsonUtil.PubInfoToJson(paramVarArgs);
        f.execute(new Object[] { paramVarArgs });
      }
    }
  }
}

/* Location:
 * Qualified Name:     bt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */