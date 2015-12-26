package android.support.v7.internal.widget;

import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

class SpinnerCompat$2
  implements ViewTreeObserver.OnGlobalLayoutListener
{
  SpinnerCompat$2(SpinnerCompat paramSpinnerCompat) {}
  
  public void onGlobalLayout()
  {
    if (!SpinnerCompat.access$100(this$0).isShowing()) {
      SpinnerCompat.access$100(this$0).show();
    }
    ViewTreeObserver localViewTreeObserver = this$0.getViewTreeObserver();
    if (localViewTreeObserver != null) {
      localViewTreeObserver.removeGlobalOnLayoutListener(this);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.SpinnerCompat.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */