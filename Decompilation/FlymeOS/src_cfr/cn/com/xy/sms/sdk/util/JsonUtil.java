/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  org.json.JSONArray
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.util;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtil {
    public static void JSONCombine(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject != null) {
            try {
                Iterator iterator = jSONObject2.keys();
                do {
                    if (!iterator.hasNext()) {
                        return;
                    }
                    String string2 = (String)iterator.next();
                    jSONObject.put(string2, jSONObject2.get(string2));
                } while (true);
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
            }
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String PubInfoToJson(JSONObject var0) {
        block8 : {
            if (var0 == null) {
                return "";
            }
            try {
                var0.put("id", (Object)var0.optString("pubId"));
                var0.put("name", (Object)var0.optString("pubName"));
                var0.put("classifyName", (Object)var0.optString("pubType"));
                var0.put("weiboName", (Object)var0.optString("weiBoName"));
                var0.put("weiboUrl", (Object)var0.optString("weiBoUrl"));
                var0.put("weixin", (Object)var0.optString("weiXin"));
                var0.put("logo", (Object)var0.optString("rectLogoName"));
                var0.put("logoc", (Object)var0.optString("circleLogoName"));
                var0.put("website", (Object)var0.optString("webSite"));
                var3_2 = var0.optJSONArray("pubNumInfolist");
                if (var3_2 != null && var3_2.length() > 0) {
                    var2_3 = var3_2.length();
                    var4_4 = new JSONArray();
                    var1_5 = 0;
                    break block8;
                }
                var0.put("pubnum", (Object)"");
lbl20: // 2 sources:
                do {
                    var0.remove("pubId");
                    var0.remove("pubName");
                    var0.remove("pubType");
                    var0.remove("pubTypeCode");
                    var0.remove("weiXin");
                    var0.remove("weiBoName");
                    var0.remove("weiBoUrl");
                    var0.remove("introduce");
                    var0.remove("address");
                    var0.remove("faxNum");
                    var0.remove("webSite");
                    var0.remove("versionCode");
                    var0.remove("email");
                    var0.remove("parentPubId");
                    var0.remove("slogan");
                    var0.remove("rectLogoName");
                    var0.remove("circleLogoName");
                    var0.remove("extend");
                    var0.remove("pubNumInfolist");
                    var0.remove("loadMenuTime");
                    var0.remove("updateInfoTime");
                    var0.remove("hasmenu");
                    return var0.toString();
                    break;
                } while (true);
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return "";
            }
        }
        do {
            if (var1_5 >= var2_3) {
                var0.put("pubnum", (Object)var4_4);
                ** continue;
            }
            var5_6 = (JSONObject)var3_2.get(var1_5);
            if ("2".equals((Object)var5_6.optString("type"))) {
                var6_7 = new JSONObject();
                var6_7.put("purpose", (Object)var5_6.optString("purpose"));
                var6_7.put("num", (Object)var5_6.optString("num"));
                var6_7.put("areaCode", (Object)var5_6.optString("areaCode"));
                var6_7.put("extend", (Object)var5_6.optString("extend"));
                var4_4.put((Object)var6_7);
            }
            ++var1_5;
        } while (true);
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static /* varargs */ JSONObject getJsonObject(JSONObject jSONObject, String ... arrstring) {
        if (arrstring == null) return null;
        if (jSONObject == null) {
            return null;
        }
        int n2 = arrstring.length;
        if (n2 == 0) return null;
        if (n2 % 2 != 0) {
            return null;
        }
        int n3 = 0;
        do {
            JSONObject jSONObject2 = jSONObject;
            if (n3 >= n2) return jSONObject2;
            if (arrstring[n3] != null && arrstring[n3 + 1] != null) {
                jSONObject.put(arrstring[n3], (Object)arrstring[n3 + 1]);
            }
            n3 += 2;
        } while (true);
        catch (JSONException jSONException) {
            jSONException.printStackTrace();
            return null;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static /* varargs */ JSONObject getJsonObject(String ... var0) {
        if (var0 == null) {
            return null;
        }
        var2_2 = var0.length;
        if (var2_2 == 0) return null;
        if (var2_2 % 2 != 0) return null;
        var3_3 = new JSONObject();
        var1_4 = 0;
        ** while (var1_4 < var2_2)
        {
            catch (JSONException var0_1) {
                var0_1.printStackTrace();
                return null;
            }
lbl13: // 1 sources:
            if (var0[var1_4] != null && var0[var1_4 + 1] != null) {
                var3_3.put(var0[var1_4], (Object)var0[var1_4 + 1]);
            }
            var1_4 += 2;
            continue;
        }
lbl18: // 1 sources:
        return var3_3;
    }

    public static Object getValFromJsonObject(JSONObject object, String string2) {
        if (string2 != null && object != null) {
            try {
                if (object.has(string2)) {
                    object = object.get(string2);
                    return object;
                }
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
            }
        }
        return "";
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getValueFromJson(JSONObject object, String string2, String string3) {
        if (object == null) return string3;
        try {
            object = object.optString(string2);
            boolean bl2 = StringUtils.isNull((String)object);
            if (!bl2) return object;
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
            return string3;
        }
        return string3;
    }

    public static Object getValueFromJsonObject(JSONObject object, String string2) {
        if (string2 != null && object != null) {
            try {
                if (object.has(string2)) {
                    object = object.get(string2);
                    return object;
                }
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
            }
        }
        return null;
    }

    public static Object getValueWithMap(Map<String, Object> object, String string2) {
        if (object != null) {
            try {
                if (!object.isEmpty() && !StringUtils.isNull(string2) && object.containsKey(string2)) {
                    object = object.get(string2);
                    return object;
                }
            }
            catch (Throwable var0_1) {
                // empty catch block
            }
        }
        return "";
    }

    public static JSONArray parseStrToJsonArray(String string2) {
        if (!StringUtils.isNull(string2)) {
            try {
                string2 = new JSONArray(string2);
                return string2;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
            }
        }
        return null;
    }

    public static JSONObject parseStrToJsonObject(String string2) {
        if (!StringUtils.isNull(string2)) {
            try {
                string2 = new JSONObject(string2.trim());
                return string2;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
            }
        }
        return null;
    }

    public static void putJsonToConV(ContentValues contentValues, JSONObject object, String string2, String string3) {
        if (!StringUtils.isNull((String)(object = object.optString(string3)))) {
            contentValues.put(string2, (String)object);
            return;
        }
        contentValues.remove(string2);
    }

    public static void putJsonToMap(JSONObject jSONObject, Map<String, String> map) {
        if (jSONObject != null && map != null) {
            try {
                Iterator iterator = jSONObject.keys();
                do {
                    if (!iterator.hasNext()) {
                        return;
                    }
                    String string2 = (String)iterator.next();
                    map.put(string2, jSONObject.getString(string2));
                } while (true);
            }
            catch (Throwable var0_1) {
                // empty catch block
            }
        }
    }
}

