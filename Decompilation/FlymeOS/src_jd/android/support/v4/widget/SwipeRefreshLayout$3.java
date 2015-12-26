package android.support.v4.widget;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class SwipeRefreshLayout$3
  extends Animation
{
  SwipeRefreshLayout$3(SwipeRefreshLayout paramSwipeRefreshLayout) {}
  
  public void applyTransformation(float paramFloat, Transformation paramTransformation)
  {
    SwipeRefreshLayout.access$700(this$0, 1.0F - paramFloat);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.SwipeRefreshLayout.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */