package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.dex.DexUtil;
import java.util.Map;
import org.json.JSONObject;

public class ParseWatchManager
{
  public static JSONObject parseMsgForWatch(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
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
    paramString2 = ParseManager.a(paramContext, paramString1, paramString2, paramString3, 0L, paramMap);
    paramString1 = null;
    paramContext = paramString1;
    if (paramString2 != null)
    {
      paramContext = paramString1;
      if (ParseBubbleManager.getParseStatu(paramString2) != -1) {
        paramContext = DexUtil.handerWatchValueMap(paramString2);
      }
    }
    return paramContext;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.ParseWatchManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */