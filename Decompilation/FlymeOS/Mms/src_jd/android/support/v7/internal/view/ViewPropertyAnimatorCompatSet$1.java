package android.support.v7.internal.view;

import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import java.util.ArrayList;

class ViewPropertyAnimatorCompatSet$1
  extends ViewPropertyAnimatorListenerAdapter
{
  private int mProxyEndCount = 0;
  private boolean mProxyStarted = false;
  
  ViewPropertyAnimatorCompatSet$1(ViewPropertyAnimatorCompatSet paramViewPropertyAnimatorCompatSet) {}
  
  public void onAnimationEnd(View paramView)
  {
    int i = mProxyEndCount + 1;
    mProxyEndCount = i;
    if (i == ViewPropertyAnimatorCompatSet.access$200(this$0).size())
    {
      if (ViewPropertyAnimatorCompatSet.access$000(this$0) != null) {
        ViewPropertyAnimatorCompatSet.access$000(this$0).onAnimationEnd(null);
      }
      onEnd();
    }
  }
  
  public void onAnimationStart(View paramView)
  {
    if (mProxyStarted) {}
    do
    {
      return;
      mProxyStarted = true;
    } while (ViewPropertyAnimatorCompatSet.access$000(this$0) == null);
    ViewPropertyAnimatorCompatSet.access$000(this$0).onAnimationStart(null);
  }
  
  void onEnd()
  {
    mProxyEndCount = 0;
    mProxyStarted = false;
    ViewPropertyAnimatorCompatSet.access$100(this$0);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.view.ViewPropertyAnimatorCompatSet.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */