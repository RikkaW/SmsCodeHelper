/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.util.ParseBubbleManager;
import cn.com.xy.sms.util.ParseManager;
import cn.com.xy.sms.util.ParseSmsToBubbleUtil;
import java.util.HashMap;
import java.util.Map;

public class ParseNotificationManager {
    private static Map<String, Object> a(Context context, String string2, String string3, String string4, String string5, long l2, HashMap<String, String> hashMap) {
        if (context == null) {
            throw new Exception(" Context is null.");
        }
        if (string3 == null) {
            throw new Exception(" phoneNumber is null.");
        }
        if (string5 == null) {
            throw new Exception(" smsContent is null.");
        }
        return ParseSmsToBubbleUtil.parseSmsToBubbleResultMap(string2, string3, string5, string4, l2, 4, true, true, hashMap);
    }

    public static Map<String, Object> parseNotificationMsg(Context hashMap, long l2, String string2, String string3, String string4, Map<String, String> map) {
        if (hashMap == null) {
            throw new Exception(" Context is null.");
        }
        if (string2 == null) {
            throw new Exception(" phoneNumber is null.");
        }
        if (string4 == null) {
            throw new Exception(" smsContent is null.");
        }
        if ((hashMap = ParseManager.a((Context)hashMap, string2, string3, string4, 0, map)) != null && ParseBubbleManager.getParseStatu(hashMap) != -1) {
            if ((hashMap = DexUtil.handerNotificationValueMap(hashMap)) == null) {
                hashMap = new HashMap();
                hashMap.put("Result", false);
                return hashMap;
            }
            hashMap.put("Result", true);
            return hashMap;
        }
        hashMap = new HashMap();
        hashMap.put((String)"Result", false);
        return hashMap;
    }

    public static Map<String, Object> parseNotificationMsg(Context object, String string2, String string3, String string4, String string5, long l2, HashMap<String, String> hashMap) {
        if ((object = ParseNotificationManager.a((Context)object, string2, string3, string4, string5, l2, hashMap)) != null) {
            ParseSmsToBubbleUtil.backGroundHandleMapByType(hashMap, object);
            return DexUtil.handerNotificationValueMap(object);
        }
        return null;
    }

    public static Map<String, Object> parseNotificationMsgAndPopupWindow(Context context, String object, String string2, String string3, String string4, long l2, HashMap<String, String> hashMap) {
        if ((object = ParseNotificationManager.a(context, (String)object, string2, string3, string4, l2, hashMap)) != null) {
            ParseManager.parseMsgToPopupWindow(context, string2, string3, string4, object, false, hashMap);
            return DexUtil.handerNotificationValueMap(object);
        }
        return null;
    }
}

