package com.google.common.collect;

import com.google.common.base.Equivalence;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;

final class MapMakerInternalMap$EntrySet
  extends AbstractSet<Map.Entry<K, V>>
{
  MapMakerInternalMap$EntrySet(MapMakerInternalMap paramMapMakerInternalMap) {}
  
  public void clear()
  {
    this$0.clear();
  }
  
  public boolean contains(Object paramObject)
  {
    if (!(paramObject instanceof Map.Entry)) {}
    Object localObject;
    do
    {
      do
      {
        return false;
        paramObject = (Map.Entry)paramObject;
        localObject = ((Map.Entry)paramObject).getKey();
      } while (localObject == null);
      localObject = this$0.get(localObject);
    } while ((localObject == null) || (!this$0.valueEquivalence.equivalent(((Map.Entry)paramObject).getValue(), localObject)));
    return true;
  }
  
  public boolean isEmpty()
  {
    return this$0.isEmpty();
  }
  
  public Iterator<Map.Entry<K, V>> iterator()
  {
    return new MapMakerInternalMap.EntryIterator(this$0);
  }
  
  public boolean remove(Object paramObject)
  {
    if (!(paramObject instanceof Map.Entry)) {}
    Object localObject;
    do
    {
      return false;
      paramObject = (Map.Entry)paramObject;
      localObject = ((Map.Entry)paramObject).getKey();
    } while ((localObject == null) || (!this$0.remove(localObject, ((Map.Entry)paramObject).getValue())));
    return true;
  }
  
  public int size()
  {
    return this$0.size();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.EntrySet
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */