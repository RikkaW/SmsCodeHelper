package android.support.v4.view.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener;
import java.util.List;

class AccessibilityManagerCompatIcs
{
  public static boolean addAccessibilityStateChangeListener(AccessibilityManager paramAccessibilityManager, Object paramObject)
  {
    return paramAccessibilityManager.addAccessibilityStateChangeListener((AccessibilityManager.AccessibilityStateChangeListener)paramObject);
  }
  
  public static List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(AccessibilityManager paramAccessibilityManager, int paramInt)
  {
    return paramAccessibilityManager.getEnabledAccessibilityServiceList(paramInt);
  }
  
  public static List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(AccessibilityManager paramAccessibilityManager)
  {
    return paramAccessibilityManager.getInstalledAccessibilityServiceList();
  }
  
  public static boolean isTouchExplorationEnabled(AccessibilityManager paramAccessibilityManager)
  {
    return paramAccessibilityManager.isTouchExplorationEnabled();
  }
  
  public static Object newAccessibilityStateChangeListener(AccessibilityStateChangeListenerBridge paramAccessibilityStateChangeListenerBridge)
  {
    return new AccessibilityManagerCompatIcs.1(paramAccessibilityStateChangeListenerBridge);
  }
  
  public static boolean removeAccessibilityStateChangeListener(AccessibilityManager paramAccessibilityManager, Object paramObject)
  {
    return paramAccessibilityManager.removeAccessibilityStateChangeListener((AccessibilityManager.AccessibilityStateChangeListener)paramObject);
  }
  
  static abstract interface AccessibilityStateChangeListenerBridge
  {
    public abstract void onAccessibilityStateChanged(boolean paramBoolean);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.accessibility.AccessibilityManagerCompatIcs
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */