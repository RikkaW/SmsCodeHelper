package android.support.v7.internal.widget;

import android.support.v7.app.ActionBar.Tab;
import android.view.View;
import android.view.View.OnClickListener;

class ScrollingTabContainerView$TabClickListener
  implements View.OnClickListener
{
  private ScrollingTabContainerView$TabClickListener(ScrollingTabContainerView paramScrollingTabContainerView) {}
  
  public void onClick(View paramView)
  {
    ((ScrollingTabContainerView.TabView)paramView).getTab().select();
    int j = ScrollingTabContainerView.access$100(this$0).getChildCount();
    int i = 0;
    if (i < j)
    {
      View localView = ScrollingTabContainerView.access$100(this$0).getChildAt(i);
      if (localView == paramView) {}
      for (boolean bool = true;; bool = false)
      {
        localView.setSelected(bool);
        i += 1;
        break;
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ScrollingTabContainerView.TabClickListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */