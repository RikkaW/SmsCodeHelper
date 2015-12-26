package com.google.common.collect;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class ForwardingMap<K, V>
  extends ForwardingObject
  implements Map<K, V>
{
  public void clear()
  {
    delegate().clear();
  }
  
  public boolean containsKey(Object paramObject)
  {
    return delegate().containsKey(paramObject);
  }
  
  public boolean containsValue(Object paramObject)
  {
    return delegate().containsValue(paramObject);
  }
  
  protected abstract Map<K, V> delegate();
  
  public Set<Map.Entry<K, V>> entrySet()
  {
    return delegate().entrySet();
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (delegate().equals(paramObject));
  }
  
  public V get(Object paramObject)
  {
    return (V)delegate().get(paramObject);
  }
  
  public int hashCode()
  {
    return delegate().hashCode();
  }
  
  public boolean isEmpty()
  {
    return delegate().isEmpty();
  }
  
  public Set<K> keySet()
  {
    return delegate().keySet();
  }
  
  public V put(K paramK, V paramV)
  {
    return (V)delegate().put(paramK, paramV);
  }
  
  public void putAll(Map<? extends K, ? extends V> paramMap)
  {
    delegate().putAll(paramMap);
  }
  
  public V remove(Object paramObject)
  {
    return (V)delegate().remove(paramObject);
  }
  
  public int size()
  {
    return delegate().size();
  }
  
  public Collection<V> values()
  {
    return delegate().values();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ForwardingMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */