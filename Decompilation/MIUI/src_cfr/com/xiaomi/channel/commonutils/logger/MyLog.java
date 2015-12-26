/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.HashMap
 *  java.util.concurrent.atomic.AtomicInteger
 */
package com.xiaomi.channel.commonutils.logger;

import com.xiaomi.channel.commonutils.logger.DefaultAndroidLogger;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class MyLog {
    private static int LOG_LEVEL = 2;
    private static final Integer NEGATIVE_CODE;
    private static LoggerInterface logger;
    private static final HashMap<Integer, String> mActionNames;
    private static AtomicInteger mCodeGenerator;
    private static final HashMap<Integer, Long> mStartTimes;

    static {
        logger = new DefaultAndroidLogger();
        mStartTimes = new HashMap();
        mActionNames = new HashMap();
        NEGATIVE_CODE = -1;
        mCodeGenerator = new AtomicInteger(1);
    }

    public static void e(String string2) {
        MyLog.log(4, string2);
    }

    public static void e(String string2, Throwable throwable) {
        MyLog.log(4, string2, throwable);
    }

    public static void e(Throwable throwable) {
        MyLog.log(4, throwable);
    }

    public static void info(String string2) {
        MyLog.log(0, string2);
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

    public static void pe(Integer object) {
        if (LOG_LEVEL > 1 || !mStartTimes.containsKey(object)) {
            return;
        }
        long l = (Long)mStartTimes.remove(object);
        object = (String)mActionNames.remove(object);
        long l2 = System.currentTimeMillis();
        logger.log((String)object + " ends in " + (l2 - l) + " ms");
    }

    public static Integer ps(String string2) {
        if (LOG_LEVEL <= 1) {
            Integer n = mCodeGenerator.incrementAndGet();
            mStartTimes.put((Object)n, (Object)System.currentTimeMillis());
            mActionNames.put((Object)n, (Object)string2);
            logger.log(string2 + " starts");
            return n;
        }
        return NEGATIVE_CODE;
    }

    public static void v(String string2) {
        MyLog.log(1, "[Thread:" + Thread.currentThread().getId() + "] " + string2);
    }

    public static void warn(String string2) {
        MyLog.log(2, "[Thread:" + Thread.currentThread().getId() + "] " + string2);
    }
}

