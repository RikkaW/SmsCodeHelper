/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.database.sqlite.SQLiteDatabase
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.util;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.c;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.e;
import cn.com.xy.sms.sdk.db.entity.k;
import cn.com.xy.sms.sdk.db.entity.s;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.d;
import cn.com.xy.sms.sdk.util.j;
import cn.com.xy.sms.util.ParseManager;
import cn.com.xy.sms.util.SdkParamUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class i
extends Thread {
    private static boolean a = false;

    public static void a() {
        synchronized (i.class) {
            if (!a) {
                new i().start();
            }
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void a(cn.com.xy.sms.sdk.db.entity.d var0) {
        block15 : {
            var2_3 = System.currentTimeMillis();
            new StringBuilder("\u68c0\u67e5\u4e0b\u8f7d\u65f6\u95f4jarSubInfo.delaystart=").append(var0.h).append("jarSubInfo.delayend=").append(var0.i).append("nowTime=").append(var2_3).append(" jarSubInfo.status=").append(var0.f);
            if (var0.f != 0 || var0.h > var2_3 || var0.i < var2_3) break block15;
            new StringBuilder("\u8d85\u8fc7\u5468\u671f\uff0c\u542f\u52a8\u4e0b\u8f7d name=").append(var0.b).append(" url=").append(var0.d);
            if (StringUtils.isNull(var0.d)) ** GOTO lbl52
            var4_4 = String.valueOf((Object)var0.b) + ".zip";
            var7_5 = String.valueOf((Object)var0.b) + ".sql";
            var6_6 = String.valueOf((Object)var0.b) + ".txt";
            d.f(var0.d, Constant.getFilePath(), var4_4);
            XyUtil.upZipFile(String.valueOf((Object)Constant.getFilePath()) + var4_4, Constant.getPARSE_PATH());
            new StringBuilder("\u6210\u529f\u4e0b\u8f7d").append(var4_4).append("\uff0c\u6210\u529f\u89e3\u538b").append(var4_4);
            var5_7 = String.valueOf((Object)Constant.getPARSE_PATH()) + var7_5;
            if (d.a(var5_7)) {
                var7_5 = StringUtils.getFileMD5(String.valueOf((Object)Constant.getPARSE_PATH()) + var7_5);
                var6_6 = l.a(Constant.getPARSE_PATH(), var6_6);
                if (LogManager.debug) {
                    // empty if block
                }
                if (var7_5.equals((Object)var6_6)) {
                    Constant.getContext();
                    i.a(var5_7);
                }
            }
            var5_7 = var0.b;
            {
                catch (Throwable var0_1) {
                    var0_1.printStackTrace();
                    return;
                }
            }
            try {
                var2_3 = System.currentTimeMillis();
                var6_6 = new ContentValues();
                var6_6.put("last_load_time", String.valueOf((long)var2_3));
                var6_6.put("status", "1");
                DBManager.update("tb_jar_list", (ContentValues)var6_6, "name = ? ", new String[]{var5_7});
                SdkParamUtil.setParamValue(Constant.getContext(), "SMART_DATA_UPDATE_TIME", String.valueOf((long)var2_3));
                ** GOTO lbl38
            }
            catch (Throwable var5_8) {
                block16 : {
                    var5_8.printStackTrace();
lbl38: // 3 sources:
                    d.a(Constant.getPARSE_PATH(), String.valueOf((Object)var0.b) + "_", ".jar", String.valueOf((Object)var0.b) + "_" + var0.c + ".jar");
                    d.b(String.valueOf((Object)var0.b) + "_", ".dex", String.valueOf((Object)var0.b) + "_" + var0.c + ".dex");
                    d.a(Constant.getPARSE_PATH(), String.valueOf((Object)var0.b) + ".jar", String.valueOf((Object)var0.b) + "_" + var0.c + ".jar");
                    if ("parseUtilMain".equals((Object)var0.b)) {
                        DexUtil.init();
                    } else {
                        DexUtil.removeClassLoaderBySubname(var0.b);
                        if (!"OnlineUpdateCycleConfig".equals((Object)var0.b)) break block16;
                        DexUtil.initOnlineUpdateCycleConfig();
                    }
                }
                d.c(String.valueOf((Object)Constant.getFilePath()) + var4_4);
                SysParamEntityManager.setParam("BEFORE_HAND_PARSE_SMS_TIME", String.valueOf((long)System.currentTimeMillis()));
lbl52: // 3 sources:
                var1_9 = ParseManager.getParseVersion(Constant.getContext(), null) + 1;
                SdkParamUtil.setParamValue(Constant.getContext(), "PARSE_VERSION", String.valueOf((int)var1_9));
                if (System.currentTimeMillis() < Constant.lastVersionChangeTime + 600000) return;
                DuoquUtils.getSdkDoAction().parseVersionChange(var1_9);
                Constant.lastVersionChangeTime = System.currentTimeMillis();
                return;
            }
        }
        if (var0.f == 1) {
            if (LogManager.debug == false) return;
            new StringBuilder(String.valueOf((Object)var0.b)).append("\u5df2\u7ecf\u4e0b\u8f7d\uff0c\u4e0d\u9700\u8981\u4e0b\u8f7d url=").append(var0.d);
            return;
        }
        if (LogManager.debug == false) return;
        new StringBuilder("\u5f53\u524d\u65f6\u95f4\u4e0d\u5728\u4e0b\u8f7d\u533a\u57df\u5185\uff0c\u4e0d\u4e0b\u8f7dname=").append(var0.b).append(" url=").append(var0.d);
        return;
        catch (Throwable var0_2) {
            return;
        }
    }

    /*
     * Exception decompiling
     */
    private static void a(String var0) {
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
     * Exception decompiling
     */
    private static void a(String var0, SQLiteDatabase var1_4) {
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
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void a(Map<String, String> var0, XyCallBack var1_2) {
        try {
            i.e();
            var0 = e.a();
            if (var0.isEmpty()) ** GOTO lbl8
            var3_3 = var0.size();
            var2_4 = 0;
            ** GOTO lbl20
lbl8: // 2 sources:
            do {
                if (!c.c(c.c()) && !i.b()) {
                    XyUtil.doXycallBack(var1_2, "1");
                    return;
                }
                XyUtil.doXycallBack(var1_2, "0");
                return;
                break;
            } while (true);
        }
        catch (Throwable var0_1) {
            return;
        }
lbl-1000: // 1 sources:
        {
            var4_5 = var0.get(var2_4);
            new StringBuilder("checkJar =").append(var4_5);
            i.a(var4_5);
            ++var2_4;
lbl20: // 2 sources:
            ** while (var2_4 < var3_3)
        }
lbl21: // 1 sources:
        ** while (true)
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void a(boolean bl2, boolean bl3) {
        if (!NetUtil.isEnhance()) {
            return;
        }
        i.e();
        List<cn.com.xy.sms.sdk.db.entity.d> list = e.a();
        try {
            String string2;
            if (list.isEmpty()) return;
            String string3 = string2 = SysParamEntityManager.getStringParam(Constant.getContext(), "EM_VERSION");
            if (StringUtils.isNull(string2)) {
                string3 = "-1";
            }
            if ((string3 = cn.com.xy.sms.sdk.net.util.i.a(list, k.b(), string3, bl3)) == null) return;
            NetUtil.executeHttpRequest(0, string3, new j(bl2), String.valueOf((Object)NetUtil.getPopupServiceUrl()) + "updatejar", null, false);
            return;
        }
        catch (Throwable var2_5) {
            var2_5.printStackTrace();
            return;
        }
    }

    public static boolean b() {
        try {
            i.e();
            i.a(false, true);
            Object object = e.a();
            if (!object.isEmpty()) {
                object = object.iterator();
                while (object.hasNext()) {
                    int n2;
                    cn.com.xy.sms.sdk.db.entity.d d2 = (cn.com.xy.sms.sdk.db.entity.d)object.next();
                    if (StringUtils.isNull(d2.d) || (n2 = d2.f) != 0) continue;
                    return true;
                }
            }
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
        }
        return false;
    }

    private static void c() {
        try {
            i.a(true, true);
            if (SysParamEntityManager.getIntParam(Constant.getContext(), "ONLINE_UPDATE_SDK") == 0) {
                return;
            }
            s.b("1");
            return;
        }
        catch (Throwable var0) {
            var0.printStackTrace();
            return;
        }
    }

    private static void d() {
        try {
            int n2 = ParseManager.getParseVersion(Constant.getContext(), null) + 1;
            SdkParamUtil.setParamValue(Constant.getContext(), "PARSE_VERSION", String.valueOf((int)n2));
            if (System.currentTimeMillis() >= Constant.lastVersionChangeTime + 600000) {
                DuoquUtils.getSdkDoAction().parseVersionChange(n2);
                Constant.lastVersionChangeTime = System.currentTimeMillis();
            }
            return;
        }
        catch (Throwable var1_1) {
            return;
        }
    }

    private static void e() {
        if (ParseManager.isInitData()) {
            cn.com.xy.sms.sdk.db.entity.d d2 = e.a("parseUtilMain");
            new StringBuilder("parseUtilMain jarSubInfo=").append(d2);
            if (d2 == null) {
                e.a("parseUtilMain", "-1", 1);
                e.a("ParseHelper", "-1", 1);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void run() {
        if (!a) {
            a = true;
            try {
                i.a(true, true);
                int n2 = SysParamEntityManager.getIntParam(Constant.getContext(), "ONLINE_UPDATE_SDK");
                if (n2 != 0) {
                    s.b("1");
                }
            }
            catch (Throwable var2_2) {
                var2_2.printStackTrace();
            }
            a = false;
        }
    }
}

