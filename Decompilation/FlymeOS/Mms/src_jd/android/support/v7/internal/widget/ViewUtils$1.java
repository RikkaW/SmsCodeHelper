package android.support.v7.internal.widget;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Method;

final class ViewUtils$1
  implements Runnable
{
  ViewUtils$1(View paramView, Handler paramHandler) {}
  
  public void run()
  {
    try
    {
      Method localMethod = val$view.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
      if (!localMethod.isAccessible()) {
        localMethod.setAccessible(true);
      }
      ViewUtils.access$102(localMethod);
      val$handler.postAtFrontOfQueue(new ViewUtils.1.1(this));
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ViewUtils.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */