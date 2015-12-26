package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.queue.a.a;
import cn.com.xy.sms.sdk.util.JsonUtil;
import org.json.JSONObject;

public final class n
{
  private static String a = "tb_resourse_queue";
  private static String b = " DROP TABLE IF EXISTS tb_resourse_queue";
  
  public static String a()
  {
    return " create table  if not exists tb_resourse_queue ( id INTEGER PRIMARY KEY, res_type INTEGER, res_version INTEGER, res_url TEXT, down_statu INTEGER DEFAULT '0', temp_filename TEXT, down_failed_time LONG DEFAULT '0')";
  }
  
  /* Error */
  public static org.json.JSONArray a(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 8
    //   3: new 24	java/lang/StringBuilder
    //   6: dup
    //   7: iload_0
    //   8: invokestatic 30	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   11: invokespecial 33	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   14: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   17: astore 9
    //   19: ldc 8
    //   21: bipush 7
    //   23: anewarray 26	java/lang/String
    //   26: dup
    //   27: iconst_0
    //   28: ldc 38
    //   30: aastore
    //   31: dup
    //   32: iconst_1
    //   33: ldc 40
    //   35: aastore
    //   36: dup
    //   37: iconst_2
    //   38: ldc 42
    //   40: aastore
    //   41: dup
    //   42: iconst_3
    //   43: ldc 44
    //   45: aastore
    //   46: dup
    //   47: iconst_4
    //   48: ldc 46
    //   50: aastore
    //   51: dup
    //   52: iconst_5
    //   53: ldc 48
    //   55: aastore
    //   56: dup
    //   57: bipush 6
    //   59: ldc 50
    //   61: aastore
    //   62: ldc 52
    //   64: iconst_2
    //   65: anewarray 26	java/lang/String
    //   68: dup
    //   69: iconst_0
    //   70: aload 9
    //   72: aastore
    //   73: dup
    //   74: iconst_1
    //   75: ldc 54
    //   77: aastore
    //   78: aconst_null
    //   79: aconst_null
    //   80: ldc 56
    //   82: aconst_null
    //   83: invokestatic 62	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   86: astore 9
    //   88: aload 9
    //   90: astore 8
    //   92: new 24	java/lang/StringBuilder
    //   95: dup
    //   96: ldc 64
    //   98: invokespecial 33	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   101: aload 8
    //   103: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   106: pop
    //   107: aload 8
    //   109: ifnull +237 -> 346
    //   112: aload 8
    //   114: invokevirtual 74	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   117: ifle +229 -> 346
    //   120: aload 8
    //   122: ldc 38
    //   124: invokevirtual 78	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   127: istore_0
    //   128: aload 8
    //   130: ldc 40
    //   132: invokevirtual 78	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   135: istore_1
    //   136: aload 8
    //   138: ldc 42
    //   140: invokevirtual 78	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   143: istore_2
    //   144: aload 8
    //   146: ldc 44
    //   148: invokevirtual 78	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   151: istore_3
    //   152: aload 8
    //   154: ldc 46
    //   156: invokevirtual 78	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   159: istore 4
    //   161: aload 8
    //   163: ldc 48
    //   165: invokevirtual 78	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   168: istore 5
    //   170: aload 8
    //   172: ldc 50
    //   174: invokevirtual 78	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   177: istore 6
    //   179: new 80	org/json/JSONArray
    //   182: dup
    //   183: invokespecial 81	org/json/JSONArray:<init>	()V
    //   186: astore 9
    //   188: aload 8
    //   190: invokevirtual 85	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   193: istore 7
    //   195: iload 7
    //   197: ifne +12 -> 209
    //   200: aload 8
    //   202: iconst_1
    //   203: invokestatic 89	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   206: aload 9
    //   208: areturn
    //   209: new 91	org/json/JSONObject
    //   212: dup
    //   213: invokespecial 92	org/json/JSONObject:<init>	()V
    //   216: astore 10
    //   218: aload 10
    //   220: ldc 38
    //   222: aload 8
    //   224: iload_0
    //   225: invokevirtual 96	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   228: invokevirtual 100	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   231: pop
    //   232: aload 10
    //   234: ldc 40
    //   236: aload 8
    //   238: iload_1
    //   239: invokevirtual 96	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   242: invokevirtual 100	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   245: pop
    //   246: aload 10
    //   248: ldc 42
    //   250: aload 8
    //   252: iload_2
    //   253: invokevirtual 96	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   256: invokevirtual 100	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   259: pop
    //   260: aload 10
    //   262: ldc 44
    //   264: aload 8
    //   266: iload_3
    //   267: invokevirtual 103	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   270: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   273: pop
    //   274: aload 10
    //   276: ldc 46
    //   278: aload 8
    //   280: iload 4
    //   282: invokevirtual 96	cn/com/xy/sms/sdk/db/XyCursor:getInt	(I)I
    //   285: invokevirtual 100	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   288: pop
    //   289: aload 10
    //   291: ldc 48
    //   293: aload 8
    //   295: iload 5
    //   297: invokevirtual 103	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   300: invokevirtual 106	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   303: pop
    //   304: aload 10
    //   306: ldc 50
    //   308: aload 8
    //   310: iload 6
    //   312: invokevirtual 110	cn/com/xy/sms/sdk/db/XyCursor:getLong	(I)J
    //   315: invokevirtual 113	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   318: pop
    //   319: aload 9
    //   321: aload 10
    //   323: invokevirtual 116	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   326: pop
    //   327: goto -139 -> 188
    //   330: astore 10
    //   332: aload 10
    //   334: invokevirtual 119	java/lang/Throwable:printStackTrace	()V
    //   337: aload 8
    //   339: iconst_1
    //   340: invokestatic 89	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   343: aload 9
    //   345: areturn
    //   346: aconst_null
    //   347: astore 9
    //   349: goto -149 -> 200
    //   352: astore 9
    //   354: aconst_null
    //   355: astore 8
    //   357: aload 8
    //   359: iconst_1
    //   360: invokestatic 89	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   363: aload 9
    //   365: athrow
    //   366: astore 9
    //   368: goto -11 -> 357
    //   371: astore 9
    //   373: goto -16 -> 357
    //   376: astore 10
    //   378: aconst_null
    //   379: astore 9
    //   381: goto -49 -> 332
    //   384: astore 10
    //   386: aconst_null
    //   387: astore 9
    //   389: goto -57 -> 332
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	392	0	paramInt	int
    //   135	104	1	i	int
    //   143	110	2	j	int
    //   151	116	3	k	int
    //   159	122	4	m	int
    //   168	128	5	n	int
    //   177	134	6	i1	int
    //   193	3	7	bool	boolean
    //   1	357	8	localObject1	Object
    //   17	331	9	localObject2	Object
    //   352	12	9	localObject3	Object
    //   366	1	9	localObject4	Object
    //   371	1	9	localObject5	Object
    //   379	9	9	localObject6	Object
    //   216	106	10	localJSONObject	JSONObject
    //   330	3	10	localThrowable1	Throwable
    //   376	1	10	localThrowable2	Throwable
    //   384	1	10	localThrowable3	Throwable
    // Exception table:
    //   from	to	target	type
    //   188	195	330	java/lang/Throwable
    //   209	327	330	java/lang/Throwable
    //   3	88	352	finally
    //   92	107	366	finally
    //   112	188	366	finally
    //   188	195	366	finally
    //   209	327	366	finally
    //   332	337	371	finally
    //   3	88	376	java/lang/Throwable
    //   92	107	384	java/lang/Throwable
    //   112	188	384	java/lang/Throwable
  }
  
  public static void a(Integer paramInteger, boolean paramBoolean, String paramString)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      if (paramBoolean)
      {
        localContentValues.put("res_url", "");
        localContentValues.put("down_failed_time", "0");
        localContentValues.put("down_statu", "1");
        localContentValues.put("temp_filename", paramString);
      }
      for (;;)
      {
        DBManager.update("tb_resourse_queue", localContentValues, "id = ? ", new String[] { String.valueOf(paramInteger) });
        return;
        localContentValues.put("down_failed_time", Long.valueOf(System.currentTimeMillis()));
        localContentValues.put("down_statu", "0");
        localContentValues.put("temp_filename", paramString);
      }
      return;
    }
    catch (Throwable paramInteger)
    {
      paramInteger.printStackTrace();
    }
  }
  
  /* Error */
  public static void a(org.json.JSONArray paramJSONArray)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +478 -> 479
    //   4: aload_0
    //   5: invokevirtual 157	org/json/JSONArray:length	()I
    //   8: ifle +471 -> 479
    //   11: aload_0
    //   12: invokevirtual 157	org/json/JSONArray:length	()I
    //   15: istore_2
    //   16: iconst_0
    //   17: istore_1
    //   18: goto +456 -> 474
    //   21: aload_0
    //   22: iload_1
    //   23: invokevirtual 161	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   26: astore 6
    //   28: aload 6
    //   30: ldc -93
    //   32: invokestatic 169	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   35: checkcast 26	java/lang/String
    //   38: astore 4
    //   40: aload 4
    //   42: invokestatic 175	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   45: ifne +99 -> 144
    //   48: aload 4
    //   50: ldc -126
    //   52: invokevirtual 179	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   55: istore_3
    //   56: iload_3
    //   57: ifeq +87 -> 144
    //   60: aload 6
    //   62: ifnull +82 -> 144
    //   65: aload 6
    //   67: ldc 40
    //   69: invokestatic 169	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   72: checkcast 26	java/lang/String
    //   75: astore 4
    //   77: aload 6
    //   79: ldc 42
    //   81: invokestatic 169	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   84: checkcast 26	java/lang/String
    //   87: astore 5
    //   89: ldc 8
    //   91: ldc -75
    //   93: iconst_2
    //   94: anewarray 26	java/lang/String
    //   97: dup
    //   98: iconst_0
    //   99: new 24	java/lang/StringBuilder
    //   102: dup
    //   103: aload 4
    //   105: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   108: invokespecial 33	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   111: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   114: aastore
    //   115: dup
    //   116: iconst_1
    //   117: new 24	java/lang/StringBuilder
    //   120: dup
    //   121: aload 5
    //   123: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   126: invokespecial 33	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   129: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   132: aastore
    //   133: invokestatic 185	cn/com/xy/sms/sdk/db/DBManager:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   136: pop
    //   137: aload 4
    //   139: aload 5
    //   141: invokestatic 189	cn/com/xy/sms/sdk/queue/a/a:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   144: aconst_null
    //   145: astore 4
    //   147: aload 6
    //   149: ldc 40
    //   151: invokestatic 169	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   154: checkcast 26	java/lang/String
    //   157: astore 7
    //   159: aload 6
    //   161: ldc 42
    //   163: invokestatic 169	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   166: checkcast 26	java/lang/String
    //   169: astore 8
    //   171: aload 6
    //   173: ifnull +295 -> 468
    //   176: new 24	java/lang/StringBuilder
    //   179: dup
    //   180: aload 7
    //   182: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   185: invokespecial 33	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   188: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   191: astore 5
    //   193: new 24	java/lang/StringBuilder
    //   196: dup
    //   197: aload 8
    //   199: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   202: invokespecial 33	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   205: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   208: astore 9
    //   210: new 24	java/lang/StringBuilder
    //   213: dup
    //   214: aload 7
    //   216: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   219: invokespecial 33	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   222: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   225: astore 10
    //   227: new 24	java/lang/StringBuilder
    //   230: dup
    //   231: aload 8
    //   233: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   236: invokespecial 33	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   239: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   242: astore 11
    //   244: ldc 8
    //   246: iconst_2
    //   247: anewarray 26	java/lang/String
    //   250: dup
    //   251: iconst_0
    //   252: aload 5
    //   254: aastore
    //   255: dup
    //   256: iconst_1
    //   257: aload 9
    //   259: aastore
    //   260: ldc -65
    //   262: iconst_2
    //   263: anewarray 26	java/lang/String
    //   266: dup
    //   267: iconst_0
    //   268: aload 10
    //   270: aastore
    //   271: dup
    //   272: iconst_1
    //   273: aload 11
    //   275: aastore
    //   276: invokestatic 194	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   279: astore 5
    //   281: aload 5
    //   283: astore 4
    //   285: new 122	android/content/ContentValues
    //   288: dup
    //   289: invokespecial 123	android/content/ContentValues:<init>	()V
    //   292: astore 5
    //   294: aload 5
    //   296: ldc 40
    //   298: aload 7
    //   300: invokevirtual 128	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   303: aload 5
    //   305: ldc 42
    //   307: aload 8
    //   309: invokevirtual 128	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   312: aload 5
    //   314: ldc 44
    //   316: aload 6
    //   318: ldc 44
    //   320: invokestatic 169	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   323: checkcast 26	java/lang/String
    //   326: invokevirtual 128	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   329: aload 4
    //   331: ifnull +86 -> 417
    //   334: aload 4
    //   336: invokevirtual 74	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   339: ifle +78 -> 417
    //   342: ldc 8
    //   344: aload 5
    //   346: ldc -65
    //   348: iconst_2
    //   349: anewarray 26	java/lang/String
    //   352: dup
    //   353: iconst_0
    //   354: new 24	java/lang/StringBuilder
    //   357: dup
    //   358: aload 7
    //   360: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   363: invokespecial 33	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   366: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   369: aastore
    //   370: dup
    //   371: iconst_1
    //   372: new 24	java/lang/StringBuilder
    //   375: dup
    //   376: aload 8
    //   378: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   381: invokespecial 33	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   384: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   387: aastore
    //   388: invokestatic 139	cn/com/xy/sms/sdk/db/DBManager:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   391: pop
    //   392: aload 4
    //   394: iconst_1
    //   395: invokestatic 89	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   398: goto +82 -> 480
    //   401: astore 4
    //   403: aload 4
    //   405: invokevirtual 119	java/lang/Throwable:printStackTrace	()V
    //   408: goto -264 -> 144
    //   411: astore_0
    //   412: aload_0
    //   413: invokevirtual 119	java/lang/Throwable:printStackTrace	()V
    //   416: return
    //   417: ldc 8
    //   419: aload 5
    //   421: invokestatic 198	cn/com/xy/sms/sdk/db/DBManager:insert	(Ljava/lang/String;Landroid/content/ContentValues;)J
    //   424: pop2
    //   425: goto -33 -> 392
    //   428: astore 5
    //   430: aload 5
    //   432: invokevirtual 119	java/lang/Throwable:printStackTrace	()V
    //   435: aload 4
    //   437: iconst_1
    //   438: invokestatic 89	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   441: goto +39 -> 480
    //   444: aload 4
    //   446: iconst_1
    //   447: invokestatic 89	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   450: aload_0
    //   451: athrow
    //   452: astore_0
    //   453: goto -9 -> 444
    //   456: astore_0
    //   457: goto -13 -> 444
    //   460: astore 5
    //   462: aconst_null
    //   463: astore 4
    //   465: goto -35 -> 430
    //   468: aconst_null
    //   469: astore 4
    //   471: goto -79 -> 392
    //   474: iload_1
    //   475: iload_2
    //   476: if_icmplt -455 -> 21
    //   479: return
    //   480: iload_1
    //   481: iconst_1
    //   482: iadd
    //   483: istore_1
    //   484: goto -10 -> 474
    //   487: astore_0
    //   488: goto -44 -> 444
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	491	0	paramJSONArray	org.json.JSONArray
    //   17	467	1	i	int
    //   15	462	2	j	int
    //   55	2	3	bool	boolean
    //   38	355	4	localObject1	Object
    //   401	44	4	localThrowable1	Throwable
    //   463	7	4	localObject2	Object
    //   87	333	5	localObject3	Object
    //   428	3	5	localThrowable2	Throwable
    //   460	1	5	localThrowable3	Throwable
    //   26	291	6	localJSONObject	JSONObject
    //   157	202	7	str1	String
    //   169	208	8	str2	String
    //   208	50	9	str3	String
    //   225	44	10	str4	String
    //   242	32	11	str5	String
    // Exception table:
    //   from	to	target	type
    //   65	144	401	java/lang/Throwable
    //   4	16	411	java/lang/Throwable
    //   21	56	411	java/lang/Throwable
    //   392	398	411	java/lang/Throwable
    //   403	408	411	java/lang/Throwable
    //   435	441	411	java/lang/Throwable
    //   444	452	411	java/lang/Throwable
    //   285	329	428	java/lang/Throwable
    //   334	392	428	java/lang/Throwable
    //   417	425	428	java/lang/Throwable
    //   285	329	452	finally
    //   334	392	452	finally
    //   417	425	452	finally
    //   430	435	456	finally
    //   147	171	460	java/lang/Throwable
    //   176	281	460	java/lang/Throwable
    //   147	171	487	finally
    //   176	281	487	finally
  }
  
  private static void a(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null) {}
    try
    {
      String str = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "res_type");
      paramJSONObject = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "res_version");
      DBManager.delete("tb_resourse_queue", "res_type = ? and res_version < ?", new String[] { str, paramJSONObject });
      a.a(str, paramJSONObject);
      return;
    }
    catch (Throwable paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
  }
  
  public static void a(boolean paramBoolean, String paramString)
  {
    int i = 0;
    try
    {
      ContentValues localContentValues = new ContentValues();
      if (paramBoolean) {
        i = 2;
      }
      localContentValues.put("down_statu", Integer.valueOf(i));
      new StringBuilder("unzipSuess=").append(paramBoolean).append(" fileNam=").append(paramString);
      DBManager.update("tb_resourse_queue", localContentValues, "temp_filename = ?", new String[] { paramString });
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  /* Error */
  public static String b(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aconst_null
    //   4: astore 6
    //   6: ldc 125
    //   8: astore_2
    //   9: aload_2
    //   10: astore 5
    //   12: aload 6
    //   14: astore 4
    //   16: aload 7
    //   18: astore_3
    //   19: new 24	java/lang/StringBuilder
    //   22: dup
    //   23: ldc -34
    //   25: invokespecial 33	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   28: iload_0
    //   29: invokevirtual 225	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   32: ldc -29
    //   34: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   37: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   40: astore 8
    //   42: aload_2
    //   43: astore 5
    //   45: aload 6
    //   47: astore 4
    //   49: aload 7
    //   51: astore_3
    //   52: ldc 8
    //   54: iconst_1
    //   55: anewarray 26	java/lang/String
    //   58: dup
    //   59: iconst_0
    //   60: ldc 42
    //   62: aastore
    //   63: aload 8
    //   65: aconst_null
    //   66: invokestatic 194	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   69: astore 6
    //   71: aload_2
    //   72: astore 7
    //   74: aload 6
    //   76: ifnull +47 -> 123
    //   79: aload_2
    //   80: astore 7
    //   82: aload_2
    //   83: astore 5
    //   85: aload 6
    //   87: astore 4
    //   89: aload 6
    //   91: astore_3
    //   92: aload 6
    //   94: invokevirtual 74	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   97: ifle +26 -> 123
    //   100: aload_2
    //   101: astore 5
    //   103: aload 6
    //   105: astore 4
    //   107: aload 6
    //   109: astore_3
    //   110: aload 6
    //   112: invokevirtual 85	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   115: istore_1
    //   116: iload_1
    //   117: ifne +15 -> 132
    //   120: aload_2
    //   121: astore 7
    //   123: aload 6
    //   125: iconst_1
    //   126: invokestatic 89	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   129: aload 7
    //   131: areturn
    //   132: aload_2
    //   133: astore 5
    //   135: aload 6
    //   137: astore 4
    //   139: aload 6
    //   141: astore_3
    //   142: aload 6
    //   144: iconst_0
    //   145: invokevirtual 103	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   148: astore_2
    //   149: goto -49 -> 100
    //   152: astore_2
    //   153: aload 4
    //   155: astore_3
    //   156: aload_2
    //   157: invokevirtual 119	java/lang/Throwable:printStackTrace	()V
    //   160: aload 4
    //   162: iconst_1
    //   163: invokestatic 89	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   166: aload 5
    //   168: areturn
    //   169: astore_2
    //   170: aload_3
    //   171: iconst_1
    //   172: invokestatic 89	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   175: aload_2
    //   176: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	177	0	paramInt	int
    //   115	2	1	bool	boolean
    //   8	141	2	str1	String
    //   152	5	2	localThrowable	Throwable
    //   169	7	2	localObject1	Object
    //   18	153	3	localObject2	Object
    //   14	147	4	localXyCursor1	cn.com.xy.sms.sdk.db.XyCursor
    //   10	157	5	str2	String
    //   4	139	6	localXyCursor2	cn.com.xy.sms.sdk.db.XyCursor
    //   1	129	7	localObject3	Object
    //   40	24	8	str3	String
    // Exception table:
    //   from	to	target	type
    //   19	42	152	java/lang/Throwable
    //   52	71	152	java/lang/Throwable
    //   92	100	152	java/lang/Throwable
    //   110	116	152	java/lang/Throwable
    //   142	149	152	java/lang/Throwable
    //   19	42	169	finally
    //   52	71	169	finally
    //   92	100	169	finally
    //   110	116	169	finally
    //   142	149	169	finally
    //   156	160	169	finally
  }
  
  /* Error */
  private static void b(JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aconst_null
    //   3: astore_3
    //   4: aload_1
    //   5: astore_2
    //   6: aload_0
    //   7: ldc 40
    //   9: invokestatic 169	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   12: checkcast 26	java/lang/String
    //   15: astore 4
    //   17: aload_1
    //   18: astore_2
    //   19: aload_0
    //   20: ldc 42
    //   22: invokestatic 169	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   25: checkcast 26	java/lang/String
    //   28: astore 5
    //   30: aload_0
    //   31: ifnull +273 -> 304
    //   34: aload_1
    //   35: astore_2
    //   36: new 24	java/lang/StringBuilder
    //   39: dup
    //   40: aload 4
    //   42: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   45: invokespecial 33	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   48: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   51: astore 6
    //   53: aload_1
    //   54: astore_2
    //   55: new 24	java/lang/StringBuilder
    //   58: dup
    //   59: aload 5
    //   61: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   64: invokespecial 33	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   67: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   70: astore 7
    //   72: aload_1
    //   73: astore_2
    //   74: new 24	java/lang/StringBuilder
    //   77: dup
    //   78: aload 4
    //   80: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   83: invokespecial 33	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   86: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   89: astore 8
    //   91: aload_1
    //   92: astore_2
    //   93: new 24	java/lang/StringBuilder
    //   96: dup
    //   97: aload 5
    //   99: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   102: invokespecial 33	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   105: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   108: astore 9
    //   110: aload_1
    //   111: astore_2
    //   112: ldc 8
    //   114: iconst_2
    //   115: anewarray 26	java/lang/String
    //   118: dup
    //   119: iconst_0
    //   120: aload 6
    //   122: aastore
    //   123: dup
    //   124: iconst_1
    //   125: aload 7
    //   127: aastore
    //   128: ldc -65
    //   130: iconst_2
    //   131: anewarray 26	java/lang/String
    //   134: dup
    //   135: iconst_0
    //   136: aload 8
    //   138: aastore
    //   139: dup
    //   140: iconst_1
    //   141: aload 9
    //   143: aastore
    //   144: invokestatic 194	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   147: astore_1
    //   148: new 122	android/content/ContentValues
    //   151: dup
    //   152: invokespecial 123	android/content/ContentValues:<init>	()V
    //   155: astore_2
    //   156: aload_2
    //   157: ldc 40
    //   159: aload 4
    //   161: invokevirtual 128	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   164: aload_2
    //   165: ldc 42
    //   167: aload 5
    //   169: invokevirtual 128	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   172: aload_2
    //   173: ldc 44
    //   175: aload_0
    //   176: ldc 44
    //   178: invokestatic 169	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   181: checkcast 26	java/lang/String
    //   184: invokevirtual 128	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   187: aload_1
    //   188: ifnull +67 -> 255
    //   191: aload_1
    //   192: invokevirtual 74	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   195: ifle +60 -> 255
    //   198: ldc 8
    //   200: aload_2
    //   201: ldc -65
    //   203: iconst_2
    //   204: anewarray 26	java/lang/String
    //   207: dup
    //   208: iconst_0
    //   209: new 24	java/lang/StringBuilder
    //   212: dup
    //   213: aload 4
    //   215: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   218: invokespecial 33	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   221: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   224: aastore
    //   225: dup
    //   226: iconst_1
    //   227: new 24	java/lang/StringBuilder
    //   230: dup
    //   231: aload 5
    //   233: invokestatic 135	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   236: invokespecial 33	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   239: invokevirtual 36	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   242: aastore
    //   243: invokestatic 139	cn/com/xy/sms/sdk/db/DBManager:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   246: pop
    //   247: aload_1
    //   248: astore_0
    //   249: aload_0
    //   250: iconst_1
    //   251: invokestatic 89	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   254: return
    //   255: ldc 8
    //   257: aload_2
    //   258: invokestatic 198	cn/com/xy/sms/sdk/db/DBManager:insert	(Ljava/lang/String;Landroid/content/ContentValues;)J
    //   261: pop2
    //   262: aload_1
    //   263: astore_0
    //   264: goto -15 -> 249
    //   267: astore_1
    //   268: aload_3
    //   269: astore_0
    //   270: aload_0
    //   271: astore_2
    //   272: aload_1
    //   273: invokevirtual 119	java/lang/Throwable:printStackTrace	()V
    //   276: aload_0
    //   277: iconst_1
    //   278: invokestatic 89	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   281: return
    //   282: astore_0
    //   283: aload_2
    //   284: iconst_1
    //   285: invokestatic 89	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   288: aload_0
    //   289: athrow
    //   290: astore_0
    //   291: aload_1
    //   292: astore_2
    //   293: goto -10 -> 283
    //   296: astore_2
    //   297: aload_1
    //   298: astore_0
    //   299: aload_2
    //   300: astore_1
    //   301: goto -31 -> 270
    //   304: aconst_null
    //   305: astore_0
    //   306: goto -57 -> 249
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	309	0	paramJSONObject	JSONObject
    //   1	262	1	localXyCursor	cn.com.xy.sms.sdk.db.XyCursor
    //   267	31	1	localThrowable1	Throwable
    //   300	1	1	localObject1	Object
    //   5	288	2	localObject2	Object
    //   296	4	2	localThrowable2	Throwable
    //   3	266	3	localObject3	Object
    //   15	199	4	str1	String
    //   28	204	5	str2	String
    //   51	70	6	str3	String
    //   70	56	7	str4	String
    //   89	48	8	str5	String
    //   108	34	9	str6	String
    // Exception table:
    //   from	to	target	type
    //   6	17	267	java/lang/Throwable
    //   19	30	267	java/lang/Throwable
    //   36	53	267	java/lang/Throwable
    //   55	72	267	java/lang/Throwable
    //   74	91	267	java/lang/Throwable
    //   93	110	267	java/lang/Throwable
    //   112	148	267	java/lang/Throwable
    //   6	17	282	finally
    //   19	30	282	finally
    //   36	53	282	finally
    //   55	72	282	finally
    //   74	91	282	finally
    //   93	110	282	finally
    //   112	148	282	finally
    //   272	276	282	finally
    //   148	187	290	finally
    //   191	247	290	finally
    //   255	262	290	finally
    //   148	187	296	java/lang/Throwable
    //   191	247	296	java/lang/Throwable
    //   255	262	296	java/lang/Throwable
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.n
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */