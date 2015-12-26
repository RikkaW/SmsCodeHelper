/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.concurrent.atomic.AtomicInteger
 */
package com.amap.api.mapcore2d;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class cr
implements ThreadFactory {
    private final AtomicInteger a = new AtomicInteger(1);

    cr() {
    }

    @Override
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, "AsyncTask #" + this.a.getAndIncrement());
    }
}

