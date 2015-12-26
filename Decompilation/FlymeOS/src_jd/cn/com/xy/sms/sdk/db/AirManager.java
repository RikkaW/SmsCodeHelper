package cn.com.xy.sms.sdk.db;

public class AirManager
{
  public static final String AIR_NUM = "air_num";
  public static final String COMPANY = "company";
  public static final String CREATE_TABLE = "create table  if not exists tb_air (id INTEGER PRIMARY KEY,air_num TEXT not null unique,start_city TEXT,end_city TEXT,start_place TEXT,end_place TEXT,start_time TEXT,end_time TEXT,company TEXT)";
  public static final String DROP_TABLE = " DROP TABLE IF EXISTS tb_air";
  public static final String END_CITY = "end_city";
  public static final String END_PALCE = "end_place";
  public static final String END_TIME = "end_time";
  public static final String ID = "id";
  public static final String START_CITY = "start_city";
  public static final String START_PLACE = "start_place";
  public static final String START_TIME = "start_time";
  public static final String TABLE_NAME = "tb_air";
  
  public static boolean checkUpdateData()
  {
    new a().start();
    return true;
  }
  
  /* Error */
  public static void importAirData(android.content.Context paramContext)
  {
    // Byte code:
    //   0: new 60	java/lang/StringBuilder
    //   3: dup
    //   4: invokestatic 66	cn/com/xy/sms/sdk/constant/Constant:getDRAWBLE_PATH	()Ljava/lang/String;
    //   7: invokestatic 72	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   10: invokespecial 75	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   13: ldc 77
    //   15: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   21: astore 5
    //   23: aload 5
    //   25: invokestatic 90	cn/com/xy/sms/sdk/util/XyUtil:getLineByCompressFile	(Ljava/lang/String;)Ljava/io/LineNumberReader;
    //   28: astore_0
    //   29: invokestatic 96	cn/com/xy/sms/sdk/db/DBManager:getSQLiteDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: astore_1
    //   33: aload_0
    //   34: astore_3
    //   35: aload_1
    //   36: astore_2
    //   37: aload_1
    //   38: invokevirtual 101	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   41: aload_0
    //   42: astore_3
    //   43: aload_1
    //   44: astore_2
    //   45: aload_0
    //   46: invokevirtual 106	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   49: astore 4
    //   51: aload 4
    //   53: ifnonnull +13 -> 66
    //   56: aload 5
    //   58: iconst_1
    //   59: aload_0
    //   60: aconst_null
    //   61: aload_1
    //   62: invokestatic 110	cn/com/xy/sms/sdk/db/DBManager:closeDB	(Ljava/lang/String;ZLjava/io/LineNumberReader;Ljava/io/BufferedReader;Landroid/database/sqlite/SQLiteDatabase;)V
    //   65: return
    //   66: aload_0
    //   67: astore_3
    //   68: aload_1
    //   69: astore_2
    //   70: aload 4
    //   72: ldc 112
    //   74: invokevirtual 116	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   77: astore 4
    //   79: aload_0
    //   80: astore_3
    //   81: aload_1
    //   82: astore_2
    //   83: aload_0
    //   84: invokevirtual 106	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   87: astore 6
    //   89: aload 6
    //   91: ifnonnull +13 -> 104
    //   94: aload 5
    //   96: iconst_1
    //   97: aload_0
    //   98: aconst_null
    //   99: aload_1
    //   100: invokestatic 110	cn/com/xy/sms/sdk/db/DBManager:closeDB	(Ljava/lang/String;ZLjava/io/LineNumberReader;Ljava/io/BufferedReader;Landroid/database/sqlite/SQLiteDatabase;)V
    //   103: return
    //   104: aload_0
    //   105: astore_3
    //   106: aload_1
    //   107: astore_2
    //   108: aload 6
    //   110: ldc 118
    //   112: invokevirtual 116	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   115: astore 6
    //   117: aload_0
    //   118: astore_3
    //   119: aload_1
    //   120: astore_2
    //   121: aload 6
    //   123: arraylength
    //   124: iconst_3
    //   125: if_icmplt -46 -> 79
    //   128: aload_0
    //   129: astore_3
    //   130: aload_1
    //   131: astore_2
    //   132: new 120	android/content/ContentValues
    //   135: dup
    //   136: invokespecial 121	android/content/ContentValues:<init>	()V
    //   139: astore 7
    //   141: aload_0
    //   142: astore_3
    //   143: aload_1
    //   144: astore_2
    //   145: aload 7
    //   147: ldc 8
    //   149: aload 6
    //   151: iconst_0
    //   152: aaload
    //   153: invokevirtual 125	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   156: aload_0
    //   157: astore_3
    //   158: aload_1
    //   159: astore_2
    //   160: aload 7
    //   162: ldc 35
    //   164: aload 4
    //   166: aload 6
    //   168: iconst_1
    //   169: aaload
    //   170: invokestatic 130	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   173: invokevirtual 134	java/lang/Integer:intValue	()I
    //   176: aaload
    //   177: invokevirtual 125	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   180: aload_0
    //   181: astore_3
    //   182: aload_1
    //   183: astore_2
    //   184: aload 7
    //   186: ldc 23
    //   188: aload 4
    //   190: aload 6
    //   192: iconst_2
    //   193: aaload
    //   194: invokestatic 130	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   197: invokevirtual 134	java/lang/Integer:intValue	()I
    //   200: aaload
    //   201: invokevirtual 125	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   204: aload_0
    //   205: astore_3
    //   206: aload_1
    //   207: astore_2
    //   208: aload_1
    //   209: ldc 41
    //   211: aload 7
    //   213: ldc -120
    //   215: iconst_1
    //   216: anewarray 68	java/lang/String
    //   219: dup
    //   220: iconst_0
    //   221: aload 6
    //   223: iconst_0
    //   224: aaload
    //   225: aastore
    //   226: invokevirtual 140	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   229: i2l
    //   230: lconst_1
    //   231: lcmp
    //   232: ifge -153 -> 79
    //   235: aload_0
    //   236: astore_3
    //   237: aload_1
    //   238: astore_2
    //   239: aload_1
    //   240: ldc 41
    //   242: aconst_null
    //   243: aload 7
    //   245: invokevirtual 144	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   248: pop2
    //   249: goto -170 -> 79
    //   252: astore 4
    //   254: aload_0
    //   255: astore_3
    //   256: aload_1
    //   257: astore_2
    //   258: aload 4
    //   260: invokevirtual 147	java/lang/Throwable:printStackTrace	()V
    //   263: aload 5
    //   265: iconst_1
    //   266: aload_0
    //   267: aconst_null
    //   268: aload_1
    //   269: invokestatic 110	cn/com/xy/sms/sdk/db/DBManager:closeDB	(Ljava/lang/String;ZLjava/io/LineNumberReader;Ljava/io/BufferedReader;Landroid/database/sqlite/SQLiteDatabase;)V
    //   272: return
    //   273: astore_1
    //   274: aconst_null
    //   275: astore_0
    //   276: aconst_null
    //   277: astore_2
    //   278: aload 5
    //   280: iconst_1
    //   281: aload_0
    //   282: aconst_null
    //   283: aload_2
    //   284: invokestatic 110	cn/com/xy/sms/sdk/db/DBManager:closeDB	(Ljava/lang/String;ZLjava/io/LineNumberReader;Ljava/io/BufferedReader;Landroid/database/sqlite/SQLiteDatabase;)V
    //   287: aload_1
    //   288: athrow
    //   289: astore_1
    //   290: aconst_null
    //   291: astore_2
    //   292: goto -14 -> 278
    //   295: astore_1
    //   296: aload_3
    //   297: astore_0
    //   298: goto -20 -> 278
    //   301: astore 4
    //   303: aconst_null
    //   304: astore_0
    //   305: aconst_null
    //   306: astore_1
    //   307: goto -53 -> 254
    //   310: astore 4
    //   312: aconst_null
    //   313: astore_1
    //   314: goto -60 -> 254
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	317	0	paramContext	android.content.Context
    //   32	237	1	localSQLiteDatabase1	android.database.sqlite.SQLiteDatabase
    //   273	15	1	localObject1	Object
    //   289	1	1	localObject2	Object
    //   295	1	1	localObject3	Object
    //   306	8	1	localObject4	Object
    //   36	256	2	localSQLiteDatabase2	android.database.sqlite.SQLiteDatabase
    //   34	263	3	localContext	android.content.Context
    //   49	140	4	localObject5	Object
    //   252	7	4	localThrowable1	Throwable
    //   301	1	4	localThrowable2	Throwable
    //   310	1	4	localThrowable3	Throwable
    //   21	258	5	str	String
    //   87	135	6	localObject6	Object
    //   139	105	7	localContentValues	android.content.ContentValues
    // Exception table:
    //   from	to	target	type
    //   37	41	252	java/lang/Throwable
    //   45	51	252	java/lang/Throwable
    //   70	79	252	java/lang/Throwable
    //   83	89	252	java/lang/Throwable
    //   108	117	252	java/lang/Throwable
    //   121	128	252	java/lang/Throwable
    //   132	141	252	java/lang/Throwable
    //   145	156	252	java/lang/Throwable
    //   160	180	252	java/lang/Throwable
    //   184	204	252	java/lang/Throwable
    //   208	235	252	java/lang/Throwable
    //   239	249	252	java/lang/Throwable
    //   23	29	273	finally
    //   29	33	289	finally
    //   37	41	295	finally
    //   45	51	295	finally
    //   70	79	295	finally
    //   83	89	295	finally
    //   108	117	295	finally
    //   121	128	295	finally
    //   132	141	295	finally
    //   145	156	295	finally
    //   160	180	295	finally
    //   184	204	295	finally
    //   208	235	295	finally
    //   239	249	295	finally
    //   258	263	295	finally
    //   23	29	301	java/lang/Throwable
    //   29	33	310	java/lang/Throwable
  }
  
  /* Error */
  public static String[] queryStartEndPlace(String paramString)
  {
    // Byte code:
    //   0: new 60	java/lang/StringBuilder
    //   3: dup
    //   4: aload_0
    //   5: invokestatic 72	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   8: invokespecial 75	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   11: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   14: astore_0
    //   15: ldc 41
    //   17: iconst_2
    //   18: anewarray 68	java/lang/String
    //   21: dup
    //   22: iconst_0
    //   23: ldc 35
    //   25: aastore
    //   26: dup
    //   27: iconst_1
    //   28: ldc 23
    //   30: aastore
    //   31: ldc -106
    //   33: iconst_1
    //   34: anewarray 68	java/lang/String
    //   37: dup
    //   38: iconst_0
    //   39: aload_0
    //   40: aastore
    //   41: invokestatic 154	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   44: astore_3
    //   45: aload_3
    //   46: ifnull +118 -> 164
    //   49: aload_3
    //   50: astore_0
    //   51: aload_3
    //   52: invokevirtual 159	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   55: ifeq +109 -> 164
    //   58: aload_3
    //   59: astore_0
    //   60: aload_3
    //   61: ldc 35
    //   63: invokevirtual 163	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   66: istore_1
    //   67: aload_3
    //   68: astore_0
    //   69: aload_3
    //   70: ldc 23
    //   72: invokevirtual 163	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   75: istore_2
    //   76: aload_3
    //   77: astore_0
    //   78: aload_3
    //   79: iload_1
    //   80: invokevirtual 167	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   83: astore 4
    //   85: aload_3
    //   86: astore_0
    //   87: aload_3
    //   88: iload_2
    //   89: invokevirtual 167	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   92: astore 5
    //   94: aload_3
    //   95: iconst_1
    //   96: invokestatic 171	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   99: iconst_2
    //   100: anewarray 68	java/lang/String
    //   103: dup
    //   104: iconst_0
    //   105: aload 4
    //   107: aastore
    //   108: dup
    //   109: iconst_1
    //   110: aload 5
    //   112: aastore
    //   113: areturn
    //   114: astore 4
    //   116: aconst_null
    //   117: astore_3
    //   118: aload_3
    //   119: astore_0
    //   120: ldc -83
    //   122: new 60	java/lang/StringBuilder
    //   125: dup
    //   126: ldc -81
    //   128: invokespecial 75	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   131: aload 4
    //   133: invokevirtual 178	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   136: invokevirtual 81	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   142: aload 4
    //   144: invokestatic 184	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   147: aload_3
    //   148: iconst_1
    //   149: invokestatic 171	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   152: aconst_null
    //   153: areturn
    //   154: astore_3
    //   155: aconst_null
    //   156: astore_0
    //   157: aload_0
    //   158: iconst_1
    //   159: invokestatic 171	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   162: aload_3
    //   163: athrow
    //   164: aload_3
    //   165: iconst_1
    //   166: invokestatic 171	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   169: goto -17 -> 152
    //   172: astore_3
    //   173: goto -16 -> 157
    //   176: astore 4
    //   178: goto -60 -> 118
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	181	0	paramString	String
    //   66	14	1	i	int
    //   75	14	2	j	int
    //   44	104	3	localXyCursor1	XyCursor
    //   154	11	3	localXyCursor2	XyCursor
    //   172	1	3	localObject	Object
    //   83	23	4	str1	String
    //   114	29	4	localThrowable1	Throwable
    //   176	1	4	localThrowable2	Throwable
    //   92	19	5	str2	String
    // Exception table:
    //   from	to	target	type
    //   0	45	114	java/lang/Throwable
    //   0	45	154	finally
    //   51	58	172	finally
    //   60	67	172	finally
    //   69	76	172	finally
    //   78	85	172	finally
    //   87	94	172	finally
    //   120	147	172	finally
    //   51	58	176	java/lang/Throwable
    //   60	67	176	java/lang/Throwable
    //   69	76	176	java/lang/Throwable
    //   78	85	176	java/lang/Throwable
    //   87	94	176	java/lang/Throwable
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.AirManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */