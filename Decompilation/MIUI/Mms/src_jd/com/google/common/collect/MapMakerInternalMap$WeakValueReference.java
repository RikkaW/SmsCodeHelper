package com.google.common.collect;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class MapMakerInternalMap$WeakValueReference<K, V>
  extends WeakReference<V>
  implements MapMakerInternalMap.ValueReference<K, V>
{
  final MapMakerInternalMap.ReferenceEntry<K, V> entry;
  
  MapMakerInternalMap$WeakValueReference(ReferenceQueue<V> paramReferenceQueue, V paramV, MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    super(paramV, paramReferenceQueue);
    entry = paramReferenceEntry;
  }
  
  public void clear(MapMakerInternalMap.ValueReference<K, V> paramValueReference)
  {
    clear();
  }
  
  public MapMakerInternalMap.ValueReference<K, V> copyFor(ReferenceQueue<V> paramReferenceQueue, MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    return new WeakValueReference(paramReferenceQueue, get(), paramReferenceEntry);
  }
  
  public MapMakerInternalMap.ReferenceEntry<K, V> getEntry()
  {
    return entry;
  }
  
  public boolean isComputingReference()
  {
    return false;
  }
  
  public V waitForValue()
  {
    return (V)get();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.WeakValueReference
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */