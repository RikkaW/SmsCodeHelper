package com.android.mms;

import aaq.a;
import android.os.Handler;
import fv;
import fw;

public final class MmsApp$f
  implements aaq.a
{
  private Handler a;
  private MmsApp.e b;
  private final Runnable c = new fv(this);
  private final Runnable d = new fw(this);
  
  public MmsApp$f(Handler paramHandler)
  {
    a = paramHandler;
  }
  
  private void a(Runnable paramRunnable, long paramLong)
  {
    if (a != null) {
      a.postDelayed(paramRunnable, paramLong);
    }
  }
  
  public void a()
  {
    try
    {
      MmsApp.b(true);
      c();
      if ((b != null) && (b.b())) {
        a(c, 500L);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void a(MmsApp.e parame)
  {
    b = parame;
  }
  
  public void b()
  {
    try
    {
      MmsApp.b(false);
      c();
      if ((b != null) && (b.b())) {
        a(d, 500L);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public void c()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 35	com/android/mms/MmsApp$f:a	Landroid/os/Handler;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnonnull +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: getfield 35	com/android/mms/MmsApp$f:a	Landroid/os/Handler;
    //   18: aload_0
    //   19: getfield 28	com/android/mms/MmsApp$f:c	Ljava/lang/Runnable;
    //   22: invokevirtual 65	android/os/Handler:removeCallbacks	(Ljava/lang/Runnable;)V
    //   25: aload_0
    //   26: getfield 35	com/android/mms/MmsApp$f:a	Landroid/os/Handler;
    //   29: aload_0
    //   30: getfield 33	com/android/mms/MmsApp$f:d	Ljava/lang/Runnable;
    //   33: invokevirtual 65	android/os/Handler:removeCallbacks	(Ljava/lang/Runnable;)V
    //   36: goto -25 -> 11
    //   39: astore_1
    //   40: aload_0
    //   41: monitorexit
    //   42: aload_1
    //   43: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	44	0	this	f
    //   6	2	1	localHandler	Handler
    //   39	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	39	finally
    //   14	36	39	finally
  }
}

/* Location:
 * Qualified Name:     com.android.mms.MmsApp.f
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */