/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.graphics.drawable.BitmapDrawable
 *  java.io.File
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.HashMap
 *  java.util.Map$Entry
 *  java.util.concurrent.Executors
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package cn.com.xy.sms.util;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.ParseItemManager;
import cn.com.xy.sms.sdk.db.TrainManager;
import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.e;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.NetWebUtil;
import cn.com.xy.sms.sdk.queue.b;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;
import cn.com.xy.sms.sdk.util.DateUtils;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.PopupMsgManager;
import cn.com.xy.sms.sdk.util.PopupUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.d;
import cn.com.xy.sms.util.ParseBubbleManager;
import cn.com.xy.sms.util.ParseSmsToBubbleUtil;
import cn.com.xy.sms.util.SdkCallBack;
import cn.com.xy.sms.util.SdkParamUtil;
import cn.com.xy.sms.util.f;
import cn.com.xy.sms.util.g;
import cn.com.xy.sms.util.h;
import cn.com.xy.sms.util.i;
import cn.com.xy.sms.util.j;
import cn.com.xy.sms.util.k;
import cn.com.xy.sms.util.l;
import cn.com.xy.sms.util.m;
import cn.com.xy.sms.util.o;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseManager {
    public static final String UPDATE_ICCID_INFO_CACHE_ACTION = "cn.com.xy.sms.iccidinfo.action";
    private static final String a = "yyyyMMdd";
    private static ExecutorService b = Executors.newFixedThreadPool((int)1);
    private static final long c = 604800000;
    private static boolean d = false;
    private static BroadcastReceiver e = new f();
    private static long f = 0;
    private static String g = null;
    private static HashMap<String, Long> h = new HashMap();
    private static ExecutorService i = Executors.newFixedThreadPool((int)1);
    public static long mins = 1;

    static /* synthetic */ BitmapDrawable a(Context context, String string2, String string3, String string4, String string5, int n2, int n3, SdkCallBack sdkCallBack) {
        return ParseManager.b(context, string2, string3, string4, string5, n2, n3, sdkCallBack);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static Map<String, Object> a(Context object, String string2, String string3, String object2, long l2, Map<String, String> object3) {
        synchronized (ParseManager.class) {
            int n2;
            if (object == null) {
                throw new Exception(" Context is null.");
            }
            if (string2 == null) {
                throw new Exception(" phoneNumber is null.");
            }
            if (object2 == null) {
                throw new Exception(" smsContent is null.");
            }
            Object var9_6 = null;
            Object object4 = object3;
            if (object3 == null) {
                object4 = new HashMap();
            }
            if (!object4.containsKey("ALLOW_VERCODE_MSG")) {
                object4.put("ALLOW_VERCODE_MSG", "true");
            }
            if (object4.containsKey("ALLOW_PERSONAL_MSG") && !DuoquUtils.getSdkDoAction().isContact((Context)object, string2)) {
                object4.put("ALLOW_PERSONAL_MSG", "false");
            }
            object4.put("version", DexUtil.getSceneVersion());
            object4.put("channel", SysParamEntityManager.getStringParam((Context)object, "CHANNEL"));
            object4.put("smsCenterNum", string3);
            if (g == null) {
                g = IccidLocationUtil.getProvince();
            }
            if (StringUtils.isNull(g)) {
                object4.put("provice", g);
            }
            if ((n2 = SysParamEntityManager.getIntParam(Constant.getContext(), "RECOGNIZE_LEVEL")) != -1) {
                object4.put("RECOGNIZE_LEVEL", String.valueOf((int)n2));
            }
            object3 = object2.trim();
            object = var9_6;
            if (d.c(Constant.getPARSE_PATH(), "parseUtilMain", "jar")) {
                object = var9_6;
                if (cn.com.xy.sms.sdk.net.util.l.a(Constant.getJarPath()).booleanValue()) {
                    object = object2 = DexUtil.parseMsgToMap(string2, (String)object3, object4);
                    if (object2 == null) {
                        LogManager.e("parseMsg", "result is null phoneNumber:" + string2 + " smsContent: " + (String)object3 + " msgID: " + (String)object4.get("msgId"), null);
                        object = object2;
                    }
                }
            }
            if (!ParseItemManager.isInitData()) {
                object = new HashMap();
                object.put("parseStatu", -1);
            } else {
                if (System.currentTimeMillis() >= f + DexUtil.getUpdateCycleByType(7, 600000)) {
                    cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(2, new String[0]));
                    f = System.currentTimeMillis();
                }
                if (object != null) {
                    object2 = (String)object4.get("simIndex");
                    if (!StringUtils.isNull((String)object2)) {
                        object.put("simIndex", object2);
                    }
                    if (!StringUtils.isNull((String)(object2 = (String)object.get("title_num")))) {
                        if (!StringUtils.isNull((String)(object4 = (String)object4.get("simIccid")))) {
                            cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(1, new String[]{"simIccid", object4, "receiveNum", string2, "sms", object3, "centerNum", string3, "sceneId", object2}));
                        }
                        cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(6, new String[]{"titleNo", object2}));
                        cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(3, new String[]{"titleNo", object2}));
                        cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(8, new String[]{"titleNo", object2}));
                        cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(5, new String[]{"titleNo", object2, "type", "0"}));
                    }
                }
                cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(9, new String[]{"num", string2, "msg", object3, "cnum", string3, "smsTime", String.valueOf((long)l2)}));
            }
            return object;
        }
    }

    private static JSONObject a(String arrstring, String string2, String string3) {
        if (StringUtils.isNull((String)arrstring)) {
            return null;
        }
        if ((arrstring = arrstring.split("/")).length == 1 || StringUtils.isNull(string2) && StringUtils.isNull(string3)) {
            return TrainManager.queryTrainInfo(arrstring[0]);
        }
        int n2 = arrstring.length;
        int n3 = 0;
        while (n3 < n2) {
            JSONObject jSONObject = TrainManager.queryTrainInfo(arrstring[n3]);
            String string4 = (String)JsonUtil.getValueFromJsonObject(jSONObject, "station_list");
            if (!StringUtils.isNull(string4) && ParseManager.checkStationList(string4, string2, string3, false)) {
                return jSONObject;
            }
            ++n3;
        }
        return null;
    }

    private static void a(Context context) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("cn.com.xy.sms.iccidinfo.action");
            context.registerReceiver(e, intentFilter);
            return;
        }
        catch (Throwable var0_1) {
            LogManager.e("initSdk", "registerReceiver error " + var0_1.getMessage(), var0_1);
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
    static /* synthetic */ void a(String var0, String var1_1, String var2_2, String var3_3, SdkCallBack var4_4) {
        if (StringUtils.isNull((String)var1_1)) {
            XyUtil.doXycallBackResult(var4_4, new Object[]{var0, null, var1_1, var2_2, var3_3, false});
            return;
        }
        if (!StringUtils.isNull((String)var1_1)) ** GOTO lbl7
        var11_5 = null;
        ** GOTO lbl11
lbl7: // 1 sources:
        var12_8 = var1_1.split("/");
        if (var12_8.length == 1 || StringUtils.isNull(var2_2) && StringUtils.isNull(var3_3)) {
            var11_5 = TrainManager.queryTrainInfo(var12_8[0]);
        }
        ** GOTO lbl46
lbl11: // 4 sources:
        do {
            var9_12 = 0;
            try {
                var12_8 = (String)JsonUtil.getValueFromJsonObject(var11_5, "data_time");
                var7_13 = var9_12;
                if (var12_8 != null) {
                    var7_13 = Long.parseLong((String)var12_8);
                }
            }
            catch (NumberFormatException var12_10) {
                var7_13 = var9_12;
            }
            if (var11_5 != null) {
                var5_6 = System.currentTimeMillis() - var7_13 > DexUtil.getUpdateCycleByType(21, 604800000) ? 1 : 0;
                if (var5_6 == 0 || !NetUtil.checkAccessNetWork(2)) {
                    try {
                        var12_8 = (String)JsonUtil.getValueFromJsonObject(var11_5, "station_list");
                        if (!StringUtils.isNull((String)var12_8)) {
                            var11_5.put("station_list", (Object)new JSONArray((String)var12_8));
                        }
                    }
                    catch (JSONException var12_9) {
                        var12_9.printStackTrace();
                    }
                    XyUtil.doXycallBackResult(var4_4, new Object[]{var0, var11_5, var1_1, var2_2, var3_3, false});
                    return;
                }
            }
            if (!NetUtil.checkAccessNetWork(2)) {
                XyUtil.doXycallBackResult(var4_4, new Object[]{var0, "offNetwork"});
                return;
            }
            if ((var1_1 = var1_1.split("/")).length == 1 || StringUtils.isNull(var2_2) && StringUtils.isNull(var3_3)) {
                TrainManager.checkDataOnline(var4_4, var0, var1_1[0], var2_2, var3_3);
                return;
            }
            var6_7 = var1_1.length;
            var5_6 = 0;
            while (var5_6 < var6_7) {
                TrainManager.checkDataOnline(var4_4, var0, var1_1[var5_6], var2_2, var3_3);
                ++var5_6;
            }
            return;
            break;
        } while (true);
lbl46: // 1 sources:
        var6_7 = var12_8.length;
        var5_6 = 0;
        do {
            if (var5_6 < var6_7) ** GOTO lbl52
            var11_5 = null;
            ** GOTO lbl11
lbl52: // 1 sources:
            var11_5 = TrainManager.queryTrainInfo((String)var12_8[var5_6]);
            var13_11 = (String)JsonUtil.getValueFromJsonObject(var11_5, "station_list");
            if (StringUtils.isNull(var13_11) || !ParseManager.checkStationList(var13_11, var2_2, var3_3, false)) ** break;
            ** continue;
            ++var5_6;
        } while (true);
    }

    private static boolean a(long l2) {
        if (System.currentTimeMillis() - l2 > DexUtil.getUpdateCycleByType(21, 604800000)) {
            return true;
        }
        return false;
    }

    public static String addQueryTrafficAndChargeToMenuData(String string2, Map<String, String> map) {
        return cn.com.xy.sms.sdk.db.entity.a.e.a(string2, map);
    }

    public static void addViewVersion(String string2, String string3) {
        string2 = String.valueOf((Object)string3) + "_" + string2;
        SysParamEntityManager.insertOrUpdateKeyValue(Constant.getContext(), "bubbleViewVersion", string2, null);
        SysParamEntityManager.cacheMap.put((Object)"bubbleViewVersion", (Object)string2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static BitmapDrawable b(Context object, String string2, String string3, String string4, String string5, int n2, int n3, SdkCallBack sdkCallBack) {
        Object var9_10;
        String string6;
        String string7;
        block11 : {
            block12 : {
                block10 : {
                    string7 = null;
                    var9_10 = null;
                    string6 = string7;
                    try {
                        if (NetUtil.checkAccessNetWork(2)) break block10;
                        object = var9_10;
                        if (sdkCallBack == null) return object;
                        string6 = string7;
                    }
                    catch (Throwable var0_1) {
                        return string6;
                    }
                    sdkCallBack.execute(null);
                    return null;
                }
                string6 = string7;
                h.put((Object)string5, (Object)System.currentTimeMillis());
                string6 = string7;
                if (d.g(string2, string3, string4) != 0) break block11;
                string6 = string7;
                h.remove((Object)string5);
                string6 = string7;
                string2 = ViewUtil.createBitmapByPath2((Context)object, string5, n2, n3);
                if (sdkCallBack == null) break block12;
                string6 = string2;
                sdkCallBack.execute(new Object[]{string2});
            }
            object = string2;
            if (string2 == null) return object;
            string6 = string2;
            h.remove((Object)string5);
            return string2;
        }
        object = var9_10;
        if (sdkCallBack == null) return object;
        string6 = string7;
        sdkCallBack.execute(null);
        return var9_10;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static void b(String var0, String var1_1, String var2_2, String var3_3, SdkCallBack var4_4) {
        if (StringUtils.isNull((String)var1_1)) {
            XyUtil.doXycallBackResult(var4_4, new Object[]{var0, null, var1_1, var2_2, var3_3, false});
            return;
        }
        if (!StringUtils.isNull((String)var1_1)) ** GOTO lbl7
        var11_5 = null;
        ** GOTO lbl11
lbl7: // 1 sources:
        var12_8 = var1_1.split("/");
        if (var12_8.length == 1 || StringUtils.isNull(var2_2) && StringUtils.isNull(var3_3)) {
            var11_5 = TrainManager.queryTrainInfo(var12_8[0]);
        }
        ** GOTO lbl46
lbl11: // 4 sources:
        do {
            var9_12 = 0;
            try {
                var12_8 = (String)JsonUtil.getValueFromJsonObject(var11_5, "data_time");
                var7_13 = var9_12;
                if (var12_8 != null) {
                    var7_13 = Long.parseLong((String)var12_8);
                }
            }
            catch (NumberFormatException var12_10) {
                var7_13 = var9_12;
            }
            if (var11_5 != null) {
                var5_6 = System.currentTimeMillis() - var7_13 > DexUtil.getUpdateCycleByType(21, 604800000) ? 1 : 0;
                if (var5_6 == 0 || !NetUtil.checkAccessNetWork(2)) {
                    try {
                        var12_8 = (String)JsonUtil.getValueFromJsonObject(var11_5, "station_list");
                        if (!StringUtils.isNull((String)var12_8)) {
                            var11_5.put("station_list", (Object)new JSONArray((String)var12_8));
                        }
                    }
                    catch (JSONException var12_9) {
                        var12_9.printStackTrace();
                    }
                    XyUtil.doXycallBackResult(var4_4, new Object[]{var0, var11_5, var1_1, var2_2, var3_3, false});
                    return;
                }
            }
            if (!NetUtil.checkAccessNetWork(2)) {
                XyUtil.doXycallBackResult(var4_4, new Object[]{var0, "offNetwork"});
                return;
            }
            if ((var1_1 = var1_1.split("/")).length == 1 || StringUtils.isNull(var2_2) && StringUtils.isNull(var3_3)) {
                TrainManager.checkDataOnline(var4_4, var0, var1_1[0], var2_2, var3_3);
                return;
            }
            var6_7 = var1_1.length;
            var5_6 = 0;
            while (var5_6 < var6_7) {
                TrainManager.checkDataOnline(var4_4, var0, var1_1[var5_6], var2_2, var3_3);
                ++var5_6;
            }
            return;
            break;
        } while (true);
lbl46: // 1 sources:
        var6_7 = var12_8.length;
        var5_6 = 0;
        do {
            if (var5_6 < var6_7) ** GOTO lbl52
            var11_5 = null;
            ** GOTO lbl11
lbl52: // 1 sources:
            var11_5 = TrainManager.queryTrainInfo((String)var12_8[var5_6]);
            var13_11 = (String)JsonUtil.getValueFromJsonObject(var11_5, "station_list");
            if (StringUtils.isNull(var13_11) || !ParseManager.checkStationList(var13_11, var2_2, var3_3, false)) ** break;
            ** continue;
            ++var5_6;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void checkDataForUpdate(Map<String, String> map, SdkCallBack sdkCallBack) {
        try {
            if (!NetUtil.checkAccessNetWork(map)) {
                XyUtil.doXycallBack(sdkCallBack, "-1");
                return;
            }
            if (!ParseItemManager.isInitData()) {
                XyUtil.doXycallBack(sdkCallBack, "0");
                return;
            }
            if (cn.com.xy.sms.sdk.util.i.b()) {
                XyUtil.doXycallBack(sdkCallBack, "1");
                bx.a(map, null);
                return;
            }
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
        bx.a(map, sdkCallBack);
    }

    public static boolean checkStationList(String string2, String string3, String string4, boolean bl2) {
        block12 : {
            block13 : {
                JSONArray jSONArray;
                block11 : {
                    if (string2 == null) {
                        return false;
                    }
                    jSONArray = new JSONArray(string2);
                    if (!StringUtils.isNull(string3) || StringUtils.isNull(string4)) break block11;
                    string2 = (JSONObject)jSONArray.get(0);
                    if (string2 == null) break block12;
                    if (!string2.getString("name").trim().equalsIgnoreCase(string4.trim())) {
                        return true;
                    }
                }
                if (!StringUtils.isNull(string4) || StringUtils.isNull(string3)) break block13;
                string2 = (JSONObject)jSONArray.get(jSONArray.length() - 1);
                if (string2 == null) break block12;
                if (!string2.getString("name").trim().equalsIgnoreCase(string3.trim())) {
                    return true;
                }
            }
            try {
                if (!StringUtils.isNull(string3) && !StringUtils.isNull(string4)) {
                    int n2 = string2.indexOf("\"" + string3 + "\"");
                    int n3 = string2.indexOf("\"" + string4 + "\"");
                    if (n2 != -1 && n3 != -1 && n2 < n3) {
                        return true;
                    }
                }
            }
            catch (JSONException var0_1) {
                var0_1.printStackTrace();
            }
        }
        return false;
    }

    public static void clearHistorySmsByNum(Context context, String string2, Map<String, String> map) {
        PopupMsgManager.removeBusinessMessageByNum(context, string2, false, map);
    }

    public static void deleteMatchCache(String string2, long l2) {
        try {
            MatchCacheManager.deleteMatchCache(string2, l2);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void deleteMatchCache(String string2, String string3, long l2) {
        try {
            MatchCacheManager.deleteMatchCache(string2, string3, l2);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static boolean doAction(Activity activity, String string2, Map<String, String> map) {
        return DuoquUtils.doAction(activity, string2, map);
    }

    public static void executeQueryTrainInfoRunnable(Runnable runnable) {
        b.execute(runnable);
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static BitmapDrawable findLogoByLogoName(Context var0, String var1_4, int var2_6, int var3_7, int var4_8, Map<String, String> var5_9, SdkCallBack var6_10) {
        synchronized (ParseManager.class) {
            block34 : {
                block35 : {
                    block33 : {
                        block31 : {
                            block32 : {
                                var10_11 = Constant.getPath("duoqu_publiclogo");
                                var9_12 = String.valueOf((Object)var10_11) + (String)var1_4;
                                var11_13 = new File(var9_12);
                                if (!var11_13.exists()) break block31;
                                new StringBuilder("logoPath=").append(var9_12).append("\u6587\u4ef6\u5b58\u5728\uff0cdecode");
                                var0 = ViewUtil.createBitmapByPath2((Context)var0, (File)var11_13, var2_6, var3_7);
                                if (var0 == null) break block32;
                                XyUtil.doXycallBackResult(var6_10, new Object[]{var0});
                                var1_4 = (Long)ParseManager.h.get((Object)"runResourseQueue");
                                if (var1_4 != null) {
                                    if (System.currentTimeMillis() <= var1_4.longValue() + DexUtil.getUpdateCycleByType(20, 3600000)) return var0;
                                }
                                cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(7, new String[0]));
                                ParseManager.h.put((Object)"runResourseQueue", (Object)System.currentTimeMillis());
                                do {
                                    return var0;
                                    break;
                                } while (true);
                            }
                            ParseManager.h.put((Object)var9_12, (Object)System.currentTimeMillis());
                            XyUtil.doXycallBackResult(var6_10, null);
                            var0 = (Long)ParseManager.h.get((Object)"runResourseQueue");
                            if (var0 == null) ** GOTO lbl29
                            if (System.currentTimeMillis() <= var0.longValue() + DexUtil.getUpdateCycleByType(20, 3600000)) return null;
lbl29: // 2 sources:
                            cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(7, new String[0]));
                            ParseManager.h.put((Object)"runResourseQueue", (Object)System.currentTimeMillis());
                            return null;
                        }
                        var11_13 = (Long)ParseManager.h.get((Object)var9_12);
                        if (var11_13 == null) break block33;
                        if (System.currentTimeMillis() >= var11_13.longValue() + DexUtil.getUpdateCycleByType(19, 60 * ParseManager.mins * 1000)) break block33;
                        new StringBuilder("logoPath=").append(var9_12).append(" \u8fd8\u6ca1\u5230\u65f6\u95f4");
                        XyUtil.doXycallBackResult(var6_10, null);
                        var0 = (Long)ParseManager.h.get((Object)"runResourseQueue");
                        if (var0 == null) ** GOTO lbl45
                        if (System.currentTimeMillis() <= var0.longValue() + DexUtil.getUpdateCycleByType(20, 3600000)) return null;
lbl45: // 2 sources:
                        cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(7, new String[0]));
                        ParseManager.h.put((Object)"runResourseQueue", (Object)System.currentTimeMillis());
                        return null;
                    }
                    var11_13 = "http://down2.bizport.cn/publicnum/upload/" + (String)var1_4;
                    var7_15 = var8_14 = false;
                    if (var5_9 == null) break block34;
                    var7_15 = var8_14;
                    if (var5_9.isEmpty()) break block35;
                    var7_15 = "true".equals(var5_9.get("syn"));
                }
                if (var7_15) ** GOTO lbl74
            }
            ParseManager.i.execute((Runnable)new i((Context)var0, (String)var11_13, var10_11, (String)var1_4, var9_12, var2_6, var3_7, var6_10));
            var0 = null;
lbl63: // 2 sources:
            do {
                var5_9 = (Long)ParseManager.h.get((Object)"runResourseQueue");
                if (var5_9 == null) ** GOTO lbl70
                var1_4 = var0;
                if (System.currentTimeMillis() <= var5_9 + DexUtil.getUpdateCycleByType(20, 3600000)) return var1_4;
lbl70: // 2 sources:
                cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(7, new String[0]));
                ParseManager.h.put((Object)"runResourseQueue", (Object)System.currentTimeMillis());
                var1_4 = var0;
                return var1_4;
                break;
            } while (true);
lbl74: // 1 sources:
            try {
                var0 = ParseManager.b((Context)var0, (String)var11_13, var10_11, (String)var1_4, var9_12, var2_6, var3_7, var6_10);
                ** continue;
            }
            catch (Throwable var0_1) {
                var0 = null;
lbl80: // 3 sources:
                var5_9 = (Long)ParseManager.h.get((Object)"runResourseQueue");
                if (var5_9 == null) ** GOTO lbl85
                var1_4 = var0;
                if (System.currentTimeMillis() <= var5_9 + DexUtil.getUpdateCycleByType(20, 3600000)) return var1_4;
lbl85: // 2 sources:
                cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(7, new String[0]));
                ParseManager.h.put((Object)"runResourseQueue", (Object)System.currentTimeMillis());
                return var0;
            }
            catch (Throwable var0_2) {
                var1_4 = (Long)ParseManager.h.get((Object)"runResourseQueue");
                if (var1_4 == null) ** GOTO lbl96
                {
                    catch (Throwable var0_3) {
                        throw var0_3;
                    }
                }
                if (System.currentTimeMillis() <= var1_4.longValue() + DexUtil.getUpdateCycleByType(20, 3600000)) throw var0_2;
lbl96: // 2 sources:
                cn.com.xy.sms.sdk.queue.g.a(new cn.com.xy.sms.sdk.queue.i(7, new String[0]));
                ParseManager.h.put((Object)"runResourseQueue", (Object)System.currentTimeMillis());
                throw var0_2;
            }
            finally {
            }
            catch (Throwable var1_5) {
                ** GOTO lbl80
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String getBubbleViewVersion(Map<String, Object> object) {
        object = "20151014";
        if (StringUtils.isNull(Constant.current_bubble_version)) {
            Constant.current_bubble_version = "20151014";
            return object;
        } else {
            String string2 = SysParamEntityManager.getStringParam(Constant.getContext(), "bubbleViewVersion");
            if (StringUtils.isNull(string2)) return object;
            {
                object = string2.split("_");
                string2 = object[0];
                String string3 = object[1];
                Constant.current_bubble_version = string2;
                String string4 = DateUtils.getCurrentTimeString("yyyyMMdd");
                object = string2;
                if (!DateUtils.compareDateString(string4, string3, "yyyyMMdd")) return object;
                {
                    new j(string4);
                    return string2;
                }
            }
        }
    }

    public static int getOperatorByNum(String string2) {
        if (StringUtils.isNull(string2)) {
            return -1;
        }
        return IccidLocationUtil.getOperatorByNum(StringUtils.getPhoneNumberNo86(string2));
    }

    public static int getOperatorNumByPubNum(String string2) {
        return cn.com.xy.sms.sdk.db.entity.a.e.b(string2);
    }

    public static int getParseVersion(Context object, Map map) {
        try {
            object = SdkParamUtil.getParamValue((Context)object, "PARSE_VERSION");
            if (!StringUtils.isNull((String)object)) {
                int n2 = Integer.parseInt((String)object);
                return n2;
            }
        }
        catch (Throwable var0_1) {
            LogManager.e("XIAOYUAN", "getParseVersion" + var0_1.getLocalizedMessage(), var0_1);
        }
        return 0;
    }

    public static String getSdkVersion() {
        return NetUtil.APPVERSION;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void initSdk(Context object, String string2, String string3, boolean bl2, boolean bl3, Map<String, String> map) {
        if (object == null) {
            throw new Exception("context is null,please check.");
        }
        Constant.initContext((Context)object);
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("cn.com.xy.sms.iccidinfo.action");
            object.registerReceiver(e, intentFilter);
        }
        catch (Throwable var6_7) {
            LogManager.e("initSdk", "registerReceiver error " + var6_7.getMessage(), var6_7);
        }
        object = new g((Context)object, string2, string3, bl2, bl3, map);
        if (map != null && map.containsKey("SYNCHRONIZED")) {
            object.run();
            return;
        }
        NetUtil.executeRunnable((Runnable)object);
    }

    public static boolean isEnterpriseSms(Context context, String string2, String string3, Map<String, String> map) {
        if (!cn.com.xy.sms.sdk.net.util.l.a(7)) {
            return false;
        }
        if (d.c(Constant.getPARSE_PATH(), "parseUtilMain", "jar") && cn.com.xy.sms.sdk.net.util.l.a(Constant.getJarPath()).booleanValue()) {
            return DexUtil.isEnterpriseSms(context, string2, string3, map);
        }
        return PopupUtil.isEnterpriseSms(context, string2, string3, map);
    }

    public static boolean isInitData() {
        return ParseItemManager.isInitData();
    }

    public static boolean ismUseNewDes() {
        return d;
    }

    public static HashMap<String, JSONObject> loadAllPubInfo(Set<String> set) {
        return cn.com.xy.sms.sdk.db.entity.a.e.a(set);
    }

    public static HashMap<String, String> loadAllPubNum(Set<String> set) {
        return cn.com.xy.sms.sdk.db.entity.a.e.b(set);
    }

    public static void loadLocation(String string2, int n2, String string3, boolean bl2) {
        IccidLocationUtil.getAreaCodeByCnumOrIccid(null, n2, string2, string3, bl2, true);
    }

    public static boolean needUpdateJar() {
        return e.e();
    }

    public static void parseMsgCallBack(Context context, String string2, String string3, String string4, Map<String, String> map) {
        try {
            ParseSmsToBubbleUtil.backGroundHandleMapByType(map, ParseManager.a(context, string2, string3, string4, 0, map));
            return;
        }
        catch (Throwable var0_1) {
            throw new RuntimeException(var0_1);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String parseMsgToBubble(Context object, String string2, String string3, String string4, Map<String, String> hashMap) {
        if (!cn.com.xy.sms.sdk.net.util.l.a(2)) {
            return null;
        }
        if (hashMap == null) {
            hashMap = new HashMap();
        }
        hashMap.put("popup_type", "2");
        object = ParseManager.a((Context)object, string2, string3, string4, 0, hashMap);
        if (object == null) return null;
        object = string2 = (String)object.get("ADACTION");
        if (string2 != null) return object;
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Map<String, Object> parseMsgToBubbleCardResult(Context hashMap, String string2, String string3, String string4, String string5, long l2, byte by2, Map<String, String> hashMap2) {
        if (!cn.com.xy.sms.sdk.net.util.l.a(9)) {
            new StringBuilder("msgId: ").append(string2).append(" parseMsgToBubbleCardResult power not valid");
            return null;
        }
        long l3 = System.currentTimeMillis();
        Map<String, Object> map = ParseManager.a((Context)hashMap, string3, string4, string5, l2, hashMap2);
        long l4 = System.currentTimeMillis();
        new StringBuilder("parseMsg time:").append(l4 - l3);
        hashMap2 = map;
        if (ParseBubbleManager.getParseStatu(map) == -1) return hashMap2;
        if (ParseItemManager.execNqSql) {
            new StringBuilder("ParseItemManager.execNqSql: ").append(ParseItemManager.execNqSql);
            hashMap = map;
            if (map == null) {
                hashMap = new HashMap();
            }
            hashMap.clear();
            hashMap.put("parseStatu", "-2");
            return hashMap;
        }
        hashMap2 = hashMap = PopupUtil.parseMsgToBubbleCardResult((Context)hashMap, string2, string3, string4, string5, l2, by2, map, false);
        if (hashMap != null) return hashMap2;
        new StringBuilder("cardResult: ").append((Object)hashMap);
        return hashMap;
    }

    public static Map<String, Object> parseMsgToMap(Context context, String string2, String string3, String string4, Map<String, String> hashMap) {
        if (hashMap == null) {
            hashMap = new HashMap();
        }
        hashMap.put("popup_type", "2");
        new StringBuilder("parseMsgToMap: ").append(string2).append(" msg: ").append(string4);
        return ParseManager.a(context, string2, string3, string4, 0, hashMap);
    }

    public static Map<String, Object> parseMsgToPopupWindow(Context context, String string2, String string3, String string4, Map<String, String> map) {
        return ParseManager.parseMsgToPopupWindow(context, string2, string3, string4, false, map);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static Map<String, Object> parseMsgToPopupWindow(Context var0, String var1_1, String var2_2, String var3_3, Map<String, Object> var4_4, boolean var5_5, Map<String, String> var6_6) {
        if (var4_4 == null || var4_4.size() <= 1) ** GOTO lbl20
        if (var6_6 == null || var6_6.isEmpty()) ** GOTO lbl39
        if (var5_5) {
            var8_7 = var6_6.get("msgId");
            var9_8 = var6_6.get("msgTime");
            if (!StringUtils.isNull(var8_7) && !StringUtils.isNull(var9_8)) {
                ParseSmsToBubbleUtil.backGroundParseSmsBubble(var8_7, var1_1, var3_3, var2_2, Long.valueOf((String)var9_8), false, true, var4_4);
            }
        }
        ParseSmsToBubbleUtil.backGroundHandleMapByType(var6_6, var4_4);
        var2_2 = var6_6.get("POPUP_SHOW_MASTER");
        if (var2_2 == null) ** GOTO lbl39
        if ("0".equals((Object)var2_2.trim())) {
            return PopupUtil.getResultMap(false, true);
        }
        if (!"1".equals((Object)var2_2.trim())) ** GOTO lbl18
        var2_2 = (String)var4_4.get("title_num");
        if (var2_2.startsWith("01") != false ? (var2_2 = var6_6.get("POPUP_SHOW_BANK")) != null && "0".equals((Object)var2_2.trim()) != false : (var2_2.startsWith("02") != false ? (var2_2 = var6_6.get("POPUP_SHOW_SP")) != null && "0".equals((Object)var2_2.trim()) != false : (var2_2 = var6_6.get("POPUP_SHOW_LIFE")) != null && "0".equals((Object)var2_2.trim()) != false)) {
            return PopupUtil.getResultMap(false, true);
        }
        ** GOTO lbl39
lbl18: // 1 sources:
        if (!"2".equals((Object)var2_2.trim())) ** GOTO lbl39
        ** GOTO lbl28
lbl20: // 1 sources:
        if (ViewUtil.getChannelType() != 3) return PopupUtil.getResultMap(false, false);
        if (var6_6 != null && !var6_6.isEmpty() && (var2_2 = var6_6.get("POPUP_SHOW_MASTER")) != null && "0".equals((Object)var2_2.trim())) {
            return PopupUtil.getResultMap(false, false);
        }
        var2_2 = null;
        if (var6_6 == null) return PopupUtil.getResultMap(PopupUtil.getResultMap(false, false), var1_1, var3_3, var2_2, var0);
        var2_2 = new HashMap();
        var2_2.putAll(var6_6);
        return PopupUtil.getResultMap(PopupUtil.getResultMap(false, false), var1_1, var3_3, var2_2, var0);
lbl28: // 1 sources:
        var2_2 = (String)var4_4.get("title_num");
        var7_9 = 0;
        do {
            if (var7_9 >= 13) {
                return PopupUtil.getResultMap(false, true);
            }
            if (var2_2.startsWith(new String[]{"01025", "02044", "03006", "03015", "04010", "05035", "08104", "12003", "13004", "15003", "16002", "17005", "00000"}[var7_9])) {
                var7_9 = 1;
                if (var7_9 != 0) break;
                return PopupUtil.getResultMap(false, true);
            }
            ++var7_9;
        } while (true);
lbl39: // 5 sources:
        var4_4 = PopupUtil.parseMsgToPopupWindow(var0, var1_1, var3_3, var4_4);
        if (ViewUtil.getChannelType() != 3) return var4_4;
        var2_2 = null;
        if (var6_6 == null) return PopupUtil.getResultMap(var4_4, var1_1, var3_3, var2_2, var0);
        var2_2 = new HashMap();
        var2_2.putAll(var6_6);
        return PopupUtil.getResultMap(var4_4, var1_1, var3_3, var2_2, var0);
    }

    public static Map<String, Object> parseMsgToPopupWindow(Context context, String string2, String string3, String string4, boolean bl2, Map<String, String> hashMap) {
        if (context == null) {
            throw new Exception(" Context is null.");
        }
        if (string2 == null) {
            throw new Exception(" phoneNumber is null.");
        }
        if (string4 == null) {
            throw new Exception(" smsContent is null.");
        }
        if (!cn.com.xy.sms.sdk.net.util.l.a(3)) {
            PopupUtil.getResultMap(false, false);
        }
        if (hashMap == null) {
            hashMap = new HashMap();
        }
        hashMap.put("popup_type", "1");
        new StringBuilder("\u8c03\u7528parseMsg\u5f00\u59cb=").append(System.currentTimeMillis());
        Map<String, Object> map = ParseManager.a(context, string2, string3, string4, 0, hashMap);
        new StringBuilder("\u8c03\u7528parseMsg\u7ed3\u675f=").append(System.currentTimeMillis());
        return ParseManager.parseMsgToPopupWindow(context, string2, string3, string4, map, bl2, hashMap);
    }

    public static Map<String, Object> parseMsgToRichAndSimpleBubble(Context object, String string2, String string3, String string4, String string5, long l2, byte by2, Map<String, String> map) {
        if (!cn.com.xy.sms.sdk.net.util.l.a(9)) {
            return null;
        }
        if (ParseBubbleManager.getParseStatu(map = ParseManager.a((Context)object, string3, string4, string5, l2, map)) == -1) {
            return null;
        }
        object = PopupUtil.parseMsgToBubbleCardResult((Context)object, string2, string3, string4, string5, l2, by2, map, false);
        new StringBuilder("cardResult: ").append(object);
        return object;
    }

    public static String parseSmsToClassify(Context context, String string2, String string3, String string4, String string5, Map<String, String> map) {
        if (!cn.com.xy.sms.sdk.net.util.l.a(6)) {
            return null;
        }
        return bx.a(ParseManager.a(context, string2, string3, string4, 0, map));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int parseSmsType(Context map, String string2, String string3, String string4, Map<String, String> hashMap, int n2) {
        if (hashMap == null) {
            hashMap = new HashMap();
        }
        hashMap.put("popup_type", "2");
        map = ParseManager.a(map, string2, string4, string3, 0, hashMap);
        if (map == null) return -1;
        try {
            if (ParseBubbleManager.getParseStatu(map) == -1) return -1;
            return DexUtil.getSmsTypeByMap(map, n2);
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
        }
        return -1;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void queryAllSimCardTrafficAndChargeActionData(Context context, String string2, SdkCallBack sdkCallBack) {
        try {
            Object object = IccidLocationUtil.getIccidAreaCodeMap();
            if (object == null) {
                sdkCallBack.execute(new Object[]{0, "iccidMap is null"});
                return;
            }
            object = object.entrySet().iterator();
            while (object.hasNext()) {
                String string3 = (String)((Map.Entry)object.next()).getKey();
                ParseManager.queryMenuByPhoneNum(context, string2, 1, string3, null, new o(sdkCallBack, string3));
            }
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            sdkCallBack.execute(new Object[]{0, "error:" + var0_1.getMessage()});
        }
    }

    public static String queryDefService(Context context) {
        return SysParamEntityManager.queryValueParamKey(context, "defService");
    }

    public static void queryFlightData(String object, String string2, String string3, Map<String, Object> jSONObject, SdkCallBack sdkCallBack) {
        if (StringUtils.isNull(string2) || StringUtils.isNull(string3)) {
            XyUtil.doXycallBackResult(sdkCallBack, null);
            return;
        }
        String string4 = (String)JsonUtil.getValueWithMap(jSONObject, "flight_form");
        String string5 = (String)JsonUtil.getValueWithMap(jSONObject, "flight_to");
        String string6 = (String)JsonUtil.getValueWithMap(jSONObject, "flight_from_airport");
        String string7 = (String)JsonUtil.getValueWithMap(jSONObject, "flight_to_airport");
        String string8 = (String)JsonUtil.getValueWithMap(jSONObject, "phoneNumber");
        String string9 = (String)JsonUtil.getValueWithMap(jSONObject, "titleNo");
        String string10 = (String)JsonUtil.getValueWithMap(jSONObject, "msgId");
        object = new k((String)JsonUtil.getValueWithMap(jSONObject, "bubbleJsonObj"), string8, string9, string10, (String)JsonUtil.getValueWithMap(jSONObject, "messageBody"), sdkCallBack, string4, string5, string6, string7, (String)object);
        try {
            jSONObject = new JSONObject();
            jSONObject.put("flight_num", (Object)string2.replace((CharSequence)" ", (CharSequence)""));
            jSONObject.put("flight_date", (Object)string3);
            NetWebUtil.sendPostRequest(NetWebUtil.WEB_SERVER_URL_FLIGHT, jSONObject.toString(), (XyCallBack)object);
            return;
        }
        catch (JSONException var0_1) {
            var0_1.printStackTrace();
            XyUtil.doXycallBackResult(sdkCallBack, null);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String queryMenuByPhoneNum(Context object, String string2, int n2, String string3, Map<String, String> map) {
        object = null;
        synchronized (ParseManager.class) {
            void var3_3;
            void var2_2;
            void var4_4;
            void var1_1;
            boolean bl2 = cn.com.xy.sms.sdk.net.util.l.a(4);
            if (!bl2) return object;
            if (StringUtils.isPhoneNumber((String)var1_1)) return object;
            return bx.a((String)var1_1, (int)var2_2, (String)var3_3, var4_4, null);
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static String queryMenuByPhoneNum(Context object, String string2, int n2, String string3, Map<String, String> map, SdkCallBack sdkCallBack) {
        object = null;
        // MONITORENTER : cn.com.xy.sms.util.ParseManager.class
        boolean bl2 = cn.com.xy.sms.sdk.net.util.l.a(4);
        if (!bl2) {
            // MONITOREXIT : cn.com.xy.sms.util.ParseManager.class
            return object;
        }
        object = string2 = bx.a(string2, n2, string3, map, sdkCallBack);
        try {
            b.a();
            bs.a();
            return object;
        }
        catch (Throwable var1_3) {
            LogManager.e("ParseManager", "queryPublicInfo error: " + var1_3.getMessage(), var1_3);
            return object;
        }
        catch (Throwable throwable) {
            try {
                LogManager.e("ParseManager", "queryMenuByPhoneNum error: " + throwable.getMessage(), throwable);
            }
            catch (Throwable var0_1) {
                try {
                    b.a();
                    bs.a();
                }
                catch (Throwable var1_6) {
                    LogManager.e("ParseManager", "queryPublicInfo error: " + var1_6.getMessage(), var1_6);
                    throw var0_1;
                }
                throw var0_1;
            }
            try {
                b.a();
                bs.a();
                return object;
            }
            catch (Throwable var1_5) {
                LogManager.e("ParseManager", "queryPublicInfo error: " + var1_5.getMessage(), var1_5);
                return object;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String queryPublicInfo(Context object, String string2, int n2, String string3, Map<String, String> map) {
        object = null;
        synchronized (ParseManager.class) {
            void var3_3;
            void var2_2;
            void var4_4;
            void var1_1;
            boolean bl2 = cn.com.xy.sms.sdk.net.util.l.a(5);
            if (!bl2) return object;
            if (StringUtils.isPhoneNumber((String)var1_1)) return object;
            return bx.a((String)var1_1, (int)var2_2, (String)var3_3, var4_4, null);
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static String queryPublicInfo(Context object, String string2, int n2, String string3, Map<String, String> map, SdkCallBack sdkCallBack) {
        block19 : {
            object = null;
            // MONITORENTER : cn.com.xy.sms.util.ParseManager.class
            boolean bl2 = cn.com.xy.sms.sdk.net.util.l.a(5);
            if (!bl2) {
                // MONITOREXIT : cn.com.xy.sms.util.ParseManager.class
                return object;
            }
            bl2 = StringUtils.isPhoneNumber(string2);
            if (!bl2) break block19;
            try {
                b.a();
                bs.a();
                return object;
            }
            catch (Throwable var1_3) {
                LogManager.e("ParseManager", "queryPublicInfo error: " + var1_3.getMessage(), var1_3);
                return object;
            }
        }
        object = string2 = bx.a(string2, n2, string3, map, (XyCallBack)sdkCallBack);
        try {
            b.a();
            bs.a();
            return object;
        }
        catch (Throwable var1_4) {
            LogManager.e("ParseManager", "queryPublicInfo error: " + var1_4.getMessage(), var1_4);
            return object;
        }
        catch (Throwable throwable) {
            try {
                LogManager.e("ParseManager", "queryPublicInfo error: " + throwable.getMessage(), throwable);
            }
            catch (Throwable var0_1) {
                try {
                    b.a();
                    bs.a();
                }
                catch (Throwable var1_7) {
                    LogManager.e("ParseManager", "queryPublicInfo error: " + var1_7.getMessage(), var1_7);
                    throw var0_1;
                }
                throw var0_1;
            }
            try {
                b.a();
                bs.a();
                return object;
            }
            catch (Throwable var1_6) {
                LogManager.e("ParseManager", "queryPublicInfo error: " + var1_6.getMessage(), var1_6);
                return object;
            }
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static String queryPublicInfoWithId(Context var0, String var1_4, int var2_7, String var3_8, Map<String, String> var4_9, SdkCallBack var5_10) {
        var7_11 = null;
        // MONITORENTER : cn.com.xy.sms.util.ParseManager.class
        var6_12 = cn.com.xy.sms.sdk.net.util.l.a(5);
        if (!var6_12) {
            var0 = var7_11;
            return var0;
        }
        var6_12 = StringUtils.isPhoneNumber(var1_4);
        var0 = var7_11;
        if (var6_12) ** GOTO lbl12
        ** try [egrp 2[TRYBLOCK] [2, 3 : 39->50)] { 
lbl11: // 1 sources:
        ** GOTO lbl-1000
lbl12: // 1 sources:
        // MONITOREXIT : cn.com.xy.sms.util.ParseManager.class
        return var0;
lbl-1000: // 1 sources:
        {
            var0 = bx.b(var1_4, var2_7, var3_8, var4_9, var5_10);
        }
        try {
            b.a();
            bs.a();
            return var0;
        }
        catch (Throwable var1_5) {
            LogManager.e("ParseManager", "queryPublicInfo error: " + var1_5.getMessage(), var1_5);
            return var0;
        }
lbl22: // 1 sources:
        catch (Throwable var0_1) {
            try {
                LogManager.e("ParseManager", "queryPublicInfo 2error: " + var0_1.getMessage(), var0_1);
            }
lbl25: // 2 sources:
            catch (Throwable var0_3) {
                try {
                    b.a();
                    bs.a();
                }
                catch (Throwable var1_6) {
                    LogManager.e("ParseManager", "queryPublicInfo error: " + var1_6.getMessage(), var1_6);
                    throw var0_3;
                }
                throw var0_3;
            }
            try {
                b.a();
                bs.a();
                return var7_11;
            }
            catch (Throwable var0_2) {
                LogManager.e("ParseManager", "queryPublicInfo error: " + var0_2.getMessage(), var0_2);
                return var7_11;
            }
        }
    }

    public static void queryTrainInfo(String string2, String string3, String string4, String string5, Map<String, Object> object, SdkCallBack sdkCallBack) {
        String string6 = (String)JsonUtil.getValueWithMap(object, "phoneNumber");
        String string7 = (String)JsonUtil.getValueWithMap(object, "titleNo");
        String string8 = (String)JsonUtil.getValueWithMap(object, "bubbleJsonObj");
        object = (String)JsonUtil.getValueWithMap(object, "messageBody");
        b.execute((Runnable)new m(string2, string3, string4, string5, string8, string6, string7, (String)object, sdkCallBack));
    }

    public static long setDefServiceSwitch(Context context, String string2) {
        try {
            SysParamEntityManager.insertOrUpdateKeyValue(context, "defService", string2, null);
            return 0;
        }
        catch (Throwable var0_1) {
            return -2;
        }
    }

    public static void setLogSdkDoAction(cn.com.xy.sms.sdk.util.k k2) {
        DuoquUtils.logSdkDoAction = k2;
    }

    public static void setSdkDoAction(AbsSdkDoAction absSdkDoAction) {
        DuoquUtils.sdkAction = absSdkDoAction;
    }

    public static void setSmartEnhance(boolean bl2) {
        try {
            SysParamEntityManager.insertOrUpdateKeyValue(Constant.getContext(), "smartsms_enhance", String.valueOf((boolean)bl2), null);
            SysParamEntityManager.cacheMap.put((Object)"smartsms_enhance", (Object)bl2);
            return;
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
            return;
        }
    }

    public static void setmUseNewDes(boolean bl2) {
        d = bl2;
    }

    public static void unRegisterReceiver(Context context) {
        try {
            if (e != null) {
                context.unregisterReceiver(e);
            }
            return;
        }
        catch (Throwable var0_1) {
            LogManager.e("ParseManager.unRegisterReceiver", "unRegisterReceiver error " + var0_1.getMessage(), var0_1);
            return;
        }
    }

    public static void updateData(Map<String, String> map, SdkCallBack sdkCallBack) {
        try {
            NetUtil.executeRunnable(new h(map, sdkCallBack));
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void updateMatchCacheManager(BusinessSmsMessage businessSmsMessage) {
        if (businessSmsMessage == null) {
            return;
        }
        try {
            ParseManager.updateMatchCacheManager((String)businessSmsMessage.getValue("phoneNum"), businessSmsMessage.getTitleNo(), String.valueOf((long)businessSmsMessage.getSmsId()), businessSmsMessage.bubbleJsonObj, businessSmsMessage.getMessageBody());
            return;
        }
        catch (JSONException var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void updateMatchCacheManager(String string2, String string3, String string4, JSONObject jSONObject, String string5) {
        NetUtil.executeRunnable(new l(string2, string5, jSONObject, string3, string4));
    }

    public static void updateNow() {
        cn.com.xy.sms.sdk.util.i.a(true, false);
    }
}

