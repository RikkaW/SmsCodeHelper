/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.content.Context
 *  java.io.File
 *  java.lang.Boolean
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashMap
 */
package cn.com.xy.sms.sdk.db.entity;

import android.content.ContentValues;
import android.content.Context;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.ParseItemManager;
import cn.com.xy.sms.sdk.db.entity.IccidInfoManager;
import cn.com.xy.sms.sdk.db.entity.e;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.queue.g;
import cn.com.xy.sms.sdk.queue.i;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.d;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SysParamEntityManager {
    public static final String CREATE_TABLE = "create table  if not exists tb_sdk_param (id int primary key,p_key TEXT,p_value TEXT,pextend_value TEXT)";
    public static final String DROP_TABLE = " DROP TABLE IF EXISTS tb_sdk_param";
    public static final String ID = "id";
    public static final String PEXTENDVALUE = "pextend_value";
    public static final String PKEY = "p_key";
    public static final String PVALUE = "p_value";
    public static final String TABLE_NAME = "tb_sdk_param";
    public static HashMap<String, Object> cacheMap = new HashMap();

    public static void clearOldData(String string2) {
        try {
            String string3 = SysParamEntityManager.queryValueParamKey(Constant.getContext(), "CHANNEL");
            if (!(StringUtils.isNull(string3) || StringUtils.isNull(string2) || string3.equals((Object)string2))) {
                SysParamEntityManager.clearOldData(true);
            }
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
    public static void clearOldData(boolean var0) {
        if (!var0) ** GOTO lbl19
        DBManager.delete("tb_scene_config", null, null);
        {
            catch (Throwable var1_1) {
                var1_1.printStackTrace();
            }
            try {
                DBManager.delete("tb_scenerule_config", null, null);
            }
            catch (Throwable var1_3) {
                var1_3.printStackTrace();
            }
            try {
                DBManager.delete("tb_res_download", null, null);
            }
            catch (Throwable var1_4) {
                var1_4.printStackTrace();
            }
            DBManager.delete("tb_xml_res_download", null, null);
lbl19: // 2 sources:
            ** try [egrp 4[TRYBLOCK] [4 : 36->94)] { 
lbl20: // 1 sources:
            ** GOTO lbl23
            catch (Throwable var1_5) {
                var1_5.printStackTrace();
            }
lbl23: // 2 sources:
            e.c();
            d.d(Constant.getPARSE_PATH());
            d.a(Constant.getContext().getDir("outdex", 0));
            ParseItemManager.deleteAll();
            return;
        }
lbl28: // 1 sources:
        catch (Throwable var1_6) {
            return;
        }
    }

    public static void deleteOldFile() {
        try {
            String string2 = String.valueOf((Object)Constant.getContext().getFilesDir().getPath()) + File.separator + "parse" + File.separator;
            if (d.a(string2)) {
                d.a(string2, "ParseUtilCasual_", ".jar", null);
                d.a(string2, "ParseUtilEC_", ".jar", null);
                d.a(string2, "ParseUtilFinanceL_", ".jar", null);
                d.a(string2, "ParseUtilFinanceM_", ".jar", null);
                d.a(string2, "ParseUtilFinanceS_", ".jar", null);
                d.a(string2, "ParseUtilLife_", ".jar", null);
                d.a(string2, "ParseUtilMove_", ".jar", null);
                d.a(string2, "ParseUtilTelecom_", ".jar", null);
                d.a(string2, "ParseUtilTravel_", ".jar", null);
                d.a(string2, "ParseUtilUnicom_", ".jar", null);
            }
            if (d.a(Constant.getContext().getDir("outdex", 0).getPath())) {
                d.b("ParseUtilCasual_", ".dex", null);
                d.b("ParseUtilEC_", ".dex", null);
                d.b("ParseUtilFinanceL_", ".dex", null);
                d.b("ParseUtilFinanceM_", ".dex", null);
                d.b("ParseUtilFinanceS_", ".dex", null);
                d.b("ParseUtilLife_", ".dex", null);
                d.b("ParseUtilMove_", ".dex", null);
                d.b("ParseUtilTelecom_", ".dex", null);
                d.b("ParseUtilTravel_", ".dex", null);
                d.b("ParseUtilUnicom_", ".dex", null);
            }
            return;
        }
        catch (Throwable var0_1) {
            return;
        }
    }

    public static boolean getBooleanParam(Context context, String string2) {
        return SysParamEntityManager.getBooleanParam(context, string2, false);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static boolean getBooleanParam(Context var0, String var1_4, boolean var2_5) {
        var3_6 = SysParamEntityManager.cacheMap.get((Object)var1_4);
        if (var3_6 != null) return Boolean.parseBoolean((String)var3_6.toString());
        try {
            var0 = SysParamEntityManager.queryValueParamKey((Context)var0, var1_4);
            if (var0 != null) ** GOTO lbl8
            SysParamEntityManager.cacheMap.put((Object)var1_4, (Object)var2_5);
            ** GOTO lbl12
lbl8: // 1 sources:
            var2_5 = "false".equalsIgnoreCase((String)var0) == false;
        }
        catch (Throwable var0_1) {
            var2_5 = false;
            ** GOTO lbl16
lbl12: // 2 sources:
            try {
                SysParamEntityManager.cacheMap.put((Object)var1_4, (Object)var2_5);
                return var2_5;
            }
            catch (Throwable var0_3) {}
lbl16: // 2 sources:
            var0_2.printStackTrace();
            return var2_5;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int getIntParam(Context object, String string2) {
        Object object2 = cacheMap.get((Object)string2);
        if (object2 != null) return Integer.parseInt((String)((String)object2));
        object = SysParamEntityManager.queryValueParamKey((Context)object, string2);
        if (object == null) return -1;
        try {
            cacheMap.put((Object)string2, object);
            return Integer.valueOf((String)object);
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
        }
        return -1;
    }

    /*
     * Exception decompiling
     */
    public static long getLongParam(String var0, long var1_3, Context var3_4) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 6[SIMPLE_IF_TAKEN]
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
     * Lifted jumps to return sites
     */
    public static String getStringParam(Context var0, String var1_1) {
        var2_5 = SysParamEntityManager.cacheMap.get((Object)var1_1);
        if (var2_5 == null) {
            var0 = SysParamEntityManager.queryValueParamKey((Context)var0, var1_1);
            if (var0 == null) return var0;
            SysParamEntityManager.cacheMap.put((Object)var1_1, var0);
            return var0;
        }
        try {
            return (String)var2_5;
        }
        catch (Throwable var1_2) {
            var0 = null;
            ** GOTO lbl15
            catch (Throwable var1_4) {}
lbl15: // 2 sources:
            var1_3.printStackTrace();
            return var0;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void initParams(Context context, String string2, String string3, boolean bl2, boolean bl3, Map<String, String> object) {
        String string4;
        SysParamEntityManager.clearOldData(string2);
        String string5 = SysParamEntityManager.queryValueParamKey(context, "smartsms_enhance");
        if (string5 == null) {
            if (object != null) {
                string5 = (String)object.get("smartsms_enhance");
            }
            string4 = string5;
            if (string5 == null) {
                string4 = "true";
            }
            SysParamEntityManager.insertOrUpdateKeyValue(context, "smartsms_enhance", string4, null);
            cacheMap.put((Object)"smartsms_enhance", (Object)string4);
        }
        if ((string5 = SysParamEntityManager.queryValueParamKey(context, "SUPPORT_NETWORK_TYPE")) == null) {
            if (object != null) {
                string5 = (String)object.get("SUPPORT_NETWORK_TYPE");
            }
            string4 = string5;
            if (string5 == null) {
                string4 = "1";
            }
            SysParamEntityManager.insertOrUpdateKeyValue(context, "SUPPORT_NETWORK_TYPE", string4, null);
            cacheMap.put((Object)"SUPPORT_NETWORK_TYPE", (Object)string4);
        }
        if ((string5 = SysParamEntityManager.queryValueParamKey(context, "ONLINE_UPDATE_SDK_PERIOD")) == null) {
            if (object != null) {
                string5 = (String)object.get("ONLINE_UPDATE_SDK_PERIOD");
            }
            string4 = string5;
            if (string5 == null) {
                string4 = "1";
            }
            SysParamEntityManager.insertOrUpdateKeyValue(context, "ONLINE_UPDATE_SDK_PERIOD", string4, null);
            cacheMap.put((Object)"ONLINE_UPDATE_SDK_PERIOD", (Object)string4);
        }
        SysParamEntityManager.insertOrUpdateKeyValue(context, "PRELOADENABLE", String.valueOf((boolean)bl2), null);
        SysParamEntityManager.insertOrUpdateKeyValue(context, "SMSLOCATEENABLE", String.valueOf((boolean)bl3), null);
        SysParamEntityManager.insertOrUpdateKeyValue(context, "CHANNEL", String.valueOf((Object)string2), null);
        cacheMap.put((Object)"PRELOADENABLE", (Object)bl2);
        cacheMap.put((Object)"SMSLOCATEENABLE", (Object)bl3);
        cacheMap.put((Object)"CHANNEL", (Object)string2);
        String string6 = null;
        String string7 = null;
        String string8 = null;
        String string9 = null;
        String string10 = null;
        String string11 = null;
        String string12 = null;
        String string13 = null;
        String string14 = null;
        String string15 = null;
        String string16 = null;
        string4 = null;
        String string17 = null;
        String string18 = null;
        string5 = null;
        string2 = null;
        if (object != null) {
            string2 = (String)object.get("SIM_ICCID_2");
            string5 = (String)object.get("SMS_LOCATE_2");
            if (string2 != null) {
                IccidInfoManager.insertIccid(string2, false, string5, "", "", "", context);
                g.a(new i(1, "simIccid", string2, "smsLocate", string5));
            }
            string7 = (String)object.get("CUSTOM_LOCATION_SERVER_URL");
            string8 = (String)object.get("CUSTOM_PUBLIC_SERVER_URL");
            string9 = (String)object.get("CUSTOM_SDK_SERVER_URL");
            string10 = (String)object.get("ONLINE_UPDATE_SDK");
            string11 = (String)object.get("QUERY_ONLINE");
            string6 = (String)object.get("SMS_LOCATE");
            string12 = (String)object.get("RECOGNIZE_LEVEL");
            string2 = (String)object.get("SECRETKEY");
            string13 = (String)object.get("OPEN_POPUP_DRAG");
            string15 = (String)object.get("AUTO_UPDATE_DATA");
            cacheMap.put((Object)"SECRETKEY", (Object)string2);
            string14 = (String)object.get("POPUP_BG_TYPE");
            string17 = (String)object.get("SCENE_CENSUS_ONLINE");
            string18 = (String)object.get("CUSTOM_SDK_RES_DOWNLAOD_URL");
            string16 = (String)object.get("SUPPORT_NETWORK_TYPE_MAJOR");
            string4 = (String)object.get("ONLINE_UPDATE_RES_PERIOD");
            string5 = (String)object.get("REPARSE_BUBBLE_CYCLE");
            string2 = (String)object.get("COMPARE_PUBNUM_OPERATOR");
        }
        object = string7;
        if (string7 == null) {
            object = "";
        }
        SysParamEntityManager.insertOrUpdateKeyValue(context, "CUSTOM_LOCATION_SERVER_URL", (String)object, null);
        cacheMap.put((Object)"CUSTOM_LOCATION_SERVER_URL", object);
        object = string8 == null ? "" : string8;
        if (!StringUtils.isNull((String)object)) {
            if (NetUtil.isUseHttps()) {
                NetUtil.PUBINFO_SERVER_URL_HTTPS = object;
            } else {
                NetUtil.serverUrl2 = object;
            }
        }
        SysParamEntityManager.insertOrUpdateKeyValue(context, "CUSTOM_PUBLIC_SERVER_URL", (String)object, null);
        cacheMap.put((Object)"CUSTOM_PUBLIC_SERVER_URL", object);
        object = string9 == null ? "" : string9;
        SysParamEntityManager.insertOrUpdateKeyValue(context, "CUSTOM_SDK_SERVER_URL", (String)object, null);
        cacheMap.put((Object)"CUSTOM_SDK_SERVER_URL", object);
        if (!StringUtils.isNull((String)object)) {
            if (NetUtil.isUseHttps()) {
                NetUtil.POPUP_SERVER_URL_HTTPS = object;
            } else {
                NetUtil.serverUrl = object;
            }
        }
        object = string18 == null ? "" : string18;
        SysParamEntityManager.insertOrUpdateKeyValue(context, "CUSTOM_SDK_RES_DOWNLAOD_URL", (String)object, null);
        cacheMap.put((Object)"CUSTOM_SDK_RES_DOWNLAOD_URL", object);
        if (!StringUtils.isNull((String)object)) {
            NetUtil.prex = object;
        }
        object = string10 == null ? "1" : string10;
        SysParamEntityManager.insertOrUpdateKeyValue(context, "ONLINE_UPDATE_SDK", (String)object, null);
        cacheMap.put((Object)"ONLINE_UPDATE_SDK", object);
        object = string11 == null ? "1" : string11;
        SysParamEntityManager.insertOrUpdateKeyValue(context, "QUERY_ONLINE", (String)object, null);
        cacheMap.put((Object)"QUERY_ONLINE", object);
        object = string17 == null ? "0" : string17;
        SysParamEntityManager.insertOrUpdateKeyValue(context, "SCENE_CENSUS_ONLINE", (String)object, null);
        cacheMap.put((Object)"SCENE_CENSUS_ONLINE", object);
        object = string12 == null ? "3" : string12;
        SysParamEntityManager.insertOrUpdateKeyValue(context, "RECOGNIZE_LEVEL", (String)object, null);
        cacheMap.put((Object)"RECOGNIZE_LEVEL", object);
        object = string13 == null ? "0" : string13;
        cacheMap.put((Object)"OPEN_POPUP_DRAG", object);
        object = string15 == null ? "0" : string15;
        SysParamEntityManager.insertOrUpdateKeyValue(context, "AUTO_UPDATE_DATA", (String)object, null);
        cacheMap.put((Object)"AUTO_UPDATE_DATA", object);
        object = string14 == null ? "1" : string14;
        SysParamEntityManager.insertOrUpdateKeyValue(context, "POPUP_BG_TYPE", (String)object, null);
        cacheMap.put((Object)"POPUP_BG_TYPE", object);
        object = string16 == null ? "2" : string16;
        SysParamEntityManager.insertOrUpdateKeyValue(context, "SUPPORT_NETWORK_TYPE_MAJOR", (String)object, null);
        cacheMap.put((Object)"SUPPORT_NETWORK_TYPE_MAJOR", object);
        object = string4 == null ? "2" : string4;
        SysParamEntityManager.insertOrUpdateKeyValue(context, "ONLINE_UPDATE_RES_PERIOD", (String)object, null);
        cacheMap.put((Object)"ONLINE_UPDATE_RES_PERIOD", object);
        object = string5 == null ? "-1" : string5;
        SysParamEntityManager.insertOrUpdateKeyValue(context, "REPARSE_BUBBLE_CYCLE", (String)object, null);
        cacheMap.put((Object)"REPARSE_BUBBLE_CYCLE", object);
        if (string2 == null) {
            string2 = "true";
        }
        SysParamEntityManager.insertOrUpdateKeyValue(context, "COMPARE_PUBNUM_OPERATOR", string2, null);
        cacheMap.put((Object)"COMPARE_PUBNUM_OPERATOR", (Object)string2);
        if (!StringUtils.isNull(string3)) {
            IccidInfoManager.insertIccid(string3, true, string6, "", "", "", context);
            g.a(new i(1, "simIccid", string3, "smsLocate", string6));
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static long insertOrUpdateKeyValue(Context object, String string2, String string3, String string4) {
        int n2;
        object = SysParamEntityManager.queryValueParamKey((Context)object, string2);
        ContentValues contentValues = new ContentValues();
        contentValues.put("p_key", string2);
        contentValues.put("p_value", string3);
        contentValues.put("pextend_value", string4);
        if (object != null) {
            n2 = DBManager.update("tb_sdk_param", contentValues, "p_key=?", new String[]{string2});
            do {
                return n2;
                break;
            } while (true);
        }
        n2 = (int)DBManager.insert("tb_sdk_param", contentValues);
        return n2;
    }

    /*
     * Exception decompiling
     */
    public static String queryValueParamKey(Context var0, String var1_1) {
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
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static long setParam(String string2, String string3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("p_key", string2);
        contentValues.put("p_value", string3);
        int n2 = DBManager.update("tb_sdk_param", contentValues, "p_key=?", new String[]{string2});
        cacheMap.put((Object)string2, (Object)string3);
        if (n2 > 0) return 0;
        try {
            return DBManager.insert("tb_sdk_param", contentValues);
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
        }
        return 0;
    }
}

