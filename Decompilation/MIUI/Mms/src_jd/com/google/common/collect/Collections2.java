package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Iterator;

public final class Collections2
{
  static final Joiner STANDARD_JOINER = Joiner.on(", ");
  
  static boolean containsAllImpl(Collection<?> paramCollection1, Collection<?> paramCollection2)
  {
    Preconditions.checkNotNull(paramCollection1);
    paramCollection2 = paramCollection2.iterator();
    while (paramCollection2.hasNext()) {
      if (!paramCollection1.contains(paramCollection2.next())) {
        return false;
      }
    }
    return true;
  }
  
  static StringBuilder newStringBuilderForCollection(int paramInt)
  {
    if (paramInt >= 0) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool, "size must be non-negative");
      return new StringBuilder((int)Math.min(paramInt * 8L, 1073741824L));
    }
  }
  
  static String toStringImpl(Collection<?> paramCollection)
  {
    StringBuilder localStringBuilder = newStringBuilderForCollection(paramCollection.size()).append('[');
    STANDARD_JOINER.appendTo(localStringBuilder, Iterables.transform(paramCollection, new Function()
    {
      public Object apply(Object paramAnonymousObject)
      {
        Object localObject = paramAnonymousObject;
        if (paramAnonymousObject == val$collection) {
          localObject = "(this Collection)";
        }
        return localObject;
      }
    }));
    return ']';
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Collections2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */