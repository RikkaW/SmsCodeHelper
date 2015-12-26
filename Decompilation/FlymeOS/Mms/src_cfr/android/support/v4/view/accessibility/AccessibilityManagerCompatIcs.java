/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.accessibilityservice.AccessibilityServiceInfo
 *  android.view.accessibility.AccessibilityManager
 *  android.view.accessibility.AccessibilityManager$AccessibilityStateChangeListener
 *  java.lang.Object
 */
package android.support.v4.view.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.support.v4.view.accessibility.AccessibilityManagerCompatIcs$1;
import android.view.accessibility.AccessibilityManager;
import java.util.List;

class AccessibilityManagerCompatIcs {
    AccessibilityManagerCompatIcs() {
    }

    public static boolean addAccessibilityStateChangeListener(AccessibilityManager accessibilityManager, Object object) {
        return accessibilityManager.addAccessibilityStateChangeListener((AccessibilityManager.AccessibilityStateChangeListener)object);
    }

    public static List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(AccessibilityManager accessibilityManager, int n2) {
        return accessibilityManager.getEnabledAccessibilityServiceList(n2);
    }

    public static List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(AccessibilityManager accessibilityManager) {
        return accessibilityManager.getInstalledAccessibilityServiceList();
    }

    public static boolean isTouchExplorationEnabled(AccessibilityManager accessibilityManager) {
        return accessibilityManager.isTouchExplorationEnabled();
    }

    public static Object newAccessibilityStateChangeListener(AccessibilityStateChangeListenerBridge accessibilityStateChangeListenerBridge) {
        return new AccessibilityManagerCompatIcs$1(accessibilityStateChangeListenerBridge);
    }

    public static boolean removeAccessibilityStateChangeListener(AccessibilityManager accessibilityManager, Object object) {
        return accessibilityManager.removeAccessibilityStateChangeListener((AccessibilityManager.AccessibilityStateChangeListener)object);
    }

    static interface AccessibilityStateChangeListenerBridge {
        public void onAccessibilityStateChanged(boolean var1);
    }

}

