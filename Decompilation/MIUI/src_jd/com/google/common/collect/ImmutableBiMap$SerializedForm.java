package com.google.common.collect;

class ImmutableBiMap$SerializedForm
  extends ImmutableMap.SerializedForm
{
  private static final long serialVersionUID = 0L;
  
  ImmutableBiMap$SerializedForm(ImmutableBiMap<?, ?> paramImmutableBiMap)
  {
    super(paramImmutableBiMap);
  }
  
  Object readResolve()
  {
    return createMap(new ImmutableBiMap.Builder());
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ImmutableBiMap.SerializedForm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */