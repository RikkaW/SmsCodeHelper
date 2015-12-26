/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  org.json.JSONObject
 */
package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.sdk.util.PopupUtil;
import cn.com.xy.sms.util.ParseManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ParseYCardManager {
    private static String a = "yunyingshang_s_0001";
    private static String b = "1003";
    private static String c = "xiaoyuan";

    public static Map<String, Object> parseMsgForCard(Context object, String object2, String string2, String string3, Map<String, String> map) {
        if (object == null) {
            throw new Exception(" Context is null.");
        }
        if (object2 == null) {
            throw new Exception(" phoneNumber is null.");
        }
        if (string3 == null) {
            throw new Exception(" smsContent is null.");
        }
        if (!l.a(8)) {
            PopupUtil.getResultMap(false, false);
        }
        if ((object = ParseManager.a((Context)object, (String)object2, string2, string3, 0, map)) != null) {
            object.put("parseMsgForCard", "true");
            object = object2 = DexUtil.handerValueMap(object);
            if (object2 == null) {
                object = new HashMap();
                object.put("Result", false);
            }
            return object;
        }
        object = new HashMap();
        object.put("Result", false);
        return object;
    }

    public static String parseMsgForCardData(Context map, String object, String string2, String string3, Map<String, String> map2) {
        if (map == null) {
            throw new Exception(" Context is null.");
        }
        if (object == null) {
            throw new Exception(" phoneNumber is null.");
        }
        if (string3 == null) {
            throw new Exception(" smsContent is null.");
        }
        if (!l.a(8)) {
            PopupUtil.getResultMap(false, false);
        }
        if ((object = ParseManager.a((Context)map, (String)object, string2, string3, 0, map2)) != null) {
            map = DexUtil.handerValueMap(object);
            if (map == null) {
                return null;
            }
            new StringBuilder("res=").append(object.toString());
            new StringBuilder("map=").append(map.toString());
            if (((JSONObject)map.get("content")).toString() != null) {
                new StringBuilder("content=").append(((JSONObject)map.get("content")).toString());
                object = ((JSONObject)map.get("theReturn")).put("content", map.get("content"));
                object.put("title", map.get("title"));
                object.put("card_tag", (Object)"xiaoyuan");
                return object.toString();
            }
        }
        return null;
    }
}

