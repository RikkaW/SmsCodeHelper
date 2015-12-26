package com.xiaomi.mms.data;

import com.xiaomi.mms.utils.FixedSizeMap;

public class RecentMessageCache
{
  private static final Object PRESENT = new Object();
  private static final FixedSizeMap<String, Object> mMap = new FixedSizeMap(20, 20);
  
  public static void add(String paramString)
  {
    try
    {
      mMap.put(paramString, PRESENT);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public static boolean contain(String paramString)
  {
    try
    {
      boolean bool = mMap.containsKey(paramString);
      return bool;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.data.RecentMessageCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */