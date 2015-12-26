package com.amap.api.mapcore2d;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

public class ek
{
  static final String a = "/a/";
  static final String b = "b";
  static final String c = "c";
  static final String d = "d";
  
  static void a(Context paramContext)
  {
    try
    {
      en localen = en.a(2);
      if (localen == null) {
        return;
      }
      localen.a(paramContext);
      return;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  static void a(Context paramContext, Throwable paramThrowable, int paramInt, String paramString1, String paramString2)
  {
    try
    {
      ExecutorService localExecutorService = ed.a();
      if (localExecutorService != null)
      {
        if (localExecutorService.isShutdown()) {
          return;
        }
        localExecutorService.submit(new ek.1(paramInt, paramContext, paramThrowable, paramString1, paramString2));
        return;
      }
    }
    catch (RejectedExecutionException paramContext) {}catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  static void b(Context paramContext)
  {
    try
    {
      ExecutorService localExecutorService = ed.a();
      if (localExecutorService != null)
      {
        if (localExecutorService.isShutdown()) {
          return;
        }
        localExecutorService.submit(new ek.2(paramContext));
        return;
      }
    }
    catch (Throwable paramContext)
    {
      ed.a(paramContext, "Log", "processLog");
      paramContext.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ek
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */