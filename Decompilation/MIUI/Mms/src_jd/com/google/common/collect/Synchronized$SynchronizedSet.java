package com.google.common.collect;

import java.util.Set;

class Synchronized$SynchronizedSet<E>
  extends Synchronized.SynchronizedCollection<E>
  implements Set<E>
{
  private static final long serialVersionUID = 0L;
  
  Synchronized$SynchronizedSet(Set<E> paramSet, Object paramObject)
  {
    super(paramSet, paramObject, null);
  }
  
  Set<E> delegate()
  {
    return (Set)super.delegate();
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    synchronized (mutex)
    {
      boolean bool = delegate().equals(paramObject);
      return bool;
    }
  }
  
  public int hashCode()
  {
    synchronized (mutex)
    {
      int i = delegate().hashCode();
      return i;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Synchronized.SynchronizedSet
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */