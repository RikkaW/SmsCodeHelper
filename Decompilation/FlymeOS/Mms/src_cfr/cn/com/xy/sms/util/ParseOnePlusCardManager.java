/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Boolean
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  org.json.JSONObject
 */
package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.log.PrintTestLogUtil;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import cn.com.xy.sms.util.ParseBubbleManager;
import cn.com.xy.sms.util.ParseManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ParseOnePlusCardManager {
    /*
     * Enabled aggressive block sorting
     */
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
        if (l.a(8) && (object = ParseManager.a((Context)object, (String)object2, string2, string3, 0, map)) != null && ParseBubbleManager.getParseStatu(object) != -1) {
            object.put("msgTime", map.get("msgTime"));
            object = DexUtil.handerValueMap(object);
            if (object == null) {
                object2 = new HashMap();
                object2.put("Result", false);
                return object2;
            }
            object2 = (JSONObject)object.get("card_content");
            if (object2 != null) {
                string2 = (Integer)object2.get("card_type");
                string3 = new JSONObject();
                string3.put("msgid", (Object)map.get("msgid"));
                string3.put("phone", (Object)map.get("phone"));
                string3.put("content", (Object)map.get("content"));
                string3.put("msgTime", (Object)map.get("msgTime"));
                string3.put("smsdate", (Object)map.get("smsdate"));
                object2.put("msgid", (Object)map.get("msgid"));
                object2.put("smsdate", (Object)map.get("smsdate"));
                string3.put("result", (Object)object2.toString());
                PrintTestLogUtil.printTestJsonLog("ParseOnePlusCardManager", (JSONObject)string3, "resultJson=");
                long l2 = DuoquUtils.getSdkDoAction().createCard((JSONObject)string3, string2.intValue(), map);
                boolean bl2 = l2 > 0;
                object.put("Result", bl2);
                object.put("id", String.valueOf((long)l2));
                return object;
            }
        } else {
            object = null;
        }
        object2 = object;
        if (object != null) return object2;
        {
            object = new HashMap();
            object.put("Result", false);
            return object;
        }
    }
}

