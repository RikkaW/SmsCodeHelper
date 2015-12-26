/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  android.content.pm.Signature
 *  android.os.Bundle
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.Throwable
 *  java.security.MessageDigest
 *  java.util.Locale
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import com.amap.api.mapcore2d.ed;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class da {
    private static String a = "";
    private static String b = null;
    private static String c = "";
    private static String d;
    private static String e;

    static {
        e = null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(Context context) {
        try {
            if (!"".equals((Object)a)) {
                return a;
            }
            PackageManager packageManager = context.getPackageManager();
            a = (String)packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
            do {
                return a;
                break;
            } while (true);
        }
        catch (PackageManager.NameNotFoundException var0_1) {
            ed.a((Throwable)var0_1, "AppInfo", "getApplicationName");
            var0_1.printStackTrace();
            return a;
        }
        catch (Throwable var0_2) {
            ed.a(var0_2, "AppInfo", "getApplicationName");
            var0_2.printStackTrace();
            return a;
        }
    }

    static void a(String string2) {
        d = string2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String b(Context context) {
        try {
            if (b != null && !"".equals((Object)b)) {
                return b;
            }
            b = context.getApplicationContext().getPackageName();
            do {
                return b;
                break;
            } while (true);
        }
        catch (Throwable var0_1) {
            ed.a(var0_1, "AppInfo", "getPackageName");
            var0_1.printStackTrace();
            return b;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String c(Context context) {
        try {
            if (!"".equals((Object)c)) {
                return c;
            }
            c = context.getPackageManager().getPackageInfo((String)context.getPackageName(), (int)0).versionName;
            do {
                return c;
                break;
            } while (true);
        }
        catch (PackageManager.NameNotFoundException var0_1) {
            ed.a((Throwable)var0_1, "AppInfo", "getApplicationVersion");
            var0_1.printStackTrace();
            return c;
        }
        catch (Throwable var0_2) {
            ed.a(var0_2, "AppInfo", "getApplicationVersion");
            var0_2.printStackTrace();
            return c;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String d(Context object) {
        if (e != null && !"".equals((Object)e)) {
            return e;
        }
        object = object.getPackageManager().getPackageInfo(object.getPackageName(), 64);
        byte[] arrby = object.signatures[0].toByteArray();
        arrby = MessageDigest.getInstance((String)"SHA1").digest(arrby);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < arrby.length; ++i2) {
            String string2 = Integer.toHexString((int)(arrby[i2] & 255)).toUpperCase(Locale.US);
            if (string2.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(string2);
            stringBuffer.append(":");
        }
        try {
            stringBuffer.append(object.packageName);
            e = stringBuffer.toString();
            return e;
        }
        catch (PackageManager.NameNotFoundException var0_1) {
            ed.a((Throwable)var0_1, "AppInfo", "getSHA1AndPackage");
            var0_1.printStackTrace();
            do {
                return e;
                break;
            } while (true);
        }
        catch (NoSuchAlgorithmException var0_2) {
            ed.a(var0_2, "AppInfo", "getSHA1AndPackage");
            var0_2.printStackTrace();
            return e;
        }
        catch (Throwable var0_3) {
            ed.a(var0_3, "AppInfo", "getSHA1AndPackage");
            var0_3.printStackTrace();
            return e;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String e(Context object) {
        try {
            return da.g((Context)object);
        }
        catch (PackageManager.NameNotFoundException var0_1) {
            var0_1.printStackTrace();
            do {
                return d;
                break;
            } while (true);
        }
        catch (Throwable var0_2) {
            var0_2.printStackTrace();
            return d;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String f(Context object) {
        try {
            return da.g((Context)object);
        }
        catch (PackageManager.NameNotFoundException var0_1) {
            ed.a((Throwable)var0_1, "AppInfo", "getKey");
            var0_1.printStackTrace();
            do {
                return d;
                break;
            } while (true);
        }
        catch (Throwable var0_2) {
            ed.a(var0_2, "AppInfo", "getKey");
            var0_2.printStackTrace();
            return d;
        }
    }

    private static String g(Context context) {
        if (d == null || d.equals((Object)"")) {
            d = context.getPackageManager().getApplicationInfo((String)context.getPackageName(), (int)128).metaData.getString("com.amap.api.v2.apikey");
        }
        return d;
    }
}

