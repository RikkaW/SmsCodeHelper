/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package android.support.v4.view.accessibility;

import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.support.v4.view.accessibility.AccessibilityManagerCompatIcs;

class AccessibilityManagerCompat$AccessibilityManagerIcsImpl$1
implements AccessibilityManagerCompatIcs.AccessibilityStateChangeListenerBridge {
    final /* synthetic */ AccessibilityManagerCompat.AccessibilityManagerIcsImpl this$0;
    final /* synthetic */ AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat val$listener;

    AccessibilityManagerCompat$AccessibilityManagerIcsImpl$1(AccessibilityManagerCompat.AccessibilityManagerIcsImpl accessibilityManagerIcsImpl, AccessibilityManagerCompat.AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
        this.this$0 = accessibilityManagerIcsImpl;
        this.val$listener = accessibilityStateChangeListenerCompat;
    }

    @Override
    public void onAccessibilityStateChanged(boolean bl2) {
        this.val$listener.onAccessibilityStateChanged(bl2);
    }
}

