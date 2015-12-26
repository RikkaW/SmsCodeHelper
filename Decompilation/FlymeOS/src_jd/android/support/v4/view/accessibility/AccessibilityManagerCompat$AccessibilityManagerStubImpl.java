package android.support.v4.view.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityManager;
import java.util.Collections;
import java.util.List;

class AccessibilityManagerCompat$AccessibilityManagerStubImpl
  implements AccessibilityManagerCompat.AccessibilityManagerVersionImpl
{
  public boolean addAccessibilityStateChangeListener(AccessibilityManager paramAccessibilityManager, AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat paramAccessibilityStateChangeListenerCompat)
  {
    return false;
  }
  
  public List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(AccessibilityManager paramAccessibilityManager, int paramInt)
  {
    return Collections.emptyList();
  }
  
  public List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(AccessibilityManager paramAccessibilityManager)
  {
    return Collections.emptyList();
  }
  
  public boolean isTouchExplorationEnabled(AccessibilityManager paramAccessibilityManager)
  {
    return false;
  }
  
  public Object newAccessiblityStateChangeListener(AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat paramAccessibilityStateChangeListenerCompat)
  {
    return null;
  }
  
  public boolean removeAccessibilityStateChangeListener(AccessibilityManager paramAccessibilityManager, AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat paramAccessibilityStateChangeListenerCompat)
  {
    return false;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.accessibility.AccessibilityManagerCompat.AccessibilityManagerStubImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */