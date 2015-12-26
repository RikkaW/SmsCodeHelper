/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.sqlite.SQLiteDatabase
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.IccidInfo;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.util.StringUtils;

public class IccidInfoManager {
    public static final String ADD_SIM_INDEX = "ALTER TABLE tb_phone_info ADD COLUMN sim_index INTEGER DEFAULT -1 ";
    public static final String ADD_USER_AREACODE = "ALTER TABLE tb_phone_info ADD COLUMN user_areacode TEXT ";
    public static final String ADD_USER_OPERATOR = "ALTER TABLE tb_phone_info ADD COLUMN user_operator TEXT ";
    public static final String ADD_USER_PROVINCES = "ALTER TABLE tb_phone_info ADD COLUMN user_provinces TEXT ";
    public static final String AREACODE = "areacode";
    public static final String CITY = "city";
    public static final String CNUM = "cnum";
    public static final String CREATE_TABLE = "create table  if not exists tb_phone_info (id INTEGER PRIMARY KEY,iccid TEXT ,city TEXT,provinces TEXT,operator TEXT,areacode TEXT,ispost INTEGER DEFAULT 0,num TEXT,cnum TEXT,updateTime LONG,deft  INTEGER DEFAULT 0,net_updateTime LONG DEFAULT 0,user_provinces TEXT,user_areacode TEXT,user_operator TEXT,sim_index INTEGER DEFAULT -1)";
    public static final String DEFT = "deft";
    public static final String DROP_TABLE = " DROP TABLE IF EXISTS tb_phone_info";
    public static final String ICCID = "iccid";
    public static final String ID = "id";
    public static final String ISPOST = "ispost";
    public static final String NET_UPDATE_TIME = "net_updateTime";
    public static final String NUM = "num";
    public static final String OPERATOR = "operator";
    public static final String PROVINCES = "provinces";
    public static final String SIM_INDEX = "sim_index";
    public static final String TABLE_NAME = "tb_phone_info";
    public static final String UPDATE_TIME = "updateTime";
    public static final String USER_AREACODE = "user_areacode";
    public static final String USER_OPERATOR = "user_operator";
    public static final String USER_PROVINCES = "user_provinces";

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static long a(SQLiteDatabase sQLiteDatabase, String string2, int n2, String string3, String string4, String string5) {
        try {
            long l2;
            ContentValues contentValues = new ContentValues();
            contentValues.put("iccid", string2);
            contentValues.put("sim_index", Integer.valueOf((int)n2));
            if (!StringUtils.isNull(string3)) {
                contentValues.put("user_provinces", string3.trim());
                contentValues.put("updateTime", Long.valueOf((long)System.currentTimeMillis()));
            }
            if (!StringUtils.isNull(string4)) {
                contentValues.put("user_areacode", string4.trim());
            } else {
                contentValues.put("user_areacode", IccidInfoManager.getProviceCode(string3));
            }
            if (!StringUtils.isNull(string5)) {
                contentValues.put("user_operator", string5);
            }
            if (StringUtils.isNull(string2)) {
                l2 = sQLiteDatabase.update("tb_phone_info", contentValues, "sim_index = " + n2 + " AND iccid" + " IS NULL", null);
            } else {
                long l3;
                l2 = l3 = (long)sQLiteDatabase.update("tb_phone_info", contentValues, "iccid = ?", new String[]{string2});
                if (l3 < 1) {
                    sQLiteDatabase.execSQL("UPDATE tb_phone_info SET sim_index= -1 WHERE sim_index=" + n2 + " AND iccid" + " IS NOT NULL");
                    l2 = l3;
                }
            }
            if (l2 >= 1) return l2;
            return sQLiteDatabase.insert("tb_phone_info", null, contentValues);
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return -1;
        }
    }

    private static boolean a(String string2, String string3) {
        if (string2 != null && string3 != null && string2.indexOf(string3) != -1) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static int deleteIccidInfo(String string2, int n2) {
        string2 = string2 != null && string2.length() > 0 ? "iccid='" + string2 + "'" : "iccid IS NULL AND sim_index='" + n2 + "'";
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("user_provinces", "");
            contentValues.put("user_areacode", "");
            contentValues.put("user_operator", "");
            return DBManager.update("tb_phone_info", contentValues, string2, null);
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return -1;
        }
    }

    public static String getHead(IccidInfo iccidInfo) {
        if (!(iccidInfo == null || iccidInfo.isPost != 0 || StringUtils.isNull(iccidInfo.iccid) || StringUtils.isNull(iccidInfo.num) || StringUtils.isNull(iccidInfo.cnum))) {
            return String.valueOf((Object)iccidInfo.num) + ";" + iccidInfo.cnum + ";" + iccidInfo.iccid;
        }
        return "";
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String getProviceCode(String string2) {
        String[][] arrarrstring = new String[][]{{"\u5317\u4eac", "BJ"}, {"\u4e0a\u6d77", "SH"}, {"\u5929\u6d25", "TJ"}, {"\u91cd\u5e86", "CQ"}, {"\u9ed1\u9f99\u6c5f", "HL"}, {"\u5409\u6797", "JL"}, {"\u8fbd\u5b81", "LN"}, {"\u65b0\u7586", "XJ"}, {"\u897f\u85cf", "XZ"}, {"\u5185\u8499\u53e4", "NM"}, {"\u7518\u8083", "GS"}, {"\u9752\u6d77", "QH"}, {"\u9655\u897f", "XA"}, {"\u5b81\u590f", "NX"}, {"\u5c71\u897f", "SX"}, {"\u5c71\u4e1c", "SD"}, {"\u5b89\u5fbd", "AW"}, {"\u6cb3\u5357", "HN"}, {"\u6cb3\u5317", "HB"}, {"\u6d59\u6c5f", "ZJ"}, {"\u6c5f\u82cf", "JS"}, {"\u6e56\u5357", "CS"}, {"\u6e56\u5317", "WH"}, {"\u8d35\u5dde", "GZ"}, {"\u56db\u5ddd", "SC"}, {"\u6c5f\u897f", "JX"}, {"\u4e91\u5357", "YN"}, {"\u5e7f\u4e1c", "GD"}, {"\u5e7f\u897f", "GX"}, {"\u798f\u5efa", "FJ"}, {"\u6d77\u5357", "HK"}, {"\u9999\u6e2f", "XG"}, {"\u6fb3\u95e8", "OM"}, {"\u53f0\u6e7e", "TW"}};
        int n2 = 0;
        while (n2 < 34) {
            String string3 = arrarrstring[n2][0];
            if (string2 != null && string3 != null && string2.indexOf(string3) != -1) {
                return arrarrstring[n2][1];
            }
            boolean bl2 = false;
            if (bl2) {
                return arrarrstring[n2][1];
            }
            ++n2;
        }
        return null;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static long insertIccid(String var0, boolean var1_4, String var2_5, String var3_6, String var4_7, String var5_8, Context var6_9) {
        try {
            var6_9 = new ContentValues();
            var6_9.put("iccid", var0);
            if (!StringUtils.isNull((String)var2_5) && var2_5.indexOf(";") == -1) {
                if (!StringUtils.isNull((String)var2_5)) {
                    var6_9.put("provinces", var2_5.trim());
                    var6_9.put("updateTime", Long.valueOf((long)System.currentTimeMillis()));
                }
                if (!StringUtils.isNull(var3_6)) {
                    var6_9.put("areacode", var3_6.trim());
                } else {
                    var6_9.put("areacode", IccidInfoManager.getProviceCode((String)var2_5));
                }
                if (!StringUtils.isNull(var4_7)) {
                    var6_9.put("city", var4_7.trim());
                }
                if (!StringUtils.isNull(var5_8)) {
                    var6_9.put("operator", var5_8);
                }
            } else if (!StringUtils.isNull((String)var2_5)) {
                if ((var2_5 = var2_5.split(";")).length > 0) {
                    var6_9.put("provinces", (String)var2_5[0]);
                    var6_9.put("updateTime", Long.valueOf((long)System.currentTimeMillis()));
                    var6_9.put("areacode", IccidInfoManager.getProviceCode((String)var2_5[0]));
                }
                if (var2_5.length >= 2) {
                    var6_9.put("city", (String)var2_5[1]);
                }
            }
            if (!var1_4) ** GOTO lbl32
            var2_5 = new ContentValues();
            var2_5.put("deft", Integer.valueOf((int)0));
            DBManager.update("tb_phone_info", (ContentValues)var2_5, null, null);
            ** GOTO lbl32
        }
        catch (Throwable var0_1) {
            var8_12 = -1;
            ** GOTO lbl51
lbl32: // 2 sources:
            var7_10 = var1_4 != false ? 1 : 0;
            var6_9.put("deft", Integer.valueOf((int)var7_10));
            var7_10 = DBManager.update("tb_phone_info", (ContentValues)var6_9, "iccid = ?", new String[]{var0});
            var10_11 = var7_10;
            if (var10_11 < 1) {
                var8_12 = var10_11;
                var8_12 = var10_11 = DBManager.insert("tb_phone_info", (ContentValues)var6_9);
                IccidLocationUtil.putIccidAreaCodeToCache(var0, var6_9.getAsString("areacode"), var5_8, null, null);
                return var10_11;
            }
            var8_12 = var10_11;
            try {
                var2_5 = IccidInfoManager.queryIccidInfo(var0, Constant.getContext());
                var8_12 = var10_11;
                if (var2_5 == null) return var8_12;
                var8_12 = var10_11;
                IccidLocationUtil.putIccidAreaCodeToCache(var0, var2_5.areaCode, var2_5.operator, var2_5.userAreacode, var2_5.userOperator);
                return var10_11;
            }
            catch (Throwable var0_3) {}
lbl51: // 2 sources:
            var0_2.printStackTrace();
            return var8_12;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static boolean insertOrUpdateIccid(String var0, int var1_9, String var2_10, String var3_16, String var4_17, boolean var5_18) {
        block29 : {
            if (var1_9 < 0) return false;
            if (StringUtils.isNull(var2_10) != false) return false;
            if (StringUtils.isNull(var3_16) != false) return false;
            if (StringUtils.isNull(var4_17)) {
                return false;
            }
            var8_19 = null;
            var10_20 = null;
            var9_21 = DBManager.getSQLiteDatabase();
            var9_21.beginTransaction();
            var6_22 = IccidInfoManager.a(var9_21, var0, var1_9, var2_10, var3_16, var4_17);
            if (var6_22 >= 1) break block29;
            if (var9_21 == null) return false;
            try {
                if (var9_21.inTransaction() == false) return false;
                var9_21.endTransaction();
                return false;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return false;
            }
            finally {
                DBManager.close(var9_21);
                return false;
            }
        }
        var9_21.setTransactionSuccessful();
        if (var9_21 == null) return true;
        try {
            if (var9_21.inTransaction() == false) return true;
            var9_21.endTransaction();
            return true;
        }
        catch (Throwable var0_3) {
            var0_3.printStackTrace();
            return true;
        }
        finally {
            DBManager.close(var9_21);
            return true;
        }
        catch (Throwable var2_11) {
            var0 = var10_20;
            ** GOTO lbl47
            catch (Throwable var0_8) {
                var8_19 = var9_21;
                ** GOTO lbl-1000
            }
            catch (Throwable var2_15) {
                var0 = var9_21;
            }
lbl47: // 2 sources:
            var8_19 = var0;
            try {
                var2_10.printStackTrace();
                if (var0 == null) return false;
            }
            catch (Throwable var0_5) lbl-1000: // 2 sources:
            {
                if (var8_19 == null) throw var0_6;
                try {
                    if (var8_19.inTransaction() == false) throw var0_6;
                    var8_19.endTransaction();
                }
                catch (Throwable var2_14) {
                    var2_14.printStackTrace();
                    throw var0_6;
                }
                finally {
                    DBManager.close((SQLiteDatabase)var8_19);
                    throw var0_6;
                }
                throw var0_6;
            }
            try {
                if (var0.inTransaction() == false) return false;
                var0.endTransaction();
                return false;
            }
            catch (Throwable var2_12) {
                var2_12.printStackTrace();
                return false;
            }
            finally {
                DBManager.close((SQLiteDatabase)var0);
                return false;
            }
        }
    }

    public static IccidInfo queryDeftIccidInfo(Context context) {
        return IccidInfoManager.queryIccidInfo(null, context);
    }

    /*
     * Exception decompiling
     */
    public static IccidInfo queryIccidInfo(String var0, int var1_4) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
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

    public static IccidInfo queryIccidInfo(String string2, Context context) {
        return IccidInfoManager.queryIccidInfo(string2, -1);
    }

    public static void updateIccidCnum(String string2, String string3, String string4, Context context) {
        try {
            if (!StringUtils.isNull(string2) && !StringUtils.isNull(string3) && !StringUtils.isNull(string4) && (string4.equals((Object)"10086") || string4.equals((Object)"10010") || string4.equals((Object)"10000"))) {
                context = new ContentValues();
                context.put("num", string4);
                context.put("cnum", string3);
                DBManager.update("tb_phone_info", (ContentValues)context, "iccid = ? and ispost = 0", new String[]{string2});
            }
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }
}

