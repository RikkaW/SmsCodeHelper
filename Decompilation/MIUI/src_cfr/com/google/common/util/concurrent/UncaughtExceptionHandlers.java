/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.Runtime
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.logging.Level
 *  java.util.logging.Logger
 */
package com.google.common.util.concurrent;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class UncaughtExceptionHandlers {
    private UncaughtExceptionHandlers() {
    }

    static final class Exiter
    implements Thread.UncaughtExceptionHandler {
        private static final Logger logger = Logger.getLogger((String)Exiter.class.getName());
        private final Runtime runtime;

        @Override
        public void uncaughtException(Thread thread, Throwable throwable) {
            logger.log(Level.SEVERE, String.format((String)"Caught an exception in %s.  Shutting down.", (Object[])new Object[]{thread}), throwable);
            this.runtime.exit(1);
        }
    }

}

