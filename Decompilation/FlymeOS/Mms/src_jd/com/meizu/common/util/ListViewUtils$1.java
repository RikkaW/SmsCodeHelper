package com.meizu.common.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.widget.ListView;

class ListViewUtils$1
  extends AnimatorListenerAdapter
{
  ListViewUtils$1(ListViewUtils paramListViewUtils, ListViewUtils.OnListViewFadeListener paramOnListViewFadeListener, int paramInt1, int paramInt2, ListView paramListView, int paramInt3) {}
  
  public void onAnimationEnd(Animator paramAnimator)
  {
    if (val$startPos == val$endPos) {
      ListViewUtils.access$000(this$0, val$listView, val$listView.getChildAt(val$startPos - val$firstVisiblePosition), val$onListViewFadeListener);
    }
    for (;;)
    {
      int i = val$startPos;
      while (i <= val$endPos)
      {
        paramAnimator = val$listView.getChildAt(i - val$firstVisiblePosition);
        if (paramAnimator != null)
        {
          paramAnimator.setTranslationX(0.0F);
          paramAnimator.setAlpha(1.0F);
        }
        i += 1;
      }
      if (val$onListViewFadeListener != null) {
        val$onListViewFadeListener.onEndListViewFadedOut();
      }
    }
  }
  
  public void onAnimationStart(Animator paramAnimator)
  {
    if (val$onListViewFadeListener != null) {
      val$onListViewFadeListener.onStartListViewFadeOut();
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.ListViewUtils.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */