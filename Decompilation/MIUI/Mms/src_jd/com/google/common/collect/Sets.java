package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public final class Sets
{
  static boolean equalsImpl(Set<?> paramSet, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1 = false;
    if (paramSet == paramObject) {
      bool1 = true;
    }
    while (!(paramObject instanceof Set)) {
      return bool1;
    }
    paramObject = (Set)paramObject;
    try
    {
      if (paramSet.size() == ((Set)paramObject).size())
      {
        bool1 = paramSet.containsAll((Collection)paramObject);
        if (!bool1) {}
      }
      for (bool1 = bool2;; bool1 = false) {
        return bool1;
      }
      return false;
    }
    catch (NullPointerException paramSet)
    {
      return false;
    }
    catch (ClassCastException paramSet) {}
  }
  
  static int hashCodeImpl(Set<?> paramSet)
  {
    int i = 0;
    paramSet = paramSet.iterator();
    if (paramSet.hasNext())
    {
      Object localObject = paramSet.next();
      if (localObject != null) {}
      for (int j = localObject.hashCode();; j = 0)
      {
        i += j;
        break;
      }
    }
    return i;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Sets
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */