package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

class MapMaker$NullConcurrentMap<K, V>
  extends AbstractMap<K, V>
  implements ConcurrentMap<K, V>, Serializable
{
  private static final long serialVersionUID = 0L;
  private final MapMaker.RemovalCause removalCause;
  private final MapMaker.RemovalListener<K, V> removalListener;
  
  MapMaker$NullConcurrentMap(MapMaker paramMapMaker)
  {
    removalListener = paramMapMaker.getRemovalListener();
    removalCause = nullRemovalCause;
  }
  
  public boolean containsKey(Object paramObject)
  {
    return false;
  }
  
  public boolean containsValue(Object paramObject)
  {
    return false;
  }
  
  public Set<Map.Entry<K, V>> entrySet()
  {
    return Collections.emptySet();
  }
  
  public V get(Object paramObject)
  {
    return null;
  }
  
  void notifyRemoval(K paramK, V paramV)
  {
    paramK = new MapMaker.RemovalNotification(paramK, paramV, removalCause);
    removalListener.onRemoval(paramK);
  }
  
  public V put(K paramK, V paramV)
  {
    Preconditions.checkNotNull(paramK);
    Preconditions.checkNotNull(paramV);
    notifyRemoval(paramK, paramV);
    return null;
  }
  
  public V putIfAbsent(K paramK, V paramV)
  {
    return (V)put(paramK, paramV);
  }
  
  public V remove(Object paramObject)
  {
    return null;
  }
  
  public boolean remove(Object paramObject1, Object paramObject2)
  {
    return false;
  }
  
  public V replace(K paramK, V paramV)
  {
    Preconditions.checkNotNull(paramK);
    Preconditions.checkNotNull(paramV);
    return null;
  }
  
  public boolean replace(K paramK, V paramV1, V paramV2)
  {
    Preconditions.checkNotNull(paramK);
    Preconditions.checkNotNull(paramV2);
    return false;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMaker.NullConcurrentMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */