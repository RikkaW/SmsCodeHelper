package android.support.v4.widget;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class SwipeRefreshLayout$6
  extends Animation
{
  SwipeRefreshLayout$6(SwipeRefreshLayout paramSwipeRefreshLayout) {}
  
  public void applyTransformation(float paramFloat, Transformation paramTransformation)
  {
    if (!SwipeRefreshLayout.access$1100(this$0)) {}
    for (int i = (int)(SwipeRefreshLayout.access$1200(this$0) - Math.abs(this$0.mOriginalOffsetTop));; i = (int)SwipeRefreshLayout.access$1200(this$0))
    {
      int j = this$0.mFrom;
      i = (int)((i - this$0.mFrom) * paramFloat);
      int k = SwipeRefreshLayout.access$400(this$0).getTop();
      SwipeRefreshLayout.access$900(this$0, i + j - k, false);
      SwipeRefreshLayout.access$100(this$0).setArrowScale(1.0F - paramFloat);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.SwipeRefreshLayout.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */