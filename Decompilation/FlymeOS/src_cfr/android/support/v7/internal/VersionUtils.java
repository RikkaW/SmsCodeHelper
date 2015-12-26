/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.lang.Object
 */
package android.support.v7.internal;

import android.os.Build;

public class VersionUtils {
    private VersionUtils() {
    }

    public static boolean isAtLeastL() {
        if (Build.VERSION.SDK_INT >= 21) {
            return true;
        }
        return false;
    }
}

