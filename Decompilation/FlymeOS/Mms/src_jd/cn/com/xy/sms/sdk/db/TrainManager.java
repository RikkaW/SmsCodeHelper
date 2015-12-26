package cn.com.xy.sms.sdk.db;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.util.i;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TrainManager
{
  public static final String ADD_DATA_TIME = "ALTER TABLE tb_train ADD COLUMN data_time LONG default 0 ";
  public static final String ADD_STATION_LIST = "ALTER TABLE tb_train ADD COLUMN station_list TEXT ";
  public static final String CREATE_TABLE = "create table  if not exists tb_train (id INTEGER PRIMARY KEY,train_num TEXT not null unique,start_city TEXT,end_city TEXT,train_type INTEGER default 0,start_time TEXT,end_time TEXT,mileage TEXT,station_list TEXT,duration TEXT,data_time LONG default 0)";
  public static final String DATA_TIME = "data_time";
  public static final String DROP_TABLE = " DROP TABLE IF EXISTS tb_train";
  public static final String DURATION = "duration";
  public static final String END_CITY = "end_city";
  public static final String END_TIME = "end_time";
  public static final String ID = "id";
  public static final String MILEAGE = "mileage";
  public static final String START_CITY = "start_city";
  public static final String START_TIME = "start_time";
  public static final String STATION_LIST = "station_list";
  public static final String TABLE_NAME = "tb_train";
  public static final String TRAIN_NUM = "train_num";
  public static final String TRAIN_TYPE = "train_type";
  
  public static void checkDataOnline(XyCallBack paramXyCallBack, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    try
    {
      paramString3 = new g(paramXyCallBack, paramString1, paramString2, paramString3, paramString4);
      paramString2 = i.e(paramString2.replace("次", ""));
      if (NetUtil.isEnhance())
      {
        NetUtil.executePubNumServiceHttpRequest(paramString2, "990005", paramString3, "", true, false, "checi", false);
        return;
      }
      XyUtil.doXycallBackResult(paramXyCallBack, new Object[] { paramString1 });
      return;
    }
    catch (Throwable paramXyCallBack)
    {
      paramXyCallBack.printStackTrace();
    }
  }
  
  public static boolean checkUpdateData()
  {
    new f().start();
    return true;
  }
  
  public static ContentValues getContentValues(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null) {
      try
      {
        if (!paramJSONObject.has("stations")) {
          return null;
        }
        JSONArray localJSONArray1 = paramJSONObject.getJSONArray("stations");
        JSONArray localJSONArray2 = new JSONArray();
        int i = 0;
        for (;;)
        {
          if (i >= localJSONArray1.length())
          {
            paramJSONObject.put("station_list", localJSONArray2);
            paramJSONObject.put("data_time", System.currentTimeMillis());
            return BaseManager.getContentValues(null, new String[] { "train_num", "start_city", "end_city", "train_type", "start_time", "end_time", "mileage", "station_list", "duration", "data_time" }, new String[] { "cc", "sfz", "zdz", "lclx", "fs", "ds", "lc", "station_list", "ls", "data_time" }, paramJSONObject, false);
          }
          JSONObject localJSONObject1 = new JSONObject();
          JSONObject localJSONObject2 = localJSONArray1.getJSONObject(i);
          localJSONObject1.put("name", localJSONObject2.get("cz"));
          localJSONObject1.put("spt", localJSONObject2.get("tcsj"));
          localJSONObject1.put("stt", localJSONObject2.get("kcsj"));
          localJSONObject1.put("day", localJSONObject2.get("rq"));
          localJSONArray2.put(localJSONObject1);
          i += 1;
        }
        return null;
      }
      catch (JSONException paramJSONObject)
      {
        paramJSONObject.printStackTrace();
      }
    }
  }
  
  public static String getEndCity(String paramString)
  {
    String[] arrayOfString = null;
    try
    {
      if (!StringUtils.isNull(paramString)) {
        arrayOfString = paramString.split("/");
      }
      if ((arrayOfString != null) && (arrayOfString.length > 0))
      {
        paramString = queryEndCity(arrayOfString[0]);
        return paramString;
      }
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  /* Error */
  public static void importTrainData(android.content.Context paramContext)
  {
    // Byte code:
    //   0: new 217	java/lang/StringBuilder
    //   3: dup
    //   4: invokestatic 223	cn/com/xy/sms/sdk/constant/Constant:getDRAWBLE_PATH	()Ljava/lang/String;
    //   7: invokestatic 227	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   10: invokespecial 230	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   13: ldc -24
    //   15: invokevirtual 236	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: invokevirtual 239	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   21: astore 6
    //   23: invokestatic 244	cn/com/xy/sms/sdk/ui/popu/util/ViewUtil:getChannelType	()I
    //   26: istore_1
    //   27: aload 6
    //   29: invokestatic 248	cn/com/xy/sms/sdk/util/XyUtil:getLineByCompressFile	(Ljava/lang/String;)Ljava/io/LineNumberReader;
    //   32: astore_0
    //   33: invokestatic 254	cn/com/xy/sms/sdk/db/DBManager:getSQLiteDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   36: astore_2
    //   37: aload_0
    //   38: astore_3
    //   39: aload_2
    //   40: astore 4
    //   42: aload_2
    //   43: invokevirtual 259	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   46: aload_0
    //   47: astore_3
    //   48: aload_2
    //   49: astore 4
    //   51: aload_0
    //   52: invokevirtual 264	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   55: astore 7
    //   57: aload_0
    //   58: astore_3
    //   59: aload_2
    //   60: astore 4
    //   62: new 266	android/content/ContentValues
    //   65: dup
    //   66: invokespecial 267	android/content/ContentValues:<init>	()V
    //   69: astore 5
    //   71: iload_1
    //   72: bipush 6
    //   74: if_icmpeq +9 -> 83
    //   77: iload_1
    //   78: bipush 7
    //   80: if_icmpne +224 -> 304
    //   83: aload_0
    //   84: astore_3
    //   85: aload_2
    //   86: astore 4
    //   88: aload_0
    //   89: invokevirtual 264	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   92: astore 7
    //   94: aload 7
    //   96: ifnonnull +13 -> 109
    //   99: aload 6
    //   101: iconst_1
    //   102: aload_0
    //   103: aconst_null
    //   104: aload_2
    //   105: invokestatic 271	cn/com/xy/sms/sdk/db/DBManager:closeDB	(Ljava/lang/String;ZLjava/io/LineNumberReader;Ljava/io/BufferedReader;Landroid/database/sqlite/SQLiteDatabase;)V
    //   108: return
    //   109: aload_0
    //   110: astore_3
    //   111: aload_2
    //   112: astore 4
    //   114: aload 7
    //   116: ldc_w 273
    //   119: invokevirtual 210	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   122: astore 7
    //   124: aload_0
    //   125: astore_3
    //   126: aload_2
    //   127: astore 4
    //   129: aload 7
    //   131: arraylength
    //   132: iconst_5
    //   133: if_icmplt -50 -> 83
    //   136: aload_0
    //   137: astore_3
    //   138: aload_2
    //   139: astore 4
    //   141: aload 5
    //   143: ldc 50
    //   145: aload 7
    //   147: iconst_0
    //   148: aaload
    //   149: invokevirtual 276	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   152: aload_0
    //   153: astore_3
    //   154: aload_2
    //   155: astore 4
    //   157: aload 5
    //   159: ldc 26
    //   161: aload 7
    //   163: iconst_1
    //   164: aaload
    //   165: invokevirtual 276	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   168: aload_0
    //   169: astore_3
    //   170: aload_2
    //   171: astore 4
    //   173: aload 5
    //   175: ldc 41
    //   177: aload 7
    //   179: iconst_2
    //   180: aaload
    //   181: invokevirtual 276	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   184: aload_0
    //   185: astore_3
    //   186: aload_2
    //   187: astore 4
    //   189: aload 5
    //   191: ldc 29
    //   193: aload 7
    //   195: iconst_3
    //   196: aaload
    //   197: invokevirtual 276	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   200: aload_0
    //   201: astore_3
    //   202: aload_2
    //   203: astore 4
    //   205: aload 7
    //   207: iconst_4
    //   208: aaload
    //   209: invokestatic 204	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   212: ifne +19 -> 231
    //   215: aload_0
    //   216: astore_3
    //   217: aload_2
    //   218: astore 4
    //   220: aload 5
    //   222: ldc 44
    //   224: aload 7
    //   226: iconst_4
    //   227: aaload
    //   228: invokevirtual 276	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   231: aload_0
    //   232: astore_3
    //   233: aload_2
    //   234: astore 4
    //   236: aload_2
    //   237: ldc 47
    //   239: aload 5
    //   241: ldc_w 278
    //   244: iconst_1
    //   245: anewarray 72	java/lang/String
    //   248: dup
    //   249: iconst_0
    //   250: aload 7
    //   252: iconst_0
    //   253: aaload
    //   254: aastore
    //   255: invokevirtual 282	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   258: i2l
    //   259: lconst_1
    //   260: lcmp
    //   261: ifge -178 -> 83
    //   264: aload_0
    //   265: astore_3
    //   266: aload_2
    //   267: astore 4
    //   269: aload_2
    //   270: ldc 47
    //   272: aconst_null
    //   273: aload 5
    //   275: invokevirtual 286	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   278: pop2
    //   279: goto -196 -> 83
    //   282: astore 5
    //   284: aload_0
    //   285: astore_3
    //   286: aload_2
    //   287: astore 4
    //   289: aload 5
    //   291: invokevirtual 105	java/lang/Throwable:printStackTrace	()V
    //   294: aload 6
    //   296: iconst_1
    //   297: aload_0
    //   298: aconst_null
    //   299: aload_2
    //   300: invokestatic 271	cn/com/xy/sms/sdk/db/DBManager:closeDB	(Ljava/lang/String;ZLjava/io/LineNumberReader;Ljava/io/BufferedReader;Landroid/database/sqlite/SQLiteDatabase;)V
    //   303: return
    //   304: aload_0
    //   305: astore_3
    //   306: aload_2
    //   307: astore 4
    //   309: aload 7
    //   311: ldc_w 288
    //   314: invokevirtual 210	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   317: astore 7
    //   319: aload_0
    //   320: astore_3
    //   321: aload_2
    //   322: astore 4
    //   324: aload_0
    //   325: invokevirtual 264	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   328: astore 8
    //   330: aload 8
    //   332: ifnull -233 -> 99
    //   335: aload_0
    //   336: astore_3
    //   337: aload_2
    //   338: astore 4
    //   340: aload 8
    //   342: ldc_w 290
    //   345: invokevirtual 210	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   348: astore 8
    //   350: aload_0
    //   351: astore_3
    //   352: aload_2
    //   353: astore 4
    //   355: aload 5
    //   357: ldc 50
    //   359: aload 8
    //   361: iconst_0
    //   362: aaload
    //   363: invokevirtual 276	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   366: aload_0
    //   367: astore_3
    //   368: aload_2
    //   369: astore 4
    //   371: aload 8
    //   373: arraylength
    //   374: iconst_2
    //   375: if_icmplt +28 -> 403
    //   378: aload_0
    //   379: astore_3
    //   380: aload_2
    //   381: astore 4
    //   383: aload 5
    //   385: ldc 26
    //   387: aload 7
    //   389: aload 8
    //   391: iconst_1
    //   392: aaload
    //   393: invokestatic 295	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   396: invokevirtual 298	java/lang/Integer:intValue	()I
    //   399: aaload
    //   400: invokevirtual 276	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   403: aload_0
    //   404: astore_3
    //   405: aload_2
    //   406: astore 4
    //   408: aload_2
    //   409: ldc 47
    //   411: aload 5
    //   413: ldc_w 278
    //   416: iconst_1
    //   417: anewarray 72	java/lang/String
    //   420: dup
    //   421: iconst_0
    //   422: aload 8
    //   424: iconst_0
    //   425: aaload
    //   426: aastore
    //   427: invokevirtual 282	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   430: i2l
    //   431: lconst_1
    //   432: lcmp
    //   433: ifge -114 -> 319
    //   436: aload_0
    //   437: astore_3
    //   438: aload_2
    //   439: astore 4
    //   441: aload_2
    //   442: ldc 47
    //   444: aconst_null
    //   445: aload 5
    //   447: invokevirtual 286	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   450: pop2
    //   451: goto -132 -> 319
    //   454: astore_2
    //   455: aload_3
    //   456: astore_0
    //   457: aload 6
    //   459: iconst_1
    //   460: aload_0
    //   461: aconst_null
    //   462: aload 4
    //   464: invokestatic 271	cn/com/xy/sms/sdk/db/DBManager:closeDB	(Ljava/lang/String;ZLjava/io/LineNumberReader;Ljava/io/BufferedReader;Landroid/database/sqlite/SQLiteDatabase;)V
    //   467: aload_2
    //   468: athrow
    //   469: astore_2
    //   470: aconst_null
    //   471: astore_0
    //   472: aconst_null
    //   473: astore 4
    //   475: goto -18 -> 457
    //   478: astore_2
    //   479: aconst_null
    //   480: astore 4
    //   482: goto -25 -> 457
    //   485: astore 5
    //   487: aconst_null
    //   488: astore_0
    //   489: aconst_null
    //   490: astore_2
    //   491: goto -207 -> 284
    //   494: astore 5
    //   496: aconst_null
    //   497: astore_2
    //   498: goto -214 -> 284
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	501	0	paramContext	android.content.Context
    //   26	55	1	i	int
    //   36	406	2	localSQLiteDatabase1	android.database.sqlite.SQLiteDatabase
    //   454	14	2	localObject1	Object
    //   469	1	2	localObject2	Object
    //   478	1	2	localObject3	Object
    //   490	8	2	localObject4	Object
    //   38	418	3	localContext	android.content.Context
    //   40	441	4	localSQLiteDatabase2	android.database.sqlite.SQLiteDatabase
    //   69	205	5	localContentValues	ContentValues
    //   282	164	5	localThrowable1	Throwable
    //   485	1	5	localThrowable2	Throwable
    //   494	1	5	localThrowable3	Throwable
    //   21	437	6	str	String
    //   55	333	7	localObject5	Object
    //   328	95	8	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   42	46	282	java/lang/Throwable
    //   51	57	282	java/lang/Throwable
    //   62	71	282	java/lang/Throwable
    //   88	94	282	java/lang/Throwable
    //   114	124	282	java/lang/Throwable
    //   129	136	282	java/lang/Throwable
    //   141	152	282	java/lang/Throwable
    //   157	168	282	java/lang/Throwable
    //   173	184	282	java/lang/Throwable
    //   189	200	282	java/lang/Throwable
    //   205	215	282	java/lang/Throwable
    //   220	231	282	java/lang/Throwable
    //   236	264	282	java/lang/Throwable
    //   269	279	282	java/lang/Throwable
    //   309	319	282	java/lang/Throwable
    //   324	330	282	java/lang/Throwable
    //   340	350	282	java/lang/Throwable
    //   355	366	282	java/lang/Throwable
    //   371	378	282	java/lang/Throwable
    //   383	403	282	java/lang/Throwable
    //   408	436	282	java/lang/Throwable
    //   441	451	282	java/lang/Throwable
    //   42	46	454	finally
    //   51	57	454	finally
    //   62	71	454	finally
    //   88	94	454	finally
    //   114	124	454	finally
    //   129	136	454	finally
    //   141	152	454	finally
    //   157	168	454	finally
    //   173	184	454	finally
    //   189	200	454	finally
    //   205	215	454	finally
    //   220	231	454	finally
    //   236	264	454	finally
    //   269	279	454	finally
    //   289	294	454	finally
    //   309	319	454	finally
    //   324	330	454	finally
    //   340	350	454	finally
    //   355	366	454	finally
    //   371	378	454	finally
    //   383	403	454	finally
    //   408	436	454	finally
    //   441	451	454	finally
    //   23	33	469	finally
    //   33	37	478	finally
    //   23	33	485	java/lang/Throwable
    //   33	37	494	java/lang/Throwable
  }
  
  public static String queryEndCity(String paramString)
  {
    Object localObject2 = null;
    String str2 = null;
    String str1 = str2;
    Object localObject1 = localObject2;
    for (;;)
    {
      try
      {
        paramString = paramString.replaceFirst("次", "");
        str1 = str2;
        localObject1 = localObject2;
        paramString = DBManager.query("tb_train", new String[] { "end_city" }, "train_num = ?", new String[] { paramString });
        if (paramString != null)
        {
          str1 = paramString;
          localObject1 = paramString;
          if (paramString.moveToNext())
          {
            str1 = paramString;
            localObject1 = paramString;
            str2 = paramString.getString(paramString.getColumnIndex("end_city"));
            XyCursor.closeCursor(paramString, true);
            return str2;
          }
        }
      }
      catch (Throwable paramString)
      {
        localObject1 = str1;
        paramString.printStackTrace();
        XyCursor.closeCursor(str1, true);
        return "";
      }
      finally
      {
        XyCursor.closeCursor((XyCursor)localObject1, true);
      }
      XyCursor.closeCursor(paramString, true);
    }
  }
  
  /* Error */
  public static JSONObject queryTrainInfo(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 68
    //   3: ldc 70
    //   5: invokevirtual 302	java/lang/String:replaceFirst	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   8: astore_0
    //   9: bipush 11
    //   11: anewarray 72	java/lang/String
    //   14: astore_2
    //   15: aload_2
    //   16: iconst_0
    //   17: ldc 32
    //   19: aastore
    //   20: aload_2
    //   21: iconst_1
    //   22: ldc 50
    //   24: aastore
    //   25: aload_2
    //   26: iconst_2
    //   27: ldc 38
    //   29: aastore
    //   30: aload_2
    //   31: iconst_3
    //   32: ldc 26
    //   34: aastore
    //   35: aload_2
    //   36: iconst_4
    //   37: ldc 53
    //   39: aastore
    //   40: aload_2
    //   41: iconst_5
    //   42: ldc 41
    //   44: aastore
    //   45: aload_2
    //   46: bipush 6
    //   48: ldc 29
    //   50: aastore
    //   51: aload_2
    //   52: bipush 7
    //   54: ldc 35
    //   56: aastore
    //   57: aload_2
    //   58: bipush 8
    //   60: ldc 44
    //   62: aastore
    //   63: aload_2
    //   64: bipush 9
    //   66: ldc 23
    //   68: aastore
    //   69: aload_2
    //   70: bipush 10
    //   72: ldc 17
    //   74: aastore
    //   75: ldc 47
    //   77: aload_2
    //   78: ldc_w 304
    //   81: iconst_1
    //   82: anewarray 72	java/lang/String
    //   85: dup
    //   86: iconst_0
    //   87: aload_0
    //   88: aastore
    //   89: invokestatic 308	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   92: astore_1
    //   93: aload_1
    //   94: astore_0
    //   95: aload_2
    //   96: aload_1
    //   97: invokestatic 331	cn/com/xy/sms/sdk/db/base/BaseManager:loadSingleDataFromCursor	([Ljava/lang/String;Lcn/com/xy/sms/sdk/db/XyCursor;)Lorg/json/JSONObject;
    //   100: astore_2
    //   101: aload_1
    //   102: iconst_1
    //   103: invokestatic 325	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   106: aload_2
    //   107: areturn
    //   108: astore_2
    //   109: aconst_null
    //   110: astore_1
    //   111: aload_1
    //   112: astore_0
    //   113: aload_2
    //   114: invokevirtual 105	java/lang/Throwable:printStackTrace	()V
    //   117: aload_1
    //   118: iconst_1
    //   119: invokestatic 325	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   122: aconst_null
    //   123: areturn
    //   124: astore_0
    //   125: aconst_null
    //   126: astore_2
    //   127: aload_0
    //   128: astore_1
    //   129: aload_2
    //   130: iconst_1
    //   131: invokestatic 325	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   134: aload_1
    //   135: athrow
    //   136: astore_1
    //   137: aload_0
    //   138: astore_2
    //   139: goto -10 -> 129
    //   142: astore_2
    //   143: goto -32 -> 111
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	146	0	paramString	String
    //   92	43	1	localObject1	Object
    //   136	1	1	localObject2	Object
    //   14	93	2	localObject3	Object
    //   108	6	2	localThrowable1	Throwable
    //   126	13	2	localObject4	Object
    //   142	1	2	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   0	15	108	java/lang/Throwable
    //   75	93	108	java/lang/Throwable
    //   0	15	124	finally
    //   75	93	124	finally
    //   95	101	136	finally
    //   113	117	136	finally
    //   95	101	142	java/lang/Throwable
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.TrainManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */