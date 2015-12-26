package com.ted.sdk.ivr;

import android.util.LruCache;
import android.util.Pair;

public enum IvrCache
{
  INSTANCE;
  
  private static final int CACHE_SIZE = 1000;
  private static final int SPECIAL_CACHE_SIZE = 100;
  private final LruCache<Pair<String, String>, HotNumber> mCacheMap = new LruCache(1000);
  private final LruCache<Pair<String, String>, HotNumber> mCmCacheMap = new LruCache(100);
  private final LruCache<Pair<String, String>, HotNumber> mCtCacheMap = new LruCache(100);
  private final LruCache<Pair<String, String>, HotNumber> mCuCacheMap = new LruCache(100);
  private boolean mIsLoaded;
  
  public HotNumber get(int paramInt, Pair<String, String> paramPair)
  {
    HotNumber localHotNumber1 = null;
    switch (paramInt)
    {
    }
    for (;;)
    {
      HotNumber localHotNumber2 = localHotNumber1;
      if (localHotNumber1 == null) {
        localHotNumber2 = (HotNumber)mCacheMap.get(paramPair);
      }
      return localHotNumber2;
      localHotNumber1 = (HotNumber)mCmCacheMap.get(paramPair);
      continue;
      localHotNumber1 = (HotNumber)mCuCacheMap.get(paramPair);
      continue;
      localHotNumber1 = (HotNumber)mCtCacheMap.get(paramPair);
    }
  }
  
  public boolean isLoaded()
  {
    return mIsLoaded;
  }
  
  public void put(int paramInt, Pair<String, String> paramPair, HotNumber paramHotNumber)
  {
    mIsLoaded = true;
    LruCache localLruCache = mCacheMap;
    switch (paramInt)
    {
    }
    for (;;)
    {
      localLruCache.put(paramPair, paramHotNumber);
      return;
      localLruCache = mCmCacheMap;
      continue;
      localLruCache = mCuCacheMap;
      continue;
      localLruCache = mCtCacheMap;
    }
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.ivr.IvrCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */