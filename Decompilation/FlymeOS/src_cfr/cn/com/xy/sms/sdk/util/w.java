/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashSet
 */
package cn.com.xy.sms.sdk.util;

import cn.com.xy.sms.sdk.db.entity.p;
import cn.com.xy.sms.sdk.db.entity.v;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Document;

public final class w
extends Thread {
    private static boolean a;
    private static HashSet<String> c;
    private static boolean d;
    private v b;

    static {
        c = new HashSet();
        d = false;
    }

    private w() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static v a() {
        synchronized (w.class) {
            Object object;
            ArrayList arrayList;
            block5 : {
                Iterator iterator = c.iterator();
                Object object2 = null;
                arrayList = new ArrayList();
                do {
                    object = object2;
                    if (iterator == null) break block5;
                    if (!iterator.hasNext()) {
                        object = object2;
                        break block5;
                    }
                    object = (String)iterator.next();
                    object2 = cn.com.xy.sms.sdk.db.entity.w.b((String)object);
                    arrayList.add(object);
                } while (object2 == null);
                object = object2;
            }
            c.removeAll((Collection)arrayList);
            arrayList.clear();
            return object;
        }
    }

    /*
     * Exception decompiling
     */
    private static List<p> a(Document var0) {
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

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(v v2) {
        if (!NetUtil.checkAccessNetWork(2)) {
            return;
        }
        if (v2 == null) {
            d = false;
            return;
        }
        try {
            this.b(v2);
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
        finally {
            try {
                Thread.sleep((long)2000);
            }
            catch (Throwable var1_5) {
                var1_5.printStackTrace();
            }
            this.a(w.b());
        }
    }

    public static void a(String string2) {
        synchronized (w.class) {
            if (!StringUtils.isNull(string2) && !c.contains((Object)string2)) {
                c.add((Object)string2);
            }
            return;
        }
    }

    public static void a(boolean bl2) {
        synchronized (w.class) {
            if (!d) {
                new w().start();
            }
            return;
        }
    }

    private static v b() {
        synchronized (w.class) {
            v v2;
            block5 : {
                v v3;
                v2 = v3 = w.a();
                if (v3 != null) break block5;
                v2 = cn.com.xy.sms.sdk.db.entity.w.b();
            }
            return v2;
            finally {
            }
        }
    }

    /*
     * Exception decompiling
     */
    private void b(v var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK], 7[TRYBLOCK]], but top level block is 15[CATCHBLOCK]
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

    private static void b(String string2) {
        synchronized (w.class) {
            c.remove((Object)string2);
            return;
        }
    }

    private static void b(boolean bl2) {
        synchronized (w.class) {
            d = bl2;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void run() {
        if (!d) {
            w.b(true);
            try {
                Thread.sleep((long)3000);
            }
            catch (Throwable var1_1) {
                var1_1.printStackTrace();
            }
            this.a(w.b());
            w.b(false);
        }
    }
}

