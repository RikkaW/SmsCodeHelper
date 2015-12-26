package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class Synchronized
{
  private static <E> Collection<E> collection(Collection<E> paramCollection, Object paramObject)
  {
    return new SynchronizedCollection(paramCollection, paramObject, null);
  }
  
  static <K, V> Map<K, V> map(Map<K, V> paramMap, Object paramObject)
  {
    return new SynchronizedMap(paramMap, paramObject);
  }
  
  static <E> Set<E> set(Set<E> paramSet, Object paramObject)
  {
    return new SynchronizedSet(paramSet, paramObject);
  }
  
  static class SynchronizedBiMap<K, V>
    extends Synchronized.SynchronizedMap<K, V>
    implements BiMap<K, V>, Serializable
  {
    private static final long serialVersionUID = 0L;
    private transient Set<V> valueSet;
    
    BiMap<K, V> delegate()
    {
      return (BiMap)super.delegate();
    }
    
    public Set<V> values()
    {
      synchronized (mutex)
      {
        if (valueSet == null) {
          valueSet = Synchronized.set(delegate().values(), mutex);
        }
        Set localSet = valueSet;
        return localSet;
      }
    }
  }
  
  static class SynchronizedCollection<E>
    extends Synchronized.SynchronizedObject
    implements Collection<E>
  {
    private static final long serialVersionUID = 0L;
    
    private SynchronizedCollection(Collection<E> paramCollection, Object paramObject)
    {
      super(paramObject);
    }
    
    public boolean add(E paramE)
    {
      synchronized (mutex)
      {
        boolean bool = delegate().add(paramE);
        return bool;
      }
    }
    
    public boolean addAll(Collection<? extends E> paramCollection)
    {
      synchronized (mutex)
      {
        boolean bool = delegate().addAll(paramCollection);
        return bool;
      }
    }
    
    public void clear()
    {
      synchronized (mutex)
      {
        delegate().clear();
        return;
      }
    }
    
    public boolean contains(Object paramObject)
    {
      synchronized (mutex)
      {
        boolean bool = delegate().contains(paramObject);
        return bool;
      }
    }
    
    public boolean containsAll(Collection<?> paramCollection)
    {
      synchronized (mutex)
      {
        boolean bool = delegate().containsAll(paramCollection);
        return bool;
      }
    }
    
    Collection<E> delegate()
    {
      return (Collection)super.delegate();
    }
    
    public boolean isEmpty()
    {
      synchronized (mutex)
      {
        boolean bool = delegate().isEmpty();
        return bool;
      }
    }
    
    public Iterator<E> iterator()
    {
      return delegate().iterator();
    }
    
    public boolean remove(Object paramObject)
    {
      synchronized (mutex)
      {
        boolean bool = delegate().remove(paramObject);
        return bool;
      }
    }
    
    public boolean removeAll(Collection<?> paramCollection)
    {
      synchronized (mutex)
      {
        boolean bool = delegate().removeAll(paramCollection);
        return bool;
      }
    }
    
    public boolean retainAll(Collection<?> paramCollection)
    {
      synchronized (mutex)
      {
        boolean bool = delegate().retainAll(paramCollection);
        return bool;
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
    
    public Object[] toArray()
    {
      synchronized (mutex)
      {
        Object[] arrayOfObject = delegate().toArray();
        return arrayOfObject;
      }
    }
    
    public <T> T[] toArray(T[] paramArrayOfT)
    {
      synchronized (mutex)
      {
        paramArrayOfT = delegate().toArray(paramArrayOfT);
        return paramArrayOfT;
      }
    }
  }
  
  private static class SynchronizedMap<K, V>
    extends Synchronized.SynchronizedObject
    implements Map<K, V>
  {
    private static final long serialVersionUID = 0L;
    transient Set<Map.Entry<K, V>> entrySet;
    transient Set<K> keySet;
    transient Collection<V> values;
    
    SynchronizedMap(Map<K, V> paramMap, Object paramObject)
    {
      super(paramObject);
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
          values = Synchronized.collection(delegate().values(), mutex);
        }
        Collection localCollection = values;
        return localCollection;
      }
    }
  }
  
  static class SynchronizedObject
    implements Serializable
  {
    private static final long serialVersionUID = 0L;
    final Object delegate;
    final Object mutex;
    
    SynchronizedObject(Object paramObject1, Object paramObject2)
    {
      delegate = Preconditions.checkNotNull(paramObject1);
      paramObject1 = paramObject2;
      if (paramObject2 == null) {
        paramObject1 = this;
      }
      mutex = paramObject1;
    }
    
    private void writeObject(ObjectOutputStream paramObjectOutputStream)
      throws IOException
    {
      synchronized (mutex)
      {
        paramObjectOutputStream.defaultWriteObject();
        return;
      }
    }
    
    Object delegate()
    {
      return delegate;
    }
    
    public String toString()
    {
      synchronized (mutex)
      {
        String str = delegate.toString();
        return str;
      }
    }
  }
  
  static class SynchronizedSet<E>
    extends Synchronized.SynchronizedCollection<E>
    implements Set<E>
  {
    private static final long serialVersionUID = 0L;
    
    SynchronizedSet(Set<E> paramSet, Object paramObject)
    {
      super(paramObject, null);
    }
    
    Set<E> delegate()
    {
      return (Set)super.delegate();
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
    
    public int hashCode()
    {
      synchronized (mutex)
      {
        int i = delegate().hashCode();
        return i;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Synchronized
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */