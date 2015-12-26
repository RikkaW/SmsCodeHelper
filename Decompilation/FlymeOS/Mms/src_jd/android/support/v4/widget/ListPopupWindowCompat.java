package android.support.v4.widget;

import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnTouchListener;

public class ListPopupWindowCompat
{
  static final ListPopupWindowImpl IMPL = new BaseListPopupWindowImpl();
  
  static
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      IMPL = new KitKatListPopupWindowImpl();
      return;
    }
  }
  
  public static View.OnTouchListener createDragToOpenListener(Object paramObject, View paramView)
  {
    return IMPL.createDragToOpenListener(paramObject, paramView);
  }
  
  static class BaseListPopupWindowImpl
    implements ListPopupWindowCompat.ListPopupWindowImpl
  {
    public View.OnTouchListener createDragToOpenListener(Object paramObject, View paramView)
    {
      return null;
    }
  }
  
  static class KitKatListPopupWindowImpl
    extends ListPopupWindowCompat.BaseListPopupWindowImpl
  {
    public View.OnTouchListener createDragToOpenListener(Object paramObject, View paramView)
    {
      return ListPopupWindowCompatKitKat.createDragToOpenListener(paramObject, paramView);
    }
  }
  
  static abstract interface ListPopupWindowImpl
  {
    public abstract View.OnTouchListener createDragToOpenListener(Object paramObject, View paramView);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.ListPopupWindowCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */