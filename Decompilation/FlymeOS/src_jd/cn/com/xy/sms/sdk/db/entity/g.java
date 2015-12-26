package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;

public final class g
{
  private static String a = "tb_netquery_time";
  private static String b = " DROP TABLE IF EXISTS tb_netquery_time";
  private static String c = "id";
  private static String d = "phone_num";
  private static String e = "area_code";
  private static String f = "request_time";
  private static String g = "ALTER TABLE tb_netquery_time ADD COLUMN area_code TEXT";
  
  public static String a()
  {
    return " create table  if not exists tb_netquery_time ( id INTEGER PRIMARY KEY, phone_num TEXT,area_code TEXT, request_time LONG DEFAULT '0')";
  }
  
  /* Error */
  public static java.util.List<String> a(long paramLong)
  {
    // Byte code:
    //   0: ldc 8
    //   2: iconst_1
    //   3: anewarray 39	java/lang/String
    //   6: dup
    //   7: iconst_0
    //   8: ldc 17
    //   10: aastore
    //   11: ldc 41
    //   13: iconst_1
    //   14: anewarray 39	java/lang/String
    //   17: dup
    //   18: iconst_0
    //   19: lload_0
    //   20: invokestatic 45	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   23: aastore
    //   24: aconst_null
    //   25: aconst_null
    //   26: aconst_null
    //   27: aconst_null
    //   28: invokestatic 51	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   31: astore 4
    //   33: aload 4
    //   35: ifnonnull +11 -> 46
    //   38: aload 4
    //   40: iconst_1
    //   41: invokestatic 57	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   44: aconst_null
    //   45: areturn
    //   46: aload 4
    //   48: astore_3
    //   49: new 59	java/util/ArrayList
    //   52: dup
    //   53: invokespecial 60	java/util/ArrayList:<init>	()V
    //   56: astore 5
    //   58: aload 4
    //   60: astore_3
    //   61: aload 4
    //   63: invokevirtual 64	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   66: istore_2
    //   67: iload_2
    //   68: ifne +12 -> 80
    //   71: aload 4
    //   73: iconst_1
    //   74: invokestatic 57	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   77: aload 5
    //   79: areturn
    //   80: aload 4
    //   82: astore_3
    //   83: new 66	org/json/JSONObject
    //   86: dup
    //   87: invokespecial 67	org/json/JSONObject:<init>	()V
    //   90: astore 6
    //   92: aload 4
    //   94: astore_3
    //   95: aload 6
    //   97: ldc 69
    //   99: aload 4
    //   101: iconst_0
    //   102: invokevirtual 73	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   105: invokevirtual 77	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   108: pop
    //   109: aload 4
    //   111: astore_3
    //   112: aload 6
    //   114: ldc 79
    //   116: ldc 81
    //   118: invokevirtual 77	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   121: pop
    //   122: aload 4
    //   124: astore_3
    //   125: aload 5
    //   127: aload 6
    //   129: invokevirtual 84	org/json/JSONObject:toString	()Ljava/lang/String;
    //   132: invokeinterface 90 2 0
    //   137: pop
    //   138: goto -80 -> 58
    //   141: astore 5
    //   143: aload 4
    //   145: astore_3
    //   146: aload 5
    //   148: invokevirtual 93	java/lang/Throwable:printStackTrace	()V
    //   151: aload 4
    //   153: iconst_1
    //   154: invokestatic 57	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   157: aconst_null
    //   158: areturn
    //   159: astore 4
    //   161: aconst_null
    //   162: astore_3
    //   163: aload_3
    //   164: iconst_1
    //   165: invokestatic 57	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   168: aload 4
    //   170: athrow
    //   171: astore 4
    //   173: goto -10 -> 163
    //   176: astore 5
    //   178: aconst_null
    //   179: astore 4
    //   181: goto -38 -> 143
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	184	0	paramLong	long
    //   66	2	2	bool	boolean
    //   48	116	3	localXyCursor1	cn.com.xy.sms.sdk.db.XyCursor
    //   31	121	4	localXyCursor2	cn.com.xy.sms.sdk.db.XyCursor
    //   159	10	4	localObject1	Object
    //   171	1	4	localObject2	Object
    //   179	1	4	localObject3	Object
    //   56	70	5	localArrayList	java.util.ArrayList
    //   141	6	5	localThrowable1	Throwable
    //   176	1	5	localThrowable2	Throwable
    //   90	38	6	localJSONObject	org.json.JSONObject
    // Exception table:
    //   from	to	target	type
    //   49	58	141	java/lang/Throwable
    //   61	67	141	java/lang/Throwable
    //   83	92	141	java/lang/Throwable
    //   95	109	141	java/lang/Throwable
    //   112	122	141	java/lang/Throwable
    //   125	138	141	java/lang/Throwable
    //   0	33	159	finally
    //   49	58	171	finally
    //   61	67	171	finally
    //   83	92	171	finally
    //   95	109	171	finally
    //   112	122	171	finally
    //   125	138	171	finally
    //   146	151	171	finally
    //   0	33	176	java/lang/Throwable
  }
  
  public static boolean a(String paramString1, String paramString2)
  {
    boolean bool = false;
    try
    {
      if (XyUtil.checkNetWork(Constant.getContext(), 2) == -1) {
        return false;
      }
      if (d(paramString1, paramString2) == null)
      {
        c(paramString1, paramString2);
        return true;
      }
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
      bool = true;
    }
    return bool;
  }
  
  public static void b(long paramLong)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("request_time", Long.valueOf(System.currentTimeMillis()));
      DBManager.update("tb_netquery_time", localContentValues, "request_time < ? and request_time > 0 ", new String[] { String.valueOf(paramLong) });
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      LogManager.e("NetQueryTimeManager", "clearUpdateUnKnowNumTime error: " + localThrowable.getMessage(), localThrowable);
    }
  }
  
  public static void b(String paramString1, String paramString2)
  {
    try
    {
      if (StringUtils.isNull(paramString1)) {
        return;
      }
      String str = " phone_num = ? ";
      if (!StringUtils.isNull(paramString2)) {
        str = " phone_num = ? " + " and area_code = '" + paramString2 + "'";
      }
      DBManager.delete("tb_netquery_time", str, new String[] { paramString1 });
      return;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
      LogManager.e("NetQueryTimeManager", "delNetQueryNum error: " + paramString1.getMessage(), paramString1);
    }
  }
  
  private static long c(String paramString1, String paramString2)
  {
    try
    {
      ContentValues localContentValues = BaseManager.getContentValues(null, new String[] { "area_code", paramString2, "phone_num", paramString1, "request_time", String.valueOf(System.currentTimeMillis()) });
      if (DBManager.update("tb_netquery_time", localContentValues, "phone_num = ? and area_code = ?", new String[] { paramString1, paramString2 }) <= 0)
      {
        long l = DBManager.insert("tb_netquery_time", localContentValues);
        return l;
      }
      return 0L;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
    return -1L;
  }
  
  /* Error */
  private static org.json.JSONObject d(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: iconst_3
    //   3: anewarray 39	java/lang/String
    //   6: astore_3
    //   7: aload_3
    //   8: iconst_0
    //   9: ldc 14
    //   11: aastore
    //   12: aload_3
    //   13: iconst_1
    //   14: ldc 17
    //   16: aastore
    //   17: aload_3
    //   18: iconst_2
    //   19: ldc 23
    //   21: aastore
    //   22: ldc 8
    //   24: aload_3
    //   25: ldc -68
    //   27: iconst_2
    //   28: anewarray 39	java/lang/String
    //   31: dup
    //   32: iconst_0
    //   33: aload_0
    //   34: aastore
    //   35: dup
    //   36: iconst_1
    //   37: aload_1
    //   38: aastore
    //   39: aconst_null
    //   40: aconst_null
    //   41: aconst_null
    //   42: ldc -60
    //   44: invokestatic 51	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   47: astore_0
    //   48: aload_3
    //   49: aload_0
    //   50: invokestatic 200	cn/com/xy/sms/sdk/db/base/BaseManager:loadSingleDataFromCursor	([Ljava/lang/String;Lcn/com/xy/sms/sdk/db/XyCursor;)Lorg/json/JSONObject;
    //   53: astore_1
    //   54: aload_0
    //   55: iconst_1
    //   56: invokestatic 57	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   59: aload_1
    //   60: areturn
    //   61: astore_1
    //   62: aconst_null
    //   63: astore_0
    //   64: aload_1
    //   65: invokevirtual 93	java/lang/Throwable:printStackTrace	()V
    //   68: aload_0
    //   69: iconst_1
    //   70: invokestatic 57	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   73: aconst_null
    //   74: areturn
    //   75: astore_0
    //   76: aload_2
    //   77: astore_1
    //   78: aload_1
    //   79: iconst_1
    //   80: invokestatic 57	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   83: aload_0
    //   84: athrow
    //   85: astore_2
    //   86: aload_0
    //   87: astore_1
    //   88: aload_2
    //   89: astore_0
    //   90: goto -12 -> 78
    //   93: astore_2
    //   94: aload_0
    //   95: astore_1
    //   96: aload_2
    //   97: astore_0
    //   98: goto -20 -> 78
    //   101: astore_1
    //   102: goto -38 -> 64
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	105	0	paramString1	String
    //   0	105	1	paramString2	String
    //   1	76	2	localObject1	Object
    //   85	4	2	localObject2	Object
    //   93	4	2	localObject3	Object
    //   6	43	3	arrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   2	7	61	java/lang/Throwable
    //   22	48	61	java/lang/Throwable
    //   2	7	75	finally
    //   22	48	75	finally
    //   48	54	85	finally
    //   64	68	93	finally
    //   48	54	101	java/lang/Throwable
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.g
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */