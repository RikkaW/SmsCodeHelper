package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.PhoneSmsParseManager;
import cn.com.xy.sms.sdk.util.PopupUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import java.util.HashMap;
import java.util.Map;

public class ParseSmsToBubbleUtil
{
  public static final byte RETURN_CACHE_SDK_MSG_ID = 1;
  public static final byte RETURN_CACHE_SDK_MSG_VALUE = 2;
  
  private static Map<String, Object> a(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, Map<String, Object> paramMap, boolean paramBoolean)
  {
    try
    {
      paramString1 = PopupUtil.parseMsgToSimpleBubbleResult(Constant.getContext(), paramString1, paramString2, paramString4, paramString3, paramLong, (byte)1, paramMap, paramBoolean);
      return paramString1;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  private static Map<String, Object> b(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, int paramInt, boolean paramBoolean1, boolean paramBoolean2, Map<String, Object> paramMap)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    if (paramMap != null) {}
    switch (paramInt)
    {
    default: 
      paramString1 = null;
      localObject1 = paramString1;
      label45:
      paramString1 = null;
      if (localObject1 != null) {
        paramString1 = (String)((Map)localObject1).get("View_fdes");
      }
      if (paramBoolean1) {
        paramLong = 0L;
      }
      break;
    }
    for (;;)
    {
      PhoneSmsParseManager.addInsertQueue(paramString2, paramLong, paramString1, null, null);
      return (Map<String, Object>)localObject1;
      try
      {
        localObject1 = a(paramString1, paramString2, paramString3, paramString4, paramLong, paramMap, paramBoolean2);
      }
      catch (Throwable paramString1)
      {
        Map localMap;
        paramString1.printStackTrace();
        localObject1 = localObject2;
      }
      localObject1 = b(paramString1, paramString2, paramString3, paramString4, paramLong, paramMap, paramBoolean2);
      break label45;
      localMap = a(paramString1, paramString2, paramString3, paramString4, paramLong, paramMap, paramBoolean2);
      localObject2 = localMap;
      paramString1 = b(paramString1, paramString2, paramString3, paramString4, paramLong, paramMap, paramBoolean2);
      if (localMap == null)
      {
        localObject1 = paramString1;
        break label45;
      }
      localObject1 = localMap;
      if (localMap == null) {
        break label45;
      }
      localObject1 = localMap;
      if (paramString1 == null) {
        break label45;
      }
      localObject1 = localMap;
      localObject2 = localMap;
      if (paramString1.isEmpty()) {
        break label45;
      }
      localObject2 = localMap;
      localMap.putAll(paramString1);
      paramString1 = localMap;
      break;
      break label45;
    }
  }
  
  private static Map<String, Object> b(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, Map<String, Object> paramMap, boolean paramBoolean)
  {
    try
    {
      paramString1 = PopupUtil.parseMsgToBubbleCardResult(Constant.getContext(), paramString1, paramString2, paramString4, paramString3, paramLong, (byte)1, paramMap, paramBoolean);
      return paramString1;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  protected static void backGroundHandleMapByType(Map<String, String> paramMap, Map<String, Object> paramMap1)
  {
    if (paramMap == null) {}
    do
    {
      return;
      paramMap = (String)paramMap.get("handle_type");
    } while (StringUtils.isNull(paramMap));
    new r(paramMap, paramMap1).start();
  }
  
  protected static void backGroundParseSmsBubble(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, boolean paramBoolean1, boolean paramBoolean2, Map<String, Object> paramMap)
  {
    new q(paramString1, paramString2, paramString3, paramString4, paramLong, paramBoolean1, paramBoolean2, paramMap).start();
  }
  
  public static void beforeHandParseReceiveSms(int paramInt1, int paramInt2)
  {
    beforeHandParseReceiveSms(null, paramInt1, paramInt2, false);
  }
  
  public static void beforeHandParseReceiveSms(String paramString, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (StringUtils.isNull(paramString)) {}
    for (boolean bool = true;; bool = false)
    {
      a.a(bool, paramString, paramInt1, paramInt2, paramBoolean);
      return;
    }
  }
  
  public static Map<String, Object> parseSmsToBubbleResult(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, int paramInt, boolean paramBoolean1, boolean paramBoolean2, HashMap<String, Object> paramHashMap)
  {
    return parseSmsToBubbleResultMap(paramString1, paramString2, paramString3, paramString4, paramLong, paramInt, paramBoolean1, paramBoolean2, XyUtil.changeObjMapToStrMap(paramHashMap));
  }
  
  public static Map<String, Object> parseSmsToBubbleResultMap(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, int paramInt, boolean paramBoolean1, boolean paramBoolean2, Map<String, String> paramMap)
  {
    label89:
    do
    {
      try
      {
        paramMap = ParseManager.a(Constant.getContext(), paramString2, paramString4, paramString3, paramLong, paramMap);
        if (ParseBubbleManager.getParseStatu(paramMap) != -1) {
          break label89;
        }
        return null;
      }
      catch (Throwable paramString1)
      {
        HashMap localHashMap;
        paramString1.printStackTrace();
        return null;
      }
      localHashMap = new HashMap();
      localHashMap.putAll(paramMap);
      backGroundParseSmsBubble(paramString1, paramString2, paramString3, paramString4, paramLong, paramBoolean1, paramBoolean2, paramMap);
      return localHashMap;
      while (paramInt != 4)
      {
        paramString1 = b(paramString1, paramString2, paramString3, paramString4, paramLong, paramInt, paramBoolean1, paramBoolean2, paramMap);
        return paramString1;
      }
    } while (paramMap != null);
    return null;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.ParseSmsToBubbleUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */