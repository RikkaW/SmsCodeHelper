package com.google.common.collect;

import java.util.Map;

final class RegularImmutableTable$DenseImmutableTable<R, C, V>
  extends RegularImmutableTable<R, C, V>
{
  private final ImmutableBiMap<C, Integer> columnKeyToIndex;
  private final ImmutableBiMap<R, Integer> rowKeyToIndex;
  private volatile transient ImmutableMap<R, Map<C, V>> rowMap;
  private final V[][] values;
  
  private ImmutableMap<R, Map<C, V>> makeRowMap()
  {
    ImmutableMap.Builder localBuilder1 = ImmutableMap.builder();
    int i = 0;
    while (i < values.length)
    {
      Object[] arrayOfObject = values[i];
      ImmutableMap.Builder localBuilder2 = ImmutableMap.builder();
      int j = 0;
      while (j < arrayOfObject.length)
      {
        Object localObject = arrayOfObject[j];
        if (localObject != null) {
          localBuilder2.put(columnKeyToIndex.inverse().get(Integer.valueOf(j)), localObject);
        }
        j += 1;
      }
      localBuilder1.put(rowKeyToIndex.inverse().get(Integer.valueOf(i)), localBuilder2.build());
      i += 1;
    }
    return localBuilder1.build();
  }
  
  public ImmutableMap<R, Map<C, V>> rowMap()
  {
    ImmutableMap localImmutableMap2 = rowMap;
    ImmutableMap localImmutableMap1 = localImmutableMap2;
    if (localImmutableMap2 == null)
    {
      localImmutableMap1 = makeRowMap();
      rowMap = localImmutableMap1;
    }
    return localImmutableMap1;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.RegularImmutableTable.DenseImmutableTable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */