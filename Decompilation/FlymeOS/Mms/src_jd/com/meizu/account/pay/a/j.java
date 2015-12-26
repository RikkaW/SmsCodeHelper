package com.meizu.account.pay.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;

public final class j
  implements ServiceConnection
{
  private Context a;
  private IInterface b;
  private String c;
  private String d;
  private k e;
  
  public j(Context paramContext, k paramk, String paramString1, String paramString2)
  {
    a = paramContext;
    e = paramk;
    c = paramString1;
    d = paramString2;
  }
  
  /* Error */
  public final IInterface a()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 34	com/meizu/account/pay/a/j:b	Landroid/os/IInterface;
    //   6: ifnonnull +80 -> 86
    //   9: new 36	android/content/Intent
    //   12: dup
    //   13: invokespecial 37	android/content/Intent:<init>	()V
    //   16: astore_1
    //   17: aload_1
    //   18: aload_0
    //   19: getfield 26	com/meizu/account/pay/a/j:c	Ljava/lang/String;
    //   22: invokevirtual 41	android/content/Intent:setAction	(Ljava/lang/String;)Landroid/content/Intent;
    //   25: pop
    //   26: aload_1
    //   27: aload_0
    //   28: getfield 28	com/meizu/account/pay/a/j:d	Ljava/lang/String;
    //   31: invokevirtual 44	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   34: pop
    //   35: aload_0
    //   36: getfield 22	com/meizu/account/pay/a/j:a	Landroid/content/Context;
    //   39: aload_1
    //   40: aload_0
    //   41: iconst_1
    //   42: invokevirtual 50	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   45: ifne +34 -> 79
    //   48: ldc 52
    //   50: new 54	java/lang/StringBuilder
    //   53: dup
    //   54: ldc 56
    //   56: invokespecial 59	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   59: aload_0
    //   60: getfield 26	com/meizu/account/pay/a/j:c	Ljava/lang/String;
    //   63: invokevirtual 63	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   69: invokestatic 72	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   72: pop
    //   73: aconst_null
    //   74: astore_1
    //   75: aload_0
    //   76: monitorexit
    //   77: aload_1
    //   78: areturn
    //   79: aload_0
    //   80: ldc2_w 73
    //   83: invokevirtual 78	java/lang/Object:wait	(J)V
    //   86: aload_0
    //   87: getfield 34	com/meizu/account/pay/a/j:b	Landroid/os/IInterface;
    //   90: astore_1
    //   91: goto -16 -> 75
    //   94: astore_1
    //   95: aload_1
    //   96: invokevirtual 81	java/lang/InterruptedException:printStackTrace	()V
    //   99: goto -13 -> 86
    //   102: astore_1
    //   103: aload_0
    //   104: monitorexit
    //   105: aload_1
    //   106: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	107	0	this	j
    //   16	75	1	localObject1	Object
    //   94	2	1	localInterruptedException	InterruptedException
    //   102	4	1	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   79	86	94	java/lang/InterruptedException
    //   2	73	102	finally
    //   79	86	102	finally
    //   86	91	102	finally
    //   95	99	102	finally
  }
  
  public final void b()
  {
    try
    {
      if (b != null)
      {
        a.unbindService(this);
        b = null;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    Log.d("ServiceBindHelper", "get service.");
    b = ((IInterface)e.a(paramIBinder));
    try
    {
      notifyAll();
      return;
    }
    finally
    {
      paramComponentName = finally;
      throw paramComponentName;
    }
  }
  
  public final void onServiceDisconnected(ComponentName paramComponentName)
  {
    Log.w("ServiceBindHelper", "lost service.");
    b = null;
  }
}

/* Location:
 * Qualified Name:     com.meizu.account.pay.a.j
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */