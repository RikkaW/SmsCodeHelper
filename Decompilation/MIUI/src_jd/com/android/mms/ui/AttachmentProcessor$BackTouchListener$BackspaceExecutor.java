package com.android.mms.ui;

import android.os.Handler;

class AttachmentProcessor$BackTouchListener$BackspaceExecutor
  implements Runnable
{
  private int mMessageToken;
  
  AttachmentProcessor$BackTouchListener$BackspaceExecutor(AttachmentProcessor.BackTouchListener paramBackTouchListener, int paramInt)
  {
    mMessageToken = paramInt;
  }
  
  public void run()
  {
    if (mMessageToken == AttachmentProcessor.BackTouchListener.access$000(this$1))
    {
      access$100this$1.this$0).mHandler.postDelayed(this, 100L);
      if (AttachmentProcessor.BackTouchListener.access$200(this$1)) {
        AttachmentProcessor.access$300(this$1.this$0);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentProcessor.BackTouchListener.BackspaceExecutor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */