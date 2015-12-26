package android.support.v7.internal.widget;

import android.graphics.Rect;
import android.view.ViewTreeObserver.OnDrawListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class ActionBarContainer$1
  implements ViewTreeObserver.OnDrawListener
{
  ActionBarContainer$1(ActionBarContainer paramActionBarContainer) {}
  
  public void onDraw()
  {
    try
    {
      if ((ActionBarContainer.access$000() != null) && (ActionBarContainer.access$100() != null))
      {
        Rect localRect = (Rect)ActionBarContainer.access$000().get(ActionBarContainer.access$100().invoke(this$0, new Object[0]));
        if ((localRect != null) && (Rect.intersects(ActionBarContainer.access$200(this$0), localRect)) && (!localRect.contains(ActionBarContainer.access$200(this$0)))) {
          localRect.union(ActionBarContainer.access$200(this$0));
        }
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ActionBarContainer.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */