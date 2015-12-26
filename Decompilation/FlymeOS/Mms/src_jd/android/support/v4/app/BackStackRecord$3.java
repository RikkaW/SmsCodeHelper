package android.support.v4.app;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;

class BackStackRecord$3
  implements ViewTreeObserver.OnPreDrawListener
{
  BackStackRecord$3(BackStackRecord paramBackStackRecord, View paramView, BackStackRecord.TransitionState paramTransitionState, int paramInt, Object paramObject) {}
  
  public boolean onPreDraw()
  {
    val$sceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
    BackStackRecord.access$300(this$0, val$state, val$containerId, val$transition);
    return true;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.BackStackRecord.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */