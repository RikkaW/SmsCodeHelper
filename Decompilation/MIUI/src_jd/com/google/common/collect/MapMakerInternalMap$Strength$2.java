package com.google.common.collect;

import com.google.common.base.Equivalence;
import com.google.common.base.Equivalences;

 enum MapMakerInternalMap$Strength$2
{
  MapMakerInternalMap$Strength$2()
  {
    super(paramString, paramInt, null);
  }
  
  Equivalence<Object> defaultEquivalence()
  {
    return Equivalences.identity();
  }
  
  <K, V> MapMakerInternalMap.ValueReference<K, V> referenceValue(MapMakerInternalMap.Segment<K, V> paramSegment, MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry, V paramV)
  {
    return new MapMakerInternalMap.SoftValueReference(valueReferenceQueue, paramV, paramReferenceEntry);
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.Strength.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */