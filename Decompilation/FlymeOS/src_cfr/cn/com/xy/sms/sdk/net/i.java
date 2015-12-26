/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 *  java.net.HttpURLConnection
 */
package cn.com.xy.sms.sdk.net;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.a;
import cn.com.xy.sms.sdk.net.c;
import cn.com.xy.sms.sdk.net.util.k;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.net.HttpURLConnection;

public final class i
extends a {
    public static String d = null;
    public static String e = null;
    private static String f = "HTTP";
    private String g;

    public i(String string2, String string3, String string4, String string5, boolean bl2, XyCallBack xyCallBack, Boolean bl3) {
        super(string2, null, string3, bl2, string5, xyCallBack, bl3);
        this.g = string4;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public final void a(boolean bl2, String string2, HttpURLConnection httpURLConnection) {
        String string3;
        httpURLConnection.addRequestProperty("Content-Type", "text/xml;UTF-8");
        if (!StringUtils.isNull(this.g)) {
            httpURLConnection.addRequestProperty("cnum", this.g);
        }
        String string4 = e;
        if (bl2) {
            httpURLConnection.addRequestProperty("command", "2");
            string3 = string4;
        } else {
            String string5 = SysParamEntityManager.getStringParam(Constant.getContext(), "HTTPTOKEN");
            httpURLConnection.addRequestProperty("command", "1");
            string3 = string4;
            if (!StringUtils.isNull(string5)) {
                string3 = String.valueOf((Object)string4) + string5;
                httpURLConnection.addRequestProperty("token", string5);
            }
        }
        string3 = k.a(d, string3);
        httpURLConnection.addRequestProperty("app-key", e);
        httpURLConnection.addRequestProperty("app-key-sign", string3);
        httpURLConnection.addRequestProperty("compress", "1");
        httpURLConnection.addRequestProperty("loginid", "");
        httpURLConnection.addRequestProperty("sdkversion", NetUtil.APPVERSION);
        if (!StringUtils.isNull(string2)) {
            httpURLConnection.addRequestProperty("cmd", string2);
        }
        this.a(httpURLConnection);
    }

    /*
     * Exception decompiling
     */
    @Override
    public final void run() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 29[SIMPLE_IF_TAKEN]
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

