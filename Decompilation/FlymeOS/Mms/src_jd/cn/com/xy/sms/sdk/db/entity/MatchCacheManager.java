package cn.com.xy.sms.sdk.db.entity;

import android.database.sqlite.SQLiteDatabase;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class MatchCacheManager
{
  public static final String ADD_bubble_lasttime = "ALTER TABLE tb_match_cache ADD COLUMN bubble_lasttime INTEGER DEFAULT '0'";
  public static final String ADD_card_lasttime = "ALTER TABLE tb_match_cache ADD COLUMN card_lasttime INTEGER DEFAULT '0'";
  public static final String ADD_session_lasttime = "ALTER TABLE tb_match_cache ADD COLUMN session_lasttime INTEGER DEFAULT '0'";
  public static final String DROP_TABLE = " DROP TABLE IF EXISTS tb_match_cache";
  public static final String TABLE_NAME = "tb_match_cache";
  
  private static long a()
  {
    String str = SysParamEntityManager.getStringParam(Constant.getContext(), "REPARSE_BUBBLE_CYCLE");
    long l2 = -1L;
    long l1 = l2;
    if (!"-1".equals(str)) {}
    try
    {
      l1 = Long.parseLong(str);
      if (l1 > 0L) {
        return l1;
      }
      return DexUtil.getUpdateCycleByType(14, 21600000L);
    }
    catch (Exception localException)
    {
      for (;;)
      {
        l1 = l2;
      }
    }
  }
  
  private static Map<String, JSONObject> a(String[] paramArrayOfString, int paramInt, XyCursor paramXyCursor)
  {
    if (paramXyCursor != null) {}
    for (;;)
    {
      JSONObject localJSONObject;
      try
      {
        HashMap localHashMap = new HashMap();
        if (!paramXyCursor.moveToNext()) {
          return localHashMap;
        }
        localJSONObject = new JSONObject();
        paramInt = 0;
        if (paramInt >= 9)
        {
          a(localJSONObject);
          localHashMap.put((String)JsonUtil.getValueFromJsonObject(localJSONObject, paramArrayOfString[0]), localJSONObject);
          continue;
          return null;
        }
      }
      catch (Throwable paramArrayOfString)
      {
        new StringBuilder("loadMapDataFromCursor Throwable=").append(paramArrayOfString.getLocalizedMessage());
      }
      localJSONObject.put(paramArrayOfString[paramInt], StringUtils.getNoNullString(paramXyCursor.getString(paramInt)));
      paramInt += 1;
    }
  }
  
  /* Error */
  private static void a(JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc -127
    //   3: invokestatic 91	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   6: checkcast 49	java/lang/String
    //   9: astore 5
    //   11: aload_0
    //   12: ldc -125
    //   14: invokestatic 91	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   17: invokevirtual 134	java/lang/Object:toString	()Ljava/lang/String;
    //   20: invokestatic 138	java/lang/Long:valueOf	(Ljava/lang/String;)Ljava/lang/Long;
    //   23: invokevirtual 141	java/lang/Long:longValue	()J
    //   26: lstore_1
    //   27: aload 5
    //   29: invokestatic 145	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   32: ifne +141 -> 173
    //   35: aload_0
    //   36: ldc -127
    //   38: new 81	org/json/JSONObject
    //   41: dup
    //   42: aload 5
    //   44: invokespecial 146	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   47: invokevirtual 125	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   50: pop
    //   51: aload_0
    //   52: ldc -108
    //   54: invokestatic 91	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   57: checkcast 49	java/lang/String
    //   60: astore 5
    //   62: aload_0
    //   63: ldc -106
    //   65: invokestatic 91	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   68: invokevirtual 134	java/lang/Object:toString	()Ljava/lang/String;
    //   71: invokestatic 138	java/lang/Long:valueOf	(Ljava/lang/String;)Ljava/lang/Long;
    //   74: invokevirtual 141	java/lang/Long:longValue	()J
    //   77: lstore_1
    //   78: new 99	java/lang/StringBuilder
    //   81: dup
    //   82: ldc -104
    //   84: invokespecial 104	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   87: aload 5
    //   89: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: ldc -102
    //   94: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: lload_1
    //   98: invokevirtual 157	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   101: pop
    //   102: aload 5
    //   104: invokestatic 145	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   107: ifne +115 -> 222
    //   110: new 159	org/json/JSONArray
    //   113: dup
    //   114: aload 5
    //   116: invokespecial 160	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   119: astore 6
    //   121: aload_0
    //   122: ldc -108
    //   124: aload 6
    //   126: invokevirtual 125	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   129: pop
    //   130: ldc -94
    //   132: new 99	java/lang/StringBuilder
    //   135: dup
    //   136: ldc -104
    //   138: invokespecial 104	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   141: aload 5
    //   143: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: ldc -102
    //   148: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: lload_1
    //   152: invokevirtual 157	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   155: ldc -92
    //   157: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: aload 6
    //   162: invokevirtual 167	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   165: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   168: aconst_null
    //   169: invokestatic 174	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   172: return
    //   173: aload_0
    //   174: ldc -127
    //   176: invokevirtual 178	org/json/JSONObject:remove	(Ljava/lang/String;)Ljava/lang/Object;
    //   179: pop
    //   180: invokestatic 180	cn/com/xy/sms/sdk/db/entity/MatchCacheManager:a	()J
    //   183: lstore_3
    //   184: lload_1
    //   185: lconst_0
    //   186: lcmp
    //   187: ifeq +13 -> 200
    //   190: invokestatic 185	java/lang/System:currentTimeMillis	()J
    //   193: lload_1
    //   194: lsub
    //   195: lload_3
    //   196: lcmp
    //   197: iflt -146 -> 51
    //   200: aload_0
    //   201: ldc -69
    //   203: ldc -67
    //   205: invokevirtual 125	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   208: pop
    //   209: goto -158 -> 51
    //   212: astore 5
    //   214: aload 5
    //   216: invokevirtual 192	java/lang/Throwable:printStackTrace	()V
    //   219: goto -168 -> 51
    //   222: ldc -94
    //   224: new 99	java/lang/StringBuilder
    //   227: dup
    //   228: ldc -104
    //   230: invokespecial 104	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   233: aload 5
    //   235: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: ldc -102
    //   240: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: lload_1
    //   244: invokevirtual 157	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   247: ldc -62
    //   249: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   252: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   255: aconst_null
    //   256: invokestatic 174	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   259: aload_0
    //   260: ldc -108
    //   262: invokevirtual 178	org/json/JSONObject:remove	(Ljava/lang/String;)Ljava/lang/Object;
    //   265: pop
    //   266: invokestatic 180	cn/com/xy/sms/sdk/db/entity/MatchCacheManager:a	()J
    //   269: lstore_3
    //   270: lload_1
    //   271: lconst_0
    //   272: lcmp
    //   273: ifeq +13 -> 286
    //   276: invokestatic 185	java/lang/System:currentTimeMillis	()J
    //   279: lload_1
    //   280: lsub
    //   281: lload_3
    //   282: lcmp
    //   283: iflt -111 -> 172
    //   286: aload_0
    //   287: ldc -60
    //   289: ldc -67
    //   291: invokevirtual 125	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   294: pop
    //   295: return
    //   296: astore_0
    //   297: aload_0
    //   298: invokevirtual 192	java/lang/Throwable:printStackTrace	()V
    //   301: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	302	0	paramJSONObject	JSONObject
    //   26	254	1	l1	long
    //   183	99	3	l2	long
    //   9	133	5	str	String
    //   212	22	5	localThrowable	Throwable
    //   119	42	6	localJSONArray	org.json.JSONArray
    // Exception table:
    //   from	to	target	type
    //   11	51	212	java/lang/Throwable
    //   173	184	212	java/lang/Throwable
    //   190	200	212	java/lang/Throwable
    //   200	209	212	java/lang/Throwable
    //   62	172	296	java/lang/Throwable
    //   222	270	296	java/lang/Throwable
    //   276	286	296	java/lang/Throwable
    //   286	295	296	java/lang/Throwable
  }
  
  public static boolean deleteBubbleData(String paramString1, String paramString2)
  {
    if (StringUtils.isNull(paramString1)) {}
    int i;
    do
    {
      return false;
      try
      {
        if (StringUtils.isNull(paramString2)) {
          i = DBManager.delete("tb_match_cache", " msg_id = ? ", new String[] { paramString1 });
        } else {
          i = DBManager.delete("tb_match_cache", " msg_id = ? and msg_num_md5 = ? ", new String[] { paramString1, paramString2 });
        }
      }
      catch (Throwable paramString1)
      {
        paramString1.printStackTrace();
        i = -1;
      }
    } while (i <= 0);
    return true;
  }
  
  public static void deleteDataByMsgIds(Set<Integer> paramSet)
  {
    if ((paramSet != null) && (!paramSet.isEmpty()))
    {
      localObject3 = DBManager.dblock;
      localObject2 = null;
      localObject1 = null;
    }
    for (;;)
    {
      try
      {
        localSQLiteDatabase = DBManager.getSQLiteDatabase();
        localObject1 = localSQLiteDatabase;
        localObject2 = localSQLiteDatabase;
        localStringBuffer = new StringBuffer();
        localObject1 = localSQLiteDatabase;
        localObject2 = localSQLiteDatabase;
        paramSet = paramSet.iterator();
        localObject1 = localSQLiteDatabase;
        localObject2 = localSQLiteDatabase;
        if (!paramSet.hasNext())
        {
          localObject1 = localSQLiteDatabase;
          localObject2 = localSQLiteDatabase;
          localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
          localObject1 = localSQLiteDatabase;
          localObject2 = localSQLiteDatabase;
          localSQLiteDatabase.execSQL("DELETE FROM tb_match_cache WHERE msg_id IN (" + localStringBuffer.toString() + ")");
        }
      }
      catch (Throwable paramSet)
      {
        SQLiteDatabase localSQLiteDatabase;
        StringBuffer localStringBuffer;
        Integer localInteger;
        localObject2 = localObject1;
        paramSet.printStackTrace();
        DBManager.close((SQLiteDatabase)localObject1);
        continue;
      }
      finally
      {
        DBManager.close((SQLiteDatabase)localObject2);
      }
      try
      {
        DBManager.close(localSQLiteDatabase);
        return;
      }
      finally {}
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      localInteger = (Integer)paramSet.next();
      localObject1 = localSQLiteDatabase;
      localObject2 = localSQLiteDatabase;
      localStringBuffer.append(localInteger + ",");
    }
  }
  
  public static int deleteDataByPhoneNum(String paramString)
  {
    if (!StringUtils.isNull(paramString)) {
      try
      {
        int i = DBManager.delete("tb_match_cache", " phonenum = ? ", new String[] { paramString });
        return i;
      }
      catch (Throwable paramString)
      {
        paramString.printStackTrace();
      }
    }
    return -1;
  }
  
  public static void deleteMatchCache(String paramString, long paramLong)
  {
    try
    {
      StringBuffer localStringBuffer = new StringBuffer(" bubble_lasttime < ?");
      if (paramString.length() == 8) {
        localStringBuffer.append(" and scene_id = ? ");
      }
      for (;;)
      {
        int i = DBManager.delete("tb_match_cache", localStringBuffer.toString(), new String[] { String.valueOf(paramLong), paramString });
        new StringBuilder("sceneId =").append(paramString).append(" endParseTime=").append(paramLong).append("result=").append(i);
        return;
        localStringBuffer.append(" and scene_id like '?%' ");
      }
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void deleteMatchCache(String paramString1, String paramString2, long paramLong)
  {
    try
    {
      paramString2 = new StringBuffer(paramString2 + "  < ?");
      int i;
      if (StringUtils.isNull(paramString1))
      {
        paramString2.append(" and (scene_id is null or length(scene_id) = 0) ");
        i = DBManager.delete("tb_match_cache", paramString2.toString(), new String[] { String.valueOf(paramLong) });
      }
      for (;;)
      {
        new StringBuilder("sceneId =").append(paramString1).append(" endParseTime=").append(paramLong).append("result=").append(i);
        return;
        if (paramString1.length() == 8)
        {
          paramString2.append(" and scene_id = ? ");
          i = DBManager.delete("tb_match_cache", paramString2.toString(), new String[] { String.valueOf(paramLong), paramString1 });
        }
        else
        {
          paramString2.append(" and scene_id like '" + paramString1 + "%' ");
          i = DBManager.delete("tb_match_cache", paramString2.toString(), new String[] { String.valueOf(paramLong) });
        }
      }
      return;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static String getCreateTableSql()
  {
    return "create table  if not exists tb_match_cache (  id INTEGER PRIMARY KEY, msg_num_md5 TEXT, phonenum TEXT, msg_id TEXT, scene_id TEXT, popup_window_result TEXT, bubble_result TEXT, session_reuslt TEXT, card_result TEXT, save_time INTEGER DEFAULT '0', bubble_lasttime integer default 0, session_lasttime integer default 0, card_lasttime integer default 0, EXTEND TEXT)";
  }
  
  /* Error */
  public static JSONObject getDataByParam(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: bipush 9
    //   4: anewarray 49	java/lang/String
    //   7: astore_2
    //   8: aload_2
    //   9: iconst_0
    //   10: ldc_w 315
    //   13: aastore
    //   14: aload_2
    //   15: iconst_1
    //   16: ldc_w 317
    //   19: aastore
    //   20: aload_2
    //   21: iconst_2
    //   22: ldc -127
    //   24: aastore
    //   25: aload_2
    //   26: iconst_3
    //   27: ldc -108
    //   29: aastore
    //   30: aload_2
    //   31: iconst_4
    //   32: ldc_w 319
    //   35: aastore
    //   36: aload_2
    //   37: iconst_5
    //   38: ldc -125
    //   40: aastore
    //   41: aload_2
    //   42: bipush 6
    //   44: ldc -106
    //   46: aastore
    //   47: aload_2
    //   48: bipush 7
    //   50: ldc_w 321
    //   53: aastore
    //   54: aload_2
    //   55: bipush 8
    //   57: ldc_w 323
    //   60: aastore
    //   61: ldc 20
    //   63: aload_2
    //   64: ldc_w 325
    //   67: iconst_1
    //   68: anewarray 49	java/lang/String
    //   71: dup
    //   72: iconst_0
    //   73: aload_0
    //   74: aastore
    //   75: aconst_null
    //   76: aconst_null
    //   77: aconst_null
    //   78: aconst_null
    //   79: invokestatic 329	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   82: astore_0
    //   83: aload_2
    //   84: aload_0
    //   85: invokestatic 335	cn/com/xy/sms/sdk/db/base/BaseManager:loadSingleDataFromCursor	([Ljava/lang/String;Lcn/com/xy/sms/sdk/db/XyCursor;)Lorg/json/JSONObject;
    //   88: astore_1
    //   89: aload_1
    //   90: ifnull +7 -> 97
    //   93: aload_1
    //   94: invokestatic 85	cn/com/xy/sms/sdk/db/entity/MatchCacheManager:a	(Lorg/json/JSONObject;)V
    //   97: aload_0
    //   98: iconst_1
    //   99: invokestatic 339	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   102: aload_1
    //   103: areturn
    //   104: astore_1
    //   105: aconst_null
    //   106: astore_0
    //   107: aload_1
    //   108: invokevirtual 192	java/lang/Throwable:printStackTrace	()V
    //   111: aload_0
    //   112: iconst_1
    //   113: invokestatic 339	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   116: aconst_null
    //   117: areturn
    //   118: astore_0
    //   119: aload_1
    //   120: iconst_1
    //   121: invokestatic 339	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   124: aload_0
    //   125: athrow
    //   126: astore_2
    //   127: aload_0
    //   128: astore_1
    //   129: aload_2
    //   130: astore_0
    //   131: goto -12 -> 119
    //   134: astore_2
    //   135: aload_0
    //   136: astore_1
    //   137: aload_2
    //   138: astore_0
    //   139: goto -20 -> 119
    //   142: astore_1
    //   143: goto -36 -> 107
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	146	0	paramString	String
    //   1	102	1	localJSONObject	JSONObject
    //   104	16	1	localThrowable1	Throwable
    //   128	9	1	str	String
    //   142	1	1	localThrowable2	Throwable
    //   7	77	2	arrayOfString	String[]
    //   126	4	2	localObject1	Object
    //   134	4	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   61	83	104	java/lang/Throwable
    //   61	83	118	finally
    //   83	89	126	finally
    //   93	97	126	finally
    //   107	111	134	finally
    //   83	89	142	java/lang/Throwable
    //   93	97	142	java/lang/Throwable
  }
  
  public static String getMD5(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = StringUtils.getPhoneNumberNo86(paramString1);
      if ((!StringUtils.isNull(paramString1)) && (!StringUtils.isNull(paramString2)))
      {
        paramString1 = StringUtils.getMD5(paramString1 + paramString2);
        return paramString1;
      }
    }
    catch (Throwable paramString1) {}
    return "";
  }
  
  /* Error */
  public static long insertOrUpdate(android.content.ContentValues paramContentValues, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 8
    //   3: aconst_null
    //   4: astore 9
    //   6: ldc2_w 44
    //   9: lstore 6
    //   11: aload_0
    //   12: ldc_w 315
    //   15: invokevirtual 353	android/content/ContentValues:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   18: checkcast 49	java/lang/String
    //   21: astore 10
    //   23: aload 10
    //   25: invokestatic 145	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   28: ifne +344 -> 372
    //   31: iconst_1
    //   32: anewarray 49	java/lang/String
    //   35: astore 11
    //   37: aload 11
    //   39: iconst_0
    //   40: aload 10
    //   42: aastore
    //   43: iconst_2
    //   44: anewarray 49	java/lang/String
    //   47: astore 12
    //   49: aload 12
    //   51: iconst_0
    //   52: ldc_w 317
    //   55: aastore
    //   56: aload 12
    //   58: iconst_1
    //   59: ldc_w 355
    //   62: aastore
    //   63: ldc 20
    //   65: aload 12
    //   67: ldc -56
    //   69: aload 11
    //   71: invokestatic 358	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   74: astore 10
    //   76: aload 10
    //   78: astore 8
    //   80: lload 6
    //   82: lstore 4
    //   84: aload 12
    //   86: aload 8
    //   88: invokestatic 335	cn/com/xy/sms/sdk/db/base/BaseManager:loadSingleDataFromCursor	([Ljava/lang/String;Lcn/com/xy/sms/sdk/db/XyCursor;)Lorg/json/JSONObject;
    //   91: astore 10
    //   93: lload 6
    //   95: lstore 4
    //   97: aload_0
    //   98: ldc_w 317
    //   101: invokevirtual 353	android/content/ContentValues:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   104: checkcast 49	java/lang/String
    //   107: astore 12
    //   109: lload 6
    //   111: lstore_2
    //   112: aload 10
    //   114: ifnull +42 -> 156
    //   117: lload 6
    //   119: lstore 4
    //   121: aload 10
    //   123: ldc_w 317
    //   126: invokestatic 91	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   129: checkcast 49	java/lang/String
    //   132: astore 9
    //   134: lload 6
    //   136: lstore 4
    //   138: aload 10
    //   140: ldc_w 355
    //   143: invokestatic 91	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   146: checkcast 49	java/lang/String
    //   149: invokestatic 138	java/lang/Long:valueOf	(Ljava/lang/String;)Ljava/lang/Long;
    //   152: invokevirtual 141	java/lang/Long:longValue	()J
    //   155: lstore_2
    //   156: aload 9
    //   158: ifnull +16 -> 174
    //   161: lload_2
    //   162: lstore 4
    //   164: aload 9
    //   166: aload 12
    //   168: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   171: ifne +69 -> 240
    //   174: aload 9
    //   176: ifnull +16 -> 192
    //   179: lload_2
    //   180: lstore 4
    //   182: ldc 20
    //   184: ldc -56
    //   186: aload 11
    //   188: invokestatic 206	cn/com/xy/sms/sdk/db/DBManager:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   191: pop
    //   192: lload_2
    //   193: lstore 4
    //   195: ldc 20
    //   197: aload_0
    //   198: invokestatic 362	cn/com/xy/sms/sdk/db/DBManager:insert	(Ljava/lang/String;Landroid/content/ContentValues;)J
    //   201: lstore_2
    //   202: new 99	java/lang/StringBuilder
    //   205: dup
    //   206: ldc_w 364
    //   209: invokespecial 104	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   212: aload 11
    //   214: iconst_0
    //   215: aaload
    //   216: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: ldc_w 366
    //   222: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: iload_1
    //   226: invokevirtual 294	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   229: pop
    //   230: aload 8
    //   232: astore_0
    //   233: aload_0
    //   234: iconst_1
    //   235: invokestatic 339	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   238: lload_2
    //   239: lreturn
    //   240: lload_2
    //   241: lstore 4
    //   243: ldc 20
    //   245: aload_0
    //   246: ldc -56
    //   248: aload 11
    //   250: invokestatic 370	cn/com/xy/sms/sdk/db/DBManager:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   253: pop
    //   254: lload_2
    //   255: lstore 4
    //   257: new 99	java/lang/StringBuilder
    //   260: dup
    //   261: ldc_w 372
    //   264: invokespecial 104	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   267: aload 11
    //   269: iconst_0
    //   270: aaload
    //   271: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   274: ldc_w 366
    //   277: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: iload_1
    //   281: invokevirtual 294	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   284: pop
    //   285: aload 8
    //   287: astore_0
    //   288: goto -55 -> 233
    //   291: astore 9
    //   293: ldc2_w 44
    //   296: lstore_2
    //   297: aload 8
    //   299: astore_0
    //   300: aload 9
    //   302: astore 8
    //   304: aload 8
    //   306: invokevirtual 192	java/lang/Throwable:printStackTrace	()V
    //   309: aload_0
    //   310: iconst_1
    //   311: invokestatic 339	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   314: lload_2
    //   315: lreturn
    //   316: astore_0
    //   317: aconst_null
    //   318: astore 8
    //   320: aload 8
    //   322: iconst_1
    //   323: invokestatic 339	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   326: aload_0
    //   327: athrow
    //   328: astore_0
    //   329: goto -9 -> 320
    //   332: astore 9
    //   334: aload_0
    //   335: astore 8
    //   337: aload 9
    //   339: astore_0
    //   340: goto -20 -> 320
    //   343: astore 9
    //   345: aload 8
    //   347: astore_0
    //   348: lload 4
    //   350: lstore_2
    //   351: aload 9
    //   353: astore 8
    //   355: goto -51 -> 304
    //   358: astore_0
    //   359: aload 8
    //   361: astore 9
    //   363: aload_0
    //   364: astore 8
    //   366: aload 9
    //   368: astore_0
    //   369: goto -65 -> 304
    //   372: aconst_null
    //   373: astore_0
    //   374: ldc2_w 44
    //   377: lstore_2
    //   378: goto -145 -> 233
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	381	0	paramContentValues	android.content.ContentValues
    //   0	381	1	paramInt	int
    //   111	267	2	l1	long
    //   82	267	4	l2	long
    //   9	126	6	l3	long
    //   1	364	8	localObject1	Object
    //   4	171	9	str	String
    //   291	10	9	localThrowable1	Throwable
    //   332	6	9	localObject2	Object
    //   343	9	9	localThrowable2	Throwable
    //   361	6	9	localObject3	Object
    //   21	118	10	localObject4	Object
    //   35	233	11	arrayOfString	String[]
    //   47	120	12	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   11	37	291	java/lang/Throwable
    //   43	49	291	java/lang/Throwable
    //   63	76	291	java/lang/Throwable
    //   11	37	316	finally
    //   43	49	316	finally
    //   63	76	316	finally
    //   84	93	328	finally
    //   97	109	328	finally
    //   121	134	328	finally
    //   138	156	328	finally
    //   164	174	328	finally
    //   182	192	328	finally
    //   195	202	328	finally
    //   202	230	328	finally
    //   243	254	328	finally
    //   257	285	328	finally
    //   304	309	332	finally
    //   84	93	343	java/lang/Throwable
    //   97	109	343	java/lang/Throwable
    //   121	134	343	java/lang/Throwable
    //   138	156	343	java/lang/Throwable
    //   164	174	343	java/lang/Throwable
    //   182	192	343	java/lang/Throwable
    //   195	202	343	java/lang/Throwable
    //   243	254	343	java/lang/Throwable
    //   257	285	343	java/lang/Throwable
    //   202	230	358	java/lang/Throwable
  }
  
  /* Error */
  public static Map<String, JSONObject> loadDataByParam(String paramString, String[] paramArrayOfString)
  {
    // Byte code:
    //   0: bipush 9
    //   2: anewarray 49	java/lang/String
    //   5: astore_2
    //   6: aload_2
    //   7: iconst_0
    //   8: ldc_w 315
    //   11: aastore
    //   12: aload_2
    //   13: iconst_1
    //   14: ldc_w 317
    //   17: aastore
    //   18: aload_2
    //   19: iconst_2
    //   20: ldc -127
    //   22: aastore
    //   23: aload_2
    //   24: iconst_3
    //   25: ldc -108
    //   27: aastore
    //   28: aload_2
    //   29: iconst_4
    //   30: ldc_w 319
    //   33: aastore
    //   34: aload_2
    //   35: iconst_5
    //   36: ldc -125
    //   38: aastore
    //   39: aload_2
    //   40: bipush 6
    //   42: ldc -106
    //   44: aastore
    //   45: aload_2
    //   46: bipush 7
    //   48: ldc_w 321
    //   51: aastore
    //   52: aload_2
    //   53: bipush 8
    //   55: ldc_w 323
    //   58: aastore
    //   59: ldc 20
    //   61: aload_2
    //   62: aload_0
    //   63: aload_1
    //   64: invokestatic 358	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   67: astore_1
    //   68: aload_1
    //   69: astore_0
    //   70: aload_2
    //   71: iconst_0
    //   72: aload_1
    //   73: invokestatic 376	cn/com/xy/sms/sdk/db/entity/MatchCacheManager:a	([Ljava/lang/String;ILcn/com/xy/sms/sdk/db/XyCursor;)Ljava/util/Map;
    //   76: astore_2
    //   77: aload_1
    //   78: iconst_1
    //   79: invokestatic 339	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   82: aload_2
    //   83: areturn
    //   84: astore_2
    //   85: aconst_null
    //   86: astore_1
    //   87: aload_1
    //   88: astore_0
    //   89: aload_2
    //   90: invokevirtual 192	java/lang/Throwable:printStackTrace	()V
    //   93: aload_1
    //   94: iconst_1
    //   95: invokestatic 339	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   98: aconst_null
    //   99: areturn
    //   100: astore_0
    //   101: aconst_null
    //   102: astore_2
    //   103: aload_0
    //   104: astore_1
    //   105: aload_2
    //   106: iconst_1
    //   107: invokestatic 339	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   110: aload_1
    //   111: athrow
    //   112: astore_1
    //   113: aload_0
    //   114: astore_2
    //   115: goto -10 -> 105
    //   118: astore_2
    //   119: goto -32 -> 87
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	122	0	paramString	String
    //   0	122	1	paramArrayOfString	String[]
    //   5	78	2	localObject1	Object
    //   84	6	2	localThrowable1	Throwable
    //   102	13	2	localObject2	Object
    //   118	1	2	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   59	68	84	java/lang/Throwable
    //   59	68	100	finally
    //   70	77	112	finally
    //   89	93	112	finally
    //   70	77	118	java/lang/Throwable
  }
  
  /* Error */
  public static Map<String, JSONObject> loadDataByParam(String paramString1, String[] paramArrayOfString, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: bipush 9
    //   5: anewarray 49	java/lang/String
    //   8: astore 5
    //   10: aload 5
    //   12: iconst_0
    //   13: ldc_w 315
    //   16: aastore
    //   17: aload 5
    //   19: iconst_1
    //   20: ldc_w 317
    //   23: aastore
    //   24: aload 5
    //   26: iconst_2
    //   27: ldc -127
    //   29: aastore
    //   30: aload 5
    //   32: iconst_3
    //   33: ldc -108
    //   35: aastore
    //   36: aload 5
    //   38: iconst_4
    //   39: ldc_w 319
    //   42: aastore
    //   43: aload 5
    //   45: iconst_5
    //   46: ldc -125
    //   48: aastore
    //   49: aload 5
    //   51: bipush 6
    //   53: ldc -106
    //   55: aastore
    //   56: aload 5
    //   58: bipush 7
    //   60: ldc_w 321
    //   63: aastore
    //   64: aload 5
    //   66: bipush 8
    //   68: ldc_w 323
    //   71: aastore
    //   72: ldc 20
    //   74: aload 5
    //   76: aload_0
    //   77: aload_1
    //   78: aconst_null
    //   79: aconst_null
    //   80: aload_2
    //   81: aload_3
    //   82: invokestatic 329	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   85: astore_0
    //   86: aload 5
    //   88: iconst_0
    //   89: aload_0
    //   90: invokestatic 376	cn/com/xy/sms/sdk/db/entity/MatchCacheManager:a	([Ljava/lang/String;ILcn/com/xy/sms/sdk/db/XyCursor;)Ljava/util/Map;
    //   93: astore_1
    //   94: aload_0
    //   95: iconst_1
    //   96: invokestatic 339	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   99: aload_1
    //   100: areturn
    //   101: astore_1
    //   102: aconst_null
    //   103: astore_0
    //   104: aload_1
    //   105: invokevirtual 192	java/lang/Throwable:printStackTrace	()V
    //   108: aload_0
    //   109: iconst_1
    //   110: invokestatic 339	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   113: aconst_null
    //   114: areturn
    //   115: astore_0
    //   116: aload 4
    //   118: astore_1
    //   119: aload_1
    //   120: iconst_1
    //   121: invokestatic 339	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   124: aload_0
    //   125: athrow
    //   126: astore_2
    //   127: aload_0
    //   128: astore_1
    //   129: aload_2
    //   130: astore_0
    //   131: goto -12 -> 119
    //   134: astore_2
    //   135: aload_0
    //   136: astore_1
    //   137: aload_2
    //   138: astore_0
    //   139: goto -20 -> 119
    //   142: astore_1
    //   143: goto -39 -> 104
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	146	0	paramString1	String
    //   0	146	1	paramArrayOfString	String[]
    //   0	146	2	paramString2	String
    //   0	146	3	paramString3	String
    //   1	116	4	localObject	Object
    //   8	79	5	arrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   72	86	101	java/lang/Throwable
    //   72	86	115	finally
    //   86	94	126	finally
    //   104	108	134	finally
    //   86	94	142	java/lang/Throwable
  }
  
  public static int queryDataCount(String paramString1, String paramString2)
  {
    Object localObject = null;
    String str = null;
    try
    {
      paramString1 = DBManager.query("tb_match_cache", new String[] { "id" }, "msg_num_md5 = ? AND msg_id = ?", new String[] { paramString1, paramString2 });
      if (paramString1 != null)
      {
        str = paramString1;
        localObject = paramString1;
        if (paramString1.moveToNext())
        {
          str = paramString1;
          localObject = paramString1;
          int i = paramString1.getCount();
          XyCursor.closeCursor(paramString1, true);
          return i;
        }
      }
    }
    catch (Throwable paramString1)
    {
      localObject = str;
      paramString1.printStackTrace();
      XyCursor.closeCursor(str, true);
      return 0;
    }
    finally
    {
      XyCursor.closeCursor((XyCursor)localObject, true);
    }
    XyCursor.closeCursor(paramString1, true);
    return 0;
  }
  
  public static void removeUselessKey(JSONObject paramJSONObject)
  {
    int i = 0;
    if (paramJSONObject == null) {
      return;
    }
    while (i < 10) {
      try
      {
        String str = new String[] { "version", "mactchs_id", "layoutName", "ALLOW_VERCODE_MSG", "smsCenterNum", "channel", "RECOGNIZE_LEVEL", "is_return", "viewPartParam", "simIndex" }[i];
        if (paramJSONObject.has(str)) {
          paramJSONObject.remove(str);
        }
        i += 1;
      }
      catch (Throwable paramJSONObject)
      {
        paramJSONObject.printStackTrace();
      }
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.MatchCacheManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */