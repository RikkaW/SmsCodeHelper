/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.File
 *  java.io.OutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.security.MessageDigest
 *  java.security.SecureRandom
 */
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class anv {
    public static String a(String string2) {
        if ((string2 = (String)anv.b(string2)) == null) {
            return null;
        }
        return anq.a((byte[])string2);
    }

    public static String a(String string2, String string3) {
        try {
            string2 = anq.a(anv.a(1, string3).doFinal(string2.getBytes()));
            return string2;
        }
        catch (Exception var0_1) {
            return "";
        }
    }

    public static String a(byte[] arrby) {
        return anq.a(anv.b(arrby));
    }

    private static Cipher a(int n2, String object) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            object = new DESKeySpec(anv.b(object.getBytes()));
            object = SecretKeyFactory.getInstance("DES").generateSecret((KeySpec)object);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(n2, (Key)object, secureRandom);
            return cipher;
        }
        catch (Exception var1_2) {
            return null;
        }
    }

    /*
     * Exception decompiling
     */
    public static void a(InputStream var0, OutputStream var1_5, String var2_8) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 13[UNCONDITIONALDOLOOP]
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

    /*
     * Exception decompiling
     */
    public static byte[] a(File var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 13[UNCONDITIONALDOLOOP]
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

    public static String b(String string2, String string3) {
        try {
            string2 = new String(anv.a(2, string3).doFinal(anq.a(string2)));
            return string2;
        }
        catch (Exception var0_1) {
            return "";
        }
    }

    public static byte[] b(String string2) {
        return anv.a(new File(string2));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static byte[] b(byte[] arrby) {
        MessageDigest messageDigest;
        byte[] arrby2 = null;
        try {
            messageDigest = MessageDigest.getInstance((String)"MD5");
            if (messageDigest == null) return arrby2;
        }
        catch (NoSuchAlgorithmException var1_3) {
            var1_3.printStackTrace();
            return arrby2;
        }
        messageDigest.update(arrby);
        return messageDigest.digest();
    }
}

