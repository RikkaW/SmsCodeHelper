/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Looper
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.Throwable
 *  java.util.Date
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Looper;
import com.amap.api.mapcore2d.da;
import com.amap.api.mapcore2d.dc;
import com.amap.api.mapcore2d.dh;
import com.amap.api.mapcore2d.dp;
import com.amap.api.mapcore2d.dr;
import com.amap.api.mapcore2d.ds;
import com.amap.api.mapcore2d.ef;
import com.amap.api.mapcore2d.eh;
import com.amap.api.mapcore2d.ej;
import com.amap.api.mapcore2d.eo;
import com.amap.api.mapcore2d.es;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

abstract class en {
    private dh a;

    en() {
    }

    static en a(int n2) {
        switch (n2) {
            default: {
                return null;
            }
            case 0: {
                return new eh();
            }
            case 1: {
                return new ej();
            }
            case 2: 
        }
        return new ef();
    }

    private String a(Context context, dh dh2) {
        return dc.a(context, dh2);
    }

    private String a(Context object, String string2) {
        try {
            object = dc.a((Context)object, string2.getBytes("UTF-8"));
            return object;
        }
        catch (UnsupportedEncodingException var1_2) {
            var1_2.printStackTrace();
            return null;
        }
        catch (Throwable var1_3) {
            var1_3.printStackTrace();
            return null;
        }
    }

    private String a(String string2, String string3, String string4, int n2, String string5, String string6) {
        string2 = new StringBuffer();
        string2.append(string3).append(",").append("\"timestamp\":\"");
        string2.append(string4);
        string2.append("\",\"et\":\"");
        string2.append(n2);
        string2.append("\",\"classname\":\"");
        string2.append(string5);
        string2.append("\",");
        string2.append("\"detail\":\"");
        string2.append(string6);
        string2.append("\"");
        return string2.toString();
    }

    private void a(dp dp2, String string2, String string3, int n2, boolean bl2) {
        dr dr2 = new dr();
        dr2.a(0);
        dr2.b(string2);
        dr2.a(string3);
        dp2.b(dr2, n2);
    }

    /*
     * Exception decompiling
     */
    private boolean a(Context var1_1, String var2_16, String var3_21, String var4_22, dp var5_23) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [18[CATCHBLOCK]], but top level block is 19[CATCHBLOCK]
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

    private String b(Throwable throwable) {
        return throwable.toString();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private List<dh> b(Context var1_1) {
        var2_2 = null;
        var4_6 = Looper.getMainLooper();
        // MONITORENTER : var4_6
        try {
            var1_1 = new ds((Context)var1_1).a();
            // MONITOREXIT : var4_6
            return var1_1;
        }
        catch (Throwable var3_7) {
            var2_2 = var1_1 = var2_2;
            // MONITOREXIT : var4_6
            try {
                throw var3_7;
            }
            catch (Throwable var2_3) {}
            ** GOTO lbl-1000
            catch (Throwable var2_5) {
                var1_1 = null;
            }
lbl-1000: // 2 sources:
            {
                var2_4.printStackTrace();
                return var1_1;
            }
        }
    }

    private String c() {
        return eo.a(new Date().getTime());
    }

    private String c(Context context) {
        return da.e(context);
    }

    protected abstract int a();

    protected abstract es a(dp var1);

    protected abstract String a(String var1);

    protected String a(Throwable object) {
        try {
            object = eo.a((Throwable)object);
            return object;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return null;
        }
    }

    protected abstract String a(List<dh> var1);

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    void a(Context context) {
        int n2;
        List<dh> list = this.b(context);
        if (list == null) return;
        if (list.size() == 0) {
            return;
        }
        if ((list = this.a(list)) == null) return;
        if ("".equals((Object)list)) return;
        String string2 = this.c();
        String string3 = this.a(context, this.a);
        String string4 = this.c(context);
        string2 = this.a(string4, string3, string2, n2 = this.a(), "ANR", (String)((Object)list));
        if (string2 == null) return;
        if ("".equals((Object)string2)) return;
        list = this.a((String)((Object)list));
        string3 = this.a(context, string2);
        string4 = this.b();
        string2 = Looper.getMainLooper();
        // MONITORENTER : string2
        dp dp2 = new dp(context);
        boolean bl2 = this.a(context, (String)((Object)list), string4, string3, dp2);
        this.a(dp2, this.a.a(), (String)((Object)list), n2, bl2);
        // MONITOREXIT : string2
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void a(Context context, Throwable throwable, String string2, String string3) {
        String string4;
        List<dh> list = this.b(context);
        if (list != null && list.size() != 0 && (string4 = this.a(throwable)) != null && !"".equals((Object)string4)) {
            for (dh dh2 : list) {
                if (!this.a(dh2.f(), string4)) continue;
                this.a(dh2);
                String string5 = this.c();
                String string6 = this.a(context, dh2);
                String string7 = this.c(context);
                Object object = this.b(throwable);
                if (object == null || "".equals(object)) break;
                int n2 = this.a();
                StringBuilder stringBuilder = new StringBuilder();
                if (string2 != null) {
                    stringBuilder.append("class:").append(string2);
                }
                if (string3 != null) {
                    stringBuilder.append(" method:").append(string3).append("$").append("<br/>");
                }
                stringBuilder.append(string4);
                String string8 = this.a(string4);
                if ((string5 = this.a(string7, string6, string5, n2, (String)object, stringBuilder.toString())) == null || "".equals((Object)string5)) break;
                string6 = this.a(context, string5);
                string7 = this.b();
                string5 = Looper.getMainLooper();
                synchronized (string5) {
                    object = new dp(context);
                    boolean bl2 = this.a(context, string8, string7, string6, (dp)object);
                    this.a((dp)object, dh2.a(), string8, n2, bl2);
                    continue;
                }
            }
        }
    }

    protected void a(dh dh2) {
        this.a = dh2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected boolean a(String[] arrstring, String string2) {
        int n2;
        int n3;
        if (arrstring == null || string2 == null) {
            return false;
        }
        try {
            n3 = arrstring.length;
            n2 = 0;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return false;
        }
        while (n2 < n3) {
            int n4 = string2.indexOf(arrstring[n2]);
            if (n4 != -1) {
                return true;
            }
            ++n2;
            continue;
        }
        return false;
    }

    protected abstract String b();
}

