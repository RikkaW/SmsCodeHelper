/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public final class g {
    private static String a = "tb_netquery_time";
    private static String b = " DROP TABLE IF EXISTS tb_netquery_time";
    private static String c = "id";
    private static String d = "phone_num";
    private static String e = "area_code";
    private static String f = "request_time";
    private static String g = "ALTER TABLE tb_netquery_time ADD COLUMN area_code TEXT";

    public static String a() {
        return " create table  if not exists tb_netquery_time ( id INTEGER PRIMARY KEY, phone_num TEXT,area_code TEXT, request_time LONG DEFAULT '0')";
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static List<String> a(long var0) {
        block15 : {
            var4_1 = DBManager.query("tb_netquery_time", new String[]{"phone_num"}, "request_time < ? and request_time > 0 ", new String[]{String.valueOf((long)var0)}, null, null, null, null);
            if (var4_1 != null) break block15;
            XyCursor.closeCursor(var4_1, true);
            return null;
        }
        var3_5 = var4_1;
        try {
            var5_6 = new ArrayList();
        }
        catch (Throwable var5_7) lbl-1000: // 2 sources:
        {
            var3_5 = var4_1;
            var5_8.printStackTrace();
            XyCursor.closeCursor(var4_1, true);
            return null;
        }
        do {
            block16 : {
                var3_5 = var4_1;
                var2_10 = var4_1.moveToNext();
                if (var2_10) break block16;
                XyCursor.closeCursor(var4_1, true);
                return var5_6;
            }
            var3_5 = var4_1;
            var6_11 = new JSONObject();
            var3_5 = var4_1;
            var6_11.put("num", (Object)var4_1.getString(0));
            var3_5 = var4_1;
            var6_11.put("version", (Object)"-1");
            var3_5 = var4_1;
            var5_6.add(var6_11.toString());
            continue;
            break;
        } while (true);
        catch (Throwable var4_2) {
            var3_5 = null;
lbl39: // 2 sources:
            do {
                XyCursor.closeCursor(var3_5, true);
                throw var4_3;
                break;
            } while (true);
        }
        {
            catch (Throwable var4_4) {
                ** continue;
            }
        }
        catch (Throwable var5_9) {
            var4_1 = null;
            ** GOTO lbl-1000
        }
    }

    public static boolean a(String string2, String string3) {
        boolean bl2;
        block4 : {
            bl2 = false;
            if (XyUtil.checkNetWork(Constant.getContext(), 2) != -1) break block4;
            return false;
        }
        try {
            if (g.d(string2, string3) == null) {
                g.c(string2, string3);
                return true;
            }
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            bl2 = true;
        }
        return bl2;
    }

    public static void b(long l2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("request_time", Long.valueOf((long)System.currentTimeMillis()));
            DBManager.update("tb_netquery_time", contentValues, "request_time < ? and request_time > 0 ", new String[]{String.valueOf((long)l2)});
            return;
        }
        catch (Throwable var2_2) {
            var2_2.printStackTrace();
            LogManager.e("NetQueryTimeManager", "clearUpdateUnKnowNumTime error: " + var2_2.getMessage(), var2_2);
            return;
        }
    }

    public static void b(String string2, String string3) {
        String string4;
        try {
            if (StringUtils.isNull(string2)) {
                return;
            }
            string4 = " phone_num = ? ";
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            LogManager.e("NetQueryTimeManager", "delNetQueryNum error: " + var0_1.getMessage(), var0_1);
            return;
        }
        if (!StringUtils.isNull(string3)) {
            string4 = String.valueOf((Object)" phone_num = ? ") + " and area_code = '" + string3 + "'";
        }
        DBManager.delete("tb_netquery_time", string4, new String[]{string2});
    }

    private static long c(String string2, String string3) {
        try {
            ContentValues contentValues = BaseManager.getContentValues(null, "area_code", string3, "phone_num", string2, "request_time", String.valueOf((long)System.currentTimeMillis()));
            if (DBManager.update("tb_netquery_time", contentValues, "phone_num = ? and area_code = ?", new String[]{string2, string3}) <= 0) {
                long l2 = DBManager.insert("tb_netquery_time", contentValues);
                return l2;
            }
            return 0;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return -1;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private static JSONObject d(String var0, String var1_2) {
        var2_5 = null;
        var3_8 = new String[]{"id", "phone_num", "request_time"};
        var0 = DBManager.query("tb_netquery_time", var3_8, "phone_num = ? and area_code = ?", new String[]{var0, var1_2}, null, null, null, "1");
        try {
            var1_2 = BaseManager.loadSingleDataFromCursor(var3_8, (XyCursor)var0);
        }
        catch (Throwable var2_6) {
            var1_2 = var0;
            var0 = var2_6;
            ** GOTO lbl18
        }
        XyCursor.closeCursor((XyCursor)var0, true);
        return var1_2;
        catch (Throwable var1_3) {
            var0 = null;
lbl13: // 3 sources:
            var1_2.printStackTrace();
            XyCursor.closeCursor((XyCursor)var0, true);
            return null;
        }
        catch (Throwable var0_1) {
            var1_2 = var2_5;
lbl18: // 3 sources:
            do {
                XyCursor.closeCursor((XyCursor)var1_2, true);
                throw var0;
                break;
            } while (true);
        }
        {
            catch (Throwable var2_7) {
                var1_2 = var0;
                var0 = var2_7;
                ** continue;
            }
        }
        catch (Throwable var1_4) {
            ** GOTO lbl13
        }
    }
}

