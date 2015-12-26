package com.google.common.collect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class ImmutableMap$Builder<K, V>
{
  final ArrayList<Map.Entry<K, V>> entries = Lists.newArrayList();
  
  private static <K, V> ImmutableMap<K, V> fromEntryList(List<Map.Entry<K, V>> paramList)
  {
    switch (paramList.size())
    {
    default: 
      return new RegularImmutableMap((Map.Entry[])paramList.toArray(new Map.Entry[paramList.size()]));
    case 0: 
      return ImmutableMap.of();
    }
    return new SingletonImmutableMap((Map.Entry)Iterables.getOnlyElement(paramList));
  }
  
  public ImmutableMap<K, V> build()
  {
    return fromEntryList(entries);
  }
  
  public Builder<K, V> put(K paramK, V paramV)
  {
    entries.add(ImmutableMap.entryOf(paramK, paramV));
    return this;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ImmutableMap.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */