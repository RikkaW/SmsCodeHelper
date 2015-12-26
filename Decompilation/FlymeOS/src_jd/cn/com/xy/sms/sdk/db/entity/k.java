package cn.com.xy.sms.sdk.db.entity;

import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.util.DateUtils;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.HashMap;
import org.json.JSONObject;

public final class k
{
  private static String a = "scene_id";
  private static String b = "date";
  private static String c = "action_type";
  private static String d = "times";
  private static String e = "tb_button_action_scene";
  private static String f = " DROP TABLE IF EXISTS tb_button_action_scene";
  private static String g = "create table  if not exists tb_button_action_scene (scene_id TEXT, date TEXT, action_type INTEGER DEFAULT '0', times INTEGER DEFAULT '0' ) ";
  private static String[] h = { "scene_id", "date", "action_type", "times" };
  
  public static long a(HashMap<String, String> paramHashMap)
  {
    String str = (String)paramHashMap.get("titleNo");
    paramHashMap = (String)paramHashMap.get("type");
    if (!StringUtils.isNull(paramHashMap)) {}
    for (int i = Integer.parseInt(paramHashMap);; i = -1) {
      for (;;)
      {
        try
        {
          if ((!StringUtils.isNull(str)) && (i != -1))
          {
            paramHashMap = a(str, i);
            if (paramHashMap != null)
            {
              paramHashMap.put("times", String.valueOf(Integer.parseInt(paramHashMap.getString("times")) + 1));
              DBManager.update("tb_button_action_scene", BaseManager.getContentValues(null, false, paramHashMap, h), "scene_id = ? and date = ? and action_type = ? ", new String[] { paramHashMap.getString("scene_id"), paramHashMap.getString("date"), paramHashMap.getString("action_type") });
              return 0L;
            }
            long l = DBManager.insert("tb_button_action_scene", BaseManager.getContentValues(null, new String[] { "scene_id", str, "date", DateUtils.getCurrentTimeString("yyyyMMdd"), "action_type", String.valueOf(i), "times", "1" }));
            return l;
          }
        }
        catch (Throwable paramHashMap)
        {
          paramHashMap.printStackTrace();
          return -1L;
        }
        paramHashMap = null;
      }
    }
  }
  
  /* Error */
  public static org.json.JSONArray a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: ldc 20
    //   4: getstatic 34	cn/com/xy/sms/sdk/db/entity/k:h	[Ljava/lang/String;
    //   7: ldc 120
    //   9: iconst_2
    //   10: anewarray 32	java/lang/String
    //   13: dup
    //   14: iconst_0
    //   15: aload_0
    //   16: aastore
    //   17: dup
    //   18: iconst_1
    //   19: aload_1
    //   20: aastore
    //   21: aconst_null
    //   22: aconst_null
    //   23: aconst_null
    //   24: aconst_null
    //   25: invokestatic 124	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   28: astore_1
    //   29: aload_1
    //   30: astore_0
    //   31: getstatic 34	cn/com/xy/sms/sdk/db/entity/k:h	[Ljava/lang/String;
    //   34: aload_1
    //   35: invokestatic 128	cn/com/xy/sms/sdk/db/base/BaseManager:loadArrDataFromCursor	([Ljava/lang/String;Lcn/com/xy/sms/sdk/db/XyCursor;)Lorg/json/JSONArray;
    //   38: astore_2
    //   39: aload_1
    //   40: iconst_1
    //   41: invokestatic 134	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   44: aload_2
    //   45: areturn
    //   46: astore_2
    //   47: aconst_null
    //   48: astore_1
    //   49: aload_1
    //   50: astore_0
    //   51: aload_2
    //   52: invokevirtual 113	java/lang/Throwable:printStackTrace	()V
    //   55: aload_1
    //   56: iconst_1
    //   57: invokestatic 134	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   60: aconst_null
    //   61: areturn
    //   62: astore_0
    //   63: aload_2
    //   64: astore_1
    //   65: aload_1
    //   66: iconst_1
    //   67: invokestatic 134	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   70: aload_0
    //   71: athrow
    //   72: astore_2
    //   73: aload_0
    //   74: astore_1
    //   75: aload_2
    //   76: astore_0
    //   77: goto -12 -> 65
    //   80: astore_2
    //   81: goto -32 -> 49
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	84	0	paramString1	String
    //   0	84	1	paramString2	String
    //   1	44	2	localJSONArray	org.json.JSONArray
    //   46	18	2	localThrowable1	Throwable
    //   72	4	2	localObject	Object
    //   80	1	2	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   2	29	46	java/lang/Throwable
    //   2	29	62	finally
    //   31	39	72	finally
    //   51	55	72	finally
    //   31	39	80	java/lang/Throwable
  }
  
  /* Error */
  private static JSONObject a(String paramString, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: ldc 96
    //   4: invokestatic 101	cn/com/xy/sms/sdk/util/DateUtils:getCurrentTimeString	(Ljava/lang/String;)Ljava/lang/String;
    //   7: astore_3
    //   8: ldc 20
    //   10: getstatic 34	cn/com/xy/sms/sdk/db/entity/k:h	[Ljava/lang/String;
    //   13: ldc 88
    //   15: iconst_3
    //   16: anewarray 32	java/lang/String
    //   19: dup
    //   20: iconst_0
    //   21: aload_0
    //   22: aastore
    //   23: dup
    //   24: iconst_1
    //   25: aload_3
    //   26: aastore
    //   27: dup
    //   28: iconst_2
    //   29: new 136	java/lang/StringBuilder
    //   32: dup
    //   33: iload_1
    //   34: invokestatic 76	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   37: invokespecial 139	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   40: invokevirtual 143	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   43: aastore
    //   44: aconst_null
    //   45: aconst_null
    //   46: aconst_null
    //   47: ldc 103
    //   49: invokestatic 124	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   52: astore_0
    //   53: aload_0
    //   54: astore_2
    //   55: aload_2
    //   56: astore_0
    //   57: getstatic 34	cn/com/xy/sms/sdk/db/entity/k:h	[Ljava/lang/String;
    //   60: aload_2
    //   61: invokestatic 147	cn/com/xy/sms/sdk/db/base/BaseManager:loadSingleDataFromCursor	([Ljava/lang/String;Lcn/com/xy/sms/sdk/db/XyCursor;)Lorg/json/JSONObject;
    //   64: astore_3
    //   65: aload_2
    //   66: iconst_1
    //   67: invokestatic 134	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   70: aload_3
    //   71: areturn
    //   72: astore_3
    //   73: aconst_null
    //   74: astore_2
    //   75: aload_2
    //   76: astore_0
    //   77: aload_3
    //   78: invokevirtual 113	java/lang/Throwable:printStackTrace	()V
    //   81: aload_2
    //   82: iconst_1
    //   83: invokestatic 134	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   86: aconst_null
    //   87: areturn
    //   88: astore_0
    //   89: aload_2
    //   90: iconst_1
    //   91: invokestatic 134	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   94: aload_0
    //   95: athrow
    //   96: astore_3
    //   97: aload_0
    //   98: astore_2
    //   99: aload_3
    //   100: astore_0
    //   101: goto -12 -> 89
    //   104: astore_3
    //   105: goto -30 -> 75
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	paramString	String
    //   0	108	1	paramInt	int
    //   1	98	2	str	String
    //   7	64	3	localObject1	Object
    //   72	6	3	localThrowable1	Throwable
    //   96	4	3	localObject2	Object
    //   104	1	3	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   8	53	72	java/lang/Throwable
    //   8	53	88	finally
    //   57	65	96	finally
    //   77	81	96	finally
    //   57	65	104	java/lang/Throwable
  }
  
  public static void a()
  {
    try
    {
      DBManager.delete("tb_button_action_scene", null, null);
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public static void a(String paramString)
  {
    try
    {
      DBManager.delete("tb_button_action_scene", "date < ?", new String[] { paramString });
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  /* Error */
  public static java.util.Map<String, String> b()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: ldc 20
    //   4: getstatic 34	cn/com/xy/sms/sdk/db/entity/k:h	[Ljava/lang/String;
    //   7: aconst_null
    //   8: aconst_null
    //   9: aconst_null
    //   10: aconst_null
    //   11: ldc 14
    //   13: aconst_null
    //   14: invokestatic 124	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   17: astore_2
    //   18: aload_2
    //   19: astore_1
    //   20: aload_1
    //   21: ifnonnull +10 -> 31
    //   24: aload_1
    //   25: iconst_1
    //   26: invokestatic 134	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   29: aconst_null
    //   30: areturn
    //   31: new 45	java/util/HashMap
    //   34: dup
    //   35: invokespecial 155	java/util/HashMap:<init>	()V
    //   38: astore_2
    //   39: aload_1
    //   40: invokevirtual 159	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   43: istore_0
    //   44: iload_0
    //   45: ifne +10 -> 55
    //   48: aload_1
    //   49: iconst_1
    //   50: invokestatic 134	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   53: aload_2
    //   54: areturn
    //   55: aload_1
    //   56: iconst_0
    //   57: invokevirtual 161	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   60: astore_3
    //   61: aload_1
    //   62: iconst_2
    //   63: invokevirtual 161	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   66: invokestatic 164	cn/com/xy/sms/sdk/util/StringUtils:getTwoDigitType	(Ljava/lang/String;)Ljava/lang/String;
    //   69: astore 4
    //   71: aload_1
    //   72: iconst_3
    //   73: invokevirtual 161	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   76: invokestatic 164	cn/com/xy/sms/sdk/util/StringUtils:getTwoDigitType	(Ljava/lang/String;)Ljava/lang/String;
    //   79: astore 5
    //   81: aload_2
    //   82: aload_3
    //   83: invokeinterface 170 2 0
    //   88: ifeq +59 -> 147
    //   91: aload_2
    //   92: aload_3
    //   93: new 136	java/lang/StringBuilder
    //   96: dup
    //   97: aload_2
    //   98: aload_3
    //   99: invokeinterface 171 2 0
    //   104: checkcast 32	java/lang/String
    //   107: invokestatic 174	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   110: invokespecial 139	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   113: aload 4
    //   115: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: aload 5
    //   120: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: invokevirtual 143	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   126: invokeinterface 181 3 0
    //   131: pop
    //   132: goto -93 -> 39
    //   135: astore_3
    //   136: aload_3
    //   137: invokevirtual 113	java/lang/Throwable:printStackTrace	()V
    //   140: aload_1
    //   141: iconst_1
    //   142: invokestatic 134	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   145: aload_2
    //   146: areturn
    //   147: aload_2
    //   148: aload_3
    //   149: aload 5
    //   151: invokeinterface 181 3 0
    //   156: pop
    //   157: goto -118 -> 39
    //   160: astore_3
    //   161: aload_1
    //   162: astore_2
    //   163: aload_3
    //   164: astore_1
    //   165: aload_2
    //   166: iconst_1
    //   167: invokestatic 134	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   170: aload_1
    //   171: athrow
    //   172: astore_1
    //   173: aconst_null
    //   174: astore_2
    //   175: goto -10 -> 165
    //   178: astore_3
    //   179: aload_1
    //   180: astore_2
    //   181: aload_3
    //   182: astore_1
    //   183: goto -18 -> 165
    //   186: astore_3
    //   187: aconst_null
    //   188: astore_2
    //   189: goto -53 -> 136
    //   192: astore_3
    //   193: aconst_null
    //   194: astore_2
    //   195: goto -59 -> 136
    // Local variable table:
    //   start	length	slot	name	signature
    //   43	2	0	bool	boolean
    //   1	170	1	localObject1	Object
    //   172	8	1	localObject2	Object
    //   182	1	1	localObject3	Object
    //   17	178	2	localObject4	Object
    //   60	39	3	str1	String
    //   135	14	3	localThrowable1	Throwable
    //   160	4	3	localObject5	Object
    //   178	4	3	localObject6	Object
    //   186	1	3	localThrowable2	Throwable
    //   192	1	3	localThrowable3	Throwable
    //   69	45	4	str2	String
    //   79	71	5	str3	String
    // Exception table:
    //   from	to	target	type
    //   39	44	135	java/lang/Throwable
    //   55	132	135	java/lang/Throwable
    //   147	157	135	java/lang/Throwable
    //   31	39	160	finally
    //   39	44	160	finally
    //   55	132	160	finally
    //   147	157	160	finally
    //   2	18	172	finally
    //   136	140	178	finally
    //   2	18	186	java/lang/Throwable
    //   31	39	192	java/lang/Throwable
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.k
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */