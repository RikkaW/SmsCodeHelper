/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashMap
 */
package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.entity.i;
import cn.com.xy.sms.sdk.util.DateUtils;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class j {
    private static String a = "scene_id";
    private static String b = "date";
    private static String c = "parse_times";
    private static String d = "popup_times";
    private static String e = "tb_popup_action_scene";
    private static String f = " DROP TABLE IF EXISTS tb_popup_action_scene";
    private static String g = "create table  if not exists tb_popup_action_scene (scene_id TEXT, date TEXT, parse_times INTEGER DEFAULT '0', popup_times INTEGER DEFAULT '0' ) ";

    public static long a(HashMap<String, String> object) {
        long l2;
        String string2;
        long l3;
        block12 : {
            Object var5_2 = null;
            string2 = (String)object.get((Object)"titleNo");
            l2 = -1;
            object = var5_2;
            l3 = l2;
            try {
                if (StringUtils.isNull(string2)) break block12;
                l3 = l2;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return l3;
            }
            object = j.d(string2);
        }
        if (object != null) {
            l3 = l2;
            ++object.c;
            l3 = l2;
            DBManager.update("tb_popup_action_scene", j.a((i)object), "scene_id = ? and date = ? ", new String[]{object.a, object.b});
            return 0;
        }
        l3 = l2;
        object = new i();
        l3 = l2;
        object.a = string2;
        l3 = l2;
        object.b = DateUtils.getCurrentTimeString("yyyyMMdd");
        l3 = l2;
        object.c = 1;
        l3 = l2;
        l3 = l2 = DBManager.insert("tb_popup_action_scene", j.a((i)object));
        DuoquUtils.getSdkDoAction().statisticAction(string2, -2, null);
        return l2;
    }

    private static ContentValues a(i i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("scene_id", i2.a);
        contentValues.put("date", i2.b);
        contentValues.put("parse_times", Integer.valueOf((int)i2.c));
        contentValues.put("popup_times", Integer.valueOf((int)i2.d));
        return contentValues;
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static List<i> a(String var0) {
        block16 : {
            var5_1 = new ArrayList();
            var3_2 = DBManager.query("tb_popup_action_scene", new String[]{"date"}, "date < ? ", new String[]{var0}, "date", null, null, null);
            if (var3_2 == null) break block16;
            var0 = var3_2;
            if (var3_2.getCount() <= 0) break block16;
            var0 = var3_2;
            try {
                var1_6 = var3_2.getColumnIndex("date");
            }
            catch (Throwable var4_9) lbl-1000: // 2 sources:
            {
                var0 = var3_2;
                var4_10.printStackTrace();
                XyCursor.closeCursor(var3_2, true);
                do {
                    return var5_1;
                    break;
                } while (true);
            }
            do {
                block17 : {
                    var0 = var3_2;
                    var2_7 = var3_2.moveToNext();
                    if (var2_7) break block17;
                    XyCursor.closeCursor(var3_2, true);
                    return var5_1;
                }
                var0 = var3_2;
                var4_8 = new i();
                var0 = var3_2;
                var4_8.b = var3_2.getString(var1_6);
                var0 = var3_2;
                var5_1.add(var4_8);
                continue;
                break;
            } while (true);
            catch (Throwable var3_3) {
                var0 = null;
lbl38: // 2 sources:
                do {
                    XyCursor.closeCursor((XyCursor)var0, true);
                    throw var3_4;
                    break;
                } while (true);
            }
        }
        XyCursor.closeCursor(var3_2, true);
        return var5_1;
        {
            catch (Throwable var3_5) {
                ** continue;
            }
        }
        catch (Throwable var4_11) {
            var3_2 = null;
            ** GOTO lbl-1000
        }
    }

    private static void a() {
        try {
            DBManager.delete("tb_popup_action_scene", null, null);
            return;
        }
        catch (Throwable var0) {
            var0.printStackTrace();
            return;
        }
    }

    public static long b(HashMap<String, String> object) {
        String string2;
        block5 : {
            string2 = (String)object.get((Object)"titleNo");
            object = null;
            try {
                if (!StringUtils.isNull(string2)) {
                    object = j.d(string2);
                }
                if (object == null) break block5;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return -1;
            }
            ++object.d;
            DBManager.update("tb_popup_action_scene", j.a((i)object), "scene_id = ? and date = ? ", new String[]{object.a, object.b});
            return 0;
        }
        object = new i();
        object.a = string2;
        object.b = DateUtils.getCurrentTimeString("yyyyMMdd");
        object.d = 1;
        long l2 = DBManager.insert("tb_popup_action_scene", j.a((i)object));
        return l2;
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static List<i> b(String var0) {
        var8_1 = new ArrayList();
        var6_2 = DBManager.query("tb_popup_action_scene", new String[]{"scene_id", "date", "parse_times", "popup_times"}, "date = ? ", new String[]{var0}, null, null, null, null);
        if (var6_2 == null) ** GOTO lbl25
        var0 = var6_2;
        if (var6_2.getCount() <= 0) ** GOTO lbl25
        var0 = var6_2;
        var1_6 = var6_2.getColumnIndex("scene_id");
        var0 = var6_2;
        var2_7 = var6_2.getColumnIndex("date");
        var0 = var6_2;
        var3_8 = var6_2.getColumnIndex("parse_times");
        var0 = var6_2;
        try {
            var4_9 = var6_2.getColumnIndex("popup_times");
        }
        catch (Throwable var7_12) lbl-1000: // 2 sources:
        {
            var0 = var6_2;
            var7_13.printStackTrace();
            XyCursor.closeCursor(var6_2, true);
            return var8_1;
        }
        do {
            block21 : {
                var0 = var6_2;
                var5_10 = var6_2.moveToNext();
                if (var5_10) break block21;
lbl25: // 3 sources:
                XyCursor.closeCursor(var6_2, true);
                return var8_1;
            }
            var0 = var6_2;
            var7_11 = new i();
            var0 = var6_2;
            var7_11.a = var6_2.getString(var1_6);
            var0 = var6_2;
            var7_11.b = var6_2.getString(var2_7);
            var0 = var6_2;
            var7_11.c = var6_2.getInt(var3_8);
            var0 = var6_2;
            var7_11.d = var6_2.getInt(var4_9);
            var0 = var6_2;
            var8_1.add(var7_11);
            continue;
            break;
        } while (true);
        catch (Throwable var6_3) {
            var0 = null;
lbl55: // 2 sources:
            do {
                XyCursor.closeCursor((XyCursor)var0, true);
                throw var6_4;
                break;
            } while (true);
        }
        {
            catch (Throwable var6_5) {
                ** continue;
            }
        }
        catch (Throwable var7_14) {
            var6_2 = null;
            ** GOTO lbl-1000
        }
    }

    public static void c(String string2) {
        try {
            DBManager.delete("tb_popup_action_scene", "date < ?", new String[]{string2});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private static i d(String var0) {
        block19 : {
            block20 : {
                var6_1 = null;
                var7_6 = DateUtils.getCurrentTimeString("yyyyMMdd");
                var0 = DBManager.query("tb_popup_action_scene", new String[]{"scene_id", "date", "parse_times", "popup_times"}, "scene_id = ? and date = ? ", new String[]{var0, var7_6}, null, null, null, "1");
                if (var0 == null) break block19;
                if (var0.getCount() <= 0) break block19;
                var1_12 = var0.getColumnIndex("scene_id");
                var2_13 = var0.getColumnIndex("date");
                var3_14 = var0.getColumnIndex("parse_times");
                var4_15 = var0.getColumnIndex("popup_times");
                var6_1 = null;
lbl15: // 3 sources:
                var5_16 = var0.moveToNext();
                if (var5_16) break block20;
                XyCursor.closeCursor((XyCursor)var0, true);
                return var6_1;
            }
            var8_17 = new i();
            var8_17.a = var0.getString(var1_12);
            var8_17.b = var0.getString(var2_13);
            var8_17.c = var0.getInt(var3_14);
            var8_17.d = var0.getInt(var4_15);
            return var8_17.d;
            catch (Throwable var7_7) {
                var8_18 = null;
                var0 = var6_1;
                var6_1 = var8_18;
lbl34: // 4 sources:
                var7_8.printStackTrace();
                XyCursor.closeCursor((XyCursor)var0, true);
                return var6_1;
            }
            catch (Throwable var6_2) {
                var0 = null;
lbl39: // 3 sources:
                do {
                    XyCursor.closeCursor((XyCursor)var0, true);
                    throw var6_3;
                    break;
                } while (true);
            }
        }
        XyCursor.closeCursor((XyCursor)var0, true);
        return null;
        catch (Throwable var6_4) {
            ** GOTO lbl39
        }
        {
            catch (Throwable var6_5) {
                ** continue;
            }
        }
        {
            catch (Throwable var7_9) {
                var6_1 = null;
                ** GOTO lbl34
            }
        }
        {
            finally {
                var6_1 = var8_17;
                ** GOTO lbl15
            }
            {
                catch (Throwable var7_11) {
                    ** GOTO lbl34
                }
            }
        }
    }
}

