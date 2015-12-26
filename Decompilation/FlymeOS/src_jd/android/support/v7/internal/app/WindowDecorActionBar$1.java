package android.support.v7.internal.app;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.internal.widget.ActionBarContainer;
import android.view.View;

class WindowDecorActionBar$1
  extends ViewPropertyAnimatorListenerAdapter
{
  WindowDecorActionBar$1(WindowDecorActionBar paramWindowDecorActionBar) {}
  
  public void onAnimationEnd(View paramView)
  {
    if ((WindowDecorActionBar.access$000(this$0)) && (WindowDecorActionBar.access$100(this$0) != null))
    {
      ViewCompat.setTranslationY(WindowDecorActionBar.access$100(this$0), 0.0F);
      ViewCompat.setTranslationY(WindowDecorActionBar.access$200(this$0), 0.0F);
    }
    if (WindowDecorActionBar.access$300(this$0) != null) {
      WindowDecorActionBar.access$300(this$0).setVisibility(8);
    }
    WindowDecorActionBar.access$200(this$0).setVisibility(8);
    WindowDecorActionBar.access$200(this$0).setTransitioning(false);
    WindowDecorActionBar.access$402(this$0, null);
    this$0.completeDeferredDestroyActionMode();
    if (WindowDecorActionBar.access$500(this$0) != null) {
      ViewCompat.requestApplyInsets(WindowDecorActionBar.access$500(this$0));
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.app.WindowDecorActionBar.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */