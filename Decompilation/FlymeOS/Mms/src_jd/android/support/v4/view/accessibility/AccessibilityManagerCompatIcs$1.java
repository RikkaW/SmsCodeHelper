package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener;

final class AccessibilityManagerCompatIcs$1
  implements AccessibilityManager.AccessibilityStateChangeListener
{
  AccessibilityManagerCompatIcs$1(AccessibilityManagerCompatIcs.AccessibilityStateChangeListenerBridge paramAccessibilityStateChangeListenerBridge) {}
  
  public void onAccessibilityStateChanged(boolean paramBoolean)
  {
    val$bridge.onAccessibilityStateChanged(paramBoolean);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.accessibility.AccessibilityManagerCompatIcs.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */