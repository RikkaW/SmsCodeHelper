/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.concurrent.atomic.AtomicInteger
 */
package android.support.v4.content;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class ModernAsyncTask$1
implements ThreadFactory {
    private final AtomicInteger mCount = new AtomicInteger(1);

    ModernAsyncTask$1() {
    }

    @Override
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, "ModernAsyncTask #" + this.mCount.getAndIncrement());
    }
}

