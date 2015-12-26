/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.HashMap
 */
package cn.com.xy.sms.sdk.db.entity.a;

import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.a.b;
import cn.com.xy.sms.sdk.db.entity.a.d;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.i;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;

public final class c {
    private static String a = "id";
    private static String b = "num";
    private static String c = "name";
    private static String d = "cnum";
    private static String e = "mark_time";
    private static final String f = "tb_num_name";
    private static String g = " DROP TABLE IF EXISTS tb_num_name";
    private static String h = "ALTER TABLE tb_num_name ADD COLUMN cnum TEXT ";
    private static String i = "ALTER TABLE tb_num_name ADD COLUMN mark_time LONG DEFAULT 0";

    public static String a() {
        return "CREATE TABLE  IF NOT EXISTS tb_num_name (id INTEGER PRIMARY KEY, num TEXT NOT NULL UNIQUE, name TEXT NOT NULL, cnum TEXT,mark_time  LONG DEFAULT 0)";
    }

    public static String a(String string2) {
        return c.b(string2, false);
    }

    public static String a(String string2, boolean bl2) {
        if (!c.b()) {
            return "";
        }
        return c.b(string2, bl2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void a(String string2, String object, String string3) {
        block8 : {
            Object object2;
            block7 : {
                block6 : {
                    if (StringUtils.isNull(string2) || StringUtils.isNull((String)object)) {
                        return;
                    }
                    try {
                        object2 = c.c(string2, false);
                        if (object2 == null) break block6;
                        if (!StringUtils.isNull(object2.c) && object2.c.indexOf((String)object) != -1 && !StringUtils.isNull(string3) && string3.equals((Object)object2.d)) {
                            DBManager.update("tb_num_name", BaseManager.getContentValues(null, "name", object2.c, "cnum", string3, "mark_time", "1"), "num =? ", new String[]{string2});
                            return;
                        }
                        object2 = object2.c;
                        if (object2.indexOf((String)object) != -1) break block7;
                        if (object2.split(";").length <= 3) {
                            object = String.valueOf((Object)object2) + ";" + (String)object;
                        }
                        break block8;
                    }
                    catch (Throwable var0_1) {
                        var0_1.printStackTrace();
                        return;
                    }
                }
                DBManager.insert("tb_num_name", BaseManager.getContentValues(null, new String[]{"num", string2, "name", object, "cnum", string3, "mark_time", "1"}));
                return;
            }
            object = object2;
        }
        DBManager.update("tb_num_name", BaseManager.getContentValues(null, new String[]{"name", object, "cnum", string3, "mark_time", "1"}), "num =? ", new String[]{string2});
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void a(HashMap<String, String> var0) {
        block10 : {
            block8 : {
                block9 : {
                    block11 : {
                        var5_2 = StringUtils.getPhoneNumberNo86((String)var0.get((Object)"num"));
                        var3_3 = DexUtil.getCorp((String)var0.get((Object)"msg"));
                        var6_5 = (String)var0.get((Object)"cnum");
                        if (StringUtils.isNull(var5_2) || StringUtils.isNull((String)var3_3)) break block8;
                        {
                            catch (Throwable var0_1) {
                                var0_1.printStackTrace();
                                return;
                            }
                        }
                        try {
                            var4_7 = c.c(var5_2, false);
                            if (var4_7 == null) break block9;
                            if (!StringUtils.isNull(var4_7.c) && var4_7.c.indexOf((String)var3_3) != -1 && !StringUtils.isNull(var6_5) && var6_5.equals((Object)var4_7.d)) {
                                DBManager.update("tb_num_name", BaseManager.getContentValues(null, new String[]{"name", var4_7.c, "cnum", var6_5, "mark_time", "1"}), "num =? ", new String[]{var5_2});
                                break block8;
                            }
                            var4_7 = var4_7.c;
                            if (var4_7.indexOf((String)var3_3) != -1) break block10;
                            if (var4_7.split(";").length <= 3) break block11;
                        }
                        catch (Throwable var3_4) {
                            var3_4.printStackTrace();
                            break block8;
                        }
lbl23: // 3 sources:
                        DBManager.update("tb_num_name", BaseManager.getContentValues(null, new String[]{"name", var3_3, "cnum", var6_5, "mark_time", "1"}), "num =? ", new String[]{var5_2});
                        ** GOTO lbl30
                    }
                    var3_3 = String.valueOf((Object)var4_7) + ";" + (String)var3_3;
                    ** GOTO lbl23
                }
                DBManager.insert("tb_num_name", BaseManager.getContentValues(null, new String[]{"num", var5_2, "name", var3_3, "cnum", var6_5, "mark_time", "1"}));
            }
            var1_6 = Long.valueOf((String)((String)var0.get((Object)"smsTime")));
            var1_6 = System.currentTimeMillis() - var1_6;
            if (var1_6 < 0) return;
            if (var1_6 >= 60000) return;
            NetUtil.pool.execute((Runnable)new d(var5_2));
            return;
        }
        var3_3 = var4_7;
        ** GOTO lbl23
    }

    public static b b(String string2) {
        if (!c.b()) {
            return null;
        }
        return c.c(string2, true);
    }

    /*
     * Exception decompiling
     */
    private static String b(String var0, boolean var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 5[SIMPLE_IF_TAKEN]
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
    private static boolean b() {
        boolean bl2 = false;
        int n2 = 0;
        do {
            boolean bl3;
            if (n2 > 0) {
                bl3 = true;
                return SysParamEntityManager.getBooleanParam(Constant.getContext(), "num_name_power", bl3);
            }
            bl3 = bl2;
            if (new String[]{"HUAWEICARD"}[0].equals((Object)i.e)) return SysParamEntityManager.getBooleanParam(Constant.getContext(), "num_name_power", bl3);
            ++n2;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static b c(String var0, boolean var1_1) {
        var3_2 = null;
        var2_3 = new StringBuffer("num");
        var2_3.append(" = ? ");
        if (var1_1) {
            var2_3.append(" and ");
            var2_3.append("mark_time");
            var2_3.append(" = 1 ");
        }
        var2_3 = var2_3.toString();
        if ((var2_3 = DBManager.query("tb_num_name", new String[]{"id", "num", "name", "cnum"}, (String)var2_3, new String[]{var0})) == null) ** GOTO lbl31
        var3_2 = var2_3;
        if (!var2_3.moveToNext()) ** GOTO lbl31
        var3_2 = var2_3;
        var4_7 = new b();
        var3_2 = var2_3;
        var2_3.getInt(var2_3.getColumnIndex("id"));
        var3_2 = var2_3;
        var2_3.getString(var2_3.getColumnIndex("num"));
        var3_2 = var2_3;
        var4_7.c = var2_3.getString(var2_3.getColumnIndex("name"));
        var3_2 = var2_3;
        var4_7.d = var2_3.getString(var2_3.getColumnIndex("cnum"));
        var3_2 = var4_7;
        ** GOTO lbl32
        catch (Throwable var4_8) {
            var2_3 = null;
            ** GOTO lbl35
            catch (Throwable var2_4) {}
            ** GOTO lbl-1000
lbl31: // 2 sources:
            var3_2 = null;
lbl32: // 2 sources:
            XyCursor.closeCursor((XyCursor)var2_3, true);
            c.c(var0);
            return var3_2;
lbl35: // 2 sources:
            do {
                var3_2 = var2_3;
                try {
                    var4_9.printStackTrace();
                }
                catch (Throwable var2_6) {
                    ** GOTO lbl-1000
                }
                XyCursor.closeCursor((XyCursor)var2_3, true);
                c.c(var0);
                return null;
                break;
            } while (true);
        }
        catch (Throwable var4_10) {
            ** continue;
        }
lbl-1000: // 2 sources:
        {
            XyCursor.closeCursor((XyCursor)var3_2, true);
            c.c(var0);
            throw var2_5;
        }
    }

    private static void c(String string2) {
        try {
            DBManager.update("tb_num_name", BaseManager.getContentValues(null, "mark_time", "0"), "num = ?", new String[]{string2});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }
}

