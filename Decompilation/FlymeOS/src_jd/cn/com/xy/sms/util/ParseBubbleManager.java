package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.sdk.queue.BubbleTaskQueue;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.v;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONObject;

public class ParseBubbleManager
{
  private static ExecutorService a = Executors.newFixedThreadPool(1);
  private static String b = null;
  private static ExecutorService c = Executors.newFixedThreadPool(1);
  
  private static void a(String paramString1, String paramString2, String paramString3, long paramLong, String paramString4)
  {
    c.execute(new c(paramString2, paramString3, paramString1, paramString4, paramLong));
  }
  
  private static void a(boolean paramBoolean, String paramString)
  {
    v.c(paramString);
    if (paramBoolean) {
      ParseSmsToBubbleUtil.beforeHandParseReceiveSms(200, 3);
    }
    b = null;
  }
  
  public static void addEffectiveBubbleData(String paramString1, String paramString2, JSONArray arg2)
  {
    if ((paramString2 == null) || (??? == null)) {}
    do
    {
      return;
      paramString1 = v.a(paramString1);
    } while (paramString1 == null);
    synchronized (c)
    {
      c.put(paramString2, ???);
      synchronized (a)
      {
        a.remove(paramString2);
      }
    }
    synchronized (d)
    {
      d.remove(paramString2);
      synchronized (e)
      {
        e.remove(paramString2);
        return;
      }
      paramString1 = finally;
      throw paramString1;
      paramString1 = finally;
      throw paramString1;
    }
  }
  
  public static void addInvalidBubbleData(String paramString1, String paramString2)
  {
    if (paramString2 == null) {}
    do
    {
      return;
      paramString1 = v.a(paramString1);
    } while (paramString1 == null);
    synchronized (d)
    {
      d.add(paramString2);
      synchronized (a)
      {
        a.remove(paramString2);
      }
    }
    synchronized (e)
    {
      e.remove(paramString2);
      synchronized (c)
      {
        c.remove(paramString2);
        return;
      }
      paramString1 = finally;
      throw paramString1;
      paramString1 = finally;
      throw paramString1;
    }
  }
  
  private static JSONArray b(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    try
    {
      paramString1 = parseMsgToSimpleBubbleResultKuai(Constant.getContext(), paramString1, paramString2, paramString3, paramString4, paramLong, (byte)1, null);
      if (paramString1 != null)
      {
        paramString1 = new JSONArray(paramString1);
        return paramString1;
      }
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }
  
  public static void clearAllCache(String paramString)
  {
    a(true, paramString);
  }
  
  public static void clearCacheBubbleData(String paramString)
  {
    a(false, paramString);
  }
  
  public static int deleteBubbleData(String paramString)
  {
    try
    {
      v localv = v.a(paramString);
      if (localv != null) {
        localv.b();
      }
      int i = MatchCacheManager.deleteDataByPhoneNum(paramString);
      return i;
    }
    catch (Throwable paramString) {}
    return -1;
  }
  
  public static void deleteBubbleData(Set<Integer> paramSet)
  {
    if (paramSet != null) {}
    try
    {
      if (!paramSet.isEmpty()) {
        IccidLocationUtil.iccidPool.execute(new d(paramSet));
      }
      return;
    }
    catch (Throwable paramSet)
    {
      paramSet.printStackTrace();
    }
  }
  
  public static boolean deleteBubbleData(String paramString1, String paramString2, String paramString3)
  {
    String str = null;
    if (paramString2 != null) {
      str = MatchCacheManager.getMD5(paramString2, paramString3);
    }
    deleteBubbleDataFromCache(paramString2, paramString1);
    ParseRichBubbleManager.deleteBubbleDataFromCache(paramString2, paramString1);
    return MatchCacheManager.deleteBubbleData(paramString1, str);
  }
  
  public static void deleteBubbleDataFromCache(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = v.a(paramString1);
      if (paramString1 == null) {
        return;
      }
      a.remove(paramString2);
      c.remove(paramString2);
      d.remove(paramString2);
      e.remove(paramString2);
      return;
    }
    catch (Throwable paramString1) {}
  }
  
  public static boolean equalPhoneNum(String paramString)
  {
    if ((paramString == null) || (b == null)) {
      return false;
    }
    paramString = StringUtils.getPhoneNumberNo86(paramString);
    return b.equals(paramString);
  }
  
  public static int getParseStatu(Map<String, Object> paramMap)
  {
    if (paramMap != null)
    {
      paramMap = (Integer)paramMap.get("parseStatu");
      if ((paramMap != null) && (paramMap.intValue() == -1)) {
        return -1;
      }
    }
    return 0;
  }
  
  public static void loadBubbleDataByPhoneNum(String paramString, boolean paramBoolean)
  {
    a.a();
    paramString = StringUtils.getPhoneNumberNo86(paramString);
    String[] arrayOfString = new String[1];
    arrayOfString[0] = paramString;
    v localv = v.b(paramString);
    Map localMap2;
    synchronized (c)
    {
      clearCacheBubbleData(paramString);
      b = paramString;
      localMap2 = MatchCacheManager.loadDataByParam("phoneNum=?", arrayOfString, "save_time desc", "15");
      if (localMap2 == null) {}
    }
    synchronized (a)
    {
      a.putAll(localMap2);
      if (paramBoolean) {
        ParseRichBubbleManager.pubBubbleData(paramString, localMap2, true);
      }
      loadBubbleDataByPhoneNumSecond("phoneNum=?", arrayOfString, paramBoolean, localv, paramString);
      ParseSmsToBubbleUtil.beforeHandParseReceiveSms(paramString, 50, 3, true);
      return;
      paramString = finally;
      throw paramString;
    }
  }
  
  /* Error */
  public static void loadBubbleDataByPhoneNumSecond(String arg0, String[] paramArrayOfString, boolean paramBoolean, v paramv, String paramString2)
  {
    // Byte code:
    //   0: ldc2_w 220
    //   3: invokestatic 227	java/lang/Thread:sleep	(J)V
    //   6: aload_0
    //   7: aload_1
    //   8: ldc -60
    //   10: ldc -27
    //   12: invokestatic 202	cn/com/xy/sms/sdk/db/entity/MatchCacheManager:loadDataByParam	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/util/Map;
    //   15: astore_1
    //   16: aload_1
    //   17: ifnull +22 -> 39
    //   20: aload_3
    //   21: getfield 70	cn/com/xy/sms/sdk/util/v:a	Ljava/util/Map;
    //   24: astore_0
    //   25: aload_0
    //   26: monitorenter
    //   27: aload_3
    //   28: getfield 70	cn/com/xy/sms/sdk/util/v:a	Ljava/util/Map;
    //   31: aload_1
    //   32: invokeinterface 206 2 0
    //   37: aload_0
    //   38: monitorexit
    //   39: iload_2
    //   40: ifeq +10 -> 50
    //   43: aload 4
    //   45: aload_1
    //   46: iconst_0
    //   47: invokestatic 210	cn/com/xy/sms/util/ParseRichBubbleManager:pubBubbleData	(Ljava/lang/String;Ljava/util/Map;Z)V
    //   50: return
    //   51: astore 5
    //   53: aload 5
    //   55: invokevirtual 230	java/lang/InterruptedException:printStackTrace	()V
    //   58: goto -52 -> 6
    //   61: astore_1
    //   62: aload_0
    //   63: monitorexit
    //   64: aload_1
    //   65: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	1	paramArrayOfString	String[]
    //   0	66	2	paramBoolean	boolean
    //   0	66	3	paramv	v
    //   0	66	4	paramString2	String
    //   51	3	5	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   0	6	51	java/lang/InterruptedException
    //   27	39	61	finally
  }
  
  public static String parseMsgToSimpleBubbleResultKuai(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, byte paramByte, Map<String, String> paramMap)
  {
    if (!l.a()) {
      return null;
    }
    paramContext = ParseManager.parseMsgToBubble(paramContext, paramString2, paramString3, paramString4, paramMap);
    c.execute(new c(paramString2, paramString4, paramString1, paramContext, paramLong));
    return paramContext;
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
    v localv;
    do
    {
      return (JSONObject)localObject1;
      localObject2 = null;
      localv = v.b(paramString2);
      if (b == null) {
        break;
      }
      localObject2 = (JSONObject)b.get(paramString1);
      localObject1 = localObject2;
    } while (localObject2 != null);
    if ((localObject2 == null) && (d.contains(paramString1))) {
      return null;
    }
    Object localObject1 = localObject2;
    int i;
    if (localObject2 == null)
    {
      localObject1 = localObject2;
      if (a != null)
      {
        localObject2 = (JSONObject)a.get(paramString1);
        localObject1 = localObject2;
        if (localObject2 != null)
        {
          localObject1 = localObject2;
          if (!((JSONObject)localObject2).has("need_parse_simple"))
          {
            localObject1 = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject2, "msg_num_md5");
            String str = MatchCacheManager.getMD5(paramString2, paramString3);
            if ((str != null) && (localObject1 != null) && (str.equals(localObject1)))
            {
              if (paramInt != 1) {
                break label364;
              }
              localObject1 = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject2, "session_reuslt");
              try
              {
                if (StringUtils.isNull((String)localObject1)) {
                  break label291;
                }
                ((JSONObject)localObject2).put("session_reuslt", new JSONArray((String)localObject1));
                b.put(paramString1, localObject2);
                i = 1;
              }
              catch (Throwable localThrowable)
              {
                for (;;)
                {
                  localThrowable.printStackTrace();
                  d.add(paramString1);
                  i = 2;
                }
              }
              a.remove(paramString1);
            }
          }
        }
      }
    }
    for (;;)
    {
      if (i != 1)
      {
        if (e.contains(paramString1))
        {
          return null;
          label291:
          d.add(paramString1);
          i = 2;
          break;
          i = 3;
          break;
        }
        e.add(paramString1);
        BubbleTaskQueue.addDataToQueue(i, paramString1, paramString2, paramString3, paramString4, paramLong, paramInt, (JSONObject)localObject2);
      }
      return (JSONObject)localObject2;
      label364:
      i = 0;
      break;
      i = 0;
      localObject2 = localThrowable;
    }
  }
  
  public static void queryDataByMsgItem(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, long paramLong, SdkCallBack paramSdkCallBack, boolean paramBoolean)
  {
    if ((paramString1 == null) || (paramString2 == null) || (StringUtils.isNull(paramString3)))
    {
      XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { Integer.valueOf(-1), "phonenum is null", paramString1 });
      return;
    }
    if (StringUtils.isPhoneNumber(paramString2))
    {
      XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { Integer.valueOf(-1), "phonenum is null", paramString1 });
      return;
    }
    Object localObject1 = null;
    v localv = v.b(paramString2);
    Object localObject2;
    if (c != null)
    {
      localObject2 = (JSONArray)c.get(paramString1);
      localObject1 = localObject2;
      if (localObject2 != null)
      {
        XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { Integer.valueOf(0), localObject2, paramString1 });
        return;
      }
    }
    if ((localObject1 == null) && (d.contains(paramString1)))
    {
      XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { Integer.valueOf(-1), " invalidBubbleData ", paramString1 });
      return;
    }
    int i;
    if ((localObject1 == null) && (a != null))
    {
      localObject1 = (JSONObject)a.get(paramString1);
      if ((localObject1 != null) && (!((JSONObject)localObject1).has("need_parse_simple")))
      {
        localObject2 = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject1, "msg_num_md5");
        String str = MatchCacheManager.getMD5(paramString2, paramString3);
        if ((str != null) && (localObject2 != null) && (str.equals(localObject2)))
        {
          try
          {
            localObject1 = (JSONArray)JsonUtil.getValueFromJsonObject((JSONObject)localObject1, "session_reuslt");
            if (localObject1 == null) {
              break label413;
            }
            c.put(paramString1, localObject1);
            XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { Integer.valueOf(0), localObject1, paramString1 });
            return;
          }
          catch (Throwable localThrowable)
          {
            d.add(paramString1);
            i = 2;
            localThrowable.printStackTrace();
          }
          a.remove(paramString1);
        }
      }
    }
    for (;;)
    {
      if ((i == 0) || (i == 3))
      {
        if (e.contains(paramString1))
        {
          XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { Integer.valueOf(-1), " inQueueBubbleData2 ", paramString1 });
          return;
          label413:
          d.add(paramString1);
          i = 2;
          break;
          i = 3;
          break;
        }
        if (!paramBoolean)
        {
          XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { Integer.valueOf(-2), " need parse", paramString1 });
          a.execute(new b(localv, paramString1, paramString2, paramString3, paramLong, paramSdkCallBack, paramString4, paramInt));
          return;
        }
        XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { Integer.valueOf(-4), " is scrolling", paramString1 });
        return;
      }
      XyUtil.doXycallBackResult(paramSdkCallBack, new Object[] { Integer.valueOf(-1), " invalidBubbleData ", paramString1 });
      return;
      i = 0;
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.ParseBubbleManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */