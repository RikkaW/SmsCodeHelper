package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReferenceArray;

abstract class MapMakerInternalMap$HashIterator
{
  MapMakerInternalMap.Segment<K, V> currentSegment;
  AtomicReferenceArray<MapMakerInternalMap.ReferenceEntry<K, V>> currentTable;
  MapMakerInternalMap<K, V>.WriteThroughEntry lastReturned;
  MapMakerInternalMap.ReferenceEntry<K, V> nextEntry;
  MapMakerInternalMap<K, V>.WriteThroughEntry nextExternal;
  int nextSegmentIndex;
  int nextTableIndex;
  
  MapMakerInternalMap$HashIterator(MapMakerInternalMap paramMapMakerInternalMap)
  {
    nextSegmentIndex = (segments.length - 1);
    nextTableIndex = -1;
    advance();
  }
  
  final void advance()
  {
    nextExternal = null;
    if (nextInChain()) {}
    do
    {
      do
      {
        do
        {
          ;;
          while (nextInTable()) {}
        } while (nextSegmentIndex < 0);
        MapMakerInternalMap.Segment[] arrayOfSegment = this$0.segments;
        int i = nextSegmentIndex;
        nextSegmentIndex = (i - 1);
        currentSegment = arrayOfSegment[i];
      } while (currentSegment.count == 0);
      currentTable = currentSegment.table;
      nextTableIndex = (currentTable.length() - 1);
    } while (!nextInTable());
  }
  
  boolean advanceTo(MapMakerInternalMap.ReferenceEntry<K, V> paramReferenceEntry)
  {
    try
    {
      Object localObject = paramReferenceEntry.getKey();
      paramReferenceEntry = this$0.getLiveValue(paramReferenceEntry);
      if (paramReferenceEntry != null)
      {
        nextExternal = new MapMakerInternalMap.WriteThroughEntry(this$0, localObject, paramReferenceEntry);
        return true;
      }
      return false;
    }
    finally
    {
      currentSegment.postReadCleanup();
    }
  }
  
  public boolean hasNext()
  {
    return nextExternal != null;
  }
  
  MapMakerInternalMap<K, V>.WriteThroughEntry nextEntry()
  {
    if (nextExternal == null) {
      throw new NoSuchElementException();
    }
    lastReturned = nextExternal;
    advance();
    return lastReturned;
  }
  
  boolean nextInChain()
  {
    if (nextEntry != null) {
      for (nextEntry = nextEntry.getNext(); nextEntry != null; nextEntry = nextEntry.getNext()) {
        if (advanceTo(nextEntry)) {
          return true;
        }
      }
    }
    return false;
  }
  
  boolean nextInTable()
  {
    while (nextTableIndex >= 0)
    {
      Object localObject = currentTable;
      int i = nextTableIndex;
      nextTableIndex = (i - 1);
      localObject = (MapMakerInternalMap.ReferenceEntry)((AtomicReferenceArray)localObject).get(i);
      nextEntry = ((MapMakerInternalMap.ReferenceEntry)localObject);
      if ((localObject != null) && ((advanceTo(nextEntry)) || (nextInChain()))) {
        return true;
      }
    }
    return false;
  }
  
  public void remove()
  {
    if (lastReturned != null) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkState(bool);
      this$0.remove(lastReturned.getKey());
      lastReturned = null;
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.HashIterator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */