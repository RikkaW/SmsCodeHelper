package com.google.common.collect;

import com.google.common.base.Equivalence;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

abstract class MapMakerInternalMap$AbstractSerializationProxy<K, V>
  extends ForwardingConcurrentMap<K, V>
  implements Serializable
{
  private static final long serialVersionUID = 3L;
  final int concurrencyLevel;
  transient ConcurrentMap<K, V> delegate;
  final long expireAfterAccessNanos;
  final long expireAfterWriteNanos;
  final Equivalence<Object> keyEquivalence;
  final MapMakerInternalMap.Strength keyStrength;
  final int maximumSize;
  final MapMaker.RemovalListener<? super K, ? super V> removalListener;
  final Equivalence<Object> valueEquivalence;
  final MapMakerInternalMap.Strength valueStrength;
  
  MapMakerInternalMap$AbstractSerializationProxy(MapMakerInternalMap.Strength paramStrength1, MapMakerInternalMap.Strength paramStrength2, Equivalence<Object> paramEquivalence1, Equivalence<Object> paramEquivalence2, long paramLong1, long paramLong2, int paramInt1, int paramInt2, MapMaker.RemovalListener<? super K, ? super V> paramRemovalListener, ConcurrentMap<K, V> paramConcurrentMap)
  {
    keyStrength = paramStrength1;
    valueStrength = paramStrength2;
    keyEquivalence = paramEquivalence1;
    valueEquivalence = paramEquivalence2;
    expireAfterWriteNanos = paramLong1;
    expireAfterAccessNanos = paramLong2;
    maximumSize = paramInt1;
    concurrencyLevel = paramInt2;
    removalListener = paramRemovalListener;
    delegate = paramConcurrentMap;
  }
  
  protected ConcurrentMap<K, V> delegate()
  {
    return delegate;
  }
  
  void readEntries(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    for (;;)
    {
      Object localObject1 = paramObjectInputStream.readObject();
      if (localObject1 == null) {
        return;
      }
      Object localObject2 = paramObjectInputStream.readObject();
      delegate.put(localObject1, localObject2);
    }
  }
  
  MapMaker readMapMaker(ObjectInputStream paramObjectInputStream)
    throws IOException
  {
    int i = paramObjectInputStream.readInt();
    paramObjectInputStream = new MapMaker().initialCapacity(i).setKeyStrength(keyStrength).setValueStrength(valueStrength).keyEquivalence(keyEquivalence).valueEquivalence(valueEquivalence).concurrencyLevel(concurrencyLevel);
    paramObjectInputStream.removalListener(removalListener);
    if (expireAfterWriteNanos > 0L) {
      paramObjectInputStream.expireAfterWrite(expireAfterWriteNanos, TimeUnit.NANOSECONDS);
    }
    if (expireAfterAccessNanos > 0L) {
      paramObjectInputStream.expireAfterAccess(expireAfterAccessNanos, TimeUnit.NANOSECONDS);
    }
    if (maximumSize != -1) {
      paramObjectInputStream.maximumSize(maximumSize);
    }
    return paramObjectInputStream;
  }
  
  void writeMapTo(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.writeInt(delegate.size());
    Iterator localIterator = delegate.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      paramObjectOutputStream.writeObject(localEntry.getKey());
      paramObjectOutputStream.writeObject(localEntry.getValue());
    }
    paramObjectOutputStream.writeObject(null);
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.AbstractSerializationProxy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */