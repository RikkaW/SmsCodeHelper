package com.google.common.collect;

import java.util.ListIterator;

public abstract class UnmodifiableListIterator<E>
  extends UnmodifiableIterator<E>
  implements ListIterator<E>
{
  public final void add(E paramE)
  {
    throw new UnsupportedOperationException();
  }
  
  public final void set(E paramE)
  {
    throw new UnsupportedOperationException();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.UnmodifiableListIterator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */