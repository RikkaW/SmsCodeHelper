/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
import java.io.InputStream;

public class any
extends InputStream {
    private static final int[] a = new int[256];
    private static final int[] b;
    private static /* synthetic */ int[] l;
    private final InputStream c;
    private final String d;
    private final int[] e = new int[3];
    private a f = a.a;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;

    /*
     * Enabled aggressive block sorting
     */
    static {
        int n2 = 0;
        block0 : do {
            if (n2 >= 256) {
                b = new int[]{80, 75, 3, 4};
                return;
            }
            int n3 = 0;
            int n4 = n2;
            do {
                if (n3 >= 8) {
                    any.a[n2] = n4;
                    ++n2;
                    continue block0;
                }
                n4 = (n4 & 1) == 1 ? n4 >>> 1 ^ -306674912 : (n4 >>>= 1);
                ++n3;
            } while (true);
            break;
        } while (true);
    }

    public any(InputStream inputStream, String string2) {
        this.c = inputStream;
        this.d = string2;
    }

    private int a(int n2, byte by2) {
        return n2 >>> 8 ^ a[(n2 ^ by2) & 255];
    }

    private void a(byte by2) {
        this.e[0] = this.a(this.e[0], by2);
        int[] arrn = this.e;
        arrn[1] = arrn[1] + (this.e[0] & 255);
        this.e[1] = this.e[1] * 134775813 + 1;
        this.e[2] = this.a(this.e[2], (byte)(this.e[1] >> 24));
    }

    private void a(String string2) {
        int n2 = 0;
        this.e[0] = 305419896;
        this.e[1] = 591751049;
        this.e[2] = 878082192;
        while (n2 < string2.length()) {
            this.a((byte)(string2.charAt(n2) & 255));
            ++n2;
        }
        return;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static /* synthetic */ int[] a() {
        int[] arrn;
        arrn = l;
        if (arrn != null) {
            return arrn;
        }
        arrn = new int[a.values().length];
        try {
            arrn[a.c.ordinal()] = 3;
        }
        catch (NoSuchFieldError var1_8) {}
        try {
            arrn[a.g.ordinal()] = 7;
        }
        catch (NoSuchFieldError var1_7) {}
        try {
            arrn[a.e.ordinal()] = 5;
        }
        catch (NoSuchFieldError var1_6) {}
        try {
            arrn[a.b.ordinal()] = 2;
        }
        catch (NoSuchFieldError var1_5) {}
        try {
            arrn[a.d.ordinal()] = 4;
        }
        catch (NoSuchFieldError var1_4) {}
        try {
            arrn[a.f.ordinal()] = 6;
        }
        catch (NoSuchFieldError var1_3) {}
        try {
            arrn[a.a.ordinal()] = 1;
        }
        catch (NoSuchFieldError var1_2) {}
        try {
            arrn[a.h.ordinal()] = 8;
        }
        catch (NoSuchFieldError var1_1) {}
        l = arrn;
        return arrn;
    }

    private byte b() {
        int n2 = this.e[2] | 2;
        return (byte)(n2 * (n2 ^ 1) >>> 8);
    }

    @Override
    public void close() {
        this.c.close();
        super.close();
    }

    /*
     * Exception decompiling
     */
    @Override
    public int read() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [6[CASE]], but top level block is 8[UNCONDITIONALDOLOOP]
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

    static enum a {
        a,
        b,
        c,
        d,
        e,
        f,
        g,
        h;
        

        private a(String string3, int n3) {
        }
    }

}

