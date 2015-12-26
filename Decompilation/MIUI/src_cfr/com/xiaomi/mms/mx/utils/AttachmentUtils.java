/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package com.xiaomi.mms.mx.utils;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mms.mx.cache.DiskLruCache;
import com.xiaomi.mms.mx.cache.ImageCacheManager;
import com.xiaomi.mms.mx.common.GlobalData;
import com.xiaomi.mms.mx.utils.PreferenceUtils;

public class AttachmentUtils {
    public static long generateAttachmentId() {
        long l = PreferenceUtils.getSettingLong(GlobalData.app(), "pref_key_attachment_base_id", 10240);
        l = Math.max((long)System.currentTimeMillis(), (long)l) + 1;
        PreferenceUtils.setSettingLong(GlobalData.app(), "pref_key_attachment_base_id", l);
        return l;
    }

    public static String getCachedImagePath(Context object, String string2) {
        String string3;
        String string4 = string3 = null;
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            object = ImageCacheManager.get((Context)object, "common_image_cache");
            string4 = string3;
            if (object != null) {
                object = object.getDiskLruCache();
                string4 = string3;
                if (object != null) {
                    string4 = object.getCacheFilePath(DiskLruCache.getCommonUrlDiskKey(string2));
                }
            }
        }
        return string4;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static int getMessageTypeFromMimeType(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return 0;
        }
        if (string2.startsWith("video/")) {
            return 4;
        }
        if (string2.startsWith("audio/")) {
            return 3;
        }
        if (!string2.startsWith("image/")) return 0;
        return 2;
    }
}

