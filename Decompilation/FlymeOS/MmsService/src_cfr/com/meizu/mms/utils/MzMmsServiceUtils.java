/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.BuildExt
 *  android.telephony.SubscriptionManager
 *  android.telephony.TelephonyManager
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 */
package com.meizu.mms.utils;

import android.os.BuildExt;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;

public class MzMmsServiceUtils {
    public static boolean checkIsFailedRespStatus(int n) {
        if (n == 9527 || n == 132 || n == 135 || n == 130) {
            return true;
        }
        return false;
    }

    public static int getSlotIdBySubId(int n) {
        return SubscriptionManager.getSlotId((int)n);
    }

    public static String getSubscriberIdBySubId(int n) {
        return TelephonyManager.getDefault().getSubscriberId(n);
    }

    public static boolean isCTVersion() {
        return BuildExt.CUSTOMIZE_CHINATELECOM;
    }
}

