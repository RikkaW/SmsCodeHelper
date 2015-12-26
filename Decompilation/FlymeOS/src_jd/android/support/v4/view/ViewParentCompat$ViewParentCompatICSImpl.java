package android.support.v4.view;

import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

class ViewParentCompat$ViewParentCompatICSImpl
  extends ViewParentCompat.ViewParentCompatStubImpl
{
  public boolean requestSendAccessibilityEvent(ViewParent paramViewParent, View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    return ViewParentCompatICS.requestSendAccessibilityEvent(paramViewParent, paramView, paramAccessibilityEvent);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.ViewParentCompat.ViewParentCompatICSImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */