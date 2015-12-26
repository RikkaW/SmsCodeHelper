package com.android.mms.data;

import com.android.mms.util.DraftCache;

class WorkingMessage$2
  implements Runnable
{
  WorkingMessage$2(WorkingMessage paramWorkingMessage, Conversation paramConversation, String paramString) {}
  
  public void run()
  {
    try
    {
      DraftCache.getInstance().setSavingDraft(true);
      val$conv.ensureThreadId();
      val$conv.setDraftState(true);
      WorkingMessage.access$900(this$0, val$conv, val$contents);
      return;
    }
    finally
    {
      DraftCache.getInstance().setSavingDraft(false);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.WorkingMessage.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */