/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.content;

import android.content.Context;
import java.io.File;

class ContextCompatKitKat {
    ContextCompatKitKat() {
    }

    public static File[] getExternalCacheDirs(Context context) {
        return context.getExternalCacheDirs();
    }

    public static File[] getExternalFilesDirs(Context context, String string2) {
        return context.getExternalFilesDirs(string2);
    }

    public static File[] getObbDirs(Context context) {
        return context.getObbDirs();
    }
}

