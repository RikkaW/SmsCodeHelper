/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.Cursor
 *  android.net.Uri
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package cn.com.xy.sms.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.net.NetWebUtil;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.sdk.util.PopupUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.util.ParseBubbleManager;
import cn.com.xy.sms.util.ParseManager;
import cn.com.xy.sms.util.SdkCallBack;
import cn.com.xy.sms.util.e;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseCardManager {
    public static String SMS_DATE_TIME = "sms_date_time";
    private static String a = "yunyingshang_s_0001";
    private static String b = "1003";
    private static String c = "xiaoyuan";

    private static String a(String string2) {
        Object var1_1 = null;
        Uri uri = Uri.parse((String)"content://com.yunos.lifecard/cards");
        uri = Constant.getContext().getContentResolver().query(uri, null, " arg1 like ? ", new String[]{"%" + string2 + "%"}, null);
        string2 = var1_1;
        if (uri.moveToNext()) {
            new StringBuilder("cc card_id=").append(uri.getString(uri.getColumnIndex("card_id")));
            string2 = uri.getString(uri.getColumnIndex("card_id"));
            uri.close();
        }
        return string2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static Map<String, Object> a(Map<String, Object> hashMap, String string2) {
        Uri uri = Uri.parse((String)"content://com.yunos.lifecard/cards");
        if (hashMap == null) {
            hashMap = new HashMap();
            hashMap.put("Result", false);
            return hashMap;
        }
        String string3 = ((JSONObject)hashMap.get("content")).toString();
        if (string3 != null && !string3.equals((Object)"")) {
            string3 = Constant.getContext().getContentResolver();
            ContentValues contentValues = (ContentValues)hashMap.get("contentValue");
            if (contentValues != null) {
                contentValues.put("card_id", string2);
                string2 = string3.insert(uri, contentValues);
                new StringBuilder("insert=").append((Object)string2);
            } else {
                string2 = null;
            }
            if (string2 == null) {
                hashMap.put("Result", false);
                return hashMap;
            }
            hashMap.put("Result", true);
            return hashMap;
        }
        hashMap.put("Result", false);
        return hashMap;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static Map<String, Object> b(Map<String, Object> hashMap, String string2) {
        Uri uri = Uri.parse((String)"content://com.yunos.lifecard/cards/");
        if (hashMap == null) {
            hashMap = new HashMap();
            hashMap.put("Result", false);
            return hashMap;
        }
        String string3 = ((JSONObject)hashMap.get("content")).toString();
        if (string3 != null && !string3.equals((Object)"")) {
            int n2;
            string3 = Constant.getContext().getContentResolver();
            if ((hashMap = (ContentValues)hashMap.get("contentValue")) != null) {
                new StringBuilder("values=").append((Object)hashMap);
                hashMap.remove("card_tag");
                n2 = string3.update(uri, (ContentValues)hashMap, "card_id = ?", new String[]{string2});
            } else {
                n2 = -1;
            }
            hashMap = new HashMap();
            if (n2 <= 0) {
                hashMap.put("Result", false);
                return hashMap;
            }
            hashMap.put("Result", true);
            return hashMap;
        }
        hashMap.put("Result", false);
        return hashMap;
    }

    public static boolean checkStationList(String string2, String string3, String string4) {
        return ParseManager.checkStationList(string2, string3, string4, false);
    }

    public static void expressage(String string2, String string3, SdkCallBack xyCallBack) {
        if (StringUtils.isNull(string2) || StringUtils.isNull(string3)) {
            XyUtil.doXycallBackResult(xyCallBack, new Object[0]);
            return;
        }
        xyCallBack = new e((SdkCallBack)xyCallBack);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("express_name", (Object)string2);
            jSONObject.put("express_no", (Object)string3);
            NetWebUtil.sendPostRequest(NetWebUtil.WEB_SERVER_URL2, jSONObject.toString(), xyCallBack);
            return;
        }
        catch (JSONException var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static Map<String, Object> parseMsgForCard(Context hashMap, String string2, String string3, String string4, Map<String, String> map) {
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
            if (map != null && map.containsKey(SMS_DATE_TIME)) {
                hashMap.put(SMS_DATE_TIME, map.get(SMS_DATE_TIME));
            }
            if ((hashMap = DexUtil.handerValueMap(hashMap)) == null) {
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
        if ((object = ParseManager.a((Context)map, (String)object, string2, string3, 0, map2)) != null && ParseBubbleManager.getParseStatu(object) != -1) {
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

    public static void queryFlightData(String string2, String string3, String string4, Map<String, Object> map, SdkCallBack sdkCallBack) {
        ParseManager.queryFlightData(string2, string3, string4, map, sdkCallBack);
    }

    public static void queryTrainInfo(String string2, String string3, String string4, String string5, Map<String, Object> map, SdkCallBack sdkCallBack) {
        ParseManager.queryTrainInfo(string2, string3, string4, string5, map, sdkCallBack);
    }
}

