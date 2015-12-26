package com.amap.api.services.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ah
  extends SQLiteOpenHelper
{
  static final String a = "a";
  static final String b = "b";
  static final String c = "c";
  static final String d = "d";
  static final String e = "e";
  static final String f = "a1";
  static final String g = "a2";
  static final String h = "a3";
  static final String i = "a4";
  static final String j = "a5";
  static final String k = "a6";
  static final String l = "b1";
  static final String m = "b2";
  static final String n = "b3";
  static final String o = "c1";
  static final String p = "c2";
  static final String q = "c3";
  private static final String r = "CREATE TABLE IF NOT EXISTS " + a + " (_id integer primary key autoincrement, " + f + "  varchar(20), " + g + " varchar(10)," + h + " varchar(50)," + i + " varchar(100)," + j + " varchar(20)," + k + " integer);";
  private static final String s = "CREATE TABLE IF NOT EXISTS %s (_id integer primary key autoincrement," + l + " varchar(40), " + m + " integer," + n + "  integer," + f + "  varchar(20));";
  private static final String t = "CREATE TABLE IF NOT EXISTS " + e + " (_id integer primary key autoincrement," + o + " integer," + p + " integer," + q + " integer);";
  
  public ah(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt)
  {
    super(paramContext, paramString, paramCursorFactory, paramInt);
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    try
    {
      paramSQLiteDatabase.execSQL(r);
      paramSQLiteDatabase.execSQL(String.format(s, new Object[] { b }));
      paramSQLiteDatabase.execSQL(String.format(s, new Object[] { c }));
      paramSQLiteDatabase.execSQL(String.format(s, new Object[] { d }));
      paramSQLiteDatabase.execSQL(t);
      return;
    }
    catch (Throwable paramSQLiteDatabase)
    {
      ay.a(paramSQLiteDatabase, "DB", "onCreate");
      paramSQLiteDatabase.printStackTrace();
    }
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.ah
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */