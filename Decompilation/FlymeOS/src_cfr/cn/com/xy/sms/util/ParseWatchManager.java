/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  org.json.JSONObject
 */
package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.util.ParseBubbleManager;
import cn.com.xy.sms.util.ParseManager;
import java.util.Map;
import org.json.JSONObject;

public class ParseWatchManager {
    public static JSONObject parseMsgForWatch(Context object, String string2, String object2, String string3, Map<String, String> map) {
        if (object == null) {
            throw new Exception(" Context is null.");
        }
        if (string2 == null) {
            throw new Exception(" phoneNumber is null.");
        }
        if (string3 == null) {
            throw new Exception(" smsContent is null.");
        }
        object2 = ParseManager.a((Context)object, string2, (String)object2, string3, 0, map);
        object = string2 = null;
        if (object2 != null) {
            object = string2;
            if (ParseBubbleManager.getParseStatu(object2) != -1) {
                object = DexUtil.handerWatchValueMap(object2);
            }
        }
        return object;
    }
}

