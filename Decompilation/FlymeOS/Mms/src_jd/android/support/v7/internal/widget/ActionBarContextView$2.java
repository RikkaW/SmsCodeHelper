package android.support.v7.internal.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;

class ActionBarContextView$2
  implements ViewTreeObserver.OnPreDrawListener
{
  ActionBarContextView$2(ActionBarContextView paramActionBarContextView, View paramView) {}
  
  public boolean onPreDraw()
  {
    val$viewToAnimate.getViewTreeObserver().removeOnPreDrawListener(this);
    float f = val$viewToAnimate.getHeight();
    ViewCompat.setTranslationY(val$viewToAnimate, f);
    ViewPropertyAnimatorCompat localViewPropertyAnimatorCompat = ViewCompat.animate(val$viewToAnimate).translationY(0.0F);
    localViewPropertyAnimatorCompat.setDuration(260L);
    localViewPropertyAnimatorCompat.setInterpolator(ActionBarContextView.access$000());
    localViewPropertyAnimatorCompat.start();
    return false;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ActionBarContextView.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */