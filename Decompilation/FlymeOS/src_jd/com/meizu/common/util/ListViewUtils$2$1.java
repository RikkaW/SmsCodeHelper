package com.meizu.common.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class ListViewUtils$2$1
  extends AnimatorListenerAdapter
{
  ListViewUtils$2$1(ListViewUtils.2 param2) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    if (this$1.val$onListViewFadeListener != null) {
      this$1.val$onListViewFadeListener.onEndResetListView();
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.ListViewUtils.2.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */