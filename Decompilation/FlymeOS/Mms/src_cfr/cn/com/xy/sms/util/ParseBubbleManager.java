/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.HashSet
 *  java.util.concurrent.Executors
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.sdk.queue.BubbleTaskQueue;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.v;
import cn.com.xy.sms.util.ParseManager;
import cn.com.xy.sms.util.ParseRichBubbleManager;
import cn.com.xy.sms.util.ParseSmsToBubbleUtil;
import cn.com.xy.sms.util.SdkCallBack;
import cn.com.xy.sms.util.a;
import cn.com.xy.sms.util.b;
import cn.com.xy.sms.util.c;
import cn.com.xy.sms.util.d;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONObject;

public class ParseBubbleManager {
    private static ExecutorService a = Executors.newFixedThreadPool((int)1);
    private static String b = null;
    private static ExecutorService c = Executors.newFixedThreadPool((int)1);

    static /* synthetic */ JSONArray a(String string2, String string3, String string4, String string5, long l2) {
        return ParseBubbleManager.b(string2, string3, string4, string5, l2);
    }

    private static void a(String string2, String string3, String string4, long l2, String string5) {
        c.execute((Runnable)new c(string3, string4, string2, string5, l2));
    }

    private static void a(boolean bl2, String string2) {
        v.c(string2);
        if (bl2) {
            ParseSmsToBubbleUtil.beforeHandParseReceiveSms(200, 3);
        }
        b = null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void addEffectiveBubbleData(String object, String string2, JSONArray hashSet) {
        Map<String, JSONObject> map;
        void var1_1;
        if (var1_1 == null || map == null || (object = v.a((String)object)) == null) {
            return;
        }
        Map<String, JSONArray> map2 = object.c;
        synchronized (map2) {
            object.c.put((String)var1_1, (JSONArray)((void)map));
        }
        map = object.a;
        synchronized (map) {
            object.a.remove(var1_1);
        }
        map = object.d;
        synchronized (map) {
            object.d.remove((Object)var1_1);
        }
        map = object.e;
        synchronized (map) {
            object.e.remove((Object)var1_1);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void addInvalidBubbleData(String object, String string2) {
        void var1_1;
        if (var1_1 == null || (object = v.a((String)object)) == null) {
            return;
        }
        Object object2 = object.d;
        synchronized (object2) {
            object.d.add((Object)var1_1);
        }
        object2 = object.a;
        synchronized (object2) {
            object.a.remove(var1_1);
        }
        object2 = object.e;
        synchronized (object2) {
            object.e.remove((Object)var1_1);
        }
        object2 = object.c;
        synchronized (object2) {
            object.c.remove(var1_1);
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static JSONArray b(String string2, String string3, String string4, String string5, long l2) {
        string2 = ParseBubbleManager.parseMsgToSimpleBubbleResultKuai(Constant.getContext(), string2, string3, string4, string5, l2, 1, null);
        if (string2 == null) return null;
        try {
            return new JSONArray(string2);
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
        }
        return null;
    }

    public static void clearAllCache(String string2) {
        ParseBubbleManager.a(true, string2);
    }

    public static void clearCacheBubbleData(String string2) {
        ParseBubbleManager.a(false, string2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int deleteBubbleData(String string2) {
        v v2;
        try {
            v2 = v.a(string2);
            if (v2 == null) return MatchCacheManager.deleteDataByPhoneNum(string2);
        }
        catch (Throwable var0_1) {
            return -1;
        }
        v2.b();
        return MatchCacheManager.deleteDataByPhoneNum(string2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void deleteBubbleData(Set<Integer> set) {
        if (set == null) return;
        try {
            if (set.isEmpty()) return;
            IccidLocationUtil.iccidPool.execute((Runnable)new d(set));
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static boolean deleteBubbleData(String string2, String string3, String string4) {
        String string5 = null;
        if (string3 != null) {
            string5 = MatchCacheManager.getMD5(string3, string4);
        }
        ParseBubbleManager.deleteBubbleDataFromCache(string3, string2);
        ParseRichBubbleManager.deleteBubbleDataFromCache(string3, string2);
        return MatchCacheManager.deleteBubbleData(string2, string5);
    }

    public static void deleteBubbleDataFromCache(String object, String string2) {
        block3 : {
            try {
                object = v.a((String)object);
                if (object != null) break block3;
                return;
            }
            catch (Throwable var0_1) {
                return;
            }
        }
        object.a.remove(string2);
        object.c.remove(string2);
        object.d.remove((Object)string2);
        object.e.remove((Object)string2);
    }

    public static boolean equalPhoneNum(String string2) {
        if (string2 == null || b == null) {
            return false;
        }
        string2 = StringUtils.getPhoneNumberNo86(string2);
        return b.equals((Object)string2);
    }

    public static int getParseStatu(Map<String, Object> n2) {
        if (n2 != null && (n2 = (Integer)n2.get("parseStatu")) != null && n2 == -1) {
            return -1;
        }
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void loadBubbleDataByPhoneNum(String string2, boolean bl2) {
        void var1_1;
        a.a();
        string2 = StringUtils.getPhoneNumberNo86(string2);
        String[] arrstring = new String[]{string2};
        v v2 = v.b(string2);
        Map<String, JSONArray> map = v2.c;
        synchronized (map) {
            ParseBubbleManager.clearCacheBubbleData(string2);
            b = string2;
        }
        Map<String, JSONObject> map2 = MatchCacheManager.loadDataByParam("phoneNum=?", arrstring, "save_time desc", "15");
        if (map2 != null) {
            map = v2.a;
            synchronized (map) {
                v2.a.putAll(map2);
            }
        }
        if (var1_1 != false) {
            ParseRichBubbleManager.pubBubbleData(string2, map2, true);
        }
        ParseBubbleManager.loadBubbleDataByPhoneNumSecond("phoneNum=?", arrstring, (boolean)var1_1, v2, string2);
        ParseSmsToBubbleUtil.beforeHandParseReceiveSms(string2, 50, 3, true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void loadBubbleDataByPhoneNumSecond(String map, String[] map2, boolean bl2, v v2, String string2) {
        void var2_2;
        try {
            Thread.sleep((long)10);
        }
        catch (InterruptedException var5_5) {
            var5_5.printStackTrace();
        }
        if ((map2 = MatchCacheManager.loadDataByParam(map, map2, "save_time desc", "500")) != null) {
            map = var3_3.a;
            synchronized (map) {
                var3_3.a.putAll(map2);
            }
        }
        if (var2_2 != false) {
            void var4_4;
            ParseRichBubbleManager.pubBubbleData((String)var4_4, map2, false);
        }
    }

    public static String parseMsgToSimpleBubbleResultKuai(Context object, String string2, String string3, String string4, String string5, long l2, byte by2, Map<String, String> map) {
        if (!l.a(11)) {
            return null;
        }
        object = ParseManager.parseMsgToBubble((Context)object, string3, string4, string5, map);
        c.execute((Runnable)new c(string3, string5, string2, (String)object, l2));
        return object;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static JSONObject queryDataByMsgItem(String var0, String var1_1, String var2_2, String var3_3, int var4_4, long var5_5) {
        block9 : {
            if (var0 == null) throw new Exception("msgid or phoneNum or smsContent is null but they need value.");
            if (var1_1 == null) throw new Exception("msgid or phoneNum or smsContent is null but they need value.");
            if (var2_2 == null) {
                throw new Exception("msgid or phoneNum or smsContent is null but they need value.");
            }
            if (StringUtils.isPhoneNumber(var1_1)) {
                return null;
            }
            var9_8 = null;
            var10_9 = v.b(var1_1);
            if (var10_9.b != null) {
                var8_6 = var9_8 = var10_9.b.get(var0);
                if (var9_8 != null) return var8_6;
            }
            if (var9_8 == null && var10_9.d.contains((Object)var0)) {
                return null;
            }
            var8_6 = var9_8;
            if (var9_8 != null) ** GOTO lbl47
            var8_6 = var9_8;
            if (var10_9.a == null) ** GOTO lbl47
            var8_6 = var9_8 = var10_9.a.get(var0);
            if (var9_8 == null) ** GOTO lbl47
            var8_6 = var9_8;
            if (var9_8.has("need_parse_simple")) ** GOTO lbl47
            var8_6 = (String)JsonUtil.getValueFromJsonObject(var9_8, "msg_num_md5");
            var11_10 = MatchCacheManager.getMD5(var1_1, var2_2);
            if (var11_10 == null || var8_6 == null || !var11_10.equals(var8_6)) ** GOTO lbl41
            if (var4_4 != 1) ** GOTO lbl43
            var8_6 = (String)JsonUtil.getValueFromJsonObject(var9_8, "session_reuslt");
            try {
                if (!StringUtils.isNull((String)var8_6)) {
                    var9_8.put("session_reuslt", (Object)new JSONArray((String)var8_6));
                    var10_9.b.put(var0, var9_8);
                    var7_11 = 1;
                    break block9;
                }
                var10_9.d.add((Object)var0);
                var7_11 = 2;
            }
            catch (Throwable var8_7) {
                var8_7.printStackTrace();
                var10_9.d.add((Object)var0);
                var7_11 = 2;
            }
            ** GOTO lbl44
lbl41: // 1 sources:
            var7_11 = 3;
            ** GOTO lbl44
lbl43: // 1 sources:
            var7_11 = 0;
        }
        var10_9.a.remove(var0);
        ** GOTO lbl49
lbl47: // 4 sources:
        var7_11 = 0;
        var9_8 = var8_6;
lbl49: // 2 sources:
        if (var7_11 == true) return var9_8;
        if (var10_9.e.contains((Object)var0)) {
            return null;
        }
        var10_9.e.add((Object)var0);
        BubbleTaskQueue.addDataToQueue(var7_11, var0, var1_1, var2_2, var3_3, var5_5, var4_4, var9_8);
        return var9_8;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void queryDataByMsgItem(String var0, String var1_1, String var2_2, String var3_3, int var4_4, long var5_5, SdkCallBack var7_6, boolean var8_7) {
        block10 : {
            block11 : {
                if (var0 == null || var1_1 == null || StringUtils.isNull(var2_2)) {
                    XyUtil.doXycallBackResult(var7_6, new Object[]{-1, "phonenum is null", var0});
                    return;
                }
                if (StringUtils.isPhoneNumber(var1_1)) {
                    XyUtil.doXycallBackResult(var7_6, new Object[]{-1, "phonenum is null", var0});
                    return;
                }
                var10_8 = null;
                var12_10 = v.b(var1_1);
                if (var12_10.c != null) {
                    var10_8 = var11_11 = var12_10.c.get(var0);
                    if (var11_11 != null) {
                        XyUtil.doXycallBackResult(var7_6, new Object[]{0, var11_11, var0});
                        return;
                    }
                }
                if (var10_8 == null && var12_10.d.contains((Object)var0)) {
                    XyUtil.doXycallBackResult(var7_6, new Object[]{-1, " invalidBubbleData ", var0});
                    return;
                }
                if (var10_8 != null || var12_10.a == null || (var10_8 = var12_10.a.get(var0)) == null || var10_8.has("need_parse_simple")) ** GOTO lbl38
                var11_11 = (String)JsonUtil.getValueFromJsonObject((JSONObject)var10_8, "msg_num_md5");
                var13_12 = MatchCacheManager.getMD5(var1_1, var2_2);
                if (var13_12 == null || var11_11 == null || !var13_12.equals(var11_11)) ** GOTO lbl35
                try {
                    var10_8 = (JSONArray)JsonUtil.getValueFromJsonObject((JSONObject)var10_8, "session_reuslt");
                    if (var10_8 != null) {
                        var12_10.c.put(var0, (JSONArray)var10_8);
                        XyUtil.doXycallBackResult(var7_6, new Object[]{0, var10_8, var0});
                        return;
                    }
                    var12_10.d.add((Object)var0);
                    var9_13 = 2;
                    ** GOTO lbl36
                }
                catch (Throwable var10_9) {
                    var12_10.d.add((Object)var0);
                    var9_13 = 2;
                    var10_9.printStackTrace();
                    ** GOTO lbl36
lbl35: // 1 sources:
                    var9_13 = 3;
lbl36: // 3 sources:
                    var12_10.a.remove(var0);
                    ** GOTO lbl39
lbl38: // 1 sources:
                    var9_13 = 0;
lbl39: // 2 sources:
                    if (var9_13 != 0 && var9_13 != 3) break block10;
                    if (!var12_10.e.contains((Object)var0)) break block11;
                    XyUtil.doXycallBackResult(var7_6, new Object[]{-1, " inQueueBubbleData2 ", var0});
                    return;
                }
            }
            if (!var8_7) {
                XyUtil.doXycallBackResult(var7_6, new Object[]{-2, " need parse", var0});
                ParseBubbleManager.a.execute((Runnable)new b(var12_10, var0, var1_1, var2_2, var5_5, var7_6, var3_3, var4_4));
                return;
            }
            XyUtil.doXycallBackResult(var7_6, new Object[]{-4, " is scrolling", var0});
            return;
        }
        XyUtil.doXycallBackResult(var7_6, new Object[]{-1, " invalidBubbleData ", var0});
    }
}

