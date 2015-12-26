/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Environment
 *  android.os.StatFs
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.common.library.utils;

import android.os.Environment;
import android.os.StatFs;

public class SDCardUtils {
    public static long getSDCardAvailableBytes() {
        if (SDCardUtils.isSDCardBusy()) {
            return 0;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long l = statFs.getBlockSize();
        return ((long)statFs.getAvailableBlocks() - 4) * l;
    }

    public static boolean isSDCardBusy() {
        if (!Environment.getExternalStorageState().equals((Object)"mounted")) {
            return true;
        }
        return false;
    }

    public static boolean isSDCardFull() {
        if (SDCardUtils.getSDCardAvailableBytes() <= 102400) {
            return true;
        }
        return false;
    }
}

