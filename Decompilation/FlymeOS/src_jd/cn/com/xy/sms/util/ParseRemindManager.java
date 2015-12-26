package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.sdk.util.PopupUtil;
import java.util.HashMap;
import java.util.Map;

public class ParseRemindManager
{
  public static Map<String, Object> parseRemindMsg(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
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
    if (!l.a((byte)10)) {
      PopupUtil.getResultMap(false, false);
    }
    paramContext = ParseManager.a(paramContext, paramString1, paramString2, paramString3, 0L, paramMap);
    if ((paramContext != null) && (ParseBubbleManager.getParseStatu(paramContext) != -1))
    {
      paramContext = DexUtil.handerRemindValueMap(paramContext);
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
 * Qualified Name:     cn.com.xy.sms.util.ParseRemindManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */