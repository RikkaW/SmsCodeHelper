package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;

public final class ObjectArrays
{
  private static Object[] fillArray(Iterable<?> paramIterable, Object[] paramArrayOfObject)
  {
    int i = 0;
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      paramArrayOfObject[i] = paramIterable.next();
      i += 1;
    }
    return paramArrayOfObject;
  }
  
  public static <T> T[] newArray(T[] paramArrayOfT, int paramInt)
  {
    return Platform.newArray(paramArrayOfT, paramInt);
  }
  
  static Object[] toArrayImpl(Collection<?> paramCollection)
  {
    return fillArray(paramCollection, new Object[paramCollection.size()]);
  }
  
  static <T> T[] toArrayImpl(Collection<?> paramCollection, T[] paramArrayOfT)
  {
    int i = paramCollection.size();
    Object localObject = paramArrayOfT;
    if (paramArrayOfT.length < i) {
      localObject = newArray(paramArrayOfT, i);
    }
    fillArray(paramCollection, (Object[])localObject);
    if (localObject.length > i) {
      localObject[i] = null;
    }
    return (T[])localObject;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ObjectArrays
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */