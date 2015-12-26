package com.google.common.collect;

class RegularImmutableMap$Values<V>
  extends ImmutableCollection<V>
{
  final RegularImmutableMap<?, V> map;
  
  RegularImmutableMap$Values(RegularImmutableMap<?, V> paramRegularImmutableMap)
  {
    map = paramRegularImmutableMap;
  }
  
  public boolean contains(Object paramObject)
  {
    return map.containsValue(paramObject);
  }
  
  public UnmodifiableIterator<V> iterator()
  {
    new AbstractIndexedListIterator(RegularImmutableMap.access$000(map).length)
    {
      protected V get(int paramAnonymousInt)
      {
        return (V)RegularImmutableMap.access$000(map)[paramAnonymousInt].getValue();
      }
    };
  }
  
  public int size()
  {
    return RegularImmutableMap.access$000(map).length;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.RegularImmutableMap.Values
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */