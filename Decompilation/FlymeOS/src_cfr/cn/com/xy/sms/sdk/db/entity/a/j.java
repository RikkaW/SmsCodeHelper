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
 */
package cn.com.xy.sms.sdk.db.entity.a;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.db.DBManager;

public final class j {
    private static String e = "select_pub_time";
    private static String f = " DROP TABLE IF EXISTS select_pub_time";
    private static String g = "create table  if not exists select_pub_time ( id INTEGER PRIMARY KEY AUTOINCREMENT, num TEXT, areaCode TEXT, selectTime long default 0 )";
    public String a;
    public String b;
    private int c;
    private long d;

    /*
     * Exception decompiling
     */
    public static long a(String var0, String var1_3) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 4[SIMPLE_IF_TAKEN]
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

    public static ContentValues a(j j2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("num", j2.a);
        contentValues.put("areaCode", j2.b);
        contentValues.put("selectTime", Long.valueOf((long)System.currentTimeMillis()));
        return contentValues;
    }

    public static void b(j j2) {
        try {
            DBManager.insert("select_pub_time", j.a(j2));
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static int c(j j2) {
        ContentValues contentValues = j.a(j2);
        try {
            int n2 = DBManager.update("select_pub_time", contentValues, "num = ? and areaCode= ? ", new String[]{j2.a, j2.b});
            return n2;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return -1;
        }
    }
}

