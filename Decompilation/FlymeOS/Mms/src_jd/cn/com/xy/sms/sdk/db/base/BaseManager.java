package cn.com.xy.sms.sdk.db.base;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class BaseManager
{
  private static String a(String paramString, XyCursor paramXyCursor)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramXyCursor != null) {
      localObject1 = localObject2;
    }
    try
    {
      if (paramXyCursor.moveToNext()) {
        localObject1 = paramXyCursor.getString(paramXyCursor.getColumnIndex(paramString));
      }
      return (String)localObject1;
    }
    catch (Throwable paramString)
    {
      new StringBuilder("loadSingleFeildDataFromCursor Throwable=").append(paramString.getLocalizedMessage());
    }
    return null;
  }
  
  public static ContentValues getContentValues(ContentValues paramContentValues, boolean paramBoolean, JSONObject paramJSONObject, String... paramVarArgs)
  {
    if ((paramJSONObject == null) || (paramVarArgs.length == 0))
    {
      paramContentValues = null;
      return paramContentValues;
    }
    ContentValues localContentValues = paramContentValues;
    if (paramContentValues == null) {
      localContentValues = new ContentValues();
    }
    int j = paramVarArgs.length;
    int i = 0;
    for (;;)
    {
      paramContentValues = localContentValues;
      if (i >= j) {
        break;
      }
      paramContentValues = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, paramVarArgs[i]);
      if ((!paramBoolean) || (!StringUtils.isNull(paramContentValues))) {
        localContentValues.put(paramVarArgs[i], JsonUtil.getValueFromJsonObject(paramJSONObject, paramVarArgs[i]).toString());
      }
      i += 1;
    }
  }
  
  public static ContentValues getContentValues(ContentValues paramContentValues, boolean paramBoolean, String... paramVarArgs)
  {
    if ((paramVarArgs == null) || (paramVarArgs.length % 2 != 0))
    {
      paramContentValues = null;
      return paramContentValues;
    }
    ContentValues localContentValues = paramContentValues;
    if (paramContentValues == null) {
      localContentValues = new ContentValues();
    }
    int j = paramVarArgs.length;
    int i = 0;
    for (;;)
    {
      paramContentValues = localContentValues;
      if (i >= j) {
        break;
      }
      if ((!paramBoolean) || ((!StringUtils.isNull(paramVarArgs[i])) && (!StringUtils.isNull(paramVarArgs[(i + 1)])))) {
        localContentValues.put(paramVarArgs[i], StringUtils.getNoNullString(paramVarArgs[(i + 1)]));
      }
      i += 2;
    }
  }
  
  public static ContentValues getContentValues(ContentValues paramContentValues, String... paramVarArgs)
  {
    return getContentValues(paramContentValues, false, paramVarArgs);
  }
  
  public static ContentValues getContentValues(ContentValues paramContentValues, String[] paramArrayOfString1, String[] paramArrayOfString2, JSONObject paramJSONObject, boolean paramBoolean)
  {
    if ((paramJSONObject == null) || (paramArrayOfString1 == null) || (paramArrayOfString2 == null)) {
      paramContentValues = null;
    }
    int j;
    int i;
    ContentValues localContentValues;
    do
    {
      return paramContentValues;
      j = paramArrayOfString1.length;
      i = paramArrayOfString2.length;
      if ((j == 0) || (j != i)) {
        return null;
      }
      localContentValues = paramContentValues;
      if (paramContentValues == null) {
        localContentValues = new ContentValues();
      }
      i = 0;
      paramContentValues = localContentValues;
    } while (i >= j);
    if (paramBoolean) {
      JsonUtil.putJsonToConV(localContentValues, paramJSONObject, paramArrayOfString1[i], paramArrayOfString2[i]);
    }
    for (;;)
    {
      i += 1;
      break;
      localContentValues.put(paramArrayOfString1[i], JsonUtil.getValueFromJsonObject(paramJSONObject, paramArrayOfString2[i]).toString());
    }
  }
  
  public static boolean hasRecord(String paramString1, String paramString2, String paramString3, String... paramVarArgs)
  {
    Object localObject = null;
    String str = null;
    for (;;)
    {
      try
      {
        paramString1 = DBManager.query(paramString1, new String[] { paramString2 }, paramString3, paramVarArgs);
        if (paramString1 != null)
        {
          str = paramString1;
          localObject = paramString1;
          int i = paramString1.getCount();
          if (i > 0)
          {
            XyCursor.closeCursor(paramString1, true);
            return true;
          }
        }
      }
      catch (Throwable paramString1)
      {
        localObject = str;
        paramString1.printStackTrace();
        XyCursor.closeCursor(str, true);
        return false;
      }
      finally
      {
        XyCursor.closeCursor((XyCursor)localObject, true);
      }
      XyCursor.closeCursor(paramString1, true);
    }
  }
  
  /* Error */
  public static long insertOrUpdate(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString1, String... paramVarArgs)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 8
    //   3: iconst_1
    //   4: anewarray 55	java/lang/String
    //   7: astore 9
    //   9: aload 9
    //   11: iconst_0
    //   12: aload_1
    //   13: aastore
    //   14: aload_0
    //   15: aload 9
    //   17: aload_2
    //   18: aload_3
    //   19: invokestatic 89	cn/com/xy/sms/sdk/db/DBManager:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   22: astore_2
    //   23: aload_2
    //   24: ifnull +41 -> 65
    //   27: aload_2
    //   28: invokevirtual 93	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   31: ifle +34 -> 65
    //   34: aload 9
    //   36: aload_2
    //   37: invokestatic 106	cn/com/xy/sms/sdk/db/base/BaseManager:loadSingleDataFromCursor	([Ljava/lang/String;Lcn/com/xy/sms/sdk/db/XyCursor;)Lorg/json/JSONObject;
    //   40: aload_1
    //   41: invokestatic 53	cn/com/xy/sms/sdk/util/JsonUtil:getValueFromJsonObject	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;
    //   44: checkcast 108	java/lang/Integer
    //   47: invokevirtual 111	java/lang/Integer:intValue	()I
    //   50: istore 5
    //   52: iload 5
    //   54: i2l
    //   55: lstore 6
    //   57: aload_2
    //   58: iconst_1
    //   59: invokestatic 97	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   62: lload 6
    //   64: lreturn
    //   65: aload_0
    //   66: aconst_null
    //   67: aload 4
    //   69: invokestatic 113	cn/com/xy/sms/sdk/db/base/BaseManager:getContentValues	(Landroid/content/ContentValues;[Ljava/lang/String;)Landroid/content/ContentValues;
    //   72: invokestatic 117	cn/com/xy/sms/sdk/db/DBManager:insert	(Ljava/lang/String;Landroid/content/ContentValues;)J
    //   75: lstore 6
    //   77: goto -20 -> 57
    //   80: astore_1
    //   81: aload 8
    //   83: astore_0
    //   84: aload_1
    //   85: invokevirtual 100	java/lang/Throwable:printStackTrace	()V
    //   88: aload_0
    //   89: iconst_1
    //   90: invokestatic 97	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   93: ldc2_w 118
    //   96: lreturn
    //   97: astore_0
    //   98: aconst_null
    //   99: astore_2
    //   100: aload_2
    //   101: iconst_1
    //   102: invokestatic 97	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   105: aload_0
    //   106: athrow
    //   107: astore_0
    //   108: goto -8 -> 100
    //   111: astore_1
    //   112: aload_0
    //   113: astore_2
    //   114: aload_1
    //   115: astore_0
    //   116: goto -16 -> 100
    //   119: astore_1
    //   120: aload_2
    //   121: astore_0
    //   122: goto -38 -> 84
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	125	0	paramString1	String
    //   0	125	1	paramString2	String
    //   0	125	2	paramString3	String
    //   0	125	3	paramArrayOfString1	String[]
    //   0	125	4	paramVarArgs	String[]
    //   50	3	5	i	int
    //   55	21	6	l	long
    //   1	81	8	localObject	Object
    //   7	28	9	arrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   3	9	80	java/lang/Throwable
    //   14	23	80	java/lang/Throwable
    //   3	9	97	finally
    //   14	23	97	finally
    //   27	52	107	finally
    //   65	77	107	finally
    //   84	88	111	finally
    //   27	52	119	java/lang/Throwable
    //   65	77	119	java/lang/Throwable
  }
  
  public static JSONArray loadArrDataFromCursor(String[] paramArrayOfString, XyCursor paramXyCursor)
  {
    if (paramXyCursor != null) {}
    for (;;)
    {
      JSONObject localJSONObject;
      int i;
      try
      {
        JSONArray localJSONArray = new JSONArray();
        if (!paramXyCursor.moveToNext()) {
          return localJSONArray;
        }
        localJSONObject = new JSONObject();
        i = 0;
        if (i >= paramArrayOfString.length)
        {
          localJSONArray.put(localJSONObject);
          continue;
          return null;
        }
      }
      catch (Throwable paramArrayOfString)
      {
        new StringBuilder("loadArrDataFromCursor Throwable=").append(paramArrayOfString.getLocalizedMessage());
      }
      localJSONObject.put(paramArrayOfString[i], StringUtils.getNoNullString(paramXyCursor.getString(i)));
      i += 1;
    }
  }
  
  public static Map<String, JSONObject> loadMapDataFromCursor(String[] paramArrayOfString, int paramInt, XyCursor paramXyCursor)
  {
    if (paramXyCursor != null) {}
    for (;;)
    {
      JSONObject localJSONObject;
      int i;
      try
      {
        HashMap localHashMap = new HashMap();
        if (!paramXyCursor.moveToNext()) {
          return localHashMap;
        }
        localJSONObject = new JSONObject();
        i = 0;
        if (i >= paramArrayOfString.length)
        {
          localHashMap.put((String)JsonUtil.getValueFromJsonObject(localJSONObject, paramArrayOfString[paramInt]), localJSONObject);
          continue;
          return null;
        }
      }
      catch (Throwable paramArrayOfString)
      {
        new StringBuilder("loadMapDataFromCursor Throwable=").append(paramArrayOfString.getLocalizedMessage());
      }
      localJSONObject.put(paramArrayOfString[i], StringUtils.getNoNullString(paramXyCursor.getString(i)));
      i += 1;
    }
  }
  
  public static JSONObject loadSingleDataFromCursor(String[] paramArrayOfString, XyCursor paramXyCursor)
  {
    if (paramXyCursor != null) {
      try
      {
        if (paramXyCursor.moveToNext())
        {
          JSONObject localJSONObject = new JSONObject();
          int i = 0;
          for (;;)
          {
            if (i >= paramArrayOfString.length) {
              return localJSONObject;
            }
            localJSONObject.put(paramArrayOfString[i], StringUtils.getNoNullString(paramXyCursor.getString(i)));
            i += 1;
          }
          return null;
        }
      }
      catch (Throwable paramArrayOfString)
      {
        new StringBuilder("loadSingleDataFromCursor Throwable=").append(paramArrayOfString.getLocalizedMessage());
      }
    }
    return null;
  }
  
  /* Error */
  public static long saveOrUpdate(android.database.sqlite.SQLiteDatabase paramSQLiteDatabase, String paramString1, ContentValues paramContentValues, String paramString2, String[] paramArrayOfString)
  {
    // Byte code:
    //   0: lconst_0
    //   1: lstore 6
    //   3: getstatic 157	cn/com/xy/sms/sdk/db/DBManager:dblock	Ljava/lang/Object;
    //   6: astore 8
    //   8: aload 8
    //   10: monitorenter
    //   11: aload_0
    //   12: aload_1
    //   13: aload_2
    //   14: aload_3
    //   15: aload 4
    //   17: invokevirtual 163	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   20: istore 5
    //   22: iload 5
    //   24: i2l
    //   25: lconst_0
    //   26: lcmp
    //   27: ifne +12 -> 39
    //   30: aload_0
    //   31: aload_1
    //   32: aconst_null
    //   33: aload_2
    //   34: invokevirtual 166	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   37: lstore 6
    //   39: aload 8
    //   41: monitorexit
    //   42: lload 6
    //   44: lreturn
    //   45: astore_0
    //   46: new 29	java/lang/StringBuilder
    //   49: dup
    //   50: aload_1
    //   51: invokestatic 170	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   54: invokespecial 34	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   57: ldc -84
    //   59: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: aload_0
    //   63: invokevirtual 38	java/lang/Throwable:getLocalizedMessage	()Ljava/lang/String;
    //   66: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: pop
    //   70: new 174	java/lang/Exception
    //   73: dup
    //   74: invokespecial 175	java/lang/Exception:<init>	()V
    //   77: athrow
    //   78: astore_0
    //   79: aload 8
    //   81: monitorexit
    //   82: aload_0
    //   83: athrow
    //   84: astore_0
    //   85: new 29	java/lang/StringBuilder
    //   88: dup
    //   89: aload_1
    //   90: invokestatic 170	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   93: invokespecial 34	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   96: ldc -84
    //   98: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: aload_0
    //   102: invokevirtual 38	java/lang/Throwable:getLocalizedMessage	()Ljava/lang/String;
    //   105: invokevirtual 42	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: pop
    //   109: new 174	java/lang/Exception
    //   112: dup
    //   113: invokespecial 175	java/lang/Exception:<init>	()V
    //   116: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	117	0	paramSQLiteDatabase	android.database.sqlite.SQLiteDatabase
    //   0	117	1	paramString1	String
    //   0	117	2	paramContentValues	ContentValues
    //   0	117	3	paramString2	String
    //   0	117	4	paramArrayOfString	String[]
    //   20	3	5	i	int
    //   1	42	6	l	long
    //   6	74	8	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   11	22	45	java/lang/Throwable
    //   11	22	78	finally
    //   30	39	78	finally
    //   39	42	78	finally
    //   46	78	78	finally
    //   85	117	78	finally
    //   30	39	84	java/lang/Throwable
  }
  
  public static int update(String paramString1, String paramString2, String[] paramArrayOfString1, String... paramVarArgs)
  {
    try
    {
      int i = DBManager.update(paramString1, getContentValues(null, paramVarArgs), paramString2, paramArrayOfString1);
      return i;
    }
    catch (Throwable paramString1)
    {
      paramString1.printStackTrace();
    }
    return 0;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.base.BaseManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */