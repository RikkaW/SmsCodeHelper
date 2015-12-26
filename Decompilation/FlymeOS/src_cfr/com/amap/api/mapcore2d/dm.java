/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.sqlite.SQLiteDatabase
 *  android.database.sqlite.SQLiteDatabase$CursorFactory
 *  android.database.sqlite.SQLiteOpenHelper
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.amap.api.mapcore2d.ed;

public class dm
extends SQLiteOpenHelper {
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

    public dm(Context context, String string2, SQLiteDatabase.CursorFactory cursorFactory, int n2) {
        super(context, string2, cursorFactory, n2);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(r);
            sQLiteDatabase.execSQL(String.format((String)s, (Object[])new Object[]{b}));
            sQLiteDatabase.execSQL(String.format((String)s, (Object[])new Object[]{c}));
            sQLiteDatabase.execSQL(String.format((String)s, (Object[])new Object[]{d}));
            sQLiteDatabase.execSQL(t);
            return;
        }
        catch (Throwable var1_2) {
            ed.a(var1_2, "DB", "onCreate");
            var1_2.printStackTrace();
            return;
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int n2, int n3) {
    }
}

