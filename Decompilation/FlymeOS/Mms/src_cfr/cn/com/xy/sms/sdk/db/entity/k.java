/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashMap
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.db.entity;

import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.util.DateUtils;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class k {
    private static String a = "scene_id";
    private static String b = "date";
    private static String c = "action_type";
    private static String d = "times";
    private static String e = "tb_button_action_scene";
    private static String f = " DROP TABLE IF EXISTS tb_button_action_scene";
    private static String g = "create table  if not exists tb_button_action_scene (scene_id TEXT, date TEXT, action_type INTEGER DEFAULT '0', times INTEGER DEFAULT '0' ) ";
    private static String[] h = new String[]{"scene_id", "date", "action_type", "times"};

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static long a(HashMap<String, String> object) {
        String string2 = (String)object.get((Object)"titleNo");
        int n2 = !StringUtils.isNull((String)(object = (String)object.get((Object)"type"))) ? Integer.parseInt((String)object) : -1;
        try {
            object = !StringUtils.isNull(string2) && n2 != -1 ? k.a(string2, n2) : null;
            if (object != null) {
                object.put("times", (Object)String.valueOf((int)(Integer.parseInt((String)object.getString("times")) + 1)));
                DBManager.update("tb_button_action_scene", BaseManager.getContentValues(null, false, (JSONObject)object, h), "scene_id = ? and date = ? and action_type = ? ", new String[]{object.getString("scene_id"), object.getString("date"), object.getString("action_type")});
                return 0;
            }
            return DBManager.insert("tb_button_action_scene", BaseManager.getContentValues(null, "scene_id", string2, "date", DateUtils.getCurrentTimeString("yyyyMMdd"), "action_type", String.valueOf((int)n2), "times", "1"));
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
    public static JSONArray a(String var0, String var1_2) {
        var2_3 = null;
        var0 = var1_2 = DBManager.query("tb_button_action_scene", k.h, "scene_id = ? and date = ? ", new String[]{var0, var1_2}, null, null, null, null);
        try {
            var2_3 = BaseManager.loadArrDataFromCursor(k.h, (XyCursor)var1_2);
        }
        catch (Throwable var2_6) {
            ** GOTO lbl10
        }
        XyCursor.closeCursor((XyCursor)var1_2, true);
        return var2_3;
        catch (Throwable var2_4) {
            var1_2 = null;
lbl10: // 2 sources:
            var0 = var1_2;
            var2_3.printStackTrace();
            XyCursor.closeCursor((XyCursor)var1_2, true);
            return null;
        }
        catch (Throwable var0_1) {
            var1_2 = var2_3;
lbl17: // 2 sources:
            do {
                XyCursor.closeCursor((XyCursor)var1_2, true);
                throw var0;
                break;
            } while (true);
        }
        {
            catch (Throwable var2_5) {
                var1_2 = var0;
                var0 = var2_5;
                ** continue;
            }
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private static JSONObject a(String var0, int var1_2) {
        var2_3 = null;
        var3_4 = DateUtils.getCurrentTimeString("yyyyMMdd");
        var0 = var2_3 = (var0 = DBManager.query("tb_button_action_scene", k.h, "scene_id = ? and date = ? and action_type = ? ", new String[]{var0, var3_4, String.valueOf((int)var1_2)}, null, null, null, "1"));
        try {
            var3_4 = BaseManager.loadSingleDataFromCursor(k.h, (XyCursor)var2_3);
        }
        catch (Throwable var3_7) {
            ** GOTO lbl11
        }
        XyCursor.closeCursor((XyCursor)var2_3, true);
        return var3_4;
        catch (Throwable var3_5) {
            var2_3 = null;
lbl11: // 2 sources:
            var0 = var2_3;
            var3_4.printStackTrace();
            XyCursor.closeCursor((XyCursor)var2_3, true);
            return null;
        }
        catch (Throwable var0_1) lbl-1000: // 2 sources:
        {
            do {
                XyCursor.closeCursor((XyCursor)var2_3, true);
                throw var0;
                break;
            } while (true);
        }
        {
            catch (Throwable var3_6) {
                var2_3 = var0;
                var0 = var3_6;
                ** continue;
            }
        }
    }

    public static void a() {
        try {
            DBManager.delete("tb_button_action_scene", null, null);
            return;
        }
        catch (Throwable var0) {
            var0.printStackTrace();
            return;
        }
    }

    public static void a(String string2) {
        try {
            DBManager.delete("tb_button_action_scene", "date < ?", new String[]{string2});
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
    public static Map<String, String> b() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 7[CATCHBLOCK]
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
}

