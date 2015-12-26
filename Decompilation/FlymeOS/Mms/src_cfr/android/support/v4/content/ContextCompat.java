/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.ApplicationInfo
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Environment
 *  android.util.Log
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompatApi21;
import android.support.v4.content.ContextCompatFroyo;
import android.support.v4.content.ContextCompatHoneycomb;
import android.support.v4.content.ContextCompatJellybean;
import android.support.v4.content.ContextCompatKitKat;
import android.util.Log;
import java.io.File;

public class ContextCompat {
    private static final String DIR_ANDROID = "Android";
    private static final String DIR_CACHE = "cache";
    private static final String DIR_DATA = "data";
    private static final String DIR_FILES = "files";
    private static final String DIR_OBB = "obb";
    private static final String TAG = "ContextCompat";

    /*
     * Enabled aggressive block sorting
     */
    private static /* varargs */ File buildPath(File file, String ... arrstring) {
        int n2 = arrstring.length;
        int n3 = 0;
        while (n3 < n2) {
            String string2 = arrstring[n3];
            if (file == null) {
                file = new File(string2);
            } else if (string2 != null) {
                file = new File(file, string2);
            }
            ++n3;
        }
        return file;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static File createFilesDir(File file) {
        synchronized (ContextCompat.class) {
            block5 : {
                File file2 = file;
                if (file.exists()) return file2;
                file2 = file;
                if (file.mkdirs()) return file2;
                boolean bl2 = file.exists();
                if (!bl2) break block5;
                return file;
            }
            Log.w((String)"ContextCompat", (String)("Unable to create files subdir " + file.getPath()));
            return null;
        }
    }

    public static final Drawable getDrawable(Context context, int n2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return ContextCompatApi21.getDrawable(context, n2);
        }
        return context.getResources().getDrawable(n2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static File[] getExternalCacheDirs(Context context) {
        int n2 = Build.VERSION.SDK_INT;
        if (n2 >= 19) {
            return ContextCompatKitKat.getExternalCacheDirs(context);
        }
        if (n2 >= 8) {
            context = ContextCompatFroyo.getExternalCacheDir(context);
            do {
                return new File[]{context};
                break;
            } while (true);
        }
        context = ContextCompat.buildPath(Environment.getExternalStorageDirectory(), "Android", "data", context.getPackageName(), "cache");
        return new File[]{context};
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static File[] getExternalFilesDirs(Context context, String string2) {
        int n2 = Build.VERSION.SDK_INT;
        if (n2 >= 19) {
            return ContextCompatKitKat.getExternalFilesDirs(context, string2);
        }
        if (n2 >= 8) {
            context = ContextCompatFroyo.getExternalFilesDir(context, string2);
            do {
                return new File[]{context};
                break;
            } while (true);
        }
        context = ContextCompat.buildPath(Environment.getExternalStorageDirectory(), "Android", "data", context.getPackageName(), "files", string2);
        return new File[]{context};
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static File[] getObbDirs(Context context) {
        int n2 = Build.VERSION.SDK_INT;
        if (n2 >= 19) {
            return ContextCompatKitKat.getObbDirs(context);
        }
        if (n2 >= 11) {
            context = ContextCompatHoneycomb.getObbDir(context);
            do {
                return new File[]{context};
                break;
            } while (true);
        }
        context = ContextCompat.buildPath(Environment.getExternalStorageDirectory(), "Android", "obb", context.getPackageName());
        return new File[]{context};
    }

    public static boolean startActivities(Context context, Intent[] arrintent) {
        return ContextCompat.startActivities(context, arrintent, null);
    }

    public static boolean startActivities(Context context, Intent[] arrintent, Bundle bundle) {
        int n2 = Build.VERSION.SDK_INT;
        if (n2 >= 16) {
            ContextCompatJellybean.startActivities(context, arrintent, bundle);
            return true;
        }
        if (n2 >= 11) {
            ContextCompatHoneycomb.startActivities(context, arrintent);
            return true;
        }
        return false;
    }

    public final File getCodeCacheDir(Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            return ContextCompatApi21.getCodeCacheDir(context);
        }
        return ContextCompat.createFilesDir(new File(context.getApplicationInfo().dataDir, "code_cache"));
    }

    public final File getNoBackupFilesDir(Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            return ContextCompatApi21.getNoBackupFilesDir(context);
        }
        return ContextCompat.createFilesDir(new File(context.getApplicationInfo().dataDir, "no_backup"));
    }
}

