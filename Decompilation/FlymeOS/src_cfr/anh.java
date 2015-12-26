/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.FileInputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.security.MessageDigest
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class anh {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String a(InputStream object) {
        try {
            int n2;
            byte[] arrby = new byte[1024];
            MessageDigest messageDigest = MessageDigest.getInstance((String)"MD5");
            while ((n2 = object.read(arrby)) > 0) {
                messageDigest.update(arrby, 0, n2);
            }
            return anh.a(messageDigest.digest());
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static String a(String var0) {
        block18 : {
            var3_3 = null;
            var0 = var1_4 = new FileInputStream(var0);
            var2_9 = var0 = (var2_9 = anh.a((InputStream)var1_4));
            if (var1_4 == null) break block18;
            try {
                var1_4.close();
                var2_9 = var0;
            }
            catch (IOException var1_7) {
                return var0;
            }
        }
        do {
            return var2_9;
            break;
        } while (true);
        catch (IOException var2_10) {
            var1_4 = null;
lbl15: // 2 sources:
            var0 = var1_4;
            var2_9.printStackTrace();
            var2_9 = var3_3;
            if (var1_4 == null) ** continue;
            try {
                var1_4.close();
                return null;
            }
            catch (IOException var0_1) {
                return null;
            }
        }
        catch (Throwable var1_5) {
            var0 = null;
lbl27: // 2 sources:
            if (var0 != null) {
                var0.close();
            }
lbl30: // 4 sources:
            do {
                throw var1_6;
                break;
            } while (true);
        }
        {
            catch (IOException var0_2) {
                ** continue;
            }
        }
        {
            catch (Throwable var1_8) {
                ** GOTO lbl27
            }
        }
        catch (IOException var2_11) {
            ** GOTO lbl15
        }
    }

    /*
     * Exception decompiling
     */
    public static String a(String var0, int var1_6) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [4[TRYBLOCK]], but top level block is 25[WHILELOOP]
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

    public static String a(byte[] arrby) {
        int n2 = 0;
        int n3 = arrby.length;
        char[] arrc = new char[n3 << 1];
        for (int i2 = 0; i2 < n3; ++i2) {
            int n4 = n2 + 1;
            arrc[n2] = a[(arrby[i2] & 240) >>> 4];
            n2 = n4 + 1;
            arrc[n4] = a[arrby[i2] & 15];
        }
        return new String(arrc);
    }
}

