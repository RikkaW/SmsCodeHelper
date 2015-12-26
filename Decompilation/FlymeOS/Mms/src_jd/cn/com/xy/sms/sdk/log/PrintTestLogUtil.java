package cn.com.xy.sms.sdk.log;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class PrintTestLogUtil
{
  public static void printTestJsonLog(String paramString1, JSONObject paramJSONObject, String paramString2)
  {
    if (LogManager.debug)
    {
      if (paramJSONObject != null) {
        paramString1 = paramJSONObject.keys();
      }
    }
    else {
      for (;;)
      {
        if (!paramString1.hasNext()) {
          return;
        }
        Object localObject = paramString1.next();
        try
        {
          new StringBuilder("desc:").append(paramString2).append("   key =").append(localObject).append(" value =").append(paramJSONObject.get(localObject.toString()));
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
        }
      }
    }
    new StringBuilder("desc:").append(paramString2).append("valueMap is null");
  }
  
  public static void printTestLog(String paramString1, String paramString2) {}
  
  public static void printTestLog(String paramString1, String paramString2, String paramString3)
  {
    if (LogManager.debug) {
      new StringBuilder("desc:").append(paramString3).append(" log: ").append(paramString2);
    }
  }
  
  public static void printTestLog(String paramString1, Map<String, Object> paramMap, String paramString2)
  {
    if (LogManager.debug)
    {
      if ((paramMap != null) && (!paramMap.isEmpty())) {
        paramString1 = paramMap.entrySet().iterator();
      }
    }
    else {
      for (;;)
      {
        if (!paramString1.hasNext()) {
          return;
        }
        paramMap = (Map.Entry)paramString1.next();
        new StringBuilder("desc:").append(paramString2).append("   key =").append((String)paramMap.getKey()).append(" value =").append(paramMap.getValue());
      }
    }
    new StringBuilder("desc:").append(paramString2).append("valueMap is null");
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.log.PrintTestLogUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */