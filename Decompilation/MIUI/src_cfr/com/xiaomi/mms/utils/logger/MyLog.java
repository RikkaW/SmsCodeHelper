/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashMap
 *  java.util.concurrent.atomic.AtomicInteger
 */
package com.xiaomi.mms.utils.logger;

import com.xiaomi.mms.utils.logger.DefaultAndroidLogger;
import com.xiaomi.mms.utils.logger.LoggerInterface;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class MyLog {
    private static int LOG_LEVEL = 0;
    private static final Integer NEGATIVE_CODE;
    private static LoggerInterface logger;
    private static HashMap<Integer, String> mActionNames;
    private static AtomicInteger mCodeGenerator;
    private static HashMap<Integer, Long> mStartTimes;

    static {
        logger = new DefaultAndroidLogger();
        mStartTimes = new HashMap();
        mActionNames = new HashMap();
        NEGATIVE_CODE = -1;
        mCodeGenerator = new AtomicInteger(1);
    }

    public static void d(String string2) {
        MyLog.log(1, string2);
    }

    public static void d(String string2, String string3) {
        MyLog.log(1, string2 + ", " + string3);
    }

    public static void e(String string2) {
        MyLog.log(4, string2);
    }

    public static void e(String string2, String string3) {
        MyLog.log(4, string2 + ", " + string3);
    }

    public static void e(String string2, String string3, Throwable throwable) {
        MyLog.log(4, string2 + ", " + string3, throwable);
    }

    public static void e(String string2, Throwable throwable) {
        MyLog.log(4, string2, throwable);
    }

    public static void e(Throwable throwable) {
        MyLog.log(4, throwable);
    }

    public static void i(String string2, String string3) {
        MyLog.log(0, string2 + ", " + string3);
    }

    public static void log(int n, String string2) {
        if (n >= LOG_LEVEL) {
            logger.log(string2);
        }
    }

    public static void log(int n, String string2, Throwable throwable) {
        if (n >= LOG_LEVEL) {
            logger.log(string2, throwable);
        }
    }

    public static void log(int n, Throwable throwable) {
        if (n >= LOG_LEVEL) {
            logger.log("", throwable);
        }
    }

    public static void setLogLevel(int n) {
        if (n < 0 || n > 5) {
            MyLog.log(2, "set log level as " + n);
        }
        LOG_LEVEL = n;
    }

    public static void setLogger(LoggerInterface loggerInterface) {
        logger = loggerInterface;
    }

    public static void v(String string2) {
        MyLog.log(1, string2);
    }

    public static void v(String string2, String string3) {
        MyLog.log(1, string2 + ", " + string3);
    }

    public static void w(String string2, String string3) {
        MyLog.log(2, string2 + ", " + string3);
    }
}

