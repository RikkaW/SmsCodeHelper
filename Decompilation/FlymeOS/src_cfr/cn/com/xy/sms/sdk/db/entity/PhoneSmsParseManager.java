/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class PhoneSmsParseManager {
    public static final String DROP_TABLE = " DROP TABLE IF EXISTS tb_phone_bubble_cache";
    public static final String TABLE_NAME = "tb_phone_bubble_cache";

    public static void addInsertQueue(String string2, long l2, String string3, String string4, String string5) {
        PhoneSmsParseManager.saveOrUpdateObject(string2, l2, string3, string4, string5);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static JSONObject findObjectByPhone(String var0) {
        var2_2 = new String[]{"id", "phone", "minReceiveTime", "maxReceiveTime", "useBubbleViews", "useBubbleLogoName", "extend"};
        var0 = var1_5 = DBManager.query("tb_phone_bubble_cache", (String[])var2_2, "phone = ?", new String[]{var0});
        try {
            var2_2 = BaseManager.loadSingleDataFromCursor((String[])var2_2, var1_5);
        }
        catch (Throwable var2_4) {
            ** GOTO lbl11
        }
        XyCursor.closeCursor(var1_5, true);
        return var2_2;
        catch (Throwable var2_3) {
            var1_5 = null;
lbl11: // 2 sources:
            var0 = var1_5;
            var2_2.printStackTrace();
            XyCursor.closeCursor(var1_5, true);
            return null;
        }
        catch (Throwable var0_1) {
            var2_2 = null;
            var1_6 = var0_1;
lbl19: // 2 sources:
            do {
                XyCursor.closeCursor((XyCursor)var2_2, true);
                throw var1_6;
                break;
            } while (true);
        }
        {
            catch (Throwable var1_7) {
                var2_2 = var0;
                ** continue;
            }
        }
    }

    public static String getCreateTableSql() {
        return "create table  if not exists tb_phone_bubble_cache (  id INTEGER PRIMARY KEY, phone TEXT UNIQUE, minReceiveTime LONG default 0, maxReceiveTime LONG default 0, useBubbleViews TEXT, useBubbleLogoName TEXT, extend TEXT)";
    }

    public static void reSetAllDataTime(String string2, long l2, String string3, String string4, String string5) {
        string2 = new ContentValues();
        string2.put("minReceiveTime", Long.valueOf((long)System.currentTimeMillis()));
        string2.put("maxReceiveTime", Integer.valueOf((int)0));
        try {
            DBManager.update("tb_phone_bubble_cache", (ContentValues)string2, null, null);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static long saveOrUpdateObject(String var0, long var1_2, String var3_3, String var4_4, String var5_5) {
        block17 : {
            var6_6 = 0;
            try {
                var9_7 = PhoneSmsParseManager.findObjectByPhone(var0);
                if (var9_7 == null) {
                    var10_8 = new JSONArray();
                    if (!StringUtils.isNull(var3_3)) {
                        var10_8.put(0, (Object)var3_3);
                    }
                    if (var1_2 > 0) {
                        var9_7 = var3_3 = String.valueOf((long)var1_2);
                    } else {
                        var3_3 = String.valueOf((long)(System.currentTimeMillis() + Integer.MAX_VALUE));
                        var9_7 = "0";
                    }
                    v0 = new String[]{"phone", var0, "minReceiveTime", var9_7, "maxReceiveTime", var3_3, "useBubbleViews", var10_8.toString(), "useBubbleLogoName", var4_4, "extend", var5_5};
                    var0 = BaseManager.getContentValues(null, true, v0);
                    if (var0 == null) return -1;
                    return DBManager.insert("tb_phone_bubble_cache", (ContentValues)var0);
                }
                var10_9 = new ContentValues();
                if (var1_2 > 0) {
                    if (var1_2 < Long.valueOf((String)var9_7.getString("maxReceiveTime"))) {
                        var10_9.put("maxReceiveTime", Long.valueOf((long)var1_2));
                    } else if (var1_2 > Long.valueOf((String)var9_7.getString("minReceiveTime"))) {
                        var10_9.put("minReceiveTime", Long.valueOf((long)var1_2));
                    }
                }
                if (!StringUtils.isNull(var4_4)) {
                    var10_9.put("useBubbleLogoName", var4_4);
                }
                if (!StringUtils.isNull(var5_5)) {
                    var10_9.put("extend", var5_5);
                }
                if (StringUtils.isNull(var3_3) != false) return -1;
                var4_4 = var9_7.getString("useBubbleViews");
                if (StringUtils.isNull(var4_4)) {
                    new JSONArray().put(0, (Object)var3_3);
                    break block17;
                }
                var4_4 = new JSONArray(var4_4);
                var7_10 = var4_4.length();
                ** GOTO lbl52
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return -1;
            }
        }
lbl40: // 3 sources:
        do {
            if (var10_9.size() <= 0) return Long.valueOf((String)var9_7.getString("id"));
            DBManager.update("tb_phone_bubble_cache", var10_9, "phone=?", new String[]{var0});
            return Long.valueOf((String)var9_7.getString("id"));
            break;
        } while (true);
lbl44: // 2 sources:
        do {
            if (var6_6 != var7_10) ** GOTO lbl40
            var4_4.put(var7_10, (Object)var3_3);
            var10_9.put("useBubbleViews", var4_4.toString());
            ** continue;
            break;
        } while (true);
lbl-1000: // 1 sources:
        {
            var8_11 = var3_3.equals((Object)var4_4.getString(var6_6));
            if (var8_11) ** GOTO lbl44
            ++var6_6;
lbl52: // 2 sources:
            ** while (var6_6 < var7_10)
        }
lbl53: // 1 sources:
        ** while (true)
    }
}

