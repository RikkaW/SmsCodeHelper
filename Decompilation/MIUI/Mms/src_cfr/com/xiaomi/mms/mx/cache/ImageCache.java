/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$CompressFormat
 *  android.os.Environment
 *  android.support.v4.util.LruCache
 *  java.io.File
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.mx.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.util.LruCache;
import com.xiaomi.mms.mx.cache.DiskImageCache;
import com.xiaomi.mms.mx.cache.DiskLruCache;
import com.xiaomi.mms.mx.cache.ImageCacheUtils;
import com.xiaomi.mms.mx.common.GlobalData;
import java.io.File;

public class ImageCache {
    private static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.JPEG;
    private DiskImageCache mDiskCache;
    private ImageCacheParams mImageCacheParams;
    private LruCache<String, Bitmap> mMemoryCache;

    public ImageCache(Context context, ImageCacheParams imageCacheParams) {
        this.init(context, imageCacheParams);
    }

    public ImageCache(Context context, String string2) {
        this.init(context, new ImageCacheParams(string2));
    }

    static /* synthetic */ Bitmap.CompressFormat access$000() {
        return DEFAULT_COMPRESS_FORMAT;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static File getDiskCacheDir(Context object, String string2) {
        if ("mounted".equals((Object)Environment.getExternalStorageState()) || !ImageCacheUtils.isExternalStorageRemovable()) {
            object = ImageCacheUtils.getExternalCacheDir((Context)object).getPath();
            do {
                return new File((String)object + File.separator + string2);
                break;
            } while (true);
        }
        object = object.getCacheDir().getPath();
        return new File((String)object + File.separator + string2);
    }

    private void init(Context context, ImageCacheParams imageCacheParams) {
        this.mImageCacheParams = imageCacheParams;
        if (imageCacheParams.memoryCacheEnabled) {
            this.mMemoryCache = new LruCache<String, Bitmap>(imageCacheParams.memCacheSize){

                protected int sizeOf(String string2, Bitmap bitmap) {
                    if (bitmap == null) {
                        return 0;
                    }
                    return ImageCacheUtils.getBitmapSize(bitmap);
                }
            };
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public DiskImageCache getDiskImageCache() {
        synchronized (this) {
            block4 : {
                if (this.mDiskCache == null) break block4;
                return this.mDiskCache;
            }
            if (!this.mImageCacheParams.diskCacheEnabled) return null;
            DiskImageCache diskImageCache = ImageCache.getDiskCacheDir(GlobalData.app(), this.mImageCacheParams.uniqueName);
            if (!this.mImageCacheParams.diskCacheEnabled) return this.mDiskCache;
            this.mDiskCache = DiskImageCache.openCache(GlobalData.app(), (File)diskImageCache, this.mImageCacheParams.diskCacheSize);
            if (this.mDiskCache == null) return this.mDiskCache;
            this.mDiskCache.setCompressParams(this.mImageCacheParams.compressFormat, this.mImageCacheParams.compressQuality);
            if (!this.mImageCacheParams.clearDiskCacheOnStart) return this.mDiskCache;
            this.mDiskCache.clearCache();
            return this.mDiskCache;
        }
    }

    public DiskLruCache getDiskLruCache() {
        if (this.getDiskImageCache() == null) {
            return null;
        }
        return this.getDiskImageCache().getDiskLruCache();
    }

    public static class ImageCacheParams {
        public boolean clearDiskCacheOnStart = false;
        public Bitmap.CompressFormat compressFormat = ImageCache.access$000();
        public int compressQuality = 80;
        public boolean diskCacheEnabled = true;
        public int diskCacheSize = 314572800;
        public int memCacheSize = 10485760;
        public boolean memoryCacheEnabled = true;
        public String uniqueName;

        public ImageCacheParams(String string2) {
            this(string2, 10485760);
        }

        public ImageCacheParams(String string2, int n) {
            this.uniqueName = string2;
            this.memCacheSize = n;
        }
    }

}

