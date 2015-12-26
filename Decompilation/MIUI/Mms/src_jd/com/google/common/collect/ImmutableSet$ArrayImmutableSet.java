package com.google.common.collect;

import java.util.Collection;

abstract class ImmutableSet$ArrayImmutableSet<E>
  extends ImmutableSet<E>
{
  final transient Object[] elements;
  
  ImmutableSet$ArrayImmutableSet(Object[] paramArrayOfObject)
  {
    elements = paramArrayOfObject;
  }
  
  public boolean containsAll(Collection<?> paramCollection)
  {
    if (paramCollection == this) {}
    for (;;)
    {
      return true;
      if (!(paramCollection instanceof ArrayImmutableSet)) {
        return super.containsAll(paramCollection);
      }
      if (paramCollection.size() > size()) {
        return false;
      }
      paramCollection = elements;
      int j = paramCollection.length;
      int i = 0;
      while (i < j)
      {
        if (!contains(paramCollection[i])) {
          return false;
        }
        i += 1;
      }
    }
  }
  
  public boolean isEmpty()
  {
    return false;
  }
  
  public UnmodifiableIterator<E> iterator()
  {
    return Iterators.forArray(elements);
  }
  
  public int size()
  {
    return elements.length;
  }
  
  public Object[] toArray()
  {
    Object[] arrayOfObject = new Object[size()];
    System.arraycopy(elements, 0, arrayOfObject, 0, size());
    return arrayOfObject;
  }
  
  public <T> T[] toArray(T[] paramArrayOfT)
  {
    int i = size();
    Object localObject;
    if (paramArrayOfT.length < i) {
      localObject = ObjectArrays.newArray(paramArrayOfT, i);
    }
    for (;;)
    {
      System.arraycopy(elements, 0, localObject, 0, i);
      return (T[])localObject;
      localObject = paramArrayOfT;
      if (paramArrayOfT.length > i)
      {
        paramArrayOfT[i] = null;
        localObject = paramArrayOfT;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ImmutableSet.ArrayImmutableSet
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */