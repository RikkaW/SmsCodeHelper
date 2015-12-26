/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.AsyncTask
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.os;

import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.os.AsyncTaskCompatHoneycomb;

public class AsyncTaskCompat {
    public static /* varargs */ <Params, Progress, Result> AsyncTask<Params, Progress, Result> executeParallel(AsyncTask<Params, Progress, Result> asyncTask, Params ... arrParams) {
        if (asyncTask == null) {
            throw new IllegalArgumentException("task can not be null");
        }
        if (Build.VERSION.SDK_INT >= 11) {
            AsyncTaskCompatHoneycomb.executeParallel(asyncTask, arrParams);
            return asyncTask;
        }
        asyncTask.execute((Object[])arrParams);
        return asyncTask;
    }
}

