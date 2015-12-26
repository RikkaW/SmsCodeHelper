package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.log.PrintTestLogUtil;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ParseOnePlusCardManager
{
  public static Map<String, Object> parseMsgForCard(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
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
    if (l.a((byte)8))
    {
      paramContext = ParseManager.a(paramContext, paramString1, paramString2, paramString3, 0L, paramMap);
      if ((paramContext != null) && (ParseBubbleManager.getParseStatu(paramContext) != -1))
      {
        paramContext.put("msgTime", paramMap.get("msgTime"));
        paramContext = DexUtil.handerValueMap(paramContext);
        if (paramContext == null)
        {
          paramString1 = new HashMap();
          paramString1.put("Result", Boolean.valueOf(false));
          return paramString1;
        }
        paramString1 = (JSONObject)paramContext.get("card_content");
        if (paramString1 != null)
        {
          paramString2 = (Integer)paramString1.get("card_type");
          paramString3 = new JSONObject();
          paramString3.put("msgid", paramMap.get("msgid"));
          paramString3.put("phone", paramMap.get("phone"));
          paramString3.put("content", paramMap.get("content"));
          paramString3.put("msgTime", paramMap.get("msgTime"));
          paramString3.put("smsdate", paramMap.get("smsdate"));
          paramString1.put("msgid", paramMap.get("msgid"));
          paramString1.put("smsdate", paramMap.get("smsdate"));
          paramString3.put("result", paramString1.toString());
          PrintTestLogUtil.printTestJsonLog("ParseOnePlusCardManager", paramString3, "resultJson=");
          long l = DuoquUtils.getSdkDoAction().createCard(paramString3, paramString2.intValue(), paramMap);
          if (l > 0L) {}
          for (boolean bool = true;; bool = false)
          {
            paramContext.put("Result", Boolean.valueOf(bool));
            paramContext.put("id", l);
            return paramContext;
          }
        }
      }
    }
    for (;;)
    {
      paramString1 = paramContext;
      if (paramContext != null) {
        break;
      }
      paramContext = new HashMap();
      paramContext.put("Result", Boolean.valueOf(false));
      return paramContext;
      paramContext = null;
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.ParseOnePlusCardManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */