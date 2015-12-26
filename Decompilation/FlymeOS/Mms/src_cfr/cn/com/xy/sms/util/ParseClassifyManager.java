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
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.util.ParseManager;
import java.util.HashMap;
import java.util.Map;

public class ParseClassifyManager {
    public static Map<String, Object> parseClassifyMsg(Context context, String object, String string2, String string3, Map<String, String> map) {
        if (context == null) {
            throw new Exception(" Context is null.");
        }
        if (object == null) {
            throw new Exception(" phoneNumber is null.");
        }
        if (string3 == null) {
            throw new Exception(" smsContent is null.");
        }
        object = ParseManager.a(context, (String)object, string2, string3, 0, map);
        context = new HashMap();
        context.put("Result", false);
        if (object != null && !StringUtils.isNull((String)(object = (String)object.get("title_num"))) && (object.startsWith("01") || object.startsWith("02") || object.startsWith("03") || object.startsWith("06") || object.startsWith("08") || object.startsWith("14"))) {
            context.put("Result", true);
        }
        return context;
    }
}

