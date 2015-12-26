/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.accessibility.AccessibilityEvent
 *  java.lang.Object
 */
package android.support.v4.view;

import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.AccessibilityDelegateCompatIcs;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

class AccessibilityDelegateCompat$AccessibilityDelegateIcsImpl$1
implements AccessibilityDelegateCompatIcs.AccessibilityDelegateBridge {
    final /* synthetic */ AccessibilityDelegateCompat.AccessibilityDelegateIcsImpl this$0;
    final /* synthetic */ AccessibilityDelegateCompat val$compat;

    AccessibilityDelegateCompat$AccessibilityDelegateIcsImpl$1(AccessibilityDelegateCompat.AccessibilityDelegateIcsImpl accessibilityDelegateIcsImpl, AccessibilityDelegateCompat accessibilityDelegateCompat) {
        this.this$0 = accessibilityDelegateIcsImpl;
        this.val$compat = accessibilityDelegateCompat;
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        return this.val$compat.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @Override
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.val$compat.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    @Override
    public void onInitializeAccessibilityNodeInfo(View view, Object object) {
        this.val$compat.onInitializeAccessibilityNodeInfo(view, new AccessibilityNodeInfoCompat(object));
    }

    @Override
    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.val$compat.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @Override
    public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.val$compat.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    @Override
    public void sendAccessibilityEvent(View view, int n2) {
        this.val$compat.sendAccessibilityEvent(view, n2);
    }

    @Override
    public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        this.val$compat.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}

