/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Process
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.Throwable
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.queue;

import android.os.Process;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.n;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.util.i;
import cn.com.xy.sms.sdk.queue.a.a;
import cn.com.xy.sms.sdk.queue.d;
import cn.com.xy.sms.sdk.queue.g;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public final class c
extends Thread {
    private static boolean a = false;

    public static void a() {
        synchronized (c.class) {
            if (!a && NetUtil.checkAccessNetWork(2)) {
                new c().start();
            }
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void a(int var0) {
        if (!NetUtil.checkAccessNetWork(2)) {
            return;
        }
        var9_1 = n.a(1);
        new StringBuilder("JsonArray=").append((Object)var9_1);
        if (var9_1 == null) return;
        if (var9_1.length() <= 0) return;
        var2_3 = var9_1.length();
        var0 = 0;
        while (var0 < var2_3) {
            block12 : {
                var12_11 = var9_1.getJSONObject(var0);
                var10_8 = (String)JsonUtil.getValueFromJsonObject(var12_11, "res_url");
                var13_12 = (Integer)JsonUtil.getValueFromJsonObject(var12_11, "res_version");
                var11_10 = (Long)JsonUtil.getValueFromJsonObject(var12_11, "down_failed_time");
                var12_11 = (Integer)JsonUtil.getValueFromJsonObject(var12_11, "id");
                new StringBuilder(" res_url=").append(var10_8).append(" res_version=").append(var13_12).append(" down_failed_time=").append((Object)var11_10).append("id = ").append((Object)var12_11);
                if (StringUtils.isNull(var10_8)) break block12;
                var3_5 = System.currentTimeMillis();
                var13_12 = "1" + "_" + var13_12 + "_" + var3_5 + ".zip";
                var5_6 = var11_10;
                if (var3_5 <= var5_6 + (var7_7 = DexUtil.getUpdateCycleByType(17, 3600000))) return;
                try {
                    var1_4 = cn.com.xy.sms.sdk.util.d.f(var10_8, Constant.getPath("duoqu_temp"), (String)var13_12);
lbl26: // 2 sources:
                    ** while (var1_4 == 0)
                }
                catch (Throwable var10_9) {
                    var10_9.printStackTrace();
                    new StringBuilder("Throwable=").append(var10_9.getLocalizedMessage());
                    var1_4 = -1;
                    ** GOTO lbl26
                }
lbl-1000: // 1 sources:
                {
                    n.a((Integer)var12_11, true, (String)var13_12);
                    break block12;
lbl30: // 1 sources:
                }
                n.a((Integer)var12_11, false, (String)var13_12);
                return;
                {
                    catch (Throwable var9_2) {
                        var9_2.printStackTrace();
                    }
                }
                return;
            }
            ++var0;
        }
    }

    private static void a(String string2, int n2) {
        try {
            d d2 = new d(n2);
            if (!StringUtils.isNull(string2)) {
                string2 = i.a(string2, n2);
                if (NetUtil.checkAccessNetWork(2)) {
                    NetUtil.executePubNumServiceHttpRequest(string2, "990005", d2, "", true, false, "checkResourseRequest", true);
                }
            }
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Exception decompiling
     */
    private static void b(int var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 14[WHILELOOP]
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
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static void c(int n2) {
        Object object;
        if (!NetUtil.checkAccessNetWork(2)) {
            return;
        }
        if (!a.a(1)) return;
        Object object2 = object = n.b(1);
        if (StringUtils.isNull((String)object)) {
            object2 = "-1";
        }
        try {
            object = new d(1);
            if (StringUtils.isNull((String)object2)) return;
            object2 = i.a((String)object2, 1);
            if (!NetUtil.checkAccessNetWork(2)) return;
            NetUtil.executePubNumServiceHttpRequest((String)object2, "990005", (XyCallBack)object, "", true, false, "checkResourseRequest", true);
            return;
        }
        catch (Throwable var1_3) {
            var1_3.printStackTrace();
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final void run() {
        block25 : {
            block24 : {
                block23 : {
                    this.setName("xiaoyuan_resoursequeue");
                    Process.setThreadPriority((int)g.b);
                    if (c.a) return;
                    c.a = true;
                    Thread.sleep((long)1000);
                    if (!NetUtil.checkAccessNetWork(2) || !a.a(1)) ** GOTO lbl16
                    var11_3 = var12_1 = n.b(1);
                    if (!StringUtils.isNull(var12_1)) break block23;
                    var11_3 = "-1";
                }
                c.a(var11_3, 1);
lbl16: // 2 sources:
                Thread.sleep((long)1000);
                var4_7 = NetUtil.checkAccessNetWork(2);
                if (var4_7) break block24;
lbl20: // 7 sources:
                do {
                    Thread.sleep((long)1000);
                    c.b(1);
lbl24: // 2 sources:
                    do {
                        c.a = false;
                        return;
                        break;
                    } while (true);
                    break;
                } while (true);
            }
            var11_3 = n.a(1);
            new StringBuilder("JsonArray=").append((Object)var11_3);
            if (var11_3 == null) ** GOTO lbl20
            if (var11_3.length() <= 0) ** GOTO lbl20
            var3_8 = var11_3.length();
            var1_9 = 0;
lbl37: // 2 sources:
            if (var1_9 >= var3_8) ** GOTO lbl20
            var14_15 = var11_3.getJSONObject(var1_9);
            var12_1 = (String)JsonUtil.getValueFromJsonObject(var14_15, "res_url");
            var15_16 = (Integer)JsonUtil.getValueFromJsonObject(var14_15, "res_version");
            var13_14 = (Long)JsonUtil.getValueFromJsonObject(var14_15, "down_failed_time");
            var14_15 = (Integer)JsonUtil.getValueFromJsonObject(var14_15, "id");
            new StringBuilder(" res_url=").append(var12_1).append(" res_version=").append(var15_16).append(" down_failed_time=").append((Object)var13_14).append("id = ").append((Object)var14_15);
            if (StringUtils.isNull(var12_1)) break block25;
            var5_11 = System.currentTimeMillis();
            var15_16 = "1" + "_" + var15_16 + "_" + var5_11 + ".zip";
            var7_12 = var13_14;
            if (var5_11 <= var7_12 + (var9_13 = DexUtil.getUpdateCycleByType(17, 3600000))) ** GOTO lbl20
            try {
                var2_10 = cn.com.xy.sms.sdk.util.d.f(var12_1, Constant.getPath("duoqu_temp"), (String)var15_16);
lbl52: // 2 sources:
                ** while (var2_10 == 0)
            }
            catch (Throwable var12_2) {
                var12_2.printStackTrace();
                new StringBuilder("Throwable=").append(var12_2.getLocalizedMessage());
                var2_10 = -1;
                ** GOTO lbl52
            }
lbl-1000: // 1 sources:
            {
                n.a((Integer)var14_15, true, (String)var15_16);
                break block25;
lbl56: // 1 sources:
            }
            n.a((Integer)var14_15, false, (String)var15_16);
            {
                catch (Throwable var11_4) {
                    try {
                        var11_4.printStackTrace();
                        ** continue;
                    }
                    catch (Throwable var11_5) {
                        ** continue;
                    }
                }
            }
lbl73: // 1 sources:
            ** GOTO lbl20
            catch (Throwable var11_6) {
                var11_6.printStackTrace();
                return;
            }
        }
        ++var1_9;
        ** GOTO lbl37
    }
}

