/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.database.sqlite.SQLiteDatabase
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.net.util;

import android.database.sqlite.SQLiteDatabase;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.IccidInfo;
import cn.com.xy.sms.sdk.db.entity.IccidInfoManager;
import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import cn.com.xy.sms.sdk.db.entity.a.e;
import cn.com.xy.sms.sdk.net.util.d;
import cn.com.xy.sms.sdk.util.StringUtils;

public final class g {
    /*
     * Exception decompiling
     */
    private static void a(d var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [7, 9] lbl21 : TryStatement: try { 1[TRYBLOCK]

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
     * Exception decompiling
     */
    public static void a(String var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.CannotPerformDecode: reachable test BLOCK was exited and re-entered.
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.getFarthestReachableInRange(Misc.java:143)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:385)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.rebuildSwitches(SwitchReplacer.java:334)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:527)
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

    private static void b(d arrstring) {
        e.e();
        Object object = IccidInfoManager.queryDeftIccidInfo(Constant.getContext());
        arrstring = arrstring.c();
        if (object != null) {
            if (arrstring != null && arrstring.length > 0) {
                String string2 = object.areaCode;
                object = object.iccid;
                bx.a(arrstring);
                return;
            }
            bx.b(object.areaCode, object.iccid);
            return;
        }
        if (arrstring != null && arrstring.length > 0) {
            bx.a(arrstring);
            return;
        }
        bx.b("", "");
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void c(d arrstring) {
        if ((arrstring = arrstring.c()) == null || arrstring.length <= 0) {
            return;
        }
        int n2 = arrstring.length;
        int n3 = 0;
        while (n3 < n2) {
            String string2 = arrstring[n3];
            if (!StringUtils.isNull(string2)) {
                MatchCacheManager.deleteMatchCache(string2, System.currentTimeMillis());
            }
            ++n3;
        }
    }

    private static void d(d d2) {
        d d3;
        d d4 = null;
        d d5 = d3 = null;
        d d6 = d4;
        String string2 = d2.b();
        d5 = d3;
        d6 = d4;
        d5 = d2 = DBManager.getSQLiteDatabase();
        d6 = d2;
        try {
            d2.execSQL(string2);
        }
        catch (Throwable var0_1) {
            d6 = d5;
            try {
                var0_1.printStackTrace();
            }
            catch (Throwable var0_2) {
                DBManager.close((SQLiteDatabase)d6);
                throw var0_2;
            }
            DBManager.close((SQLiteDatabase)d5);
            return;
        }
        DBManager.close((SQLiteDatabase)d2);
        return;
    }
}

