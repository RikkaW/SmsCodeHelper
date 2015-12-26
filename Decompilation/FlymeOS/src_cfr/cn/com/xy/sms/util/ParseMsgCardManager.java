/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashMap
 *  org.json.JSONObject
 */
package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.b.a;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.util.PopupUtil;
import cn.com.xy.sms.sdk.util.q;
import cn.com.xy.sms.util.ParseBubbleManager;
import cn.com.xy.sms.util.ParseManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ParseMsgCardManager {
    /*
     * Enabled aggressive block sorting
     */
    private static boolean a(Map<String, String> map) {
        if (map == null || !map.containsKey("isNeedRes")) {
            return true;
        }
        return "true".equalsIgnoreCase(map.get("isNeedRes"));
    }

    /*
     * Exception decompiling
     */
    private static JSONObject b(Map<String, Object> var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[UNCONDITIONALDOLOOP]], but top level block is 1[CATCHBLOCK]
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
    private static JSONObject c(Map<String, Object> var0) {
        block7 : {
            if (var0 == null) ** GOTO lbl27
            try {
                var2_3 = (String)var0.get("title_num");
                var3_4 = a.c(var2_3);
                if (var3_4 == null || var3_4.isEmpty()) break block7;
                var1_5 = true;
lbl7: // 2 sources:
                while (var1_5 && PopupUtil.isPopupAble(var0, var2_3)) {
                    var0.putAll(var3_4);
                    var0 = ParseMsgCardManager.b(var0);
                }
            }
            catch (Throwable var0_1) {
                try {
                    var0_1.printStackTrace();
                }
                catch (Throwable var0_2) {
                    throw var0_2;
                }
                finally {
                    q.a();
                }
lbl20: // 2 sources:
                do {
                    return null;
                    break;
                } while (true);
            }
            {
                q.a();
                return var0;
                break;
            }
        }
        var1_5 = false;
        ** GOTO lbl7
lbl27: // 2 sources:
        q.a();
        ** while (true)
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static JSONObject parseMsgForCard(Context jSONObject, String map, String string2, String string3, Map<String, String> map2) {
        block6 : {
            if (jSONObject == null) {
                throw new Exception(" Context is null.");
            }
            if (map == null) {
                throw new Exception(" phoneNumber is null.");
            }
            if (string3 == null) {
                throw new Exception(" smsContent is null.");
            }
            try {
                jSONObject = ParseManager.a((Context)jSONObject, map, string2, string3, 0, map2);
                if (jSONObject == null) return null;
                if (ParseBubbleManager.getParseStatu(jSONObject) == -1) return null;
                map = DexUtil.handerBubbleValueMap(jSONObject);
                if (map == null) return null;
                if (map.isEmpty()) {
                    return null;
                }
                break block6;
            }
            catch (Throwable var0_1) {
                // empty catch block
            }
            return null;
        }
        boolean bl2 = map2 == null ? true : (!map2.containsKey("isNeedRes") ? true : "true".equalsIgnoreCase(map2.get("isNeedRes")));
        if (!bl2) return ParseMsgCardManager.b(map);
        return ParseMsgCardManager.c(jSONObject);
    }
}

