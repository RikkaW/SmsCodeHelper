package com.meizu.common.widget;

import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

class StretchSearchView$5
  implements ViewTreeObserver.OnGlobalLayoutListener
{
  StretchSearchView$5(StretchSearchView paramStretchSearchView, ViewTreeObserver paramViewTreeObserver) {}
  
  public void onGlobalLayout()
  {
    val$vto.removeGlobalOnLayoutListener(this);
    this$0.onInitLayout();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.StretchSearchView.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */