package cn.com.xy.sms.sdk.queue;

import android.os.Process;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.n;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.util.i;
import cn.com.xy.sms.sdk.queue.a.a;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public final class c
  extends Thread
{
  private static boolean a = false;
  
  public static void a()
  {
    try
    {
      if ((!a) && (NetUtil.checkAccessNetWork(2))) {
        new c().start();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private static void a(int paramInt)
  {
    for (;;)
    {
      try
      {
        if (!NetUtil.checkAccessNetWork(2)) {
          return;
        }
        JSONArray localJSONArray = n.a(1);
        new StringBuilder("JsonArray=").append(localJSONArray);
        if ((localJSONArray != null) && (localJSONArray.length() > 0))
        {
          int j = localJSONArray.length();
          paramInt = 0;
          if (paramInt < j)
          {
            Object localObject1 = localJSONArray.getJSONObject(paramInt);
            String str = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject1, "res_url");
            Object localObject2 = (Integer)JsonUtil.getValueFromJsonObject((JSONObject)localObject1, "res_version");
            Long localLong = (Long)JsonUtil.getValueFromJsonObject((JSONObject)localObject1, "down_failed_time");
            localObject1 = (Integer)JsonUtil.getValueFromJsonObject((JSONObject)localObject1, "id");
            new StringBuilder(" res_url=").append(str).append(" res_version=").append(localObject2).append(" down_failed_time=").append(localLong).append("id = ").append(localObject1);
            if (StringUtils.isNull(str)) {
              break label308;
            }
            long l1 = System.currentTimeMillis();
            localObject2 = "1" + "_" + localObject2 + "_" + l1 + ".zip";
            long l2 = localLong.longValue();
            long l3 = DexUtil.getUpdateCycleByType(17, 3600000L);
            if (l1 > l2 + l3) {
              try
              {
                i = cn.com.xy.sms.sdk.util.d.f(str, Constant.getPath("duoqu_temp"), (String)localObject2);
                if (i == 0) {
                  n.a((Integer)localObject1, true, (String)localObject2);
                }
              }
              catch (Throwable localThrowable2)
              {
                localThrowable2.printStackTrace();
                new StringBuilder("Throwable=").append(localThrowable2.getLocalizedMessage());
                int i = -1;
                continue;
                n.a((Integer)localObject1, false, (String)localObject2);
                return;
              }
            }
          }
        }
        return;
      }
      catch (Throwable localThrowable1)
      {
        localThrowable1.printStackTrace();
      }
      label308:
      paramInt += 1;
    }
  }
  
  private static void a(String paramString, int paramInt)
  {
    try
    {
      d locald = new d(paramInt);
      if (!StringUtils.isNull(paramString))
      {
        paramString = i.a(paramString, paramInt);
        if (NetUtil.checkAccessNetWork(2)) {
          NetUtil.executePubNumServiceHttpRequest(paramString, "990005", locald, "", true, false, "checkResourseRequest", true);
        }
      }
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  /* Error */
  private static void b(int paramInt)
  {
    // Byte code:
    //   0: new 34	java/lang/StringBuilder
    //   3: dup
    //   4: ldc 98
    //   6: invokespecial 39	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   9: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   12: invokestatic 173	cn/com/xy/sms/sdk/queue/a/a:a	(Ljava/lang/String;)Ljava/util/List;
    //   15: astore_1
    //   16: aload_1
    //   17: ifnull +113 -> 130
    //   20: aload_1
    //   21: invokeinterface 179 1 0
    //   26: ifne +104 -> 130
    //   29: aload_1
    //   30: invokeinterface 183 1 0
    //   35: astore_1
    //   36: aload_1
    //   37: ifnull +93 -> 130
    //   40: aload_1
    //   41: invokeinterface 188 1 0
    //   46: ifne +4 -> 50
    //   49: return
    //   50: aload_1
    //   51: invokeinterface 192 1 0
    //   56: checkcast 194	java/io/File
    //   59: astore_3
    //   60: aload_3
    //   61: invokevirtual 197	java/io/File:getName	()Ljava/lang/String;
    //   64: astore_2
    //   65: aload_3
    //   66: ldc -57
    //   68: invokestatic 128	cn/com/xy/sms/sdk/constant/Constant:getPath	(Ljava/lang/String;)Ljava/lang/String;
    //   71: invokestatic 205	cn/com/xy/sms/sdk/util/XyUtil:upZipFile	(Ljava/io/File;Ljava/lang/String;)V
    //   74: aload_3
    //   75: invokevirtual 208	java/io/File:delete	()Z
    //   78: pop
    //   79: iconst_1
    //   80: aload_2
    //   81: invokestatic 211	cn/com/xy/sms/sdk/db/entity/n:a	(ZLjava/lang/String;)V
    //   84: goto -48 -> 36
    //   87: astore_2
    //   88: aload_2
    //   89: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   92: goto -56 -> 36
    //   95: astore_1
    //   96: return
    //   97: astore_3
    //   98: iconst_0
    //   99: aload_2
    //   100: invokestatic 211	cn/com/xy/sms/sdk/db/entity/n:a	(ZLjava/lang/String;)V
    //   103: goto -67 -> 36
    //   106: astore_2
    //   107: aload_2
    //   108: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   111: goto -75 -> 36
    //   114: astore_1
    //   115: iconst_0
    //   116: aload_2
    //   117: invokestatic 211	cn/com/xy/sms/sdk/db/entity/n:a	(ZLjava/lang/String;)V
    //   120: aload_1
    //   121: athrow
    //   122: astore_2
    //   123: aload_2
    //   124: invokevirtual 140	java/lang/Throwable:printStackTrace	()V
    //   127: goto -7 -> 120
    //   130: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	131	0	paramInt	int
    //   15	36	1	localObject1	Object
    //   95	1	1	localThrowable1	Throwable
    //   114	7	1	localObject2	Object
    //   64	17	2	str	String
    //   87	13	2	localThrowable2	Throwable
    //   106	11	2	localThrowable3	Throwable
    //   122	2	2	localThrowable4	Throwable
    //   59	16	3	localFile	java.io.File
    //   97	1	3	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   74	84	87	java/lang/Throwable
    //   0	16	95	java/lang/Throwable
    //   20	36	95	java/lang/Throwable
    //   40	49	95	java/lang/Throwable
    //   50	65	95	java/lang/Throwable
    //   88	92	95	java/lang/Throwable
    //   107	111	95	java/lang/Throwable
    //   120	122	95	java/lang/Throwable
    //   123	127	95	java/lang/Throwable
    //   65	74	97	java/lang/Exception
    //   98	103	106	java/lang/Throwable
    //   65	74	114	finally
    //   115	120	122	java/lang/Throwable
  }
  
  private static void c(int paramInt)
  {
    if (!NetUtil.checkAccessNetWork(2)) {}
    for (;;)
    {
      return;
      if (a.a(1))
      {
        Object localObject2 = n.b(1);
        Object localObject1 = localObject2;
        if (StringUtils.isNull((String)localObject2)) {
          localObject1 = "-1";
        }
        try
        {
          localObject2 = new d(1);
          if (!StringUtils.isNull((String)localObject1))
          {
            localObject1 = i.a((String)localObject1, 1);
            if (NetUtil.checkAccessNetWork(2))
            {
              NetUtil.executePubNumServiceHttpRequest((String)localObject1, "990005", (XyCallBack)localObject2, "", true, false, "checkResourseRequest", true);
              return;
            }
          }
        }
        catch (Throwable localThrowable)
        {
          localThrowable.printStackTrace();
        }
      }
    }
  }
  
  public final void run()
  {
    do
    {
      try
      {
        setName("xiaoyuan_resoursequeue");
        Process.setThreadPriority(g.b);
        if (!a) {
          a = true;
        }
        for (;;)
        {
          try
          {
            Thread.sleep(1000L);
            if ((NetUtil.checkAccessNetWork(2)) && (a.a(1)))
            {
              str = n.b(1);
              localObject1 = str;
              if (StringUtils.isNull(str)) {
                localObject1 = "-1";
              }
              a((String)localObject1, 1);
            }
            Thread.sleep(1000L);
          }
          catch (Throwable localThrowable2)
          {
            String str;
            Object localObject1;
            boolean bool;
            int k;
            continue;
          }
          try
          {
            bool = NetUtil.checkAccessNetWork(2);
            if (bool) {
              continue;
            }
          }
          catch (Throwable localThrowable1)
          {
            localThrowable1.printStackTrace();
          }
        }
        Thread.sleep(1000L);
        b(1);
        a = false;
        return;
      }
      catch (Throwable localThrowable3)
      {
        localThrowable3.printStackTrace();
        return;
      }
      localObject1 = n.a(1);
      new StringBuilder("JsonArray=").append(localObject1);
    } while ((localObject1 == null) || (((JSONArray)localObject1).length() <= 0));
    k = ((JSONArray)localObject1).length();
    int i = 0;
    while (i < k)
    {
      Object localObject2 = ((JSONArray)localObject1).getJSONObject(i);
      str = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject2, "res_url");
      Object localObject3 = (Integer)JsonUtil.getValueFromJsonObject((JSONObject)localObject2, "res_version");
      Long localLong = (Long)JsonUtil.getValueFromJsonObject((JSONObject)localObject2, "down_failed_time");
      localObject2 = (Integer)JsonUtil.getValueFromJsonObject((JSONObject)localObject2, "id");
      new StringBuilder(" res_url=").append(str).append(" res_version=").append(localObject3).append(" down_failed_time=").append(localLong).append("id = ").append(localObject2);
      if (!StringUtils.isNull(str))
      {
        long l1 = System.currentTimeMillis();
        localObject3 = "1" + "_" + localObject3 + "_" + l1 + ".zip";
        long l2 = localLong.longValue();
        long l3 = DexUtil.getUpdateCycleByType(17, 3600000L);
        if (l1 <= l2 + l3) {
          break;
        }
        try
        {
          j = cn.com.xy.sms.sdk.util.d.f(str, Constant.getPath("duoqu_temp"), (String)localObject3);
          if (j == 0) {
            n.a((Integer)localObject2, true, (String)localObject3);
          }
        }
        catch (Throwable localThrowable4)
        {
          for (;;)
          {
            localThrowable4.printStackTrace();
            new StringBuilder("Throwable=").append(localThrowable4.getLocalizedMessage());
            int j = -1;
          }
          n.a((Integer)localObject2, false, (String)localObject3);
        }
        break;
      }
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.queue.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */