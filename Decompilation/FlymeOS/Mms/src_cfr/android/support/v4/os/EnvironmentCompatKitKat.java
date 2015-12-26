/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Environment
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.os;

import android.os.Environment;
import java.io.File;

class EnvironmentCompatKitKat {
    EnvironmentCompatKitKat() {
    }

    public static String getStorageState(File file) {
        return Environment.getStorageState((File)file);
    }
}

