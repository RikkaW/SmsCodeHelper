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
 */
package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.entity.v;

public final class w {
    private static int a = 0;
    private static int b = 1;
    private static String c = "id";
    private static String d = "scene_id";
    private static String e = "url";
    private static String f = "status";
    private static String g = "pos";
    private static String h = "insert_time";
    private static String i = "last_load_time";
    private static String j = "tb_xml_res_download";
    private static String k = "sceneType";
    private static String l = " DROP TABLE IF EXISTS tb_xml_res_download";
    private static String m = "create table  if not exists tb_xml_res_download (id INTEGER PRIMARY KEY,scene_id TEXT,url TEXT,status INTEGER,pos INTEGER,last_load_time INTEGER DEFAULT '0' ,sceneType INTEGER DEFAULT '0',insert_time INTEGER DEFAULT '0' )";

    public static void a() {
        try {
            DBManager.delete("tb_xml_res_download", null, null);
            return;
        }
        catch (Throwable var0) {
            var0.printStackTrace();
            return;
        }
    }

    public static void a(long l2, int n2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("status", Integer.valueOf((int)1));
            DBManager.update("tb_xml_res_download", contentValues, "id = ? ", new String[]{String.valueOf((long)l2)});
            return;
        }
        catch (Throwable var3_3) {
            var3_3.printStackTrace();
            return;
        }
    }

    private static void a(v v2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("last_load_time", String.valueOf((long)System.currentTimeMillis()));
            DBManager.update("tb_xml_res_download", contentValues, "id = ? ", new String[]{String.valueOf((long)v2.a)});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void a(v v2, long l2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("last_load_time", String.valueOf((long)l2));
            DBManager.update("tb_xml_res_download", contentValues, "id = ? ", new String[]{String.valueOf((long)v2.a)});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void a(String string2, String string3, int n2, int n3, int n4) {
        XyCursor xyCursor;
        block14 : {
            XyCursor xyCursor2;
            XyCursor xyCursor3;
            block13 : {
                xyCursor3 = null;
                xyCursor2 = null;
                xyCursor = DBManager.query("tb_xml_res_download", new String[]{"id", "scene_id", "url", "status", "pos"}, "url = ? ", new String[]{string3});
                if (xyCursor == null) break block13;
                xyCursor2 = xyCursor;
                xyCursor3 = xyCursor;
                if (xyCursor.getCount() > 0) break block14;
            }
            xyCursor2 = xyCursor;
            xyCursor3 = xyCursor;
            ContentValues contentValues = new ContentValues();
            xyCursor2 = xyCursor;
            xyCursor3 = xyCursor;
            contentValues.put("scene_id", string2);
            xyCursor2 = xyCursor;
            xyCursor3 = xyCursor;
            contentValues.put("url", string3);
            xyCursor2 = xyCursor;
            xyCursor3 = xyCursor;
            contentValues.put("status", Integer.valueOf((int)0));
            xyCursor2 = xyCursor;
            xyCursor3 = xyCursor;
            contentValues.put("pos", Integer.valueOf((int)0));
            xyCursor2 = xyCursor;
            xyCursor3 = xyCursor;
            contentValues.put("sceneType", Integer.valueOf((int)n4));
            xyCursor2 = xyCursor;
            xyCursor3 = xyCursor;
            contentValues.put("insert_time", Long.valueOf((long)System.currentTimeMillis()));
            xyCursor2 = xyCursor;
            xyCursor3 = xyCursor;
            try {
                DBManager.insert("tb_xml_res_download", contentValues);
            }
            catch (Throwable var0_1) {
                xyCursor3 = xyCursor2;
                try {
                    var0_1.printStackTrace();
                }
                catch (Throwable var0_2) {
                    XyCursor.closeCursor(xyCursor3, true);
                    throw var0_2;
                }
                XyCursor.closeCursor(xyCursor2, true);
                return;
            }
        }
        XyCursor.closeCursor(xyCursor, true);
        return;
    }

    /*
     * Exception decompiling
     */
    public static boolean a(String var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 6[SIMPLE_IF_TAKEN]
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

    private static ContentValues b(String string2, String string3, int n2, int n3, int n4) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("scene_id", string2);
        contentValues.put("url", string3);
        contentValues.put("status", Integer.valueOf((int)n2));
        contentValues.put("pos", Integer.valueOf((int)n3));
        contentValues.put("sceneType", Integer.valueOf((int)n4));
        contentValues.put("insert_time", Long.valueOf((long)System.currentTimeMillis()));
        return contentValues;
    }

    /*
     * Exception decompiling
     */
    public static v b() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 6[SIMPLE_IF_TAKEN]
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
     * Exception decompiling
     */
    public static v b(String var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 8[SIMPLE_IF_TAKEN]
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

