package cn.com.xy.sms.sdk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import cn.com.xy.sms.sdk.db.entity.PhoneSmsParseManager;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.d;
import java.io.BufferedReader;
import java.io.LineNumberReader;
import java.util.Hashtable;

public class DBManager
{
  private static int a = 24;
  private static b b = null;
  private static int c = 1000;
  private static int d = 100;
  public static final String dataBaseName = "smssdk.db";
  public static Object dblock = new Object();
  private static Hashtable<SQLiteDatabase, Integer> e = new Hashtable();
  
  private static SQLiteDatabase a()
  {
    return getSQLiteDatabase();
  }
  
  private static SQLiteDatabase a(Context paramContext)
  {
    synchronized (e)
    {
      if (e.size() >= 10) {
        return null;
      }
      if (paramContext == null)
      {
        LogManager.e("xiaoyuan", "createSQLiteDatabase: contexts is null");
        return null;
      }
      SQLiteDatabase localSQLiteDatabase = b(paramContext).getReadableDatabase();
      if (localSQLiteDatabase != null)
      {
        paramContext = (Integer)e.get(localSQLiteDatabase);
        if (paramContext == null) {}
        int i;
        for (paramContext = Integer.valueOf(1);; paramContext = Integer.valueOf(i + 1))
        {
          e.put(localSQLiteDatabase, paramContext);
          new StringBuilder("$$$$$ db code : ").append(localSQLiteDatabase.hashCode()).append(" cnt: ").append(paramContext);
          if (localSQLiteDatabase.isOpen()) {
            break;
          }
          e.remove(localSQLiteDatabase);
          return null;
          i = paramContext.intValue();
        }
      }
      return localSQLiteDatabase;
    }
  }
  
  private static void a(SQLiteDatabase paramSQLiteDatabase, String paramString)
  {
    try
    {
      paramSQLiteDatabase.execSQL(paramString);
      return;
    }
    catch (Throwable paramSQLiteDatabase) {}
  }
  
  private static boolean a(SQLiteDatabase paramSQLiteDatabase)
  {
    if ((paramSQLiteDatabase != null) && (paramSQLiteDatabase.inTransaction()))
    {
      close(paramSQLiteDatabase);
      new StringBuilder("DBManager db inTransaction threadName:").append(Thread.currentThread().getName());
      return true;
    }
    return false;
  }
  
  private static b b(Context paramContext)
  {
    try
    {
      if (b == null) {
        b = new b(paramContext, "smssdk.db", null, 24);
      }
      paramContext = b;
      return paramContext;
    }
    finally {}
  }
  
  public static void close(SQLiteDatabase paramSQLiteDatabase)
  {
    if (paramSQLiteDatabase == null) {
      return;
    }
    for (;;)
    {
      Integer localInteger;
      try
      {
        synchronized (e)
        {
          if (!paramSQLiteDatabase.isOpen())
          {
            e.remove(paramSQLiteDatabase);
            int i = e.size();
            if (i != 0) {
              break;
            }
            return;
          }
          localInteger = (Integer)e.get(paramSQLiteDatabase);
          if (localInteger == null) {
            new StringBuilder("$$$$$ db close cnt is null ").append(paramSQLiteDatabase.hashCode());
          }
        }
        localInteger = Integer.valueOf(localInteger.intValue() - 1);
      }
      catch (Throwable paramSQLiteDatabase)
      {
        LogManager.e("xiaoyuan", "DBManager close error: " + paramSQLiteDatabase.getMessage());
        return;
      }
      if (localInteger.intValue() == 0)
      {
        e.remove(paramSQLiteDatabase);
        paramSQLiteDatabase.close();
      }
      else
      {
        e.put(paramSQLiteDatabase, localInteger);
      }
    }
  }
  
  public static void closeCursor(Cursor paramCursor)
  {
    if (paramCursor != null) {
      paramCursor.close();
    }
  }
  
  public static void closeDB(String paramString, boolean paramBoolean, LineNumberReader paramLineNumberReader, BufferedReader paramBufferedReader, SQLiteDatabase paramSQLiteDatabase)
  {
    if (paramBoolean) {}
    try
    {
      d.d(paramString);
      if (paramLineNumberReader == null) {}
    }
    catch (Throwable paramString)
    {
      try
      {
        paramLineNumberReader.close();
        if (paramBufferedReader == null) {}
      }
      catch (Throwable paramString)
      {
        try
        {
          for (;;)
          {
            paramBufferedReader.close();
            if (paramSQLiteDatabase != null) {}
            try
            {
              if (paramSQLiteDatabase.inTransaction())
              {
                paramSQLiteDatabase.setTransactionSuccessful();
                paramSQLiteDatabase.endTransaction();
              }
              paramString = paramString;
            }
            catch (Throwable paramString)
            {
              paramString = paramString;
              paramString.printStackTrace();
              return;
            }
            finally
            {
              close(paramSQLiteDatabase);
            }
          }
          paramString = paramString;
        }
        catch (Throwable paramString)
        {
          for (;;) {}
        }
      }
    }
  }
  
  public static void createDb(SQLiteDatabase paramSQLiteDatabase)
  {
    try
    {
      paramSQLiteDatabase.execSQL("create table  if not exists tb_sdk_param (id int primary key,p_key TEXT,p_value TEXT,pextend_value TEXT)");
      paramSQLiteDatabase.execSQL("create table  if not exists tb_phone_info (id INTEGER PRIMARY KEY,iccid TEXT ,city TEXT,provinces TEXT,operator TEXT,areacode TEXT,ispost INTEGER DEFAULT 0,num TEXT,cnum TEXT,updateTime LONG,deft  INTEGER DEFAULT 0,net_updateTime LONG DEFAULT 0,user_provinces TEXT,user_areacode TEXT,user_operator TEXT,sim_index INTEGER DEFAULT -1)");
      paramSQLiteDatabase.execSQL(" create table  if not exists tb_public_info ( id INTEGER PRIMARY KEY, pubId INTEGER not null unique, pubName TEXT not null, pubType TEXT, classifyCode TEXT, weiXin TEXT, weiBoName TEXT, weiBoUrl TEXT, introduce TEXT, address TEXT, faxNum TEXT, webSite TEXT, moveWebSite TEXT, versionCode TEXT, email TEXT, parentPubId int, slogan TEXT, rectLogoName TEXT, circleLogoName TEXT, extend TEXT, hasmenu int, loadMenuTime long, updateInfoTime long )");
      paramSQLiteDatabase.execSQL("create table  if not exists tb_public_menu_info ( id INTEGER PRIMARY KEY, menuCode text not null, pubId INTEGER, menuName text not null, menuType text not null, sendTo text, sp text , menuDesc text , sms text, url text, phoneNum text  , actionData text  , extend text  )");
      paramSQLiteDatabase.execSQL("create table  if not exists tb_public_num_info ( id INTEGER PRIMARY KEY, pubId INTEGER not null, num text not null, purpose text , areaCode text not null, ptype int default 1, main INTEGER default 0, communication INTEGER default 0, isfull INTEGER default 0, minLen INTEGER default 0, maxLen INTEGER default 0, len INTEGER default 0, ntype text, extend text, lastloadtime LONG default 0, isrulenum INTEGER default 0)");
      paramSQLiteDatabase.execSQL(" create table  if not exists tb_centernum_location_info ( id INTEGER PRIMARY KEY, cnum TEXT not null unique, areaCode TEXT, city TEXT, checkTime long, operator TEXT )");
      paramSQLiteDatabase.execSQL("create table  if not exists tb_scene_config (scene_id TEXT,sceneType INTEGER DEFAULT '0',isCheck INTEGER DEFAULT '0',sceneVersion TEXT,isUse INTEGER DEFAULT '0',useCount INTEGER DEFAULT '0')");
      paramSQLiteDatabase.execSQL("create table  if not exists tb_res_download (id INTEGER PRIMARY KEY,scene_id TEXT,url TEXT,status INTEGER,pos INTEGER,last_load_time INTEGER DEFAULT '0' )");
      paramSQLiteDatabase.execSQL("create table  if not exists tb_scenerule_config (id TEXT,sceneRuleVersion TEXT,scene_id TEXT,province TEXT,operator TEXT,expire_date TEXT,Func_call INTEGER,Func_acc_url INTEGER,Func_reply_sms INTEGER,Func_config TEXT,res_urls TEXT,s_version TEXT,Scene_page_config TEXT,sceneType INTEGER DEFAULT '-1',isdownload INTEGER DEFAULT '0')");
      paramSQLiteDatabase.execSQL("create table  if not exists tb_jar_list (id INTEGER PRIMARY KEY,name TEXT,version TEXT,url TEXT,status INTEGER DEFAULT '0',update_time INTEGER DEFAULT '0',delaystart INTEGER DEFAULT '0',delayend INTEGER DEFAULT '0',count INTEGER DEFAULT '0',last_load_time INTEGER DEFAULT '0' ,is_use INTEGER DEFAULT '0')");
      paramSQLiteDatabase.execSQL("create table  if not exists tb_count_scene (scene_id TEXT,count INT)");
      paramSQLiteDatabase.execSQL("create table  if not exists tb_popup_action_scene (scene_id TEXT, date TEXT, parse_times INTEGER DEFAULT '0', popup_times INTEGER DEFAULT '0' ) ");
      paramSQLiteDatabase.execSQL("create table  if not exists tb_button_action_scene (scene_id TEXT, date TEXT, action_type INTEGER DEFAULT '0', times INTEGER DEFAULT '0' ) ");
      paramSQLiteDatabase.execSQL("create table  if not exists select_pub_time ( id INTEGER PRIMARY KEY AUTOINCREMENT, num TEXT, areaCode TEXT, selectTime long default 0 )");
      paramSQLiteDatabase.execSQL("create table  if not exists tb_train (id INTEGER PRIMARY KEY,train_num TEXT not null unique,start_city TEXT,end_city TEXT,train_type INTEGER default 0,start_time TEXT,end_time TEXT,mileage TEXT,station_list TEXT,duration TEXT,data_time LONG default 0)");
      paramSQLiteDatabase.execSQL("create table  if not exists tb_air (id INTEGER PRIMARY KEY,air_num TEXT not null unique,start_city TEXT,end_city TEXT,start_place TEXT,end_place TEXT,start_time TEXT,end_time TEXT,company TEXT)");
      paramSQLiteDatabase.execSQL("create table  if not exists tb_menu_list (id INTEGER PRIMARY KEY,name TEXT,version TEXT,url TEXT,status INTEGER DEFAULT '0',update_time INTEGER DEFAULT '0',delaystart INTEGER DEFAULT '0',delayend INTEGER DEFAULT '0',count INTEGER DEFAULT '0',last_load_time INTEGER DEFAULT '0' )");
      paramSQLiteDatabase.execSQL(MatchCacheManager.getCreateTableSql());
      paramSQLiteDatabase.execSQL("create table  if not exists tb_update_task ( id INTEGER PRIMARY KEY,content TEXT,t_group TEXT,t_version long )");
      paramSQLiteDatabase.execSQL("create table  if not exists tb_xml_res_download (id INTEGER PRIMARY KEY,scene_id TEXT,url TEXT,status INTEGER,pos INTEGER,last_load_time INTEGER DEFAULT '0' ,sceneType INTEGER DEFAULT '0',insert_time INTEGER DEFAULT '0' )");
      paramSQLiteDatabase.execSQL(" create table  if not exists tb_resourse_queue ( id INTEGER PRIMARY KEY, res_type INTEGER, res_version INTEGER, res_url TEXT, down_statu INTEGER DEFAULT '0', temp_filename TEXT, down_failed_time LONG DEFAULT '0')");
      paramSQLiteDatabase.execSQL(PhoneSmsParseManager.getCreateTableSql());
      paramSQLiteDatabase.execSQL(" create table  if not exists tb_netquery_time ( id INTEGER PRIMARY KEY, phone_num TEXT,area_code TEXT, request_time LONG DEFAULT '0')");
      paramSQLiteDatabase.execSQL("CREATE TABLE  IF NOT EXISTS tb_num_name (id INTEGER PRIMARY KEY, num TEXT NOT NULL UNIQUE, name TEXT NOT NULL, cnum TEXT,mark_time  LONG DEFAULT 0)");
      paramSQLiteDatabase.execSQL(" create table  if not exists tb_emergency_queue ( id INTEGER PRIMARY KEY, emVersion INTEGER, emContent TEXT )");
      try
      {
        paramSQLiteDatabase.execSQL("ALTER TABLE tb_public_info ADD COLUMN classifyCode TEXT");
        try
        {
          paramSQLiteDatabase.execSQL("ALTER TABLE tb_scene_config ADD COLUMN isCheck INTEGER DEFAULT '0'");
          try
          {
            paramSQLiteDatabase.execSQL("ALTER TABLE tb_scene_config ADD COLUMN useCount INTEGER DEFAULT '0'");
            try
            {
              paramSQLiteDatabase.execSQL("ALTER TABLE tb_scene_config ADD COLUMN isUse INTEGER DEFAULT '0'");
              try
              {
                paramSQLiteDatabase.execSQL("ALTER TABLE tb_jar_list ADD COLUMN is_use INTEGER DEFAULT '0'");
                try
                {
                  paramSQLiteDatabase.execSQL("ALTER TABLE tb_train ADD COLUMN station_list TEXT ");
                  try
                  {
                    paramSQLiteDatabase.execSQL("ALTER TABLE tb_train ADD COLUMN data_time LONG default 0 ");
                    try
                    {
                      paramSQLiteDatabase.execSQL("ALTER TABLE tb_match_cache ADD COLUMN bubble_lasttime INTEGER DEFAULT '0'");
                      try
                      {
                        paramSQLiteDatabase.execSQL("ALTER TABLE tb_match_cache ADD COLUMN card_lasttime INTEGER DEFAULT '0'");
                        try
                        {
                          paramSQLiteDatabase.execSQL("ALTER TABLE tb_match_cache ADD COLUMN session_lasttime INTEGER DEFAULT '0'");
                          a(paramSQLiteDatabase, " ALTER TABLE tb_public_num_info ADD COLUMN lastloadtime LONG default 0");
                          a(paramSQLiteDatabase, " ALTER TABLE tb_public_num_info ADD COLUMN isrulenum INTEGER default 0");
                          a(paramSQLiteDatabase, "ALTER TABLE tb_netquery_time ADD COLUMN area_code TEXT");
                          a(paramSQLiteDatabase, "ALTER TABLE tb_phone_info ADD COLUMN user_provinces TEXT ");
                          a(paramSQLiteDatabase, "ALTER TABLE tb_phone_info ADD COLUMN user_areacode TEXT ");
                          a(paramSQLiteDatabase, "ALTER TABLE tb_phone_info ADD COLUMN user_operator TEXT ");
                          a(paramSQLiteDatabase, "ALTER TABLE tb_phone_info ADD COLUMN sim_index INTEGER DEFAULT -1 ");
                          a(paramSQLiteDatabase, "ALTER TABLE tb_num_name ADD COLUMN cnum TEXT ");
                          a(paramSQLiteDatabase, "ALTER TABLE tb_num_name ADD COLUMN mark_time LONG DEFAULT 0");
                          return;
                        }
                        catch (Throwable localThrowable1)
                        {
                          for (;;) {}
                        }
                      }
                      catch (Throwable localThrowable2)
                      {
                        for (;;) {}
                      }
                    }
                    catch (Throwable localThrowable3)
                    {
                      for (;;) {}
                    }
                  }
                  catch (Throwable localThrowable4)
                  {
                    for (;;) {}
                  }
                }
                catch (Throwable localThrowable5)
                {
                  for (;;) {}
                }
              }
              catch (Throwable localThrowable6)
              {
                for (;;) {}
              }
            }
            catch (Throwable localThrowable7)
            {
              for (;;) {}
            }
          }
          catch (Throwable localThrowable8)
          {
            for (;;) {}
          }
        }
        catch (Throwable localThrowable9)
        {
          for (;;) {}
        }
      }
      catch (Throwable localThrowable10)
      {
        for (;;) {}
      }
    }
    catch (Throwable localThrowable11)
    {
      for (;;) {}
    }
  }
  
  /* Error */
  public static int delete(String paramString1, String paramString2, String[] paramArrayOfString)
  {
    // Byte code:
    //   0: getstatic 34	cn/com/xy/sms/sdk/db/DBManager:dblock	Ljava/lang/Object;
    //   3: astore 6
    //   5: aload 6
    //   7: monitorenter
    //   8: aconst_null
    //   9: astore 5
    //   11: invokestatic 44	cn/com/xy/sms/sdk/db/DBManager:getSQLiteDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   14: astore 4
    //   16: aload 4
    //   18: astore 5
    //   20: aload 4
    //   22: aload_0
    //   23: aload_1
    //   24: aload_2
    //   25: invokevirtual 285	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   28: istore_3
    //   29: aload 4
    //   31: invokestatic 128	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   34: aload 6
    //   36: monitorexit
    //   37: iload_3
    //   38: ireturn
    //   39: astore_0
    //   40: aload 5
    //   42: invokestatic 128	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   45: iconst_m1
    //   46: istore_3
    //   47: goto -13 -> 34
    //   50: aload 4
    //   52: invokestatic 128	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   55: aload_0
    //   56: athrow
    //   57: astore_0
    //   58: aload 6
    //   60: monitorexit
    //   61: aload_0
    //   62: athrow
    //   63: astore_0
    //   64: goto -14 -> 50
    //   67: astore_0
    //   68: aconst_null
    //   69: astore 4
    //   71: goto -21 -> 50
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	74	0	paramString1	String
    //   0	74	1	paramString2	String
    //   0	74	2	paramArrayOfString	String[]
    //   28	19	3	i	int
    //   14	56	4	localSQLiteDatabase	SQLiteDatabase
    //   9	32	5	localObject1	Object
    //   3	56	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   11	16	39	java/lang/Throwable
    //   20	29	39	java/lang/Throwable
    //   29	34	57	finally
    //   34	37	57	finally
    //   40	45	57	finally
    //   50	57	57	finally
    //   20	29	63	finally
    //   11	16	67	finally
  }
  
  /* Error */
  public static void excSql(String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore_3
    //   8: aload_0
    //   9: invokestatic 290	cn/com/xy/sms/sdk/util/d:a	(Ljava/lang/String;)Z
    //   12: ifeq +83 -> 95
    //   15: new 171	java/io/BufferedReader
    //   18: dup
    //   19: new 292	java/io/FileReader
    //   22: dup
    //   23: new 294	java/io/File
    //   26: dup
    //   27: aload_0
    //   28: invokespecial 295	java/io/File:<init>	(Ljava/lang/String;)V
    //   31: invokespecial 298	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   34: invokespecial 301	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   37: astore_2
    //   38: new 168	java/io/LineNumberReader
    //   41: dup
    //   42: aload_2
    //   43: invokespecial 302	java/io/LineNumberReader:<init>	(Ljava/io/Reader;)V
    //   46: astore 4
    //   48: aload 6
    //   50: astore 5
    //   52: invokestatic 44	cn/com/xy/sms/sdk/db/DBManager:getSQLiteDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   55: astore_3
    //   56: aload_3
    //   57: astore 5
    //   59: aload_3
    //   60: invokevirtual 305	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   63: aload_3
    //   64: astore 5
    //   66: aload 4
    //   68: invokevirtual 308	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   71: astore 6
    //   73: aload 6
    //   75: ifnonnull +21 -> 96
    //   78: aload_3
    //   79: astore 5
    //   81: aload 4
    //   83: invokevirtual 169	java/io/LineNumberReader:close	()V
    //   86: aload_0
    //   87: iload_1
    //   88: aload 4
    //   90: aload_2
    //   91: aload_3
    //   92: invokestatic 310	cn/com/xy/sms/sdk/db/DBManager:closeDB	(Ljava/lang/String;ZLjava/io/LineNumberReader;Ljava/io/BufferedReader;Landroid/database/sqlite/SQLiteDatabase;)V
    //   95: return
    //   96: aload_3
    //   97: astore 5
    //   99: aload 6
    //   101: invokestatic 315	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   104: ifne -41 -> 63
    //   107: aload_3
    //   108: astore 5
    //   110: aload_3
    //   111: aload 6
    //   113: invokevirtual 120	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   116: goto -53 -> 63
    //   119: astore 6
    //   121: aload_3
    //   122: astore 5
    //   124: aload 6
    //   126: invokevirtual 181	java/lang/Throwable:printStackTrace	()V
    //   129: aload_3
    //   130: astore 5
    //   132: aload 6
    //   134: athrow
    //   135: astore 5
    //   137: aload 5
    //   139: invokevirtual 181	java/lang/Throwable:printStackTrace	()V
    //   142: aload_0
    //   143: iload_1
    //   144: aload 4
    //   146: aload_2
    //   147: aload_3
    //   148: invokestatic 310	cn/com/xy/sms/sdk/db/DBManager:closeDB	(Ljava/lang/String;ZLjava/io/LineNumberReader;Ljava/io/BufferedReader;Landroid/database/sqlite/SQLiteDatabase;)V
    //   151: return
    //   152: astore_3
    //   153: aconst_null
    //   154: astore 4
    //   156: aconst_null
    //   157: astore_2
    //   158: aload_0
    //   159: iload_1
    //   160: aload 4
    //   162: aload_2
    //   163: aload 5
    //   165: invokestatic 310	cn/com/xy/sms/sdk/db/DBManager:closeDB	(Ljava/lang/String;ZLjava/io/LineNumberReader;Ljava/io/BufferedReader;Landroid/database/sqlite/SQLiteDatabase;)V
    //   168: aload_3
    //   169: athrow
    //   170: astore_3
    //   171: aconst_null
    //   172: astore 4
    //   174: goto -16 -> 158
    //   177: astore_3
    //   178: goto -20 -> 158
    //   181: astore 6
    //   183: aload_3
    //   184: astore 5
    //   186: aload 6
    //   188: astore_3
    //   189: goto -31 -> 158
    //   192: astore 5
    //   194: aconst_null
    //   195: astore 4
    //   197: aconst_null
    //   198: astore 6
    //   200: aload_3
    //   201: astore_2
    //   202: aload 6
    //   204: astore_3
    //   205: goto -68 -> 137
    //   208: astore 5
    //   210: aconst_null
    //   211: astore 4
    //   213: aconst_null
    //   214: astore_3
    //   215: goto -78 -> 137
    //   218: astore 5
    //   220: aconst_null
    //   221: astore_3
    //   222: goto -85 -> 137
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	225	0	paramString	String
    //   0	225	1	paramBoolean	boolean
    //   37	165	2	localObject1	Object
    //   7	141	3	localSQLiteDatabase	SQLiteDatabase
    //   152	17	3	localObject2	Object
    //   170	1	3	localObject3	Object
    //   177	7	3	localObject4	Object
    //   188	34	3	localObject5	Object
    //   46	166	4	localLineNumberReader	LineNumberReader
    //   1	130	5	localObject6	Object
    //   135	29	5	localThrowable1	Throwable
    //   184	1	5	localObject7	Object
    //   192	1	5	localThrowable2	Throwable
    //   208	1	5	localThrowable3	Throwable
    //   218	1	5	localThrowable4	Throwable
    //   4	108	6	str	String
    //   119	14	6	localThrowable5	Throwable
    //   181	6	6	localObject8	Object
    //   198	5	6	localObject9	Object
    // Exception table:
    //   from	to	target	type
    //   66	73	119	java/lang/Throwable
    //   81	86	119	java/lang/Throwable
    //   99	107	119	java/lang/Throwable
    //   110	116	119	java/lang/Throwable
    //   59	63	135	java/lang/Throwable
    //   124	129	135	java/lang/Throwable
    //   132	135	135	java/lang/Throwable
    //   15	38	152	finally
    //   38	48	170	finally
    //   52	56	177	finally
    //   59	63	177	finally
    //   66	73	177	finally
    //   81	86	177	finally
    //   99	107	177	finally
    //   110	116	177	finally
    //   124	129	177	finally
    //   132	135	177	finally
    //   137	142	181	finally
    //   15	38	192	java/lang/Throwable
    //   38	48	208	java/lang/Throwable
    //   52	56	218	java/lang/Throwable
  }
  
  public static SQLiteDatabase getSQLiteDatabase()
  {
    SQLiteDatabase localSQLiteDatabase1 = null;
    long l = System.currentTimeMillis();
    for (;;)
    {
      SQLiteDatabase localSQLiteDatabase2;
      if (localSQLiteDatabase1 != null) {
        localSQLiteDatabase2 = localSQLiteDatabase1;
      }
      do
      {
        do
        {
          return localSQLiteDatabase2;
          localSQLiteDatabase1 = a(Constant.getContext());
          if (localSQLiteDatabase1 == null) {
            break;
          }
          localSQLiteDatabase2 = localSQLiteDatabase1;
        } while (!localSQLiteDatabase1.inTransaction());
        new StringBuilder("getSQLiteDatabase inTransaction hashcode: ").append(localSQLiteDatabase1.hashCode());
        return localSQLiteDatabase1;
        localSQLiteDatabase2 = localSQLiteDatabase1;
      } while (System.currentTimeMillis() - l >= c);
      try
      {
        Thread.sleep(d);
      }
      catch (InterruptedException localInterruptedException)
      {
        LogManager.e("xiaoyuan", "DBManager getSQLiteDatabase error: " + localInterruptedException.getMessage(), localInterruptedException);
      }
    }
  }
  
  public static long insert(String paramString, ContentValues paramContentValues)
  {
    Object localObject1 = null;
    SQLiteDatabase localSQLiteDatabase;
    synchronized (dblock)
    {
      try
      {
        localSQLiteDatabase = getSQLiteDatabase();
        localObject1 = localSQLiteDatabase;
        long l;
        close(localSQLiteDatabase);
      }
      catch (Throwable paramString)
      {
        for (;;)
        {
          try
          {
            l = localSQLiteDatabase.insert(paramString, null, paramContentValues);
          }
          finally {}
          try
          {
            close(localSQLiteDatabase);
            return l;
          }
          finally {}
          paramString = paramString;
          close((SQLiteDatabase)localObject1);
          l = -1L;
        }
      }
      throw paramString;
    }
  }
  
  public static XyCursor query(String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2)
  {
    return query(false, paramString1, paramArrayOfString1, paramString2, paramArrayOfString2, null, null, null, null);
  }
  
  public static XyCursor query(String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    Object localObject = null;
    try
    {
      localSQLiteDatabase = getSQLiteDatabase();
      close(paramString1);
    }
    catch (Throwable paramArrayOfString1)
    {
      try
      {
        if (a(localSQLiteDatabase)) {
          return null;
        }
        paramString1 = new XyCursor(localSQLiteDatabase, localSQLiteDatabase.query(paramString1, paramArrayOfString1, paramString2, paramArrayOfString2, paramString3, paramString4, paramString5, paramString6));
        return paramString1;
      }
      catch (Throwable paramArrayOfString1)
      {
        for (;;)
        {
          SQLiteDatabase localSQLiteDatabase;
          paramString1 = localSQLiteDatabase;
        }
      }
      paramArrayOfString1 = paramArrayOfString1;
      paramString1 = (String)localObject;
    }
    LogManager.e("xiaoyuan", "DBManager 2query error: " + paramArrayOfString1.getMessage(), paramArrayOfString1);
    return null;
  }
  
  /* Error */
  public static XyCursor query(boolean paramBoolean, String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 10
    //   3: invokestatic 44	cn/com/xy/sms/sdk/db/DBManager:getSQLiteDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   6: astore 9
    //   8: aload 9
    //   10: ifnonnull +5 -> 15
    //   13: aconst_null
    //   14: areturn
    //   15: aload 9
    //   17: invokestatic 355	cn/com/xy/sms/sdk/db/DBManager:a	(Landroid/database/sqlite/SQLiteDatabase;)Z
    //   20: ifeq +5 -> 25
    //   23: aconst_null
    //   24: areturn
    //   25: new 357	cn/com/xy/sms/sdk/db/XyCursor
    //   28: dup
    //   29: aload 9
    //   31: aload 9
    //   33: iload_0
    //   34: aload_1
    //   35: aload_2
    //   36: aload_3
    //   37: aload 4
    //   39: aload 5
    //   41: aload 6
    //   43: aload 7
    //   45: aload 8
    //   47: invokevirtual 368	android/database/sqlite/SQLiteDatabase:query	(ZLjava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   50: invokespecial 363	cn/com/xy/sms/sdk/db/XyCursor:<init>	(Landroid/database/sqlite/SQLiteDatabase;Landroid/database/Cursor;)V
    //   53: astore_1
    //   54: aload_1
    //   55: areturn
    //   56: astore_2
    //   57: aload 10
    //   59: astore_1
    //   60: aload_1
    //   61: invokestatic 128	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   64: ldc 51
    //   66: new 82	java/lang/StringBuilder
    //   69: dup
    //   70: ldc_w 370
    //   73: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   76: aload_2
    //   77: invokevirtual 150	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   80: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: invokevirtual 153	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   86: aload_2
    //   87: invokestatic 186	cn/com/xy/sms/sdk/log/LogManager:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   90: aconst_null
    //   91: areturn
    //   92: astore_2
    //   93: aload 9
    //   95: astore_1
    //   96: goto -36 -> 60
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	99	0	paramBoolean	boolean
    //   0	99	1	paramString1	String
    //   0	99	2	paramArrayOfString1	String[]
    //   0	99	3	paramString2	String
    //   0	99	4	paramArrayOfString2	String[]
    //   0	99	5	paramString3	String
    //   0	99	6	paramString4	String
    //   0	99	7	paramString5	String
    //   0	99	8	paramString6	String
    //   6	88	9	localSQLiteDatabase	SQLiteDatabase
    //   1	57	10	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   3	8	56	java/lang/Throwable
    //   15	23	92	java/lang/Throwable
    //   25	54	92	java/lang/Throwable
  }
  
  public static XyCursor rawQuery(String paramString, String[] paramArrayOfString)
  {
    for (;;)
    {
      try
      {
        SQLiteDatabase localSQLiteDatabase = getSQLiteDatabase();
        close(localSQLiteDatabase);
      }
      catch (Throwable paramString)
      {
        try
        {
          if (a(localSQLiteDatabase)) {
            return null;
          }
          paramString = new XyCursor(localSQLiteDatabase, localSQLiteDatabase.rawQuery(paramString, paramArrayOfString));
          return paramString;
        }
        catch (Throwable paramString)
        {
          for (;;) {}
        }
        paramString = paramString;
        localSQLiteDatabase = null;
      }
      LogManager.e("xiaoyuan", "DBManager rawQuery error: " + paramString.getMessage(), paramString);
      paramString = null;
    }
  }
  
  public static long saveOrUpdateTableData(String paramString1, ContentValues paramContentValues, String paramString2, String[] paramArrayOfString)
  {
    long l1 = 0L;
    try
    {
      long l2 = update(paramString1, paramContentValues, paramString2, paramArrayOfString);
      if (l2 < 1L)
      {
        l1 = l2;
        l2 = insert(paramString1, paramContentValues);
        return l2;
      }
      return -l2;
    }
    catch (Throwable paramString1)
    {
      LogManager.e("xiaoyuan", "DBManager saveOrUpdateTableData error: " + paramString1.getMessage(), paramString1);
    }
    return l1;
  }
  
  /* Error */
  public static int update(String paramString1, ContentValues paramContentValues, String paramString2, String[] paramArrayOfString)
  {
    // Byte code:
    //   0: getstatic 34	cn/com/xy/sms/sdk/db/DBManager:dblock	Ljava/lang/Object;
    //   3: astore 7
    //   5: aload 7
    //   7: monitorenter
    //   8: aconst_null
    //   9: astore 6
    //   11: invokestatic 44	cn/com/xy/sms/sdk/db/DBManager:getSQLiteDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   14: astore 5
    //   16: aload 5
    //   18: astore 6
    //   20: aload 5
    //   22: aload_0
    //   23: aload_1
    //   24: aload_2
    //   25: aload_3
    //   26: invokevirtual 388	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   29: istore 4
    //   31: aload 5
    //   33: invokestatic 128	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   36: aload 7
    //   38: monitorexit
    //   39: iload 4
    //   41: ireturn
    //   42: astore_0
    //   43: aload 6
    //   45: invokestatic 128	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   48: iconst_m1
    //   49: istore 4
    //   51: goto -15 -> 36
    //   54: aload 5
    //   56: invokestatic 128	cn/com/xy/sms/sdk/db/DBManager:close	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   59: aload_0
    //   60: athrow
    //   61: astore_0
    //   62: aload 7
    //   64: monitorexit
    //   65: aload_0
    //   66: athrow
    //   67: astore_0
    //   68: goto -14 -> 54
    //   71: astore_0
    //   72: aconst_null
    //   73: astore 5
    //   75: goto -21 -> 54
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	78	0	paramString1	String
    //   0	78	1	paramContentValues	ContentValues
    //   0	78	2	paramString2	String
    //   0	78	3	paramArrayOfString	String[]
    //   29	21	4	i	int
    //   14	60	5	localSQLiteDatabase	SQLiteDatabase
    //   9	35	6	localObject1	Object
    //   3	60	7	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   11	16	42	java/lang/Throwable
    //   20	31	42	java/lang/Throwable
    //   31	36	61	finally
    //   36	39	61	finally
    //   43	48	61	finally
    //   54	61	61	finally
    //   20	31	67	finally
    //   11	16	71	finally
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.DBManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */