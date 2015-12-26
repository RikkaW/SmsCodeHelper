/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Color
 *  android.graphics.drawable.Drawable
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.ui.popu.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;

public final class a {
    private int a = 1;
    private int b = 1;
    private int c = 1;
    private int d = 0;
    private float e = 0.0f;
    private float f = 0.0f;
    private float g = 0.0f;
    private float h = 0.0f;
    private int i = 0;
    private int j = 1;
    private int k = 0;
    private int l = 0;
    private int m = 0;
    private int n = 0;
    private int o = 0;
    private int p = 0;
    private int q = 0;
    private int r = -1;
    private int s = 0;

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static a a(Context var0, String var1_1) {
        if (var1_1 == null) return null;
        try {
            var4_5 = new a();
        }
        catch (Throwable var1_4) {
            var0 = null;
            ** GOTO lbl22
        }
        var1_1 = var1_1.split(";");
        var3_6 = var1_1.length;
        var2_7 = 0;
        ** GOTO lbl79
lbl-1000: // 1 sources:
        {
            block40 : {
                var5_8 = var1_1[var2_7];
                try {
                    if (var5_8.startsWith("TL")) {
                        var4_5.e = ViewUtil.dp2px((Context)var0, Integer.valueOf((String)var5_8.substring(2)));
                    }
                    if (!var5_8.startsWith("TR")) break block40;
                    var4_5.f = ViewUtil.dp2px((Context)var0, Integer.valueOf((String)var5_8.substring(2)));
                }
                catch (Throwable var1_2) {
                    var0 = var4_5;
lbl22: // 2 sources:
                    var1_3.printStackTrace();
                    return var0;
                }
            }
            if (var5_8.startsWith("BL")) {
                var4_5.g = ViewUtil.dp2px((Context)var0, Integer.valueOf((String)var5_8.substring(2)));
            }
            if (var5_8.startsWith("BR")) {
                var4_5.h = ViewUtil.dp2px((Context)var0, Integer.valueOf((String)var5_8.substring(2)));
            } else if (var5_8.startsWith("TP")) {
                var4_5.i = Integer.valueOf((String)var5_8.substring(2));
            } else if (var5_8.startsWith("SC")) {
                var4_5.j = Color.parseColor((String)var5_8.substring(2));
            } else if (var5_8.startsWith("SW")) {
                var4_5.k = Integer.valueOf((String)var5_8.substring(2));
            } else if (var5_8.startsWith("DW")) {
                var4_5.l = Integer.valueOf((String)var5_8.substring(2));
            } else if (var5_8.startsWith("DG")) {
                var4_5.m = Integer.valueOf((String)var5_8.substring(2));
            } else if (var5_8.startsWith("CX")) {
                var4_5.p = ViewUtil.dp2px((Context)var0, Integer.valueOf((String)var5_8.substring(2)));
            } else if (var5_8.startsWith("CY")) {
                var4_5.q = ViewUtil.dp2px((Context)var0, Integer.valueOf((String)var5_8.substring(2)));
            } else if (var5_8.startsWith("GT")) {
                var4_5.r = Integer.valueOf((String)var5_8.substring(2));
            } else if (var5_8.startsWith("GR")) {
                var4_5.s = ViewUtil.dp2px((Context)var0, Integer.valueOf((String)var5_8.substring(2)));
            } else if (var5_8.startsWith("W")) {
                var4_5.n = ViewUtil.dp2px((Context)var0, Integer.valueOf((String)var5_8.substring(1)));
            } else if (var5_8.startsWith("H")) {
                var4_5.o = ViewUtil.dp2px((Context)var0, Integer.valueOf((String)var5_8.substring(1)));
            } else if (var5_8.startsWith("S")) {
                var4_5.a = Color.parseColor((String)var5_8.substring(1));
            } else if (var5_8.startsWith("C")) {
                var4_5.b = Color.parseColor((String)var5_8.substring(1));
            } else if (var5_8.startsWith("E")) {
                var4_5.c = Color.parseColor((String)var5_8.substring(1));
            } else if (var5_8.startsWith("A")) {
                var4_5.d = Integer.valueOf((String)var5_8.substring(1));
            }
            ++var2_7;
lbl79: // 2 sources:
            ** while (var2_7 < var3_6)
        }
lbl80: // 1 sources:
        return var4_5;
    }

    /*
     * Exception decompiling
     */
    public final Drawable a() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [22] lbl71 : TryStatement: try { 9[TRYBLOCK]

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
}

