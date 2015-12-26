package com.amap.api.mapcore2d;

import android.os.Handler;
import android.os.Message;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class cq<Params, Progress, Result>
{
  private static final ThreadFactory a = new cr();
  static final Executor b;
  private static final BlockingQueue<Runnable> c = new LinkedBlockingQueue(10);
  private static final Executor d = new ThreadPoolExecutor(5, 128, 1L, TimeUnit.SECONDS, c, a, new ThreadPoolExecutor.DiscardOldestPolicy());
  private static final Executor e;
  private static final b f;
  private static volatile Executor g;
  private final e<Params, Result> h = new cq.1(this);
  private final FutureTask<Result> i = new cq.2(this, h);
  private volatile d j = d.a;
  private final AtomicBoolean k = new AtomicBoolean();
  private final AtomicBoolean l = new AtomicBoolean();
  
  static
  {
    if (cy.c()) {}
    for (Object localObject = new c(null);; localObject = Executors.newSingleThreadExecutor(a))
    {
      e = (Executor)localObject;
      b = Executors.newFixedThreadPool(2, a);
      f = new b(null);
      g = e;
      return;
    }
  }
  
  private void c(Result paramResult)
  {
    if (!l.get()) {
      d(paramResult);
    }
  }
  
  private Result d(Result paramResult)
  {
    f.obtainMessage(1, new a(this, new Object[] { paramResult })).sendToTarget();
    return paramResult;
  }
  
  private void e() {}
  
  private void e(Result paramResult)
  {
    if (c()) {
      b(paramResult);
    }
    for (;;)
    {
      j = d.c;
      return;
      a(paramResult);
    }
  }
  
  public final d a()
  {
    return j;
  }
  
  public final cq<Params, Progress, Result> a(Executor paramExecutor, Params... paramVarArgs)
  {
    if (j != d.a) {}
    switch (3.a[j.ordinal()])
    {
    default: 
      j = d.b;
      b();
      h.b = paramVarArgs;
      paramExecutor.execute(i);
      return this;
    case 1: 
      throw new IllegalStateException("Cannot execute task: the task is already running.");
    }
    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
  }
  
  protected abstract Result a(Params... paramVarArgs);
  
  protected void a(Result paramResult) {}
  
  public final boolean a(boolean paramBoolean)
  {
    k.set(true);
    return i.cancel(paramBoolean);
  }
  
  protected void b() {}
  
  protected void b(Result paramResult)
  {
    e();
  }
  
  protected void b(Progress... paramVarArgs) {}
  
  public final cq<Params, Progress, Result> c(Params... paramVarArgs)
  {
    return a(g, paramVarArgs);
  }
  
  public final boolean c()
  {
    return k.get();
  }
  
  static class a<Data>
  {
    final cq a;
    final Data[] b;
    
    a(cq paramcq, Data... paramVarArgs)
    {
      a = paramcq;
      b = paramVarArgs;
    }
  }
  
  static class b
    extends Handler
  {
    public void handleMessage(Message paramMessage)
    {
      cq.a locala = (cq.a)obj;
      try
      {
        switch (what)
        {
        case 1: 
          cq.c(a, b[0]);
          return;
        }
      }
      catch (Throwable paramMessage)
      {
        cy.a(paramMessage, "AsyncTask", "handle_handleMessage");
        return;
      }
      a.b(b);
      return;
    }
  }
  
  static class c
    implements Executor
  {
    final ArrayDeque<Runnable> a = new ArrayDeque();
    Runnable b;
    
    protected void a()
    {
      try
      {
        Runnable localRunnable = (Runnable)a.poll();
        b = localRunnable;
        if (localRunnable != null) {
          cq.d().execute(b);
        }
        return;
      }
      finally {}
    }
    
    public void execute(Runnable paramRunnable)
    {
      try
      {
        a.offer(new cq.c.1(this, paramRunnable));
        if (b == null) {
          a();
        }
        return;
      }
      finally
      {
        paramRunnable = finally;
        throw paramRunnable;
      }
    }
  }
  
  public static enum d
  {
    private d() {}
  }
  
  static abstract class e<Params, Result>
    implements Callable<Result>
  {
    Params[] b;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */