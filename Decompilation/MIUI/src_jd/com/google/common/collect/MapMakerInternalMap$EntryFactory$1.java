package com.google.common.collect;

 enum MapMakerInternalMap$EntryFactory$1
{
  MapMakerInternalMap$EntryFactory$1()
  {
    super(paramString, paramInt, null);
  }
  
  <K, V> MapMakerInternalMap.ReferenceEntry<K, V> newEntry(MapMakerInternalMap.Segment<K, V> paramSegment, K paramK, int paramInt, MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    return new MapMakerInternalMap.StrongEntry(paramK, paramInt, paramReferenceEntry);
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.EntryFactory.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */