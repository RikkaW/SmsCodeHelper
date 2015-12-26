package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityEvent;

class AccessibilityEventCompat$AccessibilityEventKitKatImpl
  extends AccessibilityEventCompat.AccessibilityEventIcsImpl
{
  public int getContentChangeTypes(AccessibilityEvent paramAccessibilityEvent)
  {
    return AccessibilityEventCompatKitKat.getContentChangeTypes(paramAccessibilityEvent);
  }
  
  public void setContentChangeTypes(AccessibilityEvent paramAccessibilityEvent, int paramInt)
  {
    AccessibilityEventCompatKitKat.setContentChangeTypes(paramAccessibilityEvent, paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.accessibility.AccessibilityEventCompat.AccessibilityEventKitKatImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */