/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.text.SimpleDateFormat
 *  java.util.Date
 */
package cn.com.xy.sms.sdk.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static final long ONE_DAY_TIME = 86400000;

    public static String addDays(String string2, String string3, int n2) {
        return DateUtils.getTimeString(string3, DateUtils.getTime(string2, string3) + 86400000 * (long)n2);
    }

    public static boolean compareDateString(String string2, String string3, String string4) {
        if (DateUtils.getTime(string2, string4) > DateUtils.getTime(string3, string4)) {
            return true;
        }
        return false;
    }

    public static String getCurrentTimeString(String string2) {
        try {
            string2 = new SimpleDateFormat(string2).format(new Date(System.currentTimeMillis()));
            return string2;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return "";
        }
    }

    public static long getTime(String string2, String string3) {
        try {
            long l2 = new SimpleDateFormat(string3).parse(string2).getTime();
            return l2;
        }
        catch (ParseException var0_1) {
            var0_1.printStackTrace();
            return 0;
        }
    }

    public static String getTimeString(String string2, long l2) {
        try {
            string2 = new SimpleDateFormat(string2).format(new Date(l2));
            return string2;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return "";
        }
    }
}

