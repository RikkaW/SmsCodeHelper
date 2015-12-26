package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.HashMap;
import java.util.Map;

public class ParseClassifyManager
{
  public static Map<String, Object> parseClassifyMsg(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
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
    paramString1 = ParseManager.a(paramContext, paramString1, paramString2, paramString3, 0L, paramMap);
    paramContext = new HashMap();
    paramContext.put("Result", Boolean.valueOf(false));
    if (paramString1 != null)
    {
      paramString1 = (String)paramString1.get("title_num");
      if ((!StringUtils.isNull(paramString1)) && ((paramString1.startsWith("01")) || (paramString1.startsWith("02")) || (paramString1.startsWith("03")) || (paramString1.startsWith("06")) || (paramString1.startsWith("08")) || (paramString1.startsWith("14")))) {
        paramContext.put("Result", Boolean.valueOf(true));
      }
    }
    return paramContext;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.ParseClassifyManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */