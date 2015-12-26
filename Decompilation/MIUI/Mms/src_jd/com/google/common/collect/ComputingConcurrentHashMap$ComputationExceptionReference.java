package com.google.common.collect;

import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ExecutionException;

final class ComputingConcurrentHashMap$ComputationExceptionReference<K, V>
  implements MapMakerInternalMap.ValueReference<K, V>
{
  final Throwable t;
  
  ComputingConcurrentHashMap$ComputationExceptionReference(Throwable paramThrowable)
  {
    t = paramThrowable;
  }
  
  public void clear(MapMakerInternalMap.ValueReference<K, V> paramValueReference) {}
  
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
    return false;
  }
  
  public V waitForValue()
    throws ExecutionException
  {
    throw new ExecutionException(t);
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ComputingConcurrentHashMap.ComputationExceptionReference
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */