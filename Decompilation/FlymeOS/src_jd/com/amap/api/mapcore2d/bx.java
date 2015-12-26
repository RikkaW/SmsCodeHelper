package com.amap.api.mapcore2d;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

class bx<T>
{
  protected LinkedList<T> a = new LinkedList();
  protected final Semaphore b = new Semaphore(0, false);
  protected boolean c = true;
  
  public ArrayList<T> a(int paramInt, boolean paramBoolean)
  {
    if (a == null) {}
    for (;;)
    {
      return null;
      try
      {
        b.acquire();
        try
        {
          if (!c) {
            continue;
          }
          ArrayList localArrayList = b(paramInt, paramBoolean);
          return localArrayList;
        }
        catch (Exception localException)
        {
          return null;
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
    }
  }
  
  public void a()
  {
    c = false;
    b.release(100);
  }
  
  /* Error */
  public void a(java.util.List<T> paramList, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 21	com/amap/api/mapcore2d/bx:a	Ljava/util/LinkedList;
    //   6: astore_3
    //   7: aload_3
    //   8: ifnonnull +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: iload_2
    //   15: iconst_1
    //   16: if_icmpne +10 -> 26
    //   19: aload_0
    //   20: getfield 21	com/amap/api/mapcore2d/bx:a	Ljava/util/LinkedList;
    //   23: invokevirtual 51	java/util/LinkedList:clear	()V
    //   26: aload_1
    //   27: ifnull +12 -> 39
    //   30: aload_0
    //   31: getfield 21	com/amap/api/mapcore2d/bx:a	Ljava/util/LinkedList;
    //   34: aload_1
    //   35: invokevirtual 55	java/util/LinkedList:addAll	(Ljava/util/Collection;)Z
    //   38: pop
    //   39: aload_0
    //   40: invokevirtual 57	com/amap/api/mapcore2d/bx:b	()V
    //   43: goto -32 -> 11
    //   46: astore_1
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_1
    //   50: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	51	0	this	bx
    //   0	51	1	paramList	java.util.List<T>
    //   0	51	2	paramBoolean	boolean
    //   6	2	3	localLinkedList	LinkedList
    // Exception table:
    //   from	to	target	type
    //   2	7	46	finally
    //   19	26	46	finally
    //   30	39	46	finally
    //   39	43	46	finally
  }
  
  /* Error */
  protected ArrayList<T> b(int paramInt, boolean paramBoolean)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: getfield 21	com/amap/api/mapcore2d/bx:a	Ljava/util/LinkedList;
    //   9: astore 6
    //   11: aload 6
    //   13: ifnonnull +11 -> 24
    //   16: aconst_null
    //   17: astore 6
    //   19: aload_0
    //   20: monitorexit
    //   21: aload 6
    //   23: areturn
    //   24: aload_0
    //   25: getfield 21	com/amap/api/mapcore2d/bx:a	Ljava/util/LinkedList;
    //   28: invokevirtual 62	java/util/LinkedList:size	()I
    //   31: istore 5
    //   33: iload_1
    //   34: istore_3
    //   35: iload_1
    //   36: iload 5
    //   38: if_icmple +6 -> 44
    //   41: iload 5
    //   43: istore_3
    //   44: new 64	java/util/ArrayList
    //   47: dup
    //   48: iload_3
    //   49: invokespecial 66	java/util/ArrayList:<init>	(I)V
    //   52: astore 6
    //   54: iload 4
    //   56: istore_1
    //   57: iload_1
    //   58: iload_3
    //   59: if_icmpge +32 -> 91
    //   62: aload 6
    //   64: aload_0
    //   65: getfield 21	com/amap/api/mapcore2d/bx:a	Ljava/util/LinkedList;
    //   68: iconst_0
    //   69: invokevirtual 70	java/util/LinkedList:get	(I)Ljava/lang/Object;
    //   72: invokevirtual 74	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   75: pop
    //   76: aload_0
    //   77: getfield 21	com/amap/api/mapcore2d/bx:a	Ljava/util/LinkedList;
    //   80: invokevirtual 78	java/util/LinkedList:removeFirst	()Ljava/lang/Object;
    //   83: pop
    //   84: iload_1
    //   85: iconst_1
    //   86: iadd
    //   87: istore_1
    //   88: goto -31 -> 57
    //   91: aload_0
    //   92: invokevirtual 57	com/amap/api/mapcore2d/bx:b	()V
    //   95: goto -76 -> 19
    //   98: astore 6
    //   100: aload_0
    //   101: monitorexit
    //   102: aload 6
    //   104: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	105	0	this	bx
    //   0	105	1	paramInt	int
    //   0	105	2	paramBoolean	boolean
    //   34	26	3	i	int
    //   1	54	4	j	int
    //   31	11	5	k	int
    //   9	54	6	localObject1	Object
    //   98	5	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   5	11	98	finally
    //   24	33	98	finally
    //   44	54	98	finally
    //   62	84	98	finally
    //   91	95	98	finally
  }
  
  protected void b()
  {
    if (a == null) {}
    while ((!c) || (a.size() == 0)) {
      return;
    }
    b.release();
  }
  
  public void c()
  {
    if (a == null) {
      return;
    }
    a.clear();
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */