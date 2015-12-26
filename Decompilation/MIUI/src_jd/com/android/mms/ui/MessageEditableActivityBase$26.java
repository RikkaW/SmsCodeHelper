package com.android.mms.ui;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class MessageEditableActivityBase$26
  implements Animation.AnimationListener
{
  MessageEditableActivityBase$26(MessageEditableActivityBase paramMessageEditableActivityBase) {}
  
  public void onAnimationEnd(Animation paramAnimation)
  {
    int i = this$0.mMessageContentPanel.getHeight();
    MessageEditableActivityBase.access$1300(this$0);
    this$0.mMessageContentPanel.getLayoutParams().height = i;
    float f1 = i;
    float f2 = this$0.mMessageContentPanelMinHeight;
    if (this$0.mAllowAnimation) {}
    for (long l = 100L;; l = 0L)
    {
      LinearAnimator.start(f1, f2, l, new LinearAnimator.FrameHandler()
      {
        public void onEnd()
        {
          this$0.mMessageContentPanel.getLayoutParams().height = -2;
          this$0.mMessageContentPanel.requestLayout();
          this$0.mMessageContentPanel.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
          {
            public boolean onPreDraw()
            {
              this$0.onResetMessageAnimationEnd();
              this$0.mMessageContentPanel.getViewTreeObserver().removeOnPreDrawListener(this);
              return true;
            }
          });
        }
        
        public void onFrame(float paramAnonymousFloat)
        {
          this$0.mMessageContentPanel.getLayoutParams().height = ((int)paramAnonymousFloat);
          this$0.mMessageContentPanel.requestLayout();
        }
      });
      return;
    }
  }
  
  public void onAnimationRepeat(Animation paramAnimation) {}
  
  public void onAnimationStart(Animation paramAnimation) {}
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.26
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */