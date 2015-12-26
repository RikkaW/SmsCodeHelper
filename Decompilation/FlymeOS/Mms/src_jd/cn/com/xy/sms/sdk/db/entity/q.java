package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.util.StringUtils;

public final class q
{
  private static int a = 0;
  private static int b = 1;
  private static int c = 0;
  private static int d = 1;
  private static String e = "scene_id";
  private static String f = "sceneVersion";
  private static String g = "sceneType";
  private static String h = "tb_scene_config";
  private static String i = "isCheck";
  private static String j = "isUse";
  private static String k = "useCount";
  private static String l = " DROP TABLE IF EXISTS tb_scene_config";
  private static String m = "create table  if not exists tb_scene_config (scene_id TEXT,sceneType INTEGER DEFAULT '0',isCheck INTEGER DEFAULT '0',sceneVersion TEXT,isUse INTEGER DEFAULT '0',useCount INTEGER DEFAULT '0')";
  private static String n = "ALTER TABLE tb_scene_config ADD COLUMN sceneType INTEGER DEFAULT '-1'";
  private static String o = "ALTER TABLE tb_scene_config ADD COLUMN isCheck INTEGER DEFAULT '0'";
  private static String p = "ALTER TABLE tb_scene_config ADD COLUMN useCount INTEGER DEFAULT '0'";
  private static String q = "ALTER TABLE tb_scene_config ADD COLUMN isUse INTEGER DEFAULT '0'";
  
  /* Error */
  public static long a(p paramp, int paramInt)
  {
    // Byte code:
    //   0: ldc2_w 60
    //   3: lstore_2
    //   4: aconst_null
    //   5: astore 7
    //   7: aconst_null
    //   8: astore 8
    //   10: aconst_null
    //   11: astore 4
    //   13: aload 7
    //   15: astore 6
    //   17: aload 8
    //   19: astore 5
    //   21: aload_0
    //   22: getfield 65	cn/com/xy/sms/sdk/db/entity/p:a	Ljava/lang/String;
    //   25: invokestatic 71	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   28: ifne +81 -> 109
    //   31: aload 7
    //   33: astore 6
    //   35: aload 8
    //   37: astore 5
    //   39: aload_0
    //   40: getfield 65	cn/com/xy/sms/sdk/db/entity/p:a	Ljava/lang/String;
    //   43: astore 4
    //   45: aload 7
    //   47: astore 6
    //   49: aload 8
    //   51: astore 5
    //   53: new 73	java/lang/StringBuilder
    //   56: dup
    //   57: iload_1
    //   58: invokestatic 79	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   61: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   64: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   67: astore 9
    //   69: aload 7
    //   71: astore 6
    //   73: aload 8
    //   75: astore 5
    //   77: ldc 24
    //   79: iconst_1
    //   80: anewarray 75	java/lang/String
    //   83: dup
    //   84: iconst_0
    //   85: ldc 15
    //   87: aastore
    //   88: ldc 88
    //   90: iconst_2
    //   91: anewarray 75	java/lang/String
    //   94: dup
    //   95: iconst_0
    //   96: aload 4
    //   98: aastore
    //   99: dup
    //   100: iconst_1
    //   101: aload 9
    //   103: aastore
    //   104: invokestatic 94	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   107: astore 4
    //   109: aload 4
    //   111: astore 6
    //   113: aload 4
    //   115: astore 5
    //   117: new 96	android/content/ContentValues
    //   120: dup
    //   121: invokespecial 97	android/content/ContentValues:<init>	()V
    //   124: astore 7
    //   126: aload 4
    //   128: astore 6
    //   130: aload 4
    //   132: astore 5
    //   134: aload_0
    //   135: getfield 99	cn/com/xy/sms/sdk/db/entity/p:b	Ljava/lang/String;
    //   138: invokestatic 71	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   141: ifne +22 -> 163
    //   144: aload 4
    //   146: astore 6
    //   148: aload 4
    //   150: astore 5
    //   152: aload 7
    //   154: ldc 18
    //   156: aload_0
    //   157: getfield 99	cn/com/xy/sms/sdk/db/entity/p:b	Ljava/lang/String;
    //   160: invokevirtual 103	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   163: aload 4
    //   165: astore 6
    //   167: aload 4
    //   169: astore 5
    //   171: aload 7
    //   173: ldc 15
    //   175: aload_0
    //   176: getfield 65	cn/com/xy/sms/sdk/db/entity/p:a	Ljava/lang/String;
    //   179: invokevirtual 103	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   182: aload 4
    //   184: astore 6
    //   186: aload 4
    //   188: astore 5
    //   190: aload 7
    //   192: ldc 21
    //   194: iload_1
    //   195: invokestatic 108	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   198: invokevirtual 111	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   201: aload 4
    //   203: astore 6
    //   205: aload 4
    //   207: astore 5
    //   209: aload 7
    //   211: ldc 27
    //   213: aload_0
    //   214: getfield 113	cn/com/xy/sms/sdk/db/entity/p:d	I
    //   217: invokestatic 108	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   220: invokevirtual 111	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   223: aload 4
    //   225: ifnull +73 -> 298
    //   228: aload 4
    //   230: astore 6
    //   232: aload 4
    //   234: astore 5
    //   236: aload 4
    //   238: invokevirtual 119	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   241: ifle +57 -> 298
    //   244: aload 4
    //   246: astore 6
    //   248: aload 4
    //   250: astore 5
    //   252: ldc 24
    //   254: aload 7
    //   256: ldc 121
    //   258: iconst_2
    //   259: anewarray 75	java/lang/String
    //   262: dup
    //   263: iconst_0
    //   264: aload_0
    //   265: getfield 65	cn/com/xy/sms/sdk/db/entity/p:a	Ljava/lang/String;
    //   268: aastore
    //   269: dup
    //   270: iconst_1
    //   271: new 73	java/lang/StringBuilder
    //   274: dup
    //   275: iload_1
    //   276: invokestatic 79	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   279: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   282: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   285: aastore
    //   286: invokestatic 125	cn/com/xy/sms/sdk/db/DBManager:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   289: pop
    //   290: aload 4
    //   292: iconst_1
    //   293: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   296: lload_2
    //   297: lreturn
    //   298: aload 4
    //   300: astore 6
    //   302: aload 4
    //   304: astore 5
    //   306: ldc 24
    //   308: aload 7
    //   310: invokestatic 133	cn/com/xy/sms/sdk/db/DBManager:insert	(Ljava/lang/String;Landroid/content/ContentValues;)J
    //   313: lstore_2
    //   314: goto -24 -> 290
    //   317: astore_0
    //   318: aload 6
    //   320: astore 5
    //   322: aload_0
    //   323: invokevirtual 136	java/lang/Throwable:printStackTrace	()V
    //   326: aload 6
    //   328: iconst_1
    //   329: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   332: ldc2_w 60
    //   335: lreturn
    //   336: astore_0
    //   337: aload 5
    //   339: iconst_1
    //   340: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   343: aload_0
    //   344: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	345	0	paramp	p
    //   0	345	1	paramInt	int
    //   3	311	2	l1	long
    //   11	292	4	localObject1	Object
    //   19	319	5	localObject2	Object
    //   15	312	6	localObject3	Object
    //   5	304	7	localContentValues	ContentValues
    //   8	66	8	localObject4	Object
    //   67	35	9	str	String
    // Exception table:
    //   from	to	target	type
    //   21	31	317	java/lang/Throwable
    //   39	45	317	java/lang/Throwable
    //   53	69	317	java/lang/Throwable
    //   77	109	317	java/lang/Throwable
    //   117	126	317	java/lang/Throwable
    //   134	144	317	java/lang/Throwable
    //   152	163	317	java/lang/Throwable
    //   171	182	317	java/lang/Throwable
    //   190	201	317	java/lang/Throwable
    //   209	223	317	java/lang/Throwable
    //   236	244	317	java/lang/Throwable
    //   252	290	317	java/lang/Throwable
    //   306	314	317	java/lang/Throwable
    //   21	31	336	finally
    //   39	45	336	finally
    //   53	69	336	finally
    //   77	109	336	finally
    //   117	126	336	finally
    //   134	144	336	finally
    //   152	163	336	finally
    //   171	182	336	finally
    //   190	201	336	finally
    //   209	223	336	finally
    //   236	244	336	finally
    //   252	290	336	finally
    //   306	314	336	finally
    //   322	326	336	finally
  }
  
  private static ContentValues a(p paramp)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("useCount", Integer.valueOf(c));
    localContentValues.put("isUse", Integer.valueOf(e));
    return localContentValues;
  }
  
  /* Error */
  public static p a(String paramString, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: iload_1
    //   4: iconst_1
    //   5: if_icmpne +135 -> 140
    //   8: new 73	java/lang/StringBuilder
    //   11: dup
    //   12: ldc -112
    //   14: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   17: iload_1
    //   18: invokevirtual 148	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   21: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   24: astore 4
    //   26: new 73	java/lang/StringBuilder
    //   29: dup
    //   30: aload_0
    //   31: invokestatic 151	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   34: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   37: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   40: astore_0
    //   41: ldc 24
    //   43: iconst_2
    //   44: anewarray 75	java/lang/String
    //   47: dup
    //   48: iconst_0
    //   49: ldc 15
    //   51: aastore
    //   52: dup
    //   53: iconst_1
    //   54: ldc 18
    //   56: aastore
    //   57: aload 4
    //   59: iconst_1
    //   60: anewarray 75	java/lang/String
    //   63: dup
    //   64: iconst_0
    //   65: aload_0
    //   66: aastore
    //   67: aconst_null
    //   68: aconst_null
    //   69: aconst_null
    //   70: ldc -103
    //   72: invokestatic 156	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   75: astore 4
    //   77: aload 4
    //   79: ifnull +142 -> 221
    //   82: aload 4
    //   84: astore_0
    //   85: aload 4
    //   87: invokevirtual 119	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   90: ifle +131 -> 221
    //   93: aload 4
    //   95: astore_0
    //   96: aload 4
    //   98: ldc 18
    //   100: invokevirtual 160	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   103: istore_1
    //   104: aload 4
    //   106: astore_0
    //   107: aload 4
    //   109: ldc 15
    //   111: invokevirtual 160	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   114: istore_2
    //   115: aconst_null
    //   116: astore 5
    //   118: aload 4
    //   120: astore_0
    //   121: aload 4
    //   123: invokevirtual 164	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   126: istore_3
    //   127: iload_3
    //   128: ifne +19 -> 147
    //   131: aload 4
    //   133: iconst_1
    //   134: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   137: aload 5
    //   139: areturn
    //   140: ldc -90
    //   142: astore 4
    //   144: goto -118 -> 26
    //   147: aload 4
    //   149: astore_0
    //   150: new 63	cn/com/xy/sms/sdk/db/entity/p
    //   153: dup
    //   154: invokespecial 167	cn/com/xy/sms/sdk/db/entity/p:<init>	()V
    //   157: astore 5
    //   159: aload 4
    //   161: astore_0
    //   162: aload 5
    //   164: aload 4
    //   166: iload_1
    //   167: invokevirtual 170	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   170: putfield 99	cn/com/xy/sms/sdk/db/entity/p:b	Ljava/lang/String;
    //   173: aload 4
    //   175: astore_0
    //   176: aload 5
    //   178: aload 4
    //   180: iload_2
    //   181: invokevirtual 170	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   184: putfield 65	cn/com/xy/sms/sdk/db/entity/p:a	Ljava/lang/String;
    //   187: goto -69 -> 118
    //   190: astore 5
    //   192: aload 4
    //   194: astore_0
    //   195: aload 5
    //   197: invokevirtual 136	java/lang/Throwable:printStackTrace	()V
    //   200: aload 4
    //   202: iconst_1
    //   203: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   206: aconst_null
    //   207: areturn
    //   208: astore_0
    //   209: aload 5
    //   211: astore 4
    //   213: aload 4
    //   215: iconst_1
    //   216: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   219: aload_0
    //   220: athrow
    //   221: aload 4
    //   223: iconst_1
    //   224: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   227: goto -21 -> 206
    //   230: astore 5
    //   232: aload_0
    //   233: astore 4
    //   235: aload 5
    //   237: astore_0
    //   238: goto -25 -> 213
    //   241: astore 5
    //   243: aconst_null
    //   244: astore 4
    //   246: goto -54 -> 192
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	249	0	paramString	String
    //   0	249	1	paramInt	int
    //   114	67	2	i1	int
    //   126	2	3	bool	boolean
    //   24	221	4	localObject1	Object
    //   1	176	5	localp	p
    //   190	20	5	localThrowable1	Throwable
    //   230	6	5	localObject2	Object
    //   241	1	5	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   85	93	190	java/lang/Throwable
    //   96	104	190	java/lang/Throwable
    //   107	115	190	java/lang/Throwable
    //   121	127	190	java/lang/Throwable
    //   150	159	190	java/lang/Throwable
    //   162	173	190	java/lang/Throwable
    //   176	187	190	java/lang/Throwable
    //   8	26	208	finally
    //   26	77	208	finally
    //   85	93	230	finally
    //   96	104	230	finally
    //   107	115	230	finally
    //   121	127	230	finally
    //   150	159	230	finally
    //   162	173	230	finally
    //   176	187	230	finally
    //   195	200	230	finally
    //   8	26	241	java/lang/Throwable
    //   26	77	241	java/lang/Throwable
  }
  
  /* Error */
  public static java.util.List<p> a(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 8
    //   3: aconst_null
    //   4: astore 7
    //   6: new 173	java/util/ArrayList
    //   9: dup
    //   10: invokespecial 174	java/util/ArrayList:<init>	()V
    //   13: astore 9
    //   15: iload_0
    //   16: iconst_1
    //   17: if_icmpne +172 -> 189
    //   20: aload 7
    //   22: astore 5
    //   24: aload 8
    //   26: astore 4
    //   28: new 73	java/lang/StringBuilder
    //   31: dup
    //   32: ldc -80
    //   34: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   37: iload_0
    //   38: invokevirtual 148	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   41: ldc -78
    //   43: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: ldc -73
    //   48: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   54: astore 6
    //   56: aload 7
    //   58: astore 5
    //   60: aload 8
    //   62: astore 4
    //   64: ldc 24
    //   66: iconst_3
    //   67: anewarray 75	java/lang/String
    //   70: dup
    //   71: iconst_0
    //   72: ldc 15
    //   74: aastore
    //   75: dup
    //   76: iconst_1
    //   77: ldc 18
    //   79: aastore
    //   80: dup
    //   81: iconst_2
    //   82: ldc 33
    //   84: aastore
    //   85: aload 6
    //   87: aconst_null
    //   88: invokestatic 94	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   91: astore 6
    //   93: aload 6
    //   95: ifnull +85 -> 180
    //   98: aload 6
    //   100: astore 5
    //   102: aload 6
    //   104: astore 4
    //   106: aload 6
    //   108: invokevirtual 119	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   111: ifle +69 -> 180
    //   114: aload 6
    //   116: astore 5
    //   118: aload 6
    //   120: astore 4
    //   122: aload 6
    //   124: ldc 15
    //   126: invokevirtual 160	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   129: istore_0
    //   130: aload 6
    //   132: astore 5
    //   134: aload 6
    //   136: astore 4
    //   138: aload 6
    //   140: ldc 18
    //   142: invokevirtual 160	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   145: istore_1
    //   146: aload 6
    //   148: astore 5
    //   150: aload 6
    //   152: astore 4
    //   154: aload 6
    //   156: ldc 33
    //   158: invokevirtual 160	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   161: istore_2
    //   162: aload 6
    //   164: astore 5
    //   166: aload 6
    //   168: astore 4
    //   170: aload 6
    //   172: invokevirtual 164	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   175: istore_3
    //   176: iload_3
    //   177: ifne +19 -> 196
    //   180: aload 6
    //   182: iconst_1
    //   183: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   186: aload 9
    //   188: areturn
    //   189: ldc -71
    //   191: astore 6
    //   193: goto -137 -> 56
    //   196: aload 6
    //   198: astore 5
    //   200: aload 6
    //   202: astore 4
    //   204: new 63	cn/com/xy/sms/sdk/db/entity/p
    //   207: dup
    //   208: invokespecial 167	cn/com/xy/sms/sdk/db/entity/p:<init>	()V
    //   211: astore 7
    //   213: aload 6
    //   215: astore 5
    //   217: aload 6
    //   219: astore 4
    //   221: aload 7
    //   223: aload 6
    //   225: iload_0
    //   226: invokevirtual 170	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   229: putfield 65	cn/com/xy/sms/sdk/db/entity/p:a	Ljava/lang/String;
    //   232: aload 6
    //   234: astore 5
    //   236: aload 6
    //   238: astore 4
    //   240: aload 7
    //   242: aload 6
    //   244: iload_1
    //   245: invokevirtual 170	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   248: putfield 99	cn/com/xy/sms/sdk/db/entity/p:b	Ljava/lang/String;
    //   251: aload 6
    //   253: astore 5
    //   255: aload 6
    //   257: astore 4
    //   259: aload 7
    //   261: aload 6
    //   263: iload_2
    //   264: invokevirtual 189	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   267: putfield 139	cn/com/xy/sms/sdk/db/entity/p:c	I
    //   270: aload 6
    //   272: astore 5
    //   274: aload 6
    //   276: astore 4
    //   278: aload 9
    //   280: aload 7
    //   282: invokeinterface 195 2 0
    //   287: pop
    //   288: goto -126 -> 162
    //   291: astore 6
    //   293: aload 5
    //   295: astore 4
    //   297: aload 6
    //   299: invokevirtual 136	java/lang/Throwable:printStackTrace	()V
    //   302: aload 5
    //   304: iconst_1
    //   305: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   308: aload 9
    //   310: areturn
    //   311: astore 5
    //   313: aload 4
    //   315: iconst_1
    //   316: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   319: aload 5
    //   321: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	322	0	paramInt	int
    //   145	100	1	i1	int
    //   161	103	2	i2	int
    //   175	2	3	bool	boolean
    //   26	288	4	localObject1	Object
    //   22	281	5	localObject2	Object
    //   311	9	5	localObject3	Object
    //   54	221	6	localObject4	Object
    //   291	7	6	localThrowable	Throwable
    //   4	277	7	localp	p
    //   1	60	8	localObject5	Object
    //   13	296	9	localArrayList	java.util.ArrayList
    // Exception table:
    //   from	to	target	type
    //   28	56	291	java/lang/Throwable
    //   64	93	291	java/lang/Throwable
    //   106	114	291	java/lang/Throwable
    //   122	130	291	java/lang/Throwable
    //   138	146	291	java/lang/Throwable
    //   154	162	291	java/lang/Throwable
    //   170	176	291	java/lang/Throwable
    //   204	213	291	java/lang/Throwable
    //   221	232	291	java/lang/Throwable
    //   240	251	291	java/lang/Throwable
    //   259	270	291	java/lang/Throwable
    //   278	288	291	java/lang/Throwable
    //   28	56	311	finally
    //   64	93	311	finally
    //   106	114	311	finally
    //   122	130	311	finally
    //   138	146	311	finally
    //   154	162	311	finally
    //   170	176	311	finally
    //   204	213	311	finally
    //   221	232	311	finally
    //   240	251	311	finally
    //   259	270	311	finally
    //   278	288	311	finally
    //   297	302	311	finally
  }
  
  /* Error */
  public static java.util.List<p> a(int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aconst_null
    //   4: astore 6
    //   6: new 173	java/util/ArrayList
    //   9: dup
    //   10: invokespecial 174	java/util/ArrayList:<init>	()V
    //   13: astore 8
    //   15: iload_0
    //   16: iconst_1
    //   17: if_icmpne +145 -> 162
    //   20: aload 6
    //   22: astore 4
    //   24: aload 7
    //   26: astore_3
    //   27: new 73	java/lang/StringBuilder
    //   30: dup
    //   31: ldc -80
    //   33: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   36: iload_0
    //   37: invokevirtual 148	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   40: ldc -56
    //   42: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: ldc -54
    //   47: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: astore 5
    //   55: aload 6
    //   57: astore 4
    //   59: aload 7
    //   61: astore_3
    //   62: ldc 24
    //   64: iconst_2
    //   65: anewarray 75	java/lang/String
    //   68: dup
    //   69: iconst_0
    //   70: ldc 15
    //   72: aastore
    //   73: dup
    //   74: iconst_1
    //   75: ldc 18
    //   77: aastore
    //   78: aload 5
    //   80: aconst_null
    //   81: invokestatic 94	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   84: astore 5
    //   86: aload 5
    //   88: ifnull +65 -> 153
    //   91: aload 5
    //   93: astore 4
    //   95: aload 5
    //   97: astore_3
    //   98: aload 5
    //   100: invokevirtual 119	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   103: ifle +50 -> 153
    //   106: aload 5
    //   108: astore 4
    //   110: aload 5
    //   112: astore_3
    //   113: aload 5
    //   115: ldc 15
    //   117: invokevirtual 160	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   120: istore_0
    //   121: aload 5
    //   123: astore 4
    //   125: aload 5
    //   127: astore_3
    //   128: aload 5
    //   130: ldc 18
    //   132: invokevirtual 160	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   135: istore_1
    //   136: aload 5
    //   138: astore 4
    //   140: aload 5
    //   142: astore_3
    //   143: aload 5
    //   145: invokevirtual 164	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   148: istore_2
    //   149: iload_2
    //   150: ifne +36 -> 186
    //   153: aload 5
    //   155: iconst_1
    //   156: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   159: aload 8
    //   161: areturn
    //   162: aload 6
    //   164: astore 4
    //   166: aload 7
    //   168: astore_3
    //   169: new 73	java/lang/StringBuilder
    //   172: dup
    //   173: ldc -52
    //   175: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   178: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   181: astore 5
    //   183: goto -128 -> 55
    //   186: aload 5
    //   188: astore 4
    //   190: aload 5
    //   192: astore_3
    //   193: new 63	cn/com/xy/sms/sdk/db/entity/p
    //   196: dup
    //   197: invokespecial 167	cn/com/xy/sms/sdk/db/entity/p:<init>	()V
    //   200: astore 6
    //   202: aload 5
    //   204: astore 4
    //   206: aload 5
    //   208: astore_3
    //   209: aload 6
    //   211: aload 5
    //   213: iload_0
    //   214: invokevirtual 170	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   217: putfield 65	cn/com/xy/sms/sdk/db/entity/p:a	Ljava/lang/String;
    //   220: aload 5
    //   222: astore 4
    //   224: aload 5
    //   226: astore_3
    //   227: aload 6
    //   229: aload 5
    //   231: iload_1
    //   232: invokevirtual 170	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   235: putfield 99	cn/com/xy/sms/sdk/db/entity/p:b	Ljava/lang/String;
    //   238: aload 5
    //   240: astore 4
    //   242: aload 5
    //   244: astore_3
    //   245: aload 8
    //   247: aload 6
    //   249: invokeinterface 195 2 0
    //   254: pop
    //   255: goto -119 -> 136
    //   258: astore 5
    //   260: aload 4
    //   262: astore_3
    //   263: aload 5
    //   265: invokevirtual 136	java/lang/Throwable:printStackTrace	()V
    //   268: aload 4
    //   270: iconst_1
    //   271: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   274: aload 8
    //   276: areturn
    //   277: astore 4
    //   279: aload_3
    //   280: iconst_1
    //   281: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   284: aload 4
    //   286: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	287	0	paramInt1	int
    //   0	287	1	paramInt2	int
    //   148	2	2	bool	boolean
    //   26	254	3	localObject1	Object
    //   22	247	4	localObject2	Object
    //   277	8	4	localObject3	Object
    //   53	190	5	localObject4	Object
    //   258	6	5	localThrowable	Throwable
    //   4	244	6	localp	p
    //   1	166	7	localObject5	Object
    //   13	262	8	localArrayList	java.util.ArrayList
    // Exception table:
    //   from	to	target	type
    //   27	55	258	java/lang/Throwable
    //   62	86	258	java/lang/Throwable
    //   98	106	258	java/lang/Throwable
    //   113	121	258	java/lang/Throwable
    //   128	136	258	java/lang/Throwable
    //   143	149	258	java/lang/Throwable
    //   169	183	258	java/lang/Throwable
    //   193	202	258	java/lang/Throwable
    //   209	220	258	java/lang/Throwable
    //   227	238	258	java/lang/Throwable
    //   245	255	258	java/lang/Throwable
    //   27	55	277	finally
    //   62	86	277	finally
    //   98	106	277	finally
    //   113	121	277	finally
    //   128	136	277	finally
    //   143	149	277	finally
    //   169	183	277	finally
    //   193	202	277	finally
    //   209	220	277	finally
    //   227	238	277	finally
    //   245	255	277	finally
    //   263	268	277	finally
  }
  
  public static void a()
  {
    try
    {
      DBManager.delete("tb_scene_config", null, null);
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  /* Error */
  public static void a(String paramString)
  {
    // Byte code:
    //   0: new 63	cn/com/xy/sms/sdk/db/entity/p
    //   3: dup
    //   4: invokespecial 167	cn/com/xy/sms/sdk/db/entity/p:<init>	()V
    //   7: astore 6
    //   9: aload 6
    //   11: aload_0
    //   12: putfield 65	cn/com/xy/sms/sdk/db/entity/p:a	Ljava/lang/String;
    //   15: aload 6
    //   17: iconst_1
    //   18: putfield 139	cn/com/xy/sms/sdk/db/entity/p:c	I
    //   21: aconst_null
    //   22: astore 4
    //   24: aconst_null
    //   25: astore 5
    //   27: aconst_null
    //   28: astore_0
    //   29: aload 4
    //   31: astore_3
    //   32: aload 5
    //   34: astore_2
    //   35: aload 6
    //   37: getfield 65	cn/com/xy/sms/sdk/db/entity/p:a	Ljava/lang/String;
    //   40: invokestatic 71	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   43: ifne +56 -> 99
    //   46: aload 4
    //   48: astore_3
    //   49: aload 5
    //   51: astore_2
    //   52: aload 6
    //   54: getfield 65	cn/com/xy/sms/sdk/db/entity/p:a	Ljava/lang/String;
    //   57: astore_0
    //   58: aload 4
    //   60: astore_3
    //   61: aload 5
    //   63: astore_2
    //   64: ldc 24
    //   66: iconst_3
    //   67: anewarray 75	java/lang/String
    //   70: dup
    //   71: iconst_0
    //   72: ldc 15
    //   74: aastore
    //   75: dup
    //   76: iconst_1
    //   77: ldc 30
    //   79: aastore
    //   80: dup
    //   81: iconst_2
    //   82: ldc 33
    //   84: aastore
    //   85: ldc -45
    //   87: iconst_1
    //   88: anewarray 75	java/lang/String
    //   91: dup
    //   92: iconst_0
    //   93: aload_0
    //   94: aastore
    //   95: invokestatic 94	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   98: astore_0
    //   99: aload_0
    //   100: ifnull +27 -> 127
    //   103: aload_0
    //   104: astore_3
    //   105: aload_0
    //   106: astore_2
    //   107: aload_0
    //   108: invokevirtual 119	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   111: ifle +16 -> 127
    //   114: aload_0
    //   115: astore_3
    //   116: aload_0
    //   117: astore_2
    //   118: aload_0
    //   119: invokevirtual 164	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   122: istore_1
    //   123: iload_1
    //   124: ifne +9 -> 133
    //   127: aload_0
    //   128: iconst_1
    //   129: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   132: return
    //   133: aload_0
    //   134: astore_3
    //   135: aload_0
    //   136: astore_2
    //   137: aload 6
    //   139: iconst_1
    //   140: putfield 141	cn/com/xy/sms/sdk/db/entity/p:e	I
    //   143: aload_0
    //   144: astore_3
    //   145: aload_0
    //   146: astore_2
    //   147: aload 6
    //   149: iconst_0
    //   150: putfield 139	cn/com/xy/sms/sdk/db/entity/p:c	I
    //   153: aload_0
    //   154: astore_3
    //   155: aload_0
    //   156: astore_2
    //   157: aload 6
    //   159: invokestatic 213	cn/com/xy/sms/sdk/db/entity/q:a	(Lcn/com/xy/sms/sdk/db/entity/p;)Landroid/content/ContentValues;
    //   162: astore 4
    //   164: aload_0
    //   165: astore_3
    //   166: aload_0
    //   167: astore_2
    //   168: new 73	java/lang/StringBuilder
    //   171: dup
    //   172: ldc -41
    //   174: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   177: aload 4
    //   179: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   182: pop
    //   183: aload_0
    //   184: astore_3
    //   185: aload_0
    //   186: astore_2
    //   187: ldc 24
    //   189: aload 4
    //   191: ldc -45
    //   193: iconst_1
    //   194: anewarray 75	java/lang/String
    //   197: dup
    //   198: iconst_0
    //   199: new 73	java/lang/StringBuilder
    //   202: dup
    //   203: aload 6
    //   205: getfield 65	cn/com/xy/sms/sdk/db/entity/p:a	Ljava/lang/String;
    //   208: invokestatic 151	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   211: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   214: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   217: aastore
    //   218: invokestatic 125	cn/com/xy/sms/sdk/db/DBManager:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   221: pop
    //   222: goto -108 -> 114
    //   225: astore_0
    //   226: aload_3
    //   227: astore_2
    //   228: aload_0
    //   229: invokevirtual 136	java/lang/Throwable:printStackTrace	()V
    //   232: aload_3
    //   233: iconst_1
    //   234: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   237: return
    //   238: astore_0
    //   239: aload_2
    //   240: iconst_1
    //   241: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   244: aload_0
    //   245: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	246	0	paramString	String
    //   122	2	1	bool	boolean
    //   34	206	2	localObject1	Object
    //   31	202	3	localObject2	Object
    //   22	168	4	localContentValues	ContentValues
    //   25	37	5	localObject3	Object
    //   7	197	6	localp	p
    // Exception table:
    //   from	to	target	type
    //   35	46	225	java/lang/Throwable
    //   52	58	225	java/lang/Throwable
    //   64	99	225	java/lang/Throwable
    //   107	114	225	java/lang/Throwable
    //   118	123	225	java/lang/Throwable
    //   137	143	225	java/lang/Throwable
    //   147	153	225	java/lang/Throwable
    //   157	164	225	java/lang/Throwable
    //   168	183	225	java/lang/Throwable
    //   187	222	225	java/lang/Throwable
    //   35	46	238	finally
    //   52	58	238	finally
    //   64	99	238	finally
    //   107	114	238	finally
    //   118	123	238	finally
    //   137	143	238	finally
    //   147	153	238	finally
    //   157	164	238	finally
    //   168	183	238	finally
    //   187	222	238	finally
    //   228	232	238	finally
  }
  
  public static void a(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("scene_id", paramString);
      localContentValues.put("sceneType", Integer.valueOf(paramInt1));
      localContentValues.put("isCheck", Integer.valueOf(1));
      DBManager.update("tb_scene_config", localContentValues, "scene_id = ? and sceneType = ?", new String[] { paramString, paramInt1 });
      return;
    }
    catch (Throwable paramString) {}
  }
  
  /* Error */
  public static void a(String paramString1, String paramString2, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 7
    //   6: aconst_null
    //   7: astore_3
    //   8: aload 6
    //   10: astore 5
    //   12: aload 7
    //   14: astore 4
    //   16: aload_0
    //   17: invokestatic 71	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   20: ifne +63 -> 83
    //   23: aload 6
    //   25: astore 5
    //   27: aload 7
    //   29: astore 4
    //   31: new 73	java/lang/StringBuilder
    //   34: dup
    //   35: iload_2
    //   36: invokestatic 79	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   39: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   42: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: astore_3
    //   46: aload 6
    //   48: astore 5
    //   50: aload 7
    //   52: astore 4
    //   54: ldc 24
    //   56: iconst_1
    //   57: anewarray 75	java/lang/String
    //   60: dup
    //   61: iconst_0
    //   62: ldc 15
    //   64: aastore
    //   65: ldc 121
    //   67: iconst_2
    //   68: anewarray 75	java/lang/String
    //   71: dup
    //   72: iconst_0
    //   73: aload_0
    //   74: aastore
    //   75: dup
    //   76: iconst_1
    //   77: aload_3
    //   78: aastore
    //   79: invokestatic 94	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   82: astore_3
    //   83: aload_3
    //   84: astore 5
    //   86: aload_3
    //   87: astore 4
    //   89: new 96	android/content/ContentValues
    //   92: dup
    //   93: invokespecial 97	android/content/ContentValues:<init>	()V
    //   96: astore 6
    //   98: aload_3
    //   99: astore 5
    //   101: aload_3
    //   102: astore 4
    //   104: aload 6
    //   106: ldc 15
    //   108: aload_0
    //   109: invokevirtual 103	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   112: aload_3
    //   113: astore 5
    //   115: aload_3
    //   116: astore 4
    //   118: aload 6
    //   120: ldc 21
    //   122: iload_2
    //   123: invokestatic 108	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   126: invokevirtual 111	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   129: aload_3
    //   130: astore 5
    //   132: aload_3
    //   133: astore 4
    //   135: aload_1
    //   136: invokestatic 71	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   139: ifne +17 -> 156
    //   142: aload_3
    //   143: astore 5
    //   145: aload_3
    //   146: astore 4
    //   148: aload 6
    //   150: ldc 18
    //   152: aload_1
    //   153: invokevirtual 103	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   156: aload_3
    //   157: ifnull +63 -> 220
    //   160: aload_3
    //   161: astore 5
    //   163: aload_3
    //   164: astore 4
    //   166: aload_3
    //   167: invokevirtual 119	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   170: ifle +50 -> 220
    //   173: aload_3
    //   174: astore 5
    //   176: aload_3
    //   177: astore 4
    //   179: ldc 24
    //   181: aload 6
    //   183: ldc 121
    //   185: iconst_2
    //   186: anewarray 75	java/lang/String
    //   189: dup
    //   190: iconst_0
    //   191: aload_0
    //   192: aastore
    //   193: dup
    //   194: iconst_1
    //   195: new 73	java/lang/StringBuilder
    //   198: dup
    //   199: iload_2
    //   200: invokestatic 79	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   203: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   206: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   209: aastore
    //   210: invokestatic 125	cn/com/xy/sms/sdk/db/DBManager:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   213: pop
    //   214: aload_3
    //   215: iconst_1
    //   216: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   219: return
    //   220: aload_3
    //   221: astore 5
    //   223: aload_3
    //   224: astore 4
    //   226: ldc 24
    //   228: aload 6
    //   230: invokestatic 133	cn/com/xy/sms/sdk/db/DBManager:insert	(Ljava/lang/String;Landroid/content/ContentValues;)J
    //   233: pop2
    //   234: goto -20 -> 214
    //   237: astore_0
    //   238: aload 5
    //   240: astore 4
    //   242: aload_0
    //   243: invokevirtual 136	java/lang/Throwable:printStackTrace	()V
    //   246: aload 5
    //   248: iconst_1
    //   249: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   252: return
    //   253: astore_0
    //   254: aload 4
    //   256: iconst_1
    //   257: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   260: aload_0
    //   261: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	262	0	paramString1	String
    //   0	262	1	paramString2	String
    //   0	262	2	paramInt	int
    //   7	217	3	localObject1	Object
    //   14	241	4	localObject2	Object
    //   10	237	5	localObject3	Object
    //   1	228	6	localContentValues	ContentValues
    //   4	47	7	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   16	23	237	java/lang/Throwable
    //   31	46	237	java/lang/Throwable
    //   54	83	237	java/lang/Throwable
    //   89	98	237	java/lang/Throwable
    //   104	112	237	java/lang/Throwable
    //   118	129	237	java/lang/Throwable
    //   135	142	237	java/lang/Throwable
    //   148	156	237	java/lang/Throwable
    //   166	173	237	java/lang/Throwable
    //   179	214	237	java/lang/Throwable
    //   226	234	237	java/lang/Throwable
    //   16	23	253	finally
    //   31	46	253	finally
    //   54	83	253	finally
    //   89	98	253	finally
    //   104	112	253	finally
    //   118	129	253	finally
    //   135	142	253	finally
    //   148	156	253	finally
    //   166	173	253	finally
    //   179	214	253	finally
    //   226	234	253	finally
    //   242	246	253	finally
  }
  
  /* Error */
  public static void a(java.util.HashMap<String, String> paramHashMap)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc -33
    //   3: invokevirtual 229	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   6: checkcast 75	java/lang/String
    //   9: astore_0
    //   10: new 63	cn/com/xy/sms/sdk/db/entity/p
    //   13: dup
    //   14: invokespecial 167	cn/com/xy/sms/sdk/db/entity/p:<init>	()V
    //   17: astore 7
    //   19: aload 7
    //   21: aload_0
    //   22: putfield 65	cn/com/xy/sms/sdk/db/entity/p:a	Ljava/lang/String;
    //   25: aload 7
    //   27: iconst_1
    //   28: putfield 139	cn/com/xy/sms/sdk/db/entity/p:c	I
    //   31: aconst_null
    //   32: astore 5
    //   34: aconst_null
    //   35: astore 6
    //   37: aconst_null
    //   38: astore_0
    //   39: aload 5
    //   41: astore 4
    //   43: aload 6
    //   45: astore_3
    //   46: aload 7
    //   48: getfield 65	cn/com/xy/sms/sdk/db/entity/p:a	Ljava/lang/String;
    //   51: invokestatic 71	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   54: ifne +58 -> 112
    //   57: aload 5
    //   59: astore 4
    //   61: aload 6
    //   63: astore_3
    //   64: aload 7
    //   66: getfield 65	cn/com/xy/sms/sdk/db/entity/p:a	Ljava/lang/String;
    //   69: astore_0
    //   70: aload 5
    //   72: astore 4
    //   74: aload 6
    //   76: astore_3
    //   77: ldc 24
    //   79: iconst_3
    //   80: anewarray 75	java/lang/String
    //   83: dup
    //   84: iconst_0
    //   85: ldc 15
    //   87: aastore
    //   88: dup
    //   89: iconst_1
    //   90: ldc 30
    //   92: aastore
    //   93: dup
    //   94: iconst_2
    //   95: ldc 33
    //   97: aastore
    //   98: ldc -45
    //   100: iconst_1
    //   101: anewarray 75	java/lang/String
    //   104: dup
    //   105: iconst_0
    //   106: aload_0
    //   107: aastore
    //   108: invokestatic 94	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   111: astore_0
    //   112: aload_0
    //   113: ifnull +29 -> 142
    //   116: aload_0
    //   117: astore 4
    //   119: aload_0
    //   120: astore_3
    //   121: aload_0
    //   122: invokevirtual 119	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   125: ifle +17 -> 142
    //   128: aload_0
    //   129: astore 4
    //   131: aload_0
    //   132: astore_3
    //   133: aload_0
    //   134: invokevirtual 164	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   137: istore_2
    //   138: iload_2
    //   139: ifne +9 -> 148
    //   142: aload_0
    //   143: iconst_1
    //   144: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   147: return
    //   148: aload_0
    //   149: astore 4
    //   151: aload_0
    //   152: astore_3
    //   153: aload_0
    //   154: aload_0
    //   155: ldc 33
    //   157: invokevirtual 160	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   160: invokevirtual 189	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   163: istore_1
    //   164: aload_0
    //   165: astore 4
    //   167: aload_0
    //   168: astore_3
    //   169: aload 7
    //   171: iconst_1
    //   172: putfield 141	cn/com/xy/sms/sdk/db/entity/p:e	I
    //   175: aload_0
    //   176: astore 4
    //   178: aload_0
    //   179: astore_3
    //   180: aload 7
    //   182: iload_1
    //   183: iconst_1
    //   184: iadd
    //   185: putfield 139	cn/com/xy/sms/sdk/db/entity/p:c	I
    //   188: aload_0
    //   189: astore 4
    //   191: aload_0
    //   192: astore_3
    //   193: aload 7
    //   195: invokestatic 213	cn/com/xy/sms/sdk/db/entity/q:a	(Lcn/com/xy/sms/sdk/db/entity/p;)Landroid/content/ContentValues;
    //   198: astore 5
    //   200: aload_0
    //   201: astore 4
    //   203: aload_0
    //   204: astore_3
    //   205: new 73	java/lang/StringBuilder
    //   208: dup
    //   209: ldc -41
    //   211: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   214: aload 5
    //   216: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   219: pop
    //   220: aload_0
    //   221: astore 4
    //   223: aload_0
    //   224: astore_3
    //   225: ldc 24
    //   227: aload 5
    //   229: ldc -45
    //   231: iconst_1
    //   232: anewarray 75	java/lang/String
    //   235: dup
    //   236: iconst_0
    //   237: new 73	java/lang/StringBuilder
    //   240: dup
    //   241: aload 7
    //   243: getfield 65	cn/com/xy/sms/sdk/db/entity/p:a	Ljava/lang/String;
    //   246: invokestatic 151	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   249: invokespecial 82	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   252: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   255: aastore
    //   256: invokestatic 125	cn/com/xy/sms/sdk/db/DBManager:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   259: pop
    //   260: goto -132 -> 128
    //   263: astore_0
    //   264: aload 4
    //   266: astore_3
    //   267: aload_0
    //   268: invokevirtual 136	java/lang/Throwable:printStackTrace	()V
    //   271: aload 4
    //   273: iconst_1
    //   274: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   277: return
    //   278: astore_0
    //   279: aload_3
    //   280: iconst_1
    //   281: invokestatic 129	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   284: aload_0
    //   285: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	286	0	paramHashMap	java.util.HashMap<String, String>
    //   163	22	1	i1	int
    //   137	2	2	bool	boolean
    //   45	235	3	localObject1	Object
    //   41	231	4	localObject2	Object
    //   32	196	5	localContentValues	ContentValues
    //   35	40	6	localObject3	Object
    //   17	225	7	localp	p
    // Exception table:
    //   from	to	target	type
    //   46	57	263	java/lang/Throwable
    //   64	70	263	java/lang/Throwable
    //   77	112	263	java/lang/Throwable
    //   121	128	263	java/lang/Throwable
    //   133	138	263	java/lang/Throwable
    //   153	164	263	java/lang/Throwable
    //   169	175	263	java/lang/Throwable
    //   180	188	263	java/lang/Throwable
    //   193	200	263	java/lang/Throwable
    //   205	220	263	java/lang/Throwable
    //   225	260	263	java/lang/Throwable
    //   46	57	278	finally
    //   64	70	278	finally
    //   77	112	278	finally
    //   121	128	278	finally
    //   133	138	278	finally
    //   153	164	278	finally
    //   169	175	278	finally
    //   180	188	278	finally
    //   193	200	278	finally
    //   205	220	278	finally
    //   225	260	278	finally
    //   267	271	278	finally
  }
  
  private static ContentValues b(p paramp, int paramInt)
  {
    ContentValues localContentValues = new ContentValues();
    if (!StringUtils.isNull(b)) {
      localContentValues.put("sceneVersion", b);
    }
    localContentValues.put("scene_id", a);
    localContentValues.put("sceneType", Integer.valueOf(paramInt));
    localContentValues.put("isCheck", Integer.valueOf(d));
    return localContentValues;
  }
  
  private static ContentValues b(String paramString1, String paramString2, int paramInt)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("scene_id", paramString1);
    localContentValues.put("sceneType", Integer.valueOf(paramInt));
    if (!StringUtils.isNull(paramString2)) {
      localContentValues.put("sceneVersion", paramString2);
    }
    return localContentValues;
  }
  
  private static void b(String paramString, int paramInt)
  {
    if (paramInt == 1) {}
    for (;;)
    {
      try
      {
        str = "scene_id=? and sceneType = " + paramInt;
        DBManager.delete("tb_scene_config", str, new String[] { paramString });
        return;
      }
      catch (Throwable paramString)
      {
        String str;
        paramString.printStackTrace();
      }
      str = "scene_id=? and sceneType != 1";
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.q
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */