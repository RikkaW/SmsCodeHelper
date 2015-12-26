package com.android.mms.ui;

import android.os.Handler;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.PopupWindow;

class AttachmentView$3
  implements ViewTreeObserver.OnGlobalLayoutListener
{
  AttachmentView$3(AttachmentView paramAttachmentView) {}
  
  public void onGlobalLayout()
  {
    AttachmentView.access$000(this$0).post(new Runnable()
    {
      public void run()
      {
        if ((AttachmentView.access$100(this$0) != null) && (AttachmentView.access$100(this$0).isShowing())) {
          this$0.showPopup();
        }
      }
    });
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentView.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */