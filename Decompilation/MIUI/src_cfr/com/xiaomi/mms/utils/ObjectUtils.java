/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.Map$Entry
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.xiaomi.mms.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ObjectUtils {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static Object convertObj(Object arrayList) {
        if (arrayList instanceof JSONObject) {
            return ObjectUtils.jsonToMap((JSONObject)arrayList);
        }
        if (arrayList instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray)arrayList;
            int n = jSONArray.length();
            ArrayList arrayList2 = new ArrayList();
            int n2 = 0;
            do {
                arrayList = arrayList2;
                if (n2 >= n) return arrayList;
                arrayList2.add(ObjectUtils.convertObj(jSONArray.opt(n2)));
                ++n2;
            } while (true);
        }
        if (arrayList != JSONObject.NULL) return arrayList;
        return null;
    }

    public static String joinMap(Map<?, ?> object, String string2) {
        if (object == null) {
            return null;
        }
        Object object2 = object.entrySet();
        object = new StringBuilder();
        boolean bl = true;
        object2 = object2.iterator();
        while (object2.hasNext()) {
            Map.Entry entry = (Map.Entry)object2.next();
            if (!bl) {
                object.append(string2);
            }
            object.append(entry.getKey());
            object.append('=');
            object.append(entry.getValue());
            bl = false;
        }
        return object.toString();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Map<String, Object> jsonToMap(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator iterator = jSONObject.keys();
        do {
            Object object = hashMap;
            if (!iterator.hasNext()) return object;
            object = (String)iterator.next();
            hashMap.put(object, ObjectUtils.convertObj(jSONObject.opt((String)object)));
        } while (true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Map<String, Object> jsonToMap(byte[] map, String string2) {
        try {
            return ObjectUtils.jsonToMap(new JSONObject(new String(map, string2)));
        }
        catch (UnsupportedEncodingException var0_1) {
            var0_1.printStackTrace();
            do {
                return null;
                break;
            } while (true);
        }
        catch (JSONException var0_2) {
            var0_2.printStackTrace();
            return null;
        }
    }
}

