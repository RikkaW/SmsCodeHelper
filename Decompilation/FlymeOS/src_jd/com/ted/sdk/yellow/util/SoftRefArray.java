package com.ted.sdk.yellow.util;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class SoftRefArray<E>
{
  ArrayList<SoftReference<E>> mList = new ArrayList();
  
  private int doFind(E paramE)
  {
    int i = mList.size() - 1;
    for (;;)
    {
      if (i < 0) {
        return -1;
      }
      if (paramE == ((SoftReference)mList.get(i)).get()) {
        return i;
      }
      i -= 1;
    }
  }
  
  public void add(E paramE)
  {
    try
    {
      if (doFind(paramE) == -1) {
        mList.add(new SoftReference(paramE));
      }
      return;
    }
    finally
    {
      paramE = finally;
      throw paramE;
    }
  }
  
  /* Error */
  public void compress()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 17	com/ted/sdk/yellow/util/SoftRefArray:mList	Ljava/util/ArrayList;
    //   6: invokevirtual 24	java/util/ArrayList:size	()I
    //   9: istore_1
    //   10: iload_1
    //   11: iconst_1
    //   12: isub
    //   13: istore_1
    //   14: iload_1
    //   15: ifge +6 -> 21
    //   18: aload_0
    //   19: monitorexit
    //   20: return
    //   21: aload_0
    //   22: getfield 17	com/ted/sdk/yellow/util/SoftRefArray:mList	Ljava/util/ArrayList;
    //   25: iload_1
    //   26: invokevirtual 28	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   29: checkcast 30	java/lang/ref/SoftReference
    //   32: invokevirtual 33	java/lang/ref/SoftReference:get	()Ljava/lang/Object;
    //   35: ifnonnull +12 -> 47
    //   38: aload_0
    //   39: getfield 17	com/ted/sdk/yellow/util/SoftRefArray:mList	Ljava/util/ArrayList;
    //   42: iload_1
    //   43: invokevirtual 49	java/util/ArrayList:remove	(I)Ljava/lang/Object;
    //   46: pop
    //   47: iload_1
    //   48: iconst_1
    //   49: isub
    //   50: istore_1
    //   51: goto -37 -> 14
    //   54: astore_2
    //   55: aload_0
    //   56: monitorexit
    //   57: aload_2
    //   58: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	59	0	this	SoftRefArray
    //   9	42	1	i	int
    //   54	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	10	54	finally
    //   21	47	54	finally
  }
  
  public E get(int paramInt)
  {
    try
    {
      Object localObject1 = ((SoftReference)mList.get(paramInt)).get();
      return (E)localObject1;
    }
    finally
    {
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
  }
  
  public void remove(E paramE)
  {
    try
    {
      int i = doFind(paramE);
      if (i >= 0) {
        mList.remove(i);
      }
      return;
    }
    finally {}
  }
  
  public int size()
  {
    try
    {
      int i = mList.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.util.SoftRefArray
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */