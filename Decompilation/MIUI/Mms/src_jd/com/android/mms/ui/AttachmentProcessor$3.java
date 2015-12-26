package com.android.mms.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

class AttachmentProcessor$3
  implements View.OnClickListener
{
  AttachmentProcessor$3(AttachmentProcessor paramAttachmentProcessor) {}
  
  public void onClick(View paramView)
  {
    paramView = new Intent(AttachmentProcessor.access$100(this$0), PhraseActivity.class);
    paramView.setAction("android.intent.action.PICK_ACTIVITY");
    AttachmentProcessor.access$100(this$0).startActivityForResult(paramView, 113);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentProcessor.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */