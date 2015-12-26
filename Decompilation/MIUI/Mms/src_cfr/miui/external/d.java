/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.PackageManager$NameNotFoundException
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 */
package miui.external;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.File;

class d {
    private d() {
    }

    private static PackageInfo a(Context context, String string2) {
        context = context.getPackageManager();
        try {
            context = context.getPackageInfo(string2, 128);
            return context;
        }
        catch (PackageManager.NameNotFoundException var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    private static String a(String string2) {
        return d.a(new String[]{"/data/app/" + string2 + "-1.apk", "/data/app/" + string2 + "-2.apk", "/data/app/" + string2 + "-1/base.apk", "/data/app/" + string2 + "-2/base.apk"});
    }

    private static String a(String string2, String string3) {
        String string4;
        string2 = string4 = d.a(string2);
        if (string4 == null) {
            string2 = d.b(string3);
        }
        return string2;
    }

    private static String a(String[] arrstring) {
        for (String string2 : arrstring) {
            if (!new File(string2).exists()) continue;
            return string2;
        }
        return null;
    }

    private static String b(String string2) {
        return d.a(new String[]{"/system/app/" + string2 + ".apk", "/system/priv-app/" + string2 + ".apk", "/system/app/" + string2 + "/" + string2 + ".apk", "/system/priv-app/" + string2 + "/" + string2 + ".apk"});
    }

    private static String c(String string2) {
        return "/data/data/" + string2 + "/lib/";
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getApkPath(Context object, String string2, String string3) {
        Object var3_3 = null;
        if (object == null) {
            return d.a(string2, string3);
        }
        string2 = d.a((Context)object, string2);
        object = var3_3;
        if (string2 == null) return object;
        return string2.applicationInfo.publicSourceDir;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getLibPath(Context object, String string2) {
        Object var2_2 = null;
        if (object == null) {
            return d.c(string2);
        }
        string2 = d.a((Context)object, string2);
        object = var2_2;
        if (string2 == null) return object;
        return string2.applicationInfo.nativeLibraryDir;
    }

    public static boolean isMiuiSystem() {
        if (d.b("miui") != null) {
            return true;
        }
        return false;
    }
}

