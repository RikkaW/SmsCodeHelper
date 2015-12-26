package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.NoSuchElementException;

abstract class AbstractIndexedListIterator<E>
  extends UnmodifiableListIterator<E>
{
  private int position;
  private final int size;
  
  protected AbstractIndexedListIterator(int paramInt)
  {
    this(paramInt, 0);
  }
  
  protected AbstractIndexedListIterator(int paramInt1, int paramInt2)
  {
    Preconditions.checkPositionIndex(paramInt2, paramInt1);
    size = paramInt1;
    position = paramInt2;
  }
  
  protected abstract E get(int paramInt);
  
  public final boolean hasNext()
  {
    return position < size;
  }
  
  public final boolean hasPrevious()
  {
    return position > 0;
  }
  
  public final E next()
  {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    int i = position;
    position = (i + 1);
    return (E)get(i);
  }
  
  public final int nextIndex()
  {
    return position;
  }
  
  public final E previous()
  {
    if (!hasPrevious()) {
      throw new NoSuchElementException();
    }
    int i = position - 1;
    position = i;
    return (E)get(i);
  }
  
  public final int previousIndex()
  {
    return position - 1;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.AbstractIndexedListIterator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */