/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 *  java.text.SimpleDateFormat
 *  java.util.Date
 *  java.util.HashMap
 */
package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.util.ParseManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ParseDateManager {
    public static Map<String, Object> parseDateMsg(Context object, String hashMap, String string2, String string3, Map<String, String> map) {
        if (object == null) {
            throw new Exception(" Context is null.");
        }
        if (hashMap == null) {
            throw new Exception(" phoneNumber is null.");
        }
        if (string3 == null) {
            throw new Exception(" smsContent is null.");
        }
        hashMap = ParseManager.a((Context)object, (String)hashMap, string2, string3, 0, map);
        object = new Date();
        object = new SimpleDateFormat("yyyy-MM-dd").format((Date)object);
        if (hashMap != null) {
            if ((hashMap = DexUtil.handerDateValueMap(hashMap)) == null || hashMap.isEmpty()) {
                hashMap = new HashMap();
                hashMap.put("Dojuage", false);
                hashMap.put("Result", false);
                hashMap.put("date", String.valueOf((Object)object) + " 09:00");
                return hashMap;
            }
            hashMap.put("Result", true);
            return hashMap;
        }
        hashMap = new HashMap();
        hashMap.put("Dojuage", false);
        hashMap.put("Result", false);
        hashMap.put("date", String.valueOf((Object)object) + " 09:00");
        return hashMap;
    }
}

