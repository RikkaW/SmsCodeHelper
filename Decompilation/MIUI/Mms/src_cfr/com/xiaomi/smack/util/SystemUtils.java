/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.Base64
 *  java.lang.Object
 *  java.lang.String
 *  java.security.MessageDigest
 */
package com.xiaomi.smack.util;

import android.content.Context;
import android.util.Base64;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.DeviceInfo;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SystemUtils {
    private static Context sContext;
    private static String sUUID;

    public static Context getContext() {
        return sContext;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String getDeviceUUID() {
        if (sUUID != null) return sUUID;
        String string2 = DeviceInfo.blockingGetIMEI(sContext);
        if (string2 == null) return sUUID;
        try {
            sUUID = Base64.encodeToString((byte[])MessageDigest.getInstance((String)"SHA1").digest(string2.getBytes()), (int)8).substring(0, 16);
            return sUUID;
        }
        catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            MyLog.e(noSuchAlgorithmException);
            return sUUID;
        }
    }

    public static void initialize(Context context) {
        sContext = context;
    }
}

