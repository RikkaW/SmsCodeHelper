import android.net.Uri;
import android.os.AsyncTask;

class gs
  extends AsyncTask<Void, Void, Void>
{
  gs(gr paramgr, Uri paramUri) {}
  
  /* Error */
  protected Void a(Void... paramVarArgs)
  {
    // Byte code:
    //   0: ldc 22
    //   2: iconst_2
    //   3: invokestatic 28	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   6: ifeq +12 -> 18
    //   9: ldc 30
    //   11: iconst_0
    //   12: anewarray 32	java/lang/Object
    //   15: invokestatic 37	fl:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   18: aload_0
    //   19: getfield 15	gs:a	Landroid/net/Uri;
    //   22: ifnull +145 -> 167
    //   25: invokestatic 41	gr:b	()V
    //   28: aload_0
    //   29: getfield 13	gs:b	Lgr;
    //   32: invokestatic 44	gr:b	(Lgr;)Landroid/content/Context;
    //   35: invokevirtual 50	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   38: aload_0
    //   39: getfield 15	gs:a	Landroid/net/Uri;
    //   42: getstatic 54	gr:c	[Ljava/lang/String;
    //   45: ldc 56
    //   47: aconst_null
    //   48: aconst_null
    //   49: invokevirtual 62	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   52: astore_1
    //   53: aload_1
    //   54: ifnull +142 -> 196
    //   57: aload_1
    //   58: invokeinterface 68 1 0
    //   63: istore_2
    //   64: iload_2
    //   65: ifle +117 -> 182
    //   68: iconst_1
    //   69: istore_2
    //   70: aload_1
    //   71: invokeinterface 71 1 0
    //   76: iload_2
    //   77: ifeq +56 -> 133
    //   80: new 73	java/lang/StringBuilder
    //   83: dup
    //   84: invokespecial 74	java/lang/StringBuilder:<init>	()V
    //   87: ldc 76
    //   89: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: aload_0
    //   93: getfield 15	gs:a	Landroid/net/Uri;
    //   96: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   99: invokevirtual 87	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   102: iconst_0
    //   103: anewarray 32	java/lang/Object
    //   106: invokestatic 37	fl:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   109: aload_0
    //   110: getfield 13	gs:b	Lgr;
    //   113: invokestatic 44	gr:b	(Lgr;)Landroid/content/Context;
    //   116: invokevirtual 50	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   119: aload_0
    //   120: getfield 15	gs:a	Landroid/net/Uri;
    //   123: getstatic 91	gr:e	Landroid/content/ContentValues;
    //   126: ldc 56
    //   128: aconst_null
    //   129: invokevirtual 95	android/content/ContentResolver:update	(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   132: pop
    //   133: aload_0
    //   134: getfield 13	gs:b	Lgr;
    //   137: iconst_0
    //   138: invokevirtual 98	gr:c	(Z)V
    //   141: invokestatic 103	com/android/mms/MmsApp:c	()Lcom/android/mms/MmsApp;
    //   144: invokevirtual 107	com/android/mms/MmsApp:k	()J
    //   147: aload_0
    //   148: getfield 13	gs:b	Lgr;
    //   151: invokestatic 110	gr:c	(Lgr;)J
    //   154: lcmp
    //   155: ifne +12 -> 167
    //   158: invokestatic 103	com/android/mms/MmsApp:c	()Lcom/android/mms/MmsApp;
    //   161: ldc2_w 111
    //   164: invokevirtual 115	com/android/mms/MmsApp:a	(J)V
    //   167: aload_0
    //   168: getfield 13	gs:b	Lgr;
    //   171: invokestatic 44	gr:b	(Lgr;)Landroid/content/Context;
    //   174: ldc2_w 116
    //   177: invokestatic 122	com/android/mms/transaction/MessagingNotification:a	(Landroid/content/Context;J)V
    //   180: aconst_null
    //   181: areturn
    //   182: iconst_0
    //   183: istore_2
    //   184: goto -114 -> 70
    //   187: astore_3
    //   188: aload_1
    //   189: invokeinterface 71 1 0
    //   194: aload_3
    //   195: athrow
    //   196: iconst_1
    //   197: istore_2
    //   198: goto -122 -> 76
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	201	0	this	gs
    //   0	201	1	paramVarArgs	Void[]
    //   63	135	2	i	int
    //   187	8	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   57	64	187	finally
  }
}

/* Location:
 * Qualified Name:     gs
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */