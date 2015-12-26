package com.google.common.collect;

import java.util.AbstractCollection;
import java.util.Iterator;

final class MapMakerInternalMap$Values
  extends AbstractCollection<V>
{
  MapMakerInternalMap$Values(MapMakerInternalMap paramMapMakerInternalMap) {}
  
  public void clear()
  {
    this$0.clear();
  }
  
  public boolean contains(Object paramObject)
  {
    return this$0.containsValue(paramObject);
  }
  
  public boolean isEmpty()
  {
    return this$0.isEmpty();
  }
  
  public Iterator<V> iterator()
  {
    return new MapMakerInternalMap.ValueIterator(this$0);
  }
  
  public int size()
  {
    return this$0.size();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.Values
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */