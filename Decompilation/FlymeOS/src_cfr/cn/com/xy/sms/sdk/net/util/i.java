/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.HashMap
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.net.util;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.IccidInfo;
import cn.com.xy.sms.sdk.db.entity.IccidInfoManager;
import cn.com.xy.sms.sdk.db.entity.SceneRule;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.a;
import cn.com.xy.sms.sdk.db.entity.a.b;
import cn.com.xy.sms.sdk.db.entity.a.c;
import cn.com.xy.sms.sdk.db.entity.d;
import cn.com.xy.sms.sdk.db.entity.p;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.net.util.j;
import cn.com.xy.sms.sdk.net.util.k;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.x;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class i {
    public static int a = 0;
    public static int b = 0;
    private static String c = "0";
    private static String d = "1";
    private static String e = "-1";
    private static int f;
    private static String g;

    static {
        a = 0;
        b = 1;
        f = -1;
        g = null;
    }

    public static String a() {
        IccidInfo iccidInfo;
        if (StringUtils.isNull(g) && (iccidInfo = IccidInfoManager.queryDeftIccidInfo(Constant.getContext())) != null && !StringUtils.isNull(iccidInfo.iccid)) {
            g = k.a(iccidInfo.iccid);
        }
        if (StringUtils.isNull(g)) {
            return "";
        }
        return g;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String a(IccidInfo iccidInfo, int n2) {
        StringBuffer stringBuffer = i.b();
        try {
            stringBuffer.append("<queryIccidInfoRequest>");
            stringBuffer.append("<iccid>");
            stringBuffer.append(iccidInfo.iccid);
            stringBuffer.append("</iccid>");
            stringBuffer.append("<cmd>" + n2 + "</cmd>");
            stringBuffer.append("<operator>" + iccidInfo.operator + "</operator>");
            stringBuffer.append("<provinces>" + iccidInfo.provinces + "</provinces>");
            stringBuffer.append("<city>" + iccidInfo.city + "</city>");
            stringBuffer.append("<updateTime>" + iccidInfo.updateTime + "</updateTime>");
            stringBuffer.append("</queryIccidInfoRequest>");
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return stringBuffer.toString();
        }
        do {
            return stringBuffer.toString();
            break;
        } while (true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(String string2) {
        StringBuffer stringBuffer = i.b();
        try {
            stringBuffer.append("<QueryToken>");
            stringBuffer.append("<sdkVersion>");
            stringBuffer.append(DexUtil.getSceneVersion());
            stringBuffer.append("</sdkVersion>");
            stringBuffer.append("<iccid>" + string2 + "</iccid>");
            stringBuffer.append("</QueryToken>");
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return stringBuffer.toString();
        }
        do {
            return stringBuffer.toString();
            break;
        } while (true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(String string2, int n2) {
        StringBuffer stringBuffer = i.b();
        try {
            stringBuffer.append("<checkResourseRequest>");
            stringBuffer.append("<sdk_version>");
            stringBuffer.append(DexUtil.getSceneVersion());
            stringBuffer.append("</sdk_version>");
            stringBuffer.append("<res_type>");
            stringBuffer.append(n2);
            stringBuffer.append("</res_type>");
            stringBuffer.append("<res_version>");
            stringBuffer.append(string2);
            stringBuffer.append("</res_version>");
            stringBuffer.append("</checkResourseRequest>");
        }
        catch (Throwable var0_1) {
            return stringBuffer.toString();
        }
        do {
            return stringBuffer.toString();
            break;
        } while (true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(String string2, int n2, int n3) {
        StringBuffer stringBuffer = i.b();
        try {
            stringBuffer.append("<UpdatePublicInfoRequest>");
            stringBuffer.append("<PublicInfoVersion>");
            stringBuffer.append(string2);
            stringBuffer.append("</PublicInfoVersion>");
            stringBuffer.append("<status>");
            stringBuffer.append(n2);
            stringBuffer.append("</status>");
            stringBuffer.append("<count>");
            stringBuffer.append(n3);
            stringBuffer.append("</count>");
            stringBuffer.append("</UpdatePublicInfoRequest>");
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return stringBuffer.toString();
        }
        do {
            return stringBuffer.toString();
            break;
        } while (true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(String string2, String string3, String string4) {
        StringBuffer stringBuffer = i.b();
        try {
            stringBuffer.append("<QueryLocationRequest>");
            stringBuffer.append("<cNum>");
            stringBuffer.append(string2);
            stringBuffer.append("</cNum>");
            stringBuffer.append("<iccid>" + string3 + "</iccid>");
            stringBuffer.append("<num>" + string4 + "</num>");
            stringBuffer.append("</QueryLocationRequest>");
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return stringBuffer.toString();
        }
        do {
            return stringBuffer.toString();
            break;
        } while (true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(String string2, String string3, String string4, String string5) {
        StringBuffer stringBuffer = i.b();
        try {
            stringBuffer.append("<queryIccidSceneRequest>");
            stringBuffer.append("<iccid>");
            stringBuffer.append(string2);
            stringBuffer.append("</iccid>");
            stringBuffer.append("<cmd>" + string3 + "</cmd>");
            stringBuffer.append("<imei>" + string4 + "</imei>");
            stringBuffer.append("<sceneId>" + string5 + "</sceneId>");
            stringBuffer.append("</queryIccidSceneRequest>");
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return stringBuffer.toString();
        }
        do {
            return stringBuffer.toString();
            break;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String a(String var0, String var1_2, String var2_3, String var3_4, String var4_5) {
        var5_6 = "";
        var7_7 = c.b(StringUtils.getPhoneNumberNo86(var0));
        var6_8 = var5_6;
        if (var7_7 == null) ** GOTO lbl-1000
        if (!StringUtils.isNull(var7_7.d)) {
            var5_6 = var7_7.d;
        }
        var6_8 = var5_6;
        if (!StringUtils.isNull(var7_7.c)) {
            var6_8 = var7_7.c;
        } else lbl-1000: // 2 sources:
        {
            var5_6 = var6_8;
            var6_8 = "";
        }
        var7_7 = i.b();
        try {
            var7_7.append("<QueryPubInfoRequest>");
            var7_7.append("<num>");
            var7_7.append(var0);
            var7_7.append("</num>");
            var7_7.append("<cnum>");
            if (var1_2 != null) {
                var5_6 = var1_2;
            }
            var7_7.append(var5_6);
            var7_7.append("</cnum>");
            var7_7.append("<areaCode>" + var2_3 + "</areaCode>");
            var7_7.append("<iccid>" + var3_4 + "</iccid>");
            var7_7.append("<type>" + var4_5 + "</type>");
            var7_7.append("<sign>");
            var7_7.append(i.g(var6_8));
            var7_7.append("</sign>");
            var7_7.append("</QueryPubInfoRequest>");
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return var7_7.toString();
        }
        return var7_7.toString();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String a(List<SceneRule> var0) {
        block5 : {
            var2_2 = 0;
            var3_3 = 0;
            var5_4 = i.b();
            try {
                var5_4.append("<QuerySceneRuleRequest>");
                var5_4.append("<SceneRuleList>");
                var1_5 = var3_3;
                if (var0 == null) break block5;
                var1_5 = var3_3;
                if (var0.isEmpty()) break block5;
                var4_6 = var0.size();
                var3_3 = 0;
                var1_5 = var2_2;
                var2_2 = var3_3;
                ** GOTO lbl40
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
            }
            return var5_4.toString();
        }
lbl20: // 2 sources:
        do {
            var5_4.append("</SceneRuleList>");
            var5_4.append("<clientVersion>");
            var5_4.append(DexUtil.getSceneVersion());
            var5_4.append("</clientVersion>");
            var5_4.append("</QuerySceneRuleRequest>");
            if (var1_5 != 0) return var5_4.toString();
            return "";
            break;
        } while (true);
lbl-1000: // 1 sources:
        {
            var6_7 = var0.get(var2_2);
            if (!StringUtils.isNull(var6_7.sceneruleVersion)) {
                var5_4.append("<SceneRule>");
                var5_4.append("<id>");
                var5_4.append(var6_7.id);
                var5_4.append("</id>");
                var5_4.append("<version>");
                var5_4.append(var6_7.sceneruleVersion);
                var5_4.append("</version>");
                var5_4.append("</SceneRule>");
                var1_5 = 1;
            }
            ++var2_2;
lbl40: // 2 sources:
            ** while (var2_2 < var4_6)
        }
lbl41: // 1 sources:
        ** while (true)
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String a(List<String> object, String string2, String charSequence, String string3) {
        if (object == null) {
            return "";
        }
        StringBuffer stringBuffer = i.b();
        Iterator<String> iterator = object.iterator();
        try {
            stringBuffer.append("<QueryPubInfoRequest>");
            stringBuffer.append("<areaCode>" + string2 + "</areaCode>");
            stringBuffer.append("<iccid>" + charSequence + "</iccid>");
            stringBuffer.append("<type>" + string3 + "</type>");
            stringBuffer.append("<allNums>");
            do {
                if (iterator.hasNext()) break block8;
                stringBuffer.append("</allNums>");
                stringBuffer.append("</QueryPubInfoRequest>");
                break;
            } while (true);
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return stringBuffer.toString();
        }
        {
            block8 : {
                return stringBuffer.toString();
            }
            string2 = new JSONObject(iterator.next());
            charSequence = new StringBuilder("<num ver =\"").append(string2.optString("version")).append("\" sign =\"");
            object = string2.optString("num");
            if (StringUtils.isNull((String)object)) {
                object = "";
            } else {
                object = i.g(c.a((String)object, true));
            }
            stringBuffer.append(charSequence.append((String)object).append("\">").toString());
            stringBuffer.append(string2.optString("num"));
            stringBuffer.append("</num>");
            continue;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String a(List<d> var0, Map<String, String> var1_2, String var2_3, boolean var3_4) {
        var9_5 = i.b();
        var10_6 = new StringBuffer();
        var11_7 = new StringBuffer();
        var4_8 = 0;
        var5_9 = SysParamEntityManager.getIntParam(Constant.getContext(), "ONLINE_UPDATE_SDK_PERIOD");
        if (var5_9 <= 0) {
            var5_9 = 1;
        }
        var6_10 = 0;
        do {
            if (var6_10 < var0.size()) ** GOTO lbl15
            if (var4_8 == 0) {
                return null;
            }
            ** GOTO lbl42
lbl15: // 1 sources:
            var12_14 = var0.get(var6_10);
            if (var3_4 && System.currentTimeMillis() < var12_14.e + DexUtil.getUpdateCycleByType(8, 86400000 * (long)var5_9)) ** GOTO lbl40
            ++var4_8;
            if (var6_10 != 0) {
                var10_6.append(",");
                var11_7.append(",");
            }
            var10_6.append(var12_14.b);
            var7_11 = var8_13 = var12_14.b;
            if (var8_13.startsWith("PU")) {
                var7_11 = var8_13.replace((CharSequence)"PU", (CharSequence)"");
            }
            if (!StringUtils.isNull(var7_11 = var1_2.get(var7_11))) {
                var10_6.append(var7_11);
            }
            var11_7.append(var12_14.c);
            var7_11 = var12_14.b;
            {
                catch (Throwable var0_1) {
                    var0_1.printStackTrace();
                    return var9_5.toString();
                }
            }
            try {
                var8_13 = new ContentValues();
                var8_13.put("update_time", String.valueOf((long)System.currentTimeMillis()));
                DBManager.update("tb_jar_list", (ContentValues)var8_13, "name = ? ", new String[]{var7_11});
                ** GOTO lbl40
            }
            catch (Throwable var7_12) {
                var7_12.printStackTrace();
lbl40: // 3 sources:
                ++var6_10;
                continue;
lbl42: // 1 sources:
                var9_5.append("<UpdateRecognitionJarRequest>");
                var9_5.append("<reqVersion>");
                var9_5.append(DexUtil.getSuanfaVersion());
                var9_5.append("</reqVersion>");
                var9_5.append("<jarVersion>");
                var9_5.append(var11_7.toString());
                var9_5.append("</jarVersion>");
                var9_5.append("<jarname>");
                var9_5.append(var10_6.toString());
                var9_5.append("</jarname>");
                var9_5.append("<emVer>");
                var9_5.append(var2_3);
                var9_5.append("</emVer>");
                var9_5.append("</UpdateRecognitionJarRequest>");
                return var9_5.toString();
            }
            break;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static Map<String, JSONObject> a(Document var0) {
        var3_1 = new HashMap();
        var4_2 = var0 /* !! */ .getElementsByTagName("info");
        var0 /* !! */  = var0 /* !! */ .getDocumentElement();
        var1_3 = 0;
        block5 : do {
            if (var1_3 >= var4_2.getLength()) {
                return var3_1;
            }
            var5_5 = new JSONObject();
            try {
                var5_5.put("id", (Object)x.a((Element)var0 /* !! */ , "rstCode"));
            }
            catch (JSONException var6_7) {
                var6_7.printStackTrace();
            }
            var7_8 = (Element)var4_2.item(var1_3);
            var6_6 = var7_8.getAttribute("pubId");
            var7_8 = var7_8.getChildNodes();
            var2_4 = 0;
            do {
                block43 : {
                    if (var2_4 >= var7_8.getLength()) {
                        var3_1.put(var6_6, var5_5);
                        ++var1_3;
                        continue block5;
                    }
                    var8_9 = var7_8.item(var2_4);
                    if (var8_9.getNodeType() == 1) {
                        block44 : {
                            var9_11 = var8_9.getNodeName();
                            try {
                                if ("pubId".equalsIgnoreCase(var9_11)) {
                                    var5_5.put("pubId", (Object)x.a(var8_9));
                                    break block43;
                                }
                                if ("pubName".equalsIgnoreCase(var9_11)) {
                                    var5_5.put("pubName", (Object)x.a(var8_9));
                                }
                                if (!"pubType".equalsIgnoreCase(var9_11)) break block44;
                                var5_5.put("pubType", (Object)x.a(var8_9));
                                break block43;
                            }
                            catch (JSONException var8_10) {
                                var8_10.printStackTrace();
                            }
                            ** GOTO lbl87
                        }
                        if ("pubTypeCode".equalsIgnoreCase(var9_11)) {
                            var5_5.put("classifyCode", (Object)x.a(var8_9));
                        } else if ("weiXin".equalsIgnoreCase(var9_11)) {
                            var5_5.put("weiXin", (Object)x.a(var8_9));
                        } else if ("weiBoName".equalsIgnoreCase(var9_11)) {
                            var5_5.put("weiBoName", (Object)x.a(var8_9));
                        } else if ("weiBoUrl".equalsIgnoreCase(var9_11)) {
                            var5_5.put("weiBoUrl", (Object)x.a(var8_9));
                        } else if ("introduce".equalsIgnoreCase(var9_11)) {
                            var5_5.put("introduce", (Object)x.a(var8_9));
                        } else if ("address".equalsIgnoreCase(var9_11)) {
                            var5_5.put("address", (Object)x.a(var8_9));
                        } else if ("faxNum".equalsIgnoreCase(var9_11)) {
                            var5_5.put("faxNum", (Object)x.a(var8_9));
                        } else if ("website".equalsIgnoreCase(var9_11)) {
                            var5_5.put("webSite", (Object)x.a(var8_9));
                        } else if ("versionCode".equalsIgnoreCase(var9_11)) {
                            var5_5.put("versionCode", (Object)x.a(var8_9));
                        } else if ("email".equalsIgnoreCase(var9_11)) {
                            var5_5.put("email", (Object)x.a(var8_9));
                        } else if ("parentPubId".equalsIgnoreCase(var9_11)) {
                            var5_5.put("parentPubId", (Object)x.a(var8_9));
                        } else if ("slogan".equalsIgnoreCase(var9_11)) {
                            var5_5.put("slogan", (Object)x.a(var8_9));
                        } else if ("rectLogoName".equalsIgnoreCase(var9_11)) {
                            var5_5.put("rectLogoName", (Object)x.a(var8_9));
                        } else if ("circleLogoName".equalsIgnoreCase(var9_11)) {
                            var5_5.put("circleLogoName", (Object)x.a(var8_9));
                        } else if ("extend".equalsIgnoreCase(var9_11)) {
                            var5_5.put("extend", (Object)x.a(var8_9));
                        } else if ("moveWebsite".equalsIgnoreCase(var9_11)) {
                            var5_5.put("moveWebsite", (Object)x.a(var8_9));
                        }
                    }
                }
                ++var2_4;
            } while (true);
            break;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static void a(Map<String, JSONObject> var0, Document var1_1) {
        var7_3 = var1_1.getElementsByTagName("menuList");
        var2_4 = 0;
        block6 : do {
            if (var2_4 >= var7_3.getLength()) {
                return;
            }
            var8_10 = new JSONArray();
            var9_11 = new HashMap();
            var1_1 = (Element)var7_3.item(var2_4);
            var10_12 = var1_1.getAttribute("pubId");
            var11_13 = var1_1.getElementsByTagName("menu");
            var3_5 = 0;
            do {
                block52 : {
                    if (var3_5 >= var11_13.getLength()) {
                        var9_11.clear();
                        var1_1 = var0.get(var10_12);
                        if (var1_1 != null) {
                            var1_1.put("pubMenuInfolist", (Object)var8_10);
                        }
                        ++var2_4;
                        continue block6;
                    }
                    var1_1 = ((Element)var11_13.item(var3_5)).getChildNodes();
                    var12_14 = new JSONObject();
                    var4_6 = 0;
                    do lbl-1000: // 2 sources:
                    {
                        block51 : {
                            if (var4_6 < var1_1.getLength()) ** GOTO lbl35
                            var12_14.put("pubId", (Object)var10_12);
                            var5_7 = var12_14.optString("menuType");
                            var1_1 = var6_9 = var12_14.optString("actionData");
                            if ("menu".equalsIgnoreCase((String)var5_7)) ** GOTO lbl198
                            var1_1 = var6_9;
                            if (!StringUtils.isNull(var6_9)) ** GOTO lbl198
                            new StringBuilder("actionType=").append((String)var5_7).append(" pubMenuInfo=").append((Object)var12_14);
                            if (!StringUtils.isNull((String)var5_7)) ** GOTO lbl73
                            var1_1 = "";
                            ** GOTO lbl198
lbl35: // 1 sources:
                            var5_7 = var1_1.item(var4_6);
                            if (var5_7.getNodeType() != 1) ** GOTO lbl-1000
                            var6_9 = var5_7.getNodeName();
                            try {
                                if ("menuCode".equalsIgnoreCase(var6_9)) {
                                    var12_14.put("menuCode", (Object)x.a((Node)var5_7));
                                    ** break block50
                                }
                                if ("menuName".equalsIgnoreCase(var6_9)) {
                                    var12_14.put("menuName", (Object)x.a((Node)var5_7));
                                }
                                if (!"menuDesc".equalsIgnoreCase(var6_9)) break block51;
                                var12_14.put("menuDesc", (Object)x.a((Node)var5_7));
                                ** break block50
                            }
                            catch (Throwable var5_8) {
                                new StringBuilder("generateMenuList error: ").append(var5_8.getMessage());
                            }
                            ** GOTO lbl-1000
                        }
                        if (!"menuType".equalsIgnoreCase(var6_9)) ** GOTO lbl55
                        var12_14.put("menuType", (Object)x.a((Node)var5_7));
                        ** GOTO lbl-1000
lbl55: // 1 sources:
                        if (!"sendTo".equalsIgnoreCase(var6_9)) ** GOTO lbl58
                        var12_14.put("sendTo", (Object)x.a((Node)var5_7));
                        ** GOTO lbl-1000
lbl58: // 1 sources:
                        if (!"sp".equalsIgnoreCase(var6_9)) ** GOTO lbl61
                        var12_14.put("sp", (Object)x.a((Node)var5_7));
                        ** GOTO lbl-1000
lbl61: // 1 sources:
                        if (!"sms".equalsIgnoreCase(var6_9)) ** GOTO lbl64
                        var12_14.put("sms", (Object)x.a((Node)var5_7));
                        ** GOTO lbl-1000
lbl64: // 1 sources:
                        if (!"url".equalsIgnoreCase(var6_9)) ** GOTO lbl67
                        var12_14.put("url", (Object)x.a((Node)var5_7));
                        ** GOTO lbl-1000
lbl67: // 1 sources:
                        if (!"phoneNum".equalsIgnoreCase(var6_9)) ** GOTO lbl70
                        var12_14.put("phoneNum", (Object)x.a((Node)var5_7));
                        ** GOTO lbl-1000
lbl70: // 1 sources:
                        if (!"extend".equalsIgnoreCase(var6_9)) ** GOTO lbl-1000
                        var12_14.put("extend", (Object)x.a((Node)var5_7));
                        ** GOTO lbl-1000
lbl73: // 1 sources:
                        var6_9 = new StringBuffer();
                        var1_1 = var5_7.startsWith("WEB_") == false ? var5_7.toLowerCase() : var5_7;
                        if ("reply_sms".equalsIgnoreCase((String)var1_1)) {
                            var6_9.append("{");
                            var6_9.append("\"type\":\"" + (String)var1_1 + "\",");
                            var6_9.append("\"send_code\":\"" + var12_14.optString("sms") + "\",");
                            var6_9.append("\"phone\":\"" + var12_14.optString("sendTo") + "\",");
                            var6_9.append("\"menuName\":\"" + var12_14.optString("menuName") + "\",");
                            var6_9.append("\"publicId\":\"" + var12_14.optString("pubId") + "\"");
                            var6_9.append("}");
                        } else if ("send_sms".equalsIgnoreCase((String)var1_1)) {
                            var6_9.append("{");
                            var6_9.append("\"type\":\"" + (String)var1_1 + "\",");
                            var6_9.append("\"send_code\":\"" + var12_14.optString("sms") + "\",");
                            var6_9.append("\"phone\":\"" + var12_14.optString("sendTo") + "\",");
                            var6_9.append("\"menuName\":\"" + var12_14.optString("menuName") + "\",");
                            var6_9.append("\"publicId\":\"" + var12_14.optString("pubId") + "\"");
                            var6_9.append("}");
                        } else if ("access_url".equalsIgnoreCase((String)var1_1) || "open_url".equalsIgnoreCase((String)var1_1)) {
                            var6_9.append("{");
                            var6_9.append("\"type\":\"" + (String)var1_1 + "\",");
                            var6_9.append("\"url\":\"" + var12_14.optString("url") + "\",");
                            var6_9.append("\"menuName\":\"" + var12_14.optString("menuName") + "\",");
                            var6_9.append("\"publicId\":\"" + var12_14.optString("pubId") + "\"");
                            var6_9.append("}");
                        } else if ("down_url".equalsIgnoreCase((String)var1_1)) {
                            var6_9.append("{");
                            var6_9.append("\"type\":\"" + (String)var1_1 + "\",");
                            var6_9.append("\"url\":\"" + var12_14.optString("url") + "\",");
                            var6_9.append("\"menuName\":\"" + var12_14.optString("menuName") + "\",");
                            var6_9.append("\"publicId\":\"" + var12_14.optString("pubId") + "\"");
                            var6_9.append("}");
                        } else if ("download".equalsIgnoreCase((String)var1_1)) {
                            var6_9.append("{");
                            var6_9.append("\"type\":\"" + (String)var1_1 + "\",");
                            var6_9.append("\"url\":\"" + var12_14.optString("url") + "\",");
                            var6_9.append("\"appName\":\"" + var12_14.optString("appName") + "\",");
                            var6_9.append("\"menuName\":\"" + var12_14.optString("menuName") + "\",");
                            var6_9.append("\"extend\":\"" + var12_14.optString("extend") + "\",");
                            var6_9.append("\"publicId\":\"" + var12_14.optString("pubId") + "\"");
                            var6_9.append("}");
                        } else if ("weibo_url".equalsIgnoreCase((String)var1_1)) {
                            var6_9.append("{");
                            var6_9.append("\"type\":\"" + (String)var1_1 + "\",");
                            var6_9.append("\"url\":\"" + var12_14.optString("url") + "\",");
                            var6_9.append("\"menuName\":\"" + var12_14.optString("menuName") + "\",");
                            var6_9.append("\"publicId\":\"" + var12_14.optString("pubId") + "\"");
                            var6_9.append("}");
                        } else if ("call_phone".equalsIgnoreCase((String)var1_1) || "call".equalsIgnoreCase((String)var1_1)) {
                            var6_9.append("{");
                            var6_9.append("\"type\":\"" + (String)var1_1 + "\",");
                            var6_9.append("\"phoneNum\":\"" + var12_14.optString("phoneNum") + "\",");
                            var6_9.append("\"menuName\":\"" + var12_14.optString("menuName") + "\",");
                            var6_9.append("\"publicId\":\"" + var12_14.optString("pubId") + "\"");
                            var6_9.append("}");
                        } else if ("map_site".equalsIgnoreCase((String)var1_1) || "open_map".equalsIgnoreCase((String)var1_1)) {
                            var6_9.append("{");
                            var6_9.append("\"type\":\"" + (String)var1_1 + "\",");
                            var6_9.append("\"address\":\"" + var12_14.optString("extend") + "\",");
                            var6_9.append("\"menuName\":\"" + var12_14.optString("menuName") + "\",");
                            var6_9.append("\"publicId\":\"" + var12_14.optString("pubId") + "\"");
                            var6_9.append("}");
                        } else if ("open_map_list".equalsIgnoreCase((String)var1_1)) {
                            var6_9.append("{");
                            var6_9.append("\"type\":\"" + (String)var1_1 + "\",");
                            var6_9.append("\"address\":\"" + var12_14.optString("extend") + "\",");
                            var6_9.append("\"menuName\":\"" + var12_14.optString("menuName") + "\",");
                            var6_9.append("\"publicId\":\"" + var12_14.optString("pubId") + "\"");
                            var6_9.append("}");
                        } else if ("repayment".equalsIgnoreCase((String)var1_1) || "zfb_repayment".equalsIgnoreCase((String)var1_1)) {
                            var6_9.append("{");
                            var6_9.append("\"type\":\"" + (String)var1_1 + "\",");
                            var6_9.append("\"appName\":\"" + var12_14.optString("extend") + "\",");
                            var6_9.append("\"appDownUrl\":\"" + var12_14.optString("url") + "\",");
                            var6_9.append("\"menuName\":\"" + var12_14.optString("menuName") + "\",");
                            var6_9.append("\"publicId\":\"" + var12_14.optString("pubId") + "\"");
                            var6_9.append("}");
                        } else if ("recharge".equalsIgnoreCase((String)var1_1) || "zfb_recharge".equalsIgnoreCase((String)var1_1)) {
                            var6_9.append("{");
                            var6_9.append("\"type\":\"" + (String)var1_1 + "\",");
                            var6_9.append("\"sp\":\"" + var12_14.optString("sp") + "\",");
                            var6_9.append("\"appName\":\"" + var12_14.optString("extend") + "\",");
                            var6_9.append("\"appDownUrl\":\"" + var12_14.optString("url") + "\",");
                            var6_9.append("\"menuName\":\"" + var12_14.optString("menuName") + "\",");
                            var6_9.append("\"publicId\":\"" + var12_14.optString("pubId") + "\"");
                            var6_9.append("}");
                        } else if ("open_app".equalsIgnoreCase((String)var1_1)) {
                            var6_9.append("{");
                            var6_9.append("\"type\":\"" + (String)var1_1 + "\",");
                            var6_9.append("\"appName\":\"" + var12_14.optString("extend") + "\",");
                            var6_9.append("\"appDownUrl\":\"" + var12_14.optString("url") + "\",");
                            var6_9.append("\"menuName\":\"" + var12_14.optString("menuName") + "\",");
                            var6_9.append("\"publicId\":\"" + var12_14.optString("pubId") + "\"");
                            var6_9.append("}");
                        } else if ("open_app_url".equalsIgnoreCase((String)var1_1)) {
                            var6_9.append("{");
                            var6_9.append("\"type\":\"" + (String)var1_1 + "\",");
                            var6_9.append("\"appName\":\"" + var12_14.optString("extend") + "\",");
                            var6_9.append("\"appDownUrl\":\"" + var12_14.optString("url") + "\",");
                            var6_9.append("\"menuName\":\"" + var12_14.optString("menuName") + "\",");
                            var6_9.append("\"publicId\":\"" + var12_14.optString("pubId") + "\"");
                            var6_9.append("}");
                        } else if ("WEB_TRAFFIC_ORDER".equalsIgnoreCase((String)var1_1)) {
                            var6_9.append("{");
                            var6_9.append("\"type\":\"" + (String)var1_1 + "\",");
                            var6_9.append("\"sp\":\"" + var12_14.optString("sp") + "\",");
                            var6_9.append("\"appName\":\"" + var12_14.optString("extend") + "\",");
                            var6_9.append("\"appDownUrl\":\"" + var12_14.optString("url") + "\",");
                            var6_9.append("\"menuName\":\"" + var12_14.optString("menuName") + "\",");
                            var6_9.append("\"publicId\":\"" + var12_14.optString("pubId") + "\"");
                            var6_9.append("}");
                        }
                        var1_1 = StringUtils.encode(var6_9.toString());
lbl198: // 4 sources:
                        var12_14.put("actionData", var1_1);
                        var13_15 = var12_14.optString("menuCode");
                        var6_9 = var12_14.optString("menuName");
                        var14_16 = var12_14.optString("extend");
                        var15_17 = var12_14.optString("pubId");
                        if (var13_15.length() == 2) {
                            if (!"menu".equalsIgnoreCase((String)var5_7)) {
                                var1_1 = JsonUtil.getJsonObject(var12_14, new String[]{"menuCode", var13_15, "pubId", var15_17, "extend", var14_16, "name", var6_9, "type", var5_7, "action_data", var1_1});
                            } else {
                                var6_9 = JsonUtil.getJsonObject(var12_14, new String[]{"menuCode", var13_15, "pubId", var15_17, "extend", var14_16, "name", var6_9, "type", var5_7});
                                var1_1 = (JSONObject)var9_11.get((Object)var13_15);
                                var1_1 = var1_1 != null ? var1_1.optJSONArray("secondmenu") : null;
                                var5_7 = var1_1;
                                if (var1_1 == null) {
                                    var5_7 = new JSONArray();
                                }
                                var6_9.put("secondmenu", var5_7);
                                var9_11.put((Object)var13_15, (Object)var6_9);
                                var1_1 = var6_9;
                            }
                            if (var1_1 != null) {
                                var8_10.put(var1_1);
                            }
                        } else if (var13_15.length() == 4) {
                            var1_1 = JsonUtil.getJsonObject(var12_14, new String[]{"menuCode", var13_15, "pubId", var15_17, "extend", var14_16, "name", var6_9, "type", var5_7, "action_data", var1_1});
                            var5_7 = var13_15.substring(0, 2);
                            var6_9 = (JSONObject)var9_11.get(var5_7);
                            if (var6_9 != null) {
                                var6_9.optJSONArray("secondmenu").put(var1_1);
                            } else {
                                var6_9 = new JSONObject();
                                var12_14 = new JSONArray();
                                var12_14.put(var1_1);
                                var6_9.put("secondmenu", (Object)var12_14);
                                var9_11.put(var5_7, (Object)var6_9);
                            }
                        }
                        break block52;
                        break;
                    } while (true);
                    catch (Throwable var1_2) {}
lbl-1000: // 13 sources:
                    {
                        
                        ++var4_6;
                        ** while (true)
                    }
                }
                ++var3_5;
            } while (true);
            break;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static void a(Element object) {
        if (object == null) {
            return;
        }
        try {
            if ((object = object.getElementsByTagName("rstSign")) == null) return;
            if (object.getLength() == 0) return;
            if (StringUtils.isNull((String)(object = x.a(object.item(0))))) return;
            IccidLocationUtil.iccidPool.execute((Runnable)new j((String)object));
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String b(String string2, String string3, String string4, String string5) {
        StringBuffer stringBuffer = i.b();
        try {
            stringBuffer.append("<QueryPubInfoRequest>");
            stringBuffer.append("<pubId>");
            stringBuffer.append(string2);
            stringBuffer.append("</pubId>");
            stringBuffer.append("<version>" + string3 + "</version>");
            stringBuffer.append("<areaCode>" + string4 + "</areaCode>");
            stringBuffer.append("<iccid>" + string5 + "</iccid>");
            stringBuffer.append("</QueryPubInfoRequest>");
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return stringBuffer.toString();
        }
        do {
            return stringBuffer.toString();
            break;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String b(List<p> var0) {
        block10 : {
            var5_2 = i.b();
            var5_2.append("<QuerySceneRequest>");
            var5_2.append("<SceneList>");
            if (var0 == null) break block10;
            if (var0.isEmpty()) break block10;
            var2_3 = var0.size();
            var1_4 = 0;
        }
lbl13: // 3 sources:
        var5_2.append("</SceneList>");
        var5_2.append("<clientVersion>");
        var5_2.append(DexUtil.getSceneVersion());
        var5_2.append("</clientVersion>");
        var5_2.append("</QuerySceneRequest>");
        do {
            return var5_2.toString();
            break;
        } while (true);
lbl-1000: // 1 sources:
        {
            block11 : {
                try {
                    var3_5 = var0.get(var1_4);
                    var5_2.append("<Scene count='" + var3_5.c + "'>");
                    var5_2.append("<sceneId >");
                    var5_2.append(var3_5.a);
                    var5_2.append("</sceneId>");
                    var5_2.append("<sceneVersion>");
                    var3_5 = var4_6 = var3_5.b;
                }
                catch (Throwable var0_1) {
                    var0_1.printStackTrace();
                    return var5_2.toString();
                }
                if (!StringUtils.isNull(var4_6)) break block11;
                var3_5 = "-1";
            }
            var5_2.append((String)var3_5);
            var5_2.append("</sceneVersion>");
            var5_2.append("</Scene>");
            ++var1_4;
            ** while (var1_4 < var2_3)
        }
lbl42: // 1 sources:
        ** GOTO lbl13
    }

    private static StringBuffer b() {
        return new StringBuffer("<?xml version='1.0' encoding='utf-8'?>");
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static Map<String, JSONObject> b(String map) {
        Element element;
        Object object;
        block6 : {
            if (StringUtils.isNull(map)) {
                return null;
            }
            int n2 = f;
            new HashMap();
            object = StringUtils.stringConvertXML(map, "");
            if (object == null) return null;
            element = object.getDocumentElement();
            if (x.a(x.a(element, "rstCode")) == f) return null;
            map = i.a((Document)object);
            i.b(map, (Document)object);
            i.a(map, (Document)object);
            if (element != null) break block6;
            return map;
            {
                catch (Throwable throwable) {
                    throwable.printStackTrace();
                    return null;
                }
            }
        }
        try {
            object = element.getElementsByTagName("rstSign");
            if (object == null) return map;
            if (object.getLength() == 0) return map;
            if (StringUtils.isNull((String)(object = x.a(object.item(0))))) return map;
            IccidLocationUtil.iccidPool.execute((Runnable)new j((String)object));
            return map;
        }
        catch (Throwable var2_4) {
            var2_4.printStackTrace();
            return map;
        }
    }

    /*
     * Exception decompiling
     */
    private static void b(Map<String, JSONObject> var0, Document var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [3[UNCONDITIONALDOLOOP]], but top level block is 5[UNCONDITIONALDOLOOP]
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
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static a c(String object) {
        a a2 = new a();
        try {
            object = StringUtils.stringConvertXML((String)object, "");
            if (object == null) {
                return a2;
            }
            int n2 = x.a(x.a((Element)(object = object.getDocumentElement()), "rstCode"));
            if (n2 == 0) {
                a2.c = x.a((Element)object, "areacode");
                a2.d = x.a((Element)object, "province");
                a2.e = x.a((Element)object, "city");
                a2.f = x.a((Element)object, "operator");
                return a2;
            }
            a2.a = n2;
            return a2;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return a2;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String c(String string2, String string3, String string4, String string5) {
        StringBuffer stringBuffer = i.b();
        try {
            stringBuffer.append("<QueryMenuInfoRequest>");
            stringBuffer.append("<pubId>");
            stringBuffer.append(string2);
            stringBuffer.append("</pubId>");
            stringBuffer.append("<version>" + string3 + "</version>");
            stringBuffer.append("<areaCode>" + string4 + "</areaCode>");
            stringBuffer.append("<iccid>" + string5 + "</iccid>");
            stringBuffer.append("</QueryMenuInfoRequest>");
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return stringBuffer.toString();
        }
        do {
            return stringBuffer.toString();
            break;
        } while (true);
    }

    public static String d(String object) {
        if ((object = StringUtils.stringConvertXML((String)object, "")) == null) {
            return null;
        }
        return x.a(object.getDocumentElement(), "token");
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String e(String string2) {
        StringBuffer stringBuffer = i.b();
        try {
            stringBuffer.append("<QueryCheciRequest>");
            stringBuffer.append("<cc>" + string2 + "</cc>");
            stringBuffer.append("</QueryCheciRequest>");
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return stringBuffer.toString();
        }
        do {
            return stringBuffer.toString();
            break;
        } while (true);
    }

    private static String f(String string2) {
        if (StringUtils.isNull(string2)) {
            return "";
        }
        return i.g(c.a(string2, true));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static String g(String arrstring) {
        int n2;
        int n3;
        StringBuilder stringBuilder;
        if (StringUtils.isNull((String)arrstring)) {
            return "";
        }
        try {
            stringBuilder = new StringBuilder();
            arrstring = arrstring.split(";");
            n2 = arrstring.length;
            n3 = 0;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return "";
        }
        do {
            if (n3 >= n2) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                return stringBuilder.toString();
            }
            stringBuilder.append(k.a(arrstring[n3]).trim());
            stringBuilder.append(";");
            ++n3;
        } while (true);
    }
}

