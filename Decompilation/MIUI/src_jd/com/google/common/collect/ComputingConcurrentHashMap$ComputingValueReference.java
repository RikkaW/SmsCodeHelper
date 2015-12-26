package com.google.common.collect;

import com.google.common.base.Function;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ExecutionException;

final class ComputingConcurrentHashMap$ComputingValueReference<K, V>
  implements MapMakerInternalMap.ValueReference<K, V>
{
  volatile MapMakerInternalMap.ValueReference<K, V> computedReference = MapMakerInternalMap.unset();
  final Function<? super K, ? extends V> computingFunction;
  
  public ComputingConcurrentHashMap$ComputingValueReference(Function<? super K, ? extends V> paramFunction)
  {
    computingFunction = paramFunction;
  }
  
  public void clear(MapMakerInternalMap.ValueReference<K, V> paramValueReference)
  {
    setValueReference(paramValueReference);
  }
  
  V compute(K paramK, int paramInt)
    throws ExecutionException
  {
    try
    {
      paramK = computingFunction.apply(paramK);
      setValueReference(new ComputingConcurrentHashMap.ComputedReference(paramK));
      return paramK;
    }
    catch (Throwable paramK)
    {
      setValueReference(new ComputingConcurrentHashMap.ComputationExceptionReference(paramK));
      throw new ExecutionException(paramK);
    }
  }
  
  public MapMakerInternalMap.ValueReference<K, V> copyFor(ReferenceQueue<V> paramReferenceQueue, MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    return this;
  }
  
  public V get()
  {
    return null;
  }
  
  public MapMakerInternalMap.ReferenceEntry<K, V> getEntry()
  {
    return null;
  }
  
  public boolean isComputingReference()
  {
    return true;
  }
  
  void setValueReference(MapMakerInternalMap.ValueReference<K, V> paramValueReference)
  {
    try
    {
      if (computedReference == MapMakerInternalMap.UNSET)
      {
        computedReference = paramValueReference;
        notifyAll();
      }
      return;
    }
    finally {}
  }
  
  /* Error */
  public V waitForValue()
    throws ExecutionException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 29	com/google/common/collect/ComputingConcurrentHashMap$ComputingValueReference:computedReference	Lcom/google/common/collect/MapMakerInternalMap$ValueReference;
    //   4: getstatic 79	com/google/common/collect/MapMakerInternalMap:UNSET	Lcom/google/common/collect/MapMakerInternalMap$ValueReference;
    //   7: if_acmpne +52 -> 59
    //   10: iconst_0
    //   11: istore_1
    //   12: iconst_0
    //   13: istore_2
    //   14: aload_0
    //   15: monitorenter
    //   16: iload_2
    //   17: istore_1
    //   18: aload_0
    //   19: getfield 29	com/google/common/collect/ComputingConcurrentHashMap$ComputingValueReference:computedReference	Lcom/google/common/collect/MapMakerInternalMap$ValueReference;
    //   22: astore_3
    //   23: getstatic 79	com/google/common/collect/MapMakerInternalMap:UNSET	Lcom/google/common/collect/MapMakerInternalMap$ValueReference;
    //   26: astore 4
    //   28: aload_3
    //   29: aload 4
    //   31: if_acmpne +16 -> 47
    //   34: aload_0
    //   35: invokevirtual 88	java/lang/Object:wait	()V
    //   38: goto -20 -> 18
    //   41: astore_3
    //   42: iconst_1
    //   43: istore_1
    //   44: goto -26 -> 18
    //   47: aload_0
    //   48: monitorexit
    //   49: iload_1
    //   50: ifeq +9 -> 59
    //   53: invokestatic 94	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   56: invokevirtual 97	java/lang/Thread:interrupt	()V
    //   59: aload_0
    //   60: getfield 29	com/google/common/collect/ComputingConcurrentHashMap$ComputingValueReference:computedReference	Lcom/google/common/collect/MapMakerInternalMap$ValueReference;
    //   63: invokeinterface 99 1 0
    //   68: areturn
    //   69: astore_3
    //   70: aload_0
    //   71: monitorexit
    //   72: aload_3
    //   73: athrow
    //   74: astore_3
    //   75: iload_1
    //   76: ifeq +9 -> 85
    //   79: invokestatic 94	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   82: invokevirtual 97	java/lang/Thread:interrupt	()V
    //   85: aload_3
    //   86: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	87	0	this	ComputingValueReference
    //   11	65	1	i	int
    //   13	4	2	j	int
    //   22	7	3	localValueReference1	MapMakerInternalMap.ValueReference
    //   41	1	3	localInterruptedException	InterruptedException
    //   69	4	3	localObject1	Object
    //   74	12	3	localObject2	Object
    //   26	4	4	localValueReference2	MapMakerInternalMap.ValueReference
    // Exception table:
    //   from	to	target	type
    //   34	38	41	java/lang/InterruptedException
    //   18	28	69	finally
    //   34	38	69	finally
    //   47	49	69	finally
    //   70	72	69	finally
    //   14	16	74	finally
    //   72	74	74	finally
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ComputingConcurrentHashMap.ComputingValueReference
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */