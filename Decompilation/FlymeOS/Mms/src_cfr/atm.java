/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.PrintStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.System
 *  java.lang.Throwable
 */
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;

public class atm {
    public static void a(String string2) {
        System.out.println(string2);
    }

    public static void a(String string2, Throwable throwable) {
        System.out.println(string2);
        if (throwable != null) {
            throwable.printStackTrace();
        }
    }

    public static boolean a(StringBuffer stringBuffer) {
        if (stringBuffer != null && stringBuffer.length() != 0) {
            return false;
        }
        return true;
    }

    public static boolean a(Map<?, ?> map) {
        if (map != null && map.size() != 0) {
            return false;
        }
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static final boolean a(String[] arrstring) {
        if (arrstring == null) {
            return true;
        }
        int n2 = arrstring.length;
        int n3 = 0;
        while (n3 < n2) {
            if (!atm.b(arrstring[n3])) {
                return false;
            }
            ++n3;
        }
        return true;
    }

    /*
     * Exception decompiling
     */
    public static String[] a(InputStream var0, String[] var1_4) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [3[CATCHBLOCK]], but top level block is 5[CATCHBLOCK]
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

    public static boolean b(String string2) {
        if (string2 != null && string2.trim().length() != 0) {
            return false;
        }
        return true;
    }
}

