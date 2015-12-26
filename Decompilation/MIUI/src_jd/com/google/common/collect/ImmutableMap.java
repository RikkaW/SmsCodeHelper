package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class ImmutableMap<K, V>
  implements Map<K, V>, Serializable
{
  public static <K, V> Builder<K, V> builder()
  {
    return new Builder();
  }
  
  static <K, V> Map.Entry<K, V> entryOf(K paramK, V paramV)
  {
    return Maps.immutableEntry(Preconditions.checkNotNull(paramK, "null key"), Preconditions.checkNotNull(paramV, "null value"));
  }
  
  public static <K, V> ImmutableMap<K, V> of()
  {
    return EmptyImmutableMap.INSTANCE;
  }
  
  public final void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean containsKey(Object paramObject)
  {
    return get(paramObject) != null;
  }
  
  public abstract ImmutableSet<Map.Entry<K, V>> entrySet();
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if ((paramObject instanceof Map))
    {
      paramObject = (Map)paramObject;
      return entrySet().equals(((Map)paramObject).entrySet());
    }
    return false;
  }
  
  public abstract V get(Object paramObject);
  
  public int hashCode()
  {
    return entrySet().hashCode();
  }
  
  public boolean isEmpty()
  {
    return size() == 0;
  }
  
  public abstract ImmutableSet<K> keySet();
  
  public final V put(K paramK, V paramV)
  {
    throw new UnsupportedOperationException();
  }
  
  public final void putAll(Map<? extends K, ? extends V> paramMap)
  {
    throw new UnsupportedOperationException();
  }
  
  public final V remove(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }
  
  public String toString()
  {
    return Maps.toStringImpl(this);
  }
  
  public abstract ImmutableCollection<V> values();
  
  Object writeReplace()
  {
    return new SerializedForm(this);
  }
  
  public static class Builder<K, V>
  {
    final ArrayList<Map.Entry<K, V>> entries = Lists.newArrayList();
    
    private static <K, V> ImmutableMap<K, V> fromEntryList(List<Map.Entry<K, V>> paramList)
    {
      switch (paramList.size())
      {
      default: 
        return new RegularImmutableMap((Map.Entry[])paramList.toArray(new Map.Entry[paramList.size()]));
      case 0: 
        return ImmutableMap.of();
      }
      return new SingletonImmutableMap((Map.Entry)Iterables.getOnlyElement(paramList));
    }
    
    public ImmutableMap<K, V> build()
    {
      return fromEntryList(entries);
    }
    
    public Builder<K, V> put(K paramK, V paramV)
    {
      entries.add(ImmutableMap.entryOf(paramK, paramV));
      return this;
    }
  }
  
  static class SerializedForm
    implements Serializable
  {
    private static final long serialVersionUID = 0L;
    private final Object[] keys;
    private final Object[] values;
    
    SerializedForm(ImmutableMap<?, ?> paramImmutableMap)
    {
      keys = new Object[paramImmutableMap.size()];
      values = new Object[paramImmutableMap.size()];
      int i = 0;
      paramImmutableMap = paramImmutableMap.entrySet().iterator();
      while (paramImmutableMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramImmutableMap.next();
        keys[i] = localEntry.getKey();
        values[i] = localEntry.getValue();
        i += 1;
      }
    }
    
    Object createMap(ImmutableMap.Builder<Object, Object> paramBuilder)
    {
      int i = 0;
      while (i < keys.length)
      {
        paramBuilder.put(keys[i], values[i]);
        i += 1;
      }
      return paramBuilder.build();
    }
    
    Object readResolve()
    {
      return createMap(new ImmutableMap.Builder());
    }
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ImmutableMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */