package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.log.LogManager;
import java.util.Map;
import org.json.JSONObject;

public class ParseOperatorManager
{
  private static String a = "ParseOperatorManager";
  
  public static JSONObject parseOperatorMsg(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, Map<String, String> paramMap)
  {
    try
    {
      ParseManager.a(paramContext, paramString2, paramString3, paramString4, paramLong, paramMap);
      return null;
    }
    catch (Throwable paramContext)
    {
      LogManager.e("ParseOperatorManager", "queryOperatorCmd " + paramContext.getMessage(), paramContext);
    }
    return null;
  }
  
  public static JSONObject queryOperatorCmd(Context paramContext, String paramString1, String paramString2, Map paramMap, SdkCallBack paramSdkCallBack)
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.ParseOperatorManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */