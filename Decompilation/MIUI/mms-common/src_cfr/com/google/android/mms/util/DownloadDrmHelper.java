/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.drm.DrmManagerClient
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 */
package com.google.android.mms.util;

import android.content.Context;
import android.drm.DrmManagerClient;
import android.util.Log;

public class DownloadDrmHelper {
    public static final String EXTENSION_DRM_MESSAGE = ".dm";
    public static final String EXTENSION_INTERNAL_FWDL = ".fl";
    public static final String MIMETYPE_DRM_MESSAGE = "application/vnd.oma.drm.message";
    private static final String TAG = "DownloadDrmHelper";

    public static String getOriginalMimeType(Context object, String string, String string2) {
        DrmManagerClient drmManagerClient = new DrmManagerClient((Context)object);
        object = string2;
        try {
            if (drmManagerClient.canHandle(string, null)) {
                object = drmManagerClient.getOriginalMimeType(string);
            }
            return object;
        }
        catch (IllegalArgumentException var0_1) {
            Log.w((String)"DownloadDrmHelper", (String)"Can't get original mime type since path is null or empty string.");
            return string2;
        }
        catch (IllegalStateException var0_2) {
            Log.w((String)"DownloadDrmHelper", (String)"DrmManagerClient didn't initialize properly.");
            return string2;
        }
    }

    public static boolean isDrmConvertNeeded(String string) {
        return "application/vnd.oma.drm.message".equals((Object)string);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isDrmMimeType(Context context, String string) {
        boolean bl2;
        boolean bl = bl2 = false;
        if (context == null) return bl;
        context = new DrmManagerClient(context);
        bl = bl2;
        if (context == null) return bl;
        bl = bl2;
        if (string == null) return bl;
        bl = bl2;
        try {
            if (string.length() <= 0) return bl;
            return context.canHandle("", string);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            Log.w((String)"DownloadDrmHelper", (String)"DrmManagerClient instance could not be created, context is Illegal.");
            return false;
        }
        catch (IllegalStateException illegalStateException) {
            Log.w((String)"DownloadDrmHelper", (String)"DrmManagerClient didn't initialize properly.");
            return false;
        }
    }

    public static String modifyDrmFwLockFileExtension(String string) {
        String string2 = string;
        if (string != null) {
            int n = string.lastIndexOf(".");
            string2 = string;
            if (n != -1) {
                string2 = string.substring(0, n);
            }
            string2 = string2.concat(".fl");
        }
        return string2;
    }
}

