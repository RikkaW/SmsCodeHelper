package com.android.mms.util;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.provider.Telephony.Mms.Rate;
import android.util.Log;

public class RateController
{
  private static RateController sInstance;
  private static boolean sMutexLock;
  private int mAnswer;
  private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if ("com.android.mms.RATE_LIMIT_CONFIRMED".equals(paramAnonymousIntent.getAction())) {}
      for (;;)
      {
        try
        {
          paramAnonymousContext = RateController.this;
          if (!paramAnonymousIntent.getBooleanExtra("answer", false)) {
            break label50;
          }
          i = 1;
          RateController.access$002(paramAnonymousContext, i);
          notifyAll();
          return;
        }
        finally {}
        return;
        label50:
        int i = 2;
      }
    }
  };
  private final Context mContext;
  
  private RateController(Context paramContext)
  {
    mContext = paramContext;
  }
  
  public static RateController getInstance()
  {
    if (sInstance == null) {
      throw new IllegalStateException("Uninitialized.");
    }
    return sInstance;
  }
  
  public static void init(Context paramContext)
  {
    if (sInstance != null) {
      Log.w("RateController", "Already initialized.");
    }
    sInstance = new RateController(paramContext.getApplicationContext());
  }
  
  /* Error */
  private int waitForAnswer()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iconst_0
    //   3: istore_1
    //   4: aload_0
    //   5: getfield 33	com/android/mms/util/RateController:mAnswer	I
    //   8: istore_2
    //   9: iload_2
    //   10: ifne +26 -> 36
    //   13: iload_1
    //   14: sipush 20000
    //   17: if_icmpge +19 -> 36
    //   20: aload_0
    //   21: ldc2_w 68
    //   24: invokevirtual 73	java/lang/Object:wait	(J)V
    //   27: iload_1
    //   28: sipush 1000
    //   31: iadd
    //   32: istore_1
    //   33: goto -29 -> 4
    //   36: aload_0
    //   37: getfield 33	com/android/mms/util/RateController:mAnswer	I
    //   40: istore_1
    //   41: aload_0
    //   42: monitorexit
    //   43: iload_1
    //   44: ireturn
    //   45: astore_3
    //   46: goto -19 -> 27
    //   49: astore_3
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_3
    //   53: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	54	0	this	RateController
    //   3	41	1	i	int
    //   8	2	2	j	int
    //   45	1	3	localInterruptedException	InterruptedException
    //   49	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   20	27	45	java/lang/InterruptedException
    //   4	9	49	finally
    //   20	27	49	finally
    //   36	41	49	finally
  }
  
  /* Error */
  public boolean isAllowedByUser()
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: getstatic 77	com/android/mms/util/RateController:sMutexLock	Z
    //   7: istore_3
    //   8: iload_3
    //   9: ifeq +15 -> 24
    //   12: aload_0
    //   13: invokevirtual 79	java/lang/Object:wait	()V
    //   16: goto -12 -> 4
    //   19: astore 4
    //   21: goto -17 -> 4
    //   24: iconst_1
    //   25: putstatic 77	com/android/mms/util/RateController:sMutexLock	Z
    //   28: aload_0
    //   29: getfield 28	com/android/mms/util/RateController:mContext	Landroid/content/Context;
    //   32: aload_0
    //   33: getfield 26	com/android/mms/util/RateController:mBroadcastReceiver	Landroid/content/BroadcastReceiver;
    //   36: new 81	android/content/IntentFilter
    //   39: dup
    //   40: ldc 83
    //   42: invokespecial 84	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   45: invokevirtual 88	android/content/Context:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   48: pop
    //   49: aload_0
    //   50: iconst_0
    //   51: putfield 33	com/android/mms/util/RateController:mAnswer	I
    //   54: new 90	android/content/Intent
    //   57: dup
    //   58: ldc 92
    //   60: invokespecial 93	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   63: astore 4
    //   65: aload 4
    //   67: ldc 94
    //   69: invokevirtual 98	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   72: pop
    //   73: aload_0
    //   74: getfield 28	com/android/mms/util/RateController:mContext	Landroid/content/Context;
    //   77: aload 4
    //   79: invokevirtual 102	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   82: aload_0
    //   83: invokespecial 104	com/android/mms/util/RateController:waitForAnswer	()I
    //   86: istore_1
    //   87: iload_1
    //   88: iconst_1
    //   89: if_icmpne +26 -> 115
    //   92: aload_0
    //   93: getfield 28	com/android/mms/util/RateController:mContext	Landroid/content/Context;
    //   96: aload_0
    //   97: getfield 26	com/android/mms/util/RateController:mBroadcastReceiver	Landroid/content/BroadcastReceiver;
    //   100: invokevirtual 108	android/content/Context:unregisterReceiver	(Landroid/content/BroadcastReceiver;)V
    //   103: iconst_0
    //   104: putstatic 77	com/android/mms/util/RateController:sMutexLock	Z
    //   107: aload_0
    //   108: invokevirtual 111	java/lang/Object:notifyAll	()V
    //   111: aload_0
    //   112: monitorexit
    //   113: iload_2
    //   114: ireturn
    //   115: iconst_0
    //   116: istore_2
    //   117: goto -25 -> 92
    //   120: astore 4
    //   122: aload_0
    //   123: getfield 28	com/android/mms/util/RateController:mContext	Landroid/content/Context;
    //   126: aload_0
    //   127: getfield 26	com/android/mms/util/RateController:mBroadcastReceiver	Landroid/content/BroadcastReceiver;
    //   130: invokevirtual 108	android/content/Context:unregisterReceiver	(Landroid/content/BroadcastReceiver;)V
    //   133: iconst_0
    //   134: putstatic 77	com/android/mms/util/RateController:sMutexLock	Z
    //   137: aload_0
    //   138: invokevirtual 111	java/lang/Object:notifyAll	()V
    //   141: aload 4
    //   143: athrow
    //   144: astore 4
    //   146: aload_0
    //   147: monitorexit
    //   148: aload 4
    //   150: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	151	0	this	RateController
    //   86	4	1	i	int
    //   1	116	2	bool1	boolean
    //   7	2	3	bool2	boolean
    //   19	1	4	localInterruptedException	InterruptedException
    //   63	15	4	localIntent	Intent
    //   120	22	4	localObject1	Object
    //   144	5	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   12	16	19	java/lang/InterruptedException
    //   54	87	120	finally
    //   4	8	144	finally
    //   12	16	144	finally
    //   24	54	144	finally
    //   92	111	144	finally
    //   122	144	144	finally
  }
  
  public final boolean isLimitSurpassed()
  {
    long l = System.currentTimeMillis();
    Object localObject1 = mContext;
    ContentResolver localContentResolver = mContext.getContentResolver();
    Uri localUri = Telephony.Mms.Rate.CONTENT_URI;
    String str = "sent_time>" + (l - 3600000L);
    localObject1 = SqliteWrapper.query((Context)localObject1, localContentResolver, localUri, new String[] { "COUNT(*) AS rate" }, str, null, null);
    if (localObject1 != null) {}
    try
    {
      if (((Cursor)localObject1).moveToFirst())
      {
        int i = ((Cursor)localObject1).getInt(0);
        if (i >= 100) {}
        for (boolean bool = true;; bool = false) {
          return bool;
        }
      }
      return false;
    }
    finally
    {
      ((Cursor)localObject1).close();
    }
  }
  
  public final void update()
  {
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("sent_time", Long.valueOf(System.currentTimeMillis()));
    SqliteWrapper.insert(mContext, mContext.getContentResolver(), Telephony.Mms.Rate.CONTENT_URI, localContentValues);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.RateController
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */