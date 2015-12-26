package com.android.mms.ui;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

class AttachmentView$1
  implements View.OnClickListener
{
  AttachmentView$1(AttachmentView paramAttachmentView) {}
  
  public void onClick(View paramView)
  {
    Message.obtain(AttachmentView.access$000(this$0), paramView.getId()).sendToTarget();
    AttachmentView.access$000(this$0).post(new Runnable()
    {
      public void run()
      {
        this$0.dismissPopup();
      }
    });
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentView.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */