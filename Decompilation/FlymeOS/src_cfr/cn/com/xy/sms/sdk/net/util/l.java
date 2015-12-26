/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.security.cert.Certificate
 *  java.util.Date
 *  java.util.HashMap
 *  java.util.jar.JarFile
 */
package cn.com.xy.sms.sdk.net.util;

import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.net.util.b;
import cn.com.xy.sms.sdk.util.KeyManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.d;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class l {
    private static String a = "95ad98c4ba9a0ec12a7dca2af77f312bef6fd02580c23fc082b28f1cab03d9d5b7694bd5dd9693a8b6786c9480dfbcc462373bd1b9f5bed66151be80a370465d6516f89e66d6d70ba52a3d063acbe4544a585d62896d953b3269efd345ff888e5ed7f7f7b60c862ca5a27f20ccdba704113a9861fcd91cf3f0fd7115987568d04f444224b3c2436b833ed0439b4fa8c92e938827f360b6a4a070fed4608a46c8a52023fabfd2561bcd4205052254caaffe9a55aa73254537a1a2c0efbcd76254bef3e01902ffee20b0a45b6c8e6beb496c9c3494d263dedf0fff4702ebbfee0cb568da4940b8f5f8c89aa96b2c21e2ff9596e30e26b18e1b563353843ee95787";
    private static HashMap<String, Boolean> b = new HashMap();
    private static HashMap<String, Integer> c = new HashMap();

    /*
     * Enabled aggressive block sorting
     */
    public static Boolean a(String string2) {
        Boolean bl2;
        if (!d.a(string2)) {
            return false;
        }
        Object object = bl2 = (Boolean)b.get((Object)string2);
        if (bl2 != null) return object;
        Constant.getContext();
        object = l.b(string2);
        object = !StringUtils.isNull((String)object) && object.indexOf("95ad98c4ba9a0ec12a7dca2af77f312bef6fd02580c23fc082b28f1cab03d9d5b7694bd5dd9693a8b6786c9480dfbcc462373bd1b9f5bed66151be80a370465d6516f89e66d6d70ba52a3d063acbe4544a585d62896d953b3269efd345ff888e5ed7f7f7b60c862ca5a27f20ccdba704113a9861fcd91cf3f0fd7115987568d04f444224b3c2436b833ed0439b4fa8c92e938827f360b6a4a070fed4608a46c8a52023fabfd2561bcd4205052254caaffe9a55aa73254537a1a2c0efbcd76254bef3e01902ffee20b0a45b6c8e6beb496c9c3494d263dedf0fff4702ebbfee0cb568da4940b8f5f8c89aa96b2c21e2ff9596e30e26b18e1b563353843ee95787") != -1 ? Boolean.valueOf((boolean)true) : Boolean.valueOf((boolean)false);
        b.put((Object)string2, object);
        return object;
    }

    /*
     * Exception decompiling
     */
    public static String a(String var0, String var1_5) {
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

    public static boolean a() {
        KeyManager.initAppKey();
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean a(byte by2) {
        boolean bl2;
        boolean bl3 = false;
        if ("XwIDAQABYUN".equals((Object)KeyManager.channel)) {
            if (by2 == 2) return true;
            bl2 = bl3;
            if (by2 != 4) return bl2;
            return true;
        }
        if ("NQIDAQABCOOL".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("6QIDAQABSTARRYSKY".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("vwIDAQABLIANLUOOS".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("FEhNrwHTXL".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 != 1) return bl2;
            return true;
        }
        if ("1i1BDH2wONE+".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("Oq1QGcwIYUNOS".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            bl2 = bl3;
            if (by2 == 2) return bl2;
            return true;
        }
        if ("j3FIT5mwLETV".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("D6mKXM8MEIZU".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("3GdfMSKwHUAWEI".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("0GCSqGSITOS".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("wupzCqnwGUAIWU".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("XRyvMvZwSMARTISAN".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("dToXA5JQDAKELE".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("p5O4wKmwGIONEE".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("z5N7W51wKINGSUN".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("Cko59T6wSUGAR".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("oWIH+3ZQLEIDIANOS".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("al30zFgQTEST_T".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("gsjHPHwIKOOBEE".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("AjAFrJSQWENTAI".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("JqyMtaHQNUBIA".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("15Du354QGIONEECARD".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("rahtBH7wTCL".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("xU6UT6pwTOS2".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("5Gx84kmwYULONG_COOLPAD".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("tnjdWFeQKTOUCH".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("Uj2pznXQHCT".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("XkXZJmwIPPTV".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("PzqP0ONQTOSWATCH".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if ("VCTyBOSwSmartisan".equals((Object)KeyManager.channel)) {
            bl2 = bl3;
            if (by2 == 1) return bl2;
            return true;
        }
        if (!"5rLWVKgQMEITU_PHONE".equals((Object)KeyManager.channel)) return true;
        bl2 = bl3;
        if (by2 == 1) return bl2;
        return true;
    }

    /*
     * Exception decompiling
     */
    private static Certificate[] a(JarFile var0, JarEntry var1_7) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [21[DOLOOP]], but top level block is 1[TRYBLOCK]
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
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private static String b(String var0) {
        block22 : {
            var0 = var1_5 = new JarFile(var0);
            var2_9 = l.a(var1_5, var1_5.getJarEntry("classes.dex"));
            if (var2_9 == null) break block22;
            var0 = var1_5;
            if (var2_9.length <= 0) break block22;
            var0 = var1_5;
            if (var2_9.length <= 0) break block22;
            var0 = var1_5;
            var2_9 = var2_9[0].getPublicKey().toString();
            var1_5.close();
        }
        var0 = var1_5;
        var1_5.close();
        try {
            var1_5.close();
        }
        catch (Throwable var0_4) {
            ** continue;
        }
lbl20: // 5 sources:
        do {
            return "";
            break;
        } while (true);
        catch (Throwable var2_10) {
            var1_5 = null;
lbl24: // 2 sources:
            var0 = var1_5;
            var2_11.printStackTrace();
            if (var1_5 == null) ** GOTO lbl20
            try {
                var1_5.close();
            }
            catch (Throwable var0_1) {}
            ** GOTO lbl20
        }
        catch (Throwable var1_6) {
            var0 = null;
lbl35: // 2 sources:
            if (var0 != null) {
                var0.close();
            }
lbl38: // 4 sources:
            do {
                throw var1_7;
                break;
            } while (true);
        }
        finally {
            return var2_9;
        }
        {
            catch (Throwable var0_3) {
                ** continue;
            }
        }
        {
            catch (Throwable var1_8) {
                ** GOTO lbl35
            }
        }
        catch (Throwable var2_12) {
            ** GOTO lbl24
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static boolean b(byte by2) {
        if (by2 != 1) return true;
        {
            try {
                if (!b.a(Constant.getContext())) {
                    return false;
                }
                by2 = (byte)new Date().getHours();
                Integer n2 = (Integer)c.get((Object)String.valueOf((int)by2));
                if (n2 == null) {
                    c.clear();
                    n2 = 1;
                } else {
                    int n3 = n2;
                    n2 = n3 + 1;
                }
                c.put((Object)String.valueOf((int)by2), (Object)n2);
                if (n2 <= 300) return true;
                return false;
            }
            catch (Throwable var2_2) {
                var2_2.printStackTrace();
                return false;
            }
        }
    }
}

