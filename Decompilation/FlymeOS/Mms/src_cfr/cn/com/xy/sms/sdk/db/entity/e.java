/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.database.sqlite.SQLiteDatabase
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashMap
 */
package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.entity.d;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.util.SdkParamUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public final class e {
    private static int a = 0;
    private static int b = 1;
    private static String c = "id";
    private static String d = "name";
    private static String e = "version";
    private static String f = "url";
    private static String g = "status";
    private static String h = "last_load_time";
    private static String i = "update_time";
    private static String j = "delaystart";
    private static String k = "delayend";
    private static String l = "count";
    private static String m = "tb_jar_list";
    private static String n = "is_use";
    private static int o = 1;
    private static int p = 0;
    private static String q = " DROP TABLE IF EXISTS tb_jar_list";
    private static String r = "create table  if not exists tb_jar_list (id INTEGER PRIMARY KEY,name TEXT,version TEXT,url TEXT,status INTEGER DEFAULT '0',update_time INTEGER DEFAULT '0',delaystart INTEGER DEFAULT '0',delayend INTEGER DEFAULT '0',count INTEGER DEFAULT '0',last_load_time INTEGER DEFAULT '0' ,is_use INTEGER DEFAULT '0')";
    private static String s = "ALTER TABLE tb_jar_list ADD COLUMN is_use INTEGER DEFAULT '0'";

    public static int a(List<String> object) {
        block6 : {
            if (object.size() != 0) break block6;
            return 0;
        }
        try {
            StringBuilder stringBuilder = new StringBuilder();
            object = object.iterator();
            do {
                if (!object.hasNext()) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.setLength(stringBuilder.length() - 1);
                        return DBManager.delete("tb_jar_list", "name IN (" + stringBuilder + ")", null);
                    }
                    break;
                }
                String string2 = (String)object.next();
                if (StringUtils.isNull(string2)) continue;
                stringBuilder.append("'");
                stringBuilder.append(string2.trim());
                stringBuilder.append("',");
            } while (true);
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
        }
        return -1;
    }

    /*
     * Exception decompiling
     */
    public static d a(String var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 8[SIMPLE_IF_TAKEN]
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
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static List<d> a() {
        block9 : {
            var14 = new ArrayList();
            var12_1 = null;
            var11_2 = null;
            try {
                var13_4 = DBManager.query("tb_jar_list", new String[]{"id", "name", "version", "url", "status", "last_load_time", "update_time", "delaystart", "delayend", "count"}, "(is_use = ? or name = ? ) AND length(name) > 7 ", new String[]{"1", "parseUtilMain"});
                if (var13_4 != null) {
                    var11_2 = var13_4;
                    var12_1 = var13_4;
                    if (var13_4.getCount() > 0) {
                        var11_2 = var13_4;
                        var12_1 = var13_4;
                        var0_6 = var13_4.getColumnIndex("id");
                        var11_2 = var13_4;
                        var12_1 = var13_4;
                        var1_7 = var13_4.getColumnIndex("name");
                        var11_2 = var13_4;
                        var12_1 = var13_4;
                        var2_8 = var13_4.getColumnIndex("version");
                        var11_2 = var13_4;
                        var12_1 = var13_4;
                        var3_9 = var13_4.getColumnIndex("url");
                        var11_2 = var13_4;
                        var12_1 = var13_4;
                        var4_10 = var13_4.getColumnIndex("status");
                        var11_2 = var13_4;
                        var12_1 = var13_4;
                        var5_11 = var13_4.getColumnIndex("last_load_time");
                        var11_2 = var13_4;
                        var12_1 = var13_4;
                        var6_12 = var13_4.getColumnIndex("update_time");
                        var11_2 = var13_4;
                        var12_1 = var13_4;
                        var7_13 = var13_4.getColumnIndex("delaystart");
                        var11_2 = var13_4;
                        var12_1 = var13_4;
                        var8_14 = var13_4.getColumnIndex("delayend");
                        var11_2 = var13_4;
                        var12_1 = var13_4;
                        var9_15 = var13_4.getColumnIndex("count");
                        do {
                            var11_2 = var13_4;
                            var12_1 = var13_4;
                            var10_16 = var13_4.moveToNext();
                            if (var10_16) {
                                var11_2 = var13_4;
                                var12_1 = var13_4;
                                var15_17 = new d();
                                var11_2 = var13_4;
                                var12_1 = var13_4;
                                var13_4.getLong(var0_6);
                                var11_2 = var13_4;
                                var12_1 = var13_4;
                                var15_17.b = var13_4.getString(var1_7);
                                var11_2 = var13_4;
                                var12_1 = var13_4;
                                var15_17.c = var13_4.getString(var2_8);
                                var11_2 = var13_4;
                                var12_1 = var13_4;
                                var15_17.d = var13_4.getString(var3_9);
                                var11_2 = var13_4;
                                var12_1 = var13_4;
                                var15_17.f = var13_4.getInt(var4_10);
                                var11_2 = var13_4;
                                var12_1 = var13_4;
                                var13_4.getLong(var5_11);
                                var11_2 = var13_4;
                                var12_1 = var13_4;
                                var15_17.e = var13_4.getLong(var6_12);
                                var11_2 = var13_4;
                                var12_1 = var13_4;
                                var15_17.h = var13_4.getLong(var7_13);
                                var11_2 = var13_4;
                                var12_1 = var13_4;
                                var15_17.i = var13_4.getLong(var8_14);
                                var11_2 = var13_4;
                                var12_1 = var13_4;
                                var13_4.getInt(var9_15);
                                var11_2 = var13_4;
                                var12_1 = var13_4;
                                var14.add(var15_17);
                                continue;
                            }
                            ** GOTO lbl93
                            break;
                        } while (true);
                    }
                }
                break block9;
                catch (Throwable var13_5) {
                    var12_1 = var11_2;
                    var13_5.printStackTrace();
                    XyCursor.closeCursor(var11_2, true);
                    return var14;
                }
            }
            catch (Throwable var11_3) {
                XyCursor.closeCursor(var12_1, true);
                throw var11_3;
            }
        }
        XyCursor.closeCursor(var13_4, true);
        return var14;
    }

    public static void a(String string2, int n2) {
        try {
            long l2 = System.currentTimeMillis();
            ContentValues contentValues = new ContentValues();
            contentValues.put("last_load_time", String.valueOf((long)l2));
            contentValues.put("status", "1");
            DBManager.update("tb_jar_list", contentValues, "name = ? ", new String[]{string2});
            SdkParamUtil.setParamValue(Constant.getContext(), "SMART_DATA_UPDATE_TIME", String.valueOf((long)l2));
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    private static void a(String string2, long l2, long l3) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("update_time", String.valueOf((long)System.currentTimeMillis()));
            contentValues.put("delaystart", String.valueOf((long)l2));
            contentValues.put("delayend", String.valueOf((long)l3));
            DBManager.update("tb_jar_list", contentValues, "name = ? ", new String[]{string2});
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
    public static void a(String var0, String var1_3, int var2_4) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 2[TRYBLOCK]
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

    public static void a(String string2, String string3, String string4, long l2, int n2, long l3, long l4) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("version", string3);
            contentValues.put("url", string4);
            contentValues.put("status", Integer.valueOf((int)0));
            contentValues.put("update_time", String.valueOf((long)l2));
            contentValues.put("delaystart", String.valueOf((long)l3));
            contentValues.put("delayend", String.valueOf((long)l4));
            DBManager.update("tb_jar_list", contentValues, "name = ? ", new String[]{string2});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static HashMap<String, String> b() {
        block9 : {
            var4 = null;
            var3_1 = null;
            var6_3 = new HashMap();
            try {
                var5_4 = DBManager.query("tb_jar_list", new String[]{"name", "version"}, null, null);
                if (var5_4 != null) {
                    var3_1 = var5_4;
                    var4 = var5_4;
                    if (var5_4.getCount() > 0) {
                        var3_1 = var5_4;
                        var4 = var5_4;
                        var0_6 = var5_4.getColumnIndex("name");
                        var3_1 = var5_4;
                        var4 = var5_4;
                        var1_7 = var5_4.getColumnIndex("version");
                        do {
                            var3_1 = var5_4;
                            var4 = var5_4;
                            var2_8 = var5_4.moveToNext();
                            if (var2_8) {
                                var3_1 = var5_4;
                                var4 = var5_4;
                                var6_3.put((Object)var5_4.getString(var0_6), (Object)var5_4.getString(var1_7));
                                continue;
                            }
                            ** GOTO lbl36
                            break;
                        } while (true);
                    }
                }
                break block9;
                catch (Throwable var5_5) {
                    var4 = var3_1;
                    var5_5.printStackTrace();
                    XyCursor.closeCursor(var3_1, true);
                    return var6_3;
                }
            }
            catch (Throwable var3_2) {
                XyCursor.closeCursor(var4, true);
                throw var3_2;
            }
        }
        XyCursor.closeCursor(var5_4, true);
        return var6_3;
    }

    public static void b(String string2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("update_time", String.valueOf((long)System.currentTimeMillis()));
            DBManager.update("tb_jar_list", contentValues, "name = ? ", new String[]{string2});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    private static void b(String string2, int n2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("count", Integer.valueOf((int)(n2 + 1)));
            DBManager.update("tb_jar_list", contentValues, "name = ? ", new String[]{string2});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean b(List<String> var0) {
        var3_8 = null;
        var2_9 = null;
        var2_9 = var4_12 = DBManager.getSQLiteDatabase();
        var3_8 = var4_12;
        var4_12.beginTransaction();
        var2_9 = var4_12;
        var3_8 = var4_12;
        var5_13 = new StringBuilder();
        var2_9 = var4_12;
        var3_8 = var4_12;
        var5_13.append("INSERT INTO tb_jar_list(name,version,is_use) ");
        var2_9 = var4_12;
        var3_8 = var4_12;
        var5_13.append("SELECT tempTb.name,tempTb.version,tempTb.is_use FROM (");
        var2_9 = var4_12;
        var3_8 = var4_12;
        var5_13.append("SELECT tbA.name,ifnull((CASE WHEN ('-1'=tbA.version OR ''=tbA.version) ");
        var2_9 = var4_12;
        var3_8 = var4_12;
        var5_13.append("THEN tbB.version ELSE tbA.version END),'-1')version,");
        var2_9 = var4_12;
        var3_8 = var4_12;
        var5_13.append("ifnull((CASE WHEN 1=tbA.is_use THEN tbA.is_use ELSE tbB.is_use END),0)is_use FROM (");
        var2_9 = var4_12;
        var3_8 = var4_12;
        var5_13.append("{SQL})tbA LEFT JOIN tb_jar_list tbB ON tbA.name=tbB.name)");
        var2_9 = var4_12;
        var3_8 = var4_12;
        var5_13.append("tempTb LEFT JOIN tb_jar_list ON tb_jar_list.name = tempTb.name");
        var2_9 = var4_12;
        var3_8 = var4_12;
        var5_14 = var5_13.toString();
        var2_9 = var4_12;
        var3_8 = var4_12;
        try {
            var0 = var0.iterator();
        }
        catch (Throwable var0_1) {
            var3_8 = var2_9;
            var0_1.printStackTrace();
            if (var2_9 == null) return false;
            var2_9.execSQL("DELETE FROM tb_jar_list WHERE id IN (SELECT min(id) FROM tb_jar_list GROUP BY name HAVING COUNT(id) > 1)");
lbl79: // 2 sources:
            do {
                block38 : {
                    if (!var2_9.inTransaction()) break block38;
                    var2_9.setTransactionSuccessful();
                    var2_9.endTransaction();
                }
lbl85: // 2 sources:
                do {
                    DBManager.close(var2_9);
                    return false;
                    break;
                } while (true);
                break;
            } while (true);
        }
        do {
            block36 : {
                var2_9 = var4_12;
                var3_8 = var4_12;
                var1_15 = var0.hasNext();
                if (var1_15) break block36;
                if (var4_12 == null) return true;
                var4_12.execSQL("DELETE FROM tb_jar_list WHERE id IN (SELECT min(id) FROM tb_jar_list GROUP BY name HAVING COUNT(id) > 1)");
lbl57: // 2 sources:
                do {
                    block37 : {
                        if (!var4_12.inTransaction()) break block37;
                        var4_12.setTransactionSuccessful();
                        var4_12.endTransaction();
                    }
lbl63: // 2 sources:
                    do {
                        DBManager.close(var4_12);
                        return true;
                        break;
                    } while (true);
                    break;
                } while (true);
            }
            var2_9 = var4_12;
            var3_8 = var4_12;
            var4_12.execSQL(var5_14.replace((CharSequence)"{SQL}", (CharSequence)((String)var0.next())));
            continue;
            break;
        } while (true);
        catch (Throwable var0_2) {
            var0_2.printStackTrace();
            ** continue;
        }
        catch (Throwable var0_3) {
            var0_3.printStackTrace();
            ** continue;
        }
        {
            catch (Throwable var0_4) {
                var0_4.printStackTrace();
                ** continue;
            }
            catch (Throwable var0_5) {
                var0_5.printStackTrace();
                ** continue;
            }
            catch (Throwable var0_6) {
                if (var3_8 == null) throw var0_6;
                try {
                    var3_8.execSQL("DELETE FROM tb_jar_list WHERE id IN (SELECT min(id) FROM tb_jar_list GROUP BY name HAVING COUNT(id) > 1)");
                }
                catch (Throwable var2_10) {
                    var2_10.printStackTrace();
                    ** continue;
                }
lbl104: // 2 sources:
                do {
                    block39 : {
                        if (!var3_8.inTransaction()) break block39;
                        var3_8.setTransactionSuccessful();
                        var3_8.endTransaction();
                    }
lbl110: // 2 sources:
                    do {
                        DBManager.close(var3_8);
                        throw var0_6;
                        break;
                    } while (true);
                    break;
                } while (true);
                catch (Throwable var2_11) {
                    var2_11.printStackTrace();
                    ** continue;
                }
            }
        }
    }

    public static int c() {
        try {
            int n2 = DBManager.delete("tb_jar_list", null, null);
            return n2;
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
            return -1;
        }
    }

    public static int c(String string2) {
        if ("parseUtilMain".equals((Object)string2) || "ParseHelper".equals((Object)string2) || "ScenesScanner".equals((Object)string2)) {
            return 1;
        }
        return 0;
    }

    public static int d() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("ParseUtilCasual");
        arrayList.add("ParseUtilEC");
        arrayList.add("ParseUtilFinanceL");
        arrayList.add("ParseUtilFinanceM");
        arrayList.add("ParseUtilFinanceS");
        arrayList.add("ParseUtilLife");
        arrayList.add("ParseUtilMove");
        arrayList.add("ParseUtilTelecom");
        arrayList.add("ParseUtilTravel");
        arrayList.add("ParseUtilUnicom");
        return e.a(arrayList);
    }

    private static int d(String string2) {
        block3 : {
            try {
                if (!StringUtils.isNull(string2)) break block3;
                return 0;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return -1;
            }
        }
        int n2 = DBManager.delete("tb_jar_list", "name = ?", new String[]{string2});
        return n2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean e() {
        var2 = null;
        var2 = var1_1 = DBManager.query("tb_jar_list", new String[]{"name"}, "(is_use = ? or name = ? ) AND length(name) > 7 AND status = ? AND url IS NOT NULL AND url <> '' ", new String[]{"1", "parseUtilMain", "0"}, null, null, null, " 1 ");
        if (var2 == null) ** GOTO lbl17
        var1_1 = var2;
        try {
            var0_3 = var2.getCount();
            ** if (var0_3 <= 0) goto lbl11
        }
        catch (Throwable var3_7) {
            ** continue;
        }
lbl-1000: // 1 sources:
        {
            XyCursor.closeCursor((XyCursor)var2, true);
            return true;
        }
lbl11: // 1 sources:
        ** GOTO lbl17
        catch (Throwable var3_4) {
            var2 = null;
            ** GOTO lbl19
            catch (Throwable var1_2) {}
            ** GOTO lbl-1000
lbl17: // 2 sources:
            XyCursor.closeCursor((XyCursor)var2, true);
            return false;
lbl19: // 2 sources:
            do {
                var1_1 = var2;
                try {
                    LogManager.e("JarSubInfoManager needUpdateJar", "\u5224\u65ad\u662f\u5426\u9700\u8981\u66f4\u65b0\u7b97\u6cd5\u5305\u5f02\u5e38", (Throwable)var3_5);
                }
                catch (Throwable var3_6) {
                    var2 = var1_1;
                    var1_1 = var3_6;
                    break;
                }
                XyCursor.closeCursor((XyCursor)var2, true);
                return false;
                break;
            } while (true);
        }
lbl-1000: // 2 sources:
        {
            XyCursor.closeCursor((XyCursor)var2, true);
            throw var1_1;
        }
    }
}

