package com.google.common.collect;

class ImmutableBiMap$EmptyBiMap
  extends ImmutableBiMap<Object, Object>
{
  ImmutableMap<Object, Object> delegate()
  {
    return ImmutableMap.of();
  }
  
  public ImmutableBiMap<Object, Object> inverse()
  {
    return this;
  }
  
  Object readResolve()
  {
    return ImmutableBiMap.access$000();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ImmutableBiMap.EmptyBiMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */