/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.os.Environment
 *  android.os.Process
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.Process;
import java.io.File;

public final class ags {
    private Context a = null;
    private boolean b = true;
    private int c = 1270;
    private int d = 310;
    private int e = 4;
    private int f = 200;
    private int g = 1;
    private int h = 0;
    private int i = 0;
    private long j = 0;
    private agr k = null;

    private ags(Context context) {
        this.a = context;
    }

    private static int a(byte[] arrby, int n2) {
        int n3 = 0;
        for (int i2 = 0; i2 < 4; ++i2) {
            n3 += (arrby[i2 + n2] & 255) << (i2 << 3);
        }
        return n3;
    }

    /*
     * Exception decompiling
     */
    protected static ags a(Context var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl19 : TryStatement: try { 3[TRYBLOCK]

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

    private static byte[] a(long l2) {
        byte[] arrby = new byte[8];
        for (int i2 = 0; i2 < 8; ++i2) {
            arrby[i2] = (byte)(l2 >> (i2 << 3) & 255);
        }
        return arrby;
    }

    private static long b(byte[] arrby, int n2) {
        int n3 = 0;
        for (n2 = 0; n2 < 8; ++n2) {
            n3 += (arrby[n2 + 14] & 255) << (n2 << 3);
        }
        return n3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static String b(Context context) {
        File file;
        boolean bl2;
        bl2 = false;
        file = null;
        if (Process.myUid() != 1000) {
            file = agc.a(context);
        }
        try {
            boolean bl3;
            bl2 = bl3 = "mounted".equals((Object)Environment.getExternalStorageState());
        }
        catch (Exception var4_4) {}
        if ((bl2 || !agc.c()) && file != null) {
            return file.getPath();
        }
        return context.getFilesDir().getPath();
    }

    private static byte[] c(int n2) {
        byte[] arrby = new byte[4];
        for (int i2 = 0; i2 < 4; ++i2) {
            arrby[i2] = (byte)(n2 >> (i2 << 3));
        }
        return arrby;
    }

    private void g() {
        long l2 = System.currentTimeMillis() + 28800000;
        if (l2 - this.j > 86400000) {
            this.j = l2 / 86400000 * 86400000;
            this.h = 0;
            this.i = 0;
        }
    }

    protected final void a(int n2) {
        this.g();
        int n3 = n2;
        if (n2 < 0) {
            n3 = 0;
        }
        this.h = n3;
    }

    protected final void a(agr agr2) {
        this.k = agr2;
    }

    protected final boolean a() {
        this.g();
        NetworkInfo networkInfo = ((ConnectivityManager)this.a.getSystemService("connectivity")).getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == 1) {
                if (this.b && this.h < this.c) {
                    return true;
                }
                return false;
            }
            if (this.b && this.i < this.d) {
                return true;
            }
            return false;
        }
        return this.b;
    }

    /*
     * Exception decompiling
     */
    protected final boolean a(String var1_1) {
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

    protected final int b() {
        return this.e;
    }

    protected final void b(int n2) {
        this.g();
        int n3 = n2;
        if (n2 < 0) {
            n3 = 0;
        }
        this.i = n3;
    }

    protected final int c() {
        return this.f;
    }

    protected final int d() {
        return this.g;
    }

    protected final int e() {
        this.g();
        return this.h;
    }

    protected final int f() {
        this.g();
        return this.i;
    }
}

