package com.google.common.collect;

class SingletonImmutableMap$Values<V>
  extends ImmutableCollection<V>
{
  final V singleValue;
  
  SingletonImmutableMap$Values(V paramV)
  {
    singleValue = paramV;
  }
  
  public boolean contains(Object paramObject)
  {
    return singleValue.equals(paramObject);
  }
  
  public boolean isEmpty()
  {
    return false;
  }
  
  public UnmodifiableIterator<V> iterator()
  {
    return Iterators.singletonIterator(singleValue);
  }
  
  public int size()
  {
    return 1;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.SingletonImmutableMap.Values
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */