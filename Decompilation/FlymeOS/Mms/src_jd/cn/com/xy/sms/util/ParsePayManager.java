package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.dex.DexUtil;
import java.util.HashMap;
import java.util.Map;

public class ParsePayManager
{
  public static Map<String, Object> parseMsgForPay(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
  {
    if (paramContext == null) {
      throw new Exception(" Context is null.");
    }
    if (paramString1 == null) {
      throw new Exception(" phoneNumber is null.");
    }
    if (paramString3 == null) {
      throw new Exception(" smsContent is null.");
    }
    paramContext = ParseManager.a(paramContext, paramString1, paramString2, paramString3, 0L, paramMap);
    if ((paramContext != null) && (ParseBubbleManager.getParseStatu(paramContext) != -1))
    {
      paramContext = DexUtil.handerPayValueMap(paramContext);
      if (paramContext == null)
      {
        paramContext = new HashMap();
        paramContext.put("Result", Boolean.valueOf(false));
        return paramContext;
      }
      paramContext.put("Result", Boolean.valueOf(true));
      return paramContext;
    }
    paramContext = new HashMap();
    paramContext.put("Result", Boolean.valueOf(false));
    return paramContext;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.ParsePayManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */