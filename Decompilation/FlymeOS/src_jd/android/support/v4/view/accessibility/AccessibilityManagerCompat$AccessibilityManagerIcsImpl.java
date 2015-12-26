package android.support.v4.view.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityManager;
import java.util.List;

class AccessibilityManagerCompat$AccessibilityManagerIcsImpl
  extends AccessibilityManagerCompat.AccessibilityManagerStubImpl
{
  public boolean addAccessibilityStateChangeListener(AccessibilityManager paramAccessibilityManager, AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat paramAccessibilityStateChangeListenerCompat)
  {
    return AccessibilityManagerCompatIcs.addAccessibilityStateChangeListener(paramAccessibilityManager, mListener);
  }
  
  public List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(AccessibilityManager paramAccessibilityManager, int paramInt)
  {
    return AccessibilityManagerCompatIcs.getEnabledAccessibilityServiceList(paramAccessibilityManager, paramInt);
  }
  
  public List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(AccessibilityManager paramAccessibilityManager)
  {
    return AccessibilityManagerCompatIcs.getInstalledAccessibilityServiceList(paramAccessibilityManager);
  }
  
  public boolean isTouchExplorationEnabled(AccessibilityManager paramAccessibilityManager)
  {
    return AccessibilityManagerCompatIcs.isTouchExplorationEnabled(paramAccessibilityManager);
  }
  
  public Object newAccessiblityStateChangeListener(AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat paramAccessibilityStateChangeListenerCompat)
  {
    return AccessibilityManagerCompatIcs.newAccessibilityStateChangeListener(new AccessibilityManagerCompat.AccessibilityManagerIcsImpl.1(this, paramAccessibilityStateChangeListenerCompat));
  }
  
  public boolean removeAccessibilityStateChangeListener(AccessibilityManager paramAccessibilityManager, AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat paramAccessibilityStateChangeListenerCompat)
  {
    return AccessibilityManagerCompatIcs.removeAccessibilityStateChangeListener(paramAccessibilityManager, mListener);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.accessibility.AccessibilityManagerCompat.AccessibilityManagerIcsImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */