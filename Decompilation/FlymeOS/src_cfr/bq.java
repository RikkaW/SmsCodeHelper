/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.Signature
 *  android.os.Bundle
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.TextUtils;

public class bq {
    private static int a = 100;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String a(Context object) {
        Object object2 = "default";
        try {
            Signature[] arrsignature = object.getPackageManager().getPackageInfo((String)object.getPackageName(), (int)64).signatures;
            object = object2;
            if (arrsignature != null) {
                object = object2;
                if (arrsignature.length > 0) {
                    object = anv.a(arrsignature[0].toByteArray());
                }
            }
        }
        catch (Exception var0_1) {
            object = object2;
        }
        object2 = object;
        if (!TextUtils.isEmpty((CharSequence)object)) return object2;
        return "default";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String b(Context object) {
        String string2 = object.getPackageName();
        object = object.getPackageManager();
        Object object2 = "";
        try {
            string2 = object.getApplicationInfo(string2, 128);
            object = object2;
            if (string2 != null) {
                object = object2;
                if (string2.metaData != null) {
                    object = string2.metaData.getString("com.ted.sdk.appkey");
                }
            }
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            object = object2;
        }
        object2 = object;
        if (!TextUtils.isEmpty((CharSequence)object)) return object2;
        return "default";
    }
}

