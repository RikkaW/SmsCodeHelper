/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.accessibility.AccessibilityManager
 *  android.view.accessibility.AccessibilityManager$AccessibilityStateChangeListener
 *  java.lang.Object
 */
package android.support.v4.view.accessibility;

import android.support.v4.view.accessibility.AccessibilityManagerCompatIcs;
import android.view.accessibility.AccessibilityManager;

final class AccessibilityManagerCompatIcs$1
implements AccessibilityManager.AccessibilityStateChangeListener {
    final /* synthetic */ AccessibilityManagerCompatIcs.AccessibilityStateChangeListenerBridge val$bridge;

    AccessibilityManagerCompatIcs$1(AccessibilityManagerCompatIcs.AccessibilityStateChangeListenerBridge accessibilityStateChangeListenerBridge) {
        this.val$bridge = accessibilityStateChangeListenerBridge;
    }

    public void onAccessibilityStateChanged(boolean bl2) {
        this.val$bridge.onAccessibilityStateChanged(bl2);
    }
}

