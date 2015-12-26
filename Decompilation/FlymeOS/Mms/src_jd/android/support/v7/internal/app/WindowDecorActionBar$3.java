package android.support.v7.internal.app;

import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.support.v7.internal.widget.ActionBarContainer;
import android.view.View;

class WindowDecorActionBar$3
  implements ViewPropertyAnimatorUpdateListener
{
  WindowDecorActionBar$3(WindowDecorActionBar paramWindowDecorActionBar) {}
  
  public void onAnimationUpdate(View paramView)
  {
    ((View)WindowDecorActionBar.access$200(this$0).getParent()).invalidate();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.app.WindowDecorActionBar.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */