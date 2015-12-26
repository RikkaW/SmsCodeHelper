/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.PrintStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.logging.Logger
 */
package com.xiaomi.common;

import java.io.PrintStream;
import java.util.logging.Logger;

public class Log {
    public static boolean localTest = false;

    public static void i(String string2, String string3) {
        if (localTest) {
            Logger.getLogger((String)string2).info(string3);
        }
    }

    public static void println(String string2) {
        if (localTest) {
            System.err.println(string2);
        }
    }

    public static void setLocalTest(boolean bl) {
        localTest = bl;
    }
}

