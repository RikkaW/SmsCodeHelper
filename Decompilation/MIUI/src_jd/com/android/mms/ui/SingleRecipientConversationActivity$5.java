package com.android.mms.ui;

import android.view.View;
import android.view.View.OnClickListener;
import miui.yellowpage.ModuleIntent;

class SingleRecipientConversationActivity$5
  implements View.OnClickListener
{
  SingleRecipientConversationActivity$5(SingleRecipientConversationActivity paramSingleRecipientConversationActivity, ModuleIntent paramModuleIntent) {}
  
  public void onClick(View paramView)
  {
    if (val$entry.getSubItemsFlag())
    {
      SingleRecipientConversationActivity.access$300(this$0, paramView, val$entry);
      return;
    }
    SingleRecipientConversationActivity.access$400(this$0, val$entry);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SingleRecipientConversationActivity.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */