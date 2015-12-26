package android.support.v7.internal.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;

class MzSlidePopupWindow$1
  implements ViewTreeObserver.OnPreDrawListener
{
  MzSlidePopupWindow$1(MzSlidePopupWindow paramMzSlidePopupWindow) {}
  
  public boolean onPreDraw()
  {
    MzSlidePopupWindow.access$100(this$0).getViewTreeObserver().removeOnPreDrawListener(this);
    if (MzSlidePopupWindow.access$200(this$0)) {}
    for (int i = MzSlidePopupWindow.access$100(this$0).getHeight();; i = -MzSlidePopupWindow.access$100(this$0).getHeight())
    {
      MzSlidePopupWindow.access$100(this$0).setTranslationY(i);
      ViewPropertyAnimatorCompat localViewPropertyAnimatorCompat = ViewCompat.animate(MzSlidePopupWindow.access$100(this$0)).translationY(0.0F);
      localViewPropertyAnimatorCompat.setDuration(200L);
      localViewPropertyAnimatorCompat.start();
      return false;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.MzSlidePopupWindow.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */