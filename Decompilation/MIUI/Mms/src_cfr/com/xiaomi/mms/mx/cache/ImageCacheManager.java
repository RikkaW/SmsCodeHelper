/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  java.util.concurrent.ConcurrentHashMap
 */
package com.xiaomi.mms.mx.cache;

import android.content.Context;
import com.xiaomi.mms.mx.cache.ImageCache;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ImageCacheManager {
    protected static Map<String, ImageCache> mImageCacheMap = new ConcurrentHashMap();

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static ImageCache get(Context context, ImageCache.ImageCacheParams imageCacheParams) {
        Map<String, ImageCache> map = mImageCacheMap;
        synchronized (map) {
            ImageCache imageCache;
            ImageCache imageCache2 = imageCache = mImageCacheMap.get(imageCacheParams.uniqueName);
            if (imageCache == null) {
                imageCache2 = new ImageCache(context, imageCacheParams);
                mImageCacheMap.put(imageCacheParams.uniqueName, imageCache2);
            }
            return imageCache2;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static ImageCache get(Context context, String string2) {
        Map<String, ImageCache> map = mImageCacheMap;
        synchronized (map) {
            ImageCache imageCache;
            ImageCache imageCache2 = imageCache = mImageCacheMap.get(string2);
            if (imageCache == null) {
                imageCache2 = new ImageCache(context, string2);
                mImageCacheMap.put(string2, imageCache2);
            }
            return imageCache2;
        }
    }
}

