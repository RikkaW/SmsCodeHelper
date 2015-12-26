/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.database.sqlite.SQLiteDatabase
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.Map$Entry
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.db.entity.a;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.db.entity.IccidInfo;
import cn.com.xy.sms.sdk.db.entity.IccidInfoManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.a.f;
import cn.com.xy.sms.sdk.db.entity.a.g;
import cn.com.xy.sms.sdk.db.entity.a.h;
import cn.com.xy.sms.sdk.db.entity.a.i;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.util.SdkCallBack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public final class e {
    private static String a = "tb_public_info";
    private static String b = "tb_public_menu_info";
    private static String c = "tb_public_num_info";
    private static String d = " DROP TABLE IF EXISTS tb_public_info";
    private static String e = " DROP TABLE IF EXISTS tb_public_menu_info";
    private static String f = " DROP TABLE IF EXISTS tb_public_num_info";
    private static String g = "ALTER TABLE tb_public_info ADD COLUMN classifyCode TEXT";
    private static String h = " ALTER TABLE tb_public_num_info ADD COLUMN lastloadtime LONG default 0";
    private static String i = " ALTER TABLE tb_public_num_info ADD COLUMN isrulenum INTEGER default 0";
    private static String j = "queryTraffic";
    private static String k = "queryCharge";
    private static String l = "selectSimCard";

    private static int a(HashMap<String, String> hashMap) {
        if (hashMap == null) {
            return -1;
        }
        return e.g((String)hashMap.get((Object)"extend"));
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static int a(String[] arrstring) {
        boolean bl2;
        block8 : {
            if (arrstring == null) return -2;
            if (arrstring.length == 0) {
                return -2;
            }
            if (arrstring.length <= 4 || (bl2 = StringUtils.isNull(arrstring[4]))) break block8;
            try {
                int n2;
                int n3 = n2 = Integer.parseInt((String)arrstring[4]);
                if (n2 != -2) return n3;
            }
            catch (Throwable var4_6) {}
        }
        if (arrstring.length <= 2) return -2;
        bl2 = StringUtils.isNull(arrstring[2]);
        if (bl2) return -2;
        try {
            return Integer.parseInt((String)arrstring[2]);
        }
        catch (Throwable var0_2) {
            return -2;
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return -2;
    }

    public static String a() {
        return " create table  if not exists tb_public_info ( id INTEGER PRIMARY KEY, pubId INTEGER not null unique, pubName TEXT not null, pubType TEXT, classifyCode TEXT, weiXin TEXT, weiBoName TEXT, weiBoUrl TEXT, introduce TEXT, address TEXT, faxNum TEXT, webSite TEXT, moveWebSite TEXT, versionCode TEXT, email TEXT, parentPubId int, slogan TEXT, rectLogoName TEXT, circleLogoName TEXT, extend TEXT, hasmenu int, loadMenuTime long, updateInfoTime long )";
    }

    private static String a(int n2, int n3, String string2) {
        if (StringUtils.isNull(string2) || "CN".equalsIgnoreCase(string2)) {
            return "CN";
        }
        if (n3 < 0) {
            LogManager.e("PubInfoManager getQueryAreaCodeBySimIndex", "simIndex\u5c0f\u4e8e0\uff0c\u8fd4\u56deCN\uff0csimIndex=" + n3, null);
            return "CN";
        }
        Object object = IccidInfoManager.queryIccidInfo(null, n3);
        if (object == null) {
            return "CN";
        }
        String string3 = String.valueOf((int)n2);
        String string4 = object.userAreacode;
        String string5 = IccidLocationUtil.getOperatorNum(null, object.userOperator);
        if (string2.equalsIgnoreCase(string4) && string3.equals((Object)string5)) {
            new StringBuilder("\u7528\u6237\u8bbe\u7f6e\u7684\u533a\u57df\u7f16\u7801\u53ca\u8fd0\u8425\u5546\u7f16\u53f7\u4e0e\u5f53\u524d\u63a5\u5165\u7801\u7684\u8fd0\u8425\u5546\u7f16\u53f7\u5339\u914d\uff0c\u8fd4\u56de\u5f53\u524d\u533a\u57df\u7f16\u7801\uff0cnumOperator=").append(n2).append(" simIndex=").append(n3).append(" areaCode=").append(string2);
            return string2;
        }
        if (!string4.equals((Object)"-2") && !StringUtils.isNull(string5)) {
            new StringBuilder("\u7528\u6237\u5df2\u7ecf\u8bbe\u7f6e\u4e86\u533a\u57df\u7f16\u7801\u53ca\u8fd0\u8425\u5546\u7f16\u53f7\uff0c\u4f46\u4e0e\u5f53\u524d\u63a5\u5165\u7801\u7684\u8fd0\u8425\u5546\u7f16\u53f7\u4e0d\u5339\u914d\uff0c\u8fd4\u56deCN\uff0cnumOperator=").append(n2).append(" simIndex=").append(n3).append(" areaCode=").append(string2);
            return "CN";
        }
        string4 = object.areaCode;
        object = IccidLocationUtil.getOperatorNum(null, object.operator);
        if (string2.equalsIgnoreCase(string4) && string3.equals(object)) {
            new StringBuilder("\u5361\u4f4d\u7684\u533a\u57df\u7f16\u7801\u53ca\u8fd0\u8425\u5546\u7f16\u53f7\u4e0e\u5f53\u524d\u63a5\u5165\u7801\u7684\u8fd0\u8425\u5546\u7f16\u53f7\u5339\u914d\uff0c\u8fd4\u56de\u5f53\u524d\u533a\u57df\u7f16\u7801\uff0cnumOperator=").append(n2).append(" simIndex=").append(n3).append(" areaCode=").append(string2);
            return string2;
        }
        new StringBuilder("\u5361\u4f4d\u4e0e\u5f53\u524d\u63a5\u5165\u7801\u7684\u8fd0\u8425\u5546\u7f16\u53f7\u4e0d\u5339\u914d\uff0c\u8fd4\u56deCN\uff0cnumOperator=").append(n2).append(" simIndex=").append(n3).append(" areaCode=").append(string2);
        return "CN";
    }

    private static String a(int n2, String string2, String string3) {
        int n3;
        int n4 = n3 = e.a(IccidLocationUtil.getIccidInfoArr(string2));
        if (n3 == -2) {
            n4 = IccidLocationUtil.getOperatorByICCID(string2);
        }
        if (n4 != -2 && n4 == n2) {
            new StringBuilder("\u5f53\u524d\u63a5\u5165\u7801\u6240\u5c5e\u8fd0\u8425\u5546\u4e0e\u5f53\u524d\u4f7f\u7528\u7684sim\u5361iccid\u6240\u5c5e\u8fd0\u8425\u5546\u76f8\u540c\u76f4\u63a5\u8fd4\u56de\u533a\u57df\u7f16\u7801\uff0cnumOperator=").append(n2).append(" simIccid=").append(string2).append(" areaCode=").append(string3);
            return string3;
        }
        new StringBuilder("\u5f53\u524d\u63a5\u5165\u7801\u6240\u5c5e\u8fd0\u8425\u5546\u4e0e\u5f53\u524d\u4f7f\u7528\u7684sim\u5361iccid\u6240\u5c5e\u8fd0\u8425\u5546\u4e0d\u76f8\u540c\uff0c\u8fd4\u56deCN\uff0cnumOperator=").append(n2).append(" simIccid=").append(string2).append(" areaCode=").append(string3);
        return "CN";
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static String a(int var0, String var1_1, String var2_2, int var3_3) {
        if ("CN".equalsIgnoreCase(var2_2)) {
            return var2_2;
        }
        try {
            block15 : {
                if (!StringUtils.isNull(var1_1) || var3_3 >= 0) ** GOTO lbl10
                var6_4 = IccidLocationUtil.getIccidAreaCodeMap();
                if (var6_4 == null) return "CN";
                if (var6_4.isEmpty()) {
                    return "CN";
                }
                ** GOTO lbl48
lbl10: // 1 sources:
                if (!StringUtils.isNull(var1_1) || var3_3 < 0) ** GOTO lbl37
                if (StringUtils.isNull(var2_2) != false) return "CN";
                if ("CN".equalsIgnoreCase(var2_2)) {
                    return "CN";
                }
                if (var3_3 < 0) {
                    LogManager.e("PubInfoManager getQueryAreaCodeBySimIndex", "simIndex\u5c0f\u4e8e0\uff0c\u8fd4\u56deCN\uff0csimIndex=" + var3_3, null);
                    return "CN";
                }
                var7_9 = IccidInfoManager.queryIccidInfo(null, var3_3);
                if (var7_9 == null) {
                    return "CN";
                }
                var6_4 = String.valueOf((int)var0);
                var8_11 = var7_9.userAreacode;
                var9_12 = IccidLocationUtil.getOperatorNum(null, var7_9.userOperator);
                if (var2_2.equalsIgnoreCase(var8_11) && var6_4.equals((Object)var9_12)) {
                    new StringBuilder("\u7528\u6237\u8bbe\u7f6e\u7684\u533a\u57df\u7f16\u7801\u53ca\u8fd0\u8425\u5546\u7f16\u53f7\u4e0e\u5f53\u524d\u63a5\u5165\u7801\u7684\u8fd0\u8425\u5546\u7f16\u53f7\u5339\u914d\uff0c\u8fd4\u56de\u5f53\u524d\u533a\u57df\u7f16\u7801\uff0cnumOperator=").append(var0).append(" simIndex=").append(var3_3).append(" areaCode=").append(var2_2);
                    return var2_2;
                }
                if (!var8_11.equals((Object)"-2") && !StringUtils.isNull(var9_12)) {
                    new StringBuilder("\u7528\u6237\u5df2\u7ecf\u8bbe\u7f6e\u4e86\u533a\u57df\u7f16\u7801\u53ca\u8fd0\u8425\u5546\u7f16\u53f7\uff0c\u4f46\u4e0e\u5f53\u524d\u63a5\u5165\u7801\u7684\u8fd0\u8425\u5546\u7f16\u53f7\u4e0d\u5339\u914d\uff0c\u8fd4\u56deCN\uff0cnumOperator=").append(var0).append(" simIndex=").append(var3_3).append(" areaCode=").append(var2_2);
                    return "CN";
                }
                var8_11 = var7_9.areaCode;
                var7_9 = IccidLocationUtil.getOperatorNum(null, var7_9.operator);
                if (var2_2.equalsIgnoreCase(var8_11) && var6_4.equals(var7_9)) {
                    new StringBuilder("\u5361\u4f4d\u7684\u533a\u57df\u7f16\u7801\u53ca\u8fd0\u8425\u5546\u7f16\u53f7\u4e0e\u5f53\u524d\u63a5\u5165\u7801\u7684\u8fd0\u8425\u5546\u7f16\u53f7\u5339\u914d\uff0c\u8fd4\u56de\u5f53\u524d\u533a\u57df\u7f16\u7801\uff0cnumOperator=").append(var0).append(" simIndex=").append(var3_3).append(" areaCode=").append(var2_2);
                    return var2_2;
                }
                new StringBuilder("\u5361\u4f4d\u4e0e\u5f53\u524d\u63a5\u5165\u7801\u7684\u8fd0\u8425\u5546\u7f16\u53f7\u4e0d\u5339\u914d\uff0c\u8fd4\u56deCN\uff0cnumOperator=").append(var0).append(" simIndex=").append(var3_3).append(" areaCode=").append(var2_2);
                return "CN";
lbl37: // 1 sources:
                var4_7 = var5_13 = e.a(IccidLocationUtil.getIccidInfoArr(var1_1));
                if (var5_13 != -2) break block15;
                var4_7 = IccidLocationUtil.getOperatorByICCID(var1_1);
            }
            if (var4_7 == -2 || var4_7 != var0) ** GOTO lbl46
            new StringBuilder("\u5f53\u524d\u63a5\u5165\u7801\u6240\u5c5e\u8fd0\u8425\u5546\u4e0e\u5f53\u524d\u4f7f\u7528\u7684sim\u5361iccid\u6240\u5c5e\u8fd0\u8425\u5546\u76f8\u540c\u76f4\u63a5\u8fd4\u56de\u533a\u57df\u7f16\u7801\uff0cnumOperator=").append(var0).append(" simIccid=").append(var1_1).append(" areaCode=").append(var2_2);
            return var2_2;
lbl46: // 1 sources:
            new StringBuilder("\u5f53\u524d\u63a5\u5165\u7801\u6240\u5c5e\u8fd0\u8425\u5546\u4e0e\u5f53\u524d\u4f7f\u7528\u7684sim\u5361iccid\u6240\u5c5e\u8fd0\u8425\u5546\u4e0d\u76f8\u540c\uff0c\u8fd4\u56deCN\uff0cnumOperator=").append(var0).append(" simIccid=").append(var1_1).append(" areaCode=").append(var2_2);
            return "CN";
lbl48: // 1 sources:
            var6_4 = var6_4.entrySet().iterator();
            do {
                if (var6_4.hasNext()) continue;
                return "CN";
            } while ((var4_6 = e.a(var8_10 = (String[])(var7_8 = (Map.Entry)var6_4.next()).getValue())) == -2 || var4_6 != var0 || var8_10 == null || var8_10.length <= 0 || StringUtils.isNull(var8_10[0]));
            new StringBuilder("\u5f53\u524d\u63a5\u5165\u7801\u6240\u5c5e\u8fd0\u8425\u5546\u4e0e\u5176\u4e2d\u4e00\u5f20sim\u5361\u7684iccid\u6240\u5c5e\u8fd0\u8425\u5546\u76f8\u540c\u65f6\u8fd4\u56de\u5bf9\u5e94sim\u5361\u7684\u533a\u57df\u7f16\u7801\uff0cnumOperator=").append(var0).append(" AreaCode=").append(var8_10[0]).append(" ICCID=").append((String)var7_8.getKey());
            return String.valueOf((Object)var8_10[0]) + "_" + (String)var7_8.getKey();
        }
        catch (Throwable var6_5) {
            var6_5.printStackTrace();
            LogManager.e("PubInfoManager getQueryAreaCode", "\u51fa\u73b0\u5f02\u5e38\uff0c\u8fd4\u56deCN\uff0cnumOperator=" + var0 + " simIccid=" + var1_1 + " areaCode=" + var2_2 + " simIndex=" + var3_3, null);
            return "CN";
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String a(String string2, Map<String, String> object) {
        block8 : {
            if (StringUtils.isNull(string2)) return null;
            if (!string2.contains((CharSequence)"action_data")) return null;
            if (object == null) return null;
            if (object.isEmpty()) {
                return null;
            }
            string2 = new JSONArray(string2);
            JSONObject jSONObject = string2.getJSONObject(0);
            jSONObject.put("name", (Object)"\u4e1a\u52a1\u529e\u7406");
            jSONObject = jSONObject.getJSONArray("secondmenu");
            JSONObject jSONObject2 = object.entrySet().iterator();
            object = string2;
            if (jSONObject2.hasNext()) {
                object = new JSONObject((String)jSONObject2.next().getValue());
                jSONObject2 = object.optJSONObject("queryTraffic");
                if (jSONObject2 != null) {
                    jSONObject.put((Object)JsonUtil.getJsonObject("name", jSONObject2.getString("name"), "type", "selectSimCard", "actionType", "queryTraffic"));
                }
                jSONObject2 = object.optJSONObject("queryCharge");
                object = string2;
                if (jSONObject2 != null) {
                    jSONObject.put((Object)JsonUtil.getJsonObject("name", jSONObject2.getString("name"), "type", "selectSimCard", "actionType", "queryCharge"));
                    object = string2;
                }
            }
            break block8;
            {
                catch (Throwable throwable) {}
            }
            catch (Throwable throwable) {
                string2 = null;
                object.printStackTrace();
                object = string2;
            }
        }
        if (object != null) return object.toString();
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String a(String string2, JSONObject jSONObject) {
        new StringBuilder("actionType=").append(string2).append(" pubMenuInfo=").append((Object)jSONObject);
        if (StringUtils.isNull(string2)) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        String string3 = string2;
        if (!string2.startsWith("WEB_")) {
            string3 = string2.toLowerCase();
        }
        if ("reply_sms".equalsIgnoreCase(string3)) {
            stringBuffer.append("{");
            stringBuffer.append("\"type\":\"" + string3 + "\",");
            stringBuffer.append("\"send_code\":\"" + jSONObject.optString("sms") + "\",");
            stringBuffer.append("\"phone\":\"" + jSONObject.optString("sendTo") + "\",");
            stringBuffer.append("\"menuName\":\"" + jSONObject.optString("menuName") + "\",");
            stringBuffer.append("\"publicId\":\"" + jSONObject.optString("pubId") + "\"");
            stringBuffer.append("}");
            return StringUtils.encode(stringBuffer.toString());
        }
        if ("send_sms".equalsIgnoreCase(string3)) {
            stringBuffer.append("{");
            stringBuffer.append("\"type\":\"" + string3 + "\",");
            stringBuffer.append("\"send_code\":\"" + jSONObject.optString("sms") + "\",");
            stringBuffer.append("\"phone\":\"" + jSONObject.optString("sendTo") + "\",");
            stringBuffer.append("\"menuName\":\"" + jSONObject.optString("menuName") + "\",");
            stringBuffer.append("\"publicId\":\"" + jSONObject.optString("pubId") + "\"");
            stringBuffer.append("}");
            return StringUtils.encode(stringBuffer.toString());
        }
        if ("access_url".equalsIgnoreCase(string3) || "open_url".equalsIgnoreCase(string3)) {
            stringBuffer.append("{");
            stringBuffer.append("\"type\":\"" + string3 + "\",");
            stringBuffer.append("\"url\":\"" + jSONObject.optString("url") + "\",");
            stringBuffer.append("\"menuName\":\"" + jSONObject.optString("menuName") + "\",");
            stringBuffer.append("\"publicId\":\"" + jSONObject.optString("pubId") + "\"");
            stringBuffer.append("}");
            return StringUtils.encode(stringBuffer.toString());
        }
        if ("down_url".equalsIgnoreCase(string3)) {
            stringBuffer.append("{");
            stringBuffer.append("\"type\":\"" + string3 + "\",");
            stringBuffer.append("\"url\":\"" + jSONObject.optString("url") + "\",");
            stringBuffer.append("\"menuName\":\"" + jSONObject.optString("menuName") + "\",");
            stringBuffer.append("\"publicId\":\"" + jSONObject.optString("pubId") + "\"");
            stringBuffer.append("}");
            return StringUtils.encode(stringBuffer.toString());
        }
        if ("download".equalsIgnoreCase(string3)) {
            stringBuffer.append("{");
            stringBuffer.append("\"type\":\"" + string3 + "\",");
            stringBuffer.append("\"url\":\"" + jSONObject.optString("url") + "\",");
            stringBuffer.append("\"appName\":\"" + jSONObject.optString("appName") + "\",");
            stringBuffer.append("\"menuName\":\"" + jSONObject.optString("menuName") + "\",");
            stringBuffer.append("\"extend\":\"" + jSONObject.optString("extend") + "\",");
            stringBuffer.append("\"publicId\":\"" + jSONObject.optString("pubId") + "\"");
            stringBuffer.append("}");
            return StringUtils.encode(stringBuffer.toString());
        }
        if ("weibo_url".equalsIgnoreCase(string3)) {
            stringBuffer.append("{");
            stringBuffer.append("\"type\":\"" + string3 + "\",");
            stringBuffer.append("\"url\":\"" + jSONObject.optString("url") + "\",");
            stringBuffer.append("\"menuName\":\"" + jSONObject.optString("menuName") + "\",");
            stringBuffer.append("\"publicId\":\"" + jSONObject.optString("pubId") + "\"");
            stringBuffer.append("}");
            return StringUtils.encode(stringBuffer.toString());
        }
        if ("call_phone".equalsIgnoreCase(string3) || "call".equalsIgnoreCase(string3)) {
            stringBuffer.append("{");
            stringBuffer.append("\"type\":\"" + string3 + "\",");
            stringBuffer.append("\"phoneNum\":\"" + jSONObject.optString("phoneNum") + "\",");
            stringBuffer.append("\"menuName\":\"" + jSONObject.optString("menuName") + "\",");
            stringBuffer.append("\"publicId\":\"" + jSONObject.optString("pubId") + "\"");
            stringBuffer.append("}");
            return StringUtils.encode(stringBuffer.toString());
        }
        if ("map_site".equalsIgnoreCase(string3) || "open_map".equalsIgnoreCase(string3)) {
            stringBuffer.append("{");
            stringBuffer.append("\"type\":\"" + string3 + "\",");
            stringBuffer.append("\"address\":\"" + jSONObject.optString("extend") + "\",");
            stringBuffer.append("\"menuName\":\"" + jSONObject.optString("menuName") + "\",");
            stringBuffer.append("\"publicId\":\"" + jSONObject.optString("pubId") + "\"");
            stringBuffer.append("}");
            return StringUtils.encode(stringBuffer.toString());
        }
        if ("open_map_list".equalsIgnoreCase(string3)) {
            stringBuffer.append("{");
            stringBuffer.append("\"type\":\"" + string3 + "\",");
            stringBuffer.append("\"address\":\"" + jSONObject.optString("extend") + "\",");
            stringBuffer.append("\"menuName\":\"" + jSONObject.optString("menuName") + "\",");
            stringBuffer.append("\"publicId\":\"" + jSONObject.optString("pubId") + "\"");
            stringBuffer.append("}");
            return StringUtils.encode(stringBuffer.toString());
        }
        if ("repayment".equalsIgnoreCase(string3) || "zfb_repayment".equalsIgnoreCase(string3)) {
            stringBuffer.append("{");
            stringBuffer.append("\"type\":\"" + string3 + "\",");
            stringBuffer.append("\"appName\":\"" + jSONObject.optString("extend") + "\",");
            stringBuffer.append("\"appDownUrl\":\"" + jSONObject.optString("url") + "\",");
            stringBuffer.append("\"menuName\":\"" + jSONObject.optString("menuName") + "\",");
            stringBuffer.append("\"publicId\":\"" + jSONObject.optString("pubId") + "\"");
            stringBuffer.append("}");
            return StringUtils.encode(stringBuffer.toString());
        }
        if ("recharge".equalsIgnoreCase(string3) || "zfb_recharge".equalsIgnoreCase(string3)) {
            stringBuffer.append("{");
            stringBuffer.append("\"type\":\"" + string3 + "\",");
            stringBuffer.append("\"sp\":\"" + jSONObject.optString("sp") + "\",");
            stringBuffer.append("\"appName\":\"" + jSONObject.optString("extend") + "\",");
            stringBuffer.append("\"appDownUrl\":\"" + jSONObject.optString("url") + "\",");
            stringBuffer.append("\"menuName\":\"" + jSONObject.optString("menuName") + "\",");
            stringBuffer.append("\"publicId\":\"" + jSONObject.optString("pubId") + "\"");
            stringBuffer.append("}");
            return StringUtils.encode(stringBuffer.toString());
        }
        if ("open_app".equalsIgnoreCase(string3)) {
            stringBuffer.append("{");
            stringBuffer.append("\"type\":\"" + string3 + "\",");
            stringBuffer.append("\"appName\":\"" + jSONObject.optString("extend") + "\",");
            stringBuffer.append("\"appDownUrl\":\"" + jSONObject.optString("url") + "\",");
            stringBuffer.append("\"menuName\":\"" + jSONObject.optString("menuName") + "\",");
            stringBuffer.append("\"publicId\":\"" + jSONObject.optString("pubId") + "\"");
            stringBuffer.append("}");
            return StringUtils.encode(stringBuffer.toString());
        }
        if ("open_app_url".equalsIgnoreCase(string3)) {
            stringBuffer.append("{");
            stringBuffer.append("\"type\":\"" + string3 + "\",");
            stringBuffer.append("\"appName\":\"" + jSONObject.optString("extend") + "\",");
            stringBuffer.append("\"appDownUrl\":\"" + jSONObject.optString("url") + "\",");
            stringBuffer.append("\"menuName\":\"" + jSONObject.optString("menuName") + "\",");
            stringBuffer.append("\"publicId\":\"" + jSONObject.optString("pubId") + "\"");
            stringBuffer.append("}");
            return StringUtils.encode(stringBuffer.toString());
        }
        if (!"WEB_TRAFFIC_ORDER".equalsIgnoreCase(string3)) return StringUtils.encode(stringBuffer.toString());
        stringBuffer.append("{");
        stringBuffer.append("\"type\":\"" + string3 + "\",");
        stringBuffer.append("\"sp\":\"" + jSONObject.optString("sp") + "\",");
        stringBuffer.append("\"appName\":\"" + jSONObject.optString("extend") + "\",");
        stringBuffer.append("\"appDownUrl\":\"" + jSONObject.optString("url") + "\",");
        stringBuffer.append("\"menuName\":\"" + jSONObject.optString("menuName") + "\",");
        stringBuffer.append("\"publicId\":\"" + jSONObject.optString("pubId") + "\"");
        stringBuffer.append("}");
        return StringUtils.encode(stringBuffer.toString());
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static ArrayList<String> a(String var0) {
        block20 : {
            var5_1 = new ArrayList();
            var4_5 = DBManager.rawQuery("select DISTINCT versionCode, pubId from tb_public_info where  pubId  in ( " + (String)var0 + " )", null);
            if (var4_5 == null) break block20;
            var0 = var4_5;
            if (var4_5.getCount() <= 0) break block20;
            var0 = var4_5;
            var1_9 = var4_5.getColumnIndex("pubId");
            var0 = var4_5;
            try {
                var2_10 = var4_5.getColumnIndex("versionCode");
            }
            catch (Throwable var5_2) lbl-1000: // 2 sources:
            {
                var0 = var4_5;
                var5_3.printStackTrace();
                XyCursor.closeCursor(var4_5, true);
                do {
                    return null;
                    break;
                } while (true);
            }
            do {
                block21 : {
                    var0 = var4_5;
                    var3_11 = var4_5.moveToNext();
                    if (var3_11) break block21;
                    XyCursor.closeCursor(var4_5, true);
                    return var5_1;
                }
                var0 = var4_5;
                var6_12 = var4_5.getString(var1_9);
                var0 = var4_5;
                var7_13 = var4_5.getString(var2_10);
                var0 = var4_5;
                var8_14 = new JSONObject();
                var0 = var4_5;
                var8_14.put("num", (Object)var6_12);
                var0 = var4_5;
                var8_14.put("version", (Object)var7_13);
                var0 = var4_5;
                var5_1.add((Object)var8_14.toString());
                continue;
                break;
            } while (true);
            catch (Throwable var4_6) {
                var0 = null;
lbl50: // 2 sources:
                do {
                    XyCursor.closeCursor((XyCursor)var0, true);
                    throw var4_7;
                    break;
                } while (true);
            }
        }
        XyCursor.closeCursor(var4_5, true);
        return null;
        {
            catch (Throwable var4_8) {
                ** continue;
            }
        }
        catch (Throwable var5_4) {
            var4_5 = null;
            ** GOTO lbl-1000
        }
    }

    /*
     * Exception decompiling
     */
    private static HashMap<String, String> a(SQLiteDatabase var0, String var1_6, String var2_8, String var3_9, int var4_10) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 12[UNCONDITIONALDOLOOP]
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
    public static HashMap<String, JSONObject> a(Set<String> var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl57 : TryStatement: try { 3[TRYBLOCK]

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
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static JSONArray a(String var0, String var1_2, int var2_3, boolean var3_4, String var4_5, String var5_6, int var6_7, SdkCallBack var7_8) {
        block10 : {
            block9 : {
                if (!var3_4) ** GOTO lbl5
                try {
                    if ("CN".equals(var1_2)) {
                        return null;
                    }
lbl5: // 3 sources:
                    if ((var9_9 = e.c((String)var0, var1_2, var2_3)) == null) {
                        e.a(var1_2, var4_5, (String)var0, var5_6, var2_3, false, var7_8);
                        return null;
                    }
                    var11_10 = (HashMap<String, String>)var9_9.get((Object)"areaCode");
                    new StringBuilder("areaCode=").append((String)var1_2).append("currentDataCode=").append((String)var11_10);
                    if ("CN".equals((Object)var11_10) && !var9_9.containsKey((Object)"ruleMatch")) {
                        if (!StringUtils.isNull(var1_2) && !"CN".equals(var1_2)) {
                            e.a(var1_2, var4_5, (String)var0, var5_6, var2_3, true, new f(var7_8, (HashMap)var9_9));
                        }
                        if (var3_4) {
                            return null;
                        }
                    }
                    var10_11 = null;
                    var3_4 = "true".equals((Object)SysParamEntityManager.getStringParam(Constant.getContext(), "COMPARE_PUBNUM_OPERATOR"));
                    var8_12 = IccidLocationUtil.getIccidAreaCodeMap().size() > 0 ? 1 : 0;
                    if (var8_12 == 0 || !var3_4 || "CN".equals((Object)var11_10)) break block9;
                    var8_12 = var9_9 == null ? -1 : e.g((String)var9_9.get((Object)"extend"));
                    if (var8_12 == -1) break block9;
                    if ((var1_2 = e.a(var8_12, var4_5, var1_2, var6_7)) == null || !var1_2.startsWith("CN")) ** GOTO lbl30
                    var11_10 = e.c((String)var0, (String)var1_2, var2_3);
                    if (var11_10 == null) ** GOTO lbl-1000
                    var1_2 = var11_10;
                    var9_9 = var10_11;
                    if (!var11_10.containsKey((Object)"pubId")) lbl-1000: // 2 sources:
                    {
                        e.a("CN", var4_5, (String)var0, var5_6, var2_3, false, var7_8);
                        return null;
                    }
                    break block10;
lbl30: // 1 sources:
                    if (StringUtils.isNull((String)var1_2) || !var1_2.contains((CharSequence)"_") || (var0 = var1_2.split("_")).length <= 1) break block9;
                }
                catch (Throwable var0_1) {
                    var0_1.printStackTrace();
                    return null;
                }
                var0 = var0[1];
                var1_2 = var9_9;
                var9_9 = var0;
                ** GOTO lbl41
            }
            var1_2 = var9_9;
            var9_9 = var10_11;
        }
        var0 = e.b(Integer.valueOf((String)((String)var1_2.get((Object)"pubId"))));
        if (StringUtils.isNull(var9_9) != false) return var0;
        if (var0 == null) return var0;
        if (var0.length() <= 0) return var0;
        var0.getJSONObject(0).put("iccid", var9_9);
        return var0;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private static JSONObject a(int var0) {
        var3_1 = new String[]{"id", "pubId", "pubName", "pubType", "classifyCode", "weiXin", "weiBoName", "weiBoUrl", "introduce", "address", "faxNum", "webSite", "versionCode", "email", "parentPubId", "slogan", "rectLogoName", "circleLogoName", "extend", "hasmenu", "loadMenuTime", "updateInfoTime", "moveWebSite"};
        var1_7 = var2_4 = DBManager.query("tb_public_info", (String[])var3_1, "pubId = ? ", new String[]{String.valueOf((int)var0)});
        try {
            var3_1 = BaseManager.loadSingleDataFromCursor((String[])var3_1, var2_4);
        }
        catch (Throwable var3_3) {
            ** GOTO lbl11
        }
        XyCursor.closeCursor(var2_4, true);
        return var3_1;
        catch (Throwable var3_2) {
            var2_4 = null;
lbl11: // 2 sources:
            var1_7 = var2_4;
            var3_1.printStackTrace();
            XyCursor.closeCursor(var2_4, true);
            return null;
        }
        catch (Throwable var1_8) {
            var3_1 = null;
            var2_5 = var1_8;
lbl19: // 2 sources:
            do {
                XyCursor.closeCursor((XyCursor)var3_1, true);
                throw var2_5;
                break;
            } while (true);
        }
        {
            catch (Throwable var2_6) {
                var3_1 = var1_7;
                ** continue;
            }
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static JSONObject a(String var0, String var1_1, int var2_5) {
        block8 : {
            var1_1 = e.c(var0, (String)var1_1, var2_5);
            if (var1_1 == null) {
                return null;
            }
            var2_5 = Integer.valueOf((String)((String)var1_1.get((Object)"pubId")));
            if (var2_5 == -1) return null;
            var0 = e.a(var2_5);
            if (var0 == null) return null;
            var0.put("purpose", var1_1.get((Object)"purpose"));
            var0.put("extend", var1_1.get((Object)"extend"));
            var3_6 = StringUtils.isNull(var0.optString("pubName"));
            var1_1 = var0;
            if (var3_6 != false) return var1_1;
            if (var0 != null) break block8;
            return var0;
            {
                catch (Throwable var1_3) {}
            }
        }
        try {
            var1_1 = new ContentValues();
            var1_1.put("loadMenuTime", Long.valueOf((long)System.currentTimeMillis()));
            var1_1.put("updateInfoTime", Long.valueOf((long)System.currentTimeMillis()));
            DBManager.update("tb_public_info", (ContentValues)var1_1, "pubId = ?", new String[]{var0.optString("pubId")});
            return var0;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return var0;
            ** GOTO lbl-1000
            catch (Throwable var1_4) {
                var0 = null;
            }
lbl-1000: // 2 sources:
            {
                var1_1.printStackTrace();
                return var0;
            }
        }
    }

    private static void a(int n2, String string2, String string3, int n3, String string4, String string5) {
        NetUtil.executeRunnable(new h(n2, string2, string3, n3, string4, string5));
    }

    /*
     * Enabled aggressive block sorting
     */
    private static void a(XyCursor xyCursor, ArrayList<String> arrayList) {
        if (xyCursor != null) {
            int n2 = xyCursor.getColumnIndex("num");
            int n3 = xyCursor.getColumnIndex("versionCode");
            if (xyCursor != null && xyCursor.getCount() > 0) {
                LogManager.e("queryPubInfo", "greatPostData: is :" + xyCursor.getCount());
            } else {
                LogManager.e("queryPubInfo", "greatPostData: is 0");
                return;
            }
            while (xyCursor.moveToNext()) {
                String string2 = xyCursor.getString(n2);
                String string3 = xyCursor.getString(n3);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("num", (Object)string2);
                jSONObject.put("version", (Object)string3);
                arrayList.add((Object)jSONObject.toString());
            }
        }
    }

    private static void a(String string2, String string3) {
        NetUtil.executeRunnable(new i(string2, string3));
    }

    private static void a(String string2, String string3, String string4, String string5, int n2, boolean bl2, SdkCallBack sdkCallBack) {
        try {
            NetUtil.executeRunnable(new g(string4, string5, string2, string3, String.valueOf((int)n2), sdkCallBack, bl2));
            return;
        }
        catch (Throwable var0_1) {
            LogManager.e("PubInfoManager", "queryPubInfoRequestAsync " + var0_1.getMessage(), var0_1);
            return;
        }
    }

    public static void a(ArrayList<String> object) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("lastloadtime", Integer.valueOf((int)0));
        object = object.iterator();
        while (object.hasNext()) {
            DBManager.update("tb_public_num_info", contentValues, "num = ?", new String[]{new JSONObject((String)object.next()).getString("num")});
        }
        return;
    }

    /*
     * Exception decompiling
     */
    public static void a(JSONObject var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [14] lbl140 : TryStatement: try { 10[TRYBLOCK]

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
    private static boolean a(String string2, String string3, int n2, int n3, int n4, String string4) {
        int n5 = string3.indexOf("*");
        if (n5 < 0) return false;
        String string5 = string3.substring(0, n5);
        string3 = string3.substring(n5 + 1);
        if (!string2.startsWith(string5) || !string2.endsWith(string3)) return false;
        n5 = string5.length();
        n5 = string3.length() + n5;
        int n6 = string2.length();
        if (n2 > 0) {
            if (n5 + n2 != n6) return false;
        } else {
            if (n3 > 0 && n6 > n5 + n3) return false;
            {
                if (n4 > 0 && n6 < n5 + n4) {
                    return false;
                }
            }
        }
        if ("sj".equals((Object)string4) && !StringUtils.sj(string2)) return false;
        return true;
    }

    /*
     * Exception decompiling
     */
    public static int b(String var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [8[DOLOOP]], but top level block is 2[TRYBLOCK]
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

    public static String b() {
        return "create table  if not exists tb_public_menu_info ( id INTEGER PRIMARY KEY, menuCode text not null, pubId INTEGER, menuName text not null, menuType text not null, sendTo text, sp text , menuDesc text , sms text, url text, phoneNum text  , actionData text  , extend text  )";
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private static HashMap<String, String> b(String var0, String var1_6, int var2_7) {
        var3_9 = var4_8 = DBManager.getSQLiteDatabase();
        try {
            var5_11 = var6_10 = "SELECT pubId,minLen,maxLen,len,ntype,num,areaCode,purpose,extend from tb_public_num_info where num like '%*' and '" + var0 + "' like  substr(num,1,length(num)-1) || '%'  and ptype = '" + var2_7 + "'";
            var3_9 = var4_8;
            if ("CN".equals((Object)var1_6)) {
                var3_9 = var4_8;
                var5_11 = String.valueOf((Object)var6_10) + " and areaCode = 'CN;'";
            }
            var3_9 = var4_8;
            var0 = e.a(var4_8, var0, String.valueOf((Object)var5_11) + " order by length(num) desc ", var1_6, var2_7);
        }
        catch (Throwable var0_5) {
            ** GOTO lbl15
        }
        DBManager.close(var4_8);
        return var0;
        catch (Throwable var0_1) {
            var4_8 = null;
lbl15: // 2 sources:
            var3_9 = var4_8;
            var0.printStackTrace();
            DBManager.close(var4_8);
            return null;
        }
        catch (Throwable var0_2) {
            var3_9 = null;
lbl22: // 2 sources:
            do {
                DBManager.close(var3_9);
                throw var0_3;
                break;
            } while (true);
        }
        {
            catch (Throwable var0_4) {
                ** continue;
            }
        }
    }

    /*
     * Exception decompiling
     */
    public static HashMap<String, String> b(Set<String> var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl51 : TryStatement: try { 3[TRYBLOCK]

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
     * Exception decompiling
     */
    private static JSONArray b(int var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 11[UNCONDITIONALDOLOOP]
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

    private static void b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("loadMenuTime", Long.valueOf((long)System.currentTimeMillis()));
            contentValues.put("updateInfoTime", Long.valueOf((long)System.currentTimeMillis()));
            DBManager.update("tb_public_info", contentValues, "pubId = ?", new String[]{jSONObject.optString("pubId")});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static int c(String string2) {
        if (StringUtils.isNull(string2)) {
            return -1;
        }
        try {
            int n2 = Integer.parseInt((String)string2);
            return n2;
        }
        catch (Throwable var2_2) {
            LogManager.e("PubInfoManager parseSimIndex", "\u65e0\u6548\u7684\u5361\u4f4d,simIndexStr=" + string2, var2_2);
            return -1;
        }
    }

    public static String c() {
        return "create table  if not exists tb_public_num_info ( id INTEGER PRIMARY KEY, pubId INTEGER not null, num text not null, purpose text , areaCode text not null, ptype int default 1, main INTEGER default 0, communication INTEGER default 0, isfull INTEGER default 0, minLen INTEGER default 0, maxLen INTEGER default 0, len INTEGER default 0, ntype text, extend text, lastloadtime LONG default 0, isrulenum INTEGER default 0)";
    }

    private static String c(int n2) {
        Map.Entry entry;
        String[] arrstring;
        int n3;
        HashMap<String, String[]> hashMap = IccidLocationUtil.getIccidAreaCodeMap();
        if (hashMap == null || hashMap.isEmpty()) {
            return "CN";
        }
        hashMap = hashMap.entrySet().iterator();
        do {
            if (hashMap.hasNext()) continue;
            return "CN";
        } while ((n3 = e.a(arrstring = (String[])(entry = (Map.Entry)hashMap.next()).getValue())) == -2 || n3 != n2 || arrstring == null || arrstring.length <= 0 || StringUtils.isNull(arrstring[0]));
        new StringBuilder("\u5f53\u524d\u63a5\u5165\u7801\u6240\u5c5e\u8fd0\u8425\u5546\u4e0e\u5176\u4e2d\u4e00\u5f20sim\u5361\u7684iccid\u6240\u5c5e\u8fd0\u8425\u5546\u76f8\u540c\u65f6\u8fd4\u56de\u5bf9\u5e94sim\u5361\u7684\u533a\u57df\u7f16\u7801\uff0cnumOperator=").append(n2).append(" AreaCode=").append(arrstring[0]).append(" ICCID=").append((String)entry.getKey());
        return String.valueOf((Object)arrstring[0]) + "_" + (String)entry.getKey();
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static HashMap<String, String> c(String var0, String var1_7, int var2_8) {
        block37 : {
            block40 : {
                block38 : {
                    block39 : {
                        block36 : {
                            var4_9 = DBManager.rawQuery("SELECT pubId,purpose,extend from tb_public_num_info where num = '" + var0 + "' and ptype = '" + var2_8 + "' and areaCode LIKE '%" + (String)var1_7 + "%'", null);
                            if (var4_9 == null) break block36;
                            if (var4_9.getCount() <= 0) break block36;
                            if (!var4_9.moveToNext()) break block37;
                            var2_8 = var4_9.getInt(0);
                            var6_10 = var4_9.getString(1);
                            var7_18 = var4_9.getString(2);
                            var3_20 = false;
                            var5_21 = var6_10;
                            var8_22 = null;
                            var6_10 = var7_18;
                            var7_18 = var4_9;
                            var4_9 = var1_7;
                            var1_7 = var6_10;
                            var6_10 = var8_22;
lbl21: // 6 sources:
                            if (var2_8 > 0 && !var3_20) {
                                var8_22 = var7_18;
                                NetUtil.executeRunnable(new i((String)var0, (String)var4_9));
                            }
                            if (var6_10 != null && var3_20) {
                                var8_22 = var7_18;
                                var6_10.put((Object)"ruleMatch", (Object)"");
                                XyCursor.closeCursor((XyCursor)var7_18, true);
                                do {
                                    return var6_10;
                                    break;
                                } while (true);
                            }
                            ** GOTO lbl106
                        }
                        XyCursor.closeCursor((XyCursor)var4_9, true);
                        if ("CN".equalsIgnoreCase((String)var1_7)) break block38;
                        var5_21 = DBManager.rawQuery("SELECT pubId,purpose,extend from tb_public_num_info where num = '" + var0 + "' and ptype = '" + var2_8 + "' and areaCode LIKE '%CN%'", null);
                        if (var5_21 == null) break block39;
                        if (var5_21.getCount() <= 0) break block39;
                        if (!var5_21.moveToNext()) break block40;
                        var2_8 = var5_21.getInt(0);
                        var1_7 = var5_21.getString(1);
                        var4_9 = var5_21.getString(2);
                        var3_20 = false;
                        var7_18 = var5_21;
                        var6_10 = null;
                        var5_21 = var1_7;
                        var8_22 = "CN";
                        var1_7 = var4_9;
                        var4_9 = var8_22;
                        ** GOTO lbl21
                    }
                    var6_10 = e.b(var0, (String)var1_7, var2_8);
                    var3_20 = true;
                    var4_9 = null;
                    var8_22 = null;
                    var7_18 = var5_21;
                    var2_8 = -1;
                    var1_7 = null;
                    var5_21 = var8_22;
                    ** GOTO lbl21
                }
                var6_10 = e.b(var0, (String)var1_7, var2_8);
                var3_20 = true;
                var5_21 = null;
                var2_8 = -1;
                var7_18 = var4_9;
                var1_7 = null;
                var8_22 = null;
                var4_9 = var5_21;
                var5_21 = var8_22;
                ** GOTO lbl21
                catch (Throwable var6_11) {
                    var1_7 = null;
                    var4_9 = null;
                    var2_8 = -1;
                    var5_21 = null;
                    var0 = null;
lbl85: // 8 sources:
                    var8_22 = var5_21;
                    var6_10.printStackTrace();
                    XyCursor.closeCursor((XyCursor)var5_21, true);
lbl89: // 2 sources:
                    while (var2_8 >= 0) {
                        var5_21 = new HashMap();
                        var5_21.put((Object)"pubId", (Object)String.valueOf((int)var2_8));
                        if (!StringUtils.isNull((String)var4_9)) {
                            var5_21.put((Object)"purpose", var4_9);
                        }
                        if (!StringUtils.isNull((String)var1_7)) {
                            var5_21.put((Object)"areaCode", var1_7);
                        }
                        var6_10 = var5_21;
                        if (StringUtils.isNull(var0)) ** continue;
                        var5_21.put((Object)"extend", var0);
                        return var5_21;
                    }
                    return null;
                }
                catch (Throwable var0_1) {
                    var4_9 = null;
lbl103: // 4 sources:
                    do {
                        XyCursor.closeCursor((XyCursor)var4_9, true);
                        throw var0_2;
                        break;
                    } while (true);
                }
lbl106: // 1 sources:
                XyCursor.closeCursor((XyCursor)var7_18, true);
                var0 = var1_7;
                var1_7 = var4_9;
                var4_9 = var5_21;
                ** GOTO lbl89
                catch (Throwable var0_3) {
                    ** GOTO lbl103
                }
                catch (Throwable var0_4) {
                    var4_9 = var5_21;
                    ** GOTO lbl103
                }
                {
                    catch (Throwable var0_5) {
                        var4_9 = var8_22;
                        ** continue;
                    }
                }
                {
                    catch (Throwable var6_12) {
                        var1_7 = null;
                        var2_8 = -1;
                        var5_21 = var4_9;
                        var0 = null;
                        var4_9 = null;
                        ** GOTO lbl85
                    }
                    catch (Throwable var6_13) {
                        var0 = null;
                        var5_21 = var4_9;
                        var1_7 = null;
                        var4_9 = null;
                        ** GOTO lbl85
                    }
                    catch (Throwable var0_6) {
                        var5_21 = var4_9;
                        var4_9 = var6_10;
                        var1_7 = null;
                        var7_19 = null;
                        var6_10 = var0_6;
                        var0 = var7_19;
                        ** GOTO lbl85
                    }
                }
                {
                    catch (Throwable var6_14) {
                        var1_7 = null;
                        var4_9 = null;
                        var2_8 = -1;
                        var0 = null;
                        ** GOTO lbl85
                    }
                    catch (Throwable var6_15) {
                        var0 = null;
                        var1_7 = null;
                        var4_9 = null;
                        ** GOTO lbl85
                    }
                    catch (Throwable var6_16) {
                        var0 = null;
                        var4_9 = var1_7;
                        var1_7 = null;
                        ** GOTO lbl85
                    }
                }
                catch (Throwable var6_17) {
                    var0 = var1_7;
                    var1_7 = var4_9;
                    var4_9 = var5_21;
                    var5_21 = var7_18;
                    ** GOTO lbl85
                }
            }
            var3_20 = false;
            var4_9 = null;
            var8_22 = null;
            var7_18 = var5_21;
            var2_8 = -1;
            var6_10 = null;
            var1_7 = null;
            var5_21 = var8_22;
            ** GOTO lbl21
        }
        var3_20 = false;
        var5_21 = null;
        var2_8 = -1;
        var7_18 = var4_9;
        var8_22 = null;
        var1_7 = null;
        var6_10 = null;
        var4_9 = var5_21;
        var5_21 = var8_22;
        ** GOTO lbl21
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void c(JSONObject jSONObject) {
        block7 : {
            if (jSONObject == null) {
                return;
            }
            JSONArray jSONArray = jSONObject.optJSONArray("secondmenu");
            if (jSONArray == null) break block7;
            if (jSONArray.length() <= 0) break block7;
            e.d(jSONObject);
            int n2 = jSONArray.length();
            int n3 = 0;
            while (n3 < n2) {
                e.d(jSONArray.getJSONObject(n3));
                ++n3;
                continue;
            }
            return;
        }
        try {
            e.d(jSONObject);
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
     * Lifted jumps to return sites
     */
    public static String d(String var0) {
        if (StringUtils.isNull(var0) != false) return null;
        if (!var0.contains((CharSequence)"action_data")) {
            return null;
        }
        var7_2 = new JSONObject();
        var0 = new JSONArray(var0);
        var4_3 = var0.length();
        var2_4 = 0;
        var1_5 = 0;
        do {
            block11 : {
                if (var2_4 >= var4_3) ** GOTO lbl-1000
                try {
                    var8_9 = var0.getJSONObject(var2_4);
                    var3_6 = var1_5;
                    if (!var8_9.has("secondmenu")) break block11;
                    var8_9 = var8_9.getJSONArray("secondmenu");
                    var3_6 = var1_5;
                    if (var8_9.length() == 0) break block11;
                    var5_7 = var8_9.length();
                    var3_6 = 0;
                    ** GOTO lbl36
                }
                catch (Throwable var0_1) {
                    var0_1.printStackTrace();
                    ** GOTO lbl-1000
                }
lbl-1000: // 1 sources:
                {
                    var9_10 = var8_9.getJSONObject(var3_6);
                    var10_11 = var9_10.getString("name");
                    if (var10_11.contains((CharSequence)"\u67e5") && var10_11.contains((CharSequence)"\u6d41\u91cf")) {
                        var7_2.put("queryTraffic", (Object)var9_10);
                    } else {
                        if (var10_11.contains((CharSequence)"\u67e5") && var10_11.contains((CharSequence)"\u8bdd\u8d39") || var10_11.contains((CharSequence)"\u67e5") && var10_11.contains((CharSequence)"\u4f59\u989d")) {
                            var7_2.put("queryCharge", (Object)var9_10);
                        }
                        if (var7_2.has("queryTraffic") && (var6_8 = var7_2.has("queryCharge"))) {
                            var1_5 = 1;
                            break;
                        }
                    }
                    ++var3_6;
lbl36: // 2 sources:
                    ** while (var3_6 < var5_7)
                }
lbl37: // 2 sources:
                if (var1_5 != 0) lbl-1000: // 3 sources:
                {
                    if (var7_2.length() != 0) return var7_2.toString();
                    return null;
                }
                var3_6 = var1_5;
            }
            ++var2_4;
            var1_5 = var3_6;
        } while (true);
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static ArrayList<String> d() {
        ArrayList arrayList;
        XyCursor xyCursor;
        block4 : {
            arrayList = new ArrayList();
            long l2 = SysParamEntityManager.getLongParam("LastPublicUpdate", 0, Constant.getContext());
            xyCursor = DBManager.rawQuery(" select DISTINCT tb_public_num_info.num , tb_public_info.pubId , tb_public_info.versionCode  from tb_public_num_info join tb_public_info   on tb_public_info.pubId = tb_public_num_info.pubId  and tb_public_num_info.lastloadtime > ?  and  tb_public_num_info.pubId  in ( select  tb_public_info.pubId   from tb_public_num_info join tb_public_info  on tb_public_info.pubId = tb_public_num_info.pubId   and tb_public_num_info.lastloadtime > ? group by tb_public_info.pubId limit 10)", new String[]{String.valueOf((long)l2), String.valueOf((long)l2)});
            e.a(xyCursor, arrayList);
            break block4;
            {
                catch (Throwable throwable) {}
            }
            catch (Throwable throwable) {
                void var3_4;
                xyCursor = null;
                var3_4.printStackTrace();
            }
        }
        XyCursor.closeCursor(xyCursor, true);
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void d(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            ContentValues contentValues = BaseManager.getContentValues(null, "menuCode", jSONObject.optString("menuCode"), "pubId", jSONObject.optString("pubId"), "menuName", jSONObject.optString("name"), "menuType", jSONObject.optString("type"), "extend", jSONObject.optString("extend"), "actionData", jSONObject.optString("action_data"));
            if ((long)DBManager.update("tb_public_menu_info", contentValues, "pubId = ? and menuCode = ?", new String[]{jSONObject.optString("pubId"), jSONObject.optString("menuCode")}) < 1) {
                DBManager.insert("tb_public_menu_info", contentValues);
                new StringBuilder("insert=").append(jSONObject.toString());
                return;
            }
            new StringBuilder("update=").append(jSONObject.toString());
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void e() {
        try {
            DBManager.delete("tb_public_num_info", "isrulenum = 1", null);
            return;
        }
        catch (Throwable var0) {
            var0.printStackTrace();
            return;
        }
    }

    private static void e(String string2) {
        try {
            DBManager.delete("tb_public_menu_info", "pubId = ?", new String[]{string2});
            return;
        }
        catch (Throwable var0_1) {
            return;
        }
    }

    /*
     * Exception decompiling
     */
    private static void e(JSONObject var0) {
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

    private static void f(String string2) {
        try {
            DBManager.delete("tb_public_num_info", " pubId =? ", new String[]{string2});
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
     * Lifted jumps to return sites
     */
    private static void f(JSONObject var0) {
        var1_2 = 0;
        var3_3 = var0.optString("areaCode").split(";");
        var2_4 = var3_3.length;
        ** while (var1_2 < var2_4)
        {
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return;
            }
lbl9: // 1 sources:
            var4_5 = var3_3[var1_2];
            if (!StringUtils.isNull(var4_5)) {
                DBManager.delete("tb_public_num_info", "  ptype = ? and num = ? and areaCode like '%" + var4_5 + "%'  and pubId !=? ", new String[]{String.valueOf((Object)var0.optString("type")), var0.optString("num"), var0.optString("pubId")});
            }
            ++var1_2;
            continue;
        }
lbl15: // 1 sources:
    }

    private static int g(String string2) {
        if (string2 == null || string2.indexOf("{") == -1) {
            return -1;
        }
        try {
            int n2 = new JSONObject(string2).getInt("sp");
            return n2;
        }
        catch (Throwable var2_2) {
            LogManager.e("PubInfoManager getOperatorNumByExtend", "\u516c\u4f17\u53f7\u8fd0\u8425\u5546\u4fe1\u606f\u8f6cJSONObject\u5f02\u5e38,extend=" + string2, var2_2);
            return -1;
        }
    }
}

