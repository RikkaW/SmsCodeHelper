package com.google.common.collect;

import java.util.AbstractSet;
import java.util.Iterator;

final class MapMakerInternalMap$KeySet
  extends AbstractSet<K>
{
  MapMakerInternalMap$KeySet(MapMakerInternalMap paramMapMakerInternalMap) {}
  
  public void clear()
  {
    this$0.clear();
  }
  
  public boolean contains(Object paramObject)
  {
    return this$0.containsKey(paramObject);
  }
  
  public boolean isEmpty()
  {
    return this$0.isEmpty();
  }
  
  public Iterator<K> iterator()
  {
    return new MapMakerInternalMap.KeyIterator(this$0);
  }
  
  public boolean remove(Object paramObject)
  {
    return this$0.remove(paramObject) != null;
  }
  
  public int size()
  {
    return this$0.size();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.KeySet
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */