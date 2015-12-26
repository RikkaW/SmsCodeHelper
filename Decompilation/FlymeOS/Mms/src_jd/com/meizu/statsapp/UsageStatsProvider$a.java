package com.meizu.statsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class UsageStatsProvider$a
  extends SQLiteOpenHelper
{
  public UsageStatsProvider$a(Context paramContext)
  {
    super(paramContext, "UsageStats.db", null, 2);
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

/* Location:
 * Qualified Name:     com.meizu.statsapp.UsageStatsProvider.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */