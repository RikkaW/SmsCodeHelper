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
import cn.com.xy.sms.sdk.queue.a.a;
import cn.com.xy.sms.sdk.util.JsonUtil;
import org.json.JSONArray;
import org.json.JSONObject;

public final class n {
    private static String a = "tb_resourse_queue";
    private static String b = " DROP TABLE IF EXISTS tb_resourse_queue";

    public static String a() {
        return " create table  if not exists tb_resourse_queue ( id INTEGER PRIMARY KEY, res_type INTEGER, res_version INTEGER, res_url TEXT, down_statu INTEGER DEFAULT '0', temp_filename TEXT, down_failed_time LONG DEFAULT '0')";
    }

    /*
     * Exception decompiling
     */
    public static JSONArray a(int var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 6[CATCHBLOCK]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(Integer n2, boolean bl2, String string2) {
        try {
            ContentValues contentValues = new ContentValues();
            if (bl2) {
                contentValues.put("res_url", "");
                contentValues.put("down_failed_time", "0");
                contentValues.put("down_statu", "1");
                contentValues.put("temp_filename", string2);
            } else {
                contentValues.put("down_failed_time", Long.valueOf((long)System.currentTimeMillis()));
                contentValues.put("down_statu", "0");
                contentValues.put("temp_filename", string2);
            }
            DBManager.update("tb_resourse_queue", contentValues, "id = ? ", new String[]{String.valueOf((Object)n2)});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Exception decompiling
     */
    public static void a(JSONArray var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 15[WHILELOOP]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void a(JSONObject object) {
        if (object == null) return;
        try {
            String string2 = (String)JsonUtil.getValueFromJsonObject((JSONObject)object, "res_type");
            object = (String)JsonUtil.getValueFromJsonObject((JSONObject)object, "res_version");
            DBManager.delete("tb_resourse_queue", "res_type = ? and res_version < ?", new String[]{String.valueOf((Object)string2), String.valueOf((Object)object)});
            a.a(string2, (String)object);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void a(boolean bl2, String string2) {
        ContentValues contentValues;
        int n2;
        block3 : {
            n2 = 0;
            try {
                contentValues = new ContentValues();
                if (!bl2) break block3;
                n2 = 2;
            }
            catch (Throwable var1_2) {
                var1_2.printStackTrace();
                return;
            }
        }
        contentValues.put("down_statu", Integer.valueOf((int)n2));
        new StringBuilder("unzipSuess=").append(bl2).append(" fileNam=").append(string2);
        DBManager.update("tb_resourse_queue", contentValues, "temp_filename = ?", new String[]{string2});
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String b(int n2) {
        XyCursor xyCursor;
        Object object;
        block7 : {
            String string2;
            object = null;
            xyCursor = null;
            String string3 = string2 = "";
            XyCursor xyCursor2 = xyCursor;
            XyCursor xyCursor3 = object;
            try {
                String string4 = "res_type = " + n2 + " ORDER BY res_version desc LIMIT 1 ";
                string3 = string2;
                xyCursor2 = xyCursor;
                xyCursor3 = object;
                xyCursor = DBManager.query("tb_resourse_queue", new String[]{"res_version"}, string4, null);
                object = string2;
                if (xyCursor == null) break block7;
                object = string2;
                string3 = string2;
                xyCursor2 = xyCursor;
                xyCursor3 = xyCursor;
                if (xyCursor.getCount() <= 0) break block7;
                do {
                    string3 = string2;
                    xyCursor2 = xyCursor;
                    xyCursor3 = xyCursor;
                    boolean bl2 = xyCursor.moveToNext();
                    if (!bl2) {
                        object = string2;
                        break block7;
                    }
                    string3 = string2;
                    xyCursor2 = xyCursor;
                    xyCursor3 = xyCursor;
                    string2 = xyCursor.getString(0);
                    continue;
                    break;
                } while (true);
                catch (Throwable throwable) {
                    xyCursor3 = xyCursor2;
                    throwable.printStackTrace();
                    XyCursor.closeCursor(xyCursor2, true);
                    return string3;
                }
            }
            catch (Throwable var2_5) {
                XyCursor.closeCursor(xyCursor3, true);
                throw var2_5;
            }
        }
        XyCursor.closeCursor(xyCursor, true);
        return object;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static void b(JSONObject var0) {
        block11 : {
            block10 : {
                var1_4 = null;
                var3_6 = null;
                var2_7 = var1_4;
                var4_9 = (String)JsonUtil.getValueFromJsonObject((JSONObject)var0, "res_type");
                var2_7 = var1_4;
                var5_10 = (String)JsonUtil.getValueFromJsonObject((JSONObject)var0, "res_version");
                if (var0 == null) break block10;
                var2_7 = var1_4;
                var6_11 = String.valueOf((Object)var4_9);
                var2_7 = var1_4;
                var7_12 = String.valueOf((Object)var5_10);
                var2_7 = var1_4;
                var8_13 = String.valueOf((Object)var4_9);
                var2_7 = var1_4;
                var9_14 = String.valueOf((Object)var5_10);
                var2_7 = var1_4;
                var1_4 = DBManager.query("tb_resourse_queue", new String[]{var6_11, var7_12}, "res_type = ? and res_version = ? ", new String[]{var8_13, var9_14});
                var2_7 = new ContentValues();
                var2_7.put("res_type", var4_9);
                var2_7.put("res_version", var5_10);
                var2_7.put("res_url", (String)JsonUtil.getValueFromJsonObject((JSONObject)var0, "res_url"));
                if (var1_4 != null && var1_4.getCount() > 0) {
                    DBManager.update("tb_resourse_queue", (ContentValues)var2_7, "res_type = ? and res_version = ? ", new String[]{String.valueOf((Object)var4_9), String.valueOf((Object)var5_10)});
                    var0 = var1_4;
                } else {
                    DBManager.insert("tb_resourse_queue", (ContentValues)var2_7);
                    var0 = var1_4;
                }
                break block11;
                catch (Throwable var1_5) {
                    var0 = var3_6;
                    ** GOTO lbl40
                    catch (Throwable var0_3) {
                        var2_7 = var1_4;
                        ** GOTO lbl-1000
                    }
                    catch (Throwable var2_8) {
                        var0 = var1_4;
                        var1_4 = var2_8;
                    }
lbl40: // 2 sources:
                    var2_7 = var0;
                    try {
                        var1_4.printStackTrace();
                    }
                    catch (Throwable var0_1) lbl-1000: // 2 sources:
                    {
                        XyCursor.closeCursor((XyCursor)var2_7, true);
                        throw var0_2;
                    }
                    XyCursor.closeCursor((XyCursor)var0, true);
                    return;
                }
            }
            var0 = null;
        }
        XyCursor.closeCursor((XyCursor)var0, true);
    }
}

