package com.meizu.common.widget;

import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.RelativeLayout;

class StretchSearchView$6
  implements ViewTreeObserver.OnPreDrawListener
{
  StretchSearchView$6(StretchSearchView paramStretchSearchView) {}
  
  public boolean onPreDraw()
  {
    StretchSearchView.access$500(this$0).getViewTreeObserver().removeOnPreDrawListener(this);
    this$0.onInitLayout();
    if (StretchSearchView.access$600(this$0)) {
      this$0.startStretch();
    }
    return true;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.StretchSearchView.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */