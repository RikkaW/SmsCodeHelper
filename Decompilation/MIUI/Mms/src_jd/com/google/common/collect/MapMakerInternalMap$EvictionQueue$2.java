package com.google.common.collect;

class MapMakerInternalMap$EvictionQueue$2
  extends AbstractLinkedIterator<MapMakerInternalMap.ReferenceEntry<K, V>>
{
  MapMakerInternalMap$EvictionQueue$2(MapMakerInternalMap.EvictionQueue paramEvictionQueue, MapMakerInternalMap.ReferenceEntry paramReferenceEntry)
  {
    super(paramReferenceEntry);
  }
  
  protected MapMakerInternalMap.ReferenceEntry<K, V> computeNext(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    MapMakerInternalMap.ReferenceEntry localReferenceEntry = paramReferenceEntry.getNextEvictable();
    paramReferenceEntry = localReferenceEntry;
    if (localReferenceEntry == this$0.head) {
      paramReferenceEntry = null;
    }
    return paramReferenceEntry;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.EvictionQueue.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */