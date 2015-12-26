/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.os.AsyncTask
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.lang.Object
 */
package com.xiaomi.mms.mx.common;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import com.xiaomi.mms.mx.common.CommonApplication;
import java.util.concurrent.Executor;

@SuppressLint(value={"NewApi"})
public abstract class AsyncTaskUtils {
    public static /* varargs */ <Params, Progress, Result> void exe(int n, AsyncTask<Params, Progress, Result> asyncTask, Params ... arrParams) {
        if (Build.VERSION.SDK_INT >= 11 && CommonApplication.getExecutorByLevel(n) != null) {
            asyncTask.executeOnExecutor(CommonApplication.getExecutorByLevel(n), (Object[])arrParams);
            return;
        }
        asyncTask.execute((Object[])arrParams);
    }
}

