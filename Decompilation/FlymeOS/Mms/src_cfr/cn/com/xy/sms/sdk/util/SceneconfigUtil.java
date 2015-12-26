/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.Arrays
 */
package cn.com.xy.sms.sdk.util;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.SceneRule;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.m;
import cn.com.xy.sms.sdk.db.entity.o;
import cn.com.xy.sms.sdk.db.entity.p;
import cn.com.xy.sms.sdk.db.entity.q;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.queue.i;
import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.t;
import cn.com.xy.sms.sdk.util.u;
import cn.com.xy.sms.sdk.util.z;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SceneconfigUtil {
    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static SceneRule getSceneRule(String var0, int var1_2) {
        var3_3 = SysParamEntityManager.getBooleanParam(Constant.getContext(), "hasImportDrawableData");
        var4_4 /* !! */  = q.a(var0, var1_2);
        new StringBuilder("titleNo =").append(var0).append("hasImportData: ").append(var3_3);
        if (var4_4 /* !! */  == null && !var3_3) ** GOTO lbl42
        var4_4 /* !! */  = ViewUtil.getChannelType() == 7 ? o.a(var0, var1_2, true) : o.a(var0, var1_2, false);
        if (var4_4 /* !! */  == null || var4_4 /* !! */ .isEmpty()) ** GOTO lbl32
        new StringBuilder("titleNo =").append(var0).append("sceneRuleList is not Empty");
        var5_5 = SceneconfigUtil.querySceneRuleByCondition(var4_4 /* !! */ );
        if (var5_5 == null) {
            if (LogManager.debug == false) return null;
            new StringBuilder("titleNo =").append(var0).append(" \u6839\u636e\u6761\u4ef6\u627e\u4e0d\u5230\u5bf9\u5e94\u7684\uff0c\u4e0d\u5f39\u7a97\uff0c\u4e5f\u4e0d\u8bf7\u6c42");
            return null;
        }
        var4_4 /* !! */  = "";
        if (var5_5 != null) {
            var4_4 /* !! */  = var5_5.res_urls;
        }
        if (var5_5.isDownload == 1 || SceneconfigUtil.isResDownloaded((String)var4_4 /* !! */ )) {
            if (LogManager.debug) {
                new StringBuilder("titleNo =").append(var0).append("\u5168\u90e8\u4e0b\u8f7d\u4e86. urls: ").append((String)var4_4 /* !! */ );
            }
            if (var5_5 == null) return var5_5;
            if (var5_5.isDownload != 0) return var5_5;
            if (var5_5 == null) return var5_5;
            try {
                var0 = new ContentValues();
                var0.put("isdownload", Integer.valueOf((int)1));
                DBManager.update("tb_scenerule_config", (ContentValues)var0, "id = ? ", new String[]{var5_5.id});
                return var5_5;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return var5_5;
            }
        }
        if ((var4_4 /* !! */  = m.a((String)var4_4 /* !! */ )) == null || var4_4 /* !! */ .isEmpty()) ** GOTO lbl61
        ** GOTO lbl56
lbl32: // 1 sources:
        new StringBuilder("titleNo =").append(var0).append("\u60c5\u666f\u4e0d\u5b58\u5728\u3002\u9700\u8981\u83b7\u53d6\u60c5\u666f\u914d\u7f6e\u6570\u636e\u3002");
        var4_4 /* !! */  = new p();
        var4_4 /* !! */ .b = "-1";
        var4_4 /* !! */ .a = var0;
        var0 = new ArrayList();
        var0.add(var4_4 /* !! */ );
        var4_4 /* !! */ .d = 0;
        q.a(var4_4 /* !! */ , var1_2);
        SceneconfigUtil.requestScenceconfig(var0, var1_2, false);
        return null;
lbl42: // 1 sources:
        var4_4 /* !! */  = new p();
        var4_4 /* !! */ .b = "-1";
        var4_4 /* !! */ .a = var0;
        var4_4 /* !! */ .d = 0;
        if (XyUtil.checkNetWork(Constant.getContext()) == 0) {
            new StringBuilder("titleNo =").append(var0).append(" \u52a0\u5165\u60c5\u666f\u961f\u5217 wifi\uff0c\u76f4\u63a5\u4e0b\u8f7d");
            var0 = new ArrayList();
            var0.add(var4_4 /* !! */ );
            q.a((p)var4_4 /* !! */ , var1_2);
            SceneconfigUtil.requestScenceconfig(var0, var1_2, false);
            return null;
        }
        new StringBuilder("titleNo =").append(var0).append(" \u52a0\u5165\u60c5\u666f\u961f\u5217");
        q.a((p)var4_4 /* !! */ , var1_2);
        return null;
lbl56: // 1 sources:
        var2_6 = var4_4 /* !! */ .size();
        var1_2 = 0;
        do {
            if (var1_2 >= var2_6) {
                z.a(false);
lbl61: // 2 sources:
                if (LogManager.debug == false) return null;
                new StringBuilder("titleNo =").append(var0).append("urls---\u8fd8\u6ca1\u6709\u5168\u90e8\u4e0b\u8f7d\u4e86");
                return null;
            }
            var5_5 = var4_4 /* !! */ .get(var1_2);
            z.a((String)var5_5);
            if (LogManager.debug) {
                new StringBuilder("titleNo =").append(var0).append(" download urls: ").append((String)var5_5);
            }
            ++var1_2;
        } while (true);
    }

    public static List<String> getUrls(String string2) {
        if (!StringUtils.isNull(string2)) {
            return Arrays.asList((Object[])string2.replaceAll("\uff1b", ";").split(";"));
        }
        return null;
    }

    /*
     * Exception decompiling
     */
    public static void handleSceneUrllist(List<p> var0, ArrayList<String> var1_3, int var2_4) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 9[UNCONDITIONALDOLOOP]
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
    public static void handleSceneconfig(List<p> var0, int var1_2) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 8[WHILELOOP]
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

    public static boolean isResDownloaded(String object) {
        if (!StringUtils.isNull((String)object)) {
            if ((object = Arrays.asList((Object[])object.replaceAll("\uff1b", ";").split(";"))) != null && !object.isEmpty()) {
                int n2 = object.size();
                int n3 = 0;
                do {
                    if (n3 >= n2) {
                        return true;
                    }
                    String string2 = (String)object.get(n3);
                    if (!m.b(string2)) {
                        if (LogManager.debug) {
                            new StringBuilder("url =").append(string2).append("\u8fd8\u6ca1\u4e0b\u8f7d");
                        }
                        return false;
                    }
                    ++n3;
                } while (true);
            }
        } else {
            return true;
        }
        return false;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void postqueryIccidScene() {
        var1 = 1;
        var0_1 = 1;
        try {
            var6_2 = SysParamEntityManager.getLongParam("PostCount", 0, Constant.getContext());
            var2_4 = var4_3 = SysParamEntityManager.getLongParam("LastPostIccidSceneTime", 0, Constant.getContext());
            if (var4_3 == 0) {
                SysParamEntityManager.setParam("LastPostIccidSceneTime", String.valueOf((long)System.currentTimeMillis()));
                var2_4 = System.currentTimeMillis();
            }
            new StringBuilder("System.currentTimeMillis()=").append(System.currentTimeMillis());
            if (Long.valueOf((long)var6_2) != 0) ** GOTO lbl13
            if (System.currentTimeMillis() <= DexUtil.getUpdateCycleByType(12, 1209600000) + var2_4) ** GOTO lbl17
            ** GOTO lbl18
lbl13: // 1 sources:
            var0_1 = System.currentTimeMillis() > DexUtil.getUpdateCycleByType(13, 5184000000L) + var2_4 ? var1 : 0;
            new StringBuilder("time+Constant.postqueryIccidScene=").append(var2_4).append(DexUtil.getUpdateCycleByType(13, 5184000000L));
lbl15: // 2 sources:
            do {
                continue;
                break;
            } while (true);
lbl17: // 1 sources:
            var0_1 = 0;
lbl18: // 2 sources:
            new StringBuilder("time+Constant.FirstpostqueryIccidScene=").append(var2_4).append(DexUtil.getUpdateCycleByType(12, 1209600000));
            ** continue;
            if (var0_1 == 0) return;
            var9_5 = i.a();
            var8_6 = new StringBuffer();
            if (var9_5.isEmpty() != false) return;
            var0_1 = 0;
            do {
                if (var0_1 >= var9_5.size()) {
                    var9_5 = new u();
                    var8_6 = cn.com.xy.sms.sdk.net.util.i.a(StringUtils.getMD5(IccidLocationUtil.getICCID(Constant.getContext())), "1", XyUtil.getImeiAndXinghao(Constant.getContext()), var8_6.toString());
                    if (NetUtil.isEnhance() == false) return;
                    NetUtil.executeLoginBeforeHttpRequest((String)var8_6, "990005", (XyCallBack)var9_5, NetUtil.STATSERVICE_URL, true);
                    return;
                }
                var8_6.append(String.valueOf((Object)((p)var9_5.get((int)var0_1)).a) + "," + ((p)var9_5.get((int)var0_1)).c + ";");
                ++var0_1;
            } while (true);
        }
        catch (Throwable var8_7) {
            var8_7.printStackTrace();
            return;
        }
    }

    public static SceneRule querySceneRuleByCondition(List<SceneRule> list) {
        SceneRule sceneRule;
        SceneRule sceneRule2 = sceneRule = null;
        if (list != null) {
            sceneRule2 = sceneRule;
            if (!list.isEmpty()) {
                sceneRule2 = sceneRule;
                if (list.size() > 0) {
                    sceneRule2 = list.get(0);
                }
            }
        }
        return sceneRule2;
    }

    public static void requestQuerySceneRuleRequest(List<SceneRule> object, int n2) {
        t t2 = new t(n2);
        try {
            object = cn.com.xy.sms.sdk.net.util.i.a(object);
            if (!StringUtils.isNull((String)object) && NetUtil.checkAccessNetWork(2)) {
                NetUtil.executeHttpRequest(0, n2, (String)object, t2, NetUtil.getPopupServiceUrl(), true);
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
    public static void requestScenceconfig(List<p> var0, int var1_3, boolean var2_4) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 10[WHILELOOP]
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

    public static void updateData() {
        long l2 = SysParamEntityManager.getLongParam("LastSceneConfigUpdate", 0, Constant.getContext());
        if (System.currentTimeMillis() > l2 + DexUtil.getUpdateCycleByType(10, 1209600000)) {
            SceneconfigUtil.requestScenceconfig(q.a(0), 0, true);
            SceneconfigUtil.requestScenceconfig(q.a(1), 1, true);
            SysParamEntityManager.setParam("LastSceneConfigUpdate", String.valueOf((long)System.currentTimeMillis()));
        }
        l2 = SysParamEntityManager.getLongParam("LastSceneRuleUpdate", 0, Constant.getContext());
        if (System.currentTimeMillis() > l2 + DexUtil.getUpdateCycleByType(11, 1209600000)) {
            SceneconfigUtil.requestQuerySceneRuleRequest(o.a(0), 0);
            SceneconfigUtil.requestQuerySceneRuleRequest(o.a(1), 1);
            SysParamEntityManager.setParam("LastSceneRuleUpdate", String.valueOf((long)System.currentTimeMillis()));
        }
    }
}

