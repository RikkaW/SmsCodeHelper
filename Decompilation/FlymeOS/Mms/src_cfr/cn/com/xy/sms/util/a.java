/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  org.json.JSONObject
 */
package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.PhoneSmsParseManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.util.ParseSmsToBubbleUtil;
import java.util.List;
import org.json.JSONObject;

public final class a
extends Thread {
    private static final Object a = new Object();
    private static boolean b = false;
    private static boolean c = false;
    private static long j = 0;
    private boolean d = false;
    private String e;
    private int f;
    private int g;
    private int h = 0;
    private boolean i = false;

    private a() {
    }

    private a(boolean bl2, String string2, int n2, int n3, boolean bl3) {
        this.d = bl2;
        this.e = string2;
        this.f = n2;
        this.g = n3;
        this.i = bl3;
        this.setName("before_parse_thread");
    }

    public static void a() {
        Object object = a;
        synchronized (object) {
            b = true;
            return;
        }
    }

    public static void a(boolean bl2, String string2, int n2, int n3, boolean bl3) {
        new a(bl2, string2, n2, n3, bl3).start();
    }

    /*
     * Exception decompiling
     */
    private static boolean b() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[DOLOOP]], but top level block is 0[TRYBLOCK]
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
     */
    private void c() {
        while (!b && j == Thread.currentThread().getId()) {
            List<JSONObject> list;
            long l2;
            long l3 = 0;
            if (this.d) {
                l2 = SysParamEntityManager.getLongParam("BEFORE_HAND_PARSE_SMS_TIME", 0, Constant.getContext());
            } else {
                list = PhoneSmsParseManager.findObjectByPhone(this.e);
                l2 = l3;
                if (list != null) {
                    list = JsonUtil.getValFromJsonObject((JSONObject)list, "maxReceiveTime");
                    l2 = l3;
                    if (list != null) {
                        l2 = Long.valueOf((String)list.toString());
                    }
                }
            }
            l3 = l2;
            if (l2 == 0) {
                l3 = System.currentTimeMillis() + Integer.MAX_VALUE;
            }
            if ((list = DuoquUtils.getSdkDoAction().getReceiveMsgByReceiveTime(this.e, 0, l3, this.f)) == null || list.isEmpty()) break;
            int n2 = list.size();
            int n3 = 0;
            l2 = l3;
            do {
                if (n3 >= n2) {
                    l3 = l2;
                    break;
                }
                JSONObject jSONObject = list.get(n3);
                l3 = Long.valueOf((String)((String)JsonUtil.getValFromJsonObject(jSONObject, "smsReceiveTime")));
                if (l3 < l2) {
                    l2 = l3;
                }
                ParseSmsToBubbleUtil.parseSmsToBubbleResultMap((String)JsonUtil.getValFromJsonObject(jSONObject, "msgId"), (String)JsonUtil.getValFromJsonObject(jSONObject, "phone"), (String)JsonUtil.getValFromJsonObject(jSONObject, "msg"), (String)JsonUtil.getValFromJsonObject(jSONObject, "centerNum"), l3, this.g, this.d, this.i, null);
                if ((n3 + 1) % 10 == 0) {
                    if (this.d) {
                        SysParamEntityManager.setParam("BEFORE_HAND_PARSE_SMS_TIME", String.valueOf((long)l2));
                    }
                    a.sleep((long)1);
                }
                l3 = l2;
                if (b) break;
                l3 = l2;
                if (j != Thread.currentThread().getId()) break;
                ++n3;
            } while (true);
            if (this.d && (n3 != n2 || n2 % 10 != 0)) {
                SysParamEntityManager.setParam("BEFORE_HAND_PARSE_SMS_TIME", String.valueOf((long)l3));
            }
            this.h += n2;
            if (b || !this.d || this.h >= 500 || n2 < this.f) break;
            a.sleep((long)5);
        }
    }

    /*
     * Exception decompiling
     */
    public final void run() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [4[TRYBLOCK]], but top level block is 20[CATCHBLOCK]
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

