package com.google.common.collect;

import java.util.AbstractQueue;
import java.util.Iterator;

final class MapMakerInternalMap$EvictionQueue<K, V>
  extends AbstractQueue<MapMakerInternalMap.ReferenceEntry<K, V>>
{
  final MapMakerInternalMap.ReferenceEntry<K, V> head = new MapMakerInternalMap.AbstractReferenceEntry()
  {
    MapMakerInternalMap.ReferenceEntry<K, V> nextEvictable = this;
    MapMakerInternalMap.ReferenceEntry<K, V> previousEvictable = this;
    
    public MapMakerInternalMap.ReferenceEntry<K, V> getNextEvictable()
    {
      return nextEvictable;
    }
    
    public MapMakerInternalMap.ReferenceEntry<K, V> getPreviousEvictable()
    {
      return previousEvictable;
    }
    
    public void setNextEvictable(MapMakerInternalMap.ReferenceEntry<K, V> paramAnonymousReferenceEntry)
    {
      nextEvictable = paramAnonymousReferenceEntry;
    }
    
    public void setPreviousEvictable(MapMakerInternalMap.ReferenceEntry<K, V> paramAnonymousReferenceEntry)
    {
      previousEvictable = paramAnonymousReferenceEntry;
    }
  };
  
  public void clear()
  {
    MapMakerInternalMap.ReferenceEntry localReferenceEntry;
    for (Object localObject = head.getNextEvictable(); localObject != head; localObject = localReferenceEntry)
    {
      localReferenceEntry = ((MapMakerInternalMap.ReferenceEntry)localObject).getNextEvictable();
      MapMakerInternalMap.nullifyEvictable((MapMakerInternalMap.ReferenceEntry)localObject);
    }
    head.setNextEvictable(head);
    head.setPreviousEvictable(head);
  }
  
  public boolean contains(Object paramObject)
  {
    return ((MapMakerInternalMap.ReferenceEntry)paramObject).getNextEvictable() != MapMakerInternalMap.NullEntry.INSTANCE;
  }
  
  public boolean isEmpty()
  {
    return head.getNextEvictable() == head;
  }
  
  public Iterator<MapMakerInternalMap.ReferenceEntry<K, V>> iterator()
  {
    new AbstractLinkedIterator(peek())
    {
      protected MapMakerInternalMap.ReferenceEntry<K, V> computeNext(MapMakerInternalMap.ReferenceEntry<K, V> paramAnonymousReferenceEntry)
      {
        MapMakerInternalMap.ReferenceEntry localReferenceEntry = paramAnonymousReferenceEntry.getNextEvictable();
        paramAnonymousReferenceEntry = localReferenceEntry;
        if (localReferenceEntry == head) {
          paramAnonymousReferenceEntry = null;
        }
        return paramAnonymousReferenceEntry;
      }
    };
  }
  
  public boolean offer(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    MapMakerInternalMap.connectEvictables(paramReferenceEntry.getPreviousEvictable(), paramReferenceEntry.getNextEvictable());
    MapMakerInternalMap.connectEvictables(head.getPreviousEvictable(), paramReferenceEntry);
    MapMakerInternalMap.connectEvictables(paramReferenceEntry, head);
    return true;
  }
  
  public MapMakerInternalMap.ReferenceEntry<K, V> peek()
  {
    MapMakerInternalMap.ReferenceEntry localReferenceEntry2 = head.getNextEvictable();
    MapMakerInternalMap.ReferenceEntry localReferenceEntry1 = localReferenceEntry2;
    if (localReferenceEntry2 == head) {
      localReferenceEntry1 = null;
    }
    return localReferenceEntry1;
  }
  
  public MapMakerInternalMap.ReferenceEntry<K, V> poll()
  {
    MapMakerInternalMap.ReferenceEntry localReferenceEntry = head.getNextEvictable();
    if (localReferenceEntry == head) {
      return null;
    }
    remove(localReferenceEntry);
    return localReferenceEntry;
  }
  
  public boolean remove(Object paramObject)
  {
    paramObject = (MapMakerInternalMap.ReferenceEntry)paramObject;
    MapMakerInternalMap.ReferenceEntry localReferenceEntry1 = ((MapMakerInternalMap.ReferenceEntry)paramObject).getPreviousEvictable();
    MapMakerInternalMap.ReferenceEntry localReferenceEntry2 = ((MapMakerInternalMap.ReferenceEntry)paramObject).getNextEvictable();
    MapMakerInternalMap.connectEvictables(localReferenceEntry1, localReferenceEntry2);
    MapMakerInternalMap.nullifyEvictable((MapMakerInternalMap.ReferenceEntry)paramObject);
    return localReferenceEntry2 != MapMakerInternalMap.NullEntry.INSTANCE;
  }
  
  public int size()
  {
    int i = 0;
    for (MapMakerInternalMap.ReferenceEntry localReferenceEntry = head.getNextEvictable(); localReferenceEntry != head; localReferenceEntry = localReferenceEntry.getNextEvictable()) {
      i += 1;
    }
    return i;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.EvictionQueue
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */