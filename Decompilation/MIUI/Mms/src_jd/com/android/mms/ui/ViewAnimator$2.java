package com.android.mms.ui;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.view.View;
import java.util.concurrent.atomic.AtomicBoolean;

class ViewAnimator$2
  implements Animator.AnimatorListener
{
  ViewAnimator$2(ViewAnimator paramViewAnimator) {}
  
  public void onAnimationCancel(Animator paramAnimator) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    ViewAnimator.access$002(this$0, 0);
    ViewAnimator.access$200(this$0).set(false);
    paramAnimator.removeAllListeners();
    ViewAnimator.access$100(this$0).invalidate();
    if ((ViewAnimator.access$100(this$0) instanceof ConversationListItem)) {
      ((ConversationListItem)ViewAnimator.access$100(this$0)).onAnimationDone();
    }
  }
  
  public void onAnimationRepeat(Animator paramAnimator) {}
  
  public void onAnimationStart(Animator paramAnimator) {}
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ViewAnimator.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */