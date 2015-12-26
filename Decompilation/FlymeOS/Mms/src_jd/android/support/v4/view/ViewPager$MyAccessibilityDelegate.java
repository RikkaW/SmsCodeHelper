package android.support.v4.view;

import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

class ViewPager$MyAccessibilityDelegate
  extends AccessibilityDelegateCompat
{
  ViewPager$MyAccessibilityDelegate(ViewPager paramViewPager) {}
  
  private boolean canScroll()
  {
    return (ViewPager.access$200(this$0) != null) && (ViewPager.access$200(this$0).getCount() > 1);
  }
  
  public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
    paramAccessibilityEvent.setClassName(ViewPager.class.getName());
    paramView = AccessibilityRecordCompat.obtain();
    paramView.setScrollable(canScroll());
    if ((paramAccessibilityEvent.getEventType() == 4096) && (ViewPager.access$200(this$0) != null))
    {
      paramView.setItemCount(ViewPager.access$200(this$0).getCount());
      paramView.setFromIndex(ViewPager.access$300(this$0));
      paramView.setToIndex(ViewPager.access$300(this$0));
    }
  }
  
  public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
  {
    super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
    paramAccessibilityNodeInfoCompat.setClassName(ViewPager.class.getName());
    paramAccessibilityNodeInfoCompat.setScrollable(canScroll());
    if (this$0.canScrollHorizontally(1)) {
      paramAccessibilityNodeInfoCompat.addAction(4096);
    }
    if (this$0.canScrollHorizontally(-1)) {
      paramAccessibilityNodeInfoCompat.addAction(8192);
    }
  }
  
  public boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle)
  {
    if (super.performAccessibilityAction(paramView, paramInt, paramBundle)) {
      return true;
    }
    switch (paramInt)
    {
    default: 
      return false;
    case 4096: 
      if (this$0.canScrollHorizontally(1))
      {
        this$0.setCurrentItem(ViewPager.access$300(this$0) + 1);
        return true;
      }
      return false;
    }
    if (this$0.canScrollHorizontally(-1))
    {
      this$0.setCurrentItem(ViewPager.access$300(this$0) - 1);
      return true;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.ViewPager.MyAccessibilityDelegate
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */