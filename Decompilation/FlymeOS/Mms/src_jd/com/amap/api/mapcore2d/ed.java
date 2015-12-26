package com.amap.api.mapcore2d;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class ed
  implements Thread.UncaughtExceptionHandler
{
  private static ed a;
  private static ExecutorService e;
  private Thread.UncaughtExceptionHandler b;
  private Context c;
  private boolean d = true;
  
  private ed(Context paramContext, dh paramdh)
  {
    c = paramContext;
    ev.a(new a(paramContext));
    d();
  }
  
  public static ed a(Context paramContext, dh paramdh)
  {
    if (paramdh == null) {
      try
      {
        throw new cz("sdk info is null");
      }
      finally {}
    }
    if ((paramdh.a() == null) || ("".equals(paramdh.a()))) {
      throw new cz("sdk name is invalid");
    }
    for (;;)
    {
      try
      {
        if (a != null) {
          continue;
        }
        a = new ed(paramContext, paramdh);
        a.a(paramContext, paramdh, ad);
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
  
  private void a(Context paramContext, dh paramdh, boolean paramBoolean)
  {
    try
    {
      ExecutorService localExecutorService = a();
      if (localExecutorService != null)
      {
        if (localExecutorService.isShutdown()) {
          return;
        }
        localExecutorService.submit(new ed.1(this, paramContext, paramdh, paramBoolean));
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
    ek.a(c, paramThrowable, paramInt, paramString1, paramString2);
  }
  
  public static void a(Throwable paramThrowable, String paramString1, String paramString2)
  {
    if (a != null) {
      a.a(paramThrowable, 1, paramString1, paramString2);
    }
  }
  
  public static ed b()
  {
    try
    {
      ed localed = a;
      return localed;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static void c()
  {
    try
    {
      if (e != null) {
        e.shutdown();
      }
      try
      {
        if ((a != null) && (Thread.getDefaultUncaughtExceptionHandler() == a) && (ab != null)) {
          Thread.setDefaultUncaughtExceptionHandler(ab);
        }
        a = null;
      }
      catch (Throwable localThrowable2)
      {
        for (;;)
        {
          localThrowable2.printStackTrace();
        }
      }
      return;
    }
    catch (Throwable localThrowable1)
    {
      for (;;)
      {
        localThrowable1.printStackTrace();
      }
    }
    finally {}
  }
  
  private void d()
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
    implements ew
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
        ek.b(a);
        return;
      }
      catch (Throwable localThrowable)
      {
        ed.a(localThrowable, "LogNetListener", "onNetCompleted");
        localThrowable.printStackTrace();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ed
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */