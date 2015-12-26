package org.apache.thrift;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class TBaseHelper$NestedStructureComparator
  implements Comparator
{
  public int compare(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == null) && (paramObject2 == null)) {
      return 0;
    }
    if (paramObject1 == null) {
      return -1;
    }
    if (paramObject2 == null) {
      return 1;
    }
    if ((paramObject1 instanceof List)) {
      return TBaseHelper.compareTo((List)paramObject1, (List)paramObject2);
    }
    if ((paramObject1 instanceof Set)) {
      return TBaseHelper.compareTo((Set)paramObject1, (Set)paramObject2);
    }
    if ((paramObject1 instanceof Map)) {
      return TBaseHelper.compareTo((Map)paramObject1, (Map)paramObject2);
    }
    if ((paramObject1 instanceof byte[])) {
      return TBaseHelper.compareTo((byte[])paramObject1, (byte[])paramObject2);
    }
    return TBaseHelper.compareTo((Comparable)paramObject1, (Comparable)paramObject2);
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.TBaseHelper.NestedStructureComparator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */