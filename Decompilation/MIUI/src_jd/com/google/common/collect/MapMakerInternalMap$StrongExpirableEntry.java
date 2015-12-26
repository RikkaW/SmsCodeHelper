package com.google.common.collect;

final class MapMakerInternalMap$StrongExpirableEntry<K, V>
  extends MapMakerInternalMap.StrongEntry<K, V>
  implements MapMakerInternalMap.ReferenceEntry<K, V>
{
  MapMakerInternalMap.ReferenceEntry<K, V> nextExpirable = MapMakerInternalMap.nullEntry();
  MapMakerInternalMap.ReferenceEntry<K, V> previousExpirable = MapMakerInternalMap.nullEntry();
  volatile long time = Long.MAX_VALUE;
  
  MapMakerInternalMap$StrongExpirableEntry(K paramK, int paramInt, MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    super(paramK, paramInt, paramReferenceEntry);
  }
  
  public long getExpirationTime()
  {
    return time;
  }
  
  public MapMakerInternalMap.ReferenceEntry<K, V> getNextExpirable()
  {
    return nextExpirable;
  }
  
  public MapMakerInternalMap.ReferenceEntry<K, V> getPreviousExpirable()
  {
    return previousExpirable;
  }
  
  public void setExpirationTime(long paramLong)
  {
    time = paramLong;
  }
  
  public void setNextExpirable(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    nextExpirable = paramReferenceEntry;
  }
  
  public void setPreviousExpirable(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    previousExpirable = paramReferenceEntry;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.StrongExpirableEntry
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */