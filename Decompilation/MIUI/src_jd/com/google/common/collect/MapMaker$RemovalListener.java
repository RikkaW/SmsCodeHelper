package com.google.common.collect;

abstract interface MapMaker$RemovalListener<K, V>
{
  public abstract void onRemoval(MapMaker.RemovalNotification<K, V> paramRemovalNotification);
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMaker.RemovalListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */