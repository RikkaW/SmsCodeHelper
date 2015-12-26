package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.dex.DexUtil;
import java.util.HashMap;
import java.util.Map;

public class ParseNotificationManager
{
  private static Map<String, Object> a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, HashMap<String, String> paramHashMap)
  {
    if (paramContext == null) {
      throw new Exception(" Context is null.");
    }
    if (paramString2 == null) {
      throw new Exception(" phoneNumber is null.");
    }
    if (paramString4 == null) {
      throw new Exception(" smsContent is null.");
    }
    return ParseSmsToBubbleUtil.parseSmsToBubbleResultMap(paramString1, paramString2, paramString4, paramString3, paramLong, 4, true, true, paramHashMap);
  }
  
  public static Map<String, Object> parseNotificationMsg(Context paramContext, long paramLong, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
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
      paramContext = DexUtil.handerNotificationValueMap(paramContext);
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
  
  public static Map<String, Object> parseNotificationMsg(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, HashMap<String, String> paramHashMap)
  {
    paramContext = a(paramContext, paramString1, paramString2, paramString3, paramString4, paramLong, paramHashMap);
    if (paramContext != null)
    {
      ParseSmsToBubbleUtil.backGroundHandleMapByType(paramHashMap, paramContext);
      return DexUtil.handerNotificationValueMap(paramContext);
    }
    return null;
  }
  
  public static Map<String, Object> parseNotificationMsgAndPopupWindow(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, HashMap<String, String> paramHashMap)
  {
    paramString1 = a(paramContext, paramString1, paramString2, paramString3, paramString4, paramLong, paramHashMap);
    if (paramString1 != null)
    {
      ParseManager.parseMsgToPopupWindow(paramContext, paramString2, paramString3, paramString4, paramString1, false, paramHashMap);
      return DexUtil.handerNotificationValueMap(paramString1);
    }
    return null;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.ParseNotificationManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */