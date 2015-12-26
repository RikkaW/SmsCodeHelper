/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 */
import android.util.Log;

class apt
implements Thread.UncaughtExceptionHandler {
    private static final String a = apt.class.getSimpleName();

    apt() {
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        Log.e((String)a, (String)("caught: " + (Object)throwable));
        throwable.printStackTrace();
    }
}

