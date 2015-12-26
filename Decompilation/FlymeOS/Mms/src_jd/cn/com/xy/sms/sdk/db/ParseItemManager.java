package cn.com.xy.sms.sdk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.a.a;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParseItemManager
{
  public static final String CREATE_INDEX = "create index if not exists indx_s_m on tb_regex (scene_id,match_id)";
  public static final String CREATE_TABLE = "create table  if not exists tb_regex (scene_id TEXT,match_id TEXT,regex_text TEXT,version_code TEXT,regex_type INTEGER  DEFAULT '0',state INTEGER  DEFAULT '0' )";
  public static final String DROP_TABLE = " DROP TABLE IF EXISTS tb_regex";
  public static final int INITTAG = -2;
  public static final String MATCH_ID = "match_id";
  public static final int NEEDDEL = -1;
  public static final int NORMAL = 0;
  public static final String REGEX_TEXT = "regex_text";
  public static final String REGEX_TYPE = "regex_type";
  public static final int REGEX_TYPE_AD = 2;
  public static final int REGEX_TYPE_SCENE = 1;
  public static final String SCENE_ID = "scene_id";
  public static final String STATE = "state";
  public static final String TABLE_NAME = "tb_regex";
  public static final String VERSION_CODE = "version_code";
  private static boolean a = false;
  private static boolean b = false;
  private static HashMap<Long, SQLiteDatabase> c = new HashMap();
  private static boolean d = false;
  public static boolean execNqSql = false;
  
  private static void a()
  {
    try
    {
      long l = Thread.currentThread().getId();
      a.a((SQLiteDatabase)c.remove(Long.valueOf(l)));
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private static void a(int paramInt)
  {
    try
    {
      a.a("tb_regex", "state=? ", new String[] { "-1" });
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  private static void a(String paramString, SQLiteDatabase paramSQLiteDatabase)
  {
    int j = 0;
    String str1;
    String[] arrayOfString;
    int i;
    if ((!StringUtils.isNull(paramString)) && (paramString.indexOf("values") != -1))
    {
      str1 = paramString.substring(0, paramString.indexOf("values"));
      paramString = paramString.replace(str1, "");
      str1 = str1 + " values ";
      arrayOfString = paramString.replace("values", "").split("'\\),\\('");
      paramString = new ArrayList();
      if ((arrayOfString != null) && (arrayOfString.length > 0))
      {
        i = 0;
        if (i < arrayOfString.length) {
          break label128;
        }
      }
      if (!paramString.isEmpty()) {
        i = j;
      }
    }
    for (;;)
    {
      if (i >= paramString.size())
      {
        return;
        label128:
        String str2;
        if (!StringUtils.isNull(arrayOfString[i]))
        {
          str2 = arrayOfString[i].trim();
          if (!str2.startsWith("(")) {
            break label201;
          }
          paramString.add(str1 + " " + str2 + "')");
        }
        for (;;)
        {
          i += 1;
          break;
          label201:
          if (str2.endsWith(";")) {
            paramString.add(str1 + " ('" + str2);
          } else if (str2.endsWith(")")) {
            paramString.add(str1 + " ('" + str2 + "')");
          } else {
            paramString.add(str1 + " ('" + str2 + "')");
          }
        }
      }
      str1 = (String)paramString.get(i);
      if (!StringUtils.isNull(str1)) {}
      try
      {
        paramSQLiteDatabase.execSQL(str1);
        i += 1;
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          localThrowable.printStackTrace();
        }
      }
    }
  }
  
  public static void checkHasData()
  {
    LogManager.e("xiaoyuan_test", "checkHasData isCheckHasData: " + b, null);
    if (b) {
      return;
    }
    b = true;
    new e().start();
  }
  
  public static void closeSpecialDatebase() {}
  
  public static void deleteAll()
  {
    try
    {
      a.a("tb_regex", null, null);
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public static void deleteRepeatData()
  {
    Object localObject3 = null;
    Object localObject1 = null;
    try
    {
      SQLiteDatabase localSQLiteDatabase = a.a();
      localObject1 = localSQLiteDatabase;
      localObject3 = localSQLiteDatabase;
      localSQLiteDatabase.execSQL("DELETE FROM tb_regex WHERE state=-2 AND match_id IN (SELECT match_id FROM tb_regex GROUP BY match_id HAVING COUNT(match_id) > 1)");
      a.a(localSQLiteDatabase);
      return;
    }
    catch (Throwable localThrowable)
    {
      localObject3 = localObject1;
      localThrowable.printStackTrace();
      return;
    }
    finally
    {
      a.a((SQLiteDatabase)localObject3);
    }
  }
  
  /* Error */
  public static String getParsePatternString(String paramString1, String paramString2, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: ldc 2
    //   5: monitorenter
    //   6: invokestatic 242	cn/com/xy/sms/sdk/db/ParseItemManager:getPatterDb	()Landroid/database/sqlite/SQLiteDatabase;
    //   9: astore 5
    //   11: getstatic 69	cn/com/xy/sms/sdk/db/ParseItemManager:d	Z
    //   14: istore_3
    //   15: iload_3
    //   16: ifeq +10 -> 26
    //   19: aconst_null
    //   20: astore_1
    //   21: ldc 2
    //   23: monitorexit
    //   24: aload_1
    //   25: areturn
    //   26: invokestatic 248	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   29: pop
    //   30: aload 5
    //   32: iconst_0
    //   33: ldc 43
    //   35: iconst_1
    //   36: anewarray 107	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc 27
    //   43: aastore
    //   44: ldc -6
    //   46: iconst_3
    //   47: anewarray 107	java/lang/String
    //   50: dup
    //   51: iconst_0
    //   52: aload_0
    //   53: aastore
    //   54: dup
    //   55: iconst_1
    //   56: aload_1
    //   57: aastore
    //   58: dup
    //   59: iconst_2
    //   60: iload_2
    //   61: invokestatic 253	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   64: aastore
    //   65: aconst_null
    //   66: aconst_null
    //   67: aconst_null
    //   68: ldc -1
    //   70: invokestatic 258	cn/com/xy/sms/sdk/db/a/a:a	(Landroid/database/sqlite/SQLiteDatabase;ZLjava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcn/com/xy/sms/sdk/db/XyCursor;
    //   73: astore_0
    //   74: aload_0
    //   75: ifnull +63 -> 138
    //   78: aload_0
    //   79: astore_1
    //   80: aload_0
    //   81: invokevirtual 263	cn/com/xy/sms/sdk/db/XyCursor:getCount	()I
    //   84: ifle +54 -> 138
    //   87: aload_0
    //   88: astore_1
    //   89: aload_0
    //   90: ldc 27
    //   92: invokevirtual 266	cn/com/xy/sms/sdk/db/XyCursor:getColumnIndex	(Ljava/lang/String;)I
    //   95: istore_2
    //   96: aload_0
    //   97: astore_1
    //   98: aload_0
    //   99: invokevirtual 269	cn/com/xy/sms/sdk/db/XyCursor:moveToNext	()Z
    //   102: ifeq +41 -> 143
    //   105: aload_0
    //   106: astore_1
    //   107: aload_0
    //   108: iload_2
    //   109: invokevirtual 272	cn/com/xy/sms/sdk/db/XyCursor:getString	(I)Ljava/lang/String;
    //   112: astore 4
    //   114: aload 4
    //   116: astore_1
    //   117: aload_0
    //   118: ifnull -97 -> 21
    //   121: aload_0
    //   122: iconst_0
    //   123: invokestatic 276	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   126: aload 4
    //   128: astore_1
    //   129: goto -108 -> 21
    //   132: astore_0
    //   133: ldc 2
    //   135: monitorexit
    //   136: aload_0
    //   137: athrow
    //   138: aload_0
    //   139: astore_1
    //   140: invokestatic 278	cn/com/xy/sms/sdk/db/ParseItemManager:checkHasData	()V
    //   143: aload_0
    //   144: ifnull +8 -> 152
    //   147: aload_0
    //   148: iconst_0
    //   149: invokestatic 276	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   152: aconst_null
    //   153: astore_1
    //   154: goto -133 -> 21
    //   157: astore 4
    //   159: aconst_null
    //   160: astore_0
    //   161: aload_0
    //   162: astore_1
    //   163: aload 4
    //   165: invokevirtual 124	java/lang/Throwable:printStackTrace	()V
    //   168: aload_0
    //   169: ifnull -17 -> 152
    //   172: aload_0
    //   173: iconst_0
    //   174: invokestatic 276	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   177: goto -25 -> 152
    //   180: aload_1
    //   181: ifnull +8 -> 189
    //   184: aload_1
    //   185: iconst_0
    //   186: invokestatic 276	cn/com/xy/sms/sdk/db/XyCursor:closeCursor	(Lcn/com/xy/sms/sdk/db/XyCursor;Z)V
    //   189: aload_0
    //   190: athrow
    //   191: astore_0
    //   192: goto -12 -> 180
    //   195: astore 4
    //   197: goto -36 -> 161
    //   200: astore_0
    //   201: aload 4
    //   203: astore_1
    //   204: goto -24 -> 180
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	207	0	paramString1	String
    //   0	207	1	paramString2	String
    //   0	207	2	paramInt	int
    //   14	2	3	bool	boolean
    //   1	126	4	str	String
    //   157	7	4	localThrowable1	Throwable
    //   195	7	4	localThrowable2	Throwable
    //   9	22	5	localSQLiteDatabase	SQLiteDatabase
    // Exception table:
    //   from	to	target	type
    //   121	126	132	finally
    //   147	152	132	finally
    //   172	177	132	finally
    //   184	189	132	finally
    //   189	191	132	finally
    //   6	15	157	java/lang/Throwable
    //   26	74	157	java/lang/Throwable
    //   80	87	191	finally
    //   89	96	191	finally
    //   98	105	191	finally
    //   107	114	191	finally
    //   140	143	191	finally
    //   163	168	191	finally
    //   80	87	195	java/lang/Throwable
    //   89	96	195	java/lang/Throwable
    //   98	105	195	java/lang/Throwable
    //   107	114	195	java/lang/Throwable
    //   140	143	195	java/lang/Throwable
    //   6	15	200	finally
    //   26	74	200	finally
  }
  
  public static SQLiteDatabase getPatterDb()
  {
    try
    {
      long l = Thread.currentThread().getId();
      SQLiteDatabase localSQLiteDatabase2 = (SQLiteDatabase)c.get(Long.valueOf(l));
      SQLiteDatabase localSQLiteDatabase1;
      if (localSQLiteDatabase2 != null)
      {
        localSQLiteDatabase1 = localSQLiteDatabase2;
        if (localSQLiteDatabase2.isOpen()) {}
      }
      else
      {
        localSQLiteDatabase1 = a.a();
        c.put(Long.valueOf(l), localSQLiteDatabase1);
      }
      return localSQLiteDatabase1;
    }
    finally {}
  }
  
  public static boolean isInitData()
  {
    if (!a) {
      if (SysParamEntityManager.getLongParam("init_xiaoyuan_sdk", 0L, Constant.getContext()) != 1L) {
        break label31;
      }
    }
    label31:
    for (boolean bool = true;; bool = false)
    {
      a = bool;
      return a;
    }
  }
  
  public static void updateNeiQianSql(Context paramContext)
  {
    paramContext = Constant.getNQSQL_PATH();
    if (d.a(paramContext)) {}
    try
    {
      execNqSql = true;
      DBManager.excSql(paramContext, true);
      execNqSql = false;
      return;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    finally
    {
      execNqSql = false;
    }
  }
  
  /* Error */
  public static void updateParse(Context paramContext)
  {
    // Byte code:
    //   0: invokestatic 315	cn/com/xy/sms/sdk/constant/Constant:getInidb_PATH	()Ljava/lang/String;
    //   3: astore_0
    //   4: aload_0
    //   5: invokestatic 305	cn/com/xy/sms/sdk/util/d:a	(Ljava/lang/String;)Z
    //   8: ifne +4 -> 12
    //   11: return
    //   12: iconst_0
    //   13: iconst_m1
    //   14: invokestatic 319	cn/com/xy/sms/sdk/db/ParseItemManager:updateStatue	(II)V
    //   17: iconst_1
    //   18: putstatic 69	cn/com/xy/sms/sdk/db/ParseItemManager:d	Z
    //   21: aload_0
    //   22: iconst_0
    //   23: invokestatic 321	cn/com/xy/sms/sdk/db/a/a:a	(Ljava/lang/String;Z)V
    //   26: iconst_0
    //   27: putstatic 69	cn/com/xy/sms/sdk/db/ParseItemManager:d	Z
    //   30: invokestatic 248	cn/com/xy/sms/sdk/constant/Constant:getContext	()Landroid/content/Context;
    //   33: pop
    //   34: ldc 43
    //   36: ldc 105
    //   38: iconst_1
    //   39: anewarray 107	java/lang/String
    //   42: dup
    //   43: iconst_0
    //   44: new 109	java/lang/StringBuilder
    //   47: dup
    //   48: ldc 111
    //   50: invokespecial 114	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   53: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: aastore
    //   57: invokestatic 121	cn/com/xy/sms/sdk/db/a/a:a	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   60: pop
    //   61: aload_0
    //   62: invokestatic 323	cn/com/xy/sms/sdk/util/d:d	(Ljava/lang/String;)V
    //   65: iconst_0
    //   66: putstatic 69	cn/com/xy/sms/sdk/db/ParseItemManager:d	Z
    //   69: aload_0
    //   70: iconst_1
    //   71: aconst_null
    //   72: aconst_null
    //   73: aconst_null
    //   74: invokestatic 326	cn/com/xy/sms/sdk/db/a/a:a	(Ljava/lang/String;ZLjava/io/LineNumberReader;Ljava/io/BufferedReader;Landroid/database/sqlite/SQLiteDatabase;)V
    //   77: return
    //   78: astore_1
    //   79: aload_1
    //   80: invokevirtual 124	java/lang/Throwable:printStackTrace	()V
    //   83: goto -22 -> 61
    //   86: astore_1
    //   87: iconst_m1
    //   88: iconst_0
    //   89: invokestatic 319	cn/com/xy/sms/sdk/db/ParseItemManager:updateStatue	(II)V
    //   92: iconst_0
    //   93: putstatic 69	cn/com/xy/sms/sdk/db/ParseItemManager:d	Z
    //   96: aload_0
    //   97: iconst_1
    //   98: aconst_null
    //   99: aconst_null
    //   100: aconst_null
    //   101: invokestatic 326	cn/com/xy/sms/sdk/db/a/a:a	(Ljava/lang/String;ZLjava/io/LineNumberReader;Ljava/io/BufferedReader;Landroid/database/sqlite/SQLiteDatabase;)V
    //   104: return
    //   105: astore_1
    //   106: iconst_0
    //   107: putstatic 69	cn/com/xy/sms/sdk/db/ParseItemManager:d	Z
    //   110: aload_0
    //   111: iconst_1
    //   112: aconst_null
    //   113: aconst_null
    //   114: aconst_null
    //   115: invokestatic 326	cn/com/xy/sms/sdk/db/a/a:a	(Ljava/lang/String;ZLjava/io/LineNumberReader;Ljava/io/BufferedReader;Landroid/database/sqlite/SQLiteDatabase;)V
    //   118: aload_1
    //   119: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	120	0	paramContext	Context
    //   78	2	1	localThrowable1	Throwable
    //   86	1	1	localThrowable2	Throwable
    //   105	14	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   34	61	78	java/lang/Throwable
    //   12	34	86	java/lang/Throwable
    //   61	65	86	java/lang/Throwable
    //   79	83	86	java/lang/Throwable
    //   12	34	105	finally
    //   34	61	105	finally
    //   61	65	105	finally
    //   79	83	105	finally
    //   87	92	105	finally
  }
  
  public static void updateStatue(int paramInt1, int paramInt2)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("state", Integer.valueOf(paramInt2));
      a.a("tb_regex", localContentValues, "state = ? ", new String[] { paramInt1 });
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.ParseItemManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */