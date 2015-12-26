package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Iterator;

final class Iterators$8
  implements Iterator<T>
{
  Iterators$8(Iterator paramIterator, Function paramFunction) {}
  
  public boolean hasNext()
  {
    return val$fromIterator.hasNext();
  }
  
  public T next()
  {
    Object localObject = val$fromIterator.next();
    return (T)val$function.apply(localObject);
  }
  
  public void remove()
  {
    val$fromIterator.remove();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Iterators.8
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */