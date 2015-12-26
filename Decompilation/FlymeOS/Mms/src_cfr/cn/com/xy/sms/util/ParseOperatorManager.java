/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  org.json.JSONObject
 */
package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.util.ParseManager;
import cn.com.xy.sms.util.SdkCallBack;
import java.util.Map;
import org.json.JSONObject;

public class ParseOperatorManager {
    private static String a = "ParseOperatorManager";

    public static JSONObject parseOperatorMsg(Context context, String string2, String string3, String string4, String string5, long l2, Map<String, String> map) {
        try {
            ParseManager.a(context, string3, string4, string5, l2, map);
            return null;
        }
        catch (Throwable var0_1) {
            LogManager.e("ParseOperatorManager", "queryOperatorCmd " + var0_1.getMessage(), var0_1);
            return null;
        }
    }

    public static JSONObject queryOperatorCmd(Context context, String string2, String string3, Map map, SdkCallBack sdkCallBack) {
        return null;
    }
}

