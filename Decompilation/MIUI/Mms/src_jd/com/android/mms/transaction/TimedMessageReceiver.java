package com.android.mms.transaction;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.mms.LogTag;
import com.android.mms.ui.MessageUtils;

public class TimedMessageReceiver
  extends BroadcastReceiver
{
  private static final Uri TIMED_MMS_URI = Uri.parse("content://mms/sent");
  private static final Uri TIMED_SMS_URI = Uri.parse("content://sms/sent");
  
  /* Error */
  private static long getFirstTimedMsgTime(Context paramContext)
  {
    // Byte code:
    //   0: ldc2_w 30
    //   3: lstore_3
    //   4: aload_0
    //   5: aload_0
    //   6: invokevirtual 37	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   9: getstatic 19	com/android/mms/transaction/TimedMessageReceiver:TIMED_SMS_URI	Landroid/net/Uri;
    //   12: iconst_1
    //   13: anewarray 39	java/lang/String
    //   16: dup
    //   17: iconst_0
    //   18: ldc 41
    //   20: aastore
    //   21: ldc 43
    //   23: aconst_null
    //   24: ldc 45
    //   26: invokestatic 51	android/database/sqlite/SqliteWrapper:query	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   29: astore 5
    //   31: lload_3
    //   32: lstore_1
    //   33: aload 5
    //   35: ifnull +31 -> 66
    //   38: lload_3
    //   39: lstore_1
    //   40: aload 5
    //   42: invokeinterface 57 1 0
    //   47: ifeq +12 -> 59
    //   50: aload 5
    //   52: iconst_0
    //   53: invokeinterface 61 2 0
    //   58: lstore_1
    //   59: aload 5
    //   61: invokeinterface 64 1 0
    //   66: aload_0
    //   67: aload_0
    //   68: invokevirtual 37	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   71: getstatic 23	com/android/mms/transaction/TimedMessageReceiver:TIMED_MMS_URI	Landroid/net/Uri;
    //   74: iconst_1
    //   75: anewarray 39	java/lang/String
    //   78: dup
    //   79: iconst_0
    //   80: ldc 66
    //   82: aastore
    //   83: ldc 43
    //   85: aconst_null
    //   86: ldc 68
    //   88: invokestatic 51	android/database/sqlite/SqliteWrapper:query	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   91: astore_0
    //   92: lload_1
    //   93: lstore_3
    //   94: aload_0
    //   95: ifnull +32 -> 127
    //   98: lload_1
    //   99: lstore_3
    //   100: aload_0
    //   101: invokeinterface 57 1 0
    //   106: ifeq +15 -> 121
    //   109: aload_0
    //   110: iconst_0
    //   111: invokeinterface 61 2 0
    //   116: lload_1
    //   117: invokestatic 74	java/lang/Math:min	(JJ)J
    //   120: lstore_3
    //   121: aload_0
    //   122: invokeinterface 64 1 0
    //   127: lload_3
    //   128: lreturn
    //   129: astore_0
    //   130: aload 5
    //   132: invokeinterface 64 1 0
    //   137: aload_0
    //   138: athrow
    //   139: astore 5
    //   141: aload_0
    //   142: invokeinterface 64 1 0
    //   147: aload 5
    //   149: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	150	0	paramContext	Context
    //   32	85	1	l1	long
    //   3	125	3	l2	long
    //   29	102	5	localCursor	android.database.Cursor
    //   139	9	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   40	59	129	finally
    //   100	121	139	finally
  }
  
  public static void scheduleNextTimedMsg(Context paramContext)
  {
    AlarmManager localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
    long l = getFirstTimedMsgTime(paramContext);
    if (l < Long.MAX_VALUE)
    {
      LogTag.verbose("Scheduled next timed message at %d (%s)", new Object[] { Long.valueOf(l), MessageUtils.getPreciseTimeStamp(l, false) });
      Intent localIntent = new Intent(paramContext, TimedMessageReceiver.class);
      localIntent.setAction("com.android.mms.transaction.TIMED_MESSAGE");
      localAlarmManager.set(0, l, PendingIntent.getBroadcast(paramContext, 0, localIntent, 268435456));
    }
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("com.android.mms.transaction.TIMED_MESSAGE".equals(paramIntent.getAction())) {
      SendMessageService.startSendTimedMsg(paramContext);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.TimedMessageReceiver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */