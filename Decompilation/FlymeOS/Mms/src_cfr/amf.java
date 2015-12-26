/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Context;
import android.text.TextUtils;

public class amf {
    private amn a;
    private amm b;
    private alw c;
    private String d;
    private boolean e;
    private amo f;

    public amf(Context context, String string2, alw alw2, amn amn2) {
        if (TextUtils.isEmpty((CharSequence)string2) || alw2 == null || amn2 == null) {
            throw new IllegalArgumentException("Params cant be null!");
        }
        this.d = string2;
        this.c = alw2;
        this.e = false;
        this.a = amn2;
        this.f = new amo(context);
    }

    private void a(String string2) {
        anf.d(string2);
    }

    private void b() {
        if (this.e) {
            throw new alt();
        }
    }

    private void b(String string2) {
        anf.c(string2);
    }

    public void a() {
        this.e = true;
        this.c.a();
    }

    public void a(amm amm2) {
        this.b = amm2;
        this.c.a(amm2);
    }

    /*
     * Exception decompiling
     */
    public boolean a(Context var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl209 : TryStatement: try { 14[TRYBLOCK]

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

