package com.ted.android.smscard;

import android.util.LruCache;
import java.util.HashSet;
import java.util.Set;

public class SmsCardCache
  extends LruCache<Long, CardBase>
{
  private static final int CACHE_SIZE = 1024;
  private static volatile SmsCardCache sInstance;
  private final Set<Long> mQueriedNumSet = new HashSet(1024);
  
  private SmsCardCache()
  {
    super(1024);
  }
  
  public static SmsCardCache getInstance()
  {
    if (sInstance == null) {}
    try
    {
      if (sInstance == null) {
        sInstance = new SmsCardCache();
      }
      return sInstance;
    }
    finally {}
  }
  
  public void clearAll()
  {
    evictAll();
    mQueriedNumSet.clear();
  }
  
  protected void entryRemoved(boolean paramBoolean, Long paramLong, CardBase paramCardBase1, CardBase paramCardBase2)
  {
    super.entryRemoved(paramBoolean, paramLong, paramCardBase1, paramCardBase2);
    if ((mQueriedNumSet.contains(paramLong)) && (paramCardBase2 == null)) {
      mQueriedNumSet.remove(paramLong);
    }
  }
  
  public boolean isQueried(Long paramLong)
  {
    return mQueriedNumSet.contains(paramLong);
  }
  
  public void putIn(Long paramLong, CardBase paramCardBase)
  {
    if (!mQueriedNumSet.contains(paramLong)) {
      mQueriedNumSet.add(paramLong);
    }
    if ((paramLong == null) || (paramCardBase == null)) {
      return;
    }
    put(paramLong, paramCardBase);
  }
}

/* Location:
 * Qualified Name:     com.ted.android.smscard.SmsCardCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */