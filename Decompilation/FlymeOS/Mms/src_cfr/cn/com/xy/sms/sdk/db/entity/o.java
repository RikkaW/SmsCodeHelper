/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.database.sqlite.SQLiteDatabase
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.entity.SceneRule;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

public final class o {
    private static String a = "id";
    private static String b = "sceneRuleVersion";
    private static String c = "scene_id";
    private static String d = "province";
    private static String e = "operator";
    private static String f = "expire_date";
    private static String g = "Func_call";
    private static String h = "Func_acc_url";
    private static String i = "Func_reply_sms";
    private static String j = "Func_config";
    private static String k = "res_urls";
    private static String l = "s_version";
    private static String m = "Scene_page_config";
    private static String n = "isdownload";
    private static String o = "tb_scenerule_config";
    private static String p = "sceneType";
    private static String q = " DROP TABLE IF EXISTS tb_scenerule_config";
    private static String r = "create table  if not exists tb_scenerule_config (id TEXT,sceneRuleVersion TEXT,scene_id TEXT,province TEXT,operator TEXT,expire_date TEXT,Func_call INTEGER,Func_acc_url INTEGER,Func_reply_sms INTEGER,Func_config TEXT,res_urls TEXT,s_version TEXT,Scene_page_config TEXT,sceneType INTEGER DEFAULT '-1',isdownload INTEGER DEFAULT '0')";
    private static String s = "ALTER TABLE tb_scenerule_config ADD COLUMN isdownload INTEGER DEFAULT '0'";

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static List<SceneRule> a(int var0) {
        block9 : {
            var20_1 = new ArrayList();
            var19_2 = null;
            var18_3 = null;
            if (var0 != 1) ** GOTO lbl10
            var16_4 = var18_3;
            var15_6 = var19_2;
            try {
                var17_7 = "sceneType = " + var0;
                ** GOTO lbl11
lbl10: // 1 sources:
                var17_7 = "sceneType != 1";
lbl11: // 2 sources:
                var16_4 = var18_3;
                var15_6 = var19_2;
                if ((var17_7 = DBManager.query("tb_scenerule_config", new String[]{"id", "scene_id", "province", "operator", "expire_date", "Func_call", "Func_acc_url", "Func_reply_sms", "Func_config", "res_urls", "s_version", "Scene_page_config", "isdownload", "sceneRuleVersion"}, (String)var17_7, null)) != null) {
                    var16_4 = var17_7;
                    var15_6 = var17_7;
                    if (var17_7.getCount() > 0) {
                        var16_4 = var17_7;
                        var15_6 = var17_7;
                        var0 = var17_7.getColumnIndex("id");
                        var16_4 = var17_7;
                        var15_6 = var17_7;
                        var1_9 = var17_7.getColumnIndex("scene_id");
                        var16_4 = var17_7;
                        var15_6 = var17_7;
                        var2_10 = var17_7.getColumnIndex("province");
                        var16_4 = var17_7;
                        var15_6 = var17_7;
                        var3_11 = var17_7.getColumnIndex("operator");
                        var16_4 = var17_7;
                        var15_6 = var17_7;
                        var4_12 = var17_7.getColumnIndex("expire_date");
                        var16_4 = var17_7;
                        var15_6 = var17_7;
                        var5_13 = var17_7.getColumnIndex("Func_call");
                        var16_4 = var17_7;
                        var15_6 = var17_7;
                        var6_14 = var17_7.getColumnIndex("Func_acc_url");
                        var16_4 = var17_7;
                        var15_6 = var17_7;
                        var7_15 = var17_7.getColumnIndex("Func_reply_sms");
                        var16_4 = var17_7;
                        var15_6 = var17_7;
                        var8_16 = var17_7.getColumnIndex("Func_config");
                        var16_4 = var17_7;
                        var15_6 = var17_7;
                        var9_17 = var17_7.getColumnIndex("res_urls");
                        var16_4 = var17_7;
                        var15_6 = var17_7;
                        var10_18 = var17_7.getColumnIndex("s_version");
                        var16_4 = var17_7;
                        var15_6 = var17_7;
                        var11_19 = var17_7.getColumnIndex("Scene_page_config");
                        var16_4 = var17_7;
                        var15_6 = var17_7;
                        var12_20 = var17_7.getColumnIndex("isdownload");
                        var16_4 = var17_7;
                        var15_6 = var17_7;
                        var13_21 = var17_7.getColumnIndex("sceneRuleVersion");
                        do {
                            var16_4 = var17_7;
                            var15_6 = var17_7;
                            var14_22 = var17_7.moveToNext();
                            if (var14_22) {
                                var16_4 = var17_7;
                                var15_6 = var17_7;
                                var18_3 = new SceneRule();
                                var16_4 = var17_7;
                                var15_6 = var17_7;
                                var18_3.id = var17_7.getString(var0);
                                var16_4 = var17_7;
                                var15_6 = var17_7;
                                var18_3.scene_id = var17_7.getString(var1_9);
                                var16_4 = var17_7;
                                var15_6 = var17_7;
                                var18_3.province = var17_7.getString(var2_10);
                                var16_4 = var17_7;
                                var15_6 = var17_7;
                                var18_3.operator = var17_7.getString(var3_11);
                                var16_4 = var17_7;
                                var15_6 = var17_7;
                                var18_3.expire_date = var17_7.getString(var4_12);
                                var16_4 = var17_7;
                                var15_6 = var17_7;
                                var18_3.Func_call = var17_7.getInt(var5_13);
                                var16_4 = var17_7;
                                var15_6 = var17_7;
                                var18_3.Func_acc_url = var17_7.getInt(var6_14);
                                var16_4 = var17_7;
                                var15_6 = var17_7;
                                var18_3.Func_reply_sms = var17_7.getInt(var7_15);
                                var16_4 = var17_7;
                                var15_6 = var17_7;
                                var18_3.Func_config = var17_7.getString(var8_16);
                                var16_4 = var17_7;
                                var15_6 = var17_7;
                                var18_3.res_urls = var17_7.getString(var9_17);
                                var16_4 = var17_7;
                                var15_6 = var17_7;
                                var18_3.s_version = var17_7.getString(var10_18);
                                var16_4 = var17_7;
                                var15_6 = var17_7;
                                var18_3.Scene_page_config = var17_7.getString(var11_19);
                                var16_4 = var17_7;
                                var15_6 = var17_7;
                                var18_3.isDownload = var17_7.getInt(var12_20);
                                var16_4 = var17_7;
                                var15_6 = var17_7;
                                var18_3.sceneruleVersion = var17_7.getString(var13_21);
                                var16_4 = var17_7;
                                var15_6 = var17_7;
                                var20_1.add(var18_3);
                                continue;
                            }
                            ** GOTO lbl124
                            break;
                        } while (true);
                    }
                }
                break block9;
                catch (Throwable var17_8) {
                    var15_6 = var16_4;
                    var17_8.printStackTrace();
                    XyCursor.closeCursor((XyCursor)var16_4, true);
                    return var20_1;
                }
            }
            catch (Throwable var16_5) {
                XyCursor.closeCursor((XyCursor)var15_6, true);
                throw var16_5;
            }
        }
        XyCursor.closeCursor((XyCursor)var17_7, true);
        return var20_1;
    }

    public static List<SceneRule> a(String string2, int n2) {
        return o.a(string2, n2, false);
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static List<SceneRule> a(String var0, int var1_3, boolean var2_4) {
        block46 : {
            block45 : {
                var20_5 = new ArrayList();
                var19_6 = null;
                var18_7 = null;
                if (!var2_4) ** GOTO lbl93
                var17_8 = var18_7;
                var16_9 = var19_6;
                if (var0.length() != 8) break block45;
                var17_8 = var18_7;
                var16_9 = var19_6;
                var0 = "scene_id LIKE '" + var0.substring(0, 2) + "%" + var0.substring(5, 8) + "' ";
lbl13: // 2 sources:
                while (var1_3 == 1) {
                    var17_8 = var18_7;
                    var16_9 = var19_6;
                    var0 = String.valueOf((Object)var0) + " and sceneType" + " = " + var1_3;
lbl18: // 2 sources:
                    do {
                        var17_8 = var18_7;
                        var16_9 = var19_6;
                        var0 = DBManager.query("tb_scenerule_config", new String[]{"id", "scene_id", "province", "operator", "expire_date", "Func_call", "Func_acc_url", "Func_reply_sms", "Func_config", "res_urls", "s_version", "Scene_page_config", "isdownload", "sceneRuleVersion"}, (String)var0, null);
                        if (var0 == null) ** GOTO lbl-1000
                        var17_8 = var0;
                        var16_9 = var0;
                        if (var0.getCount() <= 0) ** GOTO lbl-1000
                        var17_8 = var0;
                        var16_9 = var0;
                        var1_3 = var0.getColumnIndex("id");
                        var17_8 = var0;
                        var16_9 = var0;
                        var3_10 = var0.getColumnIndex("scene_id");
                        var17_8 = var0;
                        var16_9 = var0;
                        var4_11 = var0.getColumnIndex("province");
                        var17_8 = var0;
                        var16_9 = var0;
                        var5_12 = var0.getColumnIndex("operator");
                        var17_8 = var0;
                        var16_9 = var0;
                        var6_13 = var0.getColumnIndex("expire_date");
                        var17_8 = var0;
                        var16_9 = var0;
                        var7_14 = var0.getColumnIndex("Func_call");
                        var17_8 = var0;
                        var16_9 = var0;
                        var8_15 = var0.getColumnIndex("Func_acc_url");
                        var17_8 = var0;
                        var16_9 = var0;
                        var9_16 = var0.getColumnIndex("Func_reply_sms");
                        var17_8 = var0;
                        var16_9 = var0;
                        var10_17 = var0.getColumnIndex("Func_config");
                        var17_8 = var0;
                        var16_9 = var0;
                        var11_18 = var0.getColumnIndex("res_urls");
                        var17_8 = var0;
                        var16_9 = var0;
                        var12_19 = var0.getColumnIndex("s_version");
                        var17_8 = var0;
                        var16_9 = var0;
                        var13_20 = var0.getColumnIndex("Scene_page_config");
                        var17_8 = var0;
                        var16_9 = var0;
                        var14_21 = var0.getColumnIndex("isdownload");
                        var17_8 = var0;
                        var16_9 = var0;
                        var15_22 = var0.getColumnIndex("sceneRuleVersion");
lbl84: // 2 sources:
                        var17_8 = var0;
                        var16_9 = var0;
                        var2_4 = var0.moveToNext();
                        ** if (var2_4) goto lbl-1000
lbl-1000: // 3 sources:
                        {
                            XyCursor.closeCursor((XyCursor)var0, true);
                            return var20_5;
                        }
lbl-1000: // 1 sources:
                        {
                            break block46;
                        }
                        break;
                    } while (true);
                }
                ** GOTO lbl99
            }
            var17_8 = var18_7;
            var16_9 = var19_6;
            var0 = "scene_id = '" + (String)var0 + "' ";
            ** GOTO lbl13
lbl99: // 1 sources:
            var17_8 = var18_7;
            var16_9 = var19_6;
            var0 = String.valueOf((Object)var0) + " and sceneType" + " != 1";
            ** continue;
        }
        var17_8 = var0;
        var16_9 = var0;
        var18_7 = new SceneRule();
        var17_8 = var0;
        var16_9 = var0;
        var18_7.id = var0.getString(var1_3);
        var17_8 = var0;
        var16_9 = var0;
        var18_7.scene_id = var0.getString(var3_10);
        var17_8 = var0;
        var16_9 = var0;
        var18_7.province = var0.getString(var4_11);
        var17_8 = var0;
        var16_9 = var0;
        var18_7.operator = var0.getString(var5_12);
        var17_8 = var0;
        var16_9 = var0;
        var18_7.expire_date = var0.getString(var6_13);
        var17_8 = var0;
        var16_9 = var0;
        var18_7.Func_call = var0.getInt(var7_14);
        var17_8 = var0;
        var16_9 = var0;
        var18_7.Func_acc_url = var0.getInt(var8_15);
        var17_8 = var0;
        var16_9 = var0;
        var18_7.Func_reply_sms = var0.getInt(var9_16);
        var17_8 = var0;
        var16_9 = var0;
        var18_7.Func_config = var0.getString(var10_17);
        var17_8 = var0;
        var16_9 = var0;
        var18_7.res_urls = var0.getString(var11_18);
        var17_8 = var0;
        var16_9 = var0;
        var18_7.s_version = var0.getString(var12_19);
        var17_8 = var0;
        var16_9 = var0;
        var18_7.Scene_page_config = var0.getString(var13_20);
        var17_8 = var0;
        var16_9 = var0;
        var18_7.isDownload = var0.getInt(var14_21);
        var17_8 = var0;
        var16_9 = var0;
        var18_7.sceneruleVersion = var0.getString(var15_22);
        var17_8 = var0;
        var16_9 = var0;
        try {
            var20_5.add(var18_7);
            ** GOTO lbl84
        }
        catch (Throwable var0_1) {
            var16_9 = var17_8;
            try {
                var0_1.printStackTrace();
            }
            catch (Throwable var0_2) {
                XyCursor.closeCursor((XyCursor)var16_9, true);
                throw var0_2;
            }
            XyCursor.closeCursor((XyCursor)var17_8, true);
            return var20_5;
        }
    }

    public static void a() {
        try {
            DBManager.delete("tb_scenerule_config", null, null);
            return;
        }
        catch (Throwable var0) {
            var0.printStackTrace();
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void a(SceneRule sceneRule, int n2) {
        if (sceneRule == null) return;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("isdownload", Integer.valueOf((int)1));
            DBManager.update("tb_scenerule_config", contentValues, "id = ? ", new String[]{sceneRule.id});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static int b(int n2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("Func_call", Integer.valueOf((int)n2));
            n2 = DBManager.update("tb_scenerule_config", contentValues, null, null);
            return n2;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return -1;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static long b(SceneRule var0, int var1_3) {
        block6 : {
            var9_4 = new ContentValues();
            var9_4.put("id", var0.id);
            var9_4.put("sceneRuleVersion", var0.sceneruleVersion);
            var9_4.put("scene_id", var0.scene_id);
            var9_4.put("province", var0.province);
            var9_4.put("operator", var0.operator);
            var9_4.put("expire_date", var0.expire_date);
            var9_4.put("Func_call", Integer.valueOf((int)var0.Func_call));
            var9_4.put("Func_acc_url", Integer.valueOf((int)var0.Func_acc_url));
            var9_4.put("Func_reply_sms", Integer.valueOf((int)var0.Func_reply_sms));
            var9_4.put("Func_config", var0.Func_config);
            var9_4.put("res_urls", var0.res_urls);
            var9_4.put("s_version", var0.s_version);
            var9_4.put("Scene_page_config", var0.Scene_page_config);
            var9_4.put("isdownload", Integer.valueOf((int)var0.isDownload));
            var9_4.put("sceneType", Integer.valueOf((int)var1_3));
            var7_5 = null;
            var8_6 = null;
            var4_7 = null;
            var6_8 = var7_5;
            var5_9 = var8_6;
            if (!StringUtils.isNull(var0.id)) {
                var6_8 = var7_5;
                var5_9 = var8_6;
                var4_7 = var0.id;
                var6_8 = var7_5;
                var5_9 = var8_6;
                var4_7 = DBManager.query("tb_scenerule_config", new String[]{"id", "sceneRuleVersion"}, "id = ? ", new String[]{var4_7});
            }
            if (var4_7 == null) break block6;
            var6_8 = var4_7;
            var5_9 = var4_7;
            if (var4_7.getCount() <= 0) break block6;
            var6_8 = var4_7;
            var5_9 = var4_7;
            var1_3 = DBManager.update("tb_scenerule_config", var9_4, "id=? ", new String[]{var0.id});
            var2_10 = var1_3;
            ** GOTO lbl50
        }
        var6_8 = var4_7;
        var5_9 = var4_7;
        try {
            try {
                var2_10 = DBManager.insert("tb_scenerule_config", var9_4);
            }
            catch (Throwable var0_1) {
                var5_9 = var6_8;
                var0_1.printStackTrace();
                XyCursor.closeCursor((XyCursor)var6_8, true);
                return -1;
            }
lbl50: // 2 sources:
            XyCursor.closeCursor((XyCursor)var4_7, true);
            return var2_10;
        }
        catch (Throwable var0_2) {
            XyCursor.closeCursor((XyCursor)var5_9, true);
            throw var0_2;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static void b() {
        SQLiteDatabase sQLiteDatabase;
        Object object = DBManager.dblock;
        // MONITORENTER : object
        SQLiteDatabase sQLiteDatabase2 = null;
        SQLiteDatabase sQLiteDatabase3 = null;
        try {
            sQLiteDatabase3 = sQLiteDatabase = DBManager.getSQLiteDatabase();
            sQLiteDatabase2 = sQLiteDatabase;
            sQLiteDatabase.execSQL("DELETE FROM tb_scenerule_config WHERE FUNC_CALL=10 AND scene_id IN (SELECT scene_id FROM tb_scenerule_config WHERE 1=1 GROUP BY scene_id HAVING COUNT(scene_id) > 1)");
        }
        catch (Throwable var2_5) {
            sQLiteDatabase2 = sQLiteDatabase3;
            try {
                var2_5.printStackTrace();
            }
            catch (Throwable var0_3) {
                DBManager.close(sQLiteDatabase2);
                throw var0_3;
            }
            DBManager.close(sQLiteDatabase3);
            return;
        }
        DBManager.close(sQLiteDatabase);
        // MONITOREXIT : object
        return;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void b(String var0, int var1_2) {
        if (var1_2 != 1) ** GOTO lbl5
        try {
            var2_3 = "scene_id=? and sceneType = " + var1_2;
            ** GOTO lbl6
lbl5: // 1 sources:
            var2_3 = "scene_id=? and sceneType != 1";
lbl6: // 2 sources:
            DBManager.delete("tb_scenerule_config", var2_3, new String[]{var0});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    private static ContentValues c(SceneRule sceneRule, int n2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", sceneRule.id);
        contentValues.put("sceneRuleVersion", sceneRule.sceneruleVersion);
        contentValues.put("scene_id", sceneRule.scene_id);
        contentValues.put("province", sceneRule.province);
        contentValues.put("operator", sceneRule.operator);
        contentValues.put("expire_date", sceneRule.expire_date);
        contentValues.put("Func_call", Integer.valueOf((int)sceneRule.Func_call));
        contentValues.put("Func_acc_url", Integer.valueOf((int)sceneRule.Func_acc_url));
        contentValues.put("Func_reply_sms", Integer.valueOf((int)sceneRule.Func_reply_sms));
        contentValues.put("Func_config", sceneRule.Func_config);
        contentValues.put("res_urls", sceneRule.res_urls);
        contentValues.put("s_version", sceneRule.s_version);
        contentValues.put("Scene_page_config", sceneRule.Scene_page_config);
        contentValues.put("isdownload", Integer.valueOf((int)sceneRule.isDownload));
        contentValues.put("sceneType", Integer.valueOf((int)n2));
        return contentValues;
    }
}

