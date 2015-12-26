package cn.com.xy.sms.sdk.db.entity.a;

import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.i;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;

public final class c
{
  private static String a = "id";
  private static String b = "num";
  private static String c = "name";
  private static String d = "cnum";
  private static String e = "mark_time";
  private static final String f = "tb_num_name";
  private static String g = " DROP TABLE IF EXISTS tb_num_name";
  private static String h = "ALTER TABLE tb_num_name ADD COLUMN cnum TEXT ";
  private static String i = "ALTER TABLE tb_num_name ADD COLUMN mark_time LONG DEFAULT 0";
  
  public static String a()
  {
    return "CREATE TABLE  IF NOT EXISTS tb_num_name (id INTEGER PRIMARY KEY, num TEXT NOT NULL UNIQUE, name TEXT NOT NULL, cnum TEXT,mark_time  LONG DEFAULT 0)";
  }
  
  public static String a(String paramString)
  {
    return b(paramString, false);
  }
  
  public static String a(String paramString, boolean paramBoolean)
  {
    if (!b()) {
      return "";
    }
    return b(paramString, paramBoolean);
  }
  
  private static void a(String paramString1, String paramString2, String paramString3)
  {
    if ((StringUtils.isNull(paramString1)) || (StringUtils.isNull(paramString2))) {
      return;
    }
    try
    {
      localObject = c(paramString1, false);
      if (localObject == null) {
        break label234;
      }
      if ((!StringUtils.isNull(c)) && (c.indexOf(paramString2) != -1) && (!StringUtils.isNull(paramString3)) && (paramString3.equals(d)))
      {
        DBManager.update("tb_num_name", BaseManager.getContentValues(null, new String[] { "name", c, "cnum", paramString3, "mark_time", "1" }), "num =? ", new String[] { paramString1 });
        return;
      }
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
      return;
    }
    Object localObject = c;
    if (((String)localObject).indexOf(paramString2) == -1) {
      if (((String)localObject).split(";").length <= 3) {}
    }
    for (;;)
    {
      DBManager.update("tb_num_name", BaseManager.getContentValues(null, new String[] { "name", paramString2, "cnum", paramString3, "mark_time", "1" }), "num =? ", new String[] { paramString1 });
      return;
      paramString2 = localObject + ";" + paramString2;
      continue;
      label234:
      DBManager.insert("tb_num_name", BaseManager.getContentValues(null, new String[] { "num", paramString1, "name", paramString2, "cnum", paramString3, "mark_time", "1" }));
      return;
      paramString2 = (String)localObject;
    }
  }
  
  public static void a(HashMap<String, String> paramHashMap)
  {
    String str2;
    String str3;
    Object localObject2;
    try
    {
      str2 = StringUtils.getPhoneNumberNo86((String)paramHashMap.get("num"));
      String str1 = DexUtil.getCorp((String)paramHashMap.get("msg"));
      str3 = (String)paramHashMap.get("cnum");
      if ((StringUtils.isNull(str2)) || (StringUtils.isNull(str1))) {}
      for (;;)
      {
        long l = Long.valueOf((String)paramHashMap.get("smsTime")).longValue();
        l = System.currentTimeMillis() - l;
        if ((l >= 0L) && (l < 60000L)) {
          NetUtil.pool.execute(new d(str2));
        }
        return;
        try
        {
          localObject2 = c(str2, false);
          if (localObject2 == null) {
            break label356;
          }
          if ((StringUtils.isNull(c)) || (c.indexOf(str1) == -1) || (StringUtils.isNull(str3)) || (!str3.equals(d))) {
            break;
          }
          DBManager.update("tb_num_name", BaseManager.getContentValues(null, new String[] { "name", c, "cnum", str3, "mark_time", "1" }), "num =? ", new String[] { str2 });
        }
        catch (Throwable localThrowable)
        {
          localThrowable.printStackTrace();
        }
      }
      localObject2 = c;
    }
    catch (Throwable paramHashMap)
    {
      paramHashMap.printStackTrace();
      return;
    }
    if (((String)localObject2).indexOf(localThrowable) == -1) {
      if (((String)localObject2).split(";").length <= 3) {}
    }
    for (;;)
    {
      DBManager.update("tb_num_name", BaseManager.getContentValues(null, new String[] { "name", localThrowable, "cnum", str3, "mark_time", "1" }), "num =? ", new String[] { str2 });
      break;
      Object localObject1 = localObject2 + ";" + localThrowable;
      continue;
      label356:
      DBManager.insert("tb_num_name", BaseManager.getContentValues(null, new String[] { "num", str2, "name", localObject1, "cnum", str3, "mark_time", "1" }));
      break;
      localObject1 = localObject2;
    }
  }
  
  public static b b(String paramString)
  {
    if (!b()) {
      return null;
    }
    return c(paramString, true);
  }
  
  private static String b(String paramString, boolean paramBoolean)
  {
    Object localObject2;
    if (StringUtils.isNull(paramString))
    {
      localObject2 = "";
      return (String)localObject2;
    }
    Object localObject5 = null;
    String str = null;
    Object localObject4 = "num = ? ";
    Object localObject1;
    if (paramBoolean)
    {
      localObject2 = str;
      localObject1 = localObject5;
    }
    for (;;)
    {
      try
      {
        localObject4 = "num = ? " + " and mark_time = 1 ";
        localObject2 = str;
        localObject1 = localObject5;
        localObject4 = DBManager.query("tb_num_name", new String[] { "name" }, (String)localObject4, new String[] { paramString });
        if (localObject4 != null)
        {
          localObject2 = localObject4;
          localObject1 = localObject4;
          if (((XyCursor)localObject4).moveToNext())
          {
            localObject2 = localObject4;
            localObject1 = localObject4;
            str = ((XyCursor)localObject4).getString(0);
            localObject1 = str;
            XyCursor.closeCursor((XyCursor)localObject4, true);
            localObject2 = localObject1;
            if (!paramBoolean) {
              break;
            }
            c(paramString);
            return (String)localObject1;
          }
        }
      }
      catch (Throwable localThrowable)
      {
        localObject1 = localObject2;
        localThrowable.printStackTrace();
        return "";
      }
      finally
      {
        XyCursor.closeCursor((XyCursor)localObject1, true);
        if (paramBoolean) {
          c(paramString);
        }
      }
      XyCursor.closeCursor(localThrowable, true);
      if (paramBoolean) {
        c(paramString);
      }
    }
  }
  
  private static boolean b()
  {
    boolean bool2 = false;
    int j = 0;
    for (;;)
    {
      boolean bool1;
      if (j > 0) {
        bool1 = true;
      }
      do
      {
        return SysParamEntityManager.getBooleanParam(Constant.getContext(), "num_name_power", bool1);
        bool1 = bool2;
      } while (new String[] { "HUAWEICARD" }[0].equals(i.e));
      j += 1;
    }
  }
  
