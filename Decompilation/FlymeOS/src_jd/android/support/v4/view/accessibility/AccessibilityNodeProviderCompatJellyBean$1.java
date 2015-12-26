package android.support.v4.view.accessibility;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

final class AccessibilityNodeProviderCompatJellyBean$1
  extends AccessibilityNodeProvider
{
  AccessibilityNodeProviderCompatJellyBean$1(AccessibilityNodeProviderCompatJellyBean.AccessibilityNodeInfoBridge paramAccessibilityNodeInfoBridge) {}
  
  public AccessibilityNodeInfo createAccessibilityNodeInfo(int paramInt)
  {
    return (AccessibilityNodeInfo)val$bridge.createAccessibilityNodeInfo(paramInt);
  }
  
  public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String paramString, int paramInt)
  {
    return val$bridge.findAccessibilityNodeInfosByText(paramString, paramInt);
  }
  
  public boolean performAction(int paramInt1, int paramInt2, Bundle paramBundle)
  {
    return val$bridge.performAction(paramInt1, paramInt2, paramBundle);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.accessibility.AccessibilityNodeProviderCompatJellyBean.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */