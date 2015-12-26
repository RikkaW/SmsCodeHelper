package com.google.common.collect;

import com.google.common.base.Equivalence;
import com.google.common.base.Ticker;
import java.lang.ref.ReferenceQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;

class MapMakerInternalMap$Segment<K, V>
  extends ReentrantLock
{
  volatile int count;
  final Queue<MapMakerInternalMap.ReferenceEntry<K, V>> evictionQueue;
  final Queue<MapMakerInternalMap.ReferenceEntry<K, V>> expirationQueue;
  final ReferenceQueue<K> keyReferenceQueue;
  final MapMakerInternalMap<K, V> map;
  final int maxSegmentSize;
  int modCount;
  final AtomicInteger readCount = new AtomicInteger();
  final Queue<MapMakerInternalMap.ReferenceEntry<K, V>> recencyQueue;
  volatile AtomicReferenceArray<MapMakerInternalMap.ReferenceEntry<K, V>> table;
  int threshold;
  final ReferenceQueue<V> valueReferenceQueue;
  
  MapMakerInternalMap$Segment(MapMakerInternalMap<K, V> paramMapMakerInternalMap, int paramInt1, int paramInt2)
  {
    map = paramMapMakerInternalMap;
    maxSegmentSize = paramInt2;
    initTable(newEntryArray(paramInt1));
    Object localObject1;
    if (paramMapMakerInternalMap.usesKeyReferences())
    {
      localObject1 = new ReferenceQueue();
      keyReferenceQueue = ((ReferenceQueue)localObject1);
      localObject1 = localObject2;
      if (paramMapMakerInternalMap.usesValueReferences()) {
        localObject1 = new ReferenceQueue();
      }
      valueReferenceQueue = ((ReferenceQueue)localObject1);
      if ((!paramMapMakerInternalMap.evictsBySize()) && (!paramMapMakerInternalMap.expiresAfterAccess())) {
        break label163;
      }
      localObject1 = new ConcurrentLinkedQueue();
      label108:
      recencyQueue = ((Queue)localObject1);
      if (!paramMapMakerInternalMap.evictsBySize()) {
        break label171;
      }
      localObject1 = new MapMakerInternalMap.EvictionQueue();
      label130:
      evictionQueue = ((Queue)localObject1);
      if (!paramMapMakerInternalMap.expires()) {
        break label179;
      }
    }
    label163:
    label171:
    label179:
    for (paramMapMakerInternalMap = new MapMakerInternalMap.ExpirationQueue();; paramMapMakerInternalMap = MapMakerInternalMap.discardingQueue())
    {
      expirationQueue = paramMapMakerInternalMap;
      return;
      localObject1 = null;
      break;
      localObject1 = MapMakerInternalMap.discardingQueue();
      break label108;
      localObject1 = MapMakerInternalMap.discardingQueue();
      break label130;
    }
  }
  
  void clear()
  {
    if (count != 0) {
      lock();
    }
    for (;;)
    {
      try
      {
        AtomicReferenceArray localAtomicReferenceArray = table;
        if (map.removalNotificationQueue == MapMakerInternalMap.DISCARDING_QUEUE) {
          break label177;
        }
        i = 0;
        if (i >= localAtomicReferenceArray.length()) {
          break label177;
        }
        MapMakerInternalMap.ReferenceEntry localReferenceEntry = (MapMakerInternalMap.ReferenceEntry)localAtomicReferenceArray.get(i);
        if (localReferenceEntry != null)
        {
          if (!localReferenceEntry.getValueReference().isComputingReference()) {
            enqueueNotification(localReferenceEntry, MapMaker.RemovalCause.EXPLICIT);
          }
          localReferenceEntry = localReferenceEntry.getNext();
          continue;
          if (i < localAtomicReferenceArray.length())
          {
            localAtomicReferenceArray.set(i, null);
            i += 1;
            continue;
          }
          clearReferenceQueues();
          evictionQueue.clear();
          expirationQueue.clear();
          readCount.set(0);
          modCount += 1;
          count = 0;
          return;
        }
      }
      finally
      {
        unlock();
        postWriteCleanup();
      }
      i += 1;
      continue;
      label177:
      int i = 0;
    }
  }
  
  void clearKeyReferenceQueue()
  {
    while (keyReferenceQueue.poll() != null) {}
  }
  
  void clearReferenceQueues()
  {
    if (map.usesKeyReferences()) {
      clearKeyReferenceQueue();
    }
    if (map.usesValueReferences()) {
      clearValueReferenceQueue();
    }
  }
  
  boolean clearValue(K paramK, int paramInt, MapMakerInternalMap.ValueReference<K, V> paramValueReference)
  {
    lock();
    try
    {
      AtomicReferenceArray localAtomicReferenceArray = table;
      int i = paramInt & localAtomicReferenceArray.length() - 1;
      MapMakerInternalMap.ReferenceEntry localReferenceEntry2 = (MapMakerInternalMap.ReferenceEntry)localAtomicReferenceArray.get(i);
      for (MapMakerInternalMap.ReferenceEntry localReferenceEntry1 = localReferenceEntry2; localReferenceEntry1 != null; localReferenceEntry1 = localReferenceEntry1.getNext())
      {
        Object localObject = localReferenceEntry1.getKey();
        if ((localReferenceEntry1.getHash() == paramInt) && (localObject != null) && (map.keyEquivalence.equivalent(paramK, localObject)))
        {
          if (localReferenceEntry1.getValueReference() == paramValueReference)
          {
            localAtomicReferenceArray.set(i, removeFromChain(localReferenceEntry2, localReferenceEntry1));
            return true;
          }
          return false;
        }
      }
      return false;
    }
    finally
    {
      unlock();
      postWriteCleanup();
    }
  }
  
  void clearValueReferenceQueue()
  {
    while (valueReferenceQueue.poll() != null) {}
  }
  
  boolean containsKey(Object paramObject, int paramInt)
  {
    boolean bool = false;
    try
    {
      if (count != 0)
      {
        paramObject = getLiveEntry(paramObject, paramInt);
        if (paramObject == null) {
          return false;
        }
        paramObject = ((MapMakerInternalMap.ReferenceEntry)paramObject).getValueReference().get();
        if (paramObject != null) {
          bool = true;
        }
        return bool;
      }
      return false;
    }
    finally
    {
      postReadCleanup();
    }
  }
  
  boolean containsValue(Object paramObject)
  {
    try
    {
      if (count != 0)
      {
        AtomicReferenceArray localAtomicReferenceArray = table;
        int j = localAtomicReferenceArray.length();
        int i = 0;
        while (i < j)
        {
          MapMakerInternalMap.ReferenceEntry localReferenceEntry = (MapMakerInternalMap.ReferenceEntry)localAtomicReferenceArray.get(i);
          if (localReferenceEntry != null)
          {
            Object localObject = getLiveValue(localReferenceEntry);
            if (localObject == null) {}
            boolean bool;
            do
            {
              localReferenceEntry = localReferenceEntry.getNext();
              break;
              bool = map.valueEquivalence.equivalent(paramObject, localObject);
            } while (!bool);
            return true;
          }
          i += 1;
        }
      }
      return false;
    }
    finally
    {
      postReadCleanup();
    }
  }
  
  MapMakerInternalMap.ReferenceEntry<K, V> copyEntry(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry1, MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry2)
  {
    MapMakerInternalMap.ValueReference localValueReference = paramReferenceEntry1.getValueReference();
    paramReferenceEntry1 = map.entryFactory.copyEntry(this, paramReferenceEntry1, paramReferenceEntry2);
    paramReferenceEntry1.setValueReference(localValueReference.copyFor(valueReferenceQueue, paramReferenceEntry1));
    return paramReferenceEntry1;
  }
  
  void drainKeyReferenceQueue()
  {
    int i = 0;
    int j;
    do
    {
      Object localObject = keyReferenceQueue.poll();
      if (localObject == null) {
        break;
      }
      localObject = (MapMakerInternalMap.ReferenceEntry)localObject;
      map.reclaimKey((MapMakerInternalMap.ReferenceEntry)localObject);
      j = i + 1;
      i = j;
    } while (j != 16);
  }
  
  void drainRecencyQueue()
  {
    for (;;)
    {
      MapMakerInternalMap.ReferenceEntry localReferenceEntry = (MapMakerInternalMap.ReferenceEntry)recencyQueue.poll();
      if (localReferenceEntry == null) {
        break;
      }
      if (evictionQueue.contains(localReferenceEntry)) {
        evictionQueue.add(localReferenceEntry);
      }
      if ((map.expiresAfterAccess()) && (expirationQueue.contains(localReferenceEntry))) {
        expirationQueue.add(localReferenceEntry);
      }
    }
  }
  
  void drainReferenceQueues()
  {
    if (map.usesKeyReferences()) {
      drainKeyReferenceQueue();
    }
    if (map.usesValueReferences()) {
      drainValueReferenceQueue();
    }
  }
  
  void drainValueReferenceQueue()
  {
    int i = 0;
    int j;
    do
    {
      Object localObject = valueReferenceQueue.poll();
      if (localObject == null) {
        break;
      }
      localObject = (MapMakerInternalMap.ValueReference)localObject;
      map.reclaimValue((MapMakerInternalMap.ValueReference)localObject);
      j = i + 1;
      i = j;
    } while (j != 16);
  }
  
  void enqueueNotification(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry, MapMaker.RemovalCause paramRemovalCause)
  {
    enqueueNotification(paramReferenceEntry.getKey(), paramReferenceEntry.getHash(), paramReferenceEntry.getValueReference().get(), paramRemovalCause);
  }
  
  void enqueueNotification(K paramK, int paramInt, V paramV, MapMaker.RemovalCause paramRemovalCause)
  {
    if (map.removalNotificationQueue != MapMakerInternalMap.DISCARDING_QUEUE)
    {
      paramK = new MapMaker.RemovalNotification(paramK, paramV, paramRemovalCause);
      map.removalNotificationQueue.offer(paramK);
    }
  }
  
  boolean evictEntries()
  {
    if ((map.evictsBySize()) && (count >= maxSegmentSize))
    {
      drainRecencyQueue();
      MapMakerInternalMap.ReferenceEntry localReferenceEntry = (MapMakerInternalMap.ReferenceEntry)evictionQueue.remove();
      if (!removeEntry(localReferenceEntry, localReferenceEntry.getHash(), MapMaker.RemovalCause.SIZE)) {
        throw new AssertionError();
      }
      return true;
    }
    return false;
  }
  
  void expand()
  {
    AtomicReferenceArray localAtomicReferenceArray1 = table;
    int i1 = localAtomicReferenceArray1.length();
    if (i1 >= 1073741824) {
      return;
    }
    int i = count;
    AtomicReferenceArray localAtomicReferenceArray2 = newEntryArray(i1 << 1);
    threshold = (localAtomicReferenceArray2.length() * 3 / 4);
    int i2 = localAtomicReferenceArray2.length() - 1;
    int j = 0;
    if (j < i1)
    {
      MapMakerInternalMap.ReferenceEntry localReferenceEntry2 = (MapMakerInternalMap.ReferenceEntry)localAtomicReferenceArray1.get(j);
      int k = i;
      MapMakerInternalMap.ReferenceEntry localReferenceEntry1;
      if (localReferenceEntry2 != null)
      {
        localReferenceEntry1 = localReferenceEntry2.getNext();
        k = localReferenceEntry2.getHash() & i2;
        if (localReferenceEntry1 != null) {
          break label129;
        }
        localAtomicReferenceArray2.set(k, localReferenceEntry2);
        k = i;
      }
      label129:
      MapMakerInternalMap.ReferenceEntry localReferenceEntry3;
      do
      {
        j += 1;
        i = k;
        break;
        localReferenceEntry3 = localReferenceEntry2;
        while (localReferenceEntry1 != null)
        {
          int n = localReferenceEntry1.getHash() & i2;
          int m = k;
          if (n != k)
          {
            m = n;
            localReferenceEntry3 = localReferenceEntry1;
          }
          localReferenceEntry1 = localReferenceEntry1.getNext();
          k = m;
        }
        localAtomicReferenceArray2.set(k, localReferenceEntry3);
        k = i;
      } while (localReferenceEntry2 == localReferenceEntry3);
      if (isCollected(localReferenceEntry2))
      {
        removeCollectedEntry(localReferenceEntry2);
        i -= 1;
      }
      for (;;)
      {
        localReferenceEntry2 = localReferenceEntry2.getNext();
        break;
        k = localReferenceEntry2.getHash() & i2;
        localAtomicReferenceArray2.set(k, copyEntry(localReferenceEntry2, (MapMakerInternalMap.ReferenceEntry)localAtomicReferenceArray2.get(k)));
      }
    }
    table = localAtomicReferenceArray2;
    count = i;
  }
  
  void expireEntries()
  {
    drainRecencyQueue();
    if (expirationQueue.isEmpty()) {
      return;
    }
    long l = map.ticker.read();
    MapMakerInternalMap.ReferenceEntry localReferenceEntry;
    do
    {
      localReferenceEntry = (MapMakerInternalMap.ReferenceEntry)expirationQueue.peek();
      if ((localReferenceEntry == null) || (!map.isExpired(localReferenceEntry, l))) {
        break;
      }
    } while (removeEntry(localReferenceEntry, localReferenceEntry.getHash(), MapMaker.RemovalCause.EXPIRED));
    throw new AssertionError();
  }
  
  /* Error */
  V get(Object paramObject, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: iload_2
    //   3: invokevirtual 209	com/google/common/collect/MapMakerInternalMap$Segment:getLiveEntry	(Ljava/lang/Object;I)Lcom/google/common/collect/MapMakerInternalMap$ReferenceEntry;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnonnull +9 -> 17
    //   11: aload_0
    //   12: invokevirtual 212	com/google/common/collect/MapMakerInternalMap$Segment:postReadCleanup	()V
    //   15: aconst_null
    //   16: areturn
    //   17: aload_1
    //   18: invokeinterface 128 1 0
    //   23: invokeinterface 214 1 0
    //   28: astore_3
    //   29: aload_3
    //   30: ifnull +14 -> 44
    //   33: aload_0
    //   34: aload_1
    //   35: invokevirtual 335	com/google/common/collect/MapMakerInternalMap$Segment:recordRead	(Lcom/google/common/collect/MapMakerInternalMap$ReferenceEntry;)V
    //   38: aload_0
    //   39: invokevirtual 212	com/google/common/collect/MapMakerInternalMap$Segment:postReadCleanup	()V
    //   42: aload_3
    //   43: areturn
    //   44: aload_0
    //   45: invokevirtual 338	com/google/common/collect/MapMakerInternalMap$Segment:tryDrainReferenceQueues	()V
    //   48: goto -10 -> 38
    //   51: astore_1
    //   52: aload_0
    //   53: invokevirtual 212	com/google/common/collect/MapMakerInternalMap$Segment:postReadCleanup	()V
    //   56: aload_1
    //   57: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	58	0	this	Segment
    //   0	58	1	paramObject	Object
    //   0	58	2	paramInt	int
    //   28	15	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   0	7	51	finally
    //   17	29	51	finally
    //   33	38	51	finally
    //   44	48	51	finally
  }
  
  MapMakerInternalMap.ReferenceEntry<K, V> getEntry(Object paramObject, int paramInt)
  {
    if (count != 0)
    {
      MapMakerInternalMap.ReferenceEntry localReferenceEntry = getFirst(paramInt);
      if (localReferenceEntry != null)
      {
        if (localReferenceEntry.getHash() != paramInt) {}
        Object localObject;
        label57:
        do
        {
          for (;;)
          {
            localReferenceEntry = localReferenceEntry.getNext();
            break;
            localObject = localReferenceEntry.getKey();
            if (localObject != null) {
              break label57;
            }
            tryDrainReferenceQueues();
          }
        } while (!map.keyEquivalence.equivalent(paramObject, localObject));
        return localReferenceEntry;
      }
    }
    return null;
  }
  
  MapMakerInternalMap.ReferenceEntry<K, V> getFirst(int paramInt)
  {
    AtomicReferenceArray localAtomicReferenceArray = table;
    return (MapMakerInternalMap.ReferenceEntry)localAtomicReferenceArray.get(localAtomicReferenceArray.length() - 1 & paramInt);
  }
  
  MapMakerInternalMap.ReferenceEntry<K, V> getLiveEntry(Object paramObject, int paramInt)
  {
    MapMakerInternalMap.ReferenceEntry localReferenceEntry = getEntry(paramObject, paramInt);
    if (localReferenceEntry == null) {
      paramObject = null;
    }
    do
    {
      do
      {
        return (MapMakerInternalMap.ReferenceEntry<K, V>)paramObject;
        paramObject = localReferenceEntry;
      } while (!map.expires());
      paramObject = localReferenceEntry;
    } while (!map.isExpired(localReferenceEntry));
    tryExpireEntries();
    return null;
  }
  
  V getLiveValue(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    Object localObject1;
    if (paramReferenceEntry.getKey() == null)
    {
      tryDrainReferenceQueues();
      localObject1 = null;
    }
    do
    {
      Object localObject2;
      do
      {
        return (V)localObject1;
        localObject2 = paramReferenceEntry.getValueReference().get();
        if (localObject2 == null)
        {
          tryDrainReferenceQueues();
          return null;
        }
        localObject1 = localObject2;
      } while (!map.expires());
      localObject1 = localObject2;
    } while (!map.isExpired(paramReferenceEntry));
    tryExpireEntries();
    return null;
  }
  
  void initTable(AtomicReferenceArray<MapMakerInternalMap.ReferenceEntry<K, V>> paramAtomicReferenceArray)
  {
    threshold = (paramAtomicReferenceArray.length() * 3 / 4);
    if (threshold == maxSegmentSize) {
      threshold += 1;
    }
    table = paramAtomicReferenceArray;
  }
  
  boolean isCollected(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    if (paramReferenceEntry.getKey() == null) {
      return true;
    }
    return isCollected(paramReferenceEntry.getValueReference());
  }
  
  boolean isCollected(MapMakerInternalMap.ValueReference<K, V> paramValueReference)
  {
    if (paramValueReference.isComputingReference()) {}
    while (paramValueReference.get() != null) {
      return false;
    }
    return true;
  }
  
  MapMakerInternalMap.ReferenceEntry<K, V> newEntry(K paramK, int paramInt, MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    return map.entryFactory.newEntry(this, paramK, paramInt, paramReferenceEntry);
  }
  
  AtomicReferenceArray<MapMakerInternalMap.ReferenceEntry<K, V>> newEntryArray(int paramInt)
  {
    return new AtomicReferenceArray(paramInt);
  }
  
  void postReadCleanup()
  {
    if ((readCount.incrementAndGet() & 0x3F) == 0) {
      runCleanup();
    }
  }
  
  void postWriteCleanup()
  {
    runUnlockedCleanup();
  }
  
  void preWriteCleanup()
  {
    runLockedCleanup();
  }
  
  V put(K paramK, int paramInt, V paramV, boolean paramBoolean)
  {
    lock();
    try
    {
      preWriteCleanup();
      int j = count + 1;
      int i = j;
      if (j > threshold)
      {
        expand();
        i = count + 1;
      }
      Object localObject3 = table;
      j = paramInt & ((AtomicReferenceArray)localObject3).length() - 1;
      Object localObject2 = (MapMakerInternalMap.ReferenceEntry)((AtomicReferenceArray)localObject3).get(j);
      for (Object localObject1 = localObject2; localObject1 != null; localObject1 = ((MapMakerInternalMap.ReferenceEntry)localObject1).getNext())
      {
        Object localObject4 = ((MapMakerInternalMap.ReferenceEntry)localObject1).getKey();
        if ((((MapMakerInternalMap.ReferenceEntry)localObject1).getHash() == paramInt) && (localObject4 != null) && (map.keyEquivalence.equivalent(paramK, localObject4)))
        {
          localObject2 = ((MapMakerInternalMap.ReferenceEntry)localObject1).getValueReference();
          localObject3 = ((MapMakerInternalMap.ValueReference)localObject2).get();
          if (localObject3 == null)
          {
            modCount += 1;
            setValue((MapMakerInternalMap.ReferenceEntry)localObject1, paramV);
            if (!((MapMakerInternalMap.ValueReference)localObject2).isComputingReference())
            {
              enqueueNotification(paramK, paramInt, localObject3, MapMaker.RemovalCause.COLLECTED);
              i = count;
            }
            for (;;)
            {
              count = i;
              return null;
              if (evictEntries()) {
                i = count + 1;
              }
            }
          }
          if (paramBoolean)
          {
            recordLockedRead((MapMakerInternalMap.ReferenceEntry)localObject1);
            return (V)localObject3;
          }
          modCount += 1;
          enqueueNotification(paramK, paramInt, localObject3, MapMaker.RemovalCause.REPLACED);
          setValue((MapMakerInternalMap.ReferenceEntry)localObject1, paramV);
          return (V)localObject3;
        }
      }
      modCount += 1;
      paramK = newEntry(paramK, paramInt, (MapMakerInternalMap.ReferenceEntry)localObject2);
      setValue(paramK, paramV);
      ((AtomicReferenceArray)localObject3).set(j, paramK);
      if (evictEntries()) {
        i = count + 1;
      }
      count = i;
      return null;
    }
    finally
    {
      unlock();
      postWriteCleanup();
    }
  }
  
  boolean reclaimKey(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry, int paramInt)
  {
    lock();
    try
    {
      int i = count;
      AtomicReferenceArray localAtomicReferenceArray = table;
      i = paramInt & localAtomicReferenceArray.length() - 1;
      MapMakerInternalMap.ReferenceEntry localReferenceEntry2 = (MapMakerInternalMap.ReferenceEntry)localAtomicReferenceArray.get(i);
      for (MapMakerInternalMap.ReferenceEntry localReferenceEntry1 = localReferenceEntry2; localReferenceEntry1 != null; localReferenceEntry1 = localReferenceEntry1.getNext()) {
        if (localReferenceEntry1 == paramReferenceEntry)
        {
          modCount += 1;
          enqueueNotification(localReferenceEntry1.getKey(), paramInt, localReferenceEntry1.getValueReference().get(), MapMaker.RemovalCause.COLLECTED);
          paramReferenceEntry = removeFromChain(localReferenceEntry2, localReferenceEntry1);
          paramInt = count;
          localAtomicReferenceArray.set(i, paramReferenceEntry);
          count = (paramInt - 1);
          return true;
        }
      }
      return false;
    }
    finally
    {
      unlock();
      postWriteCleanup();
    }
  }
  
  boolean reclaimValue(K paramK, int paramInt, MapMakerInternalMap.ValueReference<K, V> paramValueReference)
  {
    boolean bool1 = false;
    lock();
    try
    {
      int i = count;
      AtomicReferenceArray localAtomicReferenceArray = table;
      i = paramInt & localAtomicReferenceArray.length() - 1;
      MapMakerInternalMap.ReferenceEntry localReferenceEntry2 = (MapMakerInternalMap.ReferenceEntry)localAtomicReferenceArray.get(i);
      for (MapMakerInternalMap.ReferenceEntry localReferenceEntry1 = localReferenceEntry2; localReferenceEntry1 != null; localReferenceEntry1 = localReferenceEntry1.getNext())
      {
        Object localObject = localReferenceEntry1.getKey();
        if ((localReferenceEntry1.getHash() == paramInt) && (localObject != null) && (map.keyEquivalence.equivalent(paramK, localObject)))
        {
          if (localReferenceEntry1.getValueReference() == paramValueReference)
          {
            modCount += 1;
            enqueueNotification(paramK, paramInt, paramValueReference.get(), MapMaker.RemovalCause.COLLECTED);
            paramK = removeFromChain(localReferenceEntry2, localReferenceEntry1);
            paramInt = count;
            localAtomicReferenceArray.set(i, paramK);
            count = (paramInt - 1);
            boolean bool2 = true;
            unlock();
            bool1 = bool2;
            if (!isHeldByCurrentThread())
            {
              postWriteCleanup();
              bool1 = bool2;
            }
            return bool1;
          }
          return false;
        }
      }
      return false;
    }
    finally
    {
      unlock();
      if (!isHeldByCurrentThread()) {
        postWriteCleanup();
      }
    }
  }
  
  void recordExpirationTime(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry, long paramLong)
  {
    paramReferenceEntry.setExpirationTime(map.ticker.read() + paramLong);
  }
  
  void recordLockedRead(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    evictionQueue.add(paramReferenceEntry);
    if (map.expiresAfterAccess())
    {
      recordExpirationTime(paramReferenceEntry, map.expireAfterAccessNanos);
      expirationQueue.add(paramReferenceEntry);
    }
  }
  
  void recordRead(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    if (map.expiresAfterAccess()) {
      recordExpirationTime(paramReferenceEntry, map.expireAfterAccessNanos);
    }
    recencyQueue.add(paramReferenceEntry);
  }
  
  void recordWrite(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    drainRecencyQueue();
    evictionQueue.add(paramReferenceEntry);
    if (map.expires()) {
      if (!map.expiresAfterAccess()) {
        break label61;
      }
    }
    label61:
    for (long l = map.expireAfterAccessNanos;; l = map.expireAfterWriteNanos)
    {
      recordExpirationTime(paramReferenceEntry, l);
      expirationQueue.add(paramReferenceEntry);
      return;
    }
  }
  
  V remove(Object paramObject, int paramInt)
  {
    lock();
    try
    {
      preWriteCleanup();
      int i = count;
      AtomicReferenceArray localAtomicReferenceArray = table;
      i = paramInt & localAtomicReferenceArray.length() - 1;
      MapMakerInternalMap.ReferenceEntry localReferenceEntry2 = (MapMakerInternalMap.ReferenceEntry)localAtomicReferenceArray.get(i);
      for (MapMakerInternalMap.ReferenceEntry localReferenceEntry1 = localReferenceEntry2; localReferenceEntry1 != null; localReferenceEntry1 = localReferenceEntry1.getNext())
      {
        Object localObject1 = localReferenceEntry1.getKey();
        if ((localReferenceEntry1.getHash() == paramInt) && (localObject1 != null) && (map.keyEquivalence.equivalent(paramObject, localObject1)))
        {
          paramObject = localReferenceEntry1.getValueReference();
          Object localObject2 = ((MapMakerInternalMap.ValueReference)paramObject).get();
          if (localObject2 != null) {}
          for (paramObject = MapMaker.RemovalCause.EXPLICIT;; paramObject = MapMaker.RemovalCause.COLLECTED)
          {
            modCount += 1;
            enqueueNotification(localObject1, paramInt, localObject2, (MapMaker.RemovalCause)paramObject);
            paramObject = removeFromChain(localReferenceEntry2, localReferenceEntry1);
            paramInt = count;
            localAtomicReferenceArray.set(i, paramObject);
            count = (paramInt - 1);
            return (V)localObject2;
            if (!isCollected((MapMakerInternalMap.ValueReference)paramObject)) {
              break;
            }
          }
          return null;
        }
      }
      return null;
    }
    finally
    {
      unlock();
      postWriteCleanup();
    }
  }
  
  boolean remove(Object paramObject1, int paramInt, Object paramObject2)
  {
    boolean bool = false;
    lock();
    try
    {
      preWriteCleanup();
      int i = count;
      AtomicReferenceArray localAtomicReferenceArray = table;
      i = paramInt & localAtomicReferenceArray.length() - 1;
      MapMakerInternalMap.ReferenceEntry localReferenceEntry2 = (MapMakerInternalMap.ReferenceEntry)localAtomicReferenceArray.get(i);
      for (MapMakerInternalMap.ReferenceEntry localReferenceEntry1 = localReferenceEntry2; localReferenceEntry1 != null; localReferenceEntry1 = localReferenceEntry1.getNext())
      {
        Object localObject1 = localReferenceEntry1.getKey();
        if ((localReferenceEntry1.getHash() == paramInt) && (localObject1 != null) && (map.keyEquivalence.equivalent(paramObject1, localObject1)))
        {
          paramObject1 = localReferenceEntry1.getValueReference();
          Object localObject2 = ((MapMakerInternalMap.ValueReference)paramObject1).get();
          if (map.valueEquivalence.equivalent(paramObject2, localObject2)) {}
          for (paramObject1 = MapMaker.RemovalCause.EXPLICIT;; paramObject1 = MapMaker.RemovalCause.COLLECTED)
          {
            modCount += 1;
            enqueueNotification(localObject1, paramInt, localObject2, (MapMaker.RemovalCause)paramObject1);
            paramObject2 = removeFromChain(localReferenceEntry2, localReferenceEntry1);
            paramInt = count;
            localAtomicReferenceArray.set(i, paramObject2);
            count = (paramInt - 1);
            paramObject2 = MapMaker.RemovalCause.EXPLICIT;
            if (paramObject1 == paramObject2) {
              bool = true;
            }
            return bool;
            if (!isCollected((MapMakerInternalMap.ValueReference)paramObject1)) {
              break;
            }
          }
          return false;
        }
      }
      return false;
    }
    finally
    {
      unlock();
      postWriteCleanup();
    }
  }
  
  void removeCollectedEntry(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    enqueueNotification(paramReferenceEntry, MapMaker.RemovalCause.COLLECTED);
    evictionQueue.remove(paramReferenceEntry);
    expirationQueue.remove(paramReferenceEntry);
  }
  
  boolean removeEntry(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry, int paramInt, MapMaker.RemovalCause paramRemovalCause)
  {
    int i = count;
    AtomicReferenceArray localAtomicReferenceArray = table;
    i = paramInt & localAtomicReferenceArray.length() - 1;
    MapMakerInternalMap.ReferenceEntry localReferenceEntry2 = (MapMakerInternalMap.ReferenceEntry)localAtomicReferenceArray.get(i);
    for (MapMakerInternalMap.ReferenceEntry localReferenceEntry1 = localReferenceEntry2; localReferenceEntry1 != null; localReferenceEntry1 = localReferenceEntry1.getNext()) {
      if (localReferenceEntry1 == paramReferenceEntry)
      {
        modCount += 1;
        enqueueNotification(localReferenceEntry1.getKey(), paramInt, localReferenceEntry1.getValueReference().get(), paramRemovalCause);
        paramReferenceEntry = removeFromChain(localReferenceEntry2, localReferenceEntry1);
        paramInt = count;
        localAtomicReferenceArray.set(i, paramReferenceEntry);
        count = (paramInt - 1);
        return true;
      }
    }
    return false;
  }
  
  MapMakerInternalMap.ReferenceEntry<K, V> removeFromChain(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry1, MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry2)
  {
    evictionQueue.remove(paramReferenceEntry2);
    expirationQueue.remove(paramReferenceEntry2);
    int i = count;
    MapMakerInternalMap.ReferenceEntry localReferenceEntry = paramReferenceEntry2.getNext();
    if (paramReferenceEntry1 != paramReferenceEntry2)
    {
      if (isCollected(paramReferenceEntry1))
      {
        removeCollectedEntry(paramReferenceEntry1);
        i -= 1;
      }
      for (;;)
      {
        paramReferenceEntry1 = paramReferenceEntry1.getNext();
        break;
        localReferenceEntry = copyEntry(paramReferenceEntry1, localReferenceEntry);
      }
    }
    count = i;
    return localReferenceEntry;
  }
  
  V replace(K paramK, int paramInt, V paramV)
  {
    lock();
    try
    {
      preWriteCleanup();
      AtomicReferenceArray localAtomicReferenceArray = table;
      int i = paramInt & localAtomicReferenceArray.length() - 1;
      MapMakerInternalMap.ReferenceEntry localReferenceEntry2 = (MapMakerInternalMap.ReferenceEntry)localAtomicReferenceArray.get(i);
      for (MapMakerInternalMap.ReferenceEntry localReferenceEntry1 = localReferenceEntry2; localReferenceEntry1 != null; localReferenceEntry1 = localReferenceEntry1.getNext())
      {
        Object localObject1 = localReferenceEntry1.getKey();
        if ((localReferenceEntry1.getHash() == paramInt) && (localObject1 != null) && (map.keyEquivalence.equivalent(paramK, localObject1)))
        {
          MapMakerInternalMap.ValueReference localValueReference = localReferenceEntry1.getValueReference();
          Object localObject2 = localValueReference.get();
          if (localObject2 == null)
          {
            if (isCollected(localValueReference))
            {
              int j = count;
              modCount += 1;
              enqueueNotification(localObject1, paramInt, localObject2, MapMaker.RemovalCause.COLLECTED);
              paramK = removeFromChain(localReferenceEntry2, localReferenceEntry1);
              paramInt = count;
              localAtomicReferenceArray.set(i, paramK);
              count = (paramInt - 1);
            }
            return null;
          }
          modCount += 1;
          enqueueNotification(paramK, paramInt, localObject2, MapMaker.RemovalCause.REPLACED);
          setValue(localReferenceEntry1, paramV);
          return (V)localObject2;
        }
      }
      return null;
    }
    finally
    {
      unlock();
      postWriteCleanup();
    }
  }
  
  boolean replace(K paramK, int paramInt, V paramV1, V paramV2)
  {
    lock();
    try
    {
      preWriteCleanup();
      AtomicReferenceArray localAtomicReferenceArray = table;
      int i = paramInt & localAtomicReferenceArray.length() - 1;
      MapMakerInternalMap.ReferenceEntry localReferenceEntry2 = (MapMakerInternalMap.ReferenceEntry)localAtomicReferenceArray.get(i);
      for (MapMakerInternalMap.ReferenceEntry localReferenceEntry1 = localReferenceEntry2; localReferenceEntry1 != null; localReferenceEntry1 = localReferenceEntry1.getNext())
      {
        Object localObject1 = localReferenceEntry1.getKey();
        if ((localReferenceEntry1.getHash() == paramInt) && (localObject1 != null) && (map.keyEquivalence.equivalent(paramK, localObject1)))
        {
          MapMakerInternalMap.ValueReference localValueReference = localReferenceEntry1.getValueReference();
          Object localObject2 = localValueReference.get();
          if (localObject2 == null)
          {
            if (isCollected(localValueReference))
            {
              int j = count;
              modCount += 1;
              enqueueNotification(localObject1, paramInt, localObject2, MapMaker.RemovalCause.COLLECTED);
              paramK = removeFromChain(localReferenceEntry2, localReferenceEntry1);
              paramInt = count;
              localAtomicReferenceArray.set(i, paramK);
              count = (paramInt - 1);
            }
            return false;
          }
          if (map.valueEquivalence.equivalent(paramV1, localObject2))
          {
            modCount += 1;
            enqueueNotification(paramK, paramInt, localObject2, MapMaker.RemovalCause.REPLACED);
            setValue(localReferenceEntry1, paramV2);
            return true;
          }
          recordLockedRead(localReferenceEntry1);
          return false;
        }
      }
      return false;
    }
    finally
    {
      unlock();
      postWriteCleanup();
    }
  }
  
  void runCleanup()
  {
    runLockedCleanup();
    runUnlockedCleanup();
  }
  
  void runLockedCleanup()
  {
    if (tryLock()) {}
    try
    {
      drainReferenceQueues();
      expireEntries();
      readCount.set(0);
      return;
    }
    finally
    {
      unlock();
    }
  }
  
  void runUnlockedCleanup()
  {
    if (!isHeldByCurrentThread()) {
      map.processPendingNotifications();
    }
  }
  
  void setValue(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry, V paramV)
  {
    paramReferenceEntry.setValueReference(map.valueStrength.referenceValue(this, paramReferenceEntry, paramV));
    recordWrite(paramReferenceEntry);
  }
  
  void tryDrainReferenceQueues()
  {
    if (tryLock()) {}
    try
    {
      drainReferenceQueues();
      return;
    }
    finally
    {
      unlock();
    }
  }
  
  void tryExpireEntries()
  {
    if (tryLock()) {}
    try
    {
      expireEntries();
      return;
    }
    finally
    {
      unlock();
    }
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.Segment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */