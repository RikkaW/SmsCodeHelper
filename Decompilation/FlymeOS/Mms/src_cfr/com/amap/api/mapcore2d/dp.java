/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import com.amap.api.mapcore2d.dk;
import com.amap.api.mapcore2d.dl;
import com.amap.api.mapcore2d.dn;
import com.amap.api.mapcore2d.do;
import com.amap.api.mapcore2d.dq;
import com.amap.api.mapcore2d.dr;
import com.amap.api.mapcore2d.du;
import com.amap.api.mapcore2d.ed;
import java.util.List;

public class dp {
    private dn a;

    public dp(Context context) {
        this.a = new dn(context);
    }

    private dq a(int n2) {
        switch (n2) {
            default: {
                return null;
            }
            case 0: {
                return new dl();
            }
            case 1: {
                return new do();
            }
            case 2: 
        }
        return new dk();
    }

    private void a(dr dr2, dq dq2) {
        dq2.a(dr2);
        this.a.a(dq2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(dr dr2, dq dq2) {
        String string2 = dq.a(dr2.b());
        List list = this.a.c(string2, dq2);
        if (list == null || list.size() == 0) {
            dq2.a(dr2);
            this.a.a(dq2);
            return;
        }
        list = (dr)list.get(0);
        if (dr2.a() == 0) {
            list.b(list.d() + 1);
        } else {
            list.b(0);
        }
        dq2.a((dr)((Object)list));
        this.a.b(string2, dq2);
    }

    private void c(String string2, int n2) {
        string2 = dq.a(string2);
        dq dq2 = this.a(n2);
        this.a.a(string2, dq2);
    }

    public List<dr> a(int n2, int n3) {
        try {
            Object object = this.a(n3);
            String string2 = dq.a(n2);
            object = this.a.c(string2, object);
            return object;
        }
        catch (Throwable var3_4) {
            ed.a(var3_4, "LogDB", "ByState");
            var3_4.printStackTrace();
            return null;
        }
    }

    public void a(dr object, int n2) {
        try {
            dq dq2 = this.a(n2);
            dq2.a(object);
            object = dq.a(object.b());
            this.a.b((String)object, dq2);
            return;
        }
        catch (Throwable var1_2) {
            ed.a(var1_2, "LogDB", "updateLogInfo");
            var1_2.printStackTrace();
            return;
        }
    }

    public void a(String string2, int n2) {
        try {
            this.c(string2, n2);
            return;
        }
        catch (Throwable var1_2) {
            ed.a(var1_2, "LogDB", "delLog");
            var1_2.printStackTrace();
            return;
        }
    }

    /*
     * Exception decompiling
     */
    public void b(dr var1_1, int var2_3) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:371)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:449)
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

    public void b(String string2, int n2) {
        try {
            this.c(string2, n2);
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }
}

