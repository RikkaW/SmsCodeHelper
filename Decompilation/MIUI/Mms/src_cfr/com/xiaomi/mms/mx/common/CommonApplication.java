/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.ActivityManager
 *  android.content.Context
 *  android.os.Handler
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.util.concurrent.LinkedBlockingQueue
 *  java.util.concurrent.ThreadPoolExecutor
 *  java.util.concurrent.TimeUnit
 */
package com.xiaomi.mms.mx.common;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import com.xiaomi.mms.mx.cache.ImageCache;
import com.xiaomi.mms.mx.cache.ImageCacheManager;
import com.xiaomi.mms.mx.common.GlobalData;
import com.xiaomi.mms.mx.utils.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CommonApplication {
    private static ThreadPoolExecutor[] executors = new ThreadPoolExecutor[3];
    protected static Handler sGlobalHandler;

    public static Executor getExecutorByLevel(int n) {
        if (n < 0 || n > 2) {
            throw new IllegalArgumentException("wrong level");
        }
        return executors[n];
    }

    private static void initCache() {
        int n = Math.min((int)Math.max((int)(((ActivityManager)GlobalData.app().getSystemService("activity")).getMemoryClass() * 1024 * 1024 / 8), (int)3145728), (int)12582912);
        Log.v("CommonApplication", "the memory cache is initialized to be " + n / 1024 / 1024);
        ImageCache.ImageCacheParams imageCacheParams = new ImageCache.ImageCacheParams("common_image_cache", n);
        ImageCacheManager.get(GlobalData.app(), imageCacheParams);
    }

    public static void initialize(Context context) {
        GlobalData.initialize(context);
        if (sGlobalHandler == null) {
            sGlobalHandler = new Handler();
        }
        CommonApplication.initializeThreadPool();
        CommonApplication.initCache();
    }

    private static void initializeThreadPool() {
        RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler(new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, (BlockingQueue)new LinkedBlockingQueue())){
            final /* synthetic */ ThreadPoolExecutor val$backupExe;

            @Override
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                Log.v("CommonApplication", "Thread pool executor: reject work, put into backup pool");
                this.val$backupExe.execute(runnable);
            }
        };
        CommonApplication.executors[0] = new ThreadPoolExecutor(3, 8, 60, TimeUnit.SECONDS, new SynchronousQueue(), rejectedExecutionHandler);
        CommonApplication.executors[1] = new ThreadPoolExecutor(3, 8, 60, TimeUnit.SECONDS, new SynchronousQueue(), rejectedExecutionHandler);
        CommonApplication.executors[2] = new ThreadPoolExecutor(3, 8, 60, TimeUnit.SECONDS, new SynchronousQueue(), rejectedExecutionHandler);
    }

}

