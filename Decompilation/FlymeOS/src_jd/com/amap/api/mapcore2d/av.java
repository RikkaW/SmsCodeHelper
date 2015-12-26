package com.amap.api.mapcore2d;

class av
  extends bw<n.a>
{
  void a(n.a parama)
  {
    try
    {
      remove(parama);
      return;
    }
    finally
    {
      parama = finally;
      throw parama;
    }
  }
  
  /* Error */
  boolean b(n.a parama)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: aload_1
    //   6: invokevirtual 21	com/amap/api/mapcore2d/av:contains	(Ljava/lang/Object;)Z
    //   9: istore_3
    //   10: iload_3
    //   11: iconst_1
    //   12: if_icmpne +9 -> 21
    //   15: iconst_0
    //   16: istore_2
    //   17: aload_0
    //   18: monitorexit
    //   19: iload_2
    //   20: ireturn
    //   21: aload_0
    //   22: aload_1
    //   23: invokevirtual 24	com/amap/api/mapcore2d/av:a	(Ljava/lang/Object;)V
    //   26: goto -9 -> 17
    //   29: astore_1
    //   30: aload_0
    //   31: monitorexit
    //   32: aload_1
    //   33: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	34	0	this	av
    //   0	34	1	parama	n.a
    //   1	19	2	bool1	boolean
    //   9	4	3	bool2	boolean
    // Exception table:
    //   from	to	target	type
    //   4	10	29	finally
    //   21	26	29	finally
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.av
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */