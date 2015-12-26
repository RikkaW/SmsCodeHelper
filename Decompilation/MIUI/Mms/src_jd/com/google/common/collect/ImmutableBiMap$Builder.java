package com.google.common.collect;

public final class ImmutableBiMap$Builder<K, V>
  extends ImmutableMap.Builder<K, V>
{
  public ImmutableBiMap<K, V> build()
  {
    ImmutableMap localImmutableMap = super.build();
    if (localImmutableMap.isEmpty()) {
      return ImmutableBiMap.of();
    }
    return new RegularImmutableBiMap(localImmutableMap);
  }
  
  public Builder<K, V> put(K paramK, V paramV)
  {
    super.put(paramK, paramV);
    return this;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ImmutableBiMap.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */