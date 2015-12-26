package com.google.common.collect;

import java.util.AbstractQueue;
import java.util.Iterator;

final class MapMakerInternalMap$ExpirationQueue<K, V>
  extends AbstractQueue<MapMakerInternalMap.ReferenceEntry<K, V>>
{
  final MapMakerInternalMap.ReferenceEntry<K, V> head = new MapMakerInternalMap.AbstractReferenceEntry()
  {
    MapMakerInternalMap.ReferenceEntry<K, V> nextExpirable = this;
    MapMakerInternalMap.ReferenceEntry<K, V> previousExpirable = this;
    
    public long getExpirationTime()
    {
      return Long.MAX_VALUE;
    }
    
    public MapMakerInternalMap.ReferenceEntry<K, V> getNextExpirable()
    {
      return nextExpirable;
    }
    
    public MapMakerInternalMap.ReferenceEntry<K, V> getPreviousExpirable()
    {
      return previousExpirable;
    }
    
    public void setExpirationTime(long paramAnonymousLong) {}
    
    public void setNextExpirable(MapMakerInternalMap.ReferenceEntry<K, V> paramAnonymousReferenceEntry)
    {
      nextExpirable = paramAnonymousReferenceEntry;
    }
    
    public void setPreviousExpirable(MapMakerInternalMap.ReferenceEntry<K, V> paramAnonymousReferenceEntry)
    {
      previousExpirable = paramAnonymousReferenceEntry;
    }
  };
  
  public void clear()
  {
    MapMakerInternalMap.ReferenceEntry localReferenceEntry;
    for (Object localObject = head.getNextExpirable(); localObject != head; localObject = localReferenceEntry)
    {
      localReferenceEntry = ((MapMakerInternalMap.ReferenceEntry)localObject).getNextExpirable();
      MapMakerInternalMap.nullifyExpirable((MapMakerInternalMap.ReferenceEntry)localObject);
    }
    head.setNextExpirable(head);
    head.setPreviousExpirable(head);
  }
  
  public boolean contains(Object paramObject)
  {
    return ((MapMakerInternalMap.ReferenceEntry)paramObject).getNextExpirable() != MapMakerInternalMap.NullEntry.INSTANCE;
  }
  
  public boolean isEmpty()
  {
    return head.getNextExpirable() == head;
  }
  
  public Iterator<MapMakerInternalMap.ReferenceEntry<K, V>> iterator()
  {
    new AbstractLinkedIterator(peek())
    {
      protected MapMakerInternalMap.ReferenceEntry<K, V> computeNext(MapMakerInternalMap.ReferenceEntry<K, V> paramAnonymousReferenceEntry)
      {
        MapMakerInternalMap.ReferenceEntry localReferenceEntry = paramAnonymousReferenceEntry.getNextExpirable();
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
    MapMakerInternalMap.connectExpirables(paramReferenceEntry.getPreviousExpirable(), paramReferenceEntry.getNextExpirable());
    MapMakerInternalMap.connectExpirables(head.getPreviousExpirable(), paramReferenceEntry);
    MapMakerInternalMap.connectExpirables(paramReferenceEntry, head);
    return true;
  }
  
  public MapMakerInternalMap.ReferenceEntry<K, V> peek()
  {
    MapMakerInternalMap.ReferenceEntry localReferenceEntry2 = head.getNextExpirable();
    MapMakerInternalMap.ReferenceEntry localReferenceEntry1 = localReferenceEntry2;
    if (localReferenceEntry2 == head) {
      localReferenceEntry1 = null;
    }
    return localReferenceEntry1;
  }
  
  public MapMakerInternalMap.ReferenceEntry<K, V> poll()
  {
    MapMakerInternalMap.ReferenceEntry localReferenceEntry = head.getNextExpirable();
    if (localReferenceEntry == head) {
      return null;
    }
    remove(localReferenceEntry);
    return localReferenceEntry;
  }
  
  public boolean remove(Object paramObject)
  {
    paramObject = (MapMakerInternalMap.ReferenceEntry)paramObject;
    MapMakerInternalMap.ReferenceEntry localReferenceEntry1 = ((MapMakerInternalMap.ReferenceEntry)paramObject).getPreviousExpirable();
    MapMakerInternalMap.ReferenceEntry localReferenceEntry2 = ((MapMakerInternalMap.ReferenceEntry)paramObject).getNextExpirable();
    MapMakerInternalMap.connectExpirables(localReferenceEntry1, localReferenceEntry2);
    MapMakerInternalMap.nullifyExpirable((MapMakerInternalMap.ReferenceEntry)paramObject);
    return localReferenceEntry2 != MapMakerInternalMap.NullEntry.INSTANCE;
  }
  
  public int size()
  {
    int i = 0;
    for (MapMakerInternalMap.ReferenceEntry localReferenceEntry = head.getNextExpirable(); localReferenceEntry != head; localReferenceEntry = localReferenceEntry.getNextExpirable()) {
      i += 1;
    }
    return i;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.ExpirationQueue
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */