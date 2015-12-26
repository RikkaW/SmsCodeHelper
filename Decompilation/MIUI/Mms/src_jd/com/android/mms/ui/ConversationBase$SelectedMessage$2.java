package com.android.mms.ui;

import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;

class ConversationBase$SelectedMessage$2
  implements View.OnClickListener
{
  ConversationBase$SelectedMessage$2(ConversationBase.SelectedMessage paramSelectedMessage, int paramInt) {}
  
  public void onClick(View paramView)
  {
    ConversationBase.SelectedMessage.access$3400(this$1, val$slotId);
    if (ConversationBase.access$3300(this$1.this$0) != null)
    {
      ConversationBase.access$3300(this$1.this$0).dismiss();
      ConversationBase.access$3302(this$1.this$0, null);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.SelectedMessage.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */