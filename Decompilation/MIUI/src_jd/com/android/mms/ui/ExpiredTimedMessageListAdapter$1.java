package com.android.mms.ui;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

class ExpiredTimedMessageListAdapter$1
  extends LinkedHashMap<Long, MessageItem>
{
  ExpiredTimedMessageListAdapter$1(ExpiredTimedMessageListAdapter paramExpiredTimedMessageListAdapter, int paramInt, float paramFloat, boolean paramBoolean)
  {
    super(paramInt, paramFloat, paramBoolean);
  }
  
  protected boolean removeEldestEntry(Map.Entry paramEntry)
  {
    return size() > 50;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ExpiredTimedMessageListAdapter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */