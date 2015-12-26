package com.xiaomi.mms.mx.common;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import com.xiaomi.mms.mx.cache.ImageCache.ImageCacheParams;
import com.xiaomi.mms.mx.cache.ImageCacheManager;
import com.xiaomi.mms.mx.utils.Log;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CommonApplication
{
  private static ThreadPoolExecutor[] executors = new ThreadPoolExecutor[3];
  protected static Handler sGlobalHandler;
  
  public static Executor getExecutorByLevel(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 2)) {
      throw new IllegalArgumentException("wrong level");
    }
    return executors[paramInt];
  }
  
  private static void initCache()
  {
    int i = Math.min(Math.max(((ActivityManager)GlobalData.app().getSystemService("activity")).getMemoryClass() * 1024 * 1024 / 8, 3145728), 12582912);
    Log.v("CommonApplication", "the memory cache is initialized to be " + i / 1024 / 1024);
    ImageCache.ImageCacheParams localImageCacheParams = new ImageCache.ImageCacheParams("common_image_cache", i);
    ImageCacheManager.get(GlobalData.app(), localImageCacheParams);
  }
  
  public static void initialize(Context paramContext)
  {
    GlobalData.initialize(paramContext);
    if (sGlobalHandler == null) {
      sGlobalHandler = new Handler();
    }
    initializeThreadPool();
    initCache();
  }
  
  private static void initializeThreadPool()
  {
    RejectedExecutionHandler local3 = new RejectedExecutionHandler()
    {
      public void rejectedExecution(Runnable paramAnonymousRunnable, ThreadPoolExecutor paramAnonymousThreadPoolExecutor)
      {
        Log.v("CommonApplication", "Thread pool executor: reject work, put into backup pool");
        val$backupExe.execute(paramAnonymousRunnable);
      }
    };
    executors[0] = new ThreadPoolExecutor(3, 8, 60L, TimeUnit.SECONDS, new SynchronousQueue(), local3);
    executors[1] = new ThreadPoolExecutor(3, 8, 60L, TimeUnit.SECONDS, new SynchronousQueue(), local3);
    executors[2] = new ThreadPoolExecutor(3, 8, 60L, TimeUnit.SECONDS, new SynchronousQueue(), local3);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.common.CommonApplication
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */