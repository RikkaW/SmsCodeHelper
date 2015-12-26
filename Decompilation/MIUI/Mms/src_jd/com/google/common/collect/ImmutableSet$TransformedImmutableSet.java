package com.google.common.collect;

abstract class ImmutableSet$TransformedImmutableSet<D, E>
  extends ImmutableSet<E>
{
  final int hashCode;
  final D[] source;
  
  ImmutableSet$TransformedImmutableSet(D[] paramArrayOfD, int paramInt)
  {
    source = paramArrayOfD;
    hashCode = paramInt;
  }
  
  public final int hashCode()
  {
    return hashCode;
  }
  
  public boolean isEmpty()
  {
    return false;
  }
  
  boolean isHashCodeFast()
  {
    return true;
  }
  
  public UnmodifiableIterator<E> iterator()
  {
    new AbstractIndexedListIterator(source.length)
    {
      protected E get(int paramAnonymousInt)
      {
        return (E)transform(source[paramAnonymousInt]);
      }
    };
  }
  
  public int size()
  {
    return source.length;
  }
  
  public Object[] toArray()
  {
    return toArray(new Object[size()]);
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
      i = 0;
      while (i < source.length)
      {
        localObject[i] = transform(source[i]);
        i += 1;
      }
      localObject = paramArrayOfT;
      if (paramArrayOfT.length > i)
      {
        paramArrayOfT[i] = null;
        localObject = paramArrayOfT;
      }
    }
    return (T[])localObject;
  }
  
  abstract E transform(D paramD);
}

/* Location:
 * Qualified Name:     com.google.common.collect.ImmutableSet.TransformedImmutableSet
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */