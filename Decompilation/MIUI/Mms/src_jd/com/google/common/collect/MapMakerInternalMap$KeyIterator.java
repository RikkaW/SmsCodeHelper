package com.google.common.collect;

import java.util.Iterator;

final class MapMakerInternalMap$KeyIterator
  extends MapMakerInternalMap<K, V>.HashIterator
  implements Iterator<K>
{
  MapMakerInternalMap$KeyIterator(MapMakerInternalMap paramMapMakerInternalMap)
  {
    super(paramMapMakerInternalMap);
  }
  
  public K next()
  {
    return (K)nextEntry().getKey();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.KeyIterator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */