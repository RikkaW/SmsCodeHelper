package com.android.mms.ui;

import android.content.Context;
import com.android.mms.data.Conversation;
import miui.app.ProgressDialog;

class ConversationFragment$DeleteThreadListener$1
  implements Runnable
{
  ConversationFragment$DeleteThreadListener$1(ConversationFragment.DeleteThreadListener paramDeleteThreadListener) {}
  
  public void run()
  {
    ConversationFragment.access$1302(ProgressDialog.show(ConversationFragment.DeleteThreadListener.access$1400(this$0), null, ConversationFragment.DeleteThreadListener.access$1400(this$0).getString(2131362131), true, false));
    if (ConversationFragment.DeleteThreadListener.access$1500(this$0) == null)
    {
      Conversation.startDeleteAll(ConversationFragment.DeleteThreadListener.access$1600(this$0), 1801, ConversationFragment.DeleteThreadListener.access$1700(this$0));
      return;
    }
    Conversation.startDelete(ConversationFragment.DeleteThreadListener.access$1600(this$0), 1801, ConversationFragment.DeleteThreadListener.access$1700(this$0), ConversationFragment.DeleteThreadListener.access$1500(this$0));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.DeleteThreadListener.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */