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
import java.util.HashMap;
import java.util.Map;

public class ParsePayManager {
    public static Map<String, Object> parseMsgForPay(Context hashMap, String string2, String string3, String string4, Map<String, String> map) {
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
            if ((hashMap = DexUtil.handerPayValueMap(hashMap)) == null) {
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
}

