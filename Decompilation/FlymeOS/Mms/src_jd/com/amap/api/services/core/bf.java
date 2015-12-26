package com.amap.api.services.core;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

public class bf
{
  static final String a = "/a/";
  static final String b = "b";
  static final String c = "c";
  static final String d = "d";
  
  static void a(Context paramContext)
  {
    try
    {
      bi localbi = bi.a(2);
      if (localbi == null) {
        return;
      }
      localbi.a(paramContext);
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
      ExecutorService localExecutorService = ay.a();
      if (localExecutorService != null)
      {
        if (localExecutorService.isShutdown()) {
          return;
        }
        localExecutorService.submit(new bf.1(paramInt, paramContext, paramThrowable, paramString1, paramString2));
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
      ExecutorService localExecutorService = ay.a();
      if (localExecutorService != null)
      {
        if (localExecutorService.isShutdown()) {
          return;
        }
        localExecutorService.submit(new bf.2(paramContext));
        return;
      }
    }
    catch (Throwable paramContext)
    {
      ay.a(paramContext, "Log", "processLog");
      paramContext.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.bf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */