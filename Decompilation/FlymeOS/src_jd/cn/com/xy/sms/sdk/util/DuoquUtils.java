package cn.com.xy.sms.sdk.util;

import android.app.Activity;
import android.content.Context;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.net.a;
import cn.com.xy.sms.sdk.queue.g;
import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class DuoquUtils
{
  private static AbsSdkDoAction a = null;
  private static String b = "DuoquUtils";
  public static k logSdkDoAction = null;
  public static AbsSdkDoAction sdkAction = null;
  
  public static boolean doAction(Activity paramActivity, String paramString, Map<String, String> paramMap)
  {
    try
    {
      getSdkDoAction().doAction(paramActivity, paramString, paramMap);
      logAction(paramActivity, paramString, paramMap);
      return true;
    }
    catch (Throwable paramActivity)
    {
      paramActivity.printStackTrace();
    }
    return false;
  }
  
  public static boolean doActionContext(Context paramContext, String paramString, Map<String, String> paramMap)
  {
    try
    {
      getSdkDoAction().doAction(paramContext, paramString, paramMap);
      logAction(paramContext, paramString, paramMap);
      return true;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean doCustomAction(Activity paramActivity, String paramString, HashMap<String, Object> paramHashMap)
  {
    Object localObject;
    boolean bool;
    for (;;)
    {
      try
      {
        if ("call".equalsIgnoreCase(paramString))
        {
          str1 = (String)paramHashMap.get("phone");
          localObject = paramHashMap.get("simIndex");
          if (StringUtils.isNull(str1)) {
            break label620;
          }
          paramString = (String)localObject;
          if (localObject == null) {
            paramString = Integer.valueOf(-1);
          }
          getSdkDoAction().callPhone(paramActivity, str1, Integer.valueOf(paramString.toString()).intValue());
          bool = true;
          if ((!bool) || (paramHashMap == null)) {}
        }
      }
      catch (Throwable paramActivity)
      {
        String str1;
        paramActivity.printStackTrace();
        return false;
      }
      finally
      {
        if (paramHashMap != null) {
          paramHashMap.clear();
        }
      }
      try
      {
        if ((!paramHashMap.containsKey("keepActivity")) && (paramActivity != null) && (!paramActivity.isFinishing())) {
          paramActivity.finish();
        }
      }
      catch (Throwable paramActivity)
      {
        paramActivity.printStackTrace();
      }
    }
    if (paramHashMap != null) {
      paramHashMap.clear();
    }
    return bool;
    if (("open_sms".equalsIgnoreCase(paramString)) || ("open_tongxunlu".equalsIgnoreCase(paramString)) || ("reply_sms_open".equalsIgnoreCase(paramString)))
    {
      localObject = (String)paramHashMap.get("phoneNum");
      if (!StringUtils.isNull((String)localObject)) {
        break label636;
      }
      localObject = (String)paramHashMap.get("phone");
    }
    label620:
    label625:
    label631:
    label636:
    for (;;)
    {
      if (!StringUtils.isNull((String)localObject))
      {
        str1 = (String)paramHashMap.get("msgId");
        if (StringUtils.isNull(str1)) {
          break label631;
        }
        paramString = new HashMap();
        paramString.put("msgId", str1);
      }
      for (;;)
      {
        getSdkDoAction().openSms(paramActivity, (String)localObject, paramString);
        bool = false;
        break;
        new StringBuilder("actionType: ").append(paramString).append(" phoneNum is null");
        bool = false;
        break;
        String str2;
        if ("send_sms".equalsIgnoreCase(paramString))
        {
          str1 = (String)paramHashMap.get("phoneNum");
          str2 = (String)paramHashMap.get("smsCode");
          if ((StringUtils.isNull(str1)) || (StringUtils.isNull(str2))) {
            break label620;
          }
          localObject = paramHashMap.get("simIndex");
          new StringBuilder("actionType: ").append(paramString).append(" phoneNu :").append(str1).append(" simIndex: ").append(localObject);
          if (localObject != null) {
            break label625;
          }
        }
        for (paramString = Integer.valueOf(-1);; paramString = (String)localObject)
        {
          getSdkDoAction().sendSms(paramActivity, str1, str2, Integer.valueOf(paramString.toString()).intValue(), null);
          bool = true;
          break;
          if ("openApp".equalsIgnoreCase(paramString))
          {
            localObject = String.valueOf(paramHashMap.get("appName"));
            paramString = (String)localObject;
            if (StringUtils.isNull((String)localObject)) {
              paramString = String.valueOf(paramHashMap.get("exthend"));
            }
            localObject = String.valueOf(paramHashMap.get("appDownUrl"));
            if (!StringUtils.isNull(paramString))
            {
              getSdkDoAction().openAppByAppName(paramActivity, paramString, (String)localObject);
              bool = false;
              break;
            }
            paramString = String.valueOf(paramHashMap.get("url"));
            if (StringUtils.isNull(paramString)) {
              break label620;
            }
            getSdkDoAction().openAppByUrl(paramActivity, paramString, (String)localObject);
            bool = false;
            break;
          }
          if ("toService".equalsIgnoreCase(paramString))
          {
            localObject = new JSONObject(paramHashMap);
            if (((JSONObject)localObject).has("actionType")) {
              paramString = ((JSONObject)localObject).getString("actionType");
            }
            getSdkDoAction().toService(paramActivity, paramString, (JSONObject)localObject);
            bool = false;
            break;
          }
          if ("download".equalsIgnoreCase(paramString))
          {
            paramString = String.valueOf(paramHashMap.get("appName"));
            localObject = String.valueOf(paramHashMap.get("url"));
            getSdkDoAction().downLoadApp(paramActivity, paramString, (String)localObject, null);
            bool = false;
            break;
            return false;
          }
          bool = false;
          break;
        }
        paramString = null;
      }
    }
  }
  
  public static String getCode(int paramInt1, int paramInt2)
  {
    String str1;
    switch (paramInt1)
    {
    default: 
      str1 = null;
    case 1: 
    case 3: 
    case 4: 
      while (str1 == null)
      {
        return "";
        try
        {
          str1 = d.a(paramInt2);
        }
        catch (Throwable localThrowable)
        {
          localThrowable.printStackTrace();
          return "";
        }
        str1 = Constant.getXCode3(paramInt2);
        continue;
        str1 = ViewUtil.getXCode4(paramInt2);
      }
      str1 = new StringBuilder(String.valueOf("")).append(str1.substring(0, paramInt1 - 1)).toString() + str1.substring(paramInt1, str1.length() - 1);
      return str1;
    }
    if (paramInt2 == 1) {}
    for (String str2 = "5YD15P";; str2 = "3T0CFQ") {
      break;
    }
  }
  
  public static Map<String, Object> getLogMap(Map<String, Object> paramMap)
  {
    HashMap localHashMap = new HashMap();
    if (paramMap != null) {}
    try
    {
      if (paramMap.containsKey("logkey")) {
        localHashMap.put("logkey", paramMap.get("logkey"));
      }
      return localHashMap;
    }
    catch (Throwable paramMap)
    {
      paramMap.printStackTrace();
    }
    return localHashMap;
  }
  
  public static k getLogSdkDoAction()
  {
    if (logSdkDoAction != null) {
      return logSdkDoAction;
    }
    return null;
  }
  
  public static String getPid()
  {
    return a.a(true);
  }
  
  public static AbsSdkDoAction getSdkDoAction()
  {
    if (sdkAction != null) {
      return sdkAction;
    }
    if (a == null) {
      a = new c();
    }
    String str = b;
    return a;
  }
  
  public static String getXid()
  {
    return cn.com.xy.sms.sdk.net.util.i.a();
  }
  
  public static void logAction(Context paramContext, String paramString, Map<String, String> paramMap)
  {
    for (;;)
    {
      try
      {
        paramContext = (String)paramMap.get("action");
        if (!StringUtils.isNull(paramContext))
        {
          i = DexUtil.getActionCode(paramContext);
          paramString = new JSONObject(StringUtils.decode(paramString));
          paramContext = "";
          if (paramString.has("titleNo")) {
            paramContext = paramString.getString("titleNo");
          }
          paramString = paramContext;
          if (StringUtils.isNull(paramContext)) {
            paramString = "00000000";
          }
          if (i != -1) {
            g.a(new cn.com.xy.sms.sdk.queue.i(5, new String[] { "titleNo", paramString, "type", String.valueOf(i) }));
          }
          getSdkDoAction().statisticAction(paramString, i, null);
          return;
        }
      }
      catch (Throwable paramContext)
      {
        return;
      }
      int i = -1;
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.DuoquUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */