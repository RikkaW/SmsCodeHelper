package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.dex.DexUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ParseDateManager
{
  public static Map<String, Object> parseDateMsg(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
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
    paramContext = new Date();
    paramContext = new SimpleDateFormat("yyyy-MM-dd").format(paramContext);
    if (paramString1 != null)
    {
      paramString1 = DexUtil.handerDateValueMap(paramString1);
      if ((paramString1 == null) || (paramString1.isEmpty()))
      {
        paramString1 = new HashMap();
        paramString1.put("Dojuage", Boolean.valueOf(false));
        paramString1.put("Result", Boolean.valueOf(false));
        paramString1.put("date", paramContext + " 09:00");
        return paramString1;
      }
      paramString1.put("Result", Boolean.valueOf(true));
      return paramString1;
    }
    paramString1 = new HashMap();
    paramString1.put("Dojuage", Boolean.valueOf(false));
    paramString1.put("Result", Boolean.valueOf(false));
    paramString1.put("date", paramContext + " 09:00");
    return paramString1;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.ParseDateManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */