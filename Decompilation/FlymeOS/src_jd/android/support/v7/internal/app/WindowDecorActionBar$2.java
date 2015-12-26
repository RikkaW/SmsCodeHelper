package android.support.v7.internal.app;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v7.internal.widget.ActionBarContainer;
import android.view.View;

class WindowDecorActionBar$2
  extends ViewPropertyAnimatorListenerAdapter
{
  WindowDecorActionBar$2(WindowDecorActionBar paramWindowDecorActionBar) {}
  
  public void onAnimationEnd(View paramView)
  {
    WindowDecorActionBar.access$402(this$0, null);
    WindowDecorActionBar.access$200(this$0).requestLayout();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.app.WindowDecorActionBar.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */