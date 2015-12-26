package cn.com.xy.sms.sdk.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

public final class q
  extends Thread
{
  private static boolean a = false;
  
  public static void a()
  {
    try
    {
      if (!a) {
        new q().start();
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
    List localList = cn.com.xy.sms.sdk.db.entity.q.a(paramInt, 0);
    new StringBuilder("requestQue=").append(localList);
    if (!localList.isEmpty()) {
      SceneconfigUtil.requestScenceconfig(localList, paramInt, false);
    }
  }
  
  private static void a(String paramString)
  {
    try
    {
      r localr = new r();
      Timer localTimer = new Timer();
      Date localDate = new Date();
      Calendar localCalendar = Calendar.getInstance();
      paramString = new StringBuilder(String.valueOf(localCalendar.get(1))).append("/").append(localCalendar.get(2) + 1).append("/").append(localCalendar.get(5)).toString() + " " + paramString;
      try
      {
        paramString = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(paramString);
        localTimer.schedule(localr, paramString);
        return;
      }
      catch (Throwable paramString)
      {
        for (;;)
        {
          paramString.printStackTrace();
          paramString = localDate;
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
  public final void run()
  {
    // Byte code:
    //   0: getstatic 10	cn/com/xy/sms/sdk/util/q:a	Z
    //   3: ifne +42 -> 45
    //   6: iconst_1
    //   7: putstatic 10	cn/com/xy/sms/sdk/util/q:a	Z
    //   10: ldc2_w 109
    //   13: invokestatic 114	java/lang/Thread:sleep	(J)V
    //   16: iconst_0
    //   17: invokestatic 119	cn/com/xy/sms/sdk/util/z:a	(Z)V
    //   20: ldc2_w 120
    //   23: invokestatic 114	java/lang/Thread:sleep	(J)V
    //   26: iconst_0
    //   27: invokestatic 123	cn/com/xy/sms/sdk/util/q:a	(I)V
    //   30: iconst_1
    //   31: invokestatic 123	cn/com/xy/sms/sdk/util/q:a	(I)V
    //   34: iconst_0
    //   35: invokestatic 126	cn/com/xy/sms/sdk/util/w:a	(Z)V
    //   38: invokestatic 129	cn/com/xy/sms/sdk/util/SceneconfigUtil:updateData	()V
    //   41: iconst_0
    //   42: putstatic 10	cn/com/xy/sms/sdk/util/q:a	Z
    //   45: return
    //   46: astore_1
    //   47: aload_1
    //   48: invokevirtual 107	java/lang/Throwable:printStackTrace	()V
    //   51: return
    //   52: astore_1
    //   53: goto -27 -> 26
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	56	0	this	q
    //   46	2	1	localThrowable1	Throwable
    //   52	1	1	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   0	10	46	java/lang/Throwable
    //   26	45	46	java/lang/Throwable
    //   10	26	52	java/lang/Throwable
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.q
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */