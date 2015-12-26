package com.google.common.collect;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Synchronized$SynchronizedMap<K, V>
  extends Synchronized.SynchronizedObject
  implements Map<K, V>
{
  private static final long serialVersionUID = 0L;
  transient Set<Map.Entry<K, V>> entrySet;
  transient Set<K> keySet;
  transient Collection<V> values;
  
  Synchronized$SynchronizedMap(Map<K, V> paramMap, Object paramObject)
  {
    super(paramMap, paramObject);
  }
  
  public void clear()
  {
    synchronized (mutex)
    {
      delegate().clear();
      return;
    }
  }
  
  public boolean containsKey(Object paramObject)
  {
    synchronized (mutex)
    {
      boolean bool = delegate().containsKey(paramObject);
      return bool;
    }
  }
  
  public boolean containsValue(Object paramObject)
  {
    synchronized (mutex)
    {
      boolean bool = delegate().containsValue(paramObject);
      return bool;
    }
  }
  
  Map<K, V> delegate()
  {
    return (Map)super.delegate();
  }
  
  public Set<Map.Entry<K, V>> entrySet()
  {
    synchronized (mutex)
    {
      if (entrySet == null) {
        entrySet = Synchronized.set(delegate().entrySet(), mutex);
      }
      Set localSet = entrySet;
      return localSet;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    synchronized (mutex)
    {
      boolean bool = delegate().equals(paramObject);
      return bool;
    }
  }
  
  public V get(Object paramObject)
  {
    synchronized (mutex)
    {
      paramObject = delegate().get(paramObject);
      return (V)paramObject;
    }
  }
  
  public int hashCode()
  {
    synchronized (mutex)
    {
      int i = delegate().hashCode();
      return i;
    }
  }
  
  public boolean isEmpty()
  {
    synchronized (mutex)
    {
      boolean bool = delegate().isEmpty();
      return bool;
    }
  }
  
  public Set<K> keySet()
  {
    synchronized (mutex)
    {
      if (keySet == null) {
        keySet = Synchronized.set(delegate().keySet(), mutex);
      }
      Set localSet = keySet;
      return localSet;
    }
  }
  
  public V put(K paramK, V paramV)
  {
    synchronized (mutex)
    {
      paramK = delegate().put(paramK, paramV);
      return paramK;
    }
  }
  
  public void putAll(Map<? extends K, ? extends V> paramMap)
  {
    synchronized (mutex)
    {
      delegate().putAll(paramMap);
      return;
    }
  }
  
  public V remove(Object paramObject)
  {
    synchronized (mutex)
    {
      paramObject = delegate().remove(paramObject);
      return (V)paramObject;
    }
  }
  
  public int size()
  {
    synchronized (mutex)
    {
      int i = delegate().size();
      return i;
    }
  }
  
  public Collection<V> values()
  {
    synchronized (mutex)
    {
      if (values == null) {
        values = Synchronized.access$500(delegate().values(), mutex);
      }
      Collection localCollection = values;
      return localCollection;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Synchronized.SynchronizedMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */