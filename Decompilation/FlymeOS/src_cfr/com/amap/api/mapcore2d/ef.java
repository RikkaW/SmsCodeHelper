/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.df;
import com.amap.api.mapcore2d.dh;
import com.amap.api.mapcore2d.dp;
import com.amap.api.mapcore2d.ed;
import com.amap.api.mapcore2d.ek;
import com.amap.api.mapcore2d.en;
import com.amap.api.mapcore2d.es;
import java.util.List;

class ef
extends en {
    private String[] a = new String[10];
    private int b = 0;
    private boolean c = false;
    private int d = 0;
    private a e;

    ef() {
    }

    private void b(String string2) {
        try {
            if (this.b > 9) {
                this.b = 0;
            }
            this.a[this.b] = string2;
            ++this.b;
            return;
        }
        catch (Throwable var1_2) {
            ed.a(var1_2, "ANRWriter", "addData");
            var1_2.printStackTrace();
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
    private String c() {
        block5 : {
            var2_1 = new StringBuilder();
            try {
                var1_2 = this.b;
                break block5;
lbl5: // 1 sources:
                do {
                    if (var1_2 >= this.b) return var2_1.toString();
                    var2_1.append(this.a[var1_2]);
                    ++var1_2;
                    continue;
                    break;
                } while (true);
lbl10: // 1 sources:
                var2_1.append(this.a[var1_2]);
                ++var1_2;
                break block5;
            }
            catch (Throwable var3_3) {
                ed.a(var3_3, "ANRWriter", "getLogInfo");
                var3_3.printStackTrace();
            }
            return var2_1.toString();
        }
        if (var1_2 < 10 && var1_2 <= 9) ** GOTO lbl10
        var1_2 = 0;
        ** while (true)
    }

    @Override
    protected int a() {
        return 2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    protected es a(dp dp2) {
        try {
            if (this.e == null) {
                this.e = new a(dp2);
            }
            do {
                return this.e;
                break;
            } while (true);
        }
        catch (Throwable var1_2) {
            ed.a(var1_2, "ANRWriter", "getListener");
            var1_2.printStackTrace();
            return this.e;
        }
    }

    @Override
    protected String a(String string2) {
        return df.b(string2);
    }

    /*
     * Exception decompiling
     */
    @Override
    protected String a(List<dh> var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [17[CATCHBLOCK]], but top level block is 23[CATCHBLOCK]
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

    @Override
    protected String b() {
        return ek.d;
    }

    class a
    implements es {
        private dp b;

        private a(dp dp2) {
            this.b = dp2;
        }

        @Override
        public void a(String string2) {
            try {
                this.b.b(string2, ef.this.a());
                return;
            }
            catch (Throwable var1_2) {
                var1_2.printStackTrace();
                return;
            }
        }
    }

}

