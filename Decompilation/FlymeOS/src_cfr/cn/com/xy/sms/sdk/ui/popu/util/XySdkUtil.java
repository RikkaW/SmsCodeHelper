/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.content.Context
 *  android.os.Message
 *  android.telephony.SmsMessage
 *  android.telephony.TelephonyManager
 *  android.util.LruCache
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.ui.popu.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Message;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.LruCache;
import cn.com.xy.sms.sdk.action.AbsSdkDoAction;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.queue.BubbleTaskQueue;
import cn.com.xy.sms.sdk.ui.popu.util.XySdkUtil$1;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.util.ParseBubbleManager;
import cn.com.xy.sms.util.ParseManager;
import cn.com.xy.sms.util.ParseNotificationManager;
import cn.com.xy.sms.util.ParseRichBubbleManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@SuppressLint(value={"NewApi"})
public class XySdkUtil {
    public static final int DUOQU_BUBBLE_DATA_CACHE_SIZE = 200;
    private static final int PARSE_MSG_TYPE_SIMPLE_AND_RICH = 3;
    public static final String SMARTSMS_BUBBLE = "smartsms_bubble";
    public static final String SMARTSMS_ENHANCE = "smartsms_enhance";
    public static final String SMARTSMS_HAS_SHOW_FIRST = "smartsms_has_show_first";
    public static final String SMARTSMS_NO_SHOW_AGAIN = "smartsms_no_show_again";
    public static final int SMARTSMS_PARSE_TYPE_NOTIFY = 1;
    public static final int SMARTSMS_PARSE_TYPE_ONLY_BUBBLE = 0;
    public static final int SMARTSMS_PARSE_TYPE_POUPP = 2;
    public static final String SMARTSMS_SWITCH = "smartsms_switch";
    public static final String SMARTSMS_UPDATE_TYPE = "smartsms_update_type";
    public static final String TAG = "XIAOYUAN";
    private static final LruCache<Long, JSONObject> mBubbleDataCache = new LruCache(200);
    private static LruCache<String, Map<String, Object>> notifyDataCacheMap = new LruCache(10);

    public static void clearCache(int n2, String string2) {
        if (mBubbleDataCache != null) {
            mBubbleDataCache.evictAll();
        }
        ParseBubbleManager.clearAllCache(string2);
        ParseRichBubbleManager.clearCacheBubbleData(string2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String formatPhoneNum(String string2) {
        if (string2 == null) {
            return null;
        }
        String string3 = string2.replaceAll(" ", "").replaceAll("-", "");
        if (string3.startsWith("86")) {
            return string3.substring(2, string3.length());
        }
        if (string3.startsWith("+86")) {
            return string3.substring(3, string3.length());
        }
        string2 = string3;
        if (!string3.startsWith("0086")) return string2;
        return string3.substring(4, string3.length());
    }

    public static JSONObject getBubbleDataFromCache(Long l2) {
        if (l2 == null) {
            return null;
        }
        return (JSONObject)mBubbleDataCache.get((Object)l2);
    }

    public static HashMap<String, String> getExtendMap(int n2, String string2, long l2) {
        HashMap hashMap = new HashMap();
        hashMap.put((Object)"simIndex", (Object)String.valueOf((int)n2));
        hashMap.put((Object)"simName", (Object)XySdkUtil.getSimNameBySimIndex(n2));
        hashMap.put((Object)"msgId", (Object)string2);
        hashMap.put((Object)"opensms_enable", (Object)"true");
        hashMap.put((Object)"msgTime", (Object)String.valueOf((long)l2));
        hashMap.put((Object)"handle_type", (Object)"1");
        return hashMap;
    }

    public static String getICCID(Context object) {
        try {
            object = (TelephonyManager)object.getSystemService("phone");
            if (!StringUtils.isNull(object.getSimSerialNumber())) {
                object = object.getSimSerialNumber();
                return object;
            }
        }
        catch (Exception var0_1) {
            // empty catch block
        }
        return "";
    }

    public static Map<String, Object> getNotifyDataCacheByMsgId(long l2, boolean bl2) {
        String string2 = String.valueOf((long)l2);
        Map map = (Map)notifyDataCacheMap.get((Object)string2);
        if (map != null && bl2) {
            notifyDataCacheMap.remove((Object)string2);
        }
        return map;
    }

    public static String getSimNameBySimIndex(int n2) {
        return "";
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void init(Context var0, String var1_2, String var2_3, AbsSdkDoAction var3_4, String var4_5, HashMap<String, String> var5_6) {
        if (var5_6 != null) ** GOTO lbl4
        try {
            var5_6 = new HashMap();
lbl4: // 2 sources:
            var5_6.put((Object)"SECRETKEY", (Object)var2_3);
            ParseManager.initSdk((Context)var0, var1_2, var4_5, true, true, var5_6);
            ParseManager.setSdkDoAction(var3_4);
            var0 = new XySdkUtil$1();
            var0.sendMessageDelayed(var0.obtainMessage(), 6000);
            return;
        }
        catch (Exception var0_1) {
            return;
        }
    }

    public static void init(Context context, String string2, String string3, AbsSdkDoAction absSdkDoAction, HashMap<String, String> hashMap) {
        XySdkUtil.init(context, string2, string3, absSdkDoAction, XySdkUtil.getICCID(context), hashMap);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void parseMsg(String var0, String var1_2, String var2_3, String var3_4, long var4_5, int var6_6, int var7_7) {
        if (var6_6 != 0) ** GOTO lbl5
        try {
            BubbleTaskQueue.addDataToQueue(0, var0, var2_3, var1_2, var3_4, var4_5, 3, null);
            return;
lbl5: // 1 sources:
            if (var6_6 == 1) {
                var1_2 = ParseNotificationManager.parseNotificationMsg(Constant.getContext(), var0, var2_3, var3_4, var1_2, var4_5, null);
                if (var1_2 == null) return;
                if (var1_2.size() <= 1) return;
                XySdkUtil.notifyDataCacheMap.put((Object)var0, var1_2);
                return;
            }
            if (var6_6 != 2) return;
            var8_8 = new HashMap();
            var8_8.put((Object)"simIndex", (Object)String.valueOf((int)var7_7));
            var8_8.put((Object)"simName", (Object)XySdkUtil.getSimNameBySimIndex(var7_7));
            var8_8.put((Object)"msgId", (Object)String.valueOf((Object)var0));
            var8_8.put((Object)"opensms_enable", (Object)"true");
            ParseManager.parseMsgToPopupWindow(Constant.getContext(), var2_3, var3_4, var1_2, var8_8);
            BubbleTaskQueue.addDataToQueue(0, var0, var2_3, var1_2, var3_4, var4_5, 3, null);
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Exception decompiling
     */
    public static void parseMsg(String var0, SmsMessage[] var1_2, int var2_3, int var3_4) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 1[TRYBLOCK]
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
    public static void putBubbleDataToCache(Long l2, JSONObject jSONObject) {
        if (l2 != null && jSONObject != null) {
            LruCache<Long, JSONObject> lruCache = mBubbleDataCache;
            synchronized (lruCache) {
                mBubbleDataCache.put((Object)l2, (Object)jSONObject);
                return;
            }
        }
    }

    private static String replaceFormFeeds(String string2) {
        String string3 = "";
        if (string2 != null) {
            string3 = string2.replace('\f', '\n');
        }
        return string3;
    }
}

