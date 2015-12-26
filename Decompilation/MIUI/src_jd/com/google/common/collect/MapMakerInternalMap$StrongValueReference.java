package com.google.common.collect;

import java.lang.ref.ReferenceQueue;

final class MapMakerInternalMap$StrongValueReference<K, V>
  implements MapMakerInternalMap.ValueReference<K, V>
{
  final V referent;
  
  MapMakerInternalMap$StrongValueReference(V paramV)
  {
    referent = paramV;
  }
  
  public void clear(MapMakerInternalMap.ValueReference<K, V> paramValueReference) {}
  
  public MapMakerInternalMap.ValueReference<K, V> copyFor(ReferenceQueue<V> paramReferenceQueue, MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    return this;
  }
  
  public V get()
  {
    return (V)referent;
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
  {
    return (V)get();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.StrongValueReference
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */