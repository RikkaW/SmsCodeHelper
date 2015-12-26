/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.HashMap
 *  java.util.HashSet
 *  java.util.concurrent.Executors
 *  org.json.JSONObject
 */
package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.sdk.queue.e;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.v;
import cn.com.xy.sms.util.ParseManager;
import cn.com.xy.sms.util.ParseSmsToBubbleUtil;
import cn.com.xy.sms.util.SdkCallBack;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public class ParseRichBubbleManager {
    private static long a;
    private static long b;
    private static ExecutorService c;
    public static boolean isBusy;

    static {
        isBusy = false;
        a = 21600000;
        b = 1296000000;
        c = Executors.newFixedThreadPool((int)1);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void addEffectiveBubbleData(String map, String string2, JSONObject jSONObject) {
        void var2_3;
        if (var2_3 == null || v.a(map) == null) {
            return;
        }
        map = v.g;
        synchronized (map) {
            v.g.put(string2, (JSONObject)var2_3);
            try {
                if (var2_3.has("msg_num_md5")) {
                    var2_3.remove("msg_num_md5");
                }
            }
            catch (Throwable var3_4) {
                var3_4.printStackTrace();
            }
        }
        map = v.f;
        synchronized (map) {
            v.f.remove(string2);
        }
        map = v.h;
        synchronized (map) {
            v.h.remove((Object)string2);
        }
        map = v.i;
        synchronized (map) {
            v.i.remove((Object)string2);
        }
        try {
            var2_3.remove("viewPartParam");
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void addInvalidBubbleData(String map, String string2) {
        if (string2 != null || v.a(map) == null) {
            return;
        }
        map = v.h;
        synchronized (map) {
            v.h.add((Object)string2);
        }
        map = v.f;
        synchronized (map) {
            v.f.remove(string2);
        }
        map = v.g;
        synchronized (map) {
            v.g.remove(string2);
        }
        map = v.i;
        synchronized (map) {
            v.i.remove((Object)string2);
            return;
        }
    }

    public static void clearCacheBubbleData(String string2) {
        v.c(string2);
        BusinessSmsMessage.emptyObj = null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void deleteBubbleDataFromCache(String object, String string2) {
        try {
            if (StringUtils.isNull((String)object)) {
                v.e(string2);
            }
            if ((object = v.a((String)object)) == null) return;
        }
        catch (Throwable var0_1) {
            return;
        }
        object.d(string2);
    }

    public static void loadBubbleDataByPhoneNum(String string2) {
        v.b(string2);
        Map<String, JSONObject> map = v.g;
        synchronized (map) {
            ParseRichBubbleManager.clearCacheBubbleData(string2);
            string2 = StringUtils.getPhoneNumberNo86(string2);
            v.b(string2, MatchCacheManager.loadDataByParam("phoneNum=?", new String[]{string2}));
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Map<String, Object> parseMsgToSimpleBubbleResult(Context object, String string2, String string3, String string4, String string5, byte by2, Map<String, String> map) {
        if (!l.a(11)) {
            return null;
        }
        string4 = ParseManager.parseMsgToBubble((Context)object, string3, string4, string5, map);
        if (by2 == 1) {
            long l2 = 0;
            if (!StringUtils.isNull(string5 = MatchCacheManager.getMD5(string3, string5))) {
                string3 = StringUtils.getPhoneNumberNo86(string3);
                object = string4 == null ? "" : string4;
                l2 = MatchCacheManager.insertOrUpdate(BaseManager.getContentValues(null, new String[]{"msg_num_md5", string5, "phonenum", string3, "msg_id", string2, "session_reuslt", object, "save_time", String.valueOf((long)System.currentTimeMillis()), "session_lasttime", String.valueOf((long)System.currentTimeMillis())}), 1);
            }
            if (string4 == null) return null;
            object = new HashMap();
            object.put("CACHE_SDK_MSG_ID", l2);
            object.put("CACHE_SDK_MSG_RESULT", string4);
            return object;
        }
        if (string4 == null) return null;
        object = new HashMap();
        object.put("CACHE_SDK_MSG_RESULT", string4);
        return object;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void pubBubbleData(String map, Map<String, JSONObject> map2, boolean bl2) {
        v.b(map);
        map = v.f;
        synchronized (map) {
            void var2_2;
            if (var2_2 != false) {
                v.a();
            }
            if (map2 != null) {
                v.f.putAll(map2);
                LogManager.e("duoqu_xiaoyuan", "pubBubbleData data size: " + map2.size(), null);
            } else {
                LogManager.e("duoqu_xiaoyuan", "pubBubbleData data size is null ", null);
            }
            return;
        }
    }

    public static JSONObject queryBubbleDataFromApi(String object, String string2, String string3, String string4, long l2, HashMap<String, Object> hashMap) {
        if ((object = ParseSmsToBubbleUtil.parseSmsToBubbleResult((String)object, string2, string3, string4, l2, 3, true, true, hashMap)) != null) {
            return (JSONObject)object.get("BUBBLE_JSON_RESULT");
        }
        return null;
    }

    public static JSONObject queryBubbleDataFromDb(String string2, String string3, String string4, long l2) {
        if ((string2 = MatchCacheManager.getDataByParam(string2)) != null) {
            String string5 = (String)JsonUtil.getValueFromJsonObject((JSONObject)string2, "msg_num_md5");
            string3 = MatchCacheManager.getMD5(string3, string4);
            if (string5 != null && string5.equals((Object)string3)) {
                return string2;
            }
        }
        return null;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static JSONObject queryDataByMsgItem(String var0, String var1_1, String var2_2, String var3_3, int var4_4, long var5_5) {
        block10 : {
            block9 : {
                if (var0 == null) throw new Exception("msgid or phoneNum or smsContent is null but they need value.");
                if (var1_1 == null) throw new Exception("msgid or phoneNum or smsContent is null but they need value.");
                if (var2_2 == null) {
                    throw new Exception("msgid or phoneNum or smsContent is null but they need value.");
                }
                if (StringUtils.isPhoneNumber(var1_1)) {
                    return null;
                }
                v.b(var1_1);
                var10_6 = v.g;
                var10_6 = var11_8 = v.g.get(var0);
                if (var11_8 != null) return var10_6;
                if (var11_8 == null && v.h.contains((Object)var0)) {
                    return null;
                }
                var10_6 = var11_8;
                if (var11_8 != null) ** GOTO lbl49
                var10_6 = v.f;
                var10_6 = var11_8 = v.f.get(var0);
                if (var11_8 == null) ** GOTO lbl49
                var10_6 = (String)JsonUtil.getValueFromJsonObject((JSONObject)var11_8, "msg_num_md5");
                var12_9 = MatchCacheManager.getMD5(var1_1, var2_2);
                if (var12_9 == null || var10_6 == null || !var12_9.equals(var10_6)) ** GOTO lbl41
                if (var4_4 != 2) ** GOTO lbl44
                var10_6 = (String)JsonUtil.getValueFromJsonObject((JSONObject)var11_8, "bubble_result");
                try {
                    var8_10 = Long.valueOf((String)JsonUtil.getValueFromJsonObject((JSONObject)var11_8, "bubble_lasttime").toString());
                    if (!StringUtils.isNull((String)var10_6)) {
                        if (System.currentTimeMillis() - var8_10 >= DexUtil.getUpdateCycleByType(22, ParseRichBubbleManager.b)) break block9;
                        var10_6 = new JSONObject((String)var10_6);
                        v.g.put(var0, (JSONObject)var10_6);
                        var7_11 = 1;
                        break block10;
                    }
                    if (System.currentTimeMillis() - var8_10 < DexUtil.getUpdateCycleByType(23, ParseRichBubbleManager.a)) {
                        v.h.add((Object)var0);
                        var7_11 = 2;
                    }
                    break block9;
                }
                catch (Throwable var10_7) {
                    var10_7.printStackTrace();
                    v.h.add((Object)var0);
                    var7_11 = 2;
                }
                ** GOTO lbl46
lbl41: // 1 sources:
                var7_11 = 3;
                v.h.remove((Object)var0);
                ** GOTO lbl46
            }
            var7_11 = 0;
        }
        v.f.remove(var0);
        ** GOTO lbl51
lbl49: // 2 sources:
        var7_11 = 0;
        var11_8 = var10_6;
lbl51: // 2 sources:
        if (var7_11 == true) return var11_8;
        if (v.i.contains((Object)var0)) {
            return null;
        }
        v.i.add((Object)var0);
        e.a(var7_11, var0, var1_1, var2_2, var3_3, var4_4, var5_5, (JSONObject)var11_8);
        return var11_8;
    }

    /*
     * Exception decompiling
     */
    public static void queryDataByMsgItem(String var0, String var1_1, String var2_2, long var3_3, String var5_4, int var6_5, SdkCallBack var7_6, boolean var8_7, HashMap<String, Object> var9_8) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl68 : TryStatement: try { 1[TRYBLOCK]

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
}

