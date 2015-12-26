package android.support.v4.widget;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class SwipeRefreshLayout$4
  extends Animation
{
  SwipeRefreshLayout$4(SwipeRefreshLayout paramSwipeRefreshLayout, int paramInt1, int paramInt2) {}
  
  public void applyTransformation(float paramFloat, Transformation paramTransformation)
  {
    SwipeRefreshLayout.access$100(this$0).setAlpha((int)(val$startingAlpha + (val$endingAlpha - val$startingAlpha) * paramFloat));
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.SwipeRefreshLayout.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */