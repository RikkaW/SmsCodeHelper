package com.google.common.collect;

import java.util.concurrent.ConcurrentMap;

public abstract class ForwardingConcurrentMap<K, V>
  extends ForwardingMap<K, V>
  implements ConcurrentMap<K, V>
{
  protected abstract ConcurrentMap<K, V> delegate();
  
  public V putIfAbsent(K paramK, V paramV)
  {
    return (V)delegate().putIfAbsent(paramK, paramV);
  }
  
  public boolean remove(Object paramObject1, Object paramObject2)
  {
    return delegate().remove(paramObject1, paramObject2);
  }
  
  public V replace(K paramK, V paramV)
  {
    return (V)delegate().replace(paramK, paramV);
  }
  
  public boolean replace(K paramK, V paramV1, V paramV2)
  {
    return delegate().replace(paramK, paramV1, paramV2);
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ForwardingConcurrentMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */