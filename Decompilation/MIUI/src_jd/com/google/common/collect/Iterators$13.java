package com.google.common.collect;

import java.util.NoSuchElementException;

final class Iterators$13
  extends UnmodifiableIterator<T>
{
  boolean done;
  
  Iterators$13(Object paramObject) {}
  
  public boolean hasNext()
  {
    return !done;
  }
  
  public T next()
  {
    if (done) {
      throw new NoSuchElementException();
    }
    done = true;
    return (T)val$value;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Iterators.13
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */