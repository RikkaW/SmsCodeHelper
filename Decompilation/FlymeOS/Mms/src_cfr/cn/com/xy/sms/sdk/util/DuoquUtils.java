/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashMap
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.util;

import android.app.Activity;
import android.content.Context;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.net.a;
import cn.com.xy.sms.sdk.queue.g;
import cn.com.xy.sms.sdk.queue.i;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.c;
import cn.com.xy.sms.sdk.util.k;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class DuoquUtils {
    private static AbsSdkDoAction a = null;
    private static String b;
    public static k logSdkDoAction;
    public static AbsSdkDoAction sdkAction;

    static {
        sdkAction = null;
        b = "DuoquUtils";
        logSdkDoAction = null;
    }

    public static boolean doAction(Activity activity, String string2, Map<String, String> map) {
        try {
            DuoquUtils.getSdkDoAction().doAction((Context)activity, string2, map);
            DuoquUtils.logAction((Context)activity, string2, map);
            return true;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return false;
        }
    }

    public static boolean doActionContext(Context context, String string2, Map<String, String> map) {
        try {
            DuoquUtils.getSdkDoAction().doAction(context, string2, map);
            DuoquUtils.logAction(context, string2, map);
            return true;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return false;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static boolean doCustomAction(Activity var0, String var1_4, HashMap<String, Object> var2_5) {
        block22 : {
            block24 : {
                block23 : {
                    block21 : {
                        try {
                            if ("call".equalsIgnoreCase((String)var1_4)) {
                                var5_6 = (String)var2_5.get((Object)"phone");
                                var4_9 = var2_5.get((Object)"simIndex");
                                if (StringUtils.isNull(var5_6)) break block21;
                                var1_4 = var4_9;
                                if (var4_9 == null) {
                                    var1_4 = -1;
                                }
                                DuoquUtils.getSdkDoAction().callPhone((Context)var0, var5_6, Integer.valueOf((String)var1_4.toString()));
                                var3_15 = true;
                                break block22;
                            }
                            if ("open_sms".equalsIgnoreCase((String)var1_4) || "open_tongxunlu".equalsIgnoreCase((String)var1_4) || "reply_sms_open".equalsIgnoreCase((String)var1_4)) {
                                var4_10 = (String)var2_5.get((Object)"phoneNum");
                                if (StringUtils.isNull(var4_10)) {
                                    var4_10 = (String)var2_5.get((Object)"phone");
                                }
                                if (!StringUtils.isNull(var4_10)) {
                                    var5_7 = (String)var2_5.get((Object)"msgId");
                                    if (StringUtils.isNull(var5_7)) break block23;
                                    var1_4 = new HashMap();
                                    var1_4.put("msgId", var5_7);
                                    break block24;
                                }
                                new StringBuilder("actionType: ").append((String)var1_4).append(" phoneNum is null");
                                var3_15 = false;
                                break block22;
                            }
                            if ("send_sms".equalsIgnoreCase((String)var1_4)) {
                                var5_8 = (String)var2_5.get((Object)"phoneNum");
                                var6_16 = (String)var2_5.get((Object)"smsCode");
                                if (!StringUtils.isNull(var5_8) && !StringUtils.isNull(var6_16)) {
                                    var4_11 = var2_5.get((Object)"simIndex");
                                    new StringBuilder("actionType: ").append((String)var1_4).append(" phoneNu :").append(var5_8).append(" simIndex: ").append(var4_11);
                                    var1_4 = var4_11 == null ? Integer.valueOf((int)-1) : var4_11;
                                }
                                break block21;
                            }
                            if ("openApp".equalsIgnoreCase((String)var1_4)) {
                                var1_4 = var4_12 = String.valueOf((Object)var2_5.get((Object)"appName"));
                                if (StringUtils.isNull(var4_12)) {
                                    var1_4 = String.valueOf((Object)var2_5.get((Object)"exthend"));
                                }
                                var4_12 = String.valueOf((Object)var2_5.get((Object)"appDownUrl"));
                                if (!StringUtils.isNull((String)var1_4)) {
                                    DuoquUtils.getSdkDoAction().openAppByAppName((Context)var0, (String)var1_4, var4_12);
                                    var3_15 = false;
                                    break block22;
                                }
                                var1_4 = String.valueOf((Object)var2_5.get((Object)"url"));
                                if (StringUtils.isNull((String)var1_4)) break block21;
                                DuoquUtils.getSdkDoAction().openAppByUrl((Context)var0, (String)var1_4, var4_12);
                                var3_15 = false;
                                break block22;
                            }
                            if ("toService".equalsIgnoreCase((String)var1_4)) {
                                var4_13 = new JSONObject(var2_5);
                                if (var4_13.has("actionType")) {
                                    var1_4 = var4_13.getString("actionType");
                                }
                                DuoquUtils.getSdkDoAction().toService((Context)var0, (String)var1_4, var4_13);
                                var3_15 = false;
                                break block22;
                            }
                            if (!"download".equalsIgnoreCase((String)var1_4)) break block21;
                            var1_4 = String.valueOf((Object)var2_5.get((Object)"appName"));
                            var4_14 = String.valueOf((Object)var2_5.get((Object)"url"));
                            DuoquUtils.getSdkDoAction().downLoadApp((Context)var0, (String)var1_4, var4_14, null);
                            var3_15 = false;
                            break block22;
                        }
                        catch (Throwable var0_1) {
                            var0_1.printStackTrace();
                            if (var2_5 == null) return false;
                            var2_5.clear();
                            return false;
                        }
                        catch (Throwable var0_2) {
                            if (var2_5 == null) throw var0_2;
                            var2_5.clear();
                            throw var0_2;
                        }
                    }
                    var3_15 = false;
                    ** GOTO lbl81
                    DuoquUtils.getSdkDoAction().sendSms((Context)var0, var5_8, var6_16, Integer.valueOf((String)var1_4.toString()), null);
                    var3_15 = true;
                    ** GOTO lbl81
                }
                var1_4 = null;
            }
            DuoquUtils.getSdkDoAction().openSms((Context)var0, var4_10, (Map<String, String>)var1_4);
            var3_15 = false;
        }
        if (var3_15 && var2_5 != null) {
            try {
                if (!var2_5.containsKey((Object)"keepActivity") && var0 != null && !var0.isFinishing()) {
                    var0.finish();
                }
            }
            catch (Throwable var0_3) {
                var0_3.printStackTrace();
            }
        }
        if (var2_5 == null) return var3_15;
        var2_5.clear();
        return var3_15;
    }

    /*
     * Exception decompiling
     */
    public static String getCode(int var0, int var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.CannotPerformDecode: reachable test BLOCK was exited and re-entered.
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.getFarthestReachableInRange(Misc.java:143)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:385)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:65)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:422)
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
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Map<String, Object> getLogMap(Map<String, Object> map) {
        HashMap hashMap = new HashMap();
        if (map == null) return hashMap;
        try {
            if (!map.containsKey("logkey")) return hashMap;
            hashMap.put("logkey", map.get("logkey"));
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
            return hashMap;
        }
        return hashMap;
    }

    public static k getLogSdkDoAction() {
        if (logSdkDoAction != null) {
            return logSdkDoAction;
        }
        return null;
    }

    public static String getPid() {
        return a.a(true);
    }

    public static AbsSdkDoAction getSdkDoAction() {
        if (sdkAction != null) {
            return sdkAction;
        }
        if (a == null) {
            a = new c();
        }
        String string2 = b;
        return a;
    }

    public static String getXid() {
        return cn.com.xy.sms.sdk.net.util.i.a();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void logAction(Context object, String object2, Map<String, String> map) {
        try {
            object = map.get("action");
            int n2 = !StringUtils.isNull((String)object) ? DexUtil.getActionCode((String)object) : -1;
            object2 = new JSONObject(StringUtils.decode((String)object2));
            object = "";
            if (object2.has("titleNo")) {
                object = object2.getString("titleNo");
            }
            object2 = object;
            if (StringUtils.isNull((String)object)) {
                object2 = "00000000";
            }
            if (n2 != -1) {
                g.a(new i(5, new String[]{"titleNo", object2, "type", String.valueOf((int)n2)}));
            }
            DuoquUtils.getSdkDoAction().statisticAction((String)object2, n2, null);
            return;
        }
        catch (Throwable var0_1) {
            return;
        }
    }
}

