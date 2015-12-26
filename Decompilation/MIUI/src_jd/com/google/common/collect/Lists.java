package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.util.ArrayList;

public final class Lists
{
  static int computeArrayListCapacity(int paramInt)
  {
    if (paramInt >= 0) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool);
      return Ints.saturatedCast(5L + paramInt + paramInt / 10);
    }
  }
  
  public static <E> ArrayList<E> newArrayList()
  {
    return new ArrayList();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Lists
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */