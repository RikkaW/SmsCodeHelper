package com.android.mms.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import com.android.mms.MmsApp;
import java.io.File;

public class FestivalDatabase
{
  private static String CATEGORY_TABLE_CREATE = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s INTEGER, %s INTEGER, %s TEXT, %s STRING, %s STRING, %s TEXT, %s INTEGER)", new Object[] { "categories", "_id", "category_id", "row", "title", "image_id", "desc_image_id", "image_text", "sms_count" });
  private static String DATA_TABLE_CREATE = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s INTEGER, %s STRING, %s BLOB)", new Object[] { "data", "_id", "type", "url", "data" });
  private static String EASTER_EGG_TABLE_CREATE;
  private static String MESSAGE_TABLE_CREATE = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s INTEGER, %s INTEGER, %s TEXT)", new Object[] { "messages", "_id", "message_id", "category_id", "text" });
  private static volatile FestivalDatabase sInstance = null;
  private Context mContext;
  private SQLiteDatabase mDatabase;
  
  static
  {
    EASTER_EGG_TABLE_CREATE = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s INTEGER, %s INTEGER, %s TEXT, %s STRING)", new Object[] { "easterEggs", "_id", "begin_date", "end_date", "key_words", "animation_id" });
  }
  
  private FestivalDatabase(Context paramContext)
  {
    mContext = paramContext;
  }
  
  /* Error */
  private boolean checkTable(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 94	com/android/mms/data/FestivalDatabase:mDatabase	Landroid/database/sqlite/SQLiteDatabase;
    //   4: ldc 96
    //   6: iconst_1
    //   7: anewarray 39	java/lang/String
    //   10: dup
    //   11: iconst_0
    //   12: ldc 98
    //   14: aastore
    //   15: ldc 100
    //   17: iconst_2
    //   18: anewarray 39	java/lang/String
    //   21: dup
    //   22: iconst_0
    //   23: ldc 102
    //   25: aastore
    //   26: dup
    //   27: iconst_1
    //   28: aload_1
    //   29: aastore
    //   30: aconst_null
    //   31: aconst_null
    //   32: aconst_null
    //   33: invokevirtual 108	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   36: astore_1
    //   37: aload_1
    //   38: ifnull +41 -> 79
    //   41: aload_1
    //   42: invokeinterface 114 1 0
    //   47: ifeq +26 -> 73
    //   50: aload_2
    //   51: aload_1
    //   52: iconst_0
    //   53: invokeinterface 118 2 0
    //   58: invokevirtual 122	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   61: istore_3
    //   62: aload_1
    //   63: invokeinterface 125 1 0
    //   68: iload_3
    //   69: ireturn
    //   70: astore_1
    //   71: iconst_0
    //   72: ireturn
    //   73: aload_1
    //   74: invokeinterface 125 1 0
    //   79: iconst_0
    //   80: ireturn
    //   81: astore_2
    //   82: aload_1
    //   83: invokeinterface 125 1 0
    //   88: aload_2
    //   89: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	90	0	this	FestivalDatabase
    //   0	90	1	paramString1	String
    //   0	90	2	paramString2	String
    //   61	8	3	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   0	37	70	android/database/sqlite/SQLiteException
    //   41	62	81	finally
  }
  
  private boolean checkTables()
  {
    return (checkTable("categories", CATEGORY_TABLE_CREATE)) && (checkTable("messages", MESSAGE_TABLE_CREATE)) && (checkTable("easterEggs", EASTER_EGG_TABLE_CREATE)) && (checkTable("data", DATA_TABLE_CREATE));
  }
  
  /* Error */
  private boolean copyRawToFile(int paramInt, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 4
    //   6: aconst_null
    //   7: astore 7
    //   9: aconst_null
    //   10: astore 6
    //   12: aload_0
    //   13: getfield 88	com/android/mms/data/FestivalDatabase:mContext	Landroid/content/Context;
    //   16: invokevirtual 138	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   19: iload_1
    //   20: invokevirtual 144	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   23: astore_3
    //   24: aload_3
    //   25: astore 4
    //   27: aload_3
    //   28: astore 5
    //   30: new 146	java/io/FileOutputStream
    //   33: dup
    //   34: aload_2
    //   35: invokespecial 149	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   38: astore_2
    //   39: sipush 4096
    //   42: newarray <illegal type>
    //   44: astore 4
    //   46: aload_3
    //   47: aload 4
    //   49: invokevirtual 155	java/io/InputStream:read	([B)I
    //   52: istore_1
    //   53: iload_1
    //   54: iconst_m1
    //   55: if_icmpeq +26 -> 81
    //   58: aload_2
    //   59: aload 4
    //   61: iconst_0
    //   62: iload_1
    //   63: invokevirtual 161	java/io/OutputStream:write	([BII)V
    //   66: goto -20 -> 46
    //   69: astore 4
    //   71: aload_3
    //   72: invokestatic 167	miui/util/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   75: aload_2
    //   76: invokestatic 170	miui/util/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   79: iconst_0
    //   80: ireturn
    //   81: aload_3
    //   82: invokestatic 167	miui/util/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   85: aload_2
    //   86: invokestatic 170	miui/util/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   89: iconst_1
    //   90: ireturn
    //   91: astore 5
    //   93: aload 7
    //   95: astore_2
    //   96: aload 4
    //   98: astore_3
    //   99: aload_3
    //   100: invokestatic 167	miui/util/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   103: aload_2
    //   104: invokestatic 170	miui/util/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   107: aload 5
    //   109: athrow
    //   110: astore 5
    //   112: goto -13 -> 99
    //   115: astore_2
    //   116: aload 5
    //   118: astore_3
    //   119: aload 6
    //   121: astore_2
    //   122: goto -51 -> 71
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	125	0	this	FestivalDatabase
    //   0	125	1	paramInt	int
    //   0	125	2	paramString	String
    //   23	96	3	localObject1	Object
    //   4	56	4	localObject2	Object
    //   69	28	4	localIOException	java.io.IOException
    //   1	28	5	localObject3	Object
    //   91	17	5	localObject4	Object
    //   110	7	5	localObject5	Object
    //   10	110	6	localObject6	Object
    //   7	87	7	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   39	46	69	java/io/IOException
    //   46	53	69	java/io/IOException
    //   58	66	69	java/io/IOException
    //   12	24	91	finally
    //   30	39	91	finally
    //   39	46	110	finally
    //   46	53	110	finally
    //   58	66	110	finally
    //   12	24	115	java/io/IOException
    //   30	39	115	java/io/IOException
  }
  
  /* Error */
  private boolean ensureOpen()
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_1
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield 94	com/android/mms/data/FestivalDatabase:mDatabase	Landroid/database/sqlite/SQLiteDatabase;
    //   8: ifnull +19 -> 27
    //   11: aload_0
    //   12: getfield 94	com/android/mms/data/FestivalDatabase:mDatabase	Landroid/database/sqlite/SQLiteDatabase;
    //   15: invokevirtual 174	android/database/sqlite/SQLiteDatabase:isOpen	()Z
    //   18: istore_2
    //   19: iload_2
    //   20: ifeq +7 -> 27
    //   23: aload_0
    //   24: monitorexit
    //   25: iload_1
    //   26: ireturn
    //   27: aload_0
    //   28: invokevirtual 175	com/android/mms/data/FestivalDatabase:close	()V
    //   31: new 177	java/io/File
    //   34: dup
    //   35: aload_0
    //   36: invokespecial 181	com/android/mms/data/FestivalDatabase:getDatabaseFullPath	()Ljava/lang/String;
    //   39: invokespecial 182	java/io/File:<init>	(Ljava/lang/String;)V
    //   42: invokevirtual 185	java/io/File:exists	()Z
    //   45: ifne +19 -> 64
    //   48: ldc -69
    //   50: ldc -67
    //   52: invokestatic 195	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   55: pop
    //   56: aload_0
    //   57: invokevirtual 198	com/android/mms/data/FestivalDatabase:init	()Z
    //   60: istore_1
    //   61: goto -38 -> 23
    //   64: aload_0
    //   65: aload_0
    //   66: getfield 88	com/android/mms/data/FestivalDatabase:mContext	Landroid/content/Context;
    //   69: aload_0
    //   70: invokespecial 181	com/android/mms/data/FestivalDatabase:getDatabaseFullPath	()Ljava/lang/String;
    //   73: iconst_0
    //   74: aconst_null
    //   75: invokevirtual 202	android/content/Context:openOrCreateDatabase	(Ljava/lang/String;ILandroid/database/sqlite/SQLiteDatabase$CursorFactory;)Landroid/database/sqlite/SQLiteDatabase;
    //   78: putfield 94	com/android/mms/data/FestivalDatabase:mDatabase	Landroid/database/sqlite/SQLiteDatabase;
    //   81: aload_0
    //   82: getfield 94	com/android/mms/data/FestivalDatabase:mDatabase	Landroid/database/sqlite/SQLiteDatabase;
    //   85: ifnull +10 -> 95
    //   88: aload_0
    //   89: invokespecial 204	com/android/mms/data/FestivalDatabase:checkTables	()Z
    //   92: ifne -69 -> 23
    //   95: ldc -69
    //   97: ldc -50
    //   99: invokestatic 195	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   102: pop
    //   103: aload_0
    //   104: invokevirtual 198	com/android/mms/data/FestivalDatabase:init	()Z
    //   107: istore_1
    //   108: goto -85 -> 23
    //   111: astore_3
    //   112: aload_0
    //   113: invokevirtual 198	com/android/mms/data/FestivalDatabase:init	()Z
    //   116: istore_1
    //   117: goto -94 -> 23
    //   120: astore_3
    //   121: aload_0
    //   122: monitorexit
    //   123: aload_3
    //   124: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	125	0	this	FestivalDatabase
    //   1	116	1	bool1	boolean
    //   18	2	2	bool2	boolean
    //   111	1	3	localSQLiteException	SQLiteException
    //   120	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   64	81	111	android/database/sqlite/SQLiteException
    //   4	19	120	finally
    //   27	61	120	finally
    //   64	81	120	finally
    //   81	88	120	finally
    //   88	95	120	finally
    //   95	108	120	finally
    //   112	117	120	finally
  }
  
  private String getDatabaseFullPath()
  {
    return mContext.getFilesDir().getAbsolutePath() + "/" + "festival_sms.db";
  }
  
  public static FestivalDatabase getInstance()
  {
    try
    {
      if (sInstance == null) {
        sInstance = new FestivalDatabase(MmsApp.getApp());
      }
      FestivalDatabase localFestivalDatabase = sInstance;
      return localFestivalDatabase;
    }
    finally {}
  }
  
  public void close()
  {
    if (mDatabase != null)
    {
      mDatabase.close();
      mDatabase = null;
    }
  }
  
  public boolean getMoreMessages(long paramLong)
  {
    if (!ensureOpen()) {
      return false;
    }
    try
    {
      new FestivalUpdater(mContext, mDatabase).getMoreMessages(paramLong);
      return true;
    }
    catch (Exception localException)
    {
      Log.e("FestivalSmsDatabase", "getMoreMessages error", localException);
    }
    return false;
  }
  
  public boolean init()
  {
    boolean bool = false;
    try
    {
      close();
      Log.d("FestivalSmsDatabase", "Use init database. Copy raw file.");
      copyRawToFile(2131165184, getDatabaseFullPath());
      try
      {
        mDatabase = mContext.openOrCreateDatabase(getDatabaseFullPath(), 0, null);
        SQLiteDatabase localSQLiteDatabase = mDatabase;
        if (localSQLiteDatabase != null) {
          bool = true;
        }
      }
      catch (SQLiteException localSQLiteException)
      {
        for (;;)
        {
          Log.e("FestivalSmsDatabase", "Cannot open new database");
        }
      }
      return bool;
    }
    finally {}
  }
  
  public Cursor query(String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, String paramString3, String paramString4, String paramString5)
  {
    if (!ensureOpen()) {
      return null;
    }
    try
    {
      paramString1 = mDatabase.query(paramString1, paramArrayOfString1, paramString2, paramArrayOfString2, paramString3, paramString4, paramString5);
      return paramString1;
    }
    catch (SQLiteException paramString1) {}
    return null;
  }
  
  public boolean updateMessages()
  {
    if (!ensureOpen()) {
      return false;
    }
    try
    {
      new FestivalUpdater(mContext, mDatabase).updateMessages();
      return true;
    }
    catch (Exception localException)
    {
      Log.e("FestivalSmsDatabase", "update error", localException);
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.FestivalDatabase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */