package com.google.common.collect;

import java.util.Map.Entry;

abstract interface RegularImmutableMap$LinkedEntry<K, V>
  extends Map.Entry<K, V>
{
  public abstract LinkedEntry<K, V> next();
}

/* Location:
 * Qualified Name:     com.google.common.collect.RegularImmutableMap.LinkedEntry
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */