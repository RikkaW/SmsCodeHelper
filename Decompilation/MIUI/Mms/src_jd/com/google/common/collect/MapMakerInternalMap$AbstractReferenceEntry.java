package com.google.common.collect;

abstract class MapMakerInternalMap$AbstractReferenceEntry<K, V>
  implements MapMakerInternalMap.ReferenceEntry<K, V>
{
  public long getExpirationTime()
  {
    throw new UnsupportedOperationException();
  }
  
  public int getHash()
  {
    throw new UnsupportedOperationException();
  }
  
  public K getKey()
  {
    throw new UnsupportedOperationException();
  }
  
  public MapMakerInternalMap.ReferenceEntry<K, V> getNext()
  {
    throw new UnsupportedOperationException();
  }
  
  public MapMakerInternalMap.ReferenceEntry<K, V> getNextEvictable()
  {
    throw new UnsupportedOperationException();
  }
  
  public MapMakerInternalMap.ReferenceEntry<K, V> getNextExpirable()
  {
    throw new UnsupportedOperationException();
  }
  
  public MapMakerInternalMap.ReferenceEntry<K, V> getPreviousEvictable()
  {
    throw new UnsupportedOperationException();
  }
  
  public MapMakerInternalMap.ReferenceEntry<K, V> getPreviousExpirable()
  {
    throw new UnsupportedOperationException();
  }
  
  public MapMakerInternalMap.ValueReference<K, V> getValueReference()
  {
    throw new UnsupportedOperationException();
  }
  
  public void setExpirationTime(long paramLong)
  {
    throw new UnsupportedOperationException();
  }
  
  public void setNextEvictable(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    throw new UnsupportedOperationException();
  }
  
  public void setNextExpirable(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    throw new UnsupportedOperationException();
  }
  
  public void setPreviousEvictable(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    throw new UnsupportedOperationException();
  }
  
  public void setPreviousExpirable(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    throw new UnsupportedOperationException();
  }
  
  public void setValueReference(MapMakerInternalMap.ValueReference<K, V> paramValueReference)
  {
    throw new UnsupportedOperationException();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.AbstractReferenceEntry
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */