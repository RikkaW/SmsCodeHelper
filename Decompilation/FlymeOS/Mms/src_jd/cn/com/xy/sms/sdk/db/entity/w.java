package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.util.StringUtils;

public final class w
{
  private static int a = 0;
  private static int b = 1;
  private static String c = "id";
  private static String d = "scene_id";
  private static String e = "url";
  private static String f = "status";
  private static String g = "pos";
  private static String h = "insert_time";
  private static String i = "last_load_time";
  private static String j = "tb_xml_res_download";
  private static String k = "sceneType";
  private static String l = " DROP TABLE IF EXISTS tb_xml_res_download";
  private static String m = "create table  if not exists tb_xml_res_download (id INTEGER PRIMARY KEY,scene_id TEXT,url TEXT,status INTEGER,pos INTEGER,last_load_time INTEGER DEFAULT '0' ,sceneType INTEGER DEFAULT '0',insert_time INTEGER DEFAULT '0' )";
  
  public static void a()
  {
    try
    {
      DBManager.delete("tb_xml_res_download", null, null);
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public static void a(long paramLong, int paramInt)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("status", Integer.valueOf(1));
      DBManager.update("tb_xml_res_download", localContentValues, "id = ? ", new String[] { paramLong });
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  private static void a(v paramv)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("last_load_time", System.currentTimeMillis());
      DBManager.update("tb_xml_res_download", localContentValues, "id = ? ", new String[] { a });
      return;
    }
    catch (Throwable paramv)
    {
      paramv.printStackTrace();
    }
  }
  
  public static void a(v paramv, long paramLong)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("last_load_time", paramLong);
      DBManager.update("tb_xml_res_download", localContentValues, "id = ? ", new String[] { a });
      return;
    }
    catch (Throwable paramv)
    {
      paramv.printStackTrace();
    }
  }
  
  public static void a(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    try
    {
      XyCursor localXyCursor = DBManager.query("tb_xml_res_download", new String[] { "id", "scene_id", "url", "status", "pos" }, "url = ? ", new String[] { paramString2 });
      if (localXyCursor != null)
      {
        localObject1 = localXyCursor;
        localObject2 = localXyCursor;
        if (localXyCursor.getCount() > 0) {}
      }
      else
      {
        localObject1 = localXyCursor;
        localObject2 = localXyCursor;
        ContentValues localContentValues = new ContentValues();
        localObject1 = localXyCursor;
        localObject2 = localXyCursor;
        localContentValues.put("scene_id", paramString1);
        localObject1 = localXyCursor;
        localObject2 = localXyCursor;
        localContentValues.put("url", paramString2);
        localObject1 = localXyCursor;
        localObject2 = localXyCursor;
        localContentValues.put("status", Integer.valueOf(0));
        localObject1 = localXyCursor;
        localObject2 = localXyCursor;
        localContentValues.put("pos", Integer.valueOf(0));
        localObject1 = localXyCursor;
        localObject2 = localXyCursor;
        localContentValues.put("sceneType", Integer.valueOf(paramInt3));
        localObject1 = localXyCursor;
        localObject2 = localXyCursor;
        localContentValues.put("insert_time", Long.valueOf(System.currentTimeMillis()));
        localObject1 = localXyCursor;
        localObject2 = localXyCursor;
        DBManager.insert("tb_xml_res_download", localContentValues);
      }
      XyCursor.closeCursor(localXyCursor, true);
      return;
    }
    catch (Throwable paramString1)
    {
      localObject2 = localObject1;
      paramString1.printStackTrace();
      return;
    }
    finally
    {
      XyCursor.closeCursor((XyCursor)localObject2, true);
    }
  }
  
  public static boolean a(String paramString)
  {
    if (StringUtils.isNull(paramString)) {
      return false;
    }
    Object localObject4 = null;
    Object localObject3 = null;
    Object localObject2 = localObject3;
    Object localObject1 = localObject4;
    try
    {
      paramString = paramString;
      localObject2 = localObject3;
      localObject1 = localObject4;
      paramString = DBManager.query("tb_xml_res_download", new String[] { "id", "url", "status" }, "url = ? and status = ?", new String[] { paramString, "1" });
      if (paramString != null)
      {
        localObject2 = paramString;
        localObject1 = paramString;
        int n = paramString.getCount();
        if (n > 0)
        {
          XyCursor.closeCursor(paramString, true);
          return true;
        }
      }
    }
    catch (Throwable paramString)
    {
      localObject1 = localObject2;
      paramString.printStackTrace();
      return false;
    }
    finally
    {
      XyCursor.closeCursor((XyCursor)localObject1, true);
    }
    XyCursor.closeCursor(paramString, true);
    return false;
  }
  
  private static ContentValues b(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("scene_id", paramString1);
    localContentValues.put("url", paramString2);
    localContentValues.put("status", Integer.valueOf(paramInt1));
    localContentValues.put("pos", Integer.valueOf(paramInt2));
    localContentValues.put("sceneType", Integer.valueOf(paramInt3));
    localContentValues.put("insert_time", Long.valueOf(System.currentTimeMillis()));
    return localContentValues;
  }
  
  /* Error */
  public static v b()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 9
    //   3: new 79	java/lang/StringBuilder
    //   6: dup
    //   7: invokestatic 100	java/lang/System:currentTimeMillis	()J
    //   10: ldc2_w 154
    //   13: lsub
    //   14: invokestatic 82	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   17: invokespecial 85	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   20: invokevirtual 89	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   23: astore 8
    //   25: ldc 34
    //   27: bipush 6
    //   29: anewarray 77	java/lang/String
    //   32: dup
    //   33: iconst_0
    //   34: ldc 13
    //   36: aastore
    //   37: dup
    //   38: iconst_1
    //   39: ldc 16
    //   41: aastore
    //   42: dup
    //   43: iconst_2
    //   44: ldc 19
    //   46: aastore
    //   47: dup
    //   48: iconst_3
    //   49: ldc 22
    //   51: aastore
    //   52: dup
    //   53: iconst_4
    //   54: ldc 25
    //   56: aastore
    //   57: dup
    //   58: iconst_5
    //   59: ldc 37
    //   61: aastore
    //   62: ldc -99
    //   64: iconst_2
    //   65: anewarray 77	java/lang/String
    //   68: dup
    //   69: iconst_0
    //   70: ldc -97
    //   72: aastore
    //   73: dup
    //   74: iconst_1
    //   75: aload 8
    //   77: aastore
    //   78: aconst_null
    //   79: aconst_null
    //   80: ldc -95
    //   82: ldc -105
    //   84: invokestatic 164	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   87: astore 8
    //   89: aload 8
    //   91: ifnull +182 -> 273
    //   94: aload 8
    //   96: invokevirtual 122	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   99: ifle +174 -> 273
    //   102: aload 8
    //   104: ldc 13
    //   106: invokevirtual 168	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   109: istore_1
    //   110: aload 8
    //   112: ldc 16
    //   114: invokevirtual 168	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   117: istore_2
    //   118: aload 8
    //   120: ldc 22
    //   122: invokevirtual 168	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   125: istore_3
    //   126: aload 8
    //   128: ldc 25
    //   130: invokevirtual 168	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   133: istore 4
    //   135: aload 8
    //   137: ldc 19
    //   139: invokevirtual 168	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   142: istore_0
    //   143: aload 8
    //   145: ldc 37
    //   147: invokevirtual 168	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   150: istore 5
    //   152: aload 8
    //   154: invokevirtual 172	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   157: ifeq +116 -> 273
    //   160: aload 8
    //   162: iload_1
    //   163: invokevirtual 176	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   166: lstore 6
    //   168: aload 8
    //   170: iload_2
    //   171: invokevirtual 180	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   174: pop
    //   175: aload 8
    //   177: iload_3
    //   178: invokevirtual 184	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   181: pop
    //   182: aload 8
    //   184: iload 4
    //   186: invokevirtual 184	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   189: pop
    //   190: aload 8
    //   192: iload 5
    //   194: invokevirtual 184	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   197: istore_1
    //   198: aload 8
    //   200: iload_0
    //   201: invokevirtual 180	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   204: astore 9
    //   206: new 105	cn/com/xy/sms/sdk/db/entity/v
    //   209: dup
    //   210: invokespecial 185	cn/com/xy/sms/sdk/db/entity/v:<init>	()V
    //   213: astore 10
    //   215: aload 10
    //   217: lload 6
    //   219: putfield 108	cn/com/xy/sms/sdk/db/entity/v:a	J
    //   222: aload 10
    //   224: aload 9
    //   226: putfield 187	cn/com/xy/sms/sdk/db/entity/v:c	Ljava/lang/String;
    //   229: aload 10
    //   231: iload_1
    //   232: putfield 189	cn/com/xy/sms/sdk/db/entity/v:f	I
    //   235: aload 10
    //   237: invokestatic 191	cn/com/xy/sms/sdk/db/entity/w:a	(Lcn/com/xy/sms/sdk/db/entity/v;)V
    //   240: aload 8
    //   242: iconst_1
    //   243: invokestatic 138	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   246: aload 10
    //   248: areturn
    //   249: astore 8
    //   251: aconst_null
    //   252: astore 8
    //   254: aload 8
    //   256: iconst_1
    //   257: invokestatic 138	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   260: aconst_null
    //   261: areturn
    //   262: astore 8
    //   264: aload 9
    //   266: iconst_1
    //   267: invokestatic 138	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   270: aload 8
    //   272: athrow
    //   273: aload 8
    //   275: iconst_1
    //   276: invokestatic 138	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   279: goto -19 -> 260
    //   282: astore 10
    //   284: aload 8
    //   286: astore 9
    //   288: aload 10
    //   290: astore 8
    //   292: goto -28 -> 264
    //   295: astore 9
    //   297: goto -43 -> 254
    // Local variable table:
    //   start	length	slot	name	signature
    //   142	59	0	n	int
    //   109	123	1	i1	int
    //   117	54	2	i2	int
    //   125	53	3	i3	int
    //   133	52	4	i4	int
    //   150	43	5	i5	int
    //   166	52	6	l1	long
    //   23	218	8	localObject1	Object
    //   249	1	8	localThrowable1	Throwable
    //   252	3	8	localXyCursor1	XyCursor
    //   262	23	8	localXyCursor2	XyCursor
    //   290	1	8	localObject2	Object
    //   1	286	9	localObject3	Object
    //   295	1	9	localThrowable2	Throwable
    //   213	34	10	localv	v
    //   282	7	10	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   3	89	249	java/lang/Throwable
    //   3	89	262	finally
    //   94	240	282	finally
    //   94	240	295	java/lang/Throwable
  }
  
  /* Error */
  public static v b(String paramString)
  {
    // Byte code:
    //   0: ldc 34
    //   2: bipush 6
    //   4: anewarray 77	java/lang/String
    //   7: dup
    //   8: iconst_0
    //   9: ldc 13
    //   11: aastore
    //   12: dup
    //   13: iconst_1
    //   14: ldc 16
    //   16: aastore
    //   17: dup
    //   18: iconst_2
    //   19: ldc 19
    //   21: aastore
    //   22: dup
    //   23: iconst_3
    //   24: ldc 22
    //   26: aastore
    //   27: dup
    //   28: iconst_4
    //   29: ldc 25
    //   31: aastore
    //   32: dup
    //   33: iconst_5
    //   34: ldc 37
    //   36: aastore
    //   37: ldc -62
    //   39: iconst_2
    //   40: anewarray 77	java/lang/String
    //   43: dup
    //   44: iconst_0
    //   45: ldc -97
    //   47: aastore
    //   48: dup
    //   49: iconst_1
    //   50: aload_0
    //   51: aastore
    //   52: aconst_null
    //   53: aconst_null
    //   54: ldc -95
    //   56: ldc -105
    //   58: invokestatic 164	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   61: astore 9
    //   63: aload 9
    //   65: ifnull +250 -> 315
    //   68: aload 9
    //   70: astore_0
    //   71: aload 9
    //   73: invokevirtual 122	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   76: ifle +239 -> 315
    //   79: aload 9
    //   81: astore_0
    //   82: aload 9
    //   84: ldc 13
    //   86: invokevirtual 168	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   89: istore_2
    //   90: aload 9
    //   92: astore_0
    //   93: aload 9
    //   95: ldc 16
    //   97: invokevirtual 168	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   100: istore_3
    //   101: aload 9
    //   103: astore_0
    //   104: aload 9
    //   106: ldc 22
    //   108: invokevirtual 168	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   111: istore 4
    //   113: aload 9
    //   115: astore_0
    //   116: aload 9
    //   118: ldc 25
    //   120: invokevirtual 168	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   123: istore 5
    //   125: aload 9
    //   127: astore_0
    //   128: aload 9
    //   130: ldc 19
    //   132: invokevirtual 168	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   135: istore_1
    //   136: aload 9
    //   138: astore_0
    //   139: aload 9
    //   141: ldc 37
    //   143: invokevirtual 168	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   146: istore 6
    //   148: aload 9
    //   150: astore_0
    //   151: aload 9
    //   153: invokevirtual 172	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   156: ifeq +159 -> 315
    //   159: aload 9
    //   161: astore_0
    //   162: aload 9
    //   164: iload_2
    //   165: invokevirtual 176	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   168: lstore 7
    //   170: aload 9
    //   172: astore_0
    //   173: aload 9
    //   175: iload_3
    //   176: invokevirtual 180	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   179: pop
    //   180: aload 9
    //   182: astore_0
    //   183: aload 9
    //   185: iload 4
    //   187: invokevirtual 184	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   190: pop
    //   191: aload 9
    //   193: astore_0
    //   194: aload 9
    //   196: iload 5
    //   198: invokevirtual 184	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   201: pop
    //   202: aload 9
    //   204: astore_0
    //   205: aload 9
    //   207: iload 6
    //   209: invokevirtual 184	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   212: istore_2
    //   213: aload 9
    //   215: astore_0
    //   216: aload 9
    //   218: iload_1
    //   219: invokevirtual 180	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   222: astore 10
    //   224: aload 9
    //   226: astore_0
    //   227: new 105	cn/com/xy/sms/sdk/db/entity/v
    //   230: dup
    //   231: invokespecial 185	cn/com/xy/sms/sdk/db/entity/v:<init>	()V
    //   234: astore 11
    //   236: aload 9
    //   238: astore_0
    //   239: aload 11
    //   241: lload 7
    //   243: putfield 108	cn/com/xy/sms/sdk/db/entity/v:a	J
    //   246: aload 9
    //   248: astore_0
    //   249: aload 11
    //   251: aload 10
    //   253: putfield 187	cn/com/xy/sms/sdk/db/entity/v:c	Ljava/lang/String;
    //   256: aload 9
    //   258: astore_0
    //   259: aload 11
    //   261: iload_2
    //   262: putfield 189	cn/com/xy/sms/sdk/db/entity/v:f	I
    //   265: aload 9
    //   267: astore_0
    //   268: aload 11
    //   270: invokestatic 191	cn/com/xy/sms/sdk/db/entity/w:a	(Lcn/com/xy/sms/sdk/db/entity/v;)V
    //   273: aload 9
    //   275: iconst_1
    //   276: invokestatic 138	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   279: aload 11
    //   281: areturn
    //   282: astore 10
    //   284: aconst_null
    //   285: astore 9
    //   287: aload 9
    //   289: astore_0
    //   290: aload 10
    //   292: invokevirtual 59	java/lang/Throwable:printStackTrace	()V
    //   295: aload 9
    //   297: iconst_1
    //   298: invokestatic 138	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   301: aconst_null
    //   302: areturn
    //   303: astore 9
    //   305: aconst_null
    //   306: astore_0
    //   307: aload_0
    //   308: iconst_1
    //   309: invokestatic 138	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   312: aload 9
    //   314: athrow
    //   315: aload 9
    //   317: iconst_1
    //   318: invokestatic 138	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   321: goto -20 -> 301
    //   324: astore 9
    //   326: goto -19 -> 307
    //   329: astore 10
    //   331: goto -44 -> 287
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	334	0	paramString	String
    //   135	84	1	n	int
    //   89	173	2	i1	int
    //   100	76	3	i2	int
    //   111	75	4	i3	int
    //   123	74	5	i4	int
    //   146	62	6	i5	int
    //   168	74	7	l1	long
    //   61	235	9	localXyCursor1	XyCursor
    //   303	13	9	localXyCursor2	XyCursor
    //   324	1	9	localObject	Object
    //   222	30	10	str	String
    //   282	9	10	localThrowable1	Throwable
    //   329	1	10	localThrowable2	Throwable
    //   234	46	11	localv	v
    // Exception table:
    //   from	to	target	type
    //   0	63	282	java/lang/Throwable
    //   0	63	303	finally
    //   71	79	324	finally
    //   82	90	324	finally
    //   93	101	324	finally
    //   104	113	324	finally
    //   116	125	324	finally
    //   128	136	324	finally
    //   139	148	324	finally
    //   151	159	324	finally
    //   162	170	324	finally
    //   173	180	324	finally
    //   183	191	324	finally
    //   194	202	324	finally
    //   205	213	324	finally
    //   216	224	324	finally
    //   227	236	324	finally
    //   239	246	324	finally
    //   249	256	324	finally
    //   259	265	324	finally
    //   268	273	324	finally
    //   290	295	324	finally
    //   71	79	329	java/lang/Throwable
    //   82	90	329	java/lang/Throwable
    //   93	101	329	java/lang/Throwable
    //   104	113	329	java/lang/Throwable
    //   116	125	329	java/lang/Throwable
    //   128	136	329	java/lang/Throwable
    //   139	148	329	java/lang/Throwable
    //   151	159	329	java/lang/Throwable
    //   162	170	329	java/lang/Throwable
    //   173	180	329	java/lang/Throwable
    //   183	191	329	java/lang/Throwable
    //   194	202	329	java/lang/Throwable
    //   205	213	329	java/lang/Throwable
    //   216	224	329	java/lang/Throwable
    //   227	236	329	java/lang/Throwable
    //   239	246	329	java/lang/Throwable
    //   249	256	329	java/lang/Throwable
    //   259	265	329	java/lang/Throwable
    //   268	273	329	java/lang/Throwable
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.w
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */