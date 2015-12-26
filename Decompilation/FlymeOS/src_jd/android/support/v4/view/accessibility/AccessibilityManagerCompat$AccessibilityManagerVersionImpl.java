package android.support.v4.view.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityManager;
import java.util.List;

abstract interface AccessibilityManagerCompat$AccessibilityManagerVersionImpl
{
  public abstract boolean addAccessibilityStateChangeListener(AccessibilityManager paramAccessibilityManager, AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat paramAccessibilityStateChangeListenerCompat);
  
  public abstract List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(AccessibilityManager paramAccessibilityManager, int paramInt);
  
  public abstract List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(AccessibilityManager paramAccessibilityManager);
  
  public abstract boolean isTouchExplorationEnabled(AccessibilityManager paramAccessibilityManager);
  
  public abstract Object newAccessiblityStateChangeListener(AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat paramAccessibilityStateChangeListenerCompat);
  
  public abstract boolean removeAccessibilityStateChangeListener(AccessibilityManager paramAccessibilityManager, AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat paramAccessibilityStateChangeListenerCompat);
}

/* Location:
 * Qualified Name:     android.support.v4.view.accessibility.AccessibilityManagerCompat.AccessibilityManagerVersionImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */