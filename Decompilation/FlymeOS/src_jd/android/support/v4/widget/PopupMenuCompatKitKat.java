package android.support.v4.widget;

import android.view.View.OnTouchListener;
import android.widget.PopupMenu;

class PopupMenuCompatKitKat
{
  public static View.OnTouchListener getDragToOpenListener(Object paramObject)
  {
    return ((PopupMenu)paramObject).getDragToOpenListener();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.PopupMenuCompatKitKat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */