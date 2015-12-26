package com.google.common.collect;

import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReferenceArray;

final class ComputingConcurrentHashMap$ComputingSegment<K, V>
  extends MapMakerInternalMap.Segment<K, V>
{
  ComputingConcurrentHashMap$ComputingSegment(MapMakerInternalMap<K, V> paramMapMakerInternalMap, int paramInt1, int paramInt2)
  {
    super(paramMapMakerInternalMap, paramInt1, paramInt2);
  }
  
  /* Error */
  V compute(K paramK, int paramInt, MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry, ComputingConcurrentHashMap.ComputingValueReference<K, V> paramComputingValueReference)
    throws ExecutionException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 9
    //   3: aconst_null
    //   4: astore 10
    //   6: invokestatic 25	java/lang/System:nanoTime	()J
    //   9: pop2
    //   10: lconst_0
    //   11: lstore 7
    //   13: lload 7
    //   15: lstore 5
    //   17: aload_3
    //   18: monitorenter
    //   19: lload 7
    //   21: lstore 5
    //   23: aload 10
    //   25: astore 9
    //   27: aload 4
    //   29: aload_1
    //   30: iload_2
    //   31: invokevirtual 30	com/google/common/collect/ComputingConcurrentHashMap$ComputingValueReference:compute	(Ljava/lang/Object;I)Ljava/lang/Object;
    //   34: astore 10
    //   36: lload 7
    //   38: lstore 5
    //   40: aload 10
    //   42: astore 9
    //   44: invokestatic 25	java/lang/System:nanoTime	()J
    //   47: lstore 7
    //   49: lload 7
    //   51: lstore 5
    //   53: aload 10
    //   55: astore 9
    //   57: aload_3
    //   58: monitorexit
    //   59: aload 10
    //   61: ifnull +42 -> 103
    //   64: lload 7
    //   66: lstore 5
    //   68: aload 10
    //   70: astore 9
    //   72: aload_0
    //   73: aload_1
    //   74: iload_2
    //   75: aload 10
    //   77: iconst_1
    //   78: invokevirtual 34	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:put	(Ljava/lang/Object;ILjava/lang/Object;Z)Ljava/lang/Object;
    //   81: ifnull +22 -> 103
    //   84: lload 7
    //   86: lstore 5
    //   88: aload 10
    //   90: astore 9
    //   92: aload_0
    //   93: aload_1
    //   94: iload_2
    //   95: aload 10
    //   97: getstatic 40	com/google/common/collect/MapMaker$RemovalCause:REPLACED	Lcom/google/common/collect/MapMaker$RemovalCause;
    //   100: invokevirtual 44	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:enqueueNotification	(Ljava/lang/Object;ILjava/lang/Object;Lcom/google/common/collect/MapMaker$RemovalCause;)V
    //   103: lload 7
    //   105: lconst_0
    //   106: lcmp
    //   107: ifne +7 -> 114
    //   110: invokestatic 25	java/lang/System:nanoTime	()J
    //   113: pop2
    //   114: aload 10
    //   116: ifnonnull +12 -> 128
    //   119: aload_0
    //   120: aload_1
    //   121: iload_2
    //   122: aload 4
    //   124: invokevirtual 48	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:clearValue	(Ljava/lang/Object;ILcom/google/common/collect/MapMakerInternalMap$ValueReference;)Z
    //   127: pop
    //   128: aload 10
    //   130: areturn
    //   131: astore 10
    //   133: aload_3
    //   134: monitorexit
    //   135: aload 10
    //   137: athrow
    //   138: astore_3
    //   139: lload 5
    //   141: lconst_0
    //   142: lcmp
    //   143: ifne +7 -> 150
    //   146: invokestatic 25	java/lang/System:nanoTime	()J
    //   149: pop2
    //   150: aload 9
    //   152: ifnonnull +12 -> 164
    //   155: aload_0
    //   156: aload_1
    //   157: iload_2
    //   158: aload 4
    //   160: invokevirtual 48	com/google/common/collect/ComputingConcurrentHashMap$ComputingSegment:clearValue	(Ljava/lang/Object;ILcom/google/common/collect/MapMakerInternalMap$ValueReference;)Z
    //   163: pop
    //   164: aload_3
    //   165: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	166	0	this	ComputingSegment
    //   0	166	1	paramK	K
    //   0	166	2	paramInt	int
    //   0	166	3	paramReferenceEntry	MapMakerInternalMap.ReferenceEntry<K, V>
    //   0	166	4	paramComputingValueReference	ComputingConcurrentHashMap.ComputingValueReference<K, V>
    //   15	125	5	l1	long
    //   11	93	7	l2	long
    //   1	150	9	localObject1	Object
    //   4	125	10	localObject2	Object
    //   131	5	10	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   27	36	131	finally
    //   44	49	131	finally
    //   57	59	131	finally
    //   133	135	131	finally
    //   17	19	138	finally
    //   72	84	138	finally
    //   92	103	138	finally
    //   135	138	138	finally
  }
  
  V getOrCompute(K paramK, int paramInt, Function<? super K, ? extends V> paramFunction)
    throws ExecutionException
  {
    Object localObject2;
    Object localObject1;
    for (;;)
    {
      Object localObject3;
      try
      {
        localObject2 = getEntry(paramK, paramInt);
        if (localObject2 != null)
        {
          localObject1 = getLiveValue((MapMakerInternalMap.ReferenceEntry)localObject2);
          if (localObject1 != null)
          {
            recordRead((MapMakerInternalMap.ReferenceEntry)localObject2);
            return (V)localObject1;
          }
        }
        if (localObject2 != null)
        {
          localObject1 = localObject2;
          if (((MapMakerInternalMap.ReferenceEntry)localObject2).getValueReference().isComputingReference()) {
            break;
          }
        }
        else
        {
          int j = 1;
          localObject3 = null;
          lock();
          int m;
          AtomicReferenceArray localAtomicReferenceArray;
          int k;
          MapMakerInternalMap.ReferenceEntry localReferenceEntry;
          int i;
          try
          {
            preWriteCleanup();
            m = count;
            localAtomicReferenceArray = table;
            k = paramInt & localAtomicReferenceArray.length() - 1;
            localReferenceEntry = (MapMakerInternalMap.ReferenceEntry)localAtomicReferenceArray.get(k);
            localObject1 = localReferenceEntry;
            i = j;
            if (localObject1 != null)
            {
              localObject2 = ((MapMakerInternalMap.ReferenceEntry)localObject1).getKey();
              if ((((MapMakerInternalMap.ReferenceEntry)localObject1).getHash() != paramInt) || (localObject2 == null) || (!map.keyEquivalence.equivalent(paramK, localObject2))) {
                break label424;
              }
              if (((MapMakerInternalMap.ReferenceEntry)localObject1).getValueReference().isComputingReference()) {
                i = 0;
              }
            }
            else
            {
              localObject2 = localObject1;
              if (i != 0)
              {
                localObject2 = new ComputingConcurrentHashMap.ComputingValueReference(paramFunction);
                if (localObject1 != null) {
                  break label436;
                }
              }
            }
          }
          finally {}
        }
      }
      finally
      {
        postReadCleanup();
      }
      try
      {
        localObject1 = newEntry(paramK, paramInt, localReferenceEntry);
        ((MapMakerInternalMap.ReferenceEntry)localObject1).setValueReference((MapMakerInternalMap.ValueReference)localObject2);
        localAtomicReferenceArray.set(k, localObject1);
        localObject3 = localObject2;
        localObject2 = localObject1;
        unlock();
        postWriteCleanup();
        localObject1 = localObject2;
        if (i == 0) {
          break;
        }
        paramK = compute(paramK, paramInt, (MapMakerInternalMap.ReferenceEntry)localObject2, (ComputingConcurrentHashMap.ComputingValueReference)localObject3);
        postReadCleanup();
        return paramK;
      }
      finally
      {
        label424:
        label436:
        boolean bool;
        continue;
      }
      Object localObject4 = ((MapMakerInternalMap.ReferenceEntry)localObject1).getValueReference().get();
      if (localObject4 == null)
      {
        enqueueNotification(localObject2, paramInt, localObject4, MapMaker.RemovalCause.COLLECTED);
        evictionQueue.remove(localObject1);
        expirationQueue.remove(localObject1);
        count = (m - 1);
        i = j;
        continue;
        unlock();
        postWriteCleanup();
        throw paramK;
      }
      else if ((map.expires()) && (map.isExpired((MapMakerInternalMap.ReferenceEntry)localObject1)))
      {
        enqueueNotification(localObject2, paramInt, localObject4, MapMaker.RemovalCause.EXPIRED);
      }
      else
      {
        recordLockedRead((MapMakerInternalMap.ReferenceEntry)localObject1);
        unlock();
        postWriteCleanup();
        postReadCleanup();
        return (V)localObject4;
        localObject1 = ((MapMakerInternalMap.ReferenceEntry)localObject1).getNext();
        continue;
        ((MapMakerInternalMap.ReferenceEntry)localObject1).setValueReference((MapMakerInternalMap.ValueReference)localObject2);
        localObject3 = localObject2;
        localObject2 = localObject1;
      }
    }
    if (!Thread.holdsLock(localObject1)) {}
    for (bool = true;; bool = false)
    {
      Preconditions.checkState(bool, "Recursive computation");
      localObject2 = ((MapMakerInternalMap.ReferenceEntry)localObject1).getValueReference().waitForValue();
      if (localObject2 == null) {
        break;
      }
      recordRead((MapMakerInternalMap.ReferenceEntry)localObject1);
      postReadCleanup();
      return (V)localObject2;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ComputingConcurrentHashMap.ComputingSegment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */