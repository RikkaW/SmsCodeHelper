/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.sqlite.SQLiteDatabase
 *  android.database.sqlite.SQLiteDatabase$CursorFactory
 *  android.database.sqlite.SQLiteOpenHelper
 *  java.lang.String
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.db.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import cn.com.xy.sms.sdk.db.a.a;
import cn.com.xy.sms.sdk.log.LogManager;

final class b
extends SQLiteOpenHelper {
    public b(Context context, String string2, SQLiteDatabase.CursorFactory cursorFactory, int n2) {
        super(context, string2, null, 1);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            a.b(sQLiteDatabase);
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int n2, int n3) {
        try {
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_regex");
        }
        catch (Throwable var4_4) {
            LogManager.e("xiaoyaun", "BizportDBManager onDowngrade" + var4_4.getMessage(), var4_4);
        }
        a.b(sQLiteDatabase);
    }

    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int n2, int n3) {
        a.b(sQLiteDatabase);
    }
}

