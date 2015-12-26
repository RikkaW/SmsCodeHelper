package com.google.common.collect;

import java.util.Map.Entry;

class RegularImmutableMap$EntrySet<K, V>
  extends ImmutableSet.ArrayImmutableSet<Map.Entry<K, V>>
{
  final transient RegularImmutableMap<K, V> map;
  
  RegularImmutableMap$EntrySet(RegularImmutableMap<K, V> paramRegularImmutableMap)
  {
    super(RegularImmutableMap.access$000(paramRegularImmutableMap));
    map = paramRegularImmutableMap;
  }
  
  public boolean contains(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof Map.Entry))
    {
      paramObject = (Map.Entry)paramObject;
      Object localObject = map.get(((Map.Entry)paramObject).getKey());
      bool1 = bool2;
      if (localObject != null)
      {
        bool1 = bool2;
        if (localObject.equals(((Map.Entry)paramObject).getValue())) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.RegularImmutableMap.EntrySet
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */