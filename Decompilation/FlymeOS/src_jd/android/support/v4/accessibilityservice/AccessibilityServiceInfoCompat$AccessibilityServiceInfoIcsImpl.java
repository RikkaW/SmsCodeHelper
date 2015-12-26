package android.support.v4.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.ResolveInfo;

class AccessibilityServiceInfoCompat$AccessibilityServiceInfoIcsImpl
  extends AccessibilityServiceInfoCompat.AccessibilityServiceInfoStubImpl
{
  public boolean getCanRetrieveWindowContent(AccessibilityServiceInfo paramAccessibilityServiceInfo)
  {
    return AccessibilityServiceInfoCompatIcs.getCanRetrieveWindowContent(paramAccessibilityServiceInfo);
  }
  
  public int getCapabilities(AccessibilityServiceInfo paramAccessibilityServiceInfo)
  {
    if (getCanRetrieveWindowContent(paramAccessibilityServiceInfo)) {
      return 1;
    }
    return 0;
  }
  
  public String getDescription(AccessibilityServiceInfo paramAccessibilityServiceInfo)
  {
    return AccessibilityServiceInfoCompatIcs.getDescription(paramAccessibilityServiceInfo);
  }
  
  public String getId(AccessibilityServiceInfo paramAccessibilityServiceInfo)
  {
    return AccessibilityServiceInfoCompatIcs.getId(paramAccessibilityServiceInfo);
  }
  
  public ResolveInfo getResolveInfo(AccessibilityServiceInfo paramAccessibilityServiceInfo)
  {
    return AccessibilityServiceInfoCompatIcs.getResolveInfo(paramAccessibilityServiceInfo);
  }
  
  public String getSettingsActivityName(AccessibilityServiceInfo paramAccessibilityServiceInfo)
  {
    return AccessibilityServiceInfoCompatIcs.getSettingsActivityName(paramAccessibilityServiceInfo);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.accessibilityservice.AccessibilityServiceInfoCompat.AccessibilityServiceInfoIcsImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */