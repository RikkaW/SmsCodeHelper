package com.google.common.collect;

final class RegularImmutableMap$NonTerminalEntry<K, V>
  extends ImmutableEntry<K, V>
  implements RegularImmutableMap.LinkedEntry<K, V>
{
  final RegularImmutableMap.LinkedEntry<K, V> next;
  
  RegularImmutableMap$NonTerminalEntry(K paramK, V paramV, RegularImmutableMap.LinkedEntry<K, V> paramLinkedEntry)
  {
    super(paramK, paramV);
    next = paramLinkedEntry;
  }
  
  public RegularImmutableMap.LinkedEntry<K, V> next()
  {
    return next;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.RegularImmutableMap.NonTerminalEntry
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */