/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.System
 *  java.lang.Throwable
 *  java.lang.reflect.Method
 *  java.util.HashMap
 *  java.util.LinkedList
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.util;

import android.content.Context;
import android.content.Intent;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.KeyManager;
import cn.com.xy.sms.sdk.util.PopupMsgManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.a;
import cn.com.xy.sms.util.ParseBubbleManager;
import cn.com.xy.sms.util.ParseManager;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class PopupUtil {
    public static int getActionCode(String string2) {
        block38 : {
            block33 : {
                block31 : {
                    block28 : {
                        block27 : {
                            block26 : {
                                block24 : {
                                    int n2;
                                    block25 : {
                                        block20 : {
                                            block37 : {
                                                block36 : {
                                                    block35 : {
                                                        block34 : {
                                                            block32 : {
                                                                block30 : {
                                                                    block29 : {
                                                                        int n3;
                                                                        block23 : {
                                                                            block22 : {
                                                                                block21 : {
                                                                                    n3 = 3;
                                                                                    if (StringUtils.isNull(string2)) break block20;
                                                                                    if (!string2.equalsIgnoreCase("url")) break block21;
                                                                                    return 3;
                                                                                }
                                                                                if (!string2.equalsIgnoreCase("reply_sms")) break block22;
                                                                                return 1;
                                                                            }
                                                                            if (!string2.equalsIgnoreCase("reply_sms_fwd")) break block23;
                                                                            return 1;
                                                                        }
                                                                        if (string2.equalsIgnoreCase("call_phone") || "call".equalsIgnoreCase(string2)) break block24;
                                                                        if (string2.equalsIgnoreCase("reply_sms_open")) {
                                                                            return 1;
                                                                        }
                                                                        n2 = n3;
                                                                        if (string2.equalsIgnoreCase("access_url")) break block25;
                                                                        n2 = n3;
                                                                        if (string2.equalsIgnoreCase("down_url")) break block25;
                                                                        n2 = n3;
                                                                        if ("download".equalsIgnoreCase(string2)) break block25;
                                                                        n2 = n3;
                                                                        if (string2.equalsIgnoreCase("send_email")) break block25;
                                                                        n2 = n3;
                                                                        if (string2.equalsIgnoreCase("weibo_url")) break block25;
                                                                        if (string2.equalsIgnoreCase("map_site") || "open_map".equalsIgnoreCase(string2) || "open_map_list".equalsIgnoreCase(string2) || "open_map_browser".equalsIgnoreCase(string2)) break block26;
                                                                        if (string2.equalsIgnoreCase("chong_zhi") || string2.equalsIgnoreCase("recharge") || "zfb_recharge".equalsIgnoreCase(string2) || "WEB_CHONG_ZHI".equalsIgnoreCase(string2) || "WEB_RECHARGE_CHOOSE".equalsIgnoreCase(string2)) break block27;
                                                                        if ("repayment".equalsIgnoreCase(string2) || "zfb_repayment".equals((Object)string2) || "WEB_REPAYMENT".equalsIgnoreCase(string2) || "WEB_REPAYMENT_CHOOSE".equalsIgnoreCase(string2)) break block28;
                                                                        if (!string2.equalsIgnoreCase("copy_code")) break block29;
                                                                        return 8;
                                                                    }
                                                                    if (!"open_app".equalsIgnoreCase(string2)) break block30;
                                                                    return 9;
                                                                }
                                                                if ("time_remind".equalsIgnoreCase(string2) || "sdk_time_remind".equalsIgnoreCase(string2)) break block31;
                                                                if (!"pay_water_gas".equalsIgnoreCase(string2)) break block32;
                                                                return 11;
                                                            }
                                                            if ("WEB_TRAFFIC_ORDER".equalsIgnoreCase(string2) || "WEB_TRAFFIC_CHOOSE".equalsIgnoreCase(string2) || "WEB_PURCHASE".equalsIgnoreCase(string2)) break block33;
                                                            if (!"WEB_QUERY_EXPRESS_FLOW".equalsIgnoreCase(string2)) break block34;
                                                            return 13;
                                                        }
                                                        if (!"WEB_QUERY_FLIGHT_TREND".equalsIgnoreCase(string2)) break block35;
                                                        return 14;
                                                    }
                                                    if (!"WEB_INSTALMENT_PLAN".equalsIgnoreCase(string2)) break block36;
                                                    return 15;
                                                }
                                                if (!"WEB_TRAIN_STATION".equalsIgnoreCase(string2)) break block37;
                                                return 16;
                                            }
                                            try {
                                                if (!"WEB_NEAR_SITE".equalsIgnoreCase(string2) && !"near_site".equalsIgnoreCase(string2)) {
                                                    boolean bl2 = "WEB_LIVE_CHOOSE".equalsIgnoreCase(string2);
                                                    if (bl2) {
                                                        return 18;
                                                    }
                                                    return 7;
                                                }
                                                break block38;
                                            }
                                            catch (Throwable var0_1) {
                                                LogManager.e("XIAOYUAN", "PopupUtil getActionCode error: " + var0_1.getMessage(), var0_1);
                                            }
                                        }
                                        n2 = -1;
                                    }
                                    return n2;
                                }
                                return 2;
                            }
                            return 4;
                        }
                        return 5;
                    }
                    return 6;
                }
                return 10;
            }
            return 12;
        }
        return 17;
    }

    public static BusinessSmsMessage getMsg(String string2, String string3) {
        if (!StringUtils.isNull(string2) && !StringUtils.isNull(string3)) {
            BusinessSmsMessage businessSmsMessage = new BusinessSmsMessage();
            businessSmsMessage.setOriginatingAddress(string2);
            businessSmsMessage.setMessageBody(string3);
            businessSmsMessage.isBgVis = true;
            return businessSmsMessage;
        }
        return null;
    }

    public static Map<String, Object> getResultMap(Map<String, Object> map, String string2, String string3, Map<String, Object> map2, Context context) {
        if (ViewUtil.getChannelType() == 3 && !((Boolean)map.get("Result")).booleanValue() && string2 != null && map2 != null) {
            Object object = map2.get("msgId");
            Object object2 = map2.get("simIndex");
            Object object3 = map2.get("simName");
            Object object4 = map2.get("msgTime");
            Object object5 = map2.get("uri");
            map2.clear();
            map2.put("msgId", object);
            map2.put("simIndex", object2);
            map2.put("simName", object3);
            map2.put("msgTime", object4);
            map2.put("phoneNumber", string2);
            map2.put("smsContent", string3);
            map2.put("uri", object5);
            PopupUtil.startBusinessReceiveSmsActivity(context, null, map2);
            map.put("Result", true);
        }
        return map;
    }

    public static Map<String, Object> getResultMap(boolean bl2, boolean bl3) {
        HashMap hashMap = new HashMap();
        hashMap.put("Result", bl2);
        hashMap.put("recogResult", bl3);
        return hashMap;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String getValue(int n2) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            int n3 = 1;
            do {
                if (n3 >= 5) {
                    return StringUtils.decode(StringUtils.handlerAssemble2(a.a(stringBuffer.toString())));
                }
                stringBuffer.append(DuoquUtils.getCode(n3, n2));
                ++n3;
            } while (true);
        }
        catch (Throwable var2_2) {
            var2_2.printStackTrace();
            return "";
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static boolean isEnterpriseSms(Context object, String string2, String string3, Map<String, String> map) {
        object = StringUtils.getPhoneNumberNo86(string2);
        if (StringUtils.isNull((String)object)) {
            return false;
        }
        object = object.replace((CharSequence)" ", (CharSequence)"").replace((CharSequence)"-", (CharSequence)"");
        int n2 = 0;
        do {
            if (n2 >= 43) break;
            if (new String[]{"10088", "10198", "101901", "123662", "12306", "12110110", "121100020", "11888", "11868", "1186666", "118388", "118200", "118114", "118100", "118067", "11803080", "11185", "11183", "13800138000", "095583", "1252004411", "12520", "12520029", "12520035", "125200353", "125200352", "125200304", "125200351", "12520010", "12520021", "125200303", "1252003300000", "12520032", "125200302", "12520028", "12520038", "12520024", "12520036", "125200301", "12520027", "125200354", "1252003300000", "053287003810"}[n2].equals(object)) {
                return true;
            }
            ++n2;
        } while (true);
        n2 = 0;
        do {
            if (n2 >= 9) {
                if (!object.startsWith("12520030") || object.length() > 10) break;
                return true;
            }
            if (object.startsWith(new String[]{"96", "95", "106", "10178", "10086", "1006", "1001", "1000", "116114"}[n2])) {
                return true;
            }
            ++n2;
        } while (true);
        if (!object.startsWith("12520036")) return false;
        if (object.length() != 19) return false;
        return StringUtils.sj(object.replace((CharSequence)"12520036", (CharSequence)""));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isPopupAble(Map<String, Object> map, String string2) {
        Class class_ = Class.forName((String)"cn.com.xy.sms.sdk.ui.popu.util.ViewManger");
        Method method = class_.getMethod("isPopupAble", new Class[]{Map.class, String.class});
        if (method == null) return false;
        try {
            return (Boolean)method.invoke((Object)class_, new Object[]{map, string2});
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void isUseDefaultPopup(BusinessSmsMessage businessSmsMessage, Map<String, Object> map, String string2) {
        if (businessSmsMessage == null || map == null) return;
        try {
            if (!Constant.isDefaultImageExist() || !map.containsKey("view_forceToDefault_popup")) return;
            Constant.getContext();
            map = cn.com.xy.sms.sdk.b.a.a(string2);
            if (map == null) return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
        businessSmsMessage.imagePathMap = map;
    }

    /*
     * Exception decompiling
     */
    public static Map<String, Object> parseMsgToBubbleCardResult(Context var0, String var1_4, String var2_5, String var3_6, String var4_7, long var5_8, byte var7_9, Map<String, Object> var8_10, boolean var9_11) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException: Backjump on non jumping statement [] lbl82 : TryStatement: try { 6[TRYBLOCK]

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
    public static Map<String, Object> parseMsgToPopupWindow(Context var0, String var1_3, String var2_6, Map<String, Object> var3_7) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 8[TRYBLOCK]
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
    public static Map<String, Object> parseMsgToSimpleBubbleResult(Context var0, String var1_1, String var2_2, String var3_3, String var4_5, long var5_6, byte var7_7, Map<String, Object> var8_8, boolean var9_9) {
        if (var8_8 == null) {
            return null;
        }
        var3_3 = (String)var8_8.get("ADACTION");
        if (var7_7 != 1) ** GOTO lbl15
        var10_10 = -1;
        if (!StringUtils.isNull(var4_5 = MatchCacheManager.getMD5(var2_2, var4_5))) {
            var8_8 = StringUtils.getPhoneNumberNo86(var2_2);
            var0 = var3_3 == null ? "" : var3_3;
            var10_10 = MatchCacheManager.insertOrUpdate(BaseManager.getContentValues(null, new String[]{"msg_num_md5", var4_5, "phonenum", var8_8, "msg_id", var1_1, "session_reuslt", var0, "save_time", String.valueOf((long)var5_6), "session_lasttime", String.valueOf((long)System.currentTimeMillis())}), 1);
        }
        if (var3_3 == null) ** GOTO lbl-1000
        var0 = new HashMap();
        var0.put("CACHE_SDK_MSG_ID", (long)var10_10);
        var0.put("CACHE_SDK_MSG_SIMPLE_RESULT", var3_3);
        ** GOTO lbl20
lbl15: // 1 sources:
        if (var3_3 != null) {
            var0 = new HashMap();
            var0.put("CACHE_SDK_MSG_SIMPLE_RESULT", var3_3);
        } else lbl-1000: // 2 sources:
        {
            var0 = null;
        }
lbl20: // 3 sources:
        if (var9_9 == false) return var0;
        if (ParseBubbleManager.equalPhoneNum(var2_2) == false) return var0;
        if (var3_3 == null) ** GOTO lbl26
        try {
            ParseBubbleManager.addEffectiveBubbleData(var2_2, var1_1, new JSONArray(var3_3));
            return var0;
lbl26: // 1 sources:
            ParseBubbleManager.addInvalidBubbleData(var2_2, var1_1);
            return var0;
        }
        catch (Throwable var3_4) {
            ParseBubbleManager.addInvalidBubbleData(var2_2, var1_1);
            var3_4.printStackTrace();
            return var0;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void popupDefault(BusinessSmsMessage businessSmsMessage, Map<String, Object> map, String string2) {
        if (businessSmsMessage == null || map == null) return;
        {
            try {
                map.put("view_forceToDefault_popup", "true");
                if (ViewUtil.getChannelType() == 2 || ViewUtil.getChannelType() == 8) {
                    map.put("View_fdes", "H103102;B502513,10236113;F904");
                } else if (ViewUtil.getChannelType() == 5) {
                    map.put("View_fdes", "H113;B502,10340013;F908906");
                } else {
                    map.put("View_fdes", "H101;B502,11125213;F901");
                }
                map.put("view_title_name", map.get("title_name"));
                map.put("View_viewid", "001");
                if (!Constant.isDefaultImageExist()) return;
                {
                    Constant.getContext();
                    map = cn.com.xy.sms.sdk.b.a.a(string2);
                    if (map == null) return;
                    {
                        businessSmsMessage.imagePathMap = map;
                        return;
                    }
                }
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void startBusinessReceiveSmsActivity(Context context, BusinessSmsMessage businessSmsMessage, Map<String, Object> map) {
        try {
            new StringBuilder("\u53ef\u4ee5\u5f39\u7a97\u4e86").append(System.currentTimeMillis());
            if (businessSmsMessage != null) {
                businessSmsMessage.valueMap = map;
                PopupMsgManager.businessSmsList.addLast((Object)businessSmsMessage);
            } else {
                PopupMsgManager.addThirdPopupMsgData(map);
            }
            businessSmsMessage = new Intent();
            businessSmsMessage.setClassName(context, "cn.com.xy.sms.sdk.ui.popu.BusinessReceiveSmsActivity");
            businessSmsMessage.setFlags(268435456);
            context.startActivity((Intent)businessSmsMessage);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean startWebActivity(Context context, JSONObject jSONObject, String string2, String string3) {
        try {
            Intent intent = new Intent();
            intent.putExtra("actionType", string2);
            if (jSONObject != null) {
                intent.putExtra("JSONDATA", jSONObject.toString());
            }
            if (!StringUtils.isNull(KeyManager.channel)) {
                intent.putExtra("channel", KeyManager.channel);
            }
            if (!StringUtils.isNull(NetUtil.APPVERSION)) {
                intent.putExtra("appVersion", NetUtil.APPVERSION);
            }
            intent.setClassName(context, "cn.com.xy.sms.sdk.ui.popu.web.SdkWebActivity");
            intent.setFlags(268435456);
            context.startActivity(intent);
            if (!StringUtils.isNull(string3)) {
                ParseManager.clearHistorySmsByNum(context, string3, null);
            }
            return true;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return false;
        }
    }
}

