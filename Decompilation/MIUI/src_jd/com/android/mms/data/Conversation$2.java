package com.android.mms.data;

import java.util.Iterator;
import java.util.Set;

final class Conversation$2
  implements Runnable
{
  Conversation$2(Set paramSet, long paramLong) {}
  
  public void run()
  {
    Iterator localIterator = val$threadIds.iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = (Long)localIterator.next();
      synchronized (Conversation.Cache.getInstance())
      {
        localObject2 = Conversation.Cache.get(((Long)localObject2).longValue());
        if (localObject2 != null) {
          Conversation.access$302((Conversation)localObject2, val$stickTime);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.Conversation.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */