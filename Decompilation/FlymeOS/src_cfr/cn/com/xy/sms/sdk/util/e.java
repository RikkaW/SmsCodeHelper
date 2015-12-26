/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.AssetManager
 *  android.content.res.Resources
 *  java.io.File
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashMap
 */
package cn.com.xy.sms.sdk.util;

import android.content.res.AssetManager;
import android.content.res.Resources;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.AirManager;
import cn.com.xy.sms.sdk.db.ParseItemManager;
import cn.com.xy.sms.sdk.db.TrainManager;
import cn.com.xy.sms.sdk.db.c;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.sdk.util.DateUtils;
import cn.com.xy.sms.sdk.util.SceneconfigUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.d;
import cn.com.xy.sms.sdk.util.i;
import cn.com.xy.sms.sdk.util.o;
import cn.com.xy.sms.sdk.util.p;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public final class e {
    public static boolean a = false;
    private static boolean b = false;
    private static final Object c = new Object();
    private static boolean d = false;

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static List<String> a(String string2) {
        ArrayList arrayList = null;
        if (string2 == null) {
            return arrayList;
        }
        String[] arrstring = new String[](string2);
        string2 = arrayList;
        if (!arrstring.exists()) return string2;
        string2 = arrayList;
        if (!arrstring.isDirectory()) return string2;
        arrstring = arrstring.list();
        string2 = arrayList;
        if (arrstring == null) return string2;
        string2 = arrayList;
        if (arrstring.length == 0) return string2;
        arrayList = new ArrayList();
        int n2 = arrstring.length;
        int n3 = 0;
        do {
            string2 = arrayList;
            if (n3 >= n2) return string2;
            string2 = arrstring[n3];
            int n4 = string2.lastIndexOf("_");
            if (n4 != -1) {
                arrayList.add(string2.substring(0, n4));
            }
            ++n3;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static void a() {
        block20 : {
            var1 = e.c;
            // MONITORENTER : var1
            if (!e.b) break block20;
            // MONITOREXIT : var1
            e.b = false;
            e.a = true;
            return;
        }
        e.b = true;
        // MONITOREXIT : var1
        try {
            block22 : {
                block21 : {
                    try {
                        e.b();
                        i.a();
                        SceneconfigUtil.updateData();
                        var1 = SysParamEntityManager.getStringParam(Constant.getContext(), "SCENE_CENSUS_ONLINE");
                        if (!"1".equals(var1)) break block21;
                        var1 = DateUtils.getCurrentTimeString("yyyyMMdd");
                        var2_4 = SysParamEntityManager.getStringParam(Constant.getContext(), "LastSceneCountActionUpdate");
                        var0_5 = var2_4 == null ? true : DateUtils.compareDateString((String)var1, DateUtils.addDays(var2_4, "yyyyMMdd", 1), "yyyyMMdd");
                    }
                    catch (Throwable var1_1) {
                        var1_1.printStackTrace();
                        return;
                    }
                    if (var0_5) {
                        try {
                            var2_4 = o.a((String)var1);
                            if (!StringUtils.isNull(var2_4)) {
                                var1 = new p((String)var1);
                                if (NetUtil.isEnhance()) {
                                    NetUtil.executeLoginBeforeHttpRequest(var2_4, "990005", (XyCallBack)var1, NetUtil.STATSERVICE_URL, true);
                                }
                            }
                            break block22;
                        }
                        catch (Throwable var1_2) {
                            var1_2.printStackTrace();
                        }
                    }
                    ** GOTO lbl41
                }
                if ("2".equals(var1)) {
                    SceneconfigUtil.postqueryIccidScene();
                }
            }
            bs.a();
            return;
        }
        catch (Throwable var1_3) {
            throw var1_3;
        }
        finally {
            e.b = false;
            e.a = true;
        }
    }

    /*
     * Exception decompiling
     */
    static /* synthetic */ void a(File var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [5, 15] lbl127 : TryStatement: try { 10[TRYBLOCK]

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

    /*
     * Enabled aggressive block sorting
     */
    private static void a(String string2, int n2) {
        if (!d.a(String.valueOf((Object)Constant.getDRAWBLE_PATH()) + string2)) return;
        {
            if (n2 == 0) {
                TrainManager.importTrainData(Constant.getContext());
                return;
            } else {
                if (n2 != 1) return;
                {
                    AirManager.importAirData(Constant.getContext());
                    return;
                }
            }
        }
    }

    private static void a(StringBuilder stringBuilder, String string2, String string3) {
        stringBuilder.append("SELECT '");
        stringBuilder.append(string2);
        stringBuilder.append("'name,'");
        stringBuilder.append(string3.trim());
        stringBuilder.append("'version,");
        stringBuilder.append(cn.com.xy.sms.sdk.db.entity.e.c(string2));
        stringBuilder.append(" is_use UNION ALL ");
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static void a(List<String> object, String string2, String string3, String string4, String string5, String string6) {
        if (object != null && object.contains(string5)) {
            d.a(string3, String.valueOf((Object)string5) + "_", ".jar", null);
            d.b(String.valueOf((Object)string5) + "_", ".dex", null);
            DexUtil.removeClassLoaderBySubname(string5);
        }
        if ((object = d.b(String.valueOf((Object)string2) + string4)) == null) {
            return;
        }
        d.c(String.valueOf((Object)string3) + string4);
        d.a(string3, string4, (InputStream)object);
        d.a(string3, String.valueOf((Object)string5) + ".jar", String.valueOf((Object)string5) + "_" + string6 + ".jar");
        if ("parseUtilMain".equals((Object)string5)) {
            DexUtil.init();
            return;
        }
        if (!"OnlineUpdateCycleConfig".equals((Object)string5)) return;
        DexUtil.initOnlineUpdateCycleConfig();
    }

    private static boolean a(String string2, String string3) {
        int n2;
        try {
            n2 = Integer.valueOf((String)string2);
        }
        catch (NumberFormatException var0_2) {
            return true;
        }
        try {
            int n3 = Integer.valueOf((String)string3);
            if (n3 > n2) {
                return true;
            }
        }
        catch (NumberFormatException var0_1) {
            return false;
        }
        return false;
    }

    private static boolean a(HashMap<String, String> hashMap, String string2, String string3) {
        if (hashMap == null || hashMap.isEmpty() || !hashMap.containsKey((Object)string2)) {
            return true;
        }
        return e.a((String)hashMap.get((Object)string2), string3);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static File b(String string2, String string3) {
        String string4 = String.valueOf((Object)string2) + string3 + ".sql";
        File file = new File(string4);
        if (!file.exists() || !file.isFile() || StringUtils.isNull(string4 = StringUtils.getFileMD5(string4)) || StringUtils.isNull(string2 = l.a(string2, String.valueOf((Object)string3) + ".txt")) || !string4.equals((Object)string2)) {
            return null;
        }
        return file;
    }

    /*
     * Exception decompiling
     */
    public static void b() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [14[TRYBLOCK]], but top level block is 16[TRYBLOCK]
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
    private static void b(File var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[TRYBLOCK]], but top level block is 26[UNCONDITIONALDOLOOP]
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
    public static void c() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Started 6 blocks at once
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

    private static void d() {
        XyUtil.unZip(Constant.getContext().getResources().getAssets().open("duoqu_nqsql.zip"), "duoqu_nqsql.zip", Constant.getINITSQL_PATH());
        c.b();
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static void e() {
        String string2;
        block7 : {
            boolean bl2;
            cn.com.xy.sms.sdk.db.entity.d d2 = cn.com.xy.sms.sdk.db.entity.e.a("parseUtilMain");
            String string3 = string2 = null;
            if (d2 != null) {
                string3 = string2;
                if (!StringUtils.isNull(d2.c)) {
                    new StringBuilder("name:").append(d2.b).append(",version:").append(d2.c);
                    string3 = d2.c;
                }
            }
            string2 = d.f("duoqu_parse_version.txt");
            new StringBuilder("localVersion=").append(string3).append(" assetVersion=").append(string2);
            if (StringUtils.isNull(string3) || (bl2 = e.a(string3, string2))) break block7;
            return;
            {
                catch (Throwable throwable) {
                    throwable.printStackTrace();
                    return;
                }
            }
        }
        try {
            SysParamEntityManager.clearOldData(false);
            SysParamEntityManager.setParam("sms_sdk_init", "0");
            XyUtil.unZip(Constant.getContext().getResources().getAssets().open("duoqu_parse.zip"), "parse.zip", Constant.getPARSE_PATH(), true, string2, true);
            ParseItemManager.updateParse(Constant.getContext());
            SysParamEntityManager.setParam("sms_sdk_init", "1");
            return;
        }
        catch (Throwable var1_3) {
            var1_3.printStackTrace();
            return;
        }
    }

    private static void f() {
        String string2 = SysParamEntityManager.getStringParam(Constant.getContext(), "PublicLogoVersion");
        String string3 = d.f("duoqu_publiclogo_version.txt");
        if (!StringUtils.isNull(string2) && !e.a(string2, string3)) {
            return;
        }
        try {
            XyUtil.unZip(Constant.getContext().getResources().getAssets().open("duoqu_publiclogo.zip"), "duoqu_publiclogo.zip", Constant.getPath("duoqu_publiclogo"));
            SysParamEntityManager.setParam("PublicLogoVersion", string3);
            return;
        }
        catch (IOException var0_1) {
            return;
        }
        catch (Throwable var0_2) {
            var0_2.printStackTrace();
            return;
        }
    }

    /*
     * Exception decompiling
     */
    private static void g() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[TRYBLOCK]], but top level block is 7[CATCHBLOCK]
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

    private static void h() {
        String string2 = SysParamEntityManager.getStringParam(Constant.getContext(), "MenuVersion");
        String string3 = d.f("duoqu_nqsql_version.txt");
        if (!StringUtils.isNull(string2) && !e.a(string2, string3)) {
            return;
        }
        try {
            XyUtil.unZip(Constant.getContext().getResources().getAssets().open("duoqu_nqsql.zip"), "duoqu_nqsql.zip", Constant.getINITSQL_PATH());
            c.b();
            SysParamEntityManager.setParam("MenuVersion", string3);
            return;
        }
        catch (IOException var0_1) {
            return;
        }
        catch (Throwable var0_2) {
            var0_2.printStackTrace();
            return;
        }
    }

    /*
     * Exception decompiling
     */
    private static String i() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 7[DOLOOP]
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
    private static HashMap<String, String> j() {
        List<String> list = d.g("duoqu_parse_version.txt");
        if (list == null || list.isEmpty()) {
            return null;
        }
        HashMap<String, String> hashMap = cn.com.xy.sms.sdk.db.entity.e.b();
        HashMap hashMap2 = new HashMap();
        list = list.iterator();
        while (list.hasNext()) {
            String[] arrstring = ((String)list.next()).split("=");
            if (arrstring.length != 2) continue;
            String string2 = arrstring[0];
            arrstring = arrstring[1];
            if (StringUtils.isNull(string2) || StringUtils.isNull((String)arrstring)) continue;
            String string3 = string2.replace((CharSequence)".jar", (CharSequence)"");
            boolean bl2 = hashMap == null || hashMap.isEmpty() || !hashMap.containsKey((Object)string3) ? true : e.a((String)hashMap.get((Object)string3), (String)arrstring);
            if (!bl2) continue;
            hashMap2.put((Object)string2, (Object)arrstring);
        }
        return hashMap2;
    }

    private static boolean k() {
        String string2 = d.f("duoqu_parse_version.txt");
        if (!"-1".equals((Object)string2) && string2.contains((CharSequence)"=")) {
            return false;
        }
        return true;
    }
}

