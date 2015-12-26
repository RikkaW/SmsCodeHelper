package com.google.common.collect;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class SingletonImmutableMap<K, V>
  extends ImmutableMap<K, V>
{
  private transient Map.Entry<K, V> entry;
  private transient ImmutableSet<Map.Entry<K, V>> entrySet;
  private transient ImmutableSet<K> keySet;
  final transient K singleKey;
  final transient V singleValue;
  private transient ImmutableCollection<V> values;
  
  SingletonImmutableMap(Map.Entry<K, V> paramEntry)
  {
    entry = paramEntry;
    singleKey = paramEntry.getKey();
    singleValue = paramEntry.getValue();
  }
  
  private Map.Entry<K, V> entry()
  {
    Map.Entry localEntry2 = entry;
    Map.Entry localEntry1 = localEntry2;
    if (localEntry2 == null)
    {
      localEntry1 = Maps.immutableEntry(singleKey, singleValue);
      entry = localEntry1;
    }
    return localEntry1;
  }
  
  public boolean containsKey(Object paramObject)
  {
    return singleKey.equals(paramObject);
  }
  
  public boolean containsValue(Object paramObject)
  {
    return singleValue.equals(paramObject);
  }
  
  public ImmutableSet<Map.Entry<K, V>> entrySet()
  {
    ImmutableSet localImmutableSet2 = entrySet;
    ImmutableSet localImmutableSet1 = localImmutableSet2;
    if (localImmutableSet2 == null)
    {
      localImmutableSet1 = ImmutableSet.of(entry());
      entrySet = localImmutableSet1;
    }
    return localImmutableSet1;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof Map)) {
        break;
      }
      paramObject = (Map)paramObject;
      if (((Map)paramObject).size() != 1) {
        return false;
      }
      paramObject = (Map.Entry)((Map)paramObject).entrySet().iterator().next();
    } while ((singleKey.equals(((Map.Entry)paramObject).getKey())) && (singleValue.equals(((Map.Entry)paramObject).getValue())));
    return false;
    return false;
  }
  
  public V get(Object paramObject)
  {
    if (singleKey.equals(paramObject)) {
      return (V)singleValue;
    }
    return null;
  }
  
  public int hashCode()
  {
    return singleKey.hashCode() ^ singleValue.hashCode();
  }
  
  public boolean isEmpty()
  {
    return false;
  }
  
  public ImmutableSet<K> keySet()
  {
    ImmutableSet localImmutableSet2 = keySet;
    ImmutableSet localImmutableSet1 = localImmutableSet2;
    if (localImmutableSet2 == null)
    {
      localImmutableSet1 = ImmutableSet.of(singleKey);
      keySet = localImmutableSet1;
    }
    return localImmutableSet1;
  }
  
  public int size()
  {
    return 1;
  }
  
  public String toString()
  {
    return '{' + singleKey.toString() + '=' + singleValue.toString() + '}';
  }
  
  public ImmutableCollection<V> values()
  {
    ImmutableCollection localImmutableCollection = values;
    Object localObject = localImmutableCollection;
    if (localImmutableCollection == null)
    {
      localObject = new Values(singleValue);
      values = ((ImmutableCollection)localObject);
    }
    return (ImmutableCollection<V>)localObject;
  }
  
  private static class Values<V>
    extends ImmutableCollection<V>
  {
    final V singleValue;
    
    Values(V paramV)
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
}

/* Location:
 * Qualified Name:     com.google.common.collect.SingletonImmutableMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */