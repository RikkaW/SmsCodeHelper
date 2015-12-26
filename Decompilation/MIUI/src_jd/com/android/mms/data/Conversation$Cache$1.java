package com.android.mms.data;

import java.util.Comparator;

final class Conversation$Cache$1
  implements Comparator<Conversation>
{
  public int compare(Conversation paramConversation1, Conversation paramConversation2)
  {
    return Long.signum(paramConversation2.getDate() - paramConversation1.getDate());
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.Conversation.Cache.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */