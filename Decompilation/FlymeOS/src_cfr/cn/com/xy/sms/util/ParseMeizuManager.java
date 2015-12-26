/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  org.json.JSONArray
 *  org.json.JSONObject
 */
package cn.com.xy.sms.util;

import android.content.Context;
import android.text.TextUtils;
import cn.com.xy.sms.util.ParseManager;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ParseMeizuManager {
    public static final String SMS_BALANCE = "1";
    public static final String SMS_FLOW = "2";
    public static final String SMS_FLOW_FOUR = "23";
    public static final String SMS_FLOW_THREE = "22";
    public static final String SMS_FLOW_TWO = "21";
    private static final String a = "\u67e5\u8be2\u4f59\u989d";
    private static final String b = "\u67e5\u8be2\u6d41\u91cf";
    private static final String c = "\u67e52/3G\u6d41\u91cf";
    private static final String d = "\u67e53/4G\u6d41\u91cf";
    private static final String e = "\u67e5\u8be22G\u6d41\u91cf";
    private static final String f = "\u67e5\u8be23G\u6d41\u91cf";
    private static final String g = "\u67e5\u8be24G\u6d41\u91cf";
    private static final String h = "\u67e5\u8be2\u5145\u503c";
    private static final String i = "\u67e5\u8be2\u4e1a\u52a1";

    private static String a(String string2, JSONObject jSONObject) {
        if ("1".equals((Object)string2) && "\u67e5\u8be2\u4f59\u989d".equals((Object)jSONObject.getString("name"))) {
            return jSONObject.getString("action_data");
        }
        if (("2".equals((Object)string2) || "21".equals((Object)string2) || "22".equals((Object)string2) || "23".equals((Object)string2)) && "\u67e5\u8be2\u6d41\u91cf".equals((Object)jSONObject.getString("name"))) {
            return jSONObject.getString("action_data");
        }
        if ("21".equals((Object)string2) && ("\u67e5\u8be22G\u6d41\u91cf".equals((Object)jSONObject.getString("name")) || "\u67e52/3G\u6d41\u91cf".equals((Object)jSONObject.getString("name")))) {
            return jSONObject.getString("action_data");
        }
        if ("22".equals((Object)string2) && ("\u67e5\u8be23G\u6d41\u91cf".equals((Object)jSONObject.getString("name")) || "\u67e52/3G\u6d41\u91cf".equals((Object)jSONObject.getString("name")) || "\u67e53/4G\u6d41\u91cf".equals((Object)jSONObject.getString("name")))) {
            return jSONObject.getString("action_data");
        }
        if ("23".equals((Object)string2) && ("\u67e5\u8be24G\u6d41\u91cf".equals((Object)jSONObject.getString("name")) || "\u67e53/4G\u6d41\u91cf".equals((Object)jSONObject.getString("name")))) {
            return jSONObject.getString("action_data");
        }
        return null;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static String querySmsOrderActionData(Context var0, String var1_1, String var2_2, int var3_3, String var4_4, Map<String, String> var5_5) {
        if (TextUtils.isEmpty((CharSequence)(var0 = ParseManager.queryMenuByPhoneNum((Context)var0, var1_1, var3_3, var4_4, var5_5)))) {
            return null;
        }
        var0 = new JSONArray((String)var0);
        var3_3 = 0;
        block0 : do {
            if (var3_3 >= var0.length()) {
                return null;
            }
            var1_1 = var0.getJSONObject(var3_3);
            if ("\u67e5\u8be2\u5145\u503c".equals((Object)var1_1.getString("name")) || "\u67e5\u8be2\u4e1a\u52a1".equals((Object)var1_1.getString("name"))) ** GOTO lbl13
            if (!TextUtils.isEmpty((CharSequence)(var1_1 = ParseMeizuManager.a(var2_2, (JSONObject)var1_1)))) {
                return var1_1;
            }
            ** GOTO lbl-1000
lbl13: // 1 sources:
            var1_1 = var1_1.getJSONArray("secondmenu");
            var6_6 = 0;
            do {
                if (var6_6 >= var1_1.length()) lbl-1000: // 2 sources:
                {
                    ++var3_3;
                    continue block0;
                }
                var4_4 = ParseMeizuManager.a(var2_2, var1_1.getJSONObject(var6_6));
                if (!TextUtils.isEmpty((CharSequence)var4_4)) {
                    return var4_4;
                }
                ++var6_6;
            } while (true);
            break;
        } while (true);
    }
}

