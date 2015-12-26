/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.SQLException
 *  android.database.sqlite.SQLiteDatabase
 *  android.database.sqlite.SQLiteDatabase$CursorFactory
 *  android.database.sqlite.SQLiteOpenHelper
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.xiaomi.push.providers;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.xiaomi.channel.commonutils.logger.MyLog;

public class TrafficDatabaseHelper
extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    public static final Object DataBaseLock = new Object();
    private static final String[] TRAFFIC_Columns = new String[]{"package_name", "TEXT", "message_ts", " LONG DEFAULT 0 ", "bytes", " LONG DEFAULT 0 ", "network_type", " INT DEFAULT -1 ", "rcv", " INT DEFAULT -1 ", "imsi", "TEXT"};

    public TrafficDatabaseHelper(Context context) {
        super(context, "traffic.db", null, DATABASE_VERSION);
    }

    private void createTrafficTable(SQLiteDatabase sQLiteDatabase) {
        StringBuilder stringBuilder = new StringBuilder("CREATE TABLE traffic(_id INTEGER  PRIMARY KEY ,");
        for (int i = 0; i < TRAFFIC_Columns.length - 1; i += 2) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(TRAFFIC_Columns[i]).append(" ").append(TRAFFIC_Columns[i + 1]);
        }
        stringBuilder.append(");");
        sQLiteDatabase.execSQL(stringBuilder.toString());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Object object = DataBaseLock;
        synchronized (object) {
            try {
                this.createTrafficTable(sQLiteDatabase);
            }
            catch (SQLException var1_2) {
                MyLog.e((Throwable)var1_2);
            }
            return;
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int n, int n2) {
    }
}

