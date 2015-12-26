package com.android.mms.ui;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;

class MessageEditableActivityBase$26$1
  implements LinearAnimator.FrameHandler
{
  MessageEditableActivityBase$26$1(MessageEditableActivityBase.26 param26) {}
  
  public void onEnd()
  {
    this$1.this$0.mMessageContentPanel.getLayoutParams().height = -2;
    this$1.this$0.mMessageContentPanel.requestLayout();
    this$1.this$0.mMessageContentPanel.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
    {
      public boolean onPreDraw()
      {
        this$1.this$0.onResetMessageAnimationEnd();
        this$1.this$0.mMessageContentPanel.getViewTreeObserver().removeOnPreDrawListener(this);
        return true;
      }
    });
  }
  
  public void onFrame(float paramFloat)
  {
    this$1.this$0.mMessageContentPanel.getLayoutParams().height = ((int)paramFloat);
    this$1.this$0.mMessageContentPanel.requestLayout();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.26.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */