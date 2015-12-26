import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.entity.a.e;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.util.i;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.XyUtil;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

final class bv
  implements XyCallBack
{
  bv(XyCallBack paramXyCallBack, int paramInt, String paramString1, String paramString2) {}
  
  public final void execute(Object... paramVarArgs)
  {
    String str;
    if ((paramVarArgs != null) && (paramVarArgs[0].toString().equals("0")) && (paramVarArgs.length == 2))
    {
      paramVarArgs = i.b(paramVarArgs[1].toString());
      if (paramVarArgs == null) {
        return;
      }
      paramVarArgs = (JSONObject)paramVarArgs.get(paramVarArgs.keySet().iterator().next());
      str = paramVarArgs.optString("id");
      if ("0".equals(str)) {
        e.a(paramVarArgs);
      }
    }
    for (;;)
    {
      try
      {
        if (a != null) {
          if (b == 0) {
            paramVarArgs = JsonUtil.PubInfoToJson(paramVarArgs);
          }
        }
      }
      catch (Throwable paramVarArgs)
      {
        i = 0;
      }
      try
      {
        XyUtil.doXycallBackResult(a, new Object[] { Integer.valueOf(0), paramVarArgs, c });
        i = 1;
        if ((i != 0) || (a == null)) {
          break;
        }
        XyUtil.doXycallBackResult(a, new Object[] { Integer.valueOf(-1), null, c });
        return;
      }
      catch (Throwable paramVarArgs)
      {
        for (;;)
        {
          i = 1;
        }
        paramVarArgs = null;
        continue;
      }
      if (b == 1)
      {
        paramVarArgs = paramVarArgs.optJSONArray("pubMenuInfolist");
        if ((paramVarArgs == null) || (paramVarArgs.length() <= 0)) {
          break label278;
        }
        paramVarArgs = paramVarArgs.toString();
        XyUtil.doXycallBackResult(a, new Object[] { Integer.valueOf(0), paramVarArgs, c });
        i = 1;
        continue;
        paramVarArgs.printStackTrace();
        continue;
        if ("1".equals(str)) {
          NetUtil.QueryTokenRequest(d);
        }
      }
      i = 0;
    }
  }
}

/* Location:
 * Qualified Name:     bv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */