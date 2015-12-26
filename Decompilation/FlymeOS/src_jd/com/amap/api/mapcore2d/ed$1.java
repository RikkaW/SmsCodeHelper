package com.amap.api.mapcore2d;

import android.content.Context;

class ed$1
  implements Runnable
{
  ed$1(ed paramed, Context paramContext, dh paramdh, boolean paramBoolean) {}
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: invokestatic 37	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   3: astore_1
    //   4: aload_1
    //   5: monitorenter
    //   6: new 39	com/amap/api/mapcore2d/ds
    //   9: dup
    //   10: aload_0
    //   11: getfield 20	com/amap/api/mapcore2d/ed$1:a	Landroid/content/Context;
    //   14: invokespecial 42	com/amap/api/mapcore2d/ds:<init>	(Landroid/content/Context;)V
    //   17: aload_0
    //   18: getfield 22	com/amap/api/mapcore2d/ed$1:b	Lcom/amap/api/mapcore2d/dh;
    //   21: invokevirtual 45	com/amap/api/mapcore2d/ds:a	(Lcom/amap/api/mapcore2d/dh;)V
    //   24: aload_1
    //   25: monitorexit
    //   26: aload_0
    //   27: getfield 24	com/amap/api/mapcore2d/ed$1:c	Z
    //   30: ifeq +61 -> 91
    //   33: invokestatic 37	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   36: astore_1
    //   37: aload_1
    //   38: monitorenter
    //   39: new 47	com/amap/api/mapcore2d/dv
    //   42: dup
    //   43: aload_0
    //   44: getfield 20	com/amap/api/mapcore2d/ed$1:a	Landroid/content/Context;
    //   47: invokespecial 48	com/amap/api/mapcore2d/dv:<init>	(Landroid/content/Context;)V
    //   50: astore_2
    //   51: new 50	com/amap/api/mapcore2d/dx
    //   54: dup
    //   55: invokespecial 51	com/amap/api/mapcore2d/dx:<init>	()V
    //   58: astore_3
    //   59: aload_3
    //   60: iconst_1
    //   61: invokevirtual 54	com/amap/api/mapcore2d/dx:c	(Z)V
    //   64: aload_3
    //   65: iconst_1
    //   66: invokevirtual 56	com/amap/api/mapcore2d/dx:a	(Z)V
    //   69: aload_3
    //   70: iconst_1
    //   71: invokevirtual 58	com/amap/api/mapcore2d/dx:b	(Z)V
    //   74: aload_2
    //   75: aload_3
    //   76: invokevirtual 61	com/amap/api/mapcore2d/dv:a	(Lcom/amap/api/mapcore2d/dx;)V
    //   79: aload_1
    //   80: monitorexit
    //   81: aload_0
    //   82: getfield 18	com/amap/api/mapcore2d/ed$1:d	Lcom/amap/api/mapcore2d/ed;
    //   85: invokestatic 66	com/amap/api/mapcore2d/ed:a	(Lcom/amap/api/mapcore2d/ed;)Landroid/content/Context;
    //   88: invokestatic 70	com/amap/api/mapcore2d/ek:a	(Landroid/content/Context;)V
    //   91: return
    //   92: astore_2
    //   93: aload_1
    //   94: monitorexit
    //   95: aload_2
    //   96: athrow
    //   97: astore_1
    //   98: aload_1
    //   99: invokevirtual 73	java/lang/Throwable:printStackTrace	()V
    //   102: return
    //   103: astore_2
    //   104: aload_1
    //   105: monitorexit
    //   106: aload_2
    //   107: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	this	1
    //   97	8	1	localThrowable	Throwable
    //   50	25	2	localdv	dv
    //   92	4	2	localObject1	Object
    //   103	4	2	localObject2	Object
    //   58	18	3	localdx	dx
    // Exception table:
    //   from	to	target	type
    //   6	26	92	finally
    //   93	95	92	finally
    //   0	6	97	java/lang/Throwable
    //   26	39	97	java/lang/Throwable
    //   81	91	97	java/lang/Throwable
    //   95	97	97	java/lang/Throwable
    //   106	108	97	java/lang/Throwable
    //   39	81	103	finally
    //   104	106	103	finally
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ed.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */