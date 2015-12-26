package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Map;

abstract class RegularImmutableTable<R, C, V>
  extends ImmutableTable<R, C, V>
{
  private static final Function<Table.Cell<Object, Object, Object>, Object> GET_VALUE_FUNCTION = new Function()
  {
    public Object apply(Table.Cell<Object, Object, Object> paramAnonymousCell)
    {
      return paramAnonymousCell.getValue();
    }
  };
  private final ImmutableSet<Table.Cell<R, C, V>> cellSet;
  
  public final ImmutableSet<Table.Cell<R, C, V>> cellSet()
  {
    return cellSet;
  }
  
  static final class DenseImmutableTable<R, C, V>
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
  
  static final class SparseImmutableTable<R, C, V>
    extends RegularImmutableTable<R, C, V>
  {
    private final ImmutableMap<R, Map<C, V>> rowMap;
    
    public ImmutableMap<R, Map<C, V>> rowMap()
    {
      return rowMap;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.RegularImmutableTable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */