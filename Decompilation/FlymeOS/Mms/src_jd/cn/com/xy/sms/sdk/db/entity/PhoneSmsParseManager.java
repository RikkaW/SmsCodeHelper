package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class PhoneSmsParseManager
{
  public static final String DROP_TABLE = " DROP TABLE IF EXISTS tb_phone_bubble_cache";
  public static final String TABLE_NAME = "tb_phone_bubble_cache";
  
  public static void addInsertQueue(String paramString1, long paramLong, String paramString2, String paramString3, String paramString4)
  {
    saveOrUpdateObject(paramString1, paramLong, paramString2, paramString3, paramString4);
  }
  
  /* Error */
  public static JSONObject findObjectByPhone(String paramString)
  {
    // Byte code:
    //   0: bipush 7
    //   2: anewarray 28	java/lang/String
    //   5: astore_2
    //   6: aload_2
    //   7: iconst_0
    //   8: ldc 30
    //   10: aastore
    //   11: aload_2
    //   12: iconst_1
    //   13: ldc 32
    //   15: aastore
    //   16: aload_2
    //   17: iconst_2
    //   18: ldc 34
    //   20: aastore
    //   21: aload_2
    //   22: iconst_3
    //   23: ldc 36
    //   25: aastore
    //   26: aload_2
    //   27: iconst_4
    //   28: ldc 38
    //   30: aastore
    //   31: aload_2
    //   32: iconst_5
    //   33: ldc 40
    //   35: aastore
    //   36: aload_2
    //   37: bipush 6
    //   39: ldc 42
    //   41: aastore
    //   42: ldc 11
    //   44: aload_2
    //   45: ldc 44
    //   47: iconst_1
    //   48: anewarray 28	java/lang/String
    //   51: dup
    //   52: iconst_0
    //   53: aload_0
    //   54: aastore
    //   55: invokestatic 50	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   58: astore_1
    //   59: aload_1
    //   60: astore_0
    //   61: aload_2
    //   62: aload_1
    //   63: invokestatic 56	cn/com/xy/sms/sdk/db/base/BaseManager:loadSingleDataFromCursor	([Ljava/lang/String;Lcn/com/xy/sms/sdk/db/XyCursor;)Lorg/json/JSONObject;
    //   66: astore_2
    //   67: aload_1
    //   68: iconst_1
    //   69: invokestatic 62	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   72: aload_2
    //   73: areturn
    //   74: astore_2
    //   75: aconst_null
    //   76: astore_1
    //   77: aload_1
    //   78: astore_0
    //   79: aload_2
    //   80: invokevirtual 65	java/lang/Throwable:printStackTrace	()V
    //   83: aload_1
    //   84: iconst_1
    //   85: invokestatic 62	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   88: aconst_null
    //   89: areturn
    //   90: astore_0
    //   91: aconst_null
    //   92: astore_2
    //   93: aload_0
    //   94: astore_1
    //   95: aload_2
    //   96: iconst_1
    //   97: invokestatic 62	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   100: aload_1
    //   101: athrow
    //   102: astore_1
    //   103: aload_0
    //   104: astore_2
    //   105: goto -10 -> 95
    //   108: astore_2
    //   109: goto -32 -> 77
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	112	0	paramString	String
    //   58	43	1	localObject1	Object
    //   102	1	1	localObject2	Object
    //   5	68	2	localObject3	Object
    //   74	6	2	localThrowable1	Throwable
    //   92	13	2	localObject4	Object
    //   108	1	2	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   0	6	74	java/lang/Throwable
    //   42	59	74	java/lang/Throwable
    //   0	6	90	finally
    //   42	59	90	finally
    //   61	67	102	finally
    //   79	83	102	finally
    //   61	67	108	java/lang/Throwable
  }
  
  public static String getCreateTableSql()
  {
    return "create table  if not exists tb_phone_bubble_cache (  id INTEGER PRIMARY KEY, phone TEXT UNIQUE, minReceiveTime LONG default 0, maxReceiveTime LONG default 0, useBubbleViews TEXT, useBubbleLogoName TEXT, extend TEXT)";
  }
  
  public static void reSetAllDataTime(String paramString1, long paramLong, String paramString2, String paramString3, String paramString4)
  {
    paramString1 = new ContentValues();
    paramString1.put("minReceiveTime", Long.valueOf(System.currentTimeMillis()));
    paramString1.put("maxReceiveTime", Integer.valueOf(0));
    try
    {
      DBManager.update("tb_phone_bubble_cache", paramString1, null, null);
      return;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static long saveOrUpdateObject(String paramString1, long paramLong, String paramString2, String paramString3, String paramString4)
  {
    int i = 0;
    label355:
    label434:
    label446:
    for (;;)
    {
      Object localObject2;
      try
      {
        Object localObject1 = findObjectByPhone(paramString1);
        if (localObject1 == null)
        {
          localObject2 = new JSONArray();
          if (!StringUtils.isNull(paramString2)) {
            ((JSONArray)localObject2).put(0, paramString2);
          }
          if (paramLong > 0L)
          {
            paramString2 = String.valueOf(paramLong);
            localObject1 = paramString2;
            paramString1 = BaseManager.getContentValues(null, true, new String[] { "phone", paramString1, "minReceiveTime", localObject1, "maxReceiveTime", paramString2, "useBubbleViews", ((JSONArray)localObject2).toString(), "useBubbleLogoName", paramString3, "extend", paramString4 });
            if (paramString1 == null) {
              break label434;
            }
            return DBManager.insert("tb_phone_bubble_cache", paramString1);
          }
          paramString2 = String.valueOf(System.currentTimeMillis() + 2147483647L);
          localObject1 = "0";
          continue;
        }
        localObject2 = new ContentValues();
        if (paramLong > 0L)
        {
          if (paramLong < Long.valueOf(((JSONObject)localObject1).getString("maxReceiveTime")).longValue()) {
            ((ContentValues)localObject2).put("maxReceiveTime", Long.valueOf(paramLong));
          }
        }
        else
        {
          if (!StringUtils.isNull(paramString3)) {
            ((ContentValues)localObject2).put("useBubbleLogoName", paramString3);
          }
          if (!StringUtils.isNull(paramString4)) {
            ((ContentValues)localObject2).put("extend", paramString4);
          }
          if (StringUtils.isNull(paramString2)) {
            break label434;
          }
          paramString3 = ((JSONObject)localObject1).getString("useBubbleViews");
          if (!StringUtils.isNull(paramString3)) {
            break label355;
          }
          new JSONArray().put(0, paramString2);
          if (((ContentValues)localObject2).size() > 0) {
            DBManager.update("tb_phone_bubble_cache", (ContentValues)localObject2, "phone=?", new String[] { paramString1 });
          }
          return Long.valueOf(((JSONObject)localObject1).getString("id")).longValue();
        }
        if (paramLong <= Long.valueOf(((JSONObject)localObject1).getString("minReceiveTime")).longValue()) {
          continue;
        }
        ((ContentValues)localObject2).put("minReceiveTime", Long.valueOf(paramLong));
        continue;
        paramString3 = new JSONArray(paramString3);
      }
      catch (Throwable paramString1)
      {
        paramString1.printStackTrace();
        return -1L;
      }
      int j = paramString3.length();
      for (;;)
      {
        if (i != j) {
          break label446;
        }
        paramString3.put(j, paramString2);
        ((ContentValues)localObject2).put("useBubbleViews", paramString3.toString());
        break;
        do
        {
          boolean bool = paramString2.equals(paramString3.getString(i));
          if (bool) {
            break;
          }
          i += 1;
          continue;
          return -1L;
        } while (i < j);
      }
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.PhoneSmsParseManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */