/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.PackageManager
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import cn.com.xy.sms.sdk.util.DuoquUtils;

public final class a {
    public static String a(int n2) {
        StringBuffer stringBuffer = new StringBuffer();
        int n3 = 1;
        while (n3 < 5) {
            stringBuffer.append(DuoquUtils.getCode(n3, n2));
            ++n3;
        }
        return stringBuffer.toString();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String a(String string2) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            int n2 = 0;
            do {
                if (n2 >= 4) {
                    return stringBuffer.toString();
                }
                stringBuffer.append(string2.substring(n2, n2 + 1));
                stringBuffer.append(string2.substring(n2 + 4, n2 + 5));
                stringBuffer.append(string2.substring(n2 + 8, n2 + 9));
                stringBuffer.append(string2.substring(n2 + 12, n2 + 13));
                ++n2;
            } while (true);
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return "";
        }
    }

    public static boolean a(Context context, String string2) {
        try {
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(string2));
            return true;
        }
        catch (Throwable var0_1) {
            return false;
        }
    }
}

