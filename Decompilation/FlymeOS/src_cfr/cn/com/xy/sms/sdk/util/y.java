/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.PackageManager
 *  android.net.Uri
 *  android.widget.Toast
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.Throwable
 *  java.net.URLEncoder
 */
package cn.com.xy.sms.sdk.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.net.URLEncoder;

public final class y {
    private static String a = "com.eg.android.AlipayGphone";
    private static String b = "alipays://platformapi/startapp?";
    private static String c = "&";
    private static String d = "appId=10000003";
    private static String e = "appId=09999999";
    private static String f = "XIAOYUAN";

    private static void a(StringBuffer stringBuffer, String string2, String string3) {
        if (string2.length() > 0 && string3 != null && string3.length() > 0) {
            stringBuffer.append("&");
            stringBuffer.append(string2);
            stringBuffer.append("=");
            stringBuffer.append(string3);
        }
    }

    public static boolean a(Context context, String string2) {
        if (!y.b(context, "com.eg.android.AlipayGphone")) {
            return false;
        }
        try {
            StringBuffer stringBuffer = new StringBuffer("alipays://platformapi/startapp?appId=10000003");
            y.a(stringBuffer, "sourceId", "XIAOYUAN");
            y.a(stringBuffer, "actionType", "recharge");
            y.a(stringBuffer, "mobileNo", string2);
            y.c(context, stringBuffer.toString());
            return true;
        }
        catch (Throwable var1_3) {
            var1_3.printStackTrace();
            try {
                Toast.makeText((Context)context, (CharSequence)"\u5904\u7406\u5931\u8d25.", (int)1).show();
                return false;
            }
            catch (Throwable var0_1) {
                var1_3.printStackTrace();
                return false;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean a(Context context, String string2, String string3, String string4, String string5, boolean bl2) {
        if (!y.b(context, "com.eg.android.AlipayGphone")) {
            return false;
        }
        try {
            StringBuffer stringBuffer = new StringBuffer("alipays://platformapi/startapp?appId=09999999");
            y.a(stringBuffer, "sourceId", "XIAOYUAN");
            y.a(stringBuffer, "outTradeNo", "9999");
            y.a(stringBuffer, "cardNumberType", "TAIL");
            y.a(stringBuffer, "cardNumber", string2);
            string2 = string3;
            if (!StringUtils.isNull(string3)) {
                string2 = string3.replaceAll(",", "");
            }
            y.a(stringBuffer, "amount", string2);
            y.a(stringBuffer, "bankMark", string5);
            if (string4 != null && string4.length() > 0) {
                y.a(stringBuffer, "userName", URLEncoder.encode((String)string4, (String)"UTF-8"));
            }
            y.c(context, stringBuffer.toString());
            return true;
        }
        catch (Throwable var1_3) {
            var1_3.printStackTrace();
            try {
                Toast.makeText((Context)context, (CharSequence)"\u5904\u7406\u5931\u8d25.", (int)1).show();
                return false;
            }
            catch (Throwable var0_1) {
                var1_3.printStackTrace();
                return false;
            }
        }
    }

    private static boolean b(Context context, String string2) {
        try {
            context.getPackageManager().getPackageGids(string2);
            return true;
        }
        catch (Throwable var0_1) {
            return false;
        }
    }

    private static void c(Context context, String string2) {
        Intent intent = new Intent();
        intent.setData(Uri.parse((String)string2));
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(536870912);
        intent.setFlags(335544320);
        context.startActivity(intent);
    }
}

