/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashMap
 */
package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import java.util.HashMap;

public class SdkParamUtil {
    public static String getParamValue(Context context, String string2) {
        return SysParamEntityManager.getStringParam(context, string2);
    }

    public static boolean setParamValue(Context context, String string2, String string3) {
        return SdkParamUtil.setParamValue(context, string2, string3, null);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean setParamValue(Context context, String string2, String string3, String string4) {
        try {
            if (SysParamEntityManager.insertOrUpdateKeyValue(context, string2, string3, string4) <= 0) return false;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return false;
        }
        boolean bl2 = true;
        if (!bl2) return false;
        SysParamEntityManager.cacheMap.put((Object)string2, (Object)string3);
        return false;
    }
}