  /* Error */
  private static b c(String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 220	java/lang/StringBuffer
    //   5: dup
    //   6: ldc 11
    //   8: invokespecial 221	java/lang/StringBuffer:<init>	(Ljava/lang/String;)V
    //   11: astore_2
    //   12: aload_2
    //   13: ldc -33
    //   15: invokevirtual 226	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   18: pop
    //   19: iload_1
    //   20: ifeq +24 -> 44
    //   23: aload_2
    //   24: ldc -28
    //   26: invokevirtual 226	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   29: pop
    //   30: aload_2
    //   31: ldc 20
    //   33: invokevirtual 226	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   36: pop
    //   37: aload_2
    //   38: ldc -26
    //   40: invokevirtual 226	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   43: pop
    //   44: aload_2
    //   45: invokevirtual 231	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   48: astore_2
    //   49: ldc 23
    //   51: iconst_4
    //   52: anewarray 67	java/lang/String
    //   55: dup
    //   56: iconst_0
    //   57: ldc 8
    //   59: aastore
    //   60: dup
    //   61: iconst_1
    //   62: ldc 11
    //   64: aastore
    //   65: dup
    //   66: iconst_2
    //   67: ldc 14
    //   69: aastore
    //   70: dup
    //   71: iconst_3
    //   72: ldc 17
    //   74: aastore
    //   75: aload_2
    //   76: iconst_1
    //   77: anewarray 67	java/lang/String
    //   80: dup
    //   81: iconst_0
    //   82: aload_0
    //   83: aastore
    //   84: invokestatic 183	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   87: astore_2
    //   88: aload_2
    //   89: ifnull +140 -> 229
    //   92: aload_2
    //   93: astore_3
    //   94: aload_2
    //   95: invokevirtual 188	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   98: ifeq +131 -> 229
    //   101: aload_2
    //   102: astore_3
    //   103: new 63	cn/com/xy/sms/sdk/db/entity/a/b
    //   106: dup
    //   107: invokespecial 232	cn/com/xy/sms/sdk/db/entity/a/b:<init>	()V
    //   110: astore 4
    //   112: aload_2
    //   113: astore_3
    //   114: aload_2
    //   115: aload_2
    //   116: ldc 8
    //   118: invokevirtual 235	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   121: invokevirtual 239	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   124: pop
    //   125: aload_2
    //   126: astore_3
    //   127: aload_2
    //   128: aload_2
    //   129: ldc 11
    //   131: invokevirtual 235	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   134: invokevirtual 192	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   137: pop
    //   138: aload_2
    //   139: astore_3
    //   140: aload 4
    //   142: aload_2
    //   143: aload_2
    //   144: ldc 14
    //   146: invokevirtual 235	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   149: invokevirtual 192	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   152: putfield 65	cn/com/xy/sms/sdk/db/entity/a/b:c	Ljava/lang/String;
    //   155: aload_2
    //   156: astore_3
    //   157: aload 4
    //   159: aload_2
    //   160: aload_2
    //   161: ldc 17
    //   163: invokevirtual 235	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   166: invokevirtual 192	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   169: putfield 73	cn/com/xy/sms/sdk/db/entity/a/b:d	Ljava/lang/String;
    //   172: aload 4
    //   174: astore_3
    //   175: aload_2
    //   176: iconst_1
    //   177: invokestatic 196	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   180: aload_0
    //   181: invokestatic 198	cn/com/xy/sms/sdk/db/entity/a/c:c	(Ljava/lang/String;)V
    //   184: aload_3
    //   185: areturn
    //   186: astore 4
    //   188: aconst_null
    //   189: astore_2
    //   190: aload_2
    //   191: astore_3
    //   192: aload 4
    //   194: invokevirtual 96	java/lang/Throwable:printStackTrace	()V
    //   197: aload_2
    //   198: iconst_1
    //   199: invokestatic 196	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   202: aload_0
    //   203: invokestatic 198	cn/com/xy/sms/sdk/db/entity/a/c:c	(Ljava/lang/String;)V
    //   206: aconst_null
    //   207: areturn
    //   208: astore_2
    //   209: aload_3
    //   210: iconst_1
    //   211: invokestatic 196	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   214: aload_0
    //   215: invokestatic 198	cn/com/xy/sms/sdk/db/entity/a/c:c	(Ljava/lang/String;)V
    //   218: aload_2
    //   219: athrow
    //   220: astore_2
    //   221: goto -12 -> 209
    //   224: astore 4
    //   226: goto -36 -> 190
    //   229: aconst_null
    //   230: astore_3
    //   231: goto -56 -> 175
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	234	0	paramString	String
    //   0	234	1	paramBoolean	boolean
    //   11	187	2	localObject1	Object
    //   208	11	2	localObject2	Object
    //   220	1	2	localObject3	Object
    //   1	230	3	localObject4	Object
    //   110	63	4	localb	b
    //   186	7	4	localThrowable1	Throwable
    //   224	1	4	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   2	19	186	java/lang/Throwable
    //   23	44	186	java/lang/Throwable
    //   44	88	186	java/lang/Throwable
    //   2	19	208	finally
    //   23	44	208	finally
    //   44	88	208	finally
    //   94	101	220	finally
    //   103	112	220	finally
    //   114	125	220	finally
    //   127	138	220	finally
    //   140	155	220	finally
    //   157	172	220	finally
    //   192	197	220	finally
    //   94	101	224	java/lang/Throwable
    //   103	112	224	java/lang/Throwable
    //   114	125	224	java/lang/Throwable
    //   127	138	224	java/lang/Throwable
    //   140	155	224	java/lang/Throwable
    //   157	172	224	java/lang/Throwable
  }
  
  private static void c(String paramString)
  {
    try
    {
      DBManager.update("tb_num_name", BaseManager.getContentValues(null, new String[] { "mark_time", "0" }), "num = ?", new String[] { paramString });
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.a.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */