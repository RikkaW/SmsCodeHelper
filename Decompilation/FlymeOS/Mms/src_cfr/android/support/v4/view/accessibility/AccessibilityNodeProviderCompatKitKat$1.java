/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.view.accessibility.AccessibilityNodeInfo
 *  android.view.accessibility.AccessibilityNodeProvider
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.view.accessibility;

import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompatKitKat;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

final class AccessibilityNodeProviderCompatKitKat$1
extends AccessibilityNodeProvider {
    final /* synthetic */ AccessibilityNodeProviderCompatKitKat.AccessibilityNodeInfoBridge val$bridge;

    AccessibilityNodeProviderCompatKitKat$1(AccessibilityNodeProviderCompatKitKat.AccessibilityNodeInfoBridge accessibilityNodeInfoBridge) {
        this.val$bridge = accessibilityNodeInfoBridge;
    }

    public AccessibilityNodeInfo createAccessibilityNodeInfo(int n2) {
        return (AccessibilityNodeInfo)this.val$bridge.createAccessibilityNodeInfo(n2);
    }

    public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String string2, int n2) {
        return this.val$bridge.findAccessibilityNodeInfosByText(string2, n2);
    }

    public AccessibilityNodeInfo findFocus(int n2) {
        return (AccessibilityNodeInfo)this.val$bridge.findFocus(n2);
    }

    public boolean performAction(int n2, int n3, Bundle bundle) {
        return this.val$bridge.performAction(n2, n3, bundle);
    }
}

