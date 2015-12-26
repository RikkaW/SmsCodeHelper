package com.google.common.collect;

import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ExecutionException;

abstract interface MapMakerInternalMap$ValueReference<K, V>
{
  public abstract void clear(ValueReference<K, V> paramValueReference);
  
  public abstract ValueReference<K, V> copyFor(ReferenceQueue<V> paramReferenceQueue, MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry);
  
  public abstract V get();
  
  public abstract MapMakerInternalMap.ReferenceEntry<K, V> getEntry();
  
  public abstract boolean isComputingReference();
  
  public abstract V waitForValue()
    throws ExecutionException;
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.ValueReference
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */