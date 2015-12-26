package com.android.mms.data;

import com.google.android.collect.Sets;
import java.util.HashSet;

public final class Conversation$TimedThreads
{
  private static final HashSet<Long> mTimedThreads = ;
  
  public static void clear()
  {
    mTimedThreads.clear();
  }
  
  public static boolean hasTimedMessage(long paramLong)
  {
    return mTimedThreads.contains(Long.valueOf(paramLong));
  }
  
  public static void setHasTimedMessage(long paramLong)
  {
    mTimedThreads.add(Long.valueOf(paramLong));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.Conversation.TimedThreads
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */