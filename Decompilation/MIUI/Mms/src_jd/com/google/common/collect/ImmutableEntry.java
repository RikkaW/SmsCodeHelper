package com.google.common.collect;

import java.io.Serializable;

class ImmutableEntry<K, V>
  extends AbstractMapEntry<K, V>
  implements Serializable
{
  private static final long serialVersionUID = 0L;
  private final K key;
  private final V value;
  
  ImmutableEntry(K paramK, V paramV)
  {
    key = paramK;
    value = paramV;
  }
  
  public K getKey()
  {
    return (K)key;
  }
  
  public V getValue()
  {
    return (V)value;
  }
  
  public final V setValue(V paramV)
  {
    throw new UnsupportedOperationException();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ImmutableEntry
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */