package com.amap.api.services.core;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class ay
  implements Thread.UncaughtExceptionHandler
{
  private static ay a;
  private static ExecutorService e;
  private Thread.UncaughtExceptionHandler b;
  private Context c;
  private boolean d = true;
  
  private ay(Context paramContext, ad paramad)
  {
    c = paramContext;
    bq.a(new a(paramContext));
    c();
  }
  
  public static ay a(Context paramContext, ad paramad)
  {
    if (paramad == null) {
      try
      {
        throw new v("sdk info is null");
      }
      finally {}
    }
    if ((paramad.a() == null) || ("".equals(paramad.a()))) {
      throw new v("sdk name is invalid");
    }
    for (;;)
    {
      try
      {
        if (a != null) {
          continue;
        }
        a = new ay(paramContext, paramad);
        a.a(paramContext, paramad, ad);
      }
      catch (Throwable paramContext)
      {
        paramContext.printStackTrace();
        continue;
      }
      paramContext = a;
      return paramContext;
      ad = false;
    }
  }
  
  static ExecutorService a()
  {
    try
    {
      if ((e == null) || (e.isShutdown())) {
        e = Executors.newSingleThreadExecutor();
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        ExecutorService localExecutorService;
        localThrowable.printStackTrace();
      }
    }
    finally {}
    localExecutorService = e;
    return localExecutorService;
  }
  
  private void a(Context paramContext, ad paramad, boolean paramBoolean)
  {
    try
    {
      ExecutorService localExecutorService = a();
      if (localExecutorService != null)
      {
        if (localExecutorService.isShutdown()) {
          return;
        }
        localExecutorService.submit(new ay.1(this, paramContext, paramad, paramBoolean));
        return;
      }
    }
    catch (RejectedExecutionException paramContext) {}catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private void a(Throwable paramThrowable, int paramInt, String paramString1, String paramString2)
  {
    bf.a(c, paramThrowable, paramInt, paramString1, paramString2);
  }
  
  public static void a(Throwable paramThrowable, String paramString1, String paramString2)
  {
    if (a != null) {
      a.a(paramThrowable, 1, paramString1, paramString2);
    }
  }
  
  public static ay b()
  {
    try
    {
      ay localay = a;
      return localay;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private void c()
  {
    try
    {
      b = Thread.getDefaultUncaughtExceptionHandler();
      if (b == null)
      {
        Thread.setDefaultUncaughtExceptionHandler(this);
        d = true;
        return;
      }
      if (b.toString().indexOf("com.amap.api") != -1)
      {
        d = false;
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      return;
    }
    Thread.setDefaultUncaughtExceptionHandler(this);
    d = true;
  }
  
  public void b(Throwable paramThrowable, String paramString1, String paramString2)
  {
    if (paramThrowable == null) {
      return;
    }
    try
    {
      a(paramThrowable, 1, paramString1, paramString2);
      return;
    }
    catch (Throwable paramThrowable)
    {
      paramThrowable.printStackTrace();
    }
  }
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    if (paramThrowable == null) {}
    do
    {
      return;
      a(paramThrowable, 0, null, null);
    } while (b == null);
    b.uncaughtException(paramThread, paramThrowable);
  }
  
  static class a
    implements br
  {
    private Context a;
    
    a(Context paramContext)
    {
      a = paramContext;
    }
    
    public void a()
    {
      try
      {
        bf.b(a);
        return;
      }
      catch (Throwable localThrowable)
      {
        ay.a(localThrowable, "LogNetListener", "onNetCompleted");
        localThrowable.printStackTrace();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.ay
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */