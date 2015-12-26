/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashMap
 */
package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.util.PopupUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.util.ParseBubbleManager;
import cn.com.xy.sms.util.ParseManager;
import cn.com.xy.sms.util.a;
import cn.com.xy.sms.util.q;
import cn.com.xy.sms.util.r;
import java.util.HashMap;
import java.util.Map;

public class ParseSmsToBubbleUtil {
    public static final byte RETURN_CACHE_SDK_MSG_ID = 1;
    public static final byte RETURN_CACHE_SDK_MSG_VALUE = 2;

    static /* synthetic */ Map a(String string2, String string3, String string4, String string5, long l2, int n2, boolean bl2, boolean bl3, Map map) {
        return ParseSmsToBubbleUtil.b(string2, string3, string4, string5, l2, 3, bl2, bl3, map);
    }

    private static Map<String, Object> a(String object, String string2, String string3, String string4, long l2, Map<String, Object> map, boolean bl2) {
        try {
            object = PopupUtil.parseMsgToSimpleBubbleResult(Constant.getContext(), (String)object, string2, string4, string3, l2, 1, map, bl2);
            return object;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    /*
     * Exception decompiling
     */
    private static Map<String, Object> b(String var0, String var1_2, String var2_3, String var3_4, long var4_5, int var6_6, boolean var7_7, boolean var8_8, Map<String, Object> var9_9) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [3[CASE]], but top level block is 0[TRYBLOCK]
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

    private static Map<String, Object> b(String object, String string2, String string3, String string4, long l2, Map<String, Object> map, boolean bl2) {
        try {
            object = PopupUtil.parseMsgToBubbleCardResult(Constant.getContext(), (String)object, string2, string4, string3, l2, 1, map, bl2);
            return object;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected static void backGroundHandleMapByType(Map<String, String> object, Map<String, Object> map) {
        if (object == null || StringUtils.isNull((String)(object = (String)object.get("handle_type")))) {
            return;
        }
        new r((String)object, map).start();
    }

    protected static void backGroundParseSmsBubble(String string2, String string3, String string4, String string5, long l2, boolean bl2, boolean bl3, Map<String, Object> map) {
        new q(string2, string3, string4, string5, l2, bl2, bl3, map).start();
    }

    public static void beforeHandParseReceiveSms(int n2, int n3) {
        ParseSmsToBubbleUtil.beforeHandParseReceiveSms(null, n2, n3, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void beforeHandParseReceiveSms(String string2, int n2, int n3, boolean bl2) {
        boolean bl3 = StringUtils.isNull(string2);
        a.a(bl3, string2, n2, n3, bl2);
    }

    public static Map<String, Object> parseSmsToBubbleResult(String string2, String string3, String string4, String string5, long l2, int n2, boolean bl2, boolean bl3, HashMap<String, Object> hashMap) {
        return ParseSmsToBubbleUtil.parseSmsToBubbleResultMap(string2, string3, string4, string5, l2, n2, bl2, bl3, XyUtil.changeObjMapToStrMap(hashMap));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static Map<String, Object> parseSmsToBubbleResultMap(String map, String string2, String string3, String string4, long l2, int n2, boolean bl2, boolean bl3, Map<String, String> map2) {
        try {
            map2 = ParseManager.a(Constant.getContext(), string2, string4, string3, l2, map2);
            if (ParseBubbleManager.getParseStatu(map2) == -1) {
                return null;
            }
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return null;
        }
        if (n2 != 4) {
            return ParseSmsToBubbleUtil.b(map, string2, string3, string4, l2, n2, bl2, bl3, map2);
        }
        if (map2 == null) return null;
        HashMap hashMap = new HashMap();
        hashMap.putAll(map2);
        ParseSmsToBubbleUtil.backGroundParseSmsBubble(map, string2, string3, string4, l2, bl2, bl3, map2);
        return hashMap;
    }
}

