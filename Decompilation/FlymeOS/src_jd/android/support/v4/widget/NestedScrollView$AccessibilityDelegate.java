package android.support.v4.widget;

import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ScrollView;

class NestedScrollView$AccessibilityDelegate
  extends AccessibilityDelegateCompat
{
  public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
    paramView = (NestedScrollView)paramView;
    paramAccessibilityEvent.setClassName(ScrollView.class.getName());
    paramAccessibilityEvent = AccessibilityEventCompat.asRecord(paramAccessibilityEvent);
    if (NestedScrollView.access$000(paramView) > 0) {}
    for (boolean bool = true;; bool = false)
    {
      paramAccessibilityEvent.setScrollable(bool);
      paramAccessibilityEvent.setScrollX(paramView.getScrollX());
      paramAccessibilityEvent.setScrollY(paramView.getScrollY());
      paramAccessibilityEvent.setMaxScrollX(paramView.getScrollX());
      paramAccessibilityEvent.setMaxScrollY(NestedScrollView.access$000(paramView));
      return;
    }
  }
  
  public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
  {
    super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
    paramView = (NestedScrollView)paramView;
    paramAccessibilityNodeInfoCompat.setClassName(ScrollView.class.getName());
    if (paramView.isEnabled())
    {
      int i = NestedScrollView.access$000(paramView);
      if (i > 0)
      {
        paramAccessibilityNodeInfoCompat.setScrollable(true);
        if (paramView.getScrollY() > 0) {
          paramAccessibilityNodeInfoCompat.addAction(8192);
        }
        if (paramView.getScrollY() < i) {
          paramAccessibilityNodeInfoCompat.addAction(4096);
        }
      }
    }
  }
  
  public boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle)
  {
    if (super.performAccessibilityAction(paramView, paramInt, paramBundle)) {
      return true;
    }
    paramView = (NestedScrollView)paramView;
    if (!paramView.isEnabled()) {
      return false;
    }
    switch (paramInt)
    {
    default: 
      return false;
    case 4096: 
      paramInt = Math.min(paramView.getHeight() - paramView.getPaddingBottom() - paramView.getPaddingTop() + paramView.getScrollY(), NestedScrollView.access$000(paramView));
      if (paramInt != paramView.getScrollY())
      {
        paramView.smoothScrollTo(0, paramInt);
        return true;
      }
      return false;
    }
    paramInt = paramView.getHeight();
    int i = paramView.getPaddingBottom();
    int j = paramView.getPaddingTop();
    paramInt = Math.max(paramView.getScrollY() - (paramInt - i - j), 0);
    if (paramInt != paramView.getScrollY())
    {
      paramView.smoothScrollTo(0, paramInt);
      return true;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.NestedScrollView.AccessibilityDelegate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */