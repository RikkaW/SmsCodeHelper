package android.support.v4.view.accessibility;

import android.view.View;

class AccessibilityNodeInfoCompat$AccessibilityNodeInfoApi22Impl
  extends AccessibilityNodeInfoCompat.AccessibilityNodeInfoApi21Impl
{
  public AccessibilityNodeInfoCompat getTraversalAfter(Object paramObject)
  {
    paramObject = AccessibilityNodeInfoCompatApi22.getTraversalAfter(paramObject);
    if (paramObject == null) {
      return null;
    }
    return new AccessibilityNodeInfoCompat(paramObject);
  }
  
  public AccessibilityNodeInfoCompat getTraversalBefore(Object paramObject)
  {
    paramObject = AccessibilityNodeInfoCompatApi22.getTraversalBefore(paramObject);
    if (paramObject == null) {
      return null;
    }
    return new AccessibilityNodeInfoCompat(paramObject);
  }
  
  public void setTraversalAfter(Object paramObject, View paramView)
  {
    AccessibilityNodeInfoCompatApi22.setTraversalAfter(paramObject, paramView);
  }
  
  public void setTraversalAfter(Object paramObject, View paramView, int paramInt)
  {
    AccessibilityNodeInfoCompatApi22.setTraversalAfter(paramObject, paramView, paramInt);
  }
  
  public void setTraversalBefore(Object paramObject, View paramView)
  {
    AccessibilityNodeInfoCompatApi22.setTraversalBefore(paramObject, paramView);
  }
  
  public void setTraversalBefore(Object paramObject, View paramView, int paramInt)
  {
    AccessibilityNodeInfoCompatApi22.setTraversalBefore(paramObject, paramView, paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityNodeInfoApi22Impl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */