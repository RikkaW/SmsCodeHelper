package com.meizu.statsapp;

import ajk;
import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import java.util.ArrayList;

public class UsageStatsProvider
  extends ContentProvider
{
  private static final UriMatcher a = new UriMatcher(-1);
  private a b;
  private Context c;
  private boolean d;
  private int e;
  
  static
  {
    a.addURI("com.meizu.usagestats", "event", 1);
    a.addURI("com.meizu.usagestats", "clear_events", 2);
  }
  
  public UsageStatsProvider()
  {
    e = ajk.e;
  }
  
  public UsageStatsProvider(Context paramContext)
  {
    d = true;
    e = ajk.d;
    c = paramContext;
  }
  
  private int a()
  {
    Object localObject3 = null;
    Object localObject1 = null;
    int k = 0;
    int j = 0;
    Object localObject4 = b.getReadableDatabase();
    if (localObject4 == null) {}
    for (;;)
    {
      return j;
      try
      {
        localObject4 = ((SQLiteDatabase)localObject4).rawQuery("select count(*) from event", null);
        int i = k;
        if (localObject4 != null)
        {
          i = k;
          localObject1 = localObject4;
          localObject3 = localObject4;
          if (((Cursor)localObject4).moveToNext())
          {
            localObject1 = localObject4;
            localObject3 = localObject4;
            i = ((Cursor)localObject4).getInt(0);
          }
        }
        j = i;
        return i;
      }
      catch (Exception localException)
      {
        localObject3 = localObject1;
        localException.printStackTrace();
        return 0;
      }
      finally
      {
        if (localObject3 != null) {
          ((Cursor)localObject3).close();
        }
      }
    }
  }
  
  private void b()
  {
    SQLiteDatabase localSQLiteDatabase = b.getWritableDatabase();
    if (localSQLiteDatabase == null) {
      return;
    }
    if (a() > e) {
      localSQLiteDatabase.execSQL("delete from event where _id not in ( select _id from event order by time desc limit " + e / 2 + ")");
    }
    localSQLiteDatabase.delete("event", "time < ?", new String[] { String.valueOf(System.currentTimeMillis() - 604800000L) });
  }
  
  public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> paramArrayList)
  {
    int j = paramArrayList.size();
    ContentProviderResult[] arrayOfContentProviderResult = new ContentProviderResult[j];
    localSQLiteDatabase = b.getWritableDatabase();
    if (localSQLiteDatabase == null) {
      return null;
    }
    try
    {
      localSQLiteDatabase.beginTransaction();
      int i = 0;
      while (i < j)
      {
        arrayOfContentProviderResult[i] = ((ContentProviderOperation)paramArrayList.get(i)).apply(this, arrayOfContentProviderResult, i);
        i += 1;
      }
      localSQLiteDatabase.setTransactionSuccessful();
    }
    catch (Exception paramArrayList)
    {
      for (;;)
      {
        paramArrayList.printStackTrace();
        localSQLiteDatabase.endTransaction();
      }
    }
    finally
    {
      localSQLiteDatabase.endTransaction();
    }
    return arrayOfContentProviderResult;
  }
  
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    int i = 0;
    int j = 0;
    int k = a.match(paramUri);
    if (k < 1) {}
    SQLiteDatabase localSQLiteDatabase;
    do
    {
      return j;
      localSQLiteDatabase = b.getWritableDatabase();
    } while (localSQLiteDatabase == null);
    switch (k)
    {
    }
    for (;;)
    {
      j = i;
      if (i <= 0) {
        break;
      }
      c.getContentResolver().notifyChange(paramUri, null);
      return i;
      i = localSQLiteDatabase.delete("event", paramString, paramArrayOfString);
      continue;
      b();
    }
  }
  
  public String getType(Uri paramUri)
  {
    int i = a.match(paramUri);
    if (i < 1) {
      return null;
    }
    switch (i)
    {
    default: 
      throw new IllegalStateException("Unknown URL : " + paramUri);
    }
    return "vnd.android.cursor.dir/event";
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    int i = a.match(paramUri);
    if (i < 1) {}
    SQLiteDatabase localSQLiteDatabase;
    do
    {
      return null;
      localSQLiteDatabase = b.getWritableDatabase();
    } while (localSQLiteDatabase == null);
    switch (i)
    {
    }
    long l;
    for (paramContentValues = null;; paramContentValues = Uri.parse("content://com.meizu.usagestats/event/" + String.valueOf(l)))
    {
      if (paramContentValues != null) {
        c.getContentResolver().notifyChange(paramUri, null);
      }
      return paramContentValues;
      l = localSQLiteDatabase.insert("event", null, paramContentValues);
      if (l <= 0L) {
        break;
      }
    }
  }
  
  public boolean onCreate()
  {
    if (!d) {
      c = getContext();
    }
    b = new a(c);
    return true;
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    int i = a.match(paramUri);
    if (i < 1) {}
    SQLiteDatabase localSQLiteDatabase;
    do
    {
      return null;
      localSQLiteDatabase = b.getReadableDatabase();
    } while (localSQLiteDatabase == null);
    SQLiteQueryBuilder localSQLiteQueryBuilder = new SQLiteQueryBuilder();
    String str = paramUri.getQueryParameter("limit");
    if (paramUri.getQueryParameter("distinct") != null) {
      localSQLiteQueryBuilder.setDistinct(true);
    }
    switch (i)
    {
    default: 
      throw new UnsupportedOperationException("Invalid URI " + paramUri);
    }
    localSQLiteQueryBuilder.setTables("event");
    return localSQLiteQueryBuilder.query(localSQLiteDatabase, paramArrayOfString1, paramString1, paramArrayOfString2, null, null, paramString2, str);
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    int i = 0;
    int j = 0;
    int k = a.match(paramUri);
    if (k < 1) {}
    SQLiteDatabase localSQLiteDatabase;
    do
    {
      return j;
      localSQLiteDatabase = b.getWritableDatabase();
    } while (localSQLiteDatabase == null);
    switch (k)
    {
    }
    for (;;)
    {
      j = i;
      if (i <= 0) {
        break;
      }
      c.getContentResolver().notifyChange(paramUri, null);
      return i;
      i = localSQLiteDatabase.update("event", paramContentValues, paramString, paramArrayOfString);
    }
  }
  
  static class a
    extends SQLiteOpenHelper
  {
    public a(Context paramContext)
    {
      super("UsageStats.db", null, 2);
    }
    
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS event (_id INTEGER DEFAULT '1' NOT NULL PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,type INTEGER NOT NULL,package TEXT NOT NULL,sessionid TEXT NOT NULL,time LONG,page TEXT,properties TEXT,network TEXT,channel LONG,flyme_version TEXT);");
    }
    
    public void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS event");
      onCreate(paramSQLiteDatabase);
    }
    
    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      if (paramInt1 <= 1)
      {
        paramSQLiteDatabase.beginTransaction();
        paramSQLiteDatabase.execSQL("ALTER TABLE event RENAME TO temp_event");
        paramSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS event (_id INTEGER DEFAULT '1' NOT NULL PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,type INTEGER NOT NULL,package TEXT NOT NULL,sessionid TEXT NOT NULL,time LONG,page TEXT,properties TEXT,network TEXT,channel LONG,flyme_version TEXT);");
        paramSQLiteDatabase.execSQL("INSERT INTO event(_id,name,type,package,sessionid,time,page,properties)  SELECT *  FROM temp_event");
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS temp_event");
        paramSQLiteDatabase.setTransactionSuccessful();
        paramSQLiteDatabase.endTransaction();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.statsapp.UsageStatsProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */