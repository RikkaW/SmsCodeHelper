package com.google.common.collect;

final class RegularImmutableMap$TerminalEntry<K, V>
  extends ImmutableEntry<K, V>
  implements RegularImmutableMap.LinkedEntry<K, V>
{
  RegularImmutableMap$TerminalEntry(K paramK, V paramV)
  {
    super(paramK, paramV);
  }
  
  public RegularImmutableMap.LinkedEntry<K, V> next()
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.RegularImmutableMap.TerminalEntry
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */