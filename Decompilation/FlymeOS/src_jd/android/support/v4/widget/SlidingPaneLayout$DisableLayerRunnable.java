package android.support.v4.widget;

import android.support.v4.view.ViewCompat;
import android.view.View;
import java.util.ArrayList;

class SlidingPaneLayout$DisableLayerRunnable
  implements Runnable
{
  final View mChildView;
  
  SlidingPaneLayout$DisableLayerRunnable(SlidingPaneLayout paramSlidingPaneLayout, View paramView)
  {
    mChildView = paramView;
  }
  
  public void run()
  {
    if (mChildView.getParent() == this$0)
    {
      ViewCompat.setLayerType(mChildView, 0, null);
      SlidingPaneLayout.access$1000(this$0, mChildView);
    }
    SlidingPaneLayout.access$1100(this$0).remove(this);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.SlidingPaneLayout.DisableLayerRunnable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */