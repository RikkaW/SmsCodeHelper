package cn.com.xy.sms.util;

import android.content.Context;
import java.util.Map;

public class ParseOnePlusManager
  extends ParseManager
{
  public static boolean isPinSms(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
  {
    return parseSmsType(paramContext, paramString1, paramString2, paramString3, paramMap, 1) == 1;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.ParseOnePlusManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */