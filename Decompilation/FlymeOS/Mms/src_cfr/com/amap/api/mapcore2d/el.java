/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Looper
 *  android.util.Log
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import com.amap.api.mapcore2d.cz;
import com.amap.api.mapcore2d.dc;
import com.amap.api.mapcore2d.di;
import com.amap.api.mapcore2d.dp;
import com.amap.api.mapcore2d.dr;
import com.amap.api.mapcore2d.ed;
import com.amap.api.mapcore2d.ee;
import com.amap.api.mapcore2d.eg;
import com.amap.api.mapcore2d.ei;
import com.amap.api.mapcore2d.ek;
import com.amap.api.mapcore2d.em;
import com.amap.api.mapcore2d.ep;
import com.amap.api.mapcore2d.ex;
import com.amap.api.mapcore2d.ey;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

abstract class el {
    private ep a;

    protected el(Context context) {
        try {
            this.a = this.a(context, this.a());
            return;
        }
        catch (Throwable var1_2) {
            ed.a(var1_2, "LogProcessor", "LogUpDateProcessor");
            var1_2.printStackTrace();
            return;
        }
    }

    public static el a(Context context, int n2) {
        switch (n2) {
            default: {
                return null;
            }
            case 0: {
                return new eg(context);
            }
            case 1: {
                return new ei(context);
            }
            case 2: 
        }
        return new ee(context);
    }

    private ep a(Context object, String string2) {
        block4 : {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(object.getFilesDir().getAbsolutePath());
            stringBuilder.append(ek.a);
            stringBuilder.append(string2);
            object = new File(stringBuilder.toString());
            if (object.exists() || object.mkdirs()) break block4;
            return null;
        }
        try {
            object = ep.a((File)object, 1, 1, 20480);
            return object;
        }
        catch (IOException var1_2) {
            ed.a(var1_2, "LogProcessor", "initDiskLru");
            var1_2.printStackTrace();
            return null;
        }
        catch (Throwable var1_3) {
            ed.a(var1_3, "LogProcessor", "initDiskLru");
            var1_3.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private String a(List<dr> iterator, Context object) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"pinfo\":\"").append(this.c((Context)object)).append("\",\"els\":[");
        iterator = iterator.iterator();
        boolean bl2 = true;
        while (iterator.hasNext()) {
            object = (dr)iterator.next();
            String string2 = this.c(object.b());
            boolean bl3 = bl2;
            if (string2 != null) {
                if ("".equals((Object)string2)) continue;
                object = string2 + "||" + object.d();
                if (bl2) {
                    bl2 = false;
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append("{\"log\":\"").append((String)object).append("\"}");
                bl3 = bl2;
            }
            bl2 = bl3;
        }
        return null;
    }

    private void a(dp dp2, int n2) {
        try {
            this.a(dp2.a(2, n2), dp2, n2);
            return;
        }
        catch (Throwable var1_2) {
            ed.a(var1_2, "LogProcessor", "processDeleteFail");
            var1_2.printStackTrace();
            return;
        }
    }

    private void a(List<dr> object, dp dp2, int n2) {
        if (object != null && object.size() > 0) {
            object = object.iterator();
            while (object.hasNext()) {
                dr dr2 = (dr)object.next();
                if (this.a(dr2.b())) {
                    dp2.a(dr2.b(), n2);
                    continue;
                }
                dr2.a(2);
                dp2.a(dr2, dr2.a());
            }
        }
    }

    private boolean a(String string2) {
        if (this.a == null) {
            return false;
        }
        try {
            boolean bl2 = this.a.c(string2);
            return bl2;
        }
        catch (IOException var1_2) {
            var1_2.printStackTrace();
            return false;
        }
    }

    private int b(String object) {
        block7 : {
            Log.i((String)"yiyi.qi", (String)object);
            object = new em(di.b(object.getBytes()));
            object = ex.a(false).a((ey)object);
            if (object != null) break block7;
            return 0;
        }
        object = new String((byte[])object);
        try {
            object = new JSONObject((String)object);
            if (object.has("code")) {
                int n2 = object.getInt("code");
                return n2;
            }
        }
        catch (JSONException var1_2) {
            try {
                ed.a((Throwable)var1_2, "LogProcessor", "processUpdate");
                var1_2.printStackTrace();
                return 0;
            }
            catch (cz var1_3) {
                ed.a(var1_3, "LogProcessor", "processUpdate");
                var1_3.printStackTrace();
            }
        }
        return 0;
    }

    private String c(Context object) {
        String string2;
        block4 : {
            string2 = dc.a((Context)object);
            if (!"".equals((Object)string2)) break block4;
            return null;
        }
        try {
            object = dc.b((Context)object, string2.getBytes("UTF-8"));
            return object;
        }
        catch (UnsupportedEncodingException var1_2) {
            ed.a(var1_2, "LogProcessor", "getPublicInfo");
            var1_2.printStackTrace();
            return null;
        }
        catch (Throwable var1_3) {
            ed.a(var1_3, "LogProcessor", "getPublicInfo");
            var1_3.printStackTrace();
            return null;
        }
    }

    /*
     * Exception decompiling
     */
    private String c(String var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [8[TRYBLOCK]], but top level block is 20[CATCHBLOCK]
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

    protected abstract String a();

    protected abstract boolean a(Context var1);

    protected abstract int b();

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    void b(Context object) {
        try {
            if (!this.a((Context)object)) {
                return;
            }
            Looper looper = Looper.getMainLooper();
            // MONITORENTER : looper
        }
        catch (Throwable var1_2) {
            ed.a(var1_2, "LogProcessor", "processUpdateLog");
            var1_2.printStackTrace();
            return;
        }
        dp dp2 = new dp((Context)object);
        this.a(dp2, this.b());
        List<dr> list = dp2.a(0, this.b());
        if (list == null || list.size() == 0) {
            // MONITOREXIT : looper
            return;
        }
        if ((object = this.a(list, (Context)object)) == null) {
            // MONITOREXIT : looper
            return;
        }
        if (this.b((String)object) == 1) {
            this.a(list, dp2, this.b());
        }
        // MONITOREXIT : looper
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    void c() {
        if (this.a == null || this.a.a()) return;
        try {
            this.a.close();
            return;
        }
        catch (IOException var1_1) {
            ed.a(var1_1, "LogProcessor", "closeDiskLru");
            var1_1.printStackTrace();
            return;
        }
        catch (Throwable var1_2) {
            ed.a(var1_2, "LogProcessor", "closeDiskLru");
            var1_2.printStackTrace();
            return;
        }
    }
}

