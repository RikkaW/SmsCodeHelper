/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.view.accessibility;

import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompatJellyBean$1;
import java.util.List;

class AccessibilityNodeProviderCompatJellyBean {
    AccessibilityNodeProviderCompatJellyBean() {
    }

    public static Object newAccessibilityNodeProviderBridge(AccessibilityNodeInfoBridge accessibilityNodeInfoBridge) {
        return new AccessibilityNodeProviderCompatJellyBean$1(accessibilityNodeInfoBridge);
    }

    static interface AccessibilityNodeInfoBridge {
        public Object createAccessibilityNodeInfo(int var1);

        public List<Object> findAccessibilityNodeInfosByText(String var1, int var2);

        public boolean performAction(int var1, int var2, Bundle var3);
    }

}

