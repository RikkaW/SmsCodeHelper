package com.google.common.collect;

import java.util.Map;

final class RegularImmutableTable$SparseImmutableTable<R, C, V>
  extends RegularImmutableTable<R, C, V>
{
  private final ImmutableMap<R, Map<C, V>> rowMap;
  
  public ImmutableMap<R, Map<C, V>> rowMap()
  {
    return rowMap;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.RegularImmutableTable.SparseImmutableTable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */