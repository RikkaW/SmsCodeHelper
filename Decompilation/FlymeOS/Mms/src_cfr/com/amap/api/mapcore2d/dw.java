/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.database.Cursor
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.amap.api.mapcore2d;

import android.content.ContentValues;
import android.database.Cursor;
import com.amap.api.mapcore2d.dm;
import com.amap.api.mapcore2d.du;
import com.amap.api.mapcore2d.dx;

public class dw
implements du<dx> {
    private static final String b = dm.o;
    private static final String c = dm.p;
    private static final String d = dm.q;
    private dx a = null;

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public dx a(Cursor var1_1) {
        var7_2 = true;
        try {
            var2_3 = var1_1.getInt(1);
            var3_4 = var1_1.getInt(2);
            var4_5 = var1_1.getInt(3);
            var5_6 = var2_3 != 0;
            ** GOTO lbl11
        }
        catch (Throwable var8_8) {
            var1_1 = null;
            ** GOTO lbl21
lbl11: // 1 sources:
            var6_7 = var3_4 != 0;
            if (var4_5 == 0) {
                var7_2 = false;
            }
            var1_1 = new dx();
            try {
                var1_1.a(var5_6);
                var1_1.c(var7_2);
                var1_1.b(var6_7);
                return var1_1;
            }
            catch (Throwable var8_10) {}
lbl21: // 2 sources:
            var8_9.printStackTrace();
            return var1_1;
        }
    }

    @Override
    public String a() {
        return dm.e;
    }

    @Override
    public void a(dx dx2) {
        this.a = dx2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public ContentValues b() {
        ContentValues contentValues;
        ContentValues contentValues2 = null;
        try {
            if (this.a == null) {
                return null;
            }
            contentValues = new ContentValues();
        }
        catch (Throwable var2_3) {}
        try {
            contentValues.put(b, Boolean.valueOf((boolean)this.a.a()));
            contentValues.put(c, Boolean.valueOf((boolean)this.a.b()));
            contentValues.put(d, Boolean.valueOf((boolean)this.a.c()));
            return contentValues;
        }
        catch (Throwable var3_4) {
            contentValues2 = contentValues;
            contentValues = var3_4;
        }
        {
            contentValues.printStackTrace();
            return contentValues2;
        }
    }

    @Override
    public /* synthetic */ Object b(Cursor cursor) {
        return this.a(cursor);
    }
}

