package com.google.common.collect;

import java.util.Iterator;
import java.util.Map.Entry;

class RegularImmutableBiMap<K, V>
  extends ImmutableBiMap<K, V>
{
  final transient ImmutableMap<K, V> delegate;
  final transient ImmutableBiMap<V, K> inverse;
  
  RegularImmutableBiMap(ImmutableMap<K, V> paramImmutableMap)
  {
    delegate = paramImmutableMap;
    ImmutableMap.Builder localBuilder = ImmutableMap.builder();
    paramImmutableMap = paramImmutableMap.entrySet().iterator();
    while (paramImmutableMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramImmutableMap.next();
      localBuilder.put(localEntry.getValue(), localEntry.getKey());
    }
    inverse = new RegularImmutableBiMap(localBuilder.build(), this);
  }
  
  RegularImmutableBiMap(ImmutableMap<K, V> paramImmutableMap, ImmutableBiMap<V, K> paramImmutableBiMap)
  {
    delegate = paramImmutableMap;
    inverse = paramImmutableBiMap;
  }
  
  ImmutableMap<K, V> delegate()
  {
    return delegate;
  }
  
  public ImmutableBiMap<V, K> inverse()
  {
    return inverse;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.RegularImmutableBiMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */