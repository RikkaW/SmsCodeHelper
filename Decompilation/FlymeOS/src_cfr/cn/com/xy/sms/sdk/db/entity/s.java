/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.db.entity.IccidInfo;
import cn.com.xy.sms.sdk.db.entity.IccidInfoManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.a.e;
import cn.com.xy.sms.sdk.db.entity.r;
import cn.com.xy.sms.sdk.db.entity.t;
import cn.com.xy.sms.sdk.db.entity.u;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class s {
    private static String a = "id";
    private static String b = "content";
    private static String c = "t_group";
    private static String d = "tb_update_task";
    private static String e = "t_version";
    private static String f = " DROP TABLE IF EXISTS tb_update_task";
    private static String g = "create table  if not exists tb_update_task ( id INTEGER PRIMARY KEY,content TEXT,t_group TEXT,t_version long )";
    private static String h = "1";
    private static String i = "2";
    private static final int j = 20;

    static /* synthetic */ String a(List list) {
        return s.c(list);
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static List<r> a(String var0, int var1_1) {
        block19 : {
            block18 : {
                if (StringUtils.isNull((String)var0)) {
                    return null;
                }
                var3_2 = DBManager.query(false, "tb_update_task", new String[]{"id", "content", "t_group"}, "t_group = ?", new String[]{var0}, null, null, null, "20");
                if (var3_2 == null) break block18;
                var0 = var3_2;
                var1_1 = var3_2.getCount();
                if (var1_1 > 0) break block19;
            }
            XyCursor.closeCursor(var3_2, true);
            return null;
        }
        var0 = var3_2;
        try {
            var4_6 = new ArrayList();
        }
        catch (Throwable var4_7) lbl-1000: // 2 sources:
        {
            var0 = var3_2;
            var4_8.printStackTrace();
            XyCursor.closeCursor(var3_2, true);
            return null;
        }
        do {
            block20 : {
                var0 = var3_2;
                var2_10 = var3_2.moveToNext();
                if (var2_10) break block20;
                XyCursor.closeCursor(var3_2, true);
                return var4_6;
            }
            var0 = var3_2;
            var5_11 = new r();
            var0 = var3_2;
            var5_11.b = var3_2.getLong(var3_2.getColumnIndex("id"));
            var0 = var3_2;
            var5_11.a = var3_2.getString(var3_2.getColumnIndex("content"));
            var0 = var3_2;
            var5_11.c = var3_2.getString(var3_2.getColumnIndex("t_group"));
            var0 = var3_2;
            var4_6.add(var5_11);
            continue;
            break;
        } while (true);
        catch (Throwable var3_3) {
            var0 = null;
lbl49: // 2 sources:
            do {
                XyCursor.closeCursor((XyCursor)var0, true);
                throw var3_4;
                break;
            } while (true);
        }
        {
            catch (Throwable var3_5) {
                ** continue;
            }
        }
        catch (Throwable var4_9) {
            var3_2 = null;
            ** GOTO lbl-1000
        }
    }

    public static void a(long l2) {
        try {
            DBManager.delete("tb_update_task", "id = ?", new String[]{String.valueOf((long)l2)});
            return;
        }
        catch (Throwable var2_1) {
            var2_1.printStackTrace();
            return;
        }
    }

    public static void a(r r2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("content", r2.a);
            contentValues.put("t_group", r2.c);
            contentValues.put("t_version", Long.valueOf((long)0));
            DBManager.insert("tb_update_task", contentValues);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void a(String string2) {
        try {
            DBManager.delete("tb_update_task", "id IN (" + string2 + ")", null);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void a(String string2, String string3, int n2) {
        try {
            DBManager.insert("tb_update_task", BaseManager.getContentValues(null, "content", string2, "t_group", string3, "t_version", "0"));
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    private static void b(r object) {
        if (object == null) {
            return;
        }
        IccidInfo iccidInfo = IccidInfoManager.queryDeftIccidInfo(Constant.getContext());
        ArrayList<String> arrayList = e.a(object.a);
        t t2 = new t((r)object);
        object = "";
        String string2 = "";
        if (iccidInfo != null) {
            object = iccidInfo.areaCode;
            string2 = iccidInfo.iccid;
        }
        bs.a(arrayList, (String)object, string2, "3", t2, true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void b(String object) {
        synchronized (s.class) {
            block16 : {
                boolean bl2 = StringUtils.isNull((String)object);
                if (!bl2) {
                    int n2;
                    Object object2;
                    if ("1".equals(object)) {
                        if ((object = s.c((String)object)) != null) {
                            IccidInfo iccidInfo = IccidInfoManager.queryDeftIccidInfo(Constant.getContext());
                            ArrayList<String> arrayList = e.a(object.a);
                            t t2 = new t((r)object);
                            object = "";
                            String string2 = "";
                            if (iccidInfo != null) {
                                object = iccidInfo.areaCode;
                                string2 = iccidInfo.iccid;
                            }
                            bs.a(arrayList, (String)object, string2, "3", t2, true);
                        }
                        break block16;
                    }
                    if (!"2".equals(object) || (object2 = s.a((String)object, 20)) == null || (n2 = object2.size()) == 0) break block16;
                    try {
                        if (SysParamEntityManager.getBooleanParam(Constant.getContext(), "smartsms_enhance", true) && XyUtil.checkNetWork(Constant.getContext(), 2) != -1) {
                            object = new u((List)object2);
                            if (!StringUtils.isNull((String)(object2 = s.d(object2)))) {
                                NetUtil.executePubNumServiceHttpRequest((String)object2, "990005", (XyCallBack)object, null, false, false, "pubinfo", true);
                            }
                        }
                    }
                    catch (Throwable var0_2) {
                        try {
                            var0_2.printStackTrace();
                        }
                        catch (Throwable var0_1) {
                            var0_1.printStackTrace();
                        }
                    }
                }
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static void b(List<r> object) {
        if (object == null) return;
        if (object.size() == 0) {
            return;
        }
        try {
            if (!SysParamEntityManager.getBooleanParam(Constant.getContext(), "smartsms_enhance", true)) return;
            if (XyUtil.checkNetWork(Constant.getContext(), 2) == -1) return;
            u u2 = new u((List)object);
            if (StringUtils.isNull((String)(object = s.d(object)))) return;
            NetUtil.executePubNumServiceHttpRequest((String)object, "990005", u2, null, false, false, "pubinfo", true);
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
    private static r c(String var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[TRYBLOCK]], but top level block is 11[SIMPLE_IF_TAKEN]
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

    private static String c(List<r> object) {
        block6 : {
            if (object != null) {
                try {
                    if (object.size() == 0) break block6;
                    StringBuilder stringBuilder = new StringBuilder();
                    object = object.iterator();
                    do {
                        if (!object.hasNext()) {
                            if (stringBuilder.length() > 0) {
                                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                                return stringBuilder.toString();
                            }
                            break;
                        }
                        stringBuilder.append(((r)object.next()).b);
                        stringBuilder.append(",");
                    } while (true);
                }
                catch (Throwable var0_1) {
                    var0_1.printStackTrace();
                }
                return null;
            }
        }
        return null;
    }

    /*
     * Exception decompiling
     */
    private static String d(List<r> var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 3[DOLOOP]
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

