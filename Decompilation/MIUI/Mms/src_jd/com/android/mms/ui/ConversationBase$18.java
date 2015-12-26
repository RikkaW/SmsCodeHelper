package com.android.mms.ui;

import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class ConversationBase$18
  implements Animation.AnimationListener
{
  ConversationBase$18(ConversationBase paramConversationBase) {}
  
  public void onAnimationEnd(Animation paramAnimation)
  {
    this$0.mMessageListAnimator.setVisibility(4);
    ConversationBase.access$1700(this$0);
    this$0.mMsgListView.setVisibility(0);
    ConversationBase.access$1802(this$0, false);
    this$0.mHandler.post(new Runnable()
    {
      public void run()
      {
        this$0.applyPendingCursor();
      }
    });
  }
  
  public void onAnimationRepeat(Animation paramAnimation) {}
  
  public void onAnimationStart(Animation paramAnimation) {}
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.18
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */