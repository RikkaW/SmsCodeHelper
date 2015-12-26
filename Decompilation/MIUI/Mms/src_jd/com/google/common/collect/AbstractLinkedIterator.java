package com.google.common.collect;

import java.util.NoSuchElementException;

public abstract class AbstractLinkedIterator<T>
  extends UnmodifiableIterator<T>
{
  private T nextOrNull;
  
  protected AbstractLinkedIterator(T paramT)
  {
    nextOrNull = paramT;
  }
  
  protected abstract T computeNext(T paramT);
  
  public final boolean hasNext()
  {
    return nextOrNull != null;
  }
  
  public final T next()
  {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    try
    {
      Object localObject1 = nextOrNull;
      return (T)localObject1;
    }
    finally
    {
      nextOrNull = computeNext(nextOrNull);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.AbstractLinkedIterator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */