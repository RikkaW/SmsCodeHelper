package android.support.v4.view;

import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

class ViewGroupCompat$ViewGroupCompatIcsImpl
  extends ViewGroupCompat.ViewGroupCompatHCImpl
{
  public boolean onRequestSendAccessibilityEvent(ViewGroup paramViewGroup, View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    return ViewGroupCompatIcs.onRequestSendAccessibilityEvent(paramViewGroup, paramView, paramAccessibilityEvent);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.ViewGroupCompat.ViewGroupCompatIcsImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */