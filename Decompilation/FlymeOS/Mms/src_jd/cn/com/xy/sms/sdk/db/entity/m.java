package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.Arrays;
import java.util.List;

public final class m
{
  private static int a = 0;
  private static int b = 1;
  private static String c = "id";
  private static String d = "scene_id";
  private static String e = "url";
  private static String f = "status";
  private static String g = "pos";
  private static String h = "last_load_time";
  private static String i = "tb_res_download";
  private static String j = " DROP TABLE IF EXISTS tb_res_download";
  private static String k = "create table  if not exists tb_res_download (id INTEGER PRIMARY KEY,scene_id TEXT,url TEXT,status INTEGER,pos INTEGER,last_load_time INTEGER DEFAULT '0' )";
  private static String l = "ALTER TABLE tb_res_download ADD COLUMN last_load_time INTEGER DEFAULT '0'";
  
  public static l a(l paraml)
  {
    String str1 = null;
    XyCursor localXyCursor2 = null;
    XyCursor localXyCursor1 = localXyCursor2;
    Object localObject = str1;
    try
    {
      String str2 = c;
      localXyCursor1 = localXyCursor2;
      localObject = str1;
      localXyCursor2 = DBManager.query("tb_res_download", new String[] { "id", "scene_id", "url", "status", "pos" }, "url = ? ", new String[] { str2 });
      if (localXyCursor2 != null)
      {
        localXyCursor1 = localXyCursor2;
        localObject = localXyCursor2;
        if (localXyCursor2.getCount() > 0)
        {
          localXyCursor1 = localXyCursor2;
          localObject = localXyCursor2;
          int n = localXyCursor2.getColumnIndex("id");
          localXyCursor1 = localXyCursor2;
          localObject = localXyCursor2;
          int i1 = localXyCursor2.getColumnIndex("scene_id");
          localXyCursor1 = localXyCursor2;
          localObject = localXyCursor2;
          int i2 = localXyCursor2.getColumnIndex("status");
          localXyCursor1 = localXyCursor2;
          localObject = localXyCursor2;
          int m = localXyCursor2.getColumnIndex("pos");
          localXyCursor1 = localXyCursor2;
          localObject = localXyCursor2;
          if (!localXyCursor2.moveToNext()) {
            break label380;
          }
          localXyCursor1 = localXyCursor2;
          localObject = localXyCursor2;
          l1 = localXyCursor2.getLong(n);
          localXyCursor1 = localXyCursor2;
          localObject = localXyCursor2;
          str1 = localXyCursor2.getString(i1);
          localXyCursor1 = localXyCursor2;
          localObject = localXyCursor2;
          n = localXyCursor2.getInt(i2);
          localXyCursor1 = localXyCursor2;
          localObject = localXyCursor2;
          m = localXyCursor2.getInt(m);
          localXyCursor1 = localXyCursor2;
          localObject = localXyCursor2;
          a = l1;
          localXyCursor1 = localXyCursor2;
          localObject = localXyCursor2;
          b = str1;
          localXyCursor1 = localXyCursor2;
          localObject = localXyCursor2;
          d = n;
          localXyCursor1 = localXyCursor2;
          localObject = localXyCursor2;
          e = m;
          XyCursor.closeCursor(localXyCursor2, true);
          return paraml;
        }
      }
      localXyCursor1 = localXyCursor2;
      localObject = localXyCursor2;
      long l1 = DBManager.insert("tb_res_download", c(paraml));
      if (l1 > -1L)
      {
        localXyCursor1 = localXyCursor2;
        localObject = localXyCursor2;
        a = l1;
        XyCursor.closeCursor(localXyCursor2, true);
        return paraml;
      }
    }
    catch (Throwable localThrowable)
    {
      localObject = localXyCursor1;
      localThrowable.printStackTrace();
      XyCursor.closeCursor(localXyCursor1, true);
      return paraml;
    }
    finally
    {
      XyCursor.closeCursor((XyCursor)localObject, true);
    }
    label380:
    XyCursor.closeCursor(localThrowable, true);
    return paraml;
  }
  
  public static List<String> a(String paramString)
  {
    try
    {
      if (!StringUtils.isNull(paramString))
      {
        paramString = Arrays.asList(paramString.split(";"));
        return paramString;
      }
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static void a()
  {
    try
    {
      DBManager.delete("tb_res_download", null, null);
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
      DBManager.update("tb_res_download", localContentValues, "id = ? ", new String[] { paramLong });
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public static void a(l paraml, long paramLong)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("last_load_time", paramLong);
      DBManager.update("tb_res_download", localContentValues, "id = ? ", new String[] { a });
      return;
    }
    catch (Throwable paraml)
    {
      paraml.printStackTrace();
    }
  }
  
  /* Error */
  public static l b()
  {
    // Byte code:
    //   0: new 156	java/lang/StringBuilder
    //   3: dup
    //   4: invokestatic 181	java/lang/System:currentTimeMillis	()J
    //   7: ldc2_w 182
    //   10: lsub
    //   11: invokestatic 159	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   14: invokespecial 162	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   17: invokevirtual 166	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   20: astore 7
    //   22: ldc 31
    //   24: iconst_5
    //   25: anewarray 54	java/lang/String
    //   28: dup
    //   29: iconst_0
    //   30: ldc 13
    //   32: aastore
    //   33: dup
    //   34: iconst_1
    //   35: ldc 16
    //   37: aastore
    //   38: dup
    //   39: iconst_2
    //   40: ldc 19
    //   42: aastore
    //   43: dup
    //   44: iconst_3
    //   45: ldc 22
    //   47: aastore
    //   48: dup
    //   49: iconst_4
    //   50: ldc 25
    //   52: aastore
    //   53: ldc -71
    //   55: iconst_2
    //   56: anewarray 54	java/lang/String
    //   59: dup
    //   60: iconst_0
    //   61: ldc -69
    //   63: aastore
    //   64: dup
    //   65: iconst_1
    //   66: aload 7
    //   68: aastore
    //   69: aconst_null
    //   70: aconst_null
    //   71: ldc -67
    //   73: ldc -65
    //   75: invokestatic 194	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   78: astore 7
    //   80: aload 7
    //   82: ifnull +182 -> 264
    //   85: aload 7
    //   87: invokevirtual 68	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   90: ifle +174 -> 264
    //   93: aload 7
    //   95: ldc 13
    //   97: invokevirtual 72	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   100: istore_2
    //   101: aload 7
    //   103: ldc 16
    //   105: invokevirtual 72	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   108: istore_3
    //   109: aload 7
    //   111: ldc 22
    //   113: invokevirtual 72	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   116: istore 4
    //   118: aload 7
    //   120: ldc 25
    //   122: invokevirtual 72	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   125: istore_1
    //   126: aload 7
    //   128: ldc 19
    //   130: invokevirtual 72	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   133: istore_0
    //   134: aload 7
    //   136: invokevirtual 76	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   139: ifeq +125 -> 264
    //   142: aload 7
    //   144: iload_2
    //   145: invokevirtual 80	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   148: lstore 5
    //   150: aload 7
    //   152: iload_3
    //   153: invokevirtual 84	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   156: astore 8
    //   158: aload 7
    //   160: iload 4
    //   162: invokevirtual 88	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   165: istore_2
    //   166: aload 7
    //   168: iload_1
    //   169: invokevirtual 88	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   172: istore_1
    //   173: aload 7
    //   175: iload_0
    //   176: invokevirtual 84	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   179: astore 9
    //   181: new 50	cn/com/xy/sms/sdk/db/entity/l
    //   184: dup
    //   185: invokespecial 195	cn/com/xy/sms/sdk/db/entity/l:<init>	()V
    //   188: astore 10
    //   190: aload 10
    //   192: lload 5
    //   194: putfield 91	cn/com/xy/sms/sdk/db/entity/l:a	J
    //   197: aload 10
    //   199: aload 8
    //   201: putfield 93	cn/com/xy/sms/sdk/db/entity/l:b	Ljava/lang/String;
    //   204: aload 10
    //   206: iload_2
    //   207: putfield 95	cn/com/xy/sms/sdk/db/entity/l:d	I
    //   210: aload 10
    //   212: iload_1
    //   213: putfield 97	cn/com/xy/sms/sdk/db/entity/l:e	I
    //   216: aload 10
    //   218: aload 9
    //   220: putfield 52	cn/com/xy/sms/sdk/db/entity/l:c	Ljava/lang/String;
    //   223: aload 10
    //   225: invokestatic 198	cn/com/xy/sms/sdk/db/entity/m:d	(Lcn/com/xy/sms/sdk/db/entity/l;)V
    //   228: aload 7
    //   230: iconst_1
    //   231: invokestatic 101	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   234: aload 10
    //   236: areturn
    //   237: astore 7
    //   239: aconst_null
    //   240: astore 7
    //   242: aload 7
    //   244: iconst_1
    //   245: invokestatic 101	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   248: aconst_null
    //   249: areturn
    //   250: astore 8
    //   252: aconst_null
    //   253: astore 7
    //   255: aload 7
    //   257: iconst_1
    //   258: invokestatic 101	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   261: aload 8
    //   263: athrow
    //   264: aload 7
    //   266: iconst_1
    //   267: invokestatic 101	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   270: goto -22 -> 248
    //   273: astore 8
    //   275: goto -20 -> 255
    //   278: astore 8
    //   280: goto -38 -> 242
    // Local variable table:
    //   start	length	slot	name	signature
    //   133	43	0	m	int
    //   125	88	1	n	int
    //   100	107	2	i1	int
    //   108	45	3	i2	int
    //   116	45	4	i3	int
    //   148	45	5	l1	long
    //   20	209	7	localObject1	Object
    //   237	1	7	localThrowable1	Throwable
    //   240	25	7	localXyCursor	XyCursor
    //   156	44	8	str1	String
    //   250	12	8	localObject2	Object
    //   273	1	8	localObject3	Object
    //   278	1	8	localThrowable2	Throwable
    //   179	40	9	str2	String
    //   188	47	10	locall	l
    // Exception table:
    //   from	to	target	type
    //   0	80	237	java/lang/Throwable
    //   0	80	250	finally
    //   85	228	273	finally
    //   85	228	278	java/lang/Throwable
  }
  
  private static void b(l paraml)
  {
    try
    {
      DBManager.insert("tb_res_download", c(paraml));
      return;
    }
    catch (Throwable paraml)
    {
      paraml.printStackTrace();
    }
  }
  
  public static boolean b(String paramString)
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
      paramString = DBManager.query("tb_res_download", new String[] { "id", "url", "status" }, "url = ? and status = ?", new String[] { paramString, "1" });
      if (paramString != null)
      {
        localObject2 = paramString;
        localObject1 = paramString;
        int m = paramString.getCount();
        if (m > 0)
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
  
  private static ContentValues c(l paraml)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("scene_id", b);
    localContentValues.put("url", c);
    localContentValues.put("status", Integer.valueOf(d));
    localContentValues.put("pos", Integer.valueOf(e));
    localContentValues.put("last_load_time", Integer.valueOf(0));
    return localContentValues;
  }
  
  /* Error */
  public static l c(String paramString)
  {
    // Byte code:
    //   0: ldc 31
    //   2: iconst_5
    //   3: anewarray 54	java/lang/String
    //   6: dup
    //   7: iconst_0
    //   8: ldc 13
    //   10: aastore
    //   11: dup
    //   12: iconst_1
    //   13: ldc 16
    //   15: aastore
    //   16: dup
    //   17: iconst_2
    //   18: ldc 19
    //   20: aastore
    //   21: dup
    //   22: iconst_3
    //   23: ldc 22
    //   25: aastore
    //   26: dup
    //   27: iconst_4
    //   28: ldc 25
    //   30: aastore
    //   31: ldc -50
    //   33: iconst_2
    //   34: anewarray 54	java/lang/String
    //   37: dup
    //   38: iconst_0
    //   39: ldc -69
    //   41: aastore
    //   42: dup
    //   43: iconst_1
    //   44: aload_0
    //   45: aastore
    //   46: aconst_null
    //   47: aconst_null
    //   48: ldc -67
    //   50: ldc -65
    //   52: invokestatic 194	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   55: astore 8
    //   57: aload 8
    //   59: ifnull +247 -> 306
    //   62: aload 8
    //   64: astore_0
    //   65: aload 8
    //   67: invokevirtual 68	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   70: ifle +236 -> 306
    //   73: aload 8
    //   75: astore_0
    //   76: aload 8
    //   78: ldc 13
    //   80: invokevirtual 72	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   83: istore_3
    //   84: aload 8
    //   86: astore_0
    //   87: aload 8
    //   89: ldc 16
    //   91: invokevirtual 72	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   94: istore 4
    //   96: aload 8
    //   98: astore_0
    //   99: aload 8
    //   101: ldc 22
    //   103: invokevirtual 72	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   106: istore 5
    //   108: aload 8
    //   110: astore_0
    //   111: aload 8
    //   113: ldc 25
    //   115: invokevirtual 72	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   118: istore_2
    //   119: aload 8
    //   121: astore_0
    //   122: aload 8
    //   124: ldc 19
    //   126: invokevirtual 72	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   129: istore_1
    //   130: aload 8
    //   132: astore_0
    //   133: aload 8
    //   135: invokevirtual 76	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   138: ifeq +168 -> 306
    //   141: aload 8
    //   143: astore_0
    //   144: aload 8
    //   146: iload_3
    //   147: invokevirtual 80	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   150: lstore 6
    //   152: aload 8
    //   154: astore_0
    //   155: aload 8
    //   157: iload 4
    //   159: invokevirtual 84	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   162: astore 9
    //   164: aload 8
    //   166: astore_0
    //   167: aload 8
    //   169: iload 5
    //   171: invokevirtual 88	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   174: istore_3
    //   175: aload 8
    //   177: astore_0
    //   178: aload 8
    //   180: iload_2
    //   181: invokevirtual 88	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   184: istore_2
    //   185: aload 8
    //   187: astore_0
    //   188: aload 8
    //   190: iload_1
    //   191: invokevirtual 84	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   194: astore 10
    //   196: aload 8
    //   198: astore_0
    //   199: new 50	cn/com/xy/sms/sdk/db/entity/l
    //   202: dup
    //   203: invokespecial 195	cn/com/xy/sms/sdk/db/entity/l:<init>	()V
    //   206: astore 11
    //   208: aload 8
    //   210: astore_0
    //   211: aload 11
    //   213: lload 6
    //   215: putfield 91	cn/com/xy/sms/sdk/db/entity/l:a	J
    //   218: aload 8
    //   220: astore_0
    //   221: aload 11
    //   223: aload 9
    //   225: putfield 93	cn/com/xy/sms/sdk/db/entity/l:b	Ljava/lang/String;
    //   228: aload 8
    //   230: astore_0
    //   231: aload 11
    //   233: iload_3
    //   234: putfield 95	cn/com/xy/sms/sdk/db/entity/l:d	I
    //   237: aload 8
    //   239: astore_0
    //   240: aload 11
    //   242: iload_2
    //   243: putfield 97	cn/com/xy/sms/sdk/db/entity/l:e	I
    //   246: aload 8
    //   248: astore_0
    //   249: aload 11
    //   251: aload 10
    //   253: putfield 52	cn/com/xy/sms/sdk/db/entity/l:c	Ljava/lang/String;
    //   256: aload 8
    //   258: astore_0
    //   259: aload 11
    //   261: invokestatic 198	cn/com/xy/sms/sdk/db/entity/m:d	(Lcn/com/xy/sms/sdk/db/entity/l;)V
    //   264: aload 8
    //   266: iconst_1
    //   267: invokestatic 101	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   270: aload 11
    //   272: areturn
    //   273: astore 9
    //   275: aconst_null
    //   276: astore 8
    //   278: aload 8
    //   280: astore_0
    //   281: aload 9
    //   283: invokevirtual 113	java/lang/Throwable:printStackTrace	()V
    //   286: aload 8
    //   288: iconst_1
    //   289: invokestatic 101	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   292: aconst_null
    //   293: areturn
    //   294: astore 8
    //   296: aconst_null
    //   297: astore_0
    //   298: aload_0
    //   299: iconst_1
    //   300: invokestatic 101	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   303: aload 8
    //   305: athrow
    //   306: aload 8
    //   308: iconst_1
    //   309: invokestatic 101	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   312: goto -20 -> 292
    //   315: astore 8
    //   317: goto -19 -> 298
    //   320: astore 9
    //   322: goto -44 -> 278
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	325	0	paramString	String
    //   129	62	1	m	int
    //   118	125	2	n	int
    //   83	151	3	i1	int
    //   94	64	4	i2	int
    //   106	64	5	i3	int
    //   150	64	6	l1	long
    //   55	232	8	localXyCursor1	XyCursor
    //   294	13	8	localXyCursor2	XyCursor
    //   315	1	8	localObject	Object
    //   162	62	9	str1	String
    //   273	9	9	localThrowable1	Throwable
    //   320	1	9	localThrowable2	Throwable
    //   194	58	10	str2	String
    //   206	65	11	locall	l
    // Exception table:
    //   from	to	target	type
    //   0	57	273	java/lang/Throwable
    //   0	57	294	finally
    //   65	73	315	finally
    //   76	84	315	finally
    //   87	96	315	finally
    //   99	108	315	finally
    //   111	119	315	finally
    //   122	130	315	finally
    //   133	141	315	finally
    //   144	152	315	finally
    //   155	164	315	finally
    //   167	175	315	finally
    //   178	185	315	finally
    //   188	196	315	finally
    //   199	208	315	finally
    //   211	218	315	finally
    //   221	228	315	finally
    //   231	237	315	finally
    //   240	246	315	finally
    //   249	256	315	finally
    //   259	264	315	finally
    //   281	286	315	finally
    //   65	73	320	java/lang/Throwable
    //   76	84	320	java/lang/Throwable
    //   87	96	320	java/lang/Throwable
    //   99	108	320	java/lang/Throwable
    //   111	119	320	java/lang/Throwable
    //   122	130	320	java/lang/Throwable
    //   133	141	320	java/lang/Throwable
    //   144	152	320	java/lang/Throwable
    //   155	164	320	java/lang/Throwable
    //   167	175	320	java/lang/Throwable
    //   178	185	320	java/lang/Throwable
    //   188	196	320	java/lang/Throwable
    //   199	208	320	java/lang/Throwable
    //   211	218	320	java/lang/Throwable
    //   221	228	320	java/lang/Throwable
    //   231	237	320	java/lang/Throwable
    //   240	246	320	java/lang/Throwable
    //   249	256	320	java/lang/Throwable
    //   259	264	320	java/lang/Throwable
  }
  
  private static void d(l paraml)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("last_load_time", System.currentTimeMillis());
      DBManager.update("tb_res_download", localContentValues, "id = ? ", new String[] { a });
      return;
    }
    catch (Throwable paraml)
    {
      paraml.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.m
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */