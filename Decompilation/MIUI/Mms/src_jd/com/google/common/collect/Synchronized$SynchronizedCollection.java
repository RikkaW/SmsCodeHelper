package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;

class Synchronized$SynchronizedCollection<E>
  extends Synchronized.SynchronizedObject
  implements Collection<E>
{
  private static final long serialVersionUID = 0L;
  
  private Synchronized$SynchronizedCollection(Collection<E> paramCollection, Object paramObject)
  {
    super(paramCollection, paramObject);
  }
  
  public boolean add(E paramE)
  {
    synchronized (mutex)
    {
      boolean bool = delegate().add(paramE);
      return bool;
    }
  }
  
  public boolean addAll(Collection<? extends E> paramCollection)
  {
    synchronized (mutex)
    {
      boolean bool = delegate().addAll(paramCollection);
      return bool;
    }
  }
  
  public void clear()
  {
    synchronized (mutex)
    {
      delegate().clear();
      return;
    }
  }
  
  public boolean contains(Object paramObject)
  {
    synchronized (mutex)
    {
      boolean bool = delegate().contains(paramObject);
      return bool;
    }
  }
  
  public boolean containsAll(Collection<?> paramCollection)
  {
    synchronized (mutex)
    {
      boolean bool = delegate().containsAll(paramCollection);
      return bool;
    }
  }
  
  Collection<E> delegate()
  {
    return (Collection)super.delegate();
  }
  
  public boolean isEmpty()
  {
    synchronized (mutex)
    {
      boolean bool = delegate().isEmpty();
      return bool;
    }
  }
  
  public Iterator<E> iterator()
  {
    return delegate().iterator();
  }
  
  public boolean remove(Object paramObject)
  {
    synchronized (mutex)
    {
      boolean bool = delegate().remove(paramObject);
      return bool;
    }
  }
  
  public boolean removeAll(Collection<?> paramCollection)
  {
    synchronized (mutex)
    {
      boolean bool = delegate().removeAll(paramCollection);
      return bool;
    }
  }
  
  public boolean retainAll(Collection<?> paramCollection)
  {
    synchronized (mutex)
    {
      boolean bool = delegate().retainAll(paramCollection);
      return bool;
    }
  }
  
  public int size()
  {
    synchronized (mutex)
    {
      int i = delegate().size();
      return i;
    }
  }
  
  public Object[] toArray()
  {
    synchronized (mutex)
    {
      Object[] arrayOfObject = delegate().toArray();
      return arrayOfObject;
    }
  }
  
  public <T> T[] toArray(T[] paramArrayOfT)
  {
    synchronized (mutex)
    {
      paramArrayOfT = delegate().toArray(paramArrayOfT);
      return paramArrayOfT;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Synchronized.SynchronizedCollection
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */