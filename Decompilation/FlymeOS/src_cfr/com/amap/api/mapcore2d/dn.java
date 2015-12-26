/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteDatabase
 *  android.database.sqlite.SQLiteDatabase$CursorFactory
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
package com.amap.api.mapcore2d;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amap.api.mapcore2d.dm;
import com.amap.api.mapcore2d.du;
import com.amap.api.mapcore2d.ed;
import java.util.ArrayList;
import java.util.List;

public class dn {
    private dm a;
    private SQLiteDatabase b;

    public dn(Context context) {
        this.a = new dm(context, "logdb.db", null, 1);
    }

    private SQLiteDatabase a() {
        this.b = this.a.getReadableDatabase();
        return this.b;
    }

    private SQLiteDatabase b() {
        this.b = this.a.getWritableDatabase();
        return this.b;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public <T> void a(du<T> var1_1) {
        if (var1_1 == null) {
            return;
        }
        var2_4 = var1_1.b();
        if (var2_4 == null) return;
        if (var1_1.a() == null) return;
        if (this.b == null || this.b.isReadOnly()) {
            this.b = this.b();
        }
        if (this.b == null) return;
        try {
            this.b.insert(var1_1.a(), null, var2_4);
            if (this.b == null) return;
        }
        catch (Throwable var1_2) {
            try {
                ed.a(var1_2, "DataBase", "insertData");
                var1_2.printStackTrace();
                if (this.b == null) return;
            }
            catch (Throwable var1_3) {
                if (this.b == null) throw var1_3;
                this.b.close();
                this.b = null;
                throw var1_3;
            }
            this.b.close();
lbl25: // 2 sources:
            this.b = null;
            return;
        }
        this.b.close();
        ** GOTO lbl25
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public <T> void a(String var1_1, du<T> var2_4) {
        if (var2_4.a() == null) return;
        if (var1_1 == null) {
            return;
        }
        if (this.b == null || this.b.isReadOnly()) {
            this.b = this.b();
        }
        if (this.b == null) return;
        try {
            this.b.delete(var2_4.a(), var1_1, null);
            if (this.b == null) return;
        }
        catch (Throwable var1_2) {
            try {
                ed.a(var1_2, "DataBase", "deleteData");
                var1_2.printStackTrace();
                if (this.b == null) return;
            }
            catch (Throwable var1_3) {
                if (this.b == null) throw var1_3;
                this.b.close();
                this.b = null;
                throw var1_3;
            }
            this.b.close();
lbl23: // 2 sources:
            this.b = null;
            return;
        }
        this.b.close();
        ** GOTO lbl23
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public <T> void b(String var1_1, du<T> var2_4) {
        if (var2_4 == null) return;
        if (var1_1 == null) return;
        if (var2_4.a() == null) {
            return;
        }
        var3_5 = var2_4.b();
        if (var3_5 == null) return;
        if (this.b == null || this.b.isReadOnly()) {
            this.b = this.b();
        }
        if (this.b == null) return;
        try {
            this.b.update(var2_4.a(), var3_5, var1_1, null);
            if (this.b == null) return;
        }
        catch (Throwable var1_2) {
            try {
                ed.a(var1_2, "DataBase", "updateData");
                var1_2.printStackTrace();
                if (this.b == null) return;
            }
            catch (Throwable var1_3) {
                if (this.b == null) throw var1_3;
                this.b.close();
                this.b = null;
                throw var1_3;
            }
            this.b.close();
lbl26: // 2 sources:
            this.b = null;
            return;
        }
        this.b.close();
        ** GOTO lbl26
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public <T> List<T> c(String var1_1, du<T> var2_4) {
        block8 : {
            var3_5 = new ArrayList();
            if (this.b == null) {
                this.b = this.a();
            }
            if (this.b == null) return var3_5;
            if (var2_4.a() == null) return var3_5;
            if (var1_1 == null) {
                return var3_5;
            }
            var1_1 = this.b.query(var2_4.a(), null, var1_1, null, null, null, null);
            if (var1_1 != null) break block8;
            this.b.close();
            this.b = null;
            if (this.b == null) return var3_5;
            this.b.close();
            this.b = null;
            return var3_5;
        }
        while (var1_1.moveToNext()) {
            var3_5.add(var2_4.b((Cursor)var1_1));
        }
        try {
            var1_1.close();
            if (this.b == null) return var3_5;
            ** GOTO lbl30
        }
        catch (Throwable var1_2) {
            if (this.b == null) return var3_5;
            this.b.close();
            ** GOTO lbl31
lbl30: // 1 sources:
            this.b.close();
lbl31: // 2 sources:
            this.b = null;
            return var3_5;
        }
        catch (Throwable var1_3) {
            if (this.b == null) throw var1_3;
            this.b.close();
            this.b = null;
            throw var1_3;
        }
    }
}

