package com.android.mms.ui;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class AttachmentProcessor$BackTouchListener
  implements View.OnTouchListener
{
  private int mCurrentMessageToken = 0;
  private boolean mIsInside = false;
  
  private AttachmentProcessor$BackTouchListener(AttachmentProcessor paramAttachmentProcessor) {}
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getActionMasked())
    {
    default: 
      return false;
    case 0: 
      AttachmentProcessor.access$300(this$0);
      mCurrentMessageToken += 1;
      mIsInside = true;
      access$100this$0).mHandler.postDelayed(new BackspaceExecutor(mCurrentMessageToken), 500L);
      return false;
    case 1: 
    case 3: 
      mCurrentMessageToken += 1;
      return false;
    }
    int i = (int)paramMotionEvent.getX();
    int j = (int)paramMotionEvent.getY();
    if ((i >= 0) && (i < paramView.getWidth()) && (j >= 0) && (j < paramView.getHeight()))
    {
      mIsInside = true;
      return false;
    }
    mIsInside = false;
    return false;
  }
  
  private class BackspaceExecutor
    implements Runnable
  {
    private int mMessageToken;
    
    BackspaceExecutor(int paramInt)
    {
      mMessageToken = paramInt;
    }
    
    public void run()
    {
      if (mMessageToken == mCurrentMessageToken)
      {
        access$100this$0).mHandler.postDelayed(this, 100L);
        if (mIsInside) {
          AttachmentProcessor.access$300(this$0);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentProcessor.BackTouchListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */