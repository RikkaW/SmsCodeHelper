package com.google.common.collect;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class Iterators$2
  implements Iterator<Object>
{
  public boolean hasNext()
  {
    return false;
  }
  
  public Object next()
  {
    throw new NoSuchElementException();
  }
  
  public void remove()
  {
    throw new IllegalStateException();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Iterators.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */