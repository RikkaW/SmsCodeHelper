package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.sdk.queue.e;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.v;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public class ParseRichBubbleManager
{
  private static long a = 21600000L;
  private static long b = 1296000000L;
  private static ExecutorService c = Executors.newFixedThreadPool(1);
  public static boolean isBusy = false;
  
  /* Error */
  public static void addEffectiveBubbleData(String arg0, String paramString2, JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnonnull +4 -> 5
    //   4: return
    //   5: aload_0
    //   6: invokestatic 44	cn/com/xy/sms/sdk/util/v:a	(Ljava/lang/String;)Lcn/com/xy/sms/sdk/util/v;
    //   9: ifnull -5 -> 4
    //   12: getstatic 48	cn/com/xy/sms/sdk/util/v:g	Ljava/util/Map;
    //   15: astore_0
    //   16: aload_0
    //   17: monitorenter
    //   18: getstatic 48	cn/com/xy/sms/sdk/util/v:g	Ljava/util/Map;
    //   21: aload_1
    //   22: aload_2
    //   23: invokeinterface 54 3 0
    //   28: pop
    //   29: aload_2
    //   30: ldc 56
    //   32: invokevirtual 62	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   35: ifeq +10 -> 45
    //   38: aload_2
    //   39: ldc 56
    //   41: invokevirtual 66	org/json/JSONObject:remove	(Ljava/lang/String;)Ljava/lang/Object;
    //   44: pop
    //   45: aload_0
    //   46: monitorexit
    //   47: getstatic 69	cn/com/xy/sms/sdk/util/v:f	Ljava/util/Map;
    //   50: astore_0
    //   51: aload_0
    //   52: monitorenter
    //   53: getstatic 69	cn/com/xy/sms/sdk/util/v:f	Ljava/util/Map;
    //   56: aload_1
    //   57: invokeinterface 72 2 0
    //   62: pop
    //   63: aload_0
    //   64: monitorexit
    //   65: getstatic 76	cn/com/xy/sms/sdk/util/v:h	Ljava/util/HashSet;
    //   68: astore_0
    //   69: aload_0
    //   70: monitorenter
    //   71: getstatic 76	cn/com/xy/sms/sdk/util/v:h	Ljava/util/HashSet;
    //   74: aload_1
    //   75: invokevirtual 81	java/util/HashSet:remove	(Ljava/lang/Object;)Z
    //   78: pop
    //   79: aload_0
    //   80: monitorexit
    //   81: getstatic 84	cn/com/xy/sms/sdk/util/v:i	Ljava/util/HashSet;
    //   84: astore_0
    //   85: aload_0
    //   86: monitorenter
    //   87: getstatic 84	cn/com/xy/sms/sdk/util/v:i	Ljava/util/HashSet;
    //   90: aload_1
    //   91: invokevirtual 81	java/util/HashSet:remove	(Ljava/lang/Object;)Z
    //   94: pop
    //   95: aload_0
    //   96: monitorexit
    //   97: aload_2
    //   98: ldc 86
    //   100: invokevirtual 66	org/json/JSONObject:remove	(Ljava/lang/String;)Ljava/lang/Object;
    //   103: pop
    //   104: return
    //   105: astore_0
    //   106: aload_0
    //   107: invokevirtual 89	java/lang/Throwable:printStackTrace	()V
    //   110: return
    //   111: astore_3
    //   112: aload_3
    //   113: invokevirtual 89	java/lang/Throwable:printStackTrace	()V
    //   116: goto -71 -> 45
    //   119: astore_1
    //   120: aload_0
    //   121: monitorexit
    //   122: aload_1
    //   123: athrow
    //   124: astore_1
    //   125: aload_0
    //   126: monitorexit
    //   127: aload_1
    //   128: athrow
    //   129: astore_1
    //   130: aload_0
    //   131: monitorexit
    //   132: aload_1
    //   133: athrow
    //   134: astore_1
    //   135: aload_0
    //   136: monitorexit
    //   137: aload_1
    //   138: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	1	paramString2	String
    //   0	139	2	paramJSONObject	JSONObject
    //   111	2	3	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   97	104	105	java/lang/Throwable
    //   29	45	111	java/lang/Throwable
    //   18	29	119	finally
    //   29	45	119	finally
    //   45	47	119	finally
    //   112	116	119	finally
    //   53	65	124	finally
    //   71	81	129	finally
    //   87	97	134	finally
  }
  
  public static void addInvalidBubbleData(String arg0, String paramString2)
  {
    if (paramString2 != null) {}
    while (v.a(???) == null) {
      return;
    }
    synchronized (v.h)
    {
      v.h.add(paramString2);
      synchronized (v.f)
      {
        v.f.remove(paramString2);
      }
    }
    synchronized (v.g)
    {
      v.g.remove(paramString2);
      synchronized (v.i)
      {
        v.i.remove(paramString2);
        return;
      }
      paramString2 = finally;
      throw paramString2;
      paramString2 = finally;
      throw paramString2;
    }
  }
  
  public static void clearCacheBubbleData(String paramString)
  {
    v.c(paramString);
    cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage.emptyObj = null;
  }
  
  public static void deleteBubbleDataFromCache(String paramString1, String paramString2)
  {
    try
    {
      if (StringUtils.isNull(paramString1)) {
        v.e(paramString2);
      }
      paramString1 = v.a(paramString1);
      if (paramString1 != null) {
        paramString1.d(paramString2);
      }
      return;
    }
    catch (Throwable paramString1) {}
  }
  
  public static void loadBubbleDataByPhoneNum(String paramString)
  {
    v.b(paramString);
    synchronized (v.g)
    {
      clearCacheBubbleData(paramString);
      paramString = StringUtils.getPhoneNumberNo86(paramString);
      v.b(paramString, MatchCacheManager.loadDataByParam("phoneNum=?", new String[] { paramString }));
      return;
    }
  }
  
  public static Map<String, Object> parseMsgToSimpleBubbleResult(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, byte paramByte, Map<String, String> paramMap)
  {
    if (!l.a()) {
      return null;
    }
    paramString3 = ParseManager.parseMsgToBubble(paramContext, paramString2, paramString3, paramString4, paramMap);
    if (paramByte == 1)
    {
      long l = 0L;
      paramString4 = MatchCacheManager.getMD5(paramString2, paramString4);
      if (!StringUtils.isNull(paramString4))
      {
        paramString2 = StringUtils.getPhoneNumberNo86(paramString2);
        if (paramString3 == null)
        {
          paramContext = "";
          l = MatchCacheManager.insertOrUpdate(BaseManager.getContentValues(null, new String[] { "msg_num_md5", paramString4, "phonenum", paramString2, "msg_id", paramString1, "session_reuslt", paramContext, "save_time", String.valueOf(System.currentTimeMillis()), "session_lasttime", String.valueOf(System.currentTimeMillis()) }), 1);
        }
      }
      else
      {
        if (paramString3 == null) {
          break label212;
        }
        paramContext = new HashMap();
        paramContext.put("CACHE_SDK_MSG_ID", Long.valueOf(l));
        paramContext.put("CACHE_SDK_MSG_RESULT", paramString3);
      }
    }
    for (;;)
    {
      return paramContext;
      paramContext = paramString3;
      break;
      if (paramString3 != null)
      {
        paramContext = new HashMap();
        paramContext.put("CACHE_SDK_MSG_RESULT", paramString3);
      }
      else
      {
        label212:
        paramContext = null;
      }
    }
  }
  
  /* Error */
  public static void pubBubbleData(String paramString, Map<String, JSONObject> paramMap, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 119	cn/com/xy/sms/sdk/util/v:b	(Ljava/lang/String;)Lcn/com/xy/sms/sdk/util/v;
    //   4: pop
    //   5: getstatic 69	cn/com/xy/sms/sdk/util/v:f	Ljava/util/Map;
    //   8: astore_0
    //   9: aload_0
    //   10: monitorenter
    //   11: iload_2
    //   12: ifeq +6 -> 18
    //   15: invokestatic 205	cn/com/xy/sms/sdk/util/v:a	()V
    //   18: aload_1
    //   19: ifnull +42 -> 61
    //   22: getstatic 69	cn/com/xy/sms/sdk/util/v:f	Ljava/util/Map;
    //   25: aload_1
    //   26: invokeinterface 209 2 0
    //   31: ldc -45
    //   33: new 213	java/lang/StringBuilder
    //   36: dup
    //   37: ldc -41
    //   39: invokespecial 217	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   42: aload_1
    //   43: invokeinterface 221 1 0
    //   48: invokevirtual 225	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   51: invokevirtual 229	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   54: aconst_null
    //   55: invokestatic 234	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   58: aload_0
    //   59: monitorexit
    //   60: return
    //   61: ldc -45
    //   63: ldc -20
    //   65: aconst_null
    //   66: invokestatic 234	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   69: goto -11 -> 58
    //   72: astore_1
    //   73: aload_0
    //   74: monitorexit
    //   75: aload_1
    //   76: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	77	0	paramString	String
    //   0	77	1	paramMap	Map<String, JSONObject>
    //   0	77	2	paramBoolean	boolean
    // Exception table:
    //   from	to	target	type
    //   15	18	72	finally
    //   22	58	72	finally
    //   58	60	72	finally
    //   61	69	72	finally
  }
  
  public static JSONObject queryBubbleDataFromApi(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, HashMap<String, Object> paramHashMap)
  {
    paramString1 = ParseSmsToBubbleUtil.parseSmsToBubbleResult(paramString1, paramString2, paramString3, paramString4, paramLong, 3, true, true, paramHashMap);
    if (paramString1 != null) {
      return (JSONObject)paramString1.get("BUBBLE_JSON_RESULT");
    }
    return null;
  }
  
  public static JSONObject queryBubbleDataFromDb(String paramString1, String paramString2, String paramString3, long paramLong)
  {
    paramString1 = MatchCacheManager.getDataByParam(paramString1);
    if (paramString1 != null)
    {
      String str = (String)JsonUtil.getValueFromJsonObject(paramString1, "msg_num_md5");
      paramString2 = MatchCacheManager.getMD5(paramString2, paramString3);
      if ((str != null) && (str.equals(paramString2))) {
        return paramString1;
      }
    }
    return null;
  }
  
  public static JSONObject queryDataByMsgItem(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, long paramLong)
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString3 == null)) {
      throw new Exception("msgid or phoneNum or smsContent is null but they need value.");
    }
    if (StringUtils.isPhoneNumber(paramString2)) {
      localObject1 = null;
    }
    Object localObject2;
    do
    {
      return (JSONObject)localObject1;
      v.b(paramString2);
      localObject1 = v.g;
      localObject2 = (JSONObject)v.g.get(paramString1);
      localObject1 = localObject2;
    } while (localObject2 != null);
    if ((localObject2 == null) && (v.h.contains(paramString1))) {
      return null;
    }
    Object localObject1 = localObject2;
    int i;
    if (localObject2 == null)
    {
      localObject1 = v.f;
      localObject2 = (JSONObject)v.f.get(paramString1);
      localObject1 = localObject2;
      if (localObject2 != null)
      {
        localObject1 = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject2, "msg_num_md5");
        String str = MatchCacheManager.getMD5(paramString2, paramString3);
        if ((str != null) && (localObject1 != null) && (str.equals(localObject1)))
        {
          if (paramInt != 2) {
            break label373;
          }
          localObject1 = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject2, "bubble_result");
          try
          {
            l = Long.valueOf(JsonUtil.getValueFromJsonObject((JSONObject)localObject2, "bubble_lasttime").toString()).longValue();
            if (StringUtils.isNull((String)localObject1)) {
              break label280;
            }
            if (System.currentTimeMillis() - l >= DexUtil.getUpdateCycleByType(22, b)) {
              break label373;
            }
            localObject1 = new JSONObject((String)localObject1);
            v.g.put(paramString1, localObject1);
            i = 1;
          }
          catch (Throwable localThrowable)
          {
            for (;;)
            {
              long l;
              localThrowable.printStackTrace();
              v.h.add(paramString1);
              i = 2;
            }
          }
          v.f.remove(paramString1);
        }
      }
    }
    for (;;)
    {
      if (i != 1)
      {
        if (v.i.contains(paramString1))
        {
          return null;
          label280:
          if (System.currentTimeMillis() - l >= DexUtil.getUpdateCycleByType(23, a)) {
            break label373;
          }
          v.h.add(paramString1);
          i = 2;
          break;
          i = 3;
          v.h.remove(paramString1);
          break;
        }
        v.i.add(paramString1);
        e.a(i, paramString1, paramString2, paramString3, paramString4, paramInt, paramLong, (JSONObject)localObject2);
      }
      return (JSONObject)localObject2;
      label373:
      i = 0;
      break;
      i = 0;
      localObject2 = localThrowable;
    }
  }
  
  public static void queryDataByMsgItem(String paramString1, String paramString2, String paramString3, long paramLong, String paramString4, int paramInt, SdkCallBack paramSdkCallBack, boolean paramBoolean, HashMap<String, Object> paramHashMap)
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString3 == null)) {
      throw new Exception("msgid or phoneNum or smsContent is null but they need value.");
    }
    if (StringUtils.isPhoneNumber(paramString2))
    {
      XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { Integer.valueOf(-1), "phonenum is null", paramString1 });
      return;
    }
    v.b(paramString2);
    Object localObject = v.g;
    localObject = (JSONObject)v.g.get(paramString1);
    if (localObject != null)
    {
      XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { Integer.valueOf(0), localObject, paramString1 });
      return;
    }
    if ((localObject == null) && (v.h.contains(paramString1)))
    {
      XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { Integer.valueOf(-1), paramString1 + " invalidBubbleData ", paramString1 });
      return;
    }
    int j = 0;
    int i = j;
    if (localObject == null)
    {
      localObject = v.f;
      localObject = (JSONObject)v.f.get(paramString1);
      i = j;
      if (localObject != null)
      {
        i = j;
        if (!((JSONObject)localObject).has("need_parse_bubble"))
        {
          String str1 = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject, "msg_num_md5");
          String str2 = MatchCacheManager.getMD5(paramString2, paramString3);
          if ((str2 == null) || (str1 == null) || (!str2.equals(str1))) {
            break label421;
          }
          if (paramInt != 2) {
            break label557;
          }
          try
          {
            localObject = (JSONObject)JsonUtil.getValueFromJsonObject((JSONObject)localObject, "bubble_result");
            if (localObject != null)
            {
              v.g.put(paramString1, localObject);
              XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { Integer.valueOf(0), localObject, paramString1 });
              return;
            }
          }
          catch (Throwable localThrowable)
          {
            localThrowable.printStackTrace();
            v.h.add(paramString1);
            paramInt = 2;
          }
        }
      }
    }
    for (;;)
    {
      v.f.remove(paramString1);
      i = paramInt;
      if ((i == 0) || (i == 3))
      {
        if (v.i.contains(paramString1))
        {
          XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { Integer.valueOf(-1), " inQueueBubbleData2 ", paramString1 });
          return;
          v.h.add(paramString1);
          paramInt = 2;
          continue;
          label421:
          paramInt = 3;
        }
        else
        {
          if (!paramBoolean)
          {
            XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { Integer.valueOf(-2), " need parse ", paramString1 });
            c.execute(new p(paramString1, i, paramString2, paramString3, paramLong, paramSdkCallBack, paramString4, paramHashMap));
            return;
          }
          XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { Integer.valueOf(-4), " is scrolling", paramString1 });
        }
      }
      else
      {
        XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { Integer.valueOf(-1), " invalidBubbleData dataStatu: " + i, paramString1 });
        return;
        label557:
        paramInt = 3;
      }
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.ParseRichBubbleManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */