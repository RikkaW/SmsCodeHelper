/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Environment
 *  android.os.StatFs
 *  android.text.TextUtils
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.channel.commonutils.file;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import java.io.File;

public class SDCardUtils {
    /*
     * Enabled aggressive block sorting
     */
    public static long getSDCardAvailableBytes() {
        File file;
        if (SDCardUtils.isSDCardBusy() || (file = Environment.getExternalStorageDirectory()) == null || TextUtils.isEmpty((CharSequence)file.getPath())) {
            return 0;
        }
        file = new StatFs(file.getPath());
        long l = file.getBlockSize();
        return ((long)file.getAvailableBlocks() - 4) * l;
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

    public static boolean isSDCardUnavailable() {
        return Environment.getExternalStorageState().equals((Object)"removed");
    }

    public static boolean isSDCardUseful() {
        if (!(SDCardUtils.isSDCardBusy() || SDCardUtils.isSDCardFull() || SDCardUtils.isSDCardUnavailable())) {
            return true;
        }
        return false;
    }
}

