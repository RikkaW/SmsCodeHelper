package com.google.common.collect;

import java.util.Map.Entry;

class RegularImmutableMap$KeySet<K, V>
  extends ImmutableSet.TransformedImmutableSet<Map.Entry<K, V>, K>
{
  final RegularImmutableMap<K, V> map;
  
  RegularImmutableMap$KeySet(RegularImmutableMap<K, V> paramRegularImmutableMap)
  {
    super(RegularImmutableMap.access$000(paramRegularImmutableMap), RegularImmutableMap.access$100(paramRegularImmutableMap));
    map = paramRegularImmutableMap;
  }
  
  public boolean contains(Object paramObject)
  {
    return map.containsKey(paramObject);
  }
  
  K transform(Map.Entry<K, V> paramEntry)
  {
    return (K)paramEntry.getKey();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.RegularImmutableMap.KeySet
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */