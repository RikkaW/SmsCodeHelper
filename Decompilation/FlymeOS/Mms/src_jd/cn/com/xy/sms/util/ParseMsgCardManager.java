package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.b.a;
import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.util.PopupUtil;
import cn.com.xy.sms.sdk.util.q;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseMsgCardManager
{
  private static boolean a(Map<String, String> paramMap)
  {
    if (paramMap == null) {}
    while (!paramMap.containsKey("isNeedRes")) {
      return true;
    }
    return "true".equalsIgnoreCase((String)paramMap.get("isNeedRes"));
  }
  
  private static JSONObject b(Map<String, Object> paramMap)
  {
    JSONObject localJSONObject = new JSONObject();
    paramMap = paramMap.entrySet().iterator();
    for (;;)
    {
      if (!paramMap.hasNext())
      {
        MatchCacheManager.removeUselessKey(localJSONObject);
        return localJSONObject;
      }
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      try
      {
        localJSONObject.put((String)localEntry.getKey(), localEntry.getValue());
      }
      catch (JSONException paramMap)
      {
        paramMap.printStackTrace();
      }
    }
    return null;
  }
  
  private static JSONObject c(Map<String, Object> paramMap)
  {
    if (paramMap != null) {}
    for (;;)
    {
      try
      {
        String str = (String)paramMap.get("title_num");
        HashMap localHashMap = a.c(str);
        int i;
        if ((localHashMap != null) && (!localHashMap.isEmpty()))
        {
          i = 1;
          if ((i != 0) && (PopupUtil.isPopupAble(paramMap, str)))
          {
            paramMap.putAll(localHashMap);
            paramMap = b(paramMap);
            return paramMap;
          }
        }
        else
        {
          i = 0;
          continue;
        }
      }
      catch (Throwable paramMap)
      {
        paramMap.printStackTrace();
        return null;
      }
      finally
      {
        q.a();
      }
    }
  }
  
  public static JSONObject parseMsgForCard(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
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
    for (;;)
    {
      try
      {
        paramContext = ParseManager.a(paramContext, paramString1, paramString2, paramString3, 0L, paramMap);
        if ((paramContext == null) || (ParseBubbleManager.getParseStatu(paramContext) == -1)) {
          continue;
        }
        paramString1 = DexUtil.handerBubbleValueMap(paramContext);
        if (paramString1 == null) {
          continue;
        }
        if (!paramString1.isEmpty()) {
          continue;
        }
        return null;
      }
      catch (Throwable paramContext)
      {
        return null;
        return null;
        if (paramMap != null) {
          continue;
        }
        boolean bool = true;
        continue;
      }
      if (bool)
      {
        return c(paramContext);
        if (!paramMap.containsKey("isNeedRes")) {
          bool = true;
        } else {
          bool = "true".equalsIgnoreCase((String)paramMap.get("isNeedRes"));
        }
      }
      else
      {
        paramContext = b(paramString1);
        return paramContext;
      }
    }
    return paramContext;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.ParseMsgCardManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */