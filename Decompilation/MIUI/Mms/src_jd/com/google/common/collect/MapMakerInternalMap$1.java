package com.google.common.collect;

import java.lang.ref.ReferenceQueue;

final class MapMakerInternalMap$1
  implements MapMakerInternalMap.ValueReference<Object, Object>
{
  public void clear(MapMakerInternalMap.ValueReference<Object, Object> paramValueReference) {}
  
  public MapMakerInternalMap.ValueReference<Object, Object> copyFor(ReferenceQueue<Object> paramReferenceQueue, MapMakerInternalMap.ReferenceEntry<Object, Object> paramReferenceEntry)
  {
    return this;
  }
  
  public Object get()
  {
    return null;
  }
  
  public MapMakerInternalMap.ReferenceEntry<Object, Object> getEntry()
  {
    return null;
  }
  
  public boolean isComputingReference()
  {
    return false;
  }
  
  public Object waitForValue()
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMakerInternalMap.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */