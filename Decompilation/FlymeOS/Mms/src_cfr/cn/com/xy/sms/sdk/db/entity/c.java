/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public final class c {
    private static String a = "tb_emergency_queue";

    public static String a() {
        return " create table  if not exists tb_emergency_queue ( id INTEGER PRIMARY KEY, emVersion INTEGER, emContent TEXT )";
    }

    /*
     * Exception decompiling
     */
    public static void a(JSONArray var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl38 : TryStatement: try { 5[TRYBLOCK]

        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:44)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner$1.call(Cleaner.java:22)
        // org.benf.cfr.reader.util.graph.GraphVisitorDFS.process(GraphVisitorDFS.java:68)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Cleaner.removeUnreachableCode(Cleaner.java:54)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.RemoveDeterministicJumps.apply(RemoveDeterministicJumps.java:35)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:507)
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
    public static void a(JSONObject jSONObject) {
        if (jSONObject == null) {
        }
        try {
            if (StringUtils.isNull((String)JsonUtil.getValueFromJsonObject(jSONObject, "emVersion"))) {
            }
            DBManager.delete("tb_emergency_queue", "emVersion = ?", new String[]{jSONObject.optString("emVersion")});
            return;
        }
        finally {
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
    public static JSONObject b() {
        var1 = null;
        var2_3 = new String[]{"id", "emVersion", "emContent"};
        var0_6 = DBManager.query("tb_emergency_queue", var2_3, null, null, null, null, " emVersion asc", "1");
        var1 = BaseManager.loadSingleDataFromCursor(var2_3, (XyCursor)var0_6);
        XyCursor.closeCursor((XyCursor)var0_6, true);
        return var1;
        catch (Throwable var1_1) {
            var0_6 = null;
            ** GOTO lbl20
            catch (Throwable var0_7) {}
            ** GOTO lbl-1000
            catch (Throwable var2_4) {
                var1 = var0_6;
                var0_6 = var2_4;
                ** GOTO lbl-1000
            }
            catch (Throwable var1_2) {}
lbl20: // 2 sources:
            try {
                var1.printStackTrace();
            }
            catch (Throwable var2_5) {
                var1 = var0_6;
                var0_6 = var2_5;
            }
            XyCursor.closeCursor((XyCursor)var0_6, true);
            return null;
        }
lbl-1000: // 3 sources:
        {
            XyCursor.closeCursor((XyCursor)var1, true);
            throw var0_6;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static void b(JSONObject var0) {
        block14 : {
            block13 : {
                var3_2 = null;
                if (var0 == null) {
                    XyCursor.closeCursor(null, true);
                    return;
                }
                var2_4 = var3_2;
                var4_7 = (String)JsonUtil.getValueFromJsonObject((JSONObject)var0, "emContent");
                var2_4 = var3_2;
                var5_8 = (String)JsonUtil.getValueFromJsonObject((JSONObject)var0, "emVersion");
                var2_4 = var3_2;
                if (StringUtils.isNull(var5_8)) break block13;
                var2_4 = var3_2;
                var1_9 = StringUtils.isNull(var4_7);
                if (!var1_9) break block14;
            }
            XyCursor.closeCursor(null, true);
            return;
        }
        var2_4 = var3_2;
        try {
            block15 : {
                var0 = String.valueOf((Object)var5_8);
                var2_4 = var3_2;
                var2_4 = var0 = DBManager.query("tb_emergency_queue", new String[]{"emVersion"}, "emVersion = ?", new String[]{var0});
                try {
                    var3_2 = new ContentValues();
                    var2_4 = var0;
                    var3_2.put("emVersion", var5_8);
                    var2_4 = var0;
                    var3_2.put("emContent", var4_7);
                    if (var0 != null) {
                        var2_4 = var0;
                        if (var0.getCount() > 0) {
                            var2_4 = var0;
                            DBManager.update("tb_emergency_queue", (ContentValues)var3_2, "emVersion = ? ", new String[]{String.valueOf((Object)var5_8)});
                            break block15;
                        }
                    }
                    var2_4 = var0;
                }
                catch (Throwable var2_5) {}
                DBManager.insert("tb_emergency_queue", (ContentValues)var3_2);
            }
            XyCursor.closeCursor((XyCursor)var0, true);
            return;
            ** GOTO lbl-1000
        }
        catch (Throwable var0_1) {}
        ** GOTO lbl-1000
        catch (Throwable var2_6) {
            var0 = null;
        }
lbl-1000: // 2 sources:
        {
            try {
                LogManager.e("XIAOYUAN", "insertOrUpdateEmergency " + var2_4.getLocalizedMessage(), (Throwable)var2_4);
            }
            catch (Throwable var3_3) {
                var2_4 = var0;
                var0 = var3_3;
            }
            XyCursor.closeCursor((XyCursor)var0, true);
            return;
        }
lbl-1000: // 2 sources:
        {
            XyCursor.closeCursor((XyCursor)var2_4, true);
            throw var0;
        }
    }
}

