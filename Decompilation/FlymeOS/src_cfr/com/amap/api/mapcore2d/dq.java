/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.database.Cursor
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.amap.api.mapcore2d;

import android.content.ContentValues;
import android.database.Cursor;
import com.amap.api.mapcore2d.dm;
import com.amap.api.mapcore2d.dr;
import com.amap.api.mapcore2d.du;
import com.amap.api.mapcore2d.dy;

public abstract class dq
implements du<dr> {
    private static final String a = dm.l;
    private static final String b = dm.m;
    private static final String c = dm.n;
    private static final String d = dm.f;
    private dr e = null;

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(int n2) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(b).append("=").append(n2);
        }
        catch (Throwable var2_2) {
            var2_2.printStackTrace();
            return stringBuilder.toString();
        }
        do {
            return stringBuilder.toString();
            break;
        } while (true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(String string2) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(a).append("='").append(string2).append("'");
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return stringBuilder.toString();
        }
        do {
            return stringBuilder.toString();
            break;
        } while (true);
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public dr a(Cursor object) {
        String string2;
        String string3;
        int n2;
        int n3;
        Object var5_2 = null;
        if (object == null) {
            return null;
        }
        try {
            string3 = object.getString(1);
            n3 = object.getInt(2);
            string2 = object.getString(4);
            n2 = object.getInt(3);
            object = new dr();
        }
        catch (Throwable var4_4) {
            void var4_5;
            object = var5_2;
            var4_5.printStackTrace();
            return object;
        }
        object.a(string3);
        object.a(n3);
        object.b(dy.b(string2));
        object.b(n2);
        return object;
        {
            catch (Throwable throwable) {}
        }
    }

    @Override
    public void a(dr dr2) {
        this.e = dr2;
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
            if (this.e == null) {
                return null;
            }
            contentValues = new ContentValues();
        }
        catch (Throwable var2_3) {}
        try {
            contentValues.put(a, this.e.b());
            contentValues.put(b, Integer.valueOf((int)this.e.a()));
            contentValues.put(d, dy.a(this.e.c()));
            contentValues.put(c, Integer.valueOf((int)this.e.d()));
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

