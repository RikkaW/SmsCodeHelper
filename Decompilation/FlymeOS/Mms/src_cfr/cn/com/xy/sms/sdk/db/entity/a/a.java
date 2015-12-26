/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.db.entity.a;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.util.StringUtils;

public final class a {
    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static cn.com.xy.sms.sdk.db.entity.a a(String var0) {
        var7_6 = null;
        var8_8 = null;
        var10_9 = null;
        var9_10 = null;
        var11_11 = StringUtils.getSubString((String)var0);
        var0 = String.valueOf((Object)var11_11);
        var6_12 = DBManager.query("tb_centernum_location_info", new String[]{"cnum", "areaCode", "city", "operator", "checkTime"}, "cnum = ? ", new String[]{var0});
        var0 = var7_6;
        if (var6_12 == null) ** GOTO lbl39
        var0 = var7_6;
        var7_6 = var6_12;
        var8_8 = var10_9;
        if (var6_12.getCount() <= 0) ** GOTO lbl39
        var7_6 = var6_12;
        var8_8 = var10_9;
        var1_13 = var6_12.getColumnIndex("areaCode");
        var7_6 = var6_12;
        var8_8 = var10_9;
        var2_14 = var6_12.getColumnIndex("city");
        var7_6 = var6_12;
        var8_8 = var10_9;
        var3_15 = var6_12.getColumnIndex("operator");
        var7_6 = var6_12;
        var8_8 = var10_9;
        try {
            var4_16 = var6_12.getColumnIndex("checkTime");
            var0 = var9_10;
        }
        catch (Throwable var0_5) {
            ** GOTO lbl64
        }
        do {
            block22 : {
                var7_6 = var6_12;
                var8_8 = var0;
                var5_17 = var6_12.moveToNext();
                if (var5_17) break block22;
lbl39: // 3 sources:
                XyCursor.closeCursor(var6_12, true);
                return var0;
            }
            var7_6 = var6_12;
            var8_8 = var0;
            var0 = new cn.com.xy.sms.sdk.db.entity.a();
            var7_6 = var6_12;
            var0.b = var11_11;
            var7_6 = var6_12;
            var0.c = var6_12.getString(var1_13);
            var7_6 = var6_12;
            var0.e = var6_12.getString(var2_14);
            var7_6 = var6_12;
            var0.f = var6_12.getString(var3_15);
            var7_6 = var6_12;
            var0.g = var6_12.getLong(var4_16);
            continue;
            break;
        } while (true);
        catch (Throwable var0_1) {
            var6_12 = null;
lbl64: // 3 sources:
            var7_6 = var6_12;
            var0.printStackTrace();
            XyCursor.closeCursor(var6_12, true);
            return var8_8;
        }
        catch (Throwable var0_2) {
            var7_6 = null;
lbl71: // 2 sources:
            do {
                XyCursor.closeCursor(var7_6, true);
                throw var0_3;
                break;
            } while (true);
        }
        {
            catch (Throwable var0_4) {
                ** continue;
            }
        }
        catch (Throwable var7_7) {
            var8_8 = var0;
            var0 = var7_7;
            ** GOTO lbl64
        }
    }

    public static void a(cn.com.xy.sms.sdk.db.entity.a a2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("cnum", a2.b);
            if (!StringUtils.isNull(a2.c)) {
                contentValues.put("areaCode", a2.c);
            }
            if (!StringUtils.isNull(a2.e)) {
                contentValues.put("city", a2.e);
            }
            if (!StringUtils.isNull(a2.f)) {
                contentValues.put("operator", a2.f);
            }
            contentValues.put("checkTime", Long.valueOf((long)a2.g));
            if ((long)DBManager.update("tb_centernum_location_info", contentValues, "cnum = ?", new String[]{String.valueOf((Object)a2.b)}) < 1) {
                DBManager.insert("tb_centernum_location_info", contentValues);
            }
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    private static ContentValues b(cn.com.xy.sms.sdk.db.entity.a a2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("cnum", a2.b);
        if (!StringUtils.isNull(a2.c)) {
            contentValues.put("areaCode", a2.c);
        }
        if (!StringUtils.isNull(a2.e)) {
            contentValues.put("city", a2.e);
        }
        if (!StringUtils.isNull(a2.f)) {
            contentValues.put("operator", a2.f);
        }
        contentValues.put("checkTime", Long.valueOf((long)a2.g));
        return contentValues;
    }
}

