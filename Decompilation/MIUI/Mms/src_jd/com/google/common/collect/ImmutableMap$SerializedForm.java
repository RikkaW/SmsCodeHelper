package com.google.common.collect;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map.Entry;

class ImmutableMap$SerializedForm
  implements Serializable
{
  private static final long serialVersionUID = 0L;
  private final Object[] keys;
  private final Object[] values;
  
  ImmutableMap$SerializedForm(ImmutableMap<?, ?> paramImmutableMap)
  {
    keys = new Object[paramImmutableMap.size()];
    values = new Object[paramImmutableMap.size()];
    int i = 0;
    paramImmutableMap = paramImmutableMap.entrySet().iterator();
    while (paramImmutableMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramImmutableMap.next();
      keys[i] = localEntry.getKey();
      values[i] = localEntry.getValue();
      i += 1;
    }
  }
  
  Object createMap(ImmutableMap.Builder<Object, Object> paramBuilder)
  {
    int i = 0;
    while (i < keys.length)
    {
      paramBuilder.put(keys[i], values[i]);
      i += 1;
    }
    return paramBuilder.build();
  }
  
  Object readResolve()
  {
    return createMap(new ImmutableMap.Builder());
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ImmutableMap.SerializedForm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */