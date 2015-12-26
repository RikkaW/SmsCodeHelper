package cn.com.xy.sms.sdk.db.entity;

import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import org.json.JSONObject;

public final class c
{
  private static String a = "tb_emergency_queue";
  
  public static String a()
  {
    return " create table  if not exists tb_emergency_queue ( id INTEGER PRIMARY KEY, emVersion INTEGER, emContent TEXT )";
  }
  
  /* Error */
  public static void a(org.json.JSONArray paramJSONArray)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aload_0
    //   4: ifnull +305 -> 309
    //   7: aload_0
    //   8: invokevirtual 25	org/json/JSONArray:length	()I
    //   11: ifgt +299 -> 310
    //   14: return
    //   15: iload_1
    //   16: aload_0
    //   17: invokevirtual 25	org/json/JSONArray:length	()I
    //   20: if_icmpge +289 -> 309
    //   23: aload_0
    //   24: iload_1
    //   25: invokevirtual 29	org/json/JSONArray:optJSONObject	(I)Lorg/json/JSONObject;
    //   28: astore_3
    //   29: aload_3
    //   30: ifnonnull +15 -> 45
    //   33: aconst_null
    //   34: iconst_1
    //   35: invokestatic 35	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   38: iload_1
    //   39: iconst_1
    //   40: iadd
    //   41: istore_1
    //   42: goto -27 -> 15
    //   45: aload_3
    //   46: ldc 37
    //   48: invokestatic 43	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   51: checkcast 45	java/lang/String
    //   54: astore 4
    //   56: aload_3
    //   57: ldc 47
    //   59: invokestatic 43	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   62: checkcast 45	java/lang/String
    //   65: astore 6
    //   67: aload 6
    //   69: invokestatic 53	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   72: ifne +13 -> 85
    //   75: aload 4
    //   77: invokestatic 53	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   80: istore_2
    //   81: iload_2
    //   82: ifeq +38 -> 120
    //   85: aconst_null
    //   86: iconst_1
    //   87: invokestatic 35	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   90: goto -52 -> 38
    //   93: astore_0
    //   94: ldc 55
    //   96: new 57	java/lang/StringBuilder
    //   99: dup
    //   100: ldc 59
    //   102: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   105: aload_0
    //   106: invokevirtual 65	java/lang/Throwable:getLocalizedMessage	()Ljava/lang/String;
    //   109: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   112: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   115: aload_0
    //   116: invokestatic 78	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   119: return
    //   120: new 57	java/lang/StringBuilder
    //   123: dup
    //   124: aload 6
    //   126: invokestatic 82	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   129: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   132: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   135: astore_3
    //   136: ldc 8
    //   138: iconst_1
    //   139: anewarray 45	java/lang/String
    //   142: dup
    //   143: iconst_0
    //   144: ldc 47
    //   146: aastore
    //   147: ldc 84
    //   149: iconst_1
    //   150: anewarray 45	java/lang/String
    //   153: dup
    //   154: iconst_0
    //   155: aload_3
    //   156: aastore
    //   157: invokestatic 90	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   160: astore_3
    //   161: new 92	android/content/ContentValues
    //   164: dup
    //   165: invokespecial 93	android/content/ContentValues:<init>	()V
    //   168: astore 7
    //   170: aload 7
    //   172: ldc 47
    //   174: aload 6
    //   176: invokevirtual 97	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   179: aload 7
    //   181: ldc 37
    //   183: aload 4
    //   185: invokevirtual 97	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   188: aload_3
    //   189: ifnull +50 -> 239
    //   192: aload_3
    //   193: invokevirtual 100	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   196: ifle +43 -> 239
    //   199: ldc 8
    //   201: aload 7
    //   203: ldc 102
    //   205: iconst_1
    //   206: anewarray 45	java/lang/String
    //   209: dup
    //   210: iconst_0
    //   211: new 57	java/lang/StringBuilder
    //   214: dup
    //   215: aload 6
    //   217: invokestatic 82	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   220: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   223: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   226: aastore
    //   227: invokestatic 106	cn/com/xy/sms/sdk/db/DBManager:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   230: pop
    //   231: aload_3
    //   232: iconst_1
    //   233: invokestatic 35	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   236: goto -198 -> 38
    //   239: ldc 8
    //   241: aload 7
    //   243: invokestatic 110	cn/com/xy/sms/sdk/db/DBManager:insert	(Ljava/lang/String;Landroid/content/ContentValues;)J
    //   246: pop2
    //   247: goto -16 -> 231
    //   250: astore 4
    //   252: ldc 55
    //   254: new 57	java/lang/StringBuilder
    //   257: dup
    //   258: ldc 59
    //   260: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   263: aload 4
    //   265: invokevirtual 65	java/lang/Throwable:getLocalizedMessage	()Ljava/lang/String;
    //   268: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   274: aload 4
    //   276: invokestatic 78	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   279: aload_3
    //   280: iconst_1
    //   281: invokestatic 35	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   284: goto -246 -> 38
    //   287: aload_3
    //   288: iconst_1
    //   289: invokestatic 35	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   292: aload_0
    //   293: athrow
    //   294: astore_0
    //   295: goto -8 -> 287
    //   298: astore_0
    //   299: goto -12 -> 287
    //   302: astore 4
    //   304: aconst_null
    //   305: astore_3
    //   306: goto -54 -> 252
    //   309: return
    //   310: iconst_0
    //   311: istore_1
    //   312: goto -297 -> 15
    //   315: astore_0
    //   316: aload 5
    //   318: astore_3
    //   319: goto -32 -> 287
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	322	0	paramJSONArray	org.json.JSONArray
    //   15	297	1	i	int
    //   80	2	2	bool	boolean
    //   28	291	3	localObject1	Object
    //   54	130	4	str1	String
    //   250	25	4	localThrowable1	Throwable
    //   302	1	4	localThrowable2	Throwable
    //   1	316	5	localObject2	Object
    //   65	151	6	str2	String
    //   168	74	7	localContentValues	android.content.ContentValues
    // Exception table:
    //   from	to	target	type
    //   7	14	93	java/lang/Throwable
    //   15	29	93	java/lang/Throwable
    //   33	38	93	java/lang/Throwable
    //   85	90	93	java/lang/Throwable
    //   231	236	93	java/lang/Throwable
    //   279	284	93	java/lang/Throwable
    //   287	294	93	java/lang/Throwable
    //   161	188	250	java/lang/Throwable
    //   192	231	250	java/lang/Throwable
    //   239	247	250	java/lang/Throwable
    //   161	188	294	finally
    //   192	231	294	finally
    //   239	247	294	finally
    //   252	279	298	finally
    //   45	81	302	java/lang/Throwable
    //   120	161	302	java/lang/Throwable
    //   45	81	315	finally
    //   120	161	315	finally
  }
  
  public static void a(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {}
    for (;;)
    {
      return;
      try
      {
        if (!StringUtils.isNull((String)JsonUtil.getValueFromJsonObject(paramJSONObject, "emVersion")))
        {
          DBManager.delete("tb_emergency_queue", "emVersion = ?", new String[] { paramJSONObject.optString("emVersion") });
          return;
        }
      }
      catch (Throwable paramJSONObject) {}
    }
  }
  
  /* Error */
  public static JSONObject b()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: iconst_3
    //   3: anewarray 45	java/lang/String
    //   6: astore_2
    //   7: aload_2
    //   8: iconst_0
    //   9: ldc 125
    //   11: aastore
    //   12: aload_2
    //   13: iconst_1
    //   14: ldc 47
    //   16: aastore
    //   17: aload_2
    //   18: iconst_2
    //   19: ldc 37
    //   21: aastore
    //   22: ldc 8
    //   24: aload_2
    //   25: aconst_null
    //   26: aconst_null
    //   27: aconst_null
    //   28: aconst_null
    //   29: ldc 127
    //   31: ldc -127
    //   33: invokestatic 132	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   36: astore_0
    //   37: aload_2
    //   38: aload_0
    //   39: invokestatic 138	cn/com/xy/sms/sdk/db/base/BaseManager:loadSingleDataFromCursor	([Ljava/lang/String;Lcn/com/xy/sms/sdk/db/XyCursor;)Lorg/json/JSONObject;
    //   42: astore_1
    //   43: aload_0
    //   44: iconst_1
    //   45: invokestatic 35	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   48: aload_1
    //   49: areturn
    //   50: astore_1
    //   51: aconst_null
    //   52: astore_0
    //   53: aload_1
    //   54: invokevirtual 141	java/lang/Throwable:printStackTrace	()V
    //   57: aload_0
    //   58: iconst_1
    //   59: invokestatic 35	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   62: aconst_null
    //   63: areturn
    //   64: astore_0
    //   65: aload_1
    //   66: iconst_1
    //   67: invokestatic 35	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   70: aload_0
    //   71: athrow
    //   72: astore_2
    //   73: aload_0
    //   74: astore_1
    //   75: aload_2
    //   76: astore_0
    //   77: goto -12 -> 65
    //   80: astore_2
    //   81: aload_0
    //   82: astore_1
    //   83: aload_2
    //   84: astore_0
    //   85: goto -20 -> 65
    //   88: astore_1
    //   89: goto -36 -> 53
    // Local variable table:
    //   start	length	slot	name	signature
    //   36	22	0	localXyCursor	cn.com.xy.sms.sdk.db.XyCursor
    //   64	10	0	localObject1	Object
    //   76	9	0	localObject2	Object
    //   1	48	1	localJSONObject	JSONObject
    //   50	16	1	localThrowable1	Throwable
    //   74	9	1	localObject3	Object
    //   88	1	1	localThrowable2	Throwable
    //   6	32	2	arrayOfString	String[]
    //   72	4	2	localObject4	Object
    //   80	4	2	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	50	java/lang/Throwable
    //   22	37	50	java/lang/Throwable
    //   2	7	64	finally
    //   22	37	64	finally
    //   37	43	72	finally
    //   53	57	80	finally
    //   37	43	88	java/lang/Throwable
  }
  
  /* Error */
  private static void b(JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: ifnonnull +9 -> 12
    //   6: aconst_null
    //   7: iconst_1
    //   8: invokestatic 35	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   11: return
    //   12: aload_3
    //   13: astore_2
    //   14: aload_0
    //   15: ldc 37
    //   17: invokestatic 43	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   20: checkcast 45	java/lang/String
    //   23: astore 4
    //   25: aload_3
    //   26: astore_2
    //   27: aload_0
    //   28: ldc 47
    //   30: invokestatic 43	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   33: checkcast 45	java/lang/String
    //   36: astore 5
    //   38: aload_3
    //   39: astore_2
    //   40: aload 5
    //   42: invokestatic 53	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   45: ifne +15 -> 60
    //   48: aload_3
    //   49: astore_2
    //   50: aload 4
    //   52: invokestatic 53	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   55: istore_1
    //   56: iload_1
    //   57: ifeq +9 -> 66
    //   60: aconst_null
    //   61: iconst_1
    //   62: invokestatic 35	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   65: return
    //   66: aload_3
    //   67: astore_2
    //   68: new 57	java/lang/StringBuilder
    //   71: dup
    //   72: aload 5
    //   74: invokestatic 82	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   77: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   80: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   83: astore_0
    //   84: aload_3
    //   85: astore_2
    //   86: ldc 8
    //   88: iconst_1
    //   89: anewarray 45	java/lang/String
    //   92: dup
    //   93: iconst_0
    //   94: ldc 47
    //   96: aastore
    //   97: ldc 84
    //   99: iconst_1
    //   100: anewarray 45	java/lang/String
    //   103: dup
    //   104: iconst_0
    //   105: aload_0
    //   106: aastore
    //   107: invokestatic 90	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   110: astore_0
    //   111: aload_0
    //   112: astore_2
    //   113: new 92	android/content/ContentValues
    //   116: dup
    //   117: invokespecial 93	android/content/ContentValues:<init>	()V
    //   120: astore_3
    //   121: aload_0
    //   122: astore_2
    //   123: aload_3
    //   124: ldc 47
    //   126: aload 5
    //   128: invokevirtual 97	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   131: aload_0
    //   132: astore_2
    //   133: aload_3
    //   134: ldc 37
    //   136: aload 4
    //   138: invokevirtual 97	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   141: aload_0
    //   142: ifnull +51 -> 193
    //   145: aload_0
    //   146: astore_2
    //   147: aload_0
    //   148: invokevirtual 100	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   151: ifle +42 -> 193
    //   154: aload_0
    //   155: astore_2
    //   156: ldc 8
    //   158: aload_3
    //   159: ldc 102
    //   161: iconst_1
    //   162: anewarray 45	java/lang/String
    //   165: dup
    //   166: iconst_0
    //   167: new 57	java/lang/StringBuilder
    //   170: dup
    //   171: aload 5
    //   173: invokestatic 82	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   176: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   179: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   182: aastore
    //   183: invokestatic 106	cn/com/xy/sms/sdk/db/DBManager:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   186: pop
    //   187: aload_0
    //   188: iconst_1
    //   189: invokestatic 35	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   192: return
    //   193: aload_0
    //   194: astore_2
    //   195: ldc 8
    //   197: aload_3
    //   198: invokestatic 110	cn/com/xy/sms/sdk/db/DBManager:insert	(Ljava/lang/String;Landroid/content/ContentValues;)J
    //   201: pop2
    //   202: goto -15 -> 187
    //   205: astore_2
    //   206: ldc 55
    //   208: new 57	java/lang/StringBuilder
    //   211: dup
    //   212: ldc 59
    //   214: invokespecial 62	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   217: aload_2
    //   218: invokevirtual 65	java/lang/Throwable:getLocalizedMessage	()Ljava/lang/String;
    //   221: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   224: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   227: aload_2
    //   228: invokestatic 78	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   231: aload_0
    //   232: iconst_1
    //   233: invokestatic 35	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   236: return
    //   237: astore_0
    //   238: aload_2
    //   239: iconst_1
    //   240: invokestatic 35	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   243: aload_0
    //   244: athrow
    //   245: astore_3
    //   246: aload_0
    //   247: astore_2
    //   248: aload_3
    //   249: astore_0
    //   250: goto -12 -> 238
    //   253: astore_2
    //   254: aconst_null
    //   255: astore_0
    //   256: goto -50 -> 206
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	259	0	paramJSONObject	JSONObject
    //   55	2	1	bool	boolean
    //   13	182	2	localObject1	Object
    //   205	34	2	localThrowable1	Throwable
    //   247	1	2	localJSONObject	JSONObject
    //   253	1	2	localThrowable2	Throwable
    //   1	197	3	localContentValues	android.content.ContentValues
    //   245	4	3	localObject2	Object
    //   23	114	4	str1	String
    //   36	136	5	str2	String
    // Exception table:
    //   from	to	target	type
    //   113	121	205	java/lang/Throwable
    //   123	131	205	java/lang/Throwable
    //   133	141	205	java/lang/Throwable
    //   147	154	205	java/lang/Throwable
    //   156	187	205	java/lang/Throwable
    //   195	202	205	java/lang/Throwable
    //   14	25	237	finally
    //   27	38	237	finally
    //   40	48	237	finally
    //   50	56	237	finally
    //   68	84	237	finally
    //   86	111	237	finally
    //   113	121	237	finally
    //   123	131	237	finally
    //   133	141	237	finally
    //   147	154	237	finally
    //   156	187	237	finally
    //   195	202	237	finally
    //   206	231	245	finally
    //   14	25	253	java/lang/Throwable
    //   27	38	253	java/lang/Throwable
    //   40	48	253	java/lang/Throwable
    //   50	56	253	java/lang/Throwable
    //   68	84	253	java/lang/Throwable
    //   86	111	253	java/lang/Throwable
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */