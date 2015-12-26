package cn.com.xy.sms.sdk.db.entity;

import cn.com.xy.sms.sdk.db.DBManager;

public final class b
{
  private static String a = "scene_id";
  private static String b = "count";
  private static String c = "tb_count_scene";
  private static String d = " DROP TABLE IF EXISTS tb_count_scene";
  private static String e = "create table  if not exists tb_count_scene (scene_id TEXT,count INT)";
  
  /* Error */
  public static long a(java.util.HashMap<String, String> paramHashMap)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: ldc 30
    //   5: invokevirtual 36	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   8: checkcast 38	java/lang/String
    //   11: astore 4
    //   13: aload 4
    //   15: invokestatic 44	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   18: ifne +198 -> 216
    //   21: ldc 14
    //   23: iconst_2
    //   24: anewarray 38	java/lang/String
    //   27: dup
    //   28: iconst_0
    //   29: ldc 8
    //   31: aastore
    //   32: dup
    //   33: iconst_1
    //   34: ldc 11
    //   36: aastore
    //   37: ldc 46
    //   39: iconst_1
    //   40: anewarray 38	java/lang/String
    //   43: dup
    //   44: iconst_0
    //   45: aload 4
    //   47: aastore
    //   48: invokestatic 52	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   51: astore_0
    //   52: aload_0
    //   53: ifnull +80 -> 133
    //   56: aload_0
    //   57: invokevirtual 58	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   60: ifle +73 -> 133
    //   63: aload_0
    //   64: invokevirtual 62	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   67: pop
    //   68: ldc 14
    //   70: aconst_null
    //   71: iconst_4
    //   72: anewarray 38	java/lang/String
    //   75: dup
    //   76: iconst_0
    //   77: ldc 8
    //   79: aastore
    //   80: dup
    //   81: iconst_1
    //   82: aload 4
    //   84: aastore
    //   85: dup
    //   86: iconst_2
    //   87: ldc 11
    //   89: aastore
    //   90: dup
    //   91: iconst_3
    //   92: aload_0
    //   93: aload_0
    //   94: ldc 11
    //   96: invokevirtual 66	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   99: invokevirtual 70	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   102: iconst_1
    //   103: iadd
    //   104: invokestatic 74	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   107: aastore
    //   108: invokestatic 80	cn/com/xy/sms/sdk/db/base/BaseManager:getContentValues	(Landroid/content/ContentValues;[Ljava/lang/String;)Landroid/content/ContentValues;
    //   111: ldc 46
    //   113: iconst_1
    //   114: anewarray 38	java/lang/String
    //   117: dup
    //   118: iconst_0
    //   119: aload 4
    //   121: aastore
    //   122: invokestatic 84	cn/com/xy/sms/sdk/db/DBManager:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   125: pop
    //   126: aload_0
    //   127: iconst_1
    //   128: invokestatic 88	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   131: lconst_0
    //   132: lreturn
    //   133: ldc 14
    //   135: aconst_null
    //   136: iconst_4
    //   137: anewarray 38	java/lang/String
    //   140: dup
    //   141: iconst_0
    //   142: ldc 8
    //   144: aastore
    //   145: dup
    //   146: iconst_1
    //   147: aload 4
    //   149: aastore
    //   150: dup
    //   151: iconst_2
    //   152: ldc 11
    //   154: aastore
    //   155: dup
    //   156: iconst_3
    //   157: ldc 90
    //   159: aastore
    //   160: invokestatic 80	cn/com/xy/sms/sdk/db/base/BaseManager:getContentValues	(Landroid/content/ContentValues;[Ljava/lang/String;)Landroid/content/ContentValues;
    //   163: invokestatic 94	cn/com/xy/sms/sdk/db/DBManager:insert	(Ljava/lang/String;Landroid/content/ContentValues;)J
    //   166: lstore_1
    //   167: aload_0
    //   168: iconst_1
    //   169: invokestatic 88	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   172: lload_1
    //   173: lreturn
    //   174: astore 4
    //   176: aload_3
    //   177: astore_0
    //   178: aload 4
    //   180: astore_3
    //   181: aload_3
    //   182: invokevirtual 97	java/lang/Throwable:printStackTrace	()V
    //   185: aload_0
    //   186: iconst_1
    //   187: invokestatic 88	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   190: ldc2_w 98
    //   193: lreturn
    //   194: astore_3
    //   195: aconst_null
    //   196: astore_0
    //   197: aload_0
    //   198: iconst_1
    //   199: invokestatic 88	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   202: aload_3
    //   203: athrow
    //   204: astore_3
    //   205: goto -8 -> 197
    //   208: astore_3
    //   209: goto -12 -> 197
    //   212: astore_3
    //   213: goto -32 -> 181
    //   216: aconst_null
    //   217: astore_0
    //   218: goto -166 -> 52
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	221	0	paramHashMap	java.util.HashMap<String, String>
    //   166	7	1	l	long
    //   1	181	3	localObject1	Object
    //   194	9	3	localObject2	Object
    //   204	1	3	localObject3	Object
    //   208	1	3	localObject4	Object
    //   212	1	3	localThrowable1	Throwable
    //   11	137	4	str	String
    //   174	5	4	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   13	52	174	java/lang/Throwable
    //   13	52	194	finally
    //   56	126	204	finally
    //   133	167	204	finally
    //   181	185	208	finally
    //   56	126	212	java/lang/Throwable
    //   133	167	212	java/lang/Throwable
  }
  
  /* Error */
  public static java.util.List<p> a()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: new 104	java/util/ArrayList
    //   8: dup
    //   9: invokespecial 105	java/util/ArrayList:<init>	()V
    //   12: astore 6
    //   14: ldc 14
    //   16: iconst_2
    //   17: anewarray 38	java/lang/String
    //   20: dup
    //   21: iconst_0
    //   22: ldc 8
    //   24: aastore
    //   25: dup
    //   26: iconst_1
    //   27: ldc 11
    //   29: aastore
    //   30: aconst_null
    //   31: aconst_null
    //   32: invokestatic 52	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   35: astore 5
    //   37: aload 5
    //   39: ifnull +65 -> 104
    //   42: aload 5
    //   44: astore_3
    //   45: aload 5
    //   47: astore 4
    //   49: aload 5
    //   51: invokevirtual 58	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   54: ifle +50 -> 104
    //   57: aload 5
    //   59: astore_3
    //   60: aload 5
    //   62: astore 4
    //   64: aload 5
    //   66: ldc 8
    //   68: invokevirtual 66	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   71: istore_0
    //   72: aload 5
    //   74: astore_3
    //   75: aload 5
    //   77: astore 4
    //   79: aload 5
    //   81: ldc 11
    //   83: invokevirtual 66	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   86: istore_1
    //   87: aload 5
    //   89: astore_3
    //   90: aload 5
    //   92: astore 4
    //   94: aload 5
    //   96: invokevirtual 62	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   99: istore_2
    //   100: iload_2
    //   101: ifne +12 -> 113
    //   104: aload 5
    //   106: iconst_1
    //   107: invokestatic 88	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   110: aload 6
    //   112: areturn
    //   113: aload 5
    //   115: astore_3
    //   116: aload 5
    //   118: astore 4
    //   120: new 107	cn/com/xy/sms/sdk/db/entity/p
    //   123: dup
    //   124: invokespecial 108	cn/com/xy/sms/sdk/db/entity/p:<init>	()V
    //   127: astore 7
    //   129: aload 5
    //   131: astore_3
    //   132: aload 5
    //   134: astore 4
    //   136: aload 7
    //   138: aload 5
    //   140: iload_0
    //   141: invokevirtual 111	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   144: putfield 113	cn/com/xy/sms/sdk/db/entity/p:a	Ljava/lang/String;
    //   147: aload 5
    //   149: astore_3
    //   150: aload 5
    //   152: astore 4
    //   154: aload 7
    //   156: aload 5
    //   158: iload_1
    //   159: invokevirtual 70	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   162: putfield 116	cn/com/xy/sms/sdk/db/entity/p:c	I
    //   165: aload 5
    //   167: astore_3
    //   168: aload 5
    //   170: astore 4
    //   172: aload 6
    //   174: aload 7
    //   176: invokeinterface 122 2 0
    //   181: pop
    //   182: goto -95 -> 87
    //   185: astore 5
    //   187: aload_3
    //   188: astore 4
    //   190: aload 5
    //   192: invokevirtual 97	java/lang/Throwable:printStackTrace	()V
    //   195: aload_3
    //   196: iconst_1
    //   197: invokestatic 88	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   200: aload 6
    //   202: areturn
    //   203: astore_3
    //   204: aload 4
    //   206: iconst_1
    //   207: invokestatic 88	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   210: aload_3
    //   211: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   71	70	0	i	int
    //   86	73	1	j	int
    //   99	2	2	bool	boolean
    //   4	192	3	localObject1	Object
    //   203	8	3	localObject2	Object
    //   1	204	4	localObject3	Object
    //   35	134	5	localXyCursor	cn.com.xy.sms.sdk.db.XyCursor
    //   185	6	5	localThrowable	Throwable
    //   12	189	6	localArrayList	java.util.ArrayList
    //   127	48	7	localp	p
    // Exception table:
    //   from	to	target	type
    //   14	37	185	java/lang/Throwable
    //   49	57	185	java/lang/Throwable
    //   64	72	185	java/lang/Throwable
    //   79	87	185	java/lang/Throwable
    //   94	100	185	java/lang/Throwable
    //   120	129	185	java/lang/Throwable
    //   136	147	185	java/lang/Throwable
    //   154	165	185	java/lang/Throwable
    //   172	182	185	java/lang/Throwable
    //   14	37	203	finally
    //   49	57	203	finally
    //   64	72	203	finally
    //   79	87	203	finally
    //   94	100	203	finally
    //   120	129	203	finally
    //   136	147	203	finally
    //   154	165	203	finally
    //   172	182	203	finally
    //   190	195	203	finally
  }
  
  private static void a(String paramString)
  {
    try
    {
      DBManager.delete("tb_count_scene", "scene_id = ?", new String[] { paramString });
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void b()
  {
    try
    {
      DBManager.delete("tb_count_scene", null, null);
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */