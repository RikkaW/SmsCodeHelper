/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.google.common.base;

import com.google.common.base.Platform;

public abstract class Ticker {
    private static final Ticker SYSTEM_TICKER = new Ticker(){

        @Override
        public long read() {
            return Platform.systemNanoTime();
        }
    };

    protected Ticker() {
    }

    public static Ticker systemTicker() {
        return SYSTEM_TICKER;
    }

    public abstract long read();

}

