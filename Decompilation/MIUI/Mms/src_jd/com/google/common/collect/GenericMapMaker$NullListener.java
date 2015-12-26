package com.google.common.collect;

 enum GenericMapMaker$NullListener
  implements MapMaker.RemovalListener<Object, Object>
{
  INSTANCE;
  
  private GenericMapMaker$NullListener() {}
  
  public void onRemoval(MapMaker.RemovalNotification<Object, Object> paramRemovalNotification) {}
}

/* Location:
 * Qualified Name:     com.google.common.collect.GenericMapMaker.NullListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */