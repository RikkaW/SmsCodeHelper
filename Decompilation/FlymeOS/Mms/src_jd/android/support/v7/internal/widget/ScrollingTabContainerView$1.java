package android.support.v7.internal.widget;

import android.view.View;

class ScrollingTabContainerView$1
  implements Runnable
{
  ScrollingTabContainerView$1(ScrollingTabContainerView paramScrollingTabContainerView, View paramView, int paramInt) {}
  
  public void run()
  {
    int i = val$tabView.getLeft();
    int j = (this$0.getWidth() - val$tabView.getWidth()) / 2;
    this$0.smoothScrollTo(i - j, 0);
    ScrollingTabContainerView.access$100(this$0).animateIndicatorToPosition(val$position, 300);
    this$0.mTabSelector = null;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ScrollingTabContainerView.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */