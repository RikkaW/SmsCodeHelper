package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import java.util.Iterator;

public final class Iterables
{
  public static <T> T getOnlyElement(Iterable<T> paramIterable)
  {
    return (T)Iterators.getOnlyElement(paramIterable.iterator());
  }
  
  public static String toString(Iterable<?> paramIterable)
  {
    return Iterators.toString(paramIterable.iterator());
  }
  
  public static <F, T> Iterable<T> transform(Iterable<F> paramIterable, final Function<? super F, ? extends T> paramFunction)
  {
    Preconditions.checkNotNull(paramIterable);
    Preconditions.checkNotNull(paramFunction);
    new IterableWithToString()
    {
      public Iterator<T> iterator()
      {
        return Iterators.transform(val$fromIterable.iterator(), paramFunction);
      }
    };
  }
  
  static abstract class IterableWithToString<E>
    implements Iterable<E>
  {
    public String toString()
    {
      return Iterables.toString(this);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Iterables
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */