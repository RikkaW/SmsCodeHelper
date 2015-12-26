/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.Thread
 */
import java.util.concurrent.ThreadFactory;

class aps
implements ThreadFactory {
    aps() {
    }

    @Override
    public Thread newThread(Runnable runnable) {
        runnable = new Thread(runnable);
        runnable.setUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new apt());
        return runnable;
    }
}

