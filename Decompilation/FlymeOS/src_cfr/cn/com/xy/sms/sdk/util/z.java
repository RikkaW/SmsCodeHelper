/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  java.io.File
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.text.SimpleDateFormat
 *  java.util.ArrayList
 *  java.util.HashSet
 */
package cn.com.xy.sms.sdk.util;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.l;
import cn.com.xy.sms.sdk.db.entity.m;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.d;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.zip.ZipException;

public final class z
extends Thread {
    private static boolean b;
    private static HashSet<String> d;
    private static boolean e;
    private int a = 0;
    private l c;

    static {
        d = new HashSet();
        e = false;
    }

    private z() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static l a() {
        synchronized (z.class) {
            Object object;
            ArrayList arrayList;
            block5 : {
                Iterator iterator = d.iterator();
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
                    object2 = m.c((String)object);
                    arrayList.add(object);
                } while (object2 == null);
                object = object2;
            }
            d.removeAll((Collection)arrayList);
            arrayList.clear();
            return object;
        }
    }

    /*
     * Exception decompiling
     */
    private void a(l var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [0, 16] lbl51 : TryStatement: try { 9[TRYBLOCK]

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

    public static void a(String string2) {
        synchronized (z.class) {
            if (!StringUtils.isNull(string2) && !d.contains((Object)string2)) {
                d.add((Object)string2);
            }
            return;
        }
    }

    public static void a(boolean bl2) {
        synchronized (z.class) {
            if (!e) {
                new z().start();
            }
            return;
        }
    }

    /*
     * Exception decompiling
     */
    private boolean a(String var1_1, File var2_12) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [9[TRYBLOCK]], but top level block is 42[UNCONDITIONALDOLOOP]
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

    private static l b() {
        synchronized (z.class) {
            l l2;
            block5 : {
                l l3;
                l2 = l3 = z.a();
                if (l3 != null) break block5;
                l2 = m.b();
            }
            return l2;
            finally {
            }
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private void b(l var1_1) {
        var3_6 = false;
        var6_7 = new File(String.valueOf((Object)new StringBuilder(String.valueOf((Object)Constant.getFilePath())).append("ziptemp").toString()) + File.separator);
        if (!var6_7.exists()) {
            var6_7.mkdirs();
        }
        new StringBuilder("filePath =").append(var6_7.getAbsolutePath());
        var7_8 = var1_1.c;
        var6_7 = new File(var6_7, var7_8.substring(var7_8.lastIndexOf(47) + 1));
        if (var6_7.exists()) {
            var6_7.delete();
        }
        var2_11 = var3_6;
        if (LogManager.debug) {
            new StringBuilder("\u4e0b\u8f7d\u65f6\u95f4=").append(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format((Object)System.currentTimeMillis())).append(" \u4e0b\u8f7durl=").append(NetUtil.prex).append(var1_1.c).append(" scene_id=").append(this.c.b).append(" id=").append(this.c.a);
            var2_11 = var3_6;
        }
        block13 : do {
            if (this.a < 2 && !var2_11) ** GOTO lbl31
            if (!var2_11) ** GOTO lbl29
            XyUtil.upZipFile(var6_7, Constant.getDRAWBLE_PATH());
            var4_12 = this.c.a;
            var1_1 = this.c.c;
            var1_1 = new ContentValues();
            var1_1.put("status", Integer.valueOf((int)1));
            DBManager.update("tb_res_download", (ContentValues)var1_1, "id = ? ", new String[]{String.valueOf((long)var4_12)});
lbl26: // 2 sources:
            do {
                d.a(var6_7);
lbl29: // 2 sources:
                return;
                break;
            } while (true);
lbl31: // 2 sources:
            var7_8 = String.valueOf((Object)NetUtil.prex) + var1_1.c;
            new StringBuilder("dUrl =").append(var7_8).append(" saveFilePath: ").append((Object)var6_7);
            var2_11 = var3_6 = this.a(var7_8, var6_7);
            break;
        } while (true);
        catch (Throwable var7_10) {
            ++this.a;
            ** continue;
        }
lbl-1000: // 2 sources:
        {
            do {
                try {
                    Thread.sleep((long)(this.a * 4000));
                }
                catch (InterruptedException var7_9) {
                    var7_9.printStackTrace();
                }
                continue block13;
                break;
            } while (true);
        }
        {
            catch (Throwable var1_2) {
                try {
                    var1_2.printStackTrace();
                    ** continue;
                }
                catch (ZipException var1_3) {
                    var1_3.printStackTrace();
                    d.a(var6_7);
                    return;
                }
                catch (IOException var1_4) {
                    var1_4.printStackTrace();
                    d.a(var6_7);
                    return;
                }
                catch (Throwable var1_5) {
                    var1_5.printStackTrace();
                    d.a(var6_7);
                    return;
                }
            }
        }
    }

    private static void b(String string2) {
        synchronized (z.class) {
            d.remove((Object)string2);
            return;
        }
    }

    private static void b(boolean bl2) {
        synchronized (z.class) {
            e = bl2;
            return;
        }
    }

    private static String c() {
        return String.valueOf((Object)Constant.getFilePath()) + "ziptemp";
    }

    private static String c(l object) {
        object = object.c;
        return object.substring(object.lastIndexOf(47) + 1);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void run() {
        if (!e) {
            z.b(true);
            try {
                Thread.sleep((long)3000);
            }
            catch (Throwable var1_1) {
                var1_1.printStackTrace();
            }
            this.a(z.b());
            z.b(false);
        }
    }
}

