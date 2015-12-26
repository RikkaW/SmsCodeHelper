package com.android.mms.ui;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.android.mms.data.Conversation;
import com.android.mms.util.DraftCache;

class ConversationFragment$1
  extends Handler
{
  ConversationFragment$1(ConversationFragment paramConversationFragment) {}
  
  public void handleMessage(Message paramMessage)
  {
    Log.v("ConversationFragment", "handle msg on main thread, msg: " + what);
    switch (what)
    {
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return;
          } while (!ConversationFragment.access$000(this$0, this$0.mActivity));
          ConversationFragment.access$100(this$0, this$0.mActivity);
          ConversationFragment.access$200(this$0, this$0.mActivity);
          return;
        } while (ConversationFragment.access$300(this$0));
        if (((Boolean)obj).booleanValue())
        {
          ConversationFragment.access$400(this$0);
          return;
        }
        ConversationFragment.access$500(this$0);
        return;
        this$0.startAsyncQuery(true);
        return;
        if (DraftCache.getInstance().getSavingDraft())
        {
          ConversationFragment.access$600(this$0).removeMessages(1005);
          ConversationFragment.access$600(this$0).sendEmptyMessageDelayed(1005, 1000L);
          return;
        }
        Conversation.asyncDeleteObsoleteThreads(this$0.mQueryHandler, 1803);
        return;
      } while (ConversationFragment.access$300(this$0));
      ConversationFragment.access$700(this$0);
      return;
    } while (ConversationFragment.access$300(this$0));
    ConversationFragment.access$800(this$0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */