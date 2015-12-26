/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$CompressFormat
 *  java.io.File
 *  java.lang.Math
 *  java.lang.Object
 */
package com.xiaomi.mms.mx.cache;

import android.content.Context;
import android.graphics.Bitmap;
import com.xiaomi.mms.mx.bitmap.reader.BitmapReader;
import com.xiaomi.mms.mx.cache.DiskLruCache;
import com.xiaomi.mms.mx.cache.ImageCacheUtils;
import com.xiaomi.mms.mx.utils.Log;
import java.io.File;
import java.io.IOException;

public class DiskImageCache {
    private BitmapReader mBitmapReader;
    private final File mCacheDir;
    private Bitmap.CompressFormat mCompressFormat = Bitmap.CompressFormat.JPEG;
    private int mCompressQuality = 80;
    private DiskLruCache mDiskLruCache = null;

    private DiskImageCache(File file, long l) throws IOException {
        this.mCacheDir = file;
        this.mDiskLruCache = DiskLruCache.open(this.mCacheDir, 1, 1, l);
        this.mBitmapReader = new BitmapReader();
    }

    public static DiskImageCache openCache(Context object, File file, long l) {
        try {
            if (!file.exists() || !file.isDirectory()) {
                file.mkdirs();
            }
            if (file.isDirectory() && file.canWrite()) {
                object = new DiskImageCache(file, Math.max((long)Math.min((long)(ImageCacheUtils.getUsableSpace(file) / 3), (long)l), (long)1));
                return object;
            }
        }
        catch (IOException var0_1) {
            Log.e("DiskImageCache", " Error in openCache: ", var0_1);
        }
        return null;
    }

    public void clearCache() {
        try {
            this.mDiskLruCache.delete();
            return;
        }
        catch (IOException var1_1) {
            Log.e("DiskImageCache", " Error in clearCache: ", var1_1);
            return;
        }
    }

    public DiskLruCache getDiskLruCache() {
        return this.mDiskLruCache;
    }

    public void setCompressParams(Bitmap.CompressFormat compressFormat, int n) {
        this.mCompressFormat = compressFormat;
        this.mCompressQuality = n;
    }
}

