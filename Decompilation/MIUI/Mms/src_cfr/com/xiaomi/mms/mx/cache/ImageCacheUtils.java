/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Environment
 *  android.os.StatFs
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.mx.cache;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import java.io.Closeable;
import java.io.File;

public class ImageCacheUtils {
    private ImageCacheUtils() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void closeQuietly(Closeable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
            return;
        }
        catch (RuntimeException var0_1) {
            throw var0_1;
        }
        catch (Exception var0_2) {
            return;
        }
    }

    @SuppressLint(value={"NewApi"})
    public static int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 12) {
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    @SuppressLint(value={"NewApi"})
    public static File getExternalCacheDir(Context object) {
        File file;
        if (ImageCacheUtils.hasExternalCacheDir() && (file = object.getExternalCacheDir()) != null) {
            return file;
        }
        object = "/Android/data/" + object.getPackageName() + "/cache/";
        return new File(Environment.getExternalStorageDirectory().getPath() + (String)object);
    }

    @SuppressLint(value={"NewApi"})
    public static long getUsableSpace(File file) {
        if (Build.VERSION.SDK_INT >= 9) {
            return file.getUsableSpace();
        }
        file = new StatFs(file.getPath());
        return (long)file.getBlockSize() * (long)file.getAvailableBlocks();
    }

    public static boolean hasExternalCacheDir() {
        if (Build.VERSION.SDK_INT >= 8) {
            return true;
        }
        return false;
    }

    @SuppressLint(value={"NewApi"})
    public static boolean isExternalStorageRemovable() {
        if (Build.VERSION.SDK_INT >= 9) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }
}

