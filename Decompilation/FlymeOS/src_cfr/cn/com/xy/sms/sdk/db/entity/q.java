/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashMap
 */
package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.entity.p;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class q {
    private static int a = 0;
    private static int b = 1;
    private static int c = 0;
    private static int d = 1;
    private static String e = "scene_id";
    private static String f = "sceneVersion";
    private static String g = "sceneType";
    private static String h = "tb_scene_config";
    private static String i = "isCheck";
    private static String j = "isUse";
    private static String k = "useCount";
    private static String l = " DROP TABLE IF EXISTS tb_scene_config";
    private static String m = "create table  if not exists tb_scene_config (scene_id TEXT,sceneType INTEGER DEFAULT '0',isCheck INTEGER DEFAULT '0',sceneVersion TEXT,isUse INTEGER DEFAULT '0',useCount INTEGER DEFAULT '0')";
    private static String n = "ALTER TABLE tb_scene_config ADD COLUMN sceneType INTEGER DEFAULT '-1'";
    private static String o = "ALTER TABLE tb_scene_config ADD COLUMN isCheck INTEGER DEFAULT '0'";
    private static String p = "ALTER TABLE tb_scene_config ADD COLUMN useCount INTEGER DEFAULT '0'";
    private static String q = "ALTER TABLE tb_scene_config ADD COLUMN isUse INTEGER DEFAULT '0'";

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static long a(p var0, int var1_3) {
        var2_4 = -1;
        var7_5 = null;
        var8_6 = null;
        var4_7 = null;
        var6_8 = var7_5;
        var5_9 = var8_6;
        try {
            if (!StringUtils.isNull(var0.a)) {
                var6_8 = var7_5;
                var5_9 = var8_6;
                var4_7 = var0.a;
                var6_8 = var7_5;
                var5_9 = var8_6;
                var9_10 = String.valueOf((int)var1_3);
                var6_8 = var7_5;
                var5_9 = var8_6;
                var4_7 = DBManager.query("tb_scene_config", new String[]{"scene_id"}, "scene_id = ?  and sceneType = ?", new String[]{var4_7, var9_10});
            }
            var6_8 = var4_7;
            var5_9 = var4_7;
            var7_5 = new ContentValues();
            var6_8 = var4_7;
            var5_9 = var4_7;
            if (!StringUtils.isNull(var0.b)) {
                var6_8 = var4_7;
                var5_9 = var4_7;
                var7_5.put("sceneVersion", var0.b);
            }
            var6_8 = var4_7;
            var5_9 = var4_7;
            var7_5.put("scene_id", var0.a);
            var6_8 = var4_7;
            var5_9 = var4_7;
            var7_5.put("sceneType", Integer.valueOf((int)var1_3));
            var6_8 = var4_7;
            var5_9 = var4_7;
            var7_5.put("isCheck", Integer.valueOf((int)var0.d));
            if (var4_7 == null) ** GOTO lbl-1000
            var6_8 = var4_7;
            var5_9 = var4_7;
            if (var4_7.getCount() > 0) {
                var6_8 = var4_7;
                var5_9 = var4_7;
                DBManager.update("tb_scene_config", (ContentValues)var7_5, "scene_id = ? and sceneType = ?", new String[]{var0.a, String.valueOf((int)var1_3)});
            } else lbl-1000: // 2 sources:
            {
                var6_8 = var4_7;
                var5_9 = var4_7;
                var2_4 = DBManager.insert("tb_scene_config", (ContentValues)var7_5);
            }
            XyCursor.closeCursor((XyCursor)var4_7, true);
            return var2_4;
            catch (Throwable var0_1) {
                var5_9 = var6_8;
                var0_1.printStackTrace();
                XyCursor.closeCursor((XyCursor)var6_8, true);
                return -1;
            }
        }
        catch (Throwable var0_2) {
            XyCursor.closeCursor((XyCursor)var5_9, true);
            throw var0_2;
        }
    }

    private static ContentValues a(p p2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("useCount", Integer.valueOf((int)p2.c));
        contentValues.put("isUse", Integer.valueOf((int)p2.e));
        return contentValues;
    }

    /*
     * Exception decompiling
     */
    public static p a(String var0, int var1_2) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 8[UNCONDITIONALDOLOOP]
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
    public static List<p> a(int var0) {
        block9 : {
            var8_1 = null;
            var7_2 = null;
            var9_3 = new ArrayList();
            if (var0 != 1) ** GOTO lbl10
            var5_4 = var7_2;
            var4_6 = var8_1;
            try {
                var6_7 = "sceneType = " + var0 + " and isUse" + " = 1";
                ** GOTO lbl11
lbl10: // 1 sources:
                var6_7 = "sceneType != 1 and isUse = 1";
lbl11: // 2 sources:
                var5_4 = var7_2;
                var4_6 = var8_1;
                if ((var6_7 = DBManager.query("tb_scene_config", new String[]{"scene_id", "sceneVersion", "useCount"}, (String)var6_7, null)) != null) {
                    var5_4 = var6_7;
                    var4_6 = var6_7;
                    if (var6_7.getCount() > 0) {
                        var5_4 = var6_7;
                        var4_6 = var6_7;
                        var0 = var6_7.getColumnIndex("scene_id");
                        var5_4 = var6_7;
                        var4_6 = var6_7;
                        var1_9 = var6_7.getColumnIndex("sceneVersion");
                        var5_4 = var6_7;
                        var4_6 = var6_7;
                        var2_10 = var6_7.getColumnIndex("useCount");
                        do {
                            var5_4 = var6_7;
                            var4_6 = var6_7;
                            var3_11 = var6_7.moveToNext();
                            if (var3_11) {
                                var5_4 = var6_7;
                                var4_6 = var6_7;
                                var7_2 = new p();
                                var5_4 = var6_7;
                                var4_6 = var6_7;
                                var7_2.a = var6_7.getString(var0);
                                var5_4 = var6_7;
                                var4_6 = var6_7;
                                var7_2.b = var6_7.getString(var1_9);
                                var5_4 = var6_7;
                                var4_6 = var6_7;
                                var7_2.c = var6_7.getInt(var2_10);
                                var5_4 = var6_7;
                                var4_6 = var6_7;
                                var9_3.add(var7_2);
                                continue;
                            }
                            ** GOTO lbl58
                            break;
                        } while (true);
                    }
                }
                break block9;
                catch (Throwable var6_8) {
                    var4_6 = var5_4;
                    var6_8.printStackTrace();
                    XyCursor.closeCursor((XyCursor)var5_4, true);
                    return var9_3;
                }
            }
            catch (Throwable var5_5) {
                XyCursor.closeCursor((XyCursor)var4_6, true);
                throw var5_5;
            }
        }
        XyCursor.closeCursor((XyCursor)var6_7, true);
        return var9_3;
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static List<p> a(int var0, int var1_1) {
        block17 : {
            var7_2 = null;
            var6_3 = null;
            var8_4 = new ArrayList();
            if (var0 != 1) ** GOTO lbl35
            var4_5 = var6_3;
            var3_7 = var7_2;
            var5_8 = "sceneType = " + var0 + " and isCheck" + " = 0";
lbl9: // 2 sources:
            do {
                var4_5 = var6_3;
                var3_7 = var7_2;
                var5_8 = DBManager.query("tb_scene_config", new String[]{"scene_id", "sceneVersion"}, (String)var5_8, null);
                if (var5_8 == null) ** GOTO lbl-1000
                var4_5 = var5_8;
                var3_7 = var5_8;
                if (var5_8.getCount() <= 0) ** GOTO lbl-1000
                var4_5 = var5_8;
                var3_7 = var5_8;
                var0 = var5_8.getColumnIndex("scene_id");
                var4_5 = var5_8;
                var3_7 = var5_8;
                var1_1 = var5_8.getColumnIndex("sceneVersion");
lbl27: // 2 sources:
                var4_5 = var5_8;
                var3_7 = var5_8;
                var2_10 = var5_8.moveToNext();
                ** if (var2_10) goto lbl-1000
lbl-1000: // 3 sources:
                {
                    XyCursor.closeCursor((XyCursor)var5_8, true);
                    return var8_4;
                }
lbl-1000: // 1 sources:
                {
                    break block17;
                }
                break;
            } while (true);
lbl35: // 1 sources:
            var4_5 = var6_3;
            var3_7 = var7_2;
            var5_8 = "sceneType != 1 and isCheck = 0";
            ** continue;
        }
        var4_5 = var5_8;
        var3_7 = var5_8;
        var6_3 = new p();
        var4_5 = var5_8;
        var3_7 = var5_8;
        var6_3.a = var5_8.getString(var0);
        var4_5 = var5_8;
        var3_7 = var5_8;
        var6_3.b = var5_8.getString(var1_1);
        var4_5 = var5_8;
        var3_7 = var5_8;
        try {
            var8_4.add(var6_3);
            ** GOTO lbl27
        }
        catch (Throwable var5_9) {
            var3_7 = var4_5;
            try {
                var5_9.printStackTrace();
            }
            catch (Throwable var4_6) {
                XyCursor.closeCursor((XyCursor)var3_7, true);
                throw var4_6;
            }
            XyCursor.closeCursor((XyCursor)var4_5, true);
            return var8_4;
        }
    }

    public static void a() {
        try {
            DBManager.delete("tb_scene_config", null, null);
            return;
        }
        catch (Throwable var0) {
            var0.printStackTrace();
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(String var0) {
        block10 : {
            var6_3 = new p();
            var6_3.a = var0;
            var6_3.c = 1;
            var4_4 = null;
            var5_5 = null;
            var0 = null;
            var3_6 = var4_4;
            var2_7 = var5_5;
            try {
                if (!StringUtils.isNull(var6_3.a)) {
                    var3_6 = var4_4;
                    var2_7 = var5_5;
                    var0 = var6_3.a;
                    var3_6 = var4_4;
                    var2_7 = var5_5;
                    var0 = DBManager.query("tb_scene_config", new String[]{"scene_id", "isUse", "useCount"}, "scene_id = ? ", new String[]{var0});
                }
                if (var0 != null) {
                    var3_6 = var0;
                    var2_7 = var0;
                    if (var0.getCount() > 0) {
                        do {
                            var3_6 = var0;
                            var2_7 = var0;
                            var1_8 = var0.moveToNext();
                            if (var1_8) {
                                var3_6 = var0;
                                var2_7 = var0;
                                var6_3.e = 1;
                                var3_6 = var0;
                                var2_7 = var0;
                                var6_3.c = 0;
                                var3_6 = var0;
                                var2_7 = var0;
                                var4_4 = q.a(var6_3);
                                var3_6 = var0;
                                var2_7 = var0;
                                new StringBuilder("values:").append(var4_4);
                                var3_6 = var0;
                                var2_7 = var0;
                                DBManager.update("tb_scene_config", (ContentValues)var4_4, "scene_id = ? ", new String[]{String.valueOf((Object)var6_3.a)});
                                continue;
                            }
                            ** GOTO lbl53
                            break;
                        } while (true);
                    }
                }
                break block10;
                catch (Throwable var0_1) {
                    var2_7 = var3_6;
                    var0_1.printStackTrace();
                    XyCursor.closeCursor((XyCursor)var3_6, true);
                    return;
                }
            }
            catch (Throwable var0_2) {
                XyCursor.closeCursor((XyCursor)var2_7, true);
                throw var0_2;
            }
        }
        XyCursor.closeCursor((XyCursor)var0, true);
    }

    public static void a(String string2, int n2, int n3) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("scene_id", string2);
            contentValues.put("sceneType", Integer.valueOf((int)n2));
            contentValues.put("isCheck", Integer.valueOf((int)1));
            DBManager.update("tb_scene_config", contentValues, "scene_id = ? and sceneType = ?", new String[]{string2, String.valueOf((int)n2)});
            return;
        }
        catch (Throwable var0_1) {
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void a(String var0, String var1_3, int var2_4) {
        var6_5 = null;
        var7_6 = null;
        var3_7 = null;
        var5_8 = var6_5;
        var4_9 = var7_6;
        try {
            if (!StringUtils.isNull(var0)) {
                var5_8 = var6_5;
                var4_9 = var7_6;
                var3_7 = String.valueOf((int)var2_4);
                var5_8 = var6_5;
                var4_9 = var7_6;
                var3_7 = DBManager.query("tb_scene_config", new String[]{"scene_id"}, "scene_id = ? and sceneType = ?", new String[]{var0, var3_7});
            }
            var5_8 = var3_7;
            var4_9 = var3_7;
            var6_5 = new ContentValues();
            var5_8 = var3_7;
            var4_9 = var3_7;
            var6_5.put("scene_id", var0);
            var5_8 = var3_7;
            var4_9 = var3_7;
            var6_5.put("sceneType", Integer.valueOf((int)var2_4));
            var5_8 = var3_7;
            var4_9 = var3_7;
            if (!StringUtils.isNull(var1_3)) {
                var5_8 = var3_7;
                var4_9 = var3_7;
                var6_5.put("sceneVersion", var1_3);
            }
            if (var3_7 == null) ** GOTO lbl-1000
            var5_8 = var3_7;
            var4_9 = var3_7;
            if (var3_7.getCount() > 0) {
                var5_8 = var3_7;
                var4_9 = var3_7;
                DBManager.update("tb_scene_config", var6_5, "scene_id = ? and sceneType = ?", new String[]{var0, String.valueOf((int)var2_4)});
            } else lbl-1000: // 2 sources:
            {
                var5_8 = var3_7;
                var4_9 = var3_7;
                DBManager.insert("tb_scene_config", var6_5);
            }
            XyCursor.closeCursor((XyCursor)var3_7, true);
            return;
            catch (Throwable var0_1) {
                var4_9 = var5_8;
                var0_1.printStackTrace();
                XyCursor.closeCursor((XyCursor)var5_8, true);
                return;
            }
        }
        catch (Throwable var0_2) {
            XyCursor.closeCursor((XyCursor)var4_9, true);
            throw var0_2;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a(HashMap<String, String> var0) {
        block10 : {
            var0 = (String)var0.get((Object)"titleNo");
            var7_3 = new p();
            var7_3.a = var0;
            var7_3.c = 1;
            var5_4 = null;
            var6_5 = null;
            var0 = null;
            var4_6 = var5_4;
            var3_7 = var6_5;
            try {
                if (!StringUtils.isNull(var7_3.a)) {
                    var4_6 = var5_4;
                    var3_7 = var6_5;
                    var0 = var7_3.a;
                    var4_6 = var5_4;
                    var3_7 = var6_5;
                    var0 = DBManager.query("tb_scene_config", new String[]{"scene_id", "isUse", "useCount"}, "scene_id = ? ", new String[]{var0});
                }
                if (var0 != null) {
                    var4_6 = var0;
                    var3_7 = var0;
                    if (var0.getCount() > 0) {
                        do {
                            var4_6 = var0;
                            var3_7 = var0;
                            var2_9 = var0.moveToNext();
                            if (var2_9) {
                                var4_6 = var0;
                                var3_7 = var0;
                                var1_8 = var0.getInt(var0.getColumnIndex("useCount"));
                                var4_6 = var0;
                                var3_7 = var0;
                                var7_3.e = 1;
                                var4_6 = var0;
                                var3_7 = var0;
                                var7_3.c = var1_8 + 1;
                                var4_6 = var0;
                                var3_7 = var0;
                                var5_4 = q.a(var7_3);
                                var4_6 = var0;
                                var3_7 = var0;
                                new StringBuilder("values:").append(var5_4);
                                var4_6 = var0;
                                var3_7 = var0;
                                DBManager.update("tb_scene_config", (ContentValues)var5_4, "scene_id = ? ", new String[]{String.valueOf((Object)var7_3.a)});
                                continue;
                            }
                            ** GOTO lbl57
                            break;
                        } while (true);
                    }
                }
                break block10;
                catch (Throwable var0_1) {
                    var3_7 = var4_6;
                    var0_1.printStackTrace();
                    XyCursor.closeCursor((XyCursor)var4_6, true);
                    return;
                }
            }
            catch (Throwable var0_2) {
                XyCursor.closeCursor((XyCursor)var3_7, true);
                throw var0_2;
            }
        }
        XyCursor.closeCursor((XyCursor)var0, true);
    }

    private static ContentValues b(p p2, int n2) {
        ContentValues contentValues = new ContentValues();
        if (!StringUtils.isNull(p2.b)) {
            contentValues.put("sceneVersion", p2.b);
        }
        contentValues.put("scene_id", p2.a);
        contentValues.put("sceneType", Integer.valueOf((int)n2));
        contentValues.put("isCheck", Integer.valueOf((int)p2.d));
        return contentValues;
    }

    private static ContentValues b(String string2, String string3, int n2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("scene_id", string2);
        contentValues.put("sceneType", Integer.valueOf((int)n2));
        if (!StringUtils.isNull(string3)) {
            contentValues.put("sceneVersion", string3);
        }
        return contentValues;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static void b(String var0, int var1_2) {
        if (var1_2 != 1) ** GOTO lbl5
        try {
            var2_3 = "scene_id=? and sceneType = " + var1_2;
            ** GOTO lbl6
lbl5: // 1 sources:
            var2_3 = "scene_id=? and sceneType != 1";
lbl6: // 2 sources:
            DBManager.delete("tb_scene_config", var2_3, new String[]{var0});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }
}

