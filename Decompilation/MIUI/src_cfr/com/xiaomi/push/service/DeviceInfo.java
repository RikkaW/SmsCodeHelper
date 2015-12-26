/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.provider.Settings
 *  android.provider.Settings$Secure
 *  android.telephony.TelephonyManager
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 */
package com.xiaomi.push.service;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.string.XMStringUtils;

public class DeviceInfo {
    private static String sCachedDeviceId;
    private static String sCachedIMEI;
    private static String sCachedSimpleDeviceId;

    static {
        sCachedIMEI = null;
        sCachedDeviceId = null;
        sCachedSimpleDeviceId = null;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String blockingGetIMEI(Context object) {
        if (sCachedIMEI != null) {
            return sCachedIMEI;
        }
        Object object2 = (TelephonyManager)object.getSystemService("phone");
        object = object2.getDeviceId();
        int n = 10;
        do {
            if (object == null && n > 0) {
                Thread.sleep((long)500);
            } else {
                object2 = object;
                if (object == null) return object2;
                try {
                    sCachedIMEI = object;
                    return object;
                }
                catch (Throwable var0_1) {
                    MyLog.e(var0_1);
                    return null;
                }
                catch (InterruptedException interruptedException) {}
            }
            object = object2.getDeviceId();
            --n;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String getDeviceId(Context object) {
        if (sCachedDeviceId == null) {
            String string2 = DeviceInfo.blockingGetIMEI((Context)object);
            String string3 = null;
            try {
                object = Settings.Secure.getString((ContentResolver)object.getContentResolver(), (String)"android_id");
            }
            catch (Throwable var0_1) {
                MyLog.e(var0_1);
                object = string3;
            }
            string3 = null;
            if (Build.VERSION.SDK_INT > 8) {
                string3 = Build.SERIAL;
            }
            sCachedDeviceId = "a-" + XMStringUtils.getSHA1Digest(new StringBuilder().append(string2).append((String)object).append(string3).toString());
        }
        return sCachedDeviceId;
    }

    public static String getSimOperatorName(Context context) {
        return ((TelephonyManager)context.getSystemService("phone")).getSimOperatorName();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String getSimpleDeviceId(Context object) {
        synchronized (DeviceInfo.class) {
            if (sCachedSimpleDeviceId != null) {
                return sCachedSimpleDeviceId;
            }
            String string2 = null;
            try {
                object = Settings.Secure.getString((ContentResolver)object.getContentResolver(), (String)"android_id");
            }
            catch (Throwable var0_1) {
                MyLog.e(var0_1);
                object = string2;
            }
            string2 = null;
            sCachedSimpleDeviceId = XMStringUtils.getSHA1Digest((String)object + string2);
            if (Build.VERSION.SDK_INT <= 8) return sCachedSimpleDeviceId;
            string2 = Build.SERIAL;
            return sCachedSimpleDeviceId;
        }
    }
}

