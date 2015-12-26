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
import com.amap.api.mapcore2d.cz;
import com.amap.api.mapcore2d.dh;
import com.amap.api.mapcore2d.dm;
import com.amap.api.mapcore2d.du;
import com.amap.api.mapcore2d.dy;

public class dt
implements du<dh> {
    private static String a = dm.f;
    private static String b = dm.g;
    private static String c = dm.k;
    private static String d = dm.h;
    private static String e = dm.i;
    private static String f = dm.j;
    private dh g = null;

    public static String a(String string2) {
        return a + "='" + dy.a(string2) + "'";
    }

    private String a(String[] object) {
        if (object == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int n2 = object.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            stringBuilder.append(object[i2]).append(";");
            continue;
        }
        try {
            object = stringBuilder.toString();
            return object;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return null;
        }
    }

    private String[] b(String arrstring) {
        try {
            arrstring = arrstring.split(";");
            return arrstring;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return null;
        }
    }

    public static String c() {
        return c + "=1";
    }

    public dh a(Cursor object) {
        String string2;
        boolean bl2;
        String string3;
        String string4;
        String[] arrstring;
        String string5;
        block4 : {
            bl2 = true;
            string3 = dy.b(object.getString(1));
            string5 = dy.b(object.getString(2));
            string4 = dy.b(object.getString(3));
            arrstring = this.b(dy.b(object.getString(4)));
            string2 = dy.b(object.getString(5));
            if (object.getInt(6) != 0) break block4;
            bl2 = false;
        }
        try {
            object = new dh.a(string3, string5, string4).a(bl2).a(string2).a(arrstring).a();
            return object;
        }
        catch (cz var1_2) {
            var1_2.printStackTrace();
            return null;
        }
        catch (Throwable var1_3) {
            var1_3.printStackTrace();
            return null;
        }
    }

    @Override
    public String a() {
        return dm.a;
    }

    @Override
    public void a(dh dh2) {
        this.g = dh2;
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
            if (this.g == null) {
                return null;
            }
            contentValues = new ContentValues();
        }
        catch (Throwable var2_3) {}
        try {
            contentValues.put(a, dy.a(this.g.a()));
            contentValues.put(b, dy.a(this.g.b()));
            contentValues.put(c, Boolean.valueOf((boolean)this.g.e()));
            contentValues.put(d, dy.a(this.g.c()));
            contentValues.put(f, dy.a(this.g.d()));
            contentValues.put(e, dy.a(this.a(this.g.f())));
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

