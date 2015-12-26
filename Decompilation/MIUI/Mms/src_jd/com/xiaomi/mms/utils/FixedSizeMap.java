package com.xiaomi.mms.utils;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class FixedSizeMap<K, V>
  extends LinkedHashMap<K, V>
{
  private int mMaxSize;
  
  public FixedSizeMap(int paramInt1, int paramInt2)
  {
    super(paramInt1);
    mMaxSize = paramInt2;
  }
  
  protected boolean removeEldestEntry(Map.Entry<K, V> paramEntry)
  {
    return size() > mMaxSize;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.FixedSizeMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */