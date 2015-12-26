package com.xiaomi.push.service.timers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import com.xiaomi.channel.commonutils.logger.MyLog;
import java.lang.reflect.Method;

public class AlarmManagerTimer
{
  private static volatile long mNextPingTs = 0L;
  private Context mContext = null;
  private PendingIntent mPi = null;
  
  public AlarmManagerTimer(Context paramContext)
  {
    mContext = paramContext;
  }
  
  private void setExact(AlarmManager paramAlarmManager, long paramLong, PendingIntent paramPendingIntent)
  {
    try
    {
      AlarmManager.class.getMethod("setExact", new Class[] { Integer.TYPE, Long.TYPE, PendingIntent.class }).invoke(paramAlarmManager, new Object[] { Integer.valueOf(0), Long.valueOf(paramLong), paramPendingIntent });
      return;
    }
    catch (Exception paramAlarmManager)
    {
      MyLog.e(paramAlarmManager);
    }
  }
  
  /* Error */
  public boolean isAlive()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 21	com/xiaomi/push/service/timers/AlarmManagerTimer:mPi	Landroid/app/PendingIntent;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull +9 -> 17
    //   11: iconst_1
    //   12: istore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: iload_1
    //   16: ireturn
    //   17: iconst_0
    //   18: istore_1
    //   19: goto -6 -> 13
    //   22: astore_2
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_2
    //   26: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	27	0	this	AlarmManagerTimer
    //   12	7	1	bool	boolean
    //   6	2	2	localPendingIntent	PendingIntent
    //   22	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	22	finally
  }
  
  public void register(Intent paramIntent, long paramLong)
  {
    for (;;)
    {
      try
      {
        Object localObject = mPi;
        if (localObject != null) {
          return;
        }
        localObject = (AlarmManager)mContext.getSystemService("alarm");
        mPi = PendingIntent.getBroadcast(mContext, 0, paramIntent, 0);
        if (Build.VERSION.SDK_INT >= 19)
        {
          setExact((AlarmManager)localObject, paramLong, mPi);
          MyLog.v("register timer " + mNextPingTs);
        }
        else
        {
          ((AlarmManager)localObject).set(0, paramLong, mPi);
        }
      }
      finally {}
    }
  }
  
  /* Error */
  public void registerPing(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 118	android/content/Intent
    //   5: dup
    //   6: getstatic 124	com/xiaomi/push/service/PushConstants:ACTION_PING_TIMER	Ljava/lang/String;
    //   9: invokespecial 126	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   12: astore 4
    //   14: aload 4
    //   16: aload_0
    //   17: getfield 23	com/xiaomi/push/service/timers/AlarmManagerTimer:mContext	Landroid/content/Context;
    //   20: invokevirtual 129	android/content/Context:getPackageName	()Ljava/lang/String;
    //   23: invokevirtual 133	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   26: pop
    //   27: invokestatic 139	com/xiaomi/smack/SmackConfiguration:getPingInteval	()I
    //   30: i2l
    //   31: lstore_2
    //   32: iload_1
    //   33: ifne +11 -> 44
    //   36: getstatic 14	com/xiaomi/push/service/timers/AlarmManagerTimer:mNextPingTs	J
    //   39: lconst_0
    //   40: lcmp
    //   41: ifne +29 -> 70
    //   44: invokestatic 145	java/lang/System:currentTimeMillis	()J
    //   47: lload_2
    //   48: invokestatic 150	android/os/SystemClock:elapsedRealtime	()J
    //   51: lload_2
    //   52: lrem
    //   53: lsub
    //   54: ladd
    //   55: putstatic 14	com/xiaomi/push/service/timers/AlarmManagerTimer:mNextPingTs	J
    //   58: aload_0
    //   59: aload 4
    //   61: getstatic 14	com/xiaomi/push/service/timers/AlarmManagerTimer:mNextPingTs	J
    //   64: invokevirtual 152	com/xiaomi/push/service/timers/AlarmManagerTimer:register	(Landroid/content/Intent;J)V
    //   67: aload_0
    //   68: monitorexit
    //   69: return
    //   70: getstatic 14	com/xiaomi/push/service/timers/AlarmManagerTimer:mNextPingTs	J
    //   73: lload_2
    //   74: ladd
    //   75: putstatic 14	com/xiaomi/push/service/timers/AlarmManagerTimer:mNextPingTs	J
    //   78: getstatic 14	com/xiaomi/push/service/timers/AlarmManagerTimer:mNextPingTs	J
    //   81: invokestatic 145	java/lang/System:currentTimeMillis	()J
    //   84: lcmp
    //   85: ifge -27 -> 58
    //   88: invokestatic 145	java/lang/System:currentTimeMillis	()J
    //   91: lload_2
    //   92: ladd
    //   93: putstatic 14	com/xiaomi/push/service/timers/AlarmManagerTimer:mNextPingTs	J
    //   96: goto -38 -> 58
    //   99: astore 4
    //   101: aload_0
    //   102: monitorexit
    //   103: aload 4
    //   105: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	106	0	this	AlarmManagerTimer
    //   0	106	1	paramBoolean	boolean
    //   31	61	2	l	long
    //   12	48	4	localIntent	Intent
    //   99	5	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	32	99	finally
    //   36	44	99	finally
    //   44	58	99	finally
    //   58	67	99	finally
    //   70	96	99	finally
  }
  
  public void stop()
  {
    try
    {
      if (mPi != null)
      {
        ((AlarmManager)mContext.getSystemService("alarm")).cancel(mPi);
        mPi = null;
        MyLog.v("unregister timer");
        mNextPingTs = 0L;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.timers.AlarmManagerTimer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */