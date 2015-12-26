/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Map$Entry
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.log;

import cn.com.xy.sms.sdk.log.LogManager;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class PrintTestLogUtil {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void printTestJsonLog(String object, JSONObject jSONObject, String string2) {
        if (!LogManager.debug) return;
        if (jSONObject != null) {
            object = jSONObject.keys();
            do {
                if (!object.hasNext()) {
                    return;
                }
                Object e2 = object.next();
                try {
                    new StringBuilder("desc:").append(string2).append("   key =").append(e2).append(" value =").append(jSONObject.get(e2.toString()));
                }
                catch (JSONException var3_4) {
                    var3_4.printStackTrace();
                }
            } while (true);
        }
        new StringBuilder("desc:").append(string2).append("valueMap is null");
    }

    public static void printTestLog(String string2, String string3) {
    }

    public static void printTestLog(String string2, String string3, String string4) {
        if (LogManager.debug) {
            new StringBuilder("desc:").append(string4).append(" log: ").append(string3);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void printTestLog(String iterator, Map<String, Object> entry, String string2) {
        if (!LogManager.debug) return;
        if (entry != null && !entry.isEmpty()) {
            iterator = entry.entrySet().iterator();
            do {
                if (!iterator.hasNext()) {
                    return;
                }
                entry = iterator.next();
                new StringBuilder("desc:").append(string2).append("   key =").append((String)entry.getKey()).append(" value =").append(entry.getValue());
            } while (true);
        }
        new StringBuilder("desc:").append(string2).append("valueMap is null");
    }
}

