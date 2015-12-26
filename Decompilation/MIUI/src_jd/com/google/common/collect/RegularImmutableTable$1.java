package com.google.common.collect;

import com.google.common.base.Function;

final class RegularImmutableTable$1
  implements Function<Table.Cell<Object, Object, Object>, Object>
{
  public Object apply(Table.Cell<Object, Object, Object> paramCell)
  {
    return paramCell.getValue();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.RegularImmutableTable.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */