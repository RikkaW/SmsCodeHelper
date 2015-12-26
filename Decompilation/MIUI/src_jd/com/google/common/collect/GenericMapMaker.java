package com.google.common.collect;

import com.google.common.base.Objects;

public abstract class GenericMapMaker<K0, V0>
{
  MapMaker.RemovalListener<K0, V0> removalListener;
  
  <K extends K0, V extends V0> MapMaker.RemovalListener<K, V> getRemovalListener()
  {
    return (MapMaker.RemovalListener)Objects.firstNonNull(removalListener, NullListener.INSTANCE);
  }
  
  static enum NullListener
    implements MapMaker.RemovalListener<Object, Object>
  {
    INSTANCE;
    
    private NullListener() {}
    
    public void onRemoval(MapMaker.RemovalNotification<Object, Object> paramRemovalNotification) {}
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.GenericMapMaker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */