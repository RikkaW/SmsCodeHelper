package com.android.mms.ui;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.data.Conversation;

class BlockedConversationActivity$1
  implements View.OnClickListener
{
  BlockedConversationActivity$1(BlockedConversationActivity paramBlockedConversationActivity) {}
  
  public void onClick(View paramView)
  {
    if (BlockedConversationActivity.access$000(this$0))
    {
      paramView = new Intent("android.intent.action.VIEW");
      paramView.putExtra("number", BlockedConversationActivity.access$100(this$0));
      paramView.putExtra("is_from_blocked", true);
      paramView.putExtra("reply_address", BlockedConversationActivity.access$100(this$0));
      paramView.putExtra("thread_id", this$0.mConversation.getThreadId());
      paramView.setPackage("com.android.mms");
      ComposeMessageRouterActivity.processIntent(this$0, paramView);
      this$0.startActivity(paramView);
      return;
    }
    new AlertDialog.Builder(this$0).setTitle(2131361924).setMessage(2131361923).setPositiveButton(2131361924, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        BlockedConversationActivity.access$200(this$0, BlockedConversationActivity.access$100(this$0));
      }
    }).setNegativeButton(17039360, null).show();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.BlockedConversationActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */