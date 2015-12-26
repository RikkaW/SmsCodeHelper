package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.ParseItemManager;
import cn.com.xy.sms.sdk.db.entity.PhoneSmsParseManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.e;
import java.util.List;
import org.json.JSONObject;

public final class a
  extends Thread
{
  private static final Object a = new Object();
  private static boolean b = false;
  private static boolean c = false;
  private static long j = 0L;
  private boolean d = false;
  private String e;
  private int f;
  private int g;
  private int h = 0;
  private boolean i = false;
  
  private a() {}
  
  private a(boolean paramBoolean1, String paramString, int paramInt1, int paramInt2, boolean paramBoolean2)
  {
    d = paramBoolean1;
    e = paramString;
    f = paramInt1;
    g = paramInt2;
    i = paramBoolean2;
    setName("before_parse_thread");
  }
  
  public static void a()
  {
    synchronized (a)
    {
      b = true;
      return;
    }
  }
  
  public static void a(boolean paramBoolean1, String paramString, int paramInt1, int paramInt2, boolean paramBoolean2)
  {
    new a(paramBoolean1, paramString, paramInt1, paramInt2, paramBoolean2).start();
  }
  
  private static boolean b()
  {
    boolean bool2 = false;
    int k = 0;
    for (;;)
    {
      boolean bool1;
      if ((ParseItemManager.isInitData()) && (!c))
      {
        bool1 = true;
        label18:
        return bool1;
      }
      try
      {
        sleep(1000L);
        int m = k + 1;
        bool1 = bool2;
        if (m > 30) {
          break label18;
        }
        k = m;
        if (e.a)
        {
          k = m;
          if (!c) {
            return true;
          }
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException.printStackTrace();
      }
    }
    return false;
  }
  
  private void c()
  {
    if ((b) || (j != Thread.currentThread().getId())) {
      label19:
      return;
    }
    long l2 = 0L;
    long l1;
    if (d) {
      l1 = SysParamEntityManager.getLongParam("BEFORE_HAND_PARSE_SMS_TIME", 0L, Constant.getContext());
    }
    Object localObject;
    int k;
    for (;;)
    {
      l2 = l1;
      if (l1 == 0L) {
        l2 = System.currentTimeMillis() + 2147483647L;
      }
      localObject = DuoquUtils.getSdkDoAction().getReceiveMsgByReceiveTime(e, 0L, l2, f);
      if ((localObject == null) || (((List)localObject).isEmpty())) {
        break label19;
      }
      int m = ((List)localObject).size();
      k = 0;
      l1 = l2;
      if (k < m) {
        break label242;
      }
      l2 = l1;
      label113:
      if ((d) && ((k != m) || (m % 10 != 0))) {
        SysParamEntityManager.setParam("BEFORE_HAND_PARSE_SMS_TIME", String.valueOf(l2));
      }
      h += m;
      if ((b) || (!d) || (h >= 500) || (m < f)) {
        break label19;
      }
      sleep(5L);
      break;
      localObject = PhoneSmsParseManager.findObjectByPhone(e);
      l1 = l2;
      if (localObject != null)
      {
        localObject = JsonUtil.getValFromJsonObject((JSONObject)localObject, "maxReceiveTime");
        l1 = l2;
        if (localObject != null) {
          l1 = Long.valueOf(localObject.toString()).longValue();
        }
      }
    }
    label242:
    JSONObject localJSONObject = (JSONObject)((List)localObject).get(k);
    l2 = Long.valueOf((String)JsonUtil.getValFromJsonObject(localJSONObject, "smsReceiveTime")).longValue();
    if (l2 < l1) {
      l1 = l2;
    }
    for (;;)
    {
      ParseSmsToBubbleUtil.parseSmsToBubbleResultMap((String)JsonUtil.getValFromJsonObject(localJSONObject, "msgId"), (String)JsonUtil.getValFromJsonObject(localJSONObject, "phone"), (String)JsonUtil.getValFromJsonObject(localJSONObject, "msg"), (String)JsonUtil.getValFromJsonObject(localJSONObject, "centerNum"), l2, g, d, i, null);
      if ((k + 1) % 10 == 0)
      {
        if (d) {
          SysParamEntityManager.setParam("BEFORE_HAND_PARSE_SMS_TIME", String.valueOf(l1));
        }
        sleep(1L);
      }
      l2 = l1;
      if (b) {
        break label113;
      }
      l2 = l1;
      if (j != Thread.currentThread().getId()) {
        break label113;
      }
      k += 1;
      break;
    }
  }
  
  /* Error */
  public final void run()
  {
    // Byte code:
    //   0: getstatic 28	cn/com/xy/sms/util/a:a	Ljava/lang/Object;
    //   3: astore_2
    //   4: aload_2
    //   5: monitorenter
    //   6: getstatic 32	cn/com/xy/sms/util/a:c	Z
    //   9: ifeq +7 -> 16
    //   12: iconst_1
    //   13: putstatic 30	cn/com/xy/sms/util/a:b	Z
    //   16: aload_2
    //   17: monitorexit
    //   18: invokestatic 196	cn/com/xy/sms/util/a:b	()Z
    //   21: istore_1
    //   22: iload_1
    //   23: ifne +65 -> 88
    //   26: getstatic 28	cn/com/xy/sms/util/a:a	Ljava/lang/Object;
    //   29: astore_2
    //   30: aload_2
    //   31: monitorenter
    //   32: iconst_0
    //   33: putstatic 32	cn/com/xy/sms/util/a:c	Z
    //   36: iconst_0
    //   37: putstatic 30	cn/com/xy/sms/util/a:b	Z
    //   40: lconst_0
    //   41: putstatic 34	cn/com/xy/sms/util/a:j	J
    //   44: aload_2
    //   45: monitorexit
    //   46: return
    //   47: astore_3
    //   48: aload_2
    //   49: monitorexit
    //   50: aload_3
    //   51: athrow
    //   52: astore_2
    //   53: aload_2
    //   54: invokevirtual 197	java/lang/Throwable:printStackTrace	()V
    //   57: getstatic 28	cn/com/xy/sms/util/a:a	Ljava/lang/Object;
    //   60: astore_2
    //   61: aload_2
    //   62: monitorenter
    //   63: iconst_0
    //   64: putstatic 32	cn/com/xy/sms/util/a:c	Z
    //   67: iconst_0
    //   68: putstatic 30	cn/com/xy/sms/util/a:b	Z
    //   71: lconst_0
    //   72: putstatic 34	cn/com/xy/sms/util/a:j	J
    //   75: aload_2
    //   76: monitorexit
    //   77: return
    //   78: astore_3
    //   79: aload_2
    //   80: monitorexit
    //   81: aload_3
    //   82: athrow
    //   83: astore_3
    //   84: aload_2
    //   85: monitorexit
    //   86: aload_3
    //   87: athrow
    //   88: getstatic 28	cn/com/xy/sms/util/a:a	Ljava/lang/Object;
    //   91: astore_2
    //   92: aload_2
    //   93: monitorenter
    //   94: getstatic 30	cn/com/xy/sms/util/a:b	Z
    //   97: ifeq +36 -> 133
    //   100: aload_2
    //   101: monitorexit
    //   102: goto -76 -> 26
    //   105: astore_3
    //   106: aload_2
    //   107: monitorexit
    //   108: aload_3
    //   109: athrow
    //   110: astore_3
    //   111: getstatic 28	cn/com/xy/sms/util/a:a	Ljava/lang/Object;
    //   114: astore_2
    //   115: aload_2
    //   116: monitorenter
    //   117: iconst_0
    //   118: putstatic 32	cn/com/xy/sms/util/a:c	Z
    //   121: iconst_0
    //   122: putstatic 30	cn/com/xy/sms/util/a:b	Z
    //   125: lconst_0
    //   126: putstatic 34	cn/com/xy/sms/util/a:j	J
    //   129: aload_2
    //   130: monitorexit
    //   131: aload_3
    //   132: athrow
    //   133: iconst_1
    //   134: putstatic 32	cn/com/xy/sms/util/a:c	Z
    //   137: iconst_0
    //   138: putstatic 30	cn/com/xy/sms/util/a:b	Z
    //   141: invokestatic 85	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   144: invokevirtual 89	java/lang/Thread:getId	()J
    //   147: putstatic 34	cn/com/xy/sms/util/a:j	J
    //   150: aload_2
    //   151: monitorexit
    //   152: aload_0
    //   153: invokespecial 199	cn/com/xy/sms/util/a:c	()V
    //   156: getstatic 28	cn/com/xy/sms/util/a:a	Ljava/lang/Object;
    //   159: astore_2
    //   160: aload_2
    //   161: monitorenter
    //   162: iconst_0
    //   163: putstatic 32	cn/com/xy/sms/util/a:c	Z
    //   166: iconst_0
    //   167: putstatic 30	cn/com/xy/sms/util/a:b	Z
    //   170: lconst_0
    //   171: putstatic 34	cn/com/xy/sms/util/a:j	J
    //   174: aload_2
    //   175: monitorexit
    //   176: return
    //   177: astore_3
    //   178: aload_2
    //   179: monitorexit
    //   180: aload_3
    //   181: athrow
    //   182: astore_3
    //   183: aload_2
    //   184: monitorexit
    //   185: aload_3
    //   186: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	187	0	this	a
    //   21	2	1	bool	boolean
    //   52	2	2	localThrowable	Throwable
    //   47	4	3	localObject3	Object
    //   78	4	3	localObject4	Object
    //   83	4	3	localObject5	Object
    //   105	4	3	localObject6	Object
    //   110	22	3	localObject7	Object
    //   177	4	3	localObject8	Object
    //   182	4	3	localObject9	Object
    // Exception table:
    //   from	to	target	type
    //   6	16	47	finally
    //   16	18	47	finally
    //   0	6	52	java/lang/Throwable
    //   18	22	52	java/lang/Throwable
    //   48	52	52	java/lang/Throwable
    //   88	94	52	java/lang/Throwable
    //   106	110	52	java/lang/Throwable
    //   152	156	52	java/lang/Throwable
    //   63	77	78	finally
    //   32	46	83	finally
    //   94	102	105	finally
    //   133	152	105	finally
    //   0	6	110	finally
    //   18	22	110	finally
    //   48	52	110	finally
    //   53	57	110	finally
    //   88	94	110	finally
    //   106	110	110	finally
    //   152	156	110	finally
    //   162	176	177	finally
    //   117	131	182	finally
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */