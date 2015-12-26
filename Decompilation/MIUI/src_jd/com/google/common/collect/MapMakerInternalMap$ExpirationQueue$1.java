package com.google.common.collect;

class MapMakerInternalMap$ExpirationQueue$1
  extends MapMakerInternalMap.AbstractReferenceEntry<K, V>
{
  MapMakerInternalMap.ReferenceEntry<K, V> nextExpirable = this;
  MapMakerInternalMap.ReferenceEntry<K, V> previousExpirable = this;
  
  MapMakerInternalMap$ExpirationQueue$1(MapMakerInternalMap.ExpirationQueue paramExpirationQueue) {}
  
  public long getExpirationTime()
  {
    return Long.MAX_VALUE;
  }
  
  public MapMakerInternalMap.ReferenceEntry<K, V> getNextExpirable()
  {
    return nextExpirable;
  }
  
  public MapMakerInternalMap.ReferenceEntry<K, V> getPreviousExpirable()
  {
    return previousExpirable;
  }
  
  public void setExpirationTime(long paramLong) {}
  
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
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.ExpirationQueue.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */