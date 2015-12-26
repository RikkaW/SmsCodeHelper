/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Pair
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
import android.util.Pair;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class alu
implements alw {
    private String a;
    private String b;
    private List<Pair<String, String>> c;
    private List<Pair<String, String>> d;
    private boolean e;
    private a f = null;
    private amm g;
    private long h = 200;

    public alu(String string2, String string3, List<Pair<String, String>> list, List<Pair<String, String>> list2) {
        this.a = string2;
        this.b = string3;
        this.c = list;
        this.d = list2;
        this.e = false;
    }

    private long b(String string2) {
        if ((string2 = new File(string2)).exists()) {
            return string2.length();
        }
        return 0;
    }

    private void b() {
        if (this.e) {
            throw new alt();
        }
    }

    private void c() {
        File file = new File(this.b);
        if (!file.exists() && !(file = file.getParentFile()).exists()) {
            file.mkdirs();
        }
    }

    private void c(String string2) {
        if ((string2 = new File(string2)).exists()) {
            string2.delete();
        }
    }

    private void d(String string2) {
        anf.d(string2);
    }

    private void e(String string2) {
        anf.c(string2);
    }

    @Override
    public void a() {
        this.e = true;
    }

    public void a(a a2) {
        this.f = a2;
    }

    @Override
    public void a(amm amm2) {
        this.g = amm2;
    }

    @Override
    public void a(String string2) {
        this.a = string2;
    }

    @Override
    public void a(List<Pair<String, String>> list) {
        if (this.d != null) {
            block0 : for (Pair<String, String> pair : list) {
                for (Pair<String, String> pair2 : this.d) {
                    if (!((String)pair.first).equals(pair2.first)) continue;
                    this.d.remove(pair2);
                    continue block0;
                }
            }
        } else {
            this.d = new ArrayList();
        }
        this.d.addAll(list);
    }

    /*
     * Exception decompiling
     */
    @Override
    public boolean a(boolean var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [6[TRYBLOCK]], but top level block is 9[TRYBLOCK]
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

    public static interface a {
        public void a(int var1, long var2);
    }

}

