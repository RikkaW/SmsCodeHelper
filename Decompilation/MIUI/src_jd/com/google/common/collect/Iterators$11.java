package com.google.common.collect;

final class Iterators$11
  extends AbstractIndexedListIterator<T>
{
  Iterators$11(int paramInt, Object[] paramArrayOfObject)
  {
    super(paramInt);
  }
  
  protected T get(int paramInt)
  {
    return (T)val$array[paramInt];
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Iterators.11
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */