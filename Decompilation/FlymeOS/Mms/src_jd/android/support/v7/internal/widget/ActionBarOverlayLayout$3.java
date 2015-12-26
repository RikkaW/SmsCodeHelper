package android.support.v7.internal.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;

class ActionBarOverlayLayout$3
  implements Runnable
{
  ActionBarOverlayLayout$3(ActionBarOverlayLayout paramActionBarOverlayLayout) {}
  
  public void run()
  {
    ActionBarOverlayLayout.access$300(this$0);
    ActionBarOverlayLayout.access$002(this$0, ViewCompat.animate(ActionBarOverlayLayout.access$500(this$0)).translationY(0.0F).setListener(ActionBarOverlayLayout.access$400(this$0)));
    if ((ActionBarOverlayLayout.access$600(this$0) != null) && (ActionBarOverlayLayout.access$600(this$0).getVisibility() != 8)) {
      ActionBarOverlayLayout.access$202(this$0, ViewCompat.animate(ActionBarOverlayLayout.access$600(this$0)).translationY(0.0F).setListener(ActionBarOverlayLayout.access$700(this$0)));
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ActionBarOverlayLayout.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */