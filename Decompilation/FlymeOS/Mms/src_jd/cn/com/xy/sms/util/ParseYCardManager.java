package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.sdk.util.PopupUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ParseYCardManager
{
  private static String a = "yunyingshang_s_0001";
  private static String b = "1003";
  private static String c = "xiaoyuan";
  
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
    if (!l.a((byte)8)) {
      PopupUtil.getResultMap(false, false);
    }
    paramContext = ParseManager.a(paramContext, paramString1, paramString2, paramString3, 0L, paramMap);
    if (paramContext != null)
    {
      paramContext.put("parseMsgForCard", "true");
      paramString1 = DexUtil.handerValueMap(paramContext);
      paramContext = paramString1;
      if (paramString1 == null)
      {
        paramContext = new HashMap();
        paramContext.put("Result", Boolean.valueOf(false));
      }
      return paramContext;
    }
    paramContext = new HashMap();
    paramContext.put("Result", Boolean.valueOf(false));
    return paramContext;
  }
  
  public static String parseMsgForCardData(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
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
    if (!l.a((byte)8)) {
      PopupUtil.getResultMap(false, false);
    }
    paramString1 = ParseManager.a(paramContext, paramString1, paramString2, paramString3, 0L, paramMap);
    if (paramString1 != null)
    {
      paramContext = DexUtil.handerValueMap(paramString1);
      if (paramContext == null) {
        return null;
      }
      new StringBuilder("res=").append(paramString1.toString());
      new StringBuilder("map=").append(paramContext.toString());
      if (((JSONObject)paramContext.get("content")).toString() != null)
      {
        new StringBuilder("content=").append(((JSONObject)paramContext.get("content")).toString());
        paramString1 = ((JSONObject)paramContext.get("theReturn")).put("content", paramContext.get("content"));
        paramString1.put("title", paramContext.get("title"));
        paramString1.put("card_tag", "xiaoyuan");
        return paramString1.toString();
      }
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.ParseYCardManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */