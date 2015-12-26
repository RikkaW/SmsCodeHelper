package com.google.common.collect;

import java.util.NoSuchElementException;

final class Iterators$1
  extends UnmodifiableIterator<Object>
{
  public boolean hasNext()
  {
    return false;
  }
  
  public Object next()
  {
    throw new NoSuchElementException();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Iterators.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */