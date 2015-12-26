package com.google.common.collect;

import java.util.Set;

public abstract interface Table<R, C, V>
{
  public abstract Set<Cell<R, C, V>> cellSet();
  
  public static abstract interface Cell<R, C, V>
  {
    public abstract V getValue();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Table
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */