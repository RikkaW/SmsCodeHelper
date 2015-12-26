package com.google.common.collect;

import java.util.Iterator;

public abstract class UnmodifiableIterator<E>
  implements Iterator<E>
{
  public final void remove()
  {
    throw new UnsupportedOperationException();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.UnmodifiableIterator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */