package com.google.common.collect;

import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import java.util.Map;
import java.util.Map.Entry;

public final class Maps
{
  static final Joiner.MapJoiner STANDARD_JOINER = Collections2.STANDARD_JOINER.withKeyValueSeparator("=");
  
  public static <K, V> Map.Entry<K, V> immutableEntry(K paramK, V paramV)
  {
    return new ImmutableEntry(paramK, paramV);
  }
  
  static String toStringImpl(Map<?, ?> paramMap)
  {
    StringBuilder localStringBuilder = Collections2.newStringBuilderForCollection(paramMap.size()).append('{');
    STANDARD_JOINER.appendTo(localStringBuilder, paramMap);
    return '}';
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Maps
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */