package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Ordering<T>
  implements Comparator<T>
{
  static class ArbitraryOrdering
    extends Ordering<Object>
  {
    private Map<Object, Integer> uids = Platform.tryWeakKeys(new MapMaker()).makeComputingMap(new Function()
    {
      final AtomicInteger counter = new AtomicInteger(0);
      
      public Integer apply(Object paramAnonymousObject)
      {
        return Integer.valueOf(counter.getAndIncrement());
      }
    });
    
    public int compare(Object paramObject1, Object paramObject2)
    {
      if (paramObject1 == paramObject2) {
        return 0;
      }
      int i = identityHashCode(paramObject1);
      int j = identityHashCode(paramObject2);
      if (i != j)
      {
        if (i < j) {
          return -1;
        }
        return 1;
      }
      i = ((Integer)uids.get(paramObject1)).compareTo((Integer)uids.get(paramObject2));
      if (i == 0) {
        throw new AssertionError();
      }
      return i;
    }
    
    int identityHashCode(Object paramObject)
    {
      return System.identityHashCode(paramObject);
    }
    
    public String toString()
    {
      return "Ordering.arbitrary()";
    }
  }
  
  static class IncomparableValueException
    extends ClassCastException
  {
    private static final long serialVersionUID = 0L;
    final Object value;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Ordering
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */