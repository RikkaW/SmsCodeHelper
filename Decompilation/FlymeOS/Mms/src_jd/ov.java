public class ov
{
  private static final String[] a = { "group_msg_id" };
  protected boolean j = false;
  protected long k;
  
  /* Error */
  protected void a(android.content.Context paramContext, android.net.Uri paramUri, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 5
    //   6: aconst_null
    //   7: astore 4
    //   9: iload_3
    //   10: ifeq +153 -> 163
    //   13: aload_1
    //   14: invokevirtual 33	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   17: aload_2
    //   18: getstatic 18	ov:a	[Ljava/lang/String;
    //   21: aconst_null
    //   22: aconst_null
    //   23: aconst_null
    //   24: invokevirtual 39	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   27: astore_2
    //   28: aload_2
    //   29: invokeinterface 45 1 0
    //   34: ifle +19 -> 53
    //   37: aload_2
    //   38: invokeinterface 49 1 0
    //   43: pop
    //   44: aload_2
    //   45: iconst_0
    //   46: invokeinterface 53 2 0
    //   51: astore 4
    //   53: aload 4
    //   55: astore 5
    //   57: aload_2
    //   58: ifnull +13 -> 71
    //   61: aload_2
    //   62: invokeinterface 56 1 0
    //   67: aload 4
    //   69: astore 5
    //   71: aload 5
    //   73: ifnull +49 -> 122
    //   76: new 58	java/lang/StringBuilder
    //   79: dup
    //   80: invokespecial 59	java/lang/StringBuilder:<init>	()V
    //   83: ldc 61
    //   85: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: aload 5
    //   90: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: ldc 67
    //   95: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   101: astore_2
    //   102: aload_1
    //   103: getstatic 77	android/provider/Telephony$Sms:CONTENT_URI	Landroid/net/Uri;
    //   106: aload_2
    //   107: aload_0
    //   108: getfield 79	ov:k	J
    //   111: invokestatic 84	com/android/mms/util/TimerMessageHelpler:a	(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;J)V
    //   114: aload_1
    //   115: aload_0
    //   116: getfield 79	ov:k	J
    //   119: invokestatic 87	com/android/mms/util/TimerMessageHelpler:a	(Landroid/content/Context;J)V
    //   122: return
    //   123: astore_2
    //   124: aconst_null
    //   125: astore_2
    //   126: aload 6
    //   128: astore 5
    //   130: aload_2
    //   131: ifnull -60 -> 71
    //   134: aload_2
    //   135: invokeinterface 56 1 0
    //   140: aload 6
    //   142: astore 5
    //   144: goto -73 -> 71
    //   147: astore_1
    //   148: aload 5
    //   150: astore_2
    //   151: aload_2
    //   152: ifnull +9 -> 161
    //   155: aload_2
    //   156: invokeinterface 56 1 0
    //   161: aload_1
    //   162: athrow
    //   163: aload_1
    //   164: aload_2
    //   165: aconst_null
    //   166: aload_0
    //   167: getfield 79	ov:k	J
    //   170: invokestatic 84	com/android/mms/util/TimerMessageHelpler:a	(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;J)V
    //   173: aload_1
    //   174: aload_0
    //   175: getfield 79	ov:k	J
    //   178: invokestatic 87	com/android/mms/util/TimerMessageHelpler:a	(Landroid/content/Context;J)V
    //   181: return
    //   182: astore_1
    //   183: goto -32 -> 151
    //   186: astore 4
    //   188: goto -62 -> 126
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	191	0	this	ov
    //   0	191	1	paramContext	android.content.Context
    //   0	191	2	paramUri	android.net.Uri
    //   0	191	3	paramBoolean	boolean
    //   7	61	4	str	String
    //   186	1	4	localException	Exception
    //   4	145	5	localObject1	Object
    //   1	140	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   13	28	123	java/lang/Exception
    //   13	28	147	finally
    //   28	53	182	finally
    //   28	53	186	java/lang/Exception
  }
  
  public void a(boolean paramBoolean)
  {
    j = paramBoolean;
  }
  
  public void b(long paramLong)
  {
    k = paramLong;
  }
}

/* Location:
 * Qualified Name:     ov
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */