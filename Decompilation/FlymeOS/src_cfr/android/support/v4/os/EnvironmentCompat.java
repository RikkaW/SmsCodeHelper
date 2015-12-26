/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Environment
 *  android.util.Log
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.os;

import android.os.Build;
import android.os.Environment;
import android.support.v4.os.EnvironmentCompatKitKat;
import android.util.Log;
import java.io.File;
import java.io.IOException;

public class EnvironmentCompat {
    public static final String MEDIA_UNKNOWN = "unknown";
    private static final String TAG = "EnvironmentCompat";

    public static String getStorageState(File object) {
        if (Build.VERSION.SDK_INT >= 19) {
            return EnvironmentCompatKitKat.getStorageState((File)object);
        }
        try {
            if (object.getCanonicalPath().startsWith(Environment.getExternalStorageDirectory().getCanonicalPath())) {
                object = Environment.getExternalStorageState();
                return object;
            }
        }
        catch (IOException var0_1) {
            Log.w((String)"EnvironmentCompat", (String)("Failed to resolve canonical path: " + (Object)((Object)var0_1)));
        }
        return "unknown";
    }
}

