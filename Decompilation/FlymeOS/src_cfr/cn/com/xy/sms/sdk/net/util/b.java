/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.io.File
 *  java.io.FileOutputStream
 *  java.io.Reader
 *  java.lang.Boolean
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.HashMap
 */
package cn.com.xy.sms.sdk.net.util;

import android.content.Context;
import cn.com.xy.sms.sdk.net.util.c;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

final class b {
    private static long a = -1;
    private static boolean b = false;
    private static boolean c = false;

    b() {
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static void a(Context var0, boolean var1_4) {
        var4_5 = null;
        var3_6 = null;
        var2_9 = var4_5;
        b.a = System.currentTimeMillis();
        var2_9 = var4_5;
        b.b = var1_4;
        var2_9 = var4_5;
        var0 = new File((Object)var0.getApplicationContext().getFilesDir() + File.separator + "check.log");
        var2_9 = var4_5;
        if (var0.exists()) {
            var2_9 = var4_5;
            var0.delete();
        }
        var2_9 = var4_5;
        var0.createNewFile();
        var2_9 = var4_5;
        var0 = new FileOutputStream((File)var0, false);
        var0.write(("checkEnable=" + var1_4 + "\n").getBytes());
        var0.write(("checkTime=" + System.currentTimeMillis() + "\n").getBytes());
        var0.flush();
        var0.close();
        return;
        {
            catch (Throwable var0_3) {
                var0_3.printStackTrace();
                return;
            }
        }
        catch (Throwable var2_10) {
            var0 = var3_6;
            var3_6 = var2_10;
            ** GOTO lbl37
            catch (Throwable var3_7) {
                var2_9 = var0;
                var0 = var3_7;
                ** GOTO lbl-1000
            }
            catch (Throwable var3_8) {}
lbl37: // 2 sources:
            var2_9 = var0;
            try {
                var3_6.printStackTrace();
                if (var0 == null) return;
            }
            catch (Throwable var0_2) lbl-1000: // 2 sources:
            {
                if (var2_9 == null) throw var0;
                try {
                    var2_9.close();
                }
                catch (Throwable var2_11) {
                    var2_11.printStackTrace();
                    throw var0;
                }
                throw var0;
            }
            try {
                var0.close();
                return;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    static boolean a(Context context) {
        if (a == -1) {
            Map<String, String> map = b.c(context);
            if (map != null) {
                b = Boolean.valueOf((String)map.get("checkEnable"));
                a = Long.valueOf((String)map.get("checkTime"));
            } else {
                a = 0;
                b = true;
            }
        }
        if (!b || a == 0 || a + 86400000 < System.currentTimeMillis()) {
            new c(context).start();
        }
        if (a == 0) {
            return true;
        }
        if (!b) {
            throw new Exception(" PLEASE CHECK NETWORK IS OK.");
        }
        return b;
    }

    /*
     * Exception decompiling
     */
    static /* synthetic */ void b(Context var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [27, 30] lbl118 : TryStatement: try { 20[TRYBLOCK]

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

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static Map<String, String> c(Context var0) {
        var2_4 = null;
        try {
            var0 = new File((Object)var0.getApplicationContext().getFilesDir() + File.separator + "check.log");
            if (!var0.exists()) {
                var0.createNewFile();
            }
            var1_8 = new LineNumberReader((Reader)new FileReader((File)var0));
            var2_4 = null;
        }
        catch (Throwable var0_3) {
            var1_8 = var2_4;
lbl48: // 2 sources:
            do {
                if (var1_8 != null) {
                    var1_8.close();
                }
                do {
                    throw var0;
                    break;
                } while (true);
                catch (Throwable var1_9) {
                    var1_9.printStackTrace();
                    throw var0;
                }
                break;
            } while (true);
        }
        do {
            block24 : {
                var0 = var1_8;
                var4_11 = var1_8.readLine();
                if (var4_11 != null) break block24;
                var1_8.close();
                return var2_4;
            }
            var3_10 = var2_4;
            if (var2_4 == null) {
                var0 = var1_8;
                var3_10 = new HashMap();
            }
            var0 = var1_8;
            var2_4 = var4_11.split("=");
            var0 = var1_8;
            var3_10.put(var2_4[0], var2_4[1]);
            var2_4 = var3_10;
            continue;
            break;
        } while (true);
        catch (Throwable var2_5) lbl-1000: // 2 sources:
        {
            block25 : {
                var0 = var1_8;
                var2_4.printStackTrace();
                if (var1_8 == null) break block25;
                var1_8.close();
            }
            do {
                return null;
                break;
            } while (true);
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return var2_4;
        }
        {
            catch (Throwable var0_2) {
                var0_2.printStackTrace();
                return null;
            }
        }
        {
            catch (Throwable var2_6) {
                var1_8 = var0;
                var0 = var2_6;
                ** continue;
            }
        }
        catch (Throwable var2_7) {
            var1_8 = null;
            ** GOTO lbl-1000
        }
    }

    /*
     * Exception decompiling
     */
    private static void d(Context var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 22[CATCHBLOCK]
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
}

