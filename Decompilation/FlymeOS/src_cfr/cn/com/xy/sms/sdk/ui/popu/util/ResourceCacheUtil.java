/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Color
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.util.LruCache
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.ui.popu.util;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.LruCache;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.StringUtils;

public class ResourceCacheUtil {
    private static LruCache<String, Integer> a = new LruCache(40);
    private static LruCache<String, BitmapDrawable> b = new LruCache(30);
    private static LruCache<String, Drawable> c = new LruCache(60);

    public static void clearCache() {
        a.evictAll();
        c.evictAll();
        b.evictAll();
    }

    public static Drawable getColorDrawable(String string2) {
        if (string2 == null) {
            return null;
        }
        return (Drawable)c.get((Object)string2);
    }

    public static BitmapDrawable getImgDrawable(String string2) {
        if (string2 == null) {
            return null;
        }
        return (BitmapDrawable)b.get((Object)string2);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static int parseColor(String var0) {
        if (StringUtils.isNull(var0) || var0.indexOf(".") != -1) {
            LogManager.e("ResourceCacheUtil parseColor", "\u53c2\u6570color\u7684\u989c\u8272\u503c\u9519\u8bef,color=" + var0, null);
            return -1;
        }
        var2_4 = (Integer)ResourceCacheUtil.a.get((Object)var0);
        if (var2_4 != null) {
            return var2_4;
        }
        var1_5 = Color.parseColor((String)var0);
        try {
            ResourceCacheUtil.a.put((Object)var0, (Object)var1_5);
            return var1_5;
        }
        catch (Throwable var0_1) {}
        ** GOTO lbl-1000
        catch (Throwable var0_3) {
            var1_5 = -1;
        }
lbl-1000: // 2 sources:
        {
            var0_2.printStackTrace();
            return var1_5;
        }
    }

    public static void putColorDrawable(String string2, Drawable drawable2) {
        if (string2 == null || drawable2 == null) {
            return;
        }
        LruCache<String, Drawable> lruCache = c;
        synchronized (lruCache) {
            c.put((Object)string2, (Object)drawable2);
            return;
        }
    }

    public static void putImgDrawable(String string2, BitmapDrawable bitmapDrawable) {
        if (string2 == null || bitmapDrawable == null) {
            return;
        }
        LruCache<String, BitmapDrawable> lruCache = b;
        synchronized (lruCache) {
            b.put((Object)string2, (Object)bitmapDrawable);
            return;
        }
    }
}

