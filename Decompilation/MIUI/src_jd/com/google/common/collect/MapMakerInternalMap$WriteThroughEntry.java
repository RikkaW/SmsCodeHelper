package com.google.common.collect;

import java.util.Map.Entry;

final class MapMakerInternalMap$WriteThroughEntry
  extends AbstractMapEntry<K, V>
{
  final K key;
  V value;
  
  MapMakerInternalMap$WriteThroughEntry(K paramK, V paramV)
  {
    key = paramV;
    Object localObject;
    value = localObject;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof Map.Entry))
    {
      paramObject = (Map.Entry)paramObject;
      bool1 = bool2;
      if (key.equals(((Map.Entry)paramObject).getKey()))
      {
        bool1 = bool2;
        if (value.equals(((Map.Entry)paramObject).getValue())) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public K getKey()
  {
    return (K)key;
  }
  
  public V getValue()
  {
    return (V)value;
  }
  
  public int hashCode()
  {
    return key.hashCode() ^ value.hashCode();
  }
  
  public V setValue(V paramV)
  {
    Object localObject = this$0.put(key, paramV);
    value = paramV;
    return (V)localObject;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.WriteThroughEntry
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */