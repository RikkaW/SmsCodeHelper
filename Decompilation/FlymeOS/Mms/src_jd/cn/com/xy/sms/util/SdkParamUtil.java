package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import java.util.HashMap;

public class SdkParamUtil
{
  public static String getParamValue(Context paramContext, String paramString)
  {
    return SysParamEntityManager.getStringParam(paramContext, paramString);
  }
  
  public static boolean setParamValue(Context paramContext, String paramString1, String paramString2)
  {
    return setParamValue(paramContext, paramString1, paramString2, null);
  }
  
  public static boolean setParamValue(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    try
    {
      if (SysParamEntityManager.insertOrUpdateKeyValue(paramContext, paramString1, paramString2, paramString3) > 0L) {}
      for (int i = 1;; i = 0)
      {
        if (i != 0) {
          SysParamEntityManager.cacheMap.put(paramString1, paramString2);
        }
        return false;
      }
      return false;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.SdkParamUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */