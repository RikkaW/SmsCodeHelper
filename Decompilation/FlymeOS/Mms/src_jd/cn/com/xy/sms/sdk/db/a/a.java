package cn.com.xy.sms.sdk.db.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.util.d;
import java.io.BufferedReader;
import java.io.LineNumberReader;
import java.util.Hashtable;

public final class a
{
  private static final String a = "bizport.db";
  private static int b = 1;
  private static b c = null;
  private static int d = 1000;
  private static int e = 100;
  private static Object f = new Object();
  private static Hashtable<SQLiteDatabase, Integer> g = new Hashtable();
  
  /* Error */
  public static int a(String paramString1, ContentValues paramContentValues, String paramString2, String[] paramArrayOfString)
  {
    // Byte code:
    //   0: getstatic 33	cn/com/xy/sms/sdk/db/a/a:f	Ljava/lang/Object;
    //   3: astore 7
    //   5: aload 7
    //   7: monitorenter
    //   8: aconst_null
    //   9: astore 6
    //   11: invokestatic 45	cn/com/xy/sms/sdk/db/a/a:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   14: astore 5
    //   16: aload 5
    //   18: astore 6
    //   20: aload 5
    //   22: aload_0
    //   23: aload_1
    //   24: aload_2
    //   25: aload_3
    //   26: invokevirtual 50	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   29: istore 4
    //   31: aload 5
    //   33: invokestatic 53	cn/com/xy/sms/sdk/db/a/a:a	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   36: aload 7
    //   38: monitorexit
    //   39: iload 4
    //   41: ireturn
    //   42: astore_0
    //   43: aload 6
    //   45: invokestatic 53	cn/com/xy/sms/sdk/db/a/a:a	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   48: iconst_m1
    //   49: istore 4
    //   51: goto -15 -> 36
    //   54: aload 5
    //   56: invokestatic 53	cn/com/xy/sms/sdk/db/a/a:a	(Landroid/database/sqlite/SQLiteDatabase;)V
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
  
  /* Error */
  public static int a(String paramString1, String paramString2, String[] paramArrayOfString)
  {
    // Byte code:
    //   0: getstatic 33	cn/com/xy/sms/sdk/db/a/a:f	Ljava/lang/Object;
    //   3: astore 6
    //   5: aload 6
    //   7: monitorenter
    //   8: aconst_null
    //   9: astore 5
    //   11: invokestatic 45	cn/com/xy/sms/sdk/db/a/a:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   14: astore 4
    //   16: aload 4
    //   18: astore 5
    //   20: aload 4
    //   22: aload_0
    //   23: aload_1
    //   24: aload_2
    //   25: invokevirtual 57	android/database/sqlite/SQLiteDatabase:delete	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)I
    //   28: istore_3
    //   29: aload 4
    //   31: invokestatic 53	cn/com/xy/sms/sdk/db/a/a:a	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   34: aload 6
    //   36: monitorexit
    //   37: iload_3
    //   38: ireturn
    //   39: astore_0
    //   40: aload 5
    //   42: invokestatic 53	cn/com/xy/sms/sdk/db/a/a:a	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   45: iconst_m1
    //   46: istore_3
    //   47: goto -13 -> 34
    //   50: aload 4
    //   52: invokestatic 53	cn/com/xy/sms/sdk/db/a/a:a	(Landroid/database/sqlite/SQLiteDatabase;)V
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
  
  private static long a(String paramString, ContentValues paramContentValues)
  {
    Object localObject1 = null;
    SQLiteDatabase localSQLiteDatabase;
    synchronized (f)
    {
      try
      {
        localSQLiteDatabase = a();
        localObject1 = localSQLiteDatabase;
        long l;
        a(localSQLiteDatabase);
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
            a(localSQLiteDatabase);
            return l;
          }
          finally {}
          paramString = paramString;
          a((SQLiteDatabase)localObject1);
          l = -1L;
        }
      }
      throw paramString;
    }
  }
  
  public static SQLiteDatabase a()
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
          localSQLiteDatabase2 = localSQLiteDatabase1;
        } while (localSQLiteDatabase1 != null);
        localSQLiteDatabase2 = localSQLiteDatabase1;
      } while (System.currentTimeMillis() - l >= d);
      try
      {
        Thread.sleep(e);
      }
      catch (InterruptedException localInterruptedException)
      {
        new StringBuilder("BizportDBManager getSQLiteDatabase ").append(localInterruptedException.getMessage());
      }
    }
  }
  
  private static SQLiteDatabase a(Context paramContext)
  {
    synchronized (g)
    {
      if (g.size() >= 10) {
        return null;
      }
      SQLiteDatabase localSQLiteDatabase = b(paramContext.getApplicationContext()).getReadableDatabase();
      if (localSQLiteDatabase != null)
      {
        paramContext = (Integer)g.get(localSQLiteDatabase);
        if (paramContext == null) {}
        int i;
        for (paramContext = Integer.valueOf(1);; paramContext = Integer.valueOf(i + 1))
        {
          g.put(localSQLiteDatabase, paramContext);
          if (localSQLiteDatabase.isOpen()) {
            break;
          }
          g.remove(localSQLiteDatabase);
          return null;
          i = paramContext.intValue();
        }
      }
      return localSQLiteDatabase;
    }
  }
  
  public static XyCursor a(SQLiteDatabase paramSQLiteDatabase, boolean paramBoolean, String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    try
    {
      paramSQLiteDatabase = new XyCursor(null, paramSQLiteDatabase.query(false, paramString1, paramArrayOfString1, paramString2, paramArrayOfString2, null, null, null, paramString6), 1);
      return paramSQLiteDatabase;
    }
    catch (Throwable paramSQLiteDatabase)
    {
      new StringBuilder("BizportDBManager 2query ").append(paramSQLiteDatabase.getMessage());
    }
    return null;
  }
  
  private static XyCursor a(String paramString, String[] paramArrayOfString)
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = a();
      a(localSQLiteDatabase);
    }
    catch (Throwable paramString)
    {
      try
      {
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
    new StringBuilder("BizportDBManager rawQuery ").append(paramString.getMessage());
    return null;
  }
  
  private static XyCursor a(String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2)
  {
    return a(false, paramString1, paramArrayOfString1, paramString2, paramArrayOfString2, null, null, null, null);
  }
  
  private static XyCursor a(String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    Object localObject = null;
    try
    {
      localSQLiteDatabase = a();
      a(paramString1);
    }
    catch (Throwable paramArrayOfString1)
    {
      try
      {
        paramString1 = new XyCursor(localSQLiteDatabase, localSQLiteDatabase.query(paramString1, paramArrayOfString1, paramString2, paramArrayOfString2, paramString3, paramString4, paramString5, paramString6), 1);
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
    new StringBuilder("BizportDBManager 1query ").append(paramArrayOfString1.getMessage());
    return null;
  }
  
  public static XyCursor a(boolean paramBoolean, String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    paramString3 = null;
    try
    {
      paramString4 = a();
      paramString3 = paramString4;
      paramString1 = new XyCursor(paramString4, paramString4.query(false, paramString1, paramArrayOfString1, paramString2, paramArrayOfString2, null, null, null, paramString6), 1);
      return paramString1;
    }
    catch (Throwable paramString1)
    {
      a(paramString3);
    }
    return null;
  }
  
  private static void a(Cursor paramCursor)
  {
    if (paramCursor != null) {
      paramCursor.close();
    }
  }
  
  /* Error */
  public static void a(SQLiteDatabase paramSQLiteDatabase)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +4 -> 5
    //   4: return
    //   5: getstatic 38	cn/com/xy/sms/sdk/db/a/a:g	Ljava/util/Hashtable;
    //   8: astore_1
    //   9: aload_1
    //   10: monitorenter
    //   11: getstatic 38	cn/com/xy/sms/sdk/db/a/a:g	Ljava/util/Hashtable;
    //   14: aload_0
    //   15: invokevirtual 123	java/util/Hashtable:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   18: checkcast 125	java/lang/Integer
    //   21: astore_2
    //   22: aload_2
    //   23: ifnonnull +63 -> 86
    //   26: new 89	java/lang/StringBuilder
    //   29: dup
    //   30: ldc -72
    //   32: invokespecial 94	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   35: aload_0
    //   36: invokevirtual 187	java/lang/Object:hashCode	()I
    //   39: invokevirtual 190	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   42: pop
    //   43: aload_1
    //   44: monitorexit
    //   45: getstatic 38	cn/com/xy/sms/sdk/db/a/a:g	Ljava/util/Hashtable;
    //   48: invokevirtual 106	java/util/Hashtable:size	()I
    //   51: ifne -47 -> 4
    //   54: getstatic 24	cn/com/xy/sms/sdk/db/a/a:c	Lcn/com/xy/sms/sdk/db/a/b;
    //   57: ifnull -53 -> 4
    //   60: getstatic 24	cn/com/xy/sms/sdk/db/a/a:c	Lcn/com/xy/sms/sdk/db/a/b;
    //   63: invokevirtual 191	cn/com/xy/sms/sdk/db/a/b:close	()V
    //   66: return
    //   67: astore_0
    //   68: new 89	java/lang/StringBuilder
    //   71: dup
    //   72: ldc -63
    //   74: invokespecial 94	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   77: aload_0
    //   78: invokevirtual 156	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   81: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: pop
    //   85: return
    //   86: aload_2
    //   87: invokevirtual 143	java/lang/Integer:intValue	()I
    //   90: iconst_1
    //   91: isub
    //   92: invokestatic 129	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   95: astore_2
    //   96: aload_2
    //   97: invokevirtual 143	java/lang/Integer:intValue	()I
    //   100: ifne +23 -> 123
    //   103: getstatic 38	cn/com/xy/sms/sdk/db/a/a:g	Ljava/util/Hashtable;
    //   106: aload_0
    //   107: invokevirtual 140	java/util/Hashtable:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   110: pop
    //   111: aload_0
    //   112: invokevirtual 194	android/database/sqlite/SQLiteDatabase:close	()V
    //   115: goto -72 -> 43
    //   118: astore_0
    //   119: aload_1
    //   120: monitorexit
    //   121: aload_0
    //   122: athrow
    //   123: getstatic 38	cn/com/xy/sms/sdk/db/a/a:g	Ljava/util/Hashtable;
    //   126: aload_0
    //   127: aload_2
    //   128: invokevirtual 133	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   131: pop
    //   132: goto -89 -> 43
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	135	0	paramSQLiteDatabase	SQLiteDatabase
    //   21	107	2	localInteger	Integer
    // Exception table:
    //   from	to	target	type
    //   5	11	67	java/lang/Throwable
    //   45	66	67	java/lang/Throwable
    //   119	123	67	java/lang/Throwable
    //   11	22	118	finally
    //   26	43	118	finally
    //   43	45	118	finally
    //   86	115	118	finally
    //   123	132	118	finally
  }
  
  /* Error */
  public static void a(String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore_3
    //   8: aload_0
    //   9: invokestatic 200	cn/com/xy/sms/sdk/util/d:a	(Ljava/lang/String;)Z
    //   12: ifne +4 -> 16
    //   15: return
    //   16: new 202	java/io/BufferedReader
    //   19: dup
    //   20: new 204	java/io/FileReader
    //   23: dup
    //   24: new 206	java/io/File
    //   27: dup
    //   28: aload_0
    //   29: invokespecial 207	java/io/File:<init>	(Ljava/lang/String;)V
    //   32: invokespecial 210	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   35: invokespecial 213	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   38: astore_2
    //   39: new 215	java/io/LineNumberReader
    //   42: dup
    //   43: aload_2
    //   44: invokespecial 216	java/io/LineNumberReader:<init>	(Ljava/io/Reader;)V
    //   47: astore 4
    //   49: aload 6
    //   51: astore 5
    //   53: invokestatic 45	cn/com/xy/sms/sdk/db/a/a:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   56: astore_3
    //   57: aload_3
    //   58: astore 5
    //   60: aload_3
    //   61: invokevirtual 219	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   64: aload_3
    //   65: astore 5
    //   67: aload 4
    //   69: invokevirtual 222	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   72: astore 6
    //   74: aload 6
    //   76: ifnonnull +21 -> 97
    //   79: aload_3
    //   80: astore 5
    //   82: aload 4
    //   84: invokevirtual 223	java/io/LineNumberReader:close	()V
    //   87: aload_0
    //   88: iconst_0
    //   89: aload 4
    //   91: aload_2
    //   92: aload_3
    //   93: invokestatic 226	cn/com/xy/sms/sdk/db/a/a:a	(Ljava/lang/String;ZLjava/io/LineNumberReader;Ljava/io/BufferedReader;Landroid/database/sqlite/SQLiteDatabase;)V
    //   96: return
    //   97: aload_3
    //   98: astore 5
    //   100: aload 6
    //   102: invokestatic 231	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   105: ifne -41 -> 64
    //   108: aload_3
    //   109: astore 5
    //   111: aload_3
    //   112: aload 6
    //   114: invokevirtual 234	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   117: goto -53 -> 64
    //   120: astore 6
    //   122: aload_3
    //   123: astore 5
    //   125: aload 6
    //   127: athrow
    //   128: astore 5
    //   130: aload 5
    //   132: invokevirtual 237	java/lang/Throwable:printStackTrace	()V
    //   135: aload_0
    //   136: iconst_0
    //   137: aload 4
    //   139: aload_2
    //   140: aload_3
    //   141: invokestatic 226	cn/com/xy/sms/sdk/db/a/a:a	(Ljava/lang/String;ZLjava/io/LineNumberReader;Ljava/io/BufferedReader;Landroid/database/sqlite/SQLiteDatabase;)V
    //   144: return
    //   145: astore_3
    //   146: aconst_null
    //   147: astore 4
    //   149: aconst_null
    //   150: astore_2
    //   151: aload_0
    //   152: iconst_0
    //   153: aload 4
    //   155: aload_2
    //   156: aload 5
    //   158: invokestatic 226	cn/com/xy/sms/sdk/db/a/a:a	(Ljava/lang/String;ZLjava/io/LineNumberReader;Ljava/io/BufferedReader;Landroid/database/sqlite/SQLiteDatabase;)V
    //   161: aload_3
    //   162: athrow
    //   163: astore_3
    //   164: aconst_null
    //   165: astore 4
    //   167: goto -16 -> 151
    //   170: astore_3
    //   171: goto -20 -> 151
    //   174: astore 6
    //   176: aload_3
    //   177: astore 5
    //   179: aload 6
    //   181: astore_3
    //   182: goto -31 -> 151
    //   185: astore 5
    //   187: aconst_null
    //   188: astore 4
    //   190: aconst_null
    //   191: astore 6
    //   193: aload_3
    //   194: astore_2
    //   195: aload 6
    //   197: astore_3
    //   198: goto -68 -> 130
    //   201: astore 5
    //   203: aconst_null
    //   204: astore 4
    //   206: aconst_null
    //   207: astore_3
    //   208: goto -78 -> 130
    //   211: astore 5
    //   213: aconst_null
    //   214: astore_3
    //   215: goto -85 -> 130
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	218	0	paramString	String
    //   0	218	1	paramBoolean	boolean
    //   38	157	2	localObject1	Object
    //   7	134	3	localSQLiteDatabase	SQLiteDatabase
    //   145	17	3	localObject2	Object
    //   163	1	3	localObject3	Object
    //   170	7	3	localObject4	Object
    //   181	34	3	localObject5	Object
    //   47	158	4	localLineNumberReader	LineNumberReader
    //   1	123	5	localObject6	Object
    //   128	29	5	localThrowable1	Throwable
    //   177	1	5	localObject7	Object
    //   185	1	5	localThrowable2	Throwable
    //   201	1	5	localThrowable3	Throwable
    //   211	1	5	localThrowable4	Throwable
    //   4	109	6	str	String
    //   120	6	6	localThrowable5	Throwable
    //   174	6	6	localObject8	Object
    //   191	5	6	localObject9	Object
    // Exception table:
    //   from	to	target	type
    //   67	74	120	java/lang/Throwable
    //   82	87	120	java/lang/Throwable
    //   100	108	120	java/lang/Throwable
    //   111	117	120	java/lang/Throwable
    //   60	64	128	java/lang/Throwable
    //   125	128	128	java/lang/Throwable
    //   16	39	145	finally
    //   39	49	163	finally
    //   53	57	170	finally
    //   60	64	170	finally
    //   67	74	170	finally
    //   82	87	170	finally
    //   100	108	170	finally
    //   111	117	170	finally
    //   125	128	170	finally
    //   130	135	174	finally
    //   16	39	185	java/lang/Throwable
    //   39	49	201	java/lang/Throwable
    //   53	57	211	java/lang/Throwable
  }
  
  public static void a(String paramString, boolean paramBoolean, LineNumberReader paramLineNumberReader, BufferedReader paramBufferedReader, SQLiteDatabase paramSQLiteDatabase)
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
              a(paramSQLiteDatabase);
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
  
  private static SQLiteDatabase b()
  {
    return a();
  }
  
  private static b b(Context paramContext)
  {
    try
    {
      if (c == null) {
        c = new b(paramContext, "bizport.db", null, 1);
      }
      paramContext = c;
      return paramContext;
    }
    finally {}
  }
  
  private static void c(SQLiteDatabase paramSQLiteDatabase)
  {
    try
    {
      paramSQLiteDatabase.execSQL("create table  if not exists tb_regex (scene_id TEXT,match_id TEXT,regex_text TEXT,version_code TEXT,regex_type INTEGER  DEFAULT '0',state INTEGER  DEFAULT '0' )");
      paramSQLiteDatabase.execSQL("create index if not exists indx_s_m on tb_regex (scene_id,match_id)");
      return;
    }
    catch (Throwable paramSQLiteDatabase) {}
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.a.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */