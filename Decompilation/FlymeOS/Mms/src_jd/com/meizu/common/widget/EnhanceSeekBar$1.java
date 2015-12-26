package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class EnhanceSeekBar$1
  extends AnimatorListenerAdapter
{
  EnhanceSeekBar$1(EnhanceSeekBar paramEnhanceSeekBar) {}
  
  public void onAnimationCancel(Animator paramAnimator)
  {
    if (EnhanceSeekBar.access$100(this$0) != null) {
      EnhanceSeekBar.access$100(this$0).onProgressChanged(this$0, this$0.getProgress());
    }
  }
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    if (EnhanceSeekBar.access$100(this$0) != null) {
      EnhanceSeekBar.access$100(this$0).onProgressChanged(this$0, this$0.getProgress());
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.EnhanceSeekBar.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */